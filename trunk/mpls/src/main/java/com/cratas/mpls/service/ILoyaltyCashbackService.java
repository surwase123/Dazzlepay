package com.cratas.mpls.service;

import java.util.List;

import com.cratas.mpls.web.dto.LoyaltyCashbackDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author bhupendra
 *
 */
public interface ILoyaltyCashbackService {
	
		List<LoyaltyCashbackDTO> getLoyaltyCashbackByMId(int mId);
		
		int saveLoyaltyCashback(LoyaltyCashbackDTO loyatyCashbackDTO, UserDTO userDTO);
	
		int updateLoyaltyCashback(LoyaltyCashbackDTO loyatyCashbackDTO);
	
		int deleteLoyaltyCashback(LoyaltyCashbackDTO loyatyCashbackDTO);
	
		LoyaltyCashbackDTO getLoyaltyCashbackById(int id);
		
		List<LoyaltyCashbackDTO> getLoyaltyCashback();
		
		LoyaltyCashbackDTO getLoyaltyCashbackByCode(String offerCode);
		
		String validateMerchantOffer(int mId, int cId, double amount, LoyaltyCashbackDTO loyaltyCashback);
		
		double getOfferCashbackAmt(double amount, LoyaltyCashbackDTO loyaltyCashback);
}
