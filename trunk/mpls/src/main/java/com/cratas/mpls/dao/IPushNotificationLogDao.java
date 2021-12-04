package com.cratas.mpls.dao;

import java.util.List;

import com.cratas.mpls.web.dto.PushNotificationLogDTO;

/**
 * @author bhupendra
 *
 */
public interface IPushNotificationLogDao {

	   int savePushNotificationLog(int cId, int mId, String message, String notificationArgs);
	   
	   int savePushNotificationNewLog(int cId, int mId,int notificationWay,int readonly, String message, String notificationArgs);
	   
	   int updatePushNotificationLog(int status,int isViewd, int notificationId);
	   
	   List<PushNotificationLogDTO> getPushNotificationByCid(int id);
	   
	   List<PushNotificationLogDTO> getPushNotificationByMid(int id);
	   
	   List<PushNotificationLogDTO>getPushNotification();
}
