package com.cratas.mpls.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.constant.EmailConstants;
import com.cratas.mpls.common.constant.PropertyKeyConstants;
import com.cratas.mpls.common.utility.OfferCashbackThread;
import com.cratas.mpls.common.utility.PayType;
import com.cratas.mpls.common.utility.TransactionType;
import com.cratas.mpls.dao.ICustomerTransactionDao;
import com.cratas.mpls.service.ICustomerService;
import com.cratas.mpls.service.ICustomerTransactionService;
import com.cratas.mpls.service.IEmailSenderService;
import com.cratas.mpls.service.ILoyaltyCashbackService;
import com.cratas.mpls.service.IMerchantService;
import com.cratas.mpls.service.IPushNotificationService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.CustomerDTO;
import com.cratas.mpls.web.dto.CustomerNotificationDTO;
import com.cratas.mpls.web.dto.CustomerTransactionDTO;
import com.cratas.mpls.web.dto.LoyaltyCashbackDTO;
import com.cratas.mpls.web.dto.MerchantDTO;
import com.cratas.mpls.web.dto.MerchantMappingDTO;
import com.cratas.mpls.web.dto.MerchantTransactionDTO;

/**
 * 
 * @author mukesh
 *
 */
@Service
public class CustomerTransactionServiceImpl implements ICustomerTransactionService{
	
	    private final static Logger LOGGER = LoggerFactory.getLogger(CustomerTransactionServiceImpl.class);
	
		@Autowired
		private ICustomerTransactionDao ctDao;
		
		@Autowired
		private ThreadPoolTaskExecutor taskExecutor;
		
		@Autowired
		private OfferCashbackThread offerCashbackThread;
		
		@Autowired
		private ILoyaltyCashbackService loyaltyCashbackService;
		
		@Autowired
	    private IUtilityService utilityService;
		
		@Autowired
		private IMerchantService merchantService;
		
		@Autowired
		private IPushNotificationService pushNotificationService;
		
		@Autowired
	    private IEmailSenderService emailSenderService;
		
		@Autowired
	    private Environment env;
		
		@Autowired
		private ICustomerService customerService;
		
		public int topUpTransaction(MerchantTransactionDTO merchantTransaction, CustomerTransactionDTO customerTransaction) {
			   if(!isMerchantLimitExceeds(customerTransaction.getmId(), customerTransaction.getTransactionValue())) {
				   String isValidOffer = isValidMerchantOffer(customerTransaction.getmId(), customerTransaction.getcId(), customerTransaction.getTransactionValue(), customerTransaction.getOfferCode());
				   customerTransaction = setOfferCode(isValidOffer, customerTransaction);
				   int isInserted = ctDao.insertCustomerTransaction(customerTransaction);
				   if(isInserted > 0){
					   merchantTransaction = setOfferCode(isValidOffer, merchantTransaction);
					   ctDao.insertMerchantTransaction(merchantTransaction);
					   int isUpdated = updateWalletBal(customerTransaction, merchantTransaction);
					   applyMerchantOffer(merchantTransaction, customerTransaction, isUpdated, isValidOffer);
					   sendTopupEmail(merchantTransaction, customerTransaction, isUpdated, isValidOffer);
					   return isUpdated;
				   }
				   return 0;
			   }
			   return 3;
		}
		
		private MerchantTransactionDTO setOfferCode(String isValidOffer, MerchantTransactionDTO merchantTransaction) {
				if(!isValidOffer.equals(Constants.SUCCESS)) {
					merchantTransaction.setOfferCode("");
					return merchantTransaction;
				}	
				return merchantTransaction;
		}

		private CustomerTransactionDTO setOfferCode(String isValidOffer, CustomerTransactionDTO customerTransaction) {
				if(!isValidOffer.equals(Constants.SUCCESS)) {
					customerTransaction.setOfferCode("");
					return customerTransaction;
				}	
				return customerTransaction;
		}
		
		private void sendTopupEmail(MerchantTransactionDTO merchantTransaction, CustomerTransactionDTO customerTransaction, int isPay, String isValidOffer) {
			if(StringUtils.isNotEmpty(customerTransaction.getOfferCode()) && isPay == 1 && isValidOffer.equals(Constants.SUCCESS)){
		    	 List<LoyaltyCashbackDTO> loyaltyCashback = loyaltyCashbackService.getLoyaltyCashbackByMId(merchantTransaction.getmId());
		    	 if(!loyaltyCashback.isEmpty()){
		    		 double cashBackAmt = loyaltyCashbackService.getOfferCashbackAmt(customerTransaction.getWalletBal(), loyaltyCashback.get(0));
		    		 customerTransaction.setCashbackAmt(cashBackAmt);
		    		 sendTopupSuccessfulMailWithCashBack(customerTransaction);
		    	 }
		    }else {
	    		 sendTopupSuccessfulMail(customerTransaction);
	    	 }
			
		}

