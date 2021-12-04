package com.cratas.mpls.web.rest.controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.constant.PageConstant;
import com.cratas.mpls.common.utility.GroupType;
import com.cratas.mpls.dao.dto.StateDTO;
import com.cratas.mpls.service.IAESEncryptionService;
import com.cratas.mpls.service.IAppPropertiesService;
import com.cratas.mpls.service.IAreaService;
import com.cratas.mpls.service.ICityService;
import com.cratas.mpls.service.ICountryService;
import com.cratas.mpls.service.ICurrencyService;
import com.cratas.mpls.service.ICustomerService;
import com.cratas.mpls.service.IGroupService;
import com.cratas.mpls.service.IMerchantMappingService;
import com.cratas.mpls.service.IMerchantPlanService;
import com.cratas.mpls.service.IMerchantService;
import com.cratas.mpls.service.IStateService;
import com.cratas.mpls.service.IUserService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.service.IVerificationService;
import com.cratas.mpls.web.dto.AreaDTO;
import com.cratas.mpls.web.dto.CityDTO;
import com.cratas.mpls.web.dto.CountryDTO;
import com.cratas.mpls.web.dto.CurrencyDTO;
import com.cratas.mpls.web.dto.CustomerDTO;
import com.cratas.mpls.web.dto.GroupDTO;
import com.cratas.mpls.web.dto.MerchantDTO;
import com.cratas.mpls.web.dto.MerchantMappingDTO;
import com.cratas.mpls.web.dto.MerchantPlanDTO;
import com.cratas.mpls.web.dto.UserDTO;
import com.cratas.mpls.web.dto.VerificationLogDTO;

/**
 * 
 * @author mukesh
 *
 */
@RestController
@RequestMapping("/rest/ajax")
public class AjaxRestWebServiceController {
	
	    private final static Logger LOGGER = LoggerFactory.getLogger(AjaxRestWebServiceController.class);
		
		@Autowired
		private IUtilityService utilityService;
		
		@Autowired
		private IGroupService groupService;
		
		@Autowired
		private IUserService userService;
			
		@Autowired
		private IAreaService areaService;
		
		@Autowired
		private ICityService cityService;
		
		@Autowired
		private IStateService stateService;
		
		@Autowired
		private ICurrencyService currencyService;
		
		@Autowired
		private ICountryService countryService;
		
		@Autowired
		private IVerificationService verificationService;
		
		@Autowired
		private IAESEncryptionService aesService;
		
		@Autowired
		private IMerchantService merchantService;
		
		@Autowired
		private ICustomerService customerService;
		
		@Autowired
		private IMerchantPlanService merchantPlanService;
		
		@Autowired
	    private IAppPropertiesService appPropertiesService;
		
		@Autowired
		private IMerchantMappingService merchantMappingService;
		
		@RequestMapping(value = "/getGroupList", method = RequestMethod.POST)
		public @ResponseBody List<GroupDTO> getGroupList(@RequestParam("systemId") String systemId, HttpServletRequest request, HttpServletResponse response) {
			   return groupService.getActiveGroupsById(systemId);
		}
		
		@RequestMapping(value = "/getUserList", method = RequestMethod.POST)
		public @ResponseBody List<UserDTO> getUserList(@RequestParam("systemId") String systemId, HttpServletRequest request, HttpServletResponse response) {
			   return userService.getActiveUsersById(systemId);
		}
		
		@RequestMapping(value = "/getGroupInfo/{groupId}", method = RequestMethod.POST)
		public @ResponseBody GroupDTO getUserInfo(@PathVariable("groupId") String groupId, HttpServletRequest request, HttpServletResponse response) {
			   if(StringUtils.isNotEmpty(groupId)){
				     return groupService.getGroupById(groupId);
			   }
			   return null;
		}
		
		@RequestMapping(value = "/getStateList", method = RequestMethod.POST)
		public @ResponseBody List<StateDTO> getStateList(@RequestParam("countryId") int countryId, HttpServletRequest request, HttpServletResponse response) {
			   List<StateDTO> stateList = stateService.getStateList(countryId);
			   return stateList;
		}
		
		@RequestMapping(value = "/getCityList", method = RequestMethod.POST)
		public @ResponseBody List<CityDTO> getCityList(@RequestParam("stateId") int stateId, HttpServletRequest request, HttpServletResponse response) {
			   List<CityDTO> cityList = cityService.getCityList(stateId);
			   return cityList;
		}
		
