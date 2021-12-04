package com.cratas.mpls.common.query;

public class LoyaltyCardQuery {
	
	  public static final String INSERT_LOYALTYNUMBERS = "insert into loyaltynumbers(loyaltynumber,genrationid) values(?,?)";
	  
	  public static final String ALLOCATE_LOYALTYNUMBERS_TO_MERCHANT= "update  loyaltynumbers set mId=?,requestId=?,status=?,isActive=? where id=? ";
	  
	  public static final String CHECK_LOYALTYNUMBER = "Select loyaltynumber from loyaltynumbers where loyaltynumber=?";
	  public static final String INSERT_LOYALTYNUMBERFILE = "insert into loyaltyNumberGenrationRequest(quantityofnumbers) values(?)";
	  public static final String UPDATE_LOYALTYFILESTATUS = "update  loyaltyNumberGenrationRequest set status=?,reason=? where  id=?";
	  public static final String GET_LOYALTYNUMBERS="select loyaltynumber from  loyaltynumbers";
	  public static final String GET_ALL_LOYALTYNUMBER="select  * from loyaltynumbers  ORDER BY loyaltynumber";
	  
	  public static final String GET_LOYALTYNUMBERs_FOR_ALLOCATION="select * from loyaltynumbers  where status=? and isActive=? ORDER BY loyaltynumber limit ?";
	  
	  public static final String GET_LOYALTYNUMBERS_BY_MID_AND_REQUESTID="select * from loyaltynumbers  where status=? and mId=? and requestId=? ORDER BY loyaltynumber ";
	  
	  public static final String GET_LAST_LOYALTYNUMBER="select  * from loyaltynumbers ORDER BY loyaltynumber DESC limit 1";
	  
	  public static final String GET_LOYALTY_NUMBER_ALL_REQUEST = "select * from loyaltyNumberGenrationRequest ";
	  public static final String GET_LOYALTY_NUMBER_BY_ID= "select * from loyaltynumbers where genrationid=? ORDER BY loyaltynumber";
	  	  
	  public static final String GET_ALL_ASSIGNED_LOYALTY_NUMBER= "select * from loyaltynumbers where (status='AL' or status='CA')  ORDER BY loyaltynumber";
	  
	  public static final String GET_ALL_ASSIGNED_LOYALTY_NUMBER_BY_MID="select * from loyaltynumbers where mId=? and status='CA'  ORDER BY loyaltynumber";

	  public static final String GET_ALL_AVAILABLE_LOYALTY_NUMBER_BY_MID="select * from loyaltynumbers where mId=? and (status='AL' or status='CA') ORDER BY loyaltynumber";

	  public static final String UPDATE_LOYALTYCARDNUMBER="update loyaltynumbers set cId=? ,status=?,isActive=? where mId=? and loyaltynumber=?";
	  
	  public static final String VALIDATE_LOYALTY_NUMBER="select * from  loyaltynumbers where loyaltynumber=? ";
	  
	  public static final String VALIDATE_LOYALTY_NUMBER_WITH_CID="select * from  loyaltynumbers where cId=? ";
	  
	  public static final String ACTIVATE_LOYALTYCARDNUMBER="update loyaltynumbers set isActive=? where mId=? and cId=?  ";
	  
	  public static final String BLOCK_LOYALTYCARDNUMBER="update loyaltynumbers set isActive=?,status=? where mId=? and cId=?  and isActive=1 ";
	  
	  public static final String GET_NOT_ALLOCATED_LOYALTYCARDNUMBERS="select * from loyaltynumbers where   status='NA'  ORDER BY loyaltynumber";
}

