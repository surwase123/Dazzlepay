package com.cratas.mpls.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cratas.mpls.dao.dto.SystemParamDTO;

/**
 * 
 * @author mukesh
 *
 */
public class SystemParamMapper implements RowMapper<SystemParamDTO> {

	  public SystemParamDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		   
			   SystemParamDTO systemParamDTO = new SystemParamDTO();
			   systemParamDTO.setId(rs.getInt("id"));
			   systemParamDTO.setParamName(rs.getString("paramName"));
			   systemParamDTO.setParamValue(rs.getString("paramValue"));
			   systemParamDTO.setParamDisplayName(rs.getString("paramDisplayName"));
			   systemParamDTO.setCreatedBy(rs.getString("createdBy"));
			   systemParamDTO.setUpdatedBy(rs.getString("updatedBy"));
			   systemParamDTO.setInsertTimeStamp(rs.getDate("insertTimeStamp"));
			   systemParamDTO.setUpdateTimeStamp(rs.getDate("updateTimeStamp"));
			   return systemParamDTO;
	  }
		

}
