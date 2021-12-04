package com.cratas.mpls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.utility.SaveUserNotificationThread;
import com.cratas.mpls.dao.IAreaDao;
import com.cratas.mpls.service.IAreaService;
import com.cratas.mpls.service.IMPLSService;
import com.cratas.mpls.web.dto.AreaDTO;
import com.cratas.mpls.web.dto.NotificationDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author bhupendra
 *
 */

@Service
public class AreaServiceImpl implements IAreaService{

		@Autowired
	    private IAreaDao areaDao;
		
		@Autowired
		private SaveUserNotificationThread saveUserNotificationThread;
		
		@Autowired
		private ThreadPoolTaskExecutor taskExecutor;
		
		@Autowired
		private IMPLSService mplsService;
		
		public List<AreaDTO> getArea() {
			   return areaDao.getArea();
		}
	
		public int saveArea(AreaDTO areaDTO, UserDTO userDTO) {
			   int isInsert = areaDao.saveArea(areaDTO);
			   if(isInsert == 1 && userDTO.getGroupDTO().getGroupType().equals(Constants.MAKER)) {
				    AreaDTO area = areaDao.getAreaByCode(areaDTO.getAreaName(), areaDTO.getAreaCode(), areaDTO.getCityId());
					NotificationDTO notification = mplsService.createUserNotificationObj(String.valueOf(area.getId()), areaDTO.getMenuName(), userDTO);
					mplsService.updateRecordStatus(notification.getTableName(), notification.getRecordId(), 0);
					saveUserNotificationThread.setData(notification,userDTO);
					taskExecutor.execute(saveUserNotificationThread);
					return isInsert;
				}
				return isInsert;
		}
	
		public int updateArea(AreaDTO areaDTO) {
			   return areaDao.updateArea(areaDTO);
		}
	
		public int deleteArea(AreaDTO areaDTO) {
			   return areaDao.deleteArea(areaDTO);
		}
	
		public AreaDTO getAreaByCode(String areaName, String areaCode, int cityId) {
			   return areaDao.getAreaByCode(areaName, areaCode, cityId);
		}
	
		public AreaDTO getAreaById(int id) {
			   return areaDao.getAreaById(id);
		}

		public List<AreaDTO> getAreaList(int cityId) {
			   return areaDao.getAreaList(cityId);
		}
		
		public List<AreaDTO> getAllArea(){
			   return areaDao.getAllArea();
		}

}
