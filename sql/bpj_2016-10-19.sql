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
-- Table structure for table `bpj_friend`
--

DROP TABLE IF EXISTS `bpj_friend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bpj_friend` (
  `user_id` varchar(30) DEFAULT NULL,
  `friend_user_id` varchar(30) DEFAULT NULL,
  `friend_comment_name` varchar(30) DEFAULT NULL,
  `friend_group_id` varchar(30) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `delete_by` varchar(50) DEFAULT NULL,
  `delete_date` datetime DEFAULT NULL,
  `is_deleted` int(1) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bpj_friend`
--

LOCK TABLES `bpj_friend` WRITE;
/*!40000 ALTER TABLE `bpj_friend` DISABLE KEYS */;
INSERT INTO `bpj_friend` VALUES ('24732322397945855','24726539291590656','Lucy_love','14726539291590621',NULL,NULL,NULL,NULL,NULL,NULL,0),('24732322397945855','24726539291590664','Marry_sis','14226539291590522',NULL,NULL,NULL,NULL,NULL,NULL,0),('24732322397945855','24726539291590663','Tom_bro','14226539291590522',NULL,NULL,NULL,NULL,NULL,NULL,0),('24732322397945855','24768578515369986','晓晓','14226539291590522',NULL,NULL,NULL,NULL,NULL,NULL,0),('24732322397945855','24732322397945856','test_friend','14726539291590621',NULL,NULL,NULL,NULL,NULL,NULL,0),('24732322397945855','24732322397945855','Jacky','14226539291590522',NULL,NULL,NULL,NULL,NULL,NULL,0),('24726539291590656','24732322397945855','Jacky','24768578515369984',NULL,NULL,NULL,NULL,NULL,NULL,0),('24726539291590663','24732322397945855','Jack','24777281478066176',NULL,NULL,NULL,NULL,NULL,NULL,0);
/*!40000 ALTER TABLE `bpj_friend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bpj_friend_group`
--

DROP TABLE IF EXISTS `bpj_friend_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bpj_friend_group` (
  `group_id` varchar(30) NOT NULL,
  `group_name` varchar(50) DEFAULT '未命名',
  `group_priority` int(2) DEFAULT '99',
  `user_id` varchar(30) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `delete_by` varchar(50) DEFAULT NULL,
  `delete_date` datetime DEFAULT NULL,
  `is_deleted` int(1) DEFAULT '0',
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bpj_friend_group`
--

LOCK TABLES `bpj_friend_group` WRITE;
/*!40000 ALTER TABLE `bpj_friend_group` DISABLE KEYS */;
INSERT INTO `bpj_friend_group` VALUES ('14226539291590522','我的好友',2,'24732322397945855',NULL,NULL,'Jack','2016-10-12 17:13:15',NULL,NULL,0),('14726539291590621','测试群',1,'24732322397945855',NULL,NULL,'Jack','2016-10-12 17:12:14',NULL,NULL,0),('24767126396993540','我的好友',3,'24732322397945855','Jack','2016-10-12 16:54:33','Jack','2016-10-12 16:54:33',NULL,NULL,1),('24767126396993541','我的好友',1,'24726539291590656','Lucy','2016-10-12 17:15:15','Lucy','2016-10-12 17:15:15',NULL,NULL,1),('24767126396993542','我的好友',2,'24726539291590656','Lucy','2016-10-12 17:15:52','Lucy','2016-10-12 17:15:52',NULL,NULL,1),('24768578515369984','我的好友',1,'24726539291590656','Lucy','2016-10-13 11:29:56','Lucy','2016-10-13 11:29:56',NULL,NULL,0),('24768578515369985','QQ联系人',2,'24726539291590656','Lucy','2016-10-13 11:35:56','Lucy','2016-10-13 11:35:56',NULL,NULL,0),('24777281478066176','我的好友',1,'24726539291590663','Tom','2016-10-19 10:33:20','Tom','2016-10-19 10:33:20',NULL,NULL,0);
/*!40000 ALTER TABLE `bpj_friend_group` ENABLE KEYS */;
UNLOCK TABLES;

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
  `parent_id` varchar(30) DEFAULT NULL,
  `is_parent` int(1) DEFAULT '0',
  `icon` varchar(40) DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_by` varchar(50) DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `delete_by` varchar(50) DEFAULT NULL,
  `delete_date` datetime DEFAULT NULL,
  `is_deleted` int(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_menu`
--

LOCK TABLES `security_menu` WRITE;
/*!40000 ALTER TABLE `security_menu` DISABLE KEYS */;
INSERT INTO `security_menu` VALUES ('1','sys_management','系统管理','SystemManage','javascript:;',1,NULL,1,' fa-gears (alias)',NULL,NULL,'Jack','2016-10-09 13:52:07',NULL,NULL,0),('2','front_management','前台设置','FrontManage','javascript:;',99,NULL,1,' fa-dashboard (alias)',NULL,NULL,'Jack','2016-10-09 12:58:14',NULL,NULL,0),('24762775578345502','前台页面1','前台页面1','FontPage1','/management/security/user',99,'2',0,' fa-bookmark','Jack','2016-10-09 12:59:06','Jack','2016-10-09 13:21:36',NULL,NULL,0),('24762775578345504','聊天测试','聊天测试','Chat','/management/onlineChat/chat',99,'2',0,' fa-comments','Jack','2016-10-09 15:28:32','Jack','2016-10-12 14:52:27',NULL,NULL,0),('24764226656534528','聊天测试2','聊天测试2','Chat2','/management/onlineChat/chat2',99,'2',0,' fa-comment-o','Jack','2016-10-10 09:57:47','Jack','2016-10-10 10:00:57',NULL,NULL,0),('24765683808075776','我的好友','我的好友','FriendManage','javascript:;',2,NULL,1,' fa-group (alias)','Jack','2016-10-11 10:01:41','Jack','2016-10-11 14:18:32',NULL,NULL,0),('24765683808075778','好友列表','好友列表','Friend','/management/security/friend',1,'24765683808075776',0,' fa-bars','Jack','2016-10-11 14:17:35','Jack','2016-10-11 16:55:30',NULL,NULL,0),('24767126396993536','群组管理','群组管理','FriendGroup','/management/security/friendGroup',99,'24765683808075776',0,' fa-building-o','Jack','2016-10-12 15:20:19','Jack','2016-10-12 15:20:39',NULL,NULL,0),('3','user_management','用户管理','User','/management/security/user',2,'1',0,' fa-user',NULL,NULL,'Jack','2016-10-11 10:02:07',NULL,NULL,0),('4','role_management','角色管理','Role','/management/security/role',3,'1',0,' fa-puzzle-piece',NULL,NULL,'Jack','2016-10-09 12:53:16',NULL,NULL,0),('5','right_management','权限管理','Right','/management/security/right',4,'1',0,' fa-unlock-alt',NULL,NULL,'Jack','2016-10-09 12:56:13',NULL,NULL,0),('6','menu_management','菜单管理','Menu','/management/security/menu',5,'1',0,' fa-sitemap',NULL,NULL,'Jack','2016-10-09 12:56:51',NULL,NULL,0);
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
  `is_deleted` int(1) unsigned DEFAULT '0',
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
INSERT INTO `security_right` VALUES ('24761328442802182','create','/',NULL,'Jack','2016-10-08 10:52:14','Jack','2016-10-08 10:52:14',NULL,0,NULL,'Role'),('24761328442802183','modify','/',NULL,'Jack','2016-10-08 10:54:12','Jack','2016-10-08 10:54:12',NULL,0,NULL,'Role'),('24761328442802184','delete','/',NULL,'Jack','2016-10-08 10:54:31','Jack','2016-10-08 10:54:31',NULL,0,NULL,'Role'),('24761328442802185','create','/',NULL,'Jack','2016-10-08 10:55:52','Jack','2016-10-08 10:55:52',NULL,0,NULL,'Right'),('24761328442802186','modify','/',NULL,'Jack','2016-10-08 10:56:03','Jack','2016-10-08 10:56:03',NULL,0,NULL,'Right'),('24761328442802187','delete','/',NULL,'Jack','2016-10-08 10:56:22','Jack','2016-10-08 10:56:22',NULL,0,NULL,'Right'),('24761328442802188','create','/',NULL,'Jack','2016-10-08 10:56:52','Jack','2016-10-08 10:56:52',NULL,0,NULL,'User'),('24761328442802189','modify','/',NULL,'Jack','2016-10-08 10:57:13','Jack','2016-10-08 10:57:13',NULL,0,NULL,'User'),('24761328442802190','view','/',NULL,'Jack','2016-10-08 10:57:45','Jack','2016-10-08 10:57:45',NULL,0,NULL,'User'),('24761328442802191','delete','/',NULL,'Jack','2016-10-08 10:58:04','Jack','2016-10-08 10:58:04',NULL,0,NULL,'User'),('24761328442802192','view','/',NULL,'Jack','2016-10-08 11:09:38','Jack','2016-10-08 11:09:38',NULL,0,NULL,'SystemManage'),('24761328442802193','view','/',NULL,'Jack','2016-10-08 11:10:02','Jack','2016-10-08 11:10:02',NULL,0,NULL,'FrontManage'),('24761328442802194','modify','/',NULL,'Jack','2016-10-08 11:10:29','Jack','2016-10-08 11:10:29',NULL,0,NULL,'SystemManage'),('24761328442802195','view','/',NULL,'Jack','2016-10-08 11:11:14','Jack','2016-10-08 11:11:14',NULL,0,NULL,'Role'),('24761328442802196','view','/',NULL,'Jack','2016-10-08 11:11:33','Jack','2016-10-08 11:39:58',NULL,0,NULL,'Right'),('24761328442802198','view','/',NULL,'Jack','2016-10-08 13:49:10','Jack','2016-10-08 13:49:10',NULL,0,NULL,'Menu'),('24762775578345503','view','/',NULL,'Jack','2016-10-09 12:59:20','Jack','2016-10-09 12:59:20',NULL,0,NULL,'FontPage1'),('24762775578345505','view','/',NULL,'Jack','2016-10-09 15:28:44','Jack','2016-10-09 15:28:44',NULL,0,NULL,'Chat'),('24764226656534529','view','/',NULL,'Jack','2016-10-10 09:58:05','Jack','2016-10-10 09:58:05',NULL,0,NULL,'Chat2'),('24765683808075777','view','/',NULL,'Jack','2016-10-11 10:02:34','Jack','2016-10-11 10:02:34',NULL,0,NULL,'Friend'),('24765683808075779','view','/',NULL,'Jack','2016-10-11 14:18:46','Jack','2016-10-11 14:18:46',NULL,0,NULL,'FriendManage'),('24767126396993537','view','/',NULL,'Jack','2016-10-12 15:21:01','Lucy','2016-10-12 17:17:08',NULL,0,NULL,'FriendGroup');
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
  `is_deleted` int(1) DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_name` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `security_role`
--

LOCK TABLES `security_role` WRITE;
/*!40000 ALTER TABLE `security_role` DISABLE KEYS */;
INSERT INTO `security_role` VALUES ('24748296958377985','Lucy','Jack','2016-09-14 13:31:07','Jack','2016-10-08 11:13:47',NULL,NULL,0),('24748296958377986','system','Jack','2016-09-14 13:31:07','Lucy','2016-10-08 11:14:49',NULL,NULL,0),('24748296958377987','admin','Jack','2016-09-29 10:40:40','Jack','2016-09-29 10:40:40',NULL,NULL,0),('24748296958377988','commonUser','Jack','2016-09-29 10:50:01','Lucy','2016-10-19 10:37:24',NULL,NULL,0);
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
INSERT INTO `security_role_right_rel` VALUES ('24748296958377986','24761328442802192'),('24748296958377986','24761328442802182'),('24748296958377986','24761328442802183'),('24748296958377986','24761328442802184'),('24748296958377986','24761328442802190'),('24748296958377986','24761328442802188'),('24748296958377986','24761328442802189'),('24748296958377986','24761328442802191'),('24748296958377986','24761328442802195'),('24748296958377986','24761328442802196'),('24748296958377986','24761328442802185'),('24748296958377986','24761328442802186'),('24748296958377986','24761328442802187'),('24748296958377986','24761328442802193'),('1','24761328442802192'),('1','24761328442802190'),('1','24761328442802188'),('1','24761328442802189'),('1','24761328442802195'),('1','24761328442802182'),('1','24761328442802183'),('1','24761328442802196'),('1','24761328442802193'),('1','24761328442802185'),('1','24761328442802186'),('24748296958377985','24761328442802192'),('24748296958377985','24761328442802190'),('24748296958377985','24761328442802188'),('24748296958377985','24761328442802195'),('24748296958377985','24761328442802183'),('24748296958377985','24761328442802196'),('24748296958377985','24761328442802185'),('24748296958377985','24761328442802186'),('24748296958377986','24761328442802198'),('24748296958377986','24762775578345503'),('24748296958377985','24761328442802193'),('24748296958377985','24762775578345503'),('24748296958377986','24762775578345505'),('24748296958377986','24764226656534529'),('24748296958377986','24765683808075777'),('24748296958377986','24765683808075779'),('24748296958377986','24767126396993537'),('24748296958377988','24765683808075779'),('24748296958377988','24765683808075777'),('24748296958377988',''),('24748296958377988',''),('24748296958377988',''),('24748296958377988','24767126396993537'),('24748296958377988',''),('24748296958377988',''),('24748296958377988','');
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
  `is_deleted` int(1) DEFAULT '0',
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
INSERT INTO `security_user` VALUES ('24726539291590656','Lucy','c7d97c6fec51cf796c5924c8cf43830dacbc4179','ca9e68226e732a0d','612326199306086511','13089896512','123455','1023459877@qq.com','Jack','2016-09-14 13:31:07','Jack','2016-10-09 14:01:48',NULL,0,NULL,1,'20160910020148336.jpg',1),('24726539291590663','Tom','7f2f649fcedd04173c9f80b6f9f6ba740079c172','fbb988893939f785','612327199302098710','13212321312','102321123','123213@qq.com','Jack','2016-09-14 14:34:33','Jack','2016-10-09 14:01:58',NULL,0,NULL,1,'20160910020157839.jpg',1),('24726539291590664','Marry','f5cd918949234d139726fc62c514f49d07fee7c6','0fdad86bd3fdf5ab','612326100309086511','13212321312','1221212','123213@qq.com','Jack','2016-09-14 14:35:15','Jack','2016-10-09 14:02:11',NULL,0,NULL,0,'20160910020210951.jpg',1),('24732322397945855','Jack','b6123feca70fbed23d858048bc333cf9c7b07597','696b4eebec49b9ce','612326199306086528','13898987621','1232321212','1023459876@qq.com',NULL,NULL,'Jack','2016-10-09 10:14:18',NULL,0,NULL,1,'20160910101418154.jpg',1),('24732322397945856','asdasd','a72dd19cf5c83e89d42d5b2440d0f982e28c3900','9be432bc0625dded','612326199309086137','13212321332','12311111','123aaa@qq.com','Jack','2016-09-18 10:25:11','Jack','2016-10-09 14:02:24',NULL,0,NULL,0,'20160910020223597.jpg',1),('24768578515369986','陈晓晓','edac07e75de4b609c3f730cc5ada5160df2d65c1','72bc1220d017a829','612326199308056519','','','','Jack','2016-10-13 16:08:42','Jack','2016-10-13 16:08:42',NULL,0,NULL,0,'20161310040841905.jpg',1);
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
INSERT INTO `security_user_role_rel` VALUES ('24732322397945856','24748296958377987'),('24732322397945855','24748296958377986'),('24726539291590656','24748296958377986'),('24726539291590663','24748296958377988');
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

-- Dump completed on 2016-10-19 11:11:37
