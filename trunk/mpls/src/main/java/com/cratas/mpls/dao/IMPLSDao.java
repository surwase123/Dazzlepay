/**
 * 
 */
package com.cratas.mpls.dao;

import java.util.List;
import java.util.Map;

import com.cratas.mpls.web.dto.MenuDetailMappingDTO;
import com.cratas.mpls.web.dto.NotificationDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author mukesh
 *
 */
public interface IMPLSDao {
			 
	   int saveUserNotification(NotificationDTO notificationDTO, UserDTO userDTO);

	   List<MenuDetailMappingDTO> getMenuDetailMapping();
		
	   Map<String, Object> getNotificationById(String tableName, String recordId);
		 
	   int updateUserNotificationStatus(int status, int id,String comments);
		 
	   int updateRecordStatus(String tableName, String recordId, int status);
		 		 
}
