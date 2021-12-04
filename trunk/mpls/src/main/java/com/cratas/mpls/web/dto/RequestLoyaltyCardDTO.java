package com.cratas.mpls.web.dto;

import java.math.BigDecimal;
import java.util.Date;

public class RequestLoyaltyCardDTO {
	private int id;
	private int mId;
	private int cId;
	private String merchantId;
	private String message;
	private String merchantName;
	private int allocated;
	private String reason;
	private String name;
	private int quantityOfCards;
	private BigDecimal costPerUnit;
	private BigDecimal totalCost;
	private String cardType;
	private String paymentMode;
	private String paymentReferenceNumber;
	private String shippingAddress;
	private Date insertTimeStamp;
	private Date updateTimeStamp; 
	private String loyaltyCardNumber;
	
	public int getAllocated() {
		return allocated;
	}
	public void setAllocated(int allocated) {
		this.allocated = allocated;
	}
	public BigDecimal getCostPerUnit() {
		return costPerUnit;
	}
	public void setCostPerUnit(BigDecimal costPerUnit) {
		this.costPerUnit = costPerUnit;
	}
	public BigDecimal getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getPaymentMode() {
		return paymentMode;
	}
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	public String getPaymentReferenceNumber() {
		return paymentReferenceNumber;
	}
	public void setPaymentReferenceNumber(String paymentReferenceNumber) {
		this.paymentReferenceNumber = paymentReferenceNumber;
	}
	public String getLoyaltyCardNumber() {
		return loyaltyCardNumber;
	}
	public void setLoyaltyCardNumber(String loyaltyCardNumber) {
		this.loyaltyCardNumber = loyaltyCardNumber;
	}
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantityOfCards() {
		return quantityOfCards;
	}
	public void setQuantityOfCards(int quantityOfCards) {
		this.quantityOfCards = quantityOfCards;
	}
	private int status;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getmId() {
		return mId;
	}
	public void setmId(int mId) {
		this.mId = mId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
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
	
	
}
