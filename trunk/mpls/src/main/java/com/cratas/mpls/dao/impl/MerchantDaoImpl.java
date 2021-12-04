package com.cratas.mpls.dao.impl;


import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.query.MerchantQuery;
import com.cratas.mpls.dao.IMerchantDao;
import com.cratas.mpls.web.dto.MerchantAddressDTO;
import com.cratas.mpls.web.dto.MerchantDTO;
import com.cratas.mpls.web.dto.SearchUserDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author mukesh
 *
 */
@Repository
public class MerchantDaoImpl implements IMerchantDao {

	    @Autowired
	    private JdbcTemplate jdbcTemplate;
	    
	    @Autowired
	    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	    
	    public int saveMerchant(MerchantDTO merchant, UserDTO userDetails) {
		       try{
		    	   int mId = insertMerchant(merchant);
				   if(mId > 0) {
					   if(merchant.getmAddressList() != null) {
				    	    for (MerchantAddressDTO merchantAddress : merchant.getmAddressList()) {
			    	    	    merchantAddress.setmId(mId);
			    	    	    saveMerchantAddress(merchantAddress);
							}
					    }
			    	    saveMerchantUser(mId, userDetails.getId(), merchant.getCreatedBy());
			    	    return 1;
				   }
		       }catch(Exception e){
		    	   return 0;
		       }
		       return 0;
		}
	    
	    public MerchantDTO isExistsMerchant(String mName, String panNumber, String GSTIN, String emailId, String mobileNumber) {
			   Object args[] = { mName, panNumber, GSTIN, mobileNumber, emailId };
			   List<MerchantDTO> merchantList = jdbcTemplate.query(MerchantQuery.IS_EXISTING_MERCHANT, args, new BeanPropertyRowMapper<MerchantDTO>(MerchantDTO.class));
			   if(!merchantList.isEmpty()){
				    return merchantList.get(0);
			   }
			   return null;
		}
	    
	    public boolean isMerchant(String emailId, String mobileNumber) {
			   Object args[] = { mobileNumber, emailId };
			   List<MerchantDTO> merchantList = jdbcTemplate.query(MerchantQuery.IS_MERCHANT, args, new BeanPropertyRowMapper<MerchantDTO>(MerchantDTO.class));
			   if(!merchantList.isEmpty()){
				    return true;
			   }
			   return false;
		}
	    
	    private int insertMerchant(MerchantDTO merchantDTO) {
	    	KeyHolder keyHolder = new GeneratedKeyHolder();
			   jdbcTemplate.update(connection -> {
		                PreparedStatement ps = connection.prepareStatement(MerchantQuery.SAVE_MERCHANT,Statement.RETURN_GENERATED_KEYS);
		                ps.setString(1, merchantDTO.getMerchantId());
		                ps.setString(2, merchantDTO.getCategoryCode());
		                ps.setString(3, merchantDTO.getMerchantName());
		                ps.setString(4, merchantDTO.getIcon());
		                ps.setString(5, merchantDTO.getFirstName());
		                ps.setString(6, merchantDTO.getMiddleName());
		                ps.setString(7, merchantDTO.getLastName());
		                ps.setString(8, merchantDTO.getEmailId());
		                ps.setString(9, merchantDTO.getAboutMe());
		                ps.setString(10, merchantDTO.getPANNumber());
		                ps.setString(11, merchantDTO.getGSTIN());
		                ps.setInt(12, merchantDTO.getCountryId());
		                ps.setInt(13, merchantDTO.getCurrencyCode());
		                ps.setString(14, merchantDTO.getMerchantNumber());
		                ps.setInt(15, merchantDTO.getMerchantPlanId());
		                ps.setString(16, merchantDTO.getCreatedBy());
		                return ps;
		       }, keyHolder); 
		       return keyHolder.getKey().intValue();
		}
		
	    private int saveMerchantAddress(MerchantAddressDTO merchantAddressDTO) {
			   BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(merchantAddressDTO);
			   return namedParameterJdbcTemplate.update(MerchantQuery.SAVE_MERCHANT_ADDRESS, params);
	    }
	    
