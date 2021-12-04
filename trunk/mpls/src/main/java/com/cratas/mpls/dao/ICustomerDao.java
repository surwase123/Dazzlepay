package com.cratas.mpls.dao;

import java.util.List;

import com.cratas.mpls.web.dto.CustomerDTO;
import com.cratas.mpls.web.dto.MerchantMappingDTO;
import com.cratas.mpls.web.dto.SearchUserDTO;

/**
 * 
 * @author mukesh
 *
 */
public interface ICustomerDao {

	
	   int saveCustomer(CustomerDTO customer);
	   
	   CustomerDTO getCustomerById(int userId);
	   
	   CustomerDTO getCustomerByCustomerId(String customerId);
	   
	   CustomerDTO getCustomerByMN(String mobileNumber);
	   
	   int updateCustomer(CustomerDTO customerDTO);
	   
	   List<CustomerDTO> getAllCustomer();
	   
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
