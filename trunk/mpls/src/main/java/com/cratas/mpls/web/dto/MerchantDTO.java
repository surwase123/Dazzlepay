package com.cratas.mpls.web.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author mukesh
 *
 */
public class MerchantDTO{

	    private int id;
	    private String loginId;
	    private String cId;
	    private String merchantId;
	    private String categoryCode;
	    private String merchantName;
	    private String icon;
	    private String firstName;
	    private String middleName;
	    private String lastName;
	    private String emailId;
	    private String mobileNumber;
	    private String aboutMe;
	    private String PANNumber;
	    private String GSTIN;
	    private int countryId;
	    private String countryName;
	    private String currencyName;
	    private String categoryName;
	    private String currencyCodeAlpha;
	    private int currencyCode;
	    private String merchantNumber;
	    private int merchantPlanId;
	    private String createdBy;
	    private String updatedBy;
	    private String menuName;
	    private String verifiedId;
	    private double walletBal;
	    private int countryCode;
	    private int isLocked;
	    private int numUnsuccessfulAttempts;
	    private int isActive;
	    private int isApprove;
	    private int isAddedMerchant;
	    private List<MerchantAddressDTO> mAddressList;
	    private int offerCount;
	    private String request;
	    private String customerId;
	    private String loyaltyCardNumber;
	    
	    
		public String getLoyaltyCardNumber() {
			return loyaltyCardNumber;
		}

		public void setLoyaltyCardNumber(String loyaltyCardNumber) {
			this.loyaltyCardNumber = loyaltyCardNumber;
		}

		public int getId() {
			return id;
		}
	
		public void setId(int id) {
			this.id = id;
		}
	
		public String getMerchantId() {
			return merchantId;
		}
	
		public void setMerchantId(String merchantId) {
			this.merchantId = merchantId;
		}
	
		public String getCategoryCode() {
			return categoryCode;
		}
	
		public void setCategoryCode(String categoryCode) {
			this.categoryCode = categoryCode;
		}
	
		public String getMerchantName() {
			return merchantName;
		}
	
		public void setMerchantName(String merchantName) {
			this.merchantName = merchantName;
		}
	
		public String getIcon() {
			return icon;
		}
	
		public void setIcon(String icon) {
			this.icon = icon;
		}
	
		public String getEmailId() {
			return emailId;
		}
	
		public void setEmailId(String emailId) {
			this.emailId = emailId;
		}
	
		public String getMobileNumber() {
			return mobileNumber;
		}
	
		public void setMobileNumber(String mobileNumber) {
			this.mobileNumber = mobileNumber;
		}
	
		public String getAboutMe() {
			return aboutMe;
		}
	
		public void setAboutMe(String aboutMe) {
			this.aboutMe = aboutMe;
		}				
	
		@JsonProperty("PANNumber")
		public String getPANNumber() {
			return PANNumber;
		}

		public void setPANNumber(String PANNumber) {
			this.PANNumber = PANNumber;
		}

		@JsonProperty("GSTIN")
		public String getGSTIN() {
			return GSTIN;
		}

		public void setGSTIN(String GSTIN) {
			this.GSTIN = GSTIN;
		}

		public int getCountryId() {
			return countryId;
		}

		public void setCountryId(int countryId) {
			this.countryId = countryId;
		}

		public int getCurrencyCode() {
			return currencyCode;
		}

		public void setCurrencyCode(int currencyCode) {
			this.currencyCode = currencyCode;
		}

		public String getMerchantNumber() {
			return merchantNumber;
		}
	
		public void setMerchantNumber(String merchantNumber) {
			this.merchantNumber = merchantNumber;
		}
	
		public List<MerchantAddressDTO> getmAddressList() {
			return mAddressList;
		}
	
		public void setmAddressList(List<MerchantAddressDTO> mAddressList) {
			this.mAddressList = mAddressList;
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

		public int getIsApprove() {
			return isApprove;
		}

		public void setIsApprove(int isApprove) {
			this.isApprove = isApprove;
		}

		public String getCountryName() {
			return countryName;
		}

		public void setCountryName(String countryName) {
			this.countryName = countryName;
		}

		public String getCurrencyCodeAlpha() {
			return currencyCodeAlpha;
		}

		public void setCurrencyCodeAlpha(String currencyCodeAlpha) {
			this.currencyCodeAlpha = currencyCodeAlpha;
		}

		public String getMenuName() {
			return menuName;
		}

		public void setMenuName(String menuName) {
			this.menuName = menuName;
		}

		public String getCurrencyName() {
			return currencyName;
		}

		public void setCurrencyName(String currencyName) {
			this.currencyName = currencyName;
		}

		public String getLoginId() {
			return loginId;
		}

		public void setLoginId(String loginId) {
			this.loginId = loginId;
		}

		public String getCategoryName() {
			return categoryName;
		}

		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}

		public String getVerifiedId() {
			return verifiedId;
		}

		public void setVerifiedId(String verifiedId) {
			this.verifiedId = verifiedId;
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

		public String getcId() {
			return cId;
		}

		public void setcId(String cId) {
			this.cId = cId;
		}

		public double getWalletBal() {
			return walletBal;
		}

		public void setWalletBal(double walletBal) {
			this.walletBal = walletBal;
		}

		public int getIsAddedMerchant() {
			return isAddedMerchant;
		}

		public void setIsAddedMerchant(int isAddedMerchant) {
			this.isAddedMerchant = isAddedMerchant;
		}

		public int getOfferCount() {
			return offerCount;
		}

		public void setOfferCount(int offerCount) {
			this.offerCount = offerCount;
		}

		public int getIsActive() {
			return isActive;
		}

		public void setIsActive(int isActive) {
			this.isActive = isActive;
		}

		public int getIsLocked() {
			return isLocked;
		}

		public void setIsLocked(int isLocked) {
			this.isLocked = isLocked;
		}

		public int getNumUnsuccessfulAttempts() {
			return numUnsuccessfulAttempts;
		}

		public void setNumUnsuccessfulAttempts(int numUnsuccessfulAttempts) {
			this.numUnsuccessfulAttempts = numUnsuccessfulAttempts;
		}

		public int getMerchantPlanId() {
			return merchantPlanId;
		}

		public void setMerchantPlanId(int merchantPlanId) {
			this.merchantPlanId = merchantPlanId;
		}

		public int getCountryCode() {
			return countryCode;
		}

		public void setCountryCode(int countryCode) {
			this.countryCode = countryCode;
		}

		public String getRequest() {
			return request;
		}

		public void setRequest(String request) {
			this.request = request;
		}
		public String getCustomerId() {
			return customerId;
		}
		public void setCustomerId(String customerId) {
			this.customerId = customerId;
		}
		
}
