package com.cratas.mpls.service;

import java.util.List;

import com.cratas.mpls.web.dto.CustomerNotificationDTO;
import com.cratas.mpls.web.dto.CustomerTransactionDTO;
import com.cratas.mpls.web.dto.MerchantMappingDTO;
import com.cratas.mpls.web.dto.MerchantTransactionDTO;

/**
 * 
 * @author mukesh
 *
 */
public interface ICustomerTransactionService {

	   int topUpTransaction(MerchantTransactionDTO merchantTransaction, CustomerTransactionDTO customerTransaction);
	   
	   int updateWalletBal(CustomerTransactionDTO customerTransaction, MerchantTransactionDTO merchantTransaction);
	   
	   int refundTransaction(String transactionId, String transactionType);

	   CustomerTransactionDTO getCustomerTransactionId(String customerTransactionId);
	   
	   List<CustomerTransactionDTO> getCustomerTransaction(int cId, String fromDate, String toDate);

	   int payBills(MerchantTransactionDTO merchantTransaction, CustomerTransactionDTO customerTransaction);
	   
	   int payBillsByQRCode(MerchantTransactionDTO merchantTransaction, CustomerTransactionDTO customerTransaction);

	   MerchantMappingDTO getCustomerWalletByMerchant(int cId, int mId);	   
	   
	   CustomerTransactionDTO getCustomerTransactionObj(CustomerTransactionDTO customerTransaction);

	   List<CustomerTransactionDTO> getTopCustomerTransaction(int id);
	   
	   CustomerTransactionDTO getCustomerTransDetailById(String customerTransactionId);
	   
	   MerchantTransactionDTO getMerchantTransactionObj(CustomerTransactionDTO customerTransaction);
	   
	   String getPIndicatorForCustomer(String transactionType);
	   
	   String getPIndicatorForMerchant(String transactionType);
	   
	   int insertMerchantTransaction(MerchantTransactionDTO merchantTransaction);
	   
	   int updateMerchantWalletBal(MerchantTransactionDTO merchantTransaction);
	   
	   CustomerTransactionDTO getCustomerTransDetailById(int cTId);
	   
	   int collectMoney(CustomerNotificationDTO customerNotification);
		
	   int updateCollectMoneyStatus(int id, String transactionType, String updatedBy);
	   	   
	   List<CustomerTransactionDTO> getCustomerTransactionByMId(int cId, int mId, String fromDate, String toDate);
	   	   
	   int refundMoney(CustomerNotificationDTO customerNotification);

	   List<CustomerNotificationDTO> getRefundMoneyByMerchant(int id, String fromDate, String toDate);
	   
	   CustomerNotificationDTO getCustomerNotifcation(int id);
}
