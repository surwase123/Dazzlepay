package com.cratas.mpls.common.query;

/**
 * 
 * @author mukesh
 *
 */
public class MerchantMappingQuery {
	  
	   public static final String SAVE_MERCHANT_MAPPING = "insert into merchantMapping set cId=:cId, mId=:mId, walletId=:walletId,loyaltyCardNumber=:loyaltyCardNumber, createdBy=:createdBy, isActive=:isActive";
	   
	   public static final String IS_MERCHANT_MAPPING = "select * from merchantMapping where cId=? and mId=? and isActive = ?";
	   	   
	   public static final String SELECT_MERCHANT_MAPPING_BY_CID = "select mm.*, u.firstName, u.middleName, u.lastName, u.loginId, u.emailId, u.mobileNumber, c.customerId, m.merchantName, m.merchantId from merchantMapping mm join customer "
	   		+ "c on mm.cId = c.id join user u on c.userId = u.id join merchant m on m.id = mm.mId where mm.cId=? and mm.isActive = 1";
	   
	   public static final String SELECT_MERCHANT_MAPPING_BY_MID = "select mm.*, u.firstName, u.middleName, u.lastName, u.loginId, u.emailId, u.mobileNumber, c.customerId, mm.createdDate from merchantMapping mm join "
	   		+ "customer c on mm.cId = c.id join user u on c.userId = u.id where mm.mId=? and mm.isActive = 1 order by mm.createdDate desc";

	   public static final String DELETE_MERCHANT_MAPPING = "update merchantMapping set isActive= 0, updatedBy=? where mId=? and cId=? and isActive = 1";
	   
	   public static final String  UPDATE_CUSTOMER_WALLET_BAL_FOR_C = "update merchantMapping m join customer c on m.cId = c.id set m.walletBal = (m.walletBal + ?), c.walletBal = (c.walletBal + ?), "
	   		+ "m.updatedBy=?, c.updatedBy=? where m.cId=? and m.mId=? and m.isActive=1 and c.isActive=1";
	   
	   public static final String UPDATE_CUSTOMER_WALLET_BAL_FOR_P = "update merchantMapping m join customer c on m.cId = c.id set m.walletBal = (m.walletBal - ?), c.walletBal = (c.walletBal - ?), "
		   		+ "m.updatedBy=?, c.updatedBy=? where m.cId=? and m.mId=? and m.isActive=1 and c.isActive=1";
	   
	   public static final String SELECT_CUSTOMER_BY_MID = "select mm.*, u.firstName, u.middleName, u.lastName, u.loginId, u.emailId, u.mobileNumber, c.customerId, c.createdDate from merchantMapping mm join "
		   		+ "customer c on mm.cId = c.id join user u on c.userId = u.id where mm.mId=? and mm.createdDate between ? and ? and mm.isActive = ? order by mm.createdDate desc";
	   
	   public static final String SELECT_CUSTOMER_TOKEN_BY_MID = "select nt.token from merchantMapping mm join customer c on mm.cId = c.id join user u on c.userId = u.id join appNotification nt on nt.cId = c.id "
	            + "where mm.mId=1 and mm.isActive = 1 and nt.isActive = 1 order by mm.createdDate desc";
	   
	   public static final String UPDATE_MERCHANT_WALLET_BAL_FOR_C = "update merchant set walletBal = (walletBal + ?), updatedBy=? where id=? and isActive=1";
		   
	   public static final String UPDATE_MERCHANT_WALLET_BAL_FOR_P = "update merchant set walletBal = (walletBal - ?), updatedBy=? where id=? and isActive=1";

	   public static final String SELECT_CUSTOMER_BY_MN = "select mm.*, u.firstName, u.middleName, u.lastName, u.loginId, u.emailId, u.mobileNumber, c.customerId, mm.createdDate from merchantMapping mm join customer c on mm.cId = c.id join user u on c.userId = u.id where mm.mId=? and u.mobileNumber = ? and mm.isActive = 1 order by mm.createdDate desc";

	   public static final String UPDATE_MERCHANT_MAPPING = "update merchantMapping set isActive = 1 where mId = ? and cId = ? AND isActive != 2";
	  
	   public static final String UPDATE_MERCHANT_MAPPING_WITH_LOYALTYCARDNO = "update merchantMapping set isActive = ?,loyaltyCardNumber=? where mId = ? and cId = ? AND isActive != 2";

	   public static final String GET_CUSTOMER_BAL = "SELECT * FROM merchantMapping WHERE isActive = 1";

	   public static final String DELETE_MERCHANT_MAPPING_REQUEST = "update merchantMapping set isActive = 2 where mId = ? and cId = ?";
}
