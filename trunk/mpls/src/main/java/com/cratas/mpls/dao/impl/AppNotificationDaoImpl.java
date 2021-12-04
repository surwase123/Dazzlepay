package com.cratas.mpls.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cratas.mpls.common.query.AppNotificationQuery;
import com.cratas.mpls.dao.IAppNotificationDao;
import com.cratas.mpls.web.dto.AppNotificationDTO;

/**
 * 
 * @author sunil
 *
 */
@Repository
public class AppNotificationDaoImpl implements IAppNotificationDao{
	
	   @Autowired
	   private JdbcTemplate jdbcTemplate;
	    
	   @Autowired
	   private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	   
	   public int registerFCMToken(AppNotificationDTO appNotification) {
		      updateInActiveDevice(appNotification.getDeviceId());
		      if(getAppNotification(appNotification) == null){
			      BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(appNotification);
			      return namedParameterJdbcTemplate.update(AppNotificationQuery.INSERT_APP_NOTIFICATION, params);
		      }else{
		    	  return updateFCMToken(appNotification);
		      }
	   }
	   
	   public AppNotificationDTO findByDeviceId(String deviceId) {
		      Object args[] = {deviceId};
			  List<AppNotificationDTO> appNotificationList = jdbcTemplate.query(AppNotificationQuery.SELECT_APP_NOTIFICATION_BY_DEVICE_ID, args, new BeanPropertyRowMapper<AppNotificationDTO>(AppNotificationDTO.class));
			  if(!appNotificationList.isEmpty()){
				  return appNotificationList.get(0);
			  }
			  return null;
	   }
	   
	   public List<String> getMerchantTokenByMId(int mId){
		      Object args[] = { mId };
		      List<String> tokenList = jdbcTemplate.queryForList(AppNotificationQuery.SELECT_MERCHANT_TOKEN_BY_MID, args, String.class);
			  return tokenList;
	   }
	   
	   private int updateInActiveDevice(String deviceId) {
		   	   Object[] args = { deviceId };
		       return jdbcTemplate.update(AppNotificationQuery.UPDATE_INACTIVE_DEVICE, args);
	   }
	   
	   private int updateFCMToken(AppNotificationDTO appNotificationDTO) {
		   	  Object[] args = { appNotificationDTO.getDeviceId(), appNotificationDTO.getToken()};
		   	  String updateQuery = AppNotificationQuery.UPDATE_APP_NOTIFICATION + (appNotificationDTO.getcId() != 0 ? ("cId = " +appNotificationDTO.getcId()) : ("mId = "+appNotificationDTO.getmId()));
		      return jdbcTemplate.update(updateQuery, args);
	   }
	   
	   private AppNotificationDTO getAppNotification(AppNotificationDTO appNotificationDTO) {
			  Object args[] = {};
			  String query = AppNotificationQuery.SELECT_APP_NOTIFICATION + (appNotificationDTO.getcId() != 0 ? ("cId = " +appNotificationDTO.getcId()) : ("mId = " +appNotificationDTO.getmId()));
			  List<AppNotificationDTO> appNotificationList = jdbcTemplate.query(query, args, new BeanPropertyRowMapper<AppNotificationDTO>(AppNotificationDTO.class));
			  if(!appNotificationList.isEmpty()){
				  return appNotificationList.get(0);
			  }
			  return null;
	   }
	   
	   public List<String> getCustomerTokenByCId(int cId) {
   		      Object args[] = { cId };
   			  List<String> tokenList = jdbcTemplate.queryForList(AppNotificationQuery.SELECT_CUSTOMER_TOKEN_BY_CID, args, String.class);
   			  return tokenList;
	   }

}
