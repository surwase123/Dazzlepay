package com.cratas.mpls.service;

import java.util.List;

import com.cratas.mpls.web.dto.MenuDTO;
import com.cratas.mpls.web.dto.SubMenuDTO;
import com.cratas.mpls.web.dto.UserRoleDTO;

/**
 * 
 * @author mukesh
 *
 */
public interface IMenuService {
	
	   List<MenuDTO> getMenuList();
	   
	   List<SubMenuDTO> getSubMenuList(int menuId);

	   SubMenuDTO getSubMenu(int menuId);
	   
	   MenuDTO getMenu(int menuId);
}
