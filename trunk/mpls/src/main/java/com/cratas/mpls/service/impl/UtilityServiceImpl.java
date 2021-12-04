package com.cratas.mpls.service.impl;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.constant.EmailConstants;
import com.cratas.mpls.common.constant.PropertyKeyConstants;
import com.cratas.mpls.service.IAppPropertiesService;
import com.cratas.mpls.service.ICustomerService;
import com.cratas.mpls.service.ICustomerTransactionService;
import com.cratas.mpls.service.IMenuService;
import com.cratas.mpls.service.IMerchantService;
import com.cratas.mpls.service.ISystemService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.GroupDTO;
import com.cratas.mpls.web.dto.MenuDTO;
import com.cratas.mpls.web.dto.SubMenuDTO;
import com.cratas.mpls.web.dto.SystemDTO;
import com.cratas.mpls.web.dto.UserDTO;
import com.fasterxml.uuid.Generators;

/**
 * 
 * @author mukesh
 *
 */
@Service
public class UtilityServiceImpl implements IUtilityService {
	
		private final static Logger LOGGER = LoggerFactory.getLogger(UtilityServiceImpl.class);
		
		@Autowired
		private ResourceLoader resourceLoader;
		
		@Autowired
		private String baseUrl;
		
		@Autowired
		private IMenuService menuService;
		
		@Autowired
	    private Environment env;
		
		@Autowired
		private ISystemService systemService;
		
		@Autowired
		private IAppPropertiesService appPropertiesService;
		
		@Autowired
		private IMerchantService merchantService;
		
		@Autowired
		private ICustomerService customerService;
		
		@Autowired
	 	private ICustomerTransactionService customerTransactionService;
		
		public String getViewResolverName(HttpSession session, String name){
			   String themeName = (String) session.getAttribute(Constants.THEME_NAME);
			   if(StringUtils.isEmpty(themeName)){
				     return Constants.GRADIENT + Constants.SLASH + name;
			   }
			   return themeName + Constants.SLASH + name;
			   
		}
		
		public String classpathResourceLoader(String fileName){
			   try{
					StringBuffer data = new StringBuffer();
					Resource resource = resourceLoader.getResource(Constants.CLASSPATH + Constants.COLON + fileName);
					InputStream is = resource.getInputStream();
					
					BufferedReader reader = new BufferedReader(new InputStreamReader(is));
					while (true) {
			            String line = reader.readLine();
			            if (line == null)
			                break;
			            data.append(line);
			        }
					return data.toString();
				} catch(Exception e){
					LOGGER.error("Error in loading Classpath Resource");
				}
				return null;
		}
		
		public int convertStringToInt(String str) {
				if(StringUtils.isNotEmpty(str)) {
					String strTemp = str;
					strTemp = strTemp.replaceAll(Constants.CHAR_SET_ALL_NON_INT, Constants.BLANK);
					if(strTemp.length() > Constants.ZERO) {
						int intValue = Constants.ZERO;
						try{
							intValue = Integer.parseInt(strTemp);
						}catch(NumberFormatException ex){
							LOGGER.error("NumberFormatException for String "+strTemp);
						}
						return intValue;
					}else {
						if (str!=null && !str.equals(null)) {
							try {
								throw new Exception();
							} catch(Exception e) {
								LOGGER.error("Error in parsing string to int : "+str);
							}
						}
					}
				}
				return Constants.ZERO;
		}
		
		public String getBaseUrl(){
			   return baseUrl;
		}
		
		public String generateSalt(UserDTO userDTO, HttpServletRequest request){
			   String salt = request.getSession().getId();
			   salt = toHexaDecimal(salt);
			   if(StringUtils.isNotEmpty(salt) && salt.length() > 32){
				     salt = salt.substring(0,32);
			   }
			   return salt;
		}
		
		public String generateIV(UserDTO userDTO, HttpServletRequest request){
			   String iv = request.getSession().getId();
			   iv = toHexaDecimal(iv);
			   if(StringUtils.isNotEmpty(iv) && iv.length() > 32){
				     iv = iv.substring(0,32);
			   }
			   return iv;
		}
		
		public String toHexaDecimal(String text){
			   try{
				   byte[] myBytes = text.getBytes(Constants.UTF_8);
				   return DatatypeConverter.printHexBinary(myBytes);
			   }catch(Exception e){
				   LOGGER.error("Error in Convert Hex Decimal : "+text);
			   }
			   return null;
		}

