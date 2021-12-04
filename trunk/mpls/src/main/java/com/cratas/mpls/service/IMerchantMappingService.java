package com.cratas.mpls.service;

import java.util.List;

import com.cratas.mpls.web.dto.CustomerDTO;
import com.cratas.mpls.web.dto.MerchantDTO;
import com.cratas.mpls.web.dto.MerchantMappingDTO;


/**
 * 
 * @author mukesh
 *
 */
public interface IMerchantMappingService {
	
	   int saveMerchantMapping(MerchantMappingDTO merchantMapping, String groupId);
	   
	   List<MerchantMappingDTO> getCustomerByMerchant(int mId);

	   List<MerchantMappingDTO> getMerchantMappingByCustomer(int cId);
	   
	   MerchantMappingDTO getMerchantMappingInstance(CustomerDTO customer, MerchantDTO merchant, String createdBy);

	   int deleteMerchantMapping(String updatedBy, String merchantId, String cId);
	   
	   List<MerchantMappingDTO> getCustomersByMerchant(int mId, String fromDate, String toDate, int isActive);
	   
	   List<String> getCustomerTokenListByMId(int mId);
	   
	   MerchantMappingDTO getCustomerByMN(int mId, String mobileNumber);

	   int updateMerchantMapping(int cId, int mId);
	   
	   public int updateMerchantMappingWithLoyaltyNumber(int cId, int mId,String loyaltyCardNumber,int isActive);

	   List<MerchantMappingDTO> getCustomerBal();

	   int deleteMerchantMapping(int cId, int mId);

}
