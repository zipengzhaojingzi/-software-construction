/*
Navicat MySQL Data Transfer

Source Server         : 数据库
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : petadoption

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2025-04-29 15:08:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `adminId` varchar(64) NOT NULL,
  `adminAccount` varchar(64) DEFAULT NULL,
  `adminPassword` varchar(64) DEFAULT NULL,
  `adminName` varchar(64) DEFAULT NULL,
  `adminAge` varchar(64) DEFAULT NULL,
  `adminSex` varchar(64) DEFAULT NULL,
  `adminTelephone` varchar(64) DEFAULT NULL,
  `adminEmail` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`adminId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_apply
-- ----------------------------
DROP TABLE IF EXISTS `t_apply`;
CREATE TABLE `t_apply` (
  `applyId` varchar(64) NOT NULL,
  `applyUserName` varchar(64) DEFAULT NULL,
  `applyPetName` varchar(64) DEFAULT NULL,
  `applyUserSex` varchar(64) DEFAULT NULL,
  `applyUserAddress` varchar(64) DEFAULT NULL,
  `applyUserState` varchar(64) DEFAULT NULL,
  `applyUserTelephone` varchar(64) DEFAULT NULL,
  `applyTime` varchar(64) DEFAULT NULL,
  `applyState` varchar(64) DEFAULT NULL,
  `applyUserId` varchar(64) DEFAULT NULL,
  `applyPetId` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`applyId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_event
-- ----------------------------
DROP TABLE IF EXISTS `t_event`;
CREATE TABLE `t_event` (
  `eventId` varchar(64) NOT NULL,
  `eventName` varchar(64) DEFAULT NULL,
  `eventDescription` varchar(255) DEFAULT NULL,
  `eventDate` varchar(64) DEFAULT NULL,
  `eventLocation` varchar(64) DEFAULT NULL,
  `eventImage` varchar(64) DEFAULT NULL,
  `eventApplyNum` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`eventId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_log
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `id` int(11) NOT NULL,
  `aId` varchar(64) DEFAULT NULL,
  `adminAction` varchar(64) DEFAULT NULL,
  `object` varchar(64) DEFAULT NULL,
  `createTime` varchar(64) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_pet
-- ----------------------------
DROP TABLE IF EXISTS `t_pet`;
CREATE TABLE `t_pet` (
  `petId` varchar(64) NOT NULL,
  `petName` varchar(64) DEFAULT NULL,
  `petSub` varchar(64) DEFAULT NULL,
  `petSex` varchar(64) DEFAULT NULL,
  `petType` varchar(64) DEFAULT NULL,
  `petBir` varchar(64) DEFAULT NULL,
  `petDetail` varchar(255) DEFAULT NULL,
  `petPic` varchar(64) DEFAULT NULL,
  `petState` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`petId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `userId` varchar(64) NOT NULL,
  `userAccount` varchar(64) DEFAULT NULL,
  `userPassword` varchar(64) DEFAULT NULL,
  `userName` varchar(64) DEFAULT NULL,
  `userAge` varchar(64) DEFAULT NULL,
  `userSex` varchar(64) DEFAULT NULL,
  `userTelephone` varchar(64) DEFAULT NULL,
  `userEmail` varchar(64) DEFAULT NULL,
  `userAddress` varchar(64) DEFAULT NULL,
  `userState` varchar(64) DEFAULT NULL,
  `identity` varchar(64) DEFAULT 'user',
  PRIMARY KEY (`userId`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_userlog
-- ----------------------------
DROP TABLE IF EXISTS `t_userlog`;
CREATE TABLE `t_userlog` (
  `id` int(11) NOT NULL,
  `userId` varchar(64) DEFAULT NULL,
  `userAction` varchar(64) DEFAULT NULL,
  `petId` varchar(64) DEFAULT NULL,
  `craeteTime` varchar(64) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user_event
-- ----------------------------
DROP TABLE IF EXISTS `t_user_event`;
CREATE TABLE `t_user_event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  `event_id` varchar(64) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
