package com.cratas.mpls.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cratas.mpls.common.query.MerchantQuery;
import com.cratas.mpls.common.query.MerchantProfileQuery;
import com.cratas.mpls.dao.IMerchantProfileDao;
import com.cratas.mpls.web.dto.MerchantAddressDTO;
import com.cratas.mpls.web.dto.MerchantDTO;
import com.cratas.mpls.web.dto.MerchantUserDTO;

/**
 * 
 * @author bhupendra
 *
 */
@Repository
public class MerchantProfileDaoImpl implements IMerchantProfileDao{

		@Autowired
		private JdbcTemplate jdbcTemplate;
	
		public MerchantUserDTO getMerchantId(int userId) {
			   Object[] args = { userId };
			   List<MerchantUserDTO> merchantUserList = jdbcTemplate.query(MerchantProfileQuery.GET_MERCHANT_ID, args, new BeanPropertyRowMapper<MerchantUserDTO>(MerchantUserDTO.class));
			   if(!merchantUserList.isEmpty()){
		    	   return merchantUserList.get(0);
		       }
		       return null;
		}
	
		public MerchantDTO getMerchantDetails(int mid) {
			   Object args[] = {mid};
			   List<MerchantDTO> merchantList = jdbcTemplate.query(MerchantProfileQuery.GET_MERCHANT_DETAILS, args, new BeanPropertyRowMapper<MerchantDTO>(MerchantDTO.class));
		       if(!merchantList.isEmpty()){
		    	   return merchantList.get(0);
		       }
		       return null;
		}

		public List<MerchantAddressDTO> getAddressDetails(int mid) {
			   Object[] args = { mid };
               return jdbcTemplate.query(MerchantQuery.GET_MERCHANT_ADDRESS, args, new BeanPropertyRowMapper<MerchantAddressDTO>(MerchantAddressDTO.class));
		}

		public MerchantUserDTO getMerchantUserIdByMID(int mId) {
			   Object[] args = { mId };
			   List<MerchantUserDTO> merchantUserList = jdbcTemplate.query(MerchantProfileQuery.GET_MERCHANT_USERID_BY_MID, args, new BeanPropertyRowMapper<MerchantUserDTO>(MerchantUserDTO.class));
			   if(!merchantUserList.isEmpty()){
		    	   return merchantUserList.get(0);
		       }
		       return null;
		}
}
