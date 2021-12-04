-- MariaDB dump 10.17  Distrib 10.4.13-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: mpls_init
-- ------------------------------------------------------
-- Server version	10.4.13-MariaDB-1:10.4.13+maria~bionic-log

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `area`
--

LOCK TABLES `area` WRITE;
/*!40000 ALTER TABLE `area` DISABLE KEYS */;
INSERT INTO `area` VALUES (1,'JOTHWARA','Jothwara','302012','','',1,1,103,'',1,'SuperAdmin',NULL,'2020-06-21 15:04:19','0000-00-00 00:00:00');
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'5047','Medical, Dental, Ophthalmic, and Hospital Equipment and Supplies','Medical, Dental, Ophthalmic, and Hospital Equipment and Supplies','',1,'SuperAdmin',NULL,'2020-06-21 15:04:26','0000-00-00 00:00:00');
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `city`
--

LOCK TABLES `city` WRITE;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` VALUES (1,'JAIPUR',1,103,'',1,NULL,NULL,'2020-06-21 15:04:08','0000-00-00 00:00:00');
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
) ENGINE=InnoDB AUTO_INCREMENT=246 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `country`
--

LOCK TABLES `country` WRITE;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` VALUES (1,4,'AFG','AF','Afghanistan','',1,NULL,NULL,'2020-02-12 14:30:10','0000-00-00 00:00:00'),(2,8,'ALB','AL','Albania','',1,NULL,NULL,'2020-02-12 14:30:10','0000-00-00 00:00:00'),(3,12,'DZA','DZ','Algeria','',1,NULL,NULL,'2020-02-12 14:30:10','0000-00-00 00:00:00'),(4,16,'ASM','AS','American Samoa','',1,NULL,NULL,'2020-02-12 14:30:10','0000-00-00 00:00:00'),(5,20,'AND','AD','Andorra','',1,NULL,NULL,'2020-02-12 14:30:10','0000-00-00 00:00:00'),(6,24,'AGO','AO','Angola','',1,NULL,NULL,'2020-02-12 14:30:10','0000-00-00 00:00:00'),(7,660,'AIA','AI','Anguilla','',1,NULL,NULL,'2020-02-12 14:30:11','0000-00-00 00:00:00'),(8,10,'ATA','AQ','Antarctica','',1,NULL,NULL,'2020-02-12 14:30:11','0000-00-00 00:00:00'),(9,28,'ATG','AG','Antigua and Barbuda','',1,NULL,NULL,'2020-02-12 14:30:11','0000-00-00 00:00:00'),(10,32,'ARG','AR','Argentina','',1,NULL,NULL,'2020-02-12 14:30:11','0000-00-00 00:00:00'),(11,51,'ARM','AM','Armenia','',1,NULL,NULL,'2020-02-12 14:30:11','0000-00-00 00:00:00'),(12,533,'ABW','AW','Aruba','',1,NULL,NULL,'2020-02-12 14:30:11','0000-00-00 00:00:00'),(13,36,'AUS','AU','Australia','',1,NULL,NULL,'2020-02-12 14:30:11','0000-00-00 00:00:00'),(14,40,'AUT','AT','Austria','',1,NULL,NULL,'2020-02-12 14:30:11','0000-00-00 00:00:00'),(15,31,'AZE','AZ','Azerbaijan','',1,NULL,NULL,'2020-02-12 14:30:11','0000-00-00 00:00:00'),(16,44,'BHS','BS','Bahamas','',1,NULL,NULL,'2020-02-12 14:30:12','0000-00-00 00:00:00'),(17,48,'BHR','BH','Bahrain','',1,NULL,NULL,'2020-02-12 14:30:12','0000-00-00 00:00:00'),(18,50,'BGD','BD','Bangladesh','',1,NULL,NULL,'2020-02-12 14:30:12','0000-00-00 00:00:00'),(19,52,'BRB','BB','Barbados','',1,NULL,NULL,'2020-02-12 14:30:12','0000-00-00 00:00:00'),(20,112,'BLR','BY','Belarus','',1,NULL,NULL,'2020-02-12 14:30:12','0000-00-00 00:00:00'),(21,56,'BEL','BE','Belgium','',1,NULL,NULL,'2020-02-12 14:30:12','0000-00-00 00:00:00'),(22,84,'BLZ','BZ','Belize','',1,NULL,NULL,'2020-02-12 14:30:12','0000-00-00 00:00:00'),(23,204,'BEN','BJ','Benin','',1,NULL,NULL,'2020-02-12 14:30:13','0000-00-00 00:00:00'),(24,60,'BMU','BM','Bermuda','',1,NULL,NULL,'2020-02-12 14:30:13','0000-00-00 00:00:00'),(25,64,'BTN','BT','Bhutan','',1,NULL,NULL,'2020-02-12 14:30:13','0000-00-00 00:00:00'),(26,68,'BOL','BO','Bolivia','',1,NULL,NULL,'2020-02-12 14:30:13','0000-00-00 00:00:00'),(27,535,'BES','BQ','Bonaire, Sint Eustatius, and Saba','',1,NULL,NULL,'2020-02-12 14:30:13','0000-00-00 00:00:00'),(28,70,'BIH','BA','Bosnia and Herzegovina','',1,NULL,NULL,'2020-02-12 14:30:13','0000-00-00 00:00:00'),(29,72,'BWA','BW','Botswana','',1,NULL,NULL,'2020-02-12 14:30:13','0000-00-00 00:00:00'),(30,74,'BVT','BV','Bouvet Island','',1,NULL,NULL,'2020-02-12 14:30:13','0000-00-00 00:00:00'),(31,76,'BRA','BR','Brazil','',1,NULL,NULL,'2020-02-12 14:30:13','0000-00-00 00:00:00'),(32,86,'IOT','IO','British Indian Ocean Territory','',1,NULL,NULL,'2020-02-12 14:30:13','0000-00-00 00:00:00'),(33,92,'VGB','VG','British Virgin Islands','',1,NULL,NULL,'2020-02-12 14:30:14','0000-00-00 00:00:00'),(34,96,'BRN','BN','Brunei Darussalam','',1,NULL,NULL,'2020-02-12 14:30:14','0000-00-00 00:00:00'),(35,100,'BGR','BG','Bulgaria','',1,NULL,NULL,'2020-02-12 14:30:14','0000-00-00 00:00:00'),(36,854,'BFA','BF','Burkina Faso','',1,NULL,NULL,'2020-02-12 14:30:14','0000-00-00 00:00:00'),(37,108,'BDI','BI','Burundi','',1,NULL,NULL,'2020-02-12 14:30:14','0000-00-00 00:00:00'),(38,116,'KHM','KH','Cambodia','',1,NULL,NULL,'2020-02-12 14:30:14','0000-00-00 00:00:00'),(39,120,'CMR','CM','Cameroon, United Republic of','',1,NULL,NULL,'2020-02-12 14:30:14','0000-00-00 00:00:00'),(40,124,'CAN','CA','Canada','',1,NULL,NULL,'2020-02-12 14:30:14','0000-00-00 00:00:00'),(41,132,'CPV','CV','Cape Verde Island','',1,NULL,NULL,'2020-02-12 14:30:14','0000-00-00 00:00:00'),(42,136,'CYM','KY','Cayman Islands','',1,NULL,NULL,'2020-02-12 14:30:15','0000-00-00 00:00:00'),(43,140,'CAF','CF','Central African Republic','',1,NULL,NULL,'2020-02-12 14:30:15','0000-00-00 00:00:00'),(44,148,'TCD','TD','Chad','',1,NULL,NULL,'2020-02-12 14:30:15','0000-00-00 00:00:00'),(45,152,'CHL','CL','Chile','',1,NULL,NULL,'2020-02-12 14:30:15','0000-00-00 00:00:00'),(46,156,'CHN','CN','China','',1,NULL,NULL,'2020-02-12 14:30:15','0000-00-00 00:00:00'),(47,162,'CXR','CX','Christmas Island','',1,NULL,NULL,'2020-02-12 14:30:15','0000-00-00 00:00:00'),(48,166,'CCK','CC','Cocos (Keeling) Islands','',1,NULL,NULL,'2020-02-12 14:30:15','0000-00-00 00:00:00'),(49,170,'COL','CO','Colombia','',1,NULL,NULL,'2020-02-12 14:30:15','0000-00-00 00:00:00'),(50,174,'COM','KM','Comoros','',1,NULL,NULL,'2020-02-12 14:30:16','0000-00-00 00:00:00'),(51,178,'COG','CG','Congo','',1,NULL,NULL,'2020-02-12 14:30:16','0000-00-00 00:00:00'),(52,184,'COK','CK','Cook Islands','',1,NULL,NULL,'2020-02-12 14:30:16','0000-00-00 00:00:00'),(53,188,'CRI','CR','Costa Rica','',1,NULL,NULL,'2020-02-12 14:30:16','0000-00-00 00:00:00'),(54,384,'CIV','CI','Côte d\'Ivoire (Ivory Coast)','',1,NULL,NULL,'2020-02-12 14:30:16','0000-00-00 00:00:00'),(55,191,'HRV','HR','Croatia','',1,NULL,NULL,'2020-02-12 14:30:16','0000-00-00 00:00:00'),(56,192,'CUB','CU','Cuba','',1,NULL,NULL,'2020-02-12 14:30:16','0000-00-00 00:00:00'),(57,531,'CUW','CW','Curacao','',1,NULL,NULL,'2020-02-12 14:30:16','0000-00-00 00:00:00'),(58,196,'CYP','CY','Cyprus','',1,NULL,NULL,'2020-02-12 14:30:16','0000-00-00 00:00:00'),(59,203,'CZE','CZ','Czech Republic','',1,NULL,NULL,'2020-02-12 14:30:17','0000-00-00 00:00:00'),(60,180,'COD','CD','Democratic Republic of the Congo','',1,NULL,NULL,'2020-02-12 14:30:17','0000-00-00 00:00:00'),(61,208,'DNK','DK','Denmark','',1,NULL,NULL,'2020-02-12 14:30:17','0000-00-00 00:00:00'),(62,262,'DJI','DJ','Djibouti','',1,NULL,NULL,'2020-02-12 14:30:17','0000-00-00 00:00:00'),(63,212,'DMA','DM','Dominica','',1,NULL,NULL,'2020-02-12 14:30:17','0000-00-00 00:00:00'),(64,214,'DOM','DO','Dominican Republic','',1,NULL,NULL,'2020-02-12 14:30:17','0000-00-00 00:00:00'),(65,218,'ECU','EC','Ecuador','',1,NULL,NULL,'2020-02-12 14:30:17','0000-00-00 00:00:00'),(66,818,'EGY','EG','Egypt','',1,NULL,NULL,'2020-02-12 14:30:17','0000-00-00 00:00:00'),(67,222,'SLV','SV','El Salvador','',1,NULL,NULL,'2020-02-12 14:30:17','0000-00-00 00:00:00'),(68,226,'GNQ','GQ','Equatorial Guinea','',1,NULL,NULL,'2020-02-12 14:30:18','0000-00-00 00:00:00'),(69,232,'ERI','ER','Eritrea','',1,NULL,NULL,'2020-02-12 14:30:18','0000-00-00 00:00:00'),(70,233,'EST','EE','Estonia','',1,NULL,NULL,'2020-02-12 14:30:18','0000-00-00 00:00:00'),(71,231,'ETH','ET','Ethiopia','',1,NULL,NULL,'2020-02-12 14:30:18','0000-00-00 00:00:00'),(72,238,'FLK','FK','Falkland Islands (Malvinas)','',1,NULL,NULL,'2020-02-12 14:30:18','0000-00-00 00:00:00'),(73,208,'FRO','FO','Faroe Islands','',1,NULL,NULL,'2020-02-12 14:30:18','0000-00-00 00:00:00'),(74,242,'FJI','FJ','Fiji','',1,NULL,NULL,'2020-02-12 14:30:18','0000-00-00 00:00:00'),(75,246,'FIN','FI','Finland','',1,NULL,NULL,'2020-02-12 14:30:18','0000-00-00 00:00:00'),(76,250,'FRA','FR','France','',1,NULL,NULL,'2020-02-12 14:30:18','0000-00-00 00:00:00'),(77,249,'FXX','FX','France,Metropolitan','',1,NULL,NULL,'2020-02-12 14:30:19','0000-00-00 00:00:00'),(78,254,'GUF','GF','French Guiana','',1,NULL,NULL,'2020-02-12 14:30:19','0000-00-00 00:00:00'),(79,258,'PYF','PF','French Polynesia','',1,NULL,NULL,'2020-02-12 14:30:19','0000-00-00 00:00:00'),(80,260,'ATF','TF','French Southern Territories','',1,NULL,NULL,'2020-02-12 14:30:19','0000-00-00 00:00:00'),(81,266,'GAB','GA','Gabon','',1,NULL,NULL,'2020-02-12 14:30:19','0000-00-00 00:00:00'),(82,270,'GMB','GM','Gambia','',1,NULL,NULL,'2020-02-12 14:30:19','0000-00-00 00:00:00'),(83,268,'GEO','GE','Georgia','',1,NULL,NULL,'2020-02-12 14:30:19','0000-00-00 00:00:00'),(84,276,'DEU','DE','Germany','',1,NULL,NULL,'2020-02-12 14:30:19','0000-00-00 00:00:00'),(85,288,'GHA','GH','Ghana','',1,NULL,NULL,'2020-02-12 14:30:20','0000-00-00 00:00:00'),(86,292,'GIB','GI','Gibralter','',1,NULL,NULL,'2020-02-12 14:30:20','0000-00-00 00:00:00'),(87,300,'GRC','GR','Greece','',1,NULL,NULL,'2020-02-12 14:30:20','0000-00-00 00:00:00'),(88,304,'GRL','GL','Greenland','',1,NULL,NULL,'2020-02-12 14:30:20','0000-00-00 00:00:00'),(89,308,'GRD','GD','Grenada','',1,NULL,NULL,'2020-02-12 14:30:20','0000-00-00 00:00:00'),(90,312,'GLP','GP','Guadeloupe','',1,NULL,NULL,'2020-02-12 14:30:20','0000-00-00 00:00:00'),(91,316,'GUM','GU','Guam','',1,NULL,NULL,'2020-02-12 14:30:20','0000-00-00 00:00:00'),(92,320,'GTM','GT','Guatemala','',1,NULL,NULL,'2020-02-12 14:30:20','0000-00-00 00:00:00'),(93,324,'GIN','GN','Guinea','',1,NULL,NULL,'2020-02-12 14:30:21','0000-00-00 00:00:00'),(94,624,'GNB','GW','Guinea-Bissau','',1,NULL,NULL,'2020-02-12 14:30:21','0000-00-00 00:00:00'),(95,328,'GUY','GY','Guyana','',1,NULL,NULL,'2020-02-12 14:30:21','0000-00-00 00:00:00'),(96,332,'HTI','HT','Haiti','',1,NULL,NULL,'2020-02-12 14:30:21','0000-00-00 00:00:00'),(97,334,'HMD','HM','Heard and McDonald Islands','',1,NULL,NULL,'2020-02-12 14:30:21','0000-00-00 00:00:00'),(98,336,'VAT','VA','Holy See (Vatican City State)','',1,NULL,NULL,'2020-02-12 14:30:21','0000-00-00 00:00:00'),(99,340,'HND','HN','Honduras','',1,NULL,NULL,'2020-02-12 14:30:21','0000-00-00 00:00:00'),(100,344,'HKG','HK','Hong Kong','',1,NULL,NULL,'2020-02-12 14:30:21','0000-00-00 00:00:00'),(101,348,'HUN','HU','Hungary','',1,NULL,NULL,'2020-02-12 14:30:22','0000-00-00 00:00:00'),(102,352,'ISL','IS','Iceland','',1,NULL,NULL,'2020-02-12 14:30:22','0000-00-00 00:00:00'),(103,356,'IND','IN','India','',1,NULL,NULL,'2020-02-12 14:30:22','0000-00-00 00:00:00'),(104,360,'IDN','ID','Indonesia','',1,NULL,NULL,'2020-02-12 14:30:22','0000-00-00 00:00:00'),(105,364,'IRN','IR','Iran, Islamic Republic of','',1,NULL,NULL,'2020-02-12 14:30:22','0000-00-00 00:00:00'),(106,368,'IRQ','IQ','Iraq','',1,NULL,NULL,'2020-02-12 14:30:22','0000-00-00 00:00:00'),(107,372,'IRL','IE','Ireland, Republic of','',1,NULL,NULL,'2020-02-12 14:30:22','0000-00-00 00:00:00'),(108,833,'IMN','IM','Isle of Man','',1,NULL,NULL,'2020-02-12 14:30:22','0000-00-00 00:00:00'),(109,376,'ISR','IL','Israel','',1,NULL,NULL,'2020-02-12 14:30:23','0000-00-00 00:00:00'),(110,380,'ITA','IT','Italy','',1,NULL,NULL,'2020-02-12 14:30:23','0000-00-00 00:00:00'),(111,388,'JAM','JM','Jamaica','',1,NULL,NULL,'2020-02-12 14:30:23','0000-00-00 00:00:00'),(112,392,'JPN','JP','Japan','',1,NULL,NULL,'2020-02-12 14:30:23','0000-00-00 00:00:00'),(113,400,'JOR','JO','Jordan','',1,NULL,NULL,'2020-02-12 14:30:23','0000-00-00 00:00:00'),(114,398,'KAZ','KZ','Kazakhstan','',1,NULL,NULL,'2020-02-12 14:30:23','0000-00-00 00:00:00'),(115,404,'KEN','KE','Kenya','',1,NULL,NULL,'2020-02-12 14:30:23','0000-00-00 00:00:00'),(116,296,'KIR','KI','Kiribati','',1,NULL,NULL,'2020-02-12 14:30:23','0000-00-00 00:00:00'),(117,408,'PRK','KP','Korea, Democratic Peoples Republic of','',1,NULL,NULL,'2020-02-12 14:30:24','0000-00-00 00:00:00'),(118,410,'KOR','KR','Korea, Republic of','',1,NULL,NULL,'2020-02-12 14:30:24','0000-00-00 00:00:00'),(119,414,'KWT','KW','Kuwait','',1,NULL,NULL,'2020-02-12 14:30:24','0000-00-00 00:00:00'),(120,417,'KGZ','KG','Kyrgyzstan','',1,NULL,NULL,'2020-02-12 14:30:24','0000-00-00 00:00:00'),(121,418,'LAO','LA','Laos','',1,NULL,NULL,'2020-02-12 14:30:24','0000-00-00 00:00:00'),(122,428,'LVA','LV','Latvia','',1,NULL,NULL,'2020-02-12 14:30:24','0000-00-00 00:00:00'),(123,422,'LBN','LB','Lebanon','',1,NULL,NULL,'2020-02-12 14:30:24','0000-00-00 00:00:00'),(124,426,'LSO','LS','Lesotho','',1,NULL,NULL,'2020-02-12 14:30:24','0000-00-00 00:00:00'),(125,430,'LBR','LR','Liberia','',1,NULL,NULL,'2020-02-12 14:30:24','0000-00-00 00:00:00'),(126,434,'LBY','LY','Libyan Arab Jamahiriya','',1,NULL,NULL,'2020-02-12 14:30:25','0000-00-00 00:00:00'),(127,438,'LIE','LI','Liechtenstein','',1,NULL,NULL,'2020-02-12 14:30:25','0000-00-00 00:00:00'),(128,440,'LTU','LT','Lithuania','',1,NULL,NULL,'2020-02-12 14:30:25','0000-00-00 00:00:00'),(129,442,'LUX','LU','Luxembourg','',1,NULL,NULL,'2020-02-12 14:30:25','0000-00-00 00:00:00'),(130,446,'MAC','MO','Macau, China','',1,NULL,NULL,'2020-02-12 14:30:25','0000-00-00 00:00:00'),(131,807,'MKD','MK','Macedonia','',1,NULL,NULL,'2020-02-12 14:30:25','0000-00-00 00:00:00'),(132,450,'MDG','MG','Madagascar','',1,NULL,NULL,'2020-02-12 14:30:25','0000-00-00 00:00:00'),(133,454,'MWI','MW','Malawi','',1,NULL,NULL,'2020-02-12 14:30:26','0000-00-00 00:00:00'),(134,458,'MYS','MY','Malaysia','',1,NULL,NULL,'2020-02-12 14:30:26','0000-00-00 00:00:00'),(135,462,'MDV','MV','Maldives','',1,NULL,NULL,'2020-02-12 14:30:26','0000-00-00 00:00:00'),(136,466,'MLI','ML','Mali','',1,NULL,NULL,'2020-02-12 14:30:26','0000-00-00 00:00:00'),(137,470,'MLT','MT','Malta','',1,NULL,NULL,'2020-02-12 14:30:26','0000-00-00 00:00:00'),(138,584,'MHL','MH','Marshall Islands','',1,NULL,NULL,'2020-02-12 14:30:26','0000-00-00 00:00:00'),(139,474,'MTQ','MQ','Martinique','',1,NULL,NULL,'2020-02-12 14:30:26','0000-00-00 00:00:00'),(140,478,'MRT','MR','Mauritania','',1,NULL,NULL,'2020-02-12 14:30:27','0000-00-00 00:00:00'),(141,480,'MUS','MU','Mauritius','',1,NULL,NULL,'2020-02-12 14:30:27','0000-00-00 00:00:00'),(142,175,'MYT','YT','Mayotte','',1,NULL,NULL,'2020-02-12 14:30:27','0000-00-00 00:00:00'),(143,484,'MEX','MX','Mexico','',1,NULL,NULL,'2020-02-12 14:30:27','0000-00-00 00:00:00'),(144,583,'FSM','FM','Micronesia','',1,NULL,NULL,'2020-02-12 14:30:27','0000-00-00 00:00:00'),(145,498,'MDA','MD','Moldova, Republic of','',1,NULL,NULL,'2020-02-12 14:30:27','0000-00-00 00:00:00'),(146,492,'MCO','MC','Monaco','',1,NULL,NULL,'2020-02-12 14:30:27','0000-00-00 00:00:00'),(147,496,'MNG','MN','Mongolia','',1,NULL,NULL,'2020-02-12 14:30:27','0000-00-00 00:00:00'),(148,499,'MNE','ME','Montenegro','',1,NULL,NULL,'2020-02-12 14:30:28','0000-00-00 00:00:00'),(149,504,'MAR','MA','Morocco','',1,NULL,NULL,'2020-02-12 14:30:28','0000-00-00 00:00:00'),(150,508,'MOZ','MZ','Mozambique','',1,NULL,NULL,'2020-02-12 14:30:28','0000-00-00 00:00:00'),(151,104,'MMR','MM','Myanmar','',1,NULL,NULL,'2020-02-12 14:30:28','0000-00-00 00:00:00'),(152,516,'NAM','NA','Namibia','',1,NULL,NULL,'2020-02-12 14:30:28','0000-00-00 00:00:00'),(153,520,'NRU','NR','Nauru','',1,NULL,NULL,'2020-02-12 14:30:28','0000-00-00 00:00:00'),(154,524,'NPL','NP','Nepal','',1,NULL,NULL,'2020-02-12 14:30:28','0000-00-00 00:00:00'),(155,528,'NLD','NL','Netherlands','',1,NULL,NULL,'2020-02-12 14:30:29','0000-00-00 00:00:00'),(156,540,'NCL','NC','New Caledonia','',1,NULL,NULL,'2020-02-12 14:30:29','0000-00-00 00:00:00'),(157,554,'NZL','NZ','New Zealand','',1,NULL,NULL,'2020-02-12 14:30:29','0000-00-00 00:00:00'),(158,558,'NIC','NI','Nicaragua','',1,NULL,NULL,'2020-02-12 14:30:29','0000-00-00 00:00:00'),(159,562,'NER','NE','Niger','',1,NULL,NULL,'2020-02-12 14:30:29','0000-00-00 00:00:00'),(160,566,'NGA','NG','Nigeria','',1,NULL,NULL,'2020-02-12 14:30:29','0000-00-00 00:00:00'),(161,570,'NIU','NU','Niue','',1,NULL,NULL,'2020-02-12 14:30:29','0000-00-00 00:00:00'),(162,574,'NFK','NF','Norfolk Island','',1,NULL,NULL,'2020-02-12 14:30:30','0000-00-00 00:00:00'),(163,580,'MNP','MP','Northern Mariana Islands','',1,NULL,NULL,'2020-02-12 14:30:30','0000-00-00 00:00:00'),(164,578,'NOR','NO','Norway','',1,NULL,NULL,'2020-02-12 14:30:30','0000-00-00 00:00:00'),(165,512,'OMN','OM','Oman','',1,NULL,NULL,'2020-02-12 14:30:30','0000-00-00 00:00:00'),(166,586,'PAK','PK','Pakistan','',1,NULL,NULL,'2020-02-12 14:30:30','0000-00-00 00:00:00'),(167,585,'PLW','PW','Palau','',1,NULL,NULL,'2020-02-12 14:30:30','0000-00-00 00:00:00'),(168,275,'PSE','PS','Palestine, State of','',1,NULL,NULL,'2020-02-12 14:30:31','0000-00-00 00:00:00'),(169,591,'PAN','PA','Panama','',1,NULL,NULL,'2020-02-12 14:30:31','0000-00-00 00:00:00'),(170,598,'PNG','PG','Papua New Guinea','',1,NULL,NULL,'2020-02-12 14:30:31','0000-00-00 00:00:00'),(171,600,'PRY','PY','Paraguay','',1,NULL,NULL,'2020-02-12 14:30:31','0000-00-00 00:00:00'),(172,604,'PER','PE','Peru','',1,NULL,NULL,'2020-02-12 14:30:31','0000-00-00 00:00:00'),(173,608,'PHL','PH','Philippines','',1,NULL,NULL,'2020-02-12 14:30:31','0000-00-00 00:00:00'),(174,612,'PCN','PN','Pitcairn','',1,NULL,NULL,'2020-02-12 14:30:31','0000-00-00 00:00:00'),(175,616,'POL','PL','Poland','',1,NULL,NULL,'2020-02-12 14:30:32','0000-00-00 00:00:00'),(176,620,'PRT','PT','Portugal','',1,NULL,NULL,'2020-02-12 14:30:32','0000-00-00 00:00:00'),(177,630,'PRI','PR','Puerto Rico','',1,NULL,NULL,'2020-02-12 14:30:32','0000-00-00 00:00:00'),(178,634,'QAT','QA','Qatar','',1,NULL,NULL,'2020-02-12 14:30:32','0000-00-00 00:00:00'),(179,638,'REU','RE','Réunion','',1,NULL,NULL,'2020-02-12 14:30:32','0000-00-00 00:00:00'),(180,642,'ROU','RO','Romania','',1,NULL,NULL,'2020-02-12 14:30:32','0000-00-00 00:00:00'),(181,643,'RUS','RU','Russian Federation','',1,NULL,NULL,'2020-02-12 14:30:32','0000-00-00 00:00:00'),(182,646,'RWA','RW','Rwanda','',1,NULL,NULL,'2020-02-12 14:30:32','0000-00-00 00:00:00'),(183,882,'WSM','WS','Samoa','',1,NULL,NULL,'2020-02-12 14:30:33','0000-00-00 00:00:00'),(184,674,'SMR','SM','San Marino','',1,NULL,NULL,'2020-02-12 14:30:33','0000-00-00 00:00:00'),(185,678,'STP','ST','Sao Tome and Principe','',1,NULL,NULL,'2020-02-12 14:30:33','0000-00-00 00:00:00'),(186,682,'SAU','SA','Saudi Arabia','',1,NULL,NULL,'2020-02-12 14:30:33','0000-00-00 00:00:00'),(187,686,'SEN','SN','Senegal','',1,NULL,NULL,'2020-02-12 14:30:33','0000-00-00 00:00:00'),(188,688,'SRB','RS','Serbia, Republic of','',1,NULL,NULL,'2020-02-12 14:30:33','0000-00-00 00:00:00'),(189,690,'SYC','SC','Seychelles','',1,NULL,NULL,'2020-02-12 14:30:34','0000-00-00 00:00:00'),(190,694,'SLE','SL','Sierra Leone','',1,NULL,NULL,'2020-02-12 14:30:34','0000-00-00 00:00:00'),(191,702,'SGP','SG','Singapore','',1,NULL,NULL,'2020-02-12 14:30:34','0000-00-00 00:00:00'),(192,534,'SXM','SX','Sint Maarten','',1,NULL,NULL,'2020-02-12 14:30:34','0000-00-00 00:00:00'),(193,703,'SVK','SK','Slovakia','',1,NULL,NULL,'2020-02-12 14:30:34','0000-00-00 00:00:00'),(194,705,'SVN','SI','Slovenia','',1,NULL,NULL,'2020-02-12 14:30:34','0000-00-00 00:00:00'),(195,90,'SLB','SB','Solomon Islands','',1,NULL,NULL,'2020-02-12 14:30:35','0000-00-00 00:00:00'),(196,706,'SOM','SO','Somalia','',1,NULL,NULL,'2020-02-12 14:30:35','0000-00-00 00:00:00'),(197,710,'ZAF','ZA','South Africa','',1,NULL,NULL,'2020-02-12 14:30:35','0000-00-00 00:00:00'),(198,239,'SGS','GS','South Georgia and the South Sandwich Islands','',1,NULL,NULL,'2020-02-12 14:30:35','0000-00-00 00:00:00'),(199,728,'SSD','SS','South Sudan','',1,NULL,NULL,'2020-02-12 14:30:35','0000-00-00 00:00:00'),(200,724,'ESP','ES','Spain','',1,NULL,NULL,'2020-02-12 14:30:35','0000-00-00 00:00:00'),(201,144,'LKA','LK','Sri Lanka','',1,NULL,NULL,'2020-02-12 14:30:36','0000-00-00 00:00:00'),(202,654,'SHN','SH','St. Helena','',1,NULL,NULL,'2020-02-12 14:30:36','0000-00-00 00:00:00'),(203,659,'KNA','KN','St. Kitts and Nevis','',1,NULL,NULL,'2020-02-12 14:30:36','0000-00-00 00:00:00'),(204,662,'LCA','LC','St. Lucia','',1,NULL,NULL,'2020-02-12 14:30:36','0000-00-00 00:00:00'),(205,663,'MAF','MF','St. Martin','',1,NULL,NULL,'2020-02-12 14:30:36','0000-00-00 00:00:00'),(206,666,'SPM','PM','St. Pierre and Miquelon','',1,NULL,NULL,'2020-02-12 14:30:36','0000-00-00 00:00:00'),(207,670,'VCT','VC','St. Vincent and the Grenadines','',1,NULL,NULL,'2020-02-12 14:30:37','0000-00-00 00:00:00'),(208,736,'SDN','SD','Sudan','',1,NULL,NULL,'2020-02-12 14:30:37','0000-00-00 00:00:00'),(209,740,'SUR','SR','Suriname','',1,NULL,NULL,'2020-02-12 14:30:37','0000-00-00 00:00:00'),(210,744,'SJM','SJ','Svalbard and Jan Mayen Islands','',1,NULL,NULL,'2020-02-12 14:30:37','0000-00-00 00:00:00'),(211,748,'SWZ','SZ','Swaziland','',1,NULL,NULL,'2020-02-12 14:30:37','0000-00-00 00:00:00'),(212,752,'SWE','SE','Sweden','',1,NULL,NULL,'2020-02-12 14:30:37','0000-00-00 00:00:00'),(213,756,'CHE','CH','Switzerland','',1,NULL,NULL,'2020-02-12 14:30:37','0000-00-00 00:00:00'),(214,760,'SYR','SY','Syrian Arab Republic','',1,NULL,NULL,'2020-02-12 14:30:37','0000-00-00 00:00:00'),(215,158,'TWN','TW','Taiwan','',1,NULL,NULL,'2020-02-12 14:30:37','0000-00-00 00:00:00'),(216,762,'TJK','TJ','Tajikistan','',1,NULL,NULL,'2020-02-12 14:30:37','0000-00-00 00:00:00'),(217,834,'TZA','TZ','Tanzania, United Republic of','',1,NULL,NULL,'2020-02-12 14:30:37','0000-00-00 00:00:00'),(218,764,'THA','TH','Thailand','',1,NULL,NULL,'2020-02-12 14:30:38','0000-00-00 00:00:00'),(219,626,'TLS','TL','Timor-Leste','',1,NULL,NULL,'2020-02-12 14:30:38','0000-00-00 00:00:00'),(220,768,'TGO','TG','Togo','',1,NULL,NULL,'2020-02-12 14:30:38','0000-00-00 00:00:00'),(221,772,'TKL','TK','Tokelau','',1,NULL,NULL,'2020-02-12 14:30:38','0000-00-00 00:00:00'),(222,776,'TON','TO','Tonga','',1,NULL,NULL,'2020-02-12 14:30:38','0000-00-00 00:00:00'),(223,780,'TTO','TT','Trinidad and Tobago','',1,NULL,NULL,'2020-02-12 14:30:38','0000-00-00 00:00:00'),(224,788,'TUN','TN','Tunisia','',1,NULL,NULL,'2020-02-12 14:30:38','0000-00-00 00:00:00'),(225,792,'TUR','TR','Turkey','',1,NULL,NULL,'2020-02-12 14:30:38','0000-00-00 00:00:00'),(226,795,'TKM','TM','Turkmenistan','',1,NULL,NULL,'2020-02-12 14:30:38','0000-00-00 00:00:00'),(227,796,'TCA','TC','Turks and Caicos Islands','',1,NULL,NULL,'2020-02-12 14:30:38','0000-00-00 00:00:00'),(228,798,'TUV','TV','Tuvalu','',1,NULL,NULL,'2020-02-12 14:30:38','0000-00-00 00:00:00'),(229,800,'UGA','UG','Uganda','',1,NULL,NULL,'2020-02-12 14:30:39','0000-00-00 00:00:00'),(230,804,'UKR','UA','Ukraine','',1,NULL,NULL,'2020-02-12 14:30:39','0000-00-00 00:00:00'),(231,784,'ARE','AE','United Arab Emirates','',1,NULL,NULL,'2020-02-12 14:30:39','0000-00-00 00:00:00'),(232,826,'GBR','GB','United Kingdom','',1,NULL,NULL,'2020-02-12 14:30:39','0000-00-00 00:00:00'),(233,840,'USA','US','United States','',1,NULL,NULL,'2020-02-12 14:30:39','0000-00-00 00:00:00'),(234,581,'UMI','UM','United States Minor Outlying Islands','',1,NULL,NULL,'2020-02-12 14:30:39','0000-00-00 00:00:00'),(235,850,'VIR','VI','United States Virgin Islands','',1,NULL,NULL,'2020-02-12 14:30:39','0000-00-00 00:00:00'),(236,858,'URY','UY','Uruguay','',1,NULL,NULL,'2020-02-12 14:30:39','0000-00-00 00:00:00'),(237,860,'UZB','UZ','Uzbekistan','',1,NULL,NULL,'2020-02-12 14:30:39','0000-00-00 00:00:00'),(238,548,'VUT','VU','Vanuatu','',1,NULL,NULL,'2020-02-12 14:30:39','0000-00-00 00:00:00'),(239,862,'VEN','VE','Venezuela','',1,NULL,NULL,'2020-02-12 14:30:39','0000-00-00 00:00:00'),(240,704,'VNM','VN','Vietnam','',1,NULL,NULL,'2020-02-12 14:30:40','0000-00-00 00:00:00'),(241,876,'WLF','WF','Wallis and Futuna Islands','',1,NULL,NULL,'2020-02-12 14:30:40','0000-00-00 00:00:00'),(242,732,'ESH','EH','Western Sahara','',1,NULL,NULL,'2020-02-12 14:30:40','0000-00-00 00:00:00'),(243,887,'YEM','YE','Yemen','',1,NULL,NULL,'2020-02-12 14:30:40','0000-00-00 00:00:00'),(244,894,'ZMB','ZM','Zambia','',1,NULL,NULL,'2020-02-12 14:30:40','0000-00-00 00:00:00'),(245,716,'ZWE','ZW','Zimbabwe','',1,NULL,NULL,'2020-02-12 14:30:40','0000-00-00 00:00:00');
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
) ENGINE=InnoDB AUTO_INCREMENT=264 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currency`
--

LOCK TABLES `currency` WRITE;
/*!40000 ALTER TABLE `currency` DISABLE KEYS */;
INSERT INTO `currency` VALUES (1,971,'Afghanistan','AFN','AFGHANI',0,'',1,NULL,NULL,'2020-02-12 14:31:05','0000-00-00 00:00:00'),(2,4,'Afghanistan','AFA','AFGHANI',0,'',1,NULL,NULL,'2020-02-12 14:31:05','0000-00-00 00:00:00'),(3,8,'Albania','ALL','LEK',0,'',1,NULL,NULL,'2020-02-12 14:31:05','0000-00-00 00:00:00'),(4,12,'Algeria','DZD','ALGERIAN DINAR',0,'',1,NULL,NULL,'2020-02-12 14:31:05','0000-00-00 00:00:00'),(5,840,'American Samoa','USD','U.S. DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:06','0000-00-00 00:00:00'),(6,978,'Andorra','EUR','EURO',0,'',1,NULL,NULL,'2020-02-12 14:31:06','0000-00-00 00:00:00'),(7,973,'Angola','AOA','KWANZA',0,'',1,NULL,NULL,'2020-02-12 14:31:06','0000-00-00 00:00:00'),(8,951,'Anguilla','XCD','E. CARIBBEAN DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:06','0000-00-00 00:00:00'),(9,578,'Antarctica','NOK','NORWEGIAN KRONE',0,'',1,NULL,NULL,'2020-02-12 14:31:06','0000-00-00 00:00:00'),(10,951,'Antigua and Barbuda','XCD','E. CARIBBEAN DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:06','0000-00-00 00:00:00'),(11,32,'Argentina','ARS','ARGENTINE PESO',0,'',1,NULL,NULL,'2020-02-12 14:31:07','0000-00-00 00:00:00'),(12,51,'Armenia','AMD','ARMENIAN DRAM',0,'',1,NULL,NULL,'2020-02-12 14:31:07','0000-00-00 00:00:00'),(13,533,'Aruba','AWG','ARUBAN GUILDER',0,'',1,NULL,NULL,'2020-02-12 14:31:07','0000-00-00 00:00:00'),(14,36,'Australia','AUD','AUSTRALIAN DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:07','0000-00-00 00:00:00'),(15,978,'Austria','EUR','EURO',0,'',1,NULL,NULL,'2020-02-12 14:31:07','0000-00-00 00:00:00'),(16,31,'Azerbaijan','AZM','AZERBAIJANIAN MANAT',0,'',1,NULL,NULL,'2020-02-12 14:31:08','0000-00-00 00:00:00'),(17,944,'Azerbaijan','AZN','AZERBAIJAN MANAT',0,'',1,NULL,NULL,'2020-02-12 14:31:08','0000-00-00 00:00:00'),(18,44,'Bahamas','BSD','BAHAMIAN DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:08','0000-00-00 00:00:00'),(19,48,'Bahrain','BHD','BAHRAINI DINAR',0,'',1,NULL,NULL,'2020-02-12 14:31:08','0000-00-00 00:00:00'),(20,50,'Bangladesh','BDT','TAKA',0,'',1,NULL,NULL,'2020-02-12 14:31:08','0000-00-00 00:00:00'),(21,52,'Barbados','BBD','BARBADOS DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:08','0000-00-00 00:00:00'),(22,974,'Belarus','BYR','BELARUSSIAN RUBLE',0,'',1,NULL,NULL,'2020-02-12 14:31:08','0000-00-00 00:00:00'),(23,933,'Belarus','BYN','BELARUSSIAN RUBLE',0,'',1,NULL,NULL,'2020-02-12 14:31:09','0000-00-00 00:00:00'),(24,978,'Belgium','EUR','EURO',0,'',1,NULL,NULL,'2020-02-12 14:31:09','0000-00-00 00:00:00'),(25,84,'Belize','BZD','BELIZE DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:09','0000-00-00 00:00:00'),(26,952,'Benin','XOF','CFA FRANC BCEAO',0,'',1,NULL,NULL,'2020-02-12 14:31:09','0000-00-00 00:00:00'),(27,60,'Bermuda','BMD','BERMUDIAN DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:09','0000-00-00 00:00:00'),(28,64,'Bhutan','BTN','BHUTAN NGULTRUM',0,'',1,NULL,NULL,'2020-02-12 14:31:09','0000-00-00 00:00:00'),(29,356,'Bhutan','INR','INDIAN RUPEE',0,'',1,NULL,NULL,'2020-02-12 14:31:09','0000-00-00 00:00:00'),(30,68,'Bolivia','BOB','BOLIVIANO',0,'',1,NULL,NULL,'2020-02-12 14:31:10','0000-00-00 00:00:00'),(31,977,'Bosnia and Herzegovina','BAM','CONVERTIBLE MARK',0,'',1,NULL,NULL,'2020-02-12 14:31:10','0000-00-00 00:00:00'),(32,72,'Botswana','BWP','PULA',0,'',1,NULL,NULL,'2020-02-12 14:31:10','0000-00-00 00:00:00'),(33,578,'Bouvet Island','NOK','NORWEGIAN KRONE',0,'',1,NULL,NULL,'2020-02-12 14:31:10','0000-00-00 00:00:00'),(34,76,'Brazil','BRE','BRAZILIAN CRUZEIRO',0,'',1,NULL,NULL,'2020-02-12 14:31:10','0000-00-00 00:00:00'),(35,986,'Brazil','BRL','BRAZILIAN REAL',0,'',1,NULL,NULL,'2020-02-12 14:31:10','0000-00-00 00:00:00'),(36,840,'British Indian Ocean Territory','USD','U.S. DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:10','0000-00-00 00:00:00'),(37,840,'British Virgin Islands','USD','U.S. DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:10','0000-00-00 00:00:00'),(38,96,'Brunei Darussalam','BND','BRUNEI DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:11','0000-00-00 00:00:00'),(39,975,'Bulgaria','BGN','BULGARIAN LEV',0,'',1,NULL,NULL,'2020-02-12 14:31:11','0000-00-00 00:00:00'),(40,952,'Burkina Faso','XOF','CFA FRANC BCEAO',0,'',1,NULL,NULL,'2020-02-12 14:31:11','0000-00-00 00:00:00'),(41,108,'Burundi','BIF','BURUNDI FRANC',0,'',1,NULL,NULL,'2020-02-12 14:31:11','0000-00-00 00:00:00'),(42,116,'Cambodia','KHR','RIEL',0,'',1,NULL,NULL,'2020-02-12 14:31:11','0000-00-00 00:00:00'),(43,950,'Cameroon, United Republic of','XAF','CFA FRANC BEAC',0,'',1,NULL,NULL,'2020-02-12 14:31:11','0000-00-00 00:00:00'),(44,124,'Canada','CAD','CANADIAN DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:12','0000-00-00 00:00:00'),(45,132,'Cape Verde Island','CVE','CAPE VERDE ESCUDO',0,'',1,NULL,NULL,'2020-02-12 14:31:12','0000-00-00 00:00:00'),(46,136,'Cayman Islands','KYD','CAYMAN IS. DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:12','0000-00-00 00:00:00'),(47,950,'Central African Republic','XAF','CFA FRANC BEAC',0,'',1,NULL,NULL,'2020-02-12 14:31:12','0000-00-00 00:00:00'),(48,950,'Chad','XAF','CFA FRANC BEAC',0,'',1,NULL,NULL,'2020-02-12 14:31:12','0000-00-00 00:00:00'),(49,152,'Chile','CLP','CHILEAN PESO',0,'',1,NULL,NULL,'2020-02-12 14:31:12','0000-00-00 00:00:00'),(50,156,'China','CNY','YUAN RENMINBI',0,'',1,NULL,NULL,'2020-02-12 14:31:12','0000-00-00 00:00:00'),(51,36,'Christmas Island','AUD','AUSTRALIAN DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:12','0000-00-00 00:00:00'),(52,36,'Cocos (Keeling) Islands','AUD','AUSTRALIAN DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:12','0000-00-00 00:00:00'),(53,170,'Colombia','COP','COLOMBIAN PESO',0,'',1,NULL,NULL,'2020-02-12 14:31:13','0000-00-00 00:00:00'),(54,174,'Comoros','KMF','COMORO FRANC',0,'',1,NULL,NULL,'2020-02-12 14:31:13','0000-00-00 00:00:00'),(55,950,'Congo','XAF','CFA FRANC BEAC',0,'',1,NULL,NULL,'2020-02-12 14:31:13','0000-00-00 00:00:00'),(56,554,'Cook Islands','NZD','NEW ZEALAND DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:13','0000-00-00 00:00:00'),(57,188,'Costa Rica','CRC','COSTA RICAN COLON',0,'',1,NULL,NULL,'2020-02-12 14:31:13','0000-00-00 00:00:00'),(58,952,'Côte d\'Ivoire (Ivory Coast)','XOF','CFA FRANC BCEAO',0,'',1,NULL,NULL,'2020-02-12 14:31:13','0000-00-00 00:00:00'),(59,191,'Croatia','HRK','CROATIAN KUNA',0,'',1,NULL,NULL,'2020-02-12 14:31:13','0000-00-00 00:00:00'),(60,192,'Cuba','CUP','CUBAN PESO',0,'',1,NULL,NULL,'2020-02-12 14:31:13','0000-00-00 00:00:00'),(61,196,'Cyprus','CYP','CYPRUS POUND',0,'',1,NULL,NULL,'2020-02-12 14:31:13','0000-00-00 00:00:00'),(62,978,'Cyprus','EUR','EURO',0,'',1,NULL,NULL,'2020-02-12 14:31:13','0000-00-00 00:00:00'),(63,203,'Czech Republic','CZK','CZECH KORUNA',0,'',1,NULL,NULL,'2020-02-12 14:31:14','0000-00-00 00:00:00'),(64,976,'Democratic Republic of the Congo','CDF','FRANC CONGOLAIS',0,'',1,NULL,NULL,'2020-02-12 14:31:14','0000-00-00 00:00:00'),(65,208,'Denmark','DKK','DANISH KRONE',0,'',1,NULL,NULL,'2020-02-12 14:31:14','0000-00-00 00:00:00'),(66,262,'Djibouti','DJF','DJIBOUTI FRANC',0,'',1,NULL,NULL,'2020-02-12 14:31:14','0000-00-00 00:00:00'),(67,951,'Dominica','XCD','E. CARIBBEAN DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:14','0000-00-00 00:00:00'),(68,214,'Dominican Republic','DOP','DOMINICAN PESO',0,'',1,NULL,NULL,'2020-02-12 14:31:14','0000-00-00 00:00:00'),(69,840,'Ecuador','USD','U.S. DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:14','0000-00-00 00:00:00'),(70,818,'Egypt','EGP','EGYPTIAN POUND',0,'',1,NULL,NULL,'2020-02-12 14:31:14','0000-00-00 00:00:00'),(71,222,'EL Salvador','SVC','EL SALVADOR COLON',0,'',1,NULL,NULL,'2020-02-12 14:31:14','0000-00-00 00:00:00'),(72,840,'El Salvador','USD','U.S. DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:15','0000-00-00 00:00:00'),(73,950,'Equatorial Guinea','XAF','CFA FRANC BEAC',0,'',1,NULL,NULL,'2020-02-12 14:31:15','0000-00-00 00:00:00'),(74,232,'Eritrea','ERN','ERITREAN NAKFA',0,'',1,NULL,NULL,'2020-02-12 14:31:15','0000-00-00 00:00:00'),(75,233,'Estonia','EEK','ESTONIAN KROON',0,'',1,NULL,NULL,'2020-02-12 14:31:15','0000-00-00 00:00:00'),(76,978,'Estonia','EUR','EURO',0,'',1,NULL,NULL,'2020-02-12 14:31:15','0000-00-00 00:00:00'),(77,230,'Ethiopia','ETB','ETHIOPIAN BIRR',0,'',1,NULL,NULL,'2020-02-12 14:31:15','0000-00-00 00:00:00'),(78,238,'Falkland Islands (Malvinas)','FKP','FALKLAND IS. POUND',0,'',1,NULL,NULL,'2020-02-12 14:31:15','0000-00-00 00:00:00'),(79,208,'Faroe Islands','DKK','DANISH KRONE',0,'',1,NULL,NULL,'2020-02-12 14:31:15','0000-00-00 00:00:00'),(80,242,'Fiji','FJD','FIJI DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:15','0000-00-00 00:00:00'),(81,978,'Finland','EUR','EURO',0,'',1,NULL,NULL,'2020-02-12 14:31:15','0000-00-00 00:00:00'),(82,978,'France','EUR','EURO',0,'',1,NULL,NULL,'2020-02-12 14:31:15','0000-00-00 00:00:00'),(83,978,'French Guiana','EUR','EURO',0,'',1,NULL,NULL,'2020-02-12 14:31:16','0000-00-00 00:00:00'),(84,953,'French Polynesia','XPF','CFP FRANC',0,'',1,NULL,NULL,'2020-02-12 14:31:16','0000-00-00 00:00:00'),(85,978,'French Southern Territories','EUR','EURO',0,'',1,NULL,NULL,'2020-02-12 14:31:16','0000-00-00 00:00:00'),(86,950,'Gabon','XAF','CFA FRANC BEAC',0,'',1,NULL,NULL,'2020-02-12 14:31:16','0000-00-00 00:00:00'),(87,270,'Gambia','GMD','DALASI',0,'',1,NULL,NULL,'2020-02-12 14:31:16','0000-00-00 00:00:00'),(88,981,'Georgia','GEL','LARI',0,'',1,NULL,NULL,'2020-02-12 14:31:16','0000-00-00 00:00:00'),(89,978,'Germany','EUR','EURO',0,'',1,NULL,NULL,'2020-02-12 14:31:16','0000-00-00 00:00:00'),(90,936,'Ghana','GHS','CEDI',0,'',1,NULL,NULL,'2020-02-12 14:31:16','0000-00-00 00:00:00'),(91,288,'Ghana','GHC','GHANA CEDI',0,'',1,NULL,NULL,'2020-02-12 14:31:16','0000-00-00 00:00:00'),(92,292,'Gibralter','GIP','GIBRALTAR POUND',0,'',1,NULL,NULL,'2020-02-12 14:31:16','0000-00-00 00:00:00'),(93,978,'Greece','EUR','EURO',0,'',1,NULL,NULL,'2020-02-12 14:31:16','0000-00-00 00:00:00'),(94,208,'Greenland','DKK','DANISH KRONE',0,'',1,NULL,NULL,'2020-02-12 14:31:17','0000-00-00 00:00:00'),(95,951,'Grenada','XCD','E. CARIBBEAN DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:17','0000-00-00 00:00:00'),(96,978,'Guadeloupe','EUR','EURO',0,'',1,NULL,NULL,'2020-02-12 14:31:17','0000-00-00 00:00:00'),(97,840,'Guam','USD','U.S. DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:17','0000-00-00 00:00:00'),(98,320,'Guatemala','GTQ','QUETZAL',0,'',1,NULL,NULL,'2020-02-12 14:31:17','0000-00-00 00:00:00'),(99,324,'Guinea','GNF','GUINEA FRANC',0,'',1,NULL,NULL,'2020-02-12 14:31:17','0000-00-00 00:00:00'),(100,624,'Guinea-Bissau','GWP','GUINEA-BISSAU PESO',0,'',1,NULL,NULL,'2020-02-12 14:31:17','0000-00-00 00:00:00'),(101,952,'Guinea-Bissau','XOF','CFA FRANC BCEAO',0,'',1,NULL,NULL,'2020-02-12 14:31:17','0000-00-00 00:00:00'),(102,328,'Guyana','GYD','GUYANA DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:17','0000-00-00 00:00:00'),(103,332,'Haiti','HTG','GOURDE',0,'',1,NULL,NULL,'2020-02-12 14:31:18','0000-00-00 00:00:00'),(104,332,'Heard and McDonald Islands','HTG','GOURDE',0,'',1,NULL,NULL,'2020-02-12 14:31:18','0000-00-00 00:00:00'),(105,978,'Holy See (Vatican City State)','EUR','EURO',0,'',1,NULL,NULL,'2020-02-12 14:31:18','0000-00-00 00:00:00'),(106,340,'Honduras','HNL','LEMPIRA',0,'',1,NULL,NULL,'2020-02-12 14:31:18','0000-00-00 00:00:00'),(107,344,'Hong Kong','HKD','HONG KONG DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:18','0000-00-00 00:00:00'),(108,348,'Hungary','HUF','FORINT',0,'',1,NULL,NULL,'2020-02-12 14:31:18','0000-00-00 00:00:00'),(109,352,'Iceland','ISK','ICELAND KRONA',0,'',1,NULL,NULL,'2020-02-12 14:31:18','0000-00-00 00:00:00'),(110,356,'India','INR','INDIAN RUPEE',0,'',1,NULL,NULL,'2020-02-12 14:31:19','0000-00-00 00:00:00'),(111,360,'Indonesia','IDR','RUPIAH',0,'',1,NULL,NULL,'2020-02-12 14:31:19','0000-00-00 00:00:00'),(112,364,'Iran, Islamic Republic of','IRR','IRANIAN RIAL',0,'',1,NULL,NULL,'2020-02-12 14:31:19','0000-00-00 00:00:00'),(113,365,'Iran, Islamic Republic of','IRA','IRANIAN AIRLINE RATE',0,'',1,NULL,NULL,'2020-02-12 14:31:19','0000-00-00 00:00:00'),(114,368,'Iraq','IQD','IRAQI DINAR',0,'',1,NULL,NULL,'2020-02-12 14:31:19','0000-00-00 00:00:00'),(115,978,'Ireland, Republic of','EUR','EURO',0,'',1,NULL,NULL,'2020-02-12 14:31:19','0000-00-00 00:00:00'),(116,376,'Israel','ILS','NEW ISRAELI SHEKEL',0,'',1,NULL,NULL,'2020-02-12 14:31:19','0000-00-00 00:00:00'),(117,978,'Italy','EUR','EURO',0,'',1,NULL,NULL,'2020-02-12 14:31:19','0000-00-00 00:00:00'),(118,388,'Jamaica','JMD','JAMAICAN DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:19','0000-00-00 00:00:00'),(119,392,'Japan','JPY','YEN',0,'',1,NULL,NULL,'2020-02-12 14:31:19','0000-00-00 00:00:00'),(120,400,'Jordan','JOD','JORDANIAN DINAR',0,'',1,NULL,NULL,'2020-02-12 14:31:20','0000-00-00 00:00:00'),(121,398,'Kazakhstan','KZT','TENGE',0,'',1,NULL,NULL,'2020-02-12 14:31:20','0000-00-00 00:00:00'),(122,404,'Kenya','KES','KENYAN SHILLING',0,'',1,NULL,NULL,'2020-02-12 14:31:20','0000-00-00 00:00:00'),(123,36,'Kiribati','AUD','AUSTRALIAN DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:20','0000-00-00 00:00:00'),(124,414,'Kuwait','KWD','KUWAITI DINAR',0,'',1,NULL,NULL,'2020-02-12 14:31:20','0000-00-00 00:00:00'),(125,417,'Kyrgyzstan','KGS','SOM',0,'',1,NULL,NULL,'2020-02-12 14:31:20','0000-00-00 00:00:00'),(126,418,'Laos','LAK','KIP',0,'',1,NULL,NULL,'2020-02-12 14:31:20','0000-00-00 00:00:00'),(127,428,'Latvia','LVL','LATVIAN LATS',0,'',1,NULL,NULL,'2020-02-12 14:31:20','0000-00-00 00:00:00'),(128,978,'Latvia','EUR','EURO',0,'',1,NULL,NULL,'2020-02-12 14:31:20','0000-00-00 00:00:00'),(129,422,'Lebanon','LBP','LEBANESE POUND',0,'',1,NULL,NULL,'2020-02-12 14:31:21','0000-00-00 00:00:00'),(130,426,'Lesotho','LSL','LESOTHO LOTI',0,'',1,NULL,NULL,'2020-02-12 14:31:21','0000-00-00 00:00:00'),(131,430,'Liberia','LRD','LIBERIAN DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:21','0000-00-00 00:00:00'),(132,434,'Libyan Arab Jamahiriya','LYD','LIBYAN DINAR',0,'',1,NULL,NULL,'2020-02-12 14:31:21','0000-00-00 00:00:00'),(133,756,'Liechtenstein','CHF','SWISS FRANC',0,'',1,NULL,NULL,'2020-02-12 14:31:21','0000-00-00 00:00:00'),(134,440,'Lithuania','LTL','LITHUANIAN LITAS',0,'',1,NULL,NULL,'2020-02-12 14:31:21','0000-00-00 00:00:00'),(135,978,'Luxembourg','EUR','EURO',0,'',1,NULL,NULL,'2020-02-12 14:31:21','0000-00-00 00:00:00'),(136,446,'Macau, China','MOP','PATACA',0,'',1,NULL,NULL,'2020-02-12 14:31:21','0000-00-00 00:00:00'),(137,807,'Macedonia','MKD','DENAR',0,'',1,NULL,NULL,'2020-02-12 14:31:21','0000-00-00 00:00:00'),(138,969,'Madagascar','MGA','MALAGASY ARIARY',0,'',1,NULL,NULL,'2020-02-12 14:31:22','0000-00-00 00:00:00'),(139,454,'Malawi','MWK','MALAWI KWACHA',0,'',1,NULL,NULL,'2020-02-12 14:31:22','0000-00-00 00:00:00'),(140,458,'Malaysia','MYR','MALAYSIAN RINGGIT',0,'',1,NULL,NULL,'2020-02-12 14:31:22','0000-00-00 00:00:00'),(141,462,'Maldives','MVR','RUFIYAA',0,'',1,NULL,NULL,'2020-02-12 14:31:22','0000-00-00 00:00:00'),(142,952,'Mali','XOF','CFA FRANC BCEAO',0,'',1,NULL,NULL,'2020-02-12 14:31:22','0000-00-00 00:00:00'),(143,978,'Malta','EUR','EURO',0,'',1,NULL,NULL,'2020-02-12 14:31:22','0000-00-00 00:00:00'),(144,840,'Marshall Islands','USD','U.S. DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:22','0000-00-00 00:00:00'),(145,978,'Martinique','EUR','EURO',0,'',1,NULL,NULL,'2020-02-12 14:31:22','0000-00-00 00:00:00'),(146,478,'Mauritania','MRO','OUGUIYA',0,'',1,NULL,NULL,'2020-02-12 14:31:22','0000-00-00 00:00:00'),(147,480,'Mauritius','MUR','MAURITIUS RUPEE',0,'',1,NULL,NULL,'2020-02-12 14:31:23','0000-00-00 00:00:00'),(148,978,'Mayotte','EUR','EURO',0,'',1,NULL,NULL,'2020-02-12 14:31:23','0000-00-00 00:00:00'),(149,484,'Mexico','MXN','MEXICAN PESO',0,'',1,NULL,NULL,'2020-02-12 14:31:23','0000-00-00 00:00:00'),(150,840,'Micronesia','USD','U.S. DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:23','0000-00-00 00:00:00'),(151,498,'Moldova, Republic of','MDL','MOLDOVAN LEU',0,'',1,NULL,NULL,'2020-02-12 14:31:23','0000-00-00 00:00:00'),(152,978,'Monaco','EUR','EURO',0,'',1,NULL,NULL,'2020-02-12 14:31:23','0000-00-00 00:00:00'),(153,496,'Mongolia','MNT','TUGRIK',0,'',1,NULL,NULL,'2020-02-12 14:31:23','0000-00-00 00:00:00'),(154,978,'Montenegro','EUR','EURO',0,'',1,NULL,NULL,'2020-02-12 14:31:23','0000-00-00 00:00:00'),(155,504,'Morocco','MAD','MOROCCAN DIRHAM',0,'',1,NULL,NULL,'2020-02-12 14:31:23','0000-00-00 00:00:00'),(156,943,'Mozambique','MZN','MOZAMBIQUE METICAL',0,'',1,NULL,NULL,'2020-02-12 14:31:24','0000-00-00 00:00:00'),(157,104,'Myanmar','MMK','KYAT',0,'',1,NULL,NULL,'2020-02-12 14:31:24','0000-00-00 00:00:00'),(158,516,'Namibia','NAD','NAMIBIA DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:24','0000-00-00 00:00:00'),(159,36,'Nauru','AUD','AUSTRALIAN DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:24','0000-00-00 00:00:00'),(160,524,'Nepal','NPR','NEPALESE RUPEE',0,'',1,NULL,NULL,'2020-02-12 14:31:24','0000-00-00 00:00:00'),(161,978,'Netherlands','EUR','EURO',0,'',1,NULL,NULL,'2020-02-12 14:31:24','0000-00-00 00:00:00'),(162,532,'Netherlands','ANG','NETH. ANTI. GUILDER',0,'',1,NULL,NULL,'2020-02-12 14:31:24','0000-00-00 00:00:00'),(163,953,'New Caledonia','XPF','CFP FRANC',0,'',1,NULL,NULL,'2020-02-12 14:31:24','0000-00-00 00:00:00'),(164,554,'New Zealand','NZD','NEW ZEALAND DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:24','0000-00-00 00:00:00'),(165,558,'Nicaragua','NIO','CORDOBA ORO',0,'',1,NULL,NULL,'2020-02-12 14:31:24','0000-00-00 00:00:00'),(166,952,'Niger','XOF','CFA FRANC BCEAO',0,'',1,NULL,NULL,'2020-02-12 14:31:24','0000-00-00 00:00:00'),(167,566,'Nigeria','NGN','NAIRA',0,'',1,NULL,NULL,'2020-02-12 14:31:25','0000-00-00 00:00:00'),(168,554,'Niue','NZD','NEW ZEALAND DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:25','0000-00-00 00:00:00'),(169,36,'Norfolk Island','AUD','AUSTRALIAN DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:25','0000-00-00 00:00:00'),(170,840,'Northern Mariana Islands','USD','U.S. DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:25','0000-00-00 00:00:00'),(171,578,'Norway','NOK','NORWEGIAN KRONE',0,'',1,NULL,NULL,'2020-02-12 14:31:25','0000-00-00 00:00:00'),(172,512,'Oman','OMR','RIAL OMANI',0,'',1,NULL,NULL,'2020-02-12 14:31:25','0000-00-00 00:00:00'),(173,586,'Pakistan','PKR','PAKISTAN RUPEE',0,'',1,NULL,NULL,'2020-02-12 14:31:25','0000-00-00 00:00:00'),(174,840,'Palau','USD','U.S. DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:25','0000-00-00 00:00:00'),(175,840,'Palestine, State of','USD','U.S. DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:25','0000-00-00 00:00:00'),(176,590,'Panama','PAB','BALBOA',0,'',1,NULL,NULL,'2020-02-12 14:31:25','0000-00-00 00:00:00'),(177,598,'Papua New Guinea','PGK','KINA',0,'',1,NULL,NULL,'2020-02-12 14:31:26','0000-00-00 00:00:00'),(178,600,'Paraguay','PYG','GUARANI',0,'',1,NULL,NULL,'2020-02-12 14:31:26','0000-00-00 00:00:00'),(179,604,'Peru','PEN','NUEVO SOL',0,'',1,NULL,NULL,'2020-02-12 14:31:26','0000-00-00 00:00:00'),(180,608,'Philippines','PHP','PHILIPPINE PESO',0,'',1,NULL,NULL,'2020-02-12 14:31:26','0000-00-00 00:00:00'),(181,554,'Pitcairn','NZD','NEW ZEALAND DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:26','0000-00-00 00:00:00'),(182,985,'Poland','PLN','ZLOTY',0,'',1,NULL,NULL,'2020-02-12 14:31:26','0000-00-00 00:00:00'),(183,978,'Portugal','EUR','EURO',0,'',1,NULL,NULL,'2020-02-12 14:31:26','0000-00-00 00:00:00'),(184,840,'Puerto Rico','USD','U.S. DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:26','0000-00-00 00:00:00'),(185,634,'Qatar','QAR','QATARI RIAL',0,'',1,NULL,NULL,'2020-02-12 14:31:26','0000-00-00 00:00:00'),(186,978,'Réunion','EUR','EURO',0,'',1,NULL,NULL,'2020-02-12 14:31:26','0000-00-00 00:00:00'),(187,946,'Romania','RON','LEU',0,'',1,NULL,NULL,'2020-02-12 14:31:26','0000-00-00 00:00:00'),(188,642,'Romania','ROL','ROMANIAN LEU',0,'',1,NULL,NULL,'2020-02-12 14:31:26','0000-00-00 00:00:00'),(189,810,'Russian Federation','RUR','RUSSIAN RUBLE',0,'',1,NULL,NULL,'2020-02-12 14:31:26','0000-00-00 00:00:00'),(190,643,'Russian Federation','RUB','RUSSIAN RUBLE',0,'',1,NULL,NULL,'2020-02-12 14:31:27','0000-00-00 00:00:00'),(191,646,'Rwanda','RWF','RWANDA FRANC',0,'',1,NULL,NULL,'2020-02-12 14:31:27','0000-00-00 00:00:00'),(192,882,'Samoa','WST','TALA',0,'',1,NULL,NULL,'2020-02-12 14:31:27','0000-00-00 00:00:00'),(193,978,'San Marino','EUR','EURO',0,'',1,NULL,NULL,'2020-02-12 14:31:27','0000-00-00 00:00:00'),(194,678,'Sao Tome and Principe','STD','DOBRA',0,'',1,NULL,NULL,'2020-02-12 14:31:27','0000-00-00 00:00:00'),(195,682,'Saudi Arabia','SAR','SAUDI RIYAL',0,'',1,NULL,NULL,'2020-02-12 14:31:27','0000-00-00 00:00:00'),(196,952,'Senegal','XOF','CFA FRANC BCEAO',0,'',1,NULL,NULL,'2020-02-12 14:31:27','0000-00-00 00:00:00'),(197,941,'Serbia, Republic of','RSD','SERBIAN DINAR',0,'',1,NULL,NULL,'2020-02-12 14:31:27','0000-00-00 00:00:00'),(198,891,'Serbia, Republic of','CSD','SERBIAN DINAR',0,'',1,NULL,NULL,'2020-02-12 14:31:28','0000-00-00 00:00:00'),(199,690,'Seychelles','SCR','SEYCHELLES RUPEE',0,'',1,NULL,NULL,'2020-02-12 14:31:28','0000-00-00 00:00:00'),(200,694,'Sierra Leone','SLL','LEONE',0,'',1,NULL,NULL,'2020-02-12 14:31:28','0000-00-00 00:00:00'),(201,702,'Singapore','SGD','SINGAPORE DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:28','0000-00-00 00:00:00'),(202,703,'Slovakia','SKK','SLOVAK KORUNA',0,'',1,NULL,NULL,'2020-02-12 14:31:28','0000-00-00 00:00:00'),(203,978,'Slovakia','EUR','EURO',0,'',1,NULL,NULL,'2020-02-12 14:31:28','0000-00-00 00:00:00'),(204,705,'Slovenia','SIT','SLOVENIAN TOLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:28','0000-00-00 00:00:00'),(205,978,'Slovenia','EUR','EURO',0,'',1,NULL,NULL,'2020-02-12 14:31:28','0000-00-00 00:00:00'),(206,90,'Solomon Islands','SBD','SOLOMON IS. DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:28','0000-00-00 00:00:00'),(207,706,'Somalia','SOS','SOMALI SHILLING',0,'',1,NULL,NULL,'2020-02-12 14:31:28','0000-00-00 00:00:00'),(208,710,'South Africa','ZAR','RAND',0,'',1,NULL,NULL,'2020-02-12 14:31:28','0000-00-00 00:00:00'),(209,826,'South Georgia and the South Sandwich Islands','GBP','POUND STERLING',0,'',1,NULL,NULL,'2020-02-12 14:31:29','0000-00-00 00:00:00'),(210,978,'Spain','EUR','EURO',0,'',1,NULL,NULL,'2020-02-12 14:31:29','0000-00-00 00:00:00'),(211,144,'Sri Lanka','LKR','SRI LANKA RUPEE',0,'',1,NULL,NULL,'2020-02-12 14:31:29','0000-00-00 00:00:00'),(212,654,'St. Helena','SHP','ST. HELENA POUND',0,'',1,NULL,NULL,'2020-02-12 14:31:29','0000-00-00 00:00:00'),(213,951,'St. Kitts and Nevis','XCD','E. CARIBBEAN DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:29','0000-00-00 00:00:00'),(214,951,'St. Lucia','XCD','E. CARIBBEAN DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:29','0000-00-00 00:00:00'),(215,978,'St. Pierre and Miquelon','EUR','EURO',0,'',1,NULL,NULL,'2020-02-12 14:31:29','0000-00-00 00:00:00'),(216,951,'St. Vincent and the Grenadines','XCD','E. CARIBBEAN DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:29','0000-00-00 00:00:00'),(217,938,'Sudan','SDG','SUDANESE POUND',0,'',1,NULL,NULL,'2020-02-12 14:31:29','0000-00-00 00:00:00'),(218,736,'Sudan','SDD','SUDANESE DINAR',0,'',1,NULL,NULL,'2020-02-12 14:31:29','0000-00-00 00:00:00'),(219,737,'Sudan','SDA','SUDAN AIRLINE RATE',0,'',1,NULL,NULL,'2020-02-12 14:31:29','0000-00-00 00:00:00'),(220,728,'Sudan','SSP','SOUTH SUDANESE POUND',0,'',1,NULL,NULL,'2020-02-12 14:31:30','0000-00-00 00:00:00'),(221,740,'Suriname','SRG','SURINAM GUILDER',0,'',1,NULL,NULL,'2020-02-12 14:31:30','0000-00-00 00:00:00'),(222,968,'Suriname','SRD','SURINAME DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:30','0000-00-00 00:00:00'),(223,578,'Svalbard and Jan Mayen Islands','NOK','NORWEGIAN KRONE',0,'',1,NULL,NULL,'2020-02-12 14:31:30','0000-00-00 00:00:00'),(224,748,'Swaziland','SZL','LILANGENI',0,'',1,NULL,NULL,'2020-02-12 14:31:30','0000-00-00 00:00:00'),(225,752,'Sweden','SEK','SWEDISH KRONA',0,'',1,NULL,NULL,'2020-02-12 14:31:30','0000-00-00 00:00:00'),(226,756,'Switzerland','CHF','SWISS FRANC',0,'',1,NULL,NULL,'2020-02-12 14:31:30','0000-00-00 00:00:00'),(227,760,'Syrian Arab Republic','SYP','SYRIAN POUND',0,'',1,NULL,NULL,'2020-02-12 14:31:30','0000-00-00 00:00:00'),(228,901,'Taiwan','TWD','NEW TAIWAN DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:30','0000-00-00 00:00:00'),(229,972,'Tajikistan','TJS','SOMONI',0,'',1,NULL,NULL,'2020-02-12 14:31:30','0000-00-00 00:00:00'),(230,834,'Tanzania, United Republic of','TZS','TANZANIAN SHILLING',0,'',1,NULL,NULL,'2020-02-12 14:31:31','0000-00-00 00:00:00'),(231,764,'Thailand','THB','BAHT',0,'',1,NULL,NULL,'2020-02-12 14:31:31','0000-00-00 00:00:00'),(232,626,'Timor-Leste','TPE','TIMOR ESCUDO',0,'',1,NULL,NULL,'2020-02-12 14:31:31','0000-00-00 00:00:00'),(233,840,'Timor-Leste','USD','U.S. DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:31','0000-00-00 00:00:00'),(234,952,'Togo','XOF','CFA FRANC BCEAO',0,'',1,NULL,NULL,'2020-02-12 14:31:31','0000-00-00 00:00:00'),(235,554,'Tokelau','NZD','NEW ZEALAND DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:31','0000-00-00 00:00:00'),(236,776,'Tonga','TOP','PA\'ANGA',0,'',1,NULL,NULL,'2020-02-12 14:31:31','0000-00-00 00:00:00'),(237,780,'Trinidad and Tobago','TTD','TRINI & TOBA DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:31','0000-00-00 00:00:00'),(238,788,'Tunisia','TND','TUNISIAN DINAR',0,'',1,NULL,NULL,'2020-02-12 14:31:31','0000-00-00 00:00:00'),(239,792,'Turkey','TRL','TURKISH LIRA',0,'',1,NULL,NULL,'2020-02-12 14:31:31','0000-00-00 00:00:00'),(240,949,'Turkey','TRY','TURKISH LIRA',0,'',1,NULL,NULL,'2020-02-12 14:31:31','0000-00-00 00:00:00'),(241,795,'Turkmenistan','TMM','TURKMENISTAN MANAT',0,'',1,NULL,NULL,'2020-02-12 14:31:31','0000-00-00 00:00:00'),(242,934,'Turkmenistan','TMT','TURKMENISTAN MANAT (New)',0,'',1,NULL,NULL,'2020-02-12 14:31:32','0000-00-00 00:00:00'),(243,840,'Turks and Caicos Islands','USD','U.S. DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:32','0000-00-00 00:00:00'),(244,36,'Tuvalu','AUD','AUSTRALIAN DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:32','0000-00-00 00:00:00'),(245,800,'Uganda','UGX','UGANDA SHILLING',0,'',1,NULL,NULL,'2020-02-12 14:31:32','0000-00-00 00:00:00'),(246,980,'Ukraine','UAH','UKRAINIAN HRYVNIA',0,'',1,NULL,NULL,'2020-02-12 14:31:32','0000-00-00 00:00:00'),(247,784,'United Arab Emirates','AED','U.A.E. DIRHAM',0,'',1,NULL,NULL,'2020-02-12 14:31:32','0000-00-00 00:00:00'),(248,826,'United Kingdom','GBP','POUND STERLING',0,'',1,NULL,NULL,'2020-02-12 14:31:32','0000-00-00 00:00:00'),(249,840,'United States','USD','U.S. DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:32','0000-00-00 00:00:00'),(250,840,'United States Minor Outlying Islands','USD','U.S. DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:32','0000-00-00 00:00:00'),(251,840,'United States Virgin Islands','USD','U.S. DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:32','0000-00-00 00:00:00'),(252,858,'Uruguay','UYU','PESO URUGUAYO',0,'',1,NULL,NULL,'2020-02-12 14:31:32','0000-00-00 00:00:00'),(253,860,'Uzbekistan','UZS','UZBEKISTAN SUM',0,'',1,NULL,NULL,'2020-02-12 14:31:33','0000-00-00 00:00:00'),(254,548,'Vanuatu','VUV','VATU',0,'',1,NULL,NULL,'2020-02-12 14:31:33','0000-00-00 00:00:00'),(255,928,'Venezuela','VES','Bolivar Soberano (Sovereign Bolivar)',0,'',1,NULL,NULL,'2020-02-12 14:31:33','0000-00-00 00:00:00'),(256,704,'Vietnam','VND','DONG',0,'',1,NULL,NULL,'2020-02-12 14:31:33','0000-00-00 00:00:00'),(257,953,'Wallis and Futuna Islands','XPF','CFP FRANC',0,'',1,NULL,NULL,'2020-02-12 14:31:33','0000-00-00 00:00:00'),(258,504,'Western Sahara','MAD','MOROCCAN DIRHAM',0,'',1,NULL,NULL,'2020-02-12 14:31:33','0000-00-00 00:00:00'),(259,886,'Yemen','YER','YEMENI RIAL',0,'',1,NULL,NULL,'2020-02-12 14:31:33','0000-00-00 00:00:00'),(260,967,'Zambia','ZMW','ZAMBIAN KWACHA',0,'',1,NULL,NULL,'2020-02-12 14:31:33','0000-00-00 00:00:00'),(261,894,'Zambia','ZMK','ZAMBIAN KWACHA',0,'',1,NULL,NULL,'2020-02-12 14:31:33','0000-00-00 00:00:00'),(262,932,'Zimbabwe','ZWL','Zimbabwe',0,'',1,NULL,NULL,'2020-02-12 14:31:33','0000-00-00 00:00:00'),(263,716,'Zimbabwe','ZWD','ZIMBABWE DOLLAR',0,'',1,NULL,NULL,'2020-02-12 14:31:33','0000-00-00 00:00:00');
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,3,'981838666115','680369810536361','9602646089',1000,NULL,'','2020-06-21 15:07:48',NULL,NULL,NULL),(2,5,'342123200671','935085689645204','9863524512',1500,NULL,'','2020-06-22 11:12:59',NULL,NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customerTransaction`
--

