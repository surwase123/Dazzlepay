package com.cratas.mpls.service.impl;

import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.cratas.event.service.IEventRegisterService;
import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.constant.EmailConstants;
import com.cratas.mpls.common.constant.PropertyKeyConstants;
import com.cratas.mpls.dao.IVerificationDao;
import com.cratas.mpls.service.IEmailSenderService;
import com.cratas.mpls.service.IHTTPService;
import com.cratas.mpls.service.IUtilityService;
import com.cratas.mpls.service.IVerificationService;
import com.cratas.mpls.web.controller.MerchantController;
import com.cratas.mpls.web.dto.VerificationLogDTO;
import org.apache.http.client.utils.URIBuilder;





/**
 * 
 * @author mukesh
 *
 */
@Service
public class VerificationServiceImpl implements IVerificationService {
	
	    private final static Logger LOGGER = LoggerFactory.getLogger(MerchantController.class);

	    @Autowired
	    private IVerificationDao verificationDao;
	    
	    @Autowired
	    private IHTTPService httpService;
	    
	    @Autowired
	    private IUtilityService utilityService;
	    
	    @Autowired
	    private IEmailSenderService emailSenderService;
	    
	    @Autowired
	    private Environment env;
	    
	    @Autowired
		private IEventRegisterService eventRegisterService;
				
		public String sendEmailVerificationCode(VerificationLogDTO verificationLog){
			   String verificationCode = utilityService.generateNumber(6, false, true);
			   try{
				   verificationLog.setVerificationCode(verificationCode);
				   sendVerificationCodeMail(verificationLog, verificationCode);
				   int verificationId = verificationDao.saveVerificationLog(verificationLog);
				   return String.valueOf(verificationId);
			   }catch (Exception e) {
				   LOGGER.error("Error in Send Email Verification Code -- "+verificationLog.getEmailId());
			   }
			   return null;
		}	
		
		public boolean verifyCode(VerificationLogDTO verificationLog){
			   if(null != verificationLog){
				  
				   int verifiedId = utilityService.convertStringToInt(verificationLog.getId());
				   VerificationLogDTO verification = verificationDao.getVerificationLog(verifiedId);
				   if(null != verification && verificationLog.getVerificationCode().equals(verification.getVerificationCode())){
					   updateVerificationLog(verifiedId);
					   return true;
				   }
			   }
			   return false;
		}
		
		private void sendVerificationCodeMail(final VerificationLogDTO verificationLog, final String verificationCode) {
		        if(StringUtils.isNotEmpty(verificationCode)) {
		            final String message = utilityService.classpathResourceLoader(EmailConstants.VERIFY_EMAIL_TEMPLATE)
		                    .replace(EmailConstants.LOGO_URL, /*utilityService.getBaseUrl() + Constants.LOGO_PATH*/"https://cratasproducts.in/mpls/resources/gradient/images/DP-black.png")
		                    .replace(EmailConstants.VERIFICATION_CODE, verificationCode)
		                    .replace(EmailConstants.EMAIL, env.getProperty(PropertyKeyConstants.ServerProperties.SUPPORT_EMAIL))
		                    .replace(EmailConstants.BASE_URL, utilityService.getBaseUrl())
		                    .replace(EmailConstants.APP_NAME, EmailConstants.MPLS_APP_NAME).replace(EmailConstants.APP_ACCOUNT_NAME, EmailConstants.MPLS);
		            new Thread(new Runnable() {
		                @Override
		                public void run() {
		                    emailSenderService.sendEmail(utilityService.getSenderMail(), verificationLog.getEmailId().split(Constants.COMMA), null,
		                            EmailConstants.VERIFICATION_MAIL_SUBJECT, message, null, null, true);
		                }
		            }).start();
		        }
        }
		
		public int saveVerificationLog(VerificationLogDTO verificationLog) {
			   return verificationDao.saveVerificationLog(verificationLog);
		}
		
		public int updateVerificationLog(int id) {
			   return verificationDao.updateVerificationLog(id);
		}
		
		public VerificationLogDTO getVerificationLog(int id){
			   return verificationDao.getVerificationLog(id);
		}

