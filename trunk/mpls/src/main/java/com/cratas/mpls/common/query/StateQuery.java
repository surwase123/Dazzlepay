package com.cratas.mpls.common.query;

/**
 * 
 * @author Priyanka
 *
 */

public class StateQuery {
	
		public static final String SELECT_STATE = "SELECT c.countryName,s.* FROM state s INNER JOIN country c ON s.countryId=c.id WHERE s.isActive = 1 AND s.isApproved = 1";
		
	    public static final String INSERT_STATE = "insert into state set countryId=?, id=?, stateName=?, createdBy=?";
	    
	    public static final String UPDATE_STATE = "update state set countryId=?, stateName=?, updatedBy=?, updateTimeStamp=now() where id = ?";
	    
	    public static final String DELETE_STATE = "update state set isActive = 0, updatedBy=?, updateTimeStamp=now() where id = ?";
	    
	    public static final String SELECT_STATE_BY_STATE_NAME = "SELECT s.*,c.countryName FROM state s INNER JOIN country c ON s.countryId = c.id where s.stateName=? and s.countryId=? and s.isActive = 1";
	    
	    public static final String SELECT_STATE_BY_STATE_ID = "SELECT s.*,c.countryName FROM state s INNER JOIN country c ON s.countryId = c.id where s.id=? and s.isActive = 1";
	    
	    public static final String SELECT_STATE_BY_ID = "SELECT s.*,c.countryName from state s INNER JOIN country c ON s.countryId = c.id where s.countryId = ? and s.isActive = 1 and s.isApproved = 1";

}
