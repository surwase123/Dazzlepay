package com.cratas.mpls.dao;

import java.util.List;

import com.cratas.mpls.web.dto.AreaDTO;

/**
 * 
 * @author bhupendra
 *
 */

public interface IAreaDao {
	
		List<AreaDTO> getArea();
		
		int saveArea(AreaDTO areaDTO);
	
		int updateArea(AreaDTO areaDTO);
	
		int deleteArea(AreaDTO areaDTO);
	
		AreaDTO getAreaByCode(String areaName, String areaCode, int cityId);
	
		AreaDTO getAreaById(int id);

		List<AreaDTO> getAreaList(int cityId);
		
		List<AreaDTO> getAllArea();
	
}
