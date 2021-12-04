create database mpls;

CREATE TABLE `sys_param` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `paramName` varchar(30) DEFAULT NULL,
  `paramDisplayName` varchar(50) DEFAULT NULL,
  `paramValue` varchar(30) DEFAULT NULL,
  `isActive` bit(1) DEFAULT 1,
  `createdBy` varchar(11) DEFAULT NULL,
  `updatedBy` varchar(11) DEFAULT NULL,
  `insertTimeStamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `updateTimeStamp` timestamp DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`)
);

insert into sys_param set paramName='customerIdPrefix', paramDisplayName='CustomerId Prefix', paramValue='DPC';
insert into sys_param set paramName='merchantIdPrefix', paramDisplayName='MerchantId Prefix', paramValue='DPM';
insert into sys_param set paramName='customerIdLength', paramDisplayName='Customer Id Length', paramValue='15';
insert into sys_param set paramName='merchantIdLength', paramDisplayName='Merchant Id Length', paramValue='15';
insert into sys_param set paramName='merchantIdPrefix', paramDisplayName='MerchantId Prefix', paramValue='DPM';
insert into sys_param set paramName='displayMerchantCount', paramDisplayName='Display Merchant Count', paramValue='50';
insert into sys_param set paramName='walletTransLength', paramDisplayName='Wallet Transaction Length', paramValue='12';
insert into sys_param set paramName='transactionPrefix', paramDisplayName='Transaction Prefix', paramValue='DPW';
insert into sys_param set paramName='transactionIdLength', paramDisplayName='Transaction Id Length', paramValue='19';
insert into sys_param set paramName='transCashbackType', paramDisplayName='Transaction Cashback Type', paramValue='First,Recurring';
insert into sys_param set paramName='limitDurationType', paramDisplayName='Limit Duration Type', paramValue='Day,Monthly';
insert into sys_param set paramName='limitType', paramDisplayName='Limt Type', paramValue='Customer,Merchant';
insert into sys_param set paramName='transactionType', paramDisplayName='Transaction Type', paramValue='TOPUP,PAY';
insert into sys_param set paramName='customerSearchParam', paramDisplayName='Customer Search Parameter', paramValue='Login Id~loginId, Customer Id~customerId, First Name~firstName, Mobile Number~mobileNumber,Email Id~emailId';
insert into sys_param set paramName='merchantSearchParam', paramDisplayName='Merchant Search Parameter', paramValue='Login Id~loginId, Merchant Id~merchantId, Merchant Name~merchantName, Mobile Number~mobileNumber,Email Id~emailId';


CREATE TABLE `menu` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `menuName` varchar(100) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `menuIcon` varchar(50) DEFAULT NULL,
  `action` varchar(100) DEFAULT NULL,
  `orderSequence` int(10) DEFAULT 0,
  `isActive` bit(1) DEFAULT b'1',
  `isAdd` bit(1) DEFAULT b'0',
  `isDelete` bit(1) DEFAULT b'0',
  `isUpdate` bit(1) DEFAULT b'0',
  `isMaskField` bit(1) DEFAULT b'0',
  `createdBy` varchar(11) DEFAULT NULL,
  `updatedBy` varchar(11) DEFAULT NULL,
  `insertTimeStamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `updateTimeStamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp(),
   PRIMARY KEY (`id`),
   INDEX (menuName)
);

CREATE TABLE `subMenu` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `menuId` int(10) NOT NULL,
  `subMenuName` char(100) NOT NULL,
  `description` char(200) DEFAULT NULL,
  `menuIcon` varchar(50) DEFAULT NULL,
  `action` varchar(100) DEFAULT NULL,
  `orderSequence` int(10) DEFAULT 0,
  `isActive` bit(1) DEFAULT b'1',
  `isAdd` bit(1) DEFAULT b'0',
  `isDelete` bit(1) DEFAULT b'0',
  `isUpdate` bit(1) DEFAULT b'0',
  `isMaskField` bit(1) DEFAULT b'0',
  `createdBy` varchar(11) DEFAULT NULL,
  `updatedBy` varchar(11) DEFAULT NULL,
  `insertTimeStamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `updateTimeStamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp(),
   PRIMARY KEY (`id`),
   FOREIGN KEY (menuId) REFERENCES menu(id),
   INDEX (subMenuName)
);

insert into menu set menuName='Dashboard', description='Dashboard', action='dashboard', menuIcon='feather icon-home', orderSequence=1;

