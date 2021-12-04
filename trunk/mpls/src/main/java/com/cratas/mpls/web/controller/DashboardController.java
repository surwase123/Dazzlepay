package com.cratas.mpls.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.constant.PageConstant;
import com.cratas.mpls.service.IAppPropertiesService;
import com.cratas.mpls.service.ICustomerService;
import com.cratas.mpls.service.ILoyaltyCardService;
import com.cratas.mpls.service.IMerchantMappingService;
import com.cratas.mpls.service.IMerchantService;
import com.cratas.mpls.service.IMerchantTransactionService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.MerchantDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author mukesh
 *
 */
@Controller
@RequestMapping("/merchant/dashboard")
public class DashboardController extends BaseController{
	
	    private final static Logger LOGGER = LoggerFactory.getLogger(DashboardController.class);
	
	    @Autowired
	    private IUtilityService utilityService;
	    
	    @Autowired
		private IMerchantService merchantService;
		
		@Autowired
		private IMerchantMappingService merchantMappingService;
		
		@Autowired
		private IMerchantTransactionService merchantTransService;
		
		@Autowired
		private IAppPropertiesService appProperiesService;
		
		@Autowired
		private ILoyaltyCardService loyaltyCardService;
		
		@Autowired
		private ICustomerService customerService;
	
		@RequestMapping(value = "/view", method = { RequestMethod.GET })
	    public ModelAndView adminDashboard(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes) {
			   ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.DASHBOARD));
		       if (checkUserAlreadyLogin(request, response)) {
		    	   UserDTO user = getSessionUserObject(request);
		    	   String todayDate = utilityService.getCurDateToStr(Constants.SQL_DATE_FORMAT);
		    	   setReqAttribute(request, todayDate, todayDate);
		    	   return view;
		       }
		       return new ModelAndView("redirect:/");
	    }
		
		@RequestMapping(value = "/merchantDashboard", method = { RequestMethod.GET })
	    public ModelAndView merchantDashboard(HttpServletRequest request, HttpServletResponse response, RedirectAttributes attributes) {
			   ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.MERCHANT_DASHBOARD_DATA));
		       if (checkUserAlreadyLogin(request, response)) {
		    	   UserDTO user = getSessionUserObject(request);
		    	   String todayDate = utilityService.getCurDateToStr(Constants.SQL_DATE_FORMAT);
		    	   setMerchantReqAttribute(request, user.getMerchant(), todayDate, todayDate);
		    	   return view;
		       }
		       return new ModelAndView("redirect:/");
	    }
		
		@RequestMapping(value = "/data/{mId}", method = RequestMethod.POST)
		public ModelAndView getMerchantList(@PathVariable("mId") String mId, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, HttpServletRequest request, HttpServletResponse response) {
		       if (checkUserAlreadyLogin(request, response)) {
				      ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.MERCHANT_DASHBOARD_DETAIL));
			    	  if(StringUtils.isNotEmpty(mId)) {	
			    		   int merchantId = utilityService.convertStringToInt(mId) ;
			    		   setReqAttribute(request, merchantId, fromDate, toDate);
						   return view;
			    	  }
			    	  return null;
		       }
		       return new ModelAndView("redirect:/");
		}
		
		@RequestMapping(value = "/data", method = RequestMethod.POST)
		public ModelAndView getAdminDashBoardData(@RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, HttpServletRequest request, HttpServletResponse response) {
		       if (checkUserAlreadyLogin(request, response)) {
				      ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.ADMIN_DASHBOARD_DATA));
			    	  setReqAttribute(request, fromDate, toDate);
					  return view;
		       }
		       return new ModelAndView("redirect:/");
		}
		
		private void setMerchantReqAttribute(HttpServletRequest request, MerchantDTO merchant, String fromDate, String toDate){
			    if(null != merchant){
			    	 setReqAttribute(request, merchant.getId(), fromDate, toDate);
			    }else{
			    	 LOGGER.info("Login From Admin User");
					 List<MerchantDTO> merchantList = merchantService.getAllMerchant();
					 request.setAttribute(Constants.MERCHANT_LIST, merchantList);
					 if(!merchantList.isEmpty()){
						 MerchantDTO merchantDTO = merchantList.get(0);
						 setReqAttribute(request, merchantDTO.getId(), fromDate, toDate);
				     }
			   }
        }
		
		private void setReqAttribute(HttpServletRequest request, String fromDate, String toDate){
				request.setAttribute(Constants.CUSTOMER_ID_PREFIX, appProperiesService.getSystemParamValueByName(Constants.CUSTOMER_ID_PREFIX));
				request.setAttribute(Constants.MERCHANT_ID_PREFIX, appProperiesService.getSystemParamValueByName(Constants.MERCHANT_ID_PREFIX));
				request.setAttribute(Constants.CUSTOMER_LIST, customerService.getAllCustomer());
				 request.setAttribute(Constants.LOYALTY_CARD_STATUS,loyaltyCardService.getAllAvailableLoylatyNumberSuperAdmin());
			    request.setAttribute(Constants.MERCHANT_TRANSACTION_LIST, merchantTransService.getAllMerchantTransaction(getFromDate(fromDate), getToDate(toDate)));
				request.setAttribute(Constants.ADMIN_DASHBOARD_DATA, merchantTransService.getAdminDashboardData(getFromDate(fromDate), getToDate(toDate)));
		}
		
		private void setReqAttribute(HttpServletRequest request, int mId, String fromDate, String toDate){
			    request.setAttribute(Constants.CUSTOMER_ID_PREFIX, appProperiesService.getSystemParamValueByName(Constants.CUSTOMER_ID_PREFIX));
			    request.setAttribute(Constants.MERCHANT_ID_PREFIX, appProperiesService.getSystemParamValueByName(Constants.MERCHANT_ID_PREFIX));
			    request.setAttribute(Constants.CUSTOMER_LIST, merchantMappingService.getCustomerByMerchant(mId));
			    request.setAttribute(Constants.LOYALTY_CARD_STATUS,loyaltyCardService.getAllAvailableLoylatyNumberMerchant(mId));
			    request.setAttribute(Constants.MERCHANT_TRANSACTION_LIST, merchantTransService.getMerchantTransaction(mId, getFromDate(fromDate), getToDate(toDate)));
			    request.setAttribute(Constants.MERCHANT_DASHBOARD_DATA, merchantTransService.getMerchantDashboardData(mId, getFromDate(fromDate), getToDate(toDate)));
		}

}
