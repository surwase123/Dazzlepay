package com.cratas.mpls.service;

import java.util.List;

import com.cratas.mpls.web.dto.GroupPrivilegeRequestDTO;
import com.cratas.mpls.web.dto.UserDTO;
import com.cratas.mpls.web.dto.UserRoleDTO;

/**
 * 
 * @author mukesh
 *
 */
public interface IUserRoleService {
	
		int saveUserRole(UserRoleDTO userRoleDTO);
	    	    
	    List<UserRoleDTO> getUserRoleById(String groupId);
	    
	    List<UserRoleDTO> getUserRole(String systemId);
	    
	    int deleteUserRole(UserRoleDTO userRoleDTO);
	    
	    int deleteGroupPrivilege(String groupId);
	    
	    void addUserNotification(UserDTO userDTO, GroupPrivilegeRequestDTO groupPrivilegeRequestDTO);

}
