package com.cratas.mpls.common.query;
/**
 * 
 * @author bhupendra
 *
 */
public class MerchantProfileQuery {

	   public static final String GET_MERCHANT_ID = "SELECT mu.*, u.mobileNumber FROM merchantUser mu LEFT JOIN user u ON u.id = mu.userId WHERE mu.userId = ?";
	   
	   public static final String GET_MERCHANT_DETAILS = "SELECT m.*, u.mobileNumber, c.countryName, cr.currencyCodeAlpha, cr.currencyName, ct.categoryName FROM merchant m LEFT JOIN merchantUser mu ON m.id = mu.mId LEFT JOIN user u ON u.id = mu.userId LEFT JOIN country c ON c.id = m.countryId LEFT "
	 	   	  + "JOIN currency cr ON cr.currencyCode = m.currencyCode LEFT JOIN category ct ON ct.categoryCode = m.categoryCode WHERE m.isActive = 1 AND m.isApproved = 1  AND m.id= ? GROUP BY m.id";

	   public static final String GET_MERCHANT_USERID_BY_MID = "SELECT mu.*, u.mobileNumber FROM merchantUser mu LEFT JOIN user u ON u.id = mu.userId WHERE mu.isActive = 1 and mu.mId = ?";
}
