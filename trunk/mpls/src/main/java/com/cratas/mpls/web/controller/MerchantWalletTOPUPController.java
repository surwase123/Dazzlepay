package com.cratas.mpls.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.cratas.mpls.service.IMerchantTransactionService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author sunil
 *
 */
@Controller
@RequestMapping("/merchant/topUp")
public class MerchantWalletTOPUPController extends BaseController {
	
	   private final static Logger LOGGER = LoggerFactory.getLogger(MerchantWalletTOPUPController.class);
		
	   @Autowired
	   private IUtilityService utilityService;
		
	   @Autowired
	   private IMerchantTransactionService mtService;
	   
	   @RequestMapping(value = "/view", method = RequestMethod.GET)
	   public ModelAndView RegistrationView(HttpServletRequest request, HttpServletResponse response, Model model) {
		      if (checkUserAlreadyLogin(request, response)) {
				  ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.MERCHANT_WALLET_TOP_UP));
				  return view;
		      }
		      return new ModelAndView("redirect:/");
	   }
	   
	   @RequestMapping(value = "/wallet", method = RequestMethod.POST)
	   public ModelAndView topUpAdd(@RequestParam("amount") double amount, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			  if (checkUserAlreadyLogin(request, response)) {
				  try {
					   UserDTO userDTO = getSessionUserObject(request);
					   if(null != userDTO.getMerchant()) {
						   int isInsert = mtService.topUpMerchantWallet(amount, userDTO.getMerchant().getId(), userDTO.getLoginId());
						   if(isInsert == 1){
							   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Merchant Limit has been updated Successfully");
							   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS);
							   return new ModelAndView(RedirectScreenConstants.REDIRECT_MERCHANT_TOP_UP_VIEW);
						   }
					   }
				  }catch(Exception e) {
					   LOGGER.info("Error in updae Merchant Limit -- "+e.getMessage());
				  }
			  }
			  redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in updae Merchant Limit");
			  redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE);
			  return new ModelAndView(RedirectScreenConstants.REDIRECT_MERCHANT_TOP_UP_VIEW);
	  }
}
