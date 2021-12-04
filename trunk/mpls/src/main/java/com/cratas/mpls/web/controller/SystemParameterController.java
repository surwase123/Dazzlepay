/**
 * 
 */
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
import com.cratas.mpls.dao.dto.SystemParamDTO;
import com.cratas.mpls.service.IAppPropertiesService;
import com.cratas.mpls.service.IUtilityService;

/**
 * @author bhupendra
 *
 */
@Controller
@RequestMapping("/sysParameter")
public class SystemParameterController extends BaseController{
		
		private final static Logger LOGGER = LoggerFactory.getLogger(SystemParameterController.class);
	
		@Autowired
		private IAppPropertiesService appPropertiesService;
		
		@Autowired
		private IUtilityService utilityService;
		
		@RequestMapping(value = "/view", method = RequestMethod.GET)
		public ModelAndView reconVisaTransaction(HttpServletRequest request, HttpServletResponse response, Model model) {
			   if (checkUserAlreadyLogin(request, response)) {
						ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.SYSTEM_PARAMETER));
						request.setAttribute(Constants.SYSTEM_PARAMETER_LIST, appPropertiesService.getSystemParameter());
						Map<String, Object> requestMap = ((Model) model).asMap();
						if (null != requestMap) {
							String action = (String) requestMap.get(Constants.ACTION);
							request.setAttribute(Constants.MESSAGE, requestMap.get(Constants.MESSAGE));
							request.setAttribute(Constants.IS_SUCCESS, requestMap.get(Constants.IS_SUCCESS));
							request.setAttribute(Constants.ACTION, action);
						}
						return view;
			   }
			   return new ModelAndView("redirect:/");
		}
		
		@RequestMapping(value = "/add", method = RequestMethod.POST)
		public ModelAndView add(@ModelAttribute("systemParamDTO") SystemParamDTO systemParamDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)){
					   try{
						   int isInsert = appPropertiesService.saveSysParameter(systemParamDTO);
						   if(isInsert == 1){
							   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "System Parameter Added Successfully.");
							   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
							   return new ModelAndView(RedirectScreenConstants.REDIRECT_SYSTEM_PARAMETER);
						   }else if(isInsert == 2){
							   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "System Parameter Already Exists.");
							   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
							   return new ModelAndView(RedirectScreenConstants.REDIRECT_SYSTEM_PARAMETER);
						   }
					   }catch(Exception e){
							   LOGGER.error("Error in save System Parameter -->  "+e);
					   }
					   
			   }
			   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Add System Parameter.");
			   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
			   return new ModelAndView(RedirectScreenConstants.REDIRECT_SYSTEM_PARAMETER);
		}
		
		@RequestMapping(value = "/update", method = RequestMethod.GET)
		public ModelAndView update(@RequestParam("paramName") String paramName, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response, Model model) {
				   if(checkUserAlreadyLogin(request, response)){
					   if(StringUtils.isNotEmpty(paramName)){
						    redirectAttribute.addFlashAttribute(Constants.SYSTEM_PARAMETER, appPropertiesService.getSystemParamByName(paramName));
						    redirectAttribute.addFlashAttribute(Constants.ACTION, Constants.UPDATE);
							return new ModelAndView(RedirectScreenConstants.REDIRECT_SYSTEM_PARAMETER);
				       }
					   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in update System Parameter.");
					   return new ModelAndView(RedirectScreenConstants.REDIRECT_SYSTEM_PARAMETER);
				   }
				   return new ModelAndView("redirect:/");
		}
		
		/**
		 * This method used to update country.
		 * @param countryDTO
		 * @param redirectAttribute
		 * @return
		 */
		@RequestMapping(value = "/edit", method = RequestMethod.POST)
		public ModelAndView edit(@ModelAttribute("systemParamDTO") SystemParamDTO systemParamDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)){
					   try{
						   int isUpdate = appPropertiesService.updateSystemParameter(systemParamDTO);
						   if(isUpdate > 0){
							   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "System Parameter Updated Successfully.");
							   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
							   return new ModelAndView(RedirectScreenConstants.REDIRECT_SYSTEM_PARAMETER);
						   }
					   }catch(Exception e){
						   LOGGER.error("Error in update System Parameter -->  "+e);
					   }
			   }
			   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Update System Parameter.");
			   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
			   return new ModelAndView(RedirectScreenConstants.REDIRECT_SYSTEM_PARAMETER);
		}
		
		
		/**
		 * This method used to delete country.
		 * @param countryDTO
		 * @param redirectAttribute
		 * @return
		 */
		@RequestMapping(value = "/delete", method = RequestMethod.POST)
		public ModelAndView delete(@ModelAttribute("systemParamDTO") SystemParamDTO systemParamDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)){
					   try{
						   int isInsert = appPropertiesService.deleteSystemParameter(systemParamDTO);
						   if(isInsert > 0){
							   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "System Parameter Deleted Successfully.");
							   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
							   return new ModelAndView(RedirectScreenConstants.REDIRECT_SYSTEM_PARAMETER);
						   }
					   }catch(Exception e){
						   LOGGER.error("Error in delete System Parameter -->  "+e);
					   }
			   }
			   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in delete System Parameter.");
			   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
			   return new ModelAndView(RedirectScreenConstants.REDIRECT_SYSTEM_PARAMETER);
		}
}