insert into menu set menuName='Admin', description='Admin', menuIcon='fas fa-users-cog', orderSequence=2;
insert into subMenu set subMenuName='System Parameter', description='System Parameter', action='sysParameter/view', menuIcon='feather icon-file-plus', menuId=2, isAdd=1, isDelete=1, isUpdate=1, orderSequence=1;
insert into subMenu set subMenuName='Country', description='Country', action='country/view', menuIcon='feather icon-file-plus', menuId=2, isAdd=1, isDelete=1, isUpdate=1, orderSequence=2;
insert into subMenu set subMenuName='Currency', description='Add Currency', menuIcon='feather icon-file-plus', action='currency/view', menuId=2, isAdd=1, isDelete=1, isUpdate=1, orderSequence=2;
insert into subMenu set subMenuName='State', description='State', action='state/view', menuIcon='feather icon-file-plus', menuId=2, isAdd=1, isDelete=1, isUpdate=1, orderSequence=3;
insert into subMenu set subMenuName='City', description='City', action='city/view', menuIcon='feather icon-file-plus', menuId=2, isAdd=1, isDelete=1, isUpdate=1, orderSequence=4;
insert into subMenu set subMenuName='Area', description='Area', action='area/view', menuIcon='feather icon-file-plus', menuId=2, isAdd=1, isDelete=1, isUpdate=1, orderSequence=5;
insert into subMenu set subMenuName='Category', description='Category', action='category/view', menuIcon='feather icon-file-plus', menuId=2, isAdd=1, isDelete=1, isUpdate=1, orderSequence=6;
insert into subMenu set subMenuName='Merchant Plan', description='Merchant Plan', action='merchantPlan/view', menuIcon='feather icon-file-plus', menuId=2, isAdd=1, isDelete=1, isUpdate=1, orderSequence=6;
insert into subMenu set subMenuName='Limit Profile', description='Limit Profile', action='limitProfile/view', menuIcon='feather icon-file-plus', menuId=2, isAdd=1, isDelete=1, isUpdate=1, orderSequence=7;

insert into menu set menuName='User', description='User Management', action='', menuIcon='feather icon-users', orderSequence=3;
insert into subMenu set subMenuName='User Group', action='group/view', menuIcon='feather icon-file-plus', description='Add User Group', menuId=3, isAdd=1, isDelete=1, isUpdate=1, orderSequence=1;
insert into subMenu set subMenuName='Group Privilege', action='user/role/view', menuIcon='feather icon-file-plus', description='Add Group Privilege', menuId=3, isAdd=1, isDelete=1, isUpdate=1, orderSequence=2;
insert into subMenu set subMenuName='User', action='add/user/view', menuIcon='feather icon-file-plus', description='Add User', menuId=3, isAdd=1, isDelete=1, isUpdate=1, orderSequence=3;

insert into menu set menuName='Merchant', description='Merchant Management', action='', menuIcon='fas fa-store', orderSequence=4;
insert into subMenu set subMenuName='Registration', action='merchant/registration/view', menuIcon='feather icon-file-plus', description='Registration', menuId=4, isAdd=1, isDelete=1, isUpdate=1, orderSequence=1;
insert into subMenu set subMenuName='Profile', action='merchant/profile/view', menuIcon='feather icon-file-plus', description='Profile', menuId=4, isAdd=0, isDelete=0, isUpdate=0, orderSequence=2;
insert into subMenu set subMenuName='Wallet TOPUP', action='merchant/topUp/view', menuIcon='feather icon-file-plus', description='Merchant Wallet TOPUP', menuId=4, isAdd=1, orderSequence=3;
insert into subMenu set subMenuName='Employee', action='merchant/employee/view', menuIcon='feather icon-file-plus', description='Employee', menuId=4, isAdd=1, isDelete=1, isUpdate=1, orderSequence=3;
insert into subMenu set subMenuName='Loyalty Cashback', action='merchant/loyaltyCashback/view', menuIcon='feather icon-file-plus', description='Loyalty Cashback', menuId=4, isAdd=1, isDelete=1, isUpdate=1, orderSequence=4;
insert into subMenu set subMenuName='Add Customer', action='customer/view', menuIcon='feather icon-file-plus', description='Customer', menuId=4, isAdd=1, isDelete=0, isUpdate=0, orderSequence=5;
insert into subMenu set subMenuName='Update Merchant Plan', action='update/merchantPlan/view', menuIcon='feather icon-file-plus', description='Update Merchant Plan', menuId=4, isAdd=0, isDelete=0, isUpdate=1, orderSequence=6;
insert into subMenu set subMenuName='Transaction', action='customer/transaction/view', menuIcon='feather icon-file-plus', description='Customer Transaction', menuId=4, isAdd=0, isDelete=0, isUpdate=0, orderSequence=7;
insert into subMenu set subMenuName='Merchant Customers', action='merchant/customer/view', menuIcon='feather icon-file-plus', description='Merchant Customers', menuId=4, isAdd=0, isDelete=0, isUpdate=0, orderSequence=8;
insert into subMenu set subMenuName='Transaction History', action='merchant/transactionHistory/view', menuIcon='feather icon-file-plus', description='Merchant Transaction History', menuId=4, isAdd=0, isDelete=0, isUpdate=0, orderSequence=9;


