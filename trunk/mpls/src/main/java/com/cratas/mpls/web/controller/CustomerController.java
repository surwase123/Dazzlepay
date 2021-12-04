package com.cratas.mpls.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.cratas.mpls.service.ICustomerService;
import com.cratas.mpls.service.ILoyaltyCardService;
import com.cratas.mpls.service.IMerchantMappingService;
import com.cratas.mpls.service.IPushNotificationService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.service.IVerificationService;
import com.cratas.mpls.web.dto.CustomerDTO;
import com.cratas.mpls.web.dto.MerchantEmployeeDTO;
import com.cratas.mpls.web.dto.MerchantMappingDTO;
import com.cratas.mpls.web.dto.RequestLoyaltyCardDTO;
import com.cratas.mpls.web.dto.UserDTO;
import com.cratas.mpls.web.dto.VerificationLogDTO;

@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController {
		
		private final static Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
		
		@Autowired
		private IUtilityService utilityService;
		
		@Autowired
		private IAppPropertiesService appProperiesService;
		
		@Autowired
		private IPushNotificationService pushNotificationService;
		
		@Autowired
		private ICustomerService customerService;
		
		@Autowired
		private IMerchantMappingService merchantMappingService;
		
		@Autowired
		private ILoyaltyCardService loyaltyCardService;
		
		@Autowired
		private IAESEncryptionService aesService;
		
		@Autowired
	    private IVerificationService verificationService;
	    
		@RequestMapping(value = "/register", method = RequestMethod.GET)
		public ModelAndView RegistrationView(HttpServletRequest request, HttpServletResponse response, Model model) {
			   ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.CUSTOMER_REG));
			   request.setAttribute(Constants.AES.toLowerCase(), aesService.getAesDTO());
			   Map<String, Object> requestMap = ((Model) model).asMap();
			   if (null != requestMap) {
					request.setAttribute(Constants.MESSAGE, requestMap.get(Constants.MESSAGE));
					request.setAttribute(Constants.IS_SUCCESS, requestMap.get(Constants.IS_SUCCESS));
					request.setAttribute(Constants.ACTION, requestMap.get(Constants.ACTION));
			   }
			   return view;
		}
		
		@RequestMapping(value = "/add", method = RequestMethod.POST)
		public ModelAndView add(@ModelAttribute("customerDTO") CustomerDTO customer, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
	    	   try{
	    		   if(isVerifiedMobile(customer, request)){
					   customer = customerService.generateCustomerId(customer);
					   int isInsert = customerService.saveCustomer(customer, null,"Customer");
					   return redirectRegisterScreen(isInsert, redirectAttribute, customer, RedirectScreenConstants.REDIRECT_CUSTOMER_REGISTER);
	    		   }
			   }catch(Exception e){
					   LOGGER.error("Error in save Customer -->  "+e);
			   }
			   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Add Customer.");
			   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
			   return new ModelAndView(RedirectScreenConstants.REDIRECT_CUSTOMER_REGISTER);
       }

	   @RequestMapping(value = "/view", method = RequestMethod.GET)
	   public ModelAndView RegAdminView(HttpServletRequest request, HttpServletResponse response, Model model) {
			  if (checkUserAlreadyLogin(request, response)) {
					ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.CUSTOMER_REG_ADMIN));
					request.setAttribute(Constants.CUSTOMER_ID_PREFIX, appProperiesService.getSystemParamValueByName(Constants.CUSTOMER_ID_PREFIX));
					UserDTO userDTO = getSessionUserObject(request);
					MerchantEmployeeDTO merchantEmployee = userDTO.getMerchantEmployee();
					request.setAttribute(Constants.AES.toLowerCase(), aesService.getAesDTO());
					if(null != merchantEmployee){
						request.setAttribute(Constants.CUSTOMER_LIST, merchantMappingService.getCustomerByMerchant(merchantEmployee.getmId()));
					}
					Map<String, Object> requestMap = ((Model) model).asMap();
					if (null != requestMap) {
						request.setAttribute(Constants.MESSAGE, requestMap.get(Constants.MESSAGE));
						request.setAttribute(Constants.IS_SUCCESS, requestMap.get(Constants.IS_SUCCESS));
						request.setAttribute(Constants.ACTION, requestMap.get(Constants.ACTION));
					}
					setMerchantLoginErrMsg(merchantEmployee, request);
					return view;
			   }
			   return new ModelAndView("redirect:/");
		}
		
		@RequestMapping(value = "/admin/add", method = RequestMethod.POST)
		public ModelAndView customerAdd(@ModelAttribute("customerDTO") CustomerDTO customer, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if (checkUserAlreadyLogin(request, response)) {
				    customer = customerService.generateCustomerId(customer);
					UserDTO userDTO = getSessionUserObject(request);	
					int isInsert = customerService.saveCustomer(customer, userDTO,"Merchant");
					if(isInsert == 1){
						CustomerDTO custObj = customerService.getCustomerByMN(customer.getMobileNumber());
						return redirectMerchantMapping(request, custObj, redirectAttribute);
					}else if(isInsert == 2) {
						CustomerDTO custObj = customerService.getCustomerByMN(customer.getMobileNumber());
						return redirectMerchantMapping(request, custObj, redirectAttribute);
					}
					return redirectRegisterScreen(isInsert, redirectAttribute, customer, RedirectScreenConstants.REDIRECT_CUSTOMER_VIEW);
				}
				redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Add Customer.");
				redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE);
				return new ModelAndView(RedirectScreenConstants.REDIRECT_CUSTOMER_VIEW);
		}
	@RequestMapping(value = "/assignLoayltyNumber", method = RequestMethod.POST)
	public @ResponseBody String assignLoyaltyCardNumber(@RequestParam("id") int cId, @RequestParam("mId") int mId,@RequestParam("loyaltyCardNumber") String loyaltyNumber, RedirectAttributes redirectAttribute,HttpServletRequest request, HttpServletResponse response) {
			   if (checkUserAlreadyLogin(request, response)) {
				   int status=loyaltyCardService.validateLoyaltyNumber(mId,cId,loyaltyNumber , "CA", 1);
					if(status==0) 
					{
						loyaltyCardService.updateLoyaltyCardNumberByMid(mId, cId, loyaltyNumber , "CA", 1);
						RequestLoyaltyCardDTO notificationDto=new RequestLoyaltyCardDTO();
						notificationDto.setLoyaltyCardNumber(loyaltyNumber);
						notificationDto.setcId(cId);
						notificationDto.setmId(mId);
						pushNotificationService.sendLoyaltyCardAllocationNotification(notificationDto,Constants.ALLOCATE_LOYALTY_CARD_NOTIFICATION);
					return Constants.ASSIGNED_SUCCESS;
					}
			 else if (status == 1) {
				return Constants.NUMBER_ALREADY_ASSIGNED;
			} else {
				return Constants.INVALID_LOYALTY_NUMBER;
			}
		}
		return "Error in LoyaltyCardNumber Assignment.";
	}
		@RequestMapping(value = "/update-profile-view", method = RequestMethod.GET)
		public ModelAndView updateProfileView(HttpServletRequest request, HttpServletResponse response, Model model) {
			   if(checkUserAlreadyLogin(request, response)) {
				    ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.UPDATE_CUSTOMER_PROFILE));				    
				    Map<String, Object> requestMap = ((Model) model).asMap();
					CustomerDTO customer = (CustomerDTO) requestMap.get(Constants.CUSTOMER);
					if(null != customer){
					    if (null != requestMap) {
							request.setAttribute(Constants.MESSAGE, requestMap.get(Constants.MESSAGE));
							request.setAttribute(Constants.IS_SUCCESS, requestMap.get(Constants.IS_SUCCESS));
							request.setAttribute(Constants.ACTION, requestMap.get(Constants.ACTION));
					    }
						return view;
					}
			   }
			   return new ModelAndView("redirect:/");
		}
		
		@RequestMapping(value = "/update-profile", method = RequestMethod.POST)
		public ModelAndView updateProfile(@ModelAttribute("userId") String userId, @ModelAttribute("groupType") String groupType, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response, Model model) {
			   if(checkUserAlreadyLogin(request, response)) {
					if (StringUtils.isNotEmpty(userId) && StringUtils.isNotEmpty(groupType)) {
						if(GroupType.CUSTOMER.getGroupType().equals(groupType)) {
							CustomerDTO customer = customerService.getCustomerById(utilityService.convertStringToInt(userId));
							redirectAttribute.addFlashAttribute(Constants.CUSTOMER, customer);
						}
					}
					return new ModelAndView(RedirectScreenConstants.REDIRECT_CUSTOMER_PROFILE_U_VIEW);
			   }
			   return new ModelAndView("redirect:/");
		}
		
		@RequestMapping(value = "/edit", method = RequestMethod.POST)
		public ModelAndView edit(@ModelAttribute("customer") CustomerDTO customer, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)) {
				    int isUpdate = customerService.updateCustomer(customer);
					if (isUpdate == 2) {
						updateUserSessionDetail(request,customer);
						redirectAttribute.addFlashAttribute(Constants.CUSTOMER, customer);
						redirectAttribute.addFlashAttribute(Constants.IS_CUSTOMER, Constants.STR_TRUE);
						redirectAttribute.addFlashAttribute(Constants.MESSAGE, "User Detail updated Successfully.");
						redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS);
						return new ModelAndView(RedirectScreenConstants.REDIRECT_CUSTOMER_PROFILE_U_VIEW);
					}
			    }
			    redirectAttribute.addFlashAttribute(Constants.CUSTOMER, customer);
				redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Update User Profile.");
				redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE);
				return new ModelAndView(RedirectScreenConstants.REDIRECT_CUSTOMER_PROFILE_U_VIEW);
		}
		
	    private ModelAndView redirectRegisterScreen(int isInsert, RedirectAttributes redirectAttribute, CustomerDTO customer, String redirectScreen){
				switch(isInsert){
				     case 1:
					   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Registration is Successful. Please Check Your Mail for Login Details.");
					   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
					   return new ModelAndView(redirectScreen);
				     case 2:
					   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Customer Already Exists.");
					   redirectAttribute.addFlashAttribute(Constants.CUSTOMER, customer);
					   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
					   return new ModelAndView(redirectScreen);
				     case 3:
					   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Customer User Login info.");
					   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
					   redirectAttribute.addFlashAttribute(Constants.CUSTOMER, customer);
					   return new ModelAndView(redirectScreen);
				     case 4:
					   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "User Regsitered as a Merchant.");
					   redirectAttribute.addFlashAttribute(Constants.CUSTOMER, customer);
					   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
					   return new ModelAndView(redirectScreen);
				     case 5:
						   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Login Id Already Exists.");
						   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
						   redirectAttribute.addFlashAttribute(Constants.CUSTOMER, customer);
						   return new ModelAndView(redirectScreen);
				}
				return new ModelAndView(redirectScreen);
	     }
	    
	     private ModelAndView redirectMerchantMapping(HttpServletRequest request, CustomerDTO customer, RedirectAttributes redirectAttribute){
		    	 int isAdded = saveMerchantMapping(request, customer);
				 if(isAdded == 1){
						redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Customer mapping request send successfully");
						redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
						return new ModelAndView(RedirectScreenConstants.REDIRECT_CUSTOMER_VIEW);
				}else if(isAdded == 2){
						redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Customer mapping request is already send");
						redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
						return new ModelAndView(RedirectScreenConstants.REDIRECT_CUSTOMER_VIEW);
				}
				else if(isAdded == 3){
					redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Customer is already mapped");
					redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
					return new ModelAndView(RedirectScreenConstants.REDIRECT_CUSTOMER_VIEW);
				}
				return new ModelAndView(RedirectScreenConstants.REDIRECT_CUSTOMER_VIEW);
	     }
		
		
		 private UserDTO updateUserSessionDetail(HttpServletRequest request, CustomerDTO customer) {
			     UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.LOGIN_DTO);
			     if(null != user) {
				    	user.setFirstName(customer.getFirstName());
				    	user.setMiddleName(customer.getMiddleName());
				    	user.setLastName(customer.getLastName());
				    	user.setEmailId(customer.getEmailId());
				    	user.setLoginId(customer.getLoginId());
				    	user.getCustomer().setMobileNumber(customer.getMobileNumber());
			     }
			     return user;
		 }
		 
		 private int saveMerchantMapping(HttpServletRequest request, CustomerDTO customer){
			     UserDTO user = (UserDTO) request.getSession().getAttribute(Constants.LOGIN_DTO);
			     if(null != user && null != user.getMerchant() && null != customer){
			    	 customer.setLoyaltyCardNumber(request.getParameter("loyaltyCardNumber")); 
			    	   MerchantMappingDTO merchnatMapping = merchantMappingService.getMerchantMappingInstance(customer, user.getMerchant(), user.getLoginId());
			    	   return merchantMappingService.saveMerchantMapping(merchnatMapping, Constants.CUSTOMER);
			     }
			     return 0;
		 }
		 
		 private boolean isVerifiedMobile(CustomerDTO customer, HttpServletRequest request){
			   String salt = utilityService.generateSalt(null, request);
	    	   String iv = utilityService.generateIV(null, request);
	    	   String verifiedId = aesService.decrypt(salt, iv, iv, customer.getVerifiedId());
	    	   VerificationLogDTO verification = verificationService.getVerificationLog(utilityService.convertStringToInt(verifiedId));
	    	   if(null != verification && verification.getIsVerified() == 1){
	    		    return true;
	    	   }
	    	   return false;
	     }
		 
		 @RequestMapping(value = "/byMobileNumber", method = RequestMethod.POST)
		 public ModelAndView getTransByTransId(@RequestParam("mobileNumber") String mobileNumber, HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttribute) { 
				if (checkUserAlreadyLogin(request, response)) {
					ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.ADD_CUSTOMER));
					  if(StringUtils.isNotEmpty(mobileNumber)){
						  request.setAttribute(Constants.CUSTOMER, customerService.getCustomerByMN(mobileNumber));
					  }
					  return view;
				}
				redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE);
				return new ModelAndView(RedirectScreenConstants.REDIRECT_CUSTOMER_VIEW);
		 }
}
