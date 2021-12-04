/**
 * 
 */
package com.cratas.mpls.service;

import java.util.List;

import com.cratas.mpls.web.dto.SystemDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * @author bhupendra
 *
 */
public interface ISystemService {
	
		int saveSystem(SystemDTO systemDTO, UserDTO userDTO);
	 
		int updateSystem(SystemDTO systemDTO);
	  
		SystemDTO getSystemById(String systemId);
	  
		List<SystemDTO> getSystem();
	  
		int deleteSystem(SystemDTO systemDTO);
	 
		List<SystemDTO> getActiveSystem();
}
