package com.cratas.mpls.dao;

import com.cratas.mpls.web.dto.VerificationLogDTO;

/**
 * 
 * @author mukesh
 *
 */
public interface IVerificationDao {
	
	   int saveVerificationLog(VerificationLogDTO verificationLog);
	   
	   int updateVerificationLog(int id);
	   
	   VerificationLogDTO getVerificationLog(int id);

}
