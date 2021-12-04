package com.cratas.mpls.web.rest.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.constant.RestAPIConstants;
import com.cratas.mpls.common.utility.GroupType;
import com.cratas.mpls.common.utility.constant.RestWebServiceErrConstants;
import com.cratas.mpls.dao.dto.StateDTO;
import com.cratas.mpls.service.IAppNotificationService;
import com.cratas.mpls.service.IAreaService;
import com.cratas.mpls.service.ICategoryService;
import com.cratas.mpls.service.ICityService;
import com.cratas.mpls.service.ICountryService;
import com.cratas.mpls.service.ICurrencyService;
import com.cratas.mpls.service.ICustomerService;
import com.cratas.mpls.service.IMerchantMappingService;
import com.cratas.mpls.service.IMerchantService;
import com.cratas.mpls.service.IPushNotificationLogService;
import com.cratas.mpls.service.IStateService;
import com.cratas.mpls.service.IUserService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.service.IVerificationService;
import com.cratas.mpls.web.dto.AppNotificationDTO;
import com.cratas.mpls.web.dto.AreaDTO;
import com.cratas.mpls.web.dto.CategoryDTO;
import com.cratas.mpls.web.dto.CityDTO;
import com.cratas.mpls.web.dto.CountryDTO;
import com.cratas.mpls.web.dto.CurrencyDTO;
import com.cratas.mpls.web.dto.CustomerDTO;
import com.cratas.mpls.web.dto.MerchantDTO;
import com.cratas.mpls.web.dto.MerchantMappingDTO;
import com.cratas.mpls.web.dto.UserDTO;
import com.cratas.mpls.web.dto.VerificationLogDTO;
import com.cratas.mpls.web.dto.WebServiceResponseDTO;

/**
 * 
 * @author sunil
 *
 */
@RestController
@RequestMapping("/rest/webservices/app")
public class AppRestWebServiceController extends RestWebServiceController {
	
	   private final static Logger LOGGER = LoggerFactory.getLogger(AppRestWebServiceController.class);
	
	   @Autowired
	   private ICountryService countryService;
	
	   @Autowired
	   private ICityService cityService;
	   
	   @Autowired
	   private IAreaService areaService;
	   
	   @Autowired
	   private ICategoryService categoryService;
	   
	   @Autowired
	   private ICurrencyService currencyService;
	   
	   @Autowired
	   private IStateService stateService;
	   
	   @Autowired
	   private IVerificationService verificationService;
	   
	   @Autowired
	   private IUtilityService utilityService;
	   
	   @Autowired
	   private IUserService userService;
	   
	   @Autowired
	   private ICustomerService customerService;
	   
	   @Autowired
	   private IMerchantService merchantService;
	   
	   @Autowired
	   private IAppNotificationService appNotificationService;
	   
	   @Autowired
	   private IMerchantMappingService merchantMappingService;
	   
	   @Autowired
	   private IPushNotificationLogService pushNotificationLogService;
	   
