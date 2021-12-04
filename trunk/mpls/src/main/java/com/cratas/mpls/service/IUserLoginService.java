package com.cratas.mpls.service;

import javax.servlet.http.HttpServletRequest;

import com.cratas.mpls.web.dto.LoginDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author mukesh
 *
 */
public interface IUserLoginService {
	
	   String userLoginStatusMessage(UserDTO userDTO);
	   
	   String getWebLoginUnSuccessfulAttemptMsg(UserDTO userDTO, LoginDTO loginDTO, HttpServletRequest request);
	   
	   String getAppLoginUnSuccessfulAttemptMsg(UserDTO userDTO, LoginDTO loginDTO);
	   
	   boolean isPasswordExpiry(UserDTO userDTO);

}
