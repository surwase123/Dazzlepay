package com.cratas.mpls.common.utility;

/**
 * 
 * @author mukesh
 *
 */
public enum PayType {
	
	   CASH("CASH"),
	   QRCODE("QRCode"),
	   UPI("UPI"),
	   ONLINE("Online"),
	   LOYALTY("Loyalty");
	
	   private String payType;
	
	   PayType(String payType) {
	          this.payType = payType;
	   }
	
	   public String getPayType() {
			  return payType;
	   }
}
