package com.cratas.mpls.common.query;

/**
 * 
 * @author mukesh
 *
 */
public class MerchantVersionQuery {
	
	   public static final String INSERT_MERCHANT_VERSION = "insert into merchantVersion set versionName=?, description=?, createdBy=?";
		
	   public static final String UPDATE_MERCHANT_VERSION = "update merchantVersion set description=?, updatedBy=?, updateTimeStamp=now() where id = ?";
	
	   public static final String DELETE_MERCHANT_VERSION = "update merchantVersion set isActive = 0, updatedBy=?, updateTimeStamp=now() where id = ?";
	
	   public static final String SELECT_MERCHANT_VERSION_BY_ID = "select * from merchantVersion where id=? and isActive=1";
	
	   public static final String SELECT_MERCHANT_VERSION_BY_NAME = "select * from merchantVersion where versionName=? and isActive = 1";
	   
	   public static final String SELECT_MERCHANT_VERSION = "select * from merchantVersion where isActive = 1 and isApproved = 1";

	   public static final String SELECT_MERCHANT_VERSION_BY_MID = "SELECT  mv.* from merchantVersion mv JOIN merchant m ON mv.id=m.merchantPlanId WHERE m.merchantId=?";

}
