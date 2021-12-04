package com.cratas.mpls.common.constant;

/**
 * 
 * @author mukesh
 *
 */
public class PropertyKeyConstants {
	
	   public interface EncryptionProperties{
		   
			 public static final String PASSWORD_STRENGTH = "password.strength";
			 public static final String AES_KEY_SIZE = "aes.keysize";
			 public static final String AES_ITERATION_COUNT = "aes.iteration.count";
			
	   }
	   
	   public interface ThreadProperties{
		   
			  public static final String FIXED_LENGTH_FILE_INSERT_COUNT_PER_QUERY = "fixedlength.file.insert.count.per.query";
			  public static final String CSV_FILE_INSERT_COUNT_PER_QUERY = "csv.file.insert.count.per.query";
			  public static final String VISA_FILE_INSERT_COUNT_PER_QUERY = "visa.file.insert.count.per.query";
			  public static final String MATCHING_PROCESS_INSERT_COUNT_PER_QUERY = "matching.process.insert.count.per.query";
			  
			
	   }
	   
	   public interface ServerProperties{
		   
			  public static final String SENDER_EMAIL = "sender.email";
			  public static final String SUPPORT_EMAIL = "support.email";
			  

	   }
	   
	   public interface AppProperties{
		   
			  public static final String APP_SECRET_KEY = "app.secret.key";
			  public static final String TOKEN_SECRET_KEY = "token.secret.key";
			  public static final String TOKEN_EXPIRY_DAYS = "token.expiry.days";
			  public static final String APP_NAME = "app.name";
			  public static final String APP_VERSION = "app.version";
			  public static final String APP_LOGO_WHITE_URL = "app.logo.white.url";
			  public static final String APP_LOGO_DARK_URL = "app.logo.dark.url";
			  public static final String APP_BG_URL = "app.bg.url";
			  public static final String FIREBASE_NOTIFICATION_URL = "firebase.notification.url";
			  public static final String FIREBASE_AUTHORIZATION_KEY = "firebase.Authorization.key";
			  
	   }
	   
	   public interface SmsProperties{
	   		
		   	  public static final String SMS_USER_NAME = "sms.user.name";
		   	  public static final String SMS_PASSWORD = "sms.password";
		   	  public static final String SMS_NOTIFICATION_URL = "sms.notification.url";
		   	  public static final String SMS_TYPE = "sms.type";
		   	  public static final String SMS_DLR = "sms.dlr";
		   	  public static final String SMS_SOURCE = "sms.source";
		   	  public static final String SMS_ENTITYID="sms.entityId";
		   	  public static final String SMS_TEMPID="sms.tempId";
	   }
	   
	   
	   public interface LoyaltyCardNumberProperties{
	   		
		   	  public static final String LOYALTY_NUMBER_PREFIX = "card.number.prefix";
		   	  public static final String LOYALTY_NUMBER_LAST_DIGITS = "card.number.last.numberofdigit";
	   }

}
