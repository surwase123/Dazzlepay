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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.constant.PageConstant;
import com.cratas.mpls.service.IAppPropertiesService;
import com.cratas.mpls.service.ICustomerService;
import com.cratas.mpls.service.ICustomerTransactionService;
import com.cratas.mpls.service.IMerchantMappingService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.CustomerDTO;
import com.cratas.mpls.web.dto.MerchantDTO;
import com.cratas.mpls.web.dto.MerchantMappingDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author sunil
 *
 */
@Controller
@RequestMapping("/customer/transactionHistory")
public class CustomerTransHistoryController extends BaseController {
	
	   private final static Logger LOGGER = LoggerFactory.getLogger(CustomerTransHistoryController.class);
		
	   @Autowired
	   private IUtilityService utilityService;
		
	   @Autowired
	   private IAppPropertiesService appProperiesService;
	   
	   @Autowired
	   private ICustomerService customerService;
		
	   @Autowired
	   private ICustomerTransactionService customerTransactionService;
	   
	   @Autowired
	   private IMerchantMappingService merchantMappingService;
	   
	   @RequestMapping(value = "/view", method = RequestMethod.GET)
	   public ModelAndView RegistrationView(HttpServletRequest request, HttpServletResponse response, Model model) {
		      if (checkUserAlreadyLogin(request, response)) {
				  ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.CUSTOMER_TRANSACTION_HISTORY));
				  UserDTO userDTO = getSessionUserObject(request);
				  String todayDate = utilityService.getCurDateToStr(Constants.SQL_DATE_FORMAT);
				  setCustomerTransReqAttribute(request, userDTO.getCustomer(), todayDate, todayDate);
				  if(userDTO.getGroupId().equals(Constants.CUSTOMER)) {
					  setMerchantTransReqAttribute(request, userDTO.getCustomer(), userDTO.getMerchant(), todayDate, todayDate);
				  }
				  return view;
		      }
		      return new ModelAndView("redirect:/");
	   }
	   
	   @RequestMapping(value = "/data/{cId}", method = RequestMethod.POST)
	   public ModelAndView getCustomerList(@PathVariable("cId") String cId, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, HttpServletRequest request, HttpServletResponse response) {
		      if (checkUserAlreadyLogin(request, response)) {
			      ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.CUSTOMER_TRANSACTION_DATA));
		    	  if(StringUtils.isNotEmpty(cId)) {	
		    		   int customerId = utilityService.convertStringToInt(cId) ;
		    		   setRequestAttribute(request, customerId, fromDate, toDate);
					   return view;
		    	  }
		    	  return null;
		      }
		      return new ModelAndView("redirect:/");
	    }
	   
	   @RequestMapping(value = "/tranHistoryBymId", method = RequestMethod.POST)
	   public ModelAndView getCustomerTranList(@RequestParam("cId") String cId, @RequestParam("mId") String mId, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, HttpServletRequest request, HttpServletResponse response) {
		      if (checkUserAlreadyLogin(request, response)) {
			      ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.CUSTOMER_TRANSACTION_DATA));
		    	  if(StringUtils.isNotEmpty(cId) && StringUtils.isNotEmpty(mId)) {	
		    		   int customerId = utilityService.convertStringToInt(cId) ;
		    		   int merchantId = utilityService.convertStringToInt(mId) ;
		    		   setMerchantRequestAttribute(request, customerId, merchantId, fromDate, toDate);
					   return view;
		    	  }
		    	  return null;
		      }
		      return new ModelAndView("redirect:/");
	    }
	   
	    private void setCustomerTransReqAttribute(HttpServletRequest request, CustomerDTO customerDTO, String fromDate, String toDate){
			   if(null != customerDTO){
			    	 setRequestAttribute(request, customerDTO.getId(), fromDate, toDate);
			   }else{
			    	 LOGGER.info("Login From Admin User");
					 List<CustomerDTO> customerList = customerService.getAllCustomer();
					 request.setAttribute(Constants.CUSTOMER_LIST, customerList);
					 if(!customerList.isEmpty()){
						 CustomerDTO customer = customerList.get(0);
						 setRequestAttribute(request, customer.getId(), fromDate, toDate);
					 }
			   }
	    }
	   
	    private void setRequestAttribute(HttpServletRequest request, int cId, String fromDate, String toDate){
		        request.setAttribute(Constants.CUSTOMER_ID_PREFIX, appProperiesService.getSystemParamValueByName(Constants.CUSTOMER_ID_PREFIX));
			    request.setAttribute(Constants.MERCHANT_ID_PREFIX, appProperiesService.getSystemParamValueByName(Constants.MERCHANT_ID_PREFIX));
			    request.setAttribute(Constants.CUSTOMER_TRANSACTION_LIST, customerTransactionService.getCustomerTransaction(cId, getFromDate(fromDate), getToDate(toDate)));
	    }
	    
	    private void setMerchantTransReqAttribute(HttpServletRequest request, CustomerDTO customerDTO, MerchantDTO merchantDTO, String fromDate, String toDate) {
	        	if(null != customerDTO && null != merchantDTO){
			    	 setRequestAttribute(request, customerDTO.getId(), fromDate, toDate);
			    }else{
			    	 LOGGER.info("Login From Admin User");
					 List<CustomerDTO> customerList = customerService.getAllCustomer();
					 List<MerchantMappingDTO> merchantList = merchantMappingService.getMerchantMappingByCustomer(customerDTO.getId());
					 request.setAttribute(Constants.MERCHANT_LIST, merchantList);
					 if(!merchantList.isEmpty() && !customerList.isEmpty()){
						 CustomerDTO customer = customerList.get(0);
						 MerchantMappingDTO merchant = merchantList.get(0);
						 setMerchantRequestAttribute(request, customer.getId(), merchant.getmId(), fromDate, toDate); 
					 }
			    }
	    }
	    
	    private void setMerchantRequestAttribute(HttpServletRequest request, int cId, int mId, String fromDate, String toDate) {
    		request.setAttribute(Constants.CUSTOMER_ID_PREFIX, appProperiesService.getSystemParamValueByName(Constants.CUSTOMER_ID_PREFIX));
    		request.setAttribute(Constants.MERCHANT_ID_PREFIX, appProperiesService.getSystemParamValueByName(Constants.MERCHANT_ID_PREFIX));
    		request.setAttribute(Constants.CUSTOMER_TRANSACTION_LIST, customerTransactionService.getCustomerTransactionByMId(cId, mId, getFromDate(fromDate), getToDate(toDate)));
	    }

}
