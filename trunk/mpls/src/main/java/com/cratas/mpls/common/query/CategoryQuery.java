package com.cratas.mpls.common.query;

/**
 * 
 * @author bhupendra
 *
 */

public class CategoryQuery {
	
	    public static final String INSERT_CATEGORY = "insert into category set categoryCode=?, categoryName=?,  description=?, createdBy=?";
	    
	    public static final String UPDATE_CATEGORY = "update category set categoryCode=?, categoryName=?, description=?, updatedBy=?, updateTimeStamp=now() where id = ?";
	    
	    public static final String DELETE_CATEGORY = "update category set isActive = 0, updatedBy=?, updateTimeStamp=now() where id = ?";
	    
	    public static final String SELECT_CATEGORY = "select * from category where isActive = 1 and isApproved = 1";
	    
	    public static final String SELECT_CATEGORY_BY_CATEGORY_CODE = "select * from category where categoryCode=? and isActive = 1 and isApproved = 1";
	    
	    public static final String SELECT_CATEGORY_BY_CATEGORY_NAME = "select * from category where (categoryCode=? OR categoryName = ?) and isActive = 1";

		public static final String SELECT_CATEGORY_BY_ID = "select * from category where id=?";
}
