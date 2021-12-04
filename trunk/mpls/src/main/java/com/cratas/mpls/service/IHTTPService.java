package com.cratas.mpls.service;

import java.util.Map;

import org.springframework.http.HttpHeaders;

/**
 * 
 * @author mukesh
 *
 */
public interface IHTTPService {
	
	   String HTTPJsonPostRequest(String url, String json);
	   
	   String HTTPGetRequest(String url);
	   
	   String HTTPGetRequest(String url, Map<String,String> headersMap);
	   
	   String HTTPPostRequest(String url, Map<String, String> paramMap);
	   
	   String HTTPFireBasePostRequest(String url, String json, HttpHeaders headers);
	   
}
