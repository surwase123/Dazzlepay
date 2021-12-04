package com.cratas.mpls.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cratas.mpls.web.dto.GroupDTO;

/**
 * 
 * @author mukesh
 *
 */
public class GroupMapper implements RowMapper<GroupDTO> {

	public GroupDTO mapRow(ResultSet rs, int rowNum) throws SQLException{ 
		   GroupDTO groupDTO = new GroupDTO();
		   groupDTO.setId(rs.getInt("id"));
		   groupDTO.setSystemId(rs.getString("systemId"));
		   groupDTO.setGroupId(rs.getString("groupId"));
		   groupDTO.setGroupName(rs.getString("groupName"));
		   groupDTO.setGroupType(rs.getString("groupType"));
		   groupDTO.setMinPassLength(rs.getInt("minPassLength"));
		   groupDTO.setMaxPassLength(rs.getInt("maxPassLength"));
		   groupDTO.setIsAlphaPassword(rs.getString("isAlphaPassword"));
		   groupDTO.setIsNumberPassword(rs.getString("isNumberPassword"));
		   groupDTO.setIsSpecialSymbolPass(rs.getString("isSpecialSymbolPass"));
		   groupDTO.setPassExpiryDays(rs.getInt("passExpiryDays"));
		   groupDTO.setPassHistoryChecks(rs.getInt("passHistoryChecks"));
		   groupDTO.setMaxConcurrentLogin(rs.getInt("maxConcurrentLogin"));
		   groupDTO.setMaxLoginRetries(rs.getInt("maxLoginRetries"));
		   groupDTO.setStatus(rs.getString("status"));
		   groupDTO.setIsEditable(rs.getInt("isEditable"));
           return groupDTO;
	}

}
