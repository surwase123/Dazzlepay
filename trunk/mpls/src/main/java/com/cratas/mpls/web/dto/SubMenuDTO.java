package com.cratas.mpls.web.dto;

/**
 * 
 * @author mukesh
 *
 */
public class SubMenuDTO {
	
	    private int id;
	    private int parentMenuId;
	   	private String subMenuName;
	   	private String description;
	   	private String menuIcon;
	   	private String action;
	   	private int orderSequence;
		private int isAdd;
		private int isDelete;
		private int isUpdate;
		private int isMaskField;
	   	
		public String getSubMenuName() {
			return subMenuName;
		}
		public void setSubMenuName(String subMenuName) {
			this.subMenuName = subMenuName;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getMenuIcon() {
			return menuIcon;
		}
		public void setMenuIcon(String menuIcon) {
			this.menuIcon = menuIcon;
		}
		public String getAction() {
			return action;
		}
		public void setAction(String action) {
			this.action = action;
		}
		public int getOrderSequence() {
			return orderSequence;
		}
		public void setOrderSequence(int orderSequence) {
			this.orderSequence = orderSequence;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getParentMenuId() {
			return parentMenuId;
		}
		public void setParentMenuId(int parentMenuId) {
			this.parentMenuId = parentMenuId;
		}
		public int getIsAdd() {
			return isAdd;
		}
		public void setIsAdd(int isAdd) {
			this.isAdd = isAdd;
		}
		public int getIsDelete() {
			return isDelete;
		}
		public void setIsDelete(int isDelete) {
			this.isDelete = isDelete;
		}
		public int getIsUpdate() {
			return isUpdate;
		}
		public void setIsUpdate(int isUpdate) {
			this.isUpdate = isUpdate;
		}
		public int getIsMaskField() {
			return isMaskField;
		}
		public void setIsMaskField(int isMaskField) {
			this.isMaskField = isMaskField;
		}

}
