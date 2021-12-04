package com.cratas.mpls.web.rest.controller;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.constant.RestAPIConstants;
import com.cratas.mpls.common.utility.constant.RestWebServiceErrConstants;
import com.cratas.mpls.service.IAESEncryptionService;
import com.cratas.mpls.service.IBCryptService;
import com.cratas.mpls.service.ICustomerService;
import com.cratas.mpls.service.IMerchantEmployeeService;
import com.cratas.mpls.service.IMerchantService;
import com.cratas.mpls.service.IUserLoginService;
import com.cratas.mpls.service.IUserService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.controller.LoginController;
import com.cratas.mpls.web.dto.ApiUserDTO;
import com.cratas.mpls.web.dto.CustomerDTO;
import com.cratas.mpls.web.dto.LoginDTO;
import com.cratas.mpls.web.dto.MerchantDTO;
import com.cratas.mpls.web.dto.MerchantEmployeeDTO;
import com.cratas.mpls.web.dto.UserDTO;
import com.cratas.mpls.web.dto.WebServiceResponseDTO;

/**
 * 
 * @author Bhupendra
 *
 */
@RestController
@RequestMapping("/rest/webservices/app/user")
public class LoginRestWebServiceController extends RestWebServiceController{

	   private final static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
		
	   @Autowired
	   private IUtilityService utilityService;
		
	   @Autowired
	   private IUserService userService;
		
	   @Autowired
	   private IAESEncryptionService aesService;
	   
	   @Autowired
	   private IUserLoginService userLoginService;
	   
	   @Autowired
	   private IBCryptService bCryptService;
	   
	   @Autowired
	   private ICustomerService customerService;
	   
	   @Autowired
	   private IMerchantEmployeeService merchantEmployeeService;
	   
	   @Autowired
	   private IMerchantService merchantService;
	   
