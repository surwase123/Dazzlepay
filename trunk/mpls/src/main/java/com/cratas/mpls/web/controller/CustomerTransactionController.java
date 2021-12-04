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
import com.cratas.mpls.service.IAppPropertiesService;
import com.cratas.mpls.service.ICustomerService;
import com.cratas.mpls.service.ICustomerTransactionService;
import com.cratas.mpls.service.ILoyaltyCashbackService;
import com.cratas.mpls.service.IMerchantMappingService;
import com.cratas.mpls.service.IQRCodeService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.CustomerDTO;
import com.cratas.mpls.web.dto.CustomerNotificationDTO;
import com.cratas.mpls.web.dto.CustomerTransactionDTO;
import com.cratas.mpls.web.dto.LoyaltyCashbackDTO;
import com.cratas.mpls.web.dto.MerchantDTO;
import com.cratas.mpls.web.dto.MerchantMappingDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author mukesh
 *
 */
@Controller
@RequestMapping("/customer/transaction")
public class CustomerTransactionController extends BaseController{
		
		private final static Logger LOGGER = LoggerFactory.getLogger(CustomerTransactionController.class);
		
	    @Autowired
	    private IUtilityService utilityService;
	 	
	 	@Autowired
	 	private ICustomerTransactionService ctService;
	 	
	 	@Autowired
		private IMerchantMappingService merchantMappingService;
	 	
	 	@Autowired
		private IAppPropertiesService appPropertiesService;
	 	
	 	@Autowired
	 	private ICustomerService customerService;
	 	
	 	@Autowired
	 	private IQRCodeService qrCodeService;
	 	
	 	@Autowired
	 	private ILoyaltyCashbackService loyaltyCashbackService;
	 
