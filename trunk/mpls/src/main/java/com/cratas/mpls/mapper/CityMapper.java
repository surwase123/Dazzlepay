package com.cratas.mpls.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cratas.mpls.web.dto.CityDTO;

/**
 * 
 * @author sunil
 *
 */
public class CityMapper implements RowMapper<CityDTO> {

		@Override
		public CityDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			CityDTO cityDTO = new CityDTO();
			cityDTO.setId(rs.getInt("id"));
			cityDTO.setCountryId(rs.getInt("countryId"));
			cityDTO.setStateId(rs.getInt("stateId"));
			cityDTO.setCityName(rs.getString("cityName"));
			cityDTO.setCreatedBy(rs.getString("createdBy"));
			cityDTO.setUpdatedby(rs.getString("updatedBy"));
			cityDTO.setCountryName(rs.getString("countryName"));
			cityDTO.setStateName(rs.getString("stateName"));
		
			return cityDTO;
		}

}
