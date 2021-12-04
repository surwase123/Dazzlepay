/**
 * 
 */
package com.cratas.mpls.service;

import java.util.List;
import java.util.Map;

import com.cratas.mpls.dao.dto.TableHeaderDTO;
import com.cratas.mpls.web.dto.NotificationDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author mukesh
 *
 */
public interface IMPLSService {
		
	   int saveUserNotification(NotificationDTO notificationDTO,UserDTO userDTO);
	   
	   List<TableHeaderDTO> getTableHeaderList(String key);

	   Map<String, Object> getNotificationById(String tableName, String recordId);

	   int updateUserNotificationStatus(int status, int id,String comments);
	   
	   String getMenuDBTableName(String menuName);
	   
	   NotificationDTO createUserNotificationObj(String recordId, String menuName, UserDTO userDTO);
	   
	   int updateRecordStatus(String tableName, String recordId, int status);
	   	   	   
}
