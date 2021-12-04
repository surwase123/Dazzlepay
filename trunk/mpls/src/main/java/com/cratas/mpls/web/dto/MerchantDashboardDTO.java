package com.cratas.mpls.web.dto;

/**
 * 
 * @author mukesh
 *
 */
public class MerchantDashboardDTO {
	
	    private int totalTopUp;
	    private int totalPay;
	    private int successTopUp;
	    private int successPay;
	    private double totalTopUpSuccess;
	    private double totalPaySuccess;
	    private double totalRefund;
	    private double totalMerchantTotalTopup;
	    private int totalCustomer;
	    private double totalCustomerBal;
	    private int inActiveUser;
	    private double merchantWalletBal;
		
		public int getTotalTopUp() {
			return totalTopUp;
		}
		public void setTotalTopUp(int totalTopUp) {
			this.totalTopUp = totalTopUp;
		}
		public int getTotalPay() {
			return totalPay;
		}
		public void setTotalPay(int totalPay) {
			this.totalPay = totalPay;
		}
		public int getSuccessTopUp() {
			return successTopUp;
		}
		public void setSuccessTopUp(int successTopUp) {
			this.successTopUp = successTopUp;
		}
		public int getSuccessPay() {
			return successPay;
		}
		public void setSuccessPay(int successPay) {
			this.successPay = successPay;
		}
		public double getTotalTopUpSuccess() {
			return totalTopUpSuccess;
		}
		public void setTotalTopUpSuccess(double totalTopUpSuccess) {
			this.totalTopUpSuccess = totalTopUpSuccess;
		}
		public double getTotalPaySuccess() {
			return totalPaySuccess;
		}
		public void setTotalPaySuccess(double totalPaySuccess) {
			this.totalPaySuccess = totalPaySuccess;
		}
		public double getTotalRefund() {
			return totalRefund;
		}
		public void setTotalRefund(double totalRefund) {
			this.totalRefund = totalRefund;
		}
		public double getTotalCustomerBal() {
			return totalCustomerBal;
		}
		public void setTotalCustomerBal(double totalCustomerBal) {
			this.totalCustomerBal = totalCustomerBal;
		}
		public int getTotalCustomer() {
			return totalCustomer;
		}
		public void setTotalCustomer(int totalCustomer) {
			this.totalCustomer = totalCustomer;
		}
		public int getInActiveUser() {
			return inActiveUser;
		}
		public void setInActiveUser(int inActiveUser) {
			this.inActiveUser = inActiveUser;
		}
		public double getMerchantWalletBal() {
			return merchantWalletBal;
		}
		public void setMerchantWalletBal(double merchantWalletBal) {
			this.merchantWalletBal = merchantWalletBal;
		}
		public double getTotalMerchantTotalTopup() {
			return totalMerchantTotalTopup;
		}
		public void setTotalMerchantTotalTopup(double totalMerchantTotalTopup) {
			this.totalMerchantTotalTopup = totalMerchantTotalTopup;
		}
		
		
}