	    public int saveMerchantUser(int mId, int userId, String createdBy) {
			   Object args[] = { mId, userId, createdBy, 1 };
			   return jdbcTemplate.update(MerchantQuery.SAVE_MERCHANT_USER, args);
	    }

		
		public MerchantDTO getMerchantByMId(String merchantId) {
			   Object args[] = { merchantId };
			   List<MerchantDTO> merchantList = jdbcTemplate.query(MerchantQuery.GET_MERCHANT_BY_MID, args, new BeanPropertyRowMapper<MerchantDTO>(MerchantDTO.class));
			   if(!merchantList.isEmpty()){
			          return merchantList.get(0);
			   }
			   return null;		
		}
		
		public MerchantDTO getMerchantById(int id) {
			   Object args[] = { id };
			   List<MerchantDTO> merchantList = jdbcTemplate.query(MerchantQuery.GET_MERCHANT_BY_ID, args, new BeanPropertyRowMapper<MerchantDTO>(MerchantDTO.class));
			   if(!merchantList.isEmpty()){
			      return merchantList.get(0);
			   }
			   return null;		
		}
		
		public int deleteAddress(int mid) {
			   Object args[] = {mid};
			   return jdbcTemplate.update(MerchantQuery.DELETE_MERCHANT_ADDRESS,args);
		}
		
		public int updateMerchant(MerchantDTO merchantDTO) {
			   try{
				   //if(isExistsMerchantById(merchantDTO) == null){
					   BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(merchantDTO);
					   namedParameterJdbcTemplate.update(MerchantQuery.UPDATE_MERCHANT, params);
					   deleteAddress(merchantDTO.getId());
			    	   for(MerchantAddressDTO merchantAddress : merchantDTO.getmAddressList()) {
			    	        merchantAddress.setmId(merchantDTO.getId());
			    	        saveMerchantAddress(merchantAddress);
					   }
			    	   return 1;
				  // }
				  // return 2;
		       }catch(Exception e){
		    	   return 0;
		       }
		}
		
		public MerchantDTO isExistsMerchantById(MerchantDTO merchant) {
			   Object args[] = { merchant.getId(), merchant.getMerchantName(), merchant.getPANNumber(), merchant.getGSTIN(), merchant.getMobileNumber(), merchant.getEmailId() };
			   List<MerchantDTO> merchantList = jdbcTemplate.query(MerchantQuery.IS_EXISTING_MERCHANT_BY_ID, args, new BeanPropertyRowMapper<MerchantDTO>(MerchantDTO.class));
			   if(!merchantList.isEmpty()){
				    return merchantList.get(0);
			   }
			   return null;
		}
		
		public int deleteMerchant(MerchantDTO merchantDTO) {
			   Object args[] = { merchantDTO.getId() };
			   int isDeleted = jdbcTemplate.update(MerchantQuery.DELETE_MERCHANT,args);
			   if(isDeleted > 0){
				   updateAddress(merchantDTO.getId());
			   }
			   return isDeleted;
		}
		
		public int updateAddress(int mid) {
			   Object args[] = { mid };
			   return jdbcTemplate.update(MerchantQuery.UPDATE_MERCHANT_ADDRESS,args);
		}
	    
	    public List<MerchantAddressDTO> getMerchantAddresss(int id) {
	    	   Object[] args = { id };
               return jdbcTemplate.query(MerchantQuery.GET_MERCHANT_ADDRESS, args, new BeanPropertyRowMapper<MerchantAddressDTO>(MerchantAddressDTO.class));
	    }
	
	    public List<MerchantDTO> getAllMerchant() {
	           return jdbcTemplate.query(MerchantQuery.GET_ALL_MERCHANT, new BeanPropertyRowMapper<MerchantDTO>(MerchantDTO.class));
	    }
	    
	    public List<MerchantDTO> recentMerchantList(int limit) {
	    	   Object[] args = { limit };
	           return jdbcTemplate.query(MerchantQuery.RECENT_MERCHANT_LIST, args, new BeanPropertyRowMapper<MerchantDTO>(MerchantDTO.class));
	    }
	    