insert into menu set menuName='Customer', description='Customer Management', action='', menuIcon='fas fa-users', orderSequence=5;
insert into subMenu set subMenuName='Search Store', action='search/store/view', menuIcon='feather icon-file-plus', description='Search Store', menuId=5, isAdd=0, isDelete=0, isUpdate=0, orderSequence=1;
insert into subMenu set subMenuName='Merchant', action='customer/merchant/view', menuIcon='feather icon-file-plus', description='Customer Merchants', menuId=5, isAdd=0, isDelete=0, isUpdate=0, orderSequence=2;
insert into subMenu set subMenuName='Transaction History', action='customer/transactionHistory/view', menuIcon='feather icon-file-plus', description='Customer Transactions History', menuId=5, isAdd=0, isDelete=0, isUpdate=0, orderSequence=3;

insert into menu set menuName='Operations', description='Customer Management', action='', menuIcon='feather icon-arrow-up-right', orderSequence=6;
insert into subMenu set subMenuName='Search Transaction', action='search/transaction/view', menuIcon='feather icon-file-plus', description='Search Transaction', menuId=6, isAdd=0, isDelete=0, isUpdate=0, orderSequence=1;
insert into subMenu set subMenuName='Search User', action='searchUser/view', menuIcon='feather icon-file-plus', description='Search User', menuId=6, isAdd=0, isDelete=0, isUpdate=0, orderSequence=2;



CREATE TABLE IF NOT EXISTS `country` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `countryCode` int(3) NOT NULL,
  `countryCodeAlpha` varchar(3) NOT NULL,
  `countryCode2Char` varchar(2) NOT NULL,
  `countryName` varchar(50) NOT NULL,
  `isActive` bit(1) DEFAULT b'1',
  `isApproved` int(11) DEFAULT 1,
  `createdBy` varchar(11) DEFAULT NULL,
  `updatedBy` varchar(11) DEFAULT NULL,
  `insertTimeStamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `updateTimeStamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `countryCode` (`countryCode`),
  KEY `countryName` (`countryName`)
);

CREATE TABLE IF NOT EXISTS `currency` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `currencyCode` int(3) NOT NULL,
  `countryName` varchar(50) NOT NULL,
  `currencyCodeAlpha` varchar(3) NOT NULL,
  `currencyName` varchar(50) NOT NULL,
  `exponent` int(1) DEFAULT 0,
  `isActive` bit(1) DEFAULT b'1',
  `isApproved` int(11) DEFAULT 1,
  `createdBy` varchar(11) DEFAULT NULL,
  `updatedBy` varchar(11) DEFAULT NULL,
  `insertTimeStamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `updateTimeStamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  CONSTRAINT FOREIGN KEY (`countryName`) REFERENCES `country` (`countryName`),
  INDEX (currencyCode)
);

CREATE TABLE IF NOT EXISTS `state` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stateName` varchar(50) NOT NULL,
  `countryId` int(11) NOT NULL,
  `isActive` bit(1) DEFAULT 1,
  `isApproved` int DEFAULT 1,
  `createdBy` varchar(11) DEFAULT NULL,
  `updatedBy` varchar(11) DEFAULT NULL,
  `insertTimeStamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `updateTimeStamp` timestamp DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`),
   FOREIGN KEY (countryId) REFERENCES country(id)
);

CREATE TABLE IF NOT EXISTS `city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cityName` varchar(100) NOT NULL,
  `stateId` int(11) NOT NULL,
  `countryId` int(11) NOT NULL,
  `isActive` bit(1) DEFAULT 1,
  `isApproved` int DEFAULT 1,
  `createdBy` varchar(11) DEFAULT NULL,
  `updatedBy` varchar(11) DEFAULT NULL,
  `insertTimeStamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `updateTimeStamp` timestamp DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`),
   FOREIGN KEY (stateId) REFERENCES state(id),
   FOREIGN KEY (countryId) REFERENCES country(id)
);

CREATE TABLE IF NOT EXISTS `area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `areaCode` varchar(10) NOT NULL,
  `areaName` varchar(100) NOT NULL,
  `pincode` varchar(6) NOT NULL,
  `latitude` varchar(200) DEFAULT NULL,
  `longitude` varchar(200) DEFAULT NULL,
  `cityId` int(11) NOT NULL,
  `stateId` int(11) NOT NULL,
  `countryId` int(11) NOT NULL,
  `isActive` bit(1) DEFAULT 1,
  `isApproved` int DEFAULT 1,
  `createdBy` varchar(11) DEFAULT NULL,
  `updatedBy` varchar(11) DEFAULT NULL,
  `insertTimeStamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `updateTimeStamp` timestamp DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`),
   FOREIGN KEY (cityId) REFERENCES city(id),
   FOREIGN KEY (stateId) REFERENCES state(id),
   FOREIGN KEY (countryId) REFERENCES country(id)
);


