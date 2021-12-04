package com.cratas.mpls.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.constant.EmailConstants;
import com.cratas.mpls.common.constant.FilePathConstants;
import com.cratas.mpls.common.constant.PropertyKeyConstants;
import com.cratas.mpls.common.utility.MenuComparator;
import com.cratas.mpls.common.utility.SaveUserNotificationThread;
import com.cratas.mpls.common.utility.SubMenuComparator;
import com.cratas.mpls.dao.IUserDao;
import com.cratas.mpls.service.IAESEncryptionService;
import com.cratas.mpls.service.IAppNotificationService;
import com.cratas.mpls.service.IBCryptService;
import com.cratas.mpls.service.ICustomerService;
import com.cratas.mpls.service.IEmailSenderService;
import com.cratas.mpls.service.IMPLSService;
import com.cratas.mpls.service.IMenuService;
import com.cratas.mpls.service.IMerchantProfileService;
import com.cratas.mpls.service.IMerchantService;
import com.cratas.mpls.service.IUserService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.ApiUserDTO;
import com.cratas.mpls.web.dto.AppNotificationDTO;
import com.cratas.mpls.web.dto.CustomerDTO;
import com.cratas.mpls.web.dto.GroupDTO;
import com.cratas.mpls.web.dto.MenuDTO;
import com.cratas.mpls.web.dto.MerchantUserDTO;
import com.cratas.mpls.web.dto.NotificationDTO;
import com.cratas.mpls.web.dto.PasswordHistoryDTO;
import com.cratas.mpls.web.dto.SubMenuDTO;
import com.cratas.mpls.web.dto.UserDTO;
import com.cratas.mpls.web.dto.UserRoleDTO;

/**
 * 
 * @author mukesh
 *
 */
@Service
@PropertySource(FilePathConstants.CLASSPATH + FilePathConstants.SERVER_PROPERTIES)
public class UserServiceImpl implements IUserService {
	
	    @Autowired
	    private IBCryptService bCryptService;
	
	    @Autowired
	    private IUserDao userDao;
	
	    @Autowired
	    private IMenuService menuService;
	
	    @Autowired
	    private SaveUserNotificationThread saveUserNotificationThread;
	
	    @Autowired
	    private ThreadPoolTaskExecutor taskExecutor;
	
	    @Autowired
	    private IMPLSService reconService;
	
	    @Autowired
	    private IEmailSenderService emailSenderService;
	
	    @Autowired
	    private IUtilityService utilityService;
	
	    @Autowired
	    private IAESEncryptionService aesService;
	
	    @Autowired
	    private Environment env;
	    
	    @Autowired
		private ICustomerService customerService;
		   
		@Autowired
		private IMerchantProfileService merchantProfileService;
		
		@Autowired
		private IMerchantService merchantService;
		
		@Autowired
		private IAppNotificationService appNotificationService;
	
	    public UserDTO getUserByLoginId(String loginId) {
	           return userDao.getUserByLoginId(loginId);
	    }
	
	    public List<UserDTO> getUser(String systemId) {
	           return userDao.getUser(systemId);
	    }
	
	    public List<UserDTO> getActiveUser() {
	           return userDao.getActiveUser();
	    }
	
	    public int updateUnSuccessfulLoginAttempts(UserDTO userDTO) {
	           return userDao.updateUnSuccessfulLoginAttempts(userDTO);
	    }
	
	    public int blockUserAccount(UserDTO userDTO) {
	           return userDao.blockUserAccount(userDTO);
	    }
	
	    public UserDTO getUserbyId(int id) {
	           return userDao.getUserbyId(id);
	    }
	
	    public int changePassword(UserDTO userDTO) {
	          return userDao.changePassword(userDTO);
	    }
	
	    public int updateLastLoginDetail(UserDTO userDTO) {
	           return userDao.updateLastLoginDetail(userDTO);
	    }
	
	    public int saveUser(UserDTO userDTO, UserDTO loginUserDTO, boolean isMerchantUser) {
		       int isInsert = userDao.saveUser(userDTO);
		       if (isInsert == 1) {
		            UserDTO userDetails = userDao.getUserByLoginId(userDTO.getLoginId());
		            if (isInsert == 1 && null != loginUserDTO && loginUserDTO.getGroupDTO().getGroupType().equals(Constants.MAKER)) {
		               NotificationDTO notification = reconService.createUserNotificationObj(String.valueOf(userDetails.getId()), userDTO.getMenuName(), userDTO);
		               reconService.updateRecordStatus(notification.getTableName(), notification.getRecordId(), 0);
		               saveUserNotificationThread.setData(notification, loginUserDTO);
		               taskExecutor.execute(saveUserNotificationThread);
		               return isInsert;
		           }
		           if(!isMerchantUser){
		              sendWelcomeMail(userDetails);
		           }
		       }
		       return isInsert;
	    }
	
