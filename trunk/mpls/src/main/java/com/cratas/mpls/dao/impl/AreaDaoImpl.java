package com.cratas.mpls.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.cratas.mpls.common.query.AreaQuery;
import com.cratas.mpls.dao.IAreaDao;
import com.cratas.mpls.mapper.AreaMapper;
import com.cratas.mpls.web.dto.AreaDTO;
/**
 * 
 * @author bhupendra
 *
 */
@Repository
public class AreaDaoImpl implements IAreaDao{

		@Autowired
		private JdbcTemplate jdbcTemplate;
		
		public List<AreaDTO> getArea() {
			return jdbcTemplate.query(AreaQuery.SELECT_AREA, new AreaMapper());
		}
	
		public int saveArea(AreaDTO areaDTO) {
			   if(getAreaByCode(areaDTO.getAreaName(), areaDTO.getAreaCode(), areaDTO.getCityId()) == null){
				      Object[] args = { areaDTO.getAreaCode(), areaDTO.getAreaName(), areaDTO.getPincode(), areaDTO.getLatitude(),areaDTO.getLongitude(), areaDTO.getCityId(), areaDTO.getStateId(),areaDTO.getCountryId(), areaDTO.getCreatedBy() };
				      return jdbcTemplate.update(AreaQuery.INSERT_AREA, args);
			   }
			   return 2;
		}
	
		public int updateArea(AreaDTO areaDTO) {
			   Object[] args = { areaDTO.getAreaName(), areaDTO.getPincode(), areaDTO.getLatitude(),areaDTO.getLongitude(), areaDTO.getCityId(), areaDTO.getStateId(),areaDTO.getCountryId(), areaDTO.getUpdatedby(), areaDTO.getId() };
		       return jdbcTemplate.update(AreaQuery.UPDATE_AREA, args);
		}
	
		public int deleteArea(AreaDTO areaDTO) {
			   Object[] args = { areaDTO.getUpdatedby(), areaDTO.getId() };
		       return jdbcTemplate.update(AreaQuery.DELETE_AREA, args);
		}
	
		public AreaDTO getAreaByCode(String areaName, String areaCode, int cityId) {
			   Object args[] = { areaCode, areaName, cityId };
		       List<AreaDTO> areaList = jdbcTemplate.query(AreaQuery.SELECT_AREA_BY_CODE, args, new AreaMapper());
		       if(!areaList.isEmpty()){
		    	   return areaList.get(0);
		       }
		       return null;
		}
	
		public AreaDTO getAreaById(int id) {
			   Object args[] = {id};
			   List<AreaDTO> areaList = jdbcTemplate.query(AreaQuery.SELECT_AREA_BY_ID, args, new AreaMapper());
		       if(!areaList.isEmpty()){
		    	   return areaList.get(0);
		       }
		       return null;
		}
	
		public List<AreaDTO> getAreaList(int cityId) {
			   Object args[] = { cityId };
			   List<AreaDTO> areaList = jdbcTemplate.query(AreaQuery.AREA_LIST_BY_CITY_ID, args, new AreaMapper());
			   return areaList;
		}
		
		public List<AreaDTO> getAllArea() {
			   List<AreaDTO> areaList = jdbcTemplate.query(AreaQuery.SELECT_ALL_AVAILABLE_AREA, new BeanPropertyRowMapper<AreaDTO>(AreaDTO.class));
			   return areaList;
		}

}
