package com.cratas.mpls.common.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.service.IUserTrackingService;
import com.cratas.mpls.web.dto.UserTrackingRequestDTO;


/**
 * 
 * @author mukesh
 *
 */
@Component
@Scope(Constants.PROTOTYPE)
public class SaveUserTrackingDetailThread implements Runnable{

		private final static Logger LOGGER = LoggerFactory.getLogger(SaveUserTrackingDetailThread.class);
		
		@Autowired
		private IUserTrackingService userService;
		
		private UserTrackingRequestDTO userTrackingDTO;
		
		public void setData(UserTrackingRequestDTO userTrackingDTO){
			this.userTrackingDTO = userTrackingDTO;
		}
		
		/* This thread use to save user tracking event in database.
		 * (non-Javadoc)
		 * 
		 * case 1.) if user comes first time in a session then store users complete data with landing url.
		 *           set  SINGLEPATH in Tracking for identification. 
		 * 
		 * case 2.) store users complete tracking , all pages user visits in a single session.
		 * 			set MULTIPATH in Tracking for identification. 
		 * 
		 */
		public void run() {
				try {
					if(userTrackingDTO.getUrlType().equals(Constants.MULTIPATH)){
						userService.addUserCompleteTrackingDetail(userTrackingDTO);
					}
				} catch (Exception e) {
					LOGGER.error("error in store user tracking detail data => "+e);
				}
		}

}
