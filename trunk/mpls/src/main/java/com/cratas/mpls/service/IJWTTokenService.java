package com.cratas.mpls.service;

/**
 * 
 * @author mukesh
 *
 */
public interface IJWTTokenService {
	
	   String generateToken(String deviceId);
		
	   boolean validateToken(String token, String deviceId);
		
	   boolean isValidRequest(String apiKey);

}
