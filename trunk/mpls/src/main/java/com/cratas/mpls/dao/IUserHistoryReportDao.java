/**
 * 
 */
package com.cratas.mpls.dao;

import java.util.List;

import com.cratas.mpls.web.dto.UserDTO;
import com.cratas.mpls.web.dto.UserHistoryDTO;

/**
 * @author bhupendra
 *
 */
public interface IUserHistoryReportDao {
	
		List<UserDTO> getUsers(UserDTO userDTO);
		
		List<UserHistoryDTO> getUserHistorydetails(String loginId);
}
