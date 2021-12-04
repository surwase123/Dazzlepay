package com.cratas.mpls.service;

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
public interface IMerchantService {
	
	   int saveMerchant(UserDTO loginUserDTO, MerchantDTO merchant);
	   
	   MerchantDTO isExistsMerchant(String mName, String aadharNumber, String GSTIN, String emailId, String mobileNumber);
	   	   	   
	   MerchantDTO getMerchantByMId(String merchantId);
	   
	   MerchantDTO getMerchantById(int id);
	   
	   int deleteAddress(int mid);
	   
	   int updateMerchant(MerchantDTO merchantDTO);
	   
	   int deleteMerchant(MerchantDTO merchantDTO);
	   
	   List<MerchantAddressDTO> getMerchantAddresss(int id);
	   
	   List<MerchantDTO> getAllMerchant();
	   
	   boolean isMerchant(String emailId, String mobileNumber);
	   
	   List<MerchantDTO> recentMerchantList(int limit, UserDTO user);
	   
	   List<MerchantDTO> filterMerchantListByArea(String area, UserDTO user);

	   List<MerchantDTO> customerMerchantMappingList(int id);

	   List<MerchantDTO> getMerchantDetails(String merchantSearchMenu, String searchValue);

	   int updateMerchantStatus(SearchUserDTO searchUserDTO);

	   List<MerchantDTO> getMerchantUserDetail(int id);

	   List<MerchantDTO> getMerchantByPanNumber(String panNumber);

	   List<MerchantDTO> getMerchantByGSTIN(String gstin);
	   
	   int unBlockMerchantUser(SearchUserDTO searchUser);

	   List<MerchantDTO> getMerchantByEmailId(String emailId);

	   List<MerchantDTO> getMerchantByMobileNo(String mobile);
	   	   
	   int getBasicMerchantPlanId();
	   
	   int updateMerchantPlan(MerchantDTO merchantRequest);
	   
	   int saveMerchantDetails(MerchantDTO merchantDTO);

	   int updateMerchantFlag(MerchantDTO merchantDTO);
	   
	   MerchantDTO getMerchantByMN(String mobileNumber);

	   List<MerchantDTO> filterMerchantByAreaPin(String areaId, String pinCode);

	   List<MerchantAddressDTO> getMerchantAddress();

	   int getTotalMerchant();

	   int getActiveMerchant(String fromDate, String toDate);

	   int getRecentMerchant(String fromDate, String toDate);

	   List<MerchantDTO> getMerchantWalletBal();
}
