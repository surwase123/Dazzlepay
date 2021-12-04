package com.cratas.mpls.service;

import java.util.List;

import com.cratas.mpls.web.dto.AppNotificationDTO;

/**
 * 
 * @author sunil
 *
 */
public interface IAppNotificationService {
	   
	   int registerFCMToken(AppNotificationDTO appNotificationDTO);
	   
	   AppNotificationDTO findByDeviceId(String deviceId);
	   
	   List<String> getMerchantTokenByMId(int mId);
	   
	   List<String> getCustomerTokenByCId(int getcId);
	   
}
