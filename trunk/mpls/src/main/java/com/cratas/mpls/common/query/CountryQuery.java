package com.cratas.mpls.common.query;

/**
 * 
 * @author bhupendra
 *
 */

public class CountryQuery {

	public static final String INSERT_COUNTRY = "insert into country set countryCode=?, countryCodeAlpha=UPPER(?), countryCode2Char=UPPER(?), countryName=?, createdBy=?";
    
    public static final String UPDATE_COUNTRY = "update country set countryCode=?, countryCodeAlpha=UPPER(?), countryCode2Char=UPPER(?), countryName=?, updatedBy=?, updateTimeStamp=now() where id = ?";
    
    public static final String DELETE_COUNTRY = "update country set isActive = 0, updatedBy=?, updateTimeStamp=now() where id = ?";
    
    public static final String SELECT_COUNTRY = "select * from country where isActive = 1 and isApproved = 1";
    
    public static final String SELECT_COUNTRY_BY_COUNTRY_CODE = "select * from country where countryCode=? and isActive = 1";
    
    public static final String SELECT_COUNTRY_BY_COUNTRY_ID = "select * from country where id=? and isActive = 1";
    
    public static final String IS_EXISTS_COUNTRY = "select * from country where (countryCode=? OR countryName=?) and isActive = 1";

}
