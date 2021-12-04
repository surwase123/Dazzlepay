package com.cratas.mpls.dao;

import java.util.List;

import com.cratas.mpls.web.dto.MerchantAddressDTO;
import com.cratas.mpls.web.dto.MerchantDTO;
import com.cratas.mpls.web.dto.SearchUserDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author mukesh
 *
 */
public interface IMerchantDao {
	
	   int saveMerchant(MerchantDTO merchant, UserDTO userDetails);
	   
	   MerchantDTO isExistsMerchant(String mName, String panNumber, String GSTIN, String emailId, String mobileNumber);
	   	   	   
	   MerchantDTO getMerchantByMId(String merchantId);
	   
	   MerchantDTO getMerchantById(int id);
	   
	   int deleteAddress(int mid);
	   
	   int updateMerchant(MerchantDTO merchantDTO);
	   
	   int deleteMerchant(MerchantDTO merchantDTO);
	   
	   List<MerchantAddressDTO> getMerchantAddresss(int id);
	   
	   List<MerchantDTO> getAllMerchant();
	   
	   boolean isMerchant(String emailId, String mobileNumber);
	   
	   List<MerchantDTO> recentMerchantList(int limit);
	   
	   List<MerchantDTO> filterMerchantListByArea(String area);

	   List<MerchantDTO> customerMerchantMappingList(int id);

	   List<MerchantDTO> getMerchantDetails(String merchantSearchMenu, String searchValue);
    
	   int updateMerchantStatus(SearchUserDTO searchUserDTO);
	   
	   List<MerchantDTO> getMerchantUserDetail(int id);
	   
	   List<MerchantDTO> getMerchantByPanNumber(String pANNumber);
	   
	   List<MerchantDTO> getMerchantByGSTIN(String gstin);
	   
	   int unBlockMerchantUser(SearchUserDTO searchUser);

	   List<MerchantDTO> getMerchantByEmailId(String emailId);
	   
	   List<MerchantDTO> getMerchantByMobileNo(String mobile);
	   
	   public int updateMerchantPlan(MerchantDTO merchantDTO);
	   
	   int saveMerchantDetails(MerchantDTO merchantDTO);
	   
	   int updateMerchantFlag(MerchantDTO merchantDTO);

	   MerchantDTO getMerchantByMN(String mobileNumber);

	   List<MerchantDTO> filterMerchantByAreaPin(String areaId, String pinCode);
	   
	   List<MerchantAddressDTO> getMerchantAddress();
	   
	   int getTotalMerchant();

	   int getActiveMerchant(String fromDate, String toDate);

	   int getRecentMerchant(String fromDate, String toDate);

	   List<MerchantDTO> getMerchantWalletBal();

	   List<MerchantDTO> mappingRequestSendList(int id);

}
