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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.constant.PageConstant;
import com.cratas.mpls.common.utility.constant.RedirectScreenConstants;
import com.cratas.mpls.service.IMerchantEmployeeService;
import com.cratas.mpls.service.IUserService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.MerchantEmployeeDTO;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * 
 * @author bhupendra
 *
 */
@Controller
@RequestMapping("/merchant/employee")
public class MerchantEmployeeController extends BaseController{
	
	    private final static Logger LOGGER = LoggerFactory.getLogger(MerchantEmployeeController.class);
	
		@Autowired
	    private IUtilityService utilityService;
	
		@Autowired
		private IMerchantEmployeeService merchantEmployeeService;
		
		@Autowired
		private IUserService userService;
		
		@RequestMapping(value = "/view", method = RequestMethod.GET)
	    public ModelAndView RegistrationView(HttpServletRequest request, HttpServletResponse response, Model model) {
		       if(checkUserAlreadyLogin(request, response)) {
		            ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.MERCHANT_EMPLOYEE));
		            UserDTO userDTO = getSessionUserObject(request);
		            MerchantEmployeeDTO merchantEmployee = userDTO.getMerchantEmployee();
		            if(merchantEmployee != null) {
		            	request.setAttribute(Constants.MERCHANT_EMPLOYEE_LIST, merchantEmployeeService.getMerchantEmployeeByMId(merchantEmployee.getmId()));
		            }
		            Map<String, Object> requestMap = ((Model) model).asMap();
		            if (null != requestMap) {
		                request.setAttribute(Constants.MESSAGE, requestMap.get(Constants.MESSAGE));
		                request.setAttribute(Constants.IS_SUCCESS, requestMap.get(Constants.IS_SUCCESS));
		                request.setAttribute(Constants.ACTION, requestMap.get(Constants.ACTION));
		            }
		            setMerchantLoginErrMsg(merchantEmployee, request);
		            return view;
		        }
		        return new ModelAndView("redirect:/");
	   }
		
		@RequestMapping(value = "/add", method = RequestMethod.POST)
		public ModelAndView add(@ModelAttribute("merchantEmployeeDTO") MerchantEmployeeDTO merchantEmployeeDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)) {
					try {
						UserDTO userDTO = getSessionUserObject(request);
						MerchantEmployeeDTO merchantEmployee = merchantEmployeeService.getMerchantEmployeeById(userDTO.getId());
						merchantEmployeeDTO.setmId(merchantEmployee.getmId());
						int isInsert = merchantEmployeeService.saveMerchantEmployee(merchantEmployeeDTO, userDTO);
						if (isInsert == 1) {
							redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Merchant Employee Added Successfully.");
							redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS);
							return new ModelAndView(RedirectScreenConstants.REDIRECT_MERCHANT_EMPLOYEE);
						} else if (isInsert == 2) {
							redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Merchant Employee Already Exists.");
							redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE);
							return new ModelAndView(RedirectScreenConstants.REDIRECT_MERCHANT_EMPLOYEE);
						}
					} catch (Exception e) {
						LOGGER.error("Error in save Merchant Employee -->  " + e);
					}
		
				}
				redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Add Merchant Employee.");
				redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE);
				return new ModelAndView(RedirectScreenConstants.REDIRECT_MERCHANT_EMPLOYEE);
		}
	
		@RequestMapping(value = "/update", method = RequestMethod.GET)
		public ModelAndView update(@RequestParam("id") String id, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response, Model model) {
				if(checkUserAlreadyLogin(request, response)) {
					if (StringUtils.isNotEmpty(id)) {
						redirectAttribute.addFlashAttribute(Constants.MERCHANT_EMPLOYEE, merchantEmployeeService.getMerchantEmployeeById(utilityService.convertStringToInt(id)));
						redirectAttribute.addFlashAttribute(Constants.ACTION, Constants.UPDATE);
						return new ModelAndView(RedirectScreenConstants.REDIRECT_MERCHANT_EMPLOYEE);
					}
					redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in update Merchant Employee.");
					return new ModelAndView(RedirectScreenConstants.REDIRECT_MERCHANT_EMPLOYEE);
				}
				return new ModelAndView("redirect:/");
		}
	
		@RequestMapping(value = "/edit", method = RequestMethod.POST)
		public ModelAndView edit(@ModelAttribute("merchantEmployeeDTO") MerchantEmployeeDTO merchantEmployeeDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if(checkUserAlreadyLogin(request, response)) {
					try {
						int isUpdate = merchantEmployeeService.updateMerchantEmployee(merchantEmployeeDTO);
						if (isUpdate == 2) {
							redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Merchant Employee Updated Successfully.");
							redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS);
							return new ModelAndView(RedirectScreenConstants.REDIRECT_MERCHANT_EMPLOYEE);
						}else if(isUpdate == 3){
							redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Merchant Employee Mobile Number already Exists.");
							redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE);
							return new ModelAndView(RedirectScreenConstants.REDIRECT_MERCHANT_EMPLOYEE);
						}
					} catch (Exception e) {
						LOGGER.error("Error in update Merchant Employee -->  " + e);
					}
				}
				redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in Update Merchant Employee.");
				redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE);
				return new ModelAndView(RedirectScreenConstants.REDIRECT_MERCHANT_EMPLOYEE);
		}
	
		@RequestMapping(value = "/delete", method = RequestMethod.POST)
		public ModelAndView delete(@ModelAttribute("merchantEmployeeDTO") MerchantEmployeeDTO merchantEmployeeDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if (checkUserAlreadyLogin(request, response)) {
					try {
						int isInsert = merchantEmployeeService.deleteMerchantEmployee(merchantEmployeeDTO);
						if (isInsert > 0) {
							redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Merchant Employee Deleted Successfully.");
							redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.SUCCESS);
							return new ModelAndView(RedirectScreenConstants.REDIRECT_MERCHANT_EMPLOYEE);
						}
					} catch (Exception e) {
						LOGGER.error("Error in delete Merchant Employee -->  " + e);
					}
				}
				redirectAttribute.addFlashAttribute(Constants.MESSAGE, "Error in delete Merchant Employee.");
				redirectAttribute.addFlashAttribute(Constants.IS_SUCCESS, Constants.FAILURE);
				return new ModelAndView(RedirectScreenConstants.REDIRECT_MERCHANT_EMPLOYEE);
		}
		
		@RequestMapping(value = "/isExistsEmailId", method = RequestMethod.POST)
		public @ResponseBody String isExistsMerchantEmailId(@RequestParam("emailId") String emailId, HttpServletRequest request, HttpServletResponse response) {
			   if(StringUtils.isNotEmpty(emailId)){
				   List<MerchantEmployeeDTO> merchantList = merchantEmployeeService.getMerchantEmpByEmailId(emailId);
				   if(merchantList.size() > 0) {
					   return Constants.STR_TRUE;
				   }
			   }
			   return Constants.FALSE;
		}
		    
		@RequestMapping(value = "/isExistsMobileNumber", method = RequestMethod.POST)
		public @ResponseBody String isExistsMerchantMobileNumber(@RequestParam("mobileNumber") String mobileNumber, HttpServletRequest request, HttpServletResponse response) {
			   if(StringUtils.isNotEmpty(mobileNumber)){
				   UserDTO user = userService.getUserByMobileNo(mobileNumber);
				   if(user != null) {
					   return Constants.STR_TRUE;
				   }
			   }
			   return Constants.FALSE;
		 }
}
