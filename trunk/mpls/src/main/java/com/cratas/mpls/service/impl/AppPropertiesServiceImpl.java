package com.cratas.mpls.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.dao.IAppPropertiesDao;
import com.cratas.mpls.dao.dto.SystemParamDTO;
import com.cratas.mpls.service.IAppPropertiesService;
import com.cratas.mpls.service.IUtilityService;

/**
 * 
 * @author mukesh
 *
 */
@Service
public class AppPropertiesServiceImpl implements IAppPropertiesService {

	
	   @Autowired
	   private IAppPropertiesDao appPropertiesDao;
	   
	   @Autowired
	   private IUtilityService utilityService;
	
	   public String getSystemParamValueByName(String paramName) {
		      SystemParamDTO systemParamDTO = appPropertiesDao.getSystemParamValueByName(paramName);
			  if(null != systemParamDTO){
				    return systemParamDTO.getParamValue();
			  }
			  return Constants.BLANK;
	   }
	
	   public List<SystemParamDTO> getSystemParamValues() {
			  return appPropertiesDao.getSystemParamValues();
	   }
	   
	   public List<String> getMPLSFields(String fieldName){
		      List<String> fileTypeList = new ArrayList<>();
		      String sourceFileType = getSystemParamValueByName(fieldName);
		      for (String fileType : sourceFileType.split(Constants.COMMA)) {
		    	    fileTypeList.add(fileType);
			  }
		      return fileTypeList;
	   }
	   
	   public List<SystemParamDTO> getDynamicFields(String fieldName){
		      List<SystemParamDTO> fieldList = new ArrayList<>();
		      String sourceFileType = getSystemParamValueByName(fieldName);
		      for (String transactionFields : sourceFileType.split(Constants.COMMA)) {
		    	    String fields[] = transactionFields.split(Constants.TILDE);
		    	    SystemParamDTO systemParamDTO = new SystemParamDTO();
		    	    systemParamDTO.setParamDisplayName(fields[0]);
		    	    systemParamDTO.setParamValue(fields[1]);
		    	    if(fields.length >= 2){
		    	    	systemParamDTO.setLength(utilityService.convertStringToInt(fields[2]));
		    	    }
		    	    fieldList.add(systemParamDTO);
			  }
		      return fieldList;
	   }
	   
	   public List<SystemParamDTO> getSystemParameter() {
			  return appPropertiesDao.getSystemParameter();
	   }

		public int saveSysParameter(SystemParamDTO systemParamDTO) {
			   return appPropertiesDao.saveSysParameter(systemParamDTO);
		}

		public int updateSystemParameter(SystemParamDTO systemParamDTO) {
			   return appPropertiesDao.updateSystemParameter(systemParamDTO);
		}

		public int deleteSystemParameter(SystemParamDTO systemParamDTO) {
			   return appPropertiesDao.deleteSystemParameter(systemParamDTO);
		}

		public SystemParamDTO getSystemParamByName(String paramName) {
			   return appPropertiesDao.getSystemParamByName(paramName);
		}

		public List<SystemParamDTO> getCustomerSearchParameter(){
		      List<SystemParamDTO> customerSearchParameterList = new ArrayList<>();
		      String sourceFileType = getSystemParamValueByName(Constants.CUSTOMER_SEARCH_PARAMETER);
		      for (String transactionFields : sourceFileType.split(Constants.COMMA)) {
		    	    String fields[] = transactionFields.split(Constants.TILDE);
		    	    SystemParamDTO systemParamDTO = new SystemParamDTO();
		    	    systemParamDTO.setParamDisplayName(fields[0]);
		    	    systemParamDTO.setParamValue(fields[1]);
		    	    customerSearchParameterList.add(systemParamDTO);
			  }
		      return customerSearchParameterList;
	   }
		
		public List<SystemParamDTO> getMerchantSearchParameter(){
		      List<SystemParamDTO> merchantSearchParameterList = new ArrayList<>();
		      String sourceFileType = getSystemParamValueByName(Constants.MERCHANT_SEARCH_PARAMETER);
		      for (String transactionFields : sourceFileType.split(Constants.COMMA)) {
		    	    String fields[] = transactionFields.split(Constants.TILDE);
		    	    SystemParamDTO systemParamDTO = new SystemParamDTO();
		    	    systemParamDTO.setParamDisplayName(fields[0]);
		    	    systemParamDTO.setParamValue(fields[1]);
		    	    merchantSearchParameterList.add(systemParamDTO);
			  }
		      return merchantSearchParameterList;
	   }
}
