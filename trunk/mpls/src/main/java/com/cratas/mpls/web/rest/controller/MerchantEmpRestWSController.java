package com.cratas.mpls.web.rest.controller;

import java.util.List;

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
import com.cratas.mpls.service.IMerchantEmployeeService;
import com.cratas.mpls.web.dto.MerchantEmployeeDTO;
import com.cratas.mpls.web.dto.WebServiceResponseDTO;


/**
 * 
 * @author mukesh
 *
 */
@RestController
@RequestMapping("/rest/webservices/merchant/employee")
public class MerchantEmpRestWSController extends RestWebServiceController{
		   
	   @Autowired
	   private IMerchantEmployeeService merchantEmployeeService;
	   
	   @RequestMapping(value = "/isExistsMobileNumber/{mobileNumber}", method = RequestMethod.GET)
	   public ResponseEntity<WebServiceResponseDTO> getMerchantEmpByMobileNo(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @PathVariable String mobileNumber) throws Exception {
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				  List<MerchantEmployeeDTO> merchantList = merchantEmployeeService.getMerchantEmpByMobileNo(mobileNumber);
				  if(merchantList.size() > 0) {
					  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.EXISTS_MERCHANT_EMPLOYEE_MOBILE_NUMBER, null, Constants.FAILURE.toUpperCase()));
				  }
				  return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, Constants.BLANK, null), Constants.SUCCESS.toUpperCase()));
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	   }	 
	   
	   @RequestMapping(value = "/isExistsEmailId", method = RequestMethod.GET)
	   public ResponseEntity<WebServiceResponseDTO> getMerchantEmpByEmailID(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestParam("emailId") String emailId) throws Exception {
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				  List<MerchantEmployeeDTO> merchantList = merchantEmployeeService.getMerchantEmpByEmailId(emailId);
				  if(merchantList.size() > 0) {
					  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.EXISTS_MERCHANT_EMP_EMAIL_ID_MSG, null, Constants.FAILURE.toUpperCase()));
				  }
				  return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, Constants.BLANK, null), Constants.SUCCESS.toUpperCase()));
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
		}	   

	   @RequestMapping(value = "/list/{mId}", method = RequestMethod.GET)
	   public ResponseEntity<WebServiceResponseDTO> getMerchnatEmployee(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @PathVariable int mId) throws Exception {
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				  List<MerchantEmployeeDTO> merchantEmpList = merchantEmployeeService.getMerchantEmployeeByMId(mId);
				  return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.MERCHANT_EMPLOYEE, merchantEmpList), Constants.SUCCESS.toUpperCase()));
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	   }
	   
	   @RequestMapping(value = "/add",  method = RequestMethod.POST)
	   public ResponseEntity<WebServiceResponseDTO> addMerchantEmp(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestBody MerchantEmployeeDTO merchantEmployeeDTO) throws Exception {
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				  int isInsert = merchantEmployeeService.saveMerchantEmployee(merchantEmployeeDTO, null);
				  return redirectMerchantEmpAPIResponse(isInsert, webServiceResponse, merchantEmployeeDTO);
			  } 
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, Constants.BLANK, Constants.FAILURE.toUpperCase()));
	   }
		
	   @RequestMapping(value = "/edit",  method = RequestMethod.POST)
	   public ResponseEntity<WebServiceResponseDTO> editMerchantEmp(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestBody MerchantEmployeeDTO merchantEmployeeDTO) throws Exception {
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				   int isUpdate = merchantEmployeeService.updateMerchantEmployee(merchantEmployeeDTO);
				   if(isUpdate == 2){
					   return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.MESSAGE, RestWebServiceErrConstants.MERCHANT_EMPLOYEE_UPDATE), Constants.SUCCESS.toUpperCase()));
				   }else{
					   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.MERCHANT_EMPLOYEE_UPDATE_ERROR, Constants.BLANK, Constants.FAILURE.toUpperCase()));
				   } 
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, Constants.BLANK, Constants.FAILURE.toUpperCase()));
	   }
		
	   @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	   public ResponseEntity<WebServiceResponseDTO> deleteMerchantEmp(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestBody MerchantEmployeeDTO merchantEmployeeDTO) throws Exception {
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				  int isDeleted = merchantEmployeeService.deleteMerchantEmployee(merchantEmployeeDTO);
				  if(isDeleted > 0){
				     return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.MESSAGE, Constants.STR_TRUE), Constants.SUCCESS.toUpperCase()));
				  }else{
					 return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.UNABLE_TO_DELETE_RECORD, Constants.BLANK, Constants.FAILURE.toUpperCase()));
				  }
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	   }
	   
	   private ResponseEntity<WebServiceResponseDTO> redirectMerchantEmpAPIResponse(int isInsert,  WebServiceResponseDTO webServiceResponse, MerchantEmployeeDTO merchantEmployeeDTO){
		       if(isInsert > 1){
			        return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.MESSAGE, RestWebServiceErrConstants.MERCHANT_EMPLOYEE_ADDED), Constants.SUCCESS.toUpperCase()));
			   }else if(isInsert == 2){
				    return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.MERCHANT_EMPLOYEE_ALREADY_EXISTS, Constants.BLANK, Constants.FAILURE.toUpperCase()));
			   }else{
				    return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.MERCHANT_EMPLOYEE_ERROR, Constants.BLANK, Constants.FAILURE.toUpperCase()));
			   }
       }
}
