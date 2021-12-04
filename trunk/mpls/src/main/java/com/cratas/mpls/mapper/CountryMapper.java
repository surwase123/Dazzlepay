package com.cratas.mpls.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cratas.mpls.web.dto.CountryDTO;

/**
 * 
 * @author bhupendra
 *
 */
public class CountryMapper implements RowMapper<CountryDTO>{

	@Override
	public CountryDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		CountryDTO countryDTO = new CountryDTO();
		countryDTO.setId(rs.getInt("id"));
		countryDTO.setCountryCode(rs.getInt("countryCode"));
		countryDTO.setCountryCodeAlpha(rs.getString("countryCodeAlpha"));
		countryDTO.setCountryCode2Char(rs.getString("countryCode2Char"));
		countryDTO.setCountryName(rs.getString("countryName"));
		countryDTO.setCreatedBy(rs.getString("createdBy"));
		countryDTO.setUpdatedby(rs.getString("updatedBy"));
		return countryDTO;
	}

}
