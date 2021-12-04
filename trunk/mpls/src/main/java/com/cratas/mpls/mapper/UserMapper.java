package com.cratas.mpls.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author mukesh
 *
 */
@Component
@Scope(Constants.PROTOTYPE)
public class UserMapper implements RowMapper<UserDTO> {

	   public UserDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		   UserDTO userDTO = new UserDTO();
		      userDTO.setId(rs.getInt("id"));
		      userDTO.setLoginId(rs.getString("loginId"));
		      userDTO.setMobileNumber(rs.getString("mobileNumber"));
		      userDTO.setSystemId(rs.getString("systemId"));
		      userDTO.setGroupId(rs.getString("groupId"));
		      userDTO.setDomainUserName(rs.getString("domainUserName"));
		      userDTO.setFirstName(rs.getString("firstName"));
		      userDTO.setMiddleName(rs.getString("middleName"));
		      userDTO.setLastName(rs.getString("lastName"));
		      userDTO.setEmailId(rs.getString("emailId"));
		      userDTO.setLastSessionId(rs.getString("lastSessionId"));
		      userDTO.setPassword(rs.getString("password"));
		      userDTO.setLastPassword(rs.getString("lastPassword"));
		      userDTO.setPasswordChangedDate(rs.getTimestamp("passwordChangedDate"));
		      userDTO.setNumUnsuccessfulAttempts(rs.getInt("numUnsuccessfulAttempts"));
		      userDTO.setMaxConcurrentLogin(rs.getInt("maxConcurrentLogin"));
		      userDTO.setLastLoggedOn(rs.getTimestamp("lastLoggedOn"));
		      userDTO.setIsLocked(rs.getByte("isLocked"));
		      userDTO.setIsActive(rs.getByte("isActive"));
		      userDTO.setStatus(rs.getString("status"));
		      userDTO.setCreatedBy(rs.getString("createdBy"));
		      userDTO.setUpdatedBy(rs.getString("updatedBy"));
		      userDTO.setInsertTimeStamp(rs.getDate("insertTimeStamp"));
		      userDTO.setUpdateTimeStamp(rs.getDate("updateTimeStamp"));
		      userDTO.setManagerId(rs.getString("managerId"));
		      userDTO.setUserType(rs.getInt("userType"));
		      userDTO.setIsEnableFingerPrint(rs.getInt("isEnableFingerprint"));
		      userDTO.setUserPin(rs.getString("userPin"));
		      userDTO.setIsUpdateDetail(rs.getInt("isUpdateDetail"));
		      return userDTO;
	    }

}
