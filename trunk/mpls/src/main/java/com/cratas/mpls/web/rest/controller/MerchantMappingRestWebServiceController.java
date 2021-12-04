package com.cratas.mpls.web.rest.controller;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
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
import com.cratas.mpls.common.utility.constant.RestWebServiceErrConstants;
import com.cratas.mpls.service.IAppPropertiesService;
import com.cratas.mpls.service.ICustomerService;
import com.cratas.mpls.service.ILoyaltyCashbackService;
import com.cratas.mpls.service.IMerchantMappingService;
import com.cratas.mpls.service.IMerchantService;
import com.cratas.mpls.service.IPushNotificationLogService;
import com.cratas.mpls.service.IUserService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.CustomerDTO;
import com.cratas.mpls.web.dto.MerchantDTO;
import com.cratas.mpls.web.dto.MerchantMappingDTO;
import com.cratas.mpls.web.dto.UserDTO;
import com.cratas.mpls.web.dto.WebServiceResponseDTO;

/**
 * 
 * @author sunil
 *
 */
@RestController
@RequestMapping("/rest/webservices/app/store")
public class MerchantMappingRestWebServiceController extends RestWebServiceController {
	   
	   @Autowired
	   private IUtilityService utilityService;
	   
	   @Autowired
	   private IMerchantService merchantService;
	   
	   @Autowired
	   private ICustomerService customerService;
	   
	   @Autowired
	   private IAppPropertiesService appPropertiesService;
	   
	   @Autowired
	   private IMerchantMappingService merchantMappingService;
	   
	   @Autowired
	   private ILoyaltyCashbackService loyaltyCashbackService;
	   
	   @Autowired
	   private IUserService userService;
	   
	   @Autowired
	   private IPushNotificationLogService pushNotificationLogService;
	   
