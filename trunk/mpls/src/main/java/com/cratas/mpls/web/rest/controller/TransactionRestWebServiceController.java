package com.cratas.mpls.web.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.constant.RestAPIConstants;
import com.cratas.mpls.common.utility.PayType;
import com.cratas.mpls.common.utility.TransactionType;
import com.cratas.mpls.common.utility.constant.RestWebServiceErrConstants;
import com.cratas.mpls.service.ICustomerTransactionService;
import com.cratas.mpls.service.ILoyaltyCashbackService;
import com.cratas.mpls.service.IMerchantService;
import com.cratas.mpls.service.IPushNotificationLogService;
import com.cratas.mpls.service.IQRCodeService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.CustomerNotificationDTO;
import com.cratas.mpls.web.dto.CustomerTransactionDTO;
import com.cratas.mpls.web.dto.LoyaltyCashbackDTO;
import com.cratas.mpls.web.dto.MerchantDTO;
import com.cratas.mpls.web.dto.MerchantMappingDTO;
import com.cratas.mpls.web.dto.MerchantTransactionDTO;
import com.cratas.mpls.web.dto.WebServiceResponseDTO;

/**
 * 
 * @author mukesh
 *
 */
@RestController
@RequestMapping("/rest/webservices/transaction")
public class TransactionRestWebServiceController extends RestWebServiceController{
	
	   private final static Logger LOGGER = LoggerFactory.getLogger(TransactionRestWebServiceController.class);
	   
	   @Autowired
	   private ICustomerTransactionService customerTransactionService;
	   
	   @Autowired
	   private IQRCodeService qrCodeService;
	   
	   @Autowired
	   private ILoyaltyCashbackService loyaltyCashbackService;
	   
	   @Autowired
	   private IUtilityService utilityService;
	   
	   @Autowired
	   private IPushNotificationLogService PushNotificationLogService;
	   
	   @Autowired
	   private IMerchantService merchantService;
	
