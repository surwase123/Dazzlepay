/**
 * 
 */
package com.cratas.mpls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.utility.SaveUserNotificationThread;
import com.cratas.mpls.dao.ISystemDao;
import com.cratas.mpls.service.IMPLSService;
import com.cratas.mpls.service.ISystemService;
import com.cratas.mpls.web.dto.NotificationDTO;
import com.cratas.mpls.web.dto.SystemDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * @author bhupendra
 *
 */
@Service
public class SystemServiceImpl implements ISystemService{

		@Autowired
		private ISystemDao systemDao;
		
		@Autowired
		private SaveUserNotificationThread saveUserNotificationThread;
			
		@Autowired
		private ThreadPoolTaskExecutor taskExecutor;
			
		@Autowired
		private IMPLSService reconService;
		
		public int saveSystem(SystemDTO systemDTO, UserDTO userDTO) {
			   int isInsert = systemDao.saveSystem(systemDTO);
			   if(isInsert == 1 && userDTO.getGroupDTO().getGroupType().equals(Constants.MAKER)) {
				    SystemDTO system = systemDao.getSystemById(systemDTO.getSystemId());
					NotificationDTO notification = reconService.createUserNotificationObj(String.valueOf(system.getId()), systemDTO.getMenuName(), userDTO);
					reconService.updateRecordStatus(notification.getTableName(), notification.getRecordId(), 0);
					saveUserNotificationThread.setData(notification,userDTO);
					taskExecutor.execute(saveUserNotificationThread);
					return isInsert;
			   }
			   return isInsert;
		}
		
		public int updateSystem(SystemDTO systemDTO) {
			   return systemDao.updateSystem(systemDTO);
			   
		}
		
		public SystemDTO getSystemById(String systemId) {
			   return systemDao.getSystemById(systemId);
		}
	
		public List<SystemDTO> getSystem() {
			   return systemDao.getSystem();
		}
	
		public int deleteSystem(SystemDTO systemDTO) {
			   return systemDao.deleteSystem(systemDTO);
		}
	
		public List<SystemDTO> getActiveSystem() {
			   return systemDao.getActiveSystem();
		}

}
