package com.cratas.mpls.service;

import java.util.List;

import com.cratas.mpls.dao.dto.StateDTO;

import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author Priyanka
 *
 */
public interface IStateService {
		
		List<StateDTO> getStates();
		
		int saveState(StateDTO stateDTO, UserDTO userDTO);
		
		int updateState(StateDTO stateDTO);
	
		int deleteState(StateDTO stateDTO);
		
		StateDTO getStatesByName(String stateName, int countryId);
	
		StateDTO getStatesById(int id);
	
		List<StateDTO> getStateList(int countryId);

}
