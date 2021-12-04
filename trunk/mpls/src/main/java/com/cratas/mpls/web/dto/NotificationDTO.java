/**
 * 
 */
package com.cratas.mpls.web.dto;

import java.util.Date;

/**
 * @author bhupendra
 *
 */
public class NotificationDTO {
	
		private int id;
		private String creatorId;
		private String checkerId;
		private int status;
		private String recordId;
		private String menuName;
		private String tableName;
		private String message;
		private String requestType;
		private Date insertDate;
		private Date updateDate;
		private String comments;
		
		public String getCheckerId() {
			return checkerId;
		}
		public void setCheckerId(String checkerId) {
			this.checkerId = checkerId;
		}
		public String getCreatorId() {
			return creatorId;
		}
		public void setCreatorId(String creatorId) {
			this.creatorId = creatorId;
		}
		public String getRecordId() {
			return recordId;
		}
		public void setRecordId(String recordId) {
			this.recordId = recordId;
		}
		public String getTableName() {
			return tableName;
		}
		public void setTableName(String tableName) {
			this.tableName = tableName;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public String getMenuName() {
			return menuName;
		}
		public void setMenuName(String menuName) {
			this.menuName = menuName;
		}
		public String getRequestType() {
			return requestType;
		}
		public void setRequestType(String requestType) {
			this.requestType = requestType;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getComments() {
			return comments;
		}
		public void setComments(String comments) {
			this.comments = comments;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public Date getInsertDate() {
			return insertDate;
		}
		public void setInsertDate(Date insertDate) {
			this.insertDate = insertDate;
		}
		public Date getUpdateDate() {
			return updateDate;
		}
		public void setUpdateDate(Date updateDate) {
			this.updateDate = updateDate;
		}
		
}
