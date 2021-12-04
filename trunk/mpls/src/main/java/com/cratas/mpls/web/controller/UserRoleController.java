package com.cratas.mpls.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
import com.cratas.mpls.service.IMenuService;
import com.cratas.mpls.service.IUserRoleService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.GroupPrivilegeRequestDTO;
import com.cratas.mpls.web.dto.MenuDTO;
import com.cratas.mpls.web.dto.SubMenuDTO;
import com.cratas.mpls.web.dto.UserDTO;
import com.cratas.mpls.web.dto.UserRoleDTO;

/**
 * 
 * @author mukesh
 *
 */
@Controller
@RequestMapping("/user/role")
public class UserRoleController extends BaseController{
	
		private final static Logger LOGGER = LoggerFactory.getLogger(GroupController.class);
		   
	    @Autowired
		private IUtilityService utilityService;
	    
	    @Autowired
	    private IUserRoleService userRoleService;
	    
	    @Autowired
	    private IGroupService groupService;
	    
	    @Autowired
	    private IMenuService menuService;
	    
	    @RequestMapping(value = "/view", method = RequestMethod.GET)
		public ModelAndView userRoleView(HttpServletRequest request, HttpServletResponse response, Model model) {
			   if(checkUserAlreadyLogin(request, response)){
				    UserDTO userDTO = getSessionUserObject(request);
					ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.USER_ROLE));
					request.setAttribute(Constants.USER_ROLE_LIST, userRoleService.getUserRole(userDTO.getSystemId()));
					request.setAttribute(Constants.GROUP_LIST, groupService.getActiveGroupsById(userDTO.getSystemId()));
					request.setAttribute(Constants.MENU_LIST, menuService.getMenuList());
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
		 * This method used to save User Role.
		 * @return
		 */
		@RequestMapping(value = "/add", method = RequestMethod.POST)
		public ModelAndView add(@ModelAttribute("GroupPrivilegeRequestDTO") GroupPrivilegeRequestDTO groupPrivilegeRequestDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)){
					   try{
						   boolean isInsert = saveUserGroupPrivilege(groupPrivilegeRequestDTO, request, Constants.ADD);
						   if(isInsert){
							   userRoleService.addUserNotification(getSessionUserObject(request), groupPrivilegeRequestDTO);
							   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "User Role Added Successfully.");
							   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
							   return new ModelAndView(RedirectScreenConstants.REDIRECT_USER_ROLE);
						   }
					   }catch(Exception e){
							   LOGGER.error("Error in Add User Role -->  "+e);
					   }
					   
			   }
			   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Add User Role.");
			   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
				   return new ModelAndView(RedirectScreenConstants.REDIRECT_USER_ROLE);
		}
		
		/**
		 *  This method used to update User Role.
		 * @return
		 */
		@RequestMapping(value = "/update", method = RequestMethod.GET)
		public ModelAndView update(@RequestParam("groupId") String groupId, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response, Model model) {
			   if(checkUserAlreadyLogin(request, response)){
				   if(StringUtils.isNotEmpty(groupId)){
					    List<UserRoleDTO> userRoleList = userRoleService.getUserRoleById(groupId);
					    if(userRoleList.size() > 0){
					       redirectAttribute.addFlashAttribute(Constants.GROUP_PRIVILEGE, mappingGroupPrivilege(userRoleList));
					       redirectAttribute.addFlashAttribute(Constants.USER_ROLE, userRoleList.get(0));
					    }
					    redirectAttribute.addFlashAttribute(Constants.ACTION, Constants.UPDATE);
						return new ModelAndView(RedirectScreenConstants.REDIRECT_USER_ROLE);
			       }
				   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in update User Role.");
				   return new ModelAndView(RedirectScreenConstants.REDIRECT_USER_ROLE);
			   }
			   return new ModelAndView("redirect:/");
		}
		
		/**
		 * This method used to update processor.
		 * @return
		 */
		@RequestMapping(value = "/edit", method = RequestMethod.POST)
		public ModelAndView edit(@ModelAttribute("GroupPrivilegeRequestDTO") GroupPrivilegeRequestDTO groupPrivilegeRequestDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)){
					   try{
						   List<UserRoleDTO> userRoleList = userRoleService.getUserRoleById(groupPrivilegeRequestDTO.getGroupId());
						   if(userRoleList.size() > 0){
							   UserRoleDTO userRoleDTO = userRoleList.get(0);
							   groupPrivilegeRequestDTO.setCreatedBy(userRoleDTO.getCreatedBy());
							   groupPrivilegeRequestDTO.setInsertTimeStamp(userRoleDTO.getInsertTimeStamp());
						   }
						   userRoleService.deleteGroupPrivilege(groupPrivilegeRequestDTO.getGroupId());
						   boolean isUpdate = saveUserGroupPrivilege(groupPrivilegeRequestDTO, request, Constants.UPDATE);
						   if(isUpdate){
							   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "User Role Updated Successfully.");
							   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
							   return new ModelAndView(RedirectScreenConstants.REDIRECT_USER_ROLE);
						   }
					   }catch(Exception e){
						   LOGGER.error("Error in update User Role -->  "+e);
					   }
			   }
			   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Update User Role.");
			   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
			   return new ModelAndView(RedirectScreenConstants.REDIRECT_USER_ROLE);
		}
		
		/**
		 *  This method used to delete User Role
		 */
		@RequestMapping(value = "/delete", method = RequestMethod.POST)
		public ModelAndView delete(@ModelAttribute("userRoleDTO") UserRoleDTO userRoleDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)){
					   try{
						   int isInsert = userRoleService.deleteUserRole(userRoleDTO);
						   if(isInsert > 0){
							   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "User Role Deleted Successfully.");
							   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
							   return new ModelAndView(RedirectScreenConstants.REDIRECT_USER_ROLE);
						   }
					   }catch(Exception e){
						   LOGGER.error("Error in delete User Role -->  "+e);
					   }
			   }
			   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in delete User Role.");
			   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
			   return new ModelAndView(RedirectScreenConstants.REDIRECT_USER_ROLE);
		}
		
		/**
		 *  This method used to delete User Role
		 */
		@RequestMapping(value = "/detail/{groupId}", method = RequestMethod.POST)
		public ModelAndView detail(@PathVariable("groupId") String groupId, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
		       ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.USER_ROLE_MODAL));
			   if(checkUserAlreadyLogin(request, response)){
					   try{
						   List<UserRoleDTO> userRoleList =  userRoleService.getUserRoleById(groupId);
						   request.setAttribute(Constants.USER_ROLE_LIST, userRoleList);
					   }catch(Exception e){
						   LOGGER.error("Error in Get User Role Detail -->  "+e);
					   }
			   }
			   return view;
		}
		
		private boolean saveUserGroupPrivilege(GroupPrivilegeRequestDTO groupPrivilegeRequestDTO, HttpServletRequest request, String action){
			    if(null != groupPrivilegeRequestDTO){
			    	  List<String> groupPrivilegeList = groupPrivilegeRequestDTO.getGroupPrivilegeMenu();
			    	  for (String menus : groupPrivilegeList) {
					      if(StringUtils.isNotEmpty(menus) && menus.contains(Constants.SUB_MENU)){
					    	  saveSubMenuGroupPrivilege(groupPrivilegeRequestDTO, request, menus, action);
					      }else{
					    	  saveMenuGroupPrivilege(groupPrivilegeRequestDTO, request, menus, action);
					     }
					  }
			    	  return true;
			    }
			    return false;
		}
		
		private void saveSubMenuGroupPrivilege(GroupPrivilegeRequestDTO groupPrivilegeRequestDTO, HttpServletRequest request, String menus, String action){
			    int subMenuId = utilityService.convertStringToInt(menus.replace(Constants.SUB_MENU, Constants.BLANK));
	  	        SubMenuDTO subMenuDTO = menuService.getSubMenu(subMenuId);
	  	        if(null != subMenuDTO){
	  	    	     UserRoleDTO userRoleDTO = getUserRole(groupPrivilegeRequestDTO, subMenuDTO.getParentMenuId(), subMenuDTO.getId());
		    	     userRoleDTO.setIsAdd(utilityService.convertStringToInt(request.getParameter(Constants.IS_ADD_SUBMENU + subMenuDTO.getId())));
		    	     userRoleDTO.setIsUpdate(utilityService.convertStringToInt(request.getParameter(Constants.IS_UPDATE_SUBMENU + subMenuDTO.getId())));
		    	     userRoleDTO.setIsDelete(utilityService.convertStringToInt(request.getParameter(Constants.IS_DELETE_SUBMENU + subMenuDTO.getId())));
		    	     userRoleDTO.setIsMaskField(utilityService.convertStringToInt(request.getParameter(Constants.IS_MASK_SUBMENU + subMenuDTO.getId())));
		    	     if(action.equals(Constants.ADD)){
		    	    	 userRoleDTO.setInsertTimeStamp(new Date());
		    	    	 userRoleDTO.setCreatedBy(groupPrivilegeRequestDTO.getCreatedBy());
		    	     }else{
		    	    	 userRoleDTO.setUpdateTimeStamp(new Date());
		    	    	 userRoleDTO.setCreatedBy(groupPrivilegeRequestDTO.getCreatedBy());
		    	    	 userRoleDTO.setUpdatedBy(groupPrivilegeRequestDTO.getUpdatedBy());
		    	     }
		    	     userRoleService.saveUserRole(userRoleDTO);
	  	       }
		}
		
		
		private void saveMenuGroupPrivilege(GroupPrivilegeRequestDTO groupPrivilegeRequestDTO, HttpServletRequest request, String menus, String action){
			    MenuDTO menuDTO = menuService.getMenu(utilityService.convertStringToInt(menus));
	    	    if(null != menuDTO){
	    		      UserRoleDTO userRoleDTO = getUserRole(groupPrivilegeRequestDTO, menuDTO.getMenuId(), 0);
		    		  userRoleDTO.setIsAdd(utilityService.convertStringToInt(request.getParameter(Constants.IS_ADD_MENU + menuDTO.getMenuId())));
			    	  userRoleDTO.setIsUpdate(utilityService.convertStringToInt(request.getParameter(Constants.IS_UPDATE_MENU + menuDTO.getMenuId())));
			    	  userRoleDTO.setIsDelete(utilityService.convertStringToInt(request.getParameter(Constants.IS_DELETE_MENU + menuDTO.getMenuId())));
			    	  userRoleDTO.setIsMaskField(utilityService.convertStringToInt(request.getParameter(Constants.IS_MASK_MENU + menuDTO.getMenuId())));
			    	  if(action.equals(Constants.ADD)){
		    	    	 userRoleDTO.setInsertTimeStamp(new Date());
		    	    	 userRoleDTO.setCreatedBy(groupPrivilegeRequestDTO.getCreatedBy());
		    	      }else{
		    	    	 userRoleDTO.setUpdateTimeStamp(new Date());
		    	    	 userRoleDTO.setCreatedBy(groupPrivilegeRequestDTO.getCreatedBy());
		    	    	 userRoleDTO.setUpdatedBy(groupPrivilegeRequestDTO.getUpdatedBy());
		    	      }
			    	  userRoleService.saveUserRole(userRoleDTO);
	    	    }
	     }
		
		private UserRoleDTO getUserRole(GroupPrivilegeRequestDTO groupPrivilegeRequestDTO, int menuId, int subMenuId){
				UserRoleDTO userRoleDTO = new UserRoleDTO();
	   	        userRoleDTO.setGroupId(groupPrivilegeRequestDTO.getGroupId());
	   	        userRoleDTO.setMenuId(menuId);
	   	        userRoleDTO.setSubMenuId(subMenuId);
	   	        return userRoleDTO;
		}
		
		private Map<String, UserRoleDTO> mappingGroupPrivilege(List<UserRoleDTO> userRoleList){
			    Map<String, UserRoleDTO> userRoleMap = new HashMap<>();
			    for (UserRoleDTO userRoleDTO : userRoleList) {
			    	 if(userRoleDTO.getSubMenuId() == 0){
			    	      userRoleMap.put(Constants.MENU + userRoleDTO.getMenuId(), userRoleDTO);
			    	 }else{
			    		  userRoleMap.put(Constants.SUB_MENU + userRoleDTO.getSubMenuId(), userRoleDTO);
			    	 }
				}
			    return userRoleMap;
		}

}
