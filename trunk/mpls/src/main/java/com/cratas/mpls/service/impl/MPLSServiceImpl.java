/**
 * 
 */
package com.cratas.mpls.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.utility.constant.InformationMsgConstants;
import com.cratas.mpls.dao.IMPLSDao;
import com.cratas.mpls.dao.dto.TableHeaderDTO;
import com.cratas.mpls.service.IMPLSService;
import com.cratas.mpls.web.dto.MenuDetailMappingDTO;
import com.cratas.mpls.web.dto.NotificationDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author mukesh
 *
 */
@Service
public class MPLSServiceImpl implements IMPLSService{
		
		@Autowired
		private IMPLSDao reconDao;
		
		private Map<String, List<TableHeaderDTO>> menuTableHeaderMap = null;
		
		private Map<String, String> menuDBTableMap = null;
		
		@PostConstruct
		public void init(){
			   menuTableHeaderMap = new HashMap<>();
			   menuDBTableMap = new HashMap<>();
			   loadMenuDetail();
		}
		
		public int saveUserNotification(NotificationDTO notificationDTO, UserDTO userDTO) {
			   notificationDTO.setCreatorId(userDTO.getLoginId());
			   notificationDTO.setCheckerId(userDTO.getManagerId());
			   return reconDao.saveUserNotification(notificationDTO,userDTO);
		}
		
		public void loadMenuDetail() {
			   List<MenuDetailMappingDTO> menuMappingList = reconDao.getMenuDetailMapping();
			   for (MenuDetailMappingDTO menuMappingDTO : menuMappingList) {
					 List<TableHeaderDTO> headerList = new LinkedList<>();
					 String fieldName = menuMappingDTO.getFieldName();
					 if(StringUtils.isNotEmpty(fieldName)){
						  String fieldNameArr[] = fieldName.split(Constants.COMMA);
						  for (String names : fieldNameArr) {
							   TableHeaderDTO headerDTO = getTableHeader(names);
							   if(null != headerDTO){
							      headerList.add(headerDTO);
							   }
						  }
						  menuTableHeaderMap.put(menuMappingDTO.getMenuName(), headerList); 
					 }
					 menuDBTableMap.put(menuMappingDTO.getMenuName(), menuMappingDTO.getDbTableName());
				}
		}
		
		private TableHeaderDTO getTableHeader(String names){
			    TableHeaderDTO tableHeaderDTO = new TableHeaderDTO();
			    String headerNameArr[] = names.split(Constants.BITWISE);
			    if(headerNameArr.length > 1){
				   tableHeaderDTO.setHeaderName(headerNameArr[0]);
				   tableHeaderDTO.setHeaderValue(headerNameArr[1]);
				   return tableHeaderDTO;
			    }
			    return null;
		}
		
		public List<TableHeaderDTO> getTableHeaderList(String key) {
			   if(menuTableHeaderMap.containsKey(key)) {
			        return menuTableHeaderMap.get(key);
			   }
			   return null;
		}
		
		public String getMenuDBTableName(String menuName){
			   if(menuDBTableMap.containsKey(menuName)) {
		           return menuDBTableMap.get(menuName);
		       }
		       return null;
		}

		public Map<String, Object> getNotificationById(String tableName, String recordId) {
			  return reconDao.getNotificationById(tableName, recordId);
		}

		public int updateUserNotificationStatus(int status, int id,String comments) {
			return reconDao.updateUserNotificationStatus(status, id,comments);
		}
		
		public NotificationDTO createUserNotificationObj(String recordId, String menuName, UserDTO userDTO){
			    NotificationDTO notificationDTO = new NotificationDTO();
				notificationDTO.setRecordId(recordId);
				notificationDTO.setMenuName(menuName);
				notificationDTO.setTableName(getMenuDBTableName(menuName));
				notificationDTO.setMessage(userDTO.getManagerId() + Constants.SPACE + InformationMsgConstants.NOTIFICATION_MESSAGE_FORMAT +  Constants.SPACE + menuName);
			    return notificationDTO;
		}
		
		public int updateRecordStatus(String tableName, String recordId, int status){
			   return reconDao.updateRecordStatus(tableName, recordId, status);
		}

}	
