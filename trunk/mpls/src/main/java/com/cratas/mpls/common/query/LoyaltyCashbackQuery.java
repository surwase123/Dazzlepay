package com.cratas.mpls.common.query;
/**
 * 
 * @author bhupendra
 *
 */
public class LoyaltyCashbackQuery {

	   public static final String INSERT_LOYALTY_CASHBACK = "insert into loyaltyCashback set mId=?, offerCode=UPPER(?), offerName=?, transCashbackType=?, cashbackType=?, minTransValue=?, fixedCashbackAmt=?, cashbackPercentage=?, maxCashbackAmt=?, createdBy=?";
	   
	   public static final String SELECT_LOYALTY_CASHBACK_BY_MID = "select * from loyaltyCashback WHERE isActive = 1 and mId=?";
	   
	   public static final String UPDATE_LOYALTY_CASHBACK = "update loyaltyCashback set mId=?, offerCode=UPPER(?), offerName=?, transCashbackType=?, cashbackType=?, minTransValue=?, fixedCashbackAmt=?, cashbackPercentage=?, maxCashbackAmt=?, updatedBy=?, updatedDate=now() where id = ?";
	   
	   public static final String DELETE_LOYALTY_CASHBACK = "update loyaltyCashback set isActive = 0, updatedBy=?, updatedDate=now() where id = ?";

	   public static final String SELECT_LOYALTYCASHBACK_BY_NAME = "select * from loyaltyCashback where isActive = 1 and mId=? and (offerName=? or offerCode=?)";

	   public static final String SELECT_LOYALTYCASHBACK_BY_ID = "select * from loyaltyCashback where id = ? and isActive = 1";
	   
	   public static final String SELECT_LOYALTY_CASHBACK = "select * from loyaltyCashback WHERE isActive = 1";
	   
	   public static final String SELECT_LOYALTY_CASHBACK_BY_OFFER_CODE = "select * from loyaltyCashback WHERE isActive = 1 and offerCode=?";

}  
