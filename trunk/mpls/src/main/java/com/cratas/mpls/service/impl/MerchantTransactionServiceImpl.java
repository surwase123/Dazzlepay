package com.cratas.mpls.service.impl;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.utility.MerchantTransComparator;
import com.cratas.mpls.common.utility.PayType;
import com.cratas.mpls.common.utility.TransactionType;
import com.cratas.mpls.dao.IMerchantTransactionDao;
import com.cratas.mpls.service.ICustomerService;
import com.cratas.mpls.service.ICustomerTransactionService;
import com.cratas.mpls.service.IMerchantMappingService;
import com.cratas.mpls.service.IMerchantService;
import com.cratas.mpls.service.IMerchantTransactionService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.AdminDashboardDTO;
import com.cratas.mpls.web.dto.CustomerNotificationDTO;
import com.cratas.mpls.web.dto.MerchantDTO;
import com.cratas.mpls.web.dto.MerchantDashboardDTO;
import com.cratas.mpls.web.dto.MerchantMappingDTO;
import com.cratas.mpls.web.dto.MerchantTransactionDTO;

/**
 * 
 * @author mukesh
 *
 */
@Service
public class MerchantTransactionServiceImpl implements IMerchantTransactionService {
	
	   @Autowired
	   private IMerchantTransactionDao merchantTransactionDao;
	   
	   @Autowired
	   private IMerchantMappingService merchantMappingService;
	   
	   @Autowired
	   private IMerchantService merchantService;
	   
	   @Autowired
	   private IUtilityService utilityService;
	   
	   @Autowired
	   private ICustomerTransactionService ctService;
	   
	   @Autowired
	   private ICustomerService customerService;
	   
	   public List<MerchantTransactionDTO> getMerchantTransaction(int mId, String fromDate, String toDate){
		      List<MerchantTransactionDTO> transList = new LinkedList<>();
		      List<MerchantTransactionDTO> topUptransactionList = merchantTransactionDao.getMerchantTOPUPTransaction(mId, fromDate, toDate);
		      List<MerchantTransactionDTO> transactionList = merchantTransactionDao.getMerchantTransaction(mId, fromDate, toDate);
		      transList.addAll(topUptransactionList);
		      transList.addAll(transactionList);
		      if(!transList.isEmpty()) {
		    	  Collections.sort(transList, new MerchantTransComparator()); 
		      }
		      return transList;
	   }
	   
       public MerchantDashboardDTO getMerchantDashboardData(int mId, String fromDate, String toDate){
    	      MerchantDashboardDTO merchantDashboard = new MerchantDashboardDTO();
    	      MerchantDTO merchant = merchantService.getMerchantById(mId);
    	      if(null != merchant) {
    	    	  merchantDashboard.setMerchantWalletBal(merchant.getWalletBal());
	    	      List<MerchantTransactionDTO> mechantTransaction = merchantTransactionDao.getMerchantTransaction(mId, fromDate, toDate);
	    	      setMerchantTransaction(merchantDashboard, mechantTransaction);
	    	      setMerchantCustomers(merchantDashboard, mId, fromDate, toDate);
	    	      merchantDashboard.setTotalMerchantTotalTopup(getTotalMerchantTopUp(merchant, fromDate, toDate));
	    	      merchantDashboard.setTotalRefund(getRefundByMerchant(merchant.getId(), fromDate, toDate));
    	      }
    	      return merchantDashboard;
       } 
       
       private double getRefundByMerchant(int id, String fromDate, String toDate) {
    	       List<CustomerNotificationDTO> customerNotification =  ctService.getRefundMoneyByMerchant(id, fromDate, toDate);
    	       double totalRefund = 0;
    	       for (CustomerNotificationDTO customerNotificationDTO : customerNotification) {
    	    	   totalRefund = totalRefund + customerNotificationDTO.getAmount();
    	       }
    	       return totalRefund;
	   }

	   public List<MerchantTransactionDTO> getCustomerPayTransByMId(int mId, int cId){
    	      return merchantTransactionDao.getCustomerPayTransByMId(mId, cId, TransactionType.PAY.getTransactionType().toUpperCase());
       }
       