	    public String firstCapitalLetter(String str){
		    	str = str.toLowerCase();
	            if(StringUtils.isNotEmpty(str) && str.length() > 1){
	                return str.substring(0, 1).toUpperCase() + str.substring(1).replace(Constants.SPACE, Constants.BLANK);
	            }
	            return str;
        }
		
		public String createPasswordPatternMessage(GroupDTO groupDTO){
			   String passPatternMessage = "Password between "+ groupDTO.getMinPassLength() +" to "+ groupDTO.getMaxPassLength() +" which contain at least <br>";
			   if(groupDTO.getIsNumberPassword().equals(Constants.Y)){
				     passPatternMessage += "<b>one numeric digit</b> <br>";
			   }
			   if(groupDTO.getIsAlphaPassword().equals(Constants.Y)){
				     passPatternMessage += "<b>one lowercase and uppercase letter</b> <br>";
			   }
			   if(groupDTO.getIsSpecialSymbolPass().equals(Constants.Y)){
				     passPatternMessage += "<b>one special character</b>";
			   }
			   return passPatternMessage;
	   }
		
	   public String getValidValue(String value){
			  if(!StringUtils.isEmpty(value)){
				   value = value.trim().replace(Constants.QUOTE, Constants.BLANK);
				   return value;
			  }
			  return Constants.BLANK;
	   }
	   
	   public String getFileExtension(String fileName) {
		      int lastIndexOf = fileName.lastIndexOf(Constants.DOT);
		      if (lastIndexOf == -1) {
		           return Constants.BLANK;
		      }
		      return fileName.substring(lastIndexOf);
	   }
		 
	   public String getRandomNumber(){
			  UUID uuid = Generators.timeBasedGenerator().generate();
			  String randomStringArr[] = uuid.toString().split(Constants.HYFUN);
			  if(randomStringArr.length > 0){
				   return randomStringArr[0].toUpperCase();
			  }
			  return Constants.BLANK;
	   }
	   
	   public Date getCurrentDate(){
		      return new java.sql.Date(Calendar.getInstance().getTime().getTime());
	   }
	   
	   public String getCurDateToStr(String pattern){
		      try{
		         String date = new SimpleDateFormat(pattern).format(new java.util.Date());
		         return date;
		      }catch(Exception e){
		    	  e.printStackTrace();
		      }
		      return Constants.BLANK;
	   }

	   @SuppressWarnings("resource")
	   public String getRemoteAddr(HttpServletRequest request) {
			   DatagramSocket socket;
			   try {
					socket = new DatagramSocket();
					socket.connect(InetAddress.getByName(Constants.SUBNET_MASK), 10002);
					return socket.getLocalAddress().getHostAddress();
			   } catch (SocketException | UnknownHostException e) {
				    e.printStackTrace();
			   }
			   return request.getRemoteAddr();
	   }
	  
	   public String convertBigIntergerToString(String value){
		     if(StringUtils.isNotEmpty(value)){
			     try{
			    	 BigInteger intValue = new BigInteger(value);
			    	 return String.valueOf(intValue);
			     }catch(Exception e){
			    	 return value;
			     }
		     }
		     return value;
	  }
	   
	  public Resource getFileResource(String downloadFilePath) {
	         try {
	            Path filePath = Paths.get(downloadFilePath).toAbsolutePath().normalize();
	            Resource resource = new UrlResource(filePath.toUri());
	            if(resource.exists()) {
	                return resource;
	            }else {
	            	LOGGER.info("File Not Found -->"+downloadFilePath);   
	            }
	         }catch (Exception ex) {
	           LOGGER.error("Error in get file Resource -->"+downloadFilePath);   
	         }
	         return null;
	  }
	  
