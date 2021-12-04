package com.cratas.mpls.web.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.constant.PageConstant;
import com.cratas.mpls.common.utility.constant.RedirectScreenConstants;
import com.cratas.mpls.service.IAppPropertiesService;
import com.cratas.mpls.service.ICustomerTransactionService;
import com.cratas.mpls.service.IUtilityService;

/**
 * 
 * @author sunil
 *
 */
@Controller
@RequestMapping("/search/transaction/")
public class TransactionController extends BaseController{
	
	   private final static Logger LOGGER = LoggerFactory.getLogger(TransactionController.class);
	   
	   @Autowired
	   private IUtilityService utilityService;
	   
	   @Autowired
	   private IAppPropertiesService appPropertiesService;
	   
	   @Autowired
	   private ICustomerTransactionService customerTransactionService;
	   
	   @RequestMapping(value = "/view", method = RequestMethod.GET)
	   public ModelAndView searchTrans(HttpServletRequest request, HttpServletResponse response, Model model) {
		      if (checkUserAlreadyLogin(request, response)) {
				  ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.SEARCH_TRANSACTION));
				  return view;
		      }
		      return new ModelAndView("redirect:/");
		}
	   
	   @RequestMapping(value = "/byTransId", method = RequestMethod.POST)
	   public ModelAndView getTransByTransId(@RequestParam("transactionId") String transactionId, HttpServletRequest request, HttpServletResponse response,RedirectAttributes redirectAttribute) { 
		      if (checkUserAlreadyLogin(request, response)) {
				  redirectAttribute.addFlashAttribute(Constants.TRANSACTION_ID, transactionId);
				  if(StringUtils.isNotEmpty(transactionId)){
					  setRequestAttribute(redirectAttribute, transactionId);
					  return new ModelAndView(RedirectScreenConstants.REDIRECT_SEARCH_TRANSACTION);
				  }
				  LOGGER.info("Invalid Transaction Id -- "+transactionId);
				  redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Invalid Transaction Id!!");
			  }
			  redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE);
			  return new ModelAndView(RedirectScreenConstants.REDIRECT_SEARCH_TRANSACTION);
		}
	   
		private void setRequestAttribute(RedirectAttributes redirectAttribute, String transactionId){
				redirectAttribute.addFlashAttribute(Constants.TRANSACTION_DETAIL, customerTransactionService.getCustomerTransDetailById(transactionId));
			    redirectAttribute.addFlashAttribute(Constants.CUSTOMER_ID_PREFIX, appPropertiesService.getSystemParamValueByName(Constants.CUSTOMER_ID_PREFIX));
				redirectAttribute.addFlashAttribute(Constants.MERCHANT_ID_PREFIX, appPropertiesService.getSystemParamValueByName(Constants.MERCHANT_ID_PREFIX));
        }
	   
}
