package com.cratas.mpls.dao;

import java.util.List;

import com.cratas.mpls.web.dto.NotificationDTO;
import com.cratas.mpls.web.dto.PasswordHistoryDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author mukesh
 *
 */
public interface IUserDao {

	    UserDTO getUserByLoginId(String loginId);
	
	    int updateUnSuccessfulLoginAttempts(UserDTO userDTO);
	
	    int blockUserAccount(UserDTO userDTO);
	
	    UserDTO getUserbyId(int id);
	
	    int changePassword(UserDTO userDTO);
	
	    int updateLastLoginDetail(UserDTO userDTO);
	
	    int saveUser(UserDTO userDTO);
	
	    int updateUser(UserDTO userDTO);
	
	    int deleteUser(UserDTO userDTO);
	
	    List<UserDTO> getUser(String systemId);
	
	    List<UserDTO> getActiveUser();
	
	    List<UserDTO> getActiveUsersById(String systemId);
	
	    List<NotificationDTO> getUserNotification();
	
	    List<PasswordHistoryDTO> getPasswordHistory(String loginId);
	
	    int savePasswordHistory(String loginId, String password);
	
	    int updatePasswordHistory(int id);
	
	    List<NotificationDTO> getUserNotification(UserDTO userDTO);
	
	    List<NotificationDTO> getUserRequest(UserDTO user);
	
	    int checkUserGroupType(String groupID);
	    
	    int setUserLoginPin(String userPin, String updatedby, int id);
	    
	    int enableOrDisbleFingerPrint(int isEnableFingerPrint, String updatedby, int id);
	    
	    int unlockUser(String loginId, String updateBy);
	    
	    UserDTO getUserByLoginIdMobileNo(String loginId, String mobileNumber);
	    		
	    UserDTO getUserByMobileNo(String mobileNumber);
}