	  public String maskFieldValue(String original){
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
	  
	  public boolean isUserMenuUrl(String uri){
		     List<MenuDTO> menuList = menuService.getMenuList();
		     List<SubMenuDTO> subMenuList = new ArrayList<>();
		     for (MenuDTO menuDTO : menuList) {
		    	 if(StringUtils.isNotEmpty(menuDTO.getAction())){
		    		 if(uri.contains(menuDTO.getAction())){
 		    		 return true;
 		    	 }
 		     }else{
 		    	 subMenuList.addAll(menuDTO.getSubMenuList());
 		     }
			 }
		     return isSubmenuUrl(uri, subMenuList);
	  }
	  
	  private boolean isSubmenuUrl(String uri, List<SubMenuDTO> subMenuList){
		      for (SubMenuDTO subMenuDTO : subMenuList) {
		    	  if(uri.contains(subMenuDTO.getAction())){
		    		 return true;
		    	  }
			  }
		      return false;
	  }
	  
	  public String getDisplayDate(String date){
		     try{
			     DateFormat inputFormatter = new SimpleDateFormat(Constants.SQL_DATE_FORMAT);
			     Date da = (Date)inputFormatter.parse(date);
			     DateFormat outputFormatter = new SimpleDateFormat(Constants.DISPLAY_DATE_FORMAT);
			     return outputFormatter.format(da);
		     }catch (Exception e) {
				  LOGGER.error("Error in convert to display Date");
			 }
		     return date;
      }
	  
	  public String generateNumber(int numberLength, boolean letter, boolean number){
		     return RandomStringUtils.random(numberLength, letter, number).toUpperCase(); 
	  }
	  
	  public String generateCustomerId(boolean letter, boolean number){
		     String customerIdPrefix = appPropertiesService.getSystemParamValueByName(Constants.CUSTOMER_ID_PREFIX);
		     int length = convertStringToInt(appPropertiesService.getSystemParamValueByName(Constants.CUSTOMER_ID_LENGTH));
		     length = getIdLength(length, customerIdPrefix);
		     String cId = RandomStringUtils.random(length, letter, number).toUpperCase();
		     if(customerService.getCustomerByCustomerId(cId) == null){
		    	  return cId;
		     }
		     return Constants.BLANK;
	  }
	  
	  public String generateMerchantId(boolean letter, boolean number){
		     String merchantIdPrefix = appPropertiesService.getSystemParamValueByName(Constants.MERCHANT_ID_PREFIX);
		     int length = convertStringToInt(appPropertiesService.getSystemParamValueByName(Constants.MERCHANT_ID_LENGTH));
		     length = getIdLength(length, merchantIdPrefix);
		     String mId =  RandomStringUtils.random(length, letter, number).toUpperCase();
		     if(merchantService.getMerchantByMId(mId) == null){
		    	  return mId;
		     }
		     return Constants.BLANK;
		     
	  }
	  
	  public String generateCustomerTxnId(boolean letter, boolean number){
		     String customerTxnPrefix = appPropertiesService.getSystemParamValueByName(Constants.TRANSACTION_PREFIX);
		     int length = convertStringToInt(appPropertiesService.getSystemParamValueByName(Constants.TRANSACTION_PREFIX_LENGTH));
		     length = getIdLength(length, customerTxnPrefix);
		     String customerTransactionId =  customerTxnPrefix+RandomStringUtils.random(length, letter, number).toUpperCase();
		     if(customerTransactionService.getCustomerTransactionId(customerTransactionId) == null){
		    	  return customerTransactionId;
		     }
		     return Constants.BLANK;
		     
	  }
	  
	  private int getIdLength(int length, String prefix){
		      int idLength = length;
		      if(StringUtils.isNotEmpty(prefix) && length > 0){
		    	  idLength = length - prefix.length();
		    	  if(idLength < 0){
		    		  return length;
		    	  }
		      }
		      return idLength;
	  }
	  
	  public String getSenderMail() {
	         String senderEmailId = env.getProperty(PropertyKeyConstants.ServerProperties.SENDER_EMAIL);
	         if (StringUtils.isEmpty(senderEmailId)) {
	            return EmailConstants.SUPPORT_EMAIL;
	         }
	         return senderEmailId;
      }
	  
	  public String getSystemName(){
	  	     List<SystemDTO> systemList = systemService.getActiveSystem();
	  	     if(!systemList.isEmpty()){
	  	    	return systemList.get(0).getSystemId();
	  	     }
	  	     return Constants.BLANK;
      }
	  
	  public double convertStringToDouble(String value){
		     if(StringUtils.isNotEmpty(value)){
		    	   return Double.parseDouble(value);
		     }
		     return 0;
	  }
	  
	  public String decimalFormat(double value){
		     NumberFormat formatter = NumberFormat.getNumberInstance();
	         formatter.setMinimumFractionDigits(2);
	         formatter.setMaximumFractionDigits(2);
	         return formatter.format(value);
	  }

}
