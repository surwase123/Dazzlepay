package com.cratas.mpls.web.dto;

import java.util.Date;

/**
 * 
 * @author bhupendra
 *
 */
public class LoyaltyCashbackDTO {

		private int id;
		private int mId;
		private String offerCode;
		private String offerName;
		private String transCashbackType;
		private String cashbackType;
		private double minTransValue;
		private double fixedCashbackAmt;
		private String cashbackPercentage;
		private double maxCashbackAmt;
		private String createdBy;
	    private String updatedBy;
	    private String menuName;
	    private byte isActive;
	    private Date createdDate;
	    private Date updatedDate;
	    
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
		public String getOfferName() {
			return offerName;
		}
		public void setOfferName(String offerName) {
			this.offerName = offerName;
		}
		public String getTransCashbackType() {
			return transCashbackType;
		}
		public void setTransCashbackType(String transCashbackType) {
			this.transCashbackType = transCashbackType;
		}
		public String getCashbackType() {
			return cashbackType;
		}
		public void setCashbackType(String cashbackType) {
			this.cashbackType = cashbackType;
		}
		public double getMinTransValue() {
			return minTransValue;
		}
		public void setMinTransValue(double minTransValue) {
			this.minTransValue = minTransValue;
		}
		public double getFixedCashbackAmt() {
			return fixedCashbackAmt;
		}
		public void setFixedCashbackAmt(double fixedCashbackAmt) {
			this.fixedCashbackAmt = fixedCashbackAmt;
		}
		public String getCashbackPercentage() {
			return cashbackPercentage;
		}
		public void setCashbackPercentage(String cashbackPercentage) {
			this.cashbackPercentage = cashbackPercentage;
		}
		public double getMaxCashbackAmt() {
			return maxCashbackAmt;
		}
		public void setMaxCashbackAmt(double maxCashbackAmt) {
			this.maxCashbackAmt = maxCashbackAmt;
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
		public String getMenuName() {
			return menuName;
		}
		public void setMenuName(String menuName) {
			this.menuName = menuName;
		}
		public String getOfferCode() {
			return offerCode;
		}
		public void setOfferCode(String offerCode) {
			this.offerCode = offerCode;
		}
		public byte getIsActive() {
			return isActive;
		}
		public void setIsActive(byte isActive) {
			this.isActive = isActive;
		}
		public Date getCreatedDate() {
			return createdDate;
		}
		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}
		public Date getUpdatedDate() {
			return updatedDate;
		}
		public void setUpdatedDate(Date updatedDate) {
			this.updatedDate = updatedDate;
		}
		
	    
}
