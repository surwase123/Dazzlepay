package com.cratas.mpls.common.query;

public class RequestLoyaltyCardQuery {
	
	  public static final String SAVE_REQUEST = "insert into loyaltynumberrequestlog(quantityOfCards,mId,message,shippingAddress,merchantId,name,cost_per_unit,total_cost,cardtype,payment_mode,payment_reference_no) values(?,?,?,?,?,?,?,?,?,?,?)";
		
	  public static final String UPDATE_LOYALTY_REQUEST_STATUS = "update loyaltynumberrequestlog set status = ? where id =?";
	  
	  public static final String UPDATE_LOYALTY_REQUEST_STATUSWITHREASON = "update loyaltynumberrequestlog set status = ? ,reason= ? where id =?";

	  public static final String GET_REQUEST_BY_ID="select * from loyaltynumberrequestlog where id=?";
	  
      public static final String GET_LOYALTYCARD_NOTIFICATION = "select * from loyaltynumberrequestlog where  status = 0  ORDER BY id DESC";
      
      public static final String GET_LOYALTYCARD_NOTIFICATION_BY_STATUS = "select * from loyaltynumberrequestlog where  status = ?";
      
      public static final String GET_PUSH_NOTIFICATION_BY_MID = "select * from notificationLog where  mId=? and status = 0";
      
	public static final String GET_STATUS_EQUALS_TO_ONE = "select * from loyaltynumberrequestlog  where  status = 1";

	public static final String GET_ALLOTED_LOYALTYCARD_NUMBER_BY_MERCHANTID_AND_STATUS = "select * from loyaltynumberrequestlog  where  mId = ? and status=3";
	
	public static final String GET_PENDING_ACCEPTED_REJECTED_LOYALTYCARD_NUMBER_REQUEST_BY_MERCHANTID_AND_STATUS = "select * from loyaltynumberrequestlog  where  mId = ? and (status=0 or status=2 or status=1)";
}