		private void sendTopupSuccessfulMail(CustomerTransactionDTO customerTransaction) {
		    MerchantMappingDTO mapping = ctDao.getCustomerWalletByMerchant(customerTransaction.getcId(), customerTransaction.getmId());
		    CustomerDTO customer = customerService.getCustomerByCId(customerTransaction.getcId());
		    MerchantDTO merchant = merchantService.getMerchantById(customerTransaction.getmId());
		    if(mapping != null && customer != null && merchant != null) {
	            final String message = utilityService.classpathResourceLoader(EmailConstants.SUCCESSFUL_TOPUP_TEMPLATE)
	            		.replace(EmailConstants.NAME, customer.getFirstName() + customer.getLastName())
	                    .replace(EmailConstants.MERCHANT, merchant.getMerchantName())
	                    .replace(EmailConstants.WALLETBALANCE, utilityService.decimalFormat(mapping.getWalletBal()))
	                    .replace(EmailConstants.AMOUNT, utilityService.decimalFormat(customerTransaction.getTransactionValue()))
	                    .replace(EmailConstants.EMAIL, env.getProperty(PropertyKeyConstants.ServerProperties.SUPPORT_EMAIL))
	                    .replace(EmailConstants.BASE_URL, utilityService.getBaseUrl())
	                    .replace(EmailConstants.CASHBACKAMOUNT, "")
	                    .replace(EmailConstants.APP_NAME, EmailConstants.MPLS_APP_NAME).replace(EmailConstants.APP_ACCOUNT_NAME, EmailConstants.MPLS);
	            new Thread(new Runnable() {
	                @Override
	                public void run() {
	                    emailSenderService.sendEmail(utilityService.getSenderMail(), customer.getEmailId().split(Constants.COMMA), null,
	                            EmailConstants.SUCCSSFUL_TOPUP_SUBJECT, message, null, null, true);
	                }
	            }).start();
		    }
	    }
		
		private void sendTopupSuccessfulMailWithCashBack(CustomerTransactionDTO customerTransaction) {
		    MerchantMappingDTO mapping = ctDao.getCustomerWalletByMerchant(customerTransaction.getcId(), customerTransaction.getmId());
		    CustomerDTO customer = customerService.getCustomerByCId(customerTransaction.getcId());
		    MerchantDTO merchant = merchantService.getMerchantById(customerTransaction.getmId());
		    if(mapping != null && customer != null && merchant != null) {
		    	String email = "";
		    	if(customerTransaction.getCashbackAmt()>0) {
		    		email = utilityService.classpathResourceLoader(EmailConstants.SUCCESSFUL_TOPUP_TEMPLATE).
	            	replace(EmailConstants.CASHBACKAMOUNT, "along with Cashback of Rs "+customerTransaction.getCashbackAmt());
                }else {
                	email = utilityService.classpathResourceLoader(EmailConstants.SUCCESSFUL_TOPUP_TEMPLATE).
	            	replace(EmailConstants.CASHBACKAMOUNT, "");
                }
	            final String message = email
	            		.replace(EmailConstants.NAME, customer.getFirstName() + customer.getLastName())
	                    .replace(EmailConstants.MERCHANT, merchant.getMerchantName())
	                    .replace(EmailConstants.WALLETBALANCE, utilityService.decimalFormat(mapping.getWalletBal()))
	                    .replace(EmailConstants.AMOUNT, utilityService.decimalFormat(customerTransaction.getWalletBal()))
	                    .replace(EmailConstants.EMAIL, env.getProperty(PropertyKeyConstants.ServerProperties.SUPPORT_EMAIL))
	                    .replace(EmailConstants.BASE_URL, utilityService.getBaseUrl())
	                    .replace(EmailConstants.APP_NAME, EmailConstants.MPLS_APP_NAME).replace(EmailConstants.APP_ACCOUNT_NAME, EmailConstants.MPLS);
	            new Thread(new Runnable() {
	                @Override
	                public void run() {
	                    emailSenderService.sendEmail(utilityService.getSenderMail(), customer.getEmailId().split(Constants.COMMA), null,
	                            EmailConstants.SUCCSSFUL_TOPUP_SUBJECT, message, null, null, true);
	                }
	            }).start();
		    }
	    }
		
