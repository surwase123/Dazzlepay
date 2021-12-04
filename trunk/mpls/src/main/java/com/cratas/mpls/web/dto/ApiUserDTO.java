package com.cratas.mpls.web.dto;

/**
 * 
 * @author sunil
 *
 */
public class ApiUserDTO {
	
		private int id;
		private String name;
		private String userId;
		private String loginId;
		private String apiPin;
		private String mobileNumber;
		private int isUpdateDetail;
		private String mId;
		private String cId;
		private int isEnableFingerPrint;
		
		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		public String getmId() {
			return mId;
		}

		public void setmId(String mId) {
			this.mId = mId;
		}

		public String getcId() {
			return cId;
		}

		public void setcId(String cId) {
			this.cId = cId;
		}

		public String getLoginId() {
			return loginId;
		}
		
		public void setLoginId(String loginId) {
			this.loginId = loginId;
		}

		public String getApiPin() {
			return apiPin;
		}
		public void setApiPin(String apiPin) {
			this.apiPin = apiPin;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
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
		
		
		
}
