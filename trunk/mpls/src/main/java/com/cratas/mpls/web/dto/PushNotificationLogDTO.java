package com.cratas.mpls.web.dto;

import java.util.Date;

/**
 * @author bhupendra
 *
 */
public class PushNotificationLogDTO {

		private int id;
		private int cId;
		private int mId;
		private String message;
		private String notificationArgs;
		private int status;
		private int customerNotificationId;
		private String notificationType;
		private MerchantDTO merchant;
	    private CustomerDTO customer;
		private Date insertTimeStamp;
		private Date updateTimeStamp;
		private int isViewed;
		private int isReadOnly;
		private int is_readonly;
		public int getIs_readonly() {
			return is_readonly;
		}
		public void setIs_readonly(int is_readonly) {
			this.is_readonly = is_readonly;
		}
		private int notificationWay;
		
		public int getIsViewed() {
			return isViewed;
		}
		public void setIsViewed(int isViewed) {
			this.isViewed = isViewed;
		}
		public int getIsReadOnly() {
			return isReadOnly;
		}
		public void setIsReadOnly(int isReadOnly) {
			this.isReadOnly = isReadOnly;
		}
		public int getNotificationWay() {
			return notificationWay;
		}
		public void setNotificationWay(int notificationWay) {
			this.notificationWay = notificationWay;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public int getcId() {
			return cId;
		}
		public void setcId(int cId) {
			this.cId = cId;
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
		public String getNotificationArgs() {
			return notificationArgs;
		}
		public void setNotificationArgs(String notificationArgs) {
			this.notificationArgs = notificationArgs;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
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
		public int getCustomerNotificationId() {
			return customerNotificationId;
		}
		public void setCustomerNotificationId(int customerNotificationId) {
			this.customerNotificationId = customerNotificationId;
		}
		public String getNotificationType() {
			return notificationType;
		}
		public void setNotificationType(String notificationType) {
			this.notificationType = notificationType;
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
		
}
