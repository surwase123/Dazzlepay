package com.cratas.mpls.web.dto;
/**
 * 
 * @author bhupendra
 *
 */
public class MerchantUserDTO {
	
		private int id;
		private int mid;
		private int userId;
		private String mobileNumber;
		private String createdBy;
	    private String updatedBy;
	    
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getMid() {
			return mid;
		}
		public void setMid(int mid) {
			this.mid = mid;
		}
		public int getUserId() {
			return userId;
		}
		public void setUserId(int userId) {
			this.userId = userId;
		}
		public String getMobileNumber() {
			return mobileNumber;
		}
		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}
		public String getCreatedBy() {
			return createdBy;
		}
		public void setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
		}
		public String getUpdatedBy() {
			return updatedBy;
		}
		public void setUpdatedBy(String updatedBy) {
			this.updatedBy = updatedBy;
		}
	    
	    
}
