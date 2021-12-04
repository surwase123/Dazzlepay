package com.cratas.mpls.dao.impl;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.query.CustomerTransactionQuery;
import com.cratas.mpls.common.query.MerchantMappingQuery;
import com.cratas.mpls.common.utility.PayType;
import com.cratas.mpls.common.utility.TransactionType;
import com.cratas.mpls.dao.ICustomerTransactionDao;
import com.cratas.mpls.web.dto.CustomerNotificationDTO;
import com.cratas.mpls.web.dto.CustomerTransactionDTO;
import com.cratas.mpls.web.dto.MerchantMappingDTO;
import com.cratas.mpls.web.dto.MerchantTransactionDTO;

/**
 * 
 * @author Bhupendra
 *
 */
@Repository
public class CustomerTransactionDaoImpl implements ICustomerTransactionDao{
	
		@Autowired
		private JdbcTemplate jdbcTemplate;
		
		@Autowired
		private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

		public int insertCustomerTransaction(CustomerTransactionDTO customerTransaction) {
			   BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(customerTransaction);
			   return namedParameterJdbcTemplate.update(CustomerTransactionQuery.INSERT_CUSTOMER_TRANSACTION, params);
		}
		
		public int insertMerchantTransaction(MerchantTransactionDTO merchantTransaction) {
			   BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(merchantTransaction);
			   return namedParameterJdbcTemplate.update(CustomerTransactionQuery.INSERT_MERCHANT_TRANSACTION, params);
		}

		public int updateCustomerWalletBal(CustomerTransactionDTO customerTransaction) {
			   if(customerTransaction.getIndicator().equals(Constants.INDICATOR_CREDIT)) {
				   Object[] arg = { customerTransaction.getTransactionValue(), customerTransaction.getTransactionValue(), customerTransaction.getCreatedBy(), customerTransaction.getCreatedBy(), customerTransaction.getcId(), customerTransaction.getmId() };
				   return jdbcTemplate.update(MerchantMappingQuery.UPDATE_CUSTOMER_WALLET_BAL_FOR_C, arg);
			   }else if(customerTransaction.getIndicator().equals(Constants.INDICATOR_DEBIT)){
				   Object[] arg = { customerTransaction.getTransactionValue(), customerTransaction.getTransactionValue(), customerTransaction.getCreatedBy(), customerTransaction.getCreatedBy(), customerTransaction.getcId(), customerTransaction.getmId() };
				   return jdbcTemplate.update(MerchantMappingQuery.UPDATE_CUSTOMER_WALLET_BAL_FOR_P, arg);
			   }
			   return 0;
		}

		public int updateMerchantWalletBal(MerchantTransactionDTO merchantTransaction) {
			   if(merchantTransaction.getIndicator().equals(Constants.INDICATOR_CREDIT)) {
				   Object[] arg = { merchantTransaction.getTransactionValue(), merchantTransaction.getCreatedBy(), merchantTransaction.getmId() };
				   return jdbcTemplate.update(MerchantMappingQuery.UPDATE_MERCHANT_WALLET_BAL_FOR_C, arg);
			   }else if(merchantTransaction.getIndicator().equals(Constants.INDICATOR_DEBIT)){
				   Object[] arg = { merchantTransaction.getTransactionValue(), merchantTransaction.getCreatedBy(), merchantTransaction.getmId() };
				   return jdbcTemplate.update(MerchantMappingQuery.UPDATE_MERCHANT_WALLET_BAL_FOR_P, arg);
			   }
			   return 0;
		}

		public int payMoney(int cId, int mId, double walletBal, String updatedBy) {
			   Object[] arg = { walletBal, walletBal, updatedBy, updatedBy, cId, mId };
			   return jdbcTemplate.update(MerchantMappingQuery.UPDATE_CUSTOMER_WALLET_BAL_FOR_P, arg);
		}
		
		public int updateCustomerWalletBal(int cId, int mId, double walletBal, String updatedBy) {
			   Object[] arg = { walletBal, walletBal, updatedBy, updatedBy, cId, mId };
			   return jdbcTemplate.update(MerchantMappingQuery.UPDATE_CUSTOMER_WALLET_BAL_FOR_C, arg);
		}

		public MerchantMappingDTO getCustomerWalletByMerchant(int cId, int mId) {
			   Object[] args = { cId, mId };
			   List<MerchantMappingDTO> customerWalletList = jdbcTemplate.query(CustomerTransactionQuery.GET_CUSTOMER_WALLET_BY_MERCHANT, args, new BeanPropertyRowMapper<MerchantMappingDTO>(MerchantMappingDTO.class));
			   if(!customerWalletList.isEmpty()){
			        return customerWalletList.get(0);
			   }
			   return null;	
		}
		
