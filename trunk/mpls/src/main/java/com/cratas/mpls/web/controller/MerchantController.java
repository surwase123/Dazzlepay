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
import com.cratas.mpls.service.ICategoryService;
import com.cratas.mpls.service.ICountryService;
import com.cratas.mpls.service.IMerchantService;
import com.cratas.mpls.service.IStateService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.service.IVerificationService;
import com.cratas.mpls.web.dto.MerchantAddressDTO;
import com.cratas.mpls.web.dto.MerchantDTO;
import com.cratas.mpls.web.dto.UserDTO;
import com.cratas.mpls.web.dto.VerificationLogDTO;

/**
 * 
 * @author mukesh
 *
 */
@Controller
@RequestMapping("/merchant")
public class MerchantController extends BaseController {

	    private final static Logger LOGGER = LoggerFactory.getLogger(MerchantController.class);
	
	    @Autowired
	    private IUtilityService utilityService;
	
	    @Autowired
	    private IMerchantService merchantService;
	    
	    @Autowired
	    private IAESEncryptionService aesService;
	    
	    @Autowired
	    private IVerificationService verificationService;
	    
	    @Autowired
	    private ICountryService countryService;
	    
	    @Autowired
	    private ICategoryService categoryService;
	    
	    @Autowired
	    private IStateService stateService;
	
	    @RequestMapping(value = "/registration/view", method = RequestMethod.GET)
	    public ModelAndView RegistrationView(HttpServletRequest request, HttpServletResponse response, Model model) {
		       if(checkUserAlreadyLogin(request, response)) {
		            ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.MERCHANT_REG_ADMIN));
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
	        
        @RequestMapping(value = "/add", method = RequestMethod.POST)
		public ModelAndView add(@ModelAttribute("merchantDTO") MerchantDTO merchantDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
	    	   if(checkUserAlreadyLogin(request, response)){
				   try{
						MerchantDTO merchant = getMerchantRegisterObject(merchantDTO, request, Constants.ADD);
						UserDTO userDTO = getSessionUserObject(request);
						int isInsert = merchantService.saveMerchant(userDTO, merchant);
						return redirectMerchantScreen(isInsert, redirectAttribute, RedirectScreenConstants.REDIRECT_MERCHANT_ADMIN_REG, merchantDTO);
				   }catch(Exception e){
						   LOGGER.error("Error in save Merchant -->  "+e);
				   }
	    	  }
			  redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Add Merchant.");
			  redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
			  return new ModelAndView(RedirectScreenConstants.REDIRECT_MERCHANT_ADMIN_REG);
       }
	    
	   @RequestMapping(value = "/update", method = RequestMethod.GET)
	   public ModelAndView update(@ModelAttribute("merchantDTO") MerchantDTO merchantDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response, Model model) {
			  if(checkUserAlreadyLogin(request, response)){
				   if(null != merchantDTO){
					    MerchantDTO merchant = merchantService.getMerchantById(merchantDTO.getId());
					    redirectAttribute.addFlashAttribute(Constants.MERCHANT, merchant);
					    List<MerchantAddressDTO> merchantAddressList =  merchantService.getMerchantAddresss(merchantDTO.getId());
					    redirectAttribute.addFlashAttribute(Constants.MERCHANT_ADDRESS_LIST, merchantAddressList);
					    redirectAttribute.addFlashAttribute(Constants.ACTION, Constants.UPDATE);
						return new ModelAndView(RedirectScreenConstants.REDIRECT_MERCHANT_ADMIN_REG);
			       }
				   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in update Merchant.");
				   return new ModelAndView(RedirectScreenConstants.REDIRECT_MERCHANT_ADMIN_REG);
			  }
			  return new ModelAndView("redirect:/");
	    }
	    
