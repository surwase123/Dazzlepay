/**
 * 
 */
package com.cratas.mpls.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cratas.mpls.dao.IUserHistoryReportDao;
import com.cratas.mpls.service.IUserHistoryService;
import com.cratas.mpls.web.dto.UserDTO;
import com.cratas.mpls.web.dto.UserHistoryDTO;

/**
 * @author bhupendra
 *
 */
@Service
public class UserHistoryServiceImpl implements IUserHistoryService{

		@Autowired
		private IUserHistoryReportDao userHistoryReportDao;
		
		public List<UserDTO> getUsers(UserDTO userDTO) {
			   return userHistoryReportDao.getUsers(userDTO);
		}
	
		public List<UserHistoryDTO> getUserHistoryDetails(String loginId) {
			   return userHistoryReportDao.getUserHistorydetails(loginId);
		}

}