		public int payBills(MerchantTransactionDTO merchantTransaction, CustomerTransactionDTO customerTransaction) {
			   //String isValidOffer = isValidMerchantOffer(customerTransaction.getmId(), customerTransaction.getcId(), customerTransaction.getTransactionValue(), customerTransaction.getOfferCode());
			   int isInserted = ctDao.insertCustomerTransaction(customerTransaction);
			   if(isInserted > 0){
				   int isInsertMerchantTransaction = ctDao.insertMerchantTransaction(merchantTransaction);
				   if(isInsertMerchantTransaction > 0){
					  int isPay = updateWalletBal(customerTransaction, merchantTransaction);
					  //applyMerchantOffer(merchantTransaction, customerTransaction, isPay, isValidOffer);
					  return isPay;
				   }
			  }
			  return 0;
		}	
		
		public int payBillsByQRCode(MerchantTransactionDTO merchantTransaction, CustomerTransactionDTO customerTransaction) {
			   //String isValidOffer = isValidMerchantOffer(customerTransaction.getmId(), customerTransaction.getcId(), customerTransaction.getTransactionValue(), customerTransaction.getOfferCode());
			   int isInserted = ctDao.insertMerchantTransaction(merchantTransaction);
			   if(isInserted > 0){
				   int isInsertMerchantTransaction = ctDao.insertCustomerTransaction(customerTransaction);
				   if(isInsertMerchantTransaction > 0){
					   int isPay = updateWalletBal(customerTransaction, merchantTransaction);
					   //applyMerchantOffer(merchantTransaction, customerTransaction, isPay, isValidOffer);
					   return isPay;
				   }
			   }
			   return 0;
		}
		
		public int refundTransaction(String transactionId, String transactionType) {
			   CustomerTransactionDTO customerTransaction = ctDao.getTransDetailByTransType(transactionId, transactionType);
			   if(null != customerTransaction) {
				   customerTransaction.setTransactionType(TransactionType.REFUND.getTransactionType().toUpperCase());
				   customerTransaction.setIndicator(getPIndicatorForCustomer(customerTransaction.getTransactionType()));
				   if(!isMerchantLimitExceeds(customerTransaction.getmId(), customerTransaction.getTransactionValue())) {
					    int isInserted = ctDao.insertCustomerTransaction(customerTransaction);
					    if(isInserted > 0){
					    	MerchantTransactionDTO merchantTransaction = getMerchantTransactionObj(customerTransaction);
					    	merchantTransaction.setIndicator(getPIndicatorForMerchant(merchantTransaction.getTransactionType()));
						    ctDao.insertMerchantTransaction(merchantTransaction);
						    return updateTransStatusForRefund(customerTransaction, merchantTransaction);
					    }
				   }
				   return 3;
			   }
			   return 0;
		}
		
		private int updateTransStatusForRefund(CustomerTransactionDTO customerTransaction, MerchantTransactionDTO merchantTransaction) {
			    try {
			    	int isUpdateCStatus = ctDao.updateCustomerTransStatus(customerTransaction.getTransactionId(), customerTransaction.getcId(), Constants.REFUND_FLAG);
			    	int isUpdateMStatus = ctDao.updateMerchantTransStatus(merchantTransaction.getTransactionId(), merchantTransaction.getmId(), Constants.REFUND_FLAG);
			    	if(isUpdateCStatus > 0 && isUpdateMStatus > 0) {
			    		int isUpdatedWalletBal = updateWalletBal(customerTransaction, merchantTransaction);
			    		if(isUpdatedWalletBal == 1) {
			    			refundCashBackAmt(customerTransaction, merchantTransaction);
			    		}
			    		return isUpdatedWalletBal;
			    	}
			    }catch (Exception e) {
					LOGGER.error("UpdateTransStatusForRefund -- Error in Update Transaction Status for Merchant and Customer -- "+customerTransaction.getTransactionId());
				}
			    return 0;
		}
		
		private void refundCashBackAmt(CustomerTransactionDTO customerTransaction, MerchantTransactionDTO merchantTransaction) {
			    CustomerTransactionDTO cCBTransaction = ctDao.getTransDetailByTransType(customerTransaction.getTransactionId(), TransactionType.CASHBACK.getTransactionType().toUpperCase());
			    if(null != cCBTransaction && cCBTransaction.getTransactionValue() > 0) {
			    	customerTransaction.setCashbackAmt(cCBTransaction.getTransactionValue());
			    	setRefundCashbackTransactionObj(customerTransaction, merchantTransaction);
			    	int isInserted = ctDao.insertCustomerTransaction(customerTransaction);
			    	if(isInserted > 0){
			    		ctDao.insertMerchantTransaction(merchantTransaction);
			    	}
			    	updateWalletBal(customerTransaction, merchantTransaction);
			    }
		}
		