CREATE TABLE IF NOT EXISTS `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categoryCode` varchar(10) NOT NULL,
  `categoryName` varchar(200) NOT NULL,
  `description` varchar(500) NOT NULL,
  `isActive` bit(1) DEFAULT 1,
  `isApproved` int DEFAULT 1,
  `createdBy` varchar(11) DEFAULT NULL,
  `updatedBy` varchar(11) DEFAULT NULL,
  `insertTimeStamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `updateTimeStamp` timestamp DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`)
);

CREATE TABLE `menuDetailMapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menuName` varchar(50) DEFAULT NULL,
  `dbTableName` varchar(50) DEFAULT NULL,
  `fieldName` varchar(1000) DEFAULT NULL,
   PRIMARY KEY (`id`)
);

insert into menuDetailMapping set menuName='Country',dbTableName='country',fieldName='Country Code`countryCode,Country Alpha`countryCodeAlpha,Country Name`countryName';
insert into menuDetailMapping set menuName='Currency',dbTableName='currency',fieldName='Currency Code`currencyCode,Currency Alpha`currencyCodeAlpha,Currency Name`currencyName,Exponent`exponent';
insert into menuDetailMapping set menuName='State',dbTableName='State',fieldName='country Id`countryId,State Name`stateName';

CREATE TABLE `user_history` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `loginId` varchar(50) DEFAULT NULL,
  `insertTimeStamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `updateTimeStamp` timestamp NOT NULL,
   PRIMARY KEY (`id`),
   INDEX (loginId)
);

CREATE TABLE `user_history_detail` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `loginId` varchar(50) DEFAULT NULL,
  `requestUrl` varchar(500) DEFAULT NULL,
  `sessionId` varchar(50) DEFAULT NULL,
  `source` varchar(10) DEFAULT NULL,
  `ipAddress` varchar(40) DEFAULT NULL,
  `os` varchar(40) DEFAULT NULL,
  `browser` varchar(20) DEFAULT NULL,
  `timestamp` timestamp NOT NULL DEFAULT current_timestamp(),
   PRIMARY KEY (`id`),
   FOREIGN KEY (loginId) REFERENCES user_history(loginId),
   INDEX (loginId)
);

CREATE TABLE `userGroupType` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `groupType` varchar(50) DEFAULT NULL,
  `displayName` varchar(50) NOT NULL,
  `isActive` bit(1) DEFAULT b'1',
  `insertTimeStamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `updateTimeStamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp(),
   PRIMARY KEY (`id`)
);

insert into userGroupType set groupType='maker', displayName='Maker', updateTimeStamp=now();
insert into userGroupType set groupType='checker', displayName='Checker', updateTimeStamp=now();
insert into userGroupType set groupType='admin', displayName='Admin', updateTimeStamp=now();
insert into userGroupType set groupType='merchant', displayName='Merchant', updateTimeStamp=now();
insert into userGroupType set groupType='customer', displayName='Customer', updateTimeStamp=now();


CREATE TABLE `usergroup` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `systemId` varchar(10) NOT NULL,
  `groupId` varchar(10) NOT NULL,
  `groupName` varchar(20) NOT NULL,
  `groupType` varchar(20) NOT NULL,
  `minPassLength` int(2) NOT NULL,
  `maxPassLength` int(2) NOT NULL,
  `isAlphaPassword` varchar(1) DEFAULT 'N',
  `isNumberPassword` varchar(1) DEFAULT 'N',
  `isSpecialSymbolPass` varchar(1) DEFAULT 'N',
  `passExpiryDays` int(1) NOT NULL,
  `passHistoryChecks` int(1) NOT NULL,
  `maxConcurrentLogin` int(2) NOT NULL,
  `maxLoginRetries` int(2) NOT NULL,
  `status` varchar(1) DEFAULT NULL,
  `isActive` bit(1) DEFAULT b'1',
  `isApproved` int(11) DEFAULT 1,
  `isEditable` int(11) DEFAULT 1,
  `createdBy` varchar(11) DEFAULT NULL,
  `updatedBy` varchar(11) DEFAULT NULL,
  `insertTimeStamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `updateTimeStamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `systemId` (`systemId`),
  KEY `groupId` (`groupId`),
  CONSTRAINT `usergroup_ibfk_1` FOREIGN KEY (`systemId`) REFERENCES `system` (`systemId`)
);

CREATE TABLE `userRole` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `groupId` varchar(10) NOT NULL,
  `menuId` int(10) NOT NULL,
  `subMenuId` int(10) DEFAULT NULL,
  `isAdd` bit(1) DEFAULT b'0',
  `isDelete` bit(1) DEFAULT b'0',
  `isUpdate` bit(1) DEFAULT b'0',
  `isMaskField` bit(1) DEFAULT b'0',
  `isActive` bit(1) DEFAULT b'1',
  `isApproved` int DEFAULT 1,
  `createdBy` varchar(11) DEFAULT NULL,
  `updatedBy` varchar(11) DEFAULT NULL,
  `insertTimeStamp` timestamp NOT NULL,
  `updateTimeStamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp(),
   PRIMARY KEY (`id`),
   FOREIGN KEY (groupId) REFERENCES usergroup(groupId)
);

