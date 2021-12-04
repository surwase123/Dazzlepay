package com.cratas.mpls.web.dto;
/**
 * 
 * @author bhupendra
 *
 */
public class MerchantEmployeeDTO {

		private int id;
		private int userId;
		private int mId;
	    private String firstName;
	    private String middleName;
	    private String lastName;
	    private String loginId;
	    private String mobileNumber;
	    private String emailId;
	    private int isOwner;
	    private int isEnableFingerprint;
	    private String createdBy;
	    private String updatedBy;
	    
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getMiddleName() {
			return middleName;
		}
		public void setMiddleName(String middleName) {
			this.middleName = middleName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getLoginId() {
			return loginId;
		}
		public void setLoginId(String loginId) {
			this.loginId = loginId;
		}
		public String getMobileNumber() {
			return mobileNumber;
		}
		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}
		public String getEmailId() {
			return emailId;
		}
		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}
		public String getCreatedBy() {
			return createdBy;
		}
		public void setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
		}
		public int getmId() {
			return mId;
		}
		public void setmId(int mId) {
			this.mId = mId;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getUpdatedBy() {
			return updatedBy;
		}
		public void setUpdatedBy(String updatedBy) {
			this.updatedBy = updatedBy;
		}
		public int getIsOwner() {
			return isOwner;
		}
		public void setIsOwner(int isOwner) {
			this.isOwner = isOwner;
		}
		public int getIsEnableFingerprint() {
			return isEnableFingerprint;
		}
		public void setIsEnableFingerprint(int isEnableFingerprint) {
			this.isEnableFingerprint = isEnableFingerprint;
		}
		
	    
}
