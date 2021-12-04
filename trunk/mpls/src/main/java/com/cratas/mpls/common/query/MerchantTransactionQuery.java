package com.cratas.mpls.common.query;

/**
 * 
 * @author mukesh
 *
 */
public class MerchantTransactionQuery {
	
	   public static final String SELECT_MERCHANT_TRANSACTION = "select mt.*, m.merchantName, m.merchantId, u.firstName, u.middleName, u.lastName, c.customerId, u.mobileNumber from merchantTransaction mt "
	   		+ "LEFT JOIN merchant m on mt.mId=m.id LEFT JOIN customer c on mt.cId = c.id LEFT JOIN user u on c.userId = u.id where u.isActive=1 and c.isActive=1 and m.isActive=1 and mt.mId=? and mt.createdDate "
	   		+ "between ? and ? order by mt.id desc";
	   
	   public static final String SELECT_CUSTOMER_PAY_TRANS_BY_MID = "select mt.*, m.merchantName, m.merchantId, u.firstName, u.middleName, u.lastName, c.customerId, u.mobileNumber from merchantTransaction mt "
		   		+ "LEFT JOIN merchant m on mt.mId=m.id LEFT JOIN customer c on mt.cId = c.id LEFT JOIN user u on c.userId = u.id where u.isActive=1 and c.isActive=1 and m.isActive=1 and mt.status='S' and mt.mId=? and mt.cId=? "
		   		+ "and mt.transactionType = ? order by mt.createdDate desc";

	   public static final String GET_MERCHANT_TRANS_BY_TXNID = "select mt.*, m.merchantName, m.merchantId, u.firstName, u.middleName, u.lastName, c.customerId, u.mobileNumber from merchantTransaction mt "
		   		+ "LEFT JOIN merchant m on mt.mId=m.id LEFT JOIN customer c on mt.cId = c.id LEFT JOIN user u on c.userId = u.id where mt.transactionId=?";

	   public static final String GET_MERCHANT_TRANS_BY_MID_TXNID = "select mt.*, m.merchantName, m.merchantId, u.firstName, u.middleName, u.lastName, c.customerId, u.mobileNumber from merchantTransaction mt "
		   		+ "LEFT JOIN merchant m on mt.mId=m.id LEFT JOIN customer c on mt.cId = c.id LEFT JOIN user u on c.userId = u.id where mt.mId=? and mt.transactionId=?";
	   
	   public static final String SELECT_TOPUP_MERCHANT_TRANSACTION = "select mt.*, m.merchantName, m.merchantId from merchantTransaction mt LEFT JOIN merchant m on mt.mId=m.id where m.isActive=1 and mt.cId = 0 "
	   			+ "and mt.mId=? and mt.createdDate between ? and ? order by mt.id desc";
	   
	   public static final String GET_MERCHANT_TRANS_BY_MT_ID = "select mt.*, m.merchantName, m.merchantId, u.firstName, u.middleName, u.lastName, c.customerId, u.mobileNumber from merchantTransaction mt "
		   		+ "LEFT JOIN merchant m on mt.mId=m.id LEFT JOIN customer c on mt.cId = c.id LEFT JOIN user u on c.userId = u.id where mt.id=?";
	   
	   public static final String GET_MERCHANT_TRANS_BY_TRANS_TYPE = "select mt.*, m.merchantName, m.merchantId, u.firstName, u.middleName, u.lastName, c.customerId, u.mobileNumber from merchantTransaction mt "
		   		+ "LEFT JOIN merchant m on mt.mId=m.id LEFT JOIN customer c on mt.cId = c.id LEFT JOIN user u on c.userId = u.id where mt.transactionId=? and mt.transactionType=?";

	   public static final String GET_TOTAL_TRAN_VAL = "SELECT * FROM merchantTransaction WHERE transactionType = 'PAY' AND STATUS ='S' and createdDate BETWEEN ? and ?";

	   public static final String SELECT_ALL_MERCHANT_TRANSACTION = "select mt.*, m.merchantName, m.merchantId, u.firstName, u.middleName, u.lastName, c.customerId, u.mobileNumber from merchantTransaction mt LEFT JOIN merchant m on mt.mId=m.id LEFT JOIN customer c on mt.cId = c.id LEFT JOIN user u on c.userId = u.id where u.isActive=1 and c.isActive=1 and m.isActive=1 and mt.createdDate between ? and ? order by mt.id DESC LIMIT 100";
	   
}
