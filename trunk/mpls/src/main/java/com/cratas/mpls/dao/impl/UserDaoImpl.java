package com.cratas.mpls.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cratas.mpls.common.query.Query;
import com.cratas.mpls.dao.IUserDao;
import com.cratas.mpls.mapper.UserMapper;
import com.cratas.mpls.mapper.UserNotificationMapper;
import com.cratas.mpls.mapper.UserPasswordHistoryMapper;
import com.cratas.mpls.web.dto.NotificationDTO;
import com.cratas.mpls.web.dto.PasswordHistoryDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author mukesh
 *
 */
@Repository
public class UserDaoImpl implements IUserDao {

	    @Autowired
	    private JdbcTemplate jdbcTemplate;
	
	    @Autowired
	    private NamedParameterJdbcTemplate namedParamJdbcTemplate;
	
	    public UserDTO getUserByLoginId(String loginId) {
		       Object args[] = new Object[] { loginId };
		       List<UserDTO> userList = jdbcTemplate.query(Query.SELECT_USER_BY_LOGIN_ID, args, new UserMapper());
		       if (userList.size() > 0) {
		            return userList.get(0);
		       }
		       return null;
	    }
	
	    public List<UserDTO> getUser(String systemId) {
	    	    Object args[] = { systemId };
	            return jdbcTemplate.query(Query.SELECT_USER, args, new UserMapper());
	    }
	
	    public List<UserDTO> getActiveUser() {
	           return jdbcTemplate.query(Query.SELECT_ACTIVE_USER, new UserMapper());
	    }
	
	    public UserDTO getUserbyId(int id) {
		       Object args[] = new Object[] { id };
		       List<UserDTO> userList = jdbcTemplate.query(Query.SELECT_USER_BY_ID, args, new UserMapper());
		       if (userList.size() > 0) {
		           return userList.get(0);
		       }
		       return null;
	    }
	
	    public int saveUser(UserDTO userDTO) {
	    	   if (getUserByLoginIdMobileNo(userDTO.getLoginId(), userDTO.getMobileNumber()) == null) {
		           BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(userDTO);
		           return namedParamJdbcTemplate.update(Query.SAVE_USER, params);
		       }
		       return 2;
	    }
	
	    public int updateUser(UserDTO userDTO) {
	           BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(userDTO);
	           return namedParamJdbcTemplate.update(Query.UPDATE_USER, params);
	    }
	
	    public int updateUnSuccessfulLoginAttempts(UserDTO userDTO) {
	           BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(userDTO);
	           return namedParamJdbcTemplate.update(Query.UPDATE_UNSUCCESSFUL_LOGIN_ATTEMPTS, params);
	    }
	
	    public int blockUserAccount(UserDTO userDTO) {
	           BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(userDTO);
	           return namedParamJdbcTemplate.update(Query.UPDATE_ACCOUNT_BLOCK, params);
	    }
	
	    public int changePassword(UserDTO userDTO) {
	           BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(userDTO);
	           return namedParamJdbcTemplate.update(Query.CHANGE_PASSWORD, params);
	    }
	
	    public int updateLastLoginDetail(UserDTO userDTO) {
	           BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(userDTO);
	           return namedParamJdbcTemplate.update(Query.UPDATE_LAST_LOGIN_DETAIL, params);
	    }
	
	    public int deleteUser(UserDTO userDTO) {
	           Object args[] = { userDTO.getUpdatedBy(), userDTO.getId() };
	           return jdbcTemplate.update(Query.DELETE_USER, args);
	    }
	
	    public List<UserDTO> getActiveUsersById(String systemId) {
               Object args[] = { systemId };
	           return jdbcTemplate.query(Query.SELECT_USERS_BY_IDS, args, new UserMapper());
	    }
	
	    public List<NotificationDTO> getUserNotification() {
	           return jdbcTemplate.query(Query.PENDING_INSTITUTION_LIST, new UserNotificationMapper());
	
	    }
	
	    public List<PasswordHistoryDTO> getPasswordHistory(String loginId) {
	           Object args[] = { loginId };
	           return jdbcTemplate.query(Query.SELECT_PASSWORD_HISTORY_MAPPER, args, new UserPasswordHistoryMapper());
	    }
	
	    public int savePasswordHistory(String loginId, String password) {
	           Object args[] = { loginId, password };
	           return jdbcTemplate.update(Query.SAVE_PASSWORD_HISTORY, args);
	    }
	
	    public int updatePasswordHistory(int id) {
	           Object args[] = { id };
	           return jdbcTemplate.update(Query.UPDATE_PASSWORD_HISTORY, args);
	    }
	
	    public List<NotificationDTO> getUserNotification(UserDTO userDTO) {
	           Object args[] = { userDTO.getLoginId() };
	           return jdbcTemplate.query(Query.USER_NOTIFICATION, args, new UserNotificationMapper());
	    }
	
	    public List<NotificationDTO> getUserRequest(UserDTO user) {
	           Object args[] = { user.getLoginId() };
	           return jdbcTemplate.query(Query.USER_REQUEST, args, new UserNotificationMapper());
	    }
	
	    public int checkUserGroupType(String groupID) {
	           return jdbcTemplate.queryForObject(Query.CHECK_USER_GROUP_TYPE, new Object[] { groupID }, Integer.class);
	    }
	    
	    public int setUserLoginPin(String userPin, String updatedby, int id) {
	           Object args[] = { userPin, updatedby, id };
	           return jdbcTemplate.update(Query.UPDATE_USER_PIN, args);
	    }
	    
	    public int enableOrDisbleFingerPrint(int isEnableFingerPrint, String updatedby, int id) {
	           Object args[] = { isEnableFingerPrint, updatedby, id };
	           return jdbcTemplate.update(Query.UPDATE_USER_FINGER_PRINT, args);
	    }

		public int unlockUser(String loginId, String updateBy) {
			   Object args[] = { updateBy, loginId };
			   return jdbcTemplate.update(Query.UNLOCK_USER, args);
		}
		
		public UserDTO getUserByLoginIdMobileNo(String loginId, String mobileNumber) {
		       Object args[] = new Object[] { loginId, mobileNumber };
		       List<UserDTO> userList = jdbcTemplate.query(Query.SELECT_USER_BY_LOGIN_ID_MOBILE_NO, args, new UserMapper());
		       if (userList.size() > 0) {
		            return userList.get(0);
		       }
		       return null;
	    }
		
		public UserDTO getUserByMobileNo(String mobileNumber) {
		       Object args[] = new Object[] { mobileNumber };
		       List<UserDTO> userList = jdbcTemplate.query(Query.SELECT_USER_BY_MOBILE_NO, args, new UserMapper());
		       if (userList.size() > 0) {
		            return userList.get(0);
		       }
		       return null;
	    }
		
}
