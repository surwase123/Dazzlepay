package com.cratas.mpls.web.rest.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.utility.constant.RedirectScreenConstants;
import com.cratas.mpls.service.ILoyaltyCardService;
import com.cratas.mpls.service.IUtilityService;

@RestController
@RequestMapping("/rest/webservices/loyaltyCard")
public class LoyaltyCardRestWebServiceController {
	
	@Autowired
	private IUtilityService utilityService;
	
	@Autowired
	private ILoyaltyCardService loyaltyCardService;
	
//	@RequestMapping(value = "/fileupload", method = RequestMethod.POST)
//    public int fileUpload(@RequestParam("fileName") MultipartFile file,RedirectAttributes redirectAttribute,HttpServletRequest request, HttpServletResponse response) throws IOException {
////		   	int flag= loyaltyCardService.saveFile(file);
//   	 return flag;
//    }
}