CREATE TABLE `user` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `systemId` varchar(10) NOT NULL,
  `groupId` varchar(10) NOT NULL,
  `loginId` varchar(11) NOT NULL,
  `domainUserName` varchar(13) DEFAULT NULL,
  `firstName` varchar(30) NOT NULL,
  `middleName` varchar(30) DEFAULT NULL,
  `lastName` varchar(30) NOT NULL,
  `emailId` varchar(37) NOT NULL,
  `password` varchar(128) DEFAULT NULL,
  `userPin` varchar(128) DEFAULT NULL,
  `isEnableFingerprint` bit(1) DEFAULT b'0',
  `lastPassword` varchar(128) DEFAULT NULL,
  `passwordChangedDate` timestamp NOT NULL DEFAULT current_timestamp(),
  `lastLoggedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `lastSessionId` varchar(128) DEFAULT NULL,
  `numUnsuccessfulAttempts` int(11) DEFAULT 0,
  `maxConcurrentLogin` int(11) DEFAULT 0,
  `managerId` varchar(11) DEFAULT NULL,
  `isLocked` bit(1) DEFAULT b'0',
  `isActive` bit(1) DEFAULT b'1',
  `userType` int DEFAULT 1 COMMENT '1 - Admin/Maker/checker, 2 - Customer, 3-Merchant,  4-SuperAdmin',
  `isApproved` int(11) DEFAULT 1,
  `status` varchar(1) DEFAULT NULL,
  `createdBy` varchar(11) DEFAULT NULL,
  `updatedBy` varchar(11) DEFAULT NULL,
  `insertTimeStamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `updateTimeStamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `groupId` (`groupId`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`groupId`) REFERENCES `usergroup` (`groupId`)
);

CREATE TABLE `passwordHistoryDetail` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `loginId` varchar(50) DEFAULT NULL,
  `password` varchar(128) NOT NULL,
  `isActive` bit(1) DEFAULT b'1',
  `createdBy` varchar(11) DEFAULT NULL,
  `updatedBy` varchar(11) DEFAULT NULL,
  `insertTimeStamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `updateTimeStamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp(),
   PRIMARY KEY (`id`),
   INDEX (loginId)
);


CREATE TABLE `userNotification` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `creatorId` varchar(11) DEFAULT NULL,
  `checkerId` varchar(11) DEFAULT NULL,
  `status` int DEFAULT 0,
  `recordId` varchar(10) DEFAULT '0',
  `tableName` varchar(50) DEFAULT '1',
  `menuName` varchar(50) DEFAULT null,
  `message` varchar(100) DEFAULT NULL,
  `comments` varchar(1000) DEFAULT NULL,
  `insertTimeStamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `updateTimeStamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `creatorId` (`creatorId`),
  KEY `makerId` (`checkerId`)
);

CREATE TABLE IF NOT EXISTS `system` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `systemId` varchar(10) NOT NULL,
  `systemName` varchar(40) NOT NULL,
  `addressLine1` varchar(50) NOT NULL,
  `addressLine2` varchar(50) DEFAULT NULL,
  `city` varchar(30) DEFAULT NULL,
  `state` varchar(30) DEFAULT NULL,
  `postalCode` varchar(10) DEFAULT NULL,
  `country` varchar(30) DEFAULT NULL,
  `firstName` varchar(30) NOT NULL,
  `middleName` varchar(30) DEFAULT NULL,
  `lastName` varchar(30) NOT NULL,
  `emailId` varchar(50) NOT NULL,
  `phoneNumber` varchar(30) NOT NULL,
  `mobileNumber` varchar(30) NOT NULL,
  `faxNo` varchar(30) NOT NULL,
  `status` varchar(1) DEFAULT NULL,
  `isActive` bit(1) DEFAULT b'1',
  `isApproved` int DEFAULT 1,
  `createdBy` varchar(11) DEFAULT NULL,
  `updatedBy` varchar(11) DEFAULT NULL,
  `insertTimeStamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `updateTimeStamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  INDEX (systemId)
);

-- --User configuration--
insert into `system` set systemId='DazzlePay', systemName='DAZZLEPAY PAYMENT PROCESSING PVT LTD', addressLine1='Flat No. 1604, 16th Floor, Panaroma Towers', addressLine2='Prathmesh Complex, Veera Desai Road, Andheri West', city='Mumbai', state='Maharashtra',postalCode='400053',country='India',firstName='Deepak',middleName='', lastName='BHUTRA',emailId='deepak@dazzlepay.co.in',phoneNumber='9820981763',mobileNumber='9820981763',faxNo='9820981763',status=1,isActive=1,isApproved=1;
insert into usergroup set systemId='DazzlePay',groupId='SuperAdmin', groupName='Super Admin',groupType='admin',minPassLength=8,maxPassLength=10,isAlphaPassword='Y',isNumberPassword='Y',isSpecialSymbolPass='Y',passExpiryDays=30,passHistoryChecks=5,maxConcurrentLogin=3,maxLoginRetries=3,isActive=1,status=1,isApproved=1,createdBy='SuperAdmin';
insert into usergroup set systemId='DazzlePay',groupId='customer', groupName='Customer',groupType='customer',minPassLength=8,maxPassLength=10,isAlphaPassword='Y',isNumberPassword='Y',isSpecialSymbolPass='Y',passExpiryDays=30,passHistoryChecks=5,maxConcurrentLogin=3,maxLoginRetries=3,isActive=1,status=1,isApproved=1,createdBy='SuperAdmin';
insert into usergroup set systemId='DazzlePay',groupId='merchant', groupName='Merchant',groupType='merchant',minPassLength=8,maxPassLength=10,isAlphaPassword='Y',isNumberPassword='Y',isSpecialSymbolPass='Y',passExpiryDays=30,passHistoryChecks=5,maxConcurrentLogin=3,maxLoginRetries=3,isActive=1,status=1,isApproved=1,createdBy='SuperAdmin';
insert into usergroup set systemId='DazzlePay',groupId='admin', groupName='Admin',groupType='admin',minPassLength=8,maxPassLength=10,isAlphaPassword='Y',isNumberPassword='Y',isSpecialSymbolPass='Y',passExpiryDays=30,passHistoryChecks=5,maxConcurrentLogin=3,maxLoginRetries=3,isActive=1,status=1,isApproved=1,createdBy='SuperAdmin';

insert into userRole set groupId='SuperAdmin',menuId=3,subMenuId=0,isAdd=0,isDelete=0,isUpdate=0,isMaskField=0,isActive=1,isApproved=1;
insert into userRole set groupId='SuperAdmin',menuId=3,subMenuId=9,isAdd=1,isDelete=1,isUpdate=1,isMaskField=0,isActive=1,isApproved=1;
insert into userRole set groupId='SuperAdmin',menuId=3,subMenuId=10,isAdd=1,isDelete=1,isUpdate=1,isMaskField=0,isActive=1,isApproved=1;
insert into userRole set groupId='SuperAdmin',menuId=3,subMenuId=11,isAdd=1,isDelete=1,isUpdate=1,isMaskField=0,isActive=1,isApproved=1;

INSERT INTO `user` VALUES (1,'DazzlePay','','','SuperAdmin','SuperAdmin','DazzlePay','DazzlePay','PAYMENT','PROCESSING','care@dazzlepay.co.in','$2a$11$3X.7B5WWYdMLKS0FUE/aTOTt8jtlAyAzyz4RExpwRqYnoT9adv/ea','$2a$11$3X.7B5WWYdMLKS0FUE/aTOTt8jtlAyAzyz4RExpwRqYnoT9adv/ea','2019-10-25 06:40:31','2019-11-20 14:12:01','D12C03E8605D064F52638D5E8BE4B25B',0,0,'',1,1,1,1,'superAdmin','superAdmin','2019-10-19 20:25:31','2019-11-20 14:12:01');
insert into passwordHistoryDetail set loginId='SuperAdmin',password='$2a$11$3X.7B5WWYdMLKS0FUE/aTOTt8jtlAyAzyz4RExpwRqYnoT9adv/ea',isActive=1,userType=4;


CREATE TABLE `merchant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `merchantId` varchar(15) NOT NULL,
  `categoryCode` varchar(10) NOT NULL,
  `merchantName` varchar(100) NOT NULL,
  `icon` LONGTEXT NOT NULL,
  `firstName` varchar(30) NOT NULL,
  `middleName` varchar(30) DEFAULT NULL,
  `lastName` varchar(30) NOT NULL,
  `emailId` varchar(50) NOT NULL,
  `mobileNumber` varchar(10) NOT NULL,
  `aboutMe` text NOT NULL,
  `PANNumber` varchar(10) NOT NULL,
  `GSTIN` varchar(15) NOT NULL,
  `walletBal` double default 0,
  `countryId` int(11) NOT NULL,
  `currencyCode` int(3) NOT NULL,
  `merchantNumber` varchar(15) NOT NULL,
  `merchantPlanId` int(11) DEFAULT 0,
  `isActive` bit(1) DEFAULT b'1',
  `isApproved` int(11) DEFAULT 1,
  `createdDate` timestamp NULL DEFAULT current_timestamp(),
  `createdBy` varchar(50) DEFAULT NULL,
  `updatedDate` timestamp NULL DEFAULT NULL,
  `updatedBy` varchar(50) DEFAULT NULL,
   PRIMARY KEY (`id`),
   INDEX (merchantId),
   INDEX (merchantNumber),
   INDEX (PANNumber),
   INDEX (GSTIN),
   INDEX (merchantName)
);

 CREATE TABLE `merchantAddress` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `mId` int(11) NOT NULL,
    `stateId` int(11) NOT NULL,
    `cityId` int(11) NOT NULL,
    `areaCode` varchar(10) NOT NULL,
    `pincode` varchar(6) NOT NULL,
    `isActive` bit(1) DEFAULT b'1',
    PRIMARY KEY (`id`),
    FOREIGN KEY (mId) REFERENCES merchant(id)
);


