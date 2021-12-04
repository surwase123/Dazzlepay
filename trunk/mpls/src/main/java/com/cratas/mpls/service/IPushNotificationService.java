package com.cratas.mpls.service;

import com.cratas.mpls.web.dto.CustomerNotificationDTO;
import com.cratas.mpls.web.dto.LoyaltyCashbackDTO;
import com.cratas.mpls.web.dto.MerchantMappingDTO;
import com.cratas.mpls.web.dto.RequestLoyaltyCardDTO;

/**
 * 
 * @author sunil
 *
 */
public interface IPushNotificationService {
	
	   void sendCashbackOfferNotification(LoyaltyCashbackDTO loyatyCashbackDTO);
	   
	   void sendMerchantMappingNotification(MerchantMappingDTO merchantMapping);
	   
	   void sendCollectMoneyNotification(CustomerNotificationDTO customerNotification);
	   	   
	   void sendRefundMoneyNotification(CustomerNotificationDTO customerNotification);
	   
	   void sendRequestLoyaltyCardNotification(RequestLoyaltyCardDTO requestLoyaltyCard);
	   
	   void sendLoyaltyCardNotification(RequestLoyaltyCardDTO requestLoyaltyCard,String notificationType);

	   void sendCustomerRegNotification(MerchantMappingDTO merchantMapping);

	   void sendMerchantRegNotification(MerchantMappingDTO merchantMapping);

	void sendLoyaltyCardAllocationNotification(RequestLoyaltyCardDTO requestLoyaltyCard, String notificationType);

}
