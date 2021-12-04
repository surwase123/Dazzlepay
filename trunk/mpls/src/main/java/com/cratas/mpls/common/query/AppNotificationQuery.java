package com.cratas.mpls.common.query;

/**
 * 
 * @author sunil
 *
 */
public class AppNotificationQuery {
	
	   public static final String INSERT_APP_NOTIFICATION = "insert into appNotification set cId=:cId, mId=:mId, deviceId=:deviceId, token=:token, createdDate = now()";
	   
	   public static final String UPDATE_APP_NOTIFICATION = "update appNotification set deviceId = ?, token = ?, isActive = 1, updatedDate = now() where ";

	   public static final String SELECT_APP_NOTIFICATION = "select * from appNotification where ";

	   public static final String SELECT_APP_NOTIFICATION_BY_DEVICE_ID = "select * from appNotification where isActive = 1 and deviceId = ?";

	   public static final String SELECT_MERCHANT_TOKEN_BY_MID = "select token from appNotification where isActive = 1 and mId = ?";

	   public static final String UPDATE_INACTIVE_DEVICE = "update appNotification set isActive = 0, updatedDate = now() where deviceId = ?";
	   
	   public static final String SELECT_CUSTOMER_TOKEN_BY_CID = "select token from appNotification where isActive = 1 and cId = ?";
}
