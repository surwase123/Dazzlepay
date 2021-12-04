package com.cratas.mpls.dao.dto;

/**
 * 
 * @author Priyanka
 *
 */


public class StateDTO {
	
		private int id;
		private String stateName;
		private int countryId;
		private String createdBy;
		private String updatedBy;
		private String menuName;
		private String countryName;
		 
		
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
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getStateName() {
			return stateName;
		}
		public void setStateName(String stateName) {
			this.stateName = stateName;
		}
		public int getCountryId() {
			return countryId;
		}
		public void setCountryId(int countryId) {
			this.countryId = countryId;
		}
		public String getCreatedBy() {
			return createdBy;
		}
		public void setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
		}
		public String getUpdatedBy() {
			return updatedBy;
		}
		public void setUpdatedBy(String updatedBy) {
			this.updatedBy = updatedBy;
		}
		
}