	   @RequestMapping(value = "/scanQRCode", method = RequestMethod.POST)
	   public ResponseEntity<WebServiceResponseDTO> scanQRCode(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestBody CustomerTransactionDTO customerTransaction){
			  WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			  if(validateToken(token, deviceId)) {
				  LOGGER.info("Customer Transaction -- Scan QR Code -- "+customerTransaction.toString());
				  if(validateTransaction(customerTransaction, 1)){
					   customerTransaction = getCustomerTransactionObj(customerTransaction);
					   MerchantMappingDTO merchantMapping = customerTransactionService.getCustomerWalletByMerchant(customerTransaction.getcId(), customerTransaction.getmId());
					   if(merchantMapping.getWalletBal() >=  customerTransaction.getWalletBal()){
						   int isPay = customerTransactionService.payBillsByQRCode(getMerchantTransactionObj(customerTransaction), customerTransaction);
						   if(isPay == 1) {
							   MerchantMappingDTO mapping = customerTransactionService.getCustomerWalletByMerchant(customerTransaction.getcId(), customerTransaction.getmId());
							   return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.MERCHANT_MAPPING, mapping), Constants.SUCCESS.toUpperCase()));
						   }
						   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.UNABLE_TO_PAY, null, Constants.FAILURE.toUpperCase()));
					   }
					   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INSUFFICIENT_FUNDS, null, Constants.FAILURE.toUpperCase()));
			     }
				 return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TRANSACTION_DETAIL, null, Constants.FAILURE.toUpperCase()));
			  }
			  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
		}
	   
	    @RequestMapping(value = "customer/addMoney", method = RequestMethod.POST)
	    public ResponseEntity<WebServiceResponseDTO> addMoney(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestBody CustomerTransactionDTO customerTransaction) throws Exception {
			   WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			   if(validateToken(token, deviceId)) {
				   if(validateTransaction(customerTransaction, 2)){
					   customerTransaction = getOfferCodeByMId(customerTransaction);
					   customerTransaction = customerTransactionService.getCustomerTransactionObj(customerTransaction);
					   MerchantDTO merchant = merchantService.getMerchantById(customerTransaction.getmId());
					   if(merchant.getWalletBal()==0) {
						   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.SET_MERCHANT_LIMIT, null, Constants.FAILURE.toUpperCase()));
				       }else {
						   int isTopup = customerTransactionService.topUpTransaction(customerTransactionService.getMerchantTransactionObj(customerTransaction), customerTransaction);
						   if(isTopup == 1){
					    	   MerchantMappingDTO mapping = customerTransactionService.getCustomerWalletByMerchant(customerTransaction.getcId(), customerTransaction.getmId());
							   return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.MERCHANT_MAPPING, mapping), Constants.SUCCESS.toUpperCase()));
					       }else  if(isTopup == 3){
					    	   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.TRANSACTION_LIMIT_EXCEEDED_ERROR_MSG, null, Constants.FAILURE.toUpperCase()));
					       }
				       }
					   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.UNABLE_TO_ADD_MONEY, null, Constants.FAILURE.toUpperCase()));
				   }
				   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TRANSACTION_DETAIL, null, Constants.FAILURE.toUpperCase()));
			   }
			   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	    }
	    
	    private CustomerTransactionDTO getOfferCodeByMId(CustomerTransactionDTO customerTransaction) {
			List<LoyaltyCashbackDTO> loyaltyCashback = loyaltyCashbackService.getLoyaltyCashbackByMId(customerTransaction.getmId());
			if(!loyaltyCashback.isEmpty()) {
				customerTransaction.setOfferCode(loyaltyCashback.get(0).getOfferCode());
			}
			return customerTransaction;
		}
	    
	    @RequestMapping(value = "customer/payBills", method = RequestMethod.POST)
	    public ResponseEntity<WebServiceResponseDTO> payMoney(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestBody CustomerTransactionDTO customerTransaction) throws Exception {
			   WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			   if(validateToken(token, deviceId)) {
				   if(validateTransaction(customerTransaction, 2)){
					   customerTransaction = customerTransactionService.getCustomerTransactionObj(customerTransaction);
					   MerchantMappingDTO merchantMapping = customerTransactionService.getCustomerWalletByMerchant(customerTransaction.getcId(), customerTransaction.getmId());
					   if(merchantMapping.getWalletBal() >=  customerTransaction.getWalletBal()){
						     int isPay = customerTransactionService.payBills(getMerchantTransactionObj(customerTransaction), customerTransaction);
						     if(isPay == 1){
						    	 MerchantMappingDTO mapping = customerTransactionService.getCustomerWalletByMerchant(customerTransaction.getcId(), customerTransaction.getmId());
								 return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.MERCHANT_MAPPING, mapping), Constants.SUCCESS.toUpperCase()));
						     }
						     return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.UNABLE_TO_PAY, null, Constants.FAILURE.toUpperCase()));
					   }
					   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INSUFFICIENT_FUNDS, null, Constants.FAILURE.toUpperCase()));
				   }
				   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TRANSACTION_DETAIL, null, Constants.FAILURE.toUpperCase()));
			   }
			   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	    }
	    
	    @RequestMapping(value = "customer/generateQRCode", method = RequestMethod.POST)
	    public ResponseEntity<WebServiceResponseDTO> generateQRCode(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestBody CustomerTransactionDTO customerTransaction) throws Exception {
			   WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			   if(validateToken(token, deviceId)) {
				   if(validateTransaction(customerTransaction, 2)){
					   customerTransaction = customerTransactionService.getCustomerTransactionObj(customerTransaction);
					   String image = qrCodeService.createQRCode(getMerchantTransactionObj(customerTransaction), customerTransaction);
					   return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.BASE64_IMAGE, StringUtils.isNotEmpty(image)?(Constants.BASE64_IMAGE + image):null), Constants.SUCCESS.toUpperCase()));
				   }
				   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TRANSACTION_DETAIL, null, Constants.FAILURE.toUpperCase()));
			   }
			   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	    }
	    
	    @RequestMapping(value = "/applyCoupon", method = RequestMethod.POST)
		public @ResponseBody ResponseEntity<WebServiceResponseDTO> applyCoupon(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestBody CustomerTransactionDTO customerTransaction, HttpServletRequest request, HttpServletResponse response) {
	    	   WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			   if(validateToken(token, deviceId)) {
		    	    if(StringUtils.isNotEmpty(customerTransaction.getOfferCode()) && customerTransaction.getTransactionValue() > 0){
					   LoyaltyCashbackDTO loyaltyCashback = loyaltyCashbackService.getLoyaltyCashbackByCode(customerTransaction.getOfferCode());
					   if(null != loyaltyCashback){
						     String isValidOffer = loyaltyCashbackService.validateMerchantOffer(customerTransaction.getmId(), customerTransaction.getcId(), customerTransaction.getTransactionValue(), loyaltyCashback);
						     if(isValidOffer.equals(Constants.SUCCESS)){
						    	  double cashbackAmt = loyaltyCashbackService.getOfferCashbackAmt(customerTransaction.getTransactionValue(), loyaltyCashback);
						    	  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, utilityService.decimalFormat(cashbackAmt), null, Constants.SUCCESS.toUpperCase()));
						     }else if(isValidOffer.equals(Constants.FAILURE)){
						    	  return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.COUPON_NOT_VALID_ERR_MSG, null, Constants.FAILURE.toUpperCase())); 
						     }
						     return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, isValidOffer, null, Constants.FAILURE.toUpperCase()));
					   }
				   }
			   }
			   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
		}
	    
	    @RequestMapping(value = "/refund/{transactionId}", method = RequestMethod.POST)
	    public ResponseEntity<WebServiceResponseDTO> refundTransaction(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @PathVariable("transactionId") String transactionId, @RequestParam("transactionType") String transactionType) throws Exception {
			   WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			   if(validateToken(token, deviceId)) {
				   if(StringUtils.isNotEmpty(transactionId) && StringUtils.isNotEmpty(transactionType)){
					   int isRefund = customerTransactionService.refundTransaction(transactionId, transactionType);
				       if(isRefund == 1){
				    	   return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, RestWebServiceErrConstants.REFUND_SUCCESSFULL, Constants.SUCCESS.toUpperCase()));
				       }else if(isRefund == 3){
				    	   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.TRANSACTION_LIMIT_EXCEEDED_ERROR_MSG, null, Constants.FAILURE.toUpperCase()));
				       }
					   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.UNABLE_TO_REFUND_TRANSACTION, null, Constants.FAILURE.toUpperCase()));
				   }
				   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TRANSACTION_DETAIL, null, Constants.FAILURE.toUpperCase()));
			   }
			   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	    }
		
		private CustomerTransactionDTO getCustomerTransactionObj(CustomerTransactionDTO customerTransaction){
			    if(customerTransaction.getTransactionType().equalsIgnoreCase(TransactionType.PAY.name())) {
					  customerTransaction.setTransactionType(TransactionType.PAY.name());
					  customerTransaction.setIndicator(Constants.INDICATOR_DEBIT);
			    }else {
				      customerTransaction.setTransactionType(TransactionType.TOPUP.name());
				      customerTransaction.setIndicator(Constants.INDICATOR_CREDIT);
			    }
			    customerTransaction.setStatus(Constants.SUCCESS_FLAG);
			    customerTransaction.setPayType(PayType.QRCODE.getPayType());
			    return customerTransaction;
	   }
	
	   private MerchantTransactionDTO getMerchantTransactionObj(CustomerTransactionDTO customerTransaction){
			   MerchantTransactionDTO merchantTransaction = new MerchantTransactionDTO();
		       BeanUtils.copyProperties(customerTransaction, merchantTransaction);
		       merchantTransaction.setIndicator(Constants.INDICATOR_CREDIT);
		       return merchantTransaction;
	   }
	   
	   private boolean validateTransaction(CustomerTransactionDTO customerTransaction, int type){
		       switch (type) {
					case 1:
						return (null != customerTransaction && customerTransaction.getTransactionValue() > 0 && customerTransaction.getmId() > 0 && customerTransaction.getcId() > 0 && 
			    		   StringUtils.isNotEmpty(customerTransaction.getTransactionId()) && StringUtils.isNotEmpty(customerTransaction.getWalletTransactionId()));
					case 2:
						return (null != customerTransaction && customerTransaction.getWalletBal() > 0 && customerTransaction.getmId() > 0);
					default:
						return false;
		       }
	   }

	   @RequestMapping(value = "/collectMoney", method = RequestMethod.POST)
	   public ResponseEntity<WebServiceResponseDTO> collectMoney(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestBody CustomerNotificationDTO customerNotification) throws Exception {
			   WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			   if(validateToken(token, deviceId)) {
				   MerchantMappingDTO merchantMapping = customerTransactionService.getCustomerWalletByMerchant(customerNotification.getcId(), customerNotification.getmId());
				   if(merchantMapping.getWalletBal() >=  customerNotification.getAmount()){
					     int isInserted = customerTransactionService.collectMoney(customerNotification);
						     if(isInserted > 0) {
						    	 return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.MESSAGE, RestWebServiceErrConstants.COLLECT_MONEY_SUCCESS), Constants.SUCCESS.toUpperCase()));
						     }else {
						    	 return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.COLLET_MONEY_ERROR_MSG, null, Constants.FAILURE.toUpperCase()));
						     }
				   }
				   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INSUFFICIENT_FUNDS, null, Constants.FAILURE.toUpperCase()));
		   }
			   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	   }
	   
	   @RequestMapping(value = "customer/refundMoney", method = RequestMethod.POST)
	    public ResponseEntity<WebServiceResponseDTO> refundMoney(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestParam("notificationId") int notificationId, @RequestBody CustomerTransactionDTO customerTransaction) throws Exception {
			   WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			   if(validateToken(token, deviceId)) {
				   if(validateTransaction(customerTransaction, 2)){
					   if(customerTransaction.getTransactionType().equals(Constants.REFUND)){
						   customerTransaction = customerTransactionService.getCustomerTransactionObj(customerTransaction);
						   MerchantMappingDTO merchantMapping = customerTransactionService.getCustomerWalletByMerchant(customerTransaction.getcId(), customerTransaction.getmId());
						       if(merchantMapping.getWalletBal() >=  customerTransaction.getWalletBal()){
								     int isPay = customerTransactionService.payBills(getMerchantTransactionObj(customerTransaction), customerTransaction);
								     if(isPay == 1){
								    	 MerchantMappingDTO mapping = customerTransactionService.getCustomerWalletByMerchant(customerTransaction.getcId(), customerTransaction.getmId());
								    	 customerTransactionService.updateCollectMoneyStatus(customerTransaction.getId(),customerTransaction.getTransactionType(), customerTransaction.getCreatedBy());
								    	 PushNotificationLogService.updatePushNotificationLog(1,1, notificationId);
								    	 return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.MERCHANT_MAPPING, mapping), Constants.SUCCESS.toUpperCase()));
								     }
								     return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.UNABLE_TO_PAY, null, Constants.FAILURE.toUpperCase()));
							   }
						       return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INSUFFICIENT_FUNDS, null, Constants.FAILURE.toUpperCase()));
					   }else {
						   customerTransactionService.updateCollectMoneyStatus(customerTransaction.getId(),customerTransaction.getTransactionType(),customerTransaction.getCreatedBy());
						   PushNotificationLogService.updatePushNotificationLog(2,1, notificationId);
						   return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.REJECT_REQUEST, null), Constants.SUCCESS.toUpperCase()));
					   }
				   }
				   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TRANSACTION_DETAIL, null, Constants.FAILURE.toUpperCase()));
			   }
			   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	    }
	   
	   @RequestMapping(value = "customer/collectMoney", method = RequestMethod.POST)
	    public ResponseEntity<WebServiceResponseDTO> collectMoney(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestParam("notificationId") int notificationId, @RequestBody CustomerTransactionDTO customerTransaction) throws Exception {
			   WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			   if(validateToken(token, deviceId)) {
				   if(validateTransaction(customerTransaction, 2)){
					   if(customerTransaction.getTransactionType().equals(Constants.PAY)){
						   customerTransaction.setTransactionType(Constants.COLLECT_MONEY);
						   customerTransaction = customerTransactionService.getCustomerTransactionObj(customerTransaction);
						   MerchantMappingDTO merchantMapping = customerTransactionService.getCustomerWalletByMerchant(customerTransaction.getcId(), customerTransaction.getmId());
						       if(merchantMapping.getWalletBal() >=  customerTransaction.getWalletBal()){
								     int isPay = customerTransactionService.payBills(getMerchantTransactionObj(customerTransaction), customerTransaction);
								     if(isPay == 1){
								    	 MerchantMappingDTO mapping = customerTransactionService.getCustomerWalletByMerchant(customerTransaction.getcId(), customerTransaction.getmId());
								    	 customerTransactionService.updateCollectMoneyStatus(customerTransaction.getId(),customerTransaction.getTransactionType(), customerTransaction.getCreatedBy());
								    	 PushNotificationLogService.updatePushNotificationLog(1,1, notificationId);
								    	 return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.MERCHANT_MAPPING, mapping), Constants.SUCCESS.toUpperCase()));
								     }
								     return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.UNABLE_TO_PAY, null, Constants.FAILURE.toUpperCase()));
							   }
						       return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INSUFFICIENT_FUNDS, null, Constants.FAILURE.toUpperCase()));
					   }else {
						   customerTransactionService.updateCollectMoneyStatus(customerTransaction.getId(),customerTransaction.getTransactionType(),customerTransaction.getCreatedBy());
						   PushNotificationLogService.updatePushNotificationLog(2,1, notificationId);
						   return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.REJECT_REQUEST, null), Constants.SUCCESS.toUpperCase()));
					   }
				   }
				   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TRANSACTION_DETAIL, null, Constants.FAILURE.toUpperCase()));
			   }
			   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	    }
	   
	   @RequestMapping(value = "/refundMoney", method = RequestMethod.POST)
	   public ResponseEntity<WebServiceResponseDTO> refundMoney(@RequestHeader("deviceId") String deviceId, @RequestHeader("token") String token, @RequestBody CustomerNotificationDTO customerNotification) throws Exception {
			   WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			   if(validateToken(token, deviceId)) {
				   MerchantMappingDTO merchantMapping = customerTransactionService.getCustomerWalletByMerchant(customerNotification.getcId(), customerNotification.getmId());
				   if(merchantMapping.getWalletBal() >=  customerNotification.getAmount()){
					   int isInserted = customerTransactionService.refundMoney(customerNotification);
						     if(isInserted > 0) {
						    	 return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, getApiResponse(null, RestAPIConstants.MESSAGE, RestWebServiceErrConstants.REFUND_REQUEST_SUCCESS), Constants.SUCCESS.toUpperCase()));
						     }else {
						    	 return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.REFUND_MONEY_ERROR_MSG, null, Constants.FAILURE.toUpperCase()));
						     }
				   }
				   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INSUFFICIENT_FUNDS, null, Constants.FAILURE.toUpperCase()));
		   }
			   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, RestWebServiceErrConstants.INVALID_TOKEN_ERR_MSG, null, Constants.FAILURE.toUpperCase()));
	   }
	   
}
