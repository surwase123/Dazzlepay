package com.cratas.mpls.common.utility;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.service.IFireBaseNotificationService;
import com.cratas.mpls.web.dto.FireBaseNotificationDTO;

/**
 * @author sunil
 *
 */
@Component
@Scope(Constants.PROTOTYPE)
public class SendUserNotificationThread implements Runnable{
	
	    private final static Logger LOGGER = LoggerFactory.getLogger(SendUserNotificationThread.class);
		
		@Autowired
		private IFireBaseNotificationService fireBaseNotificationService;
		
		private FireBaseNotificationDTO fireBaseNotificationDTO;
		
		private List<String> tokenList;
		
		public void setData(FireBaseNotificationDTO fireBaseNotificationDTO, List<String> tokenList){
			   this.fireBaseNotificationDTO = fireBaseNotificationDTO;
			   this.tokenList = tokenList;
		}
		
		public void run() {
				try {
					fireBaseNotificationService.sendFireBaseNotification(fireBaseNotificationDTO, tokenList);
				} catch (Exception e) {
					LOGGER.error("error in send user notification data => "+e);
				}
		}
}
