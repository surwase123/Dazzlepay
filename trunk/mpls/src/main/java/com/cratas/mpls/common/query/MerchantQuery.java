package com.cratas.mpls.common.query;

public class MerchantQuery {

		public static final String SAVE_MERCHANT = "insert into merchant set merchantId=?, categoryCode=?, merchantName=?, icon=?, firstName=?, middleName=?, lastName=?, emailId=?, aboutMe=?, PANNumber=UPPER(?), GSTIN=UPPER(?)," 
	    		+ "countryId=?, currencyCode=?, merchantNumber=?, merchantPlanId=?, createdBy=?";
	    
	    public static final String UPDATE_MERCHANT = "update merchant m JOIN merchantUser mu ON m.id = mu.mId JOIN user u ON u.id = mu.userId set m.categoryCode=:categoryCode, m.merchantName=:merchantName, m.icon=:icon, m.firstName=:firstName, m.middleName=:middleName, m.lastName=:lastName, m.emailId=:emailId, u.mobileNumber=:mobileNumber, m.aboutMe=:aboutMe, m.PANNumber=UPPER(:PANNumber), m.GSTIN=UPPER(:GSTIN), "
	    		+ "m.countryId=:countryId, m.currencyCode=:currencyCode, m.updatedby=:updatedBy, m.updatedDate=NOW() where m.id=:id";
	    
	    public static final String IS_EXISTING_MERCHANT  = " SELECT m.*, u.mobileNumber from merchant m LEFT JOIN merchantUser mu ON m.id = mu.mId LEFT JOIN user u ON u.id = mu.userId WHERE (m.merchantName=? or m.PANNumber = ? or m.GSTIN = ? or u.mobileNumber = ? or m.emailId = ? ) and m.isActive = 1";
	    
	    public static final String IS_MERCHANT = " SELECT m.*, u.mobileNumber from merchant m LEFT JOIN merchantUser mu ON m.id = mu.mId LEFT JOIN user u ON u.id = mu.userId WHERE (u.mobileNumber = ? or m.emailId = ?) and m.isActive = 1";
	    
        public static final String GET_MERCHANT_BY_MID = "SELECT m.*, u.mobileNumber from merchant m LEFT JOIN merchantUser mu ON m.id = mu.mId LEFT JOIN user u ON u.id = mu.userId where m.merchantId = ?";
        
        public static final String GET_MERCHANT_BY_ID = "SELECT m.*,u.mobileNumber,c.countryName,cr.currencyCodeAlpha,cr.currencyName,ct.categoryName FROM merchant m LEFT JOIN merchantUser mu ON m.id = mu.mId LEFT JOIN user u ON u.id = mu.userId LEFT JOIN country c ON c.id = m.countryId LEFT JOIN currency cr ON cr.currencyCode = m.currencyCode LEFT JOIN category ct ON ct.categoryCode = m.categoryCode WHERE m.isActive = 1 AND m.isApproved = 1  AND m.id= ? GROUP BY m.id";
	    
	    public static final String GET_ALL_MERCHANT = "SELECT m.*,u.mobileNumber,c.countryName,cr.currencyCodeAlpha,cr.currencyName,ct.categoryName FROM merchant m LEFT JOIN merchantUser mu ON m.id = mu.mId LEFT JOIN user u ON u.id = mu.userId LEFT JOIN country c ON c.id = m.countryId LEFT JOIN currency cr ON cr.currencyCode = m.currencyCode LEFT JOIN category ct ON ct.categoryCode = m.categoryCode WHERE m.isActive = 1 AND m.isApproved = 1 GROUP BY m.id";
	    
	    public static final String SAVE_MERCHANT_ADDRESS = "insert into merchantAddress set mId=:mId, stateId=:stateId, cityId=:cityId, areaCode=:areaCode, pincode=:pincode";	    
	    
	    public static final String DELETE_MERCHANT_ADDRESS = "delete from merchantAddress where mId=?";
	    
	    public static final String UPDATE_MERCHANT_ADDRESS = "update merchantAddress set isActive = 0 where mId=?";
	    
	    public static final String GET_MERCHANT_ADDRESS = "select md.*, s.stateName, c.cityName from merchantAddress md join state s on s.id = md.stateId join city c on c.id = md.cityId where md.mId=? and md.isActive=1";	    
	    	    
	    public static final String SAVE_MERCHANT_USER = " insert into merchantUser set mId=?, userId=?, createdBy=?, isOwner=?";
	    
