package com.cratas.mpls.web.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.constant.PageConstant;
import com.cratas.mpls.common.utility.constant.RedirectScreenConstants;
import com.cratas.mpls.service.IAppPropertiesService;
import com.cratas.mpls.service.ILoyaltyCashbackService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.LoyaltyCashbackDTO;
import com.cratas.mpls.web.dto.MerchantDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author bhupendra
 *
 */
@Controller
@RequestMapping("/merchant/loyaltyCashback")
public class LoyaltyCashbackController extends BaseController{

		private final static Logger LOGGER = LoggerFactory.getLogger(StateController.class);
		
		@Autowired
		private IUtilityService utilityService;
		
		@Autowired
		private ILoyaltyCashbackService loyaltyCashbackService;
		
		@Autowired
		private IAppPropertiesService appPropertiesService; 
		
		@RequestMapping(value = "/view", method = RequestMethod.GET)
		public ModelAndView loyaltyCashback(HttpServletRequest request, HttpServletResponse response, Model model) {
			   if(checkUserAlreadyLogin(request, response)){
					ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.LOYALTY_CASHBACK));
					MerchantDTO merchant = getSessionUserObject(request).getMerchant();
					if(merchant != null){
					    request.setAttribute(Constants.LOYALTY_CASHBACK_LIST, loyaltyCashbackService.getLoyaltyCashbackByMId(merchant.getId()));
					}
					request.setAttribute(Constants.TRANS_CASHBACK_TYPE, appPropertiesService.getSystemParamValueByName(Constants.TRANS_CASHBACK_TYPE));
					Map<String, Object> requestMap = ((Model) model).asMap();
			        if(null != requestMap){
			        	request.setAttribute(Constants.MESSAGE, requestMap.get(Constants.MESSAGE));
			        	request.setAttribute(Constants.IS_SUCCESS, requestMap.get(Constants.IS_SUCCESS));
			        	request.setAttribute(Constants.LOYALTY_CASHBACK, requestMap.get(Constants.LOYALTY_CASHBACK));
			        	request.setAttribute(Constants.ACTION, requestMap.get(Constants.ACTION));
			        }
					return view;
			   }
			   return new ModelAndView("redirect:/");
		}
		
		@RequestMapping(value = "/add", method = RequestMethod.POST)
		public ModelAndView add(@ModelAttribute("loyaltyCashbackDTO") LoyaltyCashbackDTO loyaltyCashbackDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)){
				   try{
					   UserDTO userDTO = getSessionUserObject(request);
					   int isInsert = loyaltyCashbackService.saveLoyaltyCashback(loyaltyCashbackDTO, userDTO);
					   if(isInsert == 1){
						   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Loyalty Cashback Added Successfully.");
						   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
						   return new ModelAndView(RedirectScreenConstants.REDIRECT_LOYALTY_CASHBACK);
					   }else if(isInsert == 2){
						   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Loyalty Cashback Offer Already Exists.");
						   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
						   return new ModelAndView(RedirectScreenConstants.REDIRECT_LOYALTY_CASHBACK);
					   }else if(isInsert == 3){
						   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Only One Loyalty Cashback Offer Per Merchant.");
						   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
						   return new ModelAndView(RedirectScreenConstants.REDIRECT_LOYALTY_CASHBACK);
					   }
				   }catch(Exception e){
						   LOGGER.error("Error in save Loyalty Cashback -->  "+e);
				   }
			   }
			   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Add Loyalty Cashback.");
			   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
			   return new ModelAndView(RedirectScreenConstants.REDIRECT_LOYALTY_CASHBACK);
		}
		
		@RequestMapping(value = "/update", method = RequestMethod.GET)
		public ModelAndView update(@RequestParam("id") int id, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response, Model model) {
			   if(checkUserAlreadyLogin(request, response)){
				   if(id > 0){
					    redirectAttribute.addFlashAttribute(Constants.LOYALTY_CASHBACK, loyaltyCashbackService.getLoyaltyCashbackById(id));
					    redirectAttribute.addFlashAttribute(Constants.ACTION, Constants.UPDATE);
						return new ModelAndView(RedirectScreenConstants.REDIRECT_LOYALTY_CASHBACK);
			       }
				   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in update Loyalty Cashback.");
				   return new ModelAndView(RedirectScreenConstants.REDIRECT_LOYALTY_CASHBACK);
			   }
			   return new ModelAndView("redirect:/");
		}
		
		@RequestMapping(value = "/edit", method = RequestMethod.POST)
		public ModelAndView edit(@ModelAttribute("loyaltyCashbackDTO") LoyaltyCashbackDTO loyaltyCashbackDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)){
					   try{
						   int isUpdate = loyaltyCashbackService.updateLoyaltyCashback(loyaltyCashbackDTO);
						   if(isUpdate > 0){
							   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Loyalty Cashback Updated Successfully.");
							   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
							   return new ModelAndView(RedirectScreenConstants.REDIRECT_LOYALTY_CASHBACK);
						   }
					   }catch(Exception e){
						   LOGGER.error("Error in update Loyalty Cashback -->  "+e);
					   }
			   }
			   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Update Loyalty Cashback.");
			   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
			   return new ModelAndView(RedirectScreenConstants.REDIRECT_LOYALTY_CASHBACK);
		}
		
		@RequestMapping(value = "/delete", method = RequestMethod.POST)
		public ModelAndView delete(@ModelAttribute("loyaltyCashbackDTO") LoyaltyCashbackDTO loyaltyCashbackDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)){
					   try{
						   int isDeleted = loyaltyCashbackService.deleteLoyaltyCashback(loyaltyCashbackDTO);
						   if(isDeleted > 0){
							   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Loyalty Cashback Deleted Successfully.");
							   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS); 
							   return new ModelAndView(RedirectScreenConstants.REDIRECT_LOYALTY_CASHBACK);
						   }
					   }catch(Exception e){
						   LOGGER.error("Error in delete Loyalty Cashback -->  "+e);
					   }
			   }
			   redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in delete Loyalty Cashback.");
			   redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE); 
			   return new ModelAndView(RedirectScreenConstants.REDIRECT_LOYALTY_CASHBACK);
		}
		
		@RequestMapping(value = "/getMerchantOffers/{mId}", method = RequestMethod.GET)
		public ModelAndView getMerchantOffers(@PathVariable("mId") String mId, @RequestParam("requestType") String requestType, HttpServletRequest request, HttpServletResponse response) {
			   ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.MERCHANT_OFFERS));
			   if(StringUtils.isNotEmpty(mId)){
				   List<LoyaltyCashbackDTO> cashbackOfferList = loyaltyCashbackService.getLoyaltyCashbackByMId(utilityService.convertStringToInt(mId));
				   request.setAttribute(Constants.LOYALTY_CASHBACK_LIST, cashbackOfferList);
				   request.setAttribute(Constants.REQUEST_TYPE, requestType);
			   }
			   return view;
		}
		
		@RequestMapping(value = "/applyCoupon", method = RequestMethod.POST)
		public @ResponseBody String applyCoupon(@RequestParam("mId") int mId, @RequestParam("cId") int cId, @RequestParam("offerCode") String offerCode, @RequestParam("amount") double amount, HttpServletRequest request, HttpServletResponse response) {
			   if(StringUtils.isNotEmpty(offerCode) && amount > 0){
				   LoyaltyCashbackDTO loyaltyCashback = loyaltyCashbackService.getLoyaltyCashbackByCode(offerCode);
				   if(null != loyaltyCashback){
					     String isValidOffer = loyaltyCashbackService.validateMerchantOffer(mId, cId, amount, loyaltyCashback);
					     if(isValidOffer.equals(Constants.SUCCESS)){
					    	  double cashbackAmt = loyaltyCashbackService.getOfferCashbackAmt(amount, loyaltyCashback);
					    	  return Constants.SUCCESS + Constants.HASH + utilityService.decimalFormat(cashbackAmt);
					     }
					     return isValidOffer;
				   }
			   }
			   return Constants.FAILURE;
		}
		
}
