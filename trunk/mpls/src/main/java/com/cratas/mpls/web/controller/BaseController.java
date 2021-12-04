package com.cratas.mpls.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.constant.PageConstant;
import com.cratas.mpls.common.utility.GroupType;
import com.cratas.mpls.common.utility.constant.InformationMsgConstants;
import com.cratas.mpls.common.utility.constant.RedirectScreenConstants;
import com.cratas.mpls.service.IAESEncryptionService;
import com.cratas.mpls.service.IAppPropertiesService;
import com.cratas.mpls.service.IAreaService;
import com.cratas.mpls.service.IBCryptService;
import com.cratas.mpls.service.ICategoryService;
import com.cratas.mpls.service.ICityService;
import com.cratas.mpls.service.ICountryService;
import com.cratas.mpls.service.ICurrencyService;
import com.cratas.mpls.service.ICustomerTransactionService;
import com.cratas.mpls.service.IMenuService;
import com.cratas.mpls.service.IMerchantService;
import com.cratas.mpls.service.IStateService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.CountryDTO;
import com.cratas.mpls.web.dto.MenuDTO;
import com.cratas.mpls.web.dto.MerchantDTO;
import com.cratas.mpls.web.dto.MerchantEmployeeDTO;
import com.cratas.mpls.web.dto.SubMenuDTO;
import com.cratas.mpls.web.dto.UserDTO;
import com.cratas.mpls.web.dto.UserRoleDTO;

/**
 * 
 * @author mukesh
 *
 */
@Controller
public class BaseController {

	    private final static Logger LOGGER = LoggerFactory.getLogger(BaseController.class);
	
	    @Autowired
	    private IUtilityService utilityService;
	     
	    @Autowired
	    IMerchantService merchantService;
	
	    @Autowired
	    private IAESEncryptionService aesService;
	
	    @Autowired
	    private IMenuService menuService;
	
	    @Autowired
	    private IBCryptService bCryptService;

	    @Autowired
	    private IStateService stateService;
	    
	    @Autowired
	    private ICityService cityService;
	    
	    @Autowired
	    private IAreaService areaService;
	    
	    @Autowired
	    private ICurrencyService currencyService;
	    
	    @Autowired
	    private ICountryService countryService;
	    
	    @Autowired
	    private ICustomerTransactionService customerTransactionService;
	    
	    @Autowired
	    private IAppPropertiesService appPropertiesService;
	    
	    @Autowired
	    private ICategoryService categoryService;
	
