package com.cratas.mpls.web.dto;

import java.util.Date;

/**
 * 
 * @author mukesh
 *
 */
public class UserRoleDTO {
	
	    private String groupId;
	    private String groupName;
	    private int menuId;
	    private int subMenuId;
	    private String menuName;
	    private String subMenuName;
	    private int isAdd;
	    private int isUpdate;
	    private int isDelete;
	    private int isMaskField;
	    private int isEditable;
	    private String createdBy;
	    private String updatedBy;
	    private Date insertTimeStamp;
	    private Date updateTimeStamp;
	   
		public String getGroupId() {
			return groupId;
		}
		public void setGroupId(String groupId) {
			this.groupId = groupId;
		}
		public int getMenuId() {
			return menuId;
		}
		public void setMenuId(int menuId) {
			this.menuId = menuId;
		}
		public int getSubMenuId() {
			return subMenuId;
		}
		public void setSubMenuId(int subMenuId) {
			this.subMenuId = subMenuId;
		}
		public int getIsAdd() {
			return isAdd;
		}
		public void setIsAdd(int isAdd) {
			this.isAdd = isAdd;
		}
		public int getIsUpdate() {
			return isUpdate;
		}
		public void setIsUpdate(int isUpdate) {
			this.isUpdate = isUpdate;
		}
		public int getIsDelete() {
			return isDelete;
		}
		public void setIsDelete(int isDelete) {
			this.isDelete = isDelete;
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
		public String getGroupName() {
			return groupName;
		}
		public void setGroupName(String groupName) {
			this.groupName = groupName;
		}
		public String getMenuName() {
			return menuName;
		}
		public void setMenuName(String menuName) {
			this.menuName = menuName;
		}
		public String getSubMenuName() {
			return subMenuName;
		}
		public void setSubMenuName(String subMenuName) {
			this.subMenuName = subMenuName;
		}
		public Date getInsertTimeStamp() {
			return insertTimeStamp;
		}
		public void setInsertTimeStamp(Date insertTimeStamp) {
			this.insertTimeStamp = insertTimeStamp;
		}
		public Date getUpdateTimeStamp() {
			return updateTimeStamp;
		}
		public void setUpdateTimeStamp(Date updateTimeStamp) {
			this.updateTimeStamp = updateTimeStamp;
		}
		public int getIsMaskField() {
			return isMaskField;
		}
		public void setIsMaskField(int isMaskField) {
			this.isMaskField = isMaskField;
		}
		public int getIsEditable() {
			return isEditable;
		}
		public void setIsEditable(int isEditable) {
			this.isEditable = isEditable;
		}
		
		
}
