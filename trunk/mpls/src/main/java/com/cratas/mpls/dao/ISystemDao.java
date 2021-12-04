/**
 * 
 */
package com.cratas.mpls.dao;

import java.util.List;

import com.cratas.mpls.web.dto.SystemDTO;

/**
 * @author bhupendra
 *
 */
public interface ISystemDao {
	
		int saveSystem(SystemDTO systemDTO);
		 
		int updateSystem(SystemDTO systemDTO);
	  
		SystemDTO getSystemById(String systemId);
	  
		List<SystemDTO> getSystem();
	  
		int deleteSystem(SystemDTO systemDTO);
	 
		List<SystemDTO> getActiveSystem();
		
}
