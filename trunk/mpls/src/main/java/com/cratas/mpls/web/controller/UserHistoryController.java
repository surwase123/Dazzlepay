/**
 * 
 */
package com.cratas.mpls.web.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.constant.PageConstant;
import com.cratas.mpls.common.utility.constant.RedirectScreenConstants;
import com.cratas.mpls.service.ISystemService;
import com.cratas.mpls.service.IUserHistoryService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.UserDTO;

/**
 * @author bhupendra
 *
 */
@Controller
@RequestMapping("/userReport")
public class UserHistoryController extends BaseController{
	
		private final static Logger LOGGER = LoggerFactory.getLogger(UserHistoryController.class);
		
		@Autowired
		private IUtilityService utilityService;
	
		@Autowired
		private ISystemService systemService;
		
		@Autowired
		private IUserHistoryService userHistoryService;
		
		@RequestMapping(value = "/view", method = RequestMethod.GET)
		public ModelAndView country(HttpServletRequest request, HttpServletResponse response, Model model) {
			   if (checkUserAlreadyLogin(request, response)) {
					UserDTO userDTO = getSessionUserObject(request);
					ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.USER_HISTORY_REPORT));
					request.setAttribute(Constants.SYSTEM_LIST, systemService.getActiveSystem());
					Map<String, Object> requestMap = ((Model) model).asMap();
					if (null != requestMap && !requestMap.isEmpty()) {
						String action = (String) requestMap.get(Constants.ACTION);
						request.setAttribute(Constants.MESSAGE, requestMap.get(Constants.MESSAGE));
						request.setAttribute(Constants.IS_SUCCESS, requestMap.get(Constants.IS_SUCCESS));
						request.setAttribute(Constants.ACTION, action);
					}else {
						LOGGER.info("User Histroy Report - Filteration");
						request.setAttribute(Constants.USER_HISTORY_REPORT_LIST, userHistoryService.getUsers(userDTO));
					}
			 		return view;
			   }
			   return new ModelAndView("redirect:/");
		}
		
		@RequestMapping(value = "/getUserDetails", method = RequestMethod.POST)
		public ModelAndView getUserDetails(@ModelAttribute("UserHistoryDTO") UserDTO userHistoryDTO, RedirectAttributes redirectAttribute, HttpServletRequest request, HttpServletResponse response) {
			   if (checkUserAlreadyLogin(request, response)) {
				   	if(userHistoryDTO != null) {
				   		UserDTO userDTO = getSessionUserObject(request);
				   		userHistoryDTO.setGroupDTO(userDTO.getGroupDTO());
				   		redirectAttribute.addFlashAttribute(Constants.USER_HISTORY, userHistoryDTO);
				   		redirectAttribute.addFlashAttribute(Constants.USER_HISTORY_REPORT_LIST, userHistoryService.getUsers(userHistoryDTO));
					    return new ModelAndView(RedirectScreenConstants.REDIRECT_USER_HISTORY_REPORT);
				   	}
			   }
			   return null;
		}
		
		@RequestMapping(value = "/getUserHistory/{loginId}", method = RequestMethod.POST)
		public @ResponseBody ModelAndView getUserInfo(@PathVariable("loginId") String loginId, HttpServletRequest request, HttpServletResponse response) {
				   if(StringUtils.isNotEmpty(loginId) && checkUserAlreadyLogin(request, response)){
					   	 ModelAndView view = new ModelAndView(utilityService.getViewResolverName(request.getSession(), PageConstant.USER_HISTORY_REPORT_MODAL));
					   	 request.setAttribute(Constants.USER_HISTORY_LIST, userHistoryService.getUserHistoryDetails(loginId));
					     return view;
				   }
				   return new ModelAndView("redirect:/");
		}	
}
