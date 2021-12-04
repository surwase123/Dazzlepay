package com.cratas.mpls.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cratas.mpls.dao.IMenuDao;
import com.cratas.mpls.service.IMenuService;
import com.cratas.mpls.web.dto.MenuDTO;
import com.cratas.mpls.web.dto.SubMenuDTO;

/**
 * 
 * @author mukesh
 *
 */
@Service
public class MenuServiceImpl implements IMenuService {
	
	    private final static Logger LOGGER = LoggerFactory.getLogger(MenuServiceImpl.class);
	    
	    @Autowired
	    private IMenuDao menuDao;
	    
	    private List<MenuDTO> menuList = null;  
	    private Map<Integer, List<SubMenuDTO>> subMenuMapList = null;  
	    private Map<Integer, MenuDTO> menuMap = null;  
	    private Map<Integer, SubMenuDTO> subMenuMap = null;
	
	    @PostConstruct
	    public void init(){
	    	  
	    	   LOGGER.info("Initialize Menu Service!!");
	    	   menuList = new LinkedList<>();
	    	   subMenuMapList = new HashMap<>();
	    	   menuMap = new HashMap<>();
	    	   subMenuMap = new HashMap<>();
	    	   loadMenuList();	    	
	    }
	    
	    private void loadMenuList(){
	    	    List<MenuDTO> mList = menuDao.getMenuList();
	    	    for (MenuDTO menuDTO : mList) {
					  List<SubMenuDTO> subMenuList = menuDao.getSubMenuList(menuDTO.getMenuId());
					  for (SubMenuDTO subMenuDTO : subMenuList) {
						  subMenuMap.put(subMenuDTO.getId(), subMenuDTO);
					  }
					  menuDTO.setSubMenuList(subMenuList);
					  subMenuMapList.put(menuDTO.getMenuId(), subMenuList);
					  menuList.add(menuDTO);
					  menuMap.put(menuDTO.getMenuId(), menuDTO);
				}
	    }
	    
		public List<MenuDTO> getMenuList() {
			   return menuList;
		}
		
		public List<SubMenuDTO> getSubMenuList(int menuId){
			   if(subMenuMapList.containsKey(menuId)){
				   return subMenuMapList.get(menuId);
			   }
			   return new ArrayList<SubMenuDTO>();
		}
		
		public SubMenuDTO getSubMenu(int menuId){
			   if(subMenuMap.containsKey(menuId)){
				   return subMenuMap.get(menuId);
			   }
			   return null;
		}
		
		public MenuDTO getMenu(int menuId){
			   if(menuMap.containsKey(menuId)){
				   return menuMap.get(menuId);
			   }
			   return null;
		}

}
