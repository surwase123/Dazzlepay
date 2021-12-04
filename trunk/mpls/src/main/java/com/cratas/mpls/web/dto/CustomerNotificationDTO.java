package com.cratas.mpls.web.dto;

/**
 * @author bhupendra
 *
 */
public class CustomerNotificationDTO {

		public int id;
		public int cId;
		public int mId;
		private double amount;
		private String transactionType;
		private String status;
		private String createdBy;
	    private String updatedby;
	    
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
		public double getAmount() {
			return amount;
		}
		public void setAmount(double amount) {
			this.amount = amount;
		}
		public String getTransactionType() {
			return transactionType;
		}
		public void setTransactionType(String transactionType) {
			this.transactionType = transactionType;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getCreatedBy() {
			return createdBy;
		}
		public void setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
		}
		public String getUpdatedby() {
			return updatedby;
		}
		public void setUpdatedby(String updatedby) {
			this.updatedby = updatedby;
		}
	    
}