	    @RequestMapping(value = "/", method = RequestMethod.GET)
	    public ModelAndView welcome(HttpServletRequest request, HttpServletResponse response, Model model) {
		       ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.LOGIN));
		       if (checkUserAlreadyLogin(request, response)) {
		    	    UserDTO user = getSessionUserObject(request);
		            return new ModelAndView(redirectScreenName(user));
		       }
		       Map<String, Object> requestMap = ((Model) model).asMap();
		       if (null != requestMap) {
		            request.setAttribute(Constants.MESSAGE, requestMap.get(Constants.MESSAGE));
		       }
		       request.setAttribute(Constants.AES.toLowerCase(), aesService.getAesDTO());
		       return view;
	    }
	
	    @RequestMapping(value = "/dashboard", method = { RequestMethod.GET })
	    public ModelAndView dashboard(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes) {
		       if (checkUserAlreadyLogin(request, response)) {
		    	   UserDTO user = getSessionUserObject(request);
		    	   if(GroupType.CUSTOMER.getGroupType().equals(user.getGroupDTO().getGroupType())){
					   setRequestAttribute(request, user);
			    	   ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.CUSTOMER_DASHBOARD));
			           return view;
		    	   }else if(GroupType.MERCHANT.getGroupType().equals(user.getGroupDTO().getGroupType())){
		    		   return new ModelAndView(RedirectScreenConstants.REDIRECT_MERCHANT_DASHBOARD);
		    	   }else if(GroupType.ADMIN.getGroupType().equals(user.getGroupDTO().getGroupType())){
		    		   return new ModelAndView(RedirectScreenConstants.REDIRECT_ADMIN_DASHBOARD);
		    	   }else{
		    		   return new ModelAndView(RedirectScreenConstants.REDIRECT_USER_PROFILE);
		    	   }
		       }
		       attributes.addFlashAttribute("message", "Session has Expired. Please Login the Application");
		       return new ModelAndView("redirect:/");
	    }
	
	    protected boolean checkUserAlreadyLogin(HttpServletRequest request, HttpServletResponse response) {
		          String isLoggedOn = (String) request.getSession().getAttribute(Constants.IS_LOGGED_ON);
		          if (StringUtils.isNotEmpty(isLoggedOn) && isLoggedOn.equalsIgnoreCase(Constants.STR_TRUE)) {
			            UserDTO userDTO = (UserDTO) request.getSession().getAttribute(Constants.LOGIN_DTO);
			            if (null != userDTO) {
			                request.setAttribute(Constants.PAGE_ROLE,
			                        getPageRole(userDTO.getUserRoleList(), request.getRequestURI()));
			            }
			            return true;
		          }
		          LOGGER.info("Session Expired !!");
		          return false;
	    }
	
	    protected String getEncryptedLoginId(HttpServletRequest request) {
	              UserDTO userDTO = (UserDTO) request.getSession().getAttribute(Constants.LOGIN_DTO);
		          if (null != userDTO) {
		               return bCryptService.getEncryptedString(userDTO.getLoginId());
		          }
		          return Constants.BLANK;
	    }

		
	    protected String redirectScreenName(UserDTO user){
				 if(GroupType.CUSTOMER.getGroupType().equals(user.getGroupDTO().getGroupType())){
			    	  return RedirectScreenConstants.REDIRECT_CUSTOMER_DASHBOARD;
			     }else if(GroupType.MERCHANT.getGroupType().equals(user.getGroupDTO().getGroupType())){
			    	 if(user.getIsUpdateDetail() == 0) {
			    		  return RedirectScreenConstants.REDIRECT_UPDATE_MERCHANT_DETAIL; 
			    	  }else {
			    		  return RedirectScreenConstants.REDIRECT_MERCHANT_DASHBOARD;
			    	  }
			     }else if(GroupType.ADMIN.getGroupType().equals(user.getGroupDTO().getGroupType())){
			    	  return RedirectScreenConstants.REDIRECT_ADMIN_DASHBOARD;
			     }
			      return RedirectScreenConstants.REDIRECT_USER_PROFILE;
		}
	
	    protected UserDTO getSessionUserObject(HttpServletRequest request) {
		          UserDTO userDTO = (UserDTO) request.getSession().getAttribute(Constants.LOGIN_DTO);
		          if (null != userDTO) {
		               return userDTO;
		          }
		          return null;
	    }
	
	    protected UserRoleDTO getPageRole(List<UserRoleDTO> userRoleList, String uri) {
		          for (UserRoleDTO userRoleDTO : userRoleList) {
			            if (userRoleDTO.getSubMenuId() == 0) {
			                MenuDTO menuDTO = menuService.getMenu(userRoleDTO.getMenuId());
			                if (null != menuDTO && StringUtils.isNotEmpty(menuDTO.getAction()) && uri.contains(menuDTO.getAction())) {
			                    return userRoleDTO;
			                }
			            }else {
			                SubMenuDTO subMenuDTO = menuService.getSubMenu(userRoleDTO.getSubMenuId());
			                if (null != subMenuDTO && uri.contains(subMenuDTO.getAction())) {
			                    return userRoleDTO;
			                }
			            }
		         }
		         return null;
	    }
	
	    @RequestMapping(value = "/generatePassword", method = RequestMethod.GET)
	    public @ResponseBody String generatePassword(HttpServletRequest request, HttpServletResponse response) {
	           return bCryptService.getEncryptedString(request.getParameter("password"));
	    }
	
	    @RequestMapping(value = "/matchPassword", method = RequestMethod.GET)
	    public @ResponseBody String matchPassword(HttpServletRequest request, HttpServletResponse response) {
		       String isMatch = String.valueOf(bCryptService.isMatch(request.getParameter("encode"), request.getParameter("password")));
		       return isMatch;
	    }
	    
	    @RequestMapping(value = "/unAuthorizedUser", method = {RequestMethod.GET})
		public ModelAndView unAuthorizedUser(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes) {
			   ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.UN_AUTHORIZED_USER));
			   request.setAttribute(Constants.MESSAGE, InformationMsgConstants.UN_AUTHORIZED_USER_MSG);
			   if(checkUserAlreadyLogin(request, response)){
			       return view;
			   }
			   attributes.addFlashAttribute("message", "Session has Expired. Please Re-Login the Application");
			   return new ModelAndView("redirect:/");
		}
	    
	    protected void setMerchantRequestAttribute(HttpServletRequest request, Map<String, Object> requestMap){
		    	  request.setAttribute(Constants.MERCHANT_LIST, merchantService.getAllMerchant());
	              request.setAttribute(Constants.COUNTRY_LIST, countryService.getCountry());
	              request.setAttribute(Constants.CATEGORY_LIST, categoryService.getCategory());
	              request.setAttribute(Constants.STATE_LIST, stateService.getStates());
	              request.setAttribute(Constants.AES.toLowerCase(), aesService.getAesDTO());
	    	      if(requestMap != null) {
	    	    	  setMerchantUpdateAttribute(request, requestMap);
	    	      }
        }
	    
	    private void setMerchantUpdateAttribute(HttpServletRequest request, Map<String, Object> requestMap) {
		    	String action = (String) requestMap.get(Constants.ACTION);
	            String isSuccess = (String) requestMap.get(Constants.IS_SUCCESS);
				MerchantDTO merchant = (MerchantDTO) requestMap.get(Constants.MERCHANT);
				if(null != merchant && (StringUtils.isNotEmpty(isSuccess) && isSuccess.equals(Constants.FAILURE) || 
						StringUtils.isNotEmpty(action) && action.equals(Constants.UPDATE))){
					CountryDTO country = countryService.getCountryById(merchant.getCountryId());
					request.setAttribute(Constants.STATE_LIST, stateService.getStates());
				    request.setAttribute(Constants.CITY_LIST, cityService.getCity());
					request.setAttribute(Constants.CURRENCY_LIST, country != null ? currencyService.getCurrencyList(country.getCountryName()) : null);
					request.setAttribute(Constants.AREA_LIST, areaService.getArea());
				}
	    }
	    
	    protected void setMerchantLoginErrMsg(MerchantEmployeeDTO merchantEmployee, HttpServletRequest request){
			      if(merchantEmployee == null){
				    	request.setAttribute(Constants.MESSAGE, "Please Login as Merchant");
				    	request.setAttribute(Constants.IS_SUCCESS, Constants.FAILURE);
				    	request.setAttribute(Constants.IS_PRIVILEGES, Constants.FALSE);
			      }
	    }
	    
	    protected ModelAndView redirectUpdateMerchantScreen(int isUpdate, RedirectAttributes redirectAttribute, MerchantDTO merchant, String redirectScreenName){
		    	  if(isUpdate == 1){
					   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Merchant Updated Successfully.");
					   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
					   return new ModelAndView(redirectScreenName);
				   }else if(isUpdate == 2){
					   redirectAttribute.addFlashAttribute(Constants.MERCHANT, merchant);
					   redirectAttribute.addFlashAttribute(Constants.MERCHANT_ADDRESS_LIST, merchant.getmAddressList());
					   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Merchant Details Already Exists(Merchant Name, PanNumber, GSTIN, EmailId & Mobile Number must be unique.");
					   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
					   redirectAttribute.addFlashAttribute(Constants.ACTION, Constants.UPDATE);
					   return new ModelAndView(redirectScreenName);
				   }else if(isUpdate == 3){
					   redirectAttribute.addFlashAttribute(Constants.MERCHANT, merchant);
					   redirectAttribute.addFlashAttribute(Constants.MERCHANT_ADDRESS_LIST, merchant.getmAddressList());
					   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Merchant Mobile Number already Exists.");
					   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
					   redirectAttribute.addFlashAttribute(Constants.ACTION, Constants.UPDATE);
					   return new ModelAndView(redirectScreenName);
				   }
		    	   return new ModelAndView(redirectScreenName);
	   }
	    
	   private void setRequestAttribute(HttpServletRequest request,UserDTO user) {
	    	   request.setAttribute(Constants.MERCHANT_LIST,merchantService.customerMerchantMappingList(user.getId()));
	    	   request.setAttribute(Constants.MERCHANT_ID_PREFIX, appPropertiesService.getSystemParamValueByName(Constants.MERCHANT_ID_PREFIX));
	    	   request.setAttribute(Constants.CUSTOMER_TRANSACTION_LIST, customerTransactionService.getTopCustomerTransaction(user.getId()));
	   }
	   
	   protected String getFromDate(String date){
		       if(StringUtils.isNotEmpty(date)){
		    	   date = date + Constants.ZERO_TIMESTAMP;
		       }
			   return date;
       }
   
	   protected String getToDate(String date){
		       if(StringUtils.isNotEmpty(date)){
		    	   date = date + Constants.TWENTY_THREE_TIMESTAMP;
		       }
			   return date;
	   }

}
