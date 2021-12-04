package com.cratas.mpls.dao;

import java.util.List;

import com.cratas.mpls.web.dto.MerchantAddressDTO;
import com.cratas.mpls.web.dto.MerchantDTO;
import com.cratas.mpls.web.dto.MerchantUserDTO;
/**
 * 
 * @author bhupendra
 *
 */
public interface IMerchantProfileDao {

	    MerchantUserDTO getMerchantId(int userId);
		
		MerchantDTO getMerchantDetails(int mid);

		List<MerchantAddressDTO> getAddressDetails(int mid);
		
		MerchantUserDTO getMerchantUserIdByMID(int mId);
}
