package com.cratas.mpls.common.query;

/**
 * 
 * @author sunil
 *
 */

public class CityQuery {
	
		public static final String INSERT_CITY = "insert into city set countryId=?, stateId=?, cityName=?";
		
		public static final String UPDATE_CITY = "update city set cityName=?, countryId=?, stateId=?,  updatedBy=?, updateTimeStamp=now() where id = ?";
		
		public static final String DELETE_CITY = "update city set isActive = 0, updatedBy=?, updateTimeStamp=now() where id = ?";
		
		public static final String SELECT_CITY_BY_NAME = "select c.countryName, s.stateName, ct.* from city ct INNER JOIN country c ON ct.countryId=c.id INNER JOIN state s ON s.id = ct.stateId where ct.cityName=? and ct.stateId=? and ct.countryId=? and ct.isActive = 1";
		
		public static final String SELECT_CITY = "select c.countryName, s.stateName, ct.* from city ct INNER JOIN country c ON ct.countryId= c.id INNER JOIN state s ON s.id = ct.stateId where ct.isActive = 1 and ct.isApproved = 1";
		
		public static final String CITY_LIST = "SELECT c.countryName, s.stateName, ct.* from city ct INNER JOIN country c ON ct.countryId=c.id INNER JOIN state s ON s.id = ct.stateId where ct.stateId=? and ct.isActive = 1 and ct.isApproved = 1";

		public static final String CITY_LIST_BY_ID = "SELECT c.countryName, s.stateName, ct.* from city ct INNER JOIN country c ON ct.countryId=c.id INNER JOIN state s ON s.id = ct.stateId where ct.id=? and ct.isActive = 1 and ct.isApproved = 1";

}
