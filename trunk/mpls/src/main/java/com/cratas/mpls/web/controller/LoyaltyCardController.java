package com.cratas.mpls.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.constant.PageConstant;
import com.cratas.mpls.common.utility.constant.RedirectScreenConstants;
import com.cratas.mpls.service.IAppPropertiesService;
import com.cratas.mpls.service.ILoyaltyCardService;
import com.cratas.mpls.service.ILoyaltyCashbackService;
import com.cratas.mpls.service.IMerchantService;
import com.cratas.mpls.service.IRequestLoyaltyCardService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.LoyaltyNumberGenerationRequestDTO;
import com.cratas.mpls.web.dto.MerchantDTO;
import com.cratas.mpls.web.dto.PushNotificationLogDTO;
import com.cratas.mpls.web.dto.RequestLoyaltyCardDTO;
import com.cratas.mpls.web.dto.UserDTO;

@Controller
@RequestMapping("loyaltyCard")
public class LoyaltyCardController extends BaseController {

	private final static Logger LOGGER = LoggerFactory.getLogger(StateController.class);

	@Autowired
	private IUtilityService utilityService;

	@Autowired
	private ILoyaltyCardService loyaltyCardService;
	@Autowired
	private IRequestLoyaltyCardService requestLoyaltyCardService;
	
	@Autowired
	   private IMerchantService merchantService;
	
	@Autowired
	private IAppPropertiesService appPropertiesService; 
	
	@RequestMapping(value = "/view", method = RequestMethod.GET)
	public ModelAndView loyaltyCard(HttpServletRequest request, HttpServletResponse response, Model model) {
		   if(checkUserAlreadyLogin(request, response)){
				ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.LOYALTY_CARD));
				MerchantDTO merchant = getSessionUserObject(request).getMerchant();
				if(merchant != null){
//				    request.setAttribute(Constants.LOYALTY_CARD_LIST, loyaltyCashbackService.getLoyaltyCashbackByMId(merchant.getId()));
				}
				request.setAttribute(Constants.TRANS_CASHBACK_TYPE, appPropertiesService.getSystemParamValueByName(Constants.TRANS_CASHBACK_TYPE));
				Map<String, Object> requestMap = ((Model) model).asMap();
		        if(null != requestMap){
		        	List<MerchantDTO> merchantList = merchantService.getAllMerchant();
				List<LoyaltyNumberGenerationRequestDTO> generatedLoyaltyNumberList = loyaltyCardService
						.getAllGeneratedLoyaltyNumber();
				List<RequestLoyaltyCardDTO> pushNotificationList = requestLoyaltyCardService.getAllRequestByStatus(3);
				request.setAttribute(Constants.MERCHANT_LIST, merchantList);
				request.setAttribute("generatedLoyaltyNumberList", generatedLoyaltyNumberList);
				request.setAttribute("pushNotificationList", pushNotificationList);

		        	request.setAttribute(Constants.LOYALTY_NUMBER, requestMap.get(Constants.LOYALTY_NUMBER));
		        	request.setAttribute(Constants.MESSAGE, requestMap.get(Constants.MESSAGE));
		        	request.setAttribute(Constants.IS_SUCCESS, requestMap.get(Constants.IS_SUCCESS));
		        	request.setAttribute(Constants.LOYALTY_CARD, requestMap.get(Constants.LOYALTY_CARD));
		        	request.setAttribute(Constants.ACTION, requestMap.get(Constants.ACTION));
		        	request.setAttribute(Constants.LOYALTY_NUMBER_LIST, loyaltyCardService.getLoyaltyNumber());
		        }
				return view;
		   }
		   return new ModelAndView("redirect:/");
	}

     @RequestMapping(value = "/generateNumbers", method = RequestMethod.POST)
     public ModelAndView fileUpload(RedirectAttributes redirectAttribute,@RequestParam("noOfCardsGenerate") int amount,HttpServletRequest request, HttpServletResponse response) throws IOException {
	int flag= loyaltyCardService.saveFile(amount);
    	if(flag==0)
    	{
    		  redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Numbers Generated Successfully.");
			   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
    	}
    	else if(flag==2) {
    		 redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Number Already Exist .");
			   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
    	}
    	else if(flag==1) {
    		  redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Exceed Number Limit.");
			  redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
    	}
    	 return  new ModelAndView(RedirectScreenConstants.REDIRECT_LOYALTYCARD);
     }
     
     @RequestMapping(value = "/merchantcards/details/{mId}", method = RequestMethod.POST)
 	public ModelAndView merchantCustomerDetails(@PathVariable("mId") String mId, HttpServletRequest request,
 			HttpServletResponse response) {
 		ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(),PageConstant.LOYALTY_MERCHANT_DROPDOWN_RESULT));
 		if (StringUtils.isNotEmpty(mId)) {
 			int merchantId = utilityService.convertStringToInt(mId);
 			request.setAttribute(Constants.CUSTOMER_LIST,
 					requestLoyaltyCardService.getAllRequestByMerchantIdAndStatus(merchantId));
 			return view;
 		}
 		return null;
 	}
     
            @RequestMapping(value = "/notificationLoyaltyCard", method = {RequestMethod.POST,RequestMethod.GET})
 			public ModelAndView loyaltyCard1(@ModelAttribute("requestLoyaltyCardDTO") RequestLoyaltyCardDTO requestLoyaltyCardDTO,HttpServletRequest request, HttpServletResponse response, Model model) {
//            	 ModelAndView view= new ModelAndView(utilityService.getViewResolverName(request.getSession(), "notificationLoyaltyCard"));
//				        
//				 return  view;
//             }
        
            if (checkUserAlreadyLogin(request, response)) {
	    	    UserDTO user = getSessionUserObject(request);
	            ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), "notificationLoyaltyCard"));
	            return  view;
	            }
            return new ModelAndView("redirect:/");
	            }
            
            @RequestMapping(value = "/downloadCsv", method = {RequestMethod.POST,RequestMethod.GET})
 			public void downloadCsv(@RequestParam("id") int id,HttpServletRequest request, HttpServletResponse response, Model model) throws IOException  {
            	
            	loyaltyCardService.genrateCsv(id,response);
    	    }
            
            @RequestMapping(value = "/downloadcsvbyrid", method = {RequestMethod.POST,RequestMethod.GET})
 			public void downloadAllocatedCsv(@RequestParam("id") int id,@RequestParam("mId") int mId,HttpServletRequest request, HttpServletResponse response, Model model) throws IOException  {
            	
            	loyaltyCardService.genrateCsvByMerchantIdRquestId(mId,id,response);
    	    }
//             @RequestMapping(value = "/view", method = RequestMethod.POST)
//  			public ModelAndView loyaltyCard2(HttpServletRequest request, HttpServletResponse response, Model model) {
//            	 ModelAndView view= new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.LOYALTY_CARD));
//            	 return  view;
//             }
}