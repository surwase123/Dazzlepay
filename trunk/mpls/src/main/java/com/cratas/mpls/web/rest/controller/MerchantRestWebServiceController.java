package com.cratas.mpls.web.rest.controller;

import java.util.HashMap;
import java.util.List;

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
import com.cratas.mpls.service.ILoyaltyCardService;
import com.cratas.mpls.service.IMerchantEmployeeService;
import com.cratas.mpls.service.IMerchantMappingService;
import com.cratas.mpls.service.IMerchantProfileService;
import com.cratas.mpls.service.IMerchantService;
import com.cratas.mpls.service.IMerchantTransactionService;
import com.cratas.mpls.service.IUserService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.service.IVerificationService;
import com.cratas.mpls.web.dto.ApiUserDTO;
import com.cratas.mpls.web.dto.CustomerDTO;
import com.cratas.mpls.web.dto.MerchantDTO;
import com.cratas.mpls.web.dto.MerchantEmployeeDTO;
import com.cratas.mpls.web.dto.MerchantMappingDTO;
import com.cratas.mpls.web.dto.UserDTO;
import com.cratas.mpls.web.dto.VerificationLogDTO;
import com.cratas.mpls.web.dto.WebServiceResponseDTO;

/**
 * 
 * @author bhupendra
 *
 */
@RestController
@RequestMapping("/rest/webservices/merchant")
public class MerchantRestWebServiceController extends RestWebServiceController{
	
		@Autowired
	    private IUtilityService utilityService;
	
	    @Autowired
	    private IMerchantService merchantService;
	    
	    @Autowired
	    private IVerificationService verificationService;
	    
	    @Autowired
		private ILoyaltyCardService loyaltyCardService;
	    
	    @Autowired
	    private IUserService userService;
	    
	    @Autowired
		private IMerchantEmployeeService merchantEmployee;
	    
	    @Autowired
		private IMerchantMappingService merchantMappingService;
	    
	    @Autowired
		private IMerchantTransactionService merchantTransService;
	    
	    @Autowired
		private IAppPropertiesService appPropertiesService;
	    
	    @Autowired
		private ICustomerService customerService;
	    
	    @Autowired
	    private IMerchantProfileService merchantProfileService;
		   
