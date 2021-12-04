package com.cratas.mpls.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cratas.mpls.web.dto.UserRoleDTO;

/**
 * 
 * @author mukesh
 *
 */
public class UserRoleMapper implements RowMapper<UserRoleDTO> {
	
		public UserRoleDTO mapRow(ResultSet rs, int rowNum) throws SQLException{ 
			      UserRoleDTO userRoleDTO = new UserRoleDTO();
			      userRoleDTO.setGroupId(rs.getString("groupId"));
			      userRoleDTO.setGroupName(rs.getString("groupName"));
			      userRoleDTO.setMenuId(rs.getInt("menuId"));
			      userRoleDTO.setSubMenuId(rs.getInt("subMenuId"));
			      userRoleDTO.setIsAdd(rs.getInt("isAdd"));
			      userRoleDTO.setIsDelete(rs.getInt("isDelete"));
			      userRoleDTO.setIsUpdate(rs.getInt("isUpdate"));
			      userRoleDTO.setIsMaskField(rs.getInt("isMaskField"));
			      userRoleDTO.setIsEditable(rs.getInt("isEditable"));
			      userRoleDTO.setCreatedBy(rs.getString("createdBy"));
			      userRoleDTO.setInsertTimeStamp(rs.getDate("insertTimeStamp"));
			      return userRoleDTO;
		}

}
