package com.cratas.mpls.web.dto;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author mukesh
 *
 */
public class UserDTO {

	    private int id;
	    private String systemId;
	    private String verifiedId;
		private String groupId;
	    private String loginId;
	    private String mobileNumber;
	    private String domainUserName;
	    private String firstName;
	    private String middleName;
	    private String lastName;
	    private String emailId;
	    private String password;
	    private String lastPassword;
	    private int numUnsuccessfulAttempts;
	    private int maxConcurrentLogin;
	    private int isUpdateDetail;
	    private Date passwordChangedDate;
	    private Date lastLoggedOn;
	    private String lastSessionId;
	    private byte isLocked;
	    private int isActive;
	    private int isApproved;
	    private int userType;
	    private String status;
	    private String menuName;
	    private String managerId;
	    private String createdBy;
	    private String updatedBy;
	    private Date insertTimeStamp;
	    private Date updateTimeStamp;
	    private GroupDTO groupDTO;
	    private List<UserRoleDTO> userRoleList;
	    private List<MenuDTO> menuList;
	    private List<NotificationDTO> notificationList;
	    private List<NotificationDTO> makerNotificationList;
	    private List<PushNotificationLogDTO> pushNotificationLogList;
	    private List<RequestLoyaltyCardDTO> requestLoyaltyCardsNotificationList;
		private CustomerDTO customer;
	    private MerchantDTO merchant;
	    private MerchantEmployeeDTO merchantEmployee;
	    private String userPin;
	    private int isEnableFingerPrint;
	    private  List<RequestLoyaltyCardDTO> notificationsList;
	
		public List<RequestLoyaltyCardDTO> getNotificationsList() {
			return notificationsList;
		}

		public void setNotificationsList(List<RequestLoyaltyCardDTO> notificationsList) {
			this.notificationsList = notificationsList;
		}

		public List<NotificationDTO> getMakerNotificationList() {
	        return makerNotificationList;
	    }
	
	    public void setMakerNotificationList(List<NotificationDTO> makerNotificationList) {
	        this.makerNotificationList = makerNotificationList;
	    }
	
	    public List<NotificationDTO> getNotificationList() {
	        return notificationList;
	    }
	
	    public void setNotificationList(List<NotificationDTO> notificationList) {
	        this.notificationList = notificationList;
	    }
	
	    public String getSystemId() {
	        return systemId;
	    }
	
	    public void setSystemId(String systemId) {
	        this.systemId = systemId;
	    }
	
	    public String getGroupId() {
	        return groupId;
	    }
	
	    public void setGroupId(String groupId) {
	        this.groupId = groupId;
	    }
	
	    public int getMaxConcurrentLogin() {
	        return maxConcurrentLogin;
	    }
	
	    public void setMaxConcurrentLogin(int maxConcurrentLogin) {
	        this.maxConcurrentLogin = maxConcurrentLogin;
	    }
	
	    public String getLoginId() {
	        return loginId;
	    }
	
	    public void setLoginId(String loginId) {
	        this.loginId = loginId;
	    }
	
	    public String getDomainUserName() {
	        return domainUserName;
	    }
	
	    public void setDomainUserName(String domainUserName) {
	        this.domainUserName = domainUserName;
	    }
	
	    public String getFirstName() {
	        return firstName;
	    }
	
	    public void setFirstName(String firstName) {
	        this.firstName = firstName;
	    }
	
	    public String getMiddleName() {
	        return middleName;
	    }
	
	    public void setMiddleName(String middleName) {
	        this.middleName = middleName;
	    }
	
	    public String getLastName() {
	        return lastName;
	    }
	
	    public void setLastName(String lastName) {
	        this.lastName = lastName;
	    }
	
	    public String getEmailId() {
	        return emailId;
	    }
	
	    public void setEmailId(String emailId) {
	        this.emailId = emailId;
	    }
	
	    public String getPassword() {
	        return password;
	    }
	
	    public void setPassword(String password) {
	        this.password = password;
	    }
	
	    public String getLastPassword() {
	        return lastPassword;
	    }
	
	    public void setLastPassword(String lastPassword) {
	        this.lastPassword = lastPassword;
	    }
	
	    public Date getPasswordChangedDate() {
	        return passwordChangedDate;
	    }
	
	    public void setPasswordChangedDate(Date passwordChangedDate) {
	        this.passwordChangedDate = passwordChangedDate;
	    }
	
	    public Date getLastLoggedOn() {
	        return lastLoggedOn;
	    }
	
	    public void setLastLoggedOn(Date lastLoggedOn) {
	        this.lastLoggedOn = lastLoggedOn;
	    }
	
	    public byte getIsLocked() {
	        return isLocked;
	    }
	
	    public void setIsLocked(byte isLocked) {
	        this.isLocked = isLocked;
	    }
	
	    public int getIsActive() {
			return isActive;
		}

