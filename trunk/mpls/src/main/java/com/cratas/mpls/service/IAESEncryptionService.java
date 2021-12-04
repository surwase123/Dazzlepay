package com.cratas.mpls.service;

import com.cratas.mpls.web.dto.AESDTO;

/**
 * 
 * @author mukesh
 *
 */
public interface IAESEncryptionService {

	    String decrypt(String salt, String iv, String passphrase, String ciphertext);
	    
	    String encrypt(String salt, String iv, String passphrase, String plaintext);
	    
	    AESDTO getAesDTO();
	    
	    String base64Encode(byte[] bytes);
	    
	    byte[] base64Decode(String str);
}
