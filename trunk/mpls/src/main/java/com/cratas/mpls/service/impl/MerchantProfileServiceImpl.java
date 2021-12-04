package com.cratas.mpls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cratas.mpls.dao.IMerchantProfileDao;
import com.cratas.mpls.service.IMerchantProfileService;
import com.cratas.mpls.web.dto.MerchantAddressDTO;
import com.cratas.mpls.web.dto.MerchantDTO;
import com.cratas.mpls.web.dto.MerchantUserDTO;

/**
 * 
 * @author bhupendra
 *
 */
@Service
public class MerchantProfileServiceImpl implements IMerchantProfileService{

		@Autowired
		private IMerchantProfileDao profileDao;
		
		public MerchantUserDTO getMerchantId(int userId) {
			   return profileDao.getMerchantId(userId);
		}
	
		public MerchantDTO getMerchantDetails(int mid) {
			   return profileDao.getMerchantDetails(mid);
		}

		public List<MerchantAddressDTO> getAddressDetails(int mid) {
			   return profileDao.getAddressDetails(mid);
		}
		
		public MerchantUserDTO getMerchantUserIdByMID(int mId) {
			   return profileDao.getMerchantUserIdByMID(mId);
		}

}