CREATE TABLE `merchantUser` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `mId` int(11) NOT NULL,
    `userId` int(11) NOT NULL,
    `mobileNumber` varchar(11) NOT NULL,
    `isActive` bit(1) DEFAULT b'1',
    `isOwner` bit(1) DEFAULT b'0',
    `createdDate` timestamp NULL DEFAULT current_timestamp(),
    `createdBy` varchar(50) DEFAULT NULL,
    `updatedDate` timestamp NULL DEFAULT NULL,
    `updatedBy` varchar(50) DEFAULT NULL,
     PRIMARY KEY (`id`),
     FOREIGN KEY (mId) REFERENCES merchant(id),
     FOREIGN KEY (userId) REFERENCES user(id)
);

CREATE TABLE `customer` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `userId` int(11) NOT NULL,
    `customerId` varchar(15) NOT NULL,
    `customerNumber` varchar(15) NOT NULL,
    `mobileNumber` varchar(10) NOT NULL,
    `walletBal` double default 0,
    `userImage` LONGTEXT DEFAULT NULL,
    `isActive` bit(1) DEFAULT b'1',
    `createdDate` timestamp NULL DEFAULT current_timestamp(),
    `createdBy` varchar(50) DEFAULT NULL,
    `updatedDate` timestamp NULL DEFAULT NULL,
    `updatedBy` varchar(50) DEFAULT NULL,
     PRIMARY KEY (`id`),
     FOREIGN KEY (userId) REFERENCES user(id)
);

