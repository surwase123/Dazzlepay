package com.cratas.mpls.web.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.constant.PageConstant;
import com.cratas.mpls.common.utility.GroupType;
import com.cratas.mpls.common.utility.constant.InformationMsgConstants;
import com.cratas.mpls.common.utility.constant.RedirectScreenConstants;
import com.cratas.mpls.service.IAESEncryptionService;
import com.cratas.mpls.service.IBCryptService;
import com.cratas.mpls.service.ICustomerService;
import com.cratas.mpls.service.IGroupService;
import com.cratas.mpls.service.IMerchantEmployeeService;
import com.cratas.mpls.service.IMerchantService;
import com.cratas.mpls.service.IPushNotificationLogService;
import com.cratas.mpls.service.IRequestLoyaltyCardService;
import com.cratas.mpls.service.IUserLoginService;
import com.cratas.mpls.service.IUserRoleService;
import com.cratas.mpls.service.IUserService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.LoginDTO;
import com.cratas.mpls.web.dto.MerchantEmployeeDTO;
import com.cratas.mpls.web.dto.PushNotificationLogDTO;
import com.cratas.mpls.web.dto.RequestLoyaltyCardDTO;
import com.cratas.mpls.web.dto.UserDTO;

@Controller
@RequestMapping("/user")
public class LoginController extends BaseController{
	
		private final static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
		
		@Autowired
		private IUtilityService utilityService;
		
		@Autowired
		private IUserService userService;
		
		@Autowired
		private	IRequestLoyaltyCardService requestLoyaltyCardService;
		
		@Autowired
		private IBCryptService bCryptService;
		
		@Autowired
		private IAESEncryptionService aesService;
	    
	    @Autowired
	    private IUserRoleService userRoleService;
	    
	    @Autowired
	    private IGroupService groupService;
	    
	    @Autowired
	    private ICustomerService customerService;
	    
	    @Autowired 
	    private IMerchantService merchantService;
	    
	    @Autowired
		private IMerchantEmployeeService merchantEmployeeService;
	    
	    @Autowired
	    private IUserLoginService userLoginService;
	    
	    @Autowired
		private IPushNotificationLogService pushNotificationLogService;
	   
		/**
		 * This method used to authentication the user credentials
		 * @param loginDTO
		 * @return
		 */
		@RequestMapping(value = "/login", method = {RequestMethod.POST})
		public ModelAndView userLogin(@ModelAttribute("loginDTO") LoginDTO loginDTO, HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes) {
			   if(StringUtils.isNotEmpty(loginDTO.getLoginId()) && StringUtils.isNotEmpty(loginDTO.getPassword())){
				    UserDTO userDTO = userService.getUserByLoginIdMobileNo(loginDTO.getLoginId(), loginDTO.getLoginId());
				    userDTO = getUserRole(userDTO);
				    if(null != userDTO && userDTO.getIsLocked() == 0 && userDTO.getIsActive() == 1){
				    	if(!userLoginService.isPasswordExpiry(userDTO)) {
							if(isMatchedPassword(userDTO, loginDTO, request)){
								userDTO.setLastSessionId(request.getSession().getId());
								userService.updateLastLoginDetail(userDTO);
								request.getSession().setAttribute(Constants.IS_LOGGED_ON, Constants.STR_TRUE);
								request.getSession().setAttribute(Constants.LOGIN_DTO, userDTO);
								return new ModelAndView(redirectScreenName(userDTO));
							}else{
								String msg = userLoginService.getWebLoginUnSuccessfulAttemptMsg(userDTO, loginDTO, request);
								attributes.addFlashAttribute(Constants.MESSAGE, msg);
								return new ModelAndView("redirect:/");
							}
				    	}else {
				        	 request.getSession().setAttribute(Constants.LOGIN_DTO, userDTO);
				        	 attributes.addFlashAttribute(Constants.MESSAGE, InformationMsgConstants.EXPIRED_ACCOUNT_MSG);
					    	 return new ModelAndView(RedirectScreenConstants.REDIRECT_SET_NEW_PASSWORD);
				        }
				    }else if(null != userDTO){
				    	String userLoginStatus = userLoginService.userLoginStatusMessage(userDTO);
				    	LOGGER.info("User Login Status --> LoginId -->" + loginDTO.getLoginId() + " --> " + userLoginStatus);
				    	attributes.addFlashAttribute(Constants.MESSAGE, userLoginStatus);
				    	return new ModelAndView("redirect:/");
				    }
			   }
			   attributes.addFlashAttribute("message", InformationMsgConstants.LOGIN_ERROR_MSG);
			   return new ModelAndView("redirect:/");
		}
		
