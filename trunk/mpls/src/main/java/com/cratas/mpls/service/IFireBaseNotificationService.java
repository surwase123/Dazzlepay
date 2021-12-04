package com.cratas.mpls.service;

import java.util.List;

import com.cratas.mpls.web.dto.FireBaseNotificationDTO;

/**
 * 
 * @author mukesh
 *
 */
public interface IFireBaseNotificationService {
	
	   void sendFireBaseNotification(FireBaseNotificationDTO fireBaseNotificationDTO, List<String> tokenList);

}
