package com.cratas.mpls.service.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.constant.EmailConstants;
import com.cratas.mpls.common.constant.PropertyKeyConstants;
import com.cratas.mpls.common.utility.GroupType;
import com.cratas.mpls.common.utility.SaveUserNotificationThread;
import com.cratas.mpls.dao.IMerchantDao;
import com.cratas.mpls.service.IAESEncryptionService;
import com.cratas.mpls.service.IAppPropertiesService;
import com.cratas.mpls.service.IBCryptService;
import com.cratas.mpls.service.IEmailSenderService;
import com.cratas.mpls.service.ILoyaltyCashbackService;
import com.cratas.mpls.service.IMPLSService;
import com.cratas.mpls.service.IMerchantEmployeeService;
import com.cratas.mpls.service.IMerchantPlanService;
import com.cratas.mpls.service.IMerchantService;
import com.cratas.mpls.service.IUserService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.LoyaltyCashbackDTO;
import com.cratas.mpls.web.dto.MerchantAddressDTO;
import com.cratas.mpls.web.dto.MerchantDTO;
import com.cratas.mpls.web.dto.MerchantEmployeeDTO;
import com.cratas.mpls.web.dto.MerchantPlanDTO;
import com.cratas.mpls.web.dto.NotificationDTO;
import com.cratas.mpls.web.dto.SearchUserDTO;
import com.cratas.mpls.web.dto.UserDTO;


/**
 * 
 * @author mukesh
 *
 */
@Service
public class MerchantServiceImpl implements IMerchantService {
	
	    private final static Logger LOGGER = LoggerFactory.getLogger(CustomerTransactionServiceImpl.class);

	    @Autowired
	    private IMerchantDao merchantDao;
	
	    @Autowired
	    private SaveUserNotificationThread saveUserNotificationThread;
	
	    @Autowired
	    private ThreadPoolTaskExecutor taskExecutor;
	
	    @Autowired
	    private IMPLSService mplsService;
	    
	    @Autowired
	    private IUserService userService;
	    
	    @Autowired
	    private IUtilityService utilityService;
	    
	    @Autowired
	    private IBCryptService bCryptService;
	    
	    @Autowired
	    private IAESEncryptionService aesService;
	    
	    @Autowired
	    private IEmailSenderService emailSenderService;
	    
	    @Autowired
	    private IAppPropertiesService appPropertiesService;
	    
	    @Autowired
	    private IMerchantEmployeeService merchantEmployeeService;
	    
	    @Autowired
	    private ILoyaltyCashbackService loyaltyCashbackService;
	    
	    @Autowired
	    private Environment env;
	    
	    @Autowired
	    private IMerchantPlanService merchantPlanService;
	    
	    public int saveMerchant(UserDTO userDTO, MerchantDTO merchantDTO) {
	    	   if(isExistsMerchant(merchantDTO.getMerchantName(), merchantDTO.getPANNumber(), merchantDTO.getGSTIN(), merchantDTO.getEmailId(), merchantDTO.getMobileNumber()) == null){
		    	   int isInsertedUser = insertMerchantUser(merchantDTO, userDTO);
		    	   if(isInsertedUser == 1){
		    		   UserDTO userDetails = userService.getUserByLoginId(merchantDTO.getLoginId());
				       int isInserted = merchantDao.saveMerchant(merchantDTO, userDetails);
				       if (isInserted > 0 && null != userDTO && userDTO.getGroupDTO().getGroupType().equals(Constants.MAKER)) {
				    	    MerchantDTO merchant = getMerchantByMId(merchantDTO.getMerchantId());
				            NotificationDTO notification = mplsService.createUserNotificationObj(String.valueOf(merchant.getId()), Constants.MERCHANT, userDTO);
				            mplsService.updateRecordStatus(notification.getTableName(), notification.getRecordId(), 0);
				            saveUserNotificationThread.setData(notification, userDTO);
				            taskExecutor.execute(saveUserNotificationThread);
				       }
				       merchantEmployeeService.saveMerchantEmployee(mapMerchantEmployee(merchantDTO), userDTO);
				       sendMerchantRegMail(merchantDTO, userDetails, isInserted);
				       return 1;
		    	   }
		    	   return 3;
	    	   }
	    	   return 2;
	    }
	   
		public MerchantDTO isExistsMerchant(String mName, String panNumber, String GSTIN, String emailId, String mobileNumber){
	    	   return merchantDao.isExistsMerchant(mName, panNumber, GSTIN, emailId, mobileNumber);
	    }
		   		   
