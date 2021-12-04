package com.cratas.mpls.dao;

import java.util.List;

import com.cratas.mpls.web.dto.AppNotificationDTO;

/**
 * 
 * @author sunil
 *
 */
public interface IAppNotificationDao {

	   int registerFCMToken(AppNotificationDTO appNotificationDTO);
	   
	   AppNotificationDTO findByDeviceId(String deviceId);
	   
	   List<String> getMerchantTokenByMId(int mId);
	   
	   List<String> getCustomerTokenByCId(int getcId);
}