		public String sendMobileVerificationCode(VerificationLogDTO verificationLog){
			
			   String verificationCode = utilityService.generateNumber(6, false, true);
			   try{
				   verificationLog.setVerificationCode(verificationCode);
				   String message="Dear "+verificationLog.getUserType()+", "+verificationCode+" is the DazzlePay OTP. This OTP is valid for next 10 mins.";
//				   StringBuffer msg = new StringBuffer("Dear%20 ");
//				   	msg.append(verificationLog.getUserType()+", ");
//				   	msg.append(verificationCode+" is the DazzlePay OTP. This OTP is valid for next 10 mins.");
				  String encode=message.toString();
				   message= encode.replace(" ", "%20");
				  final 	String url=String.valueOf(env.getProperty(PropertyKeyConstants.SmsProperties.SMS_NOTIFICATION_URL)+
				   			Constants.USER_SMS_NAME+"="+env.getProperty(PropertyKeyConstants.SmsProperties.SMS_USER_NAME)+"&"+
				   		  Constants.USER_SMS_PASSWORD+"="+ env.getProperty(PropertyKeyConstants.SmsProperties.SMS_PASSWORD)+"&"+
							  Constants.USER_SMS_TYPE+"="+ env.getProperty(PropertyKeyConstants.SmsProperties.SMS_TYPE)+"&"+
							  Constants.USER_SMS_DRL+"="+ env.getProperty(PropertyKeyConstants.SmsProperties.SMS_DLR)+"&"+
							  Constants.USER_SMS_DESTINATION+"=+91"+verificationLog.getMobileNumber() +"&"+
							  Constants.USER_SMS_SOURCE+"="+ env.getProperty(PropertyKeyConstants.SmsProperties.SMS_SOURCE)+"&"+
							  Constants.USER_SMS_ENTITYID+"="+env.getProperty(PropertyKeyConstants.SmsProperties.SMS_ENTITYID)+"&"+
							 Constants.USER_SMS_TEMPID+"="+ env.getProperty(PropertyKeyConstants.SmsProperties.SMS_TEMPID)+"&"+
							 Constants.USER_SMS_MESSAGE+"="+message);
				  
//				   		  URIBuilder b = new URIBuilder(env.getProperty(PropertyKeyConstants.SmsProperties.SMS_NOTIFICATION_URL));
//				   b.addParameter(Constants.USER_SMS_NAME, env.getProperty(PropertyKeyConstants.SmsProperties.SMS_USER_NAME));
//				   b.addParameter(Constants.USER_SMS_PASSWORD, env.getProperty(PropertyKeyConstants.SmsProperties.SMS_PASSWORD));
//				   b.addParameter(Constants.USER_SMS_TYPE, env.getProperty(PropertyKeyConstants.SmsProperties.SMS_TYPE));
//				   b.addParameter(Constants.USER_SMS_DRL, env.getProperty(PropertyKeyConstants.SmsProperties.SMS_DLR));
//				   b.addParameter(Constants.USER_SMS_DESTINATION,"\\+91"+verificationLog.getMobileNumber() );
//				   b.addParameter(Constants.USER_SMS_SOURCE, env.getProperty(PropertyKeyConstants.SmsProperties.SMS_SOURCE));
//				   b.addParameter(Constants.USER_SMS_ENTITYID,env.getProperty(PropertyKeyConstants.SmsProperties.SMS_ENTITYID));
//				   b.addParameter(Constants.USER_SMS_TEMPID, env.getProperty(PropertyKeyConstants.SmsProperties.SMS_TEMPID));
//				   b.addParameter(Constants.USER_SMS_MESSAGE,message);

				   
//				   URL url = b.build().toURL();
//				   String link=url.toString()+"&"+Constants.USER_SMS_MESSAGE+"="+message;
//				 final String  url=String.valueOf("https://sms6.rmlconnect.net/bulksms/bulksms?username=dazzletr&password=Dazzle12&type=0&dl-r=1&destination=+91"+verificationLog.getMobileNumber()+"&source=DPALRT&entityid=1601246160714561046&tempid=1607100000000141036&message=Dear%20Shashi,%20"+verificationCode+"%20is%20the%20DazzlePay%20OTP.%20This%20OTP%20is%20valid%20for%20next%2010%20mins.");
				 new Thread(new Runnable() {
		                @Override
		                public void run() {
		                	env.getProperty(PropertyKeyConstants.AppProperties.APP_NAME);
		                String res=	httpService.HTTPGetRequest(url);
		                }
		            }).start();
//				   eventRegisterService.eventRegister(Constants.RESET_PASSWORD_EVENT, getEventArgument(verificationLog), verificationLog.getMobileNumber());
				   int verificationId = verificationDao.saveVerificationLog(verificationLog);
				   return String.valueOf(verificationId);
			   }catch (Exception e) {
				   LOGGER.error("Error in Send Mobile Verification Code -- "+e+verificationLog.getMobileNumber());
			   }
			   return null;
		}

		private String getEventArgument(VerificationLogDTO verificationLog) {
			    ObjectMapper mapper = new ObjectMapper();
			    try {
					String user = mapper.writeValueAsString(verificationLog);
					return user;
				} catch (Exception e) {
					e.printStackTrace();
				} 
			    return Constants.BLANK;
		}
}