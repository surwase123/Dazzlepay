package com.cratas.mpls.web.dto;

/**
 * 
 * @author mukesh
 *
 */
public class FirebaseNotificationRequest {

	    private String registration_ids[];
	    private FireBaseNotificationDTO data;
	   
		public String[] getRegistration_ids() {
			return registration_ids;
		}
		public void setRegistration_ids(String[] registration_ids) {
			this.registration_ids = registration_ids;
		}
		public FireBaseNotificationDTO getData() {
			return data;
		}
		public void setData(FireBaseNotificationDTO data) {
			this.data = data;
		}
	   
	   
}