		public List<CustomerTransactionDTO> getCustomerTransaction(int cId, String fromDate, String toDate) {
			   Object args[] = { cId, fromDate, toDate };
			   return jdbcTemplate.query(CustomerTransactionQuery.SELECT_CUSTOMER_TRANSACTION, args, new BeanPropertyRowMapper<CustomerTransactionDTO>(CustomerTransactionDTO.class));
		}
		
		public CustomerTransactionDTO getCustomerTransactionId(String customerTransactionId) {
			   Object args[] = { customerTransactionId };
			   List<CustomerTransactionDTO> customerTransactionList = jdbcTemplate.query(CustomerTransactionQuery.GET_CUSTOMER_BY_TXNID, args, new BeanPropertyRowMapper<CustomerTransactionDTO>(CustomerTransactionDTO.class));
			   if(!customerTransactionList.isEmpty()){
			         return customerTransactionList.get(0);
			   }
			   return null;	
		}
		
		public List<CustomerTransactionDTO> getTopCustomerTransaction(int id) {
			   Object args[] = { id };
			   return jdbcTemplate.query(CustomerTransactionQuery.SELECT_TOP_CUSTOMER_TRANSACTION, args, new BeanPropertyRowMapper<CustomerTransactionDTO>(CustomerTransactionDTO.class));
		}
		
		public int updateMerchantTransStatus(String transactionId,  int mId, String status) {
			   Object args[] = { status, mId, transactionId, TransactionType.REFUND.getTransactionType().toUpperCase() };
			   return jdbcTemplate.update(CustomerTransactionQuery.UPDATE_MERCHANT_TRANSACTION, args);
		}
		
		public int updateCustomerTransStatus(String transactionId,  int cId, String status) {
			   Object args[] = { status, cId, transactionId, TransactionType.REFUND.getTransactionType().toUpperCase() };
			   return jdbcTemplate.update(CustomerTransactionQuery.UPDATE_CUSTOMER_TRANSACTION, args);
		}
		
		public CustomerTransactionDTO getCustomerTransDetailById(int cTId) {
			   Object args[] = { cTId };
			   List<CustomerTransactionDTO> customerTransList = jdbcTemplate.query(CustomerTransactionQuery.GET_CUSTOMER_TRANS_BY_CT_ID, args, new BeanPropertyRowMapper<CustomerTransactionDTO>(CustomerTransactionDTO.class));
			   if(!customerTransList.isEmpty()){
			    	CustomerTransactionDTO customerTransaction = customerTransList.get(0);
			    	CustomerTransactionDTO cCBTransaction = getTransDetailByTransType(customerTransaction.getTransactionId(), TransactionType.CASHBACK.getTransactionType().toUpperCase());
			    	if(null != cCBTransaction) {
			    	    customerTransaction.setCashbackAmt(cCBTransaction.getTransactionValue());
			    	}
			    	return customerTransaction;
			   }
			   return null;	
		}
		
		public CustomerTransactionDTO getTransDetailByTransType(String transactionId, String transactionType) {
			   Object args[] = { transactionId, transactionType };
			   List<CustomerTransactionDTO> customerTransList = jdbcTemplate.query(CustomerTransactionQuery.GET_CUSTOMER_TRANS_BY_TRANS_TYPE, args, new BeanPropertyRowMapper<CustomerTransactionDTO>(CustomerTransactionDTO.class));
			   if(!customerTransList.isEmpty()){
			    	return customerTransList.get(0);
			   }
			   return null;	
		}
		
		public CustomerTransactionDTO getCustomerTransDetailById(String transactionId) {
			   Object args[] = { transactionId };
			   List<CustomerTransactionDTO> customerTransList = jdbcTemplate.query(CustomerTransactionQuery.GET_CUSTOMER_TRANS_BY_TXNID, args, new BeanPropertyRowMapper<CustomerTransactionDTO>(CustomerTransactionDTO.class));
			   if(!customerTransList.isEmpty()){
			    	 CustomerTransactionDTO customerTransaction = getCustomerTransactionDTOObj(customerTransList, false);
			    	 CustomerTransactionDTO customerTransCashback = getCustomerTransactionDTOObj(customerTransList, true);
			    	 customerTransaction.setCashbackAmt(customerTransCashback != null ? customerTransCashback.getTransactionValue() : 0);
			    	 return customerTransaction;
			   }
			   return null;	
		}
		
