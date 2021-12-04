package com.cratas.mpls.web.dto;

import org.springframework.stereotype.Component;

@Component
public class LoginDTO {
	
		private String loginId;
		private String password;
		private String userPin;
		private String isSaveCredentials;
		
		public String getLoginId() {
			return loginId;
		}
		
		public void setLoginId(String loginId) {
			this.loginId = loginId;
		}
		
		public String getPassword() {
			return password;
		}
		
		public void setPassword(String password) {
			this.password = password;
		}
		
		public String getIsSaveCredentials() {
			return isSaveCredentials;
		}
		
		public void setIsSaveCredentials(String isSaveCredentials) {
			this.isSaveCredentials = isSaveCredentials;
		}
		
		public String getUserPin() {
			return userPin;
		}
		
		public void setUserPin(String userPin) {
			this.userPin = userPin;
		}
	
}
