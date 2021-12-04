package com.cratas.mpls.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import com.cratas.mpls.common.constant.Constants;
import com.cratas.mpls.common.constant.FilePathConstants;
import com.cratas.mpls.common.constant.PropertyKeyConstants;
import com.cratas.mpls.service.IFireBaseNotificationService;
import com.cratas.mpls.service.IHTTPService;
import com.cratas.mpls.service.IPushNotificationLogService;
import com.cratas.mpls.web.dto.FireBaseNotificationDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

/*
 * 
 */
@Service
@PropertySource(FilePathConstants.CLASSPATH + FilePathConstants.APP_PROPERTIES)
public class FireBaseNotificationServiceImpl implements IFireBaseNotificationService {
	
	   private final static Logger LOGGER = LoggerFactory.getLogger(HTTPServiceImpl.class);
	
	   @Autowired
	   private IHTTPService httpService;
	   
	   @Autowired
	   private Environment env;
	   
	   @Autowired
	   private IPushNotificationLogService pushNotificationLogService;
	
	   @SuppressWarnings("unchecked")
	   public void sendFireBaseNotification(FireBaseNotificationDTO fireBaseNotification, List<String> tokenList){
			  String notificationUrl = env.getProperty(PropertyKeyConstants.AppProperties.FIREBASE_NOTIFICATION_URL);
			  String authorizationKey = env.getProperty(PropertyKeyConstants.AppProperties.FIREBASE_AUTHORIZATION_KEY);
			  Gson gson = new Gson();
	    	  Gson gsonBuilder = new GsonBuilder().create();
			  try{
				  if(!tokenList.isEmpty()){
		    		 JsonArray myCustomArray = gsonBuilder.toJsonTree(tokenList).getAsJsonArray();
		    		 JSONObject notification = getNotification(fireBaseNotification, gson);
		    		 JSONObject data = getData(fireBaseNotification, gson);
		    		 JSONObject firebase = getFirebaseData(notification, data, myCustomArray);
		    		 HttpHeaders headers = new HttpHeaders();
					 MediaType mediaType = new MediaType(Constants.APPLICATION, Constants.JSON, StandardCharsets.UTF_8);
					 headers.setContentType(mediaType);
					 headers.set(Constants.AUTHORIZATION, Constants.KEY_EQUAL + authorizationKey);
//					 int notificationId = pushNotificationLogService.savePushNotificationLog(fireBaseNotification.getcId(), fireBaseNotification.getmId(),fireBaseNotification.getNotificationWay(),fireBaseNotification.getIsReadOnly(), fireBaseNotification.getTitle(), firebase.toString(), fireBaseNotification.getType());
					 int notificationId = pushNotificationLogService.savePushNotificationNewLog(fireBaseNotification.getcId(), fireBaseNotification.getmId(),fireBaseNotification.getNotificationWay(),fireBaseNotification.getIsReadOnly(), fireBaseNotification.getTitle(), firebase.toString(), fireBaseNotification.getType());
					 firebase.put(data, data.put("notificationId", gson.toJson(notificationId)));
			    	 String response = httpService.HTTPFireBasePostRequest(notificationUrl, firebase.toString(), headers);
			    	 LOGGER.info("Sending Notification -- response --"+response);
				 }else {
					 JSONObject notification = getNotification(fireBaseNotification, gson);
		    		 JSONObject data = getData(fireBaseNotification, gson);
		    		 JSONObject firebase = getFirebaseData(notification, data, null);
//		    		 pushNotificationLogService.savePushNotificationLog(fireBaseNotification.getcId(), fireBaseNotification.getmId(), fireBaseNotification.getTitle(), firebase.toString(), fireBaseNotification.getType());
		    		 pushNotificationLogService.savePushNotificationNewLog(fireBaseNotification.getcId(), fireBaseNotification.getmId(),fireBaseNotification.getNotificationWay(),fireBaseNotification.getIsReadOnly(), fireBaseNotification.getTitle(), firebase.toString(), fireBaseNotification.getType());
				 }
			  }catch(Exception e){
				  LOGGER.error("Error in Send firebase Notification -- "+e.getMessage());
			  }
	  }
	   
	  @SuppressWarnings("unchecked")
	  private JSONObject getFirebaseData(JSONObject notification, JSONObject data, JsonArray myCustomArray) {
		      JSONObject firebaseData = new JSONObject();
		      firebaseData.put(Constants.PRIORITY, Constants.HIGH);
		      firebaseData.put("notification", notification);
		      firebaseData.put("data", data);
		      firebaseData.put(Constants.REGISTRATION_IDS, myCustomArray);
		      return firebaseData;
	  }

	  @SuppressWarnings("unchecked")
	  private JSONObject getNotification(FireBaseNotificationDTO firebaseNotification, Gson gson) throws JsonProcessingException {
		      JSONObject notification = new JSONObject();
		      notification.put(Constants.CLICK_ACTION, Constants.FCM_PLUGIN_ACTIVITY);
		      notification.put("imageUrl", gson.toJson(firebaseNotification.getImageUrl()));
		      notification.put("title", gson.toJson(firebaseNotification.getTitle()));
	          return notification;
	  }

	  @SuppressWarnings("unchecked")
	  private JSONObject getData(FireBaseNotificationDTO firebaseNotification, Gson gson) {
		      JSONObject data = new JSONObject();
		      data.put("id", gson.toJson(firebaseNotification.getId()));
		      data.put("cId", gson.toJson(firebaseNotification.getcId()));
		      data.put("mId", gson.toJson(firebaseNotification.getmId()));
		      data.put("customer", gson.toJson(firebaseNotification.getCustomer()));
	    	  data.put("merchant", gson.toJson(firebaseNotification.getMerchant()));
	    	  data.put("message", gson.toJson(firebaseNotification.getTitle()));
	    	  data.put("notificationType", gson.toJson(firebaseNotification.getType()));
	          return data;
	  }
	  
}