	   @RequestMapping(value = "/login", method = {RequestMethod.POST})
	   public ResponseEntity<WebServiceResponseDTO> userLogin(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestBody LoginDTO loginDTO) {
		      WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				   if(StringUtils.isNotEmpty(loginDTO.getLoginId()) && StringUtils.isNotEmpty(loginDTO.getPassword())){
					    UserDTO user = userService.getUserByLoginIdMobileNo(loginDTO.getLoginId(), loginDTO.getLoginId());
					    if(null != user && user.getIsLocked() == 0){
				    	    String secretKey = appLoginSecretKey(token);
						    boolean isMatch = bCryptService.isMatch(user.getPassword(), aesService.decrypt(secretKey, secretKey, loginDTO.getLoginId(), loginDTO.getPassword()));
							if(isMatch){
								user.setLastSessionId(deviceId);
								userService.updateLastLoginDetail(user);
								return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.USER, setUserInfo(user)), Constants.SUCCESS.toUpperCase()));
							}else{
								String msg = userLoginService.getAppLoginUnSuccessfulAttemptMsg(user, loginDTO);
								return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, msg, null, Constants.FAILURE.toUpperCase()));
							}
					    }else if(null != user){
					    	String userLoginStatus = userLoginService.userLoginStatusMessage(user);
					    	LOGGER.info("User Login Status --> LoginId -->" + loginDTO.getLoginId() + " --> " + userLoginStatus);
					    	return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, userLoginStatus, null, Constants.FAILURE.toUpperCase()));
					    }
				   }
			       return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_USER_CREDENTIAL, null, Constants.FAILURE.toUpperCase()));
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
		}
	
	
	   @RequestMapping(value = "/loginWithPin", method = RequestMethod.POST)
	   public ResponseEntity<WebServiceResponseDTO> validateUserPin(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestBody LoginDTO userDTO) throws Exception {
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				  if(validatateUserInfo(userDTO, 1)) {
					  UserDTO user = getUserObj(userDTO, deviceId);
					  if(user != null && user.getIsLocked() == 0) {
						  String secretKey = appLoginSecretKey(token);
						  String decryptPin = aesService.decrypt(secretKey, secretKey, deviceId, userDTO.getUserPin());
					      if(userService.validateUserPin(user, decryptPin)) {
		                      return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.USER, setUserInfo(user)), Constants.SUCCESS.toUpperCase())); 
					      }
					      return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.LOGIN_PIN_VALIDATION_MSG, null, Constants.FAILURE.toUpperCase()));
					  }else if(null != user){
						  String userLoginStatus = userLoginService.userLoginStatusMessage(user);
					      return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, userLoginStatus, null, Constants.FAILURE.toUpperCase()));
					  }else {
						  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_USER_CREDENTIAL, null, Constants.FAILURE.toUpperCase()));
					  }
				  }  
				  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_PARAMETER, null, Constants.FAILURE.toUpperCase()));
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	   }
	   
	   @RequestMapping(value = "/loginWithFingerprint", method = RequestMethod.POST)
	   public ResponseEntity<WebServiceResponseDTO> validateUserFingerprint(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestBody LoginDTO userDTO) throws Exception {
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				  UserDTO user = getUserObj(userDTO, deviceId);
				  if(user != null && user.getIsLocked() == 0) {
				      if(user.getIsEnableFingerPrint() == 1) {
	                      return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.USER, setUserInfo(user)), Constants.SUCCESS.toUpperCase())); 
				      }
				      return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.IS_VALIDATE_FINGER_PRINT, Constants.FALSE.toLowerCase()), Constants.FAILURE.toUpperCase()));
				  }else if(null != user){
					  String userLoginStatus = userLoginService.userLoginStatusMessage(user);
				      return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, userLoginStatus, null, Constants.FAILURE.toUpperCase()));
				  }else {
					  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_USER_CREDENTIAL, null, Constants.FAILURE.toUpperCase()));
				  }
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	   }
	   
	   private boolean validatateUserInfo(LoginDTO loginDTO, int type) {
		       switch (type) {
					   case 1:
							return (StringUtils.isNotEmpty(loginDTO.getUserPin()));
					   default:
							return false;
			   }	      
	   }
	   
	   private UserDTO getUserObj(LoginDTO userDTO, String deviceId){
		       UserDTO user = null;
		       if(StringUtils.isNotEmpty(userDTO.getLoginId())) {
				  user = userService.getUserByLoginId(userDTO.getLoginId());
			   }else {
				  user = userService.getUserByDeviceId(deviceId);
			   }
		       return user;
	   }
	   
	   private String appLoginSecretKey(String token){
		       String hexVal = utilityService.toHexaDecimal(token);
		       if(StringUtils.isNotEmpty(hexVal) && hexVal.length() > 32){
				    hexVal = hexVal.substring(0,32);
			   }
			   return hexVal;
	   }
	   
	   private ApiUserDTO setUserInfo(UserDTO user) {
		       ApiUserDTO userDTO = new ApiUserDTO();
		       CustomerDTO customer = customerService.getCustomerById(user.getId());
			   if(null != customer){
				   userDTO.setcId(customer.getCustomerId());
				   userDTO.setId(customer.getId());
				   userDTO.setName(customer.getFirstName() + Constants.SPACE + customer.getLastName());
				   userDTO.setIsEnableFingerPrint(customer.getIsEnableFingerprint());
			   }else{
				   userDTO = setMerchantDetails(userDTO, user);
			   }
			   userDTO.setLoginId(user.getLoginId());
			   userDTO.setMobileNumber(user.getMobileNumber());
			   userDTO.setUserId(String.valueOf(user.getId()));
			   return userDTO;
       }
	   
	   private ApiUserDTO setMerchantDetails(ApiUserDTO userDTO, UserDTO user){
			   MerchantEmployeeDTO merchantEmployee = merchantEmployeeService.getMerchantEmployeeById(user.getId());
			   if(merchantEmployee != null){
				   MerchantDTO merchant = merchantService.getMerchantById(merchantEmployee.getmId());
				   if(merchant != null) {
					   userDTO.setName(merchant.getMerchantName());
					   userDTO.setmId(merchant.getMerchantId());
					   userDTO.setIsUpdateDetail(user.getIsUpdateDetail());
					   userDTO.setId(merchant.getId());
					   userDTO.setIsEnableFingerPrint(merchantEmployee.getIsEnableFingerprint());
				   }
			   }
			   return userDTO;
	   }
}
