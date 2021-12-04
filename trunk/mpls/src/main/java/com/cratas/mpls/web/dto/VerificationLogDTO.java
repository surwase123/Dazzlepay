package com.cratas.mpls.web.dto;

/**
 * 
 * @author mukesh
 *
 */
public class VerificationLogDTO {
	
	    private String id;
	    private String emailId;
	    private String userType;
	    private String mobileNumber;
	    private String verificationCode;
	    private int isVerified;
	    
		public String getEmailId() {
			return emailId;
		}
		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}
		public String getMobileNumber() {
			return mobileNumber;
		}
		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}
		public int getIsVerified() {
			return isVerified;
		}
		public void setIsVerified(int isVerified) {
			this.isVerified = isVerified;
		}
		public String getVerificationCode() {
			return verificationCode;
		}
		public void setVerificationCode(String verificationCode) {
			this.verificationCode = verificationCode;
		}
		public String getUserType() {
			return userType;
		}
		public void setUserType(String userType) {
			this.userType = userType;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		
		

}
