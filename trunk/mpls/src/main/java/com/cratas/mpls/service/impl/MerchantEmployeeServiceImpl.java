package com.cratas.mpls.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.utility.GroupType;
import com.cratas.mpls.dao.IMerchantEmployeeDao;
import com.cratas.mpls.service.IMerchantEmployeeService;
import com.cratas.mpls.service.IUserService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.MerchantEmployeeDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author bhupendra
 *
 */
@Service
public class MerchantEmployeeServiceImpl implements IMerchantEmployeeService{
		
	    private final static Logger LOGGER = LoggerFactory.getLogger(MerchantEmployeeServiceImpl.class);
	
		@Autowired
		private IMerchantEmployeeDao merchantEmployeeDao;
		
		@Autowired
		private IUserService userService;
		
		@Autowired
	    private IUtilityService utilityService;
		
		public int saveMerchantEmployee(MerchantEmployeeDTO merchantEmployee, UserDTO loginUserDTO) {
			   try{
				   boolean isMerchant = merchantEmployeeDao.isMerchantEmployee(merchantEmployee.getMobileNumber());
				   if(!isMerchant){
					   int isUserInserted = insertMerchantUser(merchantEmployee, loginUserDTO);
					   if(isUserInserted == 1){
						    UserDTO userDetails = userService.getUserByLoginId(merchantEmployee.getMobileNumber());
						    merchantEmployee.setUserId(userDetails.getId());
						    merchantEmployeeDao.saveMerchantEmployee(merchantEmployee);
						    return 1;
					   }
				   }
				   return 2;
	    	   }catch(Exception e){
	    		   LOGGER.error("Error in Customer creation -- "+merchantEmployee.getLoginId());
	    		   return 0;
	    	   }
		}
	
		private int insertMerchantUser(MerchantEmployeeDTO merchantEmployee, UserDTO loginUserDTO){
			    UserDTO user = getUserObj(merchantEmployee);
	    	    user.setLoginId(merchantEmployee.getMobileNumber());
	    	    user.setCreatedBy(merchantEmployee.getCreatedBy());
	    	    user.setIsActive(Constants.ACTIVE_ONE);
	    	    user.setIsApproved(Constants.ACTIVE_ONE);
	    	    return userService.saveUser(user, loginUserDTO, false);
		}
		
		public MerchantEmployeeDTO getMerchantEmployeeById(int userId) {
			   return merchantEmployeeDao.getMerchantEmployeeById(userId);
		}
	
		public List<MerchantEmployeeDTO> getMerchantEmployeeByMId(int mId) {
			   return merchantEmployeeDao.getMerchantEmployeeByMId(mId);
		}
		
		public MerchantEmployeeDTO getMerchantEmployeeByMN(String mobileNumber) {
			   return merchantEmployeeDao.getMerchantEmployeeByMN(mobileNumber);
		}

		public int updateMerchantEmployee(MerchantEmployeeDTO merchantEmployee) {
			   try{
				   return merchantEmployeeDao.updateMerchantEmployee(merchantEmployee);
	    	    }catch(Exception e){
	    		   LOGGER.error("Error in update Merchant Employee -- "+merchantEmployee.getLoginId());
	    		   return 0;
	    	    }
		}
		
		private UserDTO getUserObj(MerchantEmployeeDTO merchantEmployee){
			    UserDTO user = new UserDTO();	
			    user.setSystemId(utilityService.getSystemName());
	    	    user.setGroupId(GroupType.MERCHANT.getGroupType());
	    	    user.setEmailId(merchantEmployee.getEmailId());
	    	    user.setMobileNumber(merchantEmployee.getMobileNumber());
	    	    user.setFirstName(merchantEmployee.getFirstName());
	    	    user.setLastName(merchantEmployee.getLastName());
	    	    user.setMiddleName(merchantEmployee.getMiddleName());
	    	    user.setStatus(Constants.ONE);
	    	    user.setUserType(3);
	    	    return user;
		}

		public int deleteMerchantEmployee(MerchantEmployeeDTO merchantEmployeeDTO) {
			   return merchantEmployeeDao.deleteMerchantEmployee(merchantEmployeeDTO);
		}

		public MerchantEmployeeDTO getMerchantEmpId(int id) {
			   return merchantEmployeeDao.getMerchantEmpId(id);
		}
		
		public MerchantEmployeeDTO getMerchantOwner(int mId){
			   return merchantEmployeeDao.getMerchantOwner(mId);
		}
		
		public List<MerchantEmployeeDTO> getMerchantEmpByEmailId(String emailId){
			   return merchantEmployeeDao.getMerchantEmpByEmailId(emailId);
		}

		public List<MerchantEmployeeDTO> getMerchantEmpByMobileNo(String mobileNumber){
			   return merchantEmployeeDao.getMerchantEmpByMobileNo(mobileNumber);
		}
}
