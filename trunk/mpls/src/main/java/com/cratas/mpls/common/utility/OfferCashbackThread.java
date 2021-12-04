package com.cratas.mpls.common.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.dao.ICustomerTransactionDao;
import com.cratas.mpls.service.ICustomerTransactionService;
import com.cratas.mpls.service.ILoyaltyCashbackService;
import com.cratas.mpls.web.dto.CustomerTransactionDTO;
import com.cratas.mpls.web.dto.LoyaltyCashbackDTO;
import com.cratas.mpls.web.dto.MerchantTransactionDTO;

/**
 * 
 * @author mukesh
 *
 */
@Component
@Scope(Constants.PROTOTYPE)
public class OfferCashbackThread implements Runnable{
	
	   private final static Logger logger = LoggerFactory.getLogger(OfferCashbackThread.class);
		
	   @Autowired
	   private ICustomerTransactionService ctService;
	   	   
	   @Autowired
	   private ILoyaltyCashbackService loyaltyCashbackService;
	   
	   @Autowired
	   private ICustomerTransactionDao customerTransactionDao;
		
	   private CustomerTransactionDTO customerTransaction;
	   
	   private MerchantTransactionDTO merchantTransaction;
	   
	   private LoyaltyCashbackDTO loyalyCashback;
		
	   public void setData(CustomerTransactionDTO customerTransaction, MerchantTransactionDTO merchantTransaction, LoyaltyCashbackDTO loyalyCashback){
			  this.customerTransaction = customerTransaction;
			  this.merchantTransaction = merchantTransaction;
			  this.loyalyCashback = loyalyCashback;
	   }
		
	   public void run() {
		      try{
				  double caskbackAmt = loyaltyCashbackService.getOfferCashbackAmt(customerTransaction.getWalletBal(), loyalyCashback);
				  if(caskbackAmt > 0){
					   updateCustomerTransObj(caskbackAmt);
					   updateMerchantTransObj(caskbackAmt);
					   int isInserted = customerTransactionDao.insertCustomerTransaction(customerTransaction);
					   if(isInserted > 0){
						   int isInsertMerchantTransaction = customerTransactionDao.insertMerchantTransaction(merchantTransaction);
						   if(isInsertMerchantTransaction > 0){
							   ctService.updateWalletBal(customerTransaction, merchantTransaction);
						   }
					   }
				  }
		      }catch(Exception e){
		    	  logger.error("Error in Save Loyalty Cashback!!");
		      }
	   }
	   
	   private void updateCustomerTransObj(double caskbackAmt){
		       customerTransaction.setTransactionValue(caskbackAmt);
		       customerTransaction.setTransactionType(TransactionType.CASHBACK.name());
			   customerTransaction.setIndicator(ctService.getPIndicatorForCustomer(customerTransaction.getTransactionType()));
			   customerTransaction.setPayType(PayType.LOYALTY.getPayType());
	   }
	   
	   private void updateMerchantTransObj(double caskbackAmt){
		       merchantTransaction.setTransactionValue(caskbackAmt);
		       merchantTransaction.setTransactionType(TransactionType.CASHBACK.name());
		       merchantTransaction.setIndicator(ctService.getPIndicatorForMerchant(merchantTransaction.getTransactionType()));
		       merchantTransaction.setPayType(PayType.LOYALTY.getPayType());
       }

}