		@RequestMapping(value = "/getAreaList", method = RequestMethod.POST)
		public @ResponseBody List<AreaDTO> getAreaList(@RequestParam("cityId") int cityId, HttpServletRequest request, HttpServletResponse response) {
			   List<AreaDTO> areaList = areaService.getAreaList(cityId);
			   return areaList;
		}
				
		@RequestMapping(value = "/getCurrencyList", method = RequestMethod.POST)
		public @ResponseBody List<CurrencyDTO> getCurrencyList(@RequestParam("countryId") String countryId,HttpServletRequest request, HttpServletResponse response) {
			   CountryDTO country = countryService.getCountryById(utilityService.convertStringToInt(countryId));
			   if(country != null){
				   List<CurrencyDTO> currencyList = currencyService.getCurrencyList(country.getCountryName());
				   return currencyList;
			   }
			   return null;
		}
		
		@RequestMapping(value = "/getPincode", method = RequestMethod.POST)
		public @ResponseBody AreaDTO getPincode(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response) {
			   if(StringUtils.isNotEmpty(id)){
				   AreaDTO area = areaService.getAreaById(utilityService.convertStringToInt(id));
				   return area;
			   }
			   return null;
		}
		
		@RequestMapping(value = "/sendVerificationCode", method = RequestMethod.POST)
		public @ResponseBody String sendVerificationCode(@ModelAttribute("verificationLog") VerificationLogDTO verificationLog, HttpServletRequest request, HttpServletResponse response) {
			   if(StringUtils.isNotEmpty(verificationLog.getEmailId()) && StringUtils.isNotEmpty(verificationLog.getUserType())){
				   String verificationId = verificationService.sendEmailVerificationCode(verificationLog);
				   if(StringUtils.isNotEmpty(verificationId)){
					   String salt = utilityService.generateSalt(null, request);
			    	   String iv = utilityService.generateIV(null, request);
					   return aesService.encrypt(salt, iv, iv, verificationId);
				   }
			   }
			   return Constants.BLANK;
		}
		
		@RequestMapping(value = "/verifyCode", method = RequestMethod.POST)
		public @ResponseBody String verifyCode(@ModelAttribute("verificationLog") VerificationLogDTO verificationLog, HttpServletRequest request, HttpServletResponse response) {
			   if(StringUtils.isNotEmpty(verificationLog.getId()) && StringUtils.isNotEmpty(verificationLog.getVerificationCode())){
			       String salt = utilityService.generateSalt(null, request);
		    	   String iv = utilityService.generateIV(null, request);
		    	   String verificationCode = aesService.decrypt(salt, iv, verificationLog.getId(), verificationLog.getVerificationCode());
		    	   verificationLog.setVerificationCode(verificationCode);
		    	   
		    	   verificationLog.setId(aesService.decrypt(salt, iv, iv, verificationLog.getId()));
		    	   boolean isVerifyCode = verificationService.verifyCode(verificationLog);
			       if(isVerifyCode){
			    	   return Constants.STR_TRUE;
			       }
			   }
			   return Constants.FALSE;
		}
	   
	    @RequestMapping(value = "/report/checkFileStatus", method = RequestMethod.POST)
	    public String checkFileStatus(@RequestParam("filePath") String filePath, HttpServletRequest request, HttpServletResponse response) {
			      if(StringUtils.isNotEmpty(filePath)){  
			           try{
			        	    File file = new File(filePath);
			        	    if(file.exists()){
			        	    	return Constants.STR_TRUE;
			        	    }
			           }catch (Exception e) {
						  LOGGER.error("Error in check Report file status");
					   }
			      }
			      return Constants.FALSE;
	    }
	   
	    @RequestMapping(value = "/report/download", method = RequestMethod.GET)
	    public ResponseEntity<Resource> downloadReport(@RequestParam("filePath") String filePath, HttpServletRequest request) {
		       if(StringUtils.isNotEmpty(filePath)){
				      Resource resource = utilityService.getFileResource(filePath);
				      String contentType = Constants.BLANK;
				      try {
				           contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
				           if(StringUtils.isEmpty(contentType)) {
					            contentType = Constants.OCTET_STREAM;
					       }
				      }catch (Exception ex) {
				           LOGGER.error("Could not determine file type.");
				      }
				      return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				                .body(resource);
		       }
		       return null;
	    }
	    
