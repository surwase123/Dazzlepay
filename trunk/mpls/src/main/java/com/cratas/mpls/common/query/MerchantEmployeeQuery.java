package com.cratas.mpls.common.query;
/**
 * 
 * @author mukesh
 *
 */
public class MerchantEmployeeQuery {

	   public static final String INSERT_MERCHANT_EMPLOYEE = "insert into merchantUser set userId=:userId, mId=:mId";
	   
	   public static final String SELECT_MERCHANT_EMPLOYEE = "SELECT m.*, u.firstName, u.middleName, u.lastName, u.emailId, u.id as userId, u.loginId, u.mobileNumber, u.isEnableFingerprint FROM merchantUser m LEFT JOIN user u ON u.id = m.userId WHERE u.id=? AND m.isActive = 1 AND u.isActive=1";
	   
	   public static final String SELECT_MERCHANT_EMPLOYEE_BY_MID = "SELECT m.*, u.firstName, u.middleName, u.lastName, u.emailId, u.groupId, u.id as userId, u.loginId, u.mobileNumber, u.isEnableFingerprint FROM merchantUser m LEFT JOIN user u ON u.id = m.userId WHERE m.mId=? AND m.isActive = 1 AND u.isActive=1";
	   
	   public static final String SELECT_MERCHANT_EMPLOYEE_BY_MN = "SELECT m.*, u.firstName, u.middleName, u.lastName, u.emailId, u.groupId, u.id as userId, u.loginId, u.mobileNumber, u.isEnableFingerprint FROM merchantUser m LEFT JOIN user u ON u.id = m.userId WHERE u.mobileNumber=? AND m.isActive = 1 AND u.isActive=1";
	   
	   public static final String DELETE_MERCHANT_EMPLOYEE = "UPDATE merchantUser m, user u SET m.isActive = 0 ,u.isActive = 0, m.updatedBy=? WHERE u.id= m.userId AND u.id = ?";
	   	   
	   public static final String UPDATE_MERCHANT_EMPLOYEE = "update merchantUser m join user u on m.userId = u.id set u.mobileNumber = ?, u.firstName = ?, u.middleName = ?, u.lastName = ?, m.updatedBy=?, u.updatedBy=?, "
	   		+ "u.emailId = ?, u.loginId = ?  where m.id = ?";
	   
	   public static final String SELECT_MERCHANT_EMPLOYEE_BY_ID = "select m.*, u.mobileNumber from merchantUser m LEFT JOIN user u ON u.id = m.userId where m.id = ? and m.isActive = 1";
	   
	   public static final String IS_MERCHANT_EMPLOYEE = "SELECT m.*, u.mobileNumber FROM merchantUser m LEFT JOIN user u ON u.id = m.userId WHERE u.mobileNumber = ?  and m.isActive = 1";
	   
	   public static final String SELECT_MERCHANT_OWNER = "SELECT m.*, u.firstName, u.middleName, u.lastName, u.emailId, u.id as userId, u.loginId, u.mobileNumber FROM merchantUser m LEFT JOIN user u ON u.id = m.userId "
	   		+ "WHERE m.mId=? AND m.isActive = 1 AND u.isActive=1 and m.isOwner = 1";
	   
	   public static final String IS_EXISTS_MERCHANT_EMPLOYEE = "SELECT m.*, u.firstName, u.middleName, u.lastName, u.emailId, u.groupId, u.id as userId, u.loginId, u.mobileNumber FROM merchantUser m LEFT JOIN user u "
			+ "ON u.id = m.userId WHERE u.mobileNumber=? and m.mId != ? AND m.isActive = 1 AND u.isActive=1";
	   
	   public static final String SELECT_MERCHANT_EMPLOYEE_BY_EMAIL_ID = "SELECT m.*, u.firstName, u.middleName, u.lastName, u.emailId, u.groupId, u.id as userId, u.loginId, u.mobileNumber FROM merchantUser m LEFT JOIN user u "
		   		+ "ON u.id = m.userId LEFT JOIN merchant mt on mt.id = m.mId WHERE (u.emailId = ? OR mt.emailId = ?) AND m.isActive = 1 AND u.isActive = 1";
	   
	   public static final String SELECT_MERCHANT_EMPLOYEE_BY_MOBILE_NUMBER = "SELECT mu.* FROM merchantUser mu LEFT JOIN user u ON u.id = mu.userId LEFT JOIN merchant m ON mu.mId = m.id WHERE u.mobileNumber = ? AND m.isActive = 1 AND"
	   		+ " mu.isActive = 1";


}
