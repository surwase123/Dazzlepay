/**
 * 
 */
package com.cratas.mpls.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.query.MPLSQuery;
import com.cratas.mpls.dao.IMPLSDao;
import com.cratas.mpls.web.dto.MenuDetailMappingDTO;
import com.cratas.mpls.web.dto.NotificationDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * @author bhupendra
 *
 */
@Repository
public class MPLSDaoImpl implements IMPLSDao{

		@Autowired
		private JdbcTemplate jdbcTemplate;
		
		@Autowired
		private NamedParameterJdbcTemplate namedParamJdbcTemplate;
		
		public int saveUserNotification(NotificationDTO notificationDTO, UserDTO userDTO) {
				   BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(notificationDTO);
				   return namedParamJdbcTemplate.update(MPLSQuery.SAVE_USER_NOTIFICATION, params);
		}
		
		public int updateRecordStatus(String tableName, String recordId, int status){
			   Object args[] = { status, recordId};
			   return jdbcTemplate.update(MPLSQuery.UPDATE_RECORD_STATUS.replace(Constants.TABLE_NAME, tableName), args);
		}

		public List<MenuDetailMappingDTO> getMenuDetailMapping() {
			   return jdbcTemplate.query(MPLSQuery.FETCH_MENU_DETAIL, new BeanPropertyRowMapper<MenuDetailMappingDTO>(MenuDetailMappingDTO.class));
		}

		public Map<String, Object> getNotificationById(String tableName, String recordId) {
			   Object args[] = { recordId };
			   List<Map<String, Object>> notificationDetail = jdbcTemplate.queryForList(MPLSQuery.USER_NOTIFICATION_DETAIL.replace(Constants.TABLE_NAME, tableName), args);
			   if(notificationDetail.size() > 0) {
				   return notificationDetail.get(0);
			   }
			   return null;
		}

		public int updateUserNotificationStatus(int status, int id, String Comments) {
			   Object args[] = { Comments, status, id};
			   return jdbcTemplate.update(MPLSQuery.APPROVE_USER_NOTIFICATION, args);
		}
}