	    @RequestMapping(value = "/isExistsPANNumber", method = RequestMethod.POST)
		public @ResponseBody String isExistsPANNumber(@RequestParam("panNumber") String panNumber, HttpServletRequest request, HttpServletResponse response) {
			   if(StringUtils.isNotEmpty(panNumber)){
				   List<MerchantDTO> merchantList = merchantService.getMerchantByPanNumber(panNumber);
				   if(merchantList.size() > 0) {
					   return Constants.STR_TRUE;
				   }
			   }
			   return Constants.FALSE;
		}
	    
	    @RequestMapping(value = "/isExistsGSTIN", method = RequestMethod.POST)
		public @ResponseBody String isExistsGSTIN(@RequestParam("gstIn") String gstIn, HttpServletRequest request, HttpServletResponse response) {
			   if(StringUtils.isNotEmpty(gstIn)){
				   List<MerchantDTO> merchantList = merchantService.getMerchantByGSTIN(gstIn);
				   if(merchantList.size() > 0) {
					   return Constants.STR_TRUE;
				   }
			   }
			   return Constants.FALSE;
		}
	    
	    @RequestMapping(value = "/isExistsLoginId", method = RequestMethod.POST)
		public @ResponseBody String isExistsLoginId(@RequestParam("loginId") String loginId, HttpServletRequest request, HttpServletResponse response) {
			   if(StringUtils.isNotEmpty(loginId)){
				   UserDTO user = userService.getUserByLoginId(loginId);
				   if(user != null) {
					   return Constants.STR_TRUE;
				   }
			   }
			   return Constants.FALSE;
		}
	    
	    @RequestMapping(value = "/isExistsEmailId", method = RequestMethod.POST)
		public @ResponseBody String isExistsMerchantEmailId(@RequestParam("emailId") String emailId, HttpServletRequest request, HttpServletResponse response) {
			   if(StringUtils.isNotEmpty(emailId)){
				   List<MerchantDTO> merchantList = merchantService.getMerchantByEmailId(emailId);
				   List<CustomerDTO> customerList = customerService.getCustomerByEmailId(emailId);
				   if(merchantList.size() > 0) {
					   return Constants.ZERO_STRING;
				   }else if(customerList.size() > 0) {
					   return Constants.ONE;
				   }
			   }
			   return Constants.FALSE;
		}
	    
	    @RequestMapping(value = "/isExistsMobileNumber", method = RequestMethod.POST)
		public @ResponseBody String isExistsMerchantMobileNumber(@RequestParam("mobileNumber") String mobileNumber, HttpServletRequest request, HttpServletResponse response) {
			   if(StringUtils.isNotEmpty(mobileNumber)){
				   UserDTO user = userService.getUserByMobileNo(mobileNumber);
				   if(user != null) {
					   if(user.getGroupId().equalsIgnoreCase(GroupType.MERCHANT.getGroupType())){
						   return Constants.ZERO_STRING;
					   }else if(user.getGroupId().equalsIgnoreCase(GroupType.CUSTOMER.getGroupType())){
						   return Constants.ONE;
					   }
					   return Constants.TWO;
				   }
			   }
			   return Constants.FALSE;
		}
	    
	    @RequestMapping(value = "/getMerchantPlan", method = RequestMethod.POST)
		public @ResponseBody MerchantPlanDTO getMerchantVersionList(@RequestParam("merchantId") String merchantId, HttpServletRequest request, HttpServletResponse response) {
	    	   if(StringUtils.isNotEmpty(merchantId)) {
			       return merchantPlanService.getMerchantPlanByMId(merchantId);
	    	   }
	    	   return null;
		}
	   
	    @RequestMapping(value = "/getTransactionType", method = RequestMethod.POST)
		public @ResponseBody List<String> getTransactionType(HttpServletRequest request, HttpServletResponse response) {
			   return appPropertiesService.getMPLSFields(Constants.TRANSACTION_TYPE);
		}
	    
	    @RequestMapping(value = "/getDurationType", method = RequestMethod.POST)
		public @ResponseBody List<String> getDurationType(HttpServletRequest request, HttpServletResponse response) {
			   return appPropertiesService.getMPLSFields(Constants.LIMIT_DURATION_TYPE);
		}
	    
