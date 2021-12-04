package com.cratas.mpls.dao;

import java.util.List;

import com.cratas.mpls.web.dto.MenuDTO;
import com.cratas.mpls.web.dto.SubMenuDTO;

/**
 * 
 * @author mukesh
 *
 */
public interface IMenuDao {
	
	   List<MenuDTO> getMenuList();
	   
	   List<SubMenuDTO> getSubMenuList(int menuId);

}
