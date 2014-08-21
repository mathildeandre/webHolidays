-- MySQL dump 10.13  Distrib 5.5.37, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: lportal_ent
-- ------------------------------------------------------
-- Server version	5.5.37-0+wheezy1-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `account_`
--

DROP TABLE IF EXISTS `account_`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account_` (
  `accountId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `parentAccountId` bigint(20) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `legalName` varchar(75) DEFAULT NULL,
  `legalId` varchar(75) DEFAULT NULL,
  `legalType` varchar(75) DEFAULT NULL,
  `sicCode` varchar(75) DEFAULT NULL,
  `tickerSymbol` varchar(75) DEFAULT NULL,
  `industry` varchar(75) DEFAULT NULL,
  `type_` varchar(75) DEFAULT NULL,
  `size_` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`accountId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account_`
--

LOCK TABLES `account_` WRITE;
/*!40000 ALTER TABLE `account_` DISABLE KEYS */;
INSERT INTO `account_` VALUES (10204,10202,0,'','2011-06-14 14:48:06','2014-07-28 15:08:56',0,'Lea Haute Normandie','Lea Haute Normandie','','','','','','','');
/*!40000 ALTER TABLE `account_` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actualites_blogentrydelegate`
--

DROP TABLE IF EXISTS `actualites_blogentrydelegate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actualites_blogentrydelegate` (
  `blogEntryDelegateId` bigint(20) NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `headMasterDelegate` tinyint(4) DEFAULT NULL,
  `etabId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`blogEntryDelegateId`),
  KEY `IX_8445A362` (`etabId`),
  KEY `IX_D527109D` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actualites_blogentrydelegate`
--

LOCK TABLES `actualites_blogentrydelegate` WRITE;
/*!40000 ALTER TABLE `actualites_blogentrydelegate` DISABLE KEYS */;
/*!40000 ALTER TABLE `actualites_blogentrydelegate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actualites_blogentryinfos`
--

DROP TABLE IF EXISTS `actualites_blogentryinfos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actualites_blogentryinfos` (
  `blogEntryId` bigint(20) DEFAULT NULL,
  `headMaster` tinyint(1) DEFAULT NULL,
  `highPriority` tinyint(1) DEFAULT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `expirationDate` datetime DEFAULT NULL,
  `parutionDate` datetime DEFAULT NULL,
  `categorie` varchar(100) DEFAULT NULL,
  `imageId` bigint(20) DEFAULT '0',
  `blogEntryGroup` bigint(20) DEFAULT NULL,
  `blogEntryInfosId` bigint(20) NOT NULL,
  `flashFileState` varchar(75) DEFAULT NULL,
  `intranetNews` tinyint(1) DEFAULT NULL,
  `lastEditorId` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`blogEntryInfosId`),
  KEY `blogEntryId` (`blogEntryId`),
  KEY `IX_9BA729D6` (`blogEntryGroup`),
  KEY `IX_488237C8` (`blogEntryId`),
  KEY `IX_72F2A994` (`categorie`),
  KEY `IX_7AC11EE4` (`expirationDate`),
  KEY `IX_98F5BE57` (`groupId`),
  KEY `IX_71C15CE7` (`highPriority`,`headMaster`,`groupId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actualites_blogentryinfos`
--

LOCK TABLES `actualites_blogentryinfos` WRITE;
/*!40000 ALTER TABLE `actualites_blogentryinfos` DISABLE KEYS */;
INSERT INTO `actualites_blogentryinfos` VALUES (274720,0,0,18406,'2015-07-25 00:00:00','2014-07-25 00:00:00','0',0,274720,274729,'',0,0),(274734,0,0,18202,'2015-07-25 00:00:00','2014-07-25 00:00:00','0',0,274734,274743,'',0,0);
/*!40000 ALTER TABLE `actualites_blogentryinfos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actualites_confirmreadblogentry`
--

DROP TABLE IF EXISTS `actualites_confirmreadblogentry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actualites_confirmreadblogentry` (
  `confirmReadPK` bigint(20) NOT NULL,
  `blogEntryInfosId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`confirmReadPK`),
  KEY `IX_FA71457C` (`blogEntryInfosId`),
  KEY `IX_2A0E2B36` (`userId`,`blogEntryInfosId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actualites_confirmreadblogentry`
--

LOCK TABLES `actualites_confirmreadblogentry` WRITE;
/*!40000 ALTER TABLE `actualites_confirmreadblogentry` DISABLE KEYS */;
/*!40000 ALTER TABLE `actualites_confirmreadblogentry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `address` (
  `addressId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `classNameId` bigint(20) DEFAULT NULL,
  `classPK` bigint(20) DEFAULT NULL,
  `street1` varchar(75) DEFAULT NULL,
  `street2` varchar(75) DEFAULT NULL,
  `street3` varchar(75) DEFAULT NULL,
  `city` varchar(75) DEFAULT NULL,
  `zip` varchar(75) DEFAULT NULL,
  `regionId` bigint(20) DEFAULT NULL,
  `countryId` bigint(20) DEFAULT NULL,
  `typeId` int(11) DEFAULT NULL,
  `mailing` tinyint(4) DEFAULT NULL,
  `primary_` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`addressId`),
  KEY `IX_93D5AD4E` (`companyId`),
  KEY `IX_ABD7DAC0` (`companyId`,`classNameId`),
  KEY `IX_71CB1123` (`companyId`,`classNameId`,`classPK`),
  KEY `IX_923BD178` (`companyId`,`classNameId`,`classPK`,`mailing`),
  KEY `IX_9226DBB4` (`companyId`,`classNameId`,`classPK`,`primary_`),
  KEY `IX_5BC8B0D4` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `address`
--

LOCK TABLES `address` WRITE;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
/*!40000 ALTER TABLE `address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agenda_eventagenda`
--

DROP TABLE IF EXISTS `agenda_eventagenda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agenda_eventagenda` (
  `evtId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `startEvt` datetime DEFAULT NULL,
  `endEvt` datetime DEFAULT NULL,
  `calId` bigint(20) DEFAULT NULL,
  `title` varchar(75) DEFAULT NULL,
  `location` varchar(75) DEFAULT NULL,
  `notes` varchar(75) DEFAULT NULL,
  `url` varchar(75) DEFAULT NULL,
  `isAllDay` tinyint(4) DEFAULT NULL,
  `isVisible` tinyint(4) DEFAULT NULL,
  `remind` varchar(75) DEFAULT NULL,
  `creatorId` bigint(20) DEFAULT NULL,
  `repeatEvtId` bigint(20) DEFAULT NULL,
  `repeatEvtRRule` varchar(75) DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `uid` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`evtId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agenda_eventagenda`
--

LOCK TABLES `agenda_eventagenda` WRITE;
/*!40000 ALTER TABLE `agenda_eventagenda` DISABLE KEYS */;
/*!40000 ALTER TABLE `agenda_eventagenda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agenda_ressource`
--

DROP TABLE IF EXISTS `agenda_ressource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agenda_ressource` (
  `idRessource` bigint(20) NOT NULL,
  `idcreator` bigint(20) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `description` varchar(75) DEFAULT NULL,
  `ressourceType` int(11) DEFAULT NULL,
  `scope` int(11) DEFAULT NULL,
  `color` varchar(75) DEFAULT NULL,
  `etabId` bigint(20) DEFAULT NULL,
  `kind` int(11) DEFAULT NULL,
  `forcePubli` tinyint(4) DEFAULT NULL,
  `cdtUserId` bigint(20) DEFAULT NULL,
  `ressource` tinyint(4) DEFAULT NULL,
  `ecriture` tinyint(4) DEFAULT NULL,
  `filtreCours` int(11) DEFAULT NULL,
  `classe` bigint(20) DEFAULT NULL,
  `matiere` varchar(75) DEFAULT NULL,
  `onlyMyClass` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`idRessource`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agenda_ressource`
--

LOCK TABLES `agenda_ressource` WRITE;
/*!40000 ALTER TABLE `agenda_ressource` DISABLE KEYS */;
INSERT INTO `agenda_ressource` VALUES (19712,11105,'Agenda de L&#039;equipe Technique','',2,1,'22',18210,0,0,0,0,0,0,0,'',0),(19713,11105,'Agenda de L&#039;equipe Technique','',2,1,'22',18210,0,0,0,0,0,0,0,'',0),(270204,11105,'Mon EDT','',2,1,'22',18210,2,0,11105,0,0,1,-1,'[-1]',1),(270604,11105,'Agenda de L\'equipe Technique','',2,1,'22',18210,0,0,11105,0,0,1,-1,'[-1]',1),(270605,11105,'Agenda de L\'equipe Technique','',2,1,'22',18210,0,0,11105,0,0,1,-1,'[-1]',1),(270609,11105,'Agenda de L\'equipe Technique','',2,1,'22',18210,0,0,11105,0,0,1,-1,'[-1]',1),(270611,11105,'Agenda de L\'equipe Technique','',2,1,'22',18210,0,0,11105,0,0,1,-1,'[-1]',1),(271146,11105,'Agenda de L\'equipe Technique','',2,1,'22',18210,0,0,11105,0,0,1,-1,'[-1]',1),(271147,11105,'Agenda de L\'equipe Technique','',2,1,'22',18210,0,0,11105,0,0,1,-1,'[-1]',1),(271151,11105,'Agenda de L\'equipe Technique','',2,1,'22',18210,0,0,11105,0,0,1,-1,'[-1]',1),(271152,11105,'Agenda de L\'equipe Technique','',2,1,'22',18210,0,0,11105,0,0,1,-1,'[-1]',1),(278507,11105,'Agenda de L\'equipe Technique','',2,1,'22',18210,0,0,11105,0,0,1,-1,'[-1]',1),(278510,11105,'Agenda de L\'equipe Technique','',2,1,'22',18210,0,0,11105,0,0,1,-1,'[-1]',1),(278513,11105,'Agenda de L\'equipe Technique','',2,1,'22',18210,0,0,11105,0,0,1,-1,'[-1]',1),(278515,11105,'Agenda de L\'equipe Technique','',2,1,'22',18210,0,0,11105,0,0,1,-1,'[-1]',1),(278517,11105,'Agenda de L\'equipe Technique','',2,1,'22',18210,0,0,11105,0,0,1,-1,'[-1]',1),(278519,11105,'Agenda de L\'equipe Technique','',2,1,'22',18210,0,0,11105,0,0,1,-1,'[-1]',1),(278523,11105,'Agenda de L\'equipe Technique','',2,1,'22',18210,0,0,11105,0,0,1,-1,'[-1]',1),(278526,11105,'Agenda de L\'equipe Technique','',2,1,'22',18210,0,0,11105,0,0,1,-1,'[-1]',1),(278529,11105,'Agenda de L\'equipe Technique','',2,1,'22',18210,0,0,11105,0,0,1,-1,'[-1]',1),(278531,11105,'Agenda de L\'equipe Technique','',2,1,'22',18210,0,0,11105,0,0,1,-1,'[-1]',1),(282019,11105,'Agenda de L\'equipe Technique','',2,1,'22',18210,0,0,11105,0,0,1,-1,'[-1]',1),(282022,11105,'Agenda de L\'equipe Technique','',2,1,'22',18210,0,0,11105,0,0,1,-1,'[-1]',1),(282025,11105,'Agenda de L\'equipe Technique','',2,1,'22',18210,0,0,11105,0,0,1,-1,'[-1]',1),(282027,11105,'Agenda de L\'equipe Technique','',2,1,'22',18210,0,0,11105,0,0,1,-1,'[-1]',1),(282029,11105,'Agenda de L\'equipe Technique','',2,1,'22',18210,0,0,11105,0,0,1,-1,'[-1]',1),(282031,11105,'Agenda de L\'equipe Technique','',2,1,'22',18210,0,0,11105,0,0,1,-1,'[-1]',1),(282033,11105,'Mon agenda','',2,1,'22',18210,1,0,11105,0,0,1,-1,'[]',1),(282035,11105,'Agenda de L\'equipe Technique','',2,1,'22',18210,0,0,11105,0,0,1,-1,'[-1]',1),(282038,11105,'Agenda de L\'equipe Technique','',2,1,'22',18210,0,0,11105,0,0,1,-1,'[-1]',1),(282039,11105,'Agenda de L\'equipe Technique','',2,1,'22',18210,0,0,11105,0,0,1,-1,'[-1]',1),(282043,11105,'Agenda de L\'equipe Technique','',2,1,'22',18210,0,0,11105,0,0,1,-1,'[-1]',1),(282045,11105,'Agenda de L\'equipe Technique','',2,1,'22',18210,0,0,11105,0,0,1,-1,'[-1]',1),(282048,11105,'Agenda de L\'equipe Technique','',2,1,'22',18210,0,0,11105,0,0,1,-1,'[-1]',1),(282049,11105,'Agenda de L\'equipe Technique','',2,1,'22',18210,0,0,11105,0,0,1,-1,'[-1]',1),(282053,11105,'Agenda de L\'equipe Technique','',2,1,'22',18210,0,0,11105,0,0,1,-1,'[-1]',1),(282056,11105,'Agenda de L\'equipe Technique','',2,1,'22',18210,0,0,11105,0,0,1,-1,'[-1]',1),(282059,11105,'Agenda de L\'equipe Technique','',2,1,'22',18210,0,0,11105,0,0,1,-1,'[-1]',1),(282062,11105,'Agenda de L\'equipe Technique','',2,1,'22',18210,0,0,11105,0,0,1,-1,'[-1]',1),(282064,11105,'Agenda de L\'equipe Technique','',2,1,'22',18210,0,0,11105,0,0,1,-1,'[-1]',1),(282066,11105,'Agenda de L\'equipe Technique','',2,1,'22',18210,0,0,11105,0,0,1,-1,'[-1]',1),(282068,11105,'Agenda de L\'equipe Technique','',2,1,'22',18210,0,0,11105,0,0,1,-1,'[-1]',1);
/*!40000 ALTER TABLE `agenda_ressource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agenda_ressourcerole`
--

DROP TABLE IF EXISTS `agenda_ressourcerole`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agenda_ressourcerole` (
  `idRessourceRole` bigint(20) NOT NULL,
  `idRessource` bigint(20) DEFAULT NULL,
  `idRole` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`idRessourceRole`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agenda_ressourcerole`
--

LOCK TABLES `agenda_ressourcerole` WRITE;
/*!40000 ALTER TABLE `agenda_ressourcerole` DISABLE KEYS */;
/*!40000 ALTER TABLE `agenda_ressourcerole` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `agenda_ressourceuser`
--

DROP TABLE IF EXISTS `agenda_ressourceuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agenda_ressourceuser` (
  `idRessourceUser` bigint(20) NOT NULL,
  `idRessource` bigint(20) DEFAULT NULL,
  `idUser` bigint(20) DEFAULT NULL,
  `redacteur` tinyint(4) DEFAULT NULL,
  `visible` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`idRessourceUser`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `agenda_ressourceuser`
--

LOCK TABLES `agenda_ressourceuser` WRITE;
/*!40000 ALTER TABLE `agenda_ressourceuser` DISABLE KEYS */;
INSERT INTO `agenda_ressourceuser` VALUES (36709,36708,11105,0,1),(138204,138203,11105,0,1),(270205,270204,11105,0,1),(270606,270605,11105,1,1),(270607,270604,11105,1,1),(270608,270604,11105,1,1),(270610,270609,11105,1,1),(270612,270611,11105,1,1),(270613,270611,11105,1,1),(271148,271146,11105,1,1),(271149,271147,11105,1,1),(271150,271146,11105,1,1),(271153,271151,11105,1,1),(271154,271152,11105,1,1),(271155,271152,11105,1,1),(278508,278507,11105,1,1),(278509,278507,11105,1,1),(278511,278510,11105,1,1),(278512,278510,11105,1,1),(278514,278513,11105,1,1),(278516,278515,11105,1,1),(278518,278517,11105,1,1),(278520,278519,11105,1,1),(278524,278523,11105,1,1),(278525,278523,11105,1,1),(278527,278526,11105,1,1),(278528,278526,11105,1,1),(278530,278529,11105,1,1),(278532,278531,11105,1,1),(282020,282019,11105,1,1),(282021,282019,11105,1,1),(282023,282022,11105,1,1),(282024,282022,11105,1,1),(282026,282025,11105,1,1),(282028,282027,11105,1,1),(282030,282029,11105,1,1),(282032,282031,11105,1,1),(282034,282033,11105,1,1),(282036,282035,11105,1,1),(282037,282035,11105,1,1),(282040,282038,11105,1,1),(282041,282039,11105,1,1),(282042,282039,11105,1,1),(282044,282043,11105,1,1),(282046,282045,11105,1,1),(282047,282045,11105,1,1),(282050,282048,11105,1,1),(282051,282049,11105,1,1),(282052,282049,11105,1,1),(282054,282053,11105,1,1),(282057,282056,11105,1,1),(282058,282056,11105,1,1),(282060,282059,11105,1,1),(282061,282059,11105,1,1),(282063,282062,11105,1,1),(282065,282064,11105,1,1),(282067,282066,11105,1,1),(282069,282068,11105,1,1),(303802,19712,11105,1,1);
/*!40000 ALTER TABLE `agenda_ressourceuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `amis_friendfolder`
--

DROP TABLE IF EXISTS `amis_friendfolder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `amis_friendfolder` (
  `friendFolderId` bigint(20) NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `parentFolderId` bigint(20) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`friendFolderId`),
  KEY `IX_4D5F1242` (`parentFolderId`),
  KEY `IX_833BEB7C` (`userId`,`parentFolderId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `amis_friendfolder`
--

LOCK TABLES `amis_friendfolder` WRITE;
/*!40000 ALTER TABLE `amis_friendfolder` DISABLE KEYS */;
/*!40000 ALTER TABLE `amis_friendfolder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `amis_relationship`
--

DROP TABLE IF EXISTS `amis_relationship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `amis_relationship` (
  `relationId` bigint(20) NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `friendId` bigint(20) DEFAULT NULL,
  `stateId` int(11) DEFAULT NULL,
  `friendFolderId` bigint(20) DEFAULT NULL,
  `message` varchar(600) DEFAULT NULL,
  PRIMARY KEY (`relationId`),
  KEY `IX_F2245974` (`friendId`),
  KEY `IX_EE8CF2C8` (`friendId`,`stateId`),
  KEY `IX_93B5261` (`userId`),
  KEY `IX_A0D6BE40` (`userId`,`friendFolderId`,`stateId`),
  KEY `IX_A1A0DA2E` (`userId`,`friendId`),
  KEY `IX_207CA34E` (`userId`,`friendId`,`stateId`),
  KEY `IX_BDCAB47B` (`userId`,`stateId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `amis_relationship`
--

LOCK TABLES `amis_relationship` WRITE;
/*!40000 ALTER TABLE `amis_relationship` DISABLE KEYS */;
/*!40000 ALTER TABLE `amis_relationship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `announcementsdelivery`
--

DROP TABLE IF EXISTS `announcementsdelivery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `announcementsdelivery` (
  `deliveryId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `type_` varchar(75) DEFAULT NULL,
  `email` tinyint(4) DEFAULT NULL,
  `sms` tinyint(4) DEFAULT NULL,
  `website` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`deliveryId`),
  UNIQUE KEY `IX_BA4413D5` (`userId`,`type_`),
  KEY `IX_6EDB9600` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `announcementsdelivery`
--

LOCK TABLES `announcementsdelivery` WRITE;
/*!40000 ALTER TABLE `announcementsdelivery` DISABLE KEYS */;
INSERT INTO `announcementsdelivery` VALUES (11201,10202,11105,'general',0,0,1),(11202,10202,11105,'news',0,0,1),(11203,10202,11105,'test',0,0,1),(269524,10202,269502,'general',0,0,1),(269525,10202,269502,'news',0,0,1),(269526,10202,269502,'test',0,0,1);
/*!40000 ALTER TABLE `announcementsdelivery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `announcementsentry`
--

DROP TABLE IF EXISTS `announcementsentry`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `announcementsentry` (
  `uuid_` varchar(75) DEFAULT NULL,
  `entryId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `classNameId` bigint(20) DEFAULT NULL,
  `classPK` bigint(20) DEFAULT NULL,
  `title` varchar(75) DEFAULT NULL,
  `content` longtext,
  `url` longtext,
  `type_` varchar(75) DEFAULT NULL,
  `displayDate` datetime DEFAULT NULL,
  `expirationDate` datetime DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `alert` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`entryId`),
  KEY `IX_A6EF0B81` (`classNameId`,`classPK`),
  KEY `IX_14F06A6B` (`classNameId`,`classPK`,`alert`),
  KEY `IX_D49C2E66` (`userId`),
  KEY `IX_1AFBDE08` (`uuid_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `announcementsentry`
--

LOCK TABLES `announcementsentry` WRITE;
/*!40000 ALTER TABLE `announcementsentry` DISABLE KEYS */;
/*!40000 ALTER TABLE `announcementsentry` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `announcementsflag`
--

DROP TABLE IF EXISTS `announcementsflag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `announcementsflag` (
  `flagId` bigint(20) NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `entryId` bigint(20) DEFAULT NULL,
  `value` int(11) DEFAULT NULL,
  PRIMARY KEY (`flagId`),
  UNIQUE KEY `IX_4539A99C` (`userId`,`entryId`,`value`),
  KEY `IX_9C7EB9F` (`entryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `announcementsflag`
--

LOCK TABLES `announcementsflag` WRITE;
/*!40000 ALTER TABLE `announcementsflag` DISABLE KEYS */;
/*!40000 ALTER TABLE `announcementsflag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `applicationmanager_amfilter`
--

DROP TABLE IF EXISTS `applicationmanager_amfilter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `applicationmanager_amfilter` (
  `filterId` bigint(20) NOT NULL,
  `parentId` bigint(20) DEFAULT NULL,
  `criteria` varchar(75) DEFAULT NULL,
  `operator` varchar(75) DEFAULT NULL,
  `value` bigint(20) DEFAULT NULL,
  `populationId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`filterId`),
  KEY `IX_5EE55CE5` (`parentId`),
  KEY `IX_D793C708` (`populationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applicationmanager_amfilter`
--

LOCK TABLES `applicationmanager_amfilter` WRITE;
/*!40000 ALTER TABLE `applicationmanager_amfilter` DISABLE KEYS */;
INSERT INTO `applicationmanager_amfilter` VALUES (19557,19554,'userRoleNat','equal',11120,19553),(39921,39920,'etab','equal',18210,39919);
/*!40000 ALTER TABLE `applicationmanager_amfilter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `applicationmanager_amfiltercontainer`
--

DROP TABLE IF EXISTS `applicationmanager_amfiltercontainer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `applicationmanager_amfiltercontainer` (
  `filterContainerId` bigint(20) NOT NULL,
  `parentId` bigint(20) DEFAULT NULL,
  `populationId` bigint(20) DEFAULT NULL,
  `operator` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`filterContainerId`),
  KEY `IX_95BFEF7A` (`parentId`),
  KEY `IX_5BE4E736` (`parentId`,`populationId`),
  KEY `IX_2309071D` (`populationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applicationmanager_amfiltercontainer`
--

LOCK TABLES `applicationmanager_amfiltercontainer` WRITE;
/*!40000 ALTER TABLE `applicationmanager_amfiltercontainer` DISABLE KEYS */;
INSERT INTO `applicationmanager_amfiltercontainer` VALUES (11575,0,11574,'and'),(19554,0,19553,'and'),(39920,0,39919,'and');
/*!40000 ALTER TABLE `applicationmanager_amfiltercontainer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `applicationmanager_amsingleusers`
--

DROP TABLE IF EXISTS `applicationmanager_amsingleusers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `applicationmanager_amsingleusers` (
  `singleUserId` bigint(20) NOT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `populationId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`singleUserId`),
  KEY `IX_5F1E60D4` (`populationId`),
  KEY `IX_807FB5D2` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applicationmanager_amsingleusers`
--

LOCK TABLES `applicationmanager_amsingleusers` WRITE;
/*!40000 ALTER TABLE `applicationmanager_amsingleusers` DISABLE KEYS */;
INSERT INTO `applicationmanager_amsingleusers` VALUES (11576,11105,11574);
/*!40000 ALTER TABLE `applicationmanager_amsingleusers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `applicationmanager_applicationmanagerpopulation`
--

DROP TABLE IF EXISTS `applicationmanager_applicationmanagerpopulation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `applicationmanager_applicationmanagerpopulation` (
  `populationId` bigint(20) NOT NULL,
  `nom` varchar(75) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`populationId`),
  KEY `IX_4A7F37D6` (`companyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applicationmanager_applicationmanagerpopulation`
--

LOCK TABLES `applicationmanager_applicationmanagerpopulation` WRITE;
/*!40000 ALTER TABLE `applicationmanager_applicationmanagerpopulation` DISABLE KEYS */;
INSERT INTO `applicationmanager_applicationmanagerpopulation` VALUES (11574,'pentila',10202),(19553,'Chef étab',10202),(39919,'Tous',10202);
/*!40000 ALTER TABLE `applicationmanager_applicationmanagerpopulation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `applicationmanager_applicationmanagerroles`
--

DROP TABLE IF EXISTS `applicationmanager_applicationmanagerroles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `applicationmanager_applicationmanagerroles` (
  `managerAppliRoleId` bigint(20) NOT NULL,
  `populationId` bigint(20) DEFAULT NULL,
  `roleApplicatifId` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`managerAppliRoleId`),
  KEY `IX_3E307CE0` (`populationId`),
  KEY `IX_32A4A03A` (`roleApplicatifId`,`populationId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applicationmanager_applicationmanagerroles`
--

LOCK TABLES `applicationmanager_applicationmanagerroles` WRITE;
/*!40000 ALTER TABLE `applicationmanager_applicationmanagerroles` DISABLE KEYS */;
INSERT INTO `applicationmanager_applicationmanagerroles` VALUES (11577,11574,11227),(39923,39919,11227),(11578,11574,11231),(39924,39919,11231),(129210,11574,11235),(39925,39919,11235),(15604,11574,11239),(39927,39919,11239),(11581,11574,11243),(129224,39919,11243),(129212,11574,11247),(39928,39919,11247),(11584,11574,11251),(39929,39919,11251),(11886,11574,11255),(39930,39919,11255),(11585,11574,11259),(39931,39919,11259),(129213,11574,11263),(129228,39919,11263),(11586,11574,11267),(129229,39919,11267),(11587,11574,11271),(129236,39919,11271),(129218,11574,11275),(19562,19553,11275),(129238,39919,11275),(11589,11574,11279),(129239,39919,11279),(11590,11574,11283),(19564,19553,11283),(129240,39919,11283),(11591,11574,11287),(19565,19553,11287),(129241,39919,11287),(129219,11574,11291),(129243,39919,11291),(11592,11574,11295),(39932,39919,11295),(11593,11574,11299),(39933,39919,11299),(11823,11574,11303),(39934,39919,11303),(11595,11574,11307),(129246,39919,11307),(129222,11574,11315),(129248,39919,11315),(129223,11574,11319),(129249,39919,11319),(11598,11574,11323),(129250,39919,11323),(129209,11574,19574),(39926,39919,19574),(41731,11574,26447),(129225,39919,26447),(26501,11574,26451),(129226,39919,26451),(36793,11574,36760),(129227,39919,36760),(45932,11574,45916),(129242,39919,45916),(111504,11574,111401),(111905,39919,111401),(111903,11574,111405),(111904,39919,111405),(129211,11574,118003),(118018,39919,118003),(129217,11574,128301),(128315,39919,128301),(135571,39919,134403),(142515,11574,142501),(157731,11574,157700),(169622,11574,169221),(170101,11574,169801),(173318,39919,172805),(175079,39919,175009),(177453,11574,177424);
/*!40000 ALTER TABLE `applicationmanager_applicationmanagerroles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assetcategory`
--

DROP TABLE IF EXISTS `assetcategory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assetcategory` (
  `uuid_` varchar(75) DEFAULT NULL,
  `categoryId` bigint(20) NOT NULL,
  `groupId` bigint(20) DEFAULT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `parentCategoryId` bigint(20) DEFAULT NULL,
  `leftCategoryId` bigint(20) DEFAULT NULL,
  `rightCategoryId` bigint(20) DEFAULT NULL,
  `name` varchar(75) DEFAULT NULL,
  `title` longtext,
  `vocabularyId` bigint(20) DEFAULT NULL,
  `description` longtext,
  PRIMARY KEY (`categoryId`),
  UNIQUE KEY `IX_BE4DF2BF` (`parentCategoryId`,`name`,`vocabularyId`),
  UNIQUE KEY `IX_E8D019AA` (`uuid_`,`groupId`),
  KEY `IX_E639E2F6` (`groupId`),
  KEY `IX_D61ABE08` (`name`,`vocabularyId`),
  KEY `IX_7BB1826B` (`parentCategoryId`),
  KEY `IX_9DDD15EA` (`parentCategoryId`,`name`),
  KEY `IX_B185E980` (`parentCategoryId`,`vocabularyId`),
  KEY `IX_4D37BB00` (`uuid_`),
  KEY `IX_287B1F89` (`vocabularyId`),
  KEY `IX_510B46AC` (`groupId`,`parentCategoryId`,`name`),
  KEY `IX_2008FACB` (`groupId`,`vocabularyId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assetcategory`
--

LOCK TABLES `assetcategory` WRITE;
/*!40000 ALTER TABLE `assetcategory` DISABLE KEYS */;
/*!40000 ALTER TABLE `assetcategory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assetcategoryproperty`
--

DROP TABLE IF EXISTS `assetcategoryproperty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assetcategoryproperty` (
  `categoryPropertyId` bigint(20) NOT NULL,
  `companyId` bigint(20) DEFAULT NULL,
  `userId` bigint(20) DEFAULT NULL,
  `userName` varchar(75) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL,
  `modifiedDate` datetime DEFAULT NULL,
  `categoryId` bigint(20) DEFAULT NULL,
  `key_` varchar(75) DEFAULT NULL,
  `value` varchar(75) DEFAULT NULL,
  PRIMARY KEY (`categoryPropertyId`),
  UNIQUE KEY `IX_DBD111AA` (`categoryId`,`key_`),
  KEY `IX_99DA856` (`categoryId`),
  KEY `IX_8654719F` (`companyId`),
  KEY `IX_52340033` (`companyId`,`key_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assetcategoryproperty`
--

LOCK TABLES `assetcategoryproperty` WRITE;
/*!40000 ALTER TABLE `assetcategoryproperty` DISABLE KEYS */;
/*!40000 ALTER TABLE `assetcategoryproperty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assetentries_assetcategories`
--

DROP TABLE IF EXISTS `assetentries_assetcategories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assetentries_assetcategories` (
  `entryId` bigint(20) NOT NULL,
  `categoryId` bigint(20) NOT NULL,
  PRIMARY KEY (`entryId`,`categoryId`),
  KEY `IX_A188F560` (`categoryId`),
  KEY `IX_E119938A` (`entryId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assetentries_assetcategories`
--

LOCK TABLES `assetentries_assetcategories` WRITE;
/*!40000 ALTER TABLE `assetentries_assetcategories` DISABLE KEYS */;
/*!40000 ALTER TABLE `assetentries_assetcategories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assetentries_assettags`
--

DROP TABLE IF EXISTS `assetentries_assettags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `assetentries_assettags` (
  `entryId` bigint(20) NOT NULL,
  `tagId` bigint(20) NOT NULL,
  PRIMARY KEY (`entryId`,`tagId`),
  KEY `IX_2ED82CAD` (`entryId`),
  KEY `IX_B2A61B55` (`tagId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assetentries_assettags`
--

LOCK TABLES `assetentries_assettags` WRITE;
/*!40000 ALTER TABLE `assetentries_assettags` DISABLE KEYS */;
/*!40000 ALTER TABLE `assetentries_assettags` ENABLE KEYS */;
UNLOCK TABLES;



...






