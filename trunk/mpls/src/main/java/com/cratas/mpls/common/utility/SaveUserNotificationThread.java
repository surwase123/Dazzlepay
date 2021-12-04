/**
 * 
 */
package com.cratas.mpls.common.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.service.IMPLSService;
import com.cratas.mpls.web.dto.NotificationDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * @author bhupendra
 *
 */
@Component
@Scope(Constants.PROTOTYPE)
public class SaveUserNotificationThread implements Runnable{
	
	    private final static Logger LOGGER = LoggerFactory.getLogger(SaveUserNotificationThread.class);
		
		@Autowired
		private IMPLSService reconService;
		
		private NotificationDTO notificationDTO;
		
		private UserDTO userDTO;
		
		public void setData(NotificationDTO notificationDTO,UserDTO userDTO){
			   this.notificationDTO = notificationDTO;
			   this.userDTO = userDTO;
		}
		
		public void run() {
				try {
					reconService.saveUserNotification(notificationDTO, userDTO);
				} catch (Exception e) {
					LOGGER.error("error in store user notification data => "+e);
				}
		}
}