CREATE TABLE `verificationLog` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `userType` varchar(30) NOT NULL,
    `emailId` varchar(50) DEFAULT NULL,
    `mobileNumber` varchar(10) DEFAULT NULL,
    `verificationCode` varchar(10) DEFAULT NULL,
    `isVerified` bit(1) DEFAULT b'0',
    `createdDate` timestamp NULL DEFAULT current_timestamp(),
    `createdBy` varchar(50) DEFAULT NULL,
    `updatedDate` timestamp NULL DEFAULT NULL,
    `updatedBy` varchar(50) DEFAULT NULL,
     PRIMARY KEY (`id`),
     INDEX (emailId),
     INDEX (mobileNumber)
);


CREATE TABLE `merchantMapping` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `walletId` varchar(30) NOT NULL,
    `cId` int(11) NOT NULL,
    `mId` int(11) NOT NULL,
    `walletBal` double default 0,
    `isActive` bit(1) DEFAULT b'1',
    `createdDate` timestamp NULL DEFAULT current_timestamp(),
    `createdBy` varchar(50) DEFAULT NULL,
    `updatedDate` timestamp NULL DEFAULT NULL,
    `updatedBy` varchar(50) DEFAULT NULL,
     PRIMARY KEY (`id`),
     INDEX (walletId),
     FOREIGN KEY (cId) REFERENCES customer(id),
     FOREIGN KEY (mId) REFERENCES merchant(id)
);