	    public MerchantDTO getMerchantByMId(String merchantId){
	    	   return merchantDao.getMerchantByMId(merchantId);
	    }
		   
	    public MerchantDTO getMerchantById(int id){
	    	   return merchantDao.getMerchantById(id);
	    }
		   
	    public int deleteAddress(int mid){
	    	   return merchantDao.deleteAddress(mid);
	    }
		   
	    
	    public int updateMerchant(MerchantDTO merchant){
	    	   int isUpdated = updateMerchantUser(merchant);
	    	   if(isUpdated == 2){
	    		   return merchantDao.updateMerchant(merchant);
	    	   }
	    	   return isUpdated;
	    	   
	    }
		   
	    public int deleteMerchant(MerchantDTO merchantDTO){
	    	   return merchantDao.deleteMerchant(merchantDTO);
	    }
		   
	    public List<MerchantAddressDTO> getMerchantAddresss(int id){
	    	   return merchantDao.getMerchantAddresss(id);
	    }
		   
	    public List<MerchantDTO> getAllMerchant(){
	    	   return merchantDao.getAllMerchant();
	    }	
	    
	    private int insertMerchantUser(MerchantDTO merchant, UserDTO loginUserDTO){
	    	    UserDTO user = getUserObj(merchant);
	    	    user.setLoginId(merchant.getLoginId());
	    	    user.setCreatedBy(merchant.getCreatedBy());
	    	    user.setIsActive(Constants.ACTIVE_ONE);
	    	    user.setIsApproved(Constants.ACTIVE_ONE);
	    	    return userService.saveUser(user, loginUserDTO, true);
	    }
	    
	    private int updateMerchantUser(MerchantDTO merchant){
	    	    MerchantEmployeeDTO merchantEmployee = merchantEmployeeService.getMerchantOwner(merchant.getId());
	    	    if(null != merchantEmployee){
	    	    	merchantEmployee.setEmailId(merchant.getEmailId());
	    	    	merchantEmployee.setMobileNumber(merchant.getMobileNumber());
	    	    	merchantEmployee.setFirstName(merchant.getFirstName());
	    	    	merchantEmployee.setMiddleName(merchant.getMiddleName());
	    	    	merchantEmployee.setLastName(merchant.getLastName());
	    	    	merchantEmployee.setUpdatedBy(merchant.getUpdatedBy());
	    	    	return merchantEmployeeService.updateMerchantEmployee(merchantEmployee);
	    	    }
	    	    return 0;
	    }
	    
		private MerchantEmployeeDTO mapMerchantEmployee(MerchantDTO merchant) {
				MerchantEmployeeDTO merchantEmployee = new MerchantEmployeeDTO();
				merchantEmployee.setmId(merchant.getId());
				merchantEmployee.setEmailId(merchant.getEmailId());
				merchantEmployee.setMobileNumber(merchant.getMobileNumber());
				merchantEmployee.setFirstName(merchant.getFirstName());
				merchantEmployee.setMiddleName(merchant.getMiddleName());
				merchantEmployee.setLastName(merchant.getLastName());
				merchantEmployee.setUpdatedBy(merchant.getUpdatedBy());
				return merchantEmployee;
		}
	    
	    private UserDTO getUserObj(MerchantDTO merchant){
	    	    UserDTO user = new UserDTO();	
			    user.setSystemId(utilityService.getSystemName());
	    	    user.setGroupId(GroupType.MERCHANT.getGroupType());
	    	    user.setEmailId(merchant.getEmailId());
	    	    user.setMobileNumber(merchant.getMobileNumber());
	    	    user.setFirstName(merchant.getFirstName());
	    	    user.setLastName(merchant.getLastName());
	    	    user.setMiddleName(merchant.getMiddleName());
	    	    user.setStatus(Constants.ONE);
	    	    user.setUserType(3);
	    	    return user;
	    	    
	    }
	    	    