       private MerchantDashboardDTO setMerchantTransaction(MerchantDashboardDTO merchantDashboard, List<MerchantTransactionDTO> mechantTransaction){
    	       int successTopUp = 0; int successPay = 0; double totalTopUpSuccess = 0; double totalPaySuccess = 0; double totalCashback = 0;
    	       for (MerchantTransactionDTO merchantTransaction : mechantTransaction) {
    	    	   if(isTopUpTransaction(merchantTransaction)){
    	    		   if(merchantTransaction.getStatus().equals(Constants.SUCCESS_FLAG)){
    	    			   successTopUp = successTopUp + 1;
    	    			   totalTopUpSuccess = totalTopUpSuccess + merchantTransaction.getTransactionValue();
			           }
			       }
    	    	   if(isPayTransaction(merchantTransaction)){
    	    		   if(merchantTransaction.getStatus().equals(Constants.SUCCESS_FLAG)){
    	    			   successPay = successPay + 1;
    	    			   totalPaySuccess = totalPaySuccess + merchantTransaction.getTransactionValue();
			           }
			       }
			       if(isCashbackTransaction(merchantTransaction) && merchantTransaction.getStatus().equals(Constants.SUCCESS_FLAG)){
			    	   totalCashback = totalCashback + merchantTransaction.getTransactionValue();
			       }
			   }
    	       merchantDashboard.setTotalTopUpSuccess(totalTopUpSuccess);
    	       merchantDashboard.setTotalPaySuccess(totalPaySuccess);
    	       return merchantDashboard;
       }
       
       private double getTotalMerchantTopUp(MerchantDTO merchant, String fromDate, String toDate) {
    	       double totalMerchantTotalTopup = 0;
    	       List<MerchantTransactionDTO> topUptransactionList = merchantTransactionDao.getMerchantTOPUPTransaction(merchant.getId(), fromDate, toDate);
    	       for (MerchantTransactionDTO merchantTransaction : topUptransactionList) {
    	    	   totalMerchantTotalTopup = totalMerchantTotalTopup + merchantTransaction.getTransactionValue();
			   }
    	       return totalMerchantTotalTopup;
       }
       
       private boolean isCashbackTransaction(MerchantTransactionDTO merchantTransaction){
    	       if(TransactionType.CASHBACK.getTransactionType().equalsIgnoreCase(merchantTransaction.getTransactionType())){
    	    	   return true;
    	       }
    	       return false;
       }
       
       private boolean isTopUpTransaction(MerchantTransactionDTO merchantTransaction){
		       if(TransactionType.TOPUP.getTransactionType().equalsIgnoreCase(merchantTransaction.getTransactionType())){
		    	   return true;
		       }
		       return false;
       }
       
       private boolean isPayTransaction(MerchantTransactionDTO merchantTransaction){
		       if(TransactionType.PAY.getTransactionType().equalsIgnoreCase(merchantTransaction.getTransactionType())){
		    	   return true;
		       }
		       return false;
       }
       
       private MerchantDashboardDTO setMerchantCustomers(MerchantDashboardDTO merchantDashboard, int mId, String fromDate, String toDate){
	           List<MerchantMappingDTO> merchantCustomers = merchantMappingService.getCustomerByMerchant(mId);
	           merchantDashboard.setTotalCustomerBal(getCustomerBalByMerchant(merchantCustomers));
	           merchantDashboard.setTotalCustomer(merchantCustomers.size());
	           return merchantDashboard;
       }
       
	   private double getCustomerBalByMerchant(List<MerchantMappingDTO> merchantCustomers) {
		       double totalCustomerWalletBal = 0;
		   	   for (MerchantMappingDTO merchantMappingDTO : merchantCustomers) {
		   		totalCustomerWalletBal = totalCustomerWalletBal + merchantMappingDTO.getWalletBal();
			   }
		   	   return totalCustomerWalletBal;
	   }

	   public MerchantTransactionDTO getMerchantTransByTransId(String merchantTransactionId) {
 	          return merchantTransactionDao.getMerchantTransByTransId(merchantTransactionId);
       }
       
