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
import com.cratas.mpls.service.ICustomerTransactionService;
import com.cratas.mpls.service.IMerchantMappingService;
import com.cratas.mpls.service.IMerchantService;
import com.cratas.mpls.service.IPushNotificationLogService;
import com.cratas.mpls.web.dto.ApiUserDTO;
import com.cratas.mpls.web.dto.CustomerDTO;
import com.cratas.mpls.web.dto.MerchantDTO;
import com.cratas.mpls.web.dto.WebServiceResponseDTO;

/**
 * 
 * @author sunil
 *
 */
@RestController
@RequestMapping("/rest/webservices/app/customer")
public class CustomerRestWebServiceController extends RestWebServiceController {
	
	   @Autowired
	   private ICustomerService customerService;
	   
	   @Autowired
	   private ICustomerTransactionService customerTransactionService;
	   
	   @Autowired
	   private IAppPropertiesService appPropertiesService;
	   
	   @Autowired
	   private IMerchantService merchantService;
	   
	   @Autowired
	   private IMerchantMappingService merchantMappingService;
	   
	   @Autowired
	   private IPushNotificationLogService pushNotificationLogService;
	   
	   @RequestMapping(value = "/add", method = RequestMethod.POST)
	   public ResponseEntity<WebServiceResponseDTO> add(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestBody CustomerDTO customer) throws Exception {
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				  customer = customerService.generateCustomerId(customer);
				  if(validateCustomerInfo(customer, 1)) {
				      int isInsert = customerService.saveCustomer(customer, null,"Customer");
				      return redirectCustomerRegAPIResponse(isInsert, webServiceResponse, customer);
				  }
				  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.REG_CUSTOMER_ERR, null, Constants.FAILURE.toUpperCase()));
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	   }
	   
	   @RequestMapping(value = "/edit", method = RequestMethod.POST)
	   public ResponseEntity<WebServiceResponseDTO> edit(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestBody CustomerDTO customer) throws Exception {
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			 
			  if(validateToken(token, deviceId)) {
				  if(validateCustomerInfo(customer, 2)) {
					
					  int isUpdate = customerService.updateCustomer(customer);
				      if(isUpdate == 2) {
					   
				    	  return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.CUSTOMER, getApiResponse(null, RestAPIConstants.UPDATE,true)), Constants.SUCCESS.toUpperCase())); 
				      }
				  }
				  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.EDIT_CUSTOMER_PROFILE_ERR, null, Constants.FAILURE.toUpperCase()));
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	   }
	   
	   @RequestMapping(value = "/profile/{userId}", method = RequestMethod.GET)
	   public ResponseEntity<WebServiceResponseDTO> getCustomerProfile(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token ,@PathVariable int userId) throws Exception {
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				   CustomerDTO profile = customerService.getCustomerById(userId);
				   if(profile != null) {
					   return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.PROFILE, profile), Constants.SUCCESS.toUpperCase()));
				   }
				   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_REQUEST, null, Constants.FAILURE.toUpperCase()));
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	   }
	   
	   @RequestMapping(value = "/transactionHistory/{cId}", method = RequestMethod.GET)
	   public ResponseEntity<WebServiceResponseDTO> getCustomerTransactionHistory(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token ,@PathVariable int cId, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) throws Exception {
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				  if(validateCustomerDetails(fromDate, toDate, 1)) {
					   HashMap<String, Object> custTransList = (HashMap<String, Object>) getApiResponse(null, RestAPIConstants.CUSTOMER_TRANSACTION_LIST, customerTransactionService.getCustomerTransaction(cId, getFromDate(fromDate), getToDate(toDate)));
					   getApiResponse(custTransList, RestAPIConstants.CUSTOMER_ID_PREFIX, appPropertiesService.getSystemParamValueByName(Constants.CUSTOMER_ID_PREFIX));
					   return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(custTransList, RestAPIConstants.MERCHANT_ID_PREFIX, appPropertiesService.getSystemParamValueByName(Constants.MERCHANT_ID_PREFIX)), Constants.SUCCESS.toUpperCase()));
				  }
				  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.CUSTOMER_INVALID_PARAM, null, Constants.FAILURE.toUpperCase()));
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	   }
	   
	   @RequestMapping(value = "/merchant/{userId}", method = RequestMethod.GET)
	   public ResponseEntity<WebServiceResponseDTO> getCustomerMerchantMapping(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @PathVariable("userId") int userId) throws Exception {
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				   HashMap<String, Object> merchantList = (HashMap<String, Object>) getApiResponse(null, RestAPIConstants.CUSTOMER_MERCHANT_MAPPING_LIST, merchantService.customerMerchantMappingList(userId));
				   return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(merchantList, RestAPIConstants.MERCHANT_ID_PREFIX, appPropertiesService.getSystemParamValueByName(Constants.MERCHANT_ID_PREFIX)), Constants.SUCCESS.toUpperCase()));
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	   }
	   
	   @RequestMapping(value = "/dashboard/{userId}", method = RequestMethod.GET)
	   public ResponseEntity<WebServiceResponseDTO> getCustomerDashboard(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @PathVariable("userId") int userId) throws Exception {
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				   HashMap<String, Object> customerMerchantList = (HashMap<String, Object>) getApiResponse(null, RestAPIConstants.MERCHANT_LIST, merchantService.customerMerchantMappingList(userId));
				   getApiResponse(customerMerchantList, RestAPIConstants.CUSTOMER_TRANSACTION_LIST, customerTransactionService.getTopCustomerTransaction(userId));
				   getApiResponse(customerMerchantList, RestAPIConstants.CUSTOMER, customerService.getCustomerById(userId));
				   getApiResponse(customerMerchantList, RestAPIConstants.CUSTOMER_ID_PREFIX, appPropertiesService.getSystemParamValueByName(Constants.CUSTOMER_ID_PREFIX));
				   return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(customerMerchantList, RestAPIConstants.MERCHANT_ID_PREFIX, appPropertiesService.getSystemParamValueByName(Constants.MERCHANT_ID_PREFIX)), Constants.SUCCESS.toUpperCase()));
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	   }
	   
	   private ResponseEntity<WebServiceResponseDTO> redirectCustomerRegAPIResponse(int isInsert, WebServiceResponseDTO webServiceResponse, CustomerDTO customer) {
			   switch(isInsert){
					 case 1:
						  CustomerDTO customerDTO = customerService.getCustomerByCustomerId(customer.getCustomerId());
				    	  if(customerDTO != null) {
		                      return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.USER, setUserInfo(customerDTO)), Constants.SUCCESS.toUpperCase()));
				    	  }
					 case 2:
						 return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.CUSTOMER_EXISTS, null, Constants.FAILURE.toUpperCase()));
					 case 3:
						 return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.CUSTOMER_USER_INFO_ERR, null, Constants.FAILURE.toUpperCase()));
					 case 4:
						 return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.USER_REG_AS_MERCHANT, null, Constants.FAILURE.toUpperCase()));
					 case 5:
						 return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.LOGIN_ID_EXISTS, null, Constants.FAILURE.toUpperCase()));
					 default:
						 return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.REG_CUSTOMER_ERR, null, Constants.FAILURE.toUpperCase()));
			   }
	   }
	   
	   private boolean validateCustomerInfo(CustomerDTO customer, int type) {
			   switch (type) {
					case 1:
						 return (StringUtils.isNotEmpty(customer.getLoginId()) && StringUtils.isNotEmpty(customer.getFirstName()) && StringUtils.isNotEmpty(customer.getLastName()) && StringUtils.isNotEmpty(customer.getEmailId()) && StringUtils.isNotEmpty(customer.getMobileNumber()));
					case 2:
						 return (StringUtils.isNotEmpty(customer.getFirstName()) && StringUtils.isNotEmpty(customer.getLastName()) && StringUtils.isNotEmpty(customer.getEmailId()) && StringUtils.isNotEmpty(customer.getMobileNumber())); 
					default:
						 return false;
			   }
	   }
	   
	   private boolean validateCustomerDetails(String fromDate, String toDate, int type) {
			   switch (type) {
					case 1:
						 return (StringUtils.isNotEmpty(fromDate) && StringUtils.isNotEmpty(toDate));
					default:
						 return false;
			   }
	   }
	   
	   private ApiUserDTO setUserInfo(CustomerDTO customerDTO) {
			   ApiUserDTO userDTO = new ApiUserDTO();
			   userDTO.setId(customerDTO.getId());
			   userDTO.setcId(customerDTO.getCustomerId());
			   userDTO.setLoginId(customerDTO.getLoginId());
			   userDTO.setUserId(String.valueOf(customerDTO.getUserId()));
			   return userDTO;
	   }
	    
	   @RequestMapping(value = "/transactionHistory/{cId}/{mId}", method = RequestMethod.GET)
	   public ResponseEntity<WebServiceResponseDTO> getCustomerTransactionHistoryByMid(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token , @PathVariable int cId, @PathVariable int mId, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) throws Exception {
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				  if(validateCustomerDetails(fromDate, toDate, 1)) {
					   HashMap<String, Object> custTransList = (HashMap<String, Object>) getApiResponse(null, RestAPIConstants.CUSTOMER_TRANSACTION_LIST, customerTransactionService.getCustomerTransactionByMId(cId, mId, getFromDate(fromDate), getToDate(toDate)));
					   getApiResponse(custTransList, RestAPIConstants.CUSTOMER_ID_PREFIX, appPropertiesService.getSystemParamValueByName(Constants.CUSTOMER_ID_PREFIX));
					   return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(custTransList, RestAPIConstants.MERCHANT_ID_PREFIX, appPropertiesService.getSystemParamValueByName(Constants.MERCHANT_ID_PREFIX)), Constants.SUCCESS.toUpperCase()));
				  }
				  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.CUSTOMER_INVALID_PARAM, null, Constants.FAILURE.toUpperCase()));
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	   }
	   
	   @RequestMapping(value = "/updateCustomerStatus/{mId}", method = RequestMethod.POST)
	   public ResponseEntity<WebServiceResponseDTO> updateCustomerStatus(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestBody CustomerDTO customer, @PathVariable int mId, @RequestParam("notificationId") int notificationId){
		      WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				  MerchantDTO merchant = merchantService.getMerchantById(mId);
				  if(customer != null){
					  if(customer.getRequest().equals(Constants.ACCEPT)) {
						  	int isUpdated = merchantMappingService.updateMerchantMapping(customer.getId(), mId);
				  			if(isUpdated ==1) {
				  				pushNotificationLogService.updatePushNotificationLog(1,1, notificationId);
				  				return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.MESSAGE, RestWebServiceErrConstants.MERCHANT_MAPPING_ACCEPT+merchant.getMerchantName()+RestWebServiceErrConstants.SUCCESS), Constants.SUCCESS.toUpperCase()));
				  			}
				  			return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.NOT_MAPPED_CUSTOMER, null, Constants.FAILURE.toUpperCase()));
					  }else {
						  	 int isUpdated = merchantMappingService.deleteMerchantMapping(customer.getId(), mId);
					  		 if(isUpdated >=1) {
					  			  pushNotificationLogService.updatePushNotificationLog(2,1, notificationId);
								  return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.MESSAGE, RestWebServiceErrConstants.MERCHANT_MAPPING_REJECTED+merchant.getMerchantName()+RestWebServiceErrConstants.SUCCESS), Constants.SUCCESS.toUpperCase()));
					  		 }
					  }
				  }
				  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.CUSTOMER_INVALID_PARAM, null, Constants.FAILURE.toUpperCase()));
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	   }	  
}
