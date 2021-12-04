package com.cratas.mpls.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cratas.mpls.common.query.LoyaltyCashbackQuery;
import com.cratas.mpls.dao.ILoyaltyCashbackDao;
import com.cratas.mpls.web.dto.LoyaltyCashbackDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author bhupendra
 *
 */
@Repository
public class LoyaltyCashbackDaoImpl implements ILoyaltyCashbackDao{

		@Autowired
		private JdbcTemplate jdbcTemplate;
		
		public List<LoyaltyCashbackDTO> getLoyaltyCashbackByMId(int mId) {
			   Object args[] = { mId };
			   return jdbcTemplate.query(LoyaltyCashbackQuery.SELECT_LOYALTY_CASHBACK_BY_MID, args, new BeanPropertyRowMapper<LoyaltyCashbackDTO>(LoyaltyCashbackDTO.class));
		}
		
		public List<LoyaltyCashbackDTO> getLoyaltyCashback() {
			   return jdbcTemplate.query(LoyaltyCashbackQuery.SELECT_LOYALTY_CASHBACK, new BeanPropertyRowMapper<LoyaltyCashbackDTO>(LoyaltyCashbackDTO.class));
		}
	
		public int saveLoyaltyCashback(LoyaltyCashbackDTO loyatyCashbackDTO, UserDTO userDTO) {
			   if(getLoyaltyCashbackByMId(loyatyCashbackDTO.getmId()).isEmpty()) {
				   if(getLoyaltyCashbackByName(loyatyCashbackDTO.getmId(), loyatyCashbackDTO.getOfferName(), loyatyCashbackDTO.getOfferCode()) == null){
					      Object[] args = { loyatyCashbackDTO.getmId(), loyatyCashbackDTO.getOfferCode(), loyatyCashbackDTO.getOfferName(), loyatyCashbackDTO.getTransCashbackType(),loyatyCashbackDTO.getCashbackType(),loyatyCashbackDTO.getMinTransValue(),loyatyCashbackDTO.getFixedCashbackAmt(),loyatyCashbackDTO.getCashbackPercentage(),loyatyCashbackDTO.getMaxCashbackAmt(), loyatyCashbackDTO.getCreatedBy() };
					      return jdbcTemplate.update(LoyaltyCashbackQuery.INSERT_LOYALTY_CASHBACK, args);
				   }
				   return 2;
			   }
			   return 3;
		}
	
		public int updateLoyaltyCashback(LoyaltyCashbackDTO loyatyCashbackDTO) {
			   Object[] args = {loyatyCashbackDTO.getmId(), loyatyCashbackDTO.getOfferCode(), loyatyCashbackDTO.getOfferName(), loyatyCashbackDTO.getTransCashbackType(),loyatyCashbackDTO.getCashbackType(),loyatyCashbackDTO.getMinTransValue(),loyatyCashbackDTO.getFixedCashbackAmt(),loyatyCashbackDTO.getCashbackPercentage(),loyatyCashbackDTO.getMaxCashbackAmt(),loyatyCashbackDTO.getUpdatedBy(),loyatyCashbackDTO.getId()};
			   return jdbcTemplate.update(LoyaltyCashbackQuery.UPDATE_LOYALTY_CASHBACK, args);
		}
	
		public int deleteLoyaltyCashback(LoyaltyCashbackDTO loyatyCashbackDTO) {
			   Object[] args = {loyatyCashbackDTO.getUpdatedBy(),loyatyCashbackDTO.getId()};
			   return jdbcTemplate.update(LoyaltyCashbackQuery.DELETE_LOYALTY_CASHBACK, args);
		}

		public LoyaltyCashbackDTO getLoyaltyCashbackByName(int mid, String offerName, String offerCode) {
			   Object args[] = { mid, offerName, offerCode };
		       List<LoyaltyCashbackDTO> loyaltyCashbackList = jdbcTemplate.query(LoyaltyCashbackQuery.SELECT_LOYALTYCASHBACK_BY_NAME, args, new BeanPropertyRowMapper<LoyaltyCashbackDTO>(LoyaltyCashbackDTO.class));
		       if(!loyaltyCashbackList.isEmpty()){
		    	   return loyaltyCashbackList.get(0);
		       }
		       return null;
		}

		public LoyaltyCashbackDTO getLoyaltyCashbackById(int id) {
			   Object args[] = { id };
			   List<LoyaltyCashbackDTO> loyaltyCashbackList = jdbcTemplate.query(LoyaltyCashbackQuery.SELECT_LOYALTYCASHBACK_BY_ID, args, new BeanPropertyRowMapper<LoyaltyCashbackDTO>(LoyaltyCashbackDTO.class));
			   if(!loyaltyCashbackList.isEmpty()){
				  return loyaltyCashbackList.get(0);
			   }
			   return null;
		}
		
		public LoyaltyCashbackDTO getLoyaltyCashbackByCode(String offerCode){
			   Object args[] = { offerCode };
			   List<LoyaltyCashbackDTO> loyaltyCashbackList =  jdbcTemplate.query(LoyaltyCashbackQuery.SELECT_LOYALTY_CASHBACK_BY_OFFER_CODE, args, new BeanPropertyRowMapper<LoyaltyCashbackDTO>(LoyaltyCashbackDTO.class));
			   if(!loyaltyCashbackList.isEmpty()){
				    return loyaltyCashbackList.get(0);
			   }
			   return null;
		}
}
