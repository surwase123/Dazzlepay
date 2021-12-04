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
import com.cratas.mpls.service.IAreaService;
import com.cratas.mpls.service.ICityService;
import com.cratas.mpls.service.ICountryService;
import com.cratas.mpls.service.IStateService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.AreaDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author bhupendra
 *
 */
@Controller
@RequestMapping("/area")
public class AreaController extends BaseController{
	
		private final static Logger LOGGER = LoggerFactory.getLogger(AreaController.class);
		
		@Autowired
		private IUtilityService utilityService;
		
		@Autowired
		private IAreaService areaService;
		
		@Autowired
		private ICountryService countryService;
		
		@Autowired
		private IStateService stateService;
		
		@Autowired
		private ICityService cityService;
		
		@RequestMapping(value = "/view", method = RequestMethod.GET)
		public ModelAndView country(HttpServletRequest request, HttpServletResponse response, Model model) {
			   if(checkUserAlreadyLogin(request, response)){
					ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.AREA));
					request.setAttribute(Constants.COUNTRY_LIST, countryService.getCountry());
					request.setAttribute(Constants.AREA_LIST, areaService.getArea());
					Map<String, Object> requestMap = ((Model) model).asMap();
			        if(null != requestMap){
			        	String action = (String) requestMap.get(Constants.ACTION);
			        	request.setAttribute(Constants.MESSAGE, requestMap.get(Constants.MESSAGE));
			        	request.setAttribute(Constants.IS_SUCCESS, requestMap.get(Constants.IS_SUCCESS));
			        	request.setAttribute(Constants.AREA_SCREEN, requestMap.get(Constants.AREA_SCREEN));
			        	request.setAttribute(Constants.ACTION, requestMap.get(Constants.ACTION));
			        	setRequestAttribute(request, action, requestMap);
			        }
					return view;
			   }
			   return new ModelAndView("redirect:/");
		}

		@RequestMapping(value = "/add", method = RequestMethod.POST)
		public ModelAndView add(@ModelAttribute("areaDTO") AreaDTO areaDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)){
				   try{
					   UserDTO userDTO = getSessionUserObject(request);
					   int isInsert = areaService.saveArea(areaDTO, userDTO);
					   if(isInsert == 1){
						   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Area Added Successfully.");
						   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
						   return new ModelAndView(RedirectScreenConstants.REDIRECT_AREA);
					   }else if(isInsert == 2){
						   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Area Code Already Exists.");
						   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
						   return new ModelAndView(RedirectScreenConstants.REDIRECT_AREA);
					   }
				   }catch(Exception e){
					   LOGGER.error("Error in save Area -->  "+e);
				   }
			   }
			   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Add Area.");
			   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
			   return new ModelAndView(RedirectScreenConstants.REDIRECT_AREA);
		}
		
		
		@RequestMapping(value = "/update", method = RequestMethod.GET)
		public ModelAndView update(@RequestParam("id") String id, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response, Model model) {
			   if(checkUserAlreadyLogin(request, response)){
				   if(StringUtils.isNotEmpty(id)){
					    redirectAttribute.addFlashAttribute(Constants.AREA_SCREEN, areaService.getAreaById(utilityService.convertStringToInt(id)));
					    redirectAttribute.addFlashAttribute(Constants.ACTION, Constants.UPDATE);
						return new ModelAndView(RedirectScreenConstants.REDIRECT_AREA);
			       }
				   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in update Area.");
				   return new ModelAndView(RedirectScreenConstants.REDIRECT_AREA);
			   }
			   return new ModelAndView("redirect:/");
		}
		
		
		@RequestMapping(value = "/edit", method = RequestMethod.POST)
		public ModelAndView edit(@ModelAttribute("areaDTO") AreaDTO areaDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)){
				   try{
					   int isUpdate = areaService.updateArea(areaDTO);
					   if(isUpdate > 0){
						   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Area Updated Successfully.");
						   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
						   return new ModelAndView(RedirectScreenConstants.REDIRECT_AREA);
					   }
				   }catch(Exception e){
					   LOGGER.error("Error in update Area -->  "+e);
				   }
			   }
			   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Update Area.");
			   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
			   return new ModelAndView(RedirectScreenConstants.REDIRECT_AREA);
		}
		
		@RequestMapping(value = "/delete", method = RequestMethod.POST)
		public ModelAndView delete(@ModelAttribute("areaDTO") AreaDTO areaDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)){
				   try{
					   int isInsert = areaService.deleteArea(areaDTO);
					   if(isInsert > 0){
						   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Area Deleted Successfully.");
						   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
						   return new ModelAndView(RedirectScreenConstants.REDIRECT_AREA);
					   }
				   }catch(Exception e){
					   LOGGER.error("Error in delete Area -->  "+e);
				   }
			   }
			   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in delete Area.");
			   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
			   return new ModelAndView(RedirectScreenConstants.REDIRECT_AREA);
		}	
		
		private void setRequestAttribute(HttpServletRequest request, String action, Map<String, Object> requestMap){
			    AreaDTO area = (AreaDTO) requestMap.get(Constants.AREA_SCREEN);
				if(null != area && StringUtils.isNotEmpty(action) && action.equals(Constants.UPDATE)){
					 request.setAttribute(Constants.STATE_LIST, stateService.getStateList(area.getCountryId()));
					 request.setAttribute(Constants.CITY_LIST, cityService.getCityList(area.getStateId()));
				}
        }
}
