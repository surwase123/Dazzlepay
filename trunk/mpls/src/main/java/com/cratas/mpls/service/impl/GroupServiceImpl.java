package com.cratas.mpls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.utility.SaveUserNotificationThread;
import com.cratas.mpls.dao.IGroupDao;
import com.cratas.mpls.dao.dto.GroupTypeDTO;
import com.cratas.mpls.service.IGroupService;
import com.cratas.mpls.service.IMPLSService;
import com.cratas.mpls.web.dto.GroupDTO;
import com.cratas.mpls.web.dto.NotificationDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author mukesh
 *
 */
@Service
public class GroupServiceImpl implements IGroupService {
	
	    @Autowired
	    private IGroupDao groupDao;
	    
	    @Autowired
		private SaveUserNotificationThread saveUserNotificationThread;
		
		@Autowired
		private ThreadPoolTaskExecutor taskExecutor;
		
		@Autowired
		private IMPLSService reconService;
	    
		
		public int saveGroup(GroupDTO groupDTO, UserDTO userDTO) {
			   int isInsert = groupDao.saveGroup(groupDTO);
			   if(isInsert == 1 && userDTO.getGroupDTO().getGroupType().equals(Constants.MAKER)) {
				    GroupDTO group = groupDao.getGroupById(groupDTO.getGroupId());
					NotificationDTO notification = reconService.createUserNotificationObj(String.valueOf(group.getId()), groupDTO.getMenuName(), userDTO);
					reconService.updateRecordStatus(notification.getTableName(), notification.getRecordId(), 0);
					saveUserNotificationThread.setData(notification,userDTO);
					taskExecutor.execute(saveUserNotificationThread);
					return isInsert;
				}
				return isInsert;
		}
		
		public int updateGroup(GroupDTO groupDTO) {
			   return groupDao.updateGroup(groupDTO);
		}
		
		public GroupDTO getGroupById(String groupId) {
			   return groupDao.getGroupById(groupId);
		}
		
		public List<GroupDTO> getGroup(String systemId) {
			   return groupDao.getGroup(systemId);
		}
		
		public int deleteGroup(GroupDTO groupDTO) {
			   return groupDao.deleteGroup(groupDTO);
		}
		
		public List<GroupDTO> getActiveGroup() {
			   return groupDao.getActiveGroup();
		}
		   
		public List<GroupDTO> getActiveGroupsById(String systemId){
			   return groupDao.getActiveGroupsById(systemId);
		}
		
		public List<GroupTypeDTO> getGroupType(){
			   return groupDao.getGroupType();
		}
}
