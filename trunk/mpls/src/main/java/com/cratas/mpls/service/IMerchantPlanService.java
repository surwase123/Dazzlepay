package com.cratas.mpls.service;

import java.util.List;

import com.cratas.mpls.web.dto.MerchantPlanDTO;

/**
 * 
 * @author mukesh
 *
 */
public interface IMerchantPlanService {
	
	   List<MerchantPlanDTO> getMerchantPlan();
		
	   int saveMerchantPlan(MerchantPlanDTO merchantVersionDTO);
	
	   int updateMerchantPlan(MerchantPlanDTO merchantVersionDTO);
	
	   int deleteMerchantPlan(MerchantPlanDTO merchantVersionDTO);
		
	   MerchantPlanDTO getMerchantPlanById(int id);
	   
	   MerchantPlanDTO getMerchantPlanByName(String planName);

	   MerchantPlanDTO getMerchantPlanByMId(String merchantId);

}
