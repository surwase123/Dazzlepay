package com.cratas.mpls.common.query;

/**
 * 
 * @author mukesh
 *
 */
public class MenuQuery {
	
	    public static final String SELECT_MENU = "select * from menu where isActive = 1 order by orderSequence";
	    
	    public static final String SELECT_SUB_MENU = "select * from subMenu where isActive = 1 and menuId = ? order by orderSequence";

}
