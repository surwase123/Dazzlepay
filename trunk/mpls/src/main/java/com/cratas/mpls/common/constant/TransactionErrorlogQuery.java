/**
 * 
 */
package com.cratas.mpls.common.constant;

/**
 * @author bhupendra
 *
 */
public class TransactionErrorlogQuery {
	
	   public static final String SELECT_TRANSACTION_ERROR_LOG_TEMPLATE = "select m.*, r.processName from recontransactionerrorlog m INNER join reconProcess r on r.reconProcessId = m.reconProcessId where m.isActive = 1 and m.isApproved = 1 and "
       		+ "r.isActive = 1 and m.systemId=? ";

}
