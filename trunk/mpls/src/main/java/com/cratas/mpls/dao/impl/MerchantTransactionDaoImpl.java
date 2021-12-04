package com.cratas.mpls.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cratas.mpls.common.query.MerchantTransactionQuery;
import com.cratas.mpls.common.utility.TransactionType;
import com.cratas.mpls.dao.IMerchantTransactionDao;
import com.cratas.mpls.web.dto.MerchantTransactionDTO;

/**
 * 
 * @author mukesh
 *
 */
@Repository
public class MerchantTransactionDaoImpl implements IMerchantTransactionDao {
	   
	   @Autowired
	   private JdbcTemplate jdbcTemplate;
	
	   public List<MerchantTransactionDTO> getMerchantTransaction(int mId, String fromDate, String toDate) {
			  Object args[] = { mId, fromDate, toDate };
			  return jdbcTemplate.query(MerchantTransactionQuery.SELECT_MERCHANT_TRANSACTION, args, new BeanPropertyRowMapper<MerchantTransactionDTO>(MerchantTransactionDTO.class));
	   }
	   
	   public List<MerchantTransactionDTO> getMerchantTOPUPTransaction(int mId, String fromDate, String toDate) {
			  Object args[] = { mId, fromDate, toDate };
			  return jdbcTemplate.query(MerchantTransactionQuery.SELECT_TOPUP_MERCHANT_TRANSACTION, args, new BeanPropertyRowMapper<MerchantTransactionDTO>(MerchantTransactionDTO.class));
	   }
	   
	   public List<MerchantTransactionDTO> getCustomerPayTransByMId(int mId, int cId, String transactionType){
		      Object args[] = { mId, cId, transactionType };
			  return jdbcTemplate.query(MerchantTransactionQuery.SELECT_CUSTOMER_PAY_TRANS_BY_MID, args, new BeanPropertyRowMapper<MerchantTransactionDTO>(MerchantTransactionDTO.class));
	   }
	   
	   public MerchantTransactionDTO getMerchantTransByTransId(String merchantTransactionId) {
		      Object args[] = { merchantTransactionId };
		      List<MerchantTransactionDTO> merchantTransactionList = jdbcTemplate.query(MerchantTransactionQuery.GET_MERCHANT_TRANS_BY_TXNID, args, new BeanPropertyRowMapper<MerchantTransactionDTO>(MerchantTransactionDTO.class));
		      if(!merchantTransactionList.isEmpty()){
		          return merchantTransactionList.get(0);
		      }
		      return null;
       }
	   
	   public MerchantTransactionDTO getMerchantTransByMIDTransId(int mId, String transactionId) {
		      Object args[] = { mId, transactionId };
		      List<MerchantTransactionDTO> merchantTransactionList = jdbcTemplate.query(MerchantTransactionQuery.GET_MERCHANT_TRANS_BY_MID_TXNID, args, new BeanPropertyRowMapper<MerchantTransactionDTO>(MerchantTransactionDTO.class));
		      if(!merchantTransactionList.isEmpty()){
		          return merchantTransactionList.get(0);
		      }
		      return null;
       }
	   
	   public MerchantTransactionDTO getMerchantTransDetailById(int mTId) {
			  Object args[] = { mTId };
			  List<MerchantTransactionDTO> merchantTransList = jdbcTemplate.query(MerchantTransactionQuery.GET_MERCHANT_TRANS_BY_MT_ID, args, new BeanPropertyRowMapper<MerchantTransactionDTO>(MerchantTransactionDTO.class));
			  if(!merchantTransList.isEmpty()){
				  MerchantTransactionDTO merchantTransaction = merchantTransList.get(0);
				  MerchantTransactionDTO mCBTransaction = getMerchantTransByTransType(merchantTransaction.getTransactionId(), TransactionType.CASHBACK.getTransactionType().toUpperCase());
				  if(null != mCBTransaction) {
					  merchantTransaction.setCashbackAmt(mCBTransaction.getTransactionValue());
				  }
				  return merchantTransaction;
			  }
			  return null;	
	   }
	   
	   private MerchantTransactionDTO getMerchantTransByTransType(String transactionId, String transactionType) {
		       Object args[] = { transactionId, transactionType };
		       List<MerchantTransactionDTO> merchantTransactionList = jdbcTemplate.query(MerchantTransactionQuery.GET_MERCHANT_TRANS_BY_TRANS_TYPE, args, new BeanPropertyRowMapper<MerchantTransactionDTO>(MerchantTransactionDTO.class));
		       if(!merchantTransactionList.isEmpty()){
		           return merchantTransactionList.get(0);
		       }
		       return null;
       }

	   public List<MerchantTransactionDTO> getTotalTransValue(String fromDate, String toDate) {
		      Object args[] = { fromDate, toDate };
		      return jdbcTemplate.query(MerchantTransactionQuery.GET_TOTAL_TRAN_VAL, args, new BeanPropertyRowMapper<MerchantTransactionDTO>(MerchantTransactionDTO.class));
	   }

	   public List<MerchantTransactionDTO> getAllMerchantTransaction(String fromDate, String toDate) {
		      Object args[] = { fromDate, toDate };
		      return jdbcTemplate.query(MerchantTransactionQuery.SELECT_ALL_MERCHANT_TRANSACTION, args, new BeanPropertyRowMapper<MerchantTransactionDTO>(MerchantTransactionDTO.class));
	   }
	

}
