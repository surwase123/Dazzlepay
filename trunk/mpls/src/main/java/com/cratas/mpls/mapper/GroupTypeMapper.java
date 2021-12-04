package com.cratas.mpls.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cratas.mpls.dao.dto.GroupTypeDTO;

/**
 * 
 * @author mukesh
 *
 */
public class GroupTypeMapper implements RowMapper<GroupTypeDTO> {

		public GroupTypeDTO mapRow(ResultSet rs, int rowNum) throws SQLException{ 
			   GroupTypeDTO groupTypeDTO = new GroupTypeDTO();
			   groupTypeDTO.setGroupType(rs.getString("groupType"));
			   groupTypeDTO.setDisplayName(rs.getString("displayName"));
			   return groupTypeDTO;
		}

}
