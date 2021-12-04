package com.cratas.mpls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.dao.IPushNotificationLogDao;
import com.cratas.mpls.service.IPushNotificationLogService;
import com.cratas.mpls.web.dto.PushNotificationLogDTO;

/**
 * @author bhupendra
 *
 */
@Service
public class PushNotificationLogServiceImpl implements IPushNotificationLogService{

		@Autowired
		private IPushNotificationLogDao pushNotificationLogDao;
	
		public int savePushNotificationLog(int cId, int mId, String message, String notificationArgs, String type) {
			   if(type.equals(Constants.CUSTOMER_MAPPING_REQUSET)) {
				   return pushNotificationLogDao.savePushNotificationLog(Constants.ZERO, mId, message, notificationArgs);
			   }else {
				   return pushNotificationLogDao.savePushNotificationLog(cId, Constants.ZERO, message, notificationArgs);
			   }
		}
		@Override
		public int savePushNotificationNewLog(int cId, int mId,int getway,int readonly, String message, String notificationArgs, String type) {
			   if(type.equals(Constants.CUSTOMER_MAPPING_REQUSET)) {
				   return pushNotificationLogDao.savePushNotificationNewLog(Constants.ZERO, mId,Constants.WAY_ONE,Constants.ZERO, message, notificationArgs);
			   }
			   else if(type.equals(Constants.REQUEST_LOYALTY_CARD_NOTIFICATION) ){
				   return pushNotificationLogDao.savePushNotificationNewLog(Constants.ZERO, mId,getway,readonly, message, notificationArgs);
			   }
			   else if(type.equals(Constants.ALLOCATE_LOYALTY_CARD_NOTIFICATION)) {
				   return pushNotificationLogDao.savePushNotificationNewLog(cId, Constants.ZERO,getway,readonly, message, notificationArgs);
			   }
			   else if(type.equals(Constants.MERCHANT_MAPPING_REQUSET)) {
				   return pushNotificationLogDao.savePushNotificationNewLog(cId, Constants.ZERO,Constants.WAY_TWO,readonly, message, notificationArgs);
			   }
			   else {
				   return pushNotificationLogDao.savePushNotificationNewLog(cId, Constants.ZERO,Constants.WAY_TWO,Constants.ZERO, message, notificationArgs);
			   }
		}

		public int updatePushNotificationLog(int status,int isViewd, int notificationId) {
			   return pushNotificationLogDao.updatePushNotificationLog(status,isViewd, notificationId);
		}

		public List<PushNotificationLogDTO> getPushNotificationByCid(int id) {
			   return pushNotificationLogDao.getPushNotificationByCid(id);
		}

		public List<PushNotificationLogDTO> getPushNotificationByMid(int id) {
			 List<PushNotificationLogDTO> list = pushNotificationLogDao.getPushNotificationByMid(id);
			    return list;
		}
		
		public List<PushNotificationLogDTO> getPushNotification() {
			   return pushNotificationLogDao.getPushNotification();
		}
}
