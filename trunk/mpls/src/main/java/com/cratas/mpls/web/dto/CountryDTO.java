package com.cratas.mpls.web.dto;

/**
 * 
 * @author bhupendra
 *
 */
public class CountryDTO {
	
		private int id;
		private int countryCode;
		private String countryCodeAlpha;
		private String countryCode2Char;
		private String countryName;
		private String menuName;
		private String createdBy;
	    private String updatedby;
	    
		public String getCountryCode2Char() {
			return countryCode2Char;
		}
		public void setCountryCode2Char(String countryCode2Char) {
			this.countryCode2Char = countryCode2Char;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		
		public int getCountryCode() {
			return countryCode;
		}
		public void setCountryCode(int countryCode) {
			this.countryCode = countryCode;
		}
		public String getCountryCodeAlpha() {
			return countryCodeAlpha;
		}
		public void setCountryCodeAlpha(String countryCodeAlpha) {
			this.countryCodeAlpha = countryCodeAlpha;
		}
		public String getCountryName() {
			return countryName;
		}
		public void setCountryName(String countryName) {
			this.countryName = countryName;
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
		public String getMenuName() {
			return menuName;
		}
		public void setMenuName(String menuName) {
			this.menuName = menuName;
		}    
}
