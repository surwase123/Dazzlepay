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
import com.cratas.mpls.service.IAppPropertiesService;
import com.cratas.mpls.service.ICustomerService;
import com.cratas.mpls.service.IMerchantService;
import com.cratas.mpls.service.IUserService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.CustomerDTO;
import com.cratas.mpls.web.dto.SearchUserDTO;

/**
 * @author bhupendra
 *
 */
@Controller
@RequestMapping("searchUser")
public class SearchUserController extends BaseController{

		private final static Logger LOGGER = LoggerFactory.getLogger(SearchUserController.class);
		
		@Autowired
		private IUtilityService utilityService;
		
		@Autowired
	    private IAppPropertiesService appProperiesService;
		
		@Autowired
		private IMerchantService merchantService;
		
		@Autowired
		private ICustomerService customerService;
		
		@Autowired
		private IUserService userService;
	
		@RequestMapping(value = "/view", method = RequestMethod.GET)
		public ModelAndView city(HttpServletRequest request, HttpServletResponse response, Model model) {
			   if (checkUserAlreadyLogin(request, response)) {
					ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.SEARCH_USER));
					request.setAttribute(Constants.CUSTOMER_SEARCH_PARAMETER, appProperiesService.getCustomerSearchParameter());
					request.setAttribute(Constants.MERCHANT_SEARCH_PARAMETER, appProperiesService.getMerchantSearchParameter());
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
		
		@RequestMapping(value = "/searchDetails", method = RequestMethod.POST)
		public ModelAndView searchDetail(@ModelAttribute("searchUserDTO") SearchUserDTO searchUserDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)) {
				    redirectAttribute.addFlashAttribute(Constants.SEARCH_USER, searchUserDTO);
					try {
						if (searchUserDTO.getUserType()!=null && searchUserDTO.getUserType().equals(Constants.MERCHANT)) {
							redirectAttribute.addFlashAttribute(Constants.MERCHANT_LIST, merchantService.getMerchantDetails(searchUserDTO.getMerchantSearchBy(), searchUserDTO.getSearchValue()));
							redirectAttribute.addFlashAttribute(Constants.MERCHANT_ID_PREFIX, appProperiesService.getSystemParamValueByName(Constants.MERCHANT_ID_PREFIX));
							return new ModelAndView(RedirectScreenConstants.REDIRECT_SEARCH_USER);
						} else if (searchUserDTO.getUserType()!=null && searchUserDTO.getUserType().equals(Constants.CUSTOMER)) {
							redirectAttribute.addFlashAttribute(Constants.CUSTOMER_LIST, customerService.getCustomerDetails(searchUserDTO.getCustomerSearchBy(), searchUserDTO.getSearchValue()));
							redirectAttribute.addFlashAttribute(Constants.CUSTOMER_ID_PREFIX, appProperiesService.getSystemParamValueByName(Constants.CUSTOMER_ID_PREFIX));
							return new ModelAndView(RedirectScreenConstants.REDIRECT_SEARCH_USER);
						}
					} catch (Exception e) {
						LOGGER.error("Error in Fetch Details -->  " + e);
					}
		
				}
				redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Fetch Customer/Merchant Details.");
				redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE);
				return new ModelAndView(RedirectScreenConstants.REDIRECT_SEARCH_USER);
		}
		
		@RequestMapping(value = "/updateMerchant", method = RequestMethod.POST)
		public ModelAndView edit(@ModelAttribute("searchUserDTO") SearchUserDTO searchUserDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)) {
					try {
					    int isUpdate = merchantService.updateMerchantStatus(searchUserDTO);
						if (isUpdate > 0) {
							redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Merchant "+searchUserDTO.getOperation()+" Successfully.");
							redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS);
							return new ModelAndView(RedirectScreenConstants.REDIRECT_SEARCH_USER);
						}
					} catch (Exception e) {
						LOGGER.error("Error in update Merchant Status -->  " + e);
					}
				}
				redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Update Merchant Status.");
				redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE);
				return new ModelAndView(RedirectScreenConstants.REDIRECT_SEARCH_USER);
		}
		
		@RequestMapping(value = "/updateCustomer", method = RequestMethod.POST)
		public ModelAndView updateCustomer(@ModelAttribute("searchUserDTO") SearchUserDTO searchUserDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)) {
					try {
					    int isUpdate = customerService.updateCustomerStatus(searchUserDTO);
						if (isUpdate > 0) {
							redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Customer "+searchUserDTO.getOperation()+" Successfully.");
							redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS);
							return new ModelAndView(RedirectScreenConstants.REDIRECT_SEARCH_USER);
						}
					} catch (Exception e) {
						LOGGER.error("Error in update Customer Status -->  " + e);
					}
				}
				redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Update Customer Status.");
				redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE);
				return new ModelAndView(RedirectScreenConstants.REDIRECT_SEARCH_USER);
		}
		
		@RequestMapping(value = "/unlockMerchantUser", method = RequestMethod.POST)
		public ModelAndView unlockMerchantUser(@ModelAttribute("searchUserDTO") SearchUserDTO searchUser, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)) {
					try {
						int isUpdate = merchantService.unBlockMerchantUser(searchUser);
						if (isUpdate > 0) {
							redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Merchant "+searchUser.getOperation()+" Successfully.");
							redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS);
							return new ModelAndView(RedirectScreenConstants.REDIRECT_SEARCH_USER);
						}
					} catch (Exception e) {
						LOGGER.error("Error in update Merchant Status -->  " + e);
					}
				}
				redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Unlock Merchant.");
				redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE);
				return new ModelAndView(RedirectScreenConstants.REDIRECT_SEARCH_USER);
		}
		
		@RequestMapping(value = "/unlockCustomerUser", method = RequestMethod.POST)
		public ModelAndView unlockCustomerUser(@ModelAttribute("searchUserDTO") SearchUserDTO searchUserDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)) {
					try {
						CustomerDTO customer = customerService.getCustomerByCId(searchUserDTO.getId());
						int isUpdate = userService.unlockUser(customer.getLoginId(), searchUserDTO.getUpdatedBy());
						if (isUpdate > 0) {
							redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Customer "+searchUserDTO.getOperation()+" Successfully.");
							redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS);
							return new ModelAndView(RedirectScreenConstants.REDIRECT_SEARCH_USER);
						}
					} catch (Exception e) {
						LOGGER.error("Error in update Customer Status -->  " + e);
					}
				}
				redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Unlock Customer.");
				redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE);
				return new ModelAndView(RedirectScreenConstants.REDIRECT_SEARCH_USER);
		}
}
