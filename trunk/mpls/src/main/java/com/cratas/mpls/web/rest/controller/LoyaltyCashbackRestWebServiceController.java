package com.cratas.mpls.web.rest.controller;

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
import com.cratas.mpls.service.ILoyaltyCashbackService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.LoyaltyCashbackDTO;
import com.cratas.mpls.web.dto.WebServiceResponseDTO;

/**
 * @author bhupendra
 *
 */
@RestController
@RequestMapping("/rest/webservices/loyaltyCashback")
public class LoyaltyCashbackRestWebServiceController extends RestWebServiceController {

		@Autowired
		private ILoyaltyCashbackService loyaltyCashbackService;
		
		@Autowired
		private IAppPropertiesService appPropertiesService;
		
		@Autowired
		private IUtilityService utilityService;
		
		@RequestMapping(value = "/transactionType", method = RequestMethod.GET)
	    public ResponseEntity<WebServiceResponseDTO> getStoreList(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token) throws Exception {
			   WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			   if(validateToken(token, deviceId)) {
				   return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK,getApiResponse(null, RestAPIConstants.TRANS_CASHBACK_TYPE, appPropertiesService.getSystemParamValueByName(Constants.TRANS_CASHBACK_TYPE)), Constants.SUCCESS.toUpperCase()));
			   }
			   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	    }
		
		@RequestMapping(value = "/add",  method = RequestMethod.POST)
	    public ResponseEntity<WebServiceResponseDTO> addLoyaltyCashback(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestBody LoyaltyCashbackDTO loyaltyCashbackDTO) throws Exception {
			   WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			   if(validateToken(token, deviceId)) {
				  int isInsert = loyaltyCashbackService.saveLoyaltyCashback(loyaltyCashbackDTO, null);
				  return redirectLoyaltyCashbackAPIResponse(isInsert, webServiceResponse, loyaltyCashbackDTO);
			   } 
			   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, Constants.BLANK, Constants.FAILURE.toUpperCase()));
	    }
		
		@RequestMapping(value = "/edit",  method = RequestMethod.POST)
	    public ResponseEntity<WebServiceResponseDTO> editLoyaltyCashback(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestBody LoyaltyCashbackDTO loyaltyCashbackDTO) throws Exception {
			   WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			   if(validateToken(token, deviceId)) {
				   int isUpdate = loyaltyCashbackService.updateLoyaltyCashback(loyaltyCashbackDTO);
				   if(isUpdate == 1){
					   return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.MESSAGE, RestWebServiceErrConstants.LOYALTY_CASHBACK_UPDATE), Constants.SUCCESS.toUpperCase()));
				   }else{
					   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.LOYALTY_CASHBACK_UPDATE_ERROR, Constants.BLANK, Constants.FAILURE.toUpperCase()));
				   } 
			   }
			   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, Constants.BLANK, Constants.FAILURE.toUpperCase()));
		}
		
	   @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	   public ResponseEntity<WebServiceResponseDTO> getOfferList(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestBody LoyaltyCashbackDTO loyaltyCashbackDTO) throws Exception {
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				  int isInsert = loyaltyCashbackService.deleteLoyaltyCashback(loyaltyCashbackDTO);
				  if(isInsert > 0){
				     return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.MESSAGE, Constants.STR_TRUE), Constants.SUCCESS.toUpperCase()));
				  }else{
					 return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.UNABLE_TO_DELETE_RECORD, Constants.BLANK, Constants.FAILURE.toUpperCase()));
				  }
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	   }
	   
	   @RequestMapping(value = "/applyCoupon/{cId}",  method = RequestMethod.POST)
	   public ResponseEntity<WebServiceResponseDTO> applyCoupon(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestBody LoyaltyCashbackDTO loyaltyCashbackDTO, @RequestParam("amount") double amount, @PathVariable("cId") int cId) throws Exception {
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				  if(validateOfferCode(loyaltyCashbackDTO, amount, 1)){
					   LoyaltyCashbackDTO loyaltyCashback = loyaltyCashbackService.getLoyaltyCashbackByCode(loyaltyCashbackDTO.getOfferCode());
					   if(null != loyaltyCashback){
						     String isValidOffer = loyaltyCashbackService.validateMerchantOffer(loyaltyCashbackDTO.getmId(), cId, amount, loyaltyCashback);
						     if(isValidOffer.equals(Constants.SUCCESS)){
						    	  double cashbackAmt = loyaltyCashbackService.getOfferCashbackAmt(amount, loyaltyCashback);
								  return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.CASHBACK_AMOUNT,  utilityService.decimalFormat(cashbackAmt)), Constants.SUCCESS.toUpperCase()));
						     }
					   }
					   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_OFFER_CODE_ERROR, Constants.BLANK, Constants.FAILURE.toUpperCase()));
				  }
				  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_OFFER_CODE_ERROR, Constants.BLANK, Constants.FAILURE.toUpperCase()));
			  } 
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, Constants.BLANK, Constants.FAILURE.toUpperCase()));
	   }
		
	   private ResponseEntity<WebServiceResponseDTO> redirectLoyaltyCashbackAPIResponse(int isInsert,  WebServiceResponseDTO webServiceResponse, LoyaltyCashbackDTO loyaltyCashbackDTO){
			   if(isInsert == 1){
			        return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.MESSAGE, RestWebServiceErrConstants.LOYALTY_CASHBACK_ADDED), Constants.SUCCESS.toUpperCase()));
			   }else if(isInsert == 2){
				    return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.LOYALTY_CASHBACK_ALREADY_EXISTS, Constants.BLANK, Constants.FAILURE.toUpperCase()));
			   }else if(isInsert == 3){
				    return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.ONLY_ONE_LOYALTY_CASHBACK_PER_MERCHANT, Constants.BLANK, Constants.FAILURE.toUpperCase()));
			   }else{
				    return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.LOYALTY_CASHBACK_ERROR, Constants.BLANK, Constants.FAILURE.toUpperCase()));
			   }
	   }
	   
	   private boolean validateOfferCode(LoyaltyCashbackDTO loyaltyCashbackDTO, double amount, int type) {
		       switch (type) {
					   case 1:
						   return (StringUtils.isNotEmpty(loyaltyCashbackDTO.getOfferCode()) && loyaltyCashbackDTO.getmId() > 0 && amount > 0);
					   default:
						   return false;
			   }
	   }
	   
}
