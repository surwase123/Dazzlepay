package com.cratas.mpls.service;

import java.util.List;

import com.cratas.mpls.web.dto.MerchantEmployeeDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author bhupendra
 *
 */
public interface IMerchantEmployeeService {
	
	   int saveMerchantEmployee(MerchantEmployeeDTO merchantEmployeeDTO, UserDTO loginUserDTO);
	   
	   MerchantEmployeeDTO getMerchantEmployeeById(int userId);
	   
	   List<MerchantEmployeeDTO> getMerchantEmployeeByMId(int mId);

	   int updateMerchantEmployee(MerchantEmployeeDTO merchantEmployeeDTO);

	   int deleteMerchantEmployee(MerchantEmployeeDTO merchantEmployeeDTO);

	   MerchantEmployeeDTO getMerchantEmpId(int id);
	   
	   MerchantEmployeeDTO getMerchantOwner(int mId);

	   List<MerchantEmployeeDTO> getMerchantEmpByEmailId(String emailId);

	   List<MerchantEmployeeDTO> getMerchantEmpByMobileNo(String mobileNumber);
}
