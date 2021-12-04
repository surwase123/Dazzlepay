package com.cratas.mpls.web.rest.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.utility.constant.RestWebServiceErrConstants;
import com.cratas.mpls.service.IJWTTokenService;
import com.cratas.mpls.web.dto.Token;
import com.cratas.mpls.web.dto.WebServiceResponseDTO;

/**
 * 
 * @author mukesh
 *
 */
@RestController
@RequestMapping("/rest/webservices/jwt")
public class JwtTokenRestController extends RestWebServiceController {
	
		@Autowired
		private IJWTTokenService jWTTokenService;
		
		@RequestMapping(value = "/generateToken", method = RequestMethod.GET)
		public ResponseEntity<WebServiceResponseDTO> generateJWTToken(@RequestHeader("deviceId") String deviceId, @RequestHeader("apiKey") String apiKey) throws Exception {
			   WebServiceResponseDTO webServiceResponse = new WebServiceResponseDTO(Constants.BLANK);
			   if(StringUtils.isNotEmpty(deviceId) && StringUtils.isNotEmpty(apiKey) && jWTTokenService.isValidRequest(apiKey)) {
				   String token = jWTTokenService.generateToken(deviceId);
				   if(StringUtils.isNotEmpty(token)){
					   return ResponseEntity.ok(setWebServieResponse(webServiceResponse, Constants.BLANK, new Token(token), Constants.SUCCESS.toUpperCase()));   
				   }
			   }
			   return ResponseEntity.status(HttpStatus.OK).body(setWebServieResponse(webServiceResponse, Constants.BLANK, RestWebServiceErrConstants.TOKEN_ERR_MSG, Constants.FAILURE.toUpperCase()));
		}
}
