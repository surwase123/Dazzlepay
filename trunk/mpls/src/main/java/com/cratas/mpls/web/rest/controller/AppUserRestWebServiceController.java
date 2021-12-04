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
import com.cratas.mpls.common.utility.constant.InformationMsgConstants;
import com.cratas.mpls.common.utility.constant.RestWebServiceErrConstants;
import com.cratas.mpls.service.IAESEncryptionService;
import com.cratas.mpls.service.IAppNotificationService;
import com.cratas.mpls.service.IBCryptService;
import com.cratas.mpls.service.ICustomerService;
import com.cratas.mpls.service.IGroupService;
import com.cratas.mpls.service.IMerchantProfileService;
import com.cratas.mpls.service.IUserService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.ApiUserDTO;
import com.cratas.mpls.web.dto.AppNotificationDTO;
import com.cratas.mpls.web.dto.CustomerDTO;
import com.cratas.mpls.web.dto.GroupDTO;
import com.cratas.mpls.web.dto.MerchantUserDTO;
import com.cratas.mpls.web.dto.UserDTO;
import com.cratas.mpls.web.dto.WebServiceResponseDTO;

/**
 * 
 * @author sunil
 *
 */
@RestController
@RequestMapping("/rest/webservices/app/user")
public class AppUserRestWebServiceController extends RestWebServiceController {
	
	   private final static Logger LOGGER = LoggerFactory.getLogger(AppUserRestWebServiceController.class);
		   
	   @Autowired
	   private IUtilityService utilityService;
	   
	   @Autowired
	   private IUserService userService;
	   
	   @Autowired
	   private IBCryptService bCryptService;
	   
	   @Autowired
	   private IAESEncryptionService aesService;
	   
	   @Autowired
	   private IGroupService groupService;
	   
	   @Autowired
	   private IAppNotificationService appNotificationService;
	   
	   @Autowired
	   private ICustomerService customerService;
	   
	   @Autowired
	   private IMerchantProfileService merchantProfileService;
	   
