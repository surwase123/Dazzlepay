package com.cratas.mpls.web.dto;

/**
 * 
 * @author mukesh
 *
 */
public class CurrencyDTO {
	
	    private int id;
	    private int currencyCode;
	    private String currencyCodeAlpha;
	    private String currencyName;
	    private String countryName;
	    private int exponent;
	    private String menuName;
	    private String createdBy;
	    private String updatedby;
		
		
		public String getCountryName() {
			return countryName;
		}
		public void setCountryName(String countryName) {
			this.countryName = countryName;
		}
		public int getCurrencyCode() {
			return currencyCode;
		}
		public void setCurrencyCode(int currencyCode) {
			this.currencyCode = currencyCode;
		}
		public String getCurrencyCodeAlpha() {
			return currencyCodeAlpha;
		}
		public void setCurrencyCodeAlpha(String currencyCodeAlpha) {
			this.currencyCodeAlpha = currencyCodeAlpha;
		}
		public String getCurrencyName() {
			return currencyName;
		}
		public void setCurrencyName(String currencyName) {
			this.currencyName = currencyName;
		}
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
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
		public int getExponent() {
			return exponent;
		}
		public void setExponent(int exponent) {
			this.exponent = exponent;
		}
		public String getMenuName() {
			return menuName;
		}
		public void setMenuName(String menuName) {
			this.menuName = menuName;
		}
		
		
		
	   
}
