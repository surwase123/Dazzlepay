package com.cratas.mpls.common.query;

/**
 * 
 * @author mukesh
 *
 */
public class CustomerQuery {
	
	   public static final String INSERT_CUSTOMER = "insert into customer set userId=:userId, customerId=:customerId, customerNumber=:customerNumber, userImage=:userImage";
	   
	   public static final String SELECT_CUSTOMER = "select c.*, u.firstName, u.middleName, u.lastName, u.emailId, u.groupId, u.loginId, u.mobileNumber, u.passwordChangedDate, u.lastLoggedOn, u.isEnableFingerprint from customer c LEFT join "
	   		+ "user u on u.id = c.userId where c.userId=? and c.isActive = 1 and u.isActive=1";
	   
	   public static final String SELECT_CUSTOMER_BY_CUSTOMER_ID = "select c.*, u.firstName, u.middleName, u.lastName, u.emailId, u.groupId, u.loginId, u.mobileNumber, u.isEnableFingerprint from customer c LEFT join user u on u.id = c.userId "
	   		+ "where c.customerId=? and c.isActive = 1 and u.isActive=1";
	   
	   public static final String SELECT_CUSTOMER_BY_MN = "select c.*, u.firstName, u.middleName, u.lastName, u.emailId, u.groupId, u.loginId, u.mobileNumber, u.isEnableFingerprint from customer c LEFT join user u on u.id = c.userId where "
		   	+ "u.mobileNumber=? and c.isActive = 1 and u.isActive=1";

	   public static final String UPDATE_CUSTOMER = "update customer c join user u on c.userId = u.id set u.loginId = ?, u.mobileNumber = ?, u.firstName = ?, u.middleName = ?, u.lastName = ?, u.emailId = ?, c.userImage = ?  where c.userId = ? "
		   		+ "and u.id = ?";
		   
	   public static final String GET_ALL_CUSTOMER = "SELECT c.*, u.firstName, u.middleName, u.lastName, u.emailId, u.groupId, u.loginId, u.mobileNumber, u.isEnableFingerprint FROM customer c LEFT join user u on u.id = c.userId "
		   		+ "WHERE c.isActive = 1 GROUP BY c.id";

	   public static final String GET_CUSTOMER_DETAIL = "select c.*, u.firstName, u.middleName, u.lastName, u.emailId, u.groupId, u.loginId, u.mobileNumber, u.passwordChangedDate, u.lastLoggedOn, u.isLocked, u.numUnsuccessfulAttempts from customer c LEFT join user u on u.id = c.userId";
	   
	   public static final String BLOCK_CUSTOMER = "update customer c join user u on c.userId = u.id set c.isActive = 0, u.isActive = 0, c.updatedBy=?, u.updatedBy=? where c.id = ?";
	   
	   public static final String UNBLOCK_CUSTOMER = "update customer c join user u on c.userId = u.id set c.isActive = 1, u.isActive = 1, c.updatedBy=?, u.updatedBy=? where c.id = ?";
	   
	   public static final String SELECT_CUSTOMER_BY_CID = "select c.*, u.firstName, u.middleName, u.lastName, u.emailId, u.groupId, u.loginId, u.mobileNumber, u.isEnableFingerprint from customer c LEFT join user u on u.id = c.userId where c.id=? and c.isActive = 1 and u.isActive=1";

	   public static final String GET_CUSTOMER_BY_EMAILID = "select c.*, u.firstName, u.middleName, u.lastName, u.emailId, u.groupId, u.loginId, u.mobileNumber, u.isEnableFingerprint from customer c LEFT join user u on u.id = c.userId where u.emailId=? and c.isActive = 1 and u.isActive=1";
	   
	   public static final String SELECT_MERCHANT_BY_CID = "SELECT DISTINCT m.merchantId, m.merchantName , m.firstName, m.lastName, m.emailId, u.mobileNumber,mm.walletBal, mm.createdDate from merchantMapping mm join merchant m on mm.mId = m.id LEFT JOIN merchantUser mu ON m.id = mu.mId LEFT JOIN user u ON u.id = mu.userId where mm.cId=? and mu.isOwner=1 and mm.isActive = 1 order by mm.createdDate desc";

	   public static final String GET_TOTAL_CUSTOMER = "SELECT * FROM customer where isActive = 1";

	   public static final String GET_ACTIVE_CUSTOMER = "SELECT DISTINCT cId FROM customerTransaction WHERE createdDate BETWEEN ? and ?";

	   public static final String GET_RECENT_CUSTOMER = "SELECT * FROM customer WHERE createdDate BETWEEN ? and ? AND isActive =1";
}
