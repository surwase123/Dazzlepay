package com.cratas.mpls.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.cratas.mpls.web.dto.LoyaltyCardAvailabilityDTO;
import com.cratas.mpls.web.dto.LoyaltyNumberDTO;
import com.cratas.mpls.web.dto.LoyaltyNumberGenerationRequestDTO;

public interface ILoyaltyCardService {
	
	int saveFile(int file);
	
	List<LoyaltyNumberDTO> getLoyaltyNumber();

	List<LoyaltyNumberGenerationRequestDTO> getAllGeneratedLoyaltyNumber();

	List<LoyaltyNumberDTO> getAllGeneratedLoyaltyNumberById(int id);
	void genrateCsv(int id, HttpServletResponse response) throws IOException;

	void genrateCsvByMerchantIdRquestId(int mId, int requestId, HttpServletResponse response) throws IOException;

	void updateLoyaltyCardNumberByMid(int mId, int cId, String loyaltyNumber, String Status, int isActive);
	
	int  validateLoyaltyNumber(int mId, int cId, String loyaltyNumber, String Status,int isActive);

	int activateLoyaltyCardNumber(int mId, int cId, int isActive);

	LoyaltyCardAvailabilityDTO getAllAvailableLoylatyNumberMerchant(int mId);

	LoyaltyCardAvailabilityDTO getAllAvailableLoylatyNumberSuperAdmin();

	int blockLoyaltyCardNumber(int mId, int cId, int isActive,  String status);

	int validateReasignedLoyaltyNumber(int mId, int cId, String loyaltyNumber, String Status, int isActive);

	String verifyMobileNumberbyCid(String mobileNo, int cId);

	// for alloted card csvfile
//   	List<CsvDTO> getLoyaltyCardNumber();
}
