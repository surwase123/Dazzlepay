package com.cratas.mpls.service.impl;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.utility.constant.InformationMsgConstants;
import com.cratas.mpls.service.IAESEncryptionService;
import com.cratas.mpls.service.IBCryptService;
import com.cratas.mpls.service.IGroupService;
import com.cratas.mpls.service.IUserLoginService;
import com.cratas.mpls.service.IUserService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.LoginDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author mukesh
 *
 */
@Service
public class UserLoginServiceImpl implements IUserLoginService{
	
	   @Autowired
	   private IUtilityService utilityService;
		
	   @Autowired
	   private IUserService userService;
		
	   @Autowired
	   private IBCryptService bCryptService;
	   
	   @Autowired
	   private IAESEncryptionService aesService;
	   
	   @Autowired
	   private IGroupService groupService;
		
	   public String userLoginStatusMessage(UserDTO userDTO){
		       String message = Constants.BLANK;
		       if(userDTO.getIsActive() == 0){
		    	   message =  InformationMsgConstants.ACCOUNT_INACTIVE_MSG;
		       }else if(userDTO.getIsLocked() == 1){
		    	   message =  InformationMsgConstants.ACCOUNT_LOCKED_MSG;
		       }
		       return message;
	   }
		
	   public String getWebLoginUnSuccessfulAttemptMsg(UserDTO userDTO, LoginDTO loginDTO, HttpServletRequest request){
			  int unsuccessfulLoginAttempts = userDTO.getGroupDTO().getMaxLoginRetries();
			  int isUpdated = userService.updateUnSuccessfulLoginAttempts(userDTO);
			  if(isUpdated > 0 && checkEncryptionKey(request, userDTO, loginDTO)){
				    int remainingLoginAttempt = unsuccessfulLoginAttempts - (userDTO.getNumUnsuccessfulAttempts() + 1); 
				    if(remainingLoginAttempt == 1){
				    	 return InformationMsgConstants.WARNING_UNSUCCESSFUL_LOGIN_MSG;
				    }else if(remainingLoginAttempt == 0){
				         userService.blockUserAccount(userDTO);
				         return InformationMsgConstants.USR_BLOCKED_MSG;
				    }else{
				    	 return InformationMsgConstants.LOGIN_ERROR_MSG;
				    }
			  }else{
			    	return InformationMsgConstants.SESSION_EXPIRED_MSG;
			  }
	   }
	   
	   public String getAppLoginUnSuccessfulAttemptMsg(UserDTO user, LoginDTO loginDTO){
		      user.setGroupDTO(groupService.getGroupById(user.getGroupId()));
			  int unsuccessfulLoginAttempts = user.getGroupDTO().getMaxLoginRetries();
			  int isUpdated = userService.updateUnSuccessfulLoginAttempts(user);
			  if(isUpdated > 0){
				    int remainingLoginAttempt = unsuccessfulLoginAttempts - (user.getNumUnsuccessfulAttempts() + 1); 
				    if(remainingLoginAttempt == 1){
				    	 return InformationMsgConstants.WARNING_UNSUCCESSFUL_LOGIN_MSG;
				    }else if(remainingLoginAttempt == 0){
				         userService.blockUserAccount(user);
				         return InformationMsgConstants.USR_BLOCKED_MSG;
				    }else{
				    	 return InformationMsgConstants.LOGIN_ERROR_MSG;
				    }
			  }else{
			    	return InformationMsgConstants.SESSION_EXPIRED_MSG;
			  }
		}
		
		private boolean checkEncryptionKey(HttpServletRequest request, UserDTO userDTO, LoginDTO loginDTO){
				String salt = utilityService.generateSalt(userDTO, request);
	    	    String iv = utilityService.generateIV(userDTO, request);
			    return bCryptService.checkEncryptionKey(userDTO.getPassword(), aesService.decrypt(salt, iv, loginDTO.getLoginId(), loginDTO.getPassword()));
	    }
		
		public boolean isPasswordExpiry(UserDTO userDTO){
			   int passwordExpiryDays = Days.daysBetween(new LocalDate(userDTO.getPasswordChangedDate()), new LocalDate(new Date())).getDays();
			   if(passwordExpiryDays > userDTO.getGroupDTO().getPassExpiryDays()){
			    	return true;
			   }
			   return false;
	    }

}
