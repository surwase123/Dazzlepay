package com.cratas.mpls.web.dto;

import java.util.Date;

/**
 * 
 * @author sunil
 *
 */
public class CustomerTransactionDTO {
			
		private int id;
		private int cId;
		private int mId;
		private String mobileNumber;
		private double transactionValue;
		private String status;
		private String merchantId;
		private String merchantName;
		private String customerId;
		private String offerCode;
		private String firstName;
		private String middleName;
		private String lastName;
		private Date createdDate;
		private String customerNumber;
		private String walletTransactionId;
		private String transactionId;
		private String transactionType;
		private String indicator;
		private double walletBal;
		private String createdBy;
		private String payType;
		private double cashbackAmt;
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getcId() {
			return cId;
		}
		public void setcId(int cId) {
			this.cId = cId;
		}
		public int getmId() {
			return mId;
		}
		public void setmId(int mId) {
			this.mId = mId;
		}
		public String getMobileNumber() {
			return mobileNumber;
		}
		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}
		public double getTransactionValue() {
			return transactionValue;
		}
		public void setTransactionValue(double transactionValue) {
			this.transactionValue = transactionValue;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getMerchantId() {
			return merchantId;
		}
		public void setMerchantId(String merchantId) {
			this.merchantId = merchantId;
		}
		public String getMerchantName() {
			return merchantName;
		}
		public void setMerchantName(String merchantName) {
			this.merchantName = merchantName;
		}
		public String getCustomerId() {
			return customerId;
		}
		public void setCustomerId(String customerId) {
			this.customerId = customerId;
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
		public Date getCreatedDate() {
			return createdDate;
		}
		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}
		public String getCustomerNumber() {
			return customerNumber;
		}
		public void setCustomerNumber(String customerNumber) {
			this.customerNumber = customerNumber;
		}
		public String getWalletTransactionId() {
			return walletTransactionId;
		}
		public void setWalletTransactionId(String walletTransactionId) {
			this.walletTransactionId = walletTransactionId;
		}
		public String getTransactionId() {
			return transactionId;
		}
		public void setTransactionId(String transactionId) {
			this.transactionId = transactionId;
		}
		public String getTransactionType() {
			return transactionType;
		}
		public void setTransactionType(String transactionType) {
			this.transactionType = transactionType;
		}
		public String getIndicator() {
			return indicator;
		}
		public void setIndicator(String indicator) {
			this.indicator = indicator;
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
		public String getPayType() {
			return payType;
		}
		public void setPayType(String payType) {
			this.payType = payType;
		}
		public double getCashbackAmt() {
			return cashbackAmt;
		}
		public void setCashbackAmt(double cashbackAmt) {
			this.cashbackAmt = cashbackAmt;
		}
		public String getOfferCode() {
			return offerCode;
		}
		public void setOfferCode(String offerCode) {
			this.offerCode = offerCode;
		}
		@Override
		public String toString() {
			return "CustomerTransactionDTO [id=" + id + ", cId=" + cId + ", mId=" + mId + ", mobileNumber="
					+ mobileNumber + ", transactionValue=" + transactionValue + ", status=" + status + ", merchantId="
					+ merchantId + ", merchantName=" + merchantName + ", customerId=" + customerId + ", offerCode="
					+ offerCode + ", firstName=" + firstName + ", middleName=" + middleName + ", lastName=" + lastName
					+ ", createdDate=" + createdDate + ", customerNumber=" + customerNumber + ", walletTransactionId="
					+ walletTransactionId + ", transactionId=" + transactionId + ", transactionType=" + transactionType
					+ ", indicator=" + indicator + ", walletBal=" + walletBal + ", createdBy=" + createdBy
					+ ", payType=" + payType + ", cashbackAmt=" + cashbackAmt + "]";
		}
		
		
		
}
