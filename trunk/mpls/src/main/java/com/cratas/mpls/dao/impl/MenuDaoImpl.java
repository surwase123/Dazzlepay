package com.cratas.mpls.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cratas.mpls.common.query.MenuQuery;
import com.cratas.mpls.dao.IMenuDao;
import com.cratas.mpls.mapper.MenuMapper;
import com.cratas.mpls.mapper.SubMenuMapper;
import com.cratas.mpls.web.dto.MenuDTO;
import com.cratas.mpls.web.dto.SubMenuDTO;

/**
 * 
 * @author mukesh
 *
 */
@Repository
public class MenuDaoImpl implements IMenuDao {
	
		@Autowired
	    private JdbcTemplate jdbcTemplate;
		
		public List<MenuDTO> getMenuList() {
			   return jdbcTemplate.query(MenuQuery.SELECT_MENU, new MenuMapper());
		}
		
		public List<SubMenuDTO> getSubMenuList(int menuId) {
			   Object args[] = { menuId };
			   return jdbcTemplate.query(MenuQuery.SELECT_SUB_MENU, args, new SubMenuMapper());
		}

}
