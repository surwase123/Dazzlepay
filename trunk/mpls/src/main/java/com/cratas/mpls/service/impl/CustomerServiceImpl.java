package com.cratas.mpls.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.cratas.event.service.IEventRegisterService;
import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.constant.EmailConstants;
import com.cratas.mpls.common.constant.PropertyKeyConstants;
import com.cratas.mpls.common.utility.GroupType;
import com.cratas.mpls.dao.ICustomerDao;
import com.cratas.mpls.service.IAESEncryptionService;
import com.cratas.mpls.service.IAppPropertiesService;
import com.cratas.mpls.service.IBCryptService;
import com.cratas.mpls.service.ICustomerService;
import com.cratas.mpls.service.IEmailSenderService;
import com.cratas.mpls.service.ILoyaltyCardService;
import com.cratas.mpls.service.IMerchantService;
import com.cratas.mpls.service.IUserService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.service.IVerificationService;
import com.cratas.mpls.web.dto.CustomerDTO;
import com.cratas.mpls.web.dto.MerchantDTO;
import com.cratas.mpls.web.dto.MerchantMappingDTO;
import com.cratas.mpls.web.dto.SearchUserDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author mukesh
 *
 */
@Service
public class CustomerServiceImpl implements ICustomerService {
	
	    private final static Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);
	    
	    @Autowired
	    private ICustomerDao customerDao;
	    
	    @Autowired
	    private IUserService userService;
	    
	    @Autowired
	    private IMerchantService merchantService;
	    
	    @Autowired
	    private IAppPropertiesService appPropertiesService;
	    
	    @Autowired
	    private IUtilityService utilityService;
	    
	    @Autowired
	    private IBCryptService bCryptService;
	    
	    @Autowired
	    private IAESEncryptionService aesService;
	    
	    @Autowired
	    private ILoyaltyCardService loyaltyCardService;
	    
	   
	    
	    @Autowired
	    private IEmailSenderService emailSenderService;
	    
	    @Autowired
	    private Environment env;
	    
	    @Autowired
		private IEventRegisterService eventRegisterService;
	    
	    public int saveCustomer(CustomerDTO customer, UserDTO loginUserDTO,String registrationType){
	    	   try{
				   boolean isMerchant = merchantService.isMerchant(customer.getEmailId(), customer.getMobileNumber());
				   if(!isMerchant){
					   CustomerDTO custObj = getCustomerByMN(customer.getMobileNumber());
					   if(custObj == null) {
						   int isUserInserted = insertCustomerUser(customer, loginUserDTO);
						  
						   if(isUserInserted == 1){
							    UserDTO userDetails = userService.getUserByLoginId(customer.getMobileNumber());
							    customer.setUserId(userDetails.getId());
							    int isRegistered = customerDao.saveCustomer(customer);
							    if(registrationType.equalsIgnoreCase("Merchant"))
							    {
							    	CustomerDTO customerForLoyaltyCard = getCustomerById(userDetails.getId());
								    loyaltyCardService.updateLoyaltyCardNumberByMid(customer.getmId(), customerForLoyaltyCard.getId(),  customer.getLoyaltyCardNumber(),  "CA", 0);
							    }
							    sendNotification(customer, userDetails, isRegistered, loginUserDTO);
							    return isRegistered;
						   }
						   return 1;
					   }
					   return 2;
				   }
				   return 4;
	    	   }catch(Exception e){
	    		   LOGGER.error("Error in Customer creation -- "+customer.getLoginId() + " --- "+e.getMessage());
	    		   return 0;
	    	   }
		}
		
		private void sendNotification(CustomerDTO customer, UserDTO userDetails, int isRegistered, UserDTO user) {
				if(customer.getmId() > 0) {
					 MerchantDTO merchantDTO = merchantService.getMerchantById(customer.getmId());
					 sendCustomerRegMail(customer, userDetails, isRegistered, merchantDTO);
					 sendCustomerRegSMS(customer, userDetails, isRegistered, merchantDTO);
				}else {
					if(user != null) {
						sendCustomerRegMail(customer, userDetails, isRegistered, user.getMerchant());
					    sendCustomerRegSMS(customer, userDetails, isRegistered, user.getMerchant());
					}else {
						sendCustomerRegMail(customer, userDetails, isRegistered);
					}
				}
			
		}

		public CustomerDTO getCustomerById(int userId) {
			   return customerDao.getCustomerById(userId);
		}
		
		public CustomerDTO getCustomerByCustomerId(String customerId) {
			   return customerDao.getCustomerByCustomerId(customerId);
		}
		
		public CustomerDTO getCustomerByCId(int cId){
			   return customerDao.getCustomerByCId(cId);
		}
		
		public CustomerDTO getCustomerByMN(String mobileNumber){
			   return customerDao.getCustomerByMN(mobileNumber);
		}		
		
		private int insertCustomerUser(CustomerDTO customer, UserDTO loginUserDTO){
			    UserDTO userDetails = userService.getUserByLoginId(customer.getLoginId());
			    if(userDetails == null) {
		    	    UserDTO user = new UserDTO();
		    	    user.setSystemId(utilityService.getSystemName());
		    	    user.setEmailId(customer.getEmailId());
		    	    user.setGroupId(GroupType.CUSTOMER.getGroupType());
		    	    user.setLoginId(customer.getMobileNumber());//Here we set mobile No because login id is same as mobile number by requirment 
		    	    user.setMobileNumber(customer.getMobileNumber());
		    	    user.setCreatedBy(customer.getCreatedBy());
		    	    user.setStatus(Constants.ONE);
		    	    user.setFirstName(customer.getFirstName());
		    	    user.setLastName(customer.getLastName());
		    	    user.setMiddleName(customer.getMiddleName());
		    	    user.setUserType(2);
		    	    return userService.saveUser(user, loginUserDTO, true);
			    }
			    return 5;
        }
		
		private void sendCustomerRegMail(final CustomerDTO customer, final UserDTO user, int isRegistered, final MerchantDTO merchant) {
		        if (null != user && isRegistered > 0) {
		        	String customerIDPrefix = appPropertiesService.getSystemParamValueByName(Constants.CUSTOMER_ID_PREFIX);
		        	String encryptLoginId = bCryptService.getEncryptedString(user.getLoginId());
		            String setPasswordUrl = utilityService.getBaseUrl() + EmailConstants.SLASH  + EmailConstants.CHANGE_PARRWORD_URL + user.getId() + EmailConstants.SLASH
		                    + aesService.base64Encode(encryptLoginId.getBytes());
		            final String message = utilityService.classpathResourceLoader(EmailConstants.MERCHANT_CUSTOMER_REG_TEMPLATE)
		                    .replace(EmailConstants.NAME, customer.getFirstName() + Constants.SPACE + customer.getLastName())
		                    .replace(EmailConstants.URL, setPasswordUrl)
		                    .replace(EmailConstants.CUSTOMER_ID, customerIDPrefix + customer.getCustomerId()).replace(EmailConstants.USER_LOGIN_ID, user.getLoginId())
		                    .replace(EmailConstants.EMAIL, env.getProperty(PropertyKeyConstants.ServerProperties.SENDER_EMAIL))
		                    .replace(EmailConstants.MERCHANT, merchant.getMerchantName())
		                    .replace(EmailConstants.BASE_URL, utilityService.getBaseUrl())
		                    .replace(EmailConstants.APP_NAME, EmailConstants.MPLS_APP_NAME).replace(EmailConstants.APP_ACCOUNT_NAME, EmailConstants.MPLS).replace(EmailConstants.ID, String.valueOf(user.getId()));
		
		            new Thread(new Runnable() {
		                @Override
		                public void run() {
		                    emailSenderService.sendEmail(utilityService.getSenderMail(), customer.getEmailId().split(Constants.COMMA), null,
		                            EmailConstants.WELCOME_MAIL, message, null, null, true);
		                }
		            }).start();
		        }
        }
		
		private void sendCustomerRegMail(CustomerDTO customer, UserDTO user, int isRegistered) {
				if (null != user && isRegistered > 0) {
		        	String customerIDPrefix = appPropertiesService.getSystemParamValueByName(Constants.CUSTOMER_ID_PREFIX);
		        	String encryptLoginId = bCryptService.getEncryptedString(user.getLoginId());
		            String setPasswordUrl = utilityService.getBaseUrl() + EmailConstants.SLASH  + EmailConstants.CHANGE_PARRWORD_URL + user.getId() + EmailConstants.SLASH
		                    + aesService.base64Encode(encryptLoginId.getBytes());
		            final String message = utilityService.classpathResourceLoader(EmailConstants.CUSTOMER_REG_TEMPLATE)
		                    .replace(EmailConstants.NAME, customer.getFirstName() + Constants.SPACE + customer.getLastName())
		                    .replace(EmailConstants.URL, setPasswordUrl)
		                    .replace(EmailConstants.CUSTOMER_ID, customerIDPrefix + customer.getCustomerId()).replace(EmailConstants.USER_LOGIN_ID, user.getLoginId())
		                    .replace(EmailConstants.EMAIL, env.getProperty(PropertyKeyConstants.ServerProperties.SENDER_EMAIL))
		                    .replace(EmailConstants.BASE_URL, utilityService.getBaseUrl())
		                    .replace(EmailConstants.APP_NAME, EmailConstants.MPLS_APP_NAME).replace(EmailConstants.APP_ACCOUNT_NAME, EmailConstants.MPLS).replace(EmailConstants.ID, String.valueOf(user.getId()));
		
		            new Thread(new Runnable() {
		                @Override
		                public void run() {
		                    emailSenderService.sendEmail(utilityService.getSenderMail(), customer.getEmailId().split(Constants.COMMA), null,
		                            EmailConstants.WELCOME_MAIL, message, null, null, true);
		                }
		            }).start();
		        }
		}
		
		private void sendCustomerRegSMS(CustomerDTO customer, UserDTO user, int isRegistered, MerchantDTO merchant) {
				if (null != user && isRegistered > 0) {
					String customerIDPrefix = appPropertiesService.getSystemParamValueByName(Constants.CUSTOMER_ID_PREFIX);
					merchant.setCustomerId(customerIDPrefix+customer.getCustomerId());
					eventRegisterService.eventRegister(Constants.ADD_CUSTOMER_WELCOME_SMS, getEventArgument(merchant), customer.getMobileNumber());
				}
		}
		
		private String getEventArgument(MerchantDTO merchant) {
				ObjectMapper mapper = new ObjectMapper();
			    try {
					String data = mapper.writeValueAsString(merchant);
					return data;
				} catch (Exception e) {
					e.printStackTrace();
				} 
			    return Constants.BLANK;
		}

		public int updateCustomer(CustomerDTO customerDTO) {
			   return customerDao.updateCustomer(customerDTO);
		}
		
		public List<CustomerDTO> getAllCustomer(){
	    	   return customerDao.getAllCustomer();
	    }
		
		public CustomerDTO generateCustomerId(CustomerDTO customer){
			   customer.setCustomerId(utilityService.generateCustomerId(false, true));
			   customer.setCustomerNumber(utilityService.generateNumber(15, false, true));
			   return customer;
		}

		public List<CustomerDTO> getCustomerDetails(String customerSearchMenu, String searchValue) {
 			   return customerDao.getCustomerDetails(customerSearchMenu, searchValue);
		}

		public int updateCustomerStatus(SearchUserDTO searchUserDTO) {
			   return customerDao.updateCustomerStatus(searchUserDTO);
		}

		public List<CustomerDTO> getCustomerByEmailId(String emailId) {
			   return customerDao.getCustomerByEmailId(emailId);
		}

		public List<CustomerDTO> getCustomerByMobileNo(String mobile) {
			   return customerDao.getCustomerByMobileNo(mobile);
		}
		
		public List<MerchantMappingDTO> getMerchantByCustomer(int cId) {
			   return customerDao.getMerchantByCustomer(cId);
		}

		public int getTotalCustomer() {
			   return customerDao.getTotalCustomer();
		}

		public int getActiveCustomer(String fromDate, String toDate) {
			   String date = fromDate.substring(0,10);
			   LocalDate localDateTime =null;
			   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			   LocalDate dateTime = LocalDate.parse(date);
			   localDateTime = dateTime.minusDays(7);
			   return customerDao.getActiveCustomer(localDateTime.format(formatter)+ Constants.ZERO_TIMESTAMP, fromDate);
		}

		public int getRecentCustomer(String fromDate, String toDate) {
			   return customerDao.getRecentCustomer(fromDate, toDate);
		}
}
