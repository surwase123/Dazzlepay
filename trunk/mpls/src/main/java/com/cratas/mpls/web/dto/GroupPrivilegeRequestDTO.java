package com.cratas.mpls.web.dto;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author mukesh
 *
 */
public class GroupPrivilegeRequestDTO {
	
	    private String groupId;
	    private List<String> groupPrivilegeMenu;
	    private String screenMenuName;
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
		public List<String> getGroupPrivilegeMenu() {
			return groupPrivilegeMenu;
		}
		public void setGroupPrivilegeMenu(List<String> groupPrivilegeMenu) {
			this.groupPrivilegeMenu = groupPrivilegeMenu;
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
		public String getScreenMenuName() {
			return screenMenuName;
		}
		public void setScreenMenuName(String screenMenuName) {
			this.screenMenuName = screenMenuName;
		}
		
		
		

}
