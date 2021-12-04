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
import com.cratas.mpls.web.dto.MerchantDTO;
import com.cratas.mpls.web.dto.MerchantMappingDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author mukesh
 *
 */
@Controller
@RequestMapping("/search/store/")
public class MerchantMappingController extends BaseController{
	
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
				  ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.SEARCH_STORE));
				  request.setAttribute(Constants.AVAILABLE_AREA_LIST, merchantService.getMerchantAddress());
				  int displayMerchantCount = utilityService.convertStringToInt(appPropertiesService.getSystemParamValueByName(Constants.DISPLAY_MERCHANT_COUNT));
				  request.setAttribute(Constants.RECENT_STORE_LIST, merchantService.recentMerchantList(displayMerchantCount, getSessionUserObject(request)));
				  request.setAttribute(Constants.MERCHANT_ID_PREFIX, appPropertiesService.getSystemParamValueByName(Constants.MERCHANT_ID_PREFIX));
				  return view;
		      }
		      return new ModelAndView("redirect:/");
		}
	   
	   @RequestMapping(value = "/filter", method = RequestMethod.POST)
	   public ModelAndView filterStore( @RequestParam("areaId") String areaId, HttpServletRequest request, HttpServletResponse response, Model model) {
			  if (checkUserAlreadyLogin(request, response)) {
				  ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.FILTER_STORE));
				  request.setAttribute(Constants.MERCHANT_ID_PREFIX, appPropertiesService.getSystemParamValueByName(Constants.MERCHANT_ID_PREFIX));
				  if(StringUtils.isNotEmpty(areaId)){
					   request.setAttribute(Constants.FILTER_STORE_LIST, merchantService.filterMerchantListByArea(areaId, getSessionUserObject(request)));
				  }
				  return view;
			  }
			  return new ModelAndView("redirect:/");
		}
	   
	    @RequestMapping(value = "/mappingMerchant", method = RequestMethod.POST)
	    public @ResponseBody String mappingMerchant(@RequestParam("merchantId") String merchantId, HttpServletRequest request, HttpServletResponse response, Model model) {
	    	   if (checkUserAlreadyLogin(request, response)) {
	    		   try{
					   if(StringUtils.isNotEmpty(merchantId)){
						     UserDTO user = getSessionUserObject(request);
						     MerchantDTO merchant = merchantService.getMerchantByMId(merchantId);
						     if(null != user && null != user.getCustomer() && null != merchant){
						    	  MerchantMappingDTO merchantMapping = merchantMappingService.getMerchantMappingInstance(user.getCustomer(), merchant, user.getLoginId());
						    	  int isInserted = merchantMappingService.saveMerchantMapping(merchantMapping, Constants.MERCHANT);
						    	  return String.valueOf(isInserted);
						     }else{
						    	 return Constants.BLANK;
						     }
						    	 
					   }
	    		   }catch(Exception e){
	    			   LOGGER.error("Error in Add Customer with merchant --> "+e.getMessage());
				       return String.valueOf(0);
	    		   }
	    	   }
	    	   return Constants.BLANK;
		}	    

}
