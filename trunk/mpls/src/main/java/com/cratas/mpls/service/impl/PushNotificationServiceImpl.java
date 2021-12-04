package com.cratas.mpls.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.constant.EmailConstants;
import com.cratas.mpls.common.constant.PushNotificationMSGDTO;
import com.cratas.mpls.common.utility.SendUserNotificationThread;
import com.cratas.mpls.service.IAppNotificationService;
import com.cratas.mpls.service.ICustomerService;
import com.cratas.mpls.service.IMerchantMappingService;
import com.cratas.mpls.service.IMerchantService;
import com.cratas.mpls.service.IPushNotificationService;
import com.cratas.mpls.web.controller.StateController;
import com.cratas.mpls.web.dto.CustomerDTO;
import com.cratas.mpls.web.dto.CustomerNotificationDTO;
import com.cratas.mpls.web.dto.FireBaseNotificationDTO;
import com.cratas.mpls.web.dto.LoyaltyCashbackDTO;
import com.cratas.mpls.web.dto.MerchantDTO;
import com.cratas.mpls.web.dto.MerchantMappingDTO;
import com.cratas.mpls.web.dto.RequestLoyaltyCardDTO;

/**
 * 
 * @author sunil
 *
 */
@Service
public class PushNotificationServiceImpl implements IPushNotificationService{
	
	   private final static Logger LOGGER = LoggerFactory.getLogger(StateController.class);
	
	   @Autowired
	   private SendUserNotificationThread sendUserNotificationThread;
		
	   @Autowired
	   private ThreadPoolTaskExecutor taskExecutor;
	    
	   @Autowired
	   private IMerchantMappingService merchantMappingService;
	   
	   @Autowired
	   private IMerchantService merchantService;
	   
	   @Autowired
	   private IAppNotificationService appNotificationService;
	   
	   @Autowired
	   private ICustomerService customerService;
	
	   public void sendCashbackOfferNotification(LoyaltyCashbackDTO loyatyCashbackDTO) {
		      try{
				  List<String> tokenList = merchantMappingService.getCustomerTokenListByMId(loyatyCashbackDTO.getmId());
				  MerchantDTO merchant = merchantService.getMerchantById(loyatyCashbackDTO.getmId());
				  if(!tokenList.isEmpty() && merchant != null) {
					  String title = loyatyCashbackDTO.getOfferCode() + Constants.HYFUN + loyatyCashbackDTO.getOfferName();
					  FireBaseNotificationDTO fireBaseNotificationDTO = getFirebaseNotificationObj(loyatyCashbackDTO, merchant.getIcon(), title, Constants.LOYALTY_CASHBACK_MERCHANT);
					  sendUserNotificationThread.setData(fireBaseNotificationDTO, tokenList);
					  taskExecutor.execute(sendUserNotificationThread);
				  }
		      }catch(Exception e){
		    	  LOGGER.info("Error in Send cashback offer Notification -- "+e.getMessage());
		      }
	   }
	   
	   public void sendMerchantMappingNotification(MerchantMappingDTO merchantMapping) {
		      try{
				  List<String> tokenList = appNotificationService.getMerchantTokenByMId(merchantMapping.getmId());
				  MerchantDTO merchant = merchantService.getMerchantById(merchantMapping.getmId());
				  CustomerDTO customer = customerService.getCustomerByCId(merchantMapping.getcId());
				  if(!tokenList.isEmpty() && merchant != null && null != customer) {
					  String title = PushNotificationMSGDTO.CUSTOMER_MERCHANT_MAPPING_TITLE.replace(EmailConstants.NAME, customer.getFirstName() + Constants.SPACE + customer.getLastName());
					  FireBaseNotificationDTO fireBaseNotificationDTO = getFirebaseNotificationObj(merchantMapping, title, Constants.MERCHANT_CUSTOMER);
					  sendUserNotificationThread.setData(fireBaseNotificationDTO, tokenList);
					  taskExecutor.execute(sendUserNotificationThread);
				  }
		      }catch(Exception e){
		    	  LOGGER.info("Error in Send Merchant Mapping Notification -- "+e.getMessage());
		      }
	   }
	   