	    public static final String RECENT_MERCHANT_LIST = "SELECT m.*,u.mobileNumber,c.countryName,cr.currencyCodeAlpha,cr.currencyName,ct.categoryName FROM merchant m LEFT JOIN merchantUser mu ON m.id = mu.mId LEFT JOIN user u ON u.id = mu.userId LEFT JOIN country c ON c.id = m.countryId LEFT JOIN "
	    		+ "currency cr ON cr.currencyCode = m.currencyCode LEFT JOIN category ct ON ct.categoryCode = m.categoryCode WHERE m.isActive = 1 AND m.isApproved = 1 GROUP BY m.id order by m.id desc limit ?";
	    
	    public static final String RECENT_MERCHANT_LIST_BY_AREA = "SELECT m.*,u.mobileNumber,c.countryName,cr.currencyCodeAlpha,cr.currencyName,ct.categoryName FROM merchant m LEFT JOIN merchantUser mu ON m.id = mu.mId LEFT JOIN user u ON u.id = mu.userId LEFT JOIN country c ON c.id = m.countryId LEFT JOIN "
	    		+ "currency cr ON cr.currencyCode = m.currencyCode LEFT JOIN category ct ON ct.categoryCode = m.categoryCode join merchantAddress md on md.mId = m.id WHERE m.isActive = 1 AND m.isApproved = 1 and";
	    
	    public static final String IS_EXISTING_MERCHANT_BY_ID = "SELECT m.*, u.mobileNumber from merchant m LEFT JOIN merchantUser mu ON m.id = mu.mId LEFT JOIN user u ON u.id = mu.userId WHERE m.id != ? and (m.merchantName=? or m.PANNumber = ? or m.GSTIN = ? or u.mobileNumber = ? or m.emailId = ? ) and m.isActive = 1";

	    public static final String CUSTOMER_MERCHANT_MAPPING_BY_CID ="SELECT ct.categoryName,c.customerId,u.mobileNumber, u.firstName,u.lastName,u.emailId,mm.walletId,mm.walletBal,mm.cId,mm.loyaltyCardNumber,mm.isActive,m.merchantId,"
	    		+ "m.categoryCode,m.id,m.merchantName,m.emailId,um.mobileNumber,m.icon,m.PANNumber,m.GSTIN FROM customer c LEFT JOIN user u ON c.userId = u.id LEFT JOIN merchantMapping mm ON c.id = mm.cId "
	    		+ "LEFT JOIN merchant m ON mm.mId = m.id LEFT JOIN merchantUser mu ON mu.mId = m.id LEFT JOIN user um ON um.id = mu.userId LEFT JOIN category ct ON m.categoryCode = ct.categoryCode WHERE c.userId = ? AND mm.isActive = 1 GROUP BY m.id";

	    public static final String GET_MERCHANT_DETAIL = "SELECT m.*, u.loginId, u.mobileNumber, u.isLocked, u.numUnsuccessfulAttempts FROM merchant m LEFT JOIN merchantUser mu ON m.id = mu.mId LEFT JOIN user u ON u.id = mu.userId"; 
	    
	    public static final String DELETE_MERCHANT = "update merchant m join merchantUser mu on m.id = mu.mId join user u on u.id = mu.userId set m.isActive=0, mu.isActive=0, u.isActive=0, m.updatedBy = ?, mu.updatedBy = ?, u.updatedBy = ?  WHERE m.id = ?";

	    public static final String ACTIVE_MERCHANT = "update merchant m join merchantUser mu on m.id = mu.mId join user u on u.id = mu.userId set m.isActive=1, mu.isActive=1, u.isActive=1, m.updatedBy = ?, mu.updatedBy = ?, u.updatedBy = ?  WHERE m.id = ?";

	    public static final String GET_UESER_DETAIL_BY_MID = "SELECT u.loginId, u.mobileNumber, u.isLocked, u.numUnsuccessfulAttempts FROM user u INNER JOIN merchantUser mu ON mu.userId = u.id WHERE mu.mId = ?";

		public static final String GET_MERCHANT_BY_PAN_NUMBER = "select m.*, u.mobileNumber from merchant m LEFT JOIN merchantUser mu ON m.id = mu.mId LEFT JOIN user u ON u.id = mu.userId where m.PANNumber = ? and m.isActive = 1";

		public static final String GET_MERCHANT_BY_GSTIN = "select m.*, u.mobileNumber from merchant m LEFT JOIN merchantUser mu ON m.id = mu.mId LEFT JOIN user u ON u.id = mu.userId where m.GSTIN = ? and m.isActive = 1";
		
