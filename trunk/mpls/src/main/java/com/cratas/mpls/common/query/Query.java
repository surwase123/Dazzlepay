package com.cratas.mpls.common.query;

/**
 * 
 * @author mukesh
 *
 */
public class Query {

	    public static final String INSERT_USER_TRACKING_LOG = "insert into user_history set loginId=:loginId, updateTimeStamp=now()";
	
	    public static final String INSERT_USER_TRACKING_DETAIL_LOG = "insert into user_history_detail set loginId=:loginId, requestUrl=:requestUrl, sessionId=:sessionId, source=:source, browser=:browser,"
	            + "os=:operatingSystem, ipAddress=:ipAddress";
	
	    public static final String UPDATE_USER_TRACKING_LOG = "update user_history set updateTimeStamp=now() where loginId=:loginId";
	
	    public static final String SELECT_USER_TRACKING_LOG = "select * from user_history where loginId = ?";
	
	    public static final String SAVE_USER = "insert into user set systemId=:systemId, groupId=:groupId, loginId=UPPER(:loginId), mobileNumber=:mobileNumber, domainUserName=:domainUserName, "
	            + "firstName=:firstName, middleName=:middleName, lastName=:lastName, emailId=:emailId, createdBy=:createdBy, status=:status, managerId=:managerId, userType=:userType";
	
	    public static final String UPDATE_USER = "update user set systemId=:systemId, groupId=:groupId, domainUserName=:domainUserName, "
	            + "firstName=:firstName, middleName=:middleName, lastName=:lastName, emailId=:emailId, updatedBy=:updatedBy, status=:status, managerId=:managerId, userType=:userType where id =:id";
	
	    public static final String SELECT_USER_BY_LOGIN_ID = "select * from user where loginId = ? and isActive = 1";
	
	    public static final String SELECT_USER_BY_ID = "select * from user where id = ? and isActive = 1";
	
	    public static final String UPDATE_UNSUCCESSFUL_LOGIN_ATTEMPTS = "update user set numUnsuccessfulAttempts=(numUnsuccessfulAttempts + 1), updateTimeStamp = now() "
	            + "where loginId = :loginId";
	
	    public static final String UPDATE_ACCOUNT_BLOCK = "update user set isLocked = 1, updatedBy=:updatedBy, updateTimeStamp = now() where loginId = :loginId";
	
	    public static final String CHANGE_PASSWORD = "update user set password = :password, lastPassword = :lastPassword, passwordChangedDate = now(), updatedBy=:updatedBy, updateTimeStamp = now() "
	            + ", numUnsuccessfulAttempts = 0 where loginId = :loginId";
	
	    public static final String UPDATE_LAST_LOGIN_DETAIL = "update user set lastSessionId=:lastSessionId, lastLoggedOn = now(), numUnsuccessfulAttempts=0 where loginId = :loginId";
	
	    public static final String SELECT_SYSTEM_PARAM_VALUE_BY_PARAM_NAME = "select * from sys_param where paramName = ? and isActive = 1";
	
	    public static final String SELECT_SYSTEM_PARAM_VALUE = "select * from sys_param where isActive = 1";
	
	    public static final String DELETE_USER = "update user set isActive = 0, updatedBy=? where id=?";
	
	    public static final String SELECT_USER = "select * from user where isActive = 1 and isApproved = 1 and userType = 1 and systemId=?";
	
	    public static final String SELECT_ACTIVE_USER = "select * from user where isActive = 1 and isApproved = 1 and status = 1 and userType = 1";
	
	    public static final String SELECT_USERS_BY_IDS = "select * from user where systemId=? and isActive = 1 and status = 1 and isApproved = 1 and userType = 1";
	
	    public static final String PENDING_INSTITUTION_LIST = "select * from userNotification where status = 0";
	
	    public static final String SELECT_PASSWORD_HISTORY_MAPPER = "select * from passwordHistoryDetail where isActive = 1 and loginId= ? order by id desc";
	
	    public static final String SAVE_PASSWORD_HISTORY = "insert into passwordHistoryDetail set loginId=?, password=?";
	
	    public static final String UPDATE_PASSWORD_HISTORY = "update passwordHistoryDetail set isActive = 0 where id = ?";
	
	    public static final String USER_NOTIFICATION = "select * from userNotification where checkerId = ? and status = 0";
	
	    public static final String USER_REQUEST = "select * from userNotification where creatorId = ? order by id desc";
	
	    public static final String INSERT_SYSTEM_PARAM = "insert into sys_param set paramName=:paramName,paramDisplayName=:paramDisplayName,paramValue=:paramValue,createdBy=:createdBy";
	
	    public static final String UPDATE_SYSTEM_PARAM = "update sys_param set paramName=:paramName,paramDisplayName=:paramDisplayName,paramValue=:paramValue,updatedBy=:updatedBy, updateTimeStamp=now() where id = :id";
	
	    public static final String DELETE_SYSTEM_PARAM = "update sys_param set isActive = 0, updatedBy=?, updateTimeStamp=now() where id = ?";
	
	    public static final String CHECK_USER_GROUP_TYPE = " SELECT COUNT(*) FROM `usergroup` WHERE groupID = ? AND groupType = 'merchant' ";
	    
	    public static final String UPDATE_USER_DETAILS = "update user set firstName=?, middleName=?, lastName=? where id =?";
	    
	    public static final String UPDATE_USER_PIN = "update user set userPin=?, updatedBy=?, updateTimeStamp = now() where id =?";

		public static final String UPDATE_USER_FINGER_PRINT = "update user set isEnableFingerprint=?, updatedBy=?, updateTimeStamp = now() where id =?";
		
		public static final String UNLOCK_USER = "update user set isLocked = 0, numUnsuccessfulAttempts = 0, updatedBy=? where loginId=?";
		
		public static final String SELECT_USER_BY_LOGIN_ID_MOBILE_NO = "select * from user where (loginId = ? or mobileNumber = ?) and isActive = 1";
		
		public static final String SELECT_USER_BY_MOBILE_NO = "select * from user where mobileNumber = ? and isActive = 1";
}
