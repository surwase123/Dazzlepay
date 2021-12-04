package com.cratas.mpls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.dao.IMerchantMappingDao;
import com.cratas.mpls.service.ILoyaltyCardService;
import com.cratas.mpls.service.IMerchantMappingService;
import com.cratas.mpls.service.IPushNotificationService;
import com.cratas.mpls.web.dto.CustomerDTO;
import com.cratas.mpls.web.dto.MerchantDTO;
import com.cratas.mpls.web.dto.MerchantMappingDTO;

/**
 * 
 * @author mukesh
 *
 */
@Service
public class MerchantMappingServiceImpl implements IMerchantMappingService {
	
	   @Autowired
	   private IMerchantMappingDao merchantMappingDao;
	   
	   @Autowired
	   private ILoyaltyCardService loyaltyCardService;
	   
	   @Autowired
	   private IPushNotificationService pushNotificationService;
	   
       public int saveMerchantMapping(MerchantMappingDTO merchantMapping, String groupId){
    	      int isInsert = merchantMappingDao.saveMerchantMapping(merchantMapping);
    	      if(isInsert == 1 && groupId.equals(Constants.CUSTOMER)) {
    	    	  loyaltyCardService.updateLoyaltyCardNumberByMid(merchantMapping.getmId(), merchantMapping.getcId(),  merchantMapping.getLoyaltyCardNumber(),  "CA", Constants.ACTIVE);
    	    	  pushNotificationService.sendCustomerRegNotification(merchantMapping);
    	      }else if(isInsert == 1 && groupId.equals(Constants.MERCHANT)) {
    	    	  pushNotificationService.sendMerchantRegNotification(merchantMapping);
    	      } 
    	      return isInsert;
    	      
       }
       
       public List<MerchantMappingDTO> getMerchantMappingByCustomer(int cId){
    	      return merchantMappingDao.getMerchantMappingByCustomer(cId);
       }
       
       public List<MerchantMappingDTO> getCustomerByMerchant(int mId){
    	      return merchantMappingDao.getCustomerByMerchant(mId);
       }
       
       public MerchantMappingDTO getMerchantMappingInstance(CustomerDTO customer, MerchantDTO merchant, String createdBy){
		   	  MerchantMappingDTO merchantMapping = new MerchantMappingDTO();
		   	  merchantMapping.setcId(customer.getId());
		   	  merchantMapping.setmId(merchant.getId());
		   	  merchantMapping.setLoyaltyCardNumber(customer.getLoyaltyCardNumber());
		   	  merchantMapping.setWalletId(merchant.getMerchantId() + customer.getCustomerId());
		   	  merchantMapping.setCreatedBy(createdBy);
		   	  merchantMapping.setIsActive(Constants.ZERO);
		   	  return merchantMapping;
       }

	   public int deleteMerchantMapping(String updatedBy, String mId, String cId) {
			  return merchantMappingDao.deleteMerchantMapping(updatedBy, mId, cId);
	   }
	   
	   public List<MerchantMappingDTO> getCustomersByMerchant(int mId, String fromDate, String toDate, int isActive){
		      return merchantMappingDao.getCustomersByMerchant(mId, fromDate, toDate, isActive);
	   }
	   
	   public List<String> getCustomerTokenListByMId(int mId){
		      return merchantMappingDao.getCustomerFCMTokenByMId(mId);
	   }
	   
	   public MerchantMappingDTO getCustomerByMN(int mId, String mobileNumber) {
		      return merchantMappingDao.getCustomerByMN(mId, mobileNumber);
	   }

		public int updateMerchantMapping(int cId, int mId) {
			   return merchantMappingDao.updateMerchantMapping(cId, mId);
		}
		public int updateMerchantMappingWithLoyaltyNumber(int cId, int mId,String loyaltyCardNumber,int isActive) {
			   return merchantMappingDao.updateMerchantMappingAddLoyaltyNumber(cId, mId,loyaltyCardNumber,isActive);
		}
		public List<MerchantMappingDTO> getCustomerBal() {
			   return merchantMappingDao.getCustomerBal();
		}

		public int deleteMerchantMapping(int cId, int mId) {
			   return merchantMappingDao.deleteMerchantMapping(cId, mId);
		}
	   

}
