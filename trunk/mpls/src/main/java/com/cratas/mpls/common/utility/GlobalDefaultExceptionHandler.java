package com.cratas.mpls.common.utility;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.cratas.mpls.service.IUtilityService;


/**
 * This class used to handle global exception in Application
 * @author mukesh
 *
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	
	  private final static Logger LOGGER = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);
	  
	  @Autowired 
	  private IUtilityService utilityService;
	
	  @ExceptionHandler(value = Exception.class)
	  public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) throws Exception {
	 
			    LOGGER.error("Global --> Request: " + request.getRequestURL() + " raised " + e);
			    ModelAndView mav = new ModelAndView();
			    mav.addObject("exception", e);
			    mav.addObject("url", request.getRequestURL());
			    mav.addObject("message", e.getMessage());
			    mav.setViewName(utilityService.getViewResolverName(request.getSession(), "error"));
			    return mav;
	  }
}
