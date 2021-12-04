package com.cratas.mpls.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.constant.PageConstant;
import com.cratas.mpls.service.ICustomerService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.CustomerDTO;
import com.cratas.mpls.web.dto.UserDTO;

@Controller
@RequestMapping("/customer/profile")
public class CustomerProfileController extends BaseController{
	
		private final static Logger LOGGER = LoggerFactory.getLogger(CustomerProfileController.class);
		
		@Autowired
		private IUtilityService utilityService;
		
		@Autowired
	    private ICustomerService customerService;
	    	    
	    @RequestMapping(value = "/view", method = RequestMethod.GET)
		public ModelAndView customerProfile(HttpServletRequest request, HttpServletResponse response, Model model) {
			   if(checkUserAlreadyLogin(request, response)){
					ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.CUSTOMER_PROFILE));
					UserDTO userDTO = getSessionUserObject(request);
					setCustomerRequestAttribute(request, userDTO.getCustomer());
					return view;
			   }
			   return new ModelAndView("redirect:/");
		}
	    
	    private void setCustomerRequestAttribute(HttpServletRequest request, CustomerDTO customerDTO){
		     if(null != customerDTO){
		    	 setRequestAttribute(request, customerDTO.getId());
		     }else{
		    	 LOGGER.info("Login From Admin User");
				 List<CustomerDTO> customerList = customerService.getAllCustomer();
				 request.setAttribute(Constants.CUSTOMER_LIST, customerList);
				 if(!customerList.isEmpty()){
					 CustomerDTO customer = customerList.get(0);
					setRequestAttribute(request, customer.getId());
				 }
		     }
	    }
	    
	    private void setRequestAttribute(HttpServletRequest request, int cId){
			 request.setAttribute(Constants.CUSTOMER, customerService.getCustomerByCId(cId));
			 request.setAttribute(Constants.MERCHANT_LIST, customerService.getMerchantByCustomer(cId));
	    }
	    
	    @RequestMapping(value = "/getCustomerProfile/{cId}", method = RequestMethod.POST)
	    public ModelAndView getCustomerList(@PathVariable("cId") String cId, HttpServletRequest request, HttpServletResponse response) {
	    	   ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.CUSTOMER_PROFILE_DETAILS));
	    	   if(StringUtils.isNotEmpty(cId)) {	
	    		   int customerId = utilityService.convertStringToInt(cId) ;
	    		   setRequestAttribute(request, customerId);
				   return view;
	    	   }
	    	   return null;
	    }
	 

}