	    private void sendMerchantRegMail(final MerchantDTO merchant, final UserDTO user, int isInserted) {
		        if (null != user && isInserted > 0) {
		        	String merchantPrefix = appPropertiesService.getSystemParamValueByName(Constants.MERCHANT_ID_PREFIX);
		        	String encryptLoginId = bCryptService.getEncryptedString(user.getLoginId());
		            String resetPassUrl = utilityService.getBaseUrl() + EmailConstants.SLASH  + EmailConstants.CHANGE_PARRWORD_URL + user.getId() + EmailConstants.SLASH
		                    + aesService.base64Encode(encryptLoginId.getBytes());
		            final String message = utilityService.classpathResourceLoader(EmailConstants.MERCHANT_REG_TEMPLATE)
		                    .replace(EmailConstants.NAME, merchant.getMerchantName()) .replace(EmailConstants.URL, resetPassUrl)
		                    .replace(EmailConstants.MERCHANT_ID, merchantPrefix + merchant.getMerchantId()).replace(EmailConstants.USER_LOGIN_ID, user.getMobileNumber())
		                    .replace(EmailConstants.EMAIL, env.getProperty(PropertyKeyConstants.ServerProperties.SUPPORT_EMAIL))
		                    .replace(EmailConstants.BASE_URL, utilityService.getBaseUrl())
		                    .replace(EmailConstants.APP_NAME, EmailConstants.MPLS_APP_NAME).replace(EmailConstants.ID, String.valueOf(user.getId()));
		
		            new Thread(new Runnable() {
		                @Override
		                public void run() {
		                    emailSenderService.sendEmail(utilityService.getSenderMail(), merchant.getEmailId().split(Constants.COMMA), null,
		                            EmailConstants.WELCOME_MAIL, message, null, null, true);
		                }
		            }).start();
		        }
         }
	     
	     public boolean isMerchant(String emailId, String mobileNumber){
	    	    return merchantDao.isMerchant(emailId, mobileNumber);
	     }	
	     
	     public List<MerchantDTO> recentMerchantList(int limit, UserDTO user){
	    	    List<LoyaltyCashbackDTO> offerList = loyaltyCashbackService.getLoyaltyCashback();
	    	    List<MerchantDTO> merchantList = merchantDao.recentMerchantList(limit);
	    	    List<MerchantDTO> cutomerMerchantList = customerMerchantMappingList(user.getId());
	    	    List<MerchantDTO> mappingRequestSendList = mappingRequestSendList(user.getId());
	    	    setMerchantOfferAndAlreadyMerchant(cutomerMerchantList, mappingRequestSendList, merchantList, offerList);
	    	    return merchantList;
	     }
	     
		public List<MerchantDTO> filterMerchantListByArea(String areaId, UserDTO user){
	    	    List<LoyaltyCashbackDTO> offerList = loyaltyCashbackService.getLoyaltyCashback();
	    	    List<MerchantDTO> merchantList =  merchantDao.filterMerchantListByArea(areaId);
	    	    List<MerchantDTO> cutomerMerchantList = customerMerchantMappingList(user.getId());
	    	    if(cutomerMerchantList != null) {
	    	    	List<MerchantDTO> mappingRequestSendList = mappingRequestSendList(user.getId());
	    	    	setMerchantOfferAndAlreadyMerchant(cutomerMerchantList, mappingRequestSendList, merchantList, offerList);
	    	    }
	    	    return merchantList;
	     }
	     
	     private void setMerchantOfferAndAlreadyMerchant(List<MerchantDTO> cutomerMerchantList, List<MerchantDTO> mappingRequestSendList, List<MerchantDTO> merchantList,  List<LoyaltyCashbackDTO> offerList){
		    	 if(!cutomerMerchantList.isEmpty()){
		   	    	  for(MerchantDTO merchant : merchantList) {
		   	    		  merchant.setOfferCount(getMerchantOfferCount(offerList, merchant.getId()));
		   	    		  if(isAlreadyAddedMerchant(cutomerMerchantList, merchant.getId())){
		   	    			  merchant.setIsAddedMerchant(1);
		   	    		  }
		   	    		  if(isRequestSend(mappingRequestSendList, merchant.getId())) {
		   	    			merchant.setIsAddedMerchant(2);
		   	    		  }
					  }
		   	    }else{
			   	     for (MerchantDTO merchant : merchantList) {
			    		  merchant.setOfferCount(getMerchantOfferCount(offerList, merchant.getId()));
			    	 }
		   	    }
	     }
	     
	    private List<MerchantDTO> mappingRequestSendList(int id) {
	    	    return merchantDao.mappingRequestSendList(id);
		} 

		private int getMerchantOfferCount(List<LoyaltyCashbackDTO> offerList, int mId){
	    	     int offerCount = 0;
	    	     for (LoyaltyCashbackDTO loyaltyCashbackDTO : offerList) {
					  if(loyaltyCashbackDTO.getmId() == mId){
						  offerCount++;
					  }
				 }
	    	     return offerCount;
	     }
	     