		public void setIsActive(int isActive) {
			this.isActive = isActive;
		}

		public String getCreatedBy() {
	        return createdBy;
	    }
	
	    public void setCreatedBy(String createdBy) {
	        this.createdBy = createdBy;
	    }
	
	    public String getUpdatedBy() {
	        return updatedBy;
	    }
	
	    public void setUpdatedBy(String updatedBy) {
	        this.updatedBy = updatedBy;
	    }
	
	    public Date getInsertTimeStamp() {
	        return insertTimeStamp;
	    }
	
	    public void setInsertTimeStamp(Date insertTimeStamp) {
	        this.insertTimeStamp = insertTimeStamp;
	    }
	
	    public Date getUpdateTimeStamp() {
	        return updateTimeStamp;
	    }
	
	    public void setUpdateTimeStamp(Date updateTimeStamp) {
	        this.updateTimeStamp = updateTimeStamp;
	    }
	
	    public int getNumUnsuccessfulAttempts() {
	        return numUnsuccessfulAttempts;
	    }
	
	    public void setNumUnsuccessfulAttempts(int numUnsuccessfulAttempts) {
	        this.numUnsuccessfulAttempts = numUnsuccessfulAttempts;
	    }
	
	    public int getId() {
	        return id;
	    }
	
	    public void setId(int id) {
	        this.id = id;
	    }
	
	    public String getLastSessionId() {
	        return lastSessionId;
	    }
	
	    public void setLastSessionId(String lastSessionId) {
	        this.lastSessionId = lastSessionId;
	    }	    
	
	    public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getManagerId() {
	        return managerId;
	    }
	
	    public void setManagerId(String managerId) {
	        this.managerId = managerId;
	    }
	
	    public GroupDTO getGroupDTO() {
	        return groupDTO;
	    }
	
	    public void setGroupDTO(GroupDTO groupDTO) {
	        this.groupDTO = groupDTO;
	    }
	
	    public List<UserRoleDTO> getUserRoleList() {
	        return userRoleList;
	    }
	
	    public void setUserRoleList(List<UserRoleDTO> userRoleList) {
	        this.userRoleList = userRoleList;
	    }
	
	    public List<MenuDTO> getMenuList() {
	        return menuList;
	    }
	
	    public void setMenuList(List<MenuDTO> menuList) {
	        this.menuList = menuList;
	    }
	
	    public String getMenuName() {
	        return menuName;
	    }
	
	    public void setMenuName(String menuName) {
	        this.menuName = menuName;
	    }

		public CustomerDTO getCustomer() {
			return customer;
		}

		public void setCustomer(CustomerDTO customer) {
			this.customer = customer;
		}

		public MerchantDTO getMerchant() {
			return merchant;
		}

		public void setMerchant(MerchantDTO merchant) {
			this.merchant = merchant;
		}

		public MerchantEmployeeDTO getMerchantEmployee() {
			return merchantEmployee;
		}

		public void setMerchantEmployee(MerchantEmployeeDTO merchantEmployee) {
			this.merchantEmployee = merchantEmployee;
		}

		public int getUserType() {
			return userType;
		}

		public void setUserType(int userType) {
			this.userType = userType;
		}

		public String getUserPin() {
			return userPin;
		}

		public void setUserPin(String userPin) {
			this.userPin = userPin;
		}

		public int getIsEnableFingerPrint() {
			return isEnableFingerPrint;
		}

		public void setIsEnableFingerPrint(int isEnableFingerPrint) {
			this.isEnableFingerPrint = isEnableFingerPrint;
		}

		public String getMobileNumber() {
			return mobileNumber;
		}

		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}

		public int getIsUpdateDetail() {
			return isUpdateDetail;
		}

		public void setIsUpdateDetail(int isUpdateDetail) {
			this.isUpdateDetail = isUpdateDetail;
		}

		public int getIsApproved() {
			return isApproved;
		}

		public void setIsApproved(int isApproved) {
			this.isApproved = isApproved;
		}

		public List<PushNotificationLogDTO> getPushNotificationLogList() {
			return pushNotificationLogList;
		}

		public void setPushNotificationLogList(List<PushNotificationLogDTO> pushNotificationLogList) {
			this.pushNotificationLogList = pushNotificationLogList;
		}
		
		public String getVerifiedId() {
			return verifiedId;
		}

		public void setVerifiedId(String verifiedId) {
			this.verifiedId = verifiedId;
		}
		
		public List<RequestLoyaltyCardDTO> getRequestLoyaltyCardsNotificationList() {
			return requestLoyaltyCardsNotificationList;
		}

		public void setRequestLoyaltyCardsNotificationList(List<RequestLoyaltyCardDTO> requestLoyaltyCardsNotificationList) {
			this.requestLoyaltyCardsNotificationList = requestLoyaltyCardsNotificationList;
		}
}
