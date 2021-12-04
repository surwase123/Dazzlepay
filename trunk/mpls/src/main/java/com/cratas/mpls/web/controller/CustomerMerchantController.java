package com.cratas.mpls.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.constant.PageConstant;
import com.cratas.mpls.service.IAppPropertiesService;
import com.cratas.mpls.service.IMerchantMappingService;
import com.cratas.mpls.service.IMerchantService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.UserDTO;

@Controller
@RequestMapping("/customer/merchant")
public class CustomerMerchantController extends BaseController{

	   private final static Logger LOGGER = LoggerFactory.getLogger(MerchantMappingController.class);
	   
	   @Autowired
	   private IUtilityService utilityService;
	   
	   @Autowired
	   private IMerchantService merchantService;
	   
	   @Autowired
	   private IAppPropertiesService appPropertiesService;
	   
	   @Autowired
	   private IMerchantMappingService merchantMappingService;
	   
	   @RequestMapping(value = "/view", method = RequestMethod.GET)
	   public ModelAndView searchStore(HttpServletRequest request, HttpServletResponse response, Model model) {
		      if (checkUserAlreadyLogin(request, response)) {
				  ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.CUSTOMER_MERCHANT));
				  UserDTO userDTO = getSessionUserObject(request);
				  request.setAttribute(Constants.CUSTOMER_MERCHANT_MAPPING_LIST, merchantService.customerMerchantMappingList(userDTO.getId()));
				  request.setAttribute(Constants.MERCHANT_ID_PREFIX, appPropertiesService.getSystemParamValueByName(Constants.MERCHANT_ID_PREFIX));
				  return view;
		      }
		      return new ModelAndView("redirect:/");
		}
	   
	    @RequestMapping(value = "/deleteMerchant", method = RequestMethod.POST)
	    public @ResponseBody String mappingMerchant(@RequestParam("merchantId") String merchantId, @RequestParam("cId") String cId, HttpServletRequest request, HttpServletResponse response, Model model) {
	    	   if (checkUserAlreadyLogin(request, response)) {
	    		   try{
	    			   UserDTO userDTO = getSessionUserObject(request);
					   if(StringUtils.isNotEmpty(merchantId)){
				    	  int isInserted = merchantMappingService.deleteMerchantMapping(userDTO.getLoginId(), merchantId, cId);
				    	  if(isInserted > 0) {
				    		  return String.valueOf(isInserted);
				    	  }
					   }
	    		   }catch(Exception e){
	    			   LOGGER.error("Error in Delete Merchant with customert --> "+e.getMessage());
				       return String.valueOf(0);
	    		   }
	    	   }
	    	   return Constants.BLANK;
		}
}
