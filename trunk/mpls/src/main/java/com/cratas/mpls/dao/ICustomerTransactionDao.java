package com.cratas.mpls.dao;

import java.util.List;

import com.cratas.mpls.web.dto.CustomerNotificationDTO;
import com.cratas.mpls.web.dto.CustomerTransactionDTO;
import com.cratas.mpls.web.dto.MerchantMappingDTO;
import com.cratas.mpls.web.dto.MerchantTransactionDTO;

/**
 * 
 * @author Bhupendra
 *
 */
public interface ICustomerTransactionDao {
		
	   int insertCustomerTransaction(CustomerTransactionDTO customerTransaction);
	   
	   int insertMerchantTransaction(MerchantTransactionDTO merchantTransaction);
	   
	   int updateCustomerWalletBal(CustomerTransactionDTO customerTransaction);
	   
	   int updateMerchantWalletBal(MerchantTransactionDTO merchantTransaction);
	   
	   CustomerTransactionDTO getCustomerTransactionId(String customerTransactionId);
	   
	   List<CustomerTransactionDTO> getCustomerTransaction(int cId, String fromDate, String toDate);	   

	   MerchantMappingDTO getCustomerWalletByMerchant(int cId, int mId);

	   List<CustomerTransactionDTO> getTopCustomerTransaction(int id);

	   int updateMerchantTransStatus(String transactionId, int mId, String status);
	   
	   int updateCustomerTransStatus(String transactionId,  int cId, String status);
	   
	   CustomerTransactionDTO getCustomerTransDetailById(String transactionId);
	   
	   CustomerTransactionDTO getCustomerTransDetailById(int cTId);
	   
	   CustomerTransactionDTO getTransDetailByTransType(String transactionId, String transactionType);
	   
	   int collectMoney(CustomerNotificationDTO customerNotification);
		
	   int updateCollectMoneyStatus(int id, String transactionType, String updatedBy);
	   	   
	   List<CustomerTransactionDTO> getCustomerTransactionByMId(int cId, int mId, String fromDate, String toDate);
	   	   
	   int refundMoney(CustomerNotificationDTO customerNotification);
		
	   List<CustomerNotificationDTO> getRefundMoneyByMerchant(int id, String fromDate, String toDate);

	   CustomerNotificationDTO getCustomerNotifcation(int id);
	   
	   
}
