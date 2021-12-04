package com.cratas.mpls.service.impl;

import java.security.SecureRandom;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cratas.mpls.common.constant.FilePathConstants;
import com.cratas.mpls.common.constant.PropertyKeyConstants;
import com.cratas.mpls.service.IBCryptService;
import com.cratas.mpls.service.IUtilityService;

/**
 * This class used to generate encode, decode and matches the password. 
 * @author mukesh
 *
 */
@Service
@PropertySource(FilePathConstants.CLASSPATH + FilePathConstants.ENCRYPTION_PROPERTIES)
public class BCryptServiceImpl implements IBCryptService{
	
	   private final static Logger LOGGER = LoggerFactory.getLogger(BCryptServiceImpl.class);
	
	   private BCryptPasswordEncoder passwordEncoder;
	   
	   private SecureRandom secureRandom;
	   
	   @PostConstruct
	   private void initializeService(){
		       getPasswordEncoderInstance();
		       getSecureRandom();
		       
	   }
	   
	   @Autowired
	   private Environment env;
	   
	   @Autowired
	   private IUtilityService utilityService;

	   public String getEncryptedString(String password){
		      String hashPassowrd = passwordEncoder.encode(password);
		      return hashPassowrd;
	   }
	   
	   public boolean isMatch(String encodedPassword, CharSequence rawPassword){
		     try{
		    	 if(null != rawPassword && StringUtils.isNotEmpty(encodedPassword)){
		               return passwordEncoder.matches(rawPassword, encodedPassword);
		    	 }
		     }catch(Exception e){
		    	 LOGGER.error("Error in Match User login password --- "+e.getCause().getMessage());
		     }
		     return false;
	   }
	   
	   private SecureRandom getSecureRandomInstance(){
			   SecureRandom random = getSecureRandom();
			   byte bytes[] = new byte[20];
			   random.nextBytes(bytes);
			   return random;
	   }
	   
	   private BCryptPasswordEncoder getPasswordEncoderInstance(){
		       if(null == passwordEncoder){
		    	    String keyStrength = env.getProperty(PropertyKeyConstants.EncryptionProperties.PASSWORD_STRENGTH);
		    	    passwordEncoder = new BCryptPasswordEncoder(utilityService.convertStringToInt(keyStrength), getSecureRandomInstance());
		       }
		       return passwordEncoder;
       }
	   
	   private SecureRandom getSecureRandom(){
		       if(null == secureRandom){
		    	   secureRandom = new SecureRandom();
		       }
		       return secureRandom;
       }
	   
	   public boolean checkEncryptionKey(String encodedPassword, CharSequence rawPassword){
		      try{
		    	  passwordEncoder.matches(rawPassword, encodedPassword);
		      }catch(Exception e){
		    	 LOGGER.error("Error in check Encryption Key --- "+e.getMessage());
		    	 return false;
		      }
		      return true;
	   }
}
