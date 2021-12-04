package com.cratas.mpls.common.query;

/**
 * 
 * @author mukesh
 *
 */
public class VerificationLogQuery {

	   public static final String INSERT_VERIFICATION_LOG = "insert into verificationLog(emailId,mobileNumber,verificationCode,userType) values(?, ?, ?, ?)";
	   
	   public static final String UPDATE_VERIFICATION_LOG = "update verificationLog set isVerified = 1 where id=?";
	   
	   public static final String SELECT_VERIFICATION_LOG = "select * from verificationLog where id=?";
}
