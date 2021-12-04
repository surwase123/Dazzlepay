package com.cratas.mpls.web.dto;

public class ResetPasswordDTO {
	
		private int id;
		private String userType;
		private String mobileNumber;
		private MerchantDTO merchant;
		private CustomerDTO customer;
		private int isActive;
	    private String updatedBy;
	    
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getUserType() {
			return userType;
		}
		public void setUserType(String userType) {
			this.userType = userType;
		}
		public MerchantDTO getMerchant() {
			return merchant;
		}
		public void setMerchant(MerchantDTO merchant) {
			this.merchant = merchant;
		}
		public CustomerDTO getCustomer() {
			return customer;
		}
		public void setCustomer(CustomerDTO customer) {
			this.customer = customer;
		}
		public int getIsActive() {
			return isActive;
		}
		public void setIsActive(int isActive) {
			this.isActive = isActive;
		}
		public String getUpdatedBy() {
			return updatedBy;
		}
		public void setUpdatedBy(String updatedBy) {
			this.updatedBy = updatedBy;
		}
		public String getMobileNumber() {
			return mobileNumber;
		}
		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}
	    
}
