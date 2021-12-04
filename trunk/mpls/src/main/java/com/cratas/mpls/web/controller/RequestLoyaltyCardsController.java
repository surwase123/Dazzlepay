package com.cratas.mpls.web.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.cratas.mpls.common.utility.constant.RedirectScreenConstants;
import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.constant.PageConstant;
import com.cratas.mpls.common.utility.GroupType;
import com.cratas.mpls.service.IAppPropertiesService;
import com.cratas.mpls.service.ICustomerService;
import com.cratas.mpls.service.ILoyaltyCashbackService;
import com.cratas.mpls.service.IMerchantEmployeeService;
import com.cratas.mpls.service.IPushNotificationLogService;
import com.cratas.mpls.service.IRequestLoyaltyCardService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.MerchantDTO;
import com.cratas.mpls.web.dto.MerchantEmployeeDTO;
import com.cratas.mpls.web.dto.PushNotificationLogDTO;
import com.cratas.mpls.web.dto.RequestLoyaltyCardDTO;
import com.cratas.mpls.web.dto.UserDTO;

@Controller
@RequestMapping("requestLoyaltyCard")
public class RequestLoyaltyCardsController extends BaseController {

	private final static Logger LOGGER = LoggerFactory.getLogger(StateController.class);

	@Autowired
	private IUtilityService utilityService;
	
	@Autowired
	private IMerchantEmployeeService merchantEmployeeService;
	
	@Autowired
	private IPushNotificationLogService pushNotificationLogService;

	@Autowired
	private ILoyaltyCashbackService loyaltyCashbackService;
	
	@Autowired
    private ICustomerService customerService;

