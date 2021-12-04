package com.cratas.mpls.web.dto;

/**
 * 
 * @author mukesh
 *
 */
public class MerchantAddressDTO {
	
		private int id;
		private int mId;
		private int stateId;
	    private int cityId;
	    private String cityName;
	    private String stateName;
	    private String areaName;
	    private String areaCode;
	    private String pincode;
	    
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getStateId() {
			return stateId;
		}
		public void setStateId(int stateId) {
			this.stateId = stateId;
		}
		public int getCityId() {
			return cityId;
		}
		public void setCityId(int cityId) {
			this.cityId = cityId;
		}
		
		public String getAreaCode() {
			return areaCode;
		}
		public void setAreaCode(String areaCode) {
			this.areaCode = areaCode;
		}
		public String getPincode() {
			return pincode;
		}
		public void setPincode(String pincode) {
			this.pincode = pincode;
		}
		public int getmId() {
			return mId;
		}
		public void setmId(int mId) {
			this.mId = mId;
		}
		public String getCityName() {
			return cityName;
		}
		public void setCityName(String cityName) {
			this.cityName = cityName;
		}
		public String getStateName() {
			return stateName;
		}
		public void setStateName(String stateName) {
			this.stateName = stateName;
		}
		public String getAreaName() {
			return areaName;
		}
		public void setAreaName(String areaName) {
			this.areaName = areaName;
		}	   
		
	    
}
