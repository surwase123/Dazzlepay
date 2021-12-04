package com.cratas.mpls.service;

import java.util.List;

import com.cratas.mpls.web.dto.PushNotificationLogDTO;

/**
 * @author bhupendra
 *
 */
public interface IPushNotificationLogService {

		int savePushNotificationLog(int cId, int mId, String message, String notificationArgs, String type);
		
		int updatePushNotificationLog(int status,int isViewd, int notificationId);
		
		List<PushNotificationLogDTO> getPushNotificationByCid(int id);
		   
		List<PushNotificationLogDTO> getPushNotificationByMid(int id);
		
		List<PushNotificationLogDTO> getPushNotification();

		int savePushNotificationNewLog(int getcId, int getmId, int notificationWay, int isReadOnly, String title,
				String string, String type);
		
}