		private void setRefundCashbackTransactionObj(CustomerTransactionDTO customerTransaction, MerchantTransactionDTO merchantTransaction) {
				customerTransaction.setIndicator(Constants.INDICATOR_DEBIT);
		    	merchantTransaction.setIndicator(Constants.INDICATOR_CREDIT);
		    	customerTransaction.setTransactionType(TransactionType.REFUND.getTransactionType().toUpperCase());
		    	merchantTransaction.setTransactionType(TransactionType.REFUND.getTransactionType().toUpperCase());
		    	customerTransaction.setPayType(PayType.LOYALTY.getPayType());
		    	merchantTransaction.setPayType(PayType.LOYALTY.getPayType());
		    	customerTransaction.setTransactionValue(customerTransaction.getCashbackAmt());
		    	merchantTransaction.setTransactionValue(customerTransaction.getCashbackAmt());
		}
		
		public int updateWalletBal(CustomerTransactionDTO customerTransaction, MerchantTransactionDTO merchantTransaction) {
			   try {
				   int isUpdatedCBal = ctDao.updateCustomerWalletBal(customerTransaction);
				   int isUpdatedMBal = ctDao.updateMerchantWalletBal(merchantTransaction);
				   if(isUpdatedCBal == 2 && isUpdatedMBal == 1) {
					   return 1;
				   }
			   }catch (Exception e) {
				   LOGGER.error("UpdateWalletBalForTopUp --- Error in Update Customer & Merchant Wallet Balance -- "+e.getMessage());
			   }
			   return 0;
		}

		public MerchantMappingDTO getCustomerWalletByMerchant(int cId, int mId) {
			   return ctDao.getCustomerWalletByMerchant(cId, mId);
		}
		
		public CustomerTransactionDTO getCustomerTransactionId(String customerTransId) {
			   return ctDao.getCustomerTransactionId(customerTransId);
		}
		
		public List<CustomerTransactionDTO> getCustomerTransaction(int cId, String fromDate, String toDate){
		       return ctDao.getCustomerTransaction(cId, fromDate, toDate);
		}

		public List<CustomerTransactionDTO> getTopCustomerTransaction(int id) {
			   return ctDao.getTopCustomerTransaction(id);
		}
		
		public int insertMerchantTransaction(MerchantTransactionDTO merchantTransaction) {
			   return ctDao.insertMerchantTransaction(merchantTransaction);
		}
		
		private void applyMerchantOffer(MerchantTransactionDTO merchantTransaction, CustomerTransactionDTO customerTransaction, int isPay, String isValidOffer){
			    if(StringUtils.isNotEmpty(customerTransaction.getOfferCode()) && isPay == 1 && isValidOffer.equals(Constants.SUCCESS)){
			    	 List<LoyaltyCashbackDTO> loyaltyCashback = loyaltyCashbackService.getLoyaltyCashbackByMId(merchantTransaction.getmId());
			    	 if(!loyaltyCashback.isEmpty()){
		    			  offerCashbackThread.setData(customerTransaction, merchantTransaction, loyaltyCashback.get(0));
		    			  taskExecutor.execute(offerCashbackThread);
			    	 }
			    }
		}
		
		private String isValidMerchantOffer(int mId, int cId, double amount, String offerCode){
			    List<LoyaltyCashbackDTO> loyaltyCashback = loyaltyCashbackService.getLoyaltyCashbackByMId(mId);
			    if(!loyaltyCashback.isEmpty()){
				     return loyaltyCashbackService.validateMerchantOffer(mId, cId, amount, loyaltyCashback.get(0));
			    }
			    return Constants.BLANK;
		}		
		
	    public CustomerTransactionDTO getCustomerTransactionObj(CustomerTransactionDTO customerTransaction){
	    	   customerTransaction.setIndicator(getPIndicatorForCustomer(customerTransaction.getTransactionType()));
	    	   if(StringUtils.isEmpty(customerTransaction.getWalletTransactionId())) {
	    		   customerTransaction.setWalletTransactionId(utilityService.generateNumber(12, false, true));
	    		   customerTransaction.setTransactionId(utilityService.generateCustomerTxnId(false, true));
	    	   }
			   customerTransaction.setStatus(Constants.SUCCESS_FLAG);
			   customerTransaction.setTransactionValue(customerTransaction.getWalletBal());
			   if(customerTransaction.getTransactionType().equals(Constants.COLLECT_MONEY)) {
				   customerTransaction.setPayType(PayType.ONLINE.getPayType());
				   customerTransaction.setTransactionType(TransactionType.PAY.name());
			   }else {
				   customerTransaction.setPayType(PayType.CASH.getPayType());
			   }
			   return customerTransaction;
	    }
	    