	    public int updateUser(UserDTO userDTO) {
	           return userDao.updateUser(userDTO);
	    }
	
	    public int deleteUser(UserDTO userDTO) {
	           return userDao.deleteUser(userDTO);
	    }
	
	    public List<UserDTO> getActiveUsersById(String systemId) {
	           return userDao.getActiveUsersById(systemId);
	    }
	
	    public List<PasswordHistoryDTO> getPasswordHistory(String loginId) {
	           return userDao.getPasswordHistory(loginId);
	    }
	
	    public int savePasswordHistory(String loginId, String password, int passwordHistoryCheck) {
		       List<PasswordHistoryDTO> passwordList = getPasswordHistory(loginId);
		       int passwordCount = 1;
		       for (PasswordHistoryDTO passwordHistoryDTO : passwordList) {
		            if (passwordCount >= passwordHistoryCheck) {
		                updatePasswordHistory(passwordHistoryDTO.getId());
		            }
		            passwordCount++;
		       }
		       return userDao.savePasswordHistory(loginId, password);
	    }
	
	    public boolean isExistsPassword(String userPassword, String loginId) {
		       List<PasswordHistoryDTO> passwordList = getPasswordHistory(loginId);
		       for (PasswordHistoryDTO passwordHistoryDTO : passwordList) {
		           if (bCryptService.isMatch(passwordHistoryDTO.getPassword(), userPassword)) {
		                return true;
		           }
		       }
		       return false;
	    }
	
	    public int updatePasswordHistory(int id) {
	           return userDao.updatePasswordHistory(id);
	    }
	
	    public List<NotificationDTO> getUserNotification() {
	           return userDao.getUserNotification();
	    }
	
	    public List<NotificationDTO> getUserNotification(UserDTO userDTO) {
	           return userDao.getUserNotification(userDTO);
	    }
	
	    public String getPasswordPattern(GroupDTO groupDTO) {
		       String pattern = Constants.BLANK;
		       if (groupDTO.getIsNumberPassword().equals(Constants.Y)) {
		             pattern += Constants.NUMERIC_REGEX;
		       }
		       if (groupDTO.getIsAlphaPassword().equals(Constants.Y)) {
		             pattern += Constants.ALPHA_REGEX;
		       }
		       if (groupDTO.getIsSpecialSymbolPass().equals(Constants.Y)) {
		             pattern += Constants.SPECIAL_CHARACTER;
		       }
		       pattern += Constants.DOT + Constants.START_BRACES + groupDTO.getMinPassLength() + Constants.COMMA + groupDTO.getMaxPassLength() + Constants.END_BRACES;
		       return pattern;
	    }
	
	    /**
		 * Get Menu list By User Role
		 */
		public List<MenuDTO> getMenuListByUser(String groupId, List<UserRoleDTO> userRoleList){
			   Map<Integer, MenuDTO> userMenuMap = new HashMap<>();
			   for (UserRoleDTO userRoleDTO : userRoleList) {
			       if(!userMenuMap.containsKey(userRoleDTO.getMenuId())){
			    	   MenuDTO menuDTO = menuService.getMenu(userRoleDTO.getMenuId());
			    	   if(null != menuDTO) {
				    	   menuDTO.setUserRoleMenuList(new ArrayList<SubMenuDTO>());
				    	   setUserRoleSubMenu(userRoleDTO, menuDTO, userMenuMap);
			    	   }
			       }else{
			    	   MenuDTO menuDTO = userMenuMap.get(userRoleDTO.getMenuId());
			    	   setUserRoleSubMenu(userRoleDTO, menuDTO, userMenuMap);
			       }
				      
			   }
			   return getUserRoleMenuList(userMenuMap);
			   
	   }
		
	   public UserDTO getUserByLoginIdMobileNo(String loginId, String mobileNumber) {
			  return userDao.getUserByLoginIdMobileNo(loginId, mobileNumber);
	   }
				    
	   public UserDTO getUserByMobileNo(String mobileNumber) {
		      return userDao.getUserByMobileNo(mobileNumber);
	   }
	   
