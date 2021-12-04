package com.cratas.mpls.web.dto;

import java.util.Date;

/**
 * 
 * @author mukesh
 *
 */
public class MerchantTransactionDTO {
			
		private int id;
		private int cId;
		private int mId;
		private String transactionId;
		private String mobileNumber;
		private String walletTransactionId;
		private String transactionType;
		private String offerCode;
		private double transactionValue;
		private String indicator;
		private String status;
		private String merchantId;
		private String merchantName;
		private String customerId;
		private String firstName;
		private String middleName;
		private String lastName;
		private double walletBal;
		private Date createdDate;
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
		public String getTransactionId() {
			return transactionId;
		}
		public void setTransactionId(String transactionId) {
			this.transactionId = transactionId;
		}
		public String getWalletTransactionId() {
			return walletTransactionId;
		}
		public void setWalletTransactionId(String walletTransactionId) {
			this.walletTransactionId = walletTransactionId;
		}
		public String getTransactionType() {
			return transactionType;
		}
		public void setTransactionType(String transactionType) {
			this.transactionType = transactionType;
		}
		
		public double getTransactionValue() {
			return transactionValue;
		}
		public void setTransactionValue(double transactionValue) {
			this.transactionValue = transactionValue;
		}
		public String getIndicator() {
			return indicator;
		}
		public void setIndicator(String indicator) {
			this.indicator = indicator;
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
		public String getMobileNumber() {
			return mobileNumber;
		}
		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
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
		public String getOfferCode() {
			return offerCode;
		}
		public void setOfferCode(String offerCode) {
			this.offerCode = offerCode;
		}
		
		public double getCashbackAmt() {
			return cashbackAmt;
		}
		public void setCashbackAmt(double cashbackAmt) {
			this.cashbackAmt = cashbackAmt;
		}
		
		@Override
		public String toString() {
			return "MerchantTransactionDTO [id=" + id + ", cId=" + cId + ", mId=" + mId + ", transactionId="
					+ transactionId + ", mobileNumber=" + mobileNumber + ", walletTransactionId=" + walletTransactionId
					+ ", transactionType=" + transactionType + ", offerCode=" + offerCode + ", transactionValue="
					+ transactionValue + ", indicator=" + indicator + ", status=" + status + ", merchantId="
					+ merchantId + ", merchantName=" + merchantName + ", customerId=" + customerId + ", firstName="
					+ firstName + ", middleName=" + middleName + ", lastName=" + lastName + ", walletBal=" + walletBal
					+ ", createdDate=" + createdDate + ", createdBy=" + createdBy + ", payType=" + payType + "]";
		}
		
		
		

}
