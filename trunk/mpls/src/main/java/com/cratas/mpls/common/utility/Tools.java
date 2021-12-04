/**
 * 
 */
package com.cratas.mpls.common.utility;

import javax.annotation.PostConstruct;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.service.IUserRoleService;
import com.cratas.mpls.service.IUserService;
import com.cratas.mpls.web.dto.UserDTO;
import com.cratas.mpls.web.dto.UserRoleDTO;

/**
 * @author bhupendra
 *
 */
@Component
public class Tools {
	
	   private final static Logger LOGGER = LoggerFactory.getLogger(Tools.class);
	
	   private static IUserService userService;
	   
	   private static IUserRoleService userRoleService;
	   
	   @Autowired
	   private IUserService service;
	   
	   @Autowired
	   private IUserRoleService roleService;
	   
	   @PostConstruct
	   public void init() {
		      userService = this.service;
		      userRoleService = this.roleService;
	    }
	   
	   public static String maskString(String original){
		      String maskValue = Constants.BLANK;
		      if(original.length() < 4){
		            maskValue = original.replaceAll(Constants.MASK_STRING_REGEX_MIN_LENGTH, "$1" + Constants.MASK_STRING_MIN_LENGTH + "$3");
		      }else if(original.length() >= 4 && original.length() <= 7){
		            maskValue = original.replaceAll(Constants.MASK_STRING_REGEX_MAX_LENGTH, "$1" + Constants.MASK_STRING + "$3");
		      }else{
		    	  	char[] stringToCharArray = original.toCharArray();
		            int first = original.length() / 2;
		            int second = first + 2;
		            StringBuilder sb =new StringBuilder(Constants.BLANK);
		            for(int count = 0; count < first - 3; count++){
		                sb.append(stringToCharArray[count]);
		            }
		            String firstMask = sb.toString();
		            StringBuilder sb1 =new StringBuilder(Constants.BLANK);
		            for(int count = first + 2; count < original.length(); count++){
		                sb1.append(stringToCharArray[second++]);
		            }
		            String secondMask = sb1.toString();
		            maskValue = firstMask + Constants.MASK_STRING_MAX_LENGTH + secondMask;
		       }
		       return maskValue;
	   }
	   
	   public static String toHexDecimal(String text){
			  try{
				   byte[] bytes = text.getBytes(Constants.UTF_8);
				   return DatatypeConverter.printHexBinary(bytes);
			  }catch(Exception e){
				   LOGGER.error("Error in Convert Hex Decimal : "+text);
			  }
			  return null;
	   }
	   
	   public static UserRoleDTO getPageRoleObj(String loginId, String uri){
			  try{
				  if(StringUtils.isNotEmpty(loginId)){
					   UserDTO userDetails = userService.getUserByLoginId(loginId);
					   userDetails.setUserRoleList(userRoleService.getUserRoleById(userDetails.getGroupId()));
					   UserRoleDTO pageRole = userService.getPageRole(userDetails.getUserRoleList(), uri);   
					   return pageRole;
				  }
			  }catch(Exception e){
				   LOGGER.error("Error in get page role object"+e);
			  }
			  return null;
	   }
}