	   private void setUserRoleSubMenu(UserRoleDTO userRoleDTO, MenuDTO menuDTO,  Map<Integer, MenuDTO> userMenuMap){
		       if(menuDTO != null) {
				   if(userRoleDTO.getSubMenuId() != 0){
				    	 SubMenuDTO subMenuDTO = menuService.getSubMenu(userRoleDTO.getSubMenuId());
				    	 if(null != subMenuDTO) {
				            menuDTO.getUserRoleMenuList().add(subMenuDTO);
				    	 }
				   }
				   userMenuMap.put(userRoleDTO.getMenuId(), menuDTO);
		       }
	   }
		
	   private List<MenuDTO> getUserRoleMenuList(Map<Integer, MenuDTO> userMenuMap){
		       List<MenuDTO> userMenuList = new ArrayList<>();
		       for (Map.Entry<Integer, MenuDTO> entry : userMenuMap.entrySet()) {
		    	      MenuDTO menuDTO = entry.getValue();
		    	      if(null != menuDTO && !menuDTO.getUserRoleMenuList().isEmpty()){
		    	    	   Collections.sort(menuDTO.getUserRoleMenuList(), new SubMenuComparator()); 
		    	      }
		    	      userMenuList.add(menuDTO);
			   }
		       if(!userMenuList.isEmpty()){
    	    	   Collections.sort(userMenuList, new MenuComparator()); 
    	       }
		       return userMenuList;
	   }
	
	   public List<NotificationDTO> getUserRequest(UserDTO user) {
	          return userDao.getUserRequest(user);
	   }
	
	   private void sendWelcomeMail(final UserDTO userDTO) {
		       if (null != userDTO) {
		           String encryptLoginId = bCryptService.getEncryptedString(userDTO.getLoginId());
		           String resetPassUrl = utilityService.getBaseUrl() + EmailConstants.SLASH  + EmailConstants.CHANGE_PARRWORD_URL + userDTO.getId() + EmailConstants.SLASH
		                    + aesService.base64Encode(encryptLoginId.getBytes());
		           final String message = utilityService.classpathResourceLoader(EmailConstants.ADD_USER_TEMPLATE)
		                    .replace(EmailConstants.NAME, userDTO.getFirstName() + Constants.SPACE + userDTO.getLastName())
		                    .replace(EmailConstants.USER_LOGIN_ID, userDTO.getLoginId())
		                    .replace(EmailConstants.URL, resetPassUrl)
		                    .replace(EmailConstants.APP_ACCOUNT_NAME, EmailConstants.MPLS)
		                    .replace(EmailConstants.EMAIL, env.getProperty(PropertyKeyConstants.ServerProperties.SUPPORT_EMAIL))
		                    .replace(EmailConstants.BASE_URL, utilityService.getBaseUrl())
		                    .replace(EmailConstants.APP_NAME, EmailConstants.MPLS_APP_NAME)
		                    .replace(EmailConstants.LOGIN_ID, encryptLoginId)
		                    .replace(EmailConstants.ID, String.valueOf(userDTO.getId()));
		
		           new Thread(new Runnable() {
		                @Override
		                public void run() {
		                    emailSenderService.sendEmail(utilityService.getSenderMail(), userDTO.getEmailId().split(Constants.COMMA), null,
		                            EmailConstants.WELCOME_MAIL, message, null, null, true);
		                }
		           }).start();
		       }
	    }
	
	    public void sendResetPasswordMail(final UserDTO userDTO) {
		       if (null != userDTO) {
		            String encryptLoginId = bCryptService.getEncryptedString(userDTO.getLoginId());
		            String resetPassUrl = utilityService.getBaseUrl() + EmailConstants.SLASH + EmailConstants.CHANGE_PARRWORD_URL + userDTO.getId() + EmailConstants.SLASH
		                    + aesService.base64Encode(encryptLoginId.getBytes());
		            final String message = utilityService.classpathResourceLoader(EmailConstants.RESET_PASSWORD_TEMPLATE)
		                    .replace(EmailConstants.NAME, userDTO.getFirstName() + Constants.SPACE + userDTO.getLastName())
		                    .replace(EmailConstants.URL, resetPassUrl)
		                    .replace(EmailConstants.EMAIL, env.getProperty(PropertyKeyConstants.ServerProperties.SUPPORT_EMAIL))
		                    .replace(EmailConstants.BASE_URL, utilityService.getBaseUrl())
		                    .replace(EmailConstants.APP_NAME, EmailConstants.MPLS_APP_NAME)
		                    .replace(EmailConstants.LOGIN_ID, encryptLoginId)
		                    .replace(EmailConstants.ID, String.valueOf(userDTO.getId()));
		
		            new Thread(new Runnable() {
		                @Override
		                public void run() {
		                    emailSenderService.sendEmail(utilityService.getSenderMail(), userDTO.getEmailId().split(Constants.COMMA), null,
		                            EmailConstants.RESET_PASSWORD_SUBJECT, message, null, null, true);
		                }
		            }).start();
		       }
	    }
	
