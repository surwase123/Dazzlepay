-- MySQL dump 10.17  Distrib 10.3.17-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: mpls_init
-- ------------------------------------------------------
-- Server version	10.3.17-MariaDB-1:10.3.17+maria~xenial-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `apiToken`
--

DROP TABLE IF EXISTS `apiToken`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `apiToken` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `deviceId` varchar(300) NOT NULL,
  `apiKey` varchar(20) NOT NULL,
  `token` varchar(1000) NOT NULL,
  `isActive` bit(1) DEFAULT b'1',
  `createdDate` timestamp NULL DEFAULT current_timestamp(),
  `updatedDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `deviceId` (`deviceId`),
  KEY `token` (`token`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `apiToken`
--

LOCK TABLES `apiToken` WRITE;
/*!40000 ALTER TABLE `apiToken` DISABLE KEYS */;
/*!40000 ALTER TABLE `apiToken` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appNotification`
--

DROP TABLE IF EXISTS `appNotification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
  KEY `cId` (`cId`),
  KEY `mId` (`mId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appNotification`
--

LOCK TABLES `appNotification` WRITE;
/*!40000 ALTER TABLE `appNotification` DISABLE KEYS */;
/*!40000 ALTER TABLE `appNotification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `area`
--

DROP TABLE IF EXISTS `area`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `area` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `areaCode` varchar(10) NOT NULL,
  `areaName` varchar(100) NOT NULL,
  `pincode` varchar(6) NOT NULL,
  `latitude` varchar(200) DEFAULT NULL,
  `longitude` varchar(200) DEFAULT NULL,
  `cityId` int(11) NOT NULL,
  `stateId` int(11) NOT NULL,
  `countryId` int(11) NOT NULL,
  `isActive` bit(1) DEFAULT b'1',
  `isApproved` int(11) DEFAULT 1,
  `createdBy` varchar(11) DEFAULT NULL,
  `updatedBy` varchar(11) DEFAULT NULL,
  `insertTimeStamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `updateTimeStamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `cityId` (`cityId`),
  KEY `stateId` (`stateId`),
  KEY `countryId` (`countryId`),
  CONSTRAINT `area_ibfk_1` FOREIGN KEY (`cityId`) REFERENCES `city` (`id`),
  CONSTRAINT `area_ibfk_2` FOREIGN KEY (`stateId`) REFERENCES `state` (`id`),
  CONSTRAINT `area_ibfk_3` FOREIGN KEY (`countryId`) REFERENCES `country` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `area`
--

