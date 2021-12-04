/**
 * 
 */
package com.cratas.mpls.common.query;

/**
 * @author bhupendra
 *
 */
public class UserHistoryReportQuery {
		
		public static final String GET_USER_LIST = "select * from user  where systemId = ? ";
		
		public static final String USER_HISTORY_DETAILS_BY_ID = "SELECT * from user_history_detail where loginId = ? AND (TIMESTAMP >=(CURDATE() - INTERVAL 1 MONTH)) order by timestamp desc";
}
