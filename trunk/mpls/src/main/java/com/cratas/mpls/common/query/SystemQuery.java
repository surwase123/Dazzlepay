/**
 * 
 */
package com.cratas.mpls.common.query;

/**
 * @author bhupendra
 *
 */
public class SystemQuery {
	
	public static final String INSERT_SYSTEM = "insert into system set systemId=UPPER(:systemId), systemName=:systemName, addressLine1=:addressLine1, addressLine2=:addressLine2, city=:city, state=:state, "
	  		+ "postalCode=:postalCode, country=:country, firstName=:firstName, middleName=:middleName, lastName=:lastName, emailId=:emailId, phoneNumber=:phoneNumber, mobileNumber=:mobileNumber, faxNo=:faxNo, "
	  		+ "status=:status, createdBy=:createdBy";
	  
	  public static final String UPDATE_SYSTEM = "update system set systemId=UPPER(:systemId), systemName=:systemName, addressLine1=:addressLine1, addressLine2=:addressLine2, city=:city, state=:state, "
	  		+ "postalCode=:postalCode, country=:country, firstName=:firstName, middleName=:middleName, lastName=:lastName, emailId=:emailId, phoneNumber=:phoneNumber, mobileNumber=:mobileNumber, faxNo=:faxNo, "
	  		+ "status=:status, updatedBy=:updatedBy, updateTimeStamp=now() where id = :id";
	    
	  public static final String DELETE_SYSTEM = "update system set isActive = 0, updatedBy=?, updateTimeStamp=now() where id = ?";
	    
	  public static final String SELECT_SYSTEM = "select * from system where isActive = 1 and isApproved = 1";
	    
	  public static final String SELECT_SYSTEM_BY_ID = "select * from system where systemId=? and isActive = 1";
	  
	  public static final String SELECT_ACTIVE_SYSTEM = "select * from `system` where status = 1 and isActive = 1 and isApproved = 1";
}
