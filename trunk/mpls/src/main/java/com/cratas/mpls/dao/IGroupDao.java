package com.cratas.mpls.dao;

import java.util.List;

import com.cratas.mpls.dao.dto.GroupTypeDTO;
import com.cratas.mpls.web.dto.GroupDTO;

/**
 * 
 * @author mukesh
 *
 */
public interface IGroupDao {
	
	   int saveGroup(GroupDTO groupDTO);
	   
	   int updateGroup(GroupDTO groupDTO);
	   
	   GroupDTO getGroupById(String groupId);
	   
	   List<GroupDTO> getGroup(String systemId);
	   
	   int deleteGroup(GroupDTO groupDTO);
	   
	   List<GroupDTO> getActiveGroup();
	   	   
	   List<GroupDTO> getActiveGroupsById(String systemId);
	   
	   List<GroupTypeDTO> getGroupType();

}