	@Autowired
	private IRequestLoyaltyCardService requestLoyaltyCardService;
	@Autowired
	private IAppPropertiesService appPropertiesService;

	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView requestLoyaltyCardPage(HttpServletRequest request, HttpServletResponse response, Model model) {
		if (checkUserAlreadyLogin(request, response)) {
			ModelAndView view = new ModelAndView(
					utilityService.getViewResolverName(request.getSession(), PageConstant.REQUEST_LOYALTY_CARD));
			MerchantDTO merchant = getSessionUserObject(request).getMerchant();
			if (merchant != null) {
				request.setAttribute(Constants.REQUEST_LOYALTY_CARD_LIST,
						loyaltyCashbackService.getLoyaltyCashbackByMId(merchant.getId()));
				request.setAttribute(Constants.ALLOCATED_LOYALTYCARD_REQUEST_LIST,requestLoyaltyCardService.getAllRequestByMerchantIdAndStatus(merchant.getId()));
				request.setAttribute(Constants.REJECTED_PENDING_ACCEPTE_LOYALTY_CARD_REQUEST_LIST, requestLoyaltyCardService.getAllPendingAndAccepetedRequestByMerchantId(merchant.getId()));
				
			}
			request.setAttribute(Constants.TRANS_CASHBACK_TYPE,
					appPropertiesService.getSystemParamValueByName(Constants.TRANS_CASHBACK_TYPE));
			Map<String, Object> requestMap = ((Model) model).asMap();
			if (null != requestMap) {
				request.setAttribute(Constants.MESSAGE, requestMap.get(Constants.MESSAGE));
				request.setAttribute(Constants.IS_SUCCESS, requestMap.get(Constants.IS_SUCCESS));
				request.setAttribute(Constants.REQUEST_LOYALTY_CARD, requestMap.get(Constants.REQUEST_LOYALTY_CARD));
				request.setAttribute(Constants.ACTION, requestMap.get(Constants.ACTION));
			}
			return view;
		}
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/view1", method = RequestMethod.POST)
	public ModelAndView requestLoyaltyCard(HttpServletRequest request, RedirectAttributes redirectAttribute,
			HttpServletResponse response, Model model, @ModelAttribute("RequestLoyaltyCardDTO") RequestLoyaltyCardDTO requestDto ) {
		if (checkUserAlreadyLogin(request, response)) {
			ModelAndView view = new ModelAndView(
					utilityService.getViewResolverName(request.getSession(), PageConstant.REQUEST_LOYALTY_CARD));
			MerchantDTO merchant = getSessionUserObject(request).getMerchant();
//			RequestLoyaltyCardDTO requestDto = new RequestLoyaltyCardDTO();
			if (merchant != null) {
				requestDto.setmId(merchant.getId());
//				requestDto.setQuantityOfCards(quantityOfCards);
//				requestDto.setShippingAddress(shippingAddress);
				
				int responseData= requestLoyaltyCardService.saveRequest(requestDto);
				if (responseData == 1) {
					redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Request Send Successfully.");
					redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS);
				}
			}
			return new ModelAndView(RedirectScreenConstants.REDIRECT_REQUEST_LOYALTY_CARD);
		}
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/rejectRequest", method = RequestMethod.POST)
	public  @ResponseBody String rejectRequest(HttpServletRequest request, RedirectAttributes redirectAttribute,
			HttpServletResponse response, Model model, @RequestParam("id") int id, @RequestParam("reason") String reason) {
		if (checkUserAlreadyLogin(request, response)) {
			RequestLoyaltyCardDTO requestDto = new RequestLoyaltyCardDTO();
				requestDto.setId(id);
				requestDto.setReason(reason);
				int responseData = requestLoyaltyCardService.rejectRequest(requestDto);
				if (responseData == 1) {
					return Constants.REJECT_REQUEST_TYPE;
				}
		}
		return Constants.FAILURE;
	}
	
	@RequestMapping(value = "/acceptRequest", method = RequestMethod.POST)
	public @ResponseBody String acceptRequest(HttpServletRequest request, RedirectAttributes redirectAttribute,
			HttpServletResponse response, Model model, @RequestParam("id") int id) {
		if (checkUserAlreadyLogin(request, response)) {
			RequestLoyaltyCardDTO requestDto = new RequestLoyaltyCardDTO();
				requestDto.setId(id);
				int responseData = requestLoyaltyCardService.approveRequest(requestDto);
				if (responseData == 0) {
					return Constants.APPROVE_REQUEST_TYPE;
				}
				else {
					return Constants.INSUFFICIENT_CARDS;
				}
		}
		return Constants.FAILURE;
	}
	
	  @RequestMapping(value = "/refreshNotification", method = { RequestMethod.GET, RequestMethod.POST })
	    public ModelAndView approve(@ModelAttribute("pushNotificationLogDTO") PushNotificationLogDTO pushNotificationLogDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
		       if (checkUserAlreadyLogin(request, response)) {
		    	    UserDTO user = getSessionUserObject(request);
		            ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.NOTIFICATION_LOAYALTY_CARD));
		            user = getUserLoginTypeObject(user);
				    if(GroupType.MERCHANT.getGroupType().equals(user.getGroupDTO().getGroupType())){
				    	user.setPushNotificationLogList(getPushNotificationByMid(user.getMerchant().getId()));
				    }else {
				    	user.setRequestLoyaltyCardsNotificationList(getRequestLoyaltyCardNotificationByMid());
				    }
		            return view;
		       }
		       return new ModelAndView("redirect:/");
	    }
	  
	  @RequestMapping(value = "/getNotificationByStatus", method = { RequestMethod.GET, RequestMethod.POST })
	    public ModelAndView getNotificationByStatus(@ModelAttribute("pushNotificationLogDTO") PushNotificationLogDTO pushNotificationLogDTO,@RequestParam("status") int status, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response,Model model) {
		       if (checkUserAlreadyLogin(request, response)) {
		    	   UserDTO user = getSessionUserObject(request);
		            ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.NOTIFICATION_LOAYALTY_CARD));
		            user = getUserLoginTypeObject(user);
				    if(GroupType.MERCHANT.getGroupType().equals(user.getGroupDTO().getGroupType())){
				    	user.setPushNotificationLogList(getPushNotificationByMid(user.getMerchant().getId()));
				    }else {
				    	if(status==0)
				    	{
				    		user.setRequestLoyaltyCardsNotificationList(getRequestLoyaltyCardNotificationByMid());
				    		user.setNotificationsList(null);
				    	}
				    	else if(status==3)
				    	{
				    		List<RequestLoyaltyCardDTO> list=new ArrayList<RequestLoyaltyCardDTO>();
				    		list=this.getNotificationByStatus(3);
				    		user.setNotificationsList(list);
				    	}
				    }
		            return view;
		       }
		       return new ModelAndView("redirect:/");
	    }
	  
	  
	  private List<PushNotificationLogDTO> getPushNotificationByMid(int id) {
			List<PushNotificationLogDTO> pushNotificationList = pushNotificationLogService.getPushNotificationByMid(id);
			List<PushNotificationLogDTO> pushNotificationLogList = new LinkedList<>();
			for (PushNotificationLogDTO pushNotificationLogDTO : pushNotificationList) {
				JSONParser parser = new JSONParser();
				try {
					JSONObject json = (JSONObject) parser.parse(pushNotificationLogDTO.getNotificationArgs());
					JSONObject data = (JSONObject) json.get("data");
					pushNotificationLogDTO.setCustomerNotificationId(Integer.valueOf(data.get("id").toString()));
					pushNotificationLogDTO.setmId(Integer.valueOf(data.get("mId").toString()));
					pushNotificationLogDTO.setcId(Integer.valueOf(data.get("cId").toString()));
					pushNotificationLogDTO.setNotificationType(data.get("notificationType").toString().replaceAll("^\"|\"$", ""));
					pushNotificationLogList.add(pushNotificationLogDTO);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			return pushNotificationLogList;
	    }
	  
	  private UserDTO getUserLoginTypeObject(UserDTO user){
		    if(GroupType.CUSTOMER.getGroupType().equals(user.getGroupDTO().getGroupType())){
		    	  user.setCustomer(customerService.getCustomerById(user.getId()));
		    }else if(GroupType.MERCHANT.getGroupType().equals(user.getGroupDTO().getGroupType())){
		    	  MerchantEmployeeDTO merchantEmployee = merchantEmployeeService.getMerchantEmployeeById(user.getId());
		    	  if(null != merchantEmployee){
		    		  user.setMerchantEmployee(merchantEmployee);
		    		  user.setMerchant(merchantService.getMerchantById(merchantEmployee.getmId()));
		    	  }
		    }
		    return user;
	    }
	  
	  private List<RequestLoyaltyCardDTO> getRequestLoyaltyCardNotificationByMid() {
			List<RequestLoyaltyCardDTO> pushNotificationList = requestLoyaltyCardService.getAllRequestByStatus();
			List<RequestLoyaltyCardDTO> requestLoyaltyCardList = new LinkedList<>();
			for (RequestLoyaltyCardDTO pushNotificationLogDTO : pushNotificationList) {
				JSONParser parser = new JSONParser();
				try {
//					JSONObject json = (JSONObject) parser.parse(pushNotificationLogDTO.getNotificationArgs());
//					JSONObject data = (JSONObject) json.get("data");
					pushNotificationLogDTO.setId(Integer.valueOf(pushNotificationLogDTO.getId()));
					pushNotificationLogDTO.setmId(Integer.valueOf(pushNotificationLogDTO.getmId()));
					pushNotificationLogDTO.setMessage(pushNotificationLogDTO.getMessage());
					requestLoyaltyCardList.add(pushNotificationLogDTO);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return requestLoyaltyCardList;
	}
	  
	  private List<RequestLoyaltyCardDTO> getNotificationByMid (){
			List<RequestLoyaltyCardDTO> pushNotificationList = requestLoyaltyCardService.getAllRequestByStatus();
			List<RequestLoyaltyCardDTO> requestLoyaltyCardList = new LinkedList<>();
			for (RequestLoyaltyCardDTO pushNotificationLogDTO : pushNotificationList) {
				JSONParser parser = new JSONParser();
				try {
//					JSONObject json = (JSONObject) parser.parse(pushNotificationLogDTO.getNotificationArgs());
//					JSONObject data = (JSONObject) json.get("data");
					pushNotificationLogDTO.setId(Integer.valueOf(pushNotificationLogDTO.getId()));
					pushNotificationLogDTO.setmId(Integer.valueOf(pushNotificationLogDTO.getmId()));
					pushNotificationLogDTO.setMessage(pushNotificationLogDTO.getMessage());
					requestLoyaltyCardList.add(pushNotificationLogDTO);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return requestLoyaltyCardList;
	}

	  private List<RequestLoyaltyCardDTO> getNotificationByStatus(int status){
			List<RequestLoyaltyCardDTO> pushNotificationList = requestLoyaltyCardService.getAllRequestByStatus(status);
			List<RequestLoyaltyCardDTO> requestLoyaltyCardList = new LinkedList<>();
			for (RequestLoyaltyCardDTO pushNotificationLogDTO : pushNotificationList) {
				JSONParser parser = new JSONParser();
				try {
//					JSONObject json = (JSONObject) parser.parse(pushNotificationLogDTO.getNotificationArgs());
//					JSONObject data = (JSONObject) json.get("data");
					pushNotificationLogDTO.setId(Integer.valueOf(pushNotificationLogDTO.getId()));
					pushNotificationLogDTO.setmId(Integer.valueOf(pushNotificationLogDTO.getmId()));
					pushNotificationLogDTO.setMessage(pushNotificationLogDTO.getMessage());
					requestLoyaltyCardList.add(pushNotificationLogDTO);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return requestLoyaltyCardList;
	}
	  
	  @RequestMapping(value = "/allocatedLoyaltyCardsNumber", method = RequestMethod.GET)
		public ModelAndView approve(HttpServletRequest request, HttpServletResponse response) {
			if (checkUserAlreadyLogin(request, response)) {
				UserDTO user = getSessionUserObject(request);
				ModelAndView view = new ModelAndView(
						utilityService.getViewResolverName(request.getSession(), "allocatedLoyaltyCardsNumber"));
				MerchantDTO merchant = getSessionUserObject(request).getMerchant();
				if (merchant != null) {
					request.setAttribute(Constants.REQUEST_LOYALTY_CARD_LIST,
							loyaltyCashbackService.getLoyaltyCashbackByMId(merchant.getId()));
					request.setAttribute("allotedLoyaltyCardNumber",requestLoyaltyCardService.getAllRequestByMerchantIdAndStatus(merchant.getId()));
				}
				return view;
			}
			return new ModelAndView("redirect:/");
		}
}
