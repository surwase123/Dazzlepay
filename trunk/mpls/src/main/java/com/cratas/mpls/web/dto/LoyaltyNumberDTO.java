package com.cratas.mpls.web.dto;

public class LoyaltyNumberDTO {
	
	private	int id;
	private	String loyaltyNumber;
	private int isActive;
	private int genrationId;
	private String status;
	private int mId;
	private int cId;
	private int requestId;
	
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoyaltyNumber() {
		return loyaltyNumber;
	}
	public void setLoyaltyNumber(String loyaltyNumber) {
		this.loyaltyNumber = loyaltyNumber;
	}
	public int getGenrationId() {
		return genrationId;
	}
	public void setGenrationId(int genrationId) {
		this.genrationId = genrationId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getmId() {
		return mId;
	}
	public void setmId(int mId) {
		this.mId = mId;
	}
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	

}
