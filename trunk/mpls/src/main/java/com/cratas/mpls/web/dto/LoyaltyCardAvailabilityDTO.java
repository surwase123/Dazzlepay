package com.cratas.mpls.web.dto;

public class LoyaltyCardAvailabilityDTO {
	private int total;
	private int availble;
	private int allocated;
	
	public int getAllocated() {
		return allocated;
	}
	public void setAllocated(int allocated) {
		this.allocated = allocated;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getAvailble() {
		return availble;
	}
	public void setAvailble(int availble) {
		this.availble = availble;
	}
	
	
	

}
