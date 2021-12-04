package com.cratas.mpls.web.controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.constant.PageConstant;
import com.cratas.mpls.common.utility.GroupType;
import com.cratas.mpls.common.utility.constant.RedirectScreenConstants;
import com.cratas.mpls.service.IAESEncryptionService;
import com.cratas.mpls.service.IAppPropertiesService;
import com.cratas.mpls.service.IBCryptService;
import com.cratas.mpls.service.ICustomerService;
import com.cratas.mpls.service.ICustomerTransactionService;
import com.cratas.mpls.service.IGroupService;
import com.cratas.mpls.service.ILoyaltyCardService;
import com.cratas.mpls.service.IMPLSService;
import com.cratas.mpls.service.IMerchantEmployeeService;
import com.cratas.mpls.service.IMerchantMappingService;
import com.cratas.mpls.service.IMerchantService;
import com.cratas.mpls.service.IPushNotificationLogService;
import com.cratas.mpls.service.ISystemService;
import com.cratas.mpls.service.IUserHistoryService;
import com.cratas.mpls.service.IUserService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.CustomerDTO;
import com.cratas.mpls.web.dto.CustomerNotificationDTO;
import com.cratas.mpls.web.dto.CustomerTransactionDTO;
import com.cratas.mpls.web.dto.GroupDTO;
import com.cratas.mpls.web.dto.MerchantEmployeeDTO;
import com.cratas.mpls.web.dto.MerchantMappingDTO;
import com.cratas.mpls.web.dto.MerchantTransactionDTO;
import com.cratas.mpls.web.dto.NotificationDTO;
import com.cratas.mpls.web.dto.PushNotificationLogDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author mukesh
 *
 */
@Controller
@RequestMapping("/add/user")
public class UserController extends BaseController {
	
	    private final static Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	    @Autowired
	    private IUtilityService utilityService;
	
	    @Autowired
	    private IUserService userService;
	
	    @Autowired
	    private IGroupService groupService;
	
	    @Autowired
	    private ISystemService systemService;
	    
	    @Autowired
	    private ICustomerService customerService;
	    
	    @Autowired
		private IMerchantEmployeeService merchantEmployeeService;
	    
	    @Autowired 
	    private IMerchantService merchantService;
	    
	    @Autowired 
	    private ILoyaltyCardService loyaltyCardService;
	
	    @Autowired
	    private IBCryptService bCryptService;
	
	    @Autowired
	    private IAESEncryptionService aesService;
	
	    @Autowired
	    private IMPLSService reconService;
	
	    @Autowired
	    private IUserHistoryService userHistoryService;
	    
	    @Autowired
	    private IAppPropertiesService appPropertiesService;
	    
	    @Autowired
		private ICustomerTransactionService customerTransactionService;
	    
	    @Autowired
		private IPushNotificationLogService pushNotificationLogService;
	    
	    @Autowired
		private IMerchantMappingService merchantMappingService;
	    
