package com.cratas.mpls.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cratas.mpls.common.query.MerchantMappingQuery;
import com.cratas.mpls.dao.IMerchantMappingDao;
import com.cratas.mpls.web.dto.MerchantMappingDTO;

/**
 * 
 * @author mukesh
 *
 */
@Repository
public class MerchantMappingDaoImpl implements IMerchantMappingDao {
	
	   @Autowired
	   private JdbcTemplate jdbcTemplate;
	   
	   @Autowired
	   private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	   
	   public int saveMerchantMapping(MerchantMappingDTO merchantMapping){
		  
		      if(isExistMerchantMapping(merchantMapping.getcId(), merchantMapping.getmId(), 0) == null && isExistMerchantMapping(merchantMapping.getcId(), merchantMapping.getmId(), 1) == null){
			      BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(merchantMapping);
			      return namedParameterJdbcTemplate.update(MerchantMappingQuery.SAVE_MERCHANT_MAPPING, params);
		      }else if(isExistMerchantMapping(merchantMapping.getcId(), merchantMapping.getmId(), 0) != null) {
		    	  return 2;
		      }else if(isExistMerchantMapping(merchantMapping.getcId(), merchantMapping.getmId(), 1) != null) {
		    	  return 3;
		      }
		      return 0;    
	   }
	   
	   private MerchantMappingDTO isExistMerchantMapping(int cId, int mId, int isActive) {
			  Object args[] = { cId, mId, isActive };
			  List<MerchantMappingDTO> merchantMappingList = jdbcTemplate.query(MerchantMappingQuery.IS_MERCHANT_MAPPING, args, new BeanPropertyRowMapper<MerchantMappingDTO>(MerchantMappingDTO.class));
			  if(!merchantMappingList.isEmpty()){
				    return merchantMappingList.get(0);
			  }
			  return null;
	   }
	   
	   public List<MerchantMappingDTO> getMerchantMappingByCustomer(int cId) {
			   Object args[] = { cId };
			   List<MerchantMappingDTO> merchantMappingList = jdbcTemplate.query(MerchantMappingQuery.SELECT_MERCHANT_MAPPING_BY_CID, args, new BeanPropertyRowMapper<MerchantMappingDTO>(MerchantMappingDTO.class));
			   return merchantMappingList;
	   }
	   
	   public List<MerchantMappingDTO> getCustomerByMerchant(int mId) {
			  Object args[] = { mId };
			  List<MerchantMappingDTO> merchantMappingList = jdbcTemplate.query(MerchantMappingQuery.SELECT_MERCHANT_MAPPING_BY_MID, args, new BeanPropertyRowMapper<MerchantMappingDTO>(MerchantMappingDTO.class));
			  return merchantMappingList;
	   }

	   public int deleteMerchantMapping(String updatedBy, String mId, String cId) {
			  Object[] args = { updatedBy, mId, cId };
		      return jdbcTemplate.update(MerchantMappingQuery.DELETE_MERCHANT_MAPPING, args);
	   }
	   
	   public List<MerchantMappingDTO> getCustomersByMerchant(int mId, String fromDate, String toDate, int isActive) {
			  Object args[] = { mId, fromDate, toDate, isActive };
			  List<MerchantMappingDTO> merchantMappingList = jdbcTemplate.query(MerchantMappingQuery.SELECT_CUSTOMER_BY_MID, args, new BeanPropertyRowMapper<MerchantMappingDTO>(MerchantMappingDTO.class));
			  return merchantMappingList;
	   }
	   
	   public List<String> getCustomerFCMTokenByMId(int mId) {
			  Object args[] = { mId };
			  List<String> tokenList = jdbcTemplate.query(MerchantMappingQuery.SELECT_CUSTOMER_TOKEN_BY_MID, args, new BeanPropertyRowMapper<String>(String.class));
			  return tokenList;
	   }

	   public MerchantMappingDTO getCustomerByMN(int mId, String mobileNumber) {
		      Object args[] = { mId, mobileNumber };
		      List<MerchantMappingDTO> MerchantMappingList = jdbcTemplate.query(MerchantMappingQuery.SELECT_CUSTOMER_BY_MN, args, new BeanPropertyRowMapper<MerchantMappingDTO>(MerchantMappingDTO.class));
		      if(!MerchantMappingList.isEmpty()){
		    	   return MerchantMappingList.get(0);
		      }
		      return null;
	   }

		public int updateMerchantMapping(int cId, int mId) {
			   Object args[] = { mId, cId };
			   return jdbcTemplate.update(MerchantMappingQuery.UPDATE_MERCHANT_MAPPING, args);
		}
		public int updateMerchantMappingAddLoyaltyNumber(int cId, int mId,String LoyaltyCardNumber,int isActive) {
			   Object args[] = {isActive,LoyaltyCardNumber ,mId, cId };
			   return jdbcTemplate.update(MerchantMappingQuery.UPDATE_MERCHANT_MAPPING_WITH_LOYALTYCARDNO, args);
		}
		public List<MerchantMappingDTO> getCustomerBal() {
			   return jdbcTemplate.query(MerchantMappingQuery.GET_CUSTOMER_BAL, new BeanPropertyRowMapper<MerchantMappingDTO>(MerchantMappingDTO.class));
		}

		public int deleteMerchantMapping(int cId, int mId) {
			   Object args[] = { mId, cId };
			   return jdbcTemplate.update(MerchantMappingQuery.DELETE_MERCHANT_MAPPING_REQUEST, args);
		}

}
