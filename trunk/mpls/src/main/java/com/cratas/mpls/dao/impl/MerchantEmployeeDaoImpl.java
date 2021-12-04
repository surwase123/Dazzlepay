package com.cratas.mpls.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cratas.mpls.common.query.MerchantEmployeeQuery;
import com.cratas.mpls.dao.IMerchantEmployeeDao;
import com.cratas.mpls.web.dto.MerchantEmployeeDTO;

/**
 * 
 * @author bhupendra
 *
 */
@Repository
public class MerchantEmployeeDaoImpl implements IMerchantEmployeeDao{
	
		@Autowired
		private JdbcTemplate jdbcTemplate;
	    
		@Autowired
		private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	   
		public int saveMerchantEmployee(MerchantEmployeeDTO merchantEmployee) {
			   if(getMerchantEmployeeByMN(merchantEmployee.getMobileNumber()) == null){
				      BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(merchantEmployee);
				      return namedParameterJdbcTemplate.update(MerchantEmployeeQuery.INSERT_MERCHANT_EMPLOYEE, params);
		       }
		       return 2;	
		}
	
		public MerchantEmployeeDTO getMerchantEmployeeById(int userId) {
			   Object args[] = { userId };
			   List<MerchantEmployeeDTO> employeeList = jdbcTemplate.query(MerchantEmployeeQuery.SELECT_MERCHANT_EMPLOYEE, args, new BeanPropertyRowMapper<MerchantEmployeeDTO>(MerchantEmployeeDTO.class));
			   if(!employeeList.isEmpty()){
				  return employeeList.get(0);
			   }
			   return null;
		}
		
		public MerchantEmployeeDTO getMerchantOwner(int mId) {
			   Object args[] = { mId };
			   List<MerchantEmployeeDTO> employeeList = jdbcTemplate.query(MerchantEmployeeQuery.SELECT_MERCHANT_OWNER, args, new BeanPropertyRowMapper<MerchantEmployeeDTO>(MerchantEmployeeDTO.class));
			   if(!employeeList.isEmpty()){
				  return employeeList.get(0);
			   }
			   return null;
		}
	
		public List<MerchantEmployeeDTO> getMerchantEmployeeByMId(int mId) {
			   Object args[] = { mId };
			   return jdbcTemplate.query(MerchantEmployeeQuery.SELECT_MERCHANT_EMPLOYEE_BY_MID, args, new BeanPropertyRowMapper<MerchantEmployeeDTO>(MerchantEmployeeDTO.class));

		}
	
		public MerchantEmployeeDTO getMerchantEmployeeByMN(String mobileNumber) {
			   Object args[] = { mobileNumber };
			   List<MerchantEmployeeDTO> employeeList = jdbcTemplate.query(MerchantEmployeeQuery.SELECT_MERCHANT_EMPLOYEE_BY_MN, args, new BeanPropertyRowMapper<MerchantEmployeeDTO>(MerchantEmployeeDTO.class));
			   if(!employeeList.isEmpty()){
				  return employeeList.get(0);
			   }
			   return null;
		}
		
		private MerchantEmployeeDTO isExistsMerchantEmp(String mobileNumber, int mId) {
			   Object args[] = { mobileNumber, mId };
			   List<MerchantEmployeeDTO> employeeList = jdbcTemplate.query(MerchantEmployeeQuery.IS_EXISTS_MERCHANT_EMPLOYEE, args, new BeanPropertyRowMapper<MerchantEmployeeDTO>(MerchantEmployeeDTO.class));
			   if(!employeeList.isEmpty()){
				  return employeeList.get(0);
			   }
			   return null;
		}
		
		public int updateMerchantEmployee(MerchantEmployeeDTO merchantEmployee) {
			   if(isExistsMerchantEmp(merchantEmployee.getMobileNumber(), merchantEmployee.getmId()) == null){
				   Object[] args = { merchantEmployee.getMobileNumber(), merchantEmployee.getFirstName(), merchantEmployee.getMiddleName(), merchantEmployee.getLastName(), 
						   merchantEmployee.getUpdatedBy(), merchantEmployee.getUpdatedBy(), merchantEmployee.getEmailId(), merchantEmployee.getLoginId(), merchantEmployee.getId() };
				   int isUpdated = jdbcTemplate.update(MerchantEmployeeQuery.UPDATE_MERCHANT_EMPLOYEE, args);
				   return isUpdated;
			   }
			   return 3;
		}

		public int deleteMerchantEmployee(MerchantEmployeeDTO merchantEmployeeDTO) {
			   Object[] args = { merchantEmployeeDTO.getUpdatedBy(), merchantEmployeeDTO.getId() };
			   return jdbcTemplate.update(MerchantEmployeeQuery.DELETE_MERCHANT_EMPLOYEE, args);
		}

		public MerchantEmployeeDTO getMerchantEmpId(int id) {
			   Object args[] = { id };
			   List<MerchantEmployeeDTO> employeeList = jdbcTemplate.query(MerchantEmployeeQuery.SELECT_MERCHANT_EMPLOYEE_BY_ID, args, new BeanPropertyRowMapper<MerchantEmployeeDTO>(MerchantEmployeeDTO.class));
			   if(!employeeList.isEmpty()){
				  return employeeList.get(0);
			   }
			   return null;
		}

		public boolean isMerchantEmployee(String mobileNumber) {
			   Object args[] = { mobileNumber };
			   List<MerchantEmployeeDTO> merchantEmployeeList = jdbcTemplate.query(MerchantEmployeeQuery.IS_MERCHANT_EMPLOYEE, args, new BeanPropertyRowMapper<MerchantEmployeeDTO>(MerchantEmployeeDTO.class));
			   if(!merchantEmployeeList.isEmpty()){
				    return true;
			   }
			   return false;
		}

		public List<MerchantEmployeeDTO> getMerchantEmpByEmailId(String emailId) {
			   Object args[] = { emailId, emailId };
		       return jdbcTemplate.query(MerchantEmployeeQuery.SELECT_MERCHANT_EMPLOYEE_BY_EMAIL_ID, args, new BeanPropertyRowMapper<MerchantEmployeeDTO>(MerchantEmployeeDTO.class));
		}

		public List<MerchantEmployeeDTO> getMerchantEmpByMobileNo(String mobileNumber) {
			   Object args[] = { mobileNumber };
		       return jdbcTemplate.query(MerchantEmployeeQuery.SELECT_MERCHANT_EMPLOYEE_BY_MOBILE_NUMBER, args, new BeanPropertyRowMapper<MerchantEmployeeDTO>(MerchantEmployeeDTO.class));
		}

}