	     private boolean isAlreadyAddedMerchant(List<MerchantDTO> cutomerMerchantList, int mId){
	    	     for (MerchantDTO merchantDTO : cutomerMerchantList) {
				       if(merchantDTO.getId() == mId){
				    	   return true;
				       }
				 }
	    	     return false;
	     }
	     
	     private boolean isRequestSend(List<MerchantDTO> merchantList, int mId) {
		    	 for (MerchantDTO merchantDTO : merchantList) {
				       if(merchantDTO.getId() == mId && merchantDTO.getIsActive() == 0){
				    	   return true;
				       }
				 }
				 return false;
		 }

		 public List<MerchantDTO> customerMerchantMappingList(int id) {
			    return merchantDao.customerMerchantMappingList(id);
		 }

		 public List<MerchantDTO> getMerchantDetails(String merchantSearchMenu, String searchValue) {
			    return merchantDao.getMerchantDetails(merchantSearchMenu, searchValue);
		 }

		 public int updateMerchantStatus(SearchUserDTO searchUserDTO) {
			    return merchantDao.updateMerchantStatus(searchUserDTO);
		 }

		 public List<MerchantDTO> getMerchantUserDetail(int id) {
			    return merchantDao.getMerchantUserDetail(id);
		 }
		
		 public int unBlockMerchantUser(SearchUserDTO searchUser) {
			    return merchantDao.unBlockMerchantUser(searchUser);
		 }

		 public List<MerchantDTO> getMerchantByPanNumber(String panNumber) {
			    return merchantDao.getMerchantByPanNumber(panNumber);
		 }

		 public List<MerchantDTO> getMerchantByGSTIN(String gstin) {
			    return merchantDao.getMerchantByGSTIN(gstin);
		 }

		 public List<MerchantDTO> getMerchantByEmailId(String emailId) {
			    return merchantDao.getMerchantByEmailId(emailId);
		 }

		 public List<MerchantDTO> getMerchantByMobileNo(String mobile) {
			    return merchantDao.getMerchantByMobileNo(mobile);
		 }
		
		 public int getBasicMerchantPlanId() {
			    MerchantPlanDTO merchantPlan = merchantPlanService.getMerchantPlanByName(Constants.BASIC_MERCHANT_PLAN);
			    if(null != merchantPlan) {
			    	return merchantPlan.getId();
			    }
			    return 0;
	     }
		 
		 public int updateMerchantPlan(MerchantDTO merchantRequest) {
			    try {
				    MerchantDTO merchantDTO = getMerchantByMId(merchantRequest.getMerchantId());
				    if(null != merchantDTO) {
					   return merchantDao.updateMerchantPlan(merchantDTO);
				    }
			    }catch(Exception e) {
				 	LOGGER.error("Error in update Merchant Plan -- "+e.getMessage());
			    }	   
			    return 0;
		}
		
		public int saveMerchantDetails(MerchantDTO merchantDTO) {
			   return merchantDao.saveMerchantDetails(merchantDTO);
		}

		public int updateMerchantFlag(MerchantDTO merchantDTO) {
			   return merchantDao.updateMerchantFlag(merchantDTO);
		}

		public MerchantDTO getMerchantByMN(String mobileNumber) {
			   return merchantDao.getMerchantByMN(mobileNumber);
		}

		public List<MerchantDTO> filterMerchantByAreaPin(String areaId, String pinCode) {
			   return merchantDao.filterMerchantByAreaPin(areaId, pinCode);
		}

		public List<MerchantAddressDTO> getMerchantAddress() {
			   return merchantDao.getMerchantAddress();
		}

		public int getTotalMerchant() {
			   return merchantDao.getTotalMerchant();
		}

		public int getActiveMerchant(String fromDate, String toDate) {
			   String date = fromDate.substring(0,10);
			   LocalDate localDateTime =null;
			   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			   LocalDate dateTime = LocalDate.parse(date);
			   localDateTime = dateTime.minusDays(7);
			   return merchantDao.getActiveMerchant(localDateTime.format(formatter)+ Constants.ZERO_TIMESTAMP, fromDate);
		}

		public int getRecentMerchant(String fromDate, String toDate) {
			   return merchantDao.getRecentMerchant(fromDate, toDate);
		}

		public List<MerchantDTO> getMerchantWalletBal() {
			   return merchantDao.getMerchantWalletBal();
		} 
}
