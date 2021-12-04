package com.cratas.mpls.web.dto;

/**
 * 
 * @author Priyanka
 *
 */

public class MerchantPlanDTO {
	
	    private int id;
	    private String versionName;
	    private String description;
	    private int isActive;
	    private String createdBy;
	    private String updatedBy;
	    
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getVersionName() {
			return versionName;
		}
		public void setVersionName(String versionName) {
			this.versionName = versionName;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public int getIsActive() {
			return isActive;
		}
		public void setIsActive(int isActive) {
			this.isActive = isActive;
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