	    public int checkUserGroupType(String groupID) {
	           return userDao.checkUserGroupType(groupID);
	    }
	    
	    public UserRoleDTO getPageRole(List<UserRoleDTO> userRoleList, String uri){
	    	   if(null != userRoleList){
				   for (UserRoleDTO userRoleDTO : userRoleList) {
			    	    if(userRoleDTO.getSubMenuId() == 0){
			    		    MenuDTO menuDTO = menuService.getMenu(userRoleDTO.getMenuId());
			    		    if(null != menuDTO && uri.contains(menuDTO.getAction())){
			    			     return userRoleDTO;
			    		    }
			    	    }else{
			    		    SubMenuDTO subMenuDTO = menuService.getSubMenu(userRoleDTO.getSubMenuId());
			    		    if(null != subMenuDTO && uri.contains(subMenuDTO.getAction())){
			    			     return userRoleDTO;
			    		    }
			    	    }	
				   }
	    	   }
			   return null;
        }
	    
	    public int setLoginPin(UserDTO userDTO, ApiUserDTO apiUserDTO) {
	    	   String userUniqueId = getUserUniqueIdByType(userDTO);
			   if(StringUtils.isNotEmpty(userUniqueId)) {
				  String decryptPin = aesService.decrypt(generateDecryptKey(apiUserDTO.getLoginId()), generateDecryptKey(userUniqueId + apiUserDTO.getLoginId()), apiUserDTO.getUserId(), apiUserDTO.getApiPin());
				  if(StringUtils.isNotEmpty(decryptPin)){
					  String encryptPin = bCryptService.getEncryptedString(decryptPin);
					  if(StringUtils.isNotEmpty(encryptPin)) {
						  return userDao.setUserLoginPin(encryptPin, userDTO.getLoginId(), userDTO.getId());
					  }
				  }
			   }
	    	   return 0;
	    }
	    
	    private String getUserUniqueIdByType(UserDTO userDTO) {
			    switch (userDTO.getUserType()) {
					case 2:
						CustomerDTO customerDTO = customerService.getCustomerById(userDTO.getId());
						return customerDTO != null?customerDTO.getCustomerId():null;
					case 3:
						MerchantUserDTO merchantUserDTO = merchantProfileService.getMerchantId(userDTO.getId());
						return merchantService.getMerchantById(merchantUserDTO.getMid()).getMerchantId();
			    }
			    return null;
		}
	    
	    private String generateDecryptKey(String text){
	    	    String hexVal = utilityService.toHexaDecimal(text);
			    if(StringUtils.isNotEmpty(hexVal) && hexVal.length() > 32){
				    hexVal = hexVal.substring(0,32);
			    }
			    return hexVal;
		}
	    
	    public boolean validateUserPin(UserDTO user, String userPin) {
	    	   return StringUtils.isNotEmpty(userPin) ? bCryptService.isMatch(user.getUserPin(), userPin) : false;
	    }
	    
	    public UserDTO getUserByDeviceId(String deviceId) {
	    	   AppNotificationDTO appNotificationDTO  = appNotificationService.findByDeviceId(deviceId);
	    	   if(null != appNotificationDTO) {
	    		   if(appNotificationDTO.getcId() != 0) {
	    			   CustomerDTO customerDTO = customerService.getCustomerByCId(appNotificationDTO.getcId());
	    			   return customerDTO != null ? userDao.getUserbyId(customerDTO.getUserId()) : null;
	    		   }else if(appNotificationDTO.getmId() != 0) {
	    			   MerchantUserDTO merchantUserDTO = merchantProfileService.getMerchantUserIdByMID(appNotificationDTO.getmId());
	    			   return merchantUserDTO != null ? userDao.getUserbyId(merchantUserDTO.getUserId()) : null;
	    		   }
	    	   }
	    	   return null;  
	    }	    
	    
	    public int enableOrDisbleFingerPrint(UserDTO userDTO, int isEnableFingerPrint) {
	    	   return userDao.enableOrDisbleFingerPrint(isEnableFingerPrint, userDTO.getLoginId(), userDTO.getId());
	    }

		public int unlockUser(String loginId, String updateBy) {
			   return userDao.unlockUser(loginId, updateBy);
		}
}