	    public List<MerchantDTO> filterMerchantListByArea(String areaId) {
	    	   String query = MerchantQuery.RECENT_MERCHANT_LIST_BY_AREA + " md.areaCode LIKE " + Constants.SPACE + Constants.QUOTE + Constants.PERCENTAGE + areaId + Constants.PERCENTAGE + Constants.QUOTE + " GROUP BY m.id order by m.id desc";
	           return jdbcTemplate.query(query, new BeanPropertyRowMapper<MerchantDTO>(MerchantDTO.class));
	    }

		public List<MerchantDTO> customerMerchantMappingList(int userId) {
			   Object[] args = { userId };
	           return jdbcTemplate.query(MerchantQuery.CUSTOMER_MERCHANT_MAPPING_BY_CID, args, new BeanPropertyRowMapper<MerchantDTO>(MerchantDTO.class));
		}

		public List<MerchantDTO> getMerchantDetails(String merchantSearchMenu, String searchValue) {
			   String query = MerchantQuery.GET_MERCHANT_DETAIL;
			   Object args[] = { searchValue };
			   if(merchantSearchMenu.equals(Constants.LOGIN_ID)  || merchantSearchMenu.equals(Constants.MOBILE_NUMBER)) {
				   query += " where " + Constants.CONSTANT_U + merchantSearchMenu + " = " + "?" + " GROUP BY " + Constants.CONSTANT_M + Constants.ID;
			   }else {
				   query += " where " + Constants.CONSTANT_M + merchantSearchMenu + " = " + "?" + " GROUP BY " + Constants.CONSTANT_M + Constants.ID;
			   }
			   return jdbcTemplate.query(query,  args, new BeanPropertyRowMapper<MerchantDTO>(MerchantDTO.class));
		}

		public int updateMerchantStatus(SearchUserDTO searchUser) {
			   int isUpdate=0;
			   if(searchUser.getIsActive() == 1) {
				   Object args[] = { searchUser.getUpdatedBy(), searchUser.getUpdatedBy(), searchUser.getUpdatedBy(), searchUser.getId() };
				   isUpdate =  jdbcTemplate.update(MerchantQuery.DELETE_MERCHANT, args);
			   }else {
				   Object args[] = { searchUser.getUpdatedBy(), searchUser.getUpdatedBy(), searchUser.getUpdatedBy(), searchUser.getId()};
				   isUpdate =  jdbcTemplate.update(MerchantQuery.ACTIVE_MERCHANT, args);
			   }
			   return isUpdate;
		}
		
		public int unBlockMerchantUser(SearchUserDTO searchUser) {
			   Object args[] = { searchUser.getUpdatedBy(), searchUser.getId() };
			   return jdbcTemplate.update(MerchantQuery.UNBLOCK_MERCHANT_USER, args);
		}

		public List<MerchantDTO> getMerchantUserDetail(int mId) {
			   Object args[] = { mId };
			   return jdbcTemplate.query(MerchantQuery.GET_UESER_DETAIL_BY_MID, args, new BeanPropertyRowMapper<MerchantDTO>(MerchantDTO.class));
		}

		public List<MerchantDTO> getMerchantByPanNumber(String panNumber) {
			   Object args[] = {panNumber};
			   return jdbcTemplate.query(MerchantQuery.GET_MERCHANT_BY_PAN_NUMBER, args, new BeanPropertyRowMapper<MerchantDTO>(MerchantDTO.class));
		}

		public List<MerchantDTO> getMerchantByGSTIN(String gstin) {
			   Object args[] = {gstin};
			   return jdbcTemplate.query(MerchantQuery.GET_MERCHANT_BY_GSTIN, args, new BeanPropertyRowMapper<MerchantDTO>(MerchantDTO.class));
		}

		public List<MerchantDTO> getMerchantByEmailId(String emailId) {
			   Object args[] = {emailId};
			   return jdbcTemplate.query(MerchantQuery.GET_MERCHANT_BY_EMAILID, args, new BeanPropertyRowMapper<MerchantDTO>(MerchantDTO.class));
		}

		public List<MerchantDTO> getMerchantByMobileNo(String mobile) {
			   Object args[] = {mobile};
			   return jdbcTemplate.query(MerchantQuery.GET_MERCHANT_BY_MOBILE, args, new BeanPropertyRowMapper<MerchantDTO>(MerchantDTO.class));
		}
		
