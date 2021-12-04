package com.cratas.mpls.dao;

import java.util.List;

import com.cratas.mpls.web.dto.LoyaltyCashbackDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author bhupendra
 *
 */
public interface ILoyaltyCashbackDao {
	
		List<LoyaltyCashbackDTO> getLoyaltyCashbackByMId(int mId);
		
		int saveLoyaltyCashback(LoyaltyCashbackDTO loyatyCashbackDTO, UserDTO userDTO);
	
		int updateLoyaltyCashback(LoyaltyCashbackDTO loyatyCashbackDTO);
	
		int deleteLoyaltyCashback(LoyaltyCashbackDTO loyatyCashbackDTO);
	
		LoyaltyCashbackDTO getLoyaltyCashbackByName(int mid, String offerName, String offerCode);

		LoyaltyCashbackDTO getLoyaltyCashbackById(int id);
		
		List<LoyaltyCashbackDTO> getLoyaltyCashback();
		
		LoyaltyCashbackDTO getLoyaltyCashbackByCode(String offerCode);
}
