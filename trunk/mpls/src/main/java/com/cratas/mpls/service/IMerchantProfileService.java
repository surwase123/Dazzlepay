package com.cratas.mpls.service;

import java.util.List;

import com.cratas.mpls.web.dto.MerchantAddressDTO;
import com.cratas.mpls.web.dto.MerchantDTO;
import com.cratas.mpls.web.dto.MerchantUserDTO;

/**
 * 
 * @author bhupendra
 *
 */
public interface IMerchantProfileService {
	
	    MerchantUserDTO getMerchantId(int userId);
		
		MerchantDTO getMerchantDetails(int mid);

		List<MerchantAddressDTO> getAddressDetails(int mid);
		
		MerchantUserDTO getMerchantUserIdByMID(int mId);
}