LOCK TABLES `customerTransaction` WRITE;
/*!40000 ALTER TABLE `customerTransaction` DISABLE KEYS */;
INSERT INTO `customerTransaction` VALUES (1,2,1,'658401498037','DPW7829930042577188','TOPUP',100,'C','CASH','S','2020-06-22 11:15:33',NULL,NULL,NULL),(2,2,1,'735646020177','DPW7870875415361241','TOPUP',1900,'C','CASH','S','2020-06-22 11:20:22',NULL,NULL,NULL),(3,2,1,'490235605843','DPW8289189007112410','PAY',500,'D','CASH','S','2020-06-22 11:22:15',NULL,NULL,NULL),(4,1,1,'737724610710','DPW2134764567585217','TOPUP',2000,'C','CASH','S','2020-06-22 11:23:09',NULL,NULL,NULL),(5,1,1,'614372230563','DPW5301926708651664','PAY',1000,'D','CASH','S','2020-06-22 11:23:28',NULL,NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `limitManagement`
--

LOCK TABLES `limitManagement` WRITE;
/*!40000 ALTER TABLE `limitManagement` DISABLE KEYS */;
INSERT INTO `limitManagement` VALUES (1,0,1,'Merchant','SuperAdmin','2020-06-21 15:06:47',NULL,NULL),(2,1,0,'Customer','SuperAdmin','2020-06-21 15:08:12',NULL,NULL),(3,0,2,'Merchant','SuperAdmin','2020-06-22 09:07:45',NULL,NULL),(4,2,0,'Customer','SuperAdmin','2020-06-22 11:12:59',NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `limitManagementDetail`
--

LOCK TABLES `limitManagementDetail` WRITE;
/*!40000 ALTER TABLE `limitManagementDetail` DISABLE KEYS */;
INSERT INTO `limitManagementDetail` VALUES (1,1,'Day','TOPUP',22,3000),(2,1,'Day','PAY',33,13500),(3,1,'Monthly','TOPUP',97,21000),(4,1,'Monthly','PAY',198,48500),(5,2,'Day','TOPUP',4,0),(6,2,'Day','PAY',14,4000),(7,2,'Monthly','TOPUP',49,8000),(8,2,'Monthly','PAY',49,19000),(9,3,'Day','TOPUP',50,10000),(10,3,'Day','PAY',70,20000),(11,3,'Monthly','TOPUP',200,40000),(12,3,'Monthly','PAY',400,70000),(13,4,'Day','TOPUP',3,0),(14,4,'Day','PAY',14,4500),(15,4,'Monthly','TOPUP',48,8000),(16,4,'Monthly','PAY',49,19500);
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
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `limitProfile`
--

LOCK TABLES `limitProfile` WRITE;
/*!40000 ALTER TABLE `limitProfile` DISABLE KEYS */;
INSERT INTO `limitProfile` VALUES (1,'Customer Limit','Customer',0,'Customer Limit','\0','SuperAdmin','2020-06-20 12:06:22','SuperAdmin','2020-06-20 12:07:39'),(2,'Customer Limit','Customer',0,'Customer Limit','\0','SuperAdmin','2020-06-20 12:07:39','SuperAdmin','2020-06-20 13:15:32'),(3,'Customer Limit','Customer',0,'Customer Limit','\0','SuperAdmin','2020-06-20 13:15:32','SuperAdmin','2020-06-20 13:18:24'),(4,'Merchant Basic Limit','Merchant',1,'Merchant Basic Limit','\0','SuperAdmin','2020-06-20 13:18:09','SuperAdmin','2020-06-20 13:27:07'),(5,'Customer Limit','Customer',0,'Customer Limit','\0','SuperAdmin','2020-06-20 13:18:24','SuperAdmin','2020-06-20 13:39:22'),(6,'Merchant Limit','Merchant',2,'Merchant Premium Limit','\0','SuperAdmin','2020-06-20 13:19:11','SuperAdmin','2020-06-20 13:25:23'),(7,'Merchant Premium','Merchant',2,'Merchant Premium Limit','\0','SuperAdmin','2020-06-20 13:25:23','SuperAdmin','2020-06-20 13:27:52'),(8,'Merchant Basic','Merchant',1,'Merchant Basic Limit','\0','SuperAdmin','2020-06-20 13:27:07','SuperAdmin','2020-06-20 13:27:39'),(9,'Merchant Basic','Merchant',1,'Merchant Basic Limit','\0','SuperAdmin','2020-06-20 13:27:39','SuperAdmin','2020-06-20 13:39:29'),(10,'Merchant Premium','Merchant',1,'Merchant Premium Limit','\0','SuperAdmin','2020-06-20 13:27:52','SuperAdmin','2020-06-20 13:30:47'),(11,'Merchant Premium','Merchant',2,'Merchant Premium Limit','\0','SuperAdmin','2020-06-20 13:30:47','SuperAdmin','2020-06-20 13:31:00'),(12,'Merchant Premium','Merchant',2,'Merchant Premium Limit','\0','SuperAdmin','2020-06-20 13:31:00','SuperAdmin','2020-06-20 13:49:38'),(13,'Customer Limit','Customer',0,'Customer Limit','','SuperAdmin','2020-06-20 13:39:22',NULL,NULL),(14,'Merchant Basic','Merchant',1,'Merchant Basic Limit','','SuperAdmin','2020-06-20 13:39:29',NULL,NULL),(15,'Merchant Premium','Merchant',2,'Merchant Premium Limit','','SuperAdmin','2020-06-20 13:49:38',NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `limitProfileDetail`
--

LOCK TABLES `limitProfileDetail` WRITE;
/*!40000 ALTER TABLE `limitProfileDetail` DISABLE KEYS */;
INSERT INTO `limitProfileDetail` VALUES (41,13,'Day','TOPUP',5,2000),(42,13,'Day','PAY',15,5000),(43,13,'Monthly','TOPUP',50,10000),(44,13,'Monthly','PAY',50,20000),(45,14,'Day','TOPUP',25,7000),(46,14,'Day','PAY',35,15000),(47,14,'Monthly','TOPUP',100,25000),(48,14,'Monthly','PAY',200,50000),(49,15,'Day','TOPUP',50,10000),(50,15,'Day','PAY',70,20000),(51,15,'Monthly','TOPUP',200,40000),(52,15,'Monthly','PAY',400,70000);
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `merchant`
--

LOCK TABLES `merchant` WRITE;
/*!40000 ALTER TABLE `merchant` DISABLE KEYS */;
INSERT INTO `merchant` VALUES (1,'656052916793','5047','Royal Chemicals','data:image/jpeg;base64,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','Mukesh','','Kumar','mukesh@newsonfloor.com','9785205444','Royal Chemical is a privately owned custom chemical blender and contract manufacturer with headquarters in Twinsburg, Ohio. With liquid and dry blending capabilities, we blend and package a wide range of products for many industries, with a focus on household, industrial and institutional cleaning products','EDRPS0099K','29AADCB2230M1ZP',103,356,'158913784233258',1,'',1,'2020-06-21 15:06:35','',NULL,NULL),(2,'309596949458','5047','Phursungi medical','data:image/jpeg;base64,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','Mohit','','Tharwan','mohit@gmail.com','9325752171','Phursungi Medical is a privately owned custom chemical blender and contract manufacturer with headquarters in Twinsburg, Ohio. With liquid and dry blending capabilities, we blend and package a wide range of products for many industries, with a focus on household, industrial and institutional cleaning products','EDRPS0056K','29AADCB2230M1ZA',103,356,'723038634643004',1,'',1,'2020-06-22 09:07:45','',NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `merchantAddress`
--

LOCK TABLES `merchantAddress` WRITE;
/*!40000 ALTER TABLE `merchantAddress` DISABLE KEYS */;
INSERT INTO `merchantAddress` VALUES (1,1,1,1,'1','302012',''),(2,2,1,1,'1','302012','');
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `merchantMapping`
--

LOCK TABLES `merchantMapping` WRITE;
/*!40000 ALTER TABLE `merchantMapping` DISABLE KEYS */;
INSERT INTO `merchantMapping` VALUES (1,'309596949458981838666115',1,2,0,'\0','2020-06-22 10:52:26','MKCSE41',NULL,'MKCSE41',0),(2,'309596949458981838666115',1,2,0,'','2020-06-22 10:52:38','MKCSE41',NULL,NULL,0),(3,'656052916793981838666115',1,1,1000,'','2020-06-22 10:52:41','MKCSE41',NULL,NULL,0),(4,'309596949458342123200671',2,2,0,'','2020-06-22 11:13:26','MHIT92',NULL,NULL,0),(5,'656052916793342123200671',2,1,1500,'','2020-06-22 11:13:29','MHIT92',NULL,NULL,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `merchantTransaction`
--

LOCK TABLES `merchantTransaction` WRITE;
/*!40000 ALTER TABLE `merchantTransaction` DISABLE KEYS */;
INSERT INTO `merchantTransaction` VALUES (1,2,1,'490235605843','DPW8289189007112410','PAY',500,'C','CASH','S','2020-06-22 11:22:15',NULL,NULL,NULL),(2,1,1,'614372230563','DPW5301926708651664','PAY',1000,'C','CASH','S','2020-06-22 11:23:28',NULL,NULL,NULL),(3,1,1,'552277994029','DPW5655143408326725','PAY',500,'C','QRCode','P','2020-06-22 11:24:05',NULL,NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `merchantUser`
--

LOCK TABLES `merchantUser` WRITE;
/*!40000 ALTER TABLE `merchantUser` DISABLE KEYS */;
INSERT INTO `merchantUser` VALUES (1,1,2,'9785205444','','','2020-06-21 15:06:35','',NULL,NULL),(2,2,4,'9325752171','','','2020-06-22 09:07:45','',NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `state`
--

LOCK TABLES `state` WRITE;
/*!40000 ALTER TABLE `state` DISABLE KEYS */;
INSERT INTO `state` VALUES (1,'Rajasthan',103,'',1,'SuperAdmin',NULL,'2020-06-21 15:04:00','0000-00-00 00:00:00');
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
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subMenu`
--

LOCK TABLES `subMenu` WRITE;
/*!40000 ALTER TABLE `subMenu` DISABLE KEYS */;
INSERT INTO `subMenu` VALUES (1,2,'System Parameter','System Parameter','feather icon-file-plus','sysParameter/view',1,'','','','','\0',NULL,NULL,'2020-06-20 10:34:58','0000-00-00 00:00:00'),(2,2,'Country','Country','feather icon-file-plus','country/view',2,'','','','','\0',NULL,NULL,'2020-06-20 10:34:59','0000-00-00 00:00:00'),(3,2,'State','State','feather icon-file-plus','state/view',3,'','','','','\0',NULL,NULL,'2020-06-20 10:34:59','0000-00-00 00:00:00'),(4,2,'City','City','feather icon-file-plus','city/view',4,'','','','','\0',NULL,NULL,'2020-06-20 10:34:59','0000-00-00 00:00:00'),(5,2,'Area','Area','feather icon-file-plus','area/view',5,'','','','','\0',NULL,NULL,'2020-06-20 10:34:59','0000-00-00 00:00:00'),(6,2,'Category','Category','feather icon-file-plus','category/view',6,'','','','','\0',NULL,NULL,'2020-06-20 10:34:59','0000-00-00 00:00:00'),(7,2,'Merchant Plan','Merchant Plan','feather icon-file-plus','merchantPlan/view',6,'','','','','\0',NULL,NULL,'2020-06-20 10:34:59','0000-00-00 00:00:00'),(8,2,'Limit Profile','Limit Profile','feather icon-file-plus','limitProfile/view',7,'','','','','\0',NULL,NULL,'2020-06-20 10:34:59','0000-00-00 00:00:00'),(9,3,'User Group','Add User Group','feather icon-file-plus','group/view',1,'','','','','\0',NULL,NULL,'2020-06-20 10:35:12','0000-00-00 00:00:00'),(10,3,'Group Privilege','Add Group Privilege','feather icon-file-plus','user/role/view',2,'','','','','\0',NULL,NULL,'2020-06-20 10:35:12','0000-00-00 00:00:00'),(11,3,'User','Add User','feather icon-file-plus','add/user/view',3,'','','','','\0',NULL,NULL,'2020-06-20 10:35:12','0000-00-00 00:00:00'),(12,4,'Registration','Registration','feather icon-file-plus','merchant/registration/view',1,'','','','','\0',NULL,NULL,'2020-06-20 10:40:01','0000-00-00 00:00:00'),(13,4,'Profile','Profile','feather icon-file-plus','merchant/profile/view',2,'','\0','\0','\0','\0',NULL,NULL,'2020-06-20 10:40:02','0000-00-00 00:00:00'),(14,4,'Employee','Employee','feather icon-file-plus','merchant/employee/view',3,'','','','','\0',NULL,NULL,'2020-06-20 10:40:02','0000-00-00 00:00:00'),(15,4,'Loyalty Cashback','Loyalty Cashback','feather icon-file-plus','merchant/loyaltyCashback/view',4,'','','','','\0',NULL,NULL,'2020-06-20 10:40:02','0000-00-00 00:00:00'),(16,4,'Add Customer','Customer','feather icon-file-plus','customer/view',5,'','','\0','\0','\0',NULL,NULL,'2020-06-20 10:40:02','0000-00-00 00:00:00'),(17,4,'Upgrade Merchant Plan','Upgrade Merchant Plan','feather icon-file-plus','upgrade/merchantPlan/view',6,'','\0','\0','','\0',NULL,NULL,'2020-06-20 10:40:02','0000-00-00 00:00:00'),(18,4,'Transaction','Customer Transaction','feather icon-file-plus','customer/transaction/view',7,'','\0','\0','\0','\0',NULL,NULL,'2020-06-20 10:40:02','0000-00-00 00:00:00'),(19,4,'Merchant Customers','Merchant Customers','feather icon-file-plus','merchant/customer/view',8,'','\0','\0','\0','\0',NULL,NULL,'2020-06-20 10:40:02','0000-00-00 00:00:00'),(20,4,'Transaction History','Merchant Transaction History','feather icon-file-plus','merchant/transactionHistory/view',9,'','\0','\0','\0','\0',NULL,NULL,'2020-06-20 10:40:02','0000-00-00 00:00:00'),(21,5,'Search Store','Search Store','feather icon-file-plus','search/store/view',1,'','\0','\0','\0','\0',NULL,NULL,'2020-06-20 10:40:40','0000-00-00 00:00:00'),(22,5,'Merchant','Customer Merchants','feather icon-file-plus','customer/merchant/view',2,'','\0','\0','\0','\0',NULL,NULL,'2020-06-20 10:40:40','0000-00-00 00:00:00'),(23,5,'Transaction History','Customer Transactions History','feather icon-file-plus','customer/transactionHistory/view',3,'','\0','\0','\0','\0',NULL,NULL,'2020-06-20 10:40:40','0000-00-00 00:00:00'),(24,6,'Search Transaction','Search Transaction','feather icon-file-plus','search/transaction/view',1,'','\0','\0','\0','\0',NULL,NULL,'2020-06-20 10:41:57','0000-00-00 00:00:00'),(25,6,'Search User','Search User','feather icon-file-plus','searchUser/view',2,'','\0','\0','\0','\0',NULL,NULL,'2020-06-20 10:41:57','0000-00-00 00:00:00');
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
  `isEnableFingerprint` bit(1) DEFAULT b'1',
  `lastPassword` varchar(128) DEFAULT NULL,
  `passwordChangedDate` timestamp NOT NULL DEFAULT current_timestamp(),
  `lastLoggedOn` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `lastSessionId` varchar(128) DEFAULT NULL,
  `numUnsuccessfulAttempts` int(11) DEFAULT 0,
  `maxConcurrentLogin` int(11) DEFAULT 0,
  `managerId` varchar(11) DEFAULT NULL,
  `isLocked` bit(1) DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'DazzlePay','SuperAdmin','SuperAdmin','DazzlePay','DazzlePay','PAYMENT','PROCESSING','care@dazzlepay.co.in','$2a$11$3X.7B5WWYdMLKS0FUE/aTOTt8jtlAyAzyz4RExpwRqYnoT9adv/ea',NULL,'','$2a$11$XQeftHn7H.Fh8qqQVkO1UOUGoVYwIkx3EdbS.Qy138KZHnUHQKwb.','2019-10-25 01:10:31','2020-06-22 09:05:21','416404513EE3B21C10B05C8CB8052CA1',0,0,'','\0','',1,1,'1','superAdmin','superAdmin','2019-10-19 14:55:31','2020-06-22 09:05:21'),(2,'DazzlePay','merchant','N1UUGMNVBH',NULL,'Mukesh','','Kumar','mukesh@newsonfloor.com','$2a$11$3X.7B5WWYdMLKS0FUE/aTOTt8jtlAyAzyz4RExpwRqYnoT9adv/ea',NULL,'',NULL,'2020-06-21 15:06:34','2020-06-22 11:20:15','312B500AFF49F99EA7A83C1A62982BDB',0,0,NULL,NULL,'',3,1,'1','',NULL,'2020-06-21 15:06:34','2020-06-22 11:20:15'),(3,'DazzlePay','customer','MKCSE41',NULL,'Mukesh','','Swami','mkcse41@gmail.com','$2a$11$3X.7B5WWYdMLKS0FUE/aTOTt8jtlAyAzyz4RExpwRqYnoT9adv/ea',NULL,'',NULL,'2020-06-21 15:07:48','2020-06-22 10:51:53','3802D628A63F5869313ADC75FC23DD8E',0,0,NULL,NULL,'',2,1,'1','',NULL,'2020-06-21 15:07:48','2020-06-22 10:51:53'),(4,'DazzlePay','merchant','5RXWSMDLYV',NULL,'Mohit','','Tharwan','mohit@gmail.com','$2a$11$3X.7B5WWYdMLKS0FUE/aTOTt8jtlAyAzyz4RExpwRqYnoT9adv/ea',NULL,'',NULL,'2020-06-22 09:07:45','0000-00-00 00:00:00',NULL,0,0,NULL,NULL,'',3,1,'1','',NULL,'2020-06-22 09:07:45','2020-06-22 10:50:52'),(5,'DazzlePay','customer','MHIT92',NULL,'Mohit','','Kumar','Mohit92@gmail.com','$2a$11$3X.7B5WWYdMLKS0FUE/aTOTt8jtlAyAzyz4RExpwRqYnoT9adv/ea',NULL,'',NULL,'2020-06-22 11:12:59','2020-06-22 11:14:48','7FE1C1CE18D3E67551087867D08CAA1D',0,0,NULL,NULL,'',2,1,'1','',NULL,'2020-06-22 11:12:59','2020-06-22 11:14:48');
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
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userRole`
--

LOCK TABLES `userRole` WRITE;
/*!40000 ALTER TABLE `userRole` DISABLE KEYS */;
INSERT INTO `userRole` VALUES (5,'SuperAdmin',1,0,'\0','\0','\0','\0','',1,NULL,'SuperAdmin','2020-06-20 11:05:31','2020-06-20 11:05:31'),(6,'SuperAdmin',2,1,'','','','\0','',1,NULL,'SuperAdmin','2020-06-20 11:05:31','2020-06-20 11:05:31'),(7,'SuperAdmin',2,2,'','','','\0','',1,NULL,'SuperAdmin','2020-06-20 11:05:31','2020-06-20 11:05:31'),(8,'SuperAdmin',2,3,'','','','\0','',1,NULL,'SuperAdmin','2020-06-20 11:05:31','2020-06-20 11:05:31'),(9,'SuperAdmin',2,4,'','','','\0','',1,NULL,'SuperAdmin','2020-06-20 11:05:31','2020-06-20 11:05:31'),(10,'SuperAdmin',2,5,'','','','\0','',1,NULL,'SuperAdmin','2020-06-20 11:05:31','2020-06-20 11:05:31'),(11,'SuperAdmin',2,6,'','','','\0','',1,NULL,'SuperAdmin','2020-06-20 11:05:31','2020-06-20 11:05:31'),(12,'SuperAdmin',2,7,'','','','\0','',1,NULL,'SuperAdmin','2020-06-20 11:05:31','2020-06-20 11:05:31'),(13,'SuperAdmin',2,8,'','','','\0','',1,NULL,'SuperAdmin','2020-06-20 11:05:31','2020-06-20 11:05:31'),(14,'SuperAdmin',3,9,'','','','\0','',1,NULL,'SuperAdmin','2020-06-20 11:05:31','2020-06-20 11:05:31'),(15,'SuperAdmin',3,10,'','','','\0','',1,NULL,'SuperAdmin','2020-06-20 11:05:31','2020-06-20 11:05:31'),(16,'SuperAdmin',3,11,'','','','\0','',1,NULL,'SuperAdmin','2020-06-20 11:05:31','2020-06-20 11:05:31'),(17,'SuperAdmin',4,12,'','','','\0','',1,NULL,'SuperAdmin','2020-06-20 11:05:31','2020-06-20 11:05:31'),(18,'SuperAdmin',4,13,'\0','\0','\0','\0','',1,NULL,'SuperAdmin','2020-06-20 11:05:31','2020-06-20 11:05:31'),(19,'SuperAdmin',4,14,'','','','\0','',1,NULL,'SuperAdmin','2020-06-20 11:05:31','2020-06-20 11:05:31'),(20,'SuperAdmin',4,15,'','','','\0','',1,NULL,'SuperAdmin','2020-06-20 11:05:31','2020-06-20 11:05:31'),(21,'SuperAdmin',4,16,'','\0','\0','\0','',1,NULL,'SuperAdmin','2020-06-20 11:05:31','2020-06-20 11:05:31'),(22,'SuperAdmin',4,17,'\0','\0','','\0','',1,NULL,'SuperAdmin','2020-06-20 11:05:31','2020-06-20 11:05:31'),(23,'SuperAdmin',4,18,'\0','\0','\0','\0','',1,NULL,'SuperAdmin','2020-06-20 11:05:31','2020-06-20 11:05:31'),(24,'SuperAdmin',4,19,'\0','\0','\0','\0','',1,NULL,'SuperAdmin','2020-06-20 11:05:31','2020-06-20 11:05:31'),(25,'SuperAdmin',4,20,'\0','\0','\0','\0','',1,NULL,'SuperAdmin','2020-06-20 11:05:31','2020-06-20 11:05:31'),(26,'SuperAdmin',5,21,'\0','\0','\0','\0','',1,NULL,'SuperAdmin','2020-06-20 11:05:31','2020-06-20 11:05:31'),(27,'SuperAdmin',5,22,'\0','\0','\0','\0','',1,NULL,'SuperAdmin','2020-06-20 11:05:31','2020-06-20 11:05:31'),(28,'SuperAdmin',5,23,'\0','\0','\0','\0','',1,NULL,'SuperAdmin','2020-06-20 11:05:31','2020-06-20 11:05:31'),(29,'SuperAdmin',6,24,'\0','\0','\0','\0','',1,NULL,'SuperAdmin','2020-06-20 11:05:32','2020-06-20 11:05:32'),(30,'SuperAdmin',6,25,'\0','\0','\0','\0','',1,NULL,'SuperAdmin','2020-06-20 11:05:32','2020-06-20 11:05:32'),(31,'customer',1,0,'\0','\0','\0','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:11:41','2020-06-20 11:11:41'),(32,'customer',5,21,'\0','\0','\0','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:11:42','2020-06-20 11:11:42'),(33,'customer',5,22,'\0','\0','\0','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:11:42','2020-06-20 11:11:42'),(34,'customer',5,23,'\0','\0','\0','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:11:42','2020-06-20 11:11:42'),(35,'merchant',1,0,'\0','\0','\0','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:12:30','2020-06-20 11:12:30'),(36,'merchant',4,13,'\0','\0','\0','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:12:30','2020-06-20 11:12:30'),(37,'merchant',4,14,'','','','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:12:30','2020-06-20 11:12:30'),(38,'merchant',4,15,'','','','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:12:30','2020-06-20 11:12:30'),(39,'merchant',4,16,'','\0','\0','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:12:30','2020-06-20 11:12:30'),(40,'merchant',4,18,'\0','\0','\0','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:12:30','2020-06-20 11:12:30'),(41,'merchant',4,20,'\0','\0','\0','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:12:30','2020-06-20 11:12:30'),(42,'admin',1,0,'\0','\0','\0','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:13:56','2020-06-20 11:13:56'),(43,'admin',2,2,'','','','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:13:56','2020-06-20 11:13:56'),(44,'admin',2,3,'','','','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:13:56','2020-06-20 11:13:56'),(45,'admin',2,4,'','','','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:13:56','2020-06-20 11:13:56'),(46,'admin',2,5,'','','','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:13:56','2020-06-20 11:13:56'),(47,'admin',2,6,'','','','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:13:56','2020-06-20 11:13:56'),(48,'admin',2,7,'','','','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:13:56','2020-06-20 11:13:56'),(49,'admin',2,8,'','','','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:13:56','2020-06-20 11:13:56'),(50,'admin',3,9,'','','','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:13:57','2020-06-20 11:13:57'),(51,'admin',3,10,'','','','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:13:57','2020-06-20 11:13:57'),(52,'admin',3,11,'','','','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:13:57','2020-06-20 11:13:57'),(53,'admin',4,12,'\0','\0','\0','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:13:57','2020-06-20 11:13:57'),(54,'admin',4,13,'\0','\0','\0','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:13:57','2020-06-20 11:13:57'),(55,'admin',4,17,'\0','\0','\0','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:13:57','2020-06-20 11:13:57'),(56,'admin',4,19,'\0','\0','\0','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:13:57','2020-06-20 11:13:57'),(57,'admin',4,20,'\0','\0','\0','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:13:57','2020-06-20 11:13:57'),(58,'admin',5,21,'\0','\0','\0','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:13:57','2020-06-20 11:13:57'),(59,'admin',5,22,'\0','\0','\0','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:13:57','2020-06-20 11:13:57'),(60,'admin',5,23,'\0','\0','\0','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:13:57','2020-06-20 11:13:57'),(61,'admin',6,24,'\0','\0','\0','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:13:57','2020-06-20 11:13:57'),(62,'admin',6,25,'\0','\0','\0','\0','',1,'SuperAdmin',NULL,'2020-06-20 11:13:57','2020-06-20 11:13:57');
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_history`
--

LOCK TABLES `user_history` WRITE;
/*!40000 ALTER TABLE `user_history` DISABLE KEYS */;
INSERT INTO `user_history` VALUES (1,'SuperAdmin','2020-02-12 12:47:46','2020-06-22 09:05:21'),(2,'N1UUGMNVBH','2020-06-22 10:51:07','2020-06-22 11:20:15'),(3,'MKCSE41','2020-06-22 10:51:53','2020-06-22 10:51:53'),(4,'MHIT92','2020-06-22 11:13:18','2020-06-22 11:14:49');
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
) ENGINE=InnoDB AUTO_INCREMENT=302 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_history_detail`
--

LOCK TABLES `user_history_detail` WRITE;
/*!40000 ALTER TABLE `user_history_detail` DISABLE KEYS */;
INSERT INTO `user_history_detail` VALUES (1,'SuperAdmin','/mpls/add/user/profile','D27651F56E84FAD5924B937DA2B8D141','web','192.168.1.61','Linux','Chrome','2020-02-12 12:47:46'),(2,'SuperAdmin','/mpls/add/user/profile','6F2C953D8234BF91910F250B0AFDAA54','web','192.168.1.61','Linux','Chrome','2020-02-12 12:56:44'),(3,'SuperAdmin','/mpls/add/user/profile','14D4EB80E09CF6DD7B360158685F8041','web','192.168.1.61','Linux','Chrome','2020-02-12 12:59:10'),(4,'SuperAdmin','/mpls/user/role/detail/SuperAdmin','14D4EB80E09CF6DD7B360158685F8041','web','192.168.1.61','Linux','Chrome','2020-02-12 12:59:32'),(5,'SuperAdmin','/mpls/country/view','14D4EB80E09CF6DD7B360158685F8041','web','192.168.1.61','Linux','Chrome','2020-02-12 12:59:41'),(6,'SuperAdmin','/mpls/user/logout.htm','14D4EB80E09CF6DD7B360158685F8041','web','192.168.1.61','Linux','Chrome','2020-02-12 13:00:26'),(7,'SuperAdmin','/mpls/add/user/profile','064752AB6CC74A040629D1D9286A3FD6','web','192.168.1.53','Windows 10','Chrome','2020-02-13 05:32:22'),(8,'SuperAdmin','/mpls/currency/view','064752AB6CC74A040629D1D9286A3FD6','web','192.168.1.53','Windows 10','Chrome','2020-02-13 05:32:32'),(9,'SuperAdmin','/mpls/country/view','064752AB6CC74A040629D1D9286A3FD6','web','192.168.1.53','Windows 10','Chrome','2020-02-13 05:32:49'),(10,'SuperAdmin','/mpls/country/update','064752AB6CC74A040629D1D9286A3FD6','web','192.168.1.53','Windows 10','Chrome','2020-02-13 05:33:12'),(11,'SuperAdmin','/mpls/sysParameter/view','064752AB6CC74A040629D1D9286A3FD6','web','192.168.1.53','Windows 10','Chrome','2020-02-13 05:33:19'),(12,'SuperAdmin','/mpls/add/user/profile','1BEBFC136AA4A7A62F352CF8B007F4CC','web','192.168.1.53','Windows 10','Chrome','2020-02-13 05:47:07'),(13,'SuperAdmin','/mpls/currency/view','1BEBFC136AA4A7A62F352CF8B007F4CC','web','192.168.1.53','Windows 10','Chrome','2020-02-13 05:47:09'),(14,'SuperAdmin','/mpls/add/user/profile','610EA6F96143D26DA4256C656F10A7D9','web','192.168.1.56','Windows 10','Chrome','2020-02-13 05:49:51'),(15,'SuperAdmin','/mpls/sysParameter/view','610EA6F96143D26DA4256C656F10A7D9','web','192.168.1.56','Windows 10','Chrome','2020-02-13 05:49:57'),(16,'SuperAdmin','/mpls/country/view','610EA6F96143D26DA4256C656F10A7D9','web','192.168.1.56','Windows 10','Chrome','2020-02-13 05:49:59'),(17,'SuperAdmin','/mpls/currency/view','610EA6F96143D26DA4256C656F10A7D9','web','192.168.1.56','Windows 10','Chrome','2020-02-13 05:50:04'),(18,'SuperAdmin','/mpls/add/user/profile','3F37620AFBC98D78570BB8933CCE2291','web','192.168.1.53','Windows 10','Chrome','2020-02-13 05:55:26'),(19,'SuperAdmin','/mpls/currency/view','3F37620AFBC98D78570BB8933CCE2291','web','192.168.1.53','Windows 10','Chrome','2020-02-13 05:55:27'),(20,'SuperAdmin','/mpls/country/view','3F37620AFBC98D78570BB8933CCE2291','web','192.168.1.53','Windows 10','Chrome','2020-02-13 05:55:31'),(21,'SuperAdmin','/mpls/add/user/profile','22C412F717A37898943DB81B0F99D149','web','192.168.1.53','Windows 10','Chrome','2020-02-13 06:06:41'),(22,'SuperAdmin','/mpls/currency/view','22C412F717A37898943DB81B0F99D149','web','192.168.1.53','Windows 10','Chrome','2020-02-13 06:06:43'),(23,'SuperAdmin','/mpls/country/view','22C412F717A37898943DB81B0F99D149','web','192.168.1.53','Windows 10','Chrome','2020-02-13 06:06:47'),(24,'SuperAdmin','/mpls/add/user/profile','A7856C16DAF170BE7BB9422710A729B5','web','192.168.1.53','Windows 10','Chrome','2020-02-13 06:18:21'),(25,'SuperAdmin','/mpls/sysParameter/view','A7856C16DAF170BE7BB9422710A729B5','web','192.168.1.53','Windows 10','Chrome','2020-02-13 06:19:59'),(26,'SuperAdmin','/mpls/user/logout.htm','A7856C16DAF170BE7BB9422710A729B5','web','192.168.1.53','Windows 10','Chrome','2020-02-13 06:22:09'),(27,'SuperAdmin','/mpls/add/user/profile','606076625E8FC70C5CB43D47F778236C','web','192.168.1.53','Windows 10','Chrome','2020-02-13 06:22:11'),(28,'SuperAdmin','/mpls/add/user/profile','02FD6F23F1544A534D8D7B8BAF7859AA','web','192.168.1.53','Windows 10','Chrome','2020-02-13 08:00:08'),(29,'SuperAdmin','/mpls/city/view','02FD6F23F1544A534D8D7B8BAF7859AA','web','192.168.1.53','Windows 10','Chrome','2020-02-13 08:00:13'),(30,'SuperAdmin','/mpls/add/user/profile','0C53BF65383D9B9232E577BFED2C17E4','web','192.168.1.53','Windows 10','Chrome','2020-02-13 08:02:36'),(31,'SuperAdmin','/mpls/city/view','0C53BF65383D9B9232E577BFED2C17E4','web','192.168.1.53','Windows 10','Chrome','2020-02-13 08:02:41'),(32,'SuperAdmin','/mpls/add/user/profile','F2AE8A3CDBC325D57C0DB314492E546B','web','192.168.1.53','Windows 10','Chrome','2020-02-13 08:07:15'),(33,'SuperAdmin','/mpls/city/view','F2AE8A3CDBC325D57C0DB314492E546B','web','192.168.1.53','Windows 10','Chrome','2020-02-13 08:07:17'),(34,'SuperAdmin','/mpls/add/user/view','F2AE8A3CDBC325D57C0DB314492E546B','web','192.168.1.53','Windows 10','Chrome','2020-02-13 08:12:44'),(35,'SuperAdmin','/mpls/group/view','F2AE8A3CDBC325D57C0DB314492E546B','web','192.168.1.53','Windows 10','Chrome','2020-02-13 08:13:08'),(36,'SuperAdmin','/mpls/add/user/profile','5BA3F3614110436B51B8807BD876442B','web','192.168.1.56','Windows 10','Chrome','2020-02-13 08:27:41'),(37,'SuperAdmin','/mpls/category/view','5BA3F3614110436B51B8807BD876442B','web','192.168.1.56','Windows 10','Chrome','2020-02-13 08:27:48'),(38,'SuperAdmin','/mpls/add/user/profile','510B21C32FA5583CFA7E69E481F00C3E','web','192.168.1.56','Windows 10','Chrome','2020-02-13 08:36:06'),(39,'SuperAdmin','/mpls/category/view','510B21C32FA5583CFA7E69E481F00C3E','web','192.168.1.56','Windows 10','Chrome','2020-02-13 08:36:09'),(40,'SuperAdmin','/mpls/category/add','510B21C32FA5583CFA7E69E481F00C3E','web','192.168.1.56','Windows 10','Chrome','2020-02-13 08:39:11'),(41,'SuperAdmin','/mpls/category/delete','510B21C32FA5583CFA7E69E481F00C3E','web','192.168.1.56','Windows 10','Chrome','2020-02-13 08:40:08'),(42,'SuperAdmin','/mpls/user/logout.htm','510B21C32FA5583CFA7E69E481F00C3E','web','192.168.1.56','Windows 10','Chrome','2020-02-13 08:43:15'),(43,'SuperAdmin','/mpls/add/user/profile','FCE33976F2962F71B4D1001A0F5D69A1','web','192.168.1.56','Windows 10','Chrome','2020-02-13 08:43:16'),(44,'SuperAdmin','/mpls/category/view','FCE33976F2962F71B4D1001A0F5D69A1','web','192.168.1.56','Windows 10','Chrome','2020-02-13 08:43:17'),(45,'SuperAdmin','/mpls/category/update','FCE33976F2962F71B4D1001A0F5D69A1','web','192.168.1.56','Windows 10','Chrome','2020-02-13 08:43:19'),(46,'SuperAdmin','/mpls/user/logout.htm','FCE33976F2962F71B4D1001A0F5D69A1','web','192.168.1.56','Windows 10','Chrome','2020-02-13 08:43:59'),(47,'SuperAdmin','/mpls/add/user/profile','8FA86CB4724339E82C9720D3F1F2DCDA','web','192.168.1.56','Windows 10','Chrome','2020-02-13 08:44:01'),(48,'SuperAdmin','/mpls/category/view','8FA86CB4724339E82C9720D3F1F2DCDA','web','192.168.1.56','Windows 10','Chrome','2020-02-13 08:44:03'),(49,'SuperAdmin','/mpls/category/update','8FA86CB4724339E82C9720D3F1F2DCDA','web','192.168.1.56','Windows 10','Chrome','2020-02-13 08:44:05'),(50,'SuperAdmin','/mpls/category/edit','8FA86CB4724339E82C9720D3F1F2DCDA','web','192.168.1.56','Windows 10','Chrome','2020-02-13 08:44:10'),(51,'SuperAdmin','/mpls/currency/view','8FA86CB4724339E82C9720D3F1F2DCDA','web','192.168.1.56','Windows 10','Chrome','2020-02-13 08:46:49'),(52,'SuperAdmin','/mpls/add/user/profile','2B71830764B2279DAAFF0EE568934E97','web','192.168.1.56','Windows 10','Chrome','2020-02-13 09:11:27'),(53,'SuperAdmin','/mpls/category/view','2B71830764B2279DAAFF0EE568934E97','web','192.168.1.56','Windows 10','Chrome','2020-02-13 09:11:29'),(54,'SuperAdmin','/mpls/add/user/profile','1ABC646F51AF00248DBB1F6E71747A16','web','192.168.1.53','Windows 10','Chrome','2020-02-13 10:01:02'),(55,'SuperAdmin','/mpls/city/view','1ABC646F51AF00248DBB1F6E71747A16','web','192.168.1.53','Windows 10','Chrome','2020-02-13 10:01:05'),(56,'SuperAdmin','/mpls/add/user/profile','E0F7BC334CEF4B8982BFA595CF7AC786','web','192.168.1.56','Windows 10','Chrome','2020-02-13 10:20:25'),(57,'SuperAdmin','/mpls/category/view','E0F7BC334CEF4B8982BFA595CF7AC786','web','192.168.1.56','Windows 10','Chrome','2020-02-13 10:20:26'),(58,'SuperAdmin','/mpls/category/update','E0F7BC334CEF4B8982BFA595CF7AC786','web','192.168.1.56','Windows 10','Chrome','2020-02-13 10:22:59'),(59,'SuperAdmin','/mpls/add/user/profile','2A7176191CC5B97E2A009127F3C1BFBC','web','192.168.1.53','Windows 10','Chrome','2020-02-13 10:30:23'),(60,'SuperAdmin','/mpls/city/view','2A7176191CC5B97E2A009127F3C1BFBC','web','192.168.1.53','Windows 10','Chrome','2020-02-13 10:30:27'),(61,'SuperAdmin','/mpls/add/user/profile','D0DECEEF9D78ADEFA939821522E56DE3','web','192.168.1.53','Windows 10','Chrome','2020-02-13 10:45:04'),(62,'SuperAdmin','/mpls/user/role/view','D0DECEEF9D78ADEFA939821522E56DE3','web','192.168.1.53','Windows 10','Chrome','2020-02-13 10:45:08'),(63,'SuperAdmin','/mpls/city/view','D0DECEEF9D78ADEFA939821522E56DE3','web','192.168.1.53','Windows 10','Chrome','2020-02-13 10:45:42'),(64,'SuperAdmin','/mpls/currency/view','D0DECEEF9D78ADEFA939821522E56DE3','web','192.168.1.53','Windows 10','Chrome','2020-02-13 10:45:48'),(65,'SuperAdmin','/mpls/','D0DECEEF9D78ADEFA939821522E56DE3','web','192.168.1.53','Windows 10','Chrome','2020-02-13 10:46:37'),(66,'SuperAdmin','/mpls/add/user/profile','F25B5F1555F536EE8E972DBF3A157E66','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:24:10'),(67,'SuperAdmin','/mpls/city/view','F25B5F1555F536EE8E972DBF3A157E66','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:24:13'),(68,'SuperAdmin','/mpls/city/update','F25B5F1555F536EE8E972DBF3A157E66','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:24:28'),(69,'SuperAdmin','/mpls/user/logout.htm','F25B5F1555F536EE8E972DBF3A157E66','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:29:26'),(70,'SuperAdmin','/mpls/add/user/profile','F9687089B858E06BC0BBFB8EBED92B08','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:29:27'),(71,'SuperAdmin','/mpls/user/logout.htm','F9687089B858E06BC0BBFB8EBED92B08','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:30:07'),(72,'SuperAdmin','/mpls/add/user/profile','6DE936F0AD3F6B2D8CF288AD03E17554','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:30:10'),(73,'SuperAdmin','/mpls/city/view','6DE936F0AD3F6B2D8CF288AD03E17554','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:30:12'),(74,'SuperAdmin','/mpls/city/update','6DE936F0AD3F6B2D8CF288AD03E17554','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:30:15'),(75,'SuperAdmin','/mpls/add/user/profile','D1A953F02AE7F470CEBEAADA1C011042','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:34:02'),(76,'SuperAdmin','/mpls/city/view','D1A953F02AE7F470CEBEAADA1C011042','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:34:04'),(77,'SuperAdmin','/mpls/city/update','D1A953F02AE7F470CEBEAADA1C011042','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:34:10'),(78,'SuperAdmin','/mpls/currency/view','D1A953F02AE7F470CEBEAADA1C011042','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:35:03'),(79,'SuperAdmin','/mpls/add/user/profile','64AB48B0B2EE6EC5357F2EA3346036EE','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:50:14'),(80,'SuperAdmin','/mpls/city/view','64AB48B0B2EE6EC5357F2EA3346036EE','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:50:16'),(81,'SuperAdmin','/mpls/city/update','64AB48B0B2EE6EC5357F2EA3346036EE','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:50:19'),(82,'SuperAdmin','/mpls/add/user/profile','CB76D85412F2FCE2E46C9B98CCD421B1','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:53:40'),(83,'SuperAdmin','/mpls/city/view','CB76D85412F2FCE2E46C9B98CCD421B1','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:53:47'),(84,'SuperAdmin','/mpls/city/update','CB76D85412F2FCE2E46C9B98CCD421B1','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:54:24'),(85,'SuperAdmin','/mpls/user/logout.htm','CB76D85412F2FCE2E46C9B98CCD421B1','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:56:02'),(86,'SuperAdmin','/mpls/add/user/profile','E5B51A837BDBDA2CB17B6A560D5C3E9D','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:56:04'),(87,'SuperAdmin','/mpls/city/view','E5B51A837BDBDA2CB17B6A560D5C3E9D','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:56:05'),(88,'SuperAdmin','/mpls/city/update','E5B51A837BDBDA2CB17B6A560D5C3E9D','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:56:08'),(89,'SuperAdmin','/mpls/user/logout.htm','E5B51A837BDBDA2CB17B6A560D5C3E9D','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:59:01'),(90,'SuperAdmin','/mpls/add/user/profile','C9E4920F4CF8C6B2ADD5C11BBEC510DC','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:59:02'),(91,'SuperAdmin','/mpls/country/view','C9E4920F4CF8C6B2ADD5C11BBEC510DC','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:59:04'),(92,'SuperAdmin','/mpls/country/update','C9E4920F4CF8C6B2ADD5C11BBEC510DC','web','192.168.1.53','Windows 10','Chrome','2020-02-13 11:59:08'),(93,'SuperAdmin','/mpls/city/view','C9E4920F4CF8C6B2ADD5C11BBEC510DC','web','192.168.1.53','Windows 10','Chrome','2020-02-13 12:01:13'),(94,'SuperAdmin','/mpls/city/update','C9E4920F4CF8C6B2ADD5C11BBEC510DC','web','192.168.1.53','Windows 10','Chrome','2020-02-13 12:01:16'),(95,'SuperAdmin','/mpls/user/logout.htm','C9E4920F4CF8C6B2ADD5C11BBEC510DC','web','192.168.1.53','Windows 10','Chrome','2020-02-13 12:01:36'),(96,'SuperAdmin','/mpls/add/user/profile','BDD1FFC0AB5AE96C70EAD28BD4BBB451','web','192.168.1.53','Windows 10','Chrome','2020-02-13 12:01:38'),(97,'SuperAdmin','/mpls/city/view','BDD1FFC0AB5AE96C70EAD28BD4BBB451','web','192.168.1.53','Windows 10','Chrome','2020-02-13 12:01:40'),(98,'SuperAdmin','/mpls/city/update','BDD1FFC0AB5AE96C70EAD28BD4BBB451','web','192.168.1.53','Windows 10','Chrome','2020-02-13 12:01:42'),(99,'SuperAdmin','/mpls/user/logout.htm','BDD1FFC0AB5AE96C70EAD28BD4BBB451','web','192.168.1.53','Windows 10','Chrome','2020-02-13 12:03:02'),(100,'SuperAdmin','/mpls/add/user/profile','F10391AF4D7941B0EEC1EEB06053D7C6','web','192.168.1.53','Windows 10','Chrome','2020-02-13 12:03:07'),(101,'SuperAdmin','/mpls/city/view','F10391AF4D7941B0EEC1EEB06053D7C6','web','192.168.1.53','Windows 10','Chrome','2020-02-13 12:03:08'),(102,'SuperAdmin','/mpls/city/update','F10391AF4D7941B0EEC1EEB06053D7C6','web','192.168.1.53','Windows 10','Chrome','2020-02-13 12:03:11'),(103,'SuperAdmin','/mpls/add/user/profile','4E60E337247715D426A07991136138DD','web','192.168.1.53','Windows 10','Chrome','2020-02-13 12:03:58'),(104,'SuperAdmin','/mpls/city/view','4E60E337247715D426A07991136138DD','web','192.168.1.53','Windows 10','Chrome','2020-02-13 12:04:00'),(105,'SuperAdmin','/mpls/city/update','4E60E337247715D426A07991136138DD','web','192.168.1.53','Windows 10','Chrome','2020-02-13 12:04:18'),(106,'SuperAdmin','/mpls/add/user/profile','33B391DD4A5BCC3EB52F5CC02FC881B0','web','192.168.1.53','Windows 10','Chrome','2020-02-13 12:18:55'),(107,'SuperAdmin','/mpls/city/view','33B391DD4A5BCC3EB52F5CC02FC881B0','web','192.168.1.53','Windows 10','Chrome','2020-02-13 12:19:00'),(108,'SuperAdmin','/mpls/city/update','33B391DD4A5BCC3EB52F5CC02FC881B0','web','192.168.1.53','Windows 10','Chrome','2020-02-13 12:19:15'),(109,'SuperAdmin','/mpls/add/user/profile','419BAC73D03170784A6DC9B04974BE79','web','192.168.2.5','Linux','Chrome','2020-02-26 11:37:07'),(110,'SuperAdmin','/mpls/category/view','419BAC73D03170784A6DC9B04974BE79','web','192.168.2.5','Linux','Chrome','2020-02-26 11:37:12'),(111,'SuperAdmin','/mpls/area/view','419BAC73D03170784A6DC9B04974BE79','web','192.168.2.5','Linux','Chrome','2020-02-26 11:37:13'),(112,'SuperAdmin','/mpls/city/view','419BAC73D03170784A6DC9B04974BE79','web','192.168.2.5','Linux','Chrome','2020-02-26 11:37:14'),(113,'SuperAdmin','/mpls/state/view','419BAC73D03170784A6DC9B04974BE79','web','192.168.2.5','Linux','Chrome','2020-02-26 11:37:14'),(114,'SuperAdmin','/mpls/currency/view','419BAC73D03170784A6DC9B04974BE79','web','192.168.2.5','Linux','Chrome','2020-02-26 11:37:15'),(115,'SuperAdmin','/mpls/country/view','419BAC73D03170784A6DC9B04974BE79','web','192.168.2.5','Linux','Chrome','2020-02-26 11:37:15'),(116,'SuperAdmin','/mpls/group/view','419BAC73D03170784A6DC9B04974BE79','web','192.168.2.5','Linux','Chrome','2020-02-26 11:37:27'),(117,'SuperAdmin','/mpls/user/role/view','419BAC73D03170784A6DC9B04974BE79','web','192.168.2.5','Linux','Chrome','2020-02-26 11:37:30'),(118,'SuperAdmin','/mpls/user/role/update','419BAC73D03170784A6DC9B04974BE79','web','192.168.2.5','Linux','Chrome','2020-02-26 11:37:35'),(119,'SuperAdmin','/mpls/rest/ajax/getGroupInfo/SuperAdmin','419BAC73D03170784A6DC9B04974BE79','web','192.168.2.5','Linux','Chrome','2020-02-26 11:37:35'),(120,'SuperAdmin','/mpls/user/role/edit','419BAC73D03170784A6DC9B04974BE79','web','192.168.2.5','Linux','Chrome','2020-02-26 11:37:58'),(121,'SuperAdmin','/mpls/rest/ajax/getGroupInfo/customer','419BAC73D03170784A6DC9B04974BE79','web','192.168.2.5','Linux','Chrome','2020-02-26 11:38:04'),(122,'SuperAdmin','/mpls/user/role/add','419BAC73D03170784A6DC9B04974BE79','web','192.168.2.5','Linux','Chrome','2020-02-26 11:38:10'),(123,'SuperAdmin','/mpls/rest/ajax/getGroupInfo/merchant','419BAC73D03170784A6DC9B04974BE79','web','192.168.2.5','Linux','Chrome','2020-02-26 11:38:24'),(124,'SuperAdmin','/mpls/rest/ajax/getGroupInfo/admin','419BAC73D03170784A6DC9B04974BE79','web','192.168.2.5','Linux','Chrome','2020-02-26 11:39:15'),(125,'SuperAdmin','/mpls/user/role/detail/admin','419BAC73D03170784A6DC9B04974BE79','web','192.168.2.5','Linux','Chrome','2020-02-26 11:40:06'),(126,'SuperAdmin','/mpls/merchant/dashboard/view','7DBBB55E2E53185DB274C9710554BA8E','web','192.168.2.6','Linux','Chrome 8','2020-06-20 10:57:15'),(127,'SuperAdmin','/mpls/','7DBBB55E2E53185DB274C9710554BA8E','web','192.168.2.6','Linux','Chrome 8','2020-06-20 10:57:18'),(128,'SuperAdmin','/mpls/merchant/dashboard/view','246F766B07BE2E5048EE46FC7A4DFC00','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:00:07'),(129,'SuperAdmin','/mpls/','246F766B07BE2E5048EE46FC7A4DFC00','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:00:10'),(130,'SuperAdmin','/mpls/dashboard','246F766B07BE2E5048EE46FC7A4DFC00','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:00:14'),(131,'SuperAdmin','/mpls/merchant/dashboard/view','2C1952C9379C90335891AC7CD6974C99','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:02:26'),(132,'SuperAdmin','/mpls/merchant/dashboard/view','A5C629C5B52C0CA3EB3CB23E2E8067F1','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:03:40'),(133,'SuperAdmin','/mpls/user/role/view','A5C629C5B52C0CA3EB3CB23E2E8067F1','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:03:54'),(134,'SuperAdmin','/mpls/user/role/update','A5C629C5B52C0CA3EB3CB23E2E8067F1','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:03:59'),(135,'SuperAdmin','/mpls/rest/ajax/getGroupInfo/SuperAdmin','A5C629C5B52C0CA3EB3CB23E2E8067F1','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:03:59'),(136,'SuperAdmin','/mpls/user/role/edit','A5C629C5B52C0CA3EB3CB23E2E8067F1','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:05:31'),(137,'SuperAdmin','/mpls/user/role/detail/SuperAdmin','A5C629C5B52C0CA3EB3CB23E2E8067F1','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:05:42'),(138,'SuperAdmin','/mpls/user/logout.htm','A5C629C5B52C0CA3EB3CB23E2E8067F1','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:05:47'),(139,'SuperAdmin','/mpls/merchant/dashboard/view','969D9D7CA5AAEB0F7BBBC9F0C0B8E70B','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:06:51'),(140,'SuperAdmin','/mpls/user/role/view','969D9D7CA5AAEB0F7BBBC9F0C0B8E70B','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:06:53'),(141,'SuperAdmin','/mpls/rest/ajax/getGroupInfo/customer','969D9D7CA5AAEB0F7BBBC9F0C0B8E70B','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:11:25'),(142,'SuperAdmin','/mpls/user/role/add','969D9D7CA5AAEB0F7BBBC9F0C0B8E70B','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:11:41'),(143,'SuperAdmin','/mpls/rest/ajax/getGroupInfo/merchant','969D9D7CA5AAEB0F7BBBC9F0C0B8E70B','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:11:44'),(144,'SuperAdmin','/mpls/rest/ajax/getGroupInfo/admin','969D9D7CA5AAEB0F7BBBC9F0C0B8E70B','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:12:51'),(145,'SuperAdmin','/mpls/user/role/update','969D9D7CA5AAEB0F7BBBC9F0C0B8E70B','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:14:57'),(146,'SuperAdmin','/mpls/user/logout.htm','969D9D7CA5AAEB0F7BBBC9F0C0B8E70B','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:15:01'),(147,'SuperAdmin','/mpls/merchant/dashboard/view','1F7EF7DA87D83C7C53437053CFD377AD','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:15:04'),(148,'SuperAdmin','/mpls/customer/transaction/view','1F7EF7DA87D83C7C53437053CFD377AD','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:15:22'),(149,'SuperAdmin','/mpls/merchant/customer/view','1F7EF7DA87D83C7C53437053CFD377AD','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:15:26'),(150,'SuperAdmin','/mpls/merchant/transactionHistory/view','1F7EF7DA87D83C7C53437053CFD377AD','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:15:31'),(151,'SuperAdmin','/mpls/sysParameter/view','1F7EF7DA87D83C7C53437053CFD377AD','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:19:16'),(152,'SuperAdmin','/mpls/country/view','1F7EF7DA87D83C7C53437053CFD377AD','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:19:19'),(153,'SuperAdmin','/mpls/state/view','1F7EF7DA87D83C7C53437053CFD377AD','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:19:20'),(154,'SuperAdmin','/mpls/area/view','1F7EF7DA87D83C7C53437053CFD377AD','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:19:21'),(155,'SuperAdmin','/mpls/category/view','1F7EF7DA87D83C7C53437053CFD377AD','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:19:22'),(156,'SuperAdmin','/mpls/merchantPlan/view','1F7EF7DA87D83C7C53437053CFD377AD','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:19:23'),(157,'SuperAdmin','/mpls/merchant/dashboard/view','701D441012C71BF1820A30876C1358EF','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:26:59'),(158,'SuperAdmin','/mpls/merchantPlan/view','701D441012C71BF1820A30876C1358EF','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:27:06'),(159,'SuperAdmin','/mpls/merchant/dashboard/view','16D42649F5F55192B2C503C11033581C','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:30:29'),(160,'SuperAdmin','/mpls/merchantPlan/view','16D42649F5F55192B2C503C11033581C','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:30:35'),(161,'SuperAdmin','/mpls/merchantPlan/update','16D42649F5F55192B2C503C11033581C','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:30:46'),(162,'SuperAdmin','/mpls/limitProfile/view','16D42649F5F55192B2C503C11033581C','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:32:51'),(163,'SuperAdmin','/mpls/rest/ajax/getDurationType','16D42649F5F55192B2C503C11033581C','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:33:10'),(164,'SuperAdmin','/mpls/rest/ajax/getTransactionType','16D42649F5F55192B2C503C11033581C','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:33:10'),(165,'SuperAdmin','/mpls/limitProfile/add','16D42649F5F55192B2C503C11033581C','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:34:40'),(166,'SuperAdmin','/mpls/limitProfile/detail/1','16D42649F5F55192B2C503C11033581C','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:41:58'),(167,'SuperAdmin','/mpls/limitProfile/detail/Merchant%20Basic%20Plan','16D42649F5F55192B2C503C11033581C','web','192.168.2.6','Linux','Chrome 8','2020-06-20 11:58:12'),(168,'SuperAdmin','/mpls/merchant/dashboard/view','A5A807B031E0C77315B2D59A5D4C6E5C','web','192.168.2.6','Linux','Chrome 8','2020-06-20 12:05:41'),(169,'SuperAdmin','/mpls/limitProfile/view','A5A807B031E0C77315B2D59A5D4C6E5C','web','192.168.2.6','Linux','Chrome 8','2020-06-20 12:05:48'),(170,'SuperAdmin','/mpls/limitProfile/add','A5A807B031E0C77315B2D59A5D4C6E5C','web','192.168.2.6','Linux','Chrome 8','2020-06-20 12:06:17'),(171,'SuperAdmin','/mpls/limitProfile/detail/Customer%20Limit','A5A807B031E0C77315B2D59A5D4C6E5C','web','192.168.2.6','Linux','Chrome 8','2020-06-20 12:06:29'),(172,'SuperAdmin','/mpls/limitProfile/update','A5A807B031E0C77315B2D59A5D4C6E5C','web','192.168.2.6','Linux','Chrome 8','2020-06-20 12:07:03'),(173,'SuperAdmin','/mpls/rest/ajax/getDurationType','A5A807B031E0C77315B2D59A5D4C6E5C','web','192.168.2.6','Linux','Chrome 8','2020-06-20 12:07:08'),(174,'SuperAdmin','/mpls/rest/ajax/getTransactionType','A5A807B031E0C77315B2D59A5D4C6E5C','web','192.168.2.6','Linux','Chrome 8','2020-06-20 12:07:08'),(175,'SuperAdmin','/mpls/limitProfile/edit','A5A807B031E0C77315B2D59A5D4C6E5C','web','192.168.2.6','Linux','Chrome 8','2020-06-20 12:07:39'),(176,'SuperAdmin','/mpls/merchant/dashboard/view','370DF49ABA2BE691CEBDBBB3DDFE7B5A','web','192.168.2.6','Linux','Chrome 8','2020-06-20 12:08:38'),(177,'SuperAdmin','/mpls/limitProfile/view','370DF49ABA2BE691CEBDBBB3DDFE7B5A','web','192.168.2.6','Linux','Chrome 8','2020-06-20 12:08:47'),(178,'SuperAdmin','/mpls/limitProfile/detail/Customer%20Limit','370DF49ABA2BE691CEBDBBB3DDFE7B5A','web','192.168.2.6','Linux','Chrome 8','2020-06-20 12:08:55'),(179,'SuperAdmin','/mpls/limitProfile/detail/2','370DF49ABA2BE691CEBDBBB3DDFE7B5A','web','192.168.2.6','Linux','Chrome 8','2020-06-20 12:09:42'),(180,'SuperAdmin','/mpls/rest/ajax/getDurationType','370DF49ABA2BE691CEBDBBB3DDFE7B5A','web','192.168.2.6','Linux','Chrome 8','2020-06-20 12:20:22'),(181,'SuperAdmin','/mpls/rest/ajax/getTransactionType','370DF49ABA2BE691CEBDBBB3DDFE7B5A','web','192.168.2.6','Linux','Chrome 8','2020-06-20 12:20:22'),(182,'SuperAdmin','/mpls/merchant/dashboard/view','957E3C169BD05AA6940C291E4D3E6AC8','web','192.168.2.6','Linux','Chrome 8','2020-06-20 12:59:58'),(183,'SuperAdmin','/mpls/limitProfile/view','957E3C169BD05AA6940C291E4D3E6AC8','web','192.168.2.6','Linux','Chrome 8','2020-06-20 13:00:01'),(184,'SuperAdmin','/mpls/rest/ajax/getDurationType','957E3C169BD05AA6940C291E4D3E6AC8','web','192.168.2.6','Linux','Chrome 8','2020-06-20 13:00:07'),(185,'SuperAdmin','/mpls/rest/ajax/getTransactionType','957E3C169BD05AA6940C291E4D3E6AC8','web','192.168.2.6','Linux','Chrome 8','2020-06-20 13:00:07'),(186,'SuperAdmin','/mpls/limitProfile/detail/2','957E3C169BD05AA6940C291E4D3E6AC8','web','192.168.2.6','Linux','Chrome 8','2020-06-20 13:13:49'),(187,'SuperAdmin','/mpls/limitProfile/update','957E3C169BD05AA6940C291E4D3E6AC8','web','192.168.2.6','Linux','Chrome 8','2020-06-20 13:13:52'),(188,'SuperAdmin','/mpls/limitProfile/edit','957E3C169BD05AA6940C291E4D3E6AC8','web','192.168.2.6','Linux','Chrome 8','2020-06-20 13:15:32'),(189,'SuperAdmin','/mpls/limitProfile/detail/3','957E3C169BD05AA6940C291E4D3E6AC8','web','192.168.2.6','Linux','Chrome 8','2020-06-20 13:15:42'),(190,'SuperAdmin','/mpls/limitProfile/add','957E3C169BD05AA6940C291E4D3E6AC8','web','192.168.2.6','Linux','Chrome 8','2020-06-20 13:18:09'),(191,'SuperAdmin','/mpls/limitProfile/detail/4','957E3C169BD05AA6940C291E4D3E6AC8','web','192.168.2.6','Linux','Chrome 8','2020-06-20 13:18:12'),(192,'SuperAdmin','/mpls/limitProfile/detail/7','957E3C169BD05AA6940C291E4D3E6AC8','web','192.168.2.6','Linux','Chrome 8','2020-06-20 13:25:47'),(193,'SuperAdmin','/mpls/merchant/dashboard/view','2C5308ADD325AE52BF2DB3AB7385D9B0','web','192.168.2.6','Linux','Chrome 8','2020-06-20 13:30:24'),(194,'SuperAdmin','/mpls/limitProfile/view','2C5308ADD325AE52BF2DB3AB7385D9B0','web','192.168.2.6','Linux','Chrome 8','2020-06-20 13:30:33'),(195,'SuperAdmin','/mpls/limitProfile/update','2C5308ADD325AE52BF2DB3AB7385D9B0','web','192.168.2.6','Linux','Chrome 8','2020-06-20 13:30:41'),(196,'SuperAdmin','/mpls/limitProfile/edit','2C5308ADD325AE52BF2DB3AB7385D9B0','web','192.168.2.6','Linux','Chrome 8','2020-06-20 13:30:47'),(197,'SuperAdmin','/mpls/merchant/dashboard/view','0DC5B3F97E41BFCE50DBC01354F04424','web','192.168.2.6','Linux','Chrome 8','2020-06-20 13:49:23'),(198,'SuperAdmin','/mpls/limitProfile/view','0DC5B3F97E41BFCE50DBC01354F04424','web','192.168.2.6','Linux','Chrome 8','2020-06-20 13:49:26'),(199,'SuperAdmin','/mpls/limitProfile/update','0DC5B3F97E41BFCE50DBC01354F04424','web','192.168.2.6','Linux','Chrome 8','2020-06-20 13:49:31'),(200,'SuperAdmin','/mpls/limitProfile/edit','0DC5B3F97E41BFCE50DBC01354F04424','web','192.168.2.6','Linux','Chrome 8','2020-06-20 13:49:37'),(201,'SuperAdmin','/mpls/limitProfile/detail/14','0DC5B3F97E41BFCE50DBC01354F04424','web','192.168.2.6','Linux','Chrome 8','2020-06-20 13:49:43'),(202,'SuperAdmin','/mpls/user/role/view','0DC5B3F97E41BFCE50DBC01354F04424','web','192.168.2.6','Linux','Chrome 8','2020-06-20 13:50:01'),(203,'SuperAdmin','/mpls/merchant/dashboard/view','B511D63E2736ABADC2CD4204AB1D4B38','web','192.168.2.6','Linux','Chrome 8','2020-06-21 15:03:48'),(204,'SuperAdmin','/mpls/state/view','B511D63E2736ABADC2CD4204AB1D4B38','web','192.168.2.6','Linux','Chrome 8','2020-06-21 15:03:54'),(205,'SuperAdmin','/mpls/state/add','B511D63E2736ABADC2CD4204AB1D4B38','web','192.168.2.6','Linux','Chrome 8','2020-06-21 15:04:00'),(206,'SuperAdmin','/mpls/city/view','B511D63E2736ABADC2CD4204AB1D4B38','web','192.168.2.6','Linux','Chrome 8','2020-06-21 15:04:01'),(207,'SuperAdmin','/mpls/rest/ajax/getStateList','B511D63E2736ABADC2CD4204AB1D4B38','web','192.168.2.6','Linux','Chrome 8','2020-06-21 15:04:04'),(208,'SuperAdmin','/mpls/city/add','B511D63E2736ABADC2CD4204AB1D4B38','web','192.168.2.6','Linux','Chrome 8','2020-06-21 15:04:08'),(209,'SuperAdmin','/mpls/area/view','B511D63E2736ABADC2CD4204AB1D4B38','web','192.168.2.6','Linux','Chrome 8','2020-06-21 15:04:08'),(210,'SuperAdmin','/mpls/rest/ajax/getCityList','B511D63E2736ABADC2CD4204AB1D4B38','web','192.168.2.6','Linux','Chrome 8','2020-06-21 15:04:12'),(211,'SuperAdmin','/mpls/area/add','B511D63E2736ABADC2CD4204AB1D4B38','web','192.168.2.6','Linux','Chrome 8','2020-06-21 15:04:19'),(212,'SuperAdmin','/mpls/category/view','B511D63E2736ABADC2CD4204AB1D4B38','web','192.168.2.6','Linux','Chrome 8','2020-06-21 15:04:22'),(213,'SuperAdmin','/mpls/category/add','B511D63E2736ABADC2CD4204AB1D4B38','web','192.168.2.6','Linux','Chrome 8','2020-06-21 15:04:26'),(214,'SuperAdmin','/mpls/','B511D63E2736ABADC2CD4204AB1D4B38','web','192.168.2.6','Linux','Chrome 8','2020-06-21 15:05:03'),(215,'SuperAdmin','/mpls/user/logout.htm','B511D63E2736ABADC2CD4204AB1D4B38','web','192.168.2.6','Linux','Chrome 8','2020-06-21 15:05:07'),(216,'SuperAdmin','/mpls/merchant/dashboard/view','8BB1806C6624B1E9D2E182207E87EAE4','web','192.168.2.6','Linux','Chrome 8','2020-06-21 16:04:10'),(217,'SuperAdmin','/mpls/merchant/dashboard/data/1','8BB1806C6624B1E9D2E182207E87EAE4','web','192.168.2.6','Linux','Chrome 8','2020-06-21 16:04:10'),(218,'SuperAdmin','/mpls/merchant/dashboard/view','1EAFBCBD6A482F7EC7286ED8271B5915','web','192.168.2.6','Linux','Chrome 8','2020-06-21 16:04:47'),(219,'SuperAdmin','/mpls/merchant/dashboard/data/1','1EAFBCBD6A482F7EC7286ED8271B5915','web','192.168.2.6','Linux','Chrome 8','2020-06-21 16:04:47'),(220,'SuperAdmin','/mpls/merchant/dashboard/view','E43D9E9519DA95F6868471A345E8A5F8','web','192.168.2.6','Linux','Chrome 8','2020-06-21 16:10:26'),(221,'SuperAdmin','/mpls/merchant/dashboard/data/1','E43D9E9519DA95F6868471A345E8A5F8','web','192.168.2.6','Linux','Chrome 8','2020-06-21 16:10:26'),(222,'SuperAdmin','/mpls/merchant/dashboard/view','892CCE86EA66FC07794357A7257ABE34','web','192.168.2.6','Linux','Chrome 8','2020-06-22 06:39:20'),(223,'SuperAdmin','/mpls/merchant/dashboard/data/1','892CCE86EA66FC07794357A7257ABE34','web','192.168.2.6','Linux','Chrome 8','2020-06-22 06:39:20'),(224,'SuperAdmin','/mpls/merchant/dashboard/view','522F3E7CFA923E93D8E7682B692AD44E','web','192.168.2.6','Linux','Chrome 8','2020-06-22 06:42:22'),(225,'SuperAdmin','/mpls/merchant/dashboard/data/1','522F3E7CFA923E93D8E7682B692AD44E','web','192.168.2.6','Linux','Chrome 8','2020-06-22 06:42:23'),(226,'SuperAdmin','/mpls/merchant/dashboard/view','17E278E63C36E55F04A8CA6DBCB86EE1','web','192.168.2.6','Linux','Chrome 8','2020-06-22 06:43:47'),(227,'SuperAdmin','/mpls/merchant/dashboard/data/1','17E278E63C36E55F04A8CA6DBCB86EE1','web','192.168.2.6','Linux','Chrome 8','2020-06-22 06:43:47'),(228,'SuperAdmin','/mpls/merchant/dashboard/view','416404513EE3B21C10B05C8CB8052CA1','web','192.168.43.43','Linux','Chrome 8','2020-06-22 09:05:21'),(229,'SuperAdmin','/mpls/merchant/dashboard/data/1','416404513EE3B21C10B05C8CB8052CA1','web','192.168.43.43','Linux','Chrome 8','2020-06-22 09:05:22'),(230,'SuperAdmin','/mpls/merchant/registration/view','416404513EE3B21C10B05C8CB8052CA1','web','192.168.43.43','Linux','Chrome 8','2020-06-22 09:05:26'),(231,'SuperAdmin','/mpls/merchant/update','416404513EE3B21C10B05C8CB8052CA1','web','192.168.43.43','Linux','Chrome 8','2020-06-22 09:05:32'),(232,'SuperAdmin','/mpls/rest/ajax/getCurrencyList','416404513EE3B21C10B05C8CB8052CA1','web','192.168.43.43','Linux','Chrome 8','2020-06-22 09:05:48'),(233,'SuperAdmin','/mpls/rest/ajax/getStateList','416404513EE3B21C10B05C8CB8052CA1','web','192.168.43.43','Linux','Chrome 8','2020-06-22 09:05:48'),(234,'SuperAdmin','/mpls/rest/ajax/isExistsPANNumber','416404513EE3B21C10B05C8CB8052CA1','web','192.168.43.43','Linux','Chrome 8','2020-06-22 09:05:53'),(235,'SuperAdmin','/mpls/rest/ajax/isExistsGSTIN','416404513EE3B21C10B05C8CB8052CA1','web','192.168.43.43','Linux','Chrome 8','2020-06-22 09:06:06'),(236,'SuperAdmin','/mpls/rest/ajax/isExistsEmailId','416404513EE3B21C10B05C8CB8052CA1','web','192.168.43.43','Linux','Chrome 8','2020-06-22 09:06:59'),(237,'SuperAdmin','/mpls/rest/ajax/isExistsMobileNumber','416404513EE3B21C10B05C8CB8052CA1','web','192.168.43.43','Linux','Chrome 8','2020-06-22 09:07:04'),(238,'SuperAdmin','/mpls/rest/ajax/sendVerificationCode','416404513EE3B21C10B05C8CB8052CA1','web','192.168.43.43','Linux','Chrome 8','2020-06-22 09:07:04'),(239,'SuperAdmin','/mpls/rest/ajax/verifyCode','416404513EE3B21C10B05C8CB8052CA1','web','192.168.43.43','Linux','Chrome 8','2020-06-22 09:07:28'),(240,'SuperAdmin','/mpls/rest/ajax/getCityList','416404513EE3B21C10B05C8CB8052CA1','web','192.168.43.43','Linux','Chrome 8','2020-06-22 09:07:31'),(241,'SuperAdmin','/mpls/rest/ajax/getAreaList','416404513EE3B21C10B05C8CB8052CA1','web','192.168.43.43','Linux','Chrome 8','2020-06-22 09:07:33'),(242,'SuperAdmin','/mpls/rest/ajax/getPincode','416404513EE3B21C10B05C8CB8052CA1','web','192.168.43.43','Linux','Chrome 8','2020-06-22 09:07:34'),(243,'SuperAdmin','/mpls/merchant/register','416404513EE3B21C10B05C8CB8052CA1','web','192.168.43.43','Linux','Chrome 8','2020-06-22 09:07:44'),(244,'SuperAdmin','/mpls/user/merchant/register','416404513EE3B21C10B05C8CB8052CA1','web','192.168.43.43','Linux','Chrome 8','2020-06-22 09:07:45'),(245,'SuperAdmin','/mpls/','416404513EE3B21C10B05C8CB8052CA1','web','192.168.43.43','Linux','Chrome 8','2020-06-22 09:07:50'),(246,'SuperAdmin','/mpls/user/logout.htm','416404513EE3B21C10B05C8CB8052CA1','web','192.168.43.43','Linux','Chrome 8','2020-06-22 09:12:02'),(247,'N1UUGMNVBH','/mpls/merchant/dashboard/view','EC2BE44FEC650619DB9553EC6B1BA57B','web','0.0.0.0','Linux','Chrome 8','2020-06-22 10:51:07'),(248,'N1UUGMNVBH','/mpls/merchant/dashboard/data/1','EC2BE44FEC650619DB9553EC6B1BA57B','web','0.0.0.0','Linux','Chrome 8','2020-06-22 10:51:07'),(249,'N1UUGMNVBH','/mpls/user/logout.htm','EC2BE44FEC650619DB9553EC6B1BA57B','web','0.0.0.0','Linux','Chrome 8','2020-06-22 10:51:44'),(251,'MKCSE41','/mpls/search/store/view','3802D628A63F5869313ADC75FC23DD8E','web','0.0.0.0','Linux','Chrome 8','2020-06-22 10:52:02'),(252,'MKCSE41','/mpls/search/store/mappingMerchant','3802D628A63F5869313ADC75FC23DD8E','web','0.0.0.0','Linux','Chrome 8','2020-06-22 10:52:26'),(253,'MKCSE41','/mpls/customer/merchant/deleteMerchant','3802D628A63F5869313ADC75FC23DD8E','web','0.0.0.0','Linux','Chrome 8','2020-06-22 10:52:30'),(254,'MKCSE41','/mpls/user/logout.htm','3802D628A63F5869313ADC75FC23DD8E','web','0.0.0.0','Linux','Chrome 8','2020-06-22 10:52:44'),(255,'N1UUGMNVBH','/mpls/merchant/dashboard/view','99D63DD766CE760C42481AEA35F8B060','web','0.0.0.0','Linux','Chrome 8','2020-06-22 10:52:48'),(256,'N1UUGMNVBH','/mpls/merchant/dashboard/data/1','99D63DD766CE760C42481AEA35F8B060','web','0.0.0.0','Linux','Chrome 8','2020-06-22 10:52:48'),(257,'N1UUGMNVBH','/mpls/customer/transaction/view','99D63DD766CE760C42481AEA35F8B060','web','0.0.0.0','Linux','Chrome 8','2020-06-22 10:53:00'),(258,'N1UUGMNVBH','/mpls/merchant/dashboard/view','365B42C0883A4BE7692D7C5C485A12DF','web','0.0.0.0','Linux','Chrome 8','2020-06-22 10:56:09'),(259,'N1UUGMNVBH','/mpls/merchant/dashboard/data/1','365B42C0883A4BE7692D7C5C485A12DF','web','0.0.0.0','Linux','Chrome 8','2020-06-22 10:56:10'),(260,'N1UUGMNVBH','/mpls/user/logout.htm','365B42C0883A4BE7692D7C5C485A12DF','web','0.0.0.0','Linux','Chrome 8','2020-06-22 10:56:12'),(261,'N1UUGMNVBH','/mpls/merchant/dashboard/view','61F66BDAEB48F02D857A55A2F44E868D','web','0.0.0.0','Linux','Chrome 8','2020-06-22 10:56:13'),(262,'N1UUGMNVBH','/mpls/merchant/dashboard/data/1','61F66BDAEB48F02D857A55A2F44E868D','web','0.0.0.0','Linux','Chrome 8','2020-06-22 10:56:14'),(263,'N1UUGMNVBH','/mpls/user/logout.htm','61F66BDAEB48F02D857A55A2F44E868D','web','0.0.0.0','Linux','Chrome 8','2020-06-22 10:56:17'),(264,'N1UUGMNVBH','/mpls/merchant/dashboard/view','8B83160D67954220DEAF5A46D373F0F8','web','0.0.0.0','Linux','Chrome 8','2020-06-22 10:56:18'),(265,'N1UUGMNVBH','/mpls/merchant/dashboard/data/1','8B83160D67954220DEAF5A46D373F0F8','web','0.0.0.0','Linux','Chrome 8','2020-06-22 10:56:19'),(266,'N1UUGMNVBH','/mpls/customer/transaction/view','8B83160D67954220DEAF5A46D373F0F8','web','0.0.0.0','Linux','Chrome 8','2020-06-22 10:56:24'),(267,'N1UUGMNVBH','/mpls/customer/transaction/addMoney','8B83160D67954220DEAF5A46D373F0F8','web','0.0.0.0','Linux','Chrome 8','2020-06-22 10:56:49'),(268,'N1UUGMNVBH','/mpls/merchant/dashboard/view','E85063DD9B440BAC836B62CFED905AA6','web','0.0.0.0','Linux','Chrome 8','2020-06-22 11:10:15'),(269,'N1UUGMNVBH','/mpls/merchant/dashboard/data/1','E85063DD9B440BAC836B62CFED905AA6','web','0.0.0.0','Linux','Chrome 8','2020-06-22 11:10:15'),(270,'N1UUGMNVBH','/mpls/customer/transaction/view','E85063DD9B440BAC836B62CFED905AA6','web','0.0.0.0','Linux','Chrome 8','2020-06-22 11:10:20'),(271,'N1UUGMNVBH','/mpls/customer/transaction/addMoney','E85063DD9B440BAC836B62CFED905AA6','web','0.0.0.0','Linux','Chrome 8','2020-06-22 11:10:34'),(272,'MHIT92','/mpls/dashboard','7787DEC5F529F62697671049573FB25D','web','192.168.2.6','Linux','Chrome 8','2020-06-22 11:13:18'),(273,'MHIT92','/mpls/search/store/view','7787DEC5F529F62697671049573FB25D','web','192.168.2.6','Linux','Chrome 8','2020-06-22 11:13:22'),(274,'MHIT92','/mpls/search/store/mappingMerchant','7787DEC5F529F62697671049573FB25D','web','192.168.2.6','Linux','Chrome 8','2020-06-22 11:13:26'),(275,'MHIT92','/mpls/user/logout.htm','7787DEC5F529F62697671049573FB25D','web','192.168.2.6','Linux','Chrome 8','2020-06-22 11:13:33'),(276,'MHIT92','/mpls/dashboard','AC9105B8DE3BBBD594E398A857DD3F78','web','192.168.2.6','Linux','Chrome 8','2020-06-22 11:14:36'),(277,'MHIT92','/mpls/user/logout.htm','AC9105B8DE3BBBD594E398A857DD3F78','web','192.168.2.6','Linux','Chrome 8','2020-06-22 11:14:39'),(278,'MHIT92','/mpls/dashboard','7FC2A93BA124CAFCE7E6A12DD4458538','web','192.168.2.6','Linux','Chrome 8','2020-06-22 11:14:43'),(279,'MHIT92','/mpls/user/logout.htm','7FC2A93BA124CAFCE7E6A12DD4458538','web','192.168.2.6','Linux','Chrome 8','2020-06-22 11:14:45'),(280,'MHIT92','/mpls/dashboard','7FE1C1CE18D3E67551087867D08CAA1D','web','192.168.2.6','Linux','Chrome 8','2020-06-22 11:14:49'),(281,'MHIT92','/mpls/customer/merchant/view','7FE1C1CE18D3E67551087867D08CAA1D','web','192.168.2.6','Linux','Chrome 8','2020-06-22 11:14:56'),(282,'MHIT92','/mpls/user/logout.htm','7FE1C1CE18D3E67551087867D08CAA1D','web','192.168.2.6','Linux','Chrome 8','2020-06-22 11:15:15'),(283,'N1UUGMNVBH','/mpls/merchant/dashboard/view','3A3FF6CE2EAFD888F78950FE66F7BD52','web','192.168.2.6','Linux','Chrome 8','2020-06-22 11:15:24'),(284,'N1UUGMNVBH','/mpls/merchant/dashboard/data/1','3A3FF6CE2EAFD888F78950FE66F7BD52','web','192.168.2.6','Linux','Chrome 8','2020-06-22 11:15:24'),(285,'N1UUGMNVBH','/mpls/customer/transaction/view','3A3FF6CE2EAFD888F78950FE66F7BD52','web','192.168.2.6','Linux','Chrome 8','2020-06-22 11:15:28'),(286,'N1UUGMNVBH','/mpls/customer/transaction/addMoney','3A3FF6CE2EAFD888F78950FE66F7BD52','web','192.168.2.6','Linux','Chrome 8','2020-06-22 11:15:33'),(287,'N1UUGMNVBH','/mpls/','3A3FF6CE2EAFD888F78950FE66F7BD52','web','192.168.2.6','Linux','Chrome 8','2020-06-22 11:15:40'),(288,'N1UUGMNVBH','/mpls/merchant/dashboard/view','3B04ED37FF287C957C74D98126FFB47F','web','192.168.2.6','Linux','Chrome 8','2020-06-22 11:17:45'),(289,'N1UUGMNVBH','/mpls/merchant/dashboard/data/1','3B04ED37FF287C957C74D98126FFB47F','web','192.168.2.6','Linux','Chrome 8','2020-06-22 11:17:45'),(290,'N1UUGMNVBH','/mpls/customer/transaction/view','3B04ED37FF287C957C74D98126FFB47F','web','192.168.2.6','Linux','Chrome 8','2020-06-22 11:17:48'),(291,'N1UUGMNVBH','/mpls/customer/transaction/addMoney','3B04ED37FF287C957C74D98126FFB47F','web','192.168.2.6','Linux','Chrome 8','2020-06-22 11:17:54'),(292,'N1UUGMNVBH','/mpls/merchant/dashboard/view','312B500AFF49F99EA7A83C1A62982BDB','web','192.168.2.6','Linux','Chrome 8','2020-06-22 11:20:15'),(293,'N1UUGMNVBH','/mpls/merchant/dashboard/data/1','312B500AFF49F99EA7A83C1A62982BDB','web','192.168.2.6','Linux','Chrome 8','2020-06-22 11:20:15'),(294,'N1UUGMNVBH','/mpls/customer/transaction/view','312B500AFF49F99EA7A83C1A62982BDB','web','192.168.2.6','Linux','Chrome 8','2020-06-22 11:20:17'),(295,'N1UUGMNVBH','/mpls/customer/transaction/addMoney','312B500AFF49F99EA7A83C1A62982BDB','web','192.168.2.6','Linux','Chrome 8','2020-06-22 11:20:22'),(296,'N1UUGMNVBH','/mpls/merchant/loyaltyCashback/getMerchantOffers/1','312B500AFF49F99EA7A83C1A62982BDB','web','192.168.2.6','Linux','Chrome 8','2020-06-22 11:20:29'),(297,'N1UUGMNVBH','/mpls/customer/transaction/payBills','312B500AFF49F99EA7A83C1A62982BDB','web','192.168.2.6','Linux','Chrome 8','2020-06-22 11:22:15'),(298,'N1UUGMNVBH','/mpls/merchant/transactionHistory/view','312B500AFF49F99EA7A83C1A62982BDB','web','192.168.2.6','Linux','Chrome 8','2020-06-22 11:23:33'),(299,'N1UUGMNVBH','/mpls/merchant/transactionHistory/data/1','312B500AFF49F99EA7A83C1A62982BDB','web','192.168.2.6','Linux','Chrome 8','2020-06-22 11:23:33'),(300,'N1UUGMNVBH','/mpls/dashboard','312B500AFF49F99EA7A83C1A62982BDB','web','192.168.2.6','Linux','Chrome 8','2020-06-22 11:23:44'),(301,'N1UUGMNVBH','/mpls/customer/transaction/generateQRCode','312B500AFF49F99EA7A83C1A62982BDB','web','192.168.2.6','Linux','Chrome 8','2020-06-22 11:24:05');
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verificationLog`
--

LOCK TABLES `verificationLog` WRITE;
/*!40000 ALTER TABLE `verificationLog` DISABLE KEYS */;
INSERT INTO `verificationLog` VALUES (1,'merchant','mukesh@newsonfloor.com',NULL,'938127','','2020-06-21 15:05:42',NULL,NULL,NULL),(2,'merchant','mohit@gmail.com',NULL,'362928','','2020-06-22 09:07:04',NULL,NULL,NULL);
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

-- Dump completed on 2020-06-22 17:19:06
