package com.cratas.mpls.service;

import java.util.List;

import com.cratas.mpls.web.dto.CountryDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author bhupendra
 *
 */
public interface ICountryService {

		List<CountryDTO> getCountry();
	
		int saveCountry(CountryDTO countryDTO, UserDTO userDTO);
	
		int updateCountry(CountryDTO countryDTO);
	
		int deleteCountry(CountryDTO countryDTO);
	
		CountryDTO getCountryByCode(int countryCode);
		
		CountryDTO getCountryById(int countryId);
}
