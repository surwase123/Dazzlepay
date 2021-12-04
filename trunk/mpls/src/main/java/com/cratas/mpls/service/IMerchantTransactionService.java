package com.cratas.mpls.service;

import java.util.List;

import com.cratas.mpls.web.dto.AdminDashboardDTO;
import com.cratas.mpls.web.dto.MerchantDashboardDTO;
import com.cratas.mpls.web.dto.MerchantTransactionDTO;

/**
 * 
 * @author mukesh
 *
 */
public interface IMerchantTransactionService {
	
	   List<MerchantTransactionDTO> getMerchantTransaction(int mId, String fromDate, String toDate);
	   
	   MerchantDashboardDTO getMerchantDashboardData(int mId, String fromDate, String toDate);
	   
	   List<MerchantTransactionDTO> getCustomerPayTransByMId(int mId, int cId);
	   
	   MerchantTransactionDTO getMerchantTransByTransId(String merchantTransactionId);
	   
	   MerchantTransactionDTO getMerchantTransByMIDTransId(int mId, String transactionId);
	   
	   int topUpMerchantWallet(double amount, int mId, String loginId);
	   
	   MerchantTransactionDTO getMerchantTransDetailById(int mTId);

	   AdminDashboardDTO getAdminDashboardData(String fromDate, String toDate);

	   List<MerchantTransactionDTO> getAllMerchantTransaction(String fromDate, String toDate);

}
