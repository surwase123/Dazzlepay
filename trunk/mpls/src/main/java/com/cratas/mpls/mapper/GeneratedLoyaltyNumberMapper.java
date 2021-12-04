package com.cratas.mpls.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cratas.mpls.web.dto.LoyaltyNumberGenerationRequestDTO;

public class GeneratedLoyaltyNumberMapper implements RowMapper<LoyaltyNumberGenerationRequestDTO> {

	@Override
	public LoyaltyNumberGenerationRequestDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		LoyaltyNumberGenerationRequestDTO loyaltyNumberGenerationRequestDTO = new LoyaltyNumberGenerationRequestDTO();
		loyaltyNumberGenerationRequestDTO.setId(rs.getInt("id"));
		loyaltyNumberGenerationRequestDTO.setQuantity(rs.getInt("quantityofnumbers"));
		loyaltyNumberGenerationRequestDTO.setStatus(rs.getString("status"));
		loyaltyNumberGenerationRequestDTO.setReason(rs.getString("reason"));
		loyaltyNumberGenerationRequestDTO.setInsertTimeStamp(rs.getDate("insertTimeStamp"));
		return loyaltyNumberGenerationRequestDTO;
	}

}
