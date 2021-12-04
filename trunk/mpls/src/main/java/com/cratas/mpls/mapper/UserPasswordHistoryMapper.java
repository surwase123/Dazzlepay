package com.cratas.mpls.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cratas.mpls.web.dto.PasswordHistoryDTO;

/**
 * 
 * @author mukesh
 *
 */
public class UserPasswordHistoryMapper implements RowMapper<PasswordHistoryDTO> {

	public PasswordHistoryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		   PasswordHistoryDTO passwordHistoryDTO = new PasswordHistoryDTO();
		   passwordHistoryDTO.setId(rs.getInt("id"));
		   passwordHistoryDTO.setLoginId(rs.getString("loginId"));
		   passwordHistoryDTO.setPassword(rs.getString("password"));
		   return passwordHistoryDTO;
	}
	
}
