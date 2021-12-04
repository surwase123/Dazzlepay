package com.cratas.mpls.web.dto;

/**
 * @author sunil
 *
 */
public class CityDTO {
		private int id;
		private int countryId;
		private int stateId;
		private String cityName;
		private String menuName;
		private String createdBy;
		private String updatedby;
		private String countryName;
		private String stateName;
		
		public int getId() {
			return id;
		}
		
		public void setId(int id) {
			this.id = id;
		}
		
		public int getCountryId() {
			return countryId;
		}
		
		public void setCountryId(int countryId) {
			this.countryId = countryId;
		}
		
		public int getStateId() {
			return stateId;
		}
		
		public void setStateId(int stateId) {
			this.stateId = stateId;
		}
		
		public String getCityName() {
			return cityName;
		}
		
		public void setCityName(String cityName) {
			this.cityName = cityName;
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
		
		public String getCountryName() {
			return countryName;
		}
		
		public void setCountryName(String countryName) {
			this.countryName = countryName;
		}
		
		public String getStateName() {
			return stateName;
		}

		public void setStateName(String stateName) {
			this.stateName = stateName;
		}

}