		@RequestMapping(value = "/view", method = RequestMethod.GET)
	    public ModelAndView RegistrationView(HttpServletRequest request, HttpServletResponse response, Model model) {
		       if(checkUserAlreadyLogin(request, response)) {
		            ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.CUSTOMER_TRANSACTION));
		            setRequestAttribute(request);
		            UserDTO userDTO = getSessionUserObject(request);
		            if(null != userDTO.getMerchant()){
		                request.setAttribute(Constants.CUSTOMER_LIST, merchantMappingService.getCustomerByMerchant(userDTO.getMerchant().getId()));
		            }
		            return view;
		        }
		        return new ModelAndView("redirect:/");
	    }
		
		@RequestMapping(value = "/addMoney", method = RequestMethod.POST)
		public @ResponseBody String addMoney(@ModelAttribute("customerTransaction") CustomerTransactionDTO customerTransaction, HttpServletRequest request) {
			   try{
				   customerTransaction = ctService.getCustomerTransactionObj(customerTransaction);
				   customerTransaction = getOfferCodeByMId(customerTransaction);
				   MerchantDTO merchant = merchantService.getMerchantById(customerTransaction.getmId());
				   if(merchant.getWalletBal()==0) {
					   return Constants.PLS_SET_MERCHANT_LIMIT;
				   }else {
					   int isInsert = ctService.topUpTransaction(ctService.getMerchantTransactionObj(customerTransaction), customerTransaction);
				       if(isInsert == 1){
				    	   MerchantMappingDTO mapping = ctService.getCustomerWalletByMerchant(customerTransaction.getcId(), customerTransaction.getmId());
				    	   updateSessionUserObject(request, customerTransaction.getcId());
				    	   return utilityService.decimalFormat(mapping.getWalletBal());
				       }else if(isInsert == 3) {
				    	   return Constants.LIMIT_EXCEEDED;
				       }
				   }
			   }catch(Exception e){
				   LOGGER.error("AddMoney -- Error in Add Money in Customer Wallet -- "+e.getMessage());
			   } 
			   return Constants.FAILURE;
		}
		
		private CustomerTransactionDTO getOfferCodeByMId(CustomerTransactionDTO customerTransaction) {
			List<LoyaltyCashbackDTO> loyaltyCashback = loyaltyCashbackService.getLoyaltyCashbackByMId(customerTransaction.getmId());
			if(!loyaltyCashback.isEmpty()) {
				customerTransaction.setOfferCode(loyaltyCashback.get(0).getOfferCode());
			}
			return customerTransaction;
		}
		
		@RequestMapping(value = "/payBills", method = RequestMethod.POST)
		public @ResponseBody String payMoney(@ModelAttribute("customerTransaction") CustomerTransactionDTO customerTransaction, HttpServletRequest request) {
			   try{
				   customerTransaction = ctService.getCustomerTransactionObj(customerTransaction);
				   MerchantMappingDTO merchantMapping = ctService.getCustomerWalletByMerchant(customerTransaction.getcId(), customerTransaction.getmId());
				   if(merchantMapping.getWalletBal() >=  customerTransaction.getWalletBal()){
					     int isInserted = ctService.payBills(ctService.getMerchantTransactionObj(customerTransaction), customerTransaction);
					     if(isInserted == 1){
					    	 MerchantMappingDTO mapping = ctService.getCustomerWalletByMerchant(customerTransaction.getcId(), customerTransaction.getmId());
					    	 updateSessionUserObject(request, customerTransaction.getcId());
					    	 return utilityService.decimalFormat(mapping.getWalletBal());
					     }
				   }
				   return Constants.INSUFFICIENT_BALANCE;
			   }catch(Exception e){
				   LOGGER.error("PayBills -- Error in Pay Money to Merchant -- "+e.getMessage());
			   } 
			   return Constants.FAILURE;
		}
		
		@RequestMapping(value = "/refund/{transactionId}", method = RequestMethod.POST)
		public @ResponseBody String refundTransaction(@PathVariable("transactionId") String transactionId, @RequestParam("transactionType") String transactionType, HttpServletRequest request) {
			   try{
				   int isInsert = ctService.refundTransaction(transactionId, transactionType);
			       if(isInsert == 1){
			    	   return Constants.SUCCESS;
			       }else if(isInsert == 3) {
			    	   return Constants.LIMIT_EXCEEDED;
			       }
			   }catch(Exception e){
				   LOGGER.error("RefundTransaction -- Error in Refund Transaction -- "+e.getMessage());
			   } 
			   return Constants.FAILURE;
		}
		
		@RequestMapping(value = "/detail/{id}/{transactionId}", method = RequestMethod.GET)
		public ModelAndView transactionDetail(@PathVariable("id") int id, @PathVariable("transactionId") String transactionId, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)) {
				   ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.TRANSACTION_DETAIL)); 
				   if(StringUtils.isNotEmpty(transactionId)) {
					   request.setAttribute(Constants.TRANSACTION_DETAIL, ctService.getCustomerTransDetailById(id));
					   request.setAttribute(Constants.CUSTOMER_ID_PREFIX, appPropertiesService.getSystemParamValueByName(Constants.CUSTOMER_ID_PREFIX));
					   request.setAttribute(Constants.MERCHANT_ID_PREFIX, appPropertiesService.getSystemParamValueByName(Constants.MERCHANT_ID_PREFIX));
					   request.setAttribute(Constants.TRANS_TABLE_ID, id);
					   request.setAttribute(Constants.TRANSACTION_ID, transactionId);
				   }
				   return view;
			   }
			   return new ModelAndView("redirect:/");
		}
		
		@RequestMapping(value = "/generateQRCode", method = RequestMethod.POST)
		public @ResponseBody String generateQRCode(@ModelAttribute("customerTransaction") CustomerTransactionDTO customerTransaction, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   try{
				   customerTransaction = ctService.getCustomerTransactionObj(customerTransaction);
				   String image = qrCodeService.createQRCode(ctService.getMerchantTransactionObj(customerTransaction), customerTransaction);
				   return Constants.BASE64_IMAGE + image;
			   }catch(Exception e){
				   LOGGER.error("GenerateQRCode -- Unable to generate QR Code -- "+e.getMessage());
				   e.printStackTrace();
			   } 
			   return Constants.FAILURE;
	   }
		
	   private void updateSessionUserObject(HttpServletRequest request, int cId){
			   UserDTO user = getSessionUserObject(request);
			   CustomerDTO customer = customerService.getCustomerByCId(cId);
			   if(null != user.getCustomer() && null != customer){
			      user.setCustomer(customer);
			   }
	   }
		
	   private void setRequestAttribute(HttpServletRequest request){
			   request.setAttribute(Constants.CUSTOMER_ID_PREFIX, appPropertiesService.getSystemParamValueByName(Constants.CUSTOMER_ID_PREFIX));
	           request.setAttribute(Constants.MERCHANT_ID_PREFIX, appPropertiesService.getSystemParamValueByName(Constants.MERCHANT_ID_PREFIX));
	   }
	   
	   @RequestMapping(value = "/collectMoney", method = RequestMethod.POST)
		public @ResponseBody String collectMoney(@ModelAttribute("customerNotification") CustomerNotificationDTO customerNotification, HttpServletRequest request) {
			   try{
				   MerchantMappingDTO merchantMapping = ctService.getCustomerWalletByMerchant(customerNotification.getcId(), customerNotification.getmId());
				   if(merchantMapping.getWalletBal() >=  customerNotification.getAmount()){
					     int isInserted = ctService.collectMoney(customerNotification);
						     if(isInserted > 0) {
						    	 return Constants.SUCCESS;
						     }else {
						    	 return Constants.FAILURE;
						     }
				   }
				   return Constants.INSUFFICIENT_BALANCE;
			   }catch(Exception e){
				   LOGGER.error("Error in Pay Money to Merchant -->  "+e);
			   } 
			   return Constants.FAILURE;
		}
		
		@RequestMapping(value = "/refundMoney", method = RequestMethod.POST)
		public @ResponseBody String refundMoney(@ModelAttribute("customerNotification") CustomerNotificationDTO customerNotification, HttpServletRequest request) {
			   try{
				   MerchantMappingDTO merchantMapping = ctService.getCustomerWalletByMerchant(customerNotification.getcId(), customerNotification.getmId());
				   if(merchantMapping.getWalletBal() >=  customerNotification.getAmount()){
					     int isInserted = ctService.refundMoney(customerNotification);
						     if(isInserted > 0) {
						    	 return Constants.SUCCESS;
						     }else {
						    	 return Constants.FAILURE;
						     }
				   }
				   return Constants.INSUFFICIENT_BALANCE;
			   }catch(Exception e){
				   LOGGER.error("Error in Pay Money to Merchant -->  "+e);
			   } 
			   return Constants.FAILURE;
		}
}
