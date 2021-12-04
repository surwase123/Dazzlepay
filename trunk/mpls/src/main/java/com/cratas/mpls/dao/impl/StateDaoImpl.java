package com.cratas.mpls.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cratas.mpls.common.query.StateQuery;
import com.cratas.mpls.dao.IStateDao;
import com.cratas.mpls.dao.dto.StateDTO;
import com.cratas.mpls.mapper.StateMapper;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author Priyanka
 *
 */
@Repository
public class StateDaoImpl implements IStateDao {
	
		@Autowired
		private JdbcTemplate jdbcTemplate;
	
		
		public List<StateDTO> getStates() {
			return jdbcTemplate.query(StateQuery.SELECT_STATE, new StateMapper());
		}
	
		
		public int saveState(StateDTO stateDTO, UserDTO userDTO) {
			   if(getStatesByName(stateDTO.getStateName(), stateDTO.getCountryId()) == null){
				      Object[] args = { stateDTO.getCountryId(), stateDTO.getId(), stateDTO.getStateName(), stateDTO.getCreatedBy() };
				      return jdbcTemplate.update(StateQuery.INSERT_STATE, args);
			   }
			   return 2;
		}
		
		public int updateState(StateDTO stateDTO) {
			Object[] args = {stateDTO.getCountryId(),stateDTO.getStateName(),stateDTO.getUpdatedBy(),stateDTO.getId()};
			return jdbcTemplate.update(StateQuery.UPDATE_STATE, args);
		}
	
		
		public int deleteState(StateDTO stateDTO) {
			   Object[] args = { stateDTO.getUpdatedBy(), stateDTO.getId() };
		       return jdbcTemplate.update(StateQuery.DELETE_STATE, args);
		}
		
		public StateDTO getStatesByName(String stateName, int countryId) {
			   Object args[] = { stateName, countryId };
		       List<StateDTO> stateList = jdbcTemplate.query(StateQuery.SELECT_STATE_BY_STATE_NAME, args, new StateMapper());
		       if(!stateList.isEmpty()){
		    	   return stateList.get(0);
		       }
		       return null;
		}
	
		public StateDTO getStatesById(int id) {
			   Object args[] = { id };
		       List<StateDTO> stateList = jdbcTemplate.query(StateQuery.SELECT_STATE_BY_STATE_ID, args, new StateMapper());
		       if(!stateList.isEmpty()){
		    	   return stateList.get(0);
		       }
		       return null;
		}
		
		public List<StateDTO> getStateList(int countryId) {
			   Object args[] = { countryId };
			   List<StateDTO> stateList = jdbcTemplate.query(StateQuery.SELECT_STATE_BY_ID, args, new StateMapper());
			   return stateList;
		}

}
