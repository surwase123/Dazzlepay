package com.cratas.mpls.web.rest.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RestController;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.constant.FilePathConstants;
import com.cratas.mpls.common.constant.PropertyKeyConstants;
import com.cratas.mpls.service.IJWTTokenService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.AppDTO;
import com.cratas.mpls.web.dto.WebServiceResponseDTO;

/**
 * 
 * @author sunil
 *
 */
@RestController
@PropertySource(FilePathConstants.CLASSPATH + FilePathConstants.APP_PROPERTIES)
public class RestWebServiceController {
	
	   @Autowired
	   private Environment env;
	   
	   @Autowired
	   private IJWTTokenService jWTTokenService;
	   
	   @Autowired
	   private IUtilityService utilityService;
	
	   protected WebServiceResponseDTO setWebServieResponse(WebServiceResponseDTO webServiceResponse, String errMsg, Object data, String status) {
		         webServiceResponse.setStatus(status);
		         webServiceResponse.setErrMsg(errMsg);
		         webServiceResponse.setData(data);
		         return webServiceResponse;
	   }
	   
	   protected AppDTO getMPLSAppInfo() {
		         AppDTO appDTO = new AppDTO();
			     appDTO.setAppName(env.getProperty(PropertyKeyConstants.AppProperties.APP_NAME));
			     appDTO.setAppVersion(env.getProperty(PropertyKeyConstants.AppProperties.APP_VERSION));
			     appDTO.setBackgroundImage(env.getProperty(PropertyKeyConstants.AppProperties.APP_BG_URL));
			     appDTO.setDarkLogo(env.getProperty(PropertyKeyConstants.AppProperties.APP_LOGO_DARK_URL));
			     appDTO.setWhiteLogo(env.getProperty(PropertyKeyConstants.AppProperties.APP_LOGO_WHITE_URL));
			     appDTO.setBaseUrl(utilityService.getBaseUrl());
			     return appDTO;
	   }
	   
	   protected Map<String, Object> getApiResponse(HashMap<String, Object> apiKeyValueMap, String key, Object value){
		         if(apiKeyValueMap == null){
		        	 apiKeyValueMap = new HashMap<>();
		         }
		         apiKeyValueMap.put(key, value);
		         return apiKeyValueMap;
	   }
	   
	   protected boolean validateToken(String token, String deviceId) {
		         return (StringUtils.isNotEmpty(deviceId) && StringUtils.isNotEmpty(token) && jWTTokenService.validateToken(token, deviceId)); 
	   }
	   
	   protected String getFromDate(String date){
	       if(StringUtils.isNotEmpty(date)){
	    	   date = date + Constants.ZERO_TIMESTAMP;
	       }
		   return date;
	   }

	   protected String getToDate(String date){
	       if(StringUtils.isNotEmpty(date)){
	    	   date = date + Constants.TWENTY_THREE_TIMESTAMP;
	       }
		   return date;
	   }

}
