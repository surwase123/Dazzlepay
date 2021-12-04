package com.cratas.mpls.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cratas.mpls.common.query.UserRoleQuery;
import com.cratas.mpls.dao.IUserRoleDao;
import com.cratas.mpls.mapper.UserRoleMapper;
import com.cratas.mpls.web.dto.UserRoleDTO;

/**
 * 
 * @author mukesh
 *
 */
@Repository
public class UserRoleDaoImpl implements IUserRoleDao {
	
	    @Autowired
	    private JdbcTemplate jdbcTemplate;
	   
	    @Autowired
	    private NamedParameterJdbcTemplate namedParamJdbcTemplate;
	   
	    public int saveUserRole(UserRoleDTO userRoleDTO) {
		          BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(userRoleDTO);
		          return namedParamJdbcTemplate.update(UserRoleQuery.INSERT_USER_GROUP_ROLE, params);
	    }
		   
		public List<UserRoleDTO> getUserRoleById(String groupId) {
		    	  Object args[] = { groupId };
			      List<UserRoleDTO> groupList = jdbcTemplate.query(UserRoleQuery.SELECT_USER_GROUP_ROLE_BY_ID, args, new UserRoleMapper());
			      return groupList;
	    }
		   
		public List<UserRoleDTO> getUserRole(String systemId) {
			   Object args[] = { systemId };
		       return jdbcTemplate.query(UserRoleQuery.SELECT_USER_GROUP_ROLE, args, new UserRoleMapper());
		}
			   
		public int deleteUserRole(UserRoleDTO userRoleDTO) {
			      Object args[] = { userRoleDTO.getUpdatedBy(),  userRoleDTO.getGroupId()};
			      return jdbcTemplate.update(UserRoleQuery.DELETE_USER_GROUP_ROLE, args);
		}
		
		public int deleteGroupPrivilege(String groupId) {
			   Object args[] = { groupId };
		       return jdbcTemplate.update(UserRoleQuery.DELETE_USER_PRIVILEGE, args);
	    }


}
