package com.cratas.mpls.service.impl;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.utility.SaveUserNotificationThread;
import com.cratas.mpls.dao.IUserRoleDao;
import com.cratas.mpls.service.IMenuService;
import com.cratas.mpls.service.IMPLSService;
import com.cratas.mpls.service.IUserRoleService;
import com.cratas.mpls.web.dto.GroupDTO;
import com.cratas.mpls.web.dto.GroupPrivilegeRequestDTO;
import com.cratas.mpls.web.dto.MenuDTO;
import com.cratas.mpls.web.dto.NotificationDTO;
import com.cratas.mpls.web.dto.SubMenuDTO;
import com.cratas.mpls.web.dto.UserDTO;
import com.cratas.mpls.web.dto.UserRoleDTO;

/**
 * 
 * @author mukesh
 *
 */
@Service
public class UserRoleServiceImpl implements IUserRoleService {

		@Autowired
		private IUserRoleDao userRoleDao;
		
		@Autowired
		private IMenuService menuService;
		
		@Autowired
		private SaveUserNotificationThread saveUserNotificationThread;
		
		@Autowired
		private ThreadPoolTaskExecutor taskExecutor;
		
		@Autowired
		private IMPLSService reconService;
	   
		public int saveUserRole(UserRoleDTO userRoleDTO) {
			   return userRoleDao.saveUserRole(userRoleDTO);
		}
		
		public void addUserNotification(UserDTO userDTO, GroupPrivilegeRequestDTO groupPrivilegeRequestDTO){
			   GroupDTO group = userDTO.getGroupDTO();
			   if(group.getGroupType().equals(Constants.MAKER)) {
					NotificationDTO notification = reconService.createUserNotificationObj(groupPrivilegeRequestDTO.getGroupId(), groupPrivilegeRequestDTO.getScreenMenuName(), userDTO);
					reconService.updateRecordStatus(notification.getTableName(), notification.getRecordId(), 0);
					saveUserNotificationThread.setData(notification,userDTO);
					taskExecutor.execute(saveUserNotificationThread);
				}
		}
		
		
		public List<UserRoleDTO> getUserRoleById(String groupId) {
			   List<UserRoleDTO> newRoleList = new LinkedList<>();
			   List<UserRoleDTO> userRoleList = userRoleDao.getUserRoleById(groupId);
			   for (UserRoleDTO userRoleDTO : userRoleList) {
				   MenuDTO menuDTO = menuService.getMenu(userRoleDTO.getMenuId());
				   userRoleDTO.setMenuName(menuDTO == null ? Constants.BLANK : menuDTO.getMenuName());
				   SubMenuDTO subMenuDTO = menuService.getSubMenu(userRoleDTO.getSubMenuId());
				   userRoleDTO.setSubMenuName(subMenuDTO == null ? Constants.BLANK : subMenuDTO.getSubMenuName());
				   newRoleList.add(userRoleDTO);
			   }
			   return newRoleList;
		}
		
		public List<UserRoleDTO> getUserRole(String systemId) {
			   List<UserRoleDTO> newRoleList = new LinkedList<>();
			   List<String> userGroupList = new ArrayList<>();
			   List<UserRoleDTO> userRoleList = userRoleDao.getUserRole(systemId);
			   for (UserRoleDTO userRoleDTO : userRoleList) {
				    if(!userGroupList.contains(userRoleDTO.getGroupId())){
				          newRoleList.add(userRoleDTO);
				          userGroupList.add(userRoleDTO.getGroupId());
				    }
			   }
			   return newRoleList;
		}
	
		
		public int deleteUserRole(UserRoleDTO userRoleDTO) {
		       return userRoleDao.deleteUserRole(userRoleDTO);
		}
		
		public int deleteGroupPrivilege(String groupId){
			   return userRoleDao.deleteGroupPrivilege(groupId);
		}

}