	   @RequestMapping(value = "/search/{userId}", method = RequestMethod.GET)
	   public ResponseEntity<WebServiceResponseDTO> getStoreList(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @PathVariable("userId") int userId) throws Exception {
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				   UserDTO user = userService.getUserbyId(userId);
				   int displayMerchantCount = utilityService.convertStringToInt(appPropertiesService.getSystemParamValueByName(Constants.DISPLAY_MERCHANT_COUNT));
				   HashMap<String, Object> storeList = (HashMap<String, Object>) getApiResponse(null, RestAPIConstants.STORE_LIST, merchantService.recentMerchantList(displayMerchantCount, user));
				   return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK,getApiResponse(storeList, RestAPIConstants.MERCHANT_ID_PREFIX, appPropertiesService.getSystemParamValueByName(Constants.MERCHANT_ID_PREFIX)), Constants.SUCCESS.toUpperCase()));
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	   }
	   
	   @RequestMapping(value = "/filter", method = RequestMethod.GET)
	   public ResponseEntity<WebServiceResponseDTO> getFilterStoreList(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestParam("userId") String userId, @RequestParam("areaId") String areaId) throws Exception {
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				  if(validateMerchantMappingDetails(userId, areaId, null, null, null, 1)) {
					   UserDTO user = userService.getUserbyId(utilityService.convertStringToInt(userId));
					   HashMap<String, Object> filterStore = (HashMap<String, Object>) getApiResponse(null, RestAPIConstants.FILTER_STORE_LIST, merchantService.filterMerchantListByArea(areaId, user));
					   return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(filterStore, RestAPIConstants.MERCHANT_ID_PREFIX, appPropertiesService.getSystemParamValueByName(Constants.MERCHANT_ID_PREFIX)), Constants.SUCCESS.toUpperCase()));
				  }
				  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.MERCHANT_MAPPING_INVALID_PARAM, null, Constants.FAILURE.toUpperCase()));
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	   }
	   
	   @RequestMapping(value = "/mappingMerchant", method = RequestMethod.GET)
	   public ResponseEntity<WebServiceResponseDTO> mappingMerchant(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestParam("userId") String userId, @RequestParam("merchantId") String merchantId) throws Exception {
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				  if(validateMerchantMappingDetails(userId, null, merchantId, null, null, 2)) {
					     CustomerDTO customerDTO = customerService.getCustomerById(utilityService.convertStringToInt(userId));
					     MerchantDTO merchant = merchantService.getMerchantByMId(merchantId);
					     if(customerDTO != null && merchant != null ){
					    	  MerchantMappingDTO merchantMapping = merchantMappingService.getMerchantMappingInstance(customerDTO, merchant, customerDTO.getLoginId());
					    	  int isInserted = merchantMappingService.saveMerchantMapping(merchantMapping, Constants.MERCHANT);
							  if(isInserted == 1) {
								  return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK,getApiResponse(null, RestAPIConstants.MERCHANT_MAPPING, RestWebServiceErrConstants.MERCHANT_MAPPING_SUCCESS), Constants.SUCCESS.toUpperCase()));
							  }else if(isInserted == 2) {
								  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.MERCHANT_ALREADY_ADDED, null, Constants.FAILURE.toUpperCase()));
							  }
							  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.MERCHANT_MAPPING_ERROR, null, Constants.FAILURE.toUpperCase()));
					     }
				  }
				  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.MERCHANT_MAPPING_INVALID_PARAM, null, Constants.FAILURE.toUpperCase()));
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	   }
	   
	   @RequestMapping(value = "/deleteMerchant/{cId}", method = RequestMethod.DELETE)
	   public ResponseEntity<WebServiceResponseDTO> deleteMerchant(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @PathVariable("cId") String cId, @RequestParam("loginId") String loginId, @RequestParam("mId") String mId) throws Exception {
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				  if(validateMerchantMappingDetails(null, null, mId, cId, loginId, 3)) {
					  int isUpdate = merchantMappingService.deleteMerchantMapping(loginId, mId, cId);
			    	  if(isUpdate == 1) {
						   return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK,getApiResponse(null, RestAPIConstants.MERCHANT_MAPPING, RestWebServiceErrConstants.MERCHANT_MAPPING_DELETE_SUCCESS), Constants.SUCCESS.toUpperCase())); 
			    	  }else {
						   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.REMOVE_MERCHANT_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
			    	  }
				  }
				  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.MERCHANT_MAPPING_INVALID_PARAM, null, Constants.FAILURE.toUpperCase()));
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	   }
	   
	   @RequestMapping(value = "/loyaltyCashback/{mId}", method = RequestMethod.GET)
	   public ResponseEntity<WebServiceResponseDTO> getMerchantOffers(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @PathVariable("mId") int mId) throws Exception {
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				   HashMap<String, Object> loyaltyCashbackList = (HashMap<String, Object>) getApiResponse(null, RestAPIConstants.LOYALTY_CASHBACK_LIST, loyaltyCashbackService.getLoyaltyCashbackByMId(mId));
				   return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK,getApiResponse(loyaltyCashbackList, RestAPIConstants.TRANS_CASHBACK_TYPE, appPropertiesService.getSystemParamValueByName(Constants.TRANS_CASHBACK_TYPE)), Constants.SUCCESS.toUpperCase()));
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	   }
	   
	   private boolean validateMerchantMappingDetails(String userId, String areaId, String merchantId, String cId, String loginId, int type) {
		   	   switch(type) {
		   	   	   case 1:
			           return (StringUtils.isNotEmpty(userId) && StringUtils.isNotEmpty(areaId));
		   	   	   case 2:
				       return (StringUtils.isNotEmpty(userId) && StringUtils.isNotEmpty(merchantId));
		   	   	   case 3:
				       return (StringUtils.isNotEmpty(merchantId) && StringUtils.isNotEmpty(cId) && StringUtils.isNotEmpty(loginId));
				   default:
					   return false;  
		   	   }
	   }
	   
	   @RequestMapping(value = "/filterLocation", method = RequestMethod.GET)
	   public ResponseEntity<WebServiceResponseDTO> getFilterStoreListByLocation(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestParam("areaId") String areaId, @RequestParam("pinCode") String pinCode) throws Exception {
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				  if(StringUtils.isNotEmpty(areaId) || StringUtils.isNotEmpty(pinCode)) {
					   HashMap<String, Object> filterStore = (HashMap<String, Object>) getApiResponse(null, RestAPIConstants.FILTER_STORE_LIST, merchantService.filterMerchantByAreaPin(areaId, pinCode));
					   return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(filterStore, RestAPIConstants.MERCHANT_ID_PREFIX, appPropertiesService.getSystemParamValueByName(Constants.MERCHANT_ID_PREFIX)), Constants.SUCCESS.toUpperCase()));
				  }
				  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.MERCHANT_MAPPING_INVALID_PARAM, null, Constants.FAILURE.toUpperCase()));
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	   }
	  
	   @RequestMapping(value = "/updateMerchantMapping/{cId}", method = RequestMethod.POST)
	   public ResponseEntity<WebServiceResponseDTO> updateCustomerStatus(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestBody MerchantDTO merchant, @PathVariable int cId, @RequestParam("notificationId") int notificationId){
		      WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				  if(merchant != null){
					  if(merchant.getRequest().equals(Constants.ACCEPT)) {
					  		int isUpdated = merchantMappingService.updateMerchantMapping(cId, merchant.getId());
				  			if(isUpdated ==1) {
				  				pushNotificationLogService.updatePushNotificationLog(1,1, notificationId);
				  				return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.MESSAGE, RestWebServiceErrConstants.MERCHANT_DETAIL_SUCCESS), Constants.SUCCESS.toUpperCase()));
				  			}
				  			return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.NOT_MAPPED_CUSTOMER, null, Constants.FAILURE.toUpperCase()));
					  }
					  pushNotificationLogService.updatePushNotificationLog(2,1, notificationId);
					  return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.MESSAGE, RestWebServiceErrConstants.MERCHANT_MAPPING_REJECTED), Constants.SUCCESS.toUpperCase()));
				  }
				  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.CUSTOMER_INVALID_PARAM, null, Constants.FAILURE.toUpperCase()));
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	   }
}
