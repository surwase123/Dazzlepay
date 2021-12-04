package com.cratas.mpls.dao;

import java.util.List;

import com.cratas.mpls.web.dto.MerchantTransactionDTO;

/**
 * 
 * @author mukesh
 *
 */
public interface IMerchantTransactionDao {
	
	   List<MerchantTransactionDTO> getMerchantTransaction(int mId, String fromDate, String toDate);
	   
	   List<MerchantTransactionDTO> getMerchantTOPUPTransaction(int mId, String fromDate, String toDate);
	   
	   List<MerchantTransactionDTO> getCustomerPayTransByMId(int mId, int cId, String transactionType);

	   MerchantTransactionDTO getMerchantTransByTransId(String merchantTransactionId);

	   MerchantTransactionDTO getMerchantTransByMIDTransId(int mId, String transactionId);
	   	   
	   MerchantTransactionDTO getMerchantTransDetailById(int mTId);

	   List<MerchantTransactionDTO> getTotalTransValue(String fromDate, String toDate);
	   
	   List<MerchantTransactionDTO> getAllMerchantTransaction(String fromDate, String toDate);
}
