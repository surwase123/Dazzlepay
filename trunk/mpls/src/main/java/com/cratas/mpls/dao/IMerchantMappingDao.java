package com.cratas.mpls.dao;

import java.util.List;

import com.cratas.mpls.web.dto.MerchantMappingDTO;

/**
 * 
 * @author mukesh
 *
 */
public interface IMerchantMappingDao {
	
	   int saveMerchantMapping(MerchantMappingDTO merchantMapping);
	   	   
	   List<MerchantMappingDTO> getCustomerByMerchant(int mId);

	   List<MerchantMappingDTO> getMerchantMappingByCustomer(int cId);

	   int deleteMerchantMapping(String updatedBy, String mId, String cId);
	   
	   List<MerchantMappingDTO> getCustomersByMerchant(int mId, String fromDate, String toDate, int isActive);
	   
	   List<String> getCustomerFCMTokenByMId(int mId);
	   
	   MerchantMappingDTO getCustomerByMN(int mId, String mobileNumber);
	   
	   int updateMerchantMapping(int cId, int mId);
	   
	   int updateMerchantMappingAddLoyaltyNumber(int cId, int mId,String LoyaltyCardNumber,int isActive);

	   List<MerchantMappingDTO> getCustomerBal();
	   
	   int deleteMerchantMapping(int cId, int mId);
}
