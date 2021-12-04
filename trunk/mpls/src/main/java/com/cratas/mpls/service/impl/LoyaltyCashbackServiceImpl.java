package com.cratas.mpls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.utility.SaveUserNotificationThread;
import com.cratas.mpls.dao.ILoyaltyCashbackDao;
import com.cratas.mpls.service.ILoyaltyCashbackService;
import com.cratas.mpls.service.IMPLSService;
import com.cratas.mpls.service.IMerchantTransactionService;
import com.cratas.mpls.service.IPushNotificationService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.LoyaltyCashbackDTO;
import com.cratas.mpls.web.dto.MerchantTransactionDTO;
import com.cratas.mpls.web.dto.NotificationDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author bhupendra
 *
 */
@Service
public class LoyaltyCashbackServiceImpl implements ILoyaltyCashbackService{

		@Autowired
		private SaveUserNotificationThread saveUserNotificationThread;
		
		@Autowired
		private ThreadPoolTaskExecutor taskExecutor;
		
		@Autowired
		private ILoyaltyCashbackDao loyaltyCashbackDao;
		
		@Autowired
	    private IMPLSService mplsService;
		
		@Autowired
		private IMerchantTransactionService merchantTransactionService;
		
		@Autowired
		private IUtilityService utilityService;
		
		@Autowired
		private IPushNotificationService pushNotificationService;
	
		public List<LoyaltyCashbackDTO> getLoyaltyCashbackByMId(int mId) {
			   return loyaltyCashbackDao.getLoyaltyCashbackByMId(mId);
		}
		
		public List<LoyaltyCashbackDTO> getLoyaltyCashback(){
			   return loyaltyCashbackDao.getLoyaltyCashback();
		}
	
		public int saveLoyaltyCashback(LoyaltyCashbackDTO loyatyCashbackDTO, UserDTO userDTO) {
			   int isInsert = loyaltyCashbackDao.saveLoyaltyCashback(loyatyCashbackDTO, userDTO);
			   if(isInsert == 1 && null != userDTO && userDTO.getGroupDTO().getGroupType().equals(Constants.MAKER)) {
				    LoyaltyCashbackDTO loyatyCashback = loyaltyCashbackDao.getLoyaltyCashbackByName(loyatyCashbackDTO.getmId(), loyatyCashbackDTO.getOfferName(), loyatyCashbackDTO.getOfferCode());
					NotificationDTO notification = mplsService.createUserNotificationObj(String.valueOf(loyatyCashback.getId()), loyatyCashback.getMenuName(), userDTO);
					mplsService.updateRecordStatus(notification.getTableName(), notification.getRecordId(), 0);
					saveUserNotificationThread.setData(notification,userDTO);
					taskExecutor.execute(saveUserNotificationThread);
					return isInsert;
			   }
			   pushNotificationService.sendCashbackOfferNotification(loyatyCashbackDTO);
			   return isInsert;
		}
	
		public int updateLoyaltyCashback(LoyaltyCashbackDTO loyatyCashbackDTO) {
			   return loyaltyCashbackDao.updateLoyaltyCashback(loyatyCashbackDTO);
		}
	
		public int deleteLoyaltyCashback(LoyaltyCashbackDTO loyatyCashbackDTO) {
			   return loyaltyCashbackDao.deleteLoyaltyCashback(loyatyCashbackDTO);
		}

		public LoyaltyCashbackDTO getLoyaltyCashbackById(int id) {
			   return loyaltyCashbackDao.getLoyaltyCashbackById(id);
		}
		
		public LoyaltyCashbackDTO getLoyaltyCashbackByCode(String offerCode) {
			   return loyaltyCashbackDao.getLoyaltyCashbackByCode(offerCode);
		}
		
		public String validateMerchantOffer(int mId, int cId, double amount, LoyaltyCashbackDTO loyaltyCashback){
			   if(loyaltyCashback.getTransCashbackType().equals(Constants.FIRST)){
			    	List<MerchantTransactionDTO> merchanTransactions = merchantTransactionService.getCustomerPayTransByMId(mId, cId);
			    	if(merchanTransactions.isEmpty()){
			    		return validateMinTransAmt(amount, loyaltyCashback);
			    	}
			    	return Constants.OFFER_VALID_ON_FIRST_TRANSACTION;  
			   }else if(loyaltyCashback.getTransCashbackType().equals(Constants.RECURRING)){
				    return validateMinTransAmt(amount, loyaltyCashback);
			   }	
			   return Constants.FAILURE;
		}
		
		private String validateMinTransAmt(double amount, LoyaltyCashbackDTO loyaltyCashback){
			    if(amount >= loyaltyCashback.getMinTransValue()){
	    		   return Constants.SUCCESS;
	    	    }
			    return Constants.ERROR_MIN_TRANSACTION_OFFER; 
		}
		
		public double getOfferCashbackAmt(double amount, LoyaltyCashbackDTO loyaltyCashback){
			   if(loyaltyCashback.getCashbackType().equals(Constants.FIXED)){
				     if(amount >= loyaltyCashback.getMinTransValue()){
				    	 return loyaltyCashback.getFixedCashbackAmt();
				     }
			   }else if(loyaltyCashback.getCashbackType().equals(Constants.VARIABLE)){
				     if(amount >= loyaltyCashback.getMinTransValue()){
				    	 int cashbackPercentage = utilityService.convertStringToInt(loyaltyCashback.getCashbackPercentage());
				    	 double cashbackAmt = ((amount * cashbackPercentage) / 100);
				    	 if(cashbackAmt > loyaltyCashback.getMaxCashbackAmt()){
				    		 return loyaltyCashback.getMaxCashbackAmt();
				    	 }
				    	 return cashbackAmt;
				     }
			   }
			   return 0;
		}
		

}
