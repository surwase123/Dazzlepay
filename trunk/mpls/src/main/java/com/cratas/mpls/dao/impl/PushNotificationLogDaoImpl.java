package com.cratas.mpls.dao.impl;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.cratas.mpls.common.query.PushNotificationLogQuery;
import com.cratas.mpls.dao.IPushNotificationLogDao;
import com.cratas.mpls.web.dto.PushNotificationLogDTO;

/**
 * @author bhupendra
 *
 */
@Repository
public class PushNotificationLogDaoImpl implements IPushNotificationLogDao{
	
		@Autowired
		private JdbcTemplate jdbcTemplate;
	
		public int savePushNotificationLog(int cId, int mId, String message, String notificationArgs) {
			   KeyHolder keyHolder = new GeneratedKeyHolder();
			   jdbcTemplate.update(connection -> {
		                PreparedStatement ps = connection.prepareStatement(PushNotificationLogQuery.SAVE_PUSH_NOTIFICSTION_LOG,Statement.RETURN_GENERATED_KEYS);
		                ps.setInt(1, cId);
		                ps.setInt(2, mId);
		                ps.setString(3, message);
		                ps.setString(4, notificationArgs);
		                return ps;
		       }, keyHolder); 
		       return keyHolder.getKey().intValue();
		}
		
		public int savePushNotificationNewLog(int cId, int mId,int notificationWay,int readonly, String message, String notificationArgs) {
			   KeyHolder keyHolder = new GeneratedKeyHolder();
			   jdbcTemplate.update(connection -> {
		                PreparedStatement ps = connection.prepareStatement(PushNotificationLogQuery.SAVE_PUSH_NOTIFICSTION_NEW_LOG,Statement.RETURN_GENERATED_KEYS);
		                ps.setInt(1, cId);
		                ps.setInt(2, mId);
		                ps.setInt(3,notificationWay);
		                ps.setInt(4,readonly);
		                ps.setString(5, message);
		                ps.setString(6, notificationArgs);
		                return ps;
		       }, keyHolder); 
		       return keyHolder.getKey().intValue();
		}


		public int updatePushNotificationLog(int status,int isViewd, int notificationId) {
			   Object args[] = {status , isViewd,notificationId};
			   return jdbcTemplate.update(PushNotificationLogQuery.UPDATE_PUSH_NOTIFICSTION_LOG, args);
		}

		public List<PushNotificationLogDTO> getPushNotificationByCid(int id) {
			   Object args[] = {id};
			   return jdbcTemplate.query(PushNotificationLogQuery.GET_PUSH_NOTIFICATION_BY_CID, args, new BeanPropertyRowMapper<PushNotificationLogDTO>(PushNotificationLogDTO.class));
		}
		
		public List<PushNotificationLogDTO> getPushNotificationByMid(int id) {
			   Object args[] = {id};
			   return jdbcTemplate.query(PushNotificationLogQuery.GET_PUSH_NOTIFICATION_BY_MID, args, new BeanPropertyRowMapper<PushNotificationLogDTO>(PushNotificationLogDTO.class));
		}

		public List<PushNotificationLogDTO>getPushNotification(){
			return jdbcTemplate.query(PushNotificationLogQuery.GET_PUSH_NOTIFICATION, new BeanPropertyRowMapper<PushNotificationLogDTO>(PushNotificationLogDTO.class));
			
		}
}