CREATE TABLE `merchantTransaction` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `cId` int(11) NOT NULL,
    `mId` int(11) NOT NULL,
    `walletTransactionId` varchar(12) NOT NULL,
    `transactionId` varchar(19) NOT NULL,
    `transactionType` varchar(20) NOT NULL,
    `transactionValue` double default 0,
    `indicator` varchar(1) NOT NULL,
    `payType` varchar(100) NOT NULL,
    `offerCode` varchar(15) DEFAULT NULL,
    `status` varchar(1) DEFAULT 'F',
    `createdDate` timestamp NULL DEFAULT current_timestamp(),
    `createdBy` varchar(50) DEFAULT NULL,
    `updatedDate` timestamp NULL DEFAULT NULL,
    `updatedBy` varchar(50) DEFAULT NULL,
     PRIMARY KEY (`id`),
     INDEX (transactionId),
     FOREIGN KEY (mId) REFERENCES merchant(id)
);

CREATE TABLE `customerTransaction` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `cId` int(11) NOT NULL,
    `mId` int(11) NOT NULL,
    `walletTransactionId` varchar(12) NOT NULL,
    `transactionId` varchar(19) NOT NULL,
    `transactionType` varchar(20) NOT NULL,
    `transactionValue` double default 0,
    `indicator` varchar(1) NOT NULL,
    `payType` varchar(100) NOT NULL,
    `offerCode` varchar(15) DEFAULT NULL,
    `status` varchar(1) DEFAULT 'F',
    `createdDate` timestamp NULL DEFAULT current_timestamp(),
    `createdBy` varchar(50) DEFAULT NULL,
    `updatedDate` timestamp NULL DEFAULT NULL,
    `updatedBy` varchar(50) DEFAULT NULL,
     PRIMARY KEY (`id`),
     INDEX (transactionId),
     FOREIGN KEY (cId) REFERENCES customer(id),
     FOREIGN KEY (mId) REFERENCES merchant(id)
);

CREATE TABLE `apiToken` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `deviceId` varchar(300) NOT NULL,
    `apiKey` varchar(20) NOT NULL,
    `token` varchar(1000) NOT NULL,
    `isActive` bit(1) DEFAULT b'1',
    `createdDate` timestamp NULL DEFAULT current_timestamp(),
    `updatedDate` timestamp NULL DEFAULT NULL,
     PRIMARY KEY (`id`),
     INDEX (deviceId),
     INDEX (token)
);

CREATE TABLE `loyaltyCashback` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `mId` int(11) NOT NULL,
    `offerCode` varchar(15) NOT NULL,
    `offerName` varchar(150) NOT NULL,
    `transCashbackType` varchar(15) NOT NULL,
    `cashbackType` varchar(10) NOT NULL,
    `minTransValue` double default 0 NOT NULL,
    `fixedCashbackAmt` double default 0,
    `cashbackPercentage` varchar(3) NOT NULL,
    `maxCashbackAmt` double default 0,
    `isActive` bit(1) DEFAULT b'1',
    `createdDate` timestamp NULL DEFAULT current_timestamp(),
    `createdBy` varchar(50) DEFAULT NULL,
    `updatedDate` timestamp NULL DEFAULT NULL,
    `updatedBy` varchar(50) DEFAULT NULL,
     PRIMARY KEY (`id`),
     INDEX (offerName),
     INDEX (transCashbackType),
     INDEX (cashbackType),
     FOREIGN KEY (mId) REFERENCES merchant(id)
);


CREATE TABLE `appNotification` (
    `id` int(11) NOT NULL AUTO_INCREMENT,
    `cId` int(11) DEFAULT 0,
    `mId` int(11) DEFAULT 0,
    `deviceId` varchar(300) NOT NULL,
    `token` text NOT NULL,
    `isActive` bit(1) DEFAULT b'1',
    `createdDate` timestamp NULL DEFAULT current_timestamp(),
    `updatedDate` timestamp NULL DEFAULT NULL,
     PRIMARY KEY (`id`),
     INDEX (cId),
     INDEX (mId)
);


CREATE TABLE IF NOT EXISTS `merchantVersion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `versionName` varchar(100) NOT NULL,
  `description` varchar(500) NOT NULL,
  `isActive` bit(1) DEFAULT 1,
  `isApproved` int DEFAULT 1,
  `createdBy` varchar(11) DEFAULT NULL,
  `updatedBy` varchar(11) DEFAULT NULL,
  `insertTimeStamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `updateTimeStamp` timestamp DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
   PRIMARY KEY (`id`)
);

insert into merchantVersion set versionName='Basic', description='Basic Version Merchant';
insert into merchantVersion set versionName='Premium', description='Premium Version Merchant';
insert into merchantVersion set versionName='Super-Premium', description='Super-Premium Version Merchant';
