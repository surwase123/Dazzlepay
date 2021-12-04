package com.cratas.mpls.web.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.constant.RestAPIConstants;
import com.cratas.mpls.common.utility.constant.RestWebServiceErrConstants;
import com.cratas.mpls.service.IPushNotificationLogService;
import com.cratas.mpls.service.IRequestLoyaltyCardService;
import com.cratas.mpls.web.dto.LoyaltyCashbackDTO;
import com.cratas.mpls.web.dto.MerchantDTO;
import com.cratas.mpls.web.dto.RequestLoyaltyCardDTO;
import com.cratas.mpls.web.dto.WebServiceResponseDTO;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/webservices/app/requestLoyaltyCard")
public class RequestLoyaltyCardRestWebServiceController extends RestWebServiceController  {
	
	 private final static Logger LOGGER = LoggerFactory.getLogger(RequestLoyaltyCardRestWebServiceController.class);
	 
	 @Autowired
	 private IRequestLoyaltyCardService requestLoyaltyCardService;
	 
	 @Autowired
	 private IPushNotificationLogService pushNotificationLogService;
	 
	 @RequestMapping(value = "/request", method = RequestMethod.POST)
	    public ResponseEntity<WebServiceResponseDTO> addLoyaltyCashback(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestBody RequestLoyaltyCardDTO requestLoyaltyCardDTO) throws Exception {
			   WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			   if(validateToken(token, deviceId)) {
				  int isInsert = requestLoyaltyCardService.saveRequest(requestLoyaltyCardDTO);
				  if(isInsert==1)
				  {
					  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.MERCHANT_REQUEST_SUCCESS, null, Constants.SUCCESS.toUpperCase()));
				  }
				  else {
					  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.MERCHANT_REQUEST_FAILURE, null, Constants.FAILURE.toUpperCase()));
				  }
				 
//				  return redirectLoyaltyCashbackAPIResponse(isInsert, webServiceResponse, loyaltyCashbackDTO);
			   } 
			   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, Constants.BLANK, Constants.FAILURE.toUpperCase()));
	    }
	 
	 @RequestMapping(value = "/notification/view", method = RequestMethod.GET)
	    public ResponseEntity<WebServiceResponseDTO> updateNotification(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestParam("id") int id) throws Exception {
			   WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			   if(validateToken(token, deviceId)) {
				  int isInsert = pushNotificationLogService.updatePushNotificationLog(0,1,id);
				  if(isInsert==1)
				  {
					  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.REQUEST_SUCCESS, null, Constants.SUCCESS.toUpperCase()));
				  }
				  else {
					  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.REQUEST_FAILURE, null, Constants.FAILURE.toUpperCase()));
				  }
				 
//				  return redirectLoyaltyCashbackAPIResponse(isInsert, webServiceResponse, loyaltyCashbackDTO);
			   } 
			   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, Constants.BLANK, Constants.FAILURE.toUpperCase()));
	    }
}