	    public MerchantTransactionDTO getMerchantTransactionObj(CustomerTransactionDTO customerTransaction){
		       MerchantTransactionDTO merchantTransaction = new MerchantTransactionDTO();
		       BeanUtils.copyProperties(customerTransaction, merchantTransaction);
		       merchantTransaction.setIndicator(getPIndicatorForMerchant(customerTransaction.getTransactionType()));
		       return merchantTransaction;
	    }
	    
	    public String getPIndicatorForCustomer(String transactionType) {
	    	   if(transactionType.equals(TransactionType.TOPUP.name())) {
	    	    	  return Constants.INDICATOR_CREDIT;
	    	   }else if(transactionType.equals(TransactionType.PAY.name())) {
	    	    	  return Constants.INDICATOR_DEBIT;
	    	   }else if(transactionType.equals(Constants.COLLECT_MONEY)) {
	    	    	  return Constants.INDICATOR_DEBIT;
	    	   }else if(transactionType.equals(TransactionType.REFUND.name())) {
	    	    	  return Constants.INDICATOR_DEBIT;
	    	   }else if(transactionType.equals(TransactionType.CASHBACK.name())) {
	    	    	  return Constants.INDICATOR_CREDIT;
	    	   }
			   return Constants.BLANK;
	    }
	    
	    public String getPIndicatorForMerchant(String transactionType) {
	    	   if(transactionType.equals(TransactionType.TOPUP.name())) {
	    	    	  return Constants.INDICATOR_DEBIT;
	    	   }else if(transactionType.equals(TransactionType.PAY.name())) {
	    	    	  return Constants.INDICATOR_CREDIT;
	    	   }else if(transactionType.equals(TransactionType.REFUND.name())) {
	    	    	  return Constants.INDICATOR_CREDIT;
	    	   }else if(transactionType.equals(TransactionType.CASHBACK.name())) {
	    	    	  return Constants.INDICATOR_DEBIT;
	    	   }
			   return Constants.BLANK;
        }
	    
	    public CustomerTransactionDTO getCustomerTransDetailById(String customerTransId) {
			   return ctDao.getCustomerTransDetailById(customerTransId);
		}
	    
	    public CustomerTransactionDTO getCustomerTransDetailById(int cTId) {
			   return ctDao.getCustomerTransDetailById(cTId);
		}
	    
	    public int updateMerchantWalletBal(MerchantTransactionDTO merchantTransaction) {
	    	   return ctDao.updateMerchantWalletBal(merchantTransaction);
	    }
	    
	    private boolean isMerchantLimitExceeds(int mId, double transactionValue) {
	    	    MerchantDTO merchant = merchantService.getMerchantById(mId);
	    	    if(null != merchant) {
	    	    	 if(merchant.getWalletBal() >= transactionValue) {
	    	    		 return false;
	    	    	 }
	    	    }
	    	    return true;
	    }
	    
	    public int collectMoney(CustomerNotificationDTO customerNotification) {
			   int id = ctDao.collectMoney(customerNotification);
			   customerNotification.setId(id);
			   pushNotificationService.sendCollectMoneyNotification(customerNotification);
			   return id;
		}

		public int updateCollectMoneyStatus(int id, String transactionType, String updatedBy) {
			   return ctDao.updateCollectMoneyStatus(id, transactionType, updatedBy);
		}

		public List<CustomerTransactionDTO> getCustomerTransactionByMId(int cId, int mId, String fromDate, String toDate) {
			   return ctDao.getCustomerTransactionByMId(cId, mId, fromDate, toDate);
		}

		public int refundMoney(CustomerNotificationDTO customerNotification) {
			   int id = ctDao.refundMoney(customerNotification);
			   customerNotification.setId(id);
			   pushNotificationService.sendRefundMoneyNotification(customerNotification);
			   return id;
		}

		public List<CustomerNotificationDTO> getRefundMoneyByMerchant(int id, String fromDate, String toDate) {
			   return ctDao.getRefundMoneyByMerchant(id, fromDate, toDate);
		}

		public CustomerNotificationDTO getCustomerNotifcation(int id) {
			   return ctDao.getCustomerNotifcation(id);
		}
}
