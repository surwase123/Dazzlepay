package com.cratas.mpls.web.dto;

/**
 * @author bhupendra
 *
 */
public class AdminDashboardDTO {

		private int totalMerchant;
	    private int activeMerchantCount;
	    private int totalCustomer;
	    private int activeCustomerCount;
	    private int newMerchant;
	    private int newCustomer;
	    private double merchantWalletBal;
	    private double customerBalance;
	    private double totalTransValue;
	    
		public int getTotalMerchant() {
			return totalMerchant;
		}
		public void setTotalMerchant(int totalMerchant) {
			this.totalMerchant = totalMerchant;
		}
		public int getActiveMerchantCount() {
			return activeMerchantCount;
		}
		public void setActiveMerchantCount(int activeMerchantCount) {
			this.activeMerchantCount = activeMerchantCount;
		}
		public int getTotalCustomer() {
			return totalCustomer;
		}
		public void setTotalCustomer(int totalCustomer) {
			this.totalCustomer = totalCustomer;
		}
		public int getActiveCustomerCount() {
			return activeCustomerCount;
		}
		public void setActiveCustomerCount(int activeCustomerCount) {
			this.activeCustomerCount = activeCustomerCount;
		}
		public int getNewMerchant() {
			return newMerchant;
		}
		public void setNewMerchant(int newMerchant) {
			this.newMerchant = newMerchant;
		}
		public int getNewCustomer() {
			return newCustomer;
		}
		public void setNewCustomer(int newCustomer) {
			this.newCustomer = newCustomer;
		}
		public double getMerchantWalletBal() {
			return merchantWalletBal;
		}
		public void setMerchantWalletBal(double merchantWalletBal) {
			this.merchantWalletBal = merchantWalletBal;
		}
		public double getCustomerBalance() {
			return customerBalance;
		}
		public void setCustomerBalance(double customerBalance) {
			this.customerBalance = customerBalance;
		}
		public double getTotalTransValue() {
			return totalTransValue;
		}
		public void setTotalTransValue(double totalTransValue) {
			this.totalTransValue = totalTransValue;
		}
	    
	    
}
