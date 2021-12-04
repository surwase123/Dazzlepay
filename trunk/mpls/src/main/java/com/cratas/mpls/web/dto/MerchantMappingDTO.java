package com.cratas.mpls.web.dto;

import java.util.Date;

/**
 * 
 * @author mukesh
 *
 */
public class MerchantMappingDTO {
	
	    private String walletId;
	    private String firstName;
	    private String middleName;
	    private String lastName;
	    private String mobileNumber;
	    private String merchantName;
	    private String merchantId;
	    private String loyaltyCardNumber;
	    private String loginId;
	    private String emailId;
	    private String customerId;
	    private int mId;
	    private int cId;
	    private int isActive;
	    private double walletBal;
	    private String createdBy;
	    private Date createdDate;
	   
		public String getLoyaltyCardNumber() {
			return loyaltyCardNumber;
		}
		public void setLoyaltyCardNumber(String loyaltyCardNumber) {
			this.loyaltyCardNumber = loyaltyCardNumber;
		}
		public String getWalletId() {
			return walletId;
		}
		public void setWalletId(String walletId) {
			this.walletId = walletId;
		}
		public int getmId() {
			return mId;
		}
		public void setmId(int mId) {
			this.mId = mId;
		}
		public int getcId() {
			return cId;
		}
		public void setcId(int cId) {
			this.cId = cId;
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
		public String getMobileNumber() {
			return mobileNumber;
		}
		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}
		public String getLoginId() {
			return loginId;
		}
		public void setLoginId(String loginId) {
			this.loginId = loginId;
		}
		public String getCustomerId() {
			return customerId;
		}
		public void setCustomerId(String customerId) {
			this.customerId = customerId;
		}
		public String getMerchantName() {
			return merchantName;
		}
		public void setMerchantName(String merchantName) {
			this.merchantName = merchantName;
		}
		public String getMerchantId() {
			return merchantId;
		}
		public void setMerchantId(String merchantId) {
			this.merchantId = merchantId;
		}
		public String getEmailId() {
			return emailId;
		}
		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}
		public Date getCreatedDate() {
			return createdDate;
		}
		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}
		public int getIsActive() {
			return isActive;
		}
		public void setIsActive(int isActive) {
			this.isActive = isActive;
		}
		
	   
}
