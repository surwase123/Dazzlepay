package com.cratas.mpls.service;

import java.util.List;

import com.cratas.mpls.web.dto.CustomerDTO;
import com.cratas.mpls.web.dto.MerchantMappingDTO;
import com.cratas.mpls.web.dto.SearchUserDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author mukesh
 *
 */
public interface ICustomerService {
	
	   int saveCustomer(CustomerDTO customer, UserDTO loginUserDTO,String registrationType);
	   
	   CustomerDTO getCustomerById(int userId);
	   
	   CustomerDTO getCustomerByCustomerId(String customerId);
	   
	   int updateCustomer(CustomerDTO customer);
	   
	   CustomerDTO getCustomerByMN(String mobileNumber);
	   
	   List<CustomerDTO> getAllCustomer();
	   
	   CustomerDTO generateCustomerId(CustomerDTO customer);
	   
	   CustomerDTO getCustomerByCId(int cId);
	   
	   List<CustomerDTO> getCustomerDetails(String customerSearchMenu, String searchValue);

	   int updateCustomerStatus(SearchUserDTO searchUserDTO);

	   List<CustomerDTO> getCustomerByEmailId(String emailId);

	   List<CustomerDTO> getCustomerByMobileNo(String mobile);
	   
	   List<MerchantMappingDTO> getMerchantByCustomer(int cId);

	   int getTotalCustomer();

	   int getActiveCustomer(String fromDate, String toDate);

	   int getRecentCustomer(String fromDate, String toDate);

}
