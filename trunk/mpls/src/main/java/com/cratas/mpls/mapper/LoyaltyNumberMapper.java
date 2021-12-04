package com.cratas.mpls.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;


import com.cratas.mpls.web.dto.LoyaltyNumberDTO;
import org.springframework.jdbc.core.RowMapper;

public class LoyaltyNumberMapper implements RowMapper<LoyaltyNumberDTO>{
	
	@Override
	public LoyaltyNumberDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		LoyaltyNumberDTO loyaltyNumberDTO = new LoyaltyNumberDTO();
		loyaltyNumberDTO.setId(rs.getInt("id"));
		loyaltyNumberDTO.setmId(rs.getInt("mId"));
		loyaltyNumberDTO.setRequestId(rs.getInt("requestId"));;
		loyaltyNumberDTO.setStatus(rs.getString("status"));
		loyaltyNumberDTO.setGenrationId(rs.getInt("genrationid"));
		loyaltyNumberDTO.setIsActive(rs.getInt("isActive"));
		loyaltyNumberDTO.setLoyaltyNumber(rs.getString("loyaltyNumber"));
		return loyaltyNumberDTO;
	}

}
