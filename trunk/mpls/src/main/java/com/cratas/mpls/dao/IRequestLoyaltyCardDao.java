package com.cratas.mpls.dao;

import java.util.List;

import com.cratas.mpls.web.dto.MerchantDTO;
import com.cratas.mpls.web.dto.RequestLoyaltyCardDTO;

public interface IRequestLoyaltyCardDao {

	int saveRequest(RequestLoyaltyCardDTO request);
	
	List<RequestLoyaltyCardDTO> getAllRequestByStatus();
	
	List<RequestLoyaltyCardDTO> getAllRequestByStatus(int status);
	
	int updateRequestStatusById(RequestLoyaltyCardDTO request);
	
	int updateRequestStatusWithResonById(RequestLoyaltyCardDTO request);

	List<RequestLoyaltyCardDTO> getAllRequestByMerchantIdAndStatus(int merchantId);

	RequestLoyaltyCardDTO getRequestByRequestId(int id);

	List<RequestLoyaltyCardDTO> getAllPendingAndAccepetedRequestByMerchantId(int merchantId);
}