		@RequestMapping(value = "/reset-password", method = RequestMethod.GET)
		public ModelAndView resetPassword(HttpServletRequest request, HttpServletResponse response) {
			   ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.RESET_PASSWORD));
			   return view;
		}		
		
		@RequestMapping(value = "/set/new/password", method = RequestMethod.GET)
		public ModelAndView resetExpirePassword(HttpServletRequest request, HttpServletResponse response) {
			   UserDTO user = getSessionUserObject(request);
			   if(null != user){
				   ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.RESET_EXPIRE_PASSWORD));
				   String encryptedLoginId = bCryptService.getEncryptedString(user.getLoginId());
		    	   request.setAttribute(Constants.ID, user.getId());
				   request.setAttribute(Constants.LOGIN_ENCRYPTED_ID, encryptedLoginId);
				   request.setAttribute(Constants.AES.toLowerCase(), aesService.getAesDTO());
				   return view;
			   }
			   return new ModelAndView("redirect:/");
		}
		
		@RequestMapping(value = "/change-password/{id}/{loginId}", method = RequestMethod.GET)
		public ModelAndView changePassword(@PathVariable("id") String id, @PathVariable("loginId") String loginId, HttpServletRequest request, RedirectAttributes attributes) {
			   if(StringUtils.isNotEmpty(loginId)){
			       loginId = new String(aesService.base64Decode(loginId));
				   UserDTO userDTO = userService.getUserbyId(utilityService.convertStringToInt(id));
				   if(null != userDTO){
					   String encryptedLoginId = bCryptService.getEncryptedString(userDTO.getLoginId());
					   if(bCryptService.isMatch(loginId, userDTO.getLoginId())){
						       request.setAttribute(Constants.ID, userDTO.getId());
						       request.setAttribute(Constants.USER_ID, encryptedLoginId);
						       request.setAttribute(Constants.AES.toLowerCase(), aesService.getAesDTO());
							   ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.CHANGE_PASSWORD));
							   return view;
					   }
				   }
			   }
			   attributes.addFlashAttribute("message", "Invalid Login Id.");
			   return new ModelAndView("redirect:/");
		}
		
		
		@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
		public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
			   request.getSession().invalidate();
			   return new ModelAndView("redirect:/");
		}
		
		@RequestMapping(value = "/merchant/register", method = RequestMethod.GET)
		public ModelAndView userRegister(HttpServletRequest request, HttpServletResponse response, Model model) {
			   ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.MERCHANT_REG));
	           Map<String, Object> requestMap = ((Model) model).asMap();
	           if (null != requestMap) {
	                request.setAttribute(Constants.MESSAGE, requestMap.get(Constants.MESSAGE));
	                request.setAttribute(Constants.IS_SUCCESS, requestMap.get(Constants.IS_SUCCESS));
	                request.setAttribute(Constants.ACTION, requestMap.get(Constants.ACTION));
	           }
	           setMerchantRequestAttribute(request, requestMap);
			   return view;
		}
		
		
		/**
		 * Get User Role and Menu List by Group
		 * @param user
		 * @return
		 */
		private UserDTO getUserRole(UserDTO user){
			    if(null != user && StringUtils.isNotEmpty(user.getGroupId())){
			    	user.setGroupDTO(groupService.getGroupById(user.getGroupId()));
				    user.setUserRoleList(userRoleService.getUserRoleById(user.getGroupId()));
				    user.setMenuList(userService.getMenuListByUser(user.getGroupId(), user.getUserRoleList()));
				    user.setNotificationList(userService.getUserNotification(user));
				    user.setMakerNotificationList(userService.getUserRequest(user));
				    user = getUserLoginTypeObject(user);
				    if(GroupType.CUSTOMER.getGroupType().equals(user.getGroupDTO().getGroupType())){
				    	user.setPushNotificationLogList(getPushNotificationByCid(user.getCustomer().getId()));
				    }else if(GroupType.MERCHANT.getGroupType().equals(user.getGroupDTO().getGroupType())){
				    	user.setPushNotificationLogList(getPushNotificationByMid(user.getMerchant().getId()));
				    }
				    else {
				    	user.setRequestLoyaltyCardsNotificationList(getRequestLoyaltyCardNotificationByMid());
				    }
			    }
			    return user;
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
						pushNotificationLogDTO.setmId(Integer.valueOf(id));
						pushNotificationLogDTO.setcId(Integer.valueOf(data.get("cId").toString()));
						pushNotificationLogDTO.setNotificationType(data.get("notificationType").toString().replaceAll("^\"|\"$", ""));
						pushNotificationLogList.add(pushNotificationLogDTO);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				return pushNotificationLogList;
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

		private List<PushNotificationLogDTO> getPushNotificationByCid(int id) {
				List<PushNotificationLogDTO> pushNotificationList = pushNotificationLogService.getPushNotificationByCid(id);
				List<PushNotificationLogDTO> pushNotificationLogList = new LinkedList<>();
				for (PushNotificationLogDTO pushNotificationLogDTO : pushNotificationList) {
					JSONParser parser = new JSONParser();
					try {
						JSONObject json = (JSONObject) parser.parse(pushNotificationLogDTO.getNotificationArgs());
						JSONObject data = (JSONObject) json.get("data");
						pushNotificationLogDTO.setCustomerNotificationId(Integer.valueOf(data.get("id").toString()));
						pushNotificationLogDTO.setmId(Integer.valueOf(data.get("mId").toString()));
						pushNotificationLogDTO.setcId(id);
						pushNotificationLogDTO.setNotificationType(data.get("notificationType").toString().replaceAll("^\"|\"$", ""));
						pushNotificationLogList.add(pushNotificationLogDTO);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				return pushNotificationLogList;
		}
		
		private List<PushNotificationLogDTO> getPushNotification() {
			List<PushNotificationLogDTO> pushNotificationList = pushNotificationLogService.getPushNotification();
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
		
		private boolean isMatchedPassword(UserDTO userDTO, LoginDTO loginDTO, HttpServletRequest request) {
				String salt = utilityService.generateSalt(userDTO, request);
				String iv = utilityService.generateIV(userDTO, request);
				return bCryptService.isMatch(userDTO.getPassword(), aesService.decrypt(salt, iv, loginDTO.getLoginId(), loginDTO.getPassword()));
	    }

}
