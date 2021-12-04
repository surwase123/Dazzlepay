package com.cratas.mpls.web.dto;

import java.util.Date;

public class LoyaltyNumberGenerationRequestDTO {
	private int id;
	private int quantity;
	private String status;
	private String reason;
	private Date insertTimeStamp;
	private Date updateTimeStamp;
	
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
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

	
	

}
