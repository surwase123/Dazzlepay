package com.cratas.mpls.web.dto;

/**
 * 
 * @author mukesh
 *
 */
public class PasswordHistoryDTO {
	
	    private int id;
	    private String loginId;
	    private String password;
	   
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
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
	   
}
