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
@RequestMapping("/merchant/transactionHistory")
public class MerchantTransHistoryController extends BaseController {
	
	   private final static Logger LOGGER = LoggerFactory.getLogger(MerchantTransHistoryController.class);
		
	   @Autowired
	   private IUtilityService utilityService;
		
	   @Autowired
	   private IAppPropertiesService appProperiesService;
		
	   @Autowired
	   private IMerchantTransactionService merchantTransService;
	   
	   @RequestMapping(value = "/view", method = RequestMethod.GET)
	   public ModelAndView RegistrationView(HttpServletRequest request, HttpServletResponse response, Model model) {
		      if (checkUserAlreadyLogin(request, response)) {
				  ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.MERCHANT_TRANSACTION_HISTORY));
				  UserDTO userDTO = getSessionUserObject(request);
				  String todayDate = utilityService.getCurDateToStr(Constants.SQL_DATE_FORMAT);
				  setMerchantTransReqAttribute(request, userDTO.getMerchant(), todayDate, todayDate);
				  return view;
		      }
		      return new ModelAndView("redirect:/");
	   }
	   
	   @RequestMapping(value = "/data/{mId}", method = RequestMethod.POST)
	   public ModelAndView getMerchantList(@PathVariable("mId") String mId, @RequestParam("fromDate") String fromDate, @RequestParam("toDate") String toDate, HttpServletRequest request, HttpServletResponse response) {
		      if (checkUserAlreadyLogin(request, response)) {
			      ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.MERCHANT_TRANSACTION_DATA));
		    	  if(StringUtils.isNotEmpty(mId)) {	
		    		   int merchantId = utilityService.convertStringToInt(mId) ;
		    		   setRequestAttribute(request, merchantId, fromDate, toDate);
					   return view;
		    	  }
		    	  return null;
		      }
		      return new ModelAndView("redirect:/");
	   }
	   
	   @RequestMapping(value = "/detail/{id}/{transactionId}", method = RequestMethod.GET)
	   public ModelAndView transactionDetail(@PathVariable("id") int id, @PathVariable("transactionId") String transactionId, HttpServletRequest request, HttpServletResponse response) {
			  if(checkUserAlreadyLogin(request, response)) {
				   ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.MERCHANT_TRANSACTION_DETAIL)); 
				   if(StringUtils.isNotEmpty(transactionId)) {
					   request.setAttribute(Constants.TRANSACTION_DETAIL, merchantTransService.getMerchantTransDetailById(id));
					   request.setAttribute(Constants.CUSTOMER_ID_PREFIX, appProperiesService.getSystemParamValueByName(Constants.CUSTOMER_ID_PREFIX));
					   request.setAttribute(Constants.MERCHANT_ID_PREFIX, appProperiesService.getSystemParamValueByName(Constants.MERCHANT_ID_PREFIX));
					   request.setAttribute(Constants.TRANSACTION_ID, transactionId);
					   request.setAttribute(Constants.TRANS_TABLE_ID, id);
				   }
				   return view;
			  }
			  return new ModelAndView("redirect:/");
	   }
	   
	   private void setMerchantTransReqAttribute(HttpServletRequest request, MerchantDTO merchantDTO, String fromDate, String toDate){
			   if(null != merchantDTO){
			    	 setRequestAttribute(request, merchantDTO.getId(), fromDate, toDate);
			   }else{
			    	 LOGGER.info("Login From Admin User");
					 List<MerchantDTO> merchantList = merchantService.getAllMerchant();
					 request.setAttribute(Constants.MERCHANT_LIST, merchantList);
					 if(!merchantList.isEmpty()){
						MerchantDTO merchant = merchantList.get(0);
						setRequestAttribute(request, merchant.getId(), fromDate, toDate);
					 }
			   }
	   }
	   
	   private void setRequestAttribute(HttpServletRequest request, int mId, String fromDate, String toDate){
		       request.setAttribute(Constants.CUSTOMER_ID_PREFIX, appProperiesService.getSystemParamValueByName(Constants.CUSTOMER_ID_PREFIX));
			   request.setAttribute(Constants.MERCHANT_ID_PREFIX, appProperiesService.getSystemParamValueByName(Constants.MERCHANT_ID_PREFIX));
			   request.setAttribute(Constants.MERCHANT_TRANSACTION_LIST, merchantTransService.getMerchantTransaction(mId, getFromDate(fromDate), getToDate(toDate)));
	   }

}
