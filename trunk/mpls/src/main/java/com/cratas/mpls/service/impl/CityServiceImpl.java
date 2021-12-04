package com.cratas.mpls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.utility.SaveUserNotificationThread;
import com.cratas.mpls.dao.ICityDao;
import com.cratas.mpls.service.ICityService;
import com.cratas.mpls.service.IMPLSService;
import com.cratas.mpls.web.dto.CityDTO;
import com.cratas.mpls.web.dto.NotificationDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * @author sunil
 *
 */
@Service
public class CityServiceImpl implements ICityService {
	
		@Autowired
		private ICityDao cityDao;
		
		@Autowired
		private SaveUserNotificationThread saveUserNotificationThread;
		
		@Autowired
		private ThreadPoolTaskExecutor taskExecutor;
		
		@Autowired
		private IMPLSService mplsService;
		
		public List<CityDTO> getCity() {
			return cityDao.getCity();
		}
		
		public int saveCity(CityDTO cityDTO, UserDTO userDTO) {
			int isInsert = cityDao.saveCity(cityDTO);
			if (isInsert == 1 && userDTO.getGroupDTO().getGroupType().equals(Constants.MAKER)) {
				CityDTO city = cityDao.getCityByName(cityDTO.getCityName(), cityDTO.getStateId(), cityDTO.getCountryId());
				NotificationDTO notification = mplsService.createUserNotificationObj(String.valueOf(city.getId()), city.getMenuName(), userDTO);
				mplsService.updateRecordStatus(notification.getTableName(), notification.getRecordId(), 0);
				saveUserNotificationThread.setData(notification, userDTO);
				taskExecutor.execute(saveUserNotificationThread);
				return isInsert;
			}
			return isInsert;
		}
		
		public int updateCity(CityDTO cityDTO) {
			return cityDao.updateCity(cityDTO);
		}
		
		public int deleteCity(CityDTO cityDTO) {
			return cityDao.deleteCity(cityDTO);
		}
		
		public CityDTO getCityByName(String cityName, int stateId, int countryId) {
			return cityDao.getCityByName(cityName, stateId, countryId);
		}

		public List<CityDTO> getCityList(int stateId) {
			   return cityDao.getCityList(stateId);
		}
		
		public CityDTO getCityListById(int id){
			   return cityDao.getCityListById(id);
		}

}