		public int updateMerchantPlan(MerchantDTO merchantDTO) {
			   Object args[] = { merchantDTO.getMerchantPlanId(), merchantDTO.getUpdatedBy(), merchantDTO.getMerchantId()};
			   return jdbcTemplate.update(MerchantQuery.UPDATE_MERCHANT_PLAN, args);
			
		}
		
		public int saveMerchantDetails(MerchantDTO merchantDTO) {
			   Object args[] = {merchantDTO.getCategoryCode(), merchantDTO.getCurrencyCode(), merchantDTO.getCountryId(), merchantDTO.getAboutMe(), merchantDTO.getPANNumber(), merchantDTO.getGSTIN(), merchantDTO.getIcon(), merchantDTO.getUpdatedBy(), merchantDTO.getId()};
			   jdbcTemplate.update(MerchantQuery.SAVE_MERCHANT_DETAILS, args);
			   for(MerchantAddressDTO merchantAddress : merchantDTO.getmAddressList()) {
	    	        merchantAddress.setmId(merchantDTO.getId());
	    	        saveMerchantAddress(merchantAddress);
			   }
			   return 1;
		}

		public int updateMerchantFlag(MerchantDTO merchantDTO) {
			   Object args[] = {merchantDTO.getLoginId()};
			   return jdbcTemplate.update(MerchantQuery.UPDATE_MERCHANT_FLAG, args);
		}

		public MerchantDTO getMerchantByMN(String mobileNumber) {
			   Object args[] = {mobileNumber};
			   List<MerchantDTO> merchantList = jdbcTemplate.query(MerchantQuery.GET_MERCHANT_BY_MN, args, new BeanPropertyRowMapper<MerchantDTO>(MerchantDTO.class));
			   if(!merchantList.isEmpty()){
				    return merchantList.get(0);
			   }
			   return null;
		}

		public List<MerchantDTO> filterMerchantByAreaPin(String areaId, String pinCode) {
			   Object[] args = { areaId, pinCode };
	           return jdbcTemplate.query(MerchantQuery.RECENT_MERCHANT_BY_AREA_PIN, args, new BeanPropertyRowMapper<MerchantDTO>(MerchantDTO.class));
		}

		public List<MerchantAddressDTO> getMerchantAddress() {
			   return jdbcTemplate.query(MerchantQuery.RECENT_MERCHANT_ADDRESS, new BeanPropertyRowMapper<MerchantAddressDTO>(MerchantAddressDTO.class));
		}

		public int getTotalMerchant() {
			   List<MerchantDTO> merchantList = jdbcTemplate.query(MerchantQuery.GET_TOTAL_MERCHANT, new BeanPropertyRowMapper<MerchantDTO>(MerchantDTO.class));
			   if(merchantList != null) {
				   return merchantList.size();
			   }else {
				   return 0;
			   }
		}

		public int getActiveMerchant(String fromDate, String toDate) {
			   Object[] args = { fromDate, toDate};
			   List<MerchantDTO> merchantList = jdbcTemplate.query(MerchantQuery.GET_ACTIVE_MERCHANT, args, new BeanPropertyRowMapper<MerchantDTO>(MerchantDTO.class));
			   if(merchantList != null) {
				   return merchantList.size();
			   }else {
				   return 0;
			   }
		}

		public int getRecentMerchant(String fromDate, String toDate) {
			   Object[] args = { fromDate, toDate };
			   List<MerchantDTO> merchantList = jdbcTemplate.query(MerchantQuery.GET_RECENT_MERCHANT, args, new BeanPropertyRowMapper<MerchantDTO>(MerchantDTO.class));
			   if(merchantList != null) {
				   return merchantList.size();
			   }else {
				   return 0;
			   }
		}

		public List<MerchantDTO> getMerchantWalletBal() {
			   return jdbcTemplate.query(MerchantQuery.GET_MERCHANT_WALLETBAL, new BeanPropertyRowMapper<MerchantDTO>(MerchantDTO.class));
		}

		public List<MerchantDTO> mappingRequestSendList(int id) {
			   Object[] args = { id };
	           return jdbcTemplate.query(MerchantQuery.MAPPING_REQUEST_SEND_BY_CID, args, new BeanPropertyRowMapper<MerchantDTO>(MerchantDTO.class));
		}
}

