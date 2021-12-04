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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.constant.PageConstant;
import com.cratas.mpls.common.utility.constant.RedirectScreenConstants;
import com.cratas.mpls.service.ICountryService;
import com.cratas.mpls.service.ICurrencyService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.CurrencyDTO;
import com.cratas.mpls.web.dto.UserDTO;


/**
 * 
 * @author mukesh
 *
 */
@Controller
@RequestMapping("/currency")
public class CurrencyController extends BaseController {
	
	
        private final static Logger LOGGER = LoggerFactory.getLogger(CurrencyController.class);
	
		@Autowired
		private IUtilityService utilityService;
		
		@Autowired
		private ICurrencyService currencyService;
		
		@Autowired
		private ICountryService countryService;
	
		@RequestMapping(value = "/view", method = RequestMethod.GET)
		public ModelAndView currency(HttpServletRequest request, HttpServletResponse response, Model model) {
				   if(checkUserAlreadyLogin(request, response)){
						ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.CURRENCY));
						request.setAttribute(Constants.CURRENCY_LIST, currencyService.getCurrency());
						request.setAttribute(Constants.COUNTRY_LIST, countryService.getCountry());
						Map<String, Object> requestMap = ((Model) model).asMap();
				        if(null != requestMap){
				        	request.setAttribute(Constants.MESSAGE, requestMap.get(Constants.MESSAGE));
				        	request.setAttribute(Constants.IS_SUCCESS, requestMap.get(Constants.IS_SUCCESS));
				        	request.setAttribute(Constants.CURRENCY, requestMap.get(Constants.CURRENCY));
				        	request.setAttribute(Constants.ACTION, requestMap.get(Constants.ACTION));
				        }
						return view;
				   }
				   return new ModelAndView("redirect:/");
		}
		
		/**
		 * This method used to add currency.
		 * @param currencyDTO
		 * @param redirectAttribute
		 * @return
		 */
		@RequestMapping(value = "/add", method = RequestMethod.POST)
		public ModelAndView add(@ModelAttribute("currencyDTO") CurrencyDTO currencyDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)){
					   try{
						   UserDTO userDTO = getSessionUserObject(request);
						   int isInsert = currencyService.saveCurrency(currencyDTO, userDTO);
						   if(isInsert == 1){
							   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Currency Added Successfully.");
							   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
							   return new ModelAndView(RedirectScreenConstants.REDIRECT_CURRENCY);
						   }else if(isInsert == 2){
							   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Currency Code Already Exists.");
							   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
							   return new ModelAndView(RedirectScreenConstants.REDIRECT_CURRENCY);
						   }
					   }catch(Exception e){
							   LOGGER.error("Error in save currency -->  "+e);
					   }
					   
			   }
			   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Add Currency.");
			   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
			   return new ModelAndView(RedirectScreenConstants.REDIRECT_CURRENCY);
		}
		
		@RequestMapping(value = "/update", method = RequestMethod.GET)
		public ModelAndView update(@RequestParam("currencyCode") String currencyCode, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response, Model model) {
				   if(checkUserAlreadyLogin(request, response)){
					   if(StringUtils.isNotEmpty(currencyCode)){
						    int code = utilityService.convertStringToInt(currencyCode);
						    redirectAttribute.addFlashAttribute(Constants.CURRENCY, currencyService.getCurrencyByCode(code));
						    redirectAttribute.addFlashAttribute(Constants.ACTION, Constants.UPDATE);
							return new ModelAndView(RedirectScreenConstants.REDIRECT_CURRENCY);
				       }
					   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in update Currency.");
					   return new ModelAndView(RedirectScreenConstants.REDIRECT_CURRENCY);
				   }
				   return new ModelAndView("redirect:/");
		}
		
		/**
		 * This method used to update currency.
		 * @param currencyDTO
		 * @param redirectAttribute
		 * @return
		 */
		@RequestMapping(value = "/edit", method = RequestMethod.POST)
		public ModelAndView edit(@ModelAttribute("currencyDTO") CurrencyDTO currencyDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)){
					   try{
						   int isUpdate = currencyService.updateCurrency(currencyDTO);
						   if(isUpdate > 0){
							   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Currency Updated Successfully.");
							   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
							   return new ModelAndView(RedirectScreenConstants.REDIRECT_CURRENCY);
						   }
					   }catch(Exception e){
						   LOGGER.error("Error in update currency -->  "+e);
					   }
			   }
			   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Update Currency.");
			   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
			   return new ModelAndView(RedirectScreenConstants.REDIRECT_CURRENCY);
		}
		
		
		/**
		 * This method used to delete currency.
		 * @param currencyDTO
		 * @param redirectAttribute
		 * @return
		 */
		@RequestMapping(value = "/delete", method = RequestMethod.POST)
		public ModelAndView delete(@ModelAttribute("currencyDTO") CurrencyDTO currencyDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)){
					   try{
						   int isInsert = currencyService.deleteCurrency(currencyDTO);
						   if(isInsert > 0){
							   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Currency Deleted Successfully.");
							   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
							   return new ModelAndView(RedirectScreenConstants.REDIRECT_CURRENCY);
						   }
					   }catch(Exception e){
						   LOGGER.error("Error in delete currency -->  "+e);
					   }
			   }
			   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in delete Currency.");
			   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
			   return new ModelAndView(RedirectScreenConstants.REDIRECT_CURRENCY);
		}
}
