package com.cratas.mpls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.utility.SaveUserNotificationThread;
import com.cratas.mpls.dao.ICountryDao;
import com.cratas.mpls.service.ICountryService;
import com.cratas.mpls.service.IMPLSService;
import com.cratas.mpls.web.dto.CountryDTO;
import com.cratas.mpls.web.dto.NotificationDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author bhupendra
 *
 */
@Service
public class CountryServiceImpl implements ICountryService{

		@Autowired
	    private ICountryDao countryDao;
		
		@Autowired
		private SaveUserNotificationThread saveUserNotificationThread;
		
		@Autowired
		private ThreadPoolTaskExecutor taskExecutor;
		
		@Autowired
		private IMPLSService reconService;
		
		public List<CountryDTO> getCountry() {
			   return countryDao.getCountry();
		}
	
		public int saveCountry(CountryDTO countryDTO,  UserDTO userDTO) {
			   int isInsert = countryDao.saveCountry(countryDTO);
			   if(isInsert == 1 && userDTO.getGroupDTO().getGroupType().equals(Constants.MAKER)) {
				    CountryDTO country = countryDao.getCountryByCode(countryDTO.getCountryCode());
					NotificationDTO notification = reconService.createUserNotificationObj(String.valueOf(country.getId()), countryDTO.getMenuName(), userDTO);
					reconService.updateRecordStatus(notification.getTableName(), notification.getRecordId(), 0);
					saveUserNotificationThread.setData(notification,userDTO);
					taskExecutor.execute(saveUserNotificationThread);
					return isInsert;
				}
				return isInsert;
		}
		
		public int updateCountry(CountryDTO countryDTO) {
			   return countryDao.updateCountry(countryDTO);
		}
		
		public int deleteCountry(CountryDTO countryDTO) {
			   return countryDao.deleteCountry(countryDTO);
		}
		
		public CountryDTO getCountryByCode(int countryCode) {
			   return countryDao.getCountryByCode(countryCode);
		}
		
		public CountryDTO getCountryById(int countryId) {
			   return countryDao.getCountryById(countryId);
		}

}
