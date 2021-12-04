package com.cratas.mpls.web.dto;

import java.util.Date;

/**
 * 
 * @author sunil
 *
 */
public class ApiTokenDTO {

		private int id;
		private String deviceId;
		private String apiKey;
		private String token;
		private byte isActive;
		private Date createdDate;
		private Date updatedDate;
		
		public int getId() {
			return id;
		}
		
		public void setId(int id) {
			this.id = id;
		}
		
		public String getDeviceId() {
			return deviceId;
		}
		
		public void setDeviceId(String deviceId) {
			this.deviceId = deviceId;
		}
		
		public String getApiKey() {
			return apiKey;
		}

		public void setApiKey(String apiKey) {
			this.apiKey = apiKey;
		}

		public String getToken() {
			return token;
		}
		
		public void setToken(String token) {
			this.token = token;
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
