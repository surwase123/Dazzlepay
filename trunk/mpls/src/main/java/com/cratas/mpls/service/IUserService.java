package com.cratas.mpls.service;

import java.util.List;

import com.cratas.mpls.web.dto.ApiUserDTO;
import com.cratas.mpls.web.dto.GroupDTO;
import com.cratas.mpls.web.dto.MenuDTO;
import com.cratas.mpls.web.dto.NotificationDTO;
import com.cratas.mpls.web.dto.UserDTO;
import com.cratas.mpls.web.dto.UserRoleDTO;

/**
 * 
 * @author mukesh
 *
 */
public interface IUserService {

	    UserDTO getUserByLoginId(String loginId);
	
	    int updateUnSuccessfulLoginAttempts(UserDTO userDTO);
	
	    int blockUserAccount(UserDTO userDTO);
	
	    UserDTO getUserbyId(int id);
	
	    int changePassword(UserDTO userDTO);
	
	    int updateLastLoginDetail(UserDTO userDTO);
	
	    int saveUser(UserDTO userDTO, UserDTO loginUserDTO, boolean isMerchantUser);
	
	    int updateUser(UserDTO userDTO);
	
	    int deleteUser(UserDTO userDTO);
	
	    List<UserDTO> getUser(String systemId);
	
	    List<UserDTO> getActiveUser();
	
	    List<UserDTO> getActiveUsersById(String systemId);
	
	    String getPasswordPattern(GroupDTO groupDTO);
	
	    List<NotificationDTO> getUserNotification();
	
	    int savePasswordHistory(String loginId, String password, int passwordHistoryCheck);
	
	    boolean isExistsPassword(String userPassword, String loginId);
	
	    List<NotificationDTO> getUserNotification(UserDTO userDTO);
	
	    List<MenuDTO> getMenuListByUser(String groupId, List<UserRoleDTO> userRoleList);
	
	    List<NotificationDTO> getUserRequest(UserDTO user);
	
	    void sendResetPasswordMail(UserDTO userDTO);
	
	    int checkUserGroupType(String groupID);
	    
	    UserRoleDTO getPageRole(List<UserRoleDTO> userRoleList, String uri);
	    
	    int setLoginPin(UserDTO userDTO, ApiUserDTO apiUserDTO);
	    
	    boolean validateUserPin(UserDTO user, String userPin);
	    
	    UserDTO getUserByDeviceId(String deviceId);
	    
	    int enableOrDisbleFingerPrint(UserDTO userDTO, int isEnableFingerPrint);

		int unlockUser(String loginId, String updateBy);
		
		UserDTO getUserByLoginIdMobileNo(String loginId, String mobileNumber);
				
		UserDTO getUserByMobileNo(String mobileNumber);
	    	   
}
