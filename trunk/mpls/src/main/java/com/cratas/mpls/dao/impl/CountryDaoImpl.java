package com.cratas.mpls.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cratas.mpls.common.query.CountryQuery;
import com.cratas.mpls.dao.ICountryDao;
import com.cratas.mpls.mapper.CountryMapper;
import com.cratas.mpls.web.dto.CountryDTO;

/**
 * 
 * @author bhupendra
 *
 */
@Repository
public class CountryDaoImpl implements ICountryDao{

		@Autowired
		private JdbcTemplate jdbcTemplate;
		
		public List<CountryDTO> getCountry() {
			   return jdbcTemplate.query(CountryQuery.SELECT_COUNTRY, new CountryMapper());
		}
		
		public int saveCountry(CountryDTO countryDTO) {
			   if(isExistsCountry(countryDTO.getCountryCode(), countryDTO.getCountryName()) == null){
			      Object[] args = { countryDTO.getCountryCode(), countryDTO.getCountryCodeAlpha(), countryDTO.getCountryCode2Char(), countryDTO.getCountryName(), countryDTO.getCreatedBy() };
			      return jdbcTemplate.update(CountryQuery.INSERT_COUNTRY, args);
	  	       }
	  	       return 2;
		}
		
		public int updateCountry(CountryDTO countryDTO) {
			   Object[] args = { countryDTO.getCountryCode(), countryDTO.getCountryCodeAlpha(), countryDTO.getCountryCode2Char(),countryDTO.getCountryName(), countryDTO.getUpdatedby(), countryDTO.getId() };
		       return jdbcTemplate.update(CountryQuery.UPDATE_COUNTRY, args);
		}
		
		public int deleteCountry(CountryDTO countryDTO) {
			   Object[] args = { countryDTO.getUpdatedby(), countryDTO.getId() };
		       return jdbcTemplate.update(CountryQuery.DELETE_COUNTRY, args);
		}
		
		public CountryDTO getCountryByCode(int countryCode) {
			   Object args[] = { countryCode };
		       List<CountryDTO> countryList = jdbcTemplate.query(CountryQuery.SELECT_COUNTRY_BY_COUNTRY_CODE, args, new CountryMapper());
		       if(!countryList.isEmpty()){
		    	   return countryList.get(0);
		       }
		       return null;
		}
		
		private CountryDTO isExistsCountry(int countryCode, String countryName) {
			    Object args[] = { countryCode, countryName };
		        List<CountryDTO> countryList = jdbcTemplate.query(CountryQuery.IS_EXISTS_COUNTRY, args, new CountryMapper());
		        if(!countryList.isEmpty()){
		    	   return countryList.get(0);
		        }
		        return null;
		}
		
		public CountryDTO getCountryById(int countryId) {
			   Object args[] = { countryId };
		       List<CountryDTO> countryList = jdbcTemplate.query(CountryQuery.SELECT_COUNTRY_BY_COUNTRY_ID, args, new CountryMapper());
		       if(!countryList.isEmpty()){
		    	   return countryList.get(0);
		       }
		       return null;
		}
}
