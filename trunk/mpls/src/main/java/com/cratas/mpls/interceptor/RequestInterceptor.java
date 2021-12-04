package com.cratas.mpls.interceptor;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.utility.SaveUserTrackingDetailThread;
import com.cratas.mpls.common.utility.SaveUserTrackingThread;
import com.cratas.mpls.service.IUserService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.web.dto.UserDTO;
import com.cratas.mpls.web.dto.UserRoleDTO;
import com.cratas.mpls.web.dto.UserTrackingRequestDTO;

import eu.bitwalker.useragentutils.UserAgent;

/**
 * This class used to validate request, track user session state. 
 * @author mukesh
 *
 */
public class RequestInterceptor implements HandlerInterceptor{

	    private final static Logger LOGGER = LoggerFactory.getLogger(RequestInterceptor.class);
		
		@Autowired
		private ThreadPoolTaskExecutor taskExecutor;
	
		@Autowired
		private SaveUserTrackingThread saveUserTrackingThread;
		
		@Autowired
		private SaveUserTrackingDetailThread saveUserTrackingDetailThread;
		
		@Autowired
		private IUtilityService utilityService;
		
		@Autowired
		private IUserService userService;
	
		@Override
		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
			   try{
				   saveUserTrackingDetails(request,response, request.getSession());
				   String userAgent = request.getHeader(Constants.USER_AGENT);
				   if(StringUtils.isNotEmpty(userAgent)){
					    if(isAuthorizedUser(request, response)){
					    	return true;
					    }
					    response.sendRedirect(utilityService.getBaseUrl() + Constants.SLASH + Constants.UN_AUTHORIZED_USER);
				   }
				   return false;
			   }catch(Exception e){
				   LOGGER.error("Error in User history tracking --> "+e.getMessage());
			   }
			   return false;
		}
		
		/**
		 * This method used to track user login information like as OS, Browser and IP - Address
		 */
		private void saveUserTrackingDetails(HttpServletRequest request , HttpServletResponse response, HttpSession session){
				String cxtPath = request.getContextPath();
				String path = request.getRequestURI();
				if(Constants.BACK_SLASH.equals(cxtPath) && path.startsWith(cxtPath)){
					    path = path.substring(cxtPath.length());
				}
				if (!path.contains(Constants.RESOURCES)) {
					UserTrackingRequestDTO userTrackingDTO = (UserTrackingRequestDTO) session.getAttribute(Constants.USER_TRACKING_INFO);
					if(userTrackingDTO == null){
						userTrackingDTO = new UserTrackingRequestDTO();
						String loginId = getLoginId(request);
						if(StringUtils.isNotEmpty(loginId)){
								userTrackingDTO.setLoginId(loginId);
								userTrackingDTO.setUrlType(Constants.SINGLE_PATH);
								userTrackingDTO.setSessionId(request.getSession().getId());
								session.setAttribute(Constants.USER_TRACKING_INFO, userTrackingDTO);
								session.setAttribute(Constants.USER_INFO, loginId);
								saveUserTrackingThread.setData(userTrackingDTO);
								taskExecutor.execute(saveUserTrackingThread);
						}
				   }
				   userCompleteTracking(request, path, session);
			    }
	    }
		
		/**
		 * This method used to track user session state.
		 */
		private void userCompleteTracking(HttpServletRequest request , String path, HttpSession session){
				Set<String> userTrackingPath = getLastUserTrackingInfo(session);
				if(!userTrackingPath.contains(path) && !path.contains(Constants.RESOURCES)){
						UserTrackingRequestDTO userTrackingDTO = new UserTrackingRequestDTO();
						String loginInfo = (String) session.getAttribute(Constants.USER_INFO);
						String userAgent = request.getHeader(Constants.USER_AGENT);
						if (StringUtils.isNotEmpty(loginInfo)) {
								String source = getDeviceTypeByUserAgent(userAgent, request);
								userTrackingDTO.setSource(source);
								UserAgent userAgentobj = UserAgent.parseUserAgentString(userAgent);
								userTrackingDTO.setBrowser(userAgentobj.getBrowser().getName());
								userTrackingDTO.setOperatingSystem(userAgentobj.getOperatingSystem().getName());
								userTrackingDTO.setSessionId(session.getId());
								userTrackingDTO.setIpAddress(utilityService.getRemoteAddr(request));
								userTrackingDTO.setRequestUrl(path);
								userTrackingDTO.setUrlType(Constants.MULTIPATH);
								userTrackingDTO.setLoginId(loginInfo);
								saveUserTrackingDetailThread.setData(userTrackingDTO);
								taskExecutor.execute(saveUserTrackingDetailThread);
								userTrackingPath.add(path);
								session.setAttribute(Constants.USER_TRACKING_PATHSET, userTrackingPath);
						}
				}
	    }
		
		/**
		 * This method used to get user login Device type.
		 */
		private String getDeviceTypeByUserAgent(String userAgent, HttpServletRequest request){
				String utmSource = request.getParameter(Constants.UTM_SOURCE);
				String sessionUtmSourceValue = (String) request.getSession().getAttribute(Constants.UTM_SOURCE);
				
				if(null != utmSource && sessionUtmSourceValue == null){
				    request.getSession().setAttribute(Constants.UTM_SOURCE, utmSource);
			    }
				if(null!=userAgent && userAgent.contains(Constants.MOBILE)){
					return Constants.MOBILE;
				}else {
					request.getSession().setAttribute(Constants.UTM_SOURCE, Constants.WEB);
					return Constants.WEB;
				}
	    }
		
		private Set<String> getLastUserTrackingInfo(HttpSession session){
				@SuppressWarnings("unchecked")
				Set<String> userTrackingPath = (Set<String>) session.getAttribute(Constants.USER_TRACKING_PATHSET);
				if(null == userTrackingPath){
					  userTrackingPath = new HashSet<String>();
				}
				return userTrackingPath;
		}
		
		@Override
		public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
				throws Exception {
			
		}
	
		@Override
		public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
				throws Exception {
			
		}
		
		private String getLoginId(HttpServletRequest request) {
			    UserDTO userDTO = (UserDTO) request.getSession().getAttribute(Constants.LOGIN_DTO);
			    if(null != userDTO) {
				   return userDTO.getLoginId();
			    }
			    return Constants.BLANK;
	    }
		
		private boolean isAuthorizedUser(HttpServletRequest request, HttpServletResponse response){
				UserDTO userDTO = (UserDTO) request.getSession().getAttribute(Constants.LOGIN_DTO);
				String requestURI = request.getRequestURI();
			    if(null != userDTO && StringUtils.isNotEmpty(requestURI) && !requestURI.contains(Constants.UN_AUTHORIZED_USER)) {
			    	 if(utilityService.isUserMenuUrl(requestURI)){
				    	 UserRoleDTO userRole = userService.getPageRole(userDTO.getUserRoleList(), requestURI);
				    	 if(userRole == null){
				    		 return false;
				    	 }
			    	 }
			    }
			    return true;
		}
		
	
}
