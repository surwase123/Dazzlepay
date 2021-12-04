package com.cratas.mpls.web.dto;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author mukesh
 *
 */
public class MenuDTO {
	
	   	private int menuId;
	   	private String menuName;
	   	private String description;
	   	private String menuIcon;
	   	private String action;
	   	private int orderSequence;
	   	private int isAdd;
		private int isDelete;
		private int isUpdate;
		private int isMaskField;
	   	private List<SubMenuDTO> subMenuList = new LinkedList<>();
		private List<SubMenuDTO> userRoleMenuList = new LinkedList<>();
	   
		public int getMenuId() {
			return menuId;
		}
		public void setMenuId(int menuId) {
			this.menuId = menuId;
		}
		public String getMenuName() {
			return menuName;
		}
		public void setMenuName(String menuName) {
			this.menuName = menuName;
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
		
		public List<SubMenuDTO> getSubMenuList() {
			   return subMenuList;
		}
		public void setSubMenuList(List<SubMenuDTO> subMenuList) {
			this.subMenuList = subMenuList;
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
		public List<SubMenuDTO> getUserRoleMenuList() {
			return userRoleMenuList;
		}
		public void setUserRoleMenuList(List<SubMenuDTO> userRoleMenuList) {
			   this.userRoleMenuList = userRoleMenuList;
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
