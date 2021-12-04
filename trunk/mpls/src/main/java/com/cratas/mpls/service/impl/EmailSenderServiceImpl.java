package com.cratas.mpls.service.impl;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.cratas.mpls.service.IEmailSenderService;

/**
 * This service class used to send mail to users with file attachment.
 * @author mukesh
 *
 */
@Service
public class EmailSenderServiceImpl implements IEmailSenderService {

	  private final static Logger LOGGER = LoggerFactory.getLogger(EmailSenderServiceImpl.class);
	   
	  @Autowired
	  private JavaMailSenderImpl mailSender;

	   
	  public void sendEmail(String from, String[] to, String[] cc, String sub, String msgBody, String fileName, String filePath, boolean isHTML){
		    	MimeMessage message = mailSender.createMimeMessage();
		        try {
		            MimeMessageHelper helper = new MimeMessageHelper(message, true);
		            helper.setFrom(from);
		            helper.setTo(to);
		            helper.setSubject(sub);
		            helper.setText(msgBody, isHTML);
		            if(null != cc && cc.length > 0){
		            	for(String ccAddress : cc){
		                    helper.addCc(ccAddress);
		                }
		            }
		            if(StringUtils.isNotEmpty(fileName) &&  StringUtils.isNotEmpty(filePath)){
		                  helper.addAttachment(fileName, new ByteArrayResource(getFileArray(filePath)));
		            }
		            mailSender.send(message);
		        } catch (MessagingException e) {
		            LOGGER.error("Error in send mail => "+e.getMessage());
		            e.printStackTrace();
		        }
	    }
	    
	   private byte[] getFileArray(String fileName){
	    	  StringBuilder sb = new StringBuilder();
	    	  InputStream is = null;
	    	  try{
		    	 is = new FileInputStream(new File(fileName));
		         byte[] tmp = new byte[4*1024];
		         int size = 0;
		         while((size = is.read(tmp)) != -1){
		             sb.append(new String(tmp, 0, size));
		         }
	    	  }catch(Exception e){
	    		 LOGGER.error("Error in read mail file attachment => "+e.getMessage());
	    	  }finally {
	    		  try{
					 if(null != is){
						  is.close();
					 }
	    		  }catch(Exception e){
	    			  LOGGER.error("Error in close resource in Java mail attachment");
	    		  }
			 }
	         return sb.toString().getBytes();
	 }
	   

}
