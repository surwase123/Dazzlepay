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
import com.cratas.mpls.service.ICategoryService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.CategoryDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author bhupendra
 *
 */
@Controller
@RequestMapping("/category")
public class CategoryController extends BaseController{
	
		private final static Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
		
		@Autowired
		private IUtilityService utilityService;
		
		@Autowired
		private ICategoryService categoryService;
		
		@RequestMapping(value = "/view", method = RequestMethod.GET)
		public ModelAndView country(HttpServletRequest request, HttpServletResponse response, Model model) {
			   if(checkUserAlreadyLogin(request, response)){
					ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.CATEGORY));
					request.setAttribute(Constants.CATEGORY_LIST, categoryService.getCategory());
					Map<String, Object> requestMap = ((Model) model).asMap();
			        if(null != requestMap){
			        	request.setAttribute(Constants.MESSAGE, requestMap.get(Constants.MESSAGE));
			        	request.setAttribute(Constants.IS_SUCCESS, requestMap.get(Constants.IS_SUCCESS));
			        	request.setAttribute(Constants.CATEGORY, requestMap.get(Constants.CATEGORY));
			        	request.setAttribute(Constants.ACTION, requestMap.get(Constants.ACTION));
			        }
					return view;
			   }
			   return new ModelAndView("redirect:/");
		}
		
		/**
		 * This method used to add country.
		 * @param countryDTO
		 * @param redirectAttribute
		 * @return
		 */
		@RequestMapping(value = "/add", method = RequestMethod.POST)
		public ModelAndView add(@ModelAttribute("categoryDTO") CategoryDTO categoryDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)){
				   try{
					   UserDTO userDTO = getSessionUserObject(request);
					   int isInsert = categoryService.saveCategory(categoryDTO, userDTO);
					   if(isInsert == 1){
						   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Category Added Successfully.");
						   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
						   return new ModelAndView(RedirectScreenConstants.REDIRECT_CATEGORY);
					   }else if(isInsert == 2){
						   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Category Code Already Exists.");
						   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
						   return new ModelAndView(RedirectScreenConstants.REDIRECT_CATEGORY);
					   }
				   }catch(Exception e){
						   LOGGER.error("Error in save Category -->  "+e);
				   }
					   
			   }
			   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Add Category.");
			   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
			   return new ModelAndView(RedirectScreenConstants.REDIRECT_CATEGORY);
		}
		
		/**
		 * 
		 * @param model
		 * @return
		 */
		@RequestMapping(value = "/update", method = RequestMethod.GET)
		public ModelAndView update(@RequestParam("id") int id, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response, Model model) {
			   if(checkUserAlreadyLogin(request, response)){
				   if(id > 0){
					    redirectAttribute.addFlashAttribute(Constants.CATEGORY, categoryService.getCategoryById(id));
					    redirectAttribute.addFlashAttribute(Constants.ACTION, Constants.UPDATE);
						return new ModelAndView(RedirectScreenConstants.REDIRECT_CATEGORY);
			       }
				   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in update Category.");
				   return new ModelAndView(RedirectScreenConstants.REDIRECT_CATEGORY);
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
		public ModelAndView edit(@ModelAttribute("categoryDTO") CategoryDTO categoryDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)){
					   try{
						   int isUpdate = categoryService.updateCategory(categoryDTO);
						   if(isUpdate > 0){
							   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Category Updated Successfully.");
							   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
							   return new ModelAndView(RedirectScreenConstants.REDIRECT_CATEGORY);
						   }
					   }catch(Exception e){
						   LOGGER.error("Error in update Category -->  "+e);
					   }
			   }
			   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Update Category.");
			   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
			   return new ModelAndView(RedirectScreenConstants.REDIRECT_CATEGORY);
		}
		
		
		/**
		 * This method used to delete country.
		 * @param countryDTO
		 * @param redirectAttribute
		 * @return
		 */
		@RequestMapping(value = "/delete", method = RequestMethod.POST)
		public ModelAndView delete(@ModelAttribute("categoryDTO") CategoryDTO categoryDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)){
					   try{
						   int isInsert = categoryService.deleteCategory(categoryDTO);
						   if(isInsert > 0){
							   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Category Deleted Successfully.");
							   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
							   return new ModelAndView(RedirectScreenConstants.REDIRECT_CATEGORY);
						   }
					   }catch(Exception e){
						   LOGGER.error("Error in delete Category -->  "+e);
					   }
			   }
			   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in delete Category.");
			   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
			   return new ModelAndView(RedirectScreenConstants.REDIRECT_CATEGORY);
		}
}
