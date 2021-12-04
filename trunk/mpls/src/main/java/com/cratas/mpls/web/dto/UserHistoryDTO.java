package com.cratas.mpls.web.dto;

import java.util.Date;

/**
 * 
 * @author mukesh
 *
 */
public class UserHistoryDTO {

	    private String loginId;
	    private String request_url;
	    private String session_id;
	    private String source;
	    private String ip_address;
	    private String os;
	    private String browser;
	    private Date date;

		public String getRequest_url() {
			return request_url;
		}
		public void setRequest_url(String request_url) {
			this.request_url = request_url;
		}
		public String getSession_id() {
			return session_id;
		}
		public void setSession_id(String session_id) {
			this.session_id = session_id;
		}
		public String getIp_address() {
			return ip_address;
		}
		public void setIp_address(String ip_address) {
			this.ip_address = ip_address;
		}
		public String getSource() {
			return source;
		}
		public void setSource(String source) {
			this.source = source;
		}
		public String getOs() {
			return os;
		}
		public void setOs(String os) {
			this.os = os;
		}
		public String getBrowser() {
			return browser;
		}
		public void setBrowser(String browser) {
			this.browser = browser;
		}
		public String getLoginId() {
				return loginId;
		}
		public void setLoginId(String loginId) {
			this.loginId = loginId;
		}
		public Date getDate() {
			return date;
		}
		public void setDate(Date date) {
			this.date = date;
		}
		
}
