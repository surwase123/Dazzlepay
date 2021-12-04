package com.cratas.mpls.common.query;

/**
 * @author bhupendra
 *
 */
public class PushNotificationLogQuery {

	   public static final String SAVE_PUSH_NOTIFICSTION_LOG = "insert into notificationLog set cId = ?, mId = ?, message = ?, notificationArgs = ?";
	
	   public static final String SAVE_PUSH_NOTIFICSTION_NEW_LOG = "insert into notificationLog set cId = ?, mId = ?,notification_way=?,is_readonly=?, message = ?, notificationArgs = ?";
	   
	   public static final String UPDATE_PUSH_NOTIFICSTION_LOG = "update notificationLog set status = ? ,is_viewed=? where id =?";

       public static final String GET_PUSH_NOTIFICATION_BY_CID = "select * from notificationLog where cId = ? and status = 0  ORDER BY id DESC";
       
       public static final String GET_PUSH_NOTIFICATION_BY_MID = "select * from notificationLog where  mId=?  and status = 0  and is_viewed=0 and (notification_way=1 or notification_way=4) ORDER BY id DESC";
       
       public static final String GET_PUSH_NOTIFICATION = "select * from notificationLog where status = 0";

}
