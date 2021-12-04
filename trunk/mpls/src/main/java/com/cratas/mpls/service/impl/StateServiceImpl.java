package com.cratas.mpls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.utility.SaveUserNotificationThread;
import com.cratas.mpls.dao.IStateDao;
import com.cratas.mpls.dao.dto.StateDTO;
import com.cratas.mpls.service.IMPLSService;
import com.cratas.mpls.service.IStateService;
import com.cratas.mpls.web.dto.NotificationDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author Priyanka
 *
 */

@Service
public class StateServiceImpl implements IStateService {
	
		@Autowired
		private IStateDao stateDao;
		
		@Autowired
		private SaveUserNotificationThread saveUserNotificationThread;
		
		@Autowired
		private ThreadPoolTaskExecutor taskExecutor;
		
		@Autowired
		private IMPLSService mplsService;
	
		
		public List<StateDTO> getStates() {
			return stateDao.getStates();
		}
		
		public int saveState(StateDTO stateDTO, UserDTO userDTO) {
			   int isInsert = stateDao.saveState(stateDTO, userDTO);
			   if(isInsert == 1 && userDTO.getGroupDTO().getGroupType().equals(Constants.MAKER)) {
				    StateDTO state = stateDao.getStatesByName(stateDTO.getStateName(), stateDTO.getCountryId());
					NotificationDTO notification = mplsService.createUserNotificationObj(String.valueOf(state.getId()), state.getMenuName(), userDTO);
					mplsService.updateRecordStatus(notification.getTableName(), notification.getRecordId(), 0);
					saveUserNotificationThread.setData(notification,userDTO);
					taskExecutor.execute(saveUserNotificationThread);
					return isInsert;
			   }
			   return isInsert;
		}
	
		
		public int updateState(StateDTO stateDTO) {
			return stateDao.updateState(stateDTO);
		}
	
		
		public int deleteState(StateDTO stateDTO) {
			return stateDao.deleteState(stateDTO);
		}
	
		
		public StateDTO getStatesByName(String stateName, int countryId) {
			return stateDao.getStatesByName(stateName, countryId);
		}
	
		
		public StateDTO getStatesById(int id) {
			   return stateDao.getStatesById(id);
		}

		public List<StateDTO> getStateList(int countryId) {
			   return stateDao.getStateList(countryId);
		}

}
