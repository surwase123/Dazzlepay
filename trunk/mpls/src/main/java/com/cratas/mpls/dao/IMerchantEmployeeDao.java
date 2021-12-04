package com.cratas.mpls.dao;

import java.util.List;

import com.cratas.mpls.web.dto.MerchantEmployeeDTO;

/**
 * 
 * @author bhupendra
 *
 */
public interface IMerchantEmployeeDao {

	   int saveMerchantEmployee(MerchantEmployeeDTO merchantEmployeeDTO);
	   
	   MerchantEmployeeDTO getMerchantEmployeeById(int userId);
	   
	   List<MerchantEmployeeDTO> getMerchantEmployeeByMId(int mId);
	   
	   MerchantEmployeeDTO getMerchantEmployeeByMN(String mobileNumber);

	   int updateMerchantEmployee(MerchantEmployeeDTO merchantEmployeeDTO);

	   int deleteMerchantEmployee(MerchantEmployeeDTO merchantEmployeeDTO);

	   MerchantEmployeeDTO getMerchantEmpId(int id);

	   boolean isMerchantEmployee(String mobileNumber);
	   
	   MerchantEmployeeDTO getMerchantOwner(int mId);

	   List<MerchantEmployeeDTO> getMerchantEmpByEmailId(String emailId);

	   List<MerchantEmployeeDTO> getMerchantEmpByMobileNo(String mobileNumber);
	  
}
