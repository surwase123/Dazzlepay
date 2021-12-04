package com.cratas.mpls.dao;

import java.util.List;

import com.cratas.mpls.dao.dto.SystemParamDTO;

/**
 * 
 * @author mukesh
 *
 */
public interface IAppPropertiesDao {

	   SystemParamDTO getSystemParamValueByName(String paramName);
	   
	   List<SystemParamDTO> getSystemParamValues();
	   
	   List<SystemParamDTO> getSystemParameter();
	   
	   int saveSysParameter(SystemParamDTO systemParamDTO);
	   
	   int updateSystemParameter(SystemParamDTO systemParamDTO);
	   
	   int deleteSystemParameter(SystemParamDTO systemParamDTO);
	   
	   SystemParamDTO getSystemParamByName(String paramName);
}
