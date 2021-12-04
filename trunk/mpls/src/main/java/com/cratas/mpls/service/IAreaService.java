package com.cratas.mpls.service;

import java.util.List;

import com.cratas.mpls.web.dto.AreaDTO;
import com.cratas.mpls.web.dto.UserDTO;
/**
 * 
 * @author bhupendra
 *
 */

public interface IAreaService {

		List<AreaDTO> getArea();
		
		int saveArea(AreaDTO areaDTO,UserDTO userDTO);
	
		int updateArea(AreaDTO areaDTO);
	
		int deleteArea(AreaDTO areaDTO);
	
		AreaDTO getAreaByCode(String areaName, String areaCode, int cityId);
	
		AreaDTO getAreaById(int id);

		List<AreaDTO> getAreaList(int cityId);
		
		List<AreaDTO> getAllArea();
	
}