	   public void sendCollectMoneyNotification(CustomerNotificationDTO customerNotification) {
			  try{
				  List<String> tokenList = appNotificationService.getCustomerTokenByCId(customerNotification.getcId());
				  MerchantDTO merchant = merchantService.getMerchantById(customerNotification.getmId());
				  CustomerDTO customer = customerService.getCustomerByCId(customerNotification.getcId());
				  if(merchant != null && null != customer) {
					  String title = merchant.getMerchantName() + Constants.SPACE + PushNotificationMSGDTO.COLLECT_MONEY_REQUEST + Constants.SPACE + "Rs "+customerNotification.getAmount();
					  FireBaseNotificationDTO fireBaseNotificationDTO = getFirebaseNotificationObj(customerNotification, title, Constants.COLLECT_MONEY);
					  sendUserNotificationThread.setData(fireBaseNotificationDTO, tokenList);
					  taskExecutor.execute(sendUserNotificationThread);
				  }
			  }catch(Exception e){
			    	  LOGGER.info("Error in Send Collect Money Notification -- "+e.getMessage());
			  }
	   }
	   
	   public void sendRefundMoneyNotification(CustomerNotificationDTO customerNotification) {
			  try{
				  List<String> tokenList = appNotificationService.getCustomerTokenByCId(customerNotification.getcId());
				  MerchantDTO merchant = merchantService.getMerchantById(customerNotification.getmId());
				  CustomerDTO customer = customerService.getCustomerByCId(customerNotification.getcId());
				  if(merchant != null && null != customer) {
					  String title = merchant.getMerchantName() + Constants.SPACE + PushNotificationMSGDTO.REFUND_MONEY_REQUEST + Constants.SPACE + "Rs "+customerNotification.getAmount();
					  FireBaseNotificationDTO fireBaseNotificationDTO = getFirebaseNotificationObj(customerNotification, title, Constants.REFUND_MONEY);
					  sendUserNotificationThread.setData(fireBaseNotificationDTO, tokenList);
					  taskExecutor.execute(sendUserNotificationThread);
				  }
			  }catch(Exception e){
			    	  LOGGER.info("Error in Send Refund Money Notification -- "+e.getMessage());
			  }
	   }
	   
	   public void sendRequestLoyaltyCardNotification(RequestLoyaltyCardDTO requestLoyaltyCard) {
			  try{
				  List<String> tokenList = appNotificationService.getMerchantTokenByMId(requestLoyaltyCard.getmId());
				  MerchantDTO merchant = merchantService.getMerchantById(requestLoyaltyCard.getmId());
//				  CustomerDTO customer = customerService.getCustomerByCId(customerNotification.getcId());
				  if(merchant != null) {
					  String title = merchant.getMerchantName()+ Constants.SPACE+PushNotificationMSGDTO.CARD_REQUEST.replace("number",String.valueOf(requestLoyaltyCard.getQuantityOfCards()));
					  FireBaseNotificationDTO fireBaseNotificationDTO = getFirebaseNotificationObj(requestLoyaltyCard, title, Constants.REQUEST_LOYALTY_CARD_NOTIFICATION,Constants.WAY_THREE,0);
					  sendUserNotificationThread.setData(fireBaseNotificationDTO, tokenList);
					  taskExecutor.execute(sendUserNotificationThread);
				  }
			  }catch(Exception e){
			    	  LOGGER.info("Error in Send Request Loyalty Card Notification -- "+e.getMessage());
			  }
	   }
	   
