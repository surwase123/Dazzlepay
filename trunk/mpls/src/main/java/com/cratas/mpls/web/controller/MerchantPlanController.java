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
import com.cratas.mpls.service.IMerchantPlanService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.MerchantPlanDTO;

/**
 * 
 * @author mukesh
 *
 */
@Controller
@RequestMapping("/merchantPlan")
public class MerchantPlanController extends BaseController{
	
	    private final static Logger LOGGER = LoggerFactory.getLogger(MerchantPlanController.class);
		
		@Autowired
		private IUtilityService utilityService;
		
		@Autowired
		private IMerchantPlanService merchantPlanService;
		
		@RequestMapping(value = "/view", method = RequestMethod.GET)
		public ModelAndView merchantVersion(HttpServletRequest request, HttpServletResponse response, Model model) {
			   if(checkUserAlreadyLogin(request, response)){
					ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.MERCHANT_VERSION));
					request.setAttribute(Constants.MERCHANT_VERSION_LIST, merchantPlanService.getMerchantPlan());
					Map<String, Object> requestMap = ((Model) model).asMap();
			        if(null != requestMap){
			        	request.setAttribute(Constants.MESSAGE, requestMap.get(Constants.MESSAGE));
			        	request.setAttribute(Constants.IS_SUCCESS, requestMap.get(Constants.IS_SUCCESS));
			        	request.setAttribute(Constants.MERCHANT_VERSION, requestMap.get(Constants.MERCHANT_VERSION));
			        	request.setAttribute(Constants.ACTION, requestMap.get(Constants.ACTION));
			        }
					return view;
			   }
			   return new ModelAndView("redirect:/");
		}
		
		@RequestMapping(value = "/add", method = RequestMethod.POST)
		public ModelAndView add(@ModelAttribute("merchantVersion") MerchantPlanDTO merchantVersion, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)){
				   try{
					   int isInsert = merchantPlanService.saveMerchantPlan(merchantVersion);
					   if(isInsert == 1){
						   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Merchant Plan Added Successfully.");
						   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
						   return new ModelAndView(RedirectScreenConstants.REDIRECT_MERCHANT_VERSION);
					   }else if(isInsert == 2){
						   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Merchant Plan Already Exists.");
						   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
						   return new ModelAndView(RedirectScreenConstants.REDIRECT_MERCHANT_VERSION);
					   }
				   }catch(Exception e){
						   LOGGER.error("Error in save Merchant Version -->  "+e);
				   }
			   }
			   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Add Merchant Plan.");
			   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
			   return new ModelAndView(RedirectScreenConstants.REDIRECT_MERCHANT_VERSION);
		}
		
		@RequestMapping(value = "/update", method = RequestMethod.GET)
		public ModelAndView update(@RequestParam("id") int id, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response, Model model) {
			   if(checkUserAlreadyLogin(request, response)){
				   if(id > 0){
					    redirectAttribute.addFlashAttribute(Constants.MERCHANT_VERSION, merchantPlanService.getMerchantPlanById(id));
					    redirectAttribute.addFlashAttribute(Constants.ACTION, Constants.UPDATE);
						return new ModelAndView(RedirectScreenConstants.REDIRECT_MERCHANT_VERSION);
			       }
				   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in update Merchant Plan.");
				   return new ModelAndView(RedirectScreenConstants.REDIRECT_MERCHANT_VERSION);
			   }
			   return new ModelAndView("redirect:/");
		}
		
		@RequestMapping(value = "/edit", method = RequestMethod.POST)
		public ModelAndView edit(@ModelAttribute("merchantVersion") MerchantPlanDTO merchantVersion, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)){
				   try{
					   int isUpdate = merchantPlanService.updateMerchantPlan(merchantVersion);
					   if(isUpdate > 0){
						   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Merchant Plan Updated Successfully.");
						   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
						   return new ModelAndView(RedirectScreenConstants.REDIRECT_MERCHANT_VERSION);
					   }
				   }catch(Exception e){
					   LOGGER.error("Error in update Merchant Version -->  "+e);
				   }
			   }
			   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Update Merchant Plan.");
			   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
			   return new ModelAndView(RedirectScreenConstants.REDIRECT_MERCHANT_VERSION);
		}
		
		@RequestMapping(value = "/delete", method = RequestMethod.POST)
		public ModelAndView delete(@ModelAttribute("merchantVersion") MerchantPlanDTO merchantVersion, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)){
				   try{
					   int isInsert = merchantPlanService.deleteMerchantPlan(merchantVersion);
					   if(isInsert > 0){
						   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Merchant Plan Deleted Successfully.");
						   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
						   return new ModelAndView(RedirectScreenConstants.REDIRECT_MERCHANT_VERSION);
					   }
				   }catch(Exception e){
					   LOGGER.error("Error in delete Merchant Version -->  "+e);
				   }
			   }
			   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in delete Merchant Plan.");
			   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
			   return new ModelAndView(RedirectScreenConstants.REDIRECT_MERCHANT_VERSION);
		}
}
