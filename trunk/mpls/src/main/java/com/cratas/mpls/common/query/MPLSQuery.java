/**
 * 
 */
package com.cratas.mpls.common.query;

/**
 * 
 * @author mukesh
 *
 */
public class MPLSQuery {
			
	    public static final String SAVE_USER_NOTIFICATION = "insert into userNotification set creatorId=:creatorId,checkerId=:checkerId,recordId=:recordId,tableName=:tableName,message=:message, menuName=:menuName";
	
	    public static final String FETCH_MENU_DETAIL = "select * from menuDetailMapping";
	
	    public static final String USER_NOTIFICATION_DETAIL = "select * from %tableName% where id = ?";
	
	    public static final String APPROVE_USER_NOTIFICATION = "update userNotification set comments=?,status=? where id=?";
	
	    public static final String REJECT_USER_NOTIFICATION = "insert into userNotification set creatorId=:creatorId,menuName=:menuName,checkerId=:checkerId,recordId=:recordId,tableName=:tableName,"
	            + "message=:message,status=:status,comments=:comments";
	
	    public static final String UPDATE_RECORD_STATUS = "update %tableName% set isApproved=? where id = ?";
}
