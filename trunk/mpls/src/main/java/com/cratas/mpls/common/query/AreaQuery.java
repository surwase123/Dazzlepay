package com.cratas.mpls.common.query;
/**
 * 
 * @author bhupendra
 *
 */

public class AreaQuery {
	
	    public static final String INSERT_AREA = "insert into area set areaCode=UPPER(?), areaName=?,  pincode=?, latitude=?, longitude=?, cityId=?, stateId=?, countryId=?, createdBy=?";
	    
	    public static final String UPDATE_AREA = "update area set areaName=?,  pincode=?, latitude=?, longitude=?, cityId=?, stateId=?, countryId=?, updatedBy=?, updateTimeStamp=now() where id = ?";
	    
	    public static final String DELETE_AREA = "update area set isActive = 0, updatedBy=?, updateTimeStamp=now() where id = ?";
	    
	    public static final String SELECT_AREA = "SELECT a.*,c.countryName,s.stateName,t.cityName FROM area a INNER JOIN country c ON c.id = a.countryId INNER JOIN state s ON s.id = a.stateId INNER JOIN city t ON "
	    		+ "t.id = a.cityId where a.isActive = 1 and a.isApproved = 1";
	    
	    public static final String SELECT_AREA_BY_CODE = "SELECT a.*,c.countryName,s.stateName,t.cityName FROM area a INNER JOIN country c ON c.id= a.countryId INNER JOIN state s ON s.id = a.stateId INNER JOIN city "
	    		+ "t ON t.id = a.cityId where a.areaCode=? and a.areaName=? and a.cityId=? and a.isActive = 1";

		public static final String SELECT_AREA_BY_ID = "SELECT a.*,c.countryName,s.stateName,t.cityName FROM area a INNER JOIN country c ON c.id= a.countryId INNER JOIN state s ON s.id = a.stateId INNER JOIN city "
				+ "t ON t.id = a.cityId where a.id=? and a.isActive = 1";

		public static final String AREA_LIST_BY_CITY_ID = "SELECT a.*,c.countryName,s.stateName,t.cityName FROM area a INNER JOIN country c ON c.id= a.countryId INNER JOIN state s ON s.id = a.stateId INNER JOIN city "
				+ "t ON t.id = a.cityId where a.cityId=? and a.isActive = 1 and a.isApproved = 1";
		
		public static final String SELECT_ALL_AVAILABLE_AREA = "select a.*, cc.cityName, s.stateName, c.countryName from area a join city cc on a.cityId=cc.id join state s on s.id=a.stateId join country c on "
				+ "c.id=a.countryId where a.isActive=1 and s.isActive=1 and c.isActive=1 group by a.areaCode";
}
