/**
 * 
 */
package com.cratas.mpls.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cratas.mpls.web.dto.NotificationDTO;

/**
 * @author bhupendra
 *
 */
public class UserNotificationMapper implements RowMapper<NotificationDTO>{

	@Override
	public NotificationDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		   NotificationDTO notificationDTO = new NotificationDTO();
		   notificationDTO.setCheckerId(rs.getString("checkerId"));
		   notificationDTO.setCreatorId(rs.getString("creatorId"));
		   notificationDTO.setRecordId(rs.getString("recordId"));
		   notificationDTO.setMenuName(rs.getString("menuName"));
		   notificationDTO.setTableName(rs.getString("tableName"));
		   notificationDTO.setMessage(rs.getString("message"));
		   notificationDTO.setComments(rs.getString("comments"));
		   notificationDTO.setId(rs.getInt("id"));
		   notificationDTO.setStatus(rs.getInt("status"));
		   notificationDTO.setInsertDate(rs.getDate("insertTimeStamp"));
		   notificationDTO.setUpdateDate(rs.getDate("updateTimeStamp"));
		   return notificationDTO;
	}

}
