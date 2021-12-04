package com.cratas.mpls.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cratas.mpls.common.query.Query;
import com.cratas.mpls.dao.IUserTrackingDao;
import com.cratas.mpls.web.dto.UserHistoryDTO;
import com.cratas.mpls.web.dto.UserTrackingRequestDTO;

/**
 * 
 * @author mukesh
 *
 */
@Repository
public class UserTrackingDaoImpl implements IUserTrackingDao {

		@Autowired
		private JdbcTemplate jdbcTemplate;
		
		@Autowired
		private NamedParameterJdbcTemplate namedParamJdbcTemplate;
		
		@Override
		public int addUserTrackingDetail(UserTrackingRequestDTO userTrackingDTO) {
			   if(getUserHistoryLog(userTrackingDTO.getLoginId()).size() == 0){
					BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(userTrackingDTO);
					return namedParamJdbcTemplate.update(Query.INSERT_USER_TRACKING_LOG, params);
		       }else{
			    	BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(userTrackingDTO);
					return namedParamJdbcTemplate.update(Query.UPDATE_USER_TRACKING_LOG, params);
		       }
		}
	
		public List<UserHistoryDTO> getUserHistoryLog(String loginId) {
			   Object args[] = new Object[] { loginId };
			   List<UserHistoryDTO> userHistoryList = jdbcTemplate.query(Query.SELECT_USER_TRACKING_LOG, args, new BeanPropertyRowMapper<UserHistoryDTO>(UserHistoryDTO.class));
			   return userHistoryList;
		}
	
		@Override
		public int addUserCompleteTrackingDetail(UserTrackingRequestDTO userTrackingDTO) {
			   BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(userTrackingDTO);
			   return namedParamJdbcTemplate.update(Query.INSERT_USER_TRACKING_DETAIL_LOG, params);
		}

}
