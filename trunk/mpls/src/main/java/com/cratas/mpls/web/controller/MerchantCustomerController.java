package com.cratas.mpls.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.constant.PageConstant;
import com.cratas.mpls.service.IAppPropertiesService;
import com.cratas.mpls.service.IMerchantMappingService;
import com.cratas.mpls.service.IMerchantService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.MerchantDTO;
import com.cratas.mpls.web.dto.UserDTO;
/**
 * 
 * @author bhupendra
 *
 */
@Controller
@RequestMapping("/merchant/customer")
public class MerchantCustomerController extends BaseController{

	   private final static Logger LOGGER = LoggerFactory.getLogger(MerchantProfileController.class);
		
	   @Autowired
	   private IUtilityService utilityService;
		
	   @Autowired
	   private IMerchantService merchantService;
	
	   @Autowired
	   private IMerchantMappingService merchantMappingService;
	   
	   @Autowired
	   private IAppPropertiesService appProperiesService;
		
	   @RequestMapping(value = "/view", method = RequestMethod.GET)
	   public ModelAndView merchantCustomer(HttpServletRequest request, HttpServletResponse response, Model model) {
			  if(checkUserAlreadyLogin(request, response)){
					ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.MERCHANT_CUSTOMER));
					UserDTO userDTO = getSessionUserObject(request);
					setMerchantRequestAttribute(request, userDTO.getMerchant());
					return view;
			  }
			  return new ModelAndView("redirect:/");
		}
		
	   @RequestMapping(value = "/details/{mId}", method = RequestMethod.POST)
	   public ModelAndView merchantCustomerDetails(@PathVariable("mId") String mId, HttpServletRequest request, HttpServletResponse response) {
	    	  ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.MERCHANT_CUSTOMER_DETAILS));
	    	  if(StringUtils.isNotEmpty(mId)) {	
	    		   int merchantId = utilityService.convertStringToInt(mId) ;
	    		   request.setAttribute(Constants.CUSTOMER_LIST, merchantMappingService.getCustomerByMerchant(merchantId));
				   return view;
	    	  }
	    	  return null;
	    }
		
	    private void setMerchantRequestAttribute(HttpServletRequest request, MerchantDTO merchantDTO){
	    	   request.setAttribute(Constants.CUSTOMER_ID_PREFIX, appProperiesService.getSystemParamValueByName(Constants.CUSTOMER_ID_PREFIX));
			    if(null != merchantDTO){
				     request.setAttribute(Constants.CUSTOMER_LIST, merchantMappingService.getCustomerByMerchant(merchantDTO.getId()));
			    }else{
			    	LOGGER.info("Login From Admin User");
					List<MerchantDTO> merchantList = merchantService.getAllMerchant();
					request.setAttribute(Constants.MERCHANT_LIST, merchantList);
					if(!merchantList.isEmpty()){
						MerchantDTO merchant = merchantList.get(0);
						request.setAttribute(Constants.CUSTOMER_LIST, merchantMappingService.getCustomerByMerchant(merchant.getId()));
				    }
			   }
	    }
}
