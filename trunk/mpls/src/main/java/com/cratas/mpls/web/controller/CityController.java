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
import com.cratas.mpls.service.ICityService;
import com.cratas.mpls.service.ICountryService;
import com.cratas.mpls.service.IStateService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.CityDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author sunil
 *
 */
@Controller
@RequestMapping("/city")
public class CityController extends BaseController {

		private final static Logger LOGGER = LoggerFactory.getLogger(CityController.class);
	
		@Autowired
		private IUtilityService utilityService;
	
		@Autowired
		private ICityService cityService;
	
		@Autowired
		private IStateService stateService;
	
		@Autowired
		private ICountryService countryService;
	
		@RequestMapping(value = "/view", method = RequestMethod.GET)
		public ModelAndView city(HttpServletRequest request, HttpServletResponse response, Model model) {
			   if (checkUserAlreadyLogin(request, response)) {
					ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.CITY));
					request.setAttribute(Constants.COUNTRY_LIST, countryService.getCountry());
					request.setAttribute(Constants.CITY_ALL_LIST, cityService.getCity());
					Map<String, Object> requestMap = ((Model) model).asMap();
					if (requestMap != null) {
						String action = (String) requestMap.get(Constants.ACTION);
						request.setAttribute(Constants.MESSAGE, requestMap.get(Constants.MESSAGE));
						request.setAttribute(Constants.IS_SUCCESS, requestMap.get(Constants.IS_SUCCESS));
						request.setAttribute(Constants.CITY, requestMap.get(Constants.CITY));
						request.setAttribute(Constants.ACTION, requestMap.get(Constants.ACTION));
						setRequestAttribute(request, action, requestMap);
					}
					return view;
				}
				return new ModelAndView("redirect:/");
		}
	
		@RequestMapping(value = "/add", method = RequestMethod.POST)
		public ModelAndView add(@ModelAttribute("cityDTO") CityDTO cityDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)) {
					try {
						UserDTO userDTO = getSessionUserObject(request);
						int isInsert = cityService.saveCity(cityDTO, userDTO);
						if (isInsert == 1) {
							redirectAttribute.addFlashAttribute(Constants.MESSAGE, "City Added Successfully.");
							redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS);
							return new ModelAndView(RedirectScreenConstants.REDIRECT_CITY);
						} else if (isInsert == 2) {
							redirectAttribute.addFlashAttribute(Constants.MESSAGE, "City Already Exists.");
							redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE);
							return new ModelAndView(RedirectScreenConstants.REDIRECT_CITY);
						}
					} catch (Exception e) {
						LOGGER.error("Error in save City -->  " + e);
					}
		
				}
				redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Add City.");
				redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE);
				return new ModelAndView(RedirectScreenConstants.REDIRECT_CITY);
		}
	
		@RequestMapping(value = "/update", method = RequestMethod.GET)
		public ModelAndView update(@RequestParam("id") String id, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response, Model model) {
				if(checkUserAlreadyLogin(request, response)) {
					if (StringUtils.isNotEmpty(id)) {
						redirectAttribute.addFlashAttribute(Constants.CITY, cityService.getCityListById(utilityService.convertStringToInt(id)));
						redirectAttribute.addFlashAttribute(Constants.ACTION, Constants.UPDATE);
						return new ModelAndView(RedirectScreenConstants.REDIRECT_CITY);
					}
					redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in update City.");
					return new ModelAndView(RedirectScreenConstants.REDIRECT_CITY);
				}
				return new ModelAndView("redirect:/");
		}
	
		@RequestMapping(value = "/edit", method = RequestMethod.POST)
		public ModelAndView edit(@ModelAttribute("cityDTO") CityDTO cityDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)) {
					try {
						int isUpdate = cityService.updateCity(cityDTO);
						if (isUpdate > 0) {
							redirectAttribute.addFlashAttribute(Constants.MESSAGE, "City Updated Successfully.");
							redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS);
							return new ModelAndView(RedirectScreenConstants.REDIRECT_CITY);
						}
					} catch (Exception e) {
						LOGGER.error("Error in update city -->  " + e);
					}
				}
				redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Update City.");
				redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE);
				return new ModelAndView(RedirectScreenConstants.REDIRECT_CITY);
		}
	
		@RequestMapping(value = "/delete", method = RequestMethod.POST)
		public ModelAndView delete(@ModelAttribute("cityDTO") CityDTO cityDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if (checkUserAlreadyLogin(request, response)) {
					try {
						int isInsert = cityService.deleteCity(cityDTO);
						if (isInsert > 0) {
							redirectAttribute.addFlashAttribute(Constants.MESSAGE, "City Deleted Successfully.");
							redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS);
							return new ModelAndView(RedirectScreenConstants.REDIRECT_CITY);
						}
					} catch (Exception e) {
						LOGGER.error("Error in delete City -->  " + e);
					}
				}
				redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in delete City.");
				redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE);
				return new ModelAndView(RedirectScreenConstants.REDIRECT_CITY);
		}
		
		private void setRequestAttribute(HttpServletRequest request, String action, Map<String, Object> requestMap){
			    CityDTO city = (CityDTO) requestMap.get(Constants.CITY);
				if(null != city && StringUtils.isNotEmpty(action) && action.equals(Constants.UPDATE)){
					 request.setAttribute(Constants.STATE_LIST, stateService.getStateList(city.getCountryId()));
				}
        }
}