	   @RequestMapping(value = "/edit", method = RequestMethod.POST)
	   public ModelAndView edit(@ModelAttribute("merchantDTO") MerchantDTO merchant, @RequestParam("rowNumber") String rowNumber, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			  if(checkUserAlreadyLogin(request, response)){
					   try{
						   merchant = getMerchantObject(merchant, rowNumber, request, Constants.UPDATE);
						   int isUpdate = merchantService.updateMerchant(merchant);
						   return redirectUpdateMerchantScreen(isUpdate, redirectAttribute, merchant, RedirectScreenConstants.REDIRECT_MERCHANT_ADMIN_REG);
					   }catch(Exception e){
						   LOGGER.error("Error in update Merchant -->  "+e);
					   }
			  }
			  redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Update Merchant.");
			  redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
			  return new ModelAndView(RedirectScreenConstants.REDIRECT_MERCHANT_ADMIN_REG);
		}
	    
		@RequestMapping(value = "/delete", method = RequestMethod.POST)
		public ModelAndView delete(@ModelAttribute("merchantDTO") MerchantDTO merchantDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)){
					   try{
						   int isInsert = merchantService.deleteMerchant(merchantDTO);
						   if(isInsert > 0){
							   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Merchant Deleted Successfully.");
							   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
							   return new ModelAndView(RedirectScreenConstants.REDIRECT_MERCHANT_ADMIN_REG);
						   }
					   }catch(Exception e){
						   LOGGER.error("Error in delete Merchant -->  "+e);
					   }
			   }
			   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in delete Merchant.");
			   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
			   return new ModelAndView(RedirectScreenConstants.REDIRECT_MERCHANT_ADMIN_REG);
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
			    	  merchant.setLoginId(merchant.getMobileNumber());
			    	  merchant.setMerchantId(utilityService.generateCustomerId(false, true));
			    	  merchant.setMerchantNumber(utilityService.generateNumber(15, false, true));
			    	  merchant.setMerchantPlanId(merchantService.getBasicMerchantPlanId());
		    	}
				return merchant;
        }		
	
	    @RequestMapping(value = "/getMerchantAddress/{id}", method = RequestMethod.POST)
		public ModelAndView  getMerchantAddress(@PathVariable("id") String id,HttpServletRequest request, HttpServletResponse response) {
		       ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.MERCHANT_ADDRESS_MODAL));
		       if(StringUtils.isNotEmpty(id)){
		           request.setAttribute(Constants.MERCHANT_ADDRESS_LIST, merchantService.getMerchantAddresss(utilityService.convertStringToInt(id)));
		       }
		       return view;
		}	    
	    
	    @RequestMapping(value = "/register", method = RequestMethod.POST)
		public ModelAndView merchantRegister(@ModelAttribute("merchantDTO") MerchantDTO merchantDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
	    	   try{
	    		   if(isVerifiedEmail(merchantDTO, request)){
					   MerchantDTO merchant = getMerchantRegisterObject(merchantDTO, request, Constants.ADD);
					   UserDTO userDTO = getSessionUserObject(request);
					   int isInsert = merchantService.saveMerchant(userDTO, merchant);
					   return redirectMerchantScreen(isInsert, redirectAttribute, RedirectScreenConstants.REDIRECT_MERCHANT_REGISTER, merchant);
	    		   }
			   }catch(Exception e){
					   LOGGER.error("Error in save Merchant -->  "+e);
			   }
			   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Add Merchant.");
			   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
			   return new ModelAndView(RedirectScreenConstants.REDIRECT_MERCHANT_REGISTER);
       }
	    
	    
	   private MerchantDTO getMerchantRegisterObject(MerchantDTO merchant, HttpServletRequest request, String operation) {
			   if(operation.equals(Constants.ADD)){
			    	  merchant.setLoginId(merchant.getMobileNumber());
			    	  merchant.setMerchantId(utilityService.generateCustomerId(false, true));
			    	  merchant.setMerchantNumber(utilityService.generateNumber(15, false, true));
			    	  merchant.setMerchantPlanId(merchantService.getBasicMerchantPlanId());
		    	}
				return merchant;
	   }

	   private boolean isVerifiedEmail(MerchantDTO merchant, HttpServletRequest request){
			   String salt = utilityService.generateSalt(null, request);
	    	   String iv = utilityService.generateIV(null, request);
	    	   String verifiedId = aesService.decrypt(salt, iv, iv, merchant.getVerifiedId());
	    	   VerificationLogDTO verification = verificationService.getVerificationLog(utilityService.convertStringToInt(verifiedId));
	    	   if(null != verification && verification.getIsVerified() == 1){
	    		    return true;
	    	   }
	    	   return false;
	   }
	   
	   private ModelAndView redirectMerchantScreen(int isInsert, RedirectAttributes redirectAttribute, String redirectScreen, MerchantDTO merchant){
			   if(isInsert == 1){
				   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Registration is Successful. Please Check Your Mail for Login Details.");
				   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
				   return new ModelAndView(redirectScreen);
			   }else if(isInsert == 2){
				   redirectAttribute.addFlashAttribute(Constants.MERCHANT, merchant);
				   redirectAttribute.addFlashAttribute(Constants.MERCHANT_ADDRESS_LIST, merchant.getmAddressList());
				   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Merchant Already Exists.");
				   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
				   return new ModelAndView(redirectScreen);
			   }else if(isInsert == 3){
				   redirectAttribute.addFlashAttribute(Constants.MERCHANT, merchant);
				   redirectAttribute.addFlashAttribute(Constants.MERCHANT_ADDRESS_LIST, merchant.getmAddressList());
				   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Merchant User Login info.");
				   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
				   return new ModelAndView(redirectScreen);
			   }
			   return new ModelAndView("redirect:/");
	   }
	   
	   @RequestMapping(value = "/updateMerchantDetail", method = RequestMethod.GET)
	    public ModelAndView updateMerchantDetail(HttpServletRequest request, HttpServletResponse response, Model model) {
		       if(checkUserAlreadyLogin(request, response)) {
		            ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.UPDATE_MERCHANT_DETAIL));
		            request.setAttribute(Constants.COUNTRY_LIST, countryService.getCountry());
		            request.setAttribute(Constants.CATEGORY_LIST, categoryService.getCategory());
		            request.setAttribute(Constants.STATE_LIST, stateService.getStates());
		            Map<String, Object> requestMap = ((Model) model).asMap();
		            if (null != requestMap) {
		                request.setAttribute(Constants.MESSAGE, requestMap.get(Constants.MESSAGE));
		                request.setAttribute(Constants.IS_SUCCESS, requestMap.get(Constants.IS_SUCCESS));
		            }
		            return view;
		        }
		        return new ModelAndView("redirect:/");
	   }
	   
	   @RequestMapping(value = "/saveMerchantDetail", method = RequestMethod.POST)
		public ModelAndView saveMerchantDetail(@ModelAttribute("merchantDTO") MerchantDTO merchantDTO, @RequestParam("rowNumber") String rowNumber, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
	    	   if(checkUserAlreadyLogin(request, response)){
				   try{
					   MerchantDTO merchant = getMerchantObject(merchantDTO, rowNumber, request, Constants.UPDATE);
					   int isInsert = merchantService.saveMerchantDetails(merchant);
					   if(isInsert == 1){
						   merchantService.updateMerchantFlag(merchant);
						   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Merchant Detail Added Successfully.");
						   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
						   return new ModelAndView(RedirectScreenConstants.REDIRECT_UPDATE_MERCHANT_DETAIL);
					   }   
				   }catch(Exception e){
						   LOGGER.error("Error in save Merchant Details-->  "+e);
				   }
	    	  }
			  redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Add Merchant Details.");
			  redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
			  return new ModelAndView(RedirectScreenConstants.REDIRECT_UPDATE_MERCHANT_DETAIL);
     }
}