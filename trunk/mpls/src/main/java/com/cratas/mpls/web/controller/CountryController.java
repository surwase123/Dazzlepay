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
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.CountryDTO;
import com.cratas.mpls.web.dto.UserDTO;



/**
 * 
 * @author Bhupendra
 *
 */

@Controller
@RequestMapping("/country")
public class CountryController extends BaseController{
	
	    private final static Logger LOGGER = LoggerFactory.getLogger(CountryController.class);
		
		@Autowired
		private IUtilityService utilityService;
		
		@Autowired
		private ICountryService countryService;
		
		@RequestMapping(value = "/view", method = RequestMethod.GET)
		public ModelAndView country(HttpServletRequest request, HttpServletResponse response, Model model) {
			   if(checkUserAlreadyLogin(request, response)){
					ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.COUNTRY));
					request.setAttribute(Constants.COUNTRY_LIST, countryService.getCountry());
					Map<String, Object> requestMap = ((Model) model).asMap();
			        if(null != requestMap){
			        	request.setAttribute(Constants.MESSAGE, requestMap.get(Constants.MESSAGE));
			        	request.setAttribute(Constants.IS_SUCCESS, requestMap.get(Constants.IS_SUCCESS));
			        	request.setAttribute(Constants.COUNTRY, requestMap.get(Constants.COUNTRY));
			        	request.setAttribute(Constants.ACTION, requestMap.get(Constants.ACTION));
			        }
					return view;
			   }
			   return new ModelAndView("redirect:/");
		}

		@RequestMapping(value = "/add", method = RequestMethod.POST)
		public ModelAndView add(@ModelAttribute("countryDTO") CountryDTO countryDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)){
				   try{
					   UserDTO userDTO = getSessionUserObject(request);
					   int isInsert = countryService.saveCountry(countryDTO, userDTO);
					   if(isInsert == 1){
						   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Country Added Successfully.");
						   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
						   return new ModelAndView(RedirectScreenConstants.REDIRECT_COUNTRY);
					   }else if(isInsert == 2){
						   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Country Already Exists.");
						   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
						   return new ModelAndView(RedirectScreenConstants.REDIRECT_COUNTRY);
					   }
				   }catch(Exception e){
						   LOGGER.error("Error in save Country -->  "+e);
				   }
			   }
			   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Add Country.");
			   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
			   return new ModelAndView(RedirectScreenConstants.REDIRECT_COUNTRY);
		}
		
	
		@RequestMapping(value = "/update", method = RequestMethod.GET)
		public ModelAndView update(@RequestParam("countryCode") String currencyCode, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response, Model model) {
				   if(checkUserAlreadyLogin(request, response)){
					   if(StringUtils.isNotEmpty(currencyCode)){
						    int code = utilityService.convertStringToInt(currencyCode);
						    redirectAttribute.addFlashAttribute(Constants.COUNTRY, countryService.getCountryByCode(code));
						    redirectAttribute.addFlashAttribute(Constants.ACTION, Constants.UPDATE);
							return new ModelAndView(RedirectScreenConstants.REDIRECT_COUNTRY);
				       }
					   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in update Country.");
					   return new ModelAndView(RedirectScreenConstants.REDIRECT_COUNTRY);
				   }
				   return new ModelAndView("redirect:/");
		}
		
		@RequestMapping(value = "/edit", method = RequestMethod.POST)
		public ModelAndView edit(@ModelAttribute("countryDTO") CountryDTO countryDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)){
				   try{
					   int isUpdate = countryService.updateCountry(countryDTO);
					   if(isUpdate > 0){
						   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Country Updated Successfully.");
						   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
						   return new ModelAndView(RedirectScreenConstants.REDIRECT_COUNTRY);
					   }
				   }catch(Exception e){
					   LOGGER.error("Error in update Country -->  "+e);
				   }
			   }
			   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Update Country.");
			   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
			   return new ModelAndView(RedirectScreenConstants.REDIRECT_COUNTRY);
		}
		
		
		@RequestMapping(value = "/delete", method = RequestMethod.POST)
		public ModelAndView delete(@ModelAttribute("countryDTO") CountryDTO countryDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)){
				   try{
					   int isInsert = countryService.deleteCountry(countryDTO);
					   if(isInsert > 0){
						   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Country Deleted Successfully.");
						   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
						   return new ModelAndView(RedirectScreenConstants.REDIRECT_COUNTRY);
					   }
				   }catch(Exception e){
					   LOGGER.error("Error in delete Country -->  "+e);
				   }
			   }
			   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in delete Country.");
			   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
			   return new ModelAndView(RedirectScreenConstants.REDIRECT_COUNTRY);
		}
}
