package com.cratas.mpls.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cratas.mpls.web.dto.CurrencyDTO;

/**
 * 
 * @author mukesh
 *
 */

public class CurrencyMapper implements RowMapper<CurrencyDTO> {

	public CurrencyDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		   CurrencyDTO currencyDTO = new CurrencyDTO();
		   currencyDTO.setId(rs.getInt("id"));
		   currencyDTO.setCurrencyCode(rs.getInt("currencyCode"));
		   currencyDTO.setCurrencyCodeAlpha(rs.getString("currencyCodeAlpha"));
		   currencyDTO.setCurrencyName(rs.getString("currencyName"));
		   currencyDTO.setCountryName(rs.getString("countryName"));
		   currencyDTO.setExponent(rs.getInt("exponent"));
		   currencyDTO.setCreatedBy(rs.getString("createdBy"));
		   currencyDTO.setUpdatedby(rs.getString("updatedBy"));
		   return currencyDTO;
	}

}
