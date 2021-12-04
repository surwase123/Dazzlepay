package com.cratas.mpls.web.dto;

/**
 * 
 * @author mukesh
 *
 */
public class MenuDetailMappingDTO {
	
	
		private String id;
		private String menuName;
		private String fieldName;
		private String dbTableName;
		
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getMenuName() {
			return menuName;
		}
		public void setMenuName(String menuName) {
			this.menuName = menuName;
		}
		public String getFieldName() {
			return fieldName;
		}
		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}
		public String getDbTableName() {
			return dbTableName;
		}
		public void setDbTableName(String dbTableName) {
			this.dbTableName = dbTableName;
		}
}