LOCK TABLES `area` WRITE;
/*!40000 ALTER TABLE `area` DISABLE KEYS */;
/*!40000 ALTER TABLE `area` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `categoryCode` varchar(10) NOT NULL,
  `categoryName` varchar(200) NOT NULL,
  `description` varchar(500) NOT NULL,
  `isActive` bit(1) DEFAULT b'1',
  `isApproved` int(11) DEFAULT 1,
  `createdBy` varchar(11) DEFAULT NULL,
  `updatedBy` varchar(11) DEFAULT NULL,
  `insertTimeStamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `updateTimeStamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `city`
--

DROP TABLE IF EXISTS `city`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `city` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cityName` varchar(100) NOT NULL,
  `stateId` int(11) NOT NULL,
  `countryId` int(11) NOT NULL,
  `isActive` bit(1) DEFAULT b'1',
  `isApproved` int(11) DEFAULT 1,
  `createdBy` varchar(11) DEFAULT NULL,
  `updatedBy` varchar(11) DEFAULT NULL,
  `insertTimeStamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `updateTimeStamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `stateId` (`stateId`),
  KEY `countryId` (`countryId`),
  CONSTRAINT `city_ibfk_1` FOREIGN KEY (`stateId`) REFERENCES `state` (`id`),
  CONSTRAINT `city_ibfk_2` FOREIGN KEY (`countryId`) REFERENCES `country` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
/*!40000 ALTER TABLE `city` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `country`
--

DROP TABLE IF EXISTS `country`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `country` (
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,356,'IND','IN','India','',1,NULL,NULL,'2020-06-23 12:47:53','0000-00-00 00:00:00');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currency`
--

DROP TABLE IF EXISTS `currency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `currency` (
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
  KEY `countryName` (`countryName`),
  KEY `currencyCode` (`currencyCode`),
  CONSTRAINT `currency_ibfk_1` FOREIGN KEY (`countryName`) REFERENCES `country` (`countryName`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currency`
--

LOCK TABLES `currency` WRITE;
/*!40000 ALTER TABLE `currency` DISABLE KEYS */;
INSERT INTO `currency` VALUES (1,356,'India','INR','INDIAN RUPEE',0,'',1,NULL,NULL,'2020-06-23 12:48:22','0000-00-00 00:00:00');
/*!40000 ALTER TABLE `currency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `customerId` varchar(15) NOT NULL,
  `customerNumber` varchar(15) NOT NULL,
  `mobileNumber` varchar(10) NOT NULL,
  `walletBal` double DEFAULT 0,
  `userImage` longtext DEFAULT NULL,
  `isActive` bit(1) DEFAULT b'1',
  `createdDate` timestamp NULL DEFAULT current_timestamp(),
  `createdBy` varchar(50) DEFAULT NULL,
  `updatedDate` timestamp NULL DEFAULT NULL,
  `updatedBy` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  CONSTRAINT `customer_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customerTransaction`
--

DROP TABLE IF EXISTS `customerTransaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customerTransaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cId` int(11) NOT NULL,
  `mId` int(11) NOT NULL,
  `walletTransactionId` varchar(12) NOT NULL,
  `transactionId` varchar(19) NOT NULL,
  `transactionType` varchar(20) NOT NULL,
  `transactionValue` double DEFAULT 0,
  `indicator` varchar(1) NOT NULL,
  `payType` varchar(100) NOT NULL,
  `offerCode` varchar(15) DEFAULT NULL,
  `status` varchar(1) DEFAULT 'F',
  `createdDate` timestamp NULL DEFAULT current_timestamp(),
  `createdBy` varchar(50) DEFAULT NULL,
  `updatedDate` timestamp NULL DEFAULT NULL,
  `updatedBy` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `transactionId` (`transactionId`),
  KEY `cId` (`cId`),
  KEY `mId` (`mId`),
  CONSTRAINT `customerTransaction_ibfk_1` FOREIGN KEY (`cId`) REFERENCES `customer` (`id`),
  CONSTRAINT `customerTransaction_ibfk_2` FOREIGN KEY (`mId`) REFERENCES `merchant` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customerTransaction`
--

LOCK TABLES `customerTransaction` WRITE;
/*!40000 ALTER TABLE `customerTransaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `customerTransaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `limitManagement`
--

DROP TABLE IF EXISTS `limitManagement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `limitManagement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cId` int(11) DEFAULT 0,
  `mId` int(11) DEFAULT 0,
  `limitType` varchar(50) NOT NULL,
  `createdBy` varchar(50) DEFAULT NULL,
  `createdDate` timestamp NULL DEFAULT current_timestamp(),
  `updatedBy` varchar(50) DEFAULT NULL,
  `updatedDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `cId` (`cId`),
  KEY `mId` (`mId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `limitManagement`
--

LOCK TABLES `limitManagement` WRITE;
/*!40000 ALTER TABLE `limitManagement` DISABLE KEYS */;
/*!40000 ALTER TABLE `limitManagement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `limitManagementDetail`
--

DROP TABLE IF EXISTS `limitManagementDetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `limitManagementDetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `limitManagementId` int(11) NOT NULL,
  `durationType` varchar(100) NOT NULL,
  `transactionType` varchar(100) NOT NULL,
  `noOfTransaction` int(11) NOT NULL,
  `transactionLimit` double DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `limitManagementId` (`limitManagementId`),
  CONSTRAINT `limitManagementDetail_ibfk_1` FOREIGN KEY (`limitManagementId`) REFERENCES `limitManagement` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `limitManagementDetail`
--

LOCK TABLES `limitManagementDetail` WRITE;
/*!40000 ALTER TABLE `limitManagementDetail` DISABLE KEYS */;
/*!40000 ALTER TABLE `limitManagementDetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `limitProfile`
--

DROP TABLE IF EXISTS `limitProfile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `limitProfile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `limitName` varchar(100) NOT NULL,
  `limitType` varchar(100) NOT NULL,
  `merchantVersionId` int(11) DEFAULT 0,
  `description` varchar(200) NOT NULL,
  `isActive` bit(1) DEFAULT b'1',
  `createdBy` varchar(50) DEFAULT NULL,
  `createdDate` timestamp NULL DEFAULT current_timestamp(),
  `updatedBy` varchar(50) DEFAULT NULL,
  `updatedDate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `limitName` (`limitName`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `limitProfile`
--

LOCK TABLES `limitProfile` WRITE;
/*!40000 ALTER TABLE `limitProfile` DISABLE KEYS */;
INSERT INTO `limitProfile` VALUES (1,'Customer Limit','Customer',0,'Customer Limit','\0','SuperAdmin','2020-06-20 12:06:22','SuperAdmin','2020-06-20 12:07:39'),(2,'Customer Limit','Customer',0,'Customer Limit','\0','SuperAdmin','2020-06-20 12:07:39','SuperAdmin','2020-06-20 13:15:32'),(3,'Customer Limit','Customer',0,'Customer Limit','\0','SuperAdmin','2020-06-20 13:15:32','SuperAdmin','2020-06-20 13:18:24'),(4,'Merchant Basic Limit','Merchant',1,'Merchant Basic Limit','\0','SuperAdmin','2020-06-20 13:18:09','SuperAdmin','2020-06-20 13:27:07'),(5,'Customer Limit','Customer',0,'Customer Limit','\0','SuperAdmin','2020-06-20 13:18:24','SuperAdmin','2020-06-20 13:39:22'),(6,'Merchant Limit','Merchant',2,'Merchant Premium Limit','\0','SuperAdmin','2020-06-20 13:19:11','SuperAdmin','2020-06-20 13:25:23'),(7,'Merchant Premium','Merchant',2,'Merchant Premium Limit','\0','SuperAdmin','2020-06-20 13:25:23','SuperAdmin','2020-06-20 13:27:52'),(8,'Merchant Basic','Merchant',1,'Merchant Basic Limit','\0','SuperAdmin','2020-06-20 13:27:07','SuperAdmin','2020-06-20 13:27:39'),(9,'Merchant Basic','Merchant',1,'Merchant Basic Limit','\0','SuperAdmin','2020-06-20 13:27:39','SuperAdmin','2020-06-20 13:39:29'),(10,'Merchant Premium','Merchant',1,'Merchant Premium Limit','\0','SuperAdmin','2020-06-20 13:27:52','SuperAdmin','2020-06-20 13:30:47'),(11,'Merchant Premium','Merchant',2,'Merchant Premium Limit','\0','SuperAdmin','2020-06-20 13:30:47','SuperAdmin','2020-06-20 13:31:00'),(12,'Merchant Premium','Merchant',2,'Merchant Premium Limit','\0','SuperAdmin','2020-06-20 13:31:00','SuperAdmin','2020-06-20 13:49:38'),(13,'Customer Limit','Customer',0,'Customer Limit','\0','SuperAdmin','2020-06-20 13:39:22','SuperAdmin','2020-06-23 12:53:08'),(14,'Merchant Basic','Merchant',1,'Merchant Basic Limit','\0','SuperAdmin','2020-06-20 13:39:29','SuperAdmin','2020-06-23 12:54:22'),(15,'Merchant Premium','Merchant',2,'Merchant Premium Limit','\0','SuperAdmin','2020-06-20 13:49:38','SuperAdmin','2020-06-23 12:51:25'),(16,'Merchant Premium','Merchant',2,'Merchant Premium Limit','','SuperAdmin','2020-06-23 12:51:25',NULL,NULL),(17,'Customer Limit','Customer',0,'Customer Limit','\0','SuperAdmin','2020-06-23 12:53:08','SuperAdmin','2020-06-23 12:58:41'),(18,'Merchant Basic','Merchant',1,'Merchant Basic Limit','','SuperAdmin','2020-06-23 12:54:22',NULL,NULL),(19,'Super Premium Merchant','Merchant',3,'Super Premium Merchant Limit','\0','SuperAdmin','2020-06-23 12:56:55','SuperAdmin','2020-06-23 12:57:56'),(20,'Super Premium Merchant','Merchant',3,'Super Premium Merchant Limit','','SuperAdmin','2020-06-23 12:57:57',NULL,NULL),(21,'Customer Limit','Customer',0,'Customer Limit','','SuperAdmin','2020-06-23 12:58:41',NULL,NULL);
/*!40000 ALTER TABLE `limitProfile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `limitProfileDetail`
--

DROP TABLE IF EXISTS `limitProfileDetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `limitProfileDetail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `limitProfileId` int(11) NOT NULL,
  `durationType` varchar(100) NOT NULL,
  `transactionType` varchar(100) NOT NULL,
  `noOfTransaction` int(11) NOT NULL,
  `transactionLimit` double DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `limitProfileId` (`limitProfileId`),
  CONSTRAINT `limitProfileDetail_ibfk_1` FOREIGN KEY (`limitProfileId`) REFERENCES `limitProfile` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `limitProfileDetail`
--

LOCK TABLES `limitProfileDetail` WRITE;
/*!40000 ALTER TABLE `limitProfileDetail` DISABLE KEYS */;
INSERT INTO `limitProfileDetail` VALUES (53,16,'Day','TOPUP',200,25000),(54,16,'Day','PAY',200,25000),(55,16,'Monthly','TOPUP',2000,75000),(56,16,'Monthly','PAY',2000,75000),(61,18,'Day','TOPUP',100,10000),(62,18,'Day','PAY',100,10000),(63,18,'Monthly','TOPUP',1000,50000),(64,18,'Monthly','PAY',1000,50000),(66,20,'Day','TOPUP',400,40000),(67,20,'Day','PAY',400,40000),(68,20,'Monthly','TOPUP',4000,100000),(69,20,'Monthly','PAY',4000,100000),(70,21,'Day','TOPUP',20,5000),(71,21,'Day','PAY',20,5000),(72,21,'Monthly','TOPUP',100,25000),(73,21,'Monthly','PAY',100,25000);
/*!40000 ALTER TABLE `limitProfileDetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loyaltyCashback`
--

DROP TABLE IF EXISTS `loyaltyCashback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `loyaltyCashback` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mId` int(11) NOT NULL,
  `offerCode` varchar(15) NOT NULL,
  `offerName` varchar(150) NOT NULL,
  `transCashbackType` varchar(15) NOT NULL,
  `cashbackType` varchar(10) NOT NULL,
  `minTransValue` double NOT NULL DEFAULT 0,
  `fixedCashbackAmt` double DEFAULT 0,
  `cashbackPercentage` varchar(3) NOT NULL,
  `maxCashbackAmt` double DEFAULT 0,
  `isActive` bit(1) DEFAULT b'1',
  `createdDate` timestamp NULL DEFAULT current_timestamp(),
  `createdBy` varchar(50) DEFAULT NULL,
  `updatedDate` timestamp NULL DEFAULT NULL,
  `updatedBy` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `offerName` (`offerName`),
  KEY `transCashbackType` (`transCashbackType`),
  KEY `cashbackType` (`cashbackType`),
  KEY `mId` (`mId`),
  CONSTRAINT `loyaltyCashback_ibfk_1` FOREIGN KEY (`mId`) REFERENCES `merchant` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loyaltyCashback`
--

LOCK TABLES `loyaltyCashback` WRITE;
/*!40000 ALTER TABLE `loyaltyCashback` DISABLE KEYS */;
/*!40000 ALTER TABLE `loyaltyCashback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
  KEY `menuName` (`menuName`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,'Dashboard','Dashboard','feather icon-home','dashboard',1,'','\0','\0','\0','\0',NULL,NULL,'2020-06-20 10:34:45','0000-00-00 00:00:00'),(2,'Admin','Admin','fas fa-users-cog',NULL,2,'','\0','\0','\0','\0',NULL,NULL,'2020-06-20 10:34:52','0000-00-00 00:00:00'),(3,'User','User Management','feather icon-users','',3,'','\0','\0','\0','\0',NULL,NULL,'2020-06-20 10:35:06','0000-00-00 00:00:00'),(4,'Merchant','Merchant Management','fas fa-store','',4,'','\0','\0','\0','\0',NULL,NULL,'2020-06-20 10:35:19','0000-00-00 00:00:00'),(5,'Customer','Customer Management','fas fa-users','',5,'','\0','\0','\0','\0',NULL,NULL,'2020-06-20 10:40:10','0000-00-00 00:00:00'),(6,'Operations','Customer Management','feather icon-arrow-up-right','',6,'','\0','\0','\0','\0',NULL,NULL,'2020-06-20 10:41:51','0000-00-00 00:00:00');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menuDetailMapping`
--

DROP TABLE IF EXISTS `menuDetailMapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menuDetailMapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `menuName` varchar(50) DEFAULT NULL,
  `dbTableName` varchar(50) DEFAULT NULL,
  `fieldName` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menuDetailMapping`
--

LOCK TABLES `menuDetailMapping` WRITE;
/*!40000 ALTER TABLE `menuDetailMapping` DISABLE KEYS */;
INSERT INTO `menuDetailMapping` VALUES (1,'Country','country','Country Code`countryCode,Country Alpha`countryCodeAlpha,Country Name`countryName'),(2,'Currency','currency','Currency Code`currencyCode,Currency Alpha`currencyCodeAlpha,Currency Name`currencyName,Exponent`exponent'),(3,'State','State','country Id`countryId,State Name`stateName');
/*!40000 ALTER TABLE `menuDetailMapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `merchant`
--

DROP TABLE IF EXISTS `merchant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `merchant` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `merchantId` varchar(15) NOT NULL,
  `categoryCode` varchar(10) NOT NULL,
  `merchantName` varchar(100) NOT NULL,
  `icon` longtext NOT NULL,
  `firstName` varchar(30) NOT NULL,
  `middleName` varchar(30) DEFAULT NULL,
  `lastName` varchar(30) NOT NULL,
  `emailId` varchar(50) NOT NULL,
  `mobileNumber` varchar(10) NOT NULL,
  `aboutMe` text NOT NULL,
  `PANNumber` varchar(10) NOT NULL,
  `GSTIN` varchar(15) NOT NULL,
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
  KEY `merchantId` (`merchantId`),
  KEY `merchantNumber` (`merchantNumber`),
  KEY `PANNumber` (`PANNumber`),
  KEY `GSTIN` (`GSTIN`),
  KEY `merchantName` (`merchantName`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `merchant`
--

LOCK TABLES `merchant` WRITE;
/*!40000 ALTER TABLE `merchant` DISABLE KEYS */;
/*!40000 ALTER TABLE `merchant` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `merchantAddress`
--

DROP TABLE IF EXISTS `merchantAddress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `merchantAddress` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `mId` int(11) NOT NULL,
  `stateId` int(11) NOT NULL,
  `cityId` int(11) NOT NULL,
  `areaCode` varchar(10) NOT NULL,
  `pincode` varchar(6) NOT NULL,
  `isActive` bit(1) DEFAULT b'1',
  PRIMARY KEY (`id`),
  KEY `mId` (`mId`),
  CONSTRAINT `merchantAddress_ibfk_1` FOREIGN KEY (`mId`) REFERENCES `merchant` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `merchantAddress`
--

LOCK TABLES `merchantAddress` WRITE;
/*!40000 ALTER TABLE `merchantAddress` DISABLE KEYS */;
/*!40000 ALTER TABLE `merchantAddress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `merchantMapping`
--

DROP TABLE IF EXISTS `merchantMapping`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `merchantMapping` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `walletId` varchar(30) NOT NULL,
  `cId` int(11) NOT NULL,
  `mId` int(11) NOT NULL,
  `walletBal` double DEFAULT 0,
  `isActive` bit(1) DEFAULT b'1',
  `createdDate` timestamp NULL DEFAULT current_timestamp(),
  `createdBy` varchar(50) DEFAULT NULL,
  `updatedDate` timestamp NULL DEFAULT NULL,
  `updatedBy` varchar(50) DEFAULT NULL,
  `giftCardBal` double DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `walletId` (`walletId`),
  KEY `cId` (`cId`),
  KEY `mId` (`mId`),
  CONSTRAINT `merchantMapping_ibfk_1` FOREIGN KEY (`cId`) REFERENCES `customer` (`id`),
  CONSTRAINT `merchantMapping_ibfk_2` FOREIGN KEY (`mId`) REFERENCES `merchant` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `merchantMapping`
--

LOCK TABLES `merchantMapping` WRITE;
/*!40000 ALTER TABLE `merchantMapping` DISABLE KEYS */;
/*!40000 ALTER TABLE `merchantMapping` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `merchantTransaction`
--

DROP TABLE IF EXISTS `merchantTransaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `merchantTransaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cId` int(11) NOT NULL,
  `mId` int(11) NOT NULL,
  `walletTransactionId` varchar(12) NOT NULL,
  `transactionId` varchar(19) NOT NULL,
  `transactionType` varchar(20) NOT NULL,
  `transactionValue` double DEFAULT 0,
  `indicator` varchar(1) NOT NULL,
  `payType` varchar(100) NOT NULL,
  `offerCode` varchar(15) DEFAULT NULL,
  `status` varchar(1) DEFAULT 'F',
  `createdDate` timestamp NULL DEFAULT current_timestamp(),
  `createdBy` varchar(50) DEFAULT NULL,
  `updatedDate` timestamp NULL DEFAULT NULL,
  `updatedBy` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `transactionId` (`transactionId`),
  KEY `cId` (`cId`),
  KEY `mId` (`mId`),
  CONSTRAINT `merchantTransaction_ibfk_1` FOREIGN KEY (`cId`) REFERENCES `customer` (`id`),
  CONSTRAINT `merchantTransaction_ibfk_2` FOREIGN KEY (`mId`) REFERENCES `merchant` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `merchantTransaction`
--

LOCK TABLES `merchantTransaction` WRITE;
/*!40000 ALTER TABLE `merchantTransaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `merchantTransaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `merchantUser`
--

DROP TABLE IF EXISTS `merchantUser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
  KEY `mId` (`mId`),
  KEY `userId` (`userId`),
  CONSTRAINT `merchantUser_ibfk_1` FOREIGN KEY (`mId`) REFERENCES `merchant` (`id`),
  CONSTRAINT `merchantUser_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `merchantUser`
--

LOCK TABLES `merchantUser` WRITE;
/*!40000 ALTER TABLE `merchantUser` DISABLE KEYS */;
/*!40000 ALTER TABLE `merchantUser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `merchantVersion`
--

DROP TABLE IF EXISTS `merchantVersion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `merchantVersion` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `versionName` varchar(100) NOT NULL,
  `description` varchar(500) NOT NULL,
  `isActive` bit(1) DEFAULT b'1',
  `isApproved` int(11) DEFAULT 1,
  `createdBy` varchar(11) DEFAULT NULL,
  `updatedBy` varchar(11) DEFAULT NULL,
  `insertTimeStamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `updateTimeStamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `merchantVersion`
--

LOCK TABLES `merchantVersion` WRITE;
/*!40000 ALTER TABLE `merchantVersion` DISABLE KEYS */;
INSERT INTO `merchantVersion` VALUES (1,'Basic','Basic Version Merchant','',1,NULL,NULL,'2020-06-20 11:27:37','0000-00-00 00:00:00'),(2,'Premium','Premium Version Merchant','',1,NULL,NULL,'2020-06-20 11:27:37','0000-00-00 00:00:00'),(3,'Super-Premium','Super-Premium Version Merchant','',1,NULL,NULL,'2020-06-20 11:27:37','0000-00-00 00:00:00');
/*!40000 ALTER TABLE `merchantVersion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passwordHistoryDetail`
--

DROP TABLE IF EXISTS `passwordHistoryDetail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
  KEY `loginId` (`loginId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passwordHistoryDetail`
--

LOCK TABLES `passwordHistoryDetail` WRITE;
/*!40000 ALTER TABLE `passwordHistoryDetail` DISABLE KEYS */;
INSERT INTO `passwordHistoryDetail` VALUES (1,'SuperAdmin','$2a$11$3X.7B5WWYdMLKS0FUE/aTOTt8jtlAyAzyz4RExpwRqYnoT9adv/ea','',NULL,NULL,'2020-02-12 12:37:26','0000-00-00 00:00:00');
/*!40000 ALTER TABLE `passwordHistoryDetail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `state`
--

DROP TABLE IF EXISTS `state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `state` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stateName` varchar(50) NOT NULL,
  `countryId` int(11) NOT NULL,
  `isActive` bit(1) DEFAULT b'1',
  `isApproved` int(11) DEFAULT 1,
  `createdBy` varchar(11) DEFAULT NULL,
  `updatedBy` varchar(11) DEFAULT NULL,
  `insertTimeStamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `updateTimeStamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `countryId` (`countryId`),
  CONSTRAINT `state_ibfk_1` FOREIGN KEY (`countryId`) REFERENCES `country` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `state`
--

LOCK TABLES `state` WRITE;
/*!40000 ALTER TABLE `state` DISABLE KEYS */;
/*!40000 ALTER TABLE `state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subMenu`
--

DROP TABLE IF EXISTS `subMenu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
  KEY `menuId` (`menuId`),
  KEY `subMenuName` (`subMenuName`),
  CONSTRAINT `subMenu_ibfk_1` FOREIGN KEY (`menuId`) REFERENCES `menu` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subMenu`
--

LOCK TABLES `subMenu` WRITE;
/*!40000 ALTER TABLE `subMenu` DISABLE KEYS */;
INSERT INTO `subMenu` VALUES (1,2,'System Parameter','System Parameter','feather icon-file-plus','sysParameter/view',1,'','','','','\0',NULL,NULL,'2020-06-20 10:34:58','0000-00-00 00:00:00'),(2,2,'Country','Country','feather icon-file-plus','country/view',2,'','','','','\0',NULL,NULL,'2020-06-20 10:34:59','0000-00-00 00:00:00'),(3,2,'State','State','feather icon-file-plus','state/view',3,'','','','','\0',NULL,NULL,'2020-06-20 10:34:59','0000-00-00 00:00:00'),(4,2,'City','City','feather icon-file-plus','city/view',4,'','','','','\0',NULL,NULL,'2020-06-20 10:34:59','0000-00-00 00:00:00'),(5,2,'Area','Area','feather icon-file-plus','area/view',5,'','','','','\0',NULL,NULL,'2020-06-20 10:34:59','0000-00-00 00:00:00'),(6,2,'Category','Category','feather icon-file-plus','category/view',6,'','','','','\0',NULL,NULL,'2020-06-20 10:34:59','0000-00-00 00:00:00'),(7,2,'Merchant Plan','Merchant Plan','feather icon-file-plus','merchantPlan/view',6,'','','','','\0',NULL,NULL,'2020-06-20 10:34:59','0000-00-00 00:00:00'),(8,2,'Limit Profile','Limit Profile','feather icon-file-plus','limitProfile/view',7,'','','','','\0',NULL,NULL,'2020-06-20 10:34:59','0000-00-00 00:00:00'),(9,3,'User Group','Add User Group','feather icon-file-plus','group/view',1,'','','','','\0',NULL,NULL,'2020-06-20 10:35:12','0000-00-00 00:00:00'),(10,3,'Group Privilege','Add Group Privilege','feather icon-file-plus','user/role/view',2,'','','','','\0',NULL,NULL,'2020-06-20 10:35:12','0000-00-00 00:00:00'),(11,3,'User','Add User','feather icon-file-plus','add/user/view',3,'','','','','\0',NULL,NULL,'2020-06-20 10:35:12','0000-00-00 00:00:00'),(12,4,'Registration','Registration','feather icon-file-plus','merchant/registration/view',1,'','','','','\0',NULL,NULL,'2020-06-20 10:40:01','0000-00-00 00:00:00'),(13,4,'Profile','Profile','feather icon-file-plus','merchant/profile/view',2,'','\0','\0','\0','\0',NULL,NULL,'2020-06-20 10:40:02','0000-00-00 00:00:00'),(14,4,'Employee','Employee','feather icon-file-plus','merchant/employee/view',3,'','','','','\0',NULL,NULL,'2020-06-20 10:40:02','0000-00-00 00:00:00'),(15,4,'Loyalty Cashback','Loyalty Cashback','feather icon-file-plus','merchant/loyaltyCashback/view',4,'','','','','\0',NULL,NULL,'2020-06-20 10:40:02','0000-00-00 00:00:00'),(16,4,'Add Customer','Customer','feather icon-file-plus','customer/view',5,'','','\0','\0','\0',NULL,NULL,'2020-06-20 10:40:02','0000-00-00 00:00:00'),(17,4,'Update Merchant Plan','Update Merchant Plan','feather icon-file-plus','update/merchantPlan/view',6,'','\0','\0','','\0',NULL,NULL,'2020-06-20 10:40:02','2020-06-22 15:56:57'),(18,4,'Transaction','Customer Transaction','feather icon-file-plus','customer/transaction/view',7,'','\0','\0','\0','\0',NULL,NULL,'2020-06-20 10:40:02','0000-00-00 00:00:00'),(19,4,'Merchant Customers','Merchant Customers','feather icon-file-plus','merchant/customer/view',8,'','\0','\0','\0','\0',NULL,NULL,'2020-06-20 10:40:02','0000-00-00 00:00:00'),(20,4,'Transaction History','Merchant Transaction History','feather icon-file-plus','merchant/transactionHistory/view',9,'','\0','\0','\0','\0',NULL,NULL,'2020-06-20 10:40:02','0000-00-00 00:00:00'),(21,5,'Search Store','Search Store','feather icon-file-plus','search/store/view',1,'','\0','\0','\0','\0',NULL,NULL,'2020-06-20 10:40:40','0000-00-00 00:00:00'),(22,5,'Merchant','Customer Merchants','feather icon-file-plus','customer/merchant/view',2,'','\0','\0','\0','\0',NULL,NULL,'2020-06-20 10:40:40','0000-00-00 00:00:00'),(23,5,'Transaction History','Customer Transactions History','feather icon-file-plus','customer/transactionHistory/view',3,'','\0','\0','\0','\0',NULL,NULL,'2020-06-20 10:40:40','0000-00-00 00:00:00'),(24,6,'Search Transaction','Search Transaction','feather icon-file-plus','search/transaction/view',1,'','\0','\0','\0','\0',NULL,NULL,'2020-06-20 10:41:57','0000-00-00 00:00:00'),(25,6,'Search User','Search User','feather icon-file-plus','searchUser/view',2,'','\0','\0','\0','\0',NULL,NULL,'2020-06-20 10:41:57','0000-00-00 00:00:00'),(26,2,'Currency','Add Currency','feather icon-file-plus','currency/view',2,'','','','','\0',NULL,NULL,'2020-06-23 10:02:27','0000-00-00 00:00:00');
/*!40000 ALTER TABLE `subMenu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sys_param`
--

DROP TABLE IF EXISTS `sys_param`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sys_param` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `paramName` varchar(30) DEFAULT NULL,
  `paramDisplayName` varchar(50) DEFAULT NULL,
  `paramValue` varchar(200) DEFAULT NULL,
  `isActive` bit(1) DEFAULT b'1',
  `createdBy` varchar(11) DEFAULT NULL,
  `updatedBy` varchar(11) DEFAULT NULL,
  `insertTimeStamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `updateTimeStamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sys_param`
--

LOCK TABLES `sys_param` WRITE;
/*!40000 ALTER TABLE `sys_param` DISABLE KEYS */;
INSERT INTO `sys_param` VALUES (1,'customerIdPrefix','CustomerId Prefix','DPC','',NULL,NULL,'2020-06-20 10:34:26','0000-00-00 00:00:00'),(2,'merchantIdPrefix','MerchantId Prefix','DPM','',NULL,NULL,'2020-06-20 10:34:26','0000-00-00 00:00:00'),(3,'customerIdLength','Customer Id Length','15','',NULL,NULL,'2020-06-20 10:34:26','0000-00-00 00:00:00'),(4,'merchantIdLength','Merchant Id Length','15','',NULL,NULL,'2020-06-20 10:34:26','0000-00-00 00:00:00'),(5,'merchantIdPrefix','MerchantId Prefix','DPM','',NULL,NULL,'2020-06-20 10:34:26','0000-00-00 00:00:00'),(6,'displayMerchantCount','Display Merchant Count','50','',NULL,NULL,'2020-06-20 10:34:26','0000-00-00 00:00:00'),(7,'walletTransLength','Wallet Transaction Length','12','',NULL,NULL,'2020-06-20 10:34:26','0000-00-00 00:00:00'),(8,'transactionPrefix','Transaction Prefix','DPW','',NULL,NULL,'2020-06-20 10:34:26','0000-00-00 00:00:00'),(9,'transactionIdLength','Transaction Id Length','19','',NULL,NULL,'2020-06-20 10:34:26','0000-00-00 00:00:00'),(10,'transCashbackType','Transaction Cashback Type','First,Recurring','',NULL,NULL,'2020-06-20 10:34:26','0000-00-00 00:00:00'),(11,'limitDurationType','Limit Duration Type','Day,Monthly','',NULL,NULL,'2020-06-20 10:34:26','0000-00-00 00:00:00'),(12,'limitType','Limt Type','Customer,Merchant','',NULL,NULL,'2020-06-20 10:34:26','0000-00-00 00:00:00'),(13,'transactionType','Transaction Type','TOPUP,PAY','',NULL,NULL,'2020-06-20 10:34:26','0000-00-00 00:00:00'),(14,'customerSearchParam','Customer Search Parameter','Login Id~loginId, Customer Id~customerId, First Name~firstName, Mobile Number~mobileNumber,Email Id~emailId','',NULL,NULL,'2020-06-20 10:34:26','0000-00-00 00:00:00'),(15,'merchantSearchParam','Merchant Search Parameter','Login Id~loginId, Merchant Id~merchantId, Merchant Name~merchantName, Mobile Number~mobileNumber,Email Id~emailId','',NULL,NULL,'2020-06-20 10:34:26','0000-00-00 00:00:00');
/*!40000 ALTER TABLE `sys_param` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `system`
--

DROP TABLE IF EXISTS `system`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `system` (
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
  `isApproved` int(11) DEFAULT 1,
  `createdBy` varchar(11) DEFAULT NULL,
  `updatedBy` varchar(11) DEFAULT NULL,
  `insertTimeStamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `updateTimeStamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `systemId` (`systemId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `system`
--

LOCK TABLES `system` WRITE;
/*!40000 ALTER TABLE `system` DISABLE KEYS */;
INSERT INTO `system` VALUES (1,'DazzlePay','DAZZLEPAY PAYMENT PROCESSING PVT LTD','Flat No. 1604, 16th Floor, Panaroma Towers','Prathmesh Complex, Veera Desai Road, Andheri West','Mumbai','Maharashtra','400053','India','Deepak','','BHUTRA','deepak@dazzlepay.co.in','9820981763','9820981763','9820981763','1','',1,NULL,NULL,'2020-02-12 11:24:41','0000-00-00 00:00:00');
/*!40000 ALTER TABLE `system` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
  `userType` int(11) DEFAULT 1 COMMENT '1 - Admin/Maker/checker, 2 - Customer, 3-Merchant,  4-SuperAdmin',
  `isApproved` int(11) DEFAULT 1,
  `status` varchar(1) DEFAULT NULL,
  `createdBy` varchar(11) DEFAULT NULL,
  `updatedBy` varchar(11) DEFAULT NULL,
  `insertTimeStamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `updateTimeStamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `groupId` (`groupId`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`groupId`) REFERENCES `usergroup` (`groupId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'DazzlePay','SuperAdmin','SuperAdmin','DazzlePay','DazzlePay','PAYMENT','PROCESSING','care@dazzlepay.co.in','$2a$11$3X.7B5WWYdMLKS0FUE/aTOTt8jtlAyAzyz4RExpwRqYnoT9adv/ea',NULL,'','$2a$11$XQeftHn7H.Fh8qqQVkO1UOUGoVYwIkx3EdbS.Qy138KZHnUHQKwb.','2019-10-25 01:10:31','2020-06-23 12:42:40','BCF864AC6FDC3209F9DFF9A46EF429CA',0,0,'','\0','',1,1,'1','superAdmin','superAdmin','2019-10-19 14:55:31','2020-06-23 12:42:40'),(2,'DazzlePay','admin','SYS_ADMIN','SYS_ADMIN','System','','Admin','system@dazzlepay.co.in','$2a$11$9X.8Md9DT0EdtsFnCHMiz.pKR1mGptu5w7TWkKlCkdtFZh5Lj7/m.',NULL,'',NULL,'2020-06-23 13:04:11','2020-06-23 13:08:36','D07909B201F2EC51780E165D476FF620',0,0,'','\0','',1,1,'1','SuperAdmin',NULL,'2020-06-23 13:04:11','2020-06-23 13:08:36');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userGroupType`
--

DROP TABLE IF EXISTS `userGroupType`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userGroupType` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `groupType` varchar(50) DEFAULT NULL,
  `displayName` varchar(50) NOT NULL,
  `isActive` bit(1) DEFAULT b'1',
  `insertTimeStamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `updateTimeStamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userGroupType`
--

LOCK TABLES `userGroupType` WRITE;
/*!40000 ALTER TABLE `userGroupType` DISABLE KEYS */;
INSERT INTO `userGroupType` VALUES (1,'maker','Maker','','2020-02-12 11:02:38','2020-02-12 11:02:38'),(2,'checker','Checker','','2020-02-12 11:02:38','2020-02-12 11:02:38'),(3,'admin','Admin','','2020-02-12 11:02:38','2020-02-12 11:02:38'),(4,'merchant','Merchant','','2020-02-12 11:02:38','2020-02-12 11:02:38'),(5,'customer','Customer','','2020-02-12 11:02:38','2020-02-12 11:02:38');
/*!40000 ALTER TABLE `userGroupType` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userNotification`
--

DROP TABLE IF EXISTS `userNotification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userNotification` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `creatorId` varchar(11) DEFAULT NULL,
  `checkerId` varchar(11) DEFAULT NULL,
  `status` int(11) DEFAULT 0,
  `recordId` varchar(10) DEFAULT '0',
  `tableName` varchar(50) DEFAULT '1',
  `menuName` varchar(50) DEFAULT NULL,
  `message` varchar(100) DEFAULT NULL,
  `comments` varchar(1000) DEFAULT NULL,
  `insertTimeStamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `updateTimeStamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `creatorId` (`creatorId`),
  KEY `makerId` (`checkerId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userNotification`
--

LOCK TABLES `userNotification` WRITE;
/*!40000 ALTER TABLE `userNotification` DISABLE KEYS */;
/*!40000 ALTER TABLE `userNotification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userRole`
--

DROP TABLE IF EXISTS `userRole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
  `isApproved` int(11) DEFAULT 1,
  `createdBy` varchar(11) DEFAULT NULL,
  `updatedBy` varchar(11) DEFAULT NULL,
  `insertTimeStamp` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `updateTimeStamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `groupId` (`groupId`),
  CONSTRAINT `userRole_ibfk_1` FOREIGN KEY (`groupId`) REFERENCES `usergroup` (`groupId`)
) ENGINE=InnoDB AUTO_INCREMENT=134 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userRole`
--

LOCK TABLES `userRole` WRITE;
/*!40000 ALTER TABLE `userRole` DISABLE KEYS */;
INSERT INTO `userRole` VALUES (31,'customer',1,0,'\0','\0','\0','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:11:41','2020-06-20 11:11:41'),(32,'customer',5,21,'\0','\0','\0','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:11:42','2020-06-20 11:11:42'),(33,'customer',5,22,'\0','\0','\0','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:11:42','2020-06-20 11:11:42'),(34,'customer',5,23,'\0','\0','\0','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:11:42','2020-06-20 11:11:42'),(35,'merchant',1,0,'\0','\0','\0','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:12:30','2020-06-20 11:12:30'),(36,'merchant',4,13,'\0','\0','\0','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:12:30','2020-06-20 11:12:30'),(37,'merchant',4,14,'','','','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:12:30','2020-06-20 11:12:30'),(38,'merchant',4,15,'','','','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:12:30','2020-06-20 11:12:30'),(39,'merchant',4,16,'','\0','\0','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:12:30','2020-06-20 11:12:30'),(40,'merchant',4,18,'\0','\0','\0','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:12:30','2020-06-20 11:12:30'),(41,'merchant',4,20,'\0','\0','\0','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:12:30','2020-06-20 11:12:30'),(63,'SuperAdmin',1,0,'\0','\0','\0','\0','',1,NULL,'SuperAdmin','2020-06-23 12:38:43','2020-06-23 12:38:43'),(64,'SuperAdmin',2,1,'','','','\0','',1,NULL,'SuperAdmin','2020-06-23 12:38:43','2020-06-23 12:38:43'),(65,'SuperAdmin',2,2,'','','','\0','',1,NULL,'SuperAdmin','2020-06-23 12:38:43','2020-06-23 12:38:43'),(66,'SuperAdmin',2,26,'','','','\0','',1,NULL,'SuperAdmin','2020-06-23 12:38:43','2020-06-23 12:38:43'),(67,'SuperAdmin',2,3,'','','','\0','',1,NULL,'SuperAdmin','2020-06-23 12:38:43','2020-06-23 12:38:43'),(68,'SuperAdmin',2,4,'','','','\0','',1,NULL,'SuperAdmin','2020-06-23 12:38:44','2020-06-23 12:38:44'),(69,'SuperAdmin',2,5,'','','','\0','',1,NULL,'SuperAdmin','2020-06-23 12:38:44','2020-06-23 12:38:44'),(70,'SuperAdmin',2,6,'','','','\0','',1,NULL,'SuperAdmin','2020-06-23 12:38:44','2020-06-23 12:38:44'),(71,'SuperAdmin',2,7,'','','','\0','',1,NULL,'SuperAdmin','2020-06-23 12:38:44','2020-06-23 12:38:44'),(72,'SuperAdmin',2,8,'','','','\0','',1,NULL,'SuperAdmin','2020-06-23 12:38:44','2020-06-23 12:38:44'),(73,'SuperAdmin',3,9,'','','','\0','',1,NULL,'SuperAdmin','2020-06-23 12:38:44','2020-06-23 12:38:44'),(74,'SuperAdmin',3,10,'','','','\0','',1,NULL,'SuperAdmin','2020-06-23 12:38:44','2020-06-23 12:38:44'),(75,'SuperAdmin',3,11,'','','','\0','',1,NULL,'SuperAdmin','2020-06-23 12:38:44','2020-06-23 12:38:44'),(76,'SuperAdmin',4,12,'','','','\0','',1,NULL,'SuperAdmin','2020-06-23 12:38:44','2020-06-23 12:38:44'),(77,'SuperAdmin',4,13,'\0','\0','\0','\0','',1,NULL,'SuperAdmin','2020-06-23 12:38:44','2020-06-23 12:38:44'),(78,'SuperAdmin',4,14,'','','','\0','',1,NULL,'SuperAdmin','2020-06-23 12:38:44','2020-06-23 12:38:44'),(79,'SuperAdmin',4,15,'','','','\0','',1,NULL,'SuperAdmin','2020-06-23 12:38:44','2020-06-23 12:38:44'),(80,'SuperAdmin',4,16,'','\0','\0','\0','',1,NULL,'SuperAdmin','2020-06-23 12:38:44','2020-06-23 12:38:44'),(81,'SuperAdmin',4,17,'\0','\0','','\0','',1,NULL,'SuperAdmin','2020-06-23 12:38:44','2020-06-23 12:38:44'),(82,'SuperAdmin',4,18,'\0','\0','\0','\0','',1,NULL,'SuperAdmin','2020-06-23 12:38:44','2020-06-23 12:38:44'),(83,'SuperAdmin',4,19,'\0','\0','\0','\0','',1,NULL,'SuperAdmin','2020-06-23 12:38:45','2020-06-23 12:38:45'),(84,'SuperAdmin',4,20,'\0','\0','\0','\0','',1,NULL,'SuperAdmin','2020-06-23 12:38:45','2020-06-23 12:38:45'),(85,'SuperAdmin',5,21,'\0','\0','\0','\0','',1,NULL,'SuperAdmin','2020-06-23 12:38:45','2020-06-23 12:38:45'),(86,'SuperAdmin',5,22,'\0','\0','\0','\0','',1,NULL,'SuperAdmin','2020-06-23 12:38:45','2020-06-23 12:38:45'),(87,'SuperAdmin',5,23,'\0','\0','\0','\0','',1,NULL,'SuperAdmin','2020-06-23 12:38:45','2020-06-23 12:38:45'),(88,'SuperAdmin',6,24,'\0','\0','\0','\0','',1,NULL,'SuperAdmin','2020-06-23 12:38:45','2020-06-23 12:38:45'),(89,'SuperAdmin',6,25,'\0','\0','\0','\0','',1,NULL,'SuperAdmin','2020-06-23 12:38:45','2020-06-23 12:38:45'),(112,'admin',1,0,'\0','\0','\0','\0','',1,'SuperAdmin','SuperAdmin','2020-06-23 12:40:42','2020-06-23 12:40:42'),(113,'admin',2,2,'','','','\0','',1,'SuperAdmin','SuperAdmin','2020-06-23 12:40:42','2020-06-23 12:40:42'),(114,'admin',2,26,'','','','\0','',1,'SuperAdmin','SuperAdmin','2020-06-23 12:40:42','2020-06-23 12:40:42'),(115,'admin',2,3,'','','','\0','',1,'SuperAdmin','SuperAdmin','2020-06-23 12:40:42','2020-06-23 12:40:42'),(116,'admin',2,4,'','','','\0','',1,'SuperAdmin','SuperAdmin','2020-06-23 12:40:42','2020-06-23 12:40:42'),(117,'admin',2,5,'','','','\0','',1,'SuperAdmin','SuperAdmin','2020-06-23 12:40:42','2020-06-23 12:40:42'),(118,'admin',2,6,'','','','\0','',1,'SuperAdmin','SuperAdmin','2020-06-23 12:40:42','2020-06-23 12:40:42'),(119,'admin',2,7,'','\0','','\0','',1,'SuperAdmin','SuperAdmin','2020-06-23 12:40:42','2020-06-23 12:40:42'),(120,'admin',2,8,'','\0','\0','\0','',1,'SuperAdmin','SuperAdmin','2020-06-23 12:40:42','2020-06-23 12:40:42'),(121,'admin',3,9,'','','','\0','',1,'SuperAdmin','SuperAdmin','2020-06-23 12:40:42','2020-06-23 12:40:42'),(122,'admin',3,10,'','','','\0','',1,'SuperAdmin','SuperAdmin','2020-06-23 12:40:42','2020-06-23 12:40:42'),(123,'admin',3,11,'','','','\0','',1,'SuperAdmin','SuperAdmin','2020-06-23 12:40:43','2020-06-23 12:40:43'),(124,'admin',4,12,'\0','\0','\0','\0','',1,'SuperAdmin','SuperAdmin','2020-06-23 12:40:43','2020-06-23 12:40:43'),(125,'admin',4,13,'\0','\0','\0','\0','',1,'SuperAdmin','SuperAdmin','2020-06-23 12:40:43','2020-06-23 12:40:43'),(126,'admin',4,17,'\0','\0','\0','\0','',1,'SuperAdmin','SuperAdmin','2020-06-23 12:40:43','2020-06-23 12:40:43'),(127,'admin',4,19,'\0','\0','\0','\0','',1,'SuperAdmin','SuperAdmin','2020-06-23 12:40:43','2020-06-23 12:40:43'),(128,'admin',4,20,'\0','\0','\0','\0','',1,'SuperAdmin','SuperAdmin','2020-06-23 12:40:43','2020-06-23 12:40:43'),(129,'admin',5,21,'\0','\0','\0','\0','',1,'SuperAdmin','SuperAdmin','2020-06-23 12:40:43','2020-06-23 12:40:43'),(130,'admin',5,22,'\0','\0','\0','\0','',1,'SuperAdmin','SuperAdmin','2020-06-23 12:40:43','2020-06-23 12:40:43'),(131,'admin',5,23,'\0','\0','\0','\0','',1,'SuperAdmin','SuperAdmin','2020-06-23 12:40:43','2020-06-23 12:40:43'),(132,'admin',6,24,'\0','\0','\0','\0','',1,'SuperAdmin','SuperAdmin','2020-06-23 12:40:43','2020-06-23 12:40:43'),(133,'admin',6,25,'\0','\0','\0','\0','',1,'SuperAdmin','SuperAdmin','2020-06-23 12:40:43','2020-06-23 12:40:43');
/*!40000 ALTER TABLE `userRole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_history`
--

DROP TABLE IF EXISTS `user_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_history` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `loginId` varchar(50) DEFAULT NULL,
  `insertTimeStamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `updateTimeStamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`),
  KEY `loginId` (`loginId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_history`
--

LOCK TABLES `user_history` WRITE;
/*!40000 ALTER TABLE `user_history` DISABLE KEYS */;
INSERT INTO `user_history` VALUES (1,'SuperAdmin','2020-02-12 12:47:46','2020-06-23 12:42:40'),(2,'SYS_ADMIN','2020-06-23 13:07:52','2020-06-23 13:08:36');
/*!40000 ALTER TABLE `user_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_history_detail`
--

DROP TABLE IF EXISTS `user_history_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
  KEY `loginId` (`loginId`),
  CONSTRAINT `user_history_detail_ibfk_1` FOREIGN KEY (`loginId`) REFERENCES `user_history` (`loginId`)
) ENGINE=InnoDB AUTO_INCREMENT=246 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_history_detail`
--

LOCK TABLES `user_history_detail` WRITE;
/*!40000 ALTER TABLE `user_history_detail` DISABLE KEYS */;
INSERT INTO `user_history_detail` VALUES (1,'SuperAdmin','/mpls/add/user/profile','D27651F56E84FAD5924B937DA2B8D141','web','192.168.1.61','Linux','Chrome','2020-02-12 12:47:46'),(2,'SuperAdmin','/mpls/add/user/profile','6F2C953D8234BF91910F250B0AFDAA54','web','192.168.1.61','Linux','Chrome','2020-02-12 12:56:44'),(3,'SuperAdmin','/mpls/add/user/profile','14D4EB80E09CF6DD7B360158685F8041','web','192.168.1.61','Linux','Chrome','2020-02-12 12:59:10'),(4,'SuperAdmin','/mpls/user/role/detail/SuperAdmin','14D4EB80E09CF6DD7B360158685F8041','web','192.168.1.61','Linux','Chrome','2020-02-12 12:59:32'),(5,'SuperAdmin','/mpls/country/view','14D4EB80E09CF6DD7B360158685F8041','web','192.168.1.61','Linux','Chrome','2020-02-12 12:59:41'),(6,'SuperAdmin','/mpls/user/logout.htm','14D4EB80E09CF6DD7B360158685F8041','web','192.168.1.61','Linux','Chrome','2020-02-12 13:00:26'),(7,'SuperAdmin','/mpls/add/user/profile','064752AB6CC74A040629D1D9286A3FD6','web','192.168.1.53','Windows 10','Chrome','2020-02-13 05:32:22'),(8,'SuperAdmin','/mpls/currency/view','064752AB6CC74A040629D1D9286A3FD6','web','192.168.1.53','Windows 10','Chrome','2020-02-13 05:32:32'),(9,'SuperAdmin','/mpls/country/view','064752AB6CC74A040629D1D9286A3FD6','web','192.168.1.53','Windows 10','Chrome','2020-02-13 05:32:49'),(10,'SuperAdmin','/mpls/country/update','064752AB6CC74A040629D1D9286A3FD6','web','192.168.1.53','Windows 10','Chrome','2020-02-13 05:33:12'),(11,'SuperAdmin','/mpls/sysParameter/view','064752AB6CC74A040629D1D9286A3FD6','web','192.168.1.53','Windows 10','Chrome','2020-02-13 05:33:19'),(12,'SuperAdmin','/mpls/add/user/profile','1BEBFC136AA4A7A62F352CF8B007F4CC','web','192.168.1.53','Windows 10','Chrome','2020-02-13 05:47:07'),(13,'SuperAdmin','/mpls/currency/view','1BEBFC136AA4A7A62F352CF8B007F4CC','web','192.168.1.53','Windows 10','Chrome','2020-02-13 05:47:09'),(14,'SuperAdmin','/mpls/add/user/profile','610EA6F96143D26DA4256C656F10A7D9','web','192.168.1.56','Windows 10','Chrome','2020-02-13 05:49:51'),(15,'SuperAdmin','/mpls/sysParameter/view','610EA6F96143D26DA4256C656F10A7D9','web','192.168.1.56','Windows 10','Chrome','2020-02-13 05:49:57'),(16,'SuperAdmin','/mpls/country/view','610EA6F96143D26DA4256C656F10A7D9','web','192.168.1.56','Windows 10','Chrome','2020-02-13 05:49:59'),(17,'SuperAdmin','/mpls/currency/view','610EA6F96143D26DA4256C656F10A7D9','web','192.168.1.56','Windows 10','Chrome','2020-02-13 05:50:04'),(18,'SuperAdmin','/mpls/add/user/profile','3F37620AFBC98D78570BB8933CCE2291','web','192.168.1.53','Windows 10','Chrome','2020-02-13 05:55:26'),(19,'SuperAdmin','/mpls/currency/view','3F37620AFBC98D78570BB8933CCE2291','web','192.168.1.53','Windows 10','Chrome','2020-02-13 05:55:27'),(20,'SuperAdmin','/mpls/country/view','3F37620AFBC98D78570BB8933CCE2291','web','192.168.1.53','Windows 10','Chrome','2020-02-13 05:55:31'),(21,'SuperAdmin','/mpls/add/user/profile','22C412F717A37898943DB81B0F99D149','web','192.168.1.53','Windows 10','Chrome','2020-02-13 06:06:41'),(22,'SuperAdmin','/mpls/currency/view','22C412F717A37898943DB81B0F99D149','web','192.168.1.53','Windows 10','Chrome','2020-02-13 06:06:43'),(23,'SuperAdmin','/mpls/country/view','22C412F717A37898943DB81B0F99D149','web','192.168.1.53','Windows 10','Chrome','2020-02-13 06:06:47'),(24,'SuperAdmin','/mpls/add/user/profile','A7856C16DAF170BE7BB9422710A729B5','web','192.168.1.53','Windows 10','Chrome','2020-02-13 06:18:21'),(25,'SuperAdmin','/mpls/sysParameter/view','A7856C16DAF170BE7BB9422710A729B5','web','192.168.1.53','Windows 10','Chrome','2020-02-13 06:19:59'),(26,'SuperAdmin','/mpls/user/logout.htm','A7856C16DAF170BE7BB9422710A729B5','web','192.168.1.53','Windows 10','Chrome','2020-02-13 06:22:09'),(27,'SuperAdmin','/mpls/add/user/profile','606076625E8FC70C5CB43D47F778236C','web','192.168.1.53','Windows 10','Chrome','2020-02-13 06:22:11'),(28,'SuperAdmin','/mpls/add/user/profile','02FD6F23F1544A534D8D7B8BAF7859AA','web','192.168.1.53','Windows 10','Chrome','2020-02-13 08:00:08'),(29,'SuperAdmin','/mpls/city/view','02FD6F23F1544A534D8D7B8BAF7859AA','web','192.168.1.53','Windows 10','Chrome','2020-02-13 08:00:13'),(30,'SuperAdmin','/mpls/add/user/profile','0C53BF65383D9B9232E577BFED2C17E4','web','192.168.1.53','Windows 10','Chrome','2020-02-13 08:02:36'),(31,'SuperAdmin','/mpls/city/view','0C53BF65383D9B9232E577BFED2C17E4','web','192.168.1.53','Windows 10','Chrome','2020-02-13 08:02:41'),(32,'SuperAdmin','/mpls/add/user/profile','F2AE8A3CDBC325D57C0DB314492E546B','web','192.168.1.53','Windows 10','Chrome','2020-02-13 08:07:15'),(33,'SuperAdmin','/mpls/city/view','F2AE8A3CDBC325D57C0DB314492E546B','web','192.168.1.53','Windows 10','Chrome','2020-02-13 08:07:17'),(34,'SuperAdmin','/mpls/add/user/view','F2AE8A3CDBC325D57C0DB314492E546B','web','192.168.1.53','Windows 10','Chrome','2020-02-13 08:12:44'),(35,'SuperAdmin','/mpls/group/view','F2AE8A3CDBC325D57C0DB314492E546B','web','192.168.1.53','Windows 10','Chrome','2020-02-13 08:13:08'),(36,'SuperAdmin','/mpls/add/user/profile','5BA3F3614110436B51B8807BD876442B','web','192.168.1.56','Windows 10','Chrome','2020-02-13 08:27:41'),(37,'SuperAdmin','/mpls/category/view','5BA3F3614110436B51B8807BD876442B','web','192.168.1.56','Windows 10','Chrome','2020-02-13 08:27:48'),(38,'SuperAdmin','/mpls/add/user/profile','510B21C32FA5583CFA7E69E481F00C3E','web','192.168.1.56','Windows 10','Chrome','2020-02-13 08:36:06'),(39,'SuperAdmin','/mpls/category/view','510B21C32FA5583CFA7E69E481F00C3E','web','192.168.1.56','Windows 10','Chrome','2020-02-13 08:36:09'),(40,'SuperAdmin','/mpls/category/add','510B21C32FA5583CFA7E69E481F00C3E','web','192.168.1.56','Windows 10','Chrome','2020-02-13 08:39:11'),(41,'SuperAdmin','/mpls/category/delete','510B21C32FA5583CFA7E69E481F00C3E','web','192.168.1.56','Windows 10','Chrome','2020-02-13 08:40:08'),(42,'SuperAdmin','/mpls/user/logout.htm','510B21C32FA5583CFA7E69E481F00C3E','web','192.168.1.56','Windows 10','Chrome','2020-02-13 08:43:15'),(43,'SuperAdmin','/mpls/add/user/profile','FCE33976F2962F71B4D1001A0F5D69A1','web','192.168.1.56','Windows 10','Chrome','2020-02-13 08:43:16'),(44,'SuperAdmin','/mpls/category/view','FCE33976F2962F71B4D1001A0F5D69A1','web','192.168.1.56','Windows 10','Chrome','2020-02-13 08:43:17'),(45,'SuperAdmin','/mpls/category/update','FCE33976F2962F71B4D1001A0F5D69A1','web','192.168.1.56','Windows 10','Chrome','2020-02-13 08:43:19'),(46,'SuperAdmin','/mpls/user/logout.htm','FCE33976F2962F71B4D1001A0F5D69A1','web','192.168.1.56','Windows 10','Chrome','2020-02-13 08:43:59'),(47,'SuperAdmin','/mpls/add/user/profile','8FA86CB4724339E82C9720D3F1F2DCDA','web','192.168.1.56','Windows 10','Chrome','2020-02-13 08:44:01'),(48,'SuperAdmin','/mpls/category/view','8FA86CB4724339E82C9720D3F1F2DCDA','web','192.168.1.56','Windows 10','Chrome','2020-02-13 08:44:03'),(49,'SuperAdmin','/mpls/category/update','8FA86CB4724339E82C9720D3F1F2DCDA','web','192.168.1.56','Windows 10','Chrome','2020-02-13 08:44:05'),(50,'SuperAdmin','/mpls/category/edit','8FA86CB4724339E82C9720D3F1F2DCDA','web','192.168.1.56','Windows 10','Chrome','2020-02-13 08:44:10'),(51,'SuperAdmin','/mpls/currency/view','8FA86CB4724339E82C9720D3F1F2DCDA','web','192.168.1.56','Windows 10','Chrome','2020-02-13 08:46:49'),(52,'SuperAdmin','/mpls/add/user/profile','2B71830764B2279DAAFF0EE568934E97','web','192.168.1.56','Windows 10','Chrome','2020-02-13 09:11:27'),(53,'SuperAdmin','/mpls/category/view','2B71830764B2279DAAFF0EE568934E97','web','192.168.1.56','Windows 10','Chrome','2020-02-13 09:11:29'),(54,'SuperAdmin','/mpls/add/user/profile','1ABC646F51AF00248DBB1F6E71747A16','web','192.168.1.53','Windows 10','Chrome','2020-02-13 10:01:02'),(55,'SuperAdmin','/mpls/city/view','1ABC646F51AF00248DBB1F6E71747A16','web','192.168.1.53','Windows 10','Chrome','2020-02-13 10:01:05'),(56,'SuperAdmin','/mpls/add/user/profile','E0F7BC334CEF4B8982BFA595CF7AC786','web','192.168.1.56','Windows 10','Chrome','2020-02-13 10:20:25'),(57,'SuperAdmin','/mpls/category/view','E0F7BC334CEF4B8982BFA595CF7AC786','web','192.168.1.56','Windows 10','Chrome','2020-02-13 10:20:26'),(58,'SuperAdmin','/mpls/category/update','E0F7BC334CEF4B8982BFA595CF7AC786','web','192.168.1.56','Windows 10','Chrome','2020-02-13 10:22:59'),(59,'SuperAdmin','/mpls/add/user/profile','2A7176191CC5B97E2A009127F3C1BFBC','web','192.168.1.53','Windows 10','Chrome','2020-02-13 10:30:23'),(60,'SuperAdmin','/mpls/city/view','2A7176191CC5B97E2A009127F3C1BFBC','web','192.168.1.53','Windows 10','Chrome','2020-02-13 10:30:27'),(61,'SuperAdmin','/mpls/add/user/profile','D0DECEEF9D78ADEFA939821522E56DE3','web','192.168.1.53','Windows 10','Chrome','2020-02-13 10:45:04'),(62,'SuperAdmin','/mpls/user/role/view','D0DECEEF9D78ADEFA939821522E56DE3','web','192.168.1.53','Windows 10','Chrome','2020-02-13 10:45:08'),(63,'SuperAdmin','/mpls/city/view','D0DECEEF9D78ADEFA939821522E56DE3','web','192.168.1.53','Windows 10','Chrome','2020-02-13 10:45:42'),(64,'SuperAdmin','/mpls/currency/view','D0DECEEF9D78ADEFA939821522E56DE3','web','192.168.1.53','Windows 10','Chrome','2020-02-13 10:45:48'),(65,'SuperAdmin','/mpls/','D0DECEEF9D78ADEFA939821522E56DE3','web','192.168.1.53','Windows 10','Chrome','2020-02-13 10:46:37'),(66,'SuperAdmin','/mpls/add/user/profile','F25B5F1555F536EE8E972DBF3A157E66','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:24:10'),(67,'SuperAdmin','/mpls/city/view','F25B5F1555F536EE8E972DBF3A157E66','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:24:13'),(68,'SuperAdmin','/mpls/city/update','F25B5F1555F536EE8E972DBF3A157E66','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:24:28'),(69,'SuperAdmin','/mpls/user/logout.htm','F25B5F1555F536EE8E972DBF3A157E66','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:29:26'),(70,'SuperAdmin','/mpls/add/user/profile','F9687089B858E06BC0BBFB8EBED92B08','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:29:27'),(71,'SuperAdmin','/mpls/user/logout.htm','F9687089B858E06BC0BBFB8EBED92B08','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:30:07'),(72,'SuperAdmin','/mpls/add/user/profile','6DE936F0AD3F6B2D8CF288AD03E17554','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:30:10'),(73,'SuperAdmin','/mpls/city/view','6DE936F0AD3F6B2D8CF288AD03E17554','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:30:12'),(74,'SuperAdmin','/mpls/city/update','6DE936F0AD3F6B2D8CF288AD03E17554','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:30:15'),(75,'SuperAdmin','/mpls/add/user/profile','D1A953F02AE7F470CEBEAADA1C011042','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:34:02'),(76,'SuperAdmin','/mpls/city/view','D1A953F02AE7F470CEBEAADA1C011042','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:34:04'),(77,'SuperAdmin','/mpls/city/update','D1A953F02AE7F470CEBEAADA1C011042','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:34:10'),(78,'SuperAdmin','/mpls/currency/view','D1A953F02AE7F470CEBEAADA1C011042','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:35:03'),(79,'SuperAdmin','/mpls/add/user/profile','64AB48B0B2EE6EC5357F2EA3346036EE','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:50:14'),(80,'SuperAdmin','/mpls/city/view','64AB48B0B2EE6EC5357F2EA3346036EE','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:50:16'),(81,'SuperAdmin','/mpls/city/update','64AB48B0B2EE6EC5357F2EA3346036EE','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:50:19'),(82,'SuperAdmin','/mpls/add/user/profile','CB76D85412F2FCE2E46C9B98CCD421B1','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:53:40'),(83,'SuperAdmin','/mpls/city/view','CB76D85412F2FCE2E46C9B98CCD421B1','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:53:47'),(84,'SuperAdmin','/mpls/city/update','CB76D85412F2FCE2E46C9B98CCD421B1','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:54:24'),(85,'SuperAdmin','/mpls/user/logout.htm','CB76D85412F2FCE2E46C9B98CCD421B1','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:56:02'),(86,'SuperAdmin','/mpls/add/user/profile','E5B51A837BDBDA2CB17B6A560D5C3E9D','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:56:04'),(87,'SuperAdmin','/mpls/city/view','E5B51A837BDBDA2CB17B6A560D5C3E9D','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:56:05'),(88,'SuperAdmin','/mpls/city/update','E5B51A837BDBDA2CB17B6A560D5C3E9D','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:56:08'),(89,'SuperAdmin','/mpls/user/logout.htm','E5B51A837BDBDA2CB17B6A560D5C3E9D','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:59:01'),(90,'SuperAdmin','/mpls/add/user/profile','C9E4920F4CF8C6B2ADD5C11BBEC510DC','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:59:02'),(91,'SuperAdmin','/mpls/country/view','C9E4920F4CF8C6B2ADD5C11BBEC510DC','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:59:04'),(92,'SuperAdmin','/mpls/country/update','C9E4920F4CF8C6B2ADD5C11BBEC510DC','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:59:08'),(93,'SuperAdmin','/mpls/city/view','C9E4920F4CF8C6B2ADD5C11BBEC510DC','web','192.168.1.53','Windows 10','Chrome','2020-02-13 12:01:13'),(94,'SuperAdmin','/mpls/city/update','C9E4920F4CF8C6B2ADD5C11BBEC510DC','web','192.168.1.53','Windows 10','Chrome','2020-02-13 12:01:16'),(95,'SuperAdmin','/mpls/user/logout.htm','C9E4920F4CF8C6B2ADD5C11BBEC510DC','web','192.168.1.53','Windows 10','Chrome','2020-02-13 12:01:36'),(96,'SuperAdmin','/mpls/add/user/profile','BDD1FFC0AB5AE96C70EAD28BD4BBB451','web','192.168.1.53','Windows 10','Chrome','2020-02-13 12:01:38'),(97,'SuperAdmin','/mpls/city/view','BDD1FFC0AB5AE96C70EAD28BD4BBB451','web','192.168.1.53','Windows 10','Chrome','2020-02-13 12:01:40'),(98,'SuperAdmin','/mpls/city/update','BDD1FFC0AB5AE96C70EAD28BD4BBB451','web','192.168.1.53','Windows 10','Chrome','2020-02-13 12:01:42'),(99,'SuperAdmin','/mpls/user/logout.htm','BDD1FFC0AB5AE96C70EAD28BD4BBB451','web','192.168.1.53','Windows 10','Chrome','2020-02-13 12:03:02'),(100,'SuperAdmin','/mpls/add/user/profile','F10391AF4D7941B0EEC1EEB06053D7C6','web','192.168.1.53','Windows 10','Chrome','2020-02-13 12:03:07'),(101,'SuperAdmin','/mpls/city/view','F10391AF4D7941B0EEC1EEB06053D7C6','web','192.168.1.53','Windows 10','Chrome','2020-02-13 12:03:08'),(102,'SuperAdmin','/mpls/city/update','F10391AF4D7941B0EEC1EEB06053D7C6','web','192.168.1.53','Windows 10','Chrome','2020-02-13 12:03:11'),(103,'SuperAdmin','/mpls/add/user/profile','4E60E337247715D426A07991136138DD','web','192.168.1.53','Windows 10','Chrome','2020-02-13 12:03:58'),(104,'SuperAdmin','/mpls/city/view','4E60E337247715D426A07991136138DD','web','192.168.1.53','Windows 10','Chrome','2020-02-13 12:04:00'),(105,'SuperAdmin','/mpls/city/update','4E60E337247715D426A07991136138DD','web','192.168.1.53','Windows 10','Chrome','2020-02-13 12:04:18'),(106,'SuperAdmin','/mpls/add/user/profile','33B391DD4A5BCC3EB52F5CC02FC881B0','web','192.168.1.53','Windows 10','Chrome','2020-02-13 12:18:55'),(107,'SuperAdmin','/mpls/city/view','33B391DD4A5BCC3EB52F5CC02FC881B0','web','192.168.1.53','Windows 10','Chrome','2020-02-13 12:19:00'),(108,'SuperAdmin','/mpls/city/update','33B391DD4A5BCC3EB52F5CC02FC881B0','web','192.168.1.53','Windows 10','Chrome','2020-02-13 12:19:15'),(109,'SuperAdmin','/mpls/add/user/profile','419BAC73D03170784A6DC9B04974BE79','web','192.168.2.5','Linux','Chrome','2020-02-26 11:37:07'),(110,'SuperAdmin','/mpls/category/view','419BAC73D03170784A6DC9B04974BE79','web','192.168.2.5','Linux','Chrome','2020-02-26 11:37:12'),(111,'SuperAdmin','/mpls/area/view','419BAC73D03170784A6DC9B04974BE79','web','192.168.2.5','Linux','Chrome','2020-02-26 11:37:13'),(112,'SuperAdmin','/mpls/city/view','419BAC73D03170784A6DC9B04974BE79','web','192.168.2.5','Linux','Chrome','2020-02-26 11:37:14'),(113,'SuperAdmin','/mpls/state/view','419BAC73D03170784A6DC9B04974BE79','web','192.168.2.5','Linux','Chrome','2020-02-26 11:37:14'),(114,'SuperAdmin','/mpls/currency/view','419BAC73D03170784A6DC9B04974BE79','web','192.168.2.5','Linux','Chrome','2020-02-26 11:37:15'),(115,'SuperAdmin','/mpls/country/view','419BAC73D03170784A6DC9B04974BE79','web','192.168.2.5','Linux','Chrome','2020-02-26 11:37:15'),(116,'SuperAdmin','/mpls/group/view','419BAC73D03170784A6DC9B04974BE79','web','192.168.2.5','Linux','Chrome','2020-02-26 11:37:27'),(117,'SuperAdmin','/mpls/user/role/view','419BAC73D03170784A6DC9B04974BE79','web','192.168.2.5','Linux','Chrome','2020-02-26 11:37:30'),(118,'SuperAdmin','/mpls/user/role/update','419BAC73D03170784A6DC9B04974BE79','web','192.168.2.5','Linux','Chrome','2020-02-26 11:37:35'),(119,'SuperAdmin','/mpls/rest/ajax/getGroupInfo/SuperAdmin','419BAC73D03170784A6DC9B04974BE79','web','192.168.2.5','Linux','Chrome','2020-02-26 11:37:35'),(120,'SuperAdmin','/mpls/user/role/edit','419BAC73D03170784A6DC9B04974BE79','web','192.168.2.5','Linux','Chrome','2020-02-26 11:37:58'),(121,'SuperAdmin','/mpls/rest/ajax/getGroupInfo/customer','419BAC73D03170784A6DC9B04974BE79','web','192.168.2.5','Linux','Chrome','2020-02-26 11:38:04'),(122,'SuperAdmin','/mpls/user/role/add','419BAC73D03170784A6DC9B04974BE79','web','192.168.2.5','Linux','Chrome','2020-02-26 11:38:10'),(123,'SuperAdmin','/mpls/rest/ajax/getGroupInfo/merchant','419BAC73D03170784A6DC9B04974BE79','web','192.168.2.5','Linux','Chrome','2020-02-26 11:38:24'),(124,'SuperAdmin','/mpls/rest/ajax/getGroupInfo/admin','419BAC73D03170784A6DC9B04974BE79','web','192.168.2.5','Linux','Chrome','2020-02-26 11:39:15'),(125,'SuperAdmin','/mpls/user/role/detail/admin','419BAC73D03170784A6DC9B04974BE79','web','192.168.2.5','Linux','Chrome','2020-02-26 11:40:06'),(126,'SuperAdmin','/mpls/merchant/dashboard/view','7DBBB55E2E53185DB274C9710554BA8E','web','192.168.2.6','Linux','Chrome 8','2020-06-20 10:57:15'),(127,'SuperAdmin','/mpls/','7DBBB55E2E53185DB274C9710554BA8E','web','192.168.2.6','Linux','Chrome 8','2020-06-20 10:57:18'),(128,'SuperAdmin','/mpls/merchant/dashboard/view','246F766B07BE2E5048EE46FC7A4DFC00','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:00:07'),(129,'SuperAdmin','/mpls/','246F766B07BE2E5048EE46FC7A4DFC00','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:00:10'),(130,'SuperAdmin','/mpls/dashboard','246F766B07BE2E5048EE46FC7A4DFC00','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:00:14'),(131,'SuperAdmin','/mpls/merchant/dashboard/view','2C1952C9379C90335891AC7CD6974C99','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:02:26'),(132,'SuperAdmin','/mpls/merchant/dashboard/view','A5C629C5B52C0CA3EB3CB23E2E8067F1','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:03:40'),(133,'SuperAdmin','/mpls/user/role/view','A5C629C5B52C0CA3EB3CB23E2E8067F1','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:03:54'),(134,'SuperAdmin','/mpls/user/role/update','A5C629C5B52C0CA3EB3CB23E2E8067F1','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:03:59'),(135,'SuperAdmin','/mpls/rest/ajax/getGroupInfo/SuperAdmin','A5C629C5B52C0CA3EB3CB23E2E8067F1','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:03:59'),(136,'SuperAdmin','/mpls/user/role/edit','A5C629C5B52C0CA3EB3CB23E2E8067F1','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:05:31'),(137,'SuperAdmin','/mpls/user/role/detail/SuperAdmin','A5C629C5B52C0CA3EB3CB23E2E8067F1','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:05:42'),(138,'SuperAdmin','/mpls/user/logout.htm','A5C629C5B52C0CA3EB3CB23E2E8067F1','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:05:47'),(139,'SuperAdmin','/mpls/merchant/dashboard/view','969D9D7CA5AAEB0F7BBBC9F0C0B8E70B','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:06:51'),(140,'SuperAdmin','/mpls/user/role/view','969D9D7CA5AAEB0F7BBBC9F0C0B8E70B','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:06:53'),(141,'SuperAdmin','/mpls/rest/ajax/getGroupInfo/customer','969D9D7CA5AAEB0F7BBBC9F0C0B8E70B','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:11:25'),(142,'SuperAdmin','/mpls/user/role/add','969D9D7CA5AAEB0F7BBBC9F0C0B8E70B','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:11:41'),(143,'SuperAdmin','/mpls/rest/ajax/getGroupInfo/merchant','969D9D7CA5AAEB0F7BBBC9F0C0B8E70B','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:11:44'),(144,'SuperAdmin','/mpls/rest/ajax/getGroupInfo/admin','969D9D7CA5AAEB0F7BBBC9F0C0B8E70B','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:12:51'),(145,'SuperAdmin','/mpls/user/role/update','969D9D7CA5AAEB0F7BBBC9F0C0B8E70B','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:14:57'),(146,'SuperAdmin','/mpls/user/logout.htm','969D9D7CA5AAEB0F7BBBC9F0C0B8E70B','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:15:01'),(147,'SuperAdmin','/mpls/merchant/dashboard/view','1F7EF7DA87D83C7C53437053CFD377AD','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:15:04'),(148,'SuperAdmin','/mpls/customer/transaction/view','1F7EF7DA87D83C7C53437053CFD377AD','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:15:22'),(149,'SuperAdmin','/mpls/merchant/customer/view','1F7EF7DA87D83C7C53437053CFD377AD','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:15:26'),(150,'SuperAdmin','/mpls/merchant/transactionHistory/view','1F7EF7DA87D83C7C53437053CFD377AD','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:15:31'),(151,'SuperAdmin','/mpls/sysParameter/view','1F7EF7DA87D83C7C53437053CFD377AD','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:19:16'),(152,'SuperAdmin','/mpls/country/view','1F7EF7DA87D83C7C53437053CFD377AD','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:19:19'),(153,'SuperAdmin','/mpls/state/view','1F7EF7DA87D83C7C53437053CFD377AD','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:19:20'),(154,'SuperAdmin','/mpls/area/view','1F7EF7DA87D83C7C53437053CFD377AD','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:19:21'),(155,'SuperAdmin','/mpls/category/view','1F7EF7DA87D83C7C53437053CFD377AD','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:19:22'),(156,'SuperAdmin','/mpls/merchantPlan/view','1F7EF7DA87D83C7C53437053CFD377AD','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:19:23'),(157,'SuperAdmin','/mpls/merchant/dashboard/view','701D441012C71BF1820A30876C1358EF','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:26:59'),(158,'SuperAdmin','/mpls/merchantPlan/view','701D441012C71BF1820A30876C1358EF','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:27:06'),(159,'SuperAdmin','/mpls/merchant/dashboard/view','16D42649F5F55192B2C503C11033581C','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:30:29'),(160,'SuperAdmin','/mpls/merchantPlan/view','16D42649F5F55192B2C503C11033581C','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:30:35'),(161,'SuperAdmin','/mpls/merchantPlan/update','16D42649F5F55192B2C503C11033581C','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:30:46'),(162,'SuperAdmin','/mpls/limitProfile/view','16D42649F5F55192B2C503C11033581C','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:32:51'),(163,'SuperAdmin','/mpls/rest/ajax/getDurationType','16D42649F5F55192B2C503C11033581C','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:33:10'),(164,'SuperAdmin','/mpls/rest/ajax/getTransactionType','16D42649F5F55192B2C503C11033581C','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:33:10'),(165,'SuperAdmin','/mpls/limitProfile/add','16D42649F5F55192B2C503C11033581C','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:34:40'),(166,'SuperAdmin','/mpls/limitProfile/detail/1','16D42649F5F55192B2C503C11033581C','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:41:58'),(167,'SuperAdmin','/mpls/limitProfile/detail/Merchant%20Basic%20Plan','16D42649F5F55192B2C503C11033581C','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:58:12'),(168,'SuperAdmin','/mpls/merchant/dashboard/view','A5A807B031E0C77315B2D59A5D4C6E5C','web','192.168.2.6','Linux','Chrome 8','2020-06-20 12:05:41'),(169,'SuperAdmin','/mpls/limitProfile/view','A5A807B031E0C77315B2D59A5D4C6E5C','web','192.168.2.6','Linux','Chrome 8','2020-06-20 12:05:48'),(170,'SuperAdmin','/mpls/limitProfile/add','A5A807B031E0C77315B2D59A5D4C6E5C','web','192.168.2.6','Linux','Chrome 8','2020-06-20 12:06:17'),(171,'SuperAdmin','/mpls/limitProfile/detail/Customer%20Limit','A5A807B031E0C77315B2D59A5D4C6E5C','web','192.168.2.6','Linux','Chrome 8','2020-06-20 12:06:29'),(172,'SuperAdmin','/mpls/limitProfile/update','A5A807B031E0C77315B2D59A5D4C6E5C','web','192.168.2.6','Linux','Chrome 8','2020-06-20 12:07:03'),(173,'SuperAdmin','/mpls/rest/ajax/getDurationType','A5A807B031E0C77315B2D59A5D4C6E5C','web','192.168.2.6','Linux','Chrome 8','2020-06-20 12:07:08'),(174,'SuperAdmin','/mpls/rest/ajax/getTransactionType','A5A807B031E0C77315B2D59A5D4C6E5C','web','192.168.2.6','Linux','Chrome 8','2020-06-20 12:07:08'),(175,'SuperAdmin','/mpls/limitProfile/edit','A5A807B031E0C77315B2D59A5D4C6E5C','web','192.168.2.6','Linux','Chrome 8','2020-06-20 12:07:39'),(176,'SuperAdmin','/mpls/merchant/dashboard/view','370DF49ABA2BE691CEBDBBB3DDFE7B5A','web','192.168.2.6','Linux','Chrome 8','2020-06-20 12:08:38'),(177,'SuperAdmin','/mpls/limitProfile/view','370DF49ABA2BE691CEBDBBB3DDFE7B5A','web','192.168.2.6','Linux','Chrome 8','2020-06-20 12:08:47'),(178,'SuperAdmin','/mpls/limitProfile/detail/Customer%20Limit','370DF49ABA2BE691CEBDBBB3DDFE7B5A','web','192.168.2.6','Linux','Chrome 8','2020-06-20 12:08:55'),(179,'SuperAdmin','/mpls/limitProfile/detail/2','370DF49ABA2BE691CEBDBBB3DDFE7B5A','web','192.168.2.6','Linux','Chrome 8','2020-06-20 12:09:42'),(180,'SuperAdmin','/mpls/rest/ajax/getDurationType','370DF49ABA2BE691CEBDBBB3DDFE7B5A','web','192.168.2.6','Linux','Chrome 8','2020-06-20 12:20:22'),(181,'SuperAdmin','/mpls/rest/ajax/getTransactionType','370DF49ABA2BE691CEBDBBB3DDFE7B5A','web','192.168.2.6','Linux','Chrome 8','2020-06-20 12:20:22'),(182,'SuperAdmin','/mpls/merchant/dashboard/view','957E3C169BD05AA6940C291E4D3E6AC8','web','192.168.2.6','Linux','Chrome 8','2020-06-20 12:59:58'),(183,'SuperAdmin','/mpls/limitProfile/view','957E3C169BD05AA6940C291E4D3E6AC8','web','192.168.2.6','Linux','Chrome 8','2020-06-20 13:00:01'),(184,'SuperAdmin','/mpls/rest/ajax/getDurationType','957E3C169BD05AA6940C291E4D3E6AC8','web','192.168.2.6','Linux','Chrome 8','2020-06-20 13:00:07'),(185,'SuperAdmin','/mpls/rest/ajax/getTransactionType','957E3C169BD05AA6940C291E4D3E6AC8','web','192.168.2.6','Linux','Chrome 8','2020-06-20 13:00:07'),(186,'SuperAdmin','/mpls/limitProfile/detail/2','957E3C169BD05AA6940C291E4D3E6AC8','web','192.168.2.6','Linux','Chrome 8','2020-06-20 13:13:49'),(187,'SuperAdmin','/mpls/limitProfile/update','957E3C169BD05AA6940C291E4D3E6AC8','web','192.168.2.6','Linux','Chrome 8','2020-06-20 13:13:52'),(188,'SuperAdmin','/mpls/limitProfile/edit','957E3C169BD05AA6940C291E4D3E6AC8','web','192.168.2.6','Linux','Chrome 8','2020-06-20 13:15:32'),(189,'SuperAdmin','/mpls/limitProfile/detail/3','957E3C169BD05AA6940C291E4D3E6AC8','web','192.168.2.6','Linux','Chrome 8','2020-06-20 13:15:42'),(190,'SuperAdmin','/mpls/limitProfile/add','957E3C169BD05AA6940C291E4D3E6AC8','web','192.168.2.6','Linux','Chrome 8','2020-06-20 13:18:09'),(191,'SuperAdmin','/mpls/limitProfile/detail/4','957E3C169BD05AA6940C291E4D3E6AC8','web','192.168.2.6','Linux','Chrome 8','2020-06-20 13:18:12'),(192,'SuperAdmin','/mpls/limitProfile/detail/7','957E3C169BD05AA6940C291E4D3E6AC8','web','192.168.2.6','Linux','Chrome 8','2020-06-20 13:25:47'),(193,'SuperAdmin','/mpls/merchant/dashboard/view','2C5308ADD325AE52BF2DB3AB7385D9B0','web','192.168.2.6','Linux','Chrome 8','2020-06-20 13:30:24'),(194,'SuperAdmin','/mpls/limitProfile/view','2C5308ADD325AE52BF2DB3AB7385D9B0','web','192.168.2.6','Linux','Chrome 8','2020-06-20 13:30:33'),(195,'SuperAdmin','/mpls/limitProfile/update','2C5308ADD325AE52BF2DB3AB7385D9B0','web','192.168.2.6','Linux','Chrome 8','2020-06-20 13:30:41'),(196,'SuperAdmin','/mpls/limitProfile/edit','2C5308ADD325AE52BF2DB3AB7385D9B0','web','192.168.2.6','Linux','Chrome 8','2020-06-20 13:30:47'),(197,'SuperAdmin','/mpls/merchant/dashboard/view','0DC5B3F97E41BFCE50DBC01354F04424','web','192.168.2.6','Linux','Chrome 8','2020-06-20 13:49:23'),(198,'SuperAdmin','/mpls/limitProfile/view','0DC5B3F97E41BFCE50DBC01354F04424','web','192.168.2.6','Linux','Chrome 8','2020-06-20 13:49:26'),(199,'SuperAdmin','/mpls/limitProfile/update','0DC5B3F97E41BFCE50DBC01354F04424','web','192.168.2.6','Linux','Chrome 8','2020-06-20 13:49:31'),(200,'SuperAdmin','/mpls/limitProfile/edit','0DC5B3F97E41BFCE50DBC01354F04424','web','192.168.2.6','Linux','Chrome 8','2020-06-20 13:49:37'),(201,'SuperAdmin','/mpls/limitProfile/detail/14','0DC5B3F97E41BFCE50DBC01354F04424','web','192.168.2.6','Linux','Chrome 8','2020-06-20 13:49:43'),(202,'SuperAdmin','/mpls/user/role/view','0DC5B3F97E41BFCE50DBC01354F04424','web','192.168.2.6','Linux','Chrome 8','2020-06-20 13:50:01'),(203,'SuperAdmin','/mpls/merchant/dashboard/view','7CCA938735465396894E0C5932B8781D','web','192.168.2.5','Linux','Chrome 8','2020-06-23 12:37:18'),(204,'SuperAdmin','/mpls/user/role/view','7CCA938735465396894E0C5932B8781D','web','192.168.2.5','Linux','Chrome 8','2020-06-23 12:37:29'),(205,'SuperAdmin','/mpls/user/role/update','7CCA938735465396894E0C5932B8781D','web','192.168.2.5','Linux','Chrome 8','2020-06-23 12:38:32'),(206,'SuperAdmin','/mpls/rest/ajax/getGroupInfo/SuperAdmin','7CCA938735465396894E0C5932B8781D','web','192.168.2.5','Linux','Chrome 8','2020-06-23 12:38:32'),(207,'SuperAdmin','/mpls/user/role/edit','7CCA938735465396894E0C5932B8781D','web','192.168.2.5','Linux','Chrome 8','2020-06-23 12:38:43'),(208,'SuperAdmin','/mpls/rest/ajax/getGroupInfo/admin','7CCA938735465396894E0C5932B8781D','web','192.168.2.5','Linux','Chrome 8','2020-06-23 12:38:53'),(209,'SuperAdmin','/mpls/rest/ajax/getGroupInfo/merchant','7CCA938735465396894E0C5932B8781D','web','192.168.2.5','Linux','Chrome 8','2020-06-23 12:40:47'),(210,'SuperAdmin','/mpls/rest/ajax/getGroupInfo/customer','7CCA938735465396894E0C5932B8781D','web','192.168.2.5','Linux','Chrome 8','2020-06-23 12:41:10'),(211,'SuperAdmin','/mpls/user/logout.htm','7CCA938735465396894E0C5932B8781D','web','192.168.2.5','Linux','Chrome 8','2020-06-23 12:42:33'),(212,'SuperAdmin','/mpls/merchant/dashboard/view','BCF864AC6FDC3209F9DFF9A46EF429CA','web','192.168.2.5','Linux','Chrome 8','2020-06-23 12:42:40'),(213,'SuperAdmin','/mpls/country/view','BCF864AC6FDC3209F9DFF9A46EF429CA','web','192.168.2.5','Linux','Chrome 8','2020-06-23 12:48:43'),(214,'SuperAdmin','/mpls/currency/view','BCF864AC6FDC3209F9DFF9A46EF429CA','web','192.168.2.5','Linux','Chrome 8','2020-06-23 12:48:55'),(215,'SuperAdmin','/mpls/merchantPlan/view','BCF864AC6FDC3209F9DFF9A46EF429CA','web','192.168.2.5','Linux','Chrome 8','2020-06-23 12:49:40'),(216,'SuperAdmin','/mpls/limitProfile/view','BCF864AC6FDC3209F9DFF9A46EF429CA','web','192.168.2.5','Linux','Chrome 8','2020-06-23 12:49:42'),(217,'SuperAdmin','/mpls/limitProfile/update','BCF864AC6FDC3209F9DFF9A46EF429CA','web','192.168.2.5','Linux','Chrome 8','2020-06-23 12:49:49'),(218,'SuperAdmin','/mpls/limitProfile/edit','BCF864AC6FDC3209F9DFF9A46EF429CA','web','192.168.2.5','Linux','Chrome 8','2020-06-23 12:51:25'),(219,'SuperAdmin','/mpls/limitProfile/detail/16','BCF864AC6FDC3209F9DFF9A46EF429CA','web','192.168.2.5','Linux','Chrome 8','2020-06-23 12:53:15'),(220,'SuperAdmin','/mpls/rest/ajax/getDurationType','BCF864AC6FDC3209F9DFF9A46EF429CA','web','192.168.2.5','Linux','Chrome 8','2020-06-23 12:55:09'),(221,'SuperAdmin','/mpls/rest/ajax/getTransactionType','BCF864AC6FDC3209F9DFF9A46EF429CA','web','192.168.2.5','Linux','Chrome 8','2020-06-23 12:55:09'),(222,'SuperAdmin','/mpls/limitProfile/add','BCF864AC6FDC3209F9DFF9A46EF429CA','web','192.168.2.5','Linux','Chrome 8','2020-06-23 12:56:12'),(223,'SuperAdmin','/mpls/limitProfile/detail/19','BCF864AC6FDC3209F9DFF9A46EF429CA','web','192.168.2.5','Linux','Chrome 8','2020-06-23 12:56:59'),(224,'SuperAdmin','/mpls/limitProfile/detail/20','BCF864AC6FDC3209F9DFF9A46EF429CA','web','192.168.2.5','Linux','Chrome 8','2020-06-23 12:58:05'),(225,'SuperAdmin','/mpls/limitProfile/detail/18','BCF864AC6FDC3209F9DFF9A46EF429CA','web','192.168.2.5','Linux','Chrome 8','2020-06-23 12:58:11'),(226,'SuperAdmin','/mpls/limitProfile/detail/17','BCF864AC6FDC3209F9DFF9A46EF429CA','web','192.168.2.5','Linux','Chrome 8','2020-06-23 12:58:20'),(227,'SuperAdmin','/mpls/limitProfile/detail/21','BCF864AC6FDC3209F9DFF9A46EF429CA','web','192.168.2.5','Linux','Chrome 8','2020-06-23 12:58:48'),(228,'SuperAdmin','/mpls/user/role/view','BCF864AC6FDC3209F9DFF9A46EF429CA','web','192.168.2.5','Linux','Chrome 8','2020-06-23 12:59:23'),(229,'SuperAdmin','/mpls/add/user/view','BCF864AC6FDC3209F9DFF9A46EF429CA','web','192.168.2.5','Linux','Chrome 8','2020-06-23 12:59:27'),(230,'SuperAdmin','/mpls/user/rest/checkUserGroupType','BCF864AC6FDC3209F9DFF9A46EF429CA','web','192.168.2.5','Linux','Chrome 8','2020-06-23 12:59:32'),(231,'SuperAdmin','/mpls/add/user/add','BCF864AC6FDC3209F9DFF9A46EF429CA','web','192.168.2.5','Linux','Chrome 8','2020-06-23 13:04:11'),(232,'SuperAdmin','/mpls/group/detail/admin','BCF864AC6FDC3209F9DFF9A46EF429CA','web','192.168.2.5','Linux','Chrome 8','2020-06-23 13:04:16'),(233,'SuperAdmin','/mpls/update/merchantPlan/view','BCF864AC6FDC3209F9DFF9A46EF429CA','web','192.168.2.5','Linux','Chrome 8','2020-06-23 13:04:28'),(234,'SuperAdmin','/mpls/searchUser/view','BCF864AC6FDC3209F9DFF9A46EF429CA','web','192.168.2.5','Linux','Chrome 8','2020-06-23 13:04:32'),(235,'SuperAdmin','/mpls/search/transaction/view','BCF864AC6FDC3209F9DFF9A46EF429CA','web','192.168.2.5','Linux','Chrome 8','2020-06-23 13:04:34'),(236,'SuperAdmin','/mpls/generatePassword','BCF864AC6FDC3209F9DFF9A46EF429CA','web','192.168.2.5','Linux','Chrome 8','2020-06-23 13:06:50'),(237,'SuperAdmin','/mpls/user/logout.htm','BCF864AC6FDC3209F9DFF9A46EF429CA','web','192.168.2.5','Linux','Chrome 8','2020-06-23 13:07:44'),(239,'SYS_ADMIN','/mpls/user/logout.htm','803366FB28D387E0FF3B6FF6C1CD5259','web','192.168.2.5','Linux','Chrome 8','2020-06-23 13:07:57'),(240,'SYS_ADMIN','/mpls/merchant/dashboard/view','273D1F2EF2EEC68AF1D04BBB27C2D8A3','web','192.168.2.5','Linux','Chrome 8','2020-06-23 13:07:59'),(241,'SYS_ADMIN','/mpls/user/logout.htm','273D1F2EF2EEC68AF1D04BBB27C2D8A3','web','192.168.2.5','Linux','Chrome 8','2020-06-23 13:08:08'),(242,'SYS_ADMIN','/mpls/merchant/dashboard/view','96DCBC4B289F12AA1E59409718E36DB6','web','192.168.2.5','Linux','Chrome 8','2020-06-23 13:08:18'),(243,'SYS_ADMIN','/mpls/user/logout.htm','96DCBC4B289F12AA1E59409718E36DB6','web','192.168.2.5','Linux','Chrome 8','2020-06-23 13:08:21'),(244,'SYS_ADMIN','/mpls/merchant/dashboard/view','D07909B201F2EC51780E165D476FF620','web','192.168.2.5','Linux','Chrome 8','2020-06-23 13:08:36'),(245,'SYS_ADMIN','/mpls/user/logout.htm','D07909B201F2EC51780E165D476FF620','web','192.168.2.5','Linux','Chrome 8','2020-06-23 13:08:44');
/*!40000 ALTER TABLE `user_history_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usergroup`
--

DROP TABLE IF EXISTS `usergroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usergroup`
--

LOCK TABLES `usergroup` WRITE;
/*!40000 ALTER TABLE `usergroup` DISABLE KEYS */;
INSERT INTO `usergroup` VALUES (1,'DazzlePay','SuperAdmin','Super Admin','admin',8,10,'Y','Y','Y',30,5,3,3,'1','',1,0,'SuperAdmin',NULL,'2020-02-12 12:21:49','2020-03-11 04:49:10'),(2,'DazzlePay','customer','Customer','customer',8,10,'Y','Y','Y',30,5,3,3,'1','',1,0,'SuperAdmin',NULL,'2020-02-18 06:54:05','2020-03-11 04:49:10'),(3,'DazzlePay','merchant','Merchant','merchant',8,10,'Y','Y','Y',30,5,3,3,'1','',1,0,'SuperAdmin',NULL,'2020-02-18 06:54:05','2020-03-11 04:49:10'),(4,'DazzlePay','admin','Admin','admin',8,10,'Y','Y','Y',30,5,3,3,'1','',1,1,'SuperAdmin',NULL,'2020-02-24 08:09:31','2020-06-20 11:14:51');
/*!40000 ALTER TABLE `usergroup` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `verificationLog`
--

DROP TABLE IF EXISTS `verificationLog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
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
  KEY `emailId` (`emailId`),
  KEY `mobileNumber` (`mobileNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verificationLog`
--

LOCK TABLES `verificationLog` WRITE;
CREATE TABLE `loyaltyNumberFile` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
	`fileName` varchar(50) DEFAULT NULL,
   `status` varchar(1) DEFAULT NULL,
   `insertedBy` varchar(50) DEFAULT NULL,
   `reason` varchar(200)  DEFAULT NULL,
    `insertTimeStamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `updateTimeStamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`)
  ) ENGINE=InnoDB  DEFAULT CHARSET=latin1;


CREATE TABLE `loyaltynumbers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fileid` int(11) NOT Null,
  `loyaltynumber`varchar(50) DEFAULT NULL,
  `status` varchar(1) DEFAULT NULL,
  `isActive` bit(1) DEFAULT b'1',
    `insertTimeStamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `updateTimeStamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
   CONSTRAINT `fileId_ibfk_1` FOREIGN KEY (`fileid`) REFERENCES `loyaltyNumberFile` (`id`)
 
  ) ENGINE=InnoDB  DEFAULT CHARSET=latin1;
  
 CREATE TABLE `LoyaltyNumberRequestLog` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `mId` int(11) NOT NULL DEFAULT 0,
  `message` longtext NOT NULL,
  `quantityOfCards` int NOT NULL DEFAULT 0,
  `allocated` int NOT NULL DEFAULT 0,
  `shippingAddress`longtext NOT NULL,
  `reason` longtext DEFAULT NULL,
  `status` int(11) NOT NULL DEFAULT 0,
  `notificationArgs` longtext DEFAULT NULL,
  `isActive` bit(1) NOT NULL DEFAULT b'1',
  `insertTimeStamp` timestamp NOT NULL DEFAULT current_timestamp(),
  `updateTimeStamp` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `mId` (`mId`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1;
/*!40000 ALTER TABLE `verificationLog` DISABLE KEYS */;
/*!40000 ALTER TABLE `verificationLog` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-06-24 11:59:13
