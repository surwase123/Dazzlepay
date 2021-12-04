package com.cratas.mpls.common.query;

/**
 * 
 * @author mukesh
 *
 */
public class GroupQuery {
	
	
	   public static final String INSERT_GROUP = "insert into usergroup set groupId=UPPER(:groupId), systemId=:systemId, groupName=:groupName, groupType=:groupType, minPassLength=:minPassLength, maxPassLength=:maxPassLength, isAlphaPassword=:isAlphaPassword, "
	  		+ "isNumberPassword=:isNumberPassword, isSpecialSymbolPass=:isSpecialSymbolPass, passExpiryDays=:passExpiryDays, passHistoryChecks=:passHistoryChecks, maxConcurrentLogin=:maxConcurrentLogin, maxLoginRetries=:maxLoginRetries, "
	  		+ "status=:status, createdBy=:createdBy";
	   
	   
	   public static final String UPDATE_GROUP = "update usergroup set systemId=:systemId, groupName=:groupName, groupType=:groupType,  minPassLength=:minPassLength, maxPassLength=:maxPassLength, isAlphaPassword=:isAlphaPassword, "
		  		+ "isNumberPassword=:isNumberPassword, isSpecialSymbolPass=:isSpecialSymbolPass, passExpiryDays=:passExpiryDays, passHistoryChecks=:passHistoryChecks, maxConcurrentLogin=:maxConcurrentLogin, maxLoginRetries=:maxLoginRetries, "
		  		+ "status=:status, updatedBy=:updatedBy, updateTimeStamp=now() where id = :id";
	   
	   public static final String DELETE_GROUP = "update usergroup set isActive = 0, updatedBy=?, updateTimeStamp=now() where id = ?";
	    
	   public static final String SELECT_GROUP = "select * from usergroup where isActive = 1 and isApproved = 1 and systemId=?";
		   
	   public static final String SELECT_GROUP_BY_ID = "select * from usergroup where groupId=? and isActive = 1";
		  
	   public static final String SELECT_ACTIVE_GROUP = "select * from usergroup where status = 1 and isActive = 1 and isApproved = 1";
	   
	   public static final String SELECT_ACTIVE_GROUP_BY_SYSTEM = "select * from usergroup where systemId=? and status = 1 and isActive = 1 and isApproved = 1";
	   	   
	   public static final String SELECT_GROUP_TYPE = "select * from userGroupType where isActive = 1";

}
