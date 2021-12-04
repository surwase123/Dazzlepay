/**
 * 
 */
package com.cratas.mpls.web.dto;

import java.util.Date;

/**
 * @author sunil
 *
 */
public class AppNotificationDTO {

	    private int id; 
		private int mId;
		private int cId;
		private String token;
		private String deviceId;
		private byte isActive;
		private Date createdDate;
	    private Date updatedDate;
	    
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
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
		public String getToken() {
			return token;
		}
		public void setToken(String token) {
			this.token = token;
		}
		public String getDeviceId() {
			return deviceId;
		}
		public void setDeviceId(String deviceId) {
			this.deviceId = deviceId;
		}
		public byte getIsActive() {
			return isActive;
		}
		public void setIsActive(byte isActive) {
			this.isActive = isActive;
		}
		public Date getCreatedDate() {
			return createdDate;
		}
		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}
		public Date getUpdatedDate() {
			return updatedDate;
		}
		public void setUpdatedDate(Date updatedDate) {
			this.updatedDate = updatedDate;
		}
	
		
}
