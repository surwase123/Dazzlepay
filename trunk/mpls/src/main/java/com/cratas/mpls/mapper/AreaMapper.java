package com.cratas.mpls.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cratas.mpls.web.dto.AreaDTO;

/**
 * 
 * @author bhupendra
 *
 */

public class AreaMapper implements RowMapper<AreaDTO>{

	public AreaDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		   AreaDTO areaDTO = new AreaDTO();
		   areaDTO.setId(rs.getInt("id"));
		   areaDTO.setAreaName(rs.getString("areaName"));
		   areaDTO.setCityId(rs.getInt("cityId"));
		   areaDTO.setCityName(rs.getString("cityName"));
		   areaDTO.setCountryId(rs.getInt("countryId"));
		   areaDTO.setCountryName(rs.getString("countryName"));
		   areaDTO.setStateId(rs.getInt("stateId"));
		   areaDTO.setStateName(rs.getString("stateName"));
		   areaDTO.setLatitude(rs.getString("latitude"));
		   areaDTO.setLongitude(rs.getString("longitude"));
		   areaDTO.setPincode(rs.getString("pinCode"));
		   areaDTO.setAreaCode(rs.getString("areaCode"));
		   areaDTO.setCreatedBy(rs.getString("createdBy"));
		   areaDTO.setUpdatedby(rs.getString("updatedBy"));
		   return areaDTO;
	}

}
