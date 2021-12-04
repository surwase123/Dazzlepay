package com.cratas.mpls.common.utility;
/**
 * 
 * @author mukesh
 *
 */
public enum TransactionType {

	   TOPUP("topUp"),
	   PAY("pay"),
	   REFUND("refund"),
	   CASHBACK("cashback");
	
	   private String transactionType;

	   TransactionType(String transactionType) {
	        this.transactionType = transactionType;
	   }

	   public String getTransactionType() {
			return transactionType;
	   }
		
		
}
