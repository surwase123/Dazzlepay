package com.cratas.mpls.common.utility;

/**
 * 
 * @author mukesh
 *
 */
public enum GroupType {
	
	   CUSTOMER("customer"),
	   MERCHANT("merchant"),
	   ADMIN("admin");
	   
	   private String groupType;
	   
	   GroupType(String groupType) {
	        this.groupType = groupType;
	   }

	   public String getGroupType() {
			return groupType;
	   }
	   
}
