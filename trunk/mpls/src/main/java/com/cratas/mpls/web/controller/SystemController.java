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
import com.cratas.mpls.service.ISystemService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.SystemDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * @author bhupendra
 *
 */
@Controller
@RequestMapping("/system")
public class SystemController extends BaseController{
	
		private final static Logger LOGGER = LoggerFactory.getLogger(SystemController.class);
		   
	    @Autowired
		private IUtilityService utilityService;
	    
	    @Autowired
	    private ISystemService systemService;
		
	    @RequestMapping(value = "/view", method = RequestMethod.GET)
		public ModelAndView processorView(HttpServletRequest request, HttpServletResponse response, Model model) {
				   if(checkUserAlreadyLogin(request, response)){
						ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.SYSTEM));
						request.setAttribute(Constants.SYSTEM_LIST, systemService.getSystem());
						Map<String, Object> requestMap = ((Model) model).asMap();
				        if(null != requestMap){
				        	request.setAttribute(Constants.MESSAGE, requestMap.get(Constants.MESSAGE));
				        	request.setAttribute(Constants.IS_SUCCESS, requestMap.get(Constants.IS_SUCCESS));
				        	request.setAttribute(Constants.SYSTEM, requestMap.get(Constants.SYSTEM));
				        	request.setAttribute(Constants.ACTION, requestMap.get(Constants.ACTION));
				        }
						return view;
				   }
				   return new ModelAndView("redirect:/");
		}
		
		/**
		 * This method used to save System.
		 * @return
		 */
		@RequestMapping(value = "/add", method = RequestMethod.POST)
		public ModelAndView add(@ModelAttribute("systemDTO") SystemDTO systemDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)){
					   try{
						   UserDTO userDTO = getSessionUserObject(request);
						   int isInsert = systemService.saveSystem(systemDTO, userDTO);
						   if(isInsert == 1){
							   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "System Added Successfully.");
							   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
							   return new ModelAndView(RedirectScreenConstants.REDIRECT_SYSTEM);
						   }else if(isInsert == 2){
							   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "System Already Exists.");
							   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
							   return new ModelAndView(RedirectScreenConstants.REDIRECT_SYSTEM);
						   }
					   }catch(Exception e){
							   LOGGER.error("Error in Add System -->  "+e);
					   }
					   
			   }
			   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Add System.");
			   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
			   return new ModelAndView(RedirectScreenConstants.REDIRECT_SYSTEM);
		}
		
		/**
		 *  This method used to update System.
		 * @return
		 */
		@RequestMapping(value = "/update", method = RequestMethod.GET)
		public ModelAndView update(@RequestParam("systemId") String systemId, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response, Model model) {
				   if(checkUserAlreadyLogin(request, response)){
					   if(StringUtils.isNotEmpty(systemId)){
						    redirectAttribute.addFlashAttribute(Constants.SYSTEM, systemService.getSystemById(systemId));
						    redirectAttribute.addFlashAttribute(Constants.ACTION, Constants.UPDATE);
							return new ModelAndView(RedirectScreenConstants.REDIRECT_SYSTEM);
				       }
					   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in update system.");
					   return new ModelAndView(RedirectScreenConstants.REDIRECT_SYSTEM);
				   }
				   return new ModelAndView("redirect:/");
		}
		
		/**
		 * This method used to update System.
		 * @return
		 */
		@RequestMapping(value = "/edit", method = RequestMethod.POST)
		public ModelAndView edit(@ModelAttribute("systemDTO") SystemDTO systemDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)){
					   try{
						   int isUpdate = systemService.updateSystem(systemDTO);
						   if(isUpdate > 0){
							   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "System Updated Successfully.");
							   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
							   return new ModelAndView(RedirectScreenConstants.REDIRECT_SYSTEM);
						   }
					   }catch(Exception e){
						   LOGGER.error("Error in update system -->  "+e);
					   }
			   }
			   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Update System.");
			   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
			   return new ModelAndView(RedirectScreenConstants.REDIRECT_SYSTEM);
		}
		
		/**
		 *  This method used to delete System
		 */
		@RequestMapping(value = "/delete", method = RequestMethod.POST)
		public ModelAndView delete(@ModelAttribute("systemDTO") SystemDTO systemDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)){
					   try{
						   int isInsert = systemService.deleteSystem(systemDTO);
						   if(isInsert > 0){
							   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "System Deleted Successfully.");
							   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
							   return new ModelAndView(RedirectScreenConstants.REDIRECT_SYSTEM);
						   }
					   }catch(Exception e){
						   LOGGER.error("Error in delete system -->  "+e);
					   }
			   }
			   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in delete System.");
			   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
			   return new ModelAndView(RedirectScreenConstants.REDIRECT_SYSTEM);
		}
}
