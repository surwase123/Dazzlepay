package com.cratas.mpls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cratas.mpls.dao.IAppNotificationDao;
import com.cratas.mpls.service.IAppNotificationService;
import com.cratas.mpls.web.dto.AppNotificationDTO;

/**
 * 
 * @author sunil
 *
 */
@Service
public class AppNotificationServiceImpl implements IAppNotificationService {
	
		@Autowired
		private IAppNotificationDao appNotificationDao;
		
		public int registerFCMToken(AppNotificationDTO appNotificationDTO) {
			   return appNotificationDao.registerFCMToken(appNotificationDTO);
		}
		
		public AppNotificationDTO findByDeviceId(String deviceId) {
			   return appNotificationDao.findByDeviceId(deviceId);
		}
		
		public List<String> getMerchantTokenByMId(int mId){
		       return appNotificationDao.getMerchantTokenByMId(mId);
		}

		public List<String> getCustomerTokenByCId(int getcId) {
			   return appNotificationDao.getCustomerTokenByCId(getcId);
		}
		
}
