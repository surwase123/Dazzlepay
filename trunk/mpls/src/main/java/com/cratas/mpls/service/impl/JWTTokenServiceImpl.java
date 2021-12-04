package com.cratas.mpls.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.function.Function;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.cratas.mpls.common.constant.FilePathConstants;
import com.cratas.mpls.common.constant.PropertyKeyConstants;
import com.cratas.mpls.common.constant.RestAPIConstants;
import com.cratas.mpls.dao.IApiTokenDao;
import com.cratas.mpls.service.IJWTTokenService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.ApiTokenDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 
 * @author mukesh
 *
 */
@Service
@PropertySource(FilePathConstants.CLASSPATH + FilePathConstants.APP_PROPERTIES)
public class JWTTokenServiceImpl implements IJWTTokenService {
	
	   private final static Logger LOGGER = LoggerFactory.getLogger(JWTTokenServiceImpl.class);

	   @Autowired
	   private Environment env;
	   
	   @Autowired
	   private IApiTokenDao apiTokenDao;
	   
	   @Autowired
	   private IUtilityService utilityService;
	   
	   public String generateToken(String deviceId) {
			  ApiTokenDTO apiTokenDTO = getAPITokenObj(deviceId);
			  if(StringUtils.isNotEmpty(apiTokenDTO.getToken()) && apiTokenDao.saveToken(apiTokenDTO) == 1) {
				  return apiTokenDTO.getToken();
			  }
			  return null;
	   }	   
		
	   public boolean validateToken(String token, String deviceId) {
			  try {
					String appSecretKey = getClaimFromToken(token, Claims::getSubject);
					if (StringUtils.isNotEmpty(appSecretKey) && appSecretKey.equals(deviceId) && !isTokenExpired(token)) {
						return true;
					}					
			  }catch (JwtException | ClassCastException e) {
					LOGGER.error("Error in JWT Validate Token");
			  }
			  return false;
		}
		
		public boolean isValidRequest(String key) {
			   if (StringUtils.isNotEmpty(key)) {
					if (key.equals(env.getProperty(PropertyKeyConstants.AppProperties.APP_SECRET_KEY))) return true;
			   }
			   return false;
		}
		
		private ApiTokenDTO getAPITokenObj(String deviceId) {
			    String apiKey = utilityService.generateNumber(20, true, true);
			    ApiTokenDTO apiTokenDTO = new ApiTokenDTO();
				apiTokenDTO.setDeviceId(deviceId);
				apiTokenDTO.setApiKey(apiKey);
				apiTokenDTO.setToken(generateToken(deviceId, apiKey));
				return apiTokenDTO;
		}
		
		private String generateToken(String deviceId, String apiKey) {
			    Claims claims = Jwts.claims().setSubject(deviceId);
			    claims.put(RestAPIConstants.API_KEY, apiKey);
			    return Jwts.builder()
			    		   .setClaims(claims)
			    		   .setIssuedAt(new Date())
			    		   .setExpiration(getExpiryDate())
			    		   .signWith(SignatureAlgorithm.HS512, env.getProperty(PropertyKeyConstants.AppProperties.TOKEN_SECRET_KEY))
			    		   .compact();
	    }
		
		private Date getExpiryDate() {
		        Calendar cal = Calendar.getInstance();
		        cal.add(Calendar.DATE, Integer.parseInt(env.getProperty(PropertyKeyConstants.AppProperties.TOKEN_EXPIRY_DAYS))); 
		        return cal.getTime();
		}
		
		private boolean isTokenExpired(String token) {
	        	final Date expiration = getExpirationDateFromToken(token);
	        	return expiration.before(new Date());
	    }
		
		private Date getExpirationDateFromToken(String token) {
	        	return getClaimFromToken(token, Claims::getExpiration);
	    }
	 
		private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
			   	final Claims claims = getAllClaimsFromToken(token);
			   	return claimsResolver.apply(claims);
		}
		
		private Claims getAllClaimsFromToken(String token) {
			    return Jwts.parser().setSigningKey(env.getProperty(PropertyKeyConstants.AppProperties.TOKEN_SECRET_KEY))
					.parseClaimsJws(token).getBody();
		}

}
