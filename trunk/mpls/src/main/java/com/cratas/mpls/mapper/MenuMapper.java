package com.cratas.mpls.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cratas.mpls.web.dto.MenuDTO;

/**
 * 
 * @author mukesh
 *
 */
public class MenuMapper implements RowMapper<MenuDTO> {

	public MenuDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		   MenuDTO menuDTO = new MenuDTO();
		   menuDTO.setMenuId(rs.getInt("id"));
		   menuDTO.setMenuName(rs.getString("menuName"));
		   menuDTO.setDescription(rs.getString("description"));
		   menuDTO.setMenuIcon(rs.getString("menuIcon"));
		   menuDTO.setAction(rs.getString("action"));
		   menuDTO.setIsAdd(rs.getInt("isAdd"));
		   menuDTO.setIsDelete(rs.getInt("isDelete"));
		   menuDTO.setIsUpdate(rs.getInt("isUpdate"));
		   menuDTO.setIsMaskField(rs.getInt("isMaskField"));
		   menuDTO.setOrderSequence(rs.getInt("orderSequence"));
		   return menuDTO;
	}

}
