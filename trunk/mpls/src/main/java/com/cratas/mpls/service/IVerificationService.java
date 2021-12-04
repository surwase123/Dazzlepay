package com.cratas.mpls.service;

import com.cratas.mpls.web.dto.VerificationLogDTO;

/**
 * 
 * @author mukesh
 *
 */
public interface IVerificationService {
	
	   String sendEmailVerificationCode(VerificationLogDTO verificationLog);
	   
	   boolean verifyCode(VerificationLogDTO verificationLog);
	   
	   VerificationLogDTO getVerificationLog(int id);
	   
	   String sendMobileVerificationCode(VerificationLogDTO verificationLog);

}
