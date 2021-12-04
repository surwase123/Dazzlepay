package com.cratas.mpls.service;

import java.util.List;

import com.cratas.mpls.web.dto.LoyaltyNumberDTO;
import com.cratas.mpls.web.dto.MerchantDTO;
import com.cratas.mpls.web.dto.RequestLoyaltyCardDTO;

public interface IRequestLoyaltyCardService {

	int saveRequest(RequestLoyaltyCardDTO requestDto);

	List<RequestLoyaltyCardDTO> getAllRequestByStatus();

	List<RequestLoyaltyCardDTO> getAllRequestByStatus(int status);

	int approveRequest(RequestLoyaltyCardDTO requestDto);

	int rejectRequest(RequestLoyaltyCardDTO requestDto);

	RequestLoyaltyCardDTO getRequestByRequestId(int id);

	MerchantDTO getMerchantById(int id);

	List<LoyaltyNumberDTO> getAllLoyaltyCardNumbersByRequestIdMId(String status, int mId, int requestId);

	List<RequestLoyaltyCardDTO> getAllRequestByMerchantIdAndStatus(int merchantId);

	List<RequestLoyaltyCardDTO> getAllPendingAndAccepetedRequestByMerchantId(int merchantId);
}
