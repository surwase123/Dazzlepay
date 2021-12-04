package com.cratas.mpls.service;

/**
 * 
 * @author mukesh
 *
 */
public interface IBCryptService {

	   String getEncryptedString(String password);
		
	   boolean isMatch(String dbPassword, CharSequence userPassword);
	   
	   boolean checkEncryptionKey(String encodedPassword, CharSequence rawPassword);
		
}
