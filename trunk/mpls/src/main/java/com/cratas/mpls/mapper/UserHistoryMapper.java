package com.cratas.mpls.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.web.dto.UserHistoryDTO;

/**
 * 
 * @author mukesh
 *
 */
@Component
@Scope(Constants.PROTOTYPE)
public class UserHistoryMapper implements RowMapper<UserHistoryDTO> {

		public UserHistoryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			   UserHistoryDTO userHistoryDTO = new UserHistoryDTO();
			   userHistoryDTO.setLoginId(rs.getString("loginId"));
			   userHistoryDTO.setIp_address(rs.getString("ipAddress"));
			   userHistoryDTO.setBrowser(rs.getString("browser"));
			   userHistoryDTO.setOs(rs.getString("os"));
			   userHistoryDTO.setSession_id(rs.getString("sessionId"));
			   userHistoryDTO.setSource(rs.getString("source"));
			   userHistoryDTO.setRequest_url(rs.getString("requestUrl"));
			   userHistoryDTO.setDate(rs.getTimestamp("timestamp"));
			   return userHistoryDTO;
		}

}
