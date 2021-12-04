package com.cratas.mpls.web.dto;

import java.util.Date;

/**
 * 
 * @author mukesh
 *
 */
public class CustomerDTO {
	
		private int id;
	    private int userId;
	    private String firstName;
	    private String middleName;
	    private String lastName;
	    private String customerId;
	    private String customerNumber;
	    private String loginId;
	    private String mobileNumber;
	    private String emailId;
	    private String verifiedId;
	    private String userImage;
	    private String loyaltyCardNumber;
	    private String createdBy;
	    private double walletBal;
	    private int isActive;
	    private int isApproved;
	    private Date passwordChangedDate;
	    private Date lastLoggedOn;
	    private int isEnableFingerprint;
	    private int isLocked;
	    private int numUnsuccessfulAttempts;
	    private String request;
	    private int mId;
	    
		public String getLoyaltyCardNumber() {
			return loyaltyCardNumber;
		}
		public void setLoyaltyCardNumber(String loyaltyCardNumber) {
			this.loyaltyCardNumber = loyaltyCardNumber;
		}
		public String getUserImage() {
			return userImage;
		}
		public void setUserImage(String userImage) {
			this.userImage = userImage;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public String getCustomerId() {
			return customerId;
		}
		public void setCustomerId(String customerId) {
			this.customerId = customerId;
		}
		public String getCustomerNumber() {
			return customerNumber;
		}
		public void setCustomerNumber(String customerNumber) {
			this.customerNumber = customerNumber;
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
		public double getWalletBal() {
			   return walletBal;
		}
		public void setWalletBal(double walletBal) {
			   this.walletBal = walletBal;
		}
		public String getCreatedBy() {
			return createdBy;
		}
		public void setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
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
		public String getVerifiedId() {
			return verifiedId;
		}
		public void setVerifiedId(String verifiedId) {
			this.verifiedId = verifiedId;
		}
		public Date getPasswordChangedDate() {
			return passwordChangedDate;
		}
		public void setPasswordChangedDate(Date passwordChangedDate) {
			this.passwordChangedDate = passwordChangedDate;
		}
		public Date getLastLoggedOn() {
			return lastLoggedOn;
		}
		public void setLastLoggedOn(Date lastLoggedOn) {
			this.lastLoggedOn = lastLoggedOn;
		}
		public int getIsActive() {
			return isActive;
		}
		public void setIsActive(int isActive) {
			this.isActive = isActive;
		}
		public int getIsEnableFingerprint() {
			return isEnableFingerprint;
		}
		public void setIsEnableFingerprint(int isEnableFingerprint) {
			this.isEnableFingerprint = isEnableFingerprint;
		}
		public int getIsLocked() {
			return isLocked;
		}
		public void setIsLocked(int isLocked) {
			this.isLocked = isLocked;
		}
		public int getNumUnsuccessfulAttempts() {
			return numUnsuccessfulAttempts;
		}
		public void setNumUnsuccessfulAttempts(int numUnsuccessfulAttempts) {
			this.numUnsuccessfulAttempts = numUnsuccessfulAttempts;
		}
		public int getIsApproved() {
			return isApproved;
		}
		public void setIsApproved(int isApproved) {
			this.isApproved = isApproved;
		}
		public String getRequest() {
			return request;
		}
		public void setRequest(String request) {
			this.request = request;
		}
		public void setmId(int mId) {
			this.mId = mId;
		}
		public int getmId() {
			return mId;
		}
}