	   @RequestMapping(value = "/setPin", method = RequestMethod.POST)
	   public ResponseEntity<WebServiceResponseDTO> setPin(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestBody ApiUserDTO apiUser) throws Exception {
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				  if(validatate(apiUser, 1)) {
					  if(isValidDeviceForFP(apiUser, deviceId)) {
						  UserDTO userDTO = userService.getUserbyId(utilityService.convertStringToInt(apiUser.getUserId()));
						  if(userDTO != null) {
						      int isUpdate = userService.setLoginPin(userDTO, apiUser);
						      if(isUpdate == 1) {
			                      return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.IS_VALID_PIN, RestAPIConstants.TRUE), Constants.SUCCESS.toUpperCase())); 
						      }
							  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.LOGIN_PIN_VALIDATION_ERROR_MSG, null, Constants.FAILURE.toUpperCase()));
						  }
					  }
					  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.DEVICE_ERROR_MSG, null, Constants.FAILURE.toUpperCase()));
				  }  
				  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_REQUEST_PARAMETER, null, Constants.FAILURE.toUpperCase()));
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	   }
	   
	   @RequestMapping(value = "/reset-password-mail", method = RequestMethod.POST)
	   public ResponseEntity<WebServiceResponseDTO> resetPasswordMail(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestBody ApiUserDTO apiUserDTO) throws Exception {
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				  if(validatate(apiUserDTO, 2)) {
					  UserDTO userDTO = userService.getUserByLoginIdMobileNo(apiUserDTO.getLoginId(), apiUserDTO.getLoginId());
					  if(userDTO != null) {
						   userService.sendResetPasswordMail(userDTO);
			               return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.RESET_PASSWORD, RestWebServiceErrConstants.RESET_PASSWORD_MSG), Constants.SUCCESS.toUpperCase()));
					  }
					  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.USER_NOT_FOUND, null, Constants.FAILURE.toUpperCase()));
				  }  
				  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_USER_CREDENTIAL, null, Constants.FAILURE.toUpperCase()));
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	   }
	   
	   @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	   public ResponseEntity<WebServiceResponseDTO> updatePassword(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestBody UserDTO loginReqDTO) throws Exception {
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				  if(validatateUserInfo(loginReqDTO, 1)) {
						UserDTO userDTO = userService.getUserbyId(loginReqDTO.getId());
						if (userDTO != null) {
							String secretKey = appLoginSecretKey(token);
						    if (loginReqDTO.getLoginId().equals(userDTO.getLoginId())) {
								String decyptPass = aesService.decrypt(secretKey, secretKey, loginReqDTO.getLoginId(), loginReqDTO.getPassword());
								String encryptPass = bCryptService.getEncryptedString(decyptPass);
								GroupDTO groupDTO = groupService.getGroupById(userDTO.getGroupId());
								userDTO.setLastPassword(userDTO.getPassword());
								userDTO.setPassword(encryptPass);
								userDTO.setUpdatedBy(userDTO.getLoginId());
								userService.changePassword(userDTO);
								userService.savePasswordHistory(userDTO.getLoginId(), encryptPass, groupDTO.getPassHistoryChecks());
								return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.UPDATE_PASSWORD, RestWebServiceErrConstants.UPDATE_PASSWORD_MSG), Constants.SUCCESS.toUpperCase()));
						    }
						}
						return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.USER_NOT_FOUND, null, Constants.FAILURE.toUpperCase()));
				  }
				  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_USER_CREDENTIAL, null, Constants.FAILURE.toUpperCase()));
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	   }
	   
	   @RequestMapping(value = "/validate/password", method = RequestMethod.POST)
	   public ResponseEntity<WebServiceResponseDTO> passwordChecker(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestBody UserDTO loginReqDTO) {
		      WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				  if(validatateUserInfo(loginReqDTO, 1)) {
		            UserDTO userDTO = userService.getUserbyId(loginReqDTO.getId());
		            if (userDTO != null) {
			            String secretKey = appLoginSecretKey(token);
			            if (loginReqDTO.getLoginId().equals(userDTO.getLoginId())) {
			                GroupDTO groupDTO = groupService.getGroupById(userDTO.getGroupId());
			                String decryptPass = aesService.decrypt(secretKey, secretKey, loginReqDTO.getLoginId(), loginReqDTO.getPassword());
			                String pattern = userService.getPasswordPattern(groupDTO);
			                boolean isExistsPassword = userService.isExistsPassword(decryptPass, userDTO.getLoginId());
			                if (decryptPass.matches(pattern) && !isExistsPassword) {
			                	return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.IS_VALIDATE, Constants.STR_TRUE), Constants.SUCCESS.toUpperCase()));
			                } else if (isExistsPassword) {
			                    String msg =  InformationMsgConstants.EXISTS_PASSWORD_MSG.replace(Constants.REPLACE_NUMBER, String.valueOf(groupDTO.getPassHistoryChecks()));
								return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, msg, null, Constants.FAILURE.toUpperCase()));
			                }
			                return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, utilityService.createPasswordPatternMessage(groupDTO), null, Constants.FAILURE.toUpperCase()));
			            }
		            }
		            return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.USER_NOT_FOUND, null, Constants.FAILURE.toUpperCase()));
		      }
			}
		    return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	   }
	   
	   @RequestMapping(value = "/fingerprint", method = RequestMethod.POST)
	   public ResponseEntity<WebServiceResponseDTO> setFingerprint(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestBody ApiUserDTO apiUser) throws Exception {
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				  if(validatate(apiUser, 2)) {
					  if(isValidDeviceForFP(apiUser, deviceId)) {
						  LOGGER.info("Fingerprint -> Device ID --> "+deviceId);
						  UserDTO userDTO = userService.getUserByLoginId(apiUser.getLoginId());
						  if(userDTO != null) {
						      int isUpdate = userService.enableOrDisbleFingerPrint(userDTO, apiUser.getIsEnableFingerPrint());
						      if(isUpdate == 1) {
			                      return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.IS_ENABLE_FINGER_PRINT, RestAPIConstants.TRUE), Constants.SUCCESS.toUpperCase())); 
						      }
						      LOGGER.info("Fingerprint -> Device ID --> Error -> "+deviceId);
							  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.LOGIN_FINGER_PRINT_ERROR_MSG, null, Constants.FAILURE.toUpperCase()));
						  }
					  }
					  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.DEVICE_ERROR_MSG, null, Constants.FAILURE.toUpperCase()));
				  }  
				  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_REQUEST_PARAMETER, null, Constants.FAILURE.toUpperCase()));
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	   }
	   
	   private boolean validatate(ApiUserDTO apiUserDTO, int type) {
		   	   switch (type) {
					case 1:
					    return (StringUtils.isNotEmpty(apiUserDTO.getApiPin()) && StringUtils.isNotEmpty(apiUserDTO.getLoginId()) && StringUtils.isNotEmpty(apiUserDTO.getUserId()));
					case 2:
						return (StringUtils.isNotEmpty(apiUserDTO.getLoginId()));
					default:
						return false;
			   }
	   }
	   
	   public boolean isValidDeviceForFP(ApiUserDTO apiUser, String deviceId) {
		      UserDTO user = userService.getUserByLoginId(apiUser.getLoginId());
		      if(null != user) {
		    	  AppNotificationDTO appNotificationDTO  = appNotificationService.findByDeviceId(deviceId);
		    	  if(null != appNotificationDTO) {
		    		   if(appNotificationDTO.getcId() != 0) {
		    			   CustomerDTO customer = customerService.getCustomerByCId(appNotificationDTO.getcId());
		    			   return customer != null ? user.getId() == customer.getUserId() : false;
		    		   }else if(appNotificationDTO.getmId() != 0) {
		    			   MerchantUserDTO merchantUser = merchantProfileService.getMerchantUserIdByMID(appNotificationDTO.getmId());
		    			   return merchantUser != null ? user.getId() == merchantUser.getUserId() : false;
		    		   }
		    	  }
		      }
	    	  return true;  
       }
	   
	   private String appLoginSecretKey(String token){
			   String hexVal = utilityService.toHexaDecimal(token);
			   if(StringUtils.isNotEmpty(hexVal) && hexVal.length() > 32){
				    hexVal = hexVal.substring(0,32);
			   }
			   return hexVal;
	   }
	   
	   private boolean validatateUserInfo(UserDTO apiUserDTO, int type) {
		   	   switch (type) {
					case 1:
						return (StringUtils.isNotEmpty(apiUserDTO.getLoginId()) && StringUtils.isNotEmpty(apiUserDTO.getPassword()));
					default:
						return false;
			   }
	   }
	   
	  
}