	   @Override
		public void sendLoyaltyCardNotification(RequestLoyaltyCardDTO requestLoyaltyCard, String notificationType) {
		   try{
				  MerchantDTO merchant = merchantService.getMerchantById(requestLoyaltyCard.getmId());
				  if(merchant != null) {
					  String title="" ;
					  if(notificationType.equals(Constants.REJECT_REQUEST_NOTIFICATION)) {
						 
						   title = merchant.getMerchantName()+ Constants.SPACE+PushNotificationMSGDTO.CARD_REQUEST_REJECTD.replace("Number",String.valueOf(requestLoyaltyCard.getQuantityOfCards()));
					  }
					  else if(notificationType.equals(Constants.ACCEPT_REQUEST_NOTIFICATION)) {
						 
						   title = merchant.getMerchantName()+ Constants.SPACE+PushNotificationMSGDTO.CARD_REQUEST_ACCEPTED.replace("Number",String.valueOf(requestLoyaltyCard.getQuantityOfCards()));		 
					  }
					  else if(notificationType.equals(Constants.ALLOCATE_REQUEST_NOTIFICATION)) {
						 
						   title = merchant.getMerchantName()+ Constants.SPACE+PushNotificationMSGDTO.CARD_REQUEST_ALLOCATED.replace("Number",String.valueOf(requestLoyaltyCard.getQuantityOfCards())); 
					  }
					
					  
					  List<String> emptyList=new ArrayList<String>();
					  FireBaseNotificationDTO fireBaseNotificationDTO = getFirebaseNotificationObj(requestLoyaltyCard, title, Constants.REQUEST_LOYALTY_CARD_NOTIFICATION,Constants.WAY_FOUR,1);
					  sendUserNotificationThread.setData(fireBaseNotificationDTO, emptyList);
					  taskExecutor.execute(sendUserNotificationThread);
				  }
			  }catch(Exception e){
			    	  LOGGER.info("Error in Send  Loyalty Card Notification -- "+e.getMessage());
			  }
			
		}
	   
	   
	   @Override
		public void sendLoyaltyCardAllocationNotification(RequestLoyaltyCardDTO requestLoyaltyCard, String notificationType) {
		   try{
				  MerchantDTO merchant = merchantService.getMerchantById(requestLoyaltyCard.getmId());
				  if(merchant != null) {
					  String title="" ;
					 if(notificationType.equals(Constants.ALLOCATE_LOYALTY_CARD_NOTIFICATION)) {
						  title=title = merchant.getMerchantName()+ Constants.SPACE+PushNotificationMSGDTO.ALLOCATED_CARD_NUMBER.replace("number",String.valueOf(requestLoyaltyCard.getLoyaltyCardNumber())); 
					  }
					  
					  List<String> emptyList=new ArrayList<String>();
					  FireBaseNotificationDTO fireBaseNotificationDTO = getFirebaseNotificationObj(requestLoyaltyCard, title, Constants.ALLOCATE_LOYALTY_CARD_NOTIFICATION,Constants.WAY_TWO,1);
					  sendUserNotificationThread.setData(fireBaseNotificationDTO, emptyList);
					  taskExecutor.execute(sendUserNotificationThread);
				  }
			  }catch(Exception e){
			    	  LOGGER.info("Error in Send  Loyalty Card Notification -- "+e.getMessage());
			  }
			
		}
	   
	   private FireBaseNotificationDTO getFirebaseNotificationObj(CustomerNotificationDTO customerNotification, String title, String type) {
		   	   FireBaseNotificationDTO fireBaseNotification = new FireBaseNotificationDTO();
		   	   fireBaseNotification.setcId(customerNotification.getcId());
		   	   fireBaseNotification.setId(customerNotification.getId());
		   	   fireBaseNotification.setmId(customerNotification.getmId());
//		   	   fireBaseNotification.setTitle(title);
//		   	   fireBaseNotification.setType(type);
		   	buildFireBaseNotificationDTO(fireBaseNotification, null, title, type);
		   	   return fireBaseNotification;
	   }
	   
	   private FireBaseNotificationDTO getFirebaseNotificationObj(MerchantMappingDTO merchantMapping, String title, String type) {
			   FireBaseNotificationDTO fireBaseNotification = new FireBaseNotificationDTO();
		   	   fireBaseNotification.setmId(merchantMapping.getmId());
		   	   fireBaseNotification.setcId(merchantMapping.getcId());
//		   	   fireBaseNotification.setTitle(title);
//		   	   fireBaseNotification.setType(type);
		   	buildFireBaseNotificationDTO(fireBaseNotification, null, title, type);
		   	   return fireBaseNotification;
		}
	   private FireBaseNotificationDTO getFirebaseNotificationObj(RequestLoyaltyCardDTO requestLoyaltyCard, String title, String type,int notficationWay,int readOnly) {
		   FireBaseNotificationDTO fireBaseNotification = new FireBaseNotificationDTO();
		   if(notficationWay==Constants.WAY_FOUR) {
			   fireBaseNotification.setmId(requestLoyaltyCard.getmId());
		   }
		   else if(notficationWay==Constants.WAY_TWO) {
			   fireBaseNotification.setcId(requestLoyaltyCard.getcId());
		   }
	   	  
	   	   fireBaseNotification.setNotificationWay(notficationWay);
	   	   fireBaseNotification.setIsReadOnly(readOnly);
//	   	   fireBaseNotification.setcId(requestLoyaltyCard.getcId());
//	   	   fireBaseNotification.setTitle(title);
//	   	   fireBaseNotification.setType(type);
	   	   buildFireBaseNotificationDTO(fireBaseNotification, null, title, type);
	   	   return fireBaseNotification;
	}
	   
