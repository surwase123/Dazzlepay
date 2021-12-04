package com.cratas.mpls.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cratas.mpls.common.query.CustomerQuery;
import com.cratas.mpls.dao.ICustomerDao;
import com.cratas.mpls.web.dto.CustomerDTO;
import com.cratas.mpls.web.dto.CustomerTransactionDTO;
import com.cratas.mpls.web.dto.MerchantMappingDTO;
import com.cratas.mpls.web.dto.SearchUserDTO;

/**
 * 
 * @author mukesh
 *
 */
@Repository
public class CustomerDaoImpl implements ICustomerDao{
	
	   @Autowired
	   private JdbcTemplate jdbcTemplate;
	    
	   @Autowired
	   private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	   
	   public int saveCustomer(CustomerDTO customer) {
		      if(getCustomerByMN(customer.getMobileNumber()) == null){
			      BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(customer);
			      return namedParameterJdbcTemplate.update(CustomerQuery.INSERT_CUSTOMER, params);
		      }
		      return 2;
	   }
	   
	   public CustomerDTO getCustomerById(int userId) {
			  Object args[] = { userId };
			  List<CustomerDTO> customerList = jdbcTemplate.query(CustomerQuery.SELECT_CUSTOMER, args, new BeanPropertyRowMapper<CustomerDTO>(CustomerDTO.class));
			  if(!customerList.isEmpty()){
				  return customerList.get(0);
			  }
			  return null;
	   }
	   
	   public CustomerDTO getCustomerByCustomerId(String customerId) {
			  Object args[] = { customerId };
			  List<CustomerDTO> customerList = jdbcTemplate.query(CustomerQuery.SELECT_CUSTOMER_BY_CUSTOMER_ID, args, new BeanPropertyRowMapper<CustomerDTO>(CustomerDTO.class));
			  if(!customerList.isEmpty()){
				  return customerList.get(0);
			  }
			  return null;
	   }
	   
	   public CustomerDTO getCustomerByCId(int cId) {
			  Object args[] = { cId };
			  List<CustomerDTO> customerList = jdbcTemplate.query(CustomerQuery.SELECT_CUSTOMER_BY_CID, args, new BeanPropertyRowMapper<CustomerDTO>(CustomerDTO.class));
			  if(!customerList.isEmpty()){
				  return customerList.get(0);
			  }
			  return null;
	   }
	   
	   public CustomerDTO getCustomerByMN(String mobileNumber) {
			  Object args[] = { mobileNumber };
			  List<CustomerDTO> customerList = jdbcTemplate.query(CustomerQuery.SELECT_CUSTOMER_BY_MN, args, new BeanPropertyRowMapper<CustomerDTO>(CustomerDTO.class));
			  if(!customerList.isEmpty()){
				  return customerList.get(0);
			  }
			  return null;
	   }
	   
	   public int updateCustomer(CustomerDTO customer) {
		   	  Object[] args = { customer.getLoginId(), customer.getMobileNumber(), customer.getFirstName(), customer.getMiddleName(), customer.getLastName(), customer.getEmailId(), customer.getUserImage(), customer.getUserId(), customer.getUserId()};
		      return jdbcTemplate.update(CustomerQuery.UPDATE_CUSTOMER, args);
	   }
	   
	   public List<CustomerDTO> getAllCustomer() {
           return jdbcTemplate.query(CustomerQuery.GET_ALL_CUSTOMER, new BeanPropertyRowMapper<CustomerDTO>(CustomerDTO.class));
	   }

	   public List<CustomerDTO> getCustomerDetails(String customerSearchMenu, String searchValue) {
		      String query = CustomerQuery.GET_CUSTOMER_DETAIL;
		      Object args[] = {searchValue};
		      query += " where " + customerSearchMenu + " = " + "?";
		      return jdbcTemplate.query(query, args, new BeanPropertyRowMapper<CustomerDTO>(CustomerDTO.class));
	   }

		public int updateCustomerStatus(SearchUserDTO searchUser) {
			   int isUpdate=0;
			   if(searchUser.getIsActive() == 1) {
				   Object args[] = { searchUser.getUpdatedBy(), searchUser.getUpdatedBy(), searchUser.getId() };
				   isUpdate =  jdbcTemplate.update(CustomerQuery.BLOCK_CUSTOMER, args);
			   }else {
				   Object args[] = { searchUser.getUpdatedBy(), searchUser.getUpdatedBy(), searchUser.getId()};
				   isUpdate =  jdbcTemplate.update(CustomerQuery.UNBLOCK_CUSTOMER, args);
			   }
			   return isUpdate;
		}

		public List<CustomerDTO> getCustomerByEmailId(String emailId) {
			   Object args[] = {emailId};
			   return jdbcTemplate.query(CustomerQuery.GET_CUSTOMER_BY_EMAILID, args, new BeanPropertyRowMapper<CustomerDTO>(CustomerDTO.class));
		}

		public List<CustomerDTO> getCustomerByMobileNo(String mobile) {
			   Object args[] = {mobile};
			   return jdbcTemplate.query(CustomerQuery.SELECT_CUSTOMER_BY_MN, args, new BeanPropertyRowMapper<CustomerDTO>(CustomerDTO.class));
		}
		
		public List<MerchantMappingDTO> getMerchantByCustomer(int cId) {
			  Object args[] = { cId };
			  List<MerchantMappingDTO> merchantMappingCustomerList = jdbcTemplate.query(CustomerQuery.SELECT_MERCHANT_BY_CID, args, new BeanPropertyRowMapper<MerchantMappingDTO>(MerchantMappingDTO.class));
			  return merchantMappingCustomerList;
	    }

		public int getTotalCustomer() {
			   List<CustomerDTO> customerList = jdbcTemplate.query(CustomerQuery.GET_TOTAL_CUSTOMER, new BeanPropertyRowMapper<CustomerDTO>(CustomerDTO.class));
			   if(customerList != null) {
				   return customerList.size();
			   }else {
				   return 0;
			   }
		}

		public int getActiveCustomer(String fromDate, String toDate) {
			   Object[] args = { fromDate, toDate };
			   List<CustomerTransactionDTO> customerList = jdbcTemplate.query(CustomerQuery.GET_ACTIVE_CUSTOMER, args, new BeanPropertyRowMapper<CustomerTransactionDTO>(CustomerTransactionDTO.class));
			   if(customerList != null) {
				   return customerList.size();
			   }else {
				   return 0;
			   }
		}

		public int getRecentCustomer(String fromDate, String toDate) {
			   Object[] args = { fromDate, toDate };
			   List<CustomerDTO> customerList = jdbcTemplate.query(CustomerQuery.GET_RECENT_CUSTOMER, args, new BeanPropertyRowMapper<CustomerDTO>(CustomerDTO.class));
			   if(customerList != null) {
				   return customerList.size();
			   }else {
				   return 0;
			   }
		}

}
