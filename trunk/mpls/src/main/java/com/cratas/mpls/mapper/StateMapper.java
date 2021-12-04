package com.cratas.mpls.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.cratas.mpls.dao.dto.StateDTO;

/**
 * 
 * @author Priyanka
 *
 */

public class StateMapper implements RowMapper<StateDTO> {

		public StateDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
			StateDTO stateDTO=new StateDTO();
			stateDTO.setId(rs.getInt("id"));
			stateDTO.setCountryId(rs.getInt("countryId"));
			stateDTO.setStateName(rs.getString("stateName"));
			stateDTO.setCreatedBy(rs.getString("createdBy"));
			stateDTO.setUpdatedBy(rs.getString("updatedBy"));
			stateDTO.setCountryName(rs.getString("countryName"));
			return stateDTO;
		}

}
