package com.cratas.mpls.service.impl;



import java.security.spec.KeySpec;

import javax.annotation.PostConstruct;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.constant.FilePathConstants;
import com.cratas.mpls.common.constant.PropertyKeyConstants;
import com.cratas.mpls.service.IAESEncryptionService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.AESDTO;

/**
 * 
 * @author mukesh
 *
 */
@Service
@PropertySource(FilePathConstants.CLASSPATH + FilePathConstants.ENCRYPTION_PROPERTIES)
public class AESEncryptionServiceImpl implements IAESEncryptionService {
	
	    private final static Logger LOGGER = LoggerFactory.getLogger(AESEncryptionServiceImpl.class);
	    
	    private int keySize;
	    private int iterationCount;
	    private Cipher cipher;
	    private AESDTO aesDTO;
	    
	    @Autowired
		private Environment env;
	    
	    @Autowired
	    private IUtilityService utilityService;
	    
	    @PostConstruct
	    public void init(){
		       keySize = utilityService.convertStringToInt(env.getProperty(PropertyKeyConstants.EncryptionProperties.AES_KEY_SIZE));
		       iterationCount = utilityService.convertStringToInt(env.getProperty(PropertyKeyConstants.EncryptionProperties.AES_ITERATION_COUNT));
		       aesDTO = new AESDTO(keySize, iterationCount, null);
		       try {
		            cipher = Cipher.getInstance(Constants.AES_ALGORITHIM);
		       } catch (Exception e) {
		    	    LOGGER.error("Error in AES algorithim instance");
		            throw fail(e);
		       }
	    }

	    public String decrypt(String salt, String iv, String passphrase, String ciphertext) {
		        try {
		            SecretKey key = generateKey(salt, passphrase);
		            byte[]    decrypted = doFinal(Cipher.DECRYPT_MODE, key, iv, base64Decode(ciphertext));
		            return new String(decrypted, "UTF-8");
		        } catch (Exception e) {
		        	LOGGER.error("Error in AES decrypt String -- "+e.getMessage());
		        }
		        return null;
	    }

	    private byte[] doFinal(int encryptMode, SecretKey key, String iv, byte[] bytes) {
		        try {
		            cipher.init(encryptMode, key, new IvParameterSpec(hex(iv)));
		            return cipher.doFinal(bytes);
		        } catch (Exception e) {
		        	LOGGER.error("Error in setup AES encryption mode -- "+e.getMessage());
		        }
		        return null;
	    }

	    public String encrypt(String salt, String iv, String passphrase, String plaintext) {
		        try {
		            SecretKey key = generateKey(salt, passphrase);
		            byte[] encrypted = doFinal(Cipher.ENCRYPT_MODE, key, iv, plaintext.getBytes(Constants.UTF_8));
		            return base64Encode(encrypted);
		        } catch (Exception e) {
		        	LOGGER.error("Error in AES Encryption String -- "+e.getMessage());
		        }
		        return null;
		        
	    }

	    private IllegalStateException fail(Exception e) {
	        return new IllegalStateException(e);
	    }

	    private SecretKey generateKey(String salt, String passphrase) {
		        try {
		            SecretKeyFactory factory = SecretKeyFactory.getInstance(Constants.PBKDF2_WITHHMAC_SHA1);
		            KeySpec spec = new PBEKeySpec(passphrase.toCharArray(), hex(salt), iterationCount, keySize);
		            SecretKey key = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), Constants.AES);
		            return key;
		        } catch (Exception e) {
		        	LOGGER.error("Error in AES generate Key -- "+e.getMessage());
		        }
		        return null;
	    }

	    private byte[] hex(String str) {
		        try {
		            return Hex.decodeHex(str.toCharArray());
		        } catch (DecoderException e) {
		            throw new IllegalStateException(e);
		        }
	    }
	    
	    public String base64Encode(byte[] bytes) {
	        return Base64.encodeBase64String(bytes);
	    }

	    public byte[] base64Decode(String str) {
	        return Base64.decodeBase64(str);
	    }

		public AESDTO getAesDTO() {
			return aesDTO;
		}
	    
}
