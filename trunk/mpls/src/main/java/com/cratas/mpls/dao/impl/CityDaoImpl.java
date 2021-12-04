package com.cratas.mpls.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cratas.mpls.common.query.CityQuery;
import com.cratas.mpls.dao.ICityDao;
import com.cratas.mpls.mapper.CityMapper;
import com.cratas.mpls.web.dto.CityDTO;

/**
 * 
 * @author sunil
 *
 */
@Repository
public class CityDaoImpl implements ICityDao {

		@Autowired
		private JdbcTemplate jdbcTemplate;
		
		public List<CityDTO> getCity() {
			   return jdbcTemplate.query(CityQuery.SELECT_CITY, new CityMapper());
		}
		
		public int saveCity(CityDTO cityDTO) {
			   if (getCityByName(cityDTO.getCityName(), cityDTO.getStateId(), cityDTO.getCountryId()) == null) {
					Object[] args = { cityDTO.getCountryId(), cityDTO.getStateId(), cityDTO.getCityName()};
					return jdbcTemplate.update(CityQuery.INSERT_CITY, args);
			   }
			   return 2;
		}
		
		public int updateCity(CityDTO cityDTO) {
			   Object[] args = { cityDTO.getCityName(), cityDTO.getCountryId(), cityDTO.getStateId(), cityDTO.getUpdatedby(), cityDTO.getId() };
			   return jdbcTemplate.update(CityQuery.UPDATE_CITY, args);
		}
		
		public int deleteCity(CityDTO cityDTO) {
			   Object[] args = { cityDTO.getUpdatedby(), cityDTO.getId() };
			   return jdbcTemplate.update(CityQuery.DELETE_CITY, args);
		}
		
		public CityDTO getCityByName(String cityName, int stateId, int countryId) {
			   Object args[] = { cityName, stateId, countryId };
			   List<CityDTO> cityList = jdbcTemplate.query(CityQuery.SELECT_CITY_BY_NAME, args, new CityMapper());
			   if (!cityList.isEmpty()) {
					return cityList.get(0);
			   }
			   return null;
		}
		
		public CityDTO getCityListById(int id) {
			   Object args[] = { id };
			   List<CityDTO> cityList = jdbcTemplate.query(CityQuery.CITY_LIST_BY_ID, args, new CityMapper());
			   if (!cityList.isEmpty()) {
					return cityList.get(0);
			   }
			   return null;
		}

		public List<CityDTO> getCityList(int stateId) {
			   Object args[] = { stateId };
			   List<CityDTO> cityList = jdbcTemplate.query(CityQuery.CITY_LIST, args, new CityMapper());
			   return cityList;
		}

}
