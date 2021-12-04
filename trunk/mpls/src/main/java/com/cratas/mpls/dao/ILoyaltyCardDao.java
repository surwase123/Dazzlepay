package com.cratas.mpls.dao;

import java.util.List;
import java.util.Set;

import com.cratas.mpls.web.dto.CountryDTO;
import com.cratas.mpls.web.dto.LoyaltyNumberDTO;
import com.cratas.mpls.web.dto.LoyaltyNumberGenerationRequestDTO;

public interface ILoyaltyCardDao {

	int insertLoyaltyNumbers(Set<LoyaltyNumberDTO> numberSet);
	
	LoyaltyNumberDTO checkLoyaltyNumberExist(String number);
	
	int saveLoyaltyFile(LoyaltyNumberGenerationRequestDTO loyaltyNumberGenerationRequestDTO);
	
	int updateFileStatus(LoyaltyNumberGenerationRequestDTO loyaltyNumberGenerationRequestDTO);
	
	List<Long>getAllLoyaltyNumbers();
	
	List<LoyaltyNumberDTO> getLoyaltyNumber();

	LoyaltyNumberDTO getLastLoyaltyNumber();

	int updateLoyaltyNumbers(Set<LoyaltyNumberDTO> numberSet);
	
	List<LoyaltyNumberGenerationRequestDTO> getAllGeneratedLoyaltyNumber();
	
	List<LoyaltyNumberDTO> getAllGeneratedLoyaltyNumberById(int id);
	List<LoyaltyNumberDTO> getLoyaltyNumberForAllocation(String status, int isActive,int limit);

	List<LoyaltyNumberDTO> getAllGeneratedLoyaltyNumberByGenrationId(int id);

	List<LoyaltyNumberDTO> getLoyaltyNumbersByMidAndStatus(String status, int mId, int requestId);

	List<LoyaltyNumberDTO> getAllAssignedLoyaltyNumber();

	void updateLoyaltyCardNumber(int mId, int cId, String loyaltyNumber, String Status,int isActive);
	
	int activateLoyaltyCardNumber(int mId,int cId,int isActive);

	LoyaltyNumberDTO validateLoyatyNumber(String loyaltyNumber);

	List<LoyaltyNumberDTO> getAllAssignedLoyaltyNumberByMid(int mId);

	List<LoyaltyNumberDTO> getAllAvailableLoyaltyNumberByMid(int mId);

	List<LoyaltyNumberDTO> getNotAllocatedLoyaltyNumbers();

	int blockLoyaltyCardNumber(int mId, int cId, int isActive, String status);

	LoyaltyNumberDTO validateLoyatyNumberByCid(int cid);
}
