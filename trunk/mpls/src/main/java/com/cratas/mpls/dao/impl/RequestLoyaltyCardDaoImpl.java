package com.cratas.mpls.dao.impl;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.cratas.mpls.common.query.AreaQuery;
import com.cratas.mpls.common.query.LoyaltyCardQuery;
import com.cratas.mpls.common.query.RequestLoyaltyCardQuery;
import com.cratas.mpls.dao.IRequestLoyaltyCardDao;
import com.cratas.mpls.mapper.AreaMapper;
import com.cratas.mpls.mapper.LoyaltyCardRequestMapper;
import com.cratas.mpls.web.dto.AreaDTO;
import com.cratas.mpls.web.dto.MerchantDTO;
import com.cratas.mpls.web.dto.PushNotificationLogDTO;
import com.cratas.mpls.web.dto.RequestLoyaltyCardDTO;

@Repository
public class RequestLoyaltyCardDaoImpl implements IRequestLoyaltyCardDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public int saveRequest(RequestLoyaltyCardDTO request) {
		Object [] args= {
				request.getQuantityOfCards(),
				request.getmId(),
				request.getMessage(),
				request.getShippingAddress(),
				request.getMerchantId(),
				request.getMerchantName(),
				request.getCostPerUnit(),
				request.getTotalCost(),
				request.getCardType(),
				request.getPaymentMode(),
				request.getPaymentReferenceNumber()
		};
		return  jdbcTemplate.update(RequestLoyaltyCardQuery.SAVE_REQUEST, args);		
	}

	@Override
	public List<RequestLoyaltyCardDTO> getAllRequestByStatus() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query(RequestLoyaltyCardQuery.GET_LOYALTYCARD_NOTIFICATION, new LoyaltyCardRequestMapper());
	}
	
	public int updateRequestStatusById(RequestLoyaltyCardDTO request)
	{
		Object [] args= {
				request.getStatus(),
				request.getReason(),
				request.getId(),
		};
		return jdbcTemplate.update(RequestLoyaltyCardQuery.UPDATE_LOYALTY_REQUEST_STATUS,args);		
	}
	
	public int updateRequestStatusWithResonById(RequestLoyaltyCardDTO request)
	{
		Object [] args= {
				request.getStatus(),
				request.getReason(),
				request.getId(),
		};
		int resp= jdbcTemplate.update(RequestLoyaltyCardQuery.UPDATE_LOYALTY_REQUEST_STATUSWITHREASON,args);
		return resp;
	}

	@Override
	public RequestLoyaltyCardDTO getRequestByRequestId(int id) {
		Object [] args= {id};
		List<RequestLoyaltyCardDTO> areaList = jdbcTemplate.query(RequestLoyaltyCardQuery.GET_REQUEST_BY_ID, args,new LoyaltyCardRequestMapper());
	       if(!areaList.isEmpty()){
	    	   return areaList.get(0);
	       }
	       return null;
	}
	@Override
	public List<RequestLoyaltyCardDTO> getAllRequestByMerchantIdAndStatus(int merchantId) {
		Object[] args = { merchantId };
		return jdbcTemplate.query(RequestLoyaltyCardQuery.GET_ALLOTED_LOYALTYCARD_NUMBER_BY_MERCHANTID_AND_STATUS, args,
				new LoyaltyCardRequestMapper());

	}
	@Override
	public List<RequestLoyaltyCardDTO> getAllPendingAndAccepetedRequestByMerchantId(int merchantId) {
		Object[] args = { merchantId };
		return jdbcTemplate.query(RequestLoyaltyCardQuery.GET_PENDING_ACCEPTED_REJECTED_LOYALTYCARD_NUMBER_REQUEST_BY_MERCHANTID_AND_STATUS, args,
				new LoyaltyCardRequestMapper());

	}
	@Override
	public List<RequestLoyaltyCardDTO> getAllRequestByStatus(int status) {
		Object [] args= {
				status
		};
		return jdbcTemplate.query(RequestLoyaltyCardQuery.GET_LOYALTYCARD_NOTIFICATION_BY_STATUS, args,new LoyaltyCardRequestMapper());
	}

}
