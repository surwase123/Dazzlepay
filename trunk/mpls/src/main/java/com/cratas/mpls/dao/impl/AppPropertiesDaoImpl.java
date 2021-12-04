package com.cratas.mpls.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cratas.mpls.common.query.Query;
import com.cratas.mpls.dao.IAppPropertiesDao;
import com.cratas.mpls.dao.dto.SystemParamDTO;
import com.cratas.mpls.mapper.SystemParamMapper;

/**
 * 
 * @author mukesh
 *
 */
@Repository
public class AppPropertiesDaoImpl implements IAppPropertiesDao {
	
		@Autowired
		private JdbcTemplate jdbcTemplate;
		
		@Autowired
		   private NamedParameterJdbcTemplate namedParamJdbcTemplate;
		
		public SystemParamDTO getSystemParamValueByName(String paramName) {
			   Object args[] = new Object[] { paramName };
			   List<SystemParamDTO> sysParamList = jdbcTemplate.query(Query.SELECT_SYSTEM_PARAM_VALUE_BY_PARAM_NAME, args, new SystemParamMapper());
			   if(sysParamList.size() > 0){
				   return sysParamList.get(0);
			   }
			   return null;
		}
		
		public List<SystemParamDTO> getSystemParamValues() {
			   List<SystemParamDTO> sysParamList = jdbcTemplate.query(Query.SELECT_SYSTEM_PARAM_VALUE, new SystemParamMapper());
			   return sysParamList;
		}

		public List<SystemParamDTO> getSystemParameter() {
			   List<SystemParamDTO> sysParamList = jdbcTemplate.query(Query.SELECT_SYSTEM_PARAM_VALUE, new SystemParamMapper());
			   return sysParamList;
		}

		public int saveSysParameter(SystemParamDTO systemParamDTO) {
			   if(getSystemParamValueByName(systemParamDTO.getParamName()) == null){
	    		  BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(systemParamDTO);
			      return namedParamJdbcTemplate.update(Query.INSERT_SYSTEM_PARAM, params);
	    	   }
	    	   return 2;
		}

		public int updateSystemParameter(SystemParamDTO systemParamDTO) {
			   BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(systemParamDTO);
		       return namedParamJdbcTemplate.update(Query.UPDATE_SYSTEM_PARAM, params);
		}

		public int deleteSystemParameter(SystemParamDTO systemParamDTO) {
			   Object args[] = { systemParamDTO.getUpdatedBy(), systemParamDTO.getId()};
		       return jdbcTemplate.update(Query.DELETE_SYSTEM_PARAM, args);
		}

		public SystemParamDTO getSystemParamByName(String paramName) {
			   Object args[] = new Object[] { paramName };
			   List<SystemParamDTO> sysParamList = jdbcTemplate.query(Query.SELECT_SYSTEM_PARAM_VALUE_BY_PARAM_NAME, args, new SystemParamMapper());
			   if(sysParamList.size() > 0){
				   return sysParamList.get(0);
			   }
			   return null;
		}

}
