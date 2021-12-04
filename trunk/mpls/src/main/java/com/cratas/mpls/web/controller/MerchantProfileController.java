package com.cratas.mpls.web.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.constant.PageConstant;
import com.cratas.mpls.common.utility.constant.RedirectScreenConstants;
import com.cratas.mpls.service.IAESEncryptionService;
import com.cratas.mpls.service.IAppPropertiesService;
import com.cratas.mpls.service.IMerchantEmployeeService;
import com.cratas.mpls.service.IMerchantMappingService;
import com.cratas.mpls.service.IMerchantProfileService;
import com.cratas.mpls.service.IMerchantService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.MerchantAddressDTO;
import com.cratas.mpls.web.dto.MerchantDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author bhupendra
 *
 */
@Controller
@RequestMapping("/merchant/profile")
public class MerchantProfileController extends BaseController{
		
	    private final static Logger LOGGER = LoggerFactory.getLogger(MerchantProfileController.class);
	
		@Autowired
		private IUtilityService utilityService;
		
		@Autowired
		private IMerchantProfileService merchantProfileService;
		
		@Autowired
		private IMerchantService merchantService;
		
		@Autowired
		private IMerchantEmployeeService merchantEmployeeService;
		
		@Autowired
		private IMerchantMappingService merchantMappingService;

	    @Autowired
	    private IAESEncryptionService aesService;
	    	    
	    @Autowired
	    private IAppPropertiesService appProperiesService;
	
