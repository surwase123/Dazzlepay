package com.cratas.mpls.web.dto;

/**
 * 
 * @author mukesh
 *
 */
public class FireBaseNotificationDTO {
	
	    private int id;
	    private int cId;
	    private int mId;
	    private int notificationId;
	    private String title;
	    private String imageUrl;
	    private String type;
	    private MerchantDTO merchant;
	    private CustomerDTO customer;
	    private int notificationWay;
	    private int isReadOnly;
	    
		public int getNotificationWay() {
			return notificationWay;
		}
		public void setNotificationWay(int notificationWay) {
			this.notificationWay = notificationWay;
		}
		public int getIsReadOnly() {
			return isReadOnly;
		}
		public void setIsReadOnly(int isReadOnly) {
			this.isReadOnly = isReadOnly;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getImageUrl() {
			return imageUrl;
		}
		public void setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public int getmId() {
			return mId;
		}
		public void setmId(int mId) {
			this.mId = mId;
		}
		public MerchantDTO getMerchant() {
			return merchant;
		}
		public void setMerchant(MerchantDTO merchant) {
			this.merchant = merchant;
		}
		public CustomerDTO getCustomer() {
			return customer;
		}
		public void setCustomer(CustomerDTO customer) {
			this.customer = customer;
		}
		public int getcId() {
			return cId;
		}
		public void setcId(int cId) {
			this.cId = cId;
		}
		public int getNotificationId() {
			return notificationId;
		}
		public void setNotificationId(int notificationId) {
			this.notificationId = notificationId;
		}
		
}
