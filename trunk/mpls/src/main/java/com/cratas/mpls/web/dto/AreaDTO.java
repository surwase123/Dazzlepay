package com.cratas.mpls.web.dto;
/**
 * 
 * @author bhupendra
 *
 */

public class AreaDTO {
	
		private int id;
		private String areaCode;
		private String areaName;
		private String pincode;
		private String latitude;
		private String longitude;
		private int cityId;
		private int stateId;
		private int countryId;
		private String cityName;
		private String stateName;
		private String countryName;
		private String menuName;
	    private String createdBy;
	    private String updatedby;
	    
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getAreaName() {
			return areaName;
		}
		public void setAreaName(String areaName) {
			this.areaName = areaName;
		}
		
		public String getPincode() {
			return pincode;
		}
		public void setPincode(String pincode) {
			this.pincode = pincode;
		}
		public String getLatitude() {
			return latitude;
		}
		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}
		public String getLongitude() {
			return longitude;
		}
		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}
		
		public int getCityId() {
			return cityId;
		}
		public void setCityId(int cityId) {
			this.cityId = cityId;
		}
		public int getStateId() {
			return stateId;
		}
		public void setStateId(int stateId) {
			this.stateId = stateId;
		}
		public int getCountryId() {
			return countryId;
		}
		public void setCountryId(int countryId) {
			this.countryId = countryId;
		}
		public String getCityName() {
			return cityName;
		}
		public void setCityName(String cityName) {
			this.cityName = cityName;
		}
		public String getStateName() {
			return stateName;
		}
		public void setStateName(String stateName) {
			this.stateName = stateName;
		}
		public String getCountryName() {
			return countryName;
		}
		public void setCountryName(String countryName) {
			this.countryName = countryName;
		}
		public String getMenuName() {
			return menuName;
		}
		public void setMenuName(String menuName) {
			this.menuName = menuName;
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
		public String getAreaCode() {
			return areaCode;
		}
		public void setAreaCode(String areaCode) {
			this.areaCode = areaCode;
		}
	    
	    
}
