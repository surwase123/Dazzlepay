package com.cratas.mpls.web.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.constant.PageConstant;
import com.cratas.mpls.common.utility.constant.RedirectScreenConstants;
import com.cratas.mpls.service.ICustomerService;
import com.cratas.mpls.service.IMerchantService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.ResetPasswordDTO;

/**
 * @author priyanka
 *
 */
@Controller
@RequestMapping("/resetPassword")
public class ResetPasswordController extends BaseController{
	
	   private final static Logger LOGGER = LoggerFactory.getLogger(ResetPasswordController.class);
	   
	   @Autowired
	   private IUtilityService utilityService;
	   
	   @Autowired
	   private IMerchantService merchantService;
		
	   @Autowired
	   private ICustomerService customerService;
		
	   @RequestMapping(value = "/view", method = RequestMethod.GET)
		public ModelAndView city(HttpServletRequest request, HttpServletResponse response, Model model) {
			   if (checkUserAlreadyLogin(request, response)) {
					ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.RESET_PASSWORD_SUPERADMIN));
					Map<String, Object> requestMap = ((Model) model).asMap();
					if (requestMap != null) {
						request.setAttribute(Constants.MESSAGE, requestMap.get(Constants.MESSAGE));
						request.setAttribute(Constants.IS_SUCCESS, requestMap.get(Constants.IS_SUCCESS));
						request.setAttribute(Constants.BLOCK_UNBLOCK, requestMap.get(Constants.BLOCK_UNBLOCK));
						request.setAttribute(Constants.ACTION, requestMap.get(Constants.ACTION));
					}
					return view;
				}
				return new ModelAndView("redirect:/");
		}
	   

		@RequestMapping(value = "/searchingDetails", method = RequestMethod.POST)
		public ModelAndView searchDetail(@ModelAttribute("resetPasswordDTO") ResetPasswordDTO resetPasswordDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)) {
				    redirectAttribute.addFlashAttribute(Constants.RESET_PASSWORD, resetPasswordDTO);
					try {
						if (resetPasswordDTO.getUserType()!=null && resetPasswordDTO.getUserType().equals(Constants.MERCHANT)) {
							redirectAttribute.addFlashAttribute(Constants.MERCHANT, merchantService.getMerchantByMN(resetPasswordDTO.getMobileNumber()));
							return new ModelAndView(RedirectScreenConstants.REDIRECT_RESET_PASSWORD_BY_ADMIN);
						} else if (resetPasswordDTO.getUserType()!=null && resetPasswordDTO.getUserType().equals(Constants.CUSTOMER)) {
							redirectAttribute.addFlashAttribute(Constants.CUSTOMER, customerService.getCustomerByMN(resetPasswordDTO.getMobileNumber()));
							return new ModelAndView(RedirectScreenConstants.REDIRECT_RESET_PASSWORD_BY_ADMIN);
						}
					} catch (Exception e) {
						LOGGER.error("Error in Fetch Details -->  " + e);
					}
		
				}
				redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Fetch Customer/Merchant Details.");
				redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE);
				return new ModelAndView(RedirectScreenConstants.REDIRECT_RESET_PASSWORD_BY_ADMIN);
		}

}