       public MerchantTransactionDTO getMerchantTransByMIDTransId(int mId, String transactionId) {
	          return merchantTransactionDao.getMerchantTransByMIDTransId(mId, transactionId);
       }
       
       public int topUpMerchantWallet(double amount, int mId, String loginId) {
			  MerchantTransactionDTO merchantTransaction = getMerchantTransactionObj(amount, mId, loginId);
			  return ctService.updateMerchantWalletBal(merchantTransaction);
		}
		
		private MerchantTransactionDTO getMerchantTransactionObj(double amount, int mId, String loginId){
		        MerchantTransactionDTO merchantTransaction = new MerchantTransactionDTO();
		        merchantTransaction.setmId(mId);
		        merchantTransaction.setTransactionType(TransactionType.TOPUP.getTransactionType().toUpperCase());
		        merchantTransaction.setWalletTransactionId(utilityService.generateNumber(12, false, true));
		    	merchantTransaction.setTransactionId(utilityService.generateCustomerTxnId(false, true));
	    	    merchantTransaction.setStatus(Constants.SUCCESS_FLAG);
			    merchantTransaction.setTransactionValue(amount);
			    merchantTransaction.setPayType(PayType.CASH.getPayType());
		        merchantTransaction.setIndicator(Constants.INDICATOR_CREDIT);
		        merchantTransaction.setCreatedBy(loginId);
		        return merchantTransaction;
	    }

		public MerchantTransactionDTO getMerchantTransDetailById(int mTId) {
			   return merchantTransactionDao.getMerchantTransDetailById(mTId);
		}

		public AdminDashboardDTO getAdminDashboardData(String fromDate, String toDate) {
			   AdminDashboardDTO adminDashboard = new AdminDashboardDTO();
			   adminDashboard.setTotalMerchant(merchantService.getTotalMerchant());
			   adminDashboard.setTotalCustomer(customerService.getTotalCustomer());
			   adminDashboard.setActiveMerchantCount(merchantService.getActiveMerchant(fromDate, toDate));
			   adminDashboard.setActiveCustomerCount(customerService.getActiveCustomer(fromDate, toDate));
			   adminDashboard.setNewMerchant(merchantService.getRecentMerchant(fromDate, toDate));
			   adminDashboard.setNewCustomer(customerService.getRecentCustomer(fromDate, toDate));
			   adminDashboard.setMerchantWalletBal(getMerchantWalletBal());
			   adminDashboard.setCustomerBalance(getCustomerBal());
			   adminDashboard.setTotalTransValue(getTotalTransVal(fromDate, toDate));
			   return adminDashboard;
		}

		private double getTotalTransVal(String fromDate, String toDate) {
			    List<MerchantTransactionDTO> merchantTransaction = merchantTransactionDao.getTotalTransValue(fromDate, toDate);
			    double totalTransValue = 0;
			    for (MerchantTransactionDTO merchantTransactionDTO : merchantTransaction) {
			    	totalTransValue = totalTransValue + merchantTransactionDTO.getTransactionValue();
				}
			    return totalTransValue;
		}

		private double getCustomerBal() {
			    double totalCustWalletBal = 0;
			    List<MerchantMappingDTO> merchantMapping = merchantMappingService.getCustomerBal();
			    for (MerchantMappingDTO merchantMappingDTO : merchantMapping) {
			    	totalCustWalletBal = totalCustWalletBal + merchantMappingDTO.getWalletBal();
				}
			    return totalCustWalletBal;
		}

		private double getMerchantWalletBal() {
			    List<MerchantDTO> merchant = merchantService.getMerchantWalletBal();
			    double totalMerchantWalletBal = 0;
			    for (MerchantDTO merchantDTO : merchant) {
			    	totalMerchantWalletBal = totalMerchantWalletBal + merchantDTO.getWalletBal();
				}
			    return totalMerchantWalletBal;
		}

		public List<MerchantTransactionDTO> getAllMerchantTransaction(String fromDate, String toDate) {
			   return merchantTransactionDao.getAllMerchantTransaction(fromDate, toDate);
		}

}