		@RequestMapping(value = "/add",  method = RequestMethod.POST)
	    public ResponseEntity<WebServiceResponseDTO> addMerchant(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestBody MerchantDTO merchant) throws Exception {
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				  if(null != validateAndSetReqDetail(merchant)) {
					  if(isVerifiedEmail(merchant)){
						   int isInsert = merchantService.saveMerchant(null, merchant);
						   return redirectMerchantAPIResponse(isInsert, webServiceResponse, merchant);
				      } 
					  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.NOT_VERIFIED_EMAILID,Constants.BLANK, Constants.FAILURE.toUpperCase()));
				  }
				  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_REQUEST, Constants.BLANK, Constants.FAILURE.toUpperCase()));	  
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, Constants.BLANK, Constants.FAILURE.toUpperCase()));
	    }
		
		@RequestMapping(value = "/edit", method = RequestMethod.POST)
	    public ResponseEntity<WebServiceResponseDTO> editMerchant(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestBody MerchantDTO merchantDTO) throws Exception {
			   WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			   if(validateToken(token, deviceId)) {
				  if(null != merchantDTO.getmAddressList() && null != merchantDTO.getEmailId() && null != merchantDTO.getMobileNumber()) {
						  int isUpdate = merchantService.updateMerchant(merchantDTO);
						  String message = merchantMessage(isUpdate);
						  return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.MESSAGE, message), Constants.SUCCESS.toUpperCase()));
				  }
				  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, Constants.BLANK, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, Constants.FAILURE.toUpperCase()));
			   }
			   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, Constants.BLANK, RestWebServiceErrConstants.EDIT_MERCHANT_ERROR_MESSAGE, Constants.FAILURE.toUpperCase()));
		}
	    
	    @RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
	    public ResponseEntity<WebServiceResponseDTO> getMerchantProfile(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @PathVariable int id) throws Exception {
			   WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			   if(validateToken(token, deviceId)) {
				    MerchantDTO merchant = merchantProfileService.getMerchantDetails(id);
				    if(null != merchant) {
				    	merchant.setmAddressList(merchantProfileService.getAddressDetails(id));
				    }
				    return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.PROFILE, merchant), Constants.SUCCESS.toUpperCase()));
			   }
			   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
		}
	    
	    @RequestMapping(value = "/employee/{mId}", method = RequestMethod.GET)
		public ResponseEntity<WebServiceResponseDTO> getMerchnatEmployee(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token ,@PathVariable int mId) throws Exception {
			   WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			   if(validateToken(token, deviceId)) {
				  List<MerchantEmployeeDTO> merchEmpList = merchantEmployee.getMerchantEmployeeByMId(mId);
				  return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.MERCHANT_EMPLOYEE, merchEmpList), Constants.SUCCESS.toUpperCase()));
			   }
			   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	    }
	    
	    @RequestMapping(value = "/customer/{mId}", method = RequestMethod.GET)
		public ResponseEntity<WebServiceResponseDTO> getMerchantCustomer(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token ,@PathVariable int mId) throws Exception {
			   WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			   if(validateToken(token, deviceId)) {
					  List<MerchantMappingDTO> custList =  merchantMappingService.getCustomerByMerchant(mId);
					  return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.MERCHANT_CUSTOMER, custList), Constants.SUCCESS.toUpperCase()));
			   }
			   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	    }
	    
	    @RequestMapping(value = "/dashboard/{mId}", method = RequestMethod.GET)
	    public ResponseEntity<WebServiceResponseDTO> getMerchantDashboard(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token ,@PathVariable int mId, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) throws Exception {
			   WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			   if(validateToken(token, deviceId)) {
				   if(validateMerchantDetails(fromDate, toDate, 1)) {
					    return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.MERCHANT_DASHBOARD_DATA, merchantTransService.getMerchantDashboardData(mId, getFromDate(fromDate), getToDate(toDate))), Constants.SUCCESS.toUpperCase()));
				   }
				   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.MERCHANT_INVALID_PARAM, null, Constants.FAILURE.toUpperCase()));
			   }
			   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	    }
	    
	    @RequestMapping(value = "/transactionHistory/{mId}", method = RequestMethod.GET)
	    public ResponseEntity<WebServiceResponseDTO> getMerchantTransactionHistory(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token ,@PathVariable int mId, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate) throws Exception {
			   WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			   if(validateToken(token, deviceId)) {
				   if(validateMerchantDetails(fromDate, toDate, 1)) {
					    HashMap<String, Object> merchantTransList = (HashMap<String, Object>) getApiResponse(null, RestAPIConstants.MERCHANT_TRANSACTION_LIST, merchantTransService.getMerchantTransaction(mId, getFromDate(fromDate), getToDate(toDate)));
					    getApiResponse(merchantTransList, RestAPIConstants.CUSTOMER_ID_PREFIX, appPropertiesService.getSystemParamValueByName(Constants.CUSTOMER_ID_PREFIX));
					    return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(merchantTransList, RestAPIConstants.MERCHANT_ID_PREFIX, appPropertiesService.getSystemParamValueByName(Constants.MERCHANT_ID_PREFIX)), Constants.SUCCESS.toUpperCase()));
				   }
				   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.MERCHANT_INVALID_PARAM, null, Constants.FAILURE.toUpperCase()));
			   }
			   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	    }
	    
	    @RequestMapping(value = "/getMerchantAddress/{id}", method = RequestMethod.GET)
	    public ResponseEntity<WebServiceResponseDTO> getMerchantAddress(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @PathVariable("id") int id) throws Exception {
			   WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			   if(validateToken(token, deviceId)) {
				    return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.MERCHANT_ADDRESS_LIST, merchantService.getMerchantAddresss(id)), Constants.SUCCESS.toUpperCase()));
			   }
			   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
		}
	    
	    @RequestMapping(value = "/customer/add/{mId}", method = RequestMethod.POST)
	    public ResponseEntity<WebServiceResponseDTO> addMerchantCustomer(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @PathVariable("mId") int mId, @RequestBody CustomerDTO customer) throws Exception {
			   WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			   if(validateToken(token, deviceId)) {
				   customer = customerService.generateCustomerId(customer);
				   MerchantDTO merchant = merchantService.getMerchantById(mId);
				   if(validateCustomerInfo(customer, 1) && null != merchant) {
					   customer.setmId(mId);
				       int isInsert = customerService.saveCustomer(customer, null,"Merchant");
				       return redirectCustomerRegAPIResponse(isInsert, webServiceResponse, customer, merchant);
				   }
				   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.REG_CUSTOMER_ERR, null, Constants.FAILURE.toUpperCase()));
			   }
			   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	    }
	    
	    
		@RequestMapping(value = "/customer/assignLoyaltyCard", method = RequestMethod.POST)
		public ResponseEntity<WebServiceResponseDTO> assignLoyaltycard(@RequestHeader("deviceId") String deviceId,
				@RequestHeader("token") String token, @RequestParam("mId") int mId, @RequestParam("cId") int cId,
				@RequestParam("loyaltyCardNumber") String loyaltyCardNumber) throws Exception {
				WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
				if (validateToken(token, deviceId)) {
					int status = loyaltyCardService.validateLoyaltyNumber(mId, cId, loyaltyCardNumber, "CA", 1);
					if (status == 0) {
						loyaltyCardService.updateLoyaltyCardNumberByMid(mId, cId, loyaltyCardNumber, "CA", 1);
						return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse,
								RestWebServiceErrConstants.ASSIGNED_SUCCESS, null, Constants.SUCCESS.toUpperCase()));
					} else {
						return commonInvalidStatusResponse(status, webServiceResponse);
					}
	
				}
			return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse,
					RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
		}
		@RequestMapping(value = "/customer/verifyMobileNumberByCid", method = RequestMethod.POST)
		public ResponseEntity<WebServiceResponseDTO> verifyMobileNoByCid(@RequestHeader("deviceId") String deviceId,
				@RequestHeader("token") String token, @RequestParam("mobileNumber") String mobileNumber, @RequestParam("cId") int cId) throws Exception {
				WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
				if (validateToken(token, deviceId)) {
					String status = loyaltyCardService.verifyMobileNumberbyCid(mobileNumber,cId);
					if (!status.equalsIgnoreCase(Constants.INVALID_MOBILE_NUMBER) ) {
						
						return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.VERIFICATIONID, status), Constants.SUCCESS.toUpperCase()));
					} else{
						return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse,
								RestWebServiceErrConstants.INVALID_MOBILE_NUMBER, null, Constants.FAILURE.toUpperCase()));
					}
	
				}
			return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse,
					RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
		}
		@RequestMapping(value = "/customer/lostAndReplaceLoyaltyCard", method = RequestMethod.POST)
		public ResponseEntity<WebServiceResponseDTO> lostAndReplaceLoyaltycard(@RequestHeader("deviceId") String deviceId,
				@RequestHeader("token") String token, @RequestParam("mId") int mId, @RequestParam("cId") int cId,
				@RequestParam("loyaltyCardNumber") String loyaltyCardNumber) throws Exception {
				WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
				if (validateToken(token, deviceId)) {
					int status = loyaltyCardService.validateReasignedLoyaltyNumber(mId, cId, loyaltyCardNumber, "CA", Constants.ACTIVE);
					if (status == 0) {
						loyaltyCardService.blockLoyaltyCardNumber( mId,  cId, Constants.INACTIVE, Constants.BLOCK);
						loyaltyCardService.updateLoyaltyCardNumberByMid(mId, cId, loyaltyCardNumber, "CA", 1);
						return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse,
								RestWebServiceErrConstants.ASSIGNED_SUCCESS, null, Constants.SUCCESS.toUpperCase()));
					} else{
						return commonInvalidStatusResponse(status, webServiceResponse);
					}
	
				}
			return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse,
					RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
		}
	    
	    
	    @RequestMapping(value = "/transaction/{mId}", method = RequestMethod.GET)
	    public ResponseEntity<WebServiceResponseDTO> getMerchantTransByTransId(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @PathVariable("mId") int mId, @RequestParam("transactionId") String transactionId) throws Exception {
			   WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			   if(validateToken(token, deviceId)) {
				   if(StringUtils.isNotEmpty(transactionId)) {
					    HashMap<String, Object> transactionDetail = (HashMap<String, Object>) getApiResponse(null, RestAPIConstants.TRANSACTION_DETAIL, merchantTransService.getMerchantTransByMIDTransId(mId, transactionId));
					    getApiResponse(transactionDetail, RestAPIConstants.CUSTOMER_ID_PREFIX, appPropertiesService.getSystemParamValueByName(Constants.CUSTOMER_ID_PREFIX));
					    return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(transactionDetail, RestAPIConstants.MERCHANT_ID_PREFIX, appPropertiesService.getSystemParamValueByName(Constants.MERCHANT_ID_PREFIX)), Constants.SUCCESS.toUpperCase()));
				   }
				   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.MERCHANT_INVALID_PARAM, null, Constants.FAILURE.toUpperCase()));
			   }
			   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	    }
	    
	    @RequestMapping(value = "/wallet/topup/{mId}/{loginId}", method = RequestMethod.POST)
	    public ResponseEntity<WebServiceResponseDTO> merchantTopup(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @PathVariable("mId") int mId, @PathVariable("mId") String loginId, @RequestParam("amount") double amount) throws Exception {
			   WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			   if(validateToken(token, deviceId)) {
				   if(amount > 0 && mId > 0) {
					   int isInsert = merchantTransService.topUpMerchantWallet(amount, mId, loginId);
					   if(isInsert > 0) {
						   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.MERCHANT_WALLET_TOPUP, null, Constants.SUCCESS.toUpperCase()));
					   }
				   }
				   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.MERCHANT_INVALID_PARAM, null, Constants.FAILURE.toUpperCase()));
			   }
			   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	    }
	    
	    @RequestMapping(value = "/saveMerchantDetail", method = RequestMethod.POST)
	    public ResponseEntity<WebServiceResponseDTO> saveMerchantDetail(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestBody MerchantDTO merchant) throws Exception {
			   WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			   if(validateToken(token, deviceId)) {
				     int isInsert = merchantService.saveMerchantDetails(merchant);
				     if(isInsert == 1) {
				    	 merchantService.updateMerchantFlag(merchant);
				    	 return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.MESSAGE, RestWebServiceErrConstants.MERCHANT_DETAIL_SUCCESS), Constants.SUCCESS.toUpperCase()));
				     }else {
				    	 return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.MERCHANT_DETAIL_FAILURE, null, Constants.FAILURE.toUpperCase()));
				     }
			   }
			   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	    }
	    
	    private MerchantDTO validateAndSetReqDetail(MerchantDTO merchant) {
				if(null != merchant.getmAddressList() && null != merchant.getEmailId() && null != merchant.getMobileNumber() && null != merchant.getMerchantName()) {
					  merchant.setLoginId(utilityService.generateNumber(10, true, true));
					  merchant.setMerchantId(utilityService.generateCustomerId(false, true));
					  merchant.setMerchantNumber(utilityService.generateNumber(15, false, true));
					  merchant.setMerchantPlanId(merchantService.getBasicMerchantPlanId());
					  return merchant;
				}
				return null;
	    }
	
		private ResponseEntity<WebServiceResponseDTO> redirectMerchantAPIResponse(int isInsert,  WebServiceResponseDTO webServiceResponse, MerchantDTO merchantDTO){
			    if(isInsert == 1){
			    	UserDTO userDetails = userService.getUserByLoginId(merchantDTO.getLoginId());
			    	MerchantDTO merchant = merchantService.getMerchantByMId(merchantDTO.getMerchantId());
			    	if(null != merchant) {
			             return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.USER, setUserInfo(merchant, userDetails)), Constants.SUCCESS.toUpperCase()));
			    	}
			    }else if(isInsert == 2){
				    return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.MERCHANT_ALREADY_EXISTS, Constants.BLANK, Constants.FAILURE.toUpperCase()));
			    }else if(isInsert == 3){
				    return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.MERCHANT_USER_ERR_MSG, Constants.BLANK, Constants.FAILURE.toUpperCase()));
			    }
			    return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.ADD_MERCHANT_ERROR_MESSAGE, Constants.BLANK, Constants.FAILURE.toUpperCase()));
		}
	
	    private ApiUserDTO setUserInfo(MerchantDTO merchant, UserDTO userDetails) {
			    ApiUserDTO userDTO = new ApiUserDTO();
			    userDTO.setId(merchant.getId());
			    userDTO.setmId(merchant.getMerchantId());
		    	userDTO.setLoginId(userDetails.getLoginId());
		    	userDetails.setIsUpdateDetail(userDetails.getIsUpdateDetail());
		    	userDTO.setMobileNumber(userDetails.getMobileNumber());
		    	userDTO.setUserId(String.valueOf(userDetails.getId()));
			    return userDTO;
	    }
	    
	    private boolean isVerifiedEmail(MerchantDTO merchant){
			    String verifiedId = merchant.getVerifiedId();
			    VerificationLogDTO verification = verificationService.getVerificationLog(utilityService.convertStringToInt(verifiedId));
			    if(null != verification && verification.getIsVerified() == 1){
				     return true;
			    }
			    return false;
	    }
	    
	    private boolean validateMerchantDetails(String fromDate, String toDate, int type) {
			    switch (type) {
					 case 1:
						  return (StringUtils.isNotEmpty(fromDate) && StringUtils.isNotEmpty(toDate));
					 default:
						  return false;
			    }
	    }
	    
	    public String merchantMessage(int isUpdate){
	    	    if(isUpdate == 1){
	    		   String message= "Merchant Updated Successfully.";
				   return message;
			    }else if(isUpdate == 2){
				   String message = "Merchant Details Already Exists(Merchant Name, PanNumber, GSTIN, EmailId & Mobile Number must be unique.";
				   return message;
			    }else if(isUpdate == 3){
				   String message = "Merchant Mobile Number already Exists.";
				   return message;
			    }
	    	    return null;
	    }
	    
	    private ResponseEntity<WebServiceResponseDTO> redirectCustomerRegAPIResponse(int isInsert, WebServiceResponseDTO webServiceResponse, CustomerDTO customer, MerchantDTO merchant) {
			    switch(isInsert){
					 case 1:
						  CustomerDTO customerDTO = customerService.getCustomerByCustomerId(customer.getCustomerId());
						  int isAddMerchant = saveMerchantMapping(merchant, customerDTO, customer.getCreatedBy());
				    	  if(customerDTO != null && isAddMerchant == 1) {
				    		  return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.USER, setUserInfo(customerDTO)), Constants.SUCCESS.toUpperCase()));
				    	  }else if(customerDTO != null && isAddMerchant == 2) {
				    		  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.ALREADY_CUSTOMER_MAPPED, null, Constants.FAILURE.toUpperCase()));
				    	  }else if(customerDTO != null && isAddMerchant == 3) {
				    		  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.CUSTOMER_ALREADY_MAPPED, null, Constants.FAILURE.toUpperCase()));
				    	  }else if(customerDTO != null && isAddMerchant == 0) {
				    		  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.NOT_MAPPED_CUSTOMER, null, Constants.FAILURE.toUpperCase()));
				    	  }
					 case 2:
						 CustomerDTO custObj = customerService.getCustomerByMN(customer.getMobileNumber());
						 int isRequsetSend = saveMerchantMapping(merchant, custObj, customer.getCreatedBy());
						 if(isRequsetSend == 1) {
							 return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.CUSTOMER_EXISTS, null, Constants.SUCCESS.toUpperCase()));
						 }else if(isRequsetSend == 2) {
							 return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.ALREADY_CUSTOMER_MAPPED, null, Constants.FAILURE.toUpperCase()));
						 }else if(isRequsetSend == 3) {
							 return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.CUSTOMER_ALREADY_MAPPED, null, Constants.FAILURE.toUpperCase()));
						 }
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
	    
	    private ApiUserDTO setUserInfo(CustomerDTO customerDTO) {
		    ApiUserDTO userDTO = new ApiUserDTO();
		    userDTO.setcId(customerDTO.getCustomerId());
		    userDTO.setLoginId(customerDTO.getLoginId());
		    userDTO.setUserId(String.valueOf(customerDTO.getUserId()));
		    return userDTO;
	    }
	    
	    private boolean validateCustomerInfo(CustomerDTO customer, int type) {
			    switch (type) {
					 case 1:
						  return (StringUtils.isNotEmpty(customer.getLoginId()) && StringUtils.isNotEmpty(customer.getFirstName()) && StringUtils.isNotEmpty(customer.getLastName()) && StringUtils.isNotEmpty(customer.getEmailId()) && StringUtils.isNotEmpty(customer.getMobileNumber()));
					 default:
						  return false;
			    }
	    }
	    
	    private int saveMerchantMapping(MerchantDTO merchant, CustomerDTO customer, String createdBy){
			    if(null != merchant && null != customer){
			           MerchantMappingDTO merchantMapping = merchantMappingService.getMerchantMappingInstance(customer, merchant, createdBy);
			    	   return merchantMappingService.saveMerchantMapping(merchantMapping, Constants.CUSTOMER);
			    }
			    return 0;
        }

		@RequestMapping(value = "/panNumber/{panNumber}", method = RequestMethod.GET)
		public ResponseEntity<WebServiceResponseDTO> getMerchantByPanNumber(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @PathVariable("panNumber") String panNumber) throws Exception {
			   WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			   if(validateToken(token, deviceId)) {
				   List<MerchantDTO> merchant = merchantService.getMerchantByPanNumber(panNumber);
				   if(merchant.size() > 0) {
		   			    return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.PAN_NUMBER_ALREDY_EXIST, null, Constants.FAILURE.toUpperCase()));
				   }else {
				        return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, Constants.BLANK, null), Constants.SUCCESS.toUpperCase()));
			       }
			   }
			   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
		}
			
		@RequestMapping(value = "/gstIn/{gstIn}", method = RequestMethod.GET)
		public ResponseEntity<WebServiceResponseDTO> getMerchantByGSTIN(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @PathVariable("gstIn") String gstIn) throws Exception {
			   WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			   if(validateToken(token, deviceId)) {
					   List<MerchantDTO> merchant = merchantService.getMerchantByGSTIN(gstIn);
					   if(merchant.size() > 0) {
						    return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.GSTIN_ALREADY_EXIST, null, Constants.FAILURE.toUpperCase()));
					   }else {
					        return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, Constants.BLANK, null), Constants.SUCCESS.toUpperCase()));
				       }
			   }
			   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
		}
		
		@RequestMapping(value = "/updateMerchantFlag/{loginId}", method = RequestMethod.GET)
		    public ResponseEntity<WebServiceResponseDTO> getMerchantDashboard(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token , @PathVariable("loginId") String loginId) throws Exception {
				   WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
				   if(validateToken(token, deviceId)) {
					   MerchantDTO merchant = new MerchantDTO();
			    	   merchant.setLoginId(loginId);
			    	   int isUpdated = merchantService.updateMerchantFlag(merchant);
			    	   if(isUpdated == 1) {
						    return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.MERCHANT_UPDATED_FLAG, isUpdated), Constants.SUCCESS.toUpperCase()));
					   }
					   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.MERCHANT_UPDATE_FLAG, null, Constants.FAILURE.toUpperCase()));
				   }
				   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
		} 
		
		private ResponseEntity<WebServiceResponseDTO> commonInvalidStatusResponse(final int status, WebServiceResponseDTO webServiceResponse){
			if (status == 1) {
				return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse,
						RestWebServiceErrConstants.NUMBER_ALREADY_ASSIGNED, null, Constants.FAILURE.toUpperCase()));
			} else {
				return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse,
						RestWebServiceErrConstants.INVALID_LOYALTY_NUMBER, null, Constants.FAILURE.toUpperCase()));
			}
		}

}
	

