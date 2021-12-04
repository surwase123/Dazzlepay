package com.cratas.mpls.dao.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.cratas.mpls.common.query.MerchantVersionQuery;
import com.cratas.mpls.dao.IMerchantPlanDao;
import com.cratas.mpls.web.dto.MerchantPlanDTO;

@Repository
public class MerchantPlanDaoImpl implements IMerchantPlanDao{

		@Autowired
		private JdbcTemplate jdbcTemplate;
		
		public List<MerchantPlanDTO> getMerchantPlan() {
			   return jdbcTemplate.query(MerchantVersionQuery.SELECT_MERCHANT_VERSION, new BeanPropertyRowMapper<MerchantPlanDTO>(MerchantPlanDTO.class));
		}
		
		public int saveMerchantPlan(MerchantPlanDTO merchantPlan) {
			   if(getMerchantPlanByName(merchantPlan.getVersionName()) == null){
			         Object[] args = { merchantPlan.getVersionName(), merchantPlan.getDescription(), merchantPlan.getCreatedBy() };
			         return jdbcTemplate.update(MerchantVersionQuery.INSERT_MERCHANT_VERSION, args);
		       }
			   return 2;
		} 
		
		public int updateMerchantPlan(MerchantPlanDTO merchantPlan) {
			   Object[] args = { merchantPlan.getDescription(), merchantPlan.getUpdatedBy(), merchantPlan.getId()};
	           return jdbcTemplate.update(MerchantVersionQuery.UPDATE_MERCHANT_VERSION, args);
		}
		
		public int deleteMerchantPlan(MerchantPlanDTO merchantPlan) {
			   Object[] args = {merchantPlan.getUpdatedBy(), merchantPlan.getId()};
	           return jdbcTemplate.update(MerchantVersionQuery.DELETE_MERCHANT_VERSION, args);
		}
		
		public MerchantPlanDTO getMerchantPlanById(int id) {
			   Object args[] = {id};
	           List<MerchantPlanDTO> merchantVersionList = jdbcTemplate.query(MerchantVersionQuery.SELECT_MERCHANT_VERSION_BY_ID, args, new BeanPropertyRowMapper<MerchantPlanDTO>(MerchantPlanDTO.class));
	           if(!merchantVersionList.isEmpty()){
	    	      return merchantVersionList.get(0);
	           }
		       return null;
		}
		
		public MerchantPlanDTO getMerchantPlanByName(String planName) {
			   Object args[] = { planName };
			   List<MerchantPlanDTO> merchantVersionList = jdbcTemplate.query(MerchantVersionQuery.SELECT_MERCHANT_VERSION_BY_NAME, args, new BeanPropertyRowMapper<MerchantPlanDTO>(MerchantPlanDTO.class));
			   if(!merchantVersionList.isEmpty()){
				   return merchantVersionList.get(0);
			   }
			   return null;
	     }

		public MerchantPlanDTO getMerchantPlanByMId(String merchantId) {
			   Object args[] = { merchantId };
			   List<MerchantPlanDTO> merchantVersionList =  jdbcTemplate.query(MerchantVersionQuery.SELECT_MERCHANT_VERSION_BY_MID, args, new BeanPropertyRowMapper<MerchantPlanDTO>(MerchantPlanDTO.class));
			   if(!merchantVersionList.isEmpty()){
				   return merchantVersionList.get(0);
			   }
			   return null;
		} 
}
