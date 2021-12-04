package com.cratas.mpls.dao;

import java.util.List;

import com.cratas.mpls.web.dto.CityDTO;

/**
 * 
 * @author sunil
 *
 */
public interface ICityDao {

		List<CityDTO> getCity();
		
		int saveCity(CityDTO cityDTO);
		
		int updateCity(CityDTO cityDTO);
		
		int deleteCity(CityDTO cityDTO);
		
		CityDTO getCityByName(String cityName, int stateId, int countryId);

		List<CityDTO> getCityList(int stateId);
		
		CityDTO getCityListById(int id);

}
