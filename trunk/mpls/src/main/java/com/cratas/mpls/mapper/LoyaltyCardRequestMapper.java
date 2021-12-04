package com.cratas.mpls.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cratas.mpls.web.dto.LoyaltyNumberDTO;
import com.cratas.mpls.web.dto.RequestLoyaltyCardDTO;

public class LoyaltyCardRequestMapper implements RowMapper<RequestLoyaltyCardDTO>{
	
	@Override
	public RequestLoyaltyCardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		RequestLoyaltyCardDTO loyaltyCardDTO = new RequestLoyaltyCardDTO();
		loyaltyCardDTO.setId(rs.getInt("id"));
		loyaltyCardDTO.setmId(rs.getInt("mId"));
		loyaltyCardDTO.setMerchantName(rs.getString("name"));
		loyaltyCardDTO.setName(rs.getString("name"));
		loyaltyCardDTO.setMerchantId(rs.getString("merchantId"));
		loyaltyCardDTO.setStatus(rs.getInt("status"));
		loyaltyCardDTO.setMessage(rs.getString("message"));
		loyaltyCardDTO.setQuantityOfCards(rs.getInt("quantityOfCards"));
		loyaltyCardDTO.setAllocated(rs.getInt("allocated"));
		loyaltyCardDTO.setCardType(rs.getString("cardtype"));
		loyaltyCardDTO.setCostPerUnit(rs.getBigDecimal("cost_per_unit"));
		loyaltyCardDTO.setTotalCost(rs.getBigDecimal("total_cost"));
		loyaltyCardDTO.setPaymentMode(rs.getString("payment_mode"));
		loyaltyCardDTO.setPaymentReferenceNumber(rs.getString("payment_reference_no"));
		loyaltyCardDTO.setShippingAddress(rs.getString("shippingAddress"));
		loyaltyCardDTO.setInsertTimeStamp(rs.getDate("insertTimeStamp"));
		loyaltyCardDTO.setUpdateTimeStamp(rs.getDate("updateTimeStamp"));
		return loyaltyCardDTO;
	}
}
