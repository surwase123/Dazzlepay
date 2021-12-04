package com.cratas.mpls.web.dto;

/**
 * @author bhupendra
 *
 */
public class SearchUserDTO {

		private int id;
		private String userType;
		private String searchValue;
		private String customerSearchBy;
		private String merchantSearchBy;
		private String operation;
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
		public String getSearchValue() {
			return searchValue;
		}
		public void setSearchValue(String searchValue) {
			this.searchValue = searchValue;
		}
		
		public String getCustomerSearchBy() {
			return customerSearchBy;
		}
		public void setCustomerSearchBy(String customerSearchBy) {
			this.customerSearchBy = customerSearchBy;
		}
		public String getMerchantSearchBy() {
			return merchantSearchBy;
		}
		public void setMerchantSearchBy(String merchantSearchBy) {
			this.merchantSearchBy = merchantSearchBy;
		}
		public String getUpdatedBy() {
			return updatedBy;
		}
		public void setUpdatedBy(String updatedBy) {
			this.updatedBy = updatedBy;
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
		public String getOperation() {
			return operation;
		}
		public void setOperation(String operation) {
			this.operation = operation;
		}
		
		
}