	    public static final String UNBLOCK_MERCHANT_USER = "update merchant m join merchantUser mu on m.id = mu.mId join user u on u.id = mu.userId set u.isLocked = 0, u.updatedBy = ?  WHERE m.id = ?";

	    public static final String GET_PANNUMBER = "select m.*, u.mobileNumber from merchant m LEFT JOIN merchantUser mu ON m.id = mu.mId LEFT JOIN user u ON u.id = mu.userId where m.PANNumber=? and m.isActive=1";

		public static final String GET_GSTIN = "select m.*, u.mobileNumber from merchant m LEFT JOIN merchantUser mu ON m.id = mu.mId LEFT JOIN user u ON u.id = mu.userId where m.GSTIN=? and m.isActive=1";

		public static final String GET_MERCHANT_BY_EMAILID = "select m.*, u.mobileNumber from merchant m LEFT JOIN merchantUser mu ON m.id = mu.mId LEFT JOIN user u ON u.id = mu.userId where m.emailId = ? and m.isActive=1";

		public static final String GET_MERCHANT_BY_MOBILE = "select m.*, u.mobileNumber from merchant m LEFT JOIN merchantUser mu ON m.id = mu.mId LEFT JOIN user u ON u.id = mu.userId where u.mobileNumber = ? and m.isActive=1";
		
		public static final String UPDATE_MERCHANT_PLAN = "update merchant set merchantPlanId=?, updatedby=?, updatedDate=NOW() where merchantId=?";

		public static final String SAVE_MERCHANT_DETAILS = "update merchant set categoryCode=?, currencyCode=?, countryId=?, aboutMe=?, PANNumber=?, GSTIN=?, icon=?,updatedby=?, updatedDate=NOW() where id=?";

		public static final String UPDATE_MERCHANT_FLAG = "update user set isUpdateDetail = 1 and isActive=1 where loginId = ?";

		public static final String GET_MERCHANT_BY_MN = "SELECT m.*, u.loginId, u.mobileNumber FROM merchant m LEFT JOIN merchantUser mu ON m.id = mu.mId LEFT JOIN user u ON u.id = mu.userId WHERE m.isActive=1 AND u.mobileNumber= ?";

		public static final String RECENT_MERCHANT_BY_AREA_PIN = "SELECT m.*,u.mobileNumber,c.countryName,cr.currencyCodeAlpha,cr.currencyName,ct.categoryName FROM merchant m LEFT JOIN merchantUser mu ON m.id = mu.mId LEFT JOIN user u ON u.id = mu.userId LEFT JOIN country c ON c.id = m.countryId LEFT JOIN currency cr ON cr.currencyCode = m.currencyCode LEFT JOIN category ct ON ct.categoryCode = m.categoryCode join merchantAddress md on md.mId = m.id WHERE md.areaCode LIKE ? or md.pincode LIKE ? and m.isActive = 1 AND m.isApproved = 1 GROUP BY m.id order by m.id desc";

		public static final String RECENT_MERCHANT_ADDRESS = "select DISTINCT areaCode,pincode from merchantAddress where isActive = 1";

		public static final String GET_TOTAL_MERCHANT = "SELECT * from merchant WHERE isActive = 1";

		public static final String GET_ACTIVE_MERCHANT = "SELECT DISTINCT mId FROM merchantTransaction WHERE createdDate BETWEEN ? and ?";

		public static final String GET_RECENT_MERCHANT = "SELECT * FROM merchant WHERE createdDate BETWEEN ? and ? AND isActive =1";

		public static final String GET_MERCHANT_WALLETBAL = "SELECT * FROM merchant WHERE isActive = 1";
		
		public static final String MAPPING_REQUEST_SEND_BY_CID ="SELECT ct.categoryName,c.customerId,u.mobileNumber, u.firstName,u.lastName,u.emailId,mm.walletId,mm.walletBal,mm.cId,mm.isActive,m.merchantId,"
	    		+ "m.categoryCode,m.id,m.merchantName,m.emailId,um.mobileNumber,m.icon,m.PANNumber,m.GSTIN FROM customer c LEFT JOIN user u ON c.userId = u.id JOIN merchantMapping mm ON c.id = mm.cId "
	    		+ "LEFT JOIN merchant m ON mm.mId = m.id LEFT JOIN merchantUser mu ON mu.mId = m.id LEFT JOIN user um ON um.id = mu.userId LEFT JOIN category ct ON m.categoryCode = ct.categoryCode WHERE c.userId = ? GROUP BY m.id";
} 
