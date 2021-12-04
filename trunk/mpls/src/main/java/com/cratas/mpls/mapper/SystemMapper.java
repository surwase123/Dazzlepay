/**
 * 
 */
package com.cratas.mpls.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cratas.mpls.web.dto.SystemDTO;

/**
 * @author cts108
 *
 */
public class SystemMapper implements RowMapper<SystemDTO>{


		public SystemDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			   SystemDTO systemDTO = new SystemDTO();
		       systemDTO.setId(rs.getInt("id"));
		       systemDTO.setSystemId(rs.getString("systemId"));
		       systemDTO.setSystemName(rs.getString("systemName"));
		       systemDTO.setAddressLine1(rs.getString("addressLine1"));
		       systemDTO.setAddressLine2(rs.getString("addressLine2"));
		       systemDTO.setCity(rs.getString("city"));
		       systemDTO.setState(rs.getString("state"));
		       systemDTO.setPostalCode(rs.getString("postalCode"));
		       systemDTO.setCountry(rs.getString("country"));
		       systemDTO.setFirstName(rs.getString("firstName"));
		       systemDTO.setMiddleName(rs.getString("middleName"));
		       systemDTO.setLastName(rs.getString("lastName"));
		       systemDTO.setEmailId(rs.getString("emailId"));
		       systemDTO.setMobileNumber(rs.getString("mobileNumber"));
		       systemDTO.setPhoneNumber(rs.getString("phoneNumber"));
		       systemDTO.setFaxNo(rs.getString("faxNo"));
		       systemDTO.setStatus(rs.getString("status"));
			   return systemDTO;
		}
}