	   @RequestMapping(value = "/info", method = RequestMethod.GET)
	   public ResponseEntity<WebServiceResponseDTO> getAppInfo(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token) throws Exception {
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				   return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.APP, getMPLSAppInfo()), Constants.SUCCESS.toUpperCase()));
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	   }
	   
	   @RequestMapping(value = "/country", method = RequestMethod.GET)
	   public ResponseEntity<WebServiceResponseDTO> getCountry(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token) throws Exception {
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				  List<CountryDTO> countryList = countryService.getCountry();
				  return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.COUNTRY, countryList), Constants.SUCCESS.toUpperCase()));
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	   }
	   
	   @RequestMapping(value = "/state/{countryId}", method = RequestMethod.GET)
	   public ResponseEntity<WebServiceResponseDTO> getStateList(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token ,@PathVariable int countryId) throws Exception {
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				  List<StateDTO> statelist = stateService.getStateList(countryId);
				  return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.STATE, statelist), Constants.SUCCESS.toUpperCase()));
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	   }	   

	   @RequestMapping(value = "/states", method = RequestMethod.GET)
	   public ResponseEntity<WebServiceResponseDTO> getStates(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token) throws Exception {
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				  List<StateDTO> statelist = stateService.getStates();
				  return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.STATE, statelist), Constants.SUCCESS.toUpperCase()));
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	   }
	   
	   @RequestMapping(value = "/city/{stateId}", method = RequestMethod.GET)
	   public ResponseEntity<WebServiceResponseDTO> getCity(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @PathVariable("stateId") int stateId) throws Exception {
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				  List<CityDTO> cityList = cityService.getCityList(stateId);
				  return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.CITY, cityList), Constants.SUCCESS.toUpperCase()));
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	   }
	   
	   @RequestMapping(value = "/area/{cityId}", method = RequestMethod.GET)
	   public ResponseEntity<WebServiceResponseDTO> getArea(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @PathVariable("cityId") int cityId) throws Exception {
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				  List<AreaDTO> areaList = areaService.getAreaList(cityId);
				  return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.AREA, areaList), Constants.SUCCESS.toUpperCase()));
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	   }
	   
	   @RequestMapping(value = "/category", method = RequestMethod.GET)
	   public ResponseEntity<WebServiceResponseDTO> getCategory(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token) throws Exception {
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				  List<CategoryDTO> categoryList = categoryService.getCategory();
				  return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.CATEGORY, categoryList), Constants.SUCCESS.toUpperCase()));
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	   }
	   
	   @RequestMapping(value = "/currency/{countryId}", method = RequestMethod.GET)
	   public ResponseEntity<WebServiceResponseDTO> getCurrency(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @PathVariable("countryId") int countryId) throws Exception {
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				   CountryDTO country = countryService.getCountryById(countryId);
				   if(country != null){
					   List<CurrencyDTO> currencyList = currencyService.getCurrencyList(country.getCountryName());
					   return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.CURRENCY, currencyList), Constants.SUCCESS.toUpperCase()));
				   }
				   return ResponseEntity.status(HttpStatus.NOT_FOUND).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_COUNTRY_CODE, null, Constants.FAILURE.toUpperCase()));
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	   }
	   
	   @RequestMapping(value = "/sendVerificationCode", method = RequestMethod.POST)
	   public ResponseEntity<WebServiceResponseDTO> sendVerificationCode(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestBody VerificationLogDTO verificationLog) throws Exception {
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				  if((StringUtils.isNotEmpty(verificationLog.getEmailId()) || StringUtils.isNotEmpty(verificationLog.getMobileNumber())) && StringUtils.isNotEmpty(verificationLog.getUserType())){
					   String verificationId = verificationService.sendEmailVerificationCode(verificationLog);
					   return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.VERIFICATIONID, verificationId), Constants.SUCCESS.toUpperCase()));
				  }else{
					   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_REQUEST, Constants.BLANK,  Constants.FAILURE.toUpperCase()));
				  }	   
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, Constants.BLANK, Constants.FAILURE.toUpperCase()));
	   }
	   
	   @RequestMapping(value = "/verifyCode", method = RequestMethod.POST)
	   public ResponseEntity<WebServiceResponseDTO> verifyCode(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestBody VerificationLogDTO verificationLog) throws Exception {
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				  if(StringUtils.isNotEmpty(verificationLog.getId()) && StringUtils.isNotEmpty(verificationLog.getVerificationCode())){
					   boolean isVerifyCode = verificationService.verifyCode(verificationLog);
					   if(isVerifyCode){
				    	   return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.VERIFIED, isVerifyCode), Constants.SUCCESS.toUpperCase()));
				       }
					   return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.VERIFIED, isVerifyCode), Constants.SUCCESS.toUpperCase()));
				  }	   
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, Constants.BLANK, Constants.FAILURE.toUpperCase()));
	   }
	   
	   @RequestMapping(value = "/toHexCode/{value}", method = RequestMethod.GET)
	   public ResponseEntity<WebServiceResponseDTO> getAppInfo(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @PathVariable("value") String value) throws Exception {
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId) && StringUtils.isNotEmpty(value)) {
				   String hexCode = utilityService.toHexaDecimal(value);
				   return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.HEX_CODE, hexCode), Constants.SUCCESS.toUpperCase()));
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	   }
	   
	   @RequestMapping(value = "/area", method = RequestMethod.GET)
	   public ResponseEntity<WebServiceResponseDTO> getAreaList(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token) throws Exception {
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				   return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.AREA_LIST, merchantService.getMerchantAddress()), Constants.SUCCESS.toUpperCase()));
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	   }
	   
	   @RequestMapping(value = "/isExistsEmailId", method = RequestMethod.GET)
	    public ResponseEntity<WebServiceResponseDTO> isExistsMerchantEmailId(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestParam("emailId") String emailId) throws Exception {
			   WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			   if(validateToken(token, deviceId)) {
				   if(StringUtils.isNotEmpty(emailId)) {
					    List<MerchantDTO> merchantList = merchantService.getMerchantByEmailId(emailId);
					    List<CustomerDTO> customerList = customerService.getCustomerByEmailId(emailId);
					    if(merchantList.size() > 0) {
					    	return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.EXISTS_MERCHANT_EMAIL_ID_MSG, null, Constants.FAILURE.toUpperCase()));
					    }else if(customerList.size() > 0){
					    	return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.EXISTS_CUSTOMER_EMAIL_ID, null, Constants.FAILURE.toUpperCase()));
					    }else {
					    	return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, Constants.BLANK, null), Constants.SUCCESS.toUpperCase()));
					    }
				   }
				   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_EMAIL_ID, null, Constants.FAILURE.toUpperCase()));
			   }
			   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	    }
	    
	   @RequestMapping(value = "/isExistsMobileNumber/{mobileNumber}", method = RequestMethod.GET)
	    public ResponseEntity<WebServiceResponseDTO> isExistsMerchantMobileNumber(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @PathVariable("mobileNumber") String mobileNumber) throws Exception {
			   WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			   if(validateToken(token, deviceId)) {
				   if(StringUtils.isNotEmpty(mobileNumber)) {
					    UserDTO user = userService.getUserByMobileNo(mobileNumber);
					    if(user != null) {
						    if(user.getGroupId().equalsIgnoreCase(GroupType.MERCHANT.getGroupType())){
						    	return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.EXISTS_MERCHANT_MOBILE_NUMBER, null, Constants.FAILURE.toUpperCase()));
						    }else if(user.getGroupId().equalsIgnoreCase(GroupType.CUSTOMER.getGroupType())){
						    	return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.EXISTS_MERCHANT_MOBILE_NUMBER, null, Constants.FAILURE.toUpperCase()));
						    }
					    	return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.EXISTS_MOBILE_NUMBER, null, Constants.FAILURE.toUpperCase()));
					    }
				    	return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, Constants.BLANK, null), Constants.SUCCESS.toUpperCase()));
				   }
				   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_MOBILE_NUMBER, null, Constants.FAILURE.toUpperCase()));
			   }
			   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	    }
	    
	    @RequestMapping(value = "/isExistsLoginId/{loginId}", method = RequestMethod.GET)
	    public ResponseEntity<WebServiceResponseDTO> isExistsLoginId(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @PathVariable("loginId") String loginId) throws Exception {
			   WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			   if(validateToken(token, deviceId)) {
				   if(StringUtils.isNotEmpty(loginId)) {
					    UserDTO user = userService.getUserByLoginId(loginId);
					    if(user != null) {
					    	return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.LOGIN_ID_EXISTS, null, Constants.FAILURE.toUpperCase()));
					    }else {
					    	return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, Constants.BLANK, null), Constants.SUCCESS.toUpperCase()));
					    }
				   }
				   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_LOGIN_ID, null, Constants.FAILURE.toUpperCase()));
			   }
			   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	    }
	    
	    @RequestMapping(value = "/fcm/register", method = RequestMethod.POST)
	    public ResponseEntity<WebServiceResponseDTO> add(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestBody AppNotificationDTO appNotification) throws Exception {
			   WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			   if(validateToken(token, deviceId)) {
				   LOGGER.info("App notification device Id -- "+appNotification.getToken());
			       setAppNotificationDTO(appNotification, deviceId);
			       int isInsert = appNotificationService.registerFCMToken(appNotification);
				   return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.IS_INSERT, isInsert == 1 ? RestAPIConstants.TRUE : RestAPIConstants.FALSE), Constants.SUCCESS.toUpperCase()));
			   } 
			   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	    }
	   
	    private AppNotificationDTO setAppNotificationDTO(AppNotificationDTO appNotificationDTO, String deviceId) {
		   	    appNotificationDTO.setDeviceId(deviceId);
		        return appNotificationDTO;
	    }
	    
	    @RequestMapping(value = "/customerByMobileNumber/{mId}/{mobileNumber}", method = RequestMethod.GET)
	    public ResponseEntity<WebServiceResponseDTO> isExistCustomerMobileNumber(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token,  @PathVariable("mId") int mId, @PathVariable("mobileNumber") String mobileNumber) throws Exception {
			   WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			   if(validateToken(token, deviceId)) {
				   if(StringUtils.isNotEmpty(mobileNumber)) {
					    MerchantMappingDTO customer = merchantMappingService.getCustomerByMN(mId, mobileNumber); 
					    if(customer != null){
					    	return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.CUSTOMER, customer), Constants.SUCCESS.toUpperCase()));
					    }else {
					    	return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.RECORD_NOT_FOUND, null, Constants.FAILURE.toUpperCase()));
					    }
				   }
				   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_MOBILE_NUMBER, null, Constants.FAILURE.toUpperCase()));
			   }
			   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	    }
	    
	    @RequestMapping(value = "/sendMobileVerificationCode", method = RequestMethod.POST)
		   public ResponseEntity<WebServiceResponseDTO> sendMobileVerificationCode(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestBody VerificationLogDTO verificationLog) throws Exception {
				  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
				  if(validateToken(token, deviceId)) {
					  if((StringUtils.isNotEmpty(verificationLog.getEmailId()) || StringUtils.isNotEmpty(verificationLog.getMobileNumber())) && StringUtils.isNotEmpty(verificationLog.getUserType())){
						   String verificationId = verificationService.sendMobileVerificationCode(verificationLog);
						   return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.VERIFICATIONID, verificationId), Constants.SUCCESS.toUpperCase()));
					  }else{
						   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_REQUEST, Constants.BLANK,  Constants.FAILURE.toUpperCase()));
					  }	   
				  }
				  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, Constants.BLANK, Constants.FAILURE.toUpperCase()));
		 }
	    
	    @RequestMapping(value = "/customerByMN/{mobileNumber}", method = RequestMethod.GET)
	    public ResponseEntity<WebServiceResponseDTO> isExistCustomerMobileNumber(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @PathVariable("mobileNumber") String mobileNumber) throws Exception {
			   WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			   if(validateToken(token, deviceId)) {
				   if(StringUtils.isNotEmpty(mobileNumber)) {
					    CustomerDTO customer = customerService.getCustomerByMN(mobileNumber); 
					    if(customer != null){
					    	return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.CUSTOMER, customer), Constants.SUCCESS.toUpperCase()));
					    }else {
					    	return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.RECORD_NOT_FOUND, null, Constants.FAILURE.toUpperCase()));
					    }
				   }
				   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_MOBILE_NUMBER, null, Constants.FAILURE.toUpperCase()));
			   }
			   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	    }
	    
	    @RequestMapping(value = "/getPushNotification/{cId}/{mId}", method = RequestMethod.GET)
	    public ResponseEntity<WebServiceResponseDTO> getPushNotifications(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @PathVariable("cId") int cId, @PathVariable("mId") int mId) throws Exception {
			   WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			   if(validateToken(token, deviceId)) {
				   if(cId > 0 || mId > 0) {
					    if(cId > 0){
					    	return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.NOTIFICATION_LOG, pushNotificationLogService.getPushNotificationByCid(cId)), Constants.SUCCESS.toUpperCase()));
					    }else if(mId > 0){
					    	return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.NOTIFICATION_LOG, pushNotificationLogService.getPushNotificationByMid(mId)), Constants.SUCCESS.toUpperCase()));
					    }
				   }
				   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_PARAMETER, null, Constants.FAILURE.toUpperCase()));
			   }
			   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	    }

}
