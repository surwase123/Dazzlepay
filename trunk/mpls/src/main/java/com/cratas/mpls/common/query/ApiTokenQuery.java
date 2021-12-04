package com.cratas.mpls.common.query;
/**
 * 
 * @author Sunil
 *
 */
public class ApiTokenQuery {

	   public static final String INSERT_DEVICE_ID = "insert into apiToken set deviceId=:deviceId, token=:token, apiKey=:apiKey";
	   
	   public static final String UPDATE_DEVICE_ID = "update apiToken set isActive = 0, updatedDate = now() where deviceId = ? and isActive = 1";
	   

}
