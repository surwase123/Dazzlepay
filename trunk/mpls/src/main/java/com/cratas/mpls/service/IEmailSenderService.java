package com.cratas.mpls.service;

/**
 * 
 * @author mukesh
 *
 */
public interface IEmailSenderService {

	   
	   void sendEmail(String from, String[] to,String[] ccEmailIds, String sub, String msgBody, String fileName, String filePath, boolean isHTML);
}
