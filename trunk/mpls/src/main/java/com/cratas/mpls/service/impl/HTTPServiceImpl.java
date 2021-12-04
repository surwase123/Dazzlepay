package com.cratas.mpls.service.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.constant.PropertyKeyConstants;
import com.cratas.mpls.service.IHTTPService;
/**
 * 
 * @author mukesh
 *
 */
@Service
public class HTTPServiceImpl implements IHTTPService {
	
	   private final static Logger LOGGER = LoggerFactory.getLogger(HTTPServiceImpl.class);
	   
	   private RestTemplate restTemplate;
		
	   public String HTTPGetRequest(String url) {
			  RestTemplate restTemplate = getRestTemplate();
			  URI uri = null;
			  try {
				  uri = new URI(url);
				  return restTemplate.getForObject(uri, String.class);
				  
			  }catch (URISyntaxException e) {
				   LOGGER.error("Error in URI creation in HTTP GET .\n URL : "+url, e);
			  }
			  return restTemplate.getForObject(url, String.class);
	   }
		
	   public String HTTPGetRequest(String url, Map<String,String> headersMap) {
			  RestTemplate restTemplate = getRestTemplate();
			
			  try {
				  MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		          headers.add(Constants.USER_AGENT.toLowerCase(), Constants.USER_AGENT_DETAILS);
				  for (Map.Entry<String, String> entry : headersMap.entrySet()) {
					   headers.add(entry.getKey(), entry.getValue());
				  }
				
				  HttpEntity<String> entity = new HttpEntity<String>(Constants.PARAMETERS, headers);
				  ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
				  return result.getBody();
			  } catch (Exception e) {
					LOGGER.error("Error in URI creation in HTTP GET .\n URL : "+url, e);
			  }
			  return null;
		}
		
		public String HTTPPostRequest(String url, Map<String, String> paramMap) {
			   RestTemplate restTemplate = getRestTemplate();
			   MultiValueMap<String, String> map = getMultiValueMap(paramMap);
			   URI uri = null;
			   try {
					uri = new URI(url);
					return restTemplate.postForObject(uri, map, String.class);
			   }catch(URISyntaxException e) {
					LOGGER.error("Error in URI creation in HTTP POST .\n URL : "+url, e);
			   }
			   return restTemplate.postForObject(url, map, String.class);		
		}
		
		public String HTTPJsonPostRequest(String url, String json) {
			   RestTemplate restTemplate = getRestTemplate();
			   URI uri = null;
			   try {
					HttpHeaders headers = new HttpHeaders();
					headers.setContentType(MediaType.APPLICATION_JSON);
	
					HttpEntity<String> entity = new HttpEntity<String>(json,headers);
					uri = new URI(url);
					return restTemplate.postForObject(uri, entity, String.class);
			   }catch(URISyntaxException e) {
					LOGGER.error("Error in URI creation in HTTP POST .\n URL : "+url, e);
			   }
			   return null;		
		}
		
		public String HTTPFireBasePostRequest(String url, String json, HttpHeaders headers) {
			   RestTemplate restTemplate = getRestTemplate();
			   URI uri = null;
			   try {
					HttpEntity<String> entity = new HttpEntity<String>(json,headers);
					uri = new URI(url);
					return restTemplate.postForObject(uri, entity, String.class);
			   }catch (URISyntaxException e) {
					LOGGER.error("Error in URI creation in HTTP POST .\n URL : "+url, e);
			   }
			   return null;		
		}
		
		private MultiValueMap<String, String> getMultiValueMap(Map<String, String> paramMap) {
			    MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
			    for(Entry<String, String> entry : paramMap.entrySet()) {
					map.add(entry.getKey(), entry.getValue());
			    }
			    return map;
		}
		
		private RestTemplate getRestTemplate() {
			    try{
				   if(restTemplate == null) {
						restTemplate = new RestTemplate();
				   }
			    }catch(Exception e){
				   LOGGER.error("Error in create Rest Template Object");
			    }
			    return restTemplate;
		} 

}
