package com.cratas.mpls.common.query;

/**
 * 
 * @author mukesh
 *
 */
public class CurrencyQuery {
           
	
	    public static final String INSERT_CURRENCY = "insert into currency set currencyCode=?, countryName=?, currencyCodeAlpha=UPPER(?), currencyName=?, exponent=?, createdBy=?";
	    
	    public static final String UPDATE_CURRENCY = "update currency set currencyCode=?, countryName=?, currencyCodeAlpha=UPPER(?), currencyName=?, exponent=?, updatedBy=?, updateTimeStamp=now() where id = ?";
	    
	    public static final String DELETE_CURRENCY = "update currency set isActive = 0, updatedBy=?, updateTimeStamp=now() where id = ?";
	    
	    public static final String SELECT_CURRENCY = "select * from currency where isActive = 1 and isApproved = 1";
	    
	    public static final String SELECT_CURRENCY_BY_COUNTRY_CODE = "select * from currency where currencyCode=? and isActive = 1";

		public static final String CURRENCY_LIST = "select * from currency where countryName = ? and isActive = 1 and isApproved = 1";
}
