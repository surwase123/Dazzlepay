package com.cratas.mpls.service;

import java.util.List;

import com.cratas.mpls.dao.dto.SystemParamDTO;

/**
 * 
 * @author mukesh
 *
 */
public interface IAppPropertiesService {
	
	   String getSystemParamValueByName(String paramName);
	   
	   List<SystemParamDTO> getSystemParamValues();
	   
	   List<String> getMPLSFields(String fieldName);
	   	   	   
	   List<SystemParamDTO> getDynamicFields(String fieldName);
	   
	   List<SystemParamDTO> getSystemParameter();

	   int saveSysParameter(SystemParamDTO systemParamDTO);

	   int updateSystemParameter(SystemParamDTO systemParamDTO);

	   int deleteSystemParameter(SystemParamDTO systemParamDTO);

	   SystemParamDTO getSystemParamByName(String paramName);

	   List<SystemParamDTO> getCustomerSearchParameter();
	   
	   List<SystemParamDTO> getMerchantSearchParameter();
}