	   private FireBaseNotificationDTO getFirebaseNotificationObj(LoyaltyCashbackDTO loyatyCashbackDTO, String icon, String title, String type) {
		   FireBaseNotificationDTO fireBaseNotification = new FireBaseNotificationDTO();
	   	   fireBaseNotification.setmId(loyatyCashbackDTO.getmId());
//	   	   fireBaseNotification.setImageUrl(icon);
//	   	   fireBaseNotification.setTitle(title);
//	   	   fireBaseNotification.setType(type);
	   	   buildFireBaseNotificationDTO(fireBaseNotification, icon, title, type);
	   	   return fireBaseNotification;
}
	   
	   private void buildFireBaseNotificationDTO(FireBaseNotificationDTO fireBaseNotification, String icon, String title, String type) {
		   fireBaseNotification.setImageUrl(icon);
	   	   fireBaseNotification.setTitle(title);
	   	   fireBaseNotification.setType(type);
	   }
	public void sendCustomerRegNotification(MerchantMappingDTO merchantMapping) {
		   try{
			   List<String> tokenList = appNotificationService.getCustomerTokenByCId(merchantMapping.getcId());
			   MerchantDTO merchant = merchantService.getMerchantById(merchantMapping.getmId());
			   CustomerDTO customer = customerService.getCustomerByCId(merchantMapping.getcId());
			   if(merchant != null && null != customer) {
//				   String title = merchant.getMerchantName() + Constants.SPACE + PushNotificationMSGDTO.MAPPING_REQUEST;
				   String title = merchant.getMerchantName() + Constants.SPACE + PushNotificationMSGDTO.CUSTOMER_ADD_NOTIFICATION;
				   FireBaseNotificationDTO fireBaseNotificationDTO = getFirebaseNotificationObj(customer, merchant, title, Constants.MERCHANT_MAPPING_REQUSET);
				   sendUserNotificationThread.setData(fireBaseNotificationDTO, tokenList);
				   taskExecutor.execute(sendUserNotificationThread);
			   }
		   }catch(Exception e){
			    	  LOGGER.info("Error in Send Customer Reg Notification -- "+e.getMessage());
		   }
	}

	private FireBaseNotificationDTO getFirebaseNotificationObj(CustomerDTO customer, MerchantDTO merchant, String title, String type) {
		    FireBaseNotificationDTO fireBaseNotification = new FireBaseNotificationDTO();
		    fireBaseNotification.setId(merchant.getId());
		    fireBaseNotification.setcId(customer.getId());
	   	    fireBaseNotification.setmId(merchant.getId());
	   	    fireBaseNotification.setCustomer(customer);
	   	    fireBaseNotification.setIsReadOnly(Constants.ACTIVE);
	   	    fireBaseNotification.setTitle(title);
	   	    fireBaseNotification.setType(type);
	   	    return fireBaseNotification;
	}

	public void sendMerchantRegNotification(MerchantMappingDTO merchantMapping) {
		   try{
			   List<String> tokenList = appNotificationService.getMerchantTokenByMId(merchantMapping.getmId());
			   MerchantDTO merchant = merchantService.getMerchantById(merchantMapping.getmId());
			   CustomerDTO customer = customerService.getCustomerByCId(merchantMapping.getcId());
			   if(merchant != null && null != customer) {
				   String title = customer.getFirstName() + Constants.SPACE + PushNotificationMSGDTO.MAPPING_REQUEST;
				   FireBaseNotificationDTO fireBaseNotificationDTO = getMerchantFirebaseObj(customer, merchant, title, Constants.CUSTOMER_MAPPING_REQUSET);
				   sendUserNotificationThread.setData(fireBaseNotificationDTO, tokenList);
				   taskExecutor.execute(sendUserNotificationThread);
			   }
		    }catch(Exception e){
			    	  LOGGER.info("Error in Send Merchant Reg Notification -- "+e.getMessage());
		    }
		
	}

	private FireBaseNotificationDTO getMerchantFirebaseObj(CustomerDTO customer, MerchantDTO merchant, String title, String type) {
			FireBaseNotificationDTO fireBaseNotification = new FireBaseNotificationDTO();
			fireBaseNotification.setId(customer.getId());
			fireBaseNotification.setcId(customer.getId());
			fireBaseNotification.setmId(merchant.getId());
			fireBaseNotification.setMerchant(merchant);
			fireBaseNotification.setTitle(title);
			fireBaseNotification.setType(type);
			return fireBaseNotification;
	}
}
