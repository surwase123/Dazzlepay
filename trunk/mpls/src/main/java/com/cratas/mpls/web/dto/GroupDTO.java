package com.cratas.mpls.web.dto;

import java.util.List;

/**
 * 
 * @author mukesh
 *
 */
public class GroupDTO {
	    
	    private int id;
	    private String systemId;
	    private String groupId;
	    private String groupName;
	    private String groupType;
	    private int minPassLength;
	    private int maxPassLength;
	    private String isAlphaPassword = "N";
	    private String isNumberPassword = "N";
	    private String isSpecialSymbolPass = "N";
	    private List<String> passwordCharacteristics;
	    private int passExpiryDays;
	    private int passHistoryChecks;
	    private int maxConcurrentLogin;
	    private int maxLoginRetries;
	    private String status;
	    private String menuName;
	    private int isEditable;
	    private String createdBy;
	    private String updatedBy;
	   
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getGroupId() {
			return groupId;
		}
		public void setGroupId(String groupId) {
			this.groupId = groupId;
		}
		public String getGroupName() {
			return groupName;
		}
		public void setGroupName(String groupName) {
			this.groupName = groupName;
		}
		public int getMinPassLength() {
			return minPassLength;
		}
		public void setMinPassLength(int minPassLength) {
			this.minPassLength = minPassLength;
		}
		public int getMaxPassLength() {
			return maxPassLength;
		}
		public void setMaxPassLength(int maxPassLength) {
			this.maxPassLength = maxPassLength;
		}
		public String getIsAlphaPassword() {
			return isAlphaPassword;
		}
		public void setIsAlphaPassword(String isAlphaPassword) {
			this.isAlphaPassword = isAlphaPassword;
		}
		public String getIsNumberPassword() {
			return isNumberPassword;
		}
		public void setIsNumberPassword(String isNumberPassword) {
			this.isNumberPassword = isNumberPassword;
		}
		public String getIsSpecialSymbolPass() {
			return isSpecialSymbolPass;
		}
		public void setIsSpecialSymbolPass(String isSpecialSymbolPass) {
			this.isSpecialSymbolPass = isSpecialSymbolPass;
		}
		public int getPassExpiryDays() {
			return passExpiryDays;
		}
		public void setPassExpiryDays(int passExpiryDays) {
			this.passExpiryDays = passExpiryDays;
		}
		public int getPassHistoryChecks() {
			return passHistoryChecks;
		}
		public void setPassHistoryChecks(int passHistoryChecks) {
			this.passHistoryChecks = passHistoryChecks;
		}
		public int getMaxConcurrentLogin() {
			return maxConcurrentLogin;
		}
		public void setMaxConcurrentLogin(int maxConcurrentLogin) {
			this.maxConcurrentLogin = maxConcurrentLogin;
		}
		public int getMaxLoginRetries() {
			return maxLoginRetries;
		}
		public void setMaxLoginRetries(int maxLoginRetries) {
			this.maxLoginRetries = maxLoginRetries;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
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
		public List<String> getPasswordCharacteristics() {
			return passwordCharacteristics;
		}
		public void setPasswordCharacteristics(List<String> passwordCharacteristics) {
			this.passwordCharacteristics = passwordCharacteristics;
		}
		public String getSystemId() {
			return systemId;
		}
		public void setSystemId(String systemId) {
			this.systemId = systemId;
		}
		public String getGroupType() {
			return groupType;
		}
		public void setGroupType(String groupType) {
			this.groupType = groupType;
		}
		public String getMenuName() {
			return menuName;
		}
		public void setMenuName(String menuName) {
			this.menuName = menuName;
		}
		public int getIsEditable() {
			return isEditable;
		}
		public void setIsEditable(int isEditable) {
			this.isEditable = isEditable;
		}	
}
