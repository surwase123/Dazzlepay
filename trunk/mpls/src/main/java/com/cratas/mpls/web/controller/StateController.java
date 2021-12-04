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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.constant.PageConstant;
import com.cratas.mpls.common.utility.constant.RedirectScreenConstants;
import com.cratas.mpls.dao.dto.StateDTO;
import com.cratas.mpls.service.ICountryService;
import com.cratas.mpls.service.IStateService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author Priyanka
 *
 */


@Controller
@RequestMapping("/state")
public class StateController extends BaseController {
	
		private final static Logger LOGGER = LoggerFactory.getLogger(StateController.class);
		
		@Autowired
		private IUtilityService utilityService;
		
		@Autowired
		private IStateService stateService;
		
		@Autowired
		private ICountryService countryService;
		
		@RequestMapping(value = "/view", method = RequestMethod.GET)
		public ModelAndView state(HttpServletRequest request, HttpServletResponse response, Model model) {
				   if(checkUserAlreadyLogin(request, response)){
						ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.STATE));
						request.setAttribute(Constants.STATE_LIST, stateService.getStates());
						request.setAttribute(Constants.COUNTRY_LIST, countryService.getCountry());
						request.setAttribute(Constants.STATE_LIST, stateService.getStates());
						Map<String, Object> requestMap = ((Model) model).asMap();
				        if(null != requestMap){
				        	request.setAttribute(Constants.MESSAGE, requestMap.get(Constants.MESSAGE));
				        	request.setAttribute(Constants.IS_SUCCESS, requestMap.get(Constants.IS_SUCCESS));
				        	request.setAttribute(Constants.STATE, requestMap.get(Constants.STATE));
				        	request.setAttribute(Constants.ACTION, requestMap.get(Constants.ACTION));
				        }
						return view;
				   }
				   return new ModelAndView("redirect:/");
		}
		
		@RequestMapping(value = "/add", method = RequestMethod.POST)
		public ModelAndView add(@ModelAttribute("stateDTO") StateDTO stateDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)){
					   try{
						   UserDTO userDTO = getSessionUserObject(request);
						   int isInsert = stateService.saveState(stateDTO, userDTO);
						   if(isInsert == 1){
							   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "State Added Successfully.");
							   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
							   return new ModelAndView(RedirectScreenConstants.REDIRECT_STATE);
						   }else if(isInsert == 2){
							   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "State Name Already Exists.");
							   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
							   return new ModelAndView(RedirectScreenConstants.REDIRECT_STATE);
						   }
					   }catch(Exception e){
							   LOGGER.error("Error in save State -->  "+e);
					   }
					   
			   }
			   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Add State.");
			   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
			   return new ModelAndView(RedirectScreenConstants.REDIRECT_STATE);
		}
		
		@RequestMapping(value = "/update", method = RequestMethod.GET)
		public ModelAndView update(@RequestParam("id") int id, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response, Model model) {
				   if(checkUserAlreadyLogin(request, response)){
					   if(id > 0){
						    redirectAttribute.addFlashAttribute(Constants.STATE, stateService.getStatesById(id));
						    redirectAttribute.addFlashAttribute(Constants.ACTION, Constants.UPDATE);
							return new ModelAndView(RedirectScreenConstants.REDIRECT_STATE);
				       }
					   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in update State.");
					   return new ModelAndView(RedirectScreenConstants.REDIRECT_STATE);
				   }
				   return new ModelAndView("redirect:/");
		}
		
		@RequestMapping(value = "/edit", method = RequestMethod.POST)
		public ModelAndView edit(@ModelAttribute("stateDTO") StateDTO stateDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)){
					   try{
						   int isUpdate = stateService.updateState(stateDTO);
						   if(isUpdate > 0){
							   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "State Updated Successfully.");
							   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
							   return new ModelAndView(RedirectScreenConstants.REDIRECT_STATE);
						   }
					   }catch(Exception e){
						   LOGGER.error("Error in update State -->  "+e);
					   }
			   }
			   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Update State.");
			   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
			   return new ModelAndView(RedirectScreenConstants.REDIRECT_STATE);
		}
		
		@RequestMapping(value = "/delete", method = RequestMethod.POST)
		public ModelAndView delete(@ModelAttribute("stateDTO") StateDTO stateDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)){
					   try{
						   int isInsert = stateService.deleteState(stateDTO);
						   if(isInsert > 0){
							   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "State Deleted Successfully.");
							   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
							   return new ModelAndView(RedirectScreenConstants.REDIRECT_STATE);
						   }
					   }catch(Exception e){
						   LOGGER.error("Error in delete State -->  "+e);
					   }
			   }
			   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in delete State.");
			   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
			   return new ModelAndView(RedirectScreenConstants.REDIRECT_STATE);
		}
}