	    @RequestMapping(value = "/getCustomerDetail", method = RequestMethod.POST)
		public ModelAndView  getCustomerByMN(@RequestParam("mId") int mId,@RequestParam("mobileNumber") String mobileNumber, HttpServletRequest request, HttpServletResponse response) {
	    	   ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.CUSTOMERDETAIL));
	    	   if(StringUtils.isNotEmpty(mobileNumber)) {
	    		    MerchantMappingDTO customer = merchantMappingService.getCustomerByMN(mId, mobileNumber);
	    		    request.setAttribute(Constants.CUSTOMER, customer);
	    		    return view;
	    	   }
	    	   return null;
		}
	    
	    @RequestMapping(value = "/getCustomerDetails", method = RequestMethod.POST)
 		public ModelAndView  getCustomerByMobileNo(@RequestParam("mId") int mId,@RequestParam("mobileNumber") String mobileNumber, HttpServletRequest request, HttpServletResponse response) {
 	    	   ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.CUSTOMER_DETAIL_FOR_PAYBILL));
 	    	   if(StringUtils.isNotEmpty(mobileNumber)) {
 	    		    MerchantMappingDTO customer = merchantMappingService.getCustomerByMN(mId, mobileNumber);
 	    		    if(customer != null) {
 	    		    	request.setAttribute(Constants.IS_VALID, Constants.STR_TRUE);
 	    		    }	
 	    		    request.setAttribute(Constants.CUSTOMER, customer);
 	    		    return view;
 	    	   }
 	    	   return null;
 		}
	    
	    @RequestMapping(value = "/getStates", method = RequestMethod.POST)
		public @ResponseBody List<StateDTO> getStateList( HttpServletRequest request, HttpServletResponse response) {
			   List<StateDTO> stateList = stateService.getStates();
			   return stateList;
		}
	    
	    @RequestMapping(value = "/updateMerchantFlag", method = RequestMethod.POST)
		public @ResponseBody int updateMerchantFlag(@RequestParam("loginId") String loginId, HttpServletRequest request, HttpServletResponse response) {
	    	   MerchantDTO merchant = new MerchantDTO();
	    	   merchant.setLoginId(loginId);
			   return merchantService.updateMerchantFlag(merchant);
		}
	    
	    @RequestMapping(value = "/getCustomerDetailForRefund", method = RequestMethod.POST)
	    public ModelAndView getCustomerDetailForRefund(@RequestParam("mId") int mId,@RequestParam("mobileNumber") String mobileNumber, HttpServletRequest request, HttpServletResponse response) {
	    	   ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.CUSTOMERDETAILREFUND));
	    	   if(StringUtils.isNotEmpty(mobileNumber)) {
	    		    MerchantMappingDTO customer = merchantMappingService.getCustomerByMN(mId, mobileNumber);
	    		    request.setAttribute(Constants.CUSTOMER, customer);
	    		    return view;
	    	   }
	    	   return null;
		}
	    
	    @RequestMapping(value = "/getActiveGroupList", method = RequestMethod.POST)
		   public @ResponseBody List<GroupDTO> getActiveGroupList(HttpServletRequest request, HttpServletResponse response) {
			      return groupService.getActiveGroup();
		}
	    
	    @RequestMapping(value = "/sendMobileVerificationCode", method = RequestMethod.POST)
		public @ResponseBody String sendMobileVerificationCode(@ModelAttribute("verificationLog") VerificationLogDTO verificationLog, HttpServletRequest request, HttpServletResponse response) {
			   if(StringUtils.isNotEmpty(verificationLog.getMobileNumber())){
				   UserDTO user = userService.getUserByLoginIdMobileNo(verificationLog.getMobileNumber(), verificationLog.getMobileNumber());
				   if(user != null) {
					   verificationLog.setUserType(user.getGroupId());
				   }
				   String verificationId = verificationService.sendMobileVerificationCode(verificationLog);
				   if(StringUtils.isNotEmpty(verificationId)){
					   String salt = utilityService.generateSalt(null, request);
			    	   String iv = utilityService.generateIV(null, request);
					   return aesService.encrypt(salt, iv, iv, verificationId);
				   }
			   }
			   return Constants.BLANK;
		}
}
