/*
Navicat MySQL Data Transfer

Source Server         : 数据库
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : petadoption

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2025-04-24 17:17:50
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
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES ('1', 'admin', '123456', '梓', '12', '21', '21', '12');
INSERT INTO `t_admin` VALUES ('5e027992-7a6b-4c59-87d9-e54ec50afbd9', 'admin1', '1', '1', '1', '1', '1', '1');
INSERT INTO `t_admin` VALUES ('7716b38d-e602-40f4-a48f-9826fa023425', 'myx', '123456', '莫韵筱', '21', '女', '18868686868', '2229302376@qq.com');

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
-- Records of t_apply
-- ----------------------------
INSERT INTO `t_apply` VALUES ('00502177-e8d2-41b3-a668-38faa091da0a', '梓', 'Ling', '男', '21', '12', '有', '2024-12-11 08:24:01', '同意领养', '83a140f7-d035-4ca5-bda7-35a1fed63a25', '1');
INSERT INTO `t_apply` VALUES ('f149c3a7-7b0b-456f-833c-063577eab0d3', '郑桦', '蛙蛙', '女', '', '', '有领养经历', '2024-12-11 08:30:11', '同意领养', '5be2607c-36ef-498e-9c4a-6582786eb914', '987251c5-27af-4f77-9281-f852b03c2449');
INSERT INTO `t_apply` VALUES ('ffb1cf9b-4a0a-4a31-a837-21cf08d1eb09', '梓', '蛙蛙', '男', '21', '12', '有', '2024-12-11 08:41:46', '审核中', '83a140f7-d035-4ca5-bda7-35a1fed63a25', '987251c5-27af-4f77-9281-f852b03c2449');
INSERT INTO `t_apply` VALUES ('f8edfb73-b48b-4769-8747-287c7e93602d', '梓', '翠花', '男', '21', '12', '有', '2024-12-21 14:13:55', '审核中', '83a140f7-d035-4ca5-bda7-35a1fed63a25', '0d36298f-6bbd-4780-8dbf-cd1da51bae5e');
INSERT INTO `t_apply` VALUES ('da4335e1-a910-41bf-80be-49145c6fe62c', '梓', '小摩托', '男', '21', '12', '有', '2024-12-21 14:40:46', '审核中', '83a140f7-d035-4ca5-bda7-35a1fed63a25', '781ed743-7719-4074-bab5-013ed0a63b2e');
INSERT INTO `t_apply` VALUES ('a47513b2-76a6-4752-aa56-cbe1cc427699', 'test', '麒麟尾', '男', '1', '1', '无', '2024-12-21 17:36:41', '同意领养', 'aa7f5e16-7e06-4e0e-ae3f-36797dc7ed53', '2bf09c50-629f-456e-b61d-2dee8fc77755');
INSERT INTO `t_apply` VALUES ('50c2f04b-8e86-4167-8231-2551e6df6be1', '梓', '翠花', '男', '广西大学', '12', '有', '2025-04-15 17:39:07', '审核中', '83a140f7-d035-4ca5-bda7-35a1fed63a25', '0d36298f-6bbd-4780-8dbf-cd1da51bae5e');
INSERT INTO `t_apply` VALUES ('14a05469-a972-4870-8c09-2e0f1c2398fb', '梓', '贴贴', '男', '广西大学', '12', '有', '2025-04-15 17:39:33', '不同意领养', '83a140f7-d035-4ca5-bda7-35a1fed63a25', '0e956f50-6053-4a96-a876-b094bcc0ec58');

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
-- Records of t_event
-- ----------------------------
INSERT INTO `t_event` VALUES ('eb0a2712-0904-44a4-a5e9-d06598830754', '1', '世界破破烂烂，小动物缝缝补补，世界缝补计划聚焦流浪动物生存困境，通过媒体宣传，救助物资捐赠等一系列方式，支持行动者明确行动方向。活动以手抄报，明信片，绘画为载体；亦或者用自己的镜头记录下小动物的那一瞬间。现招募志愿者，来登记报名者信息，以及为报名者引导到自己的位置，并整理上交参加活动选手的作品。', '2025.7.8', '语言活动天台', '/upload/24b3456e-5ec2-41c6-ad15-8d191fd7e196.jpg', '0');
INSERT INTO `t_event` VALUES ('8592249a-498c-4591-9024-64208ded44b6', '小猫绝育小组', '为合适年龄的猫猫绝育，需要反应灵敏，手擒猫猫很厉害的小伙伴加入', '2025.3.14', '人民公园', '/upload/b547415a-7d1c-4361-89d0-37ed6fcc6ad2.jpg', '3');

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
-- Records of t_log
-- ----------------------------
INSERT INTO `t_log` VALUES ('0', null, '同意', '梓', '2024-12-11 08:24:18', '/Apply/agreePage');

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
-- Records of t_pet
-- ----------------------------
INSERT INTO `t_pet` VALUES ('0d36298f-6bbd-4780-8dbf-cd1da51bae5e', '翠花', '猫', '公', '银渐层', '2016-08-19', '              可爱乖巧                                                                         校内元老级猫猫，已知年纪最大，24年初杯状口炎爆发，并导致慢性肾衰，目前喂药维护中                                                                                                       ', '/upload/4ce4fb0b-324f-423c-885f-204ec364c57a.jpg', '未领养');
INSERT INTO `t_pet` VALUES ('987251c5-27af-4f77-9281-f852b03c2449', '布偶大王', '猫', '母', '布偶', '2022-11-09', '                                            旋饭狂魔，很亲人                                        ', '/upload/7443457e-acfd-4633-9674-53509124d726.jpg', '未领养');
INSERT INTO `t_pet` VALUES ('c1919bba-29be-4445-9355-4b6d23cf7494', '小果', '猫', '母', '狸花猫', '2024-01-31', '                                                               软萌好盘，撒娇求撸，喜欢贴贴                                                                       ', '/upload/8d8e4a2d-83d6-480b-b958-15c2316cd933.jpg', '未领养');
INSERT INTO `t_pet` VALUES ('781ed743-7719-4074-bab5-013ed0a63b2e', '小摩托', '公', null, '狸花猫', '2024-11-01', '                                            聪明机灵                                        ', '/upload/e93c6982-3912-4f8d-b9ce-2ae03d0f61fb.jpg', '未领养');
INSERT INTO `t_pet` VALUES ('535afb53-b9bf-450d-93a2-a54d7e68cc95', '小汤圆', '猫', '母', '', '', '胆小怂包', '/upload/ee7ddae9-660e-44fe-8bf9-7b9324fdc272.jpg', '未领养');
INSERT INTO `t_pet` VALUES ('2bf09c50-629f-456e-b61d-2dee8fc77755', '麒麟尾', '公', null, '狸花猫', '2024-09-11', '                                            碰瓷讨粮                                        ', '/upload/0fe19fda-3de8-46b3-82f9-a03058ae85c8.jpg', '已被领养');
INSERT INTO `t_pet` VALUES ('c2650a1d-68ce-4bdc-8b5c-f4a0993070d2', '牛奶妹', '公', null, '奶牛猫', '2022-01-07', '                                            热衷挑事                                        ', '/upload/776b43d8-80b1-46d6-84ee-30ae5c63e532.jpg', '未领养');
INSERT INTO `t_pet` VALUES ('7f6c09de-8e8a-44bf-9c58-f56672fc5ac6', '小斑', '公', null, '狸白猫', '', '                                            旋饭狂魔                                        ', '/upload/5a87479b-1f22-41ca-b1c2-28c6fc62d298.jpg', '未领养');
INSERT INTO `t_pet` VALUES ('0e956f50-6053-4a96-a876-b094bcc0ec58', '贴贴', '母', null, '狸花猫', '', '                                            话痨，又菜又爱玩                                        ', '/upload/ef8a3eea-ae55-43fa-81e8-5314de97d271.jpg', '未领养');
INSERT INTO `t_pet` VALUES ('cff3a853-00a1-4f07-a813-83c61423acfc', '小真诚', '母', '猫', '三花', '', '撒娇求撸，胆小怂包', '/upload/d58380e0-4079-45a3-ac1a-1e413ceb7bbf.jpg', '未领养');
INSERT INTO `t_pet` VALUES ('cf0a667b-6d7d-47a0-8601-302fd74a9a09', '妮妮', '公', '猫', '狸白猫', '', '在标本园出现的散养小猫，亲人可爱', '/upload/cd057b4a-2b36-4028-8581-f01dc65bf3fd.jpg', '未领养');
INSERT INTO `t_pet` VALUES ('416a5ce4-d451-4a95-8c76-073b59f3d3a5', '淼淼', '母', '狗', '串串', '2022-12-08', '调皮捣蛋但十分亲人，不咬人，很粘人', '/upload/be357e6b-c052-4fd8-8d71-54779dc3645d.jpg', '未领养');
INSERT INTO `t_pet` VALUES ('4cb001d0-2ed5-4f48-9788-d6fe25bfc49d', '七七', '母', '狗', '黑', '', '粘人，活泼，听得懂“坐”的口令', '/upload/2c7732c3-e9ee-4967-beed-dd4255d6378d.jpg', '未领养');

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
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('83a140f7-d035-4ca5-bda7-35a1fed63a25', 'admin', '123456', '梓', '12', '男', '12', '12', '广西大学', null, 'user');
INSERT INTO `t_user` VALUES ('5be2607c-36ef-498e-9c4a-6582786eb914', 'test', '666', '郑桦', '18', '女', '', '', '', '无领养经历', 'volunteer');
INSERT INTO `t_user` VALUES ('aa7f5e16-7e06-4e0e-ae3f-36797dc7ed53', '123456', '123456', 'test', '1', '男', '1', '1', '1', '有领养经历', 'volunteer');
INSERT INTO `t_user` VALUES ('c6e9a689-5d3d-4c98-a33d-d21ea4f2f02a', '666', '666', 'testfinal', '66', '男', '10086', 'hello@gmail.com', '中关村', '无领养经历', 'user');

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
-- Records of t_userlog
-- ----------------------------

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

-- ----------------------------
-- Records of t_user_event
-- ----------------------------
INSERT INTO `t_user_event` VALUES ('2', '5be2607c-36ef-498e-9c4a-6582786eb914', '8592249a-498c-4591-9024-64208ded44b6');
INSERT INTO `t_user_event` VALUES ('3', '5be2607c-36ef-498e-9c4a-6582786eb914', 'eb0a2712-0904-44a4-a5e9-d06598830754');
INSERT INTO `t_user_event` VALUES ('7', '1', '8592249a-498c-4591-9024-64208ded44b6');
INSERT INTO `t_user_event` VALUES ('8', '1', '8943ab1d-ef32-423f-a4e2-6dd1840efaf2');
INSERT INTO `t_user_event` VALUES ('9', '5be2607c-36ef-498e-9c4a-6582786eb914', '8943ab1d-ef32-423f-a4e2-6dd1840efaf2');
INSERT INTO `t_user_event` VALUES ('10', 'aa7f5e16-7e06-4e0e-ae3f-36797dc7ed53', '8943ab1d-ef32-423f-a4e2-6dd1840efaf2');
