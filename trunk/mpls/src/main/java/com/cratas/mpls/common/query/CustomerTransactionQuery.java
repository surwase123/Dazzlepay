package com.cratas.mpls.common.query;

public class CustomerTransactionQuery {

	   public static final String GET_CUSTOMER_BY_TXNID = "SELECT * FROM customerTransaction where transactionId = ?";
	   
	   public static final String GET_CUSTOMER_WALLET_BY_MERCHANT = "select * from merchantMapping where cId=? and mId = ? and isActive=1";
	   
	   public static final String INSERT_CUSTOMER_TRANSACTION = "insert into customerTransaction set cId=:cId, mId =:mId, walletTransactionId=:walletTransactionId, transactionId=:transactionId, transactionType=:transactionType, "
	   		+ "transactionValue=:transactionValue, indicator=:indicator, status=:status, createdBy=:createdBy, payType=:payType, offerCode=:offerCode";
	   
	   public static final String INSERT_MERCHANT_TRANSACTION = "insert into merchantTransaction set cId=:cId, mId =:mId, walletTransactionId=:walletTransactionId, transactionId=:transactionId, transactionType=:transactionType, "
		   		+ "transactionValue=:transactionValue, indicator=:indicator, status=:status, createdBy=:createdBy, payType=:payType, offerCode=:offerCode";	   
	   
	   public static final String SELECT_CUSTOMER_TRANSACTION = "select ct.*, m.merchantName, m.merchantId, u.firstName, u.middleName, u.lastName, c.customerId, u.mobileNumber from customerTransaction ct "
		   		+ "LEFT JOIN merchant m on ct.mId=m.id LEFT JOIN customer c on ct.cId = c.id LEFT JOIN user u on c.userId = u.id where u.isActive=1 and c.isActive=1 and m.isActive=1 and ct.cId=? and ct.createdDate "
		   		+ "between ? and ? order by ct.id desc";
	   
	   public static final String SELECT_TOP_CUSTOMER_TRANSACTION = "SELECT ct.*, m.merchantName, m.merchantId, u.firstName, u.middleName, u.lastName, c.customerId, u.mobileNumber FROM customerTransaction "
	   		+ "ct LEFT JOIN merchant m ON ct.mId=m.id LEFT JOIN customer c ON ct.cId = c.id LEFT JOIN user u ON c.userId = u.id WHERE u.isActive=1 AND c.isActive=1 AND m.isActive=1 AND u.id=? ORDER BY"
	   		+ " ct.createdBy desc LIMIT 10";

	   public static final String GET_CUSTOMER_TRANS_BY_TXNID = "select ct.*, m.merchantName, m.merchantId, u.firstName, u.middleName, u.lastName, c.customerId, u.mobileNumber from customerTransaction ct "
		   		+ "LEFT JOIN merchant m on ct.mId=m.id LEFT JOIN customer c on ct.cId = c.id LEFT JOIN user u on c.userId = u.id where ct.transactionId=?";
	   
	   public static final String GET_CUSTOMER_TRANS_BY_CT_ID = "select ct.*, m.merchantName, m.merchantId, u.firstName, u.middleName, u.lastName, c.customerId, u.mobileNumber from customerTransaction ct "
		   		+ "LEFT JOIN merchant m on ct.mId=m.id LEFT JOIN customer c on ct.cId = c.id LEFT JOIN user u on c.userId = u.id where ct.id=?";
	   
	   public static final String GET_CUSTOMER_TRANS_BY_TRANS_TYPE = "select ct.*, m.merchantName, m.merchantId, u.firstName, u.middleName, u.lastName, c.customerId,u.mobileNumber from customerTransaction ct "
		   		+ "LEFT JOIN merchant m on ct.mId=m.id LEFT JOIN customer c on ct.cId = c.id LEFT JOIN user u on c.userId = u.id where ct.transactionId=? and ct.transactionType=?";
	   	   
	   public static final String UPDATE_MERCHANT_TRANSACTION = "update merchantTransaction set status = ? where mId = ? and transactionId=? and transactionType != ?";
	   
	   public static final String UPDATE_CUSTOMER_TRANSACTION = "update customerTransaction set status = ? where cId = ? and transactionId=? and transactionType != ?";
	   
	   public static final String INSERT_CUSTOMER_NOTIFICATION = "insert into customerNotification set cId=?, mId=?, amount=?, transactionType=?, createdBy=?";
	   
	   public static final String UPDATE_CUSTOMER_NOTIFICATION = "update customerNotification set status=?, updatedBy=? where id=?";
	   
	   public static final String SELECT_CUSTOMER_TRANSACTION_BY_MERCHANT = "select ct.*, m.merchantName, m.merchantId, u.firstName, u.middleName, u.lastName, c.customerId, u.mobileNumber from customerTransaction ct LEFT JOIN merchant m on ct.mId=m.id LEFT JOIN customer c on ct.cId = c.id LEFT JOIN user u on c.userId = u.id where u.isActive=1 and c.isActive=1 and m.isActive=1 and ct.cId=? and ct.mId=? and ct.createdDate between ? and ? order by ct.id desc";

	   public static final String REFUND_MONEY_BY_MERCHANT = "SELECT * FROM customerNotification WHERE transactionType = ? and STATUS = ? and mId =? and createdDate between ? and ?";

	   public static final String GET_CUSTOMER_NOTIFICATION = "select * from customerNotification where id = ?";
}
