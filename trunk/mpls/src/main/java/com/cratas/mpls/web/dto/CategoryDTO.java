package com.cratas.mpls.web.dto;

/**
 * 
 * @author bhupendra
 *
 */

public class CategoryDTO {
	
	    private int id;
	    private String categoryCode;
	    private String categoryName;
	    private String description;
	    private String menuName;
	    private String createdBy;
	    private String updatedby;
	   
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		
		public String getCategoryCode() {
			return categoryCode;
		}
		public void setCategoryCode(String categoryCode) {
			this.categoryCode = categoryCode;
		}
		public String getCategoryName() {
			return categoryName;
		}
		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
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
