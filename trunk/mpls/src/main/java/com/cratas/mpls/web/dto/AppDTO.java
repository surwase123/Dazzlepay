package com.cratas.mpls.web.dto;

/**
 * 
 * @author sunil
 *
 */
public class AppDTO {
	
	    private String appName;
	    private String appVersion;
	    private String whiteLogo;
	    private String darkLogo;
	    private String backgroundImage;
	    private String baseUrl;
	   
		public String getAppName() {
			return appName;
		}
		
		public void setAppName(String appName) {
			this.appName = appName;
		}
		
		public String getAppVersion() {
			return appVersion;
		}
		
		public void setAppVersion(String appVersion) {
			this.appVersion = appVersion;
		}
		
		public String getWhiteLogo() {
			return whiteLogo;
		}
		
		public void setWhiteLogo(String whiteLogo) {
			this.whiteLogo = whiteLogo;
		}
		
		public String getDarkLogo() {
			return darkLogo;
		}
		
		public void setDarkLogo(String darkLogo) {
			this.darkLogo = darkLogo;
		}
		
		public String getBackgroundImage() {
			return backgroundImage;
		}
		
		public void setBackgroundImage(String backgroundImage) {
			this.backgroundImage = backgroundImage;
		}

		public String getBaseUrl() {
			return baseUrl;
		}

		public void setBaseUrl(String baseUrl) {
			this.baseUrl = baseUrl;
		}
		
}
