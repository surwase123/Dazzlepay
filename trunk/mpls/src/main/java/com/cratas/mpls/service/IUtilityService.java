package com.cratas.mpls.service;

import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.io.Resource;

import com.cratas.mpls.web.dto.GroupDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author mukesh
 *
 */
public interface IUtilityService {
	
	   String getRemoteAddr(HttpServletRequest request);
	   
	   String getViewResolverName(HttpSession session, String name);
	   
	   String classpathResourceLoader(String fileName);
	   
	   int convertStringToInt(String str);
	   
	   String getBaseUrl();
	   
	   String generateSalt(UserDTO userDTO, HttpServletRequest request);
	   
	   String generateIV(UserDTO userDTO, HttpServletRequest request);
	   	   
	   String createPasswordPatternMessage(GroupDTO groupDTO);
	   
	   String firstCapitalLetter(String str);
	   	   
	   String getValidValue(String value);
	   
	   String getFileExtension(String fileName);
	   
	   String getRandomNumber();
	   
	   Date getCurrentDate();
	   
	   String getCurDateToStr(String pattern);
	   
	   String convertBigIntergerToString(String value);
	   
	   Resource getFileResource(String downloadFilePath);
	   
	   String maskFieldValue(String original);
	   
	   boolean isUserMenuUrl(String uri);
	   
	   String getDisplayDate(String date);
	   
	   String generateNumber(int numberLength, boolean letter, boolean number);
	   
	   String getSenderMail();
	   
	   String getSystemName();
	   
	   String generateCustomerId(boolean letter, boolean number);
	   
	   String generateMerchantId(boolean letter, boolean number);
	   
	   String generateCustomerTxnId(boolean letter, boolean number);
	   
	   String decimalFormat(double value);
	   
	   String toHexaDecimal(String text);
}
