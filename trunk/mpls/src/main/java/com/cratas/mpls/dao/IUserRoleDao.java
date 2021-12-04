package com.cratas.mpls.dao;

import java.util.List;

import com.cratas.mpls.web.dto.UserRoleDTO;

/**
 * 
 * @author mukesh
 *
 */
public interface IUserRoleDao {
	
		int saveUserRole(UserRoleDTO userRoleDTO);
	    	    
	    List<UserRoleDTO> getUserRoleById(String groupId);
	    
	    List<UserRoleDTO> getUserRole(String systemId);
	    
	    int deleteUserRole(UserRoleDTO userRoleDTO);	

	    int deleteGroupPrivilege(String groupId);
}
