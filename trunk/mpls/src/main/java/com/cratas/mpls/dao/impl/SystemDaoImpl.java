/**
 * 
 */
package com.cratas.mpls.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cratas.mpls.common.query.SystemQuery;
import com.cratas.mpls.dao.ISystemDao;
import com.cratas.mpls.mapper.SystemMapper;
import com.cratas.mpls.web.dto.SystemDTO;

/**
 * @author bhupendra
 *
 */
@Repository
public class SystemDaoImpl implements ISystemDao{
	
	    @Autowired
	    private JdbcTemplate jdbcTemplate;
	   
	    @Autowired
	    private NamedParameterJdbcTemplate namedParamJdbcTemplate;
	   
	
		public int saveSystem(SystemDTO systemDTO) {
				if(getSystemById(systemDTO.getSystemId()) == null){
		  		      BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(systemDTO);
				      return namedParamJdbcTemplate.update(SystemQuery.INSERT_SYSTEM, params);
		  	    }
		  	    return 2;
		}
	
		
		public int updateSystem(SystemDTO systemDTO) {
			  BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(systemDTO);
		      return namedParamJdbcTemplate.update(SystemQuery.UPDATE_SYSTEM, params);
		}
	
		
		public SystemDTO getSystemById(String systemId) {
			   Object args[] = { systemId };
		       List<SystemDTO> systemList = jdbcTemplate.query(SystemQuery.SELECT_SYSTEM_BY_ID, args, new SystemMapper());
		       if(!systemList.isEmpty()){
		    	   return systemList.get(0);
		       }
		       return null;
		}
	
		
		public List<SystemDTO> getSystem() {
			   return jdbcTemplate.query(SystemQuery.SELECT_SYSTEM, new SystemMapper());
		}
	
		
		public int deleteSystem(SystemDTO systemDTO) {
			   Object args[] = { systemDTO.getUpdatedBy(),  systemDTO.getId()};
		       return jdbcTemplate.update(SystemQuery.DELETE_SYSTEM, args);
		}
	
		public List<SystemDTO> getActiveSystem() {
			   return jdbcTemplate.query(SystemQuery.SELECT_ACTIVE_SYSTEM, new SystemMapper());
		}

}
