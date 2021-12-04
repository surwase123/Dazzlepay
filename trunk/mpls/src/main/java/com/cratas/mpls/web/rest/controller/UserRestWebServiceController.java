package com.cratas.mpls.web.rest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.utility.constant.InformationMsgConstants;
import com.cratas.mpls.service.IAESEncryptionService;
import com.cratas.mpls.service.IBCryptService;
import com.cratas.mpls.service.IGroupService;
import com.cratas.mpls.service.IUserService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.GroupDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author mukesh
 *
 */
@RestController
@RequestMapping("/user/rest")
public class UserRestWebServiceController {

	    private final static Logger LOGGER = LoggerFactory.getLogger(UserRestWebServiceController.class);
	
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
	
	    @RequestMapping(value = "/update-password", method = RequestMethod.POST)
		public @ResponseBody String updatePassword(@ModelAttribute("loginDTO") UserDTO loginReqDTO, HttpServletRequest request, HttpServletResponse response) {
			   if(null != loginReqDTO && StringUtils.isNotEmpty(loginReqDTO.getLoginId())){
				   UserDTO userDTO = userService.getUserbyId(loginReqDTO.getId());
				   if(null != userDTO && bCryptService.isMatch(loginReqDTO.getLoginId(), userDTO.getLoginId())){
					   if(setUserPassword(userDTO, loginReqDTO, request)) {
						   return Constants.SUCCESS;
					   }
				   }
			   }
			   return Constants.FAILURE;
		}
	
	    @RequestMapping(value = "/validate/password", method = RequestMethod.POST)
	    public @ResponseBody String passwordChecker(@ModelAttribute("loginDTO") UserDTO loginReqDTO, HttpServletRequest request, HttpServletResponse response) {
		        if (null != loginReqDTO && StringUtils.isNotEmpty(loginReqDTO.getLoginId())) {
		            UserDTO userDTO = userService.getUserbyId(loginReqDTO.getId());
		            if (null != userDTO && bCryptService.isMatch(loginReqDTO.getLoginId(), userDTO.getLoginId())) {
		                String salt = utilityService.generateSalt(userDTO, request);
		                String iv = utilityService.generateIV(userDTO, request);
		                GroupDTO groupDTO = groupService.getGroupById(userDTO.getGroupId());
		                String decryptPass = aesService.decrypt(salt, iv, String.valueOf(userDTO.getId()), loginReqDTO.getPassword());
		                String pattern = userService.getPasswordPattern(groupDTO);
		                boolean isExistsPassword = userService.isExistsPassword(decryptPass, userDTO.getLoginId());
		                if(decryptPass == null){
			    	    	return InformationMsgConstants.SESSION_EXPIRED_MSG;
			    	    }else if(null != decryptPass && decryptPass.matches(pattern) && !isExistsPassword){
			    	    	return Constants.SUCCESS;
			    	    }else if(isExistsPassword){
			    	    	return InformationMsgConstants.EXISTS_PASSWORD_MSG.replace(Constants.REPLACE_NUMBER, String.valueOf(groupDTO.getPassHistoryChecks()));
			    	    }
			    	    return utilityService.createPasswordPatternMessage(groupDTO);
		            }
		        }
		        return Constants.FAILURE;
	    }
		
		private boolean setUserPassword(UserDTO userDTO, UserDTO loginReqDTO, HttpServletRequest request) {
			    String salt = utilityService.generateSalt(userDTO, request);
			    String iv = utilityService.generateIV(userDTO, request);
			    String decyptPass = aesService.decrypt(salt, iv, String.valueOf(userDTO.getId()), loginReqDTO.getPassword());
			    GroupDTO group = groupService.getGroupById(userDTO.getGroupId());
			    if(StringUtils.isNotEmpty(decyptPass)) {
				   String encryptPass = bCryptService.getEncryptedString(decyptPass);
				   userDTO.setLastPassword(userDTO.getPassword());
				   userDTO.setPassword(encryptPass);
				   userDTO.setUpdatedBy(userDTO.getLoginId());
				   userService.changePassword(userDTO);
				   userService.savePasswordHistory(userDTO.getLoginId(), encryptPass, group.getPassHistoryChecks());
				   return true;
			    }
			    return false;
		}
	    
	    @RequestMapping(value = "/reset-password-mail", method = RequestMethod.POST)
	    public @ResponseBody String resetPasswordMail(@RequestParam("loginId") String loginId, HttpServletRequest request, HttpServletResponse response) {
		        if (StringUtils.isNotEmpty(loginId)) {
		            try {
		                UserDTO userDTO = userService.getUserByLoginIdMobileNo(loginId, loginId);
		                if (null != userDTO) {
		                    userService.sendResetPasswordMail(userDTO);
		                    return Constants.SUCCESS;
		                }
		                return Constants.IS_NOT_EXIST;
		            } catch (Exception e) {
		                LOGGER.error("Error in Send rest password Email");
		            }
		        }
		        return Constants.FAILURE;
	    }
	
	    @RequestMapping(value = "/checkUserGroupType", method = RequestMethod.POST)
	    public @ResponseBody String checkUserGroupType(@RequestParam("groupID") String groupID, HttpServletRequest request,  HttpServletResponse response) {
		        if (StringUtils.isNotEmpty(groupID)) {
		            try {
		                int count = userService.checkUserGroupType(groupID);
		                if (count > 0) {
		                    return Constants.SUCCESS;
		                }
		                return Constants.IS_NOT_EXIST;
		            } catch (Exception e) {
		                LOGGER.error("Error in Send rest password Email");
		            }
		        }
		        return Constants.FAILURE;
	    }

}