	    @RequestMapping(value = "/view", method = RequestMethod.GET)
	    public ModelAndView GroupView(HttpServletRequest request, HttpServletResponse response, Model model) {
		       if(checkUserAlreadyLogin(request, response)) {
		            UserDTO userDTO = getSessionUserObject(request);
		            ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.USER));
		            request.setAttribute(Constants.USER_LIST, userService.getUser(userDTO.getSystemId()));
		            request.setAttribute(Constants.SYSTEM_LIST, systemService.getActiveSystem());
		            Map<String, Object> requestMap = ((Model) model).asMap();
		            if (null != requestMap) {
		                String action = (String) requestMap.get(Constants.ACTION);
		                request.setAttribute(Constants.MESSAGE, requestMap.get(Constants.MESSAGE));
		                request.setAttribute(Constants.IS_SUCCESS, requestMap.get(Constants.IS_SUCCESS));
		                request.setAttribute(Constants.ACTION, requestMap.get(Constants.ACTION));
		                setRequestAttribute(request, action, requestMap, userDTO);
		            }
		            return view;
		       }
		       return new ModelAndView("redirect:/");
	    }
	
	    /**
	     * This method used to save user.
	     * 
	     * @return
	     */
	    @RequestMapping(value = "/add", method = RequestMethod.POST)
	    public ModelAndView add(@ModelAttribute("userDTO") UserDTO userDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
	           if (checkUserAlreadyLogin(request, response)) {
	                try{
		                UserDTO loginUserDTO = getSessionUserObject(request);
		                int isInsert = userService.saveUser(userDTO, loginUserDTO, false);
		                if (isInsert == 1) {
		                    redirectAttribute.addFlashAttribute(Constants.MESSAGE, "User Added Successfully.");
		                    redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS);
		                    return new ModelAndView(RedirectScreenConstants.REDIRECT_USER);
		                } else if (isInsert == 2) {
		                    redirectAttribute.addFlashAttribute(Constants.MESSAGE, "User Already Exists.");
		                    redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE);
		                    return new ModelAndView(RedirectScreenConstants.REDIRECT_USER);
		                }
	            } catch (Exception e) {
	                LOGGER.error("Error in add User -->  " + e);
	            }
	
	        }
	        redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Add User.");
	        redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE);
	        return new ModelAndView(RedirectScreenConstants.REDIRECT_USER);
	    }
	
	    /**
	     * This method used to update User.
	     * 
	     * @return
	     */
	    @RequestMapping(value = "/update", method = RequestMethod.GET)
	    public ModelAndView update(@RequestParam("loginId") String loginId, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response, Model model) {
		       if (checkUserAlreadyLogin(request, response)) {
		            if(StringUtils.isNotEmpty(loginId)) {
		                redirectAttribute.addFlashAttribute(Constants.USER, userService.getUserByLoginId(loginId));
		                redirectAttribute.addFlashAttribute(Constants.ACTION, Constants.UPDATE);
		                return new ModelAndView(RedirectScreenConstants.REDIRECT_USER);
		            }
		            redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in update User.");
		            return new ModelAndView(RedirectScreenConstants.REDIRECT_USER);
		       }
		       return new ModelAndView("redirect:/");
	    }
	
	    /**
	     * This method used to update User.
	     * 
	     * @return
	     */
	    @RequestMapping(value = "/edit", method = RequestMethod.POST)
	    public ModelAndView edit(@ModelAttribute("userDTO") UserDTO userDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
		       if (checkUserAlreadyLogin(request, response)) {
		            try {
		                int isUpdate = userService.updateUser(userDTO);
		                if (isUpdate > 0) {
		                    redirectAttribute.addFlashAttribute(Constants.MESSAGE, "User Updated Successfully.");
		                    redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS);
		                    return new ModelAndView(RedirectScreenConstants.REDIRECT_USER);
		                }
		            } catch (Exception e) {
		                LOGGER.error("Error in update User -->  " + e);
		            }
		       }
		       redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Update User.");
		       redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE);
		       return new ModelAndView(RedirectScreenConstants.REDIRECT_USER);
	    }
	
	    /**
	     * This method used to delete User
	     */
	    @RequestMapping(value = "/delete", method = RequestMethod.POST)
	    public ModelAndView delete(@ModelAttribute("userDTO") UserDTO userDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
	           if (checkUserAlreadyLogin(request, response)) {
		            try {
		                int isInsert = userService.deleteUser(userDTO);
		                if (isInsert > 0) {
		                    redirectAttribute.addFlashAttribute(Constants.MESSAGE, "User Deleted Successfully.");
		                    redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS);
		                    return new ModelAndView(RedirectScreenConstants.REDIRECT_USER);
		                }
		            } catch (Exception e) {
		                LOGGER.error("Error in delete User -->  " + e);
		            }
	         }
	         redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in delete User.");
	         redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE);
	         return new ModelAndView(RedirectScreenConstants.REDIRECT_USER);
	    }
	
	    @RequestMapping(value = "/profile", method = RequestMethod.GET)
	    public ModelAndView userProfile(RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response, Model model) {
		       if (checkUserAlreadyLogin(request, response)) {
		            ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.USER_PROFILE));
		            request.setAttribute(Constants.USER_HISTORY_LIST, userHistoryService.getUserHistoryDetails(getSessionUserObject(request).getLoginId()));
		            request.setAttribute(Constants.CUSTOMER_ID_PREFIX, appPropertiesService.getSystemParamValueByName(Constants.CUSTOMER_ID_PREFIX));
		            request.setAttribute(Constants.MERCHANT_ID_PREFIX, appPropertiesService.getSystemParamValueByName(Constants.MERCHANT_ID_PREFIX));
		            return view;
		       }
		       return new ModelAndView("redirect:/");
	    }
	
	    /**
	     * This method used to redirect to Change User Password Screen.
	     * @return
	     */
	    @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	    public ModelAndView userUpdatePassword(RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response, Model model) {
	          if (checkUserAlreadyLogin(request, response)) {
		            ModelAndView view = new ModelAndView(
		                    utilityService.getViewResolverName(request.getSession(), PageConstant.UPDATE_PASSWORD));
		            request.setAttribute(Constants.USER_ID, getEncryptedLoginId(request));
		            request.setAttribute(Constants.AES.toLowerCase(), aesService.getAesDTO());
		            Map<String, Object> requestMap = ((Model) model).asMap();
		            if (null != requestMap) {
		                request.setAttribute(Constants.MESSAGE, requestMap.get(Constants.MESSAGE));
		            }
		            return view;
	         }
	         return new ModelAndView("redirect:/");
	    }
	
	    @RequestMapping(value = "/notification", method = { RequestMethod.GET, RequestMethod.POST })
	    public ModelAndView approve(@ModelAttribute("notificationDTO") NotificationDTO notificationDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
		       if (checkUserAlreadyLogin(request, response)) {
		            UserDTO user = getSessionUserObject(request);
		            ModelAndView view = new ModelAndView(
		                    utilityService.getViewResolverName(request.getSession(), PageConstant.NOTIFICATION));
		            user.setNotificationList(userService.getUserNotification(user));
		            request.setAttribute(Constants.USER_NOTIFICATION, notificationDTO);
		            return view;
		       }
		       return new ModelAndView("redirect:/");
	    }
	
	    @RequestMapping(value = "/approveNotification", method = RequestMethod.POST)
	    public ModelAndView approveNotification(@RequestParam("recordId") String recordId, @RequestParam("tableName") String tableName, @RequestParam("menuName") String menuName,
	            RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
		        ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.NOTIFICATION_MODAL));
		        if (checkUserAlreadyLogin(request, response) && StringUtils.isNotEmpty(tableName) && StringUtils.isNotEmpty(recordId)) {
		            request.setAttribute(Constants.HEADER_LIST, reconService.getTableHeaderList(menuName));
		            request.setAttribute(Constants.NOTIFICATION_DETAIL, reconService.getNotificationById(tableName, recordId));
		            return view;
		        }
		        return new ModelAndView("redirect:/");
	    }
	
	    @RequestMapping(value = "/approveNotificationRecord", method = RequestMethod.POST)
	    public @ResponseBody String approveNotificationRecord(@RequestParam("requestType") String requestType, @RequestParam("id") int id, @RequestParam("comments") String comments,
	            @RequestParam("tableName") String tableName, @RequestParam("menuName") String menuName, @RequestParam("recordId") String recordId, @RequestParam("creatorId") String creatorId,
	            RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
		        if (checkUserAlreadyLogin(request, response)) {
			            if (requestType != null && requestType.equalsIgnoreCase(Constants.APPROVE_REQUEST_TYPE)) {
			                int isUpdate = reconService.updateRecordStatus(tableName, recordId, 1);
			                if (isUpdate > 0) {
			                    reconService.updateUserNotificationStatus(1, id, comments);
			                }
			                return Constants.APPROVE_REQUEST_TYPE;
			            } else if (requestType != null && requestType.equalsIgnoreCase(Constants.REJECT_REQUEST_TYPE)) {
			                int isUpdate = reconService.updateRecordStatus(tableName, recordId, 2);
			                if (isUpdate > 0) {
			                    reconService.updateUserNotificationStatus(2, id, comments);
			                }
			                return Constants.REJECT_REQUEST_TYPE;
			            } else {
			                int isUpdate = reconService.updateRecordStatus(tableName, recordId, 3);
			                if (isUpdate > 0) {
			                    reconService.updateUserNotificationStatus(3, id, comments);
			                }
			                return Constants.REMOVE_REQUEST_TYPE;
			            }
		        }
		        return Constants.FAILURE;
	    }
	
	    @RequestMapping(value = "/userRequest", method = { RequestMethod.GET, RequestMethod.POST })
	    public ModelAndView makerNotification(RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
		       if (checkUserAlreadyLogin(request, response)) {
		            ModelAndView view = new ModelAndView(
		                    utilityService.getViewResolverName(request.getSession(), PageConstant.USER_REQUEST));
		            return view;
		       }
		       return new ModelAndView("redirect:/");
	    }
	
	    /**
	     * This method used to get User Info by Login Id
	     */
	    @RequestMapping(value = "/detail/{loginId}", method = RequestMethod.POST)
	    public ModelAndView detail(@PathVariable("loginId") String loginId, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
		       ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.USER_PROFILE_MODAL));
		       if (checkUserAlreadyLogin(request, response)) {
		            try {
		                UserDTO userDTO = userService.getUserByLoginId(loginId);
		                request.setAttribute(Constants.LOGIN_DTO, userDTO);
		            } catch (Exception e) {
		                LOGGER.error("Error in Get User Role Detail -->  " + e);
		            }
		       }
		       return view;
	    }
	
	    /**
	     * This method used to update User Password.
	     * @return
	     */
	    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	    public ModelAndView updatePassword(@ModelAttribute("loginDTO") UserDTO loginReqDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
		       if (checkUserAlreadyLogin(request, response)) {
		            if (null != loginReqDTO && StringUtils.isNotEmpty(loginReqDTO.getLoginId())) {
		                UserDTO userDTO = userService.getUserbyId(loginReqDTO.getId());
		                if (null != userDTO) {
		                    String salt = utilityService.generateSalt(userDTO, request);
		                    String iv = utilityService.generateIV(userDTO, request);
		                    if (bCryptService.isMatch(loginReqDTO.getLoginId(), userDTO.getLoginId())) {
		                        String decyptPass = aesService.decrypt(salt, iv, loginReqDTO.getLoginId(),
		                                loginReqDTO.getPassword());
		                        String encryptPass = bCryptService.getEncryptedString(decyptPass);
		                        GroupDTO groupDTO = groupService.getGroupById(userDTO.getGroupId());
		                        userDTO.setLastPassword(userDTO.getPassword());
		                        userDTO.setPassword(encryptPass);
		                        userDTO.setUpdatedBy(userDTO.getLoginId());
		                        userService.changePassword(userDTO);
		                        userService.savePasswordHistory(userDTO.getLoginId(), encryptPass,
		                                groupDTO.getPassHistoryChecks());
		                        redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Password updated Successfully");
		                        redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS);
		                        return new ModelAndView(RedirectScreenConstants.REDIRECT_CHANGE_PASSWORD);
		                    }
		                }
		           }
		           redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in User Change Password");
		           redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE);
		           return new ModelAndView(RedirectScreenConstants.REDIRECT_CHANGE_PASSWORD);
		        }
	        return new ModelAndView("redirect:/");
	    }
	
	    private void setRequestAttribute(HttpServletRequest request, String action, Map<String, Object> requestMap, UserDTO userObj) {
		        UserDTO userDTO = (UserDTO) requestMap.get(Constants.USER);
		        if (null != userDTO && StringUtils.isNotEmpty(action) && action.equals(Constants.UPDATE)) {
		            request.setAttribute(Constants.GROUP_LIST, groupService.getActiveGroupsById(userDTO.getSystemId()));
		            request.setAttribute(Constants.SELECT_USER_LIST, userService.getActiveUsersById(userDTO.getSystemId()));
		        } else {
		            setUserSpecificAttribute(request, userObj);
		        }
	    }
	
	    private void setUserSpecificAttribute(HttpServletRequest request, UserDTO userDTO) {
		        request.setAttribute(Constants.GROUP_LIST, groupService.getActiveGroupsById(userDTO.getSystemId()));
		        request.setAttribute(Constants.SELECT_USER_LIST, userService.getActiveUsersById(userDTO.getSystemId()));
	    }
	    
	    @RequestMapping(value = "/pushNotification", method = { RequestMethod.GET, RequestMethod.POST })
	    public ModelAndView approve(@ModelAttribute("pushNotificationLogDTO") PushNotificationLogDTO pushNotificationLogDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
		       if (checkUserAlreadyLogin(request, response)) {
		    	    UserDTO user = getSessionUserObject(request);
		            ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.PUSH_NOTIFICATION));
		            user = getUserLoginTypeObject(user);
				    if(GroupType.CUSTOMER.getGroupType().equals(user.getGroupDTO().getGroupType())){
				    	user.setPushNotificationLogList(getPushNotificationByCid(user.getCustomer().getId()));
				    }else if(GroupType.MERCHANT.getGroupType().equals(user.getGroupDTO().getGroupType())){
				    	user.setPushNotificationLogList(getPushNotificationByMid(user.getMerchant().getId()));
				    }
		            return view;
		       }
		       return new ModelAndView("redirect:/");
	    }
	    
	    private List<PushNotificationLogDTO> getPushNotificationByMid(int id) {
			List<PushNotificationLogDTO> pushNotificationList = pushNotificationLogService.getPushNotificationByMid(id);
			List<PushNotificationLogDTO> pushNotificationLogList = new LinkedList<>();
			for (PushNotificationLogDTO pushNotificationLogDTO : pushNotificationList) {
				JSONParser parser = new JSONParser();
				try {
					JSONObject json = (JSONObject) parser.parse(pushNotificationLogDTO.getNotificationArgs());
					JSONObject data = (JSONObject) json.get("data");
					pushNotificationLogDTO.setCustomerNotificationId(Integer.valueOf(data.get("id").toString()));
					pushNotificationLogDTO.setmId(Integer.valueOf(data.get("mId").toString()));
					pushNotificationLogDTO.setcId(Integer.valueOf(data.get("cId").toString()));
					pushNotificationLogDTO.setNotificationType(data.get("notificationType").toString().replaceAll("^\"|\"$", ""));
					pushNotificationLogList.add(pushNotificationLogDTO);
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			return pushNotificationLogList;
	    }

		private List<PushNotificationLogDTO> getPushNotificationByCid(int id) {
				List<PushNotificationLogDTO> pushNotificationList = pushNotificationLogService.getPushNotificationByCid(id);
				List<PushNotificationLogDTO> pushNotificationLogList = new LinkedList<>();
				for (PushNotificationLogDTO pushNotificationLogDTO : pushNotificationList) {
					JSONParser parser = new JSONParser();
					try {
						JSONObject json = (JSONObject) parser.parse(pushNotificationLogDTO.getNotificationArgs());
						JSONObject data = (JSONObject) json.get("data");
						pushNotificationLogDTO.setCustomerNotificationId(Integer.valueOf(data.get("id").toString()));
						pushNotificationLogDTO.setmId(Integer.valueOf(data.get("mId").toString()));
						pushNotificationLogDTO.setcId(id);
						pushNotificationLogDTO.setNotificationType(data.get("notificationType").toString().replaceAll("^\"|\"$", ""));
						pushNotificationLogList.add(pushNotificationLogDTO);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				return pushNotificationLogList;
		}
	
	    private UserDTO getUserLoginTypeObject(UserDTO user){
		    if(GroupType.CUSTOMER.getGroupType().equals(user.getGroupDTO().getGroupType())){
		    	  user.setCustomer(customerService.getCustomerById(user.getId()));
		    }else if(GroupType.MERCHANT.getGroupType().equals(user.getGroupDTO().getGroupType())){
		    	  MerchantEmployeeDTO merchantEmployee = merchantEmployeeService.getMerchantEmployeeById(user.getId());
		    	  if(null != merchantEmployee){
		    		  user.setMerchantEmployee(merchantEmployee);
		    		  user.setMerchant(merchantService.getMerchantById(merchantEmployee.getmId()));
		    	  }
		    }
		    return user;
	    }
	    
	    @RequestMapping(value = "/approvePushNotification", method = RequestMethod.POST)
	    public @ResponseBody String approveNotificationRecord(@RequestParam("id") int id, @RequestParam("cId") int cId, @RequestParam("mId") int mId, @RequestParam("customerNotificationId") int customerNotificationId, @RequestParam("notificationType") String notificationType,
	    		@RequestParam("transactionType") String transactionType,RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
		        if (checkUserAlreadyLogin(request, response)) {
		        	switch (notificationType) {
			        	case Constants.COLLECT_MONEY:
			        		return getCollectMoneyResponse(id, customerNotificationId, transactionType);
						case Constants.REFUND_MONEY:
							 return getRefundMoneyResponse(id, customerNotificationId, transactionType);
						case Constants.MERCHANT_MAPPING_REQUSET:
							 return getMerchantMappingReq(id, cId, mId,transactionType);
						case Constants.CUSTOMER_MAPPING_REQUSET:
							 return getCustomerMappingReq(id, cId, mId,transactionType);
						default:
							return "";
			        	}
		        }
		        return Constants.FAILURE;
	    }

		private String getCollectMoneyResponse(int id, int customerNotificationId, String transactionType) {
				CustomerNotificationDTO customerNotification = customerTransactionService.getCustomerNotifcation(customerNotificationId);
			    CustomerTransactionDTO customerTransaction = getCustomerTransactionObj(customerNotification);
				if(transactionType.equals(Constants.PAY)){
				   customerTransaction.setTransactionType(Constants.COLLECT_MONEY);
				   customerTransaction = customerTransactionService.getCustomerTransactionObj(customerTransaction);
				   MerchantMappingDTO merchantMapping = customerTransactionService.getCustomerWalletByMerchant(customerTransaction.getcId(), customerTransaction.getmId());
				       if(merchantMapping.getWalletBal() >=  customerTransaction.getWalletBal()){
						     int isPay = customerTransactionService.payBills(getMerchantTransactionObj(customerTransaction), customerTransaction);
						     if(isPay == 1){
						    	 customerTransactionService.updateCollectMoneyStatus(customerNotificationId,customerTransaction.getTransactionType(), customerTransaction.getCreatedBy());
						    	 pushNotificationLogService.updatePushNotificationLog(1,1, id);
						    	 return Constants.APPROVE_REQUEST_TYPE;
						     }
						     return Constants.FAILURE;
					   }
				       return Constants.INSUFFICIENT_FUNDS;
			    }else {
				    customerTransactionService.updateCollectMoneyStatus(customerNotificationId,customerTransaction.getTransactionType(),customerTransaction.getCreatedBy());
				    pushNotificationLogService.updatePushNotificationLog(2,1, id);
				    return Constants.REJECT_REQUEST_TYPE;
			    }
		}
		
		private String getRefundMoneyResponse(int id, int customerNotificationId, String transactionType) {
				CustomerNotificationDTO customerNotification = customerTransactionService.getCustomerNotifcation(customerNotificationId);
			    CustomerTransactionDTO customerTransaction = getCustomerTransactionObj(customerNotification);
				if(customerTransaction.getTransactionType().equals(Constants.REFUND)){
				   customerTransaction = customerTransactionService.getCustomerTransactionObj(customerTransaction);
				   MerchantMappingDTO merchantMapping = customerTransactionService.getCustomerWalletByMerchant(customerTransaction.getcId(), customerTransaction.getmId());
				       if(merchantMapping.getWalletBal() >=  customerTransaction.getWalletBal()){
						     int isPay = customerTransactionService.payBills(getMerchantTransactionObj(customerTransaction), customerTransaction);
						     if(isPay == 1){
						    	 customerTransactionService.updateCollectMoneyStatus(customerNotificationId,customerTransaction.getTransactionType(), customerTransaction.getCreatedBy());
						    	 pushNotificationLogService.updatePushNotificationLog(1,1, id);
						    	 return Constants.APPROVE_REQUEST_TYPE;
						     }
						     return Constants.FAILURE;
					   }
				       return Constants.INSUFFICIENT_FUNDS;
			    }else {
				    customerTransactionService.updateCollectMoneyStatus(customerNotificationId,customerTransaction.getTransactionType(),customerTransaction.getCreatedBy());
				    pushNotificationLogService.updatePushNotificationLog(2,1, id);
				    return Constants.REJECT_REQUEST_TYPE;
			    }
		}
		
		private MerchantTransactionDTO getMerchantTransactionObj(CustomerTransactionDTO customerTransaction){
			   MerchantTransactionDTO merchantTransaction = new MerchantTransactionDTO();
		       BeanUtils.copyProperties(customerTransaction, merchantTransaction);
		       merchantTransaction.setIndicator(Constants.INDICATOR_CREDIT);
		       return merchantTransaction;
	    }

		private CustomerTransactionDTO getCustomerTransactionObj(CustomerNotificationDTO customerNotification) {
			    CustomerTransactionDTO customerTransactionDTO = new CustomerTransactionDTO();
			    customerTransactionDTO.setmId(customerNotification.getmId());
			    customerTransactionDTO.setcId(customerNotification.getcId());
			    customerTransactionDTO.setWalletBal(customerNotification.getAmount());
			    if(customerNotification.getTransactionType().equals(Constants.REFUND)) {
			    	customerTransactionDTO.setTransactionType(Constants.REFUND);
			    }else {
			    	customerTransactionDTO.setTransactionType(Constants.PAY);
			    }
			    return customerTransactionDTO;
		}
		
		private String getMerchantMappingReq(int id, int cId, int mId, String transactionType) {
				if(transactionType.equals(Constants.PAY)) {
			  		int isUpdated = merchantMappingService.updateMerchantMapping(cId, mId);
			  	
		  			if(isUpdated ==1) {
		  				loyaltyCardService.activateLoyaltyCardNumber(mId, cId,1);
		  				pushNotificationLogService.updatePushNotificationLog(1,1, id);
		  				return Constants.APPROVE_REQUEST_TYPE;
		  			}
		  			return Constants.FAILURE;
			     }else if(transactionType.equals(Constants.REJECT)) {
			    	 int isUpdated = merchantMappingService.deleteMerchantMapping(cId, mId);
			  		 if(isUpdated >= 1) {
			  			 pushNotificationLogService.updatePushNotificationLog(2,1, id);
			  			 return Constants.REJECT_REQUEST_TYPE;
			  		 }
			     }
				 return Constants.FAILURE;
		}
		
		private String getCustomerMappingReq(int id, int cId, int mId, String transactionType) {
				if(transactionType.equals(Constants.PAY)) {
			  		int isUpdated = merchantMappingService.updateMerchantMapping(cId, mId);
		  			if(isUpdated ==1) {
		  				pushNotificationLogService.updatePushNotificationLog(1,1, id);
		  				return Constants.APPROVE_REQUEST_TYPE;
		  			}
		  			return Constants.FAILURE;
			     }
				pushNotificationLogService.updatePushNotificationLog(2,1, id);
			     return Constants.REJECT_REQUEST_TYPE;
		}
	    
}
