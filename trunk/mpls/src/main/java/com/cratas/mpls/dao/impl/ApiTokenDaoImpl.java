package com.cratas.mpls.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cratas.mpls.dao.IApiTokenDao;

import com.cratas.mpls.web.dto.ApiTokenDTO;
import com.cratas.mpls.common.query.ApiTokenQuery;

/**
 * 
 * @author Sunil
 *
 */
@Repository
public class ApiTokenDaoImpl implements IApiTokenDao {

		@Autowired
		private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
		
		@Autowired
		private JdbcTemplate jdbcTemplate;
	
		public int saveToken(ApiTokenDTO apiTokenDTO) {
			BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(apiTokenDTO);
			updateToken(apiTokenDTO.getDeviceId());
			return namedParameterJdbcTemplate.update(ApiTokenQuery.INSERT_DEVICE_ID, params);
		}
		
		private int updateToken(String deviceId) {
			Object[] args = {deviceId};
			return jdbcTemplate.update(ApiTokenQuery.UPDATE_DEVICE_ID, args);
		}

}
