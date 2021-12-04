package com.cratas.mpls.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cratas.mpls.common.query.GroupQuery;
import com.cratas.mpls.dao.IGroupDao;
import com.cratas.mpls.dao.dto.GroupTypeDTO;
import com.cratas.mpls.mapper.GroupMapper;
import com.cratas.mpls.mapper.GroupTypeMapper;
import com.cratas.mpls.web.dto.GroupDTO;

/**
 * 
 * @author mukesh
 *
 */
@Repository
public class GroupDaoImpl implements IGroupDao {
	
	    
	   @Autowired
	   private JdbcTemplate jdbcTemplate;
	   
	   @Autowired
	   private NamedParameterJdbcTemplate namedParamJdbcTemplate;
	   
	   public int saveGroup(GroupDTO groupDTO) {
	    	  if(getGroupById(groupDTO.getGroupId()) == null){
	    		  BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(groupDTO);
			      return namedParamJdbcTemplate.update(GroupQuery.INSERT_GROUP, params);
	    	  }
	    	  return 2;
       }
	   
	   public int updateGroup(GroupDTO groupDTO) {
		      BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(groupDTO);
		      return namedParamJdbcTemplate.update(GroupQuery.UPDATE_GROUP, params);
       }
	   
	   public GroupDTO getGroupById(String groupId) {
	    	  Object args[] = { groupId };
		      List<GroupDTO> groupList = jdbcTemplate.query(GroupQuery.SELECT_GROUP_BY_ID, args, new GroupMapper());
		      if(!groupList.isEmpty()){
		    	   return groupList.get(0);
		      }
		      return null;
       }
	   
	   public List<GroupDTO> getGroup(String systemId) {
		      Object args[] = { systemId };
		      return jdbcTemplate.query(GroupQuery.SELECT_GROUP, args, new GroupMapper());
	   }
		   
	   public int deleteGroup(GroupDTO groupDTO) {
		      Object args[] = { groupDTO.getUpdatedBy(),  groupDTO.getId()};
		      return jdbcTemplate.update(GroupQuery.DELETE_GROUP, args);
	   }
		   
	   public List<GroupDTO> getActiveGroup() {
			  return jdbcTemplate.query(GroupQuery.SELECT_ACTIVE_GROUP, new GroupMapper());
	   }
	   
	   public List<GroupDTO> getActiveGroupsById(String systemId) {
		      Object args[] = { systemId };
		      return jdbcTemplate.query(GroupQuery.SELECT_ACTIVE_GROUP_BY_SYSTEM, args, new GroupMapper());
	   }
	   
	   public List<GroupTypeDTO> getGroupType() {
		      return jdbcTemplate.query(GroupQuery.SELECT_GROUP_TYPE, new GroupTypeMapper());
	   }

}
