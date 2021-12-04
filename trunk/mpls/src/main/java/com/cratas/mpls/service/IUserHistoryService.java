/**
 * 
 */
package com.cratas.mpls.service;

import java.util.List;

import com.cratas.mpls.web.dto.UserDTO;
import com.cratas.mpls.web.dto.UserHistoryDTO;

/**
 * @author bhupendra
 *
 */
public interface IUserHistoryService {

	List<UserDTO> getUsers(UserDTO userDTO);

	List<UserHistoryDTO> getUserHistoryDetails(String loginId);

}
