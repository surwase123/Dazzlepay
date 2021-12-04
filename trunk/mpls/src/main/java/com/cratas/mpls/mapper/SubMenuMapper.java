package com.cratas.mpls.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cratas.mpls.web.dto.SubMenuDTO;

/**
 * 
 * @author mukesh
 *
 */
public class SubMenuMapper implements RowMapper<SubMenuDTO> {

	public SubMenuDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		   SubMenuDTO subMenuDTO = new SubMenuDTO();
		   subMenuDTO.setId(rs.getInt("id"));
		   subMenuDTO.setParentMenuId(rs.getInt("menuId"));
		   subMenuDTO.setSubMenuName(rs.getString("subMenuName"));
		   subMenuDTO.setDescription(rs.getString("description"));
		   subMenuDTO.setMenuIcon(rs.getString("menuIcon"));
		   subMenuDTO.setAction(rs.getString("action"));
		   subMenuDTO.setIsAdd(rs.getInt("isAdd"));
		   subMenuDTO.setIsDelete(rs.getInt("isDelete"));
		   subMenuDTO.setIsUpdate(rs.getInt("isUpdate"));
		   subMenuDTO.setIsMaskField(rs.getInt("isMaskField"));
		   subMenuDTO.setOrderSequence(rs.getInt("orderSequence"));
		   return subMenuDTO;
	}

}