		@RequestMapping(value = "/view", method = RequestMethod.GET)
		public ModelAndView country(HttpServletRequest request, HttpServletResponse response, Model model) {
			   if(checkUserAlreadyLogin(request, response)){
					ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.MERCHANT_PROFILE));
					UserDTO userDTO = getSessionUserObject(request);
					setRequestAttribute(request, userDTO.getMerchant());
					return view;
			   }
			   return new ModelAndView("redirect:/");
		}
		
		@RequestMapping(value = "/getMerchantProfile/{mId}", method = RequestMethod.POST)
	    public ModelAndView getMerchantList(@PathVariable("mId") String mId, HttpServletRequest request, HttpServletResponse response) {
	    	   ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.MERCHANT_DETAILS));
	    	   if(StringUtils.isNotEmpty(mId)) {	
	    		   int merchantId = utilityService.convertStringToInt(mId) ;
	    		   setRequestAttribute(request, merchantId);
				   return view;
	    	   }
	    	   return null;
	    }
		
		@RequestMapping(value = "/update", method = RequestMethod.GET)
	    public ModelAndView RegistrationView(HttpServletRequest request, HttpServletResponse response, Model model) {
		       if(checkUserAlreadyLogin(request, response)) {
		            ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.UPDATE_MERCHANT_PROFILE));
		            Map<String, Object> requestMap = ((Model) model).asMap();
		            if (null != requestMap) {
		                request.setAttribute(Constants.MESSAGE, requestMap.get(Constants.MESSAGE));
		                request.setAttribute(Constants.IS_SUCCESS, requestMap.get(Constants.IS_SUCCESS));
		                request.setAttribute(Constants.ACTION, requestMap.get(Constants.ACTION));
		            }
		            setMerchantRequestAttribute(request, requestMap);
		            return view;
		        }
		        return new ModelAndView("redirect:/");
	    }
		
		@RequestMapping(value = "/updateView", method = RequestMethod.GET)
	    public ModelAndView update(@ModelAttribute("merchantDTO") MerchantDTO merchantDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response, Model model) {
			   if(checkUserAlreadyLogin(request, response)){
				   if(null != merchantDTO){
					    MerchantDTO merchant = merchantService.getMerchantById(merchantDTO.getId());
					    redirectAttribute.addFlashAttribute(Constants.MERCHANT, merchant);
					    List<MerchantAddressDTO> merchantAddressList =  merchantService.getMerchantAddresss(merchantDTO.getId());
					    redirectAttribute.addFlashAttribute(Constants.MERCHANT_ADDRESS_LIST, merchantAddressList);
					    redirectAttribute.addFlashAttribute(Constants.ACTION, Constants.UPDATE);
					    redirectAttribute.addFlashAttribute(Constants.AES.toLowerCase(), aesService.getAesDTO());
					    return new ModelAndView(RedirectScreenConstants.REDIRECT_UPDATE_MERCHANT_PROFILE);
			       }
				   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in update Merchant.");
				   return new ModelAndView(RedirectScreenConstants.REDIRECT_UPDATE_MERCHANT_PROFILE);
			   }
			   return new ModelAndView("redirect:/");
	    }
		    
		@RequestMapping(value = "/edit", method = RequestMethod.POST)
		public ModelAndView edit(@ModelAttribute("merchantDTO") MerchantDTO merchant, @RequestParam("rowNumber") String rowNumber, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)){
				   try{
					   merchant = getMerchantObject(merchant, rowNumber, request, Constants.UPDATE);
					   int isUpdate = merchantService.updateMerchant(merchant);
					   return redirectUpdateMerchantScreen(isUpdate, redirectAttribute, merchant, RedirectScreenConstants.REDIRECT_UPDATE_MERCHANT_PROFILE);
				   }catch(Exception e){
					   LOGGER.error("Error in update Merchant -->  "+e);
				   }
			   }
			   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Update Merchant.");
			   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
			   return new ModelAndView(RedirectScreenConstants.REDIRECT_UPDATE_MERCHANT_PROFILE);
		 }
		
		 private void setRequestAttribute(HttpServletRequest request, MerchantDTO merchantDTO){
			     if(null != merchantDTO){
			    	 setRequestAttribute(request, merchantDTO.getId());
			     }else{
			    	 LOGGER.info("Login From Admin User");
					 List<MerchantDTO> merchantList = merchantService.getAllMerchant();
					 request.setAttribute(Constants.MERCHANT_LIST, merchantList);
					 if(!merchantList.isEmpty()){
						MerchantDTO merchant = merchantList.get(0);
						setRequestAttribute(request, merchant.getId());
					 }
			     }
		 }
		
		 private void setRequestAttribute(HttpServletRequest request, int mId){
				 request.setAttribute(Constants.MERCHANT, merchantProfileService.getMerchantDetails(mId));
				 request.setAttribute(Constants.MERCHANT_ADDRESS_LIST, merchantProfileService.getAddressDetails(mId));
				 request.setAttribute(Constants.MERCHANT_EMPLOYEE_LIST, merchantEmployeeService.getMerchantEmployeeByMId(mId));
				 request.setAttribute(Constants.CUSTOMER_LIST, merchantMappingService.getCustomerByMerchant(mId));
				 request.setAttribute(Constants.MERCHANT_ID_PREFIX, appProperiesService.getSystemParamValueByName(Constants.MERCHANT_ID_PREFIX));
		 }
		 
	  	 private MerchantDTO getMerchantObject(MerchantDTO merchant, String rowNumber, HttpServletRequest request, String operation) {
	        	 int number = utilityService.convertStringToInt(rowNumber);
	        	 List<MerchantAddressDTO> merchantAddressList = new LinkedList<>();
			     for (int templateField = 0; templateField <= number; templateField++) {
			    	   MerchantAddressDTO merchantAddress = new MerchantAddressDTO();
			    	   merchantAddress.setStateId(utilityService.convertStringToInt(request.getParameter(Constants.STATE + templateField)));
			    	   merchantAddress.setCityId(utilityService.convertStringToInt(request.getParameter(Constants.CITY + templateField)));
			    	   merchantAddress.setAreaCode(request.getParameter(Constants.AREA + templateField));
			    	   merchantAddress.setPincode(request.getParameter(Constants.PINCODE + templateField)); 
			    	   if(merchantAddress.getStateId() > 0 && StringUtils.isNotEmpty(merchantAddress.getPincode())){
			    		   merchantAddressList.add(merchantAddress);
			           }
				 }
			     merchant.setmAddressList(merchantAddressList);
			     if(operation.equals(Constants.ADD)){
			    	  merchant.setLoginId(utilityService.generateNumber(10, true, true));
			    	  merchant.setMerchantId(utilityService.generateCustomerId(false, true));
			    	  merchant.setMerchantNumber(utilityService.generateNumber(15, false, true));
		    	 }
				 return merchant;
 
         }
}
