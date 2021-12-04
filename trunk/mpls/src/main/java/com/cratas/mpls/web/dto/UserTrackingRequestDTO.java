package com.cratas.mpls.web.dto;

/**
 * 
 * @author mukesh
 *
 */
public class UserTrackingRequestDTO {
	
	private String loginId;
	private String ipAddress;
	private String source;
	private String browser;
	private String operatingSystem;
	private String requestUrl;
	private String sessionId;
	private String urlType;
	
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getOperatingSystem() {
		return operatingSystem;
	}
	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}
	public String getRequestUrl() {
		return requestUrl;
	}
	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getUrlType() {
		return urlType;
	}
	public void setUrlType(String urlType) {
		this.urlType = urlType;
	}
	
	
	

}
