package com.cratas.mpls.service;

import java.util.List;

import com.cratas.mpls.web.dto.CityDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * @author sunil
 *
 */
public interface ICityService {

		List<CityDTO> getCity();
		
		int saveCity(CityDTO cityDTO, UserDTO userDTO);
		
		int updateCity(CityDTO cityDTO);
		
		int deleteCity(CityDTO cityDTO);
		
		CityDTO getCityByName(String cityName, int stateId, int countryId);

		List<CityDTO> getCityList(int stateId);
		
		CityDTO getCityListById(int id);

}
