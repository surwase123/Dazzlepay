package com.cratas.mpls.service;

import java.util.List;

import com.cratas.mpls.dao.dto.GroupTypeDTO;
import com.cratas.mpls.web.dto.GroupDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author mukesh
 *
 */
public interface IGroupService {
	
	   int saveGroup(GroupDTO groupDTO, UserDTO userDTO);
	   
	   int updateGroup(GroupDTO groupDTO);
	   
	   GroupDTO getGroupById(String groupId);
	   
	   List<GroupDTO> getGroup(String systemId);
	   
	   int deleteGroup(GroupDTO groupDTO);
	   
	   List<GroupDTO> getActiveGroup();
	   
	   List<GroupDTO> getActiveGroupsById(String systemId);
	   
	   List<GroupTypeDTO> getGroupType();

}
