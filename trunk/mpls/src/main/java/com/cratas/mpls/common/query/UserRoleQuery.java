package com.cratas.mpls.common.query;

/**
 * 
 * @author mukesh
 *
 */
public class UserRoleQuery {
	
	   public static final String INSERT_USER_GROUP_ROLE = "insert into userRole set groupId=:groupId, menuId=:menuId, subMenuId=:subMenuId, isAdd=:isAdd, isDelete=:isDelete, isUpdate=:isUpdate, isMaskField=:isMaskField, createdBy=:createdBy, updatedBy=:updatedBy, "
	   		                                         + "updateTimeStamp=:updateTimeStamp, insertTimeStamp=:insertTimeStamp";
	   
	   public static final String DELETE_USER_GROUP_ROLE = "update userRole set isActive = 0, updatedBy=?, updateTimeStamp=now() where groupId = ?";
	    
	   public static final String SELECT_USER_GROUP_ROLE = "select distinct R.*, G.groupName as groupName, G.isEditable from userRole R, usergroup G where R.groupId = G.groupId and R.isActive = 1 and R.isApproved = 1 and G.systemId=?";
		   
	   public static final String SELECT_USER_GROUP_ROLE_BY_ID = "select distinct R.*, G.groupName as groupName, G.isEditable from userRole R, usergroup G where R.groupId = G.groupId and R.groupId=? and R.isActive = 1";
	   
	   public static final String DELETE_USER_PRIVILEGE = "delete from userRole where groupId = ?";
		  
}