		private CustomerTransactionDTO getCustomerTransactionDTOObj(List<CustomerTransactionDTO> customerTransList, boolean payType) {
			    return customerTransList.stream()
				                .filter(tran -> payType?(PayType.LOYALTY.getPayType().equals(tran.getPayType())):(!PayType.LOYALTY.getPayType().equals(tran.getPayType())))
				                .findAny()
				                .orElse(null);
		}
		
		public int collectMoney(CustomerNotificationDTO customerNotification) {
			   KeyHolder keyHolder = new GeneratedKeyHolder();
			   jdbcTemplate.update(connection -> {
		                PreparedStatement ps = connection.prepareStatement(CustomerTransactionQuery.INSERT_CUSTOMER_NOTIFICATION,Statement.RETURN_GENERATED_KEYS);
		                ps.setInt(1, customerNotification.getcId());
		                ps.setInt(2, customerNotification.getmId());
		                ps.setDouble(3, customerNotification.getAmount());
		                ps.setString(4, customerNotification.getTransactionType());
		                ps.setString(5, customerNotification.getCreatedBy());
		                return ps;
		       }, keyHolder); 
		       return keyHolder.getKey().intValue();         
		}
		
		public int updateCollectMoneyStatus(int id, String transactionType, String updatedBy) {
			   int isUpdated=0;
				   if(transactionType.equals(Constants.PAY)) {
					   Object args[] = { Constants.P, updatedBy, id};
					   isUpdated = jdbcTemplate.update(CustomerTransactionQuery.UPDATE_CUSTOMER_NOTIFICATION, args);
				   }else if(transactionType.equals(Constants.REFUND)){
					   Object args[] = { Constants.P, updatedBy, id};
					   isUpdated = jdbcTemplate.update(CustomerTransactionQuery.UPDATE_CUSTOMER_NOTIFICATION, args);
				   }else {
					   Object args[] = { Constants.R, updatedBy, id};
					   isUpdated = jdbcTemplate.update(CustomerTransactionQuery.UPDATE_CUSTOMER_NOTIFICATION, args);
				   }
			   return isUpdated;
		}
		
		public List<CustomerTransactionDTO> getCustomerTransactionByMId(int cId, int mId, String fromDate, String toDate) {
			   Object args[] = { cId, mId, fromDate, toDate };
			   return jdbcTemplate.query(CustomerTransactionQuery.SELECT_CUSTOMER_TRANSACTION_BY_MERCHANT, args, new BeanPropertyRowMapper<CustomerTransactionDTO>(CustomerTransactionDTO.class));
		}

		public int refundMoney(CustomerNotificationDTO customerNotification) {
			   KeyHolder keyHolder = new GeneratedKeyHolder();
			   jdbcTemplate.update(connection -> {
		                PreparedStatement ps = connection.prepareStatement(CustomerTransactionQuery.INSERT_CUSTOMER_NOTIFICATION,Statement.RETURN_GENERATED_KEYS);
		                ps.setInt(1, customerNotification.getcId());
		                ps.setInt(2, customerNotification.getmId());
		                ps.setDouble(3, customerNotification.getAmount());
		                ps.setString(4, customerNotification.getTransactionType());
		                ps.setString(5, customerNotification.getCreatedBy());
		                return ps;
		       }, keyHolder); 
		       return keyHolder.getKey().intValue();
		}

		public List<CustomerNotificationDTO> getRefundMoneyByMerchant(int id, String fromDate, String toDate) {
			   Object args[] = {Constants.REFUND, Constants.P, id, fromDate, toDate };
			   return jdbcTemplate.query(CustomerTransactionQuery.REFUND_MONEY_BY_MERCHANT, args, new BeanPropertyRowMapper<CustomerNotificationDTO>(CustomerNotificationDTO.class));
		}

		public CustomerNotificationDTO getCustomerNotifcation(int id) {
			   Object args[] = { id };
			   List<CustomerNotificationDTO> customerNotificationList = jdbcTemplate.query(CustomerTransactionQuery.GET_CUSTOMER_NOTIFICATION, args, new BeanPropertyRowMapper<CustomerNotificationDTO>(CustomerNotificationDTO.class));
			   if(!customerNotificationList.isEmpty()){
			    	return customerNotificationList.get(0);
			   }
			   return null;	
		}

}

