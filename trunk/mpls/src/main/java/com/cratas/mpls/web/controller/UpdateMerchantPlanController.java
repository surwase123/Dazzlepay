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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.constant.PageConstant;
import com.cratas.mpls.common.utility.constant.RedirectScreenConstants;
import com.cratas.mpls.service.IMerchantPlanService;
import com.cratas.mpls.service.IMerchantService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.MerchantDTO;

@Controller
@RequestMapping("/update/merchantPlan")
public class UpdateMerchantPlanController extends BaseController{
	
	    private final static Logger LOGGER = LoggerFactory.getLogger(MerchantController.class);
		
	    @Autowired
	    private IUtilityService utilityService;
	
	    @Autowired
	    private IMerchantService merchantService;
	    
	    @Autowired
		private IMerchantPlanService merchantPlanService;
	    
	    @RequestMapping(value = "/view", method = RequestMethod.GET)
		public ModelAndView merchantPlan(HttpServletRequest request, HttpServletResponse response, Model model) {
			   if(checkUserAlreadyLogin(request, response)){
					ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.MERCHANT_PLAN_UPGRADE));
					request.setAttribute(Constants.MERCHANT_LIST, merchantService.getAllMerchant());
					request.setAttribute(Constants.MERCHANT_VERSION_LIST, merchantPlanService.getMerchantPlan());
					Map<String, Object> requestMap = ((Model) model).asMap();
			        if(null != requestMap){
			        	request.setAttribute(Constants.MESSAGE, requestMap.get(Constants.MESSAGE));
			        	request.setAttribute(Constants.IS_SUCCESS, requestMap.get(Constants.IS_SUCCESS));
			        	request.setAttribute(Constants.MERCHANT, requestMap.get(Constants.MERCHANT));
			        	request.setAttribute(Constants.ACTION, requestMap.get(Constants.ACTION));
			        }
					return view;
			   }
			   return new ModelAndView("redirect:/");
		}
	    
		@RequestMapping(value = "/submit", method = RequestMethod.POST)
		public ModelAndView update(@ModelAttribute("merchantRequest") MerchantDTO merchantRequest, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response, Model model) {
			   if(checkUserAlreadyLogin(request, response)){
				   try{
					   int isUpdate = merchantService.updateMerchantPlan(merchantRequest);
					   if(isUpdate == 1){
						   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Merchant Plan Updated Successfully.");
						   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
						   return new ModelAndView(RedirectScreenConstants.REDIRECT_UPDATE_MERCHANT_PLAN);
					   }
				   }catch(Exception e){
					   LOGGER.error("Error in update merchantPlan -->  "+e);
				   }
			   }
			   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Update Merchant Plan.");
			   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
			   return new ModelAndView(RedirectScreenConstants.REDIRECT_UPDATE_MERCHANT_PLAN);
		}
		
	        

}
