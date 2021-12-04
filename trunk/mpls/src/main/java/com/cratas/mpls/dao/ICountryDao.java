package com.cratas.mpls.dao;

import java.util.List;

import com.cratas.mpls.web.dto.CountryDTO;

/**
 * 
 * @author bhupendra
 *
 */
public interface ICountryDao {

		List<CountryDTO> getCountry();
	
		int saveCountry(CountryDTO countryDTO);
	
		int updateCountry(CountryDTO countryDTO);
	
		int deleteCountry(CountryDTO countryDTO);
	
		CountryDTO getCountryByCode(int countryCode);
		
		CountryDTO getCountryById(int countryId);
}
