package com.cratas.mpls.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cratas.mpls.dao.IMerchantPlanDao;
import com.cratas.mpls.service.IMerchantPlanService;
import com.cratas.mpls.web.dto.MerchantPlanDTO;

/**
 * 
 * @author mukesh
 *
 */
@Service
public class MerchantPlanServiceImpl implements IMerchantPlanService{
	
		@Autowired
	    private IMerchantPlanDao merchantPlanDao;
	
		public List<MerchantPlanDTO> getMerchantPlan() {
			   return merchantPlanDao.getMerchantPlan();
		}
		
		public int saveMerchantPlan(MerchantPlanDTO merchantVersionDTO) {
			   return merchantPlanDao.saveMerchantPlan(merchantVersionDTO);
		}
		
		public int updateMerchantPlan(MerchantPlanDTO merchantVersionDTO) {
			   return merchantPlanDao.updateMerchantPlan(merchantVersionDTO);
		}
		
		public int deleteMerchantPlan(MerchantPlanDTO merchantVersionDTO) {
			   return merchantPlanDao.deleteMerchantPlan(merchantVersionDTO);
		}
		
		public MerchantPlanDTO getMerchantPlanById(int id) {
			   return merchantPlanDao.getMerchantPlanById(id);
		}
		
		public MerchantPlanDTO getMerchantPlanByName(String planName) {
			   return merchantPlanDao.getMerchantPlanByName(planName);
		}

		public MerchantPlanDTO getMerchantPlanByMId(String merchantId) {
			   return merchantPlanDao.getMerchantPlanByMId(merchantId);
		}

}
