CREATE DATABASE  IF NOT EXISTS `bpj` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bpj`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: localhost    Database: bpj
-- ------------------------------------------------------
-- Server version	5.7.3-m13

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
-- Table structure for table `security_menu`
--

DROP TABLE IF EXISTS `security_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_menu` (
  `id` varchar(30) NOT NULL,
  `description` varchar(30) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `right_sn` varchar(20) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `pratent_id` varchar(30) DEFAULT NULL,
  `is_parent` int(1) DEFAULT '0',
  `icon` varchar(40) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `delete_by` varchar(50) DEFAULT NULL,
  `delete_date` datetime DEFAULT NULL,
  `is_delete` int(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_menu`
--

LOCK TABLES `security_menu` WRITE;
/*!40000 ALTER TABLE `security_menu` DISABLE KEYS */;
/*!40000 ALTER TABLE `security_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `security_right`
--

DROP TABLE IF EXISTS `security_right`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_right` (
  `id` varchar(30) NOT NULL,
  `right_name` varchar(20) NOT NULL,
  `right_url` varchar(300) NOT NULL,
  `right_icon` varchar(300) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `delete_by` varchar(50) DEFAULT NULL,
  `is_delete` int(1) DEFAULT '0',
  `delete_date` datetime DEFAULT NULL,
  `right_sign` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_right`
--

LOCK TABLES `security_right` WRITE;
/*!40000 ALTER TABLE `security_right` DISABLE KEYS */;
INSERT INTO `security_right` VALUES ('1','create','createUserUrl',NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,'User'),('2','modify','modifyUserUrl',NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,'User'),('3','view','viewUserUrl',NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,'User'),('4','delete','deleteUrl',NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,'User');
/*!40000 ALTER TABLE `security_right` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `security_role`
--

DROP TABLE IF EXISTS `security_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_role` (
  `id` varchar(30) NOT NULL,
  `role_name` varchar(50) NOT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `delete_by` varchar(50) DEFAULT NULL,
  `delete_date` datetime DEFAULT NULL,
  `is_delete` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_name` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_role`
--

LOCK TABLES `security_role` WRITE;
/*!40000 ALTER TABLE `security_role` DISABLE KEYS */;
INSERT INTO `security_role` VALUES ('1','test',NULL,NULL,NULL,NULL,NULL,NULL,NULL),('2','system',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `security_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `security_role_right_rel`
--

DROP TABLE IF EXISTS `security_role_right_rel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_role_right_rel` (
  `role_id` varchar(30) DEFAULT NULL,
  `right_id` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_role_right_rel`
--

LOCK TABLES `security_role_right_rel` WRITE;
/*!40000 ALTER TABLE `security_role_right_rel` DISABLE KEYS */;
INSERT INTO `security_role_right_rel` VALUES ('1','3'),('2','1'),('2','2'),('2','3');
/*!40000 ALTER TABLE `security_role_right_rel` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `security_user`
--

DROP TABLE IF EXISTS `security_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_user` (
  `id` varchar(30) NOT NULL COMMENT 'ID',
  `user_name` varchar(50) NOT NULL COMMENT 'name',
  `passwd_plaintext` varchar(100) NOT NULL COMMENT 'password_plaintext',
  `passwd_salt` varchar(100) NOT NULL COMMENT 'password_salt',
  `id_card` varchar(18) NOT NULL,
  `tel_no` varchar(11) DEFAULT NULL,
  `qq_no` varchar(15) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `delete_by` varchar(50) DEFAULT NULL,
  `is_delete` int(1) DEFAULT '0',
  `delete_date` datetime DEFAULT NULL,
  `login_state` int(1) DEFAULT '0',
  `headpic_path` varchar(150) DEFAULT NULL,
  `status` int(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_card` (`id_card`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_user`
--

LOCK TABLES `security_user` WRITE;
/*!40000 ALTER TABLE `security_user` DISABLE KEYS */;
INSERT INTO `security_user` VALUES ('1','Jack','b6123feca70fbed23d858048bc333cf9c7b07597','696b4eebec49b9ce','123456',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,0,'Jack1001.jpg',1),('2','Lucy','123456','','123457',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,0,NULL,0,NULL,1);
/*!40000 ALTER TABLE `security_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `security_user_role_rel`
--

DROP TABLE IF EXISTS `security_user_role_rel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `security_user_role_rel` (
  `user_id` varchar(30) DEFAULT NULL,
  `role_id` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_user_role_rel`
--

LOCK TABLES `security_user_role_rel` WRITE;
/*!40000 ALTER TABLE `security_user_role_rel` DISABLE KEYS */;
INSERT INTO `security_user_role_rel` VALUES ('1','1'),('1','2');
/*!40000 ALTER TABLE `security_user_role_rel` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-05 14:58:22
