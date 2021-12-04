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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.constant.PageConstant;
import com.cratas.mpls.common.utility.constant.RedirectScreenConstants;
import com.cratas.mpls.service.IGroupService;
import com.cratas.mpls.service.ISystemService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.GroupDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author mukesh
 *
 */
@Controller
@RequestMapping("/group")
public class GroupController extends BaseController{
	
		private final static Logger LOGGER = LoggerFactory.getLogger(GroupController.class);
		   
	    @Autowired
		private IUtilityService utilityService;
		
		@Autowired
		private IGroupService groupService;
		
		@Autowired
		private ISystemService systemService;
 
		
		@RequestMapping(value = "/view", method = RequestMethod.GET)
		public ModelAndView GroupView(HttpServletRequest request, HttpServletResponse response, Model model) {
				   if(checkUserAlreadyLogin(request, response)){
					    UserDTO userDTO = getSessionUserObject(request);
						ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.GROUP));
						request.setAttribute(Constants.GROUP_LIST, groupService.getGroup(userDTO.getSystemId()));
						request.setAttribute(Constants.SYSTEM_LIST, systemService.getActiveSystem());
						request.setAttribute(Constants.GROUP_TYPE_LIST, groupService.getGroupType());
						Map<String, Object> requestMap = ((Model) model).asMap();
				        if(null != requestMap){
				        	request.setAttribute(Constants.MESSAGE, requestMap.get(Constants.MESSAGE));
				        	request.setAttribute(Constants.IS_SUCCESS, requestMap.get(Constants.IS_SUCCESS));
				        	request.setAttribute(Constants.ACTION, requestMap.get(Constants.ACTION));
				        }
						return view;
				   }
				   return new ModelAndView("redirect:/");
		}
		
		/**
		 * This method used to save Group.
		 * @return
		 */
		@RequestMapping(value = "/add", method = RequestMethod.POST)
		public ModelAndView add(@ModelAttribute("groupDTO") GroupDTO groupDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)){
					   try{
						   UserDTO userDTO = getSessionUserObject(request);
						   groupDTO = setPasswordCharacteristics(groupDTO);
						   int isInsert = groupService.saveGroup(groupDTO, userDTO);
						   if(isInsert == 1){
							   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Group Added Successfully.");
							   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
							   return new ModelAndView(RedirectScreenConstants.REDIRECT_GROUP);
						   }else if(isInsert == 2){
							   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Group Already Exists.");
							   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
							   return new ModelAndView(RedirectScreenConstants.REDIRECT_GROUP);
						   }
					   }catch(Exception e){
							   LOGGER.error("Error in add Group -->  "+e);
					   }
					   
			   }
			   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Add Group.");
			   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
			   return new ModelAndView(RedirectScreenConstants.REDIRECT_GROUP);
		}
		
		/**
		 *  This method used to update Group.
		 * @return
		 */
		@RequestMapping(value = "/update", method = RequestMethod.GET)
		public ModelAndView update(@RequestParam("groupId") String groupId, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response, Model model) {
				   if(checkUserAlreadyLogin(request, response)){
					   if(StringUtils.isNotEmpty(groupId)){
						    redirectAttribute.addFlashAttribute(Constants.GROUP, groupService.getGroupById(groupId));
						    redirectAttribute.addFlashAttribute(Constants.ACTION, Constants.UPDATE);
							return new ModelAndView(RedirectScreenConstants.REDIRECT_GROUP);
				       }
					   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in update Group.");
					   return new ModelAndView(RedirectScreenConstants.REDIRECT_GROUP);
				   }
				   return new ModelAndView("redirect:/");
		}
		
		/**
		 * This method used to update Group.
		 * @return
		 */
		@RequestMapping(value = "/edit", method = RequestMethod.POST)
		public ModelAndView edit(@ModelAttribute("groupDTO") GroupDTO groupDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)){
					   try{
						   groupDTO = setPasswordCharacteristics(groupDTO);
						   int isUpdate = groupService.updateGroup(groupDTO);
						   if(isUpdate > 0){
							   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Group Updated Successfully.");
							   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
							   return new ModelAndView(RedirectScreenConstants.REDIRECT_GROUP);
						   }
					   }catch(Exception e){
						   LOGGER.error("Error in update Group -->  "+e);
					   }
			   }
			   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Update Group.");
			   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
			   return new ModelAndView(RedirectScreenConstants.REDIRECT_GROUP);
		}
		
		/**
		 *  This method used to delete Group
		 */
		@RequestMapping(value = "/delete", method = RequestMethod.POST)
		public ModelAndView delete(@ModelAttribute("groupDTO") GroupDTO groupDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)){
					   try{
						   int isInsert = groupService.deleteGroup(groupDTO);
						   if(isInsert > 0){
							   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Group Deleted Successfully.");
							   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
							   return new ModelAndView(RedirectScreenConstants.REDIRECT_GROUP);
						   }
					   }catch(Exception e){
						   LOGGER.error("Error in delete Group -->  "+e);
					   }
			   }
			   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in delete Group.");
			   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
			   return new ModelAndView(RedirectScreenConstants.REDIRECT_GROUP);
		}
		
		@RequestMapping(value = "/detail/{groupId}", method = RequestMethod.POST)
		public ModelAndView detail(@PathVariable("groupId") String groupId, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			       ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.USER_GROUP_MODAL));
				   if(checkUserAlreadyLogin(request, response)){
					   request.setAttribute(Constants.GROUP, groupService.getGroupById(groupId));
				   }
				   return view;
		}

		private GroupDTO setPasswordCharacteristics(GroupDTO groupDTO){
			    if(null != groupDTO && null != groupDTO.getPasswordCharacteristics()){
			    	   for (String passChar : groupDTO.getPasswordCharacteristics()) {
						      if(passChar.contains(Constants.A)){
						    	  groupDTO.setIsAlphaPassword(Constants.Y);
						      }else if(passChar.contains(Constants.N)){
						    	  groupDTO.setIsNumberPassword(Constants.Y);
						      }else if(passChar.contains(Constants.S)){
						    	  groupDTO.setIsSpecialSymbolPass(Constants.Y);
						      }
					   }
			    }
			    return groupDTO;
		}

}
