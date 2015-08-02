/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : xd_book

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2015-08-02 23:55:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_book`
-- ----------------------------
DROP TABLE IF EXISTS `tb_book`;
CREATE TABLE `tb_book` (
  `number` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '图书编码,唯一性',
  `name` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '书名',
  `author` varchar(50) COLLATE utf8_bin DEFAULT '' COMMENT '作者',
  `price` float(11,2) DEFAULT '0.00' COMMENT '价格',
  `publisher` varchar(50) COLLATE utf8_bin DEFAULT '出版社',
  `userId` tinyint(4) NOT NULL DEFAULT '0' COMMENT '书本是否处于借阅状态.0表示未借出.',
  PRIMARY KEY (`number`),
  UNIQUE KEY `number` (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_book
-- ----------------------------
INSERT INTO `tb_book` VALUES ('T123', 'java编程思想', '诸葛亮', '28.50', '机械工业出版社', '30');

-- ----------------------------
-- Table structure for `tb_borrow`
-- ----------------------------
DROP TABLE IF EXISTS `tb_borrow`;
CREATE TABLE `tb_borrow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` datetime NOT NULL COMMENT '借书时间',
  `bookId` int(11) NOT NULL DEFAULT '0' COMMENT '图书id',
  `userId` int(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '借,还状态1:借,2还',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_borrow
-- ----------------------------
INSERT INTO `tb_borrow` VALUES ('1', '2015-08-02 17:37:49', '0', '0', '0');

-- ----------------------------
-- Table structure for `tb_department`
-- ----------------------------
DROP TABLE IF EXISTS `tb_department`;
CREATE TABLE `tb_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '部门名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_department
-- ----------------------------
INSERT INTO `tb_department` VALUES ('1', '研发部');
INSERT INTO `tb_department` VALUES ('2', '市场部');
INSERT INTO `tb_department` VALUES ('3', '财务部');

-- ----------------------------
-- Table structure for `tb_manager`
-- ----------------------------
DROP TABLE IF EXISTS `tb_manager`;
CREATE TABLE `tb_manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `userId` (`userId`) USING BTREE,
  CONSTRAINT `tb_manager_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `tb_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_manager
-- ----------------------------

-- ----------------------------
-- Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_bin NOT NULL,
  `departId` int(50) DEFAULT NULL,
  `job` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '职位',
  `birth` date DEFAULT NULL,
  `entry` date DEFAULT NULL COMMENT '入职时间',
  `password` varchar(255) COLLATE utf8_bin NOT NULL DEFAULT '',
  `loginName` varchar(50) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `登录名` (`loginName`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('30', '郭延思', '1', '架构师', '1990-09-07', '2014-06-23', '4QrcOUm6Wau+VuBX8g+IPg==', 'guoyansi');
INSERT INTO `tb_user` VALUES ('44', '1', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', '1');
INSERT INTO `tb_user` VALUES ('45', '2', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', '2');
INSERT INTO `tb_user` VALUES ('46', '3', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', '3');
INSERT INTO `tb_user` VALUES ('47', '44444', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', '5');
INSERT INTO `tb_user` VALUES ('48', '444446', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', '6');
INSERT INTO `tb_user` VALUES ('49', '444446', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', '7');
INSERT INTO `tb_user` VALUES ('50', '444446', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', '8');
INSERT INTO `tb_user` VALUES ('51', '444446', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', 'asdfasdf');
INSERT INTO `tb_user` VALUES ('52', '444446', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', '所得税');
INSERT INTO `tb_user` VALUES ('53', '444446', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', '热热');
INSERT INTO `tb_user` VALUES ('55', '444446', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', '微文深诋');
INSERT INTO `tb_user` VALUES ('56', '444446', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', '微文深诋水电费');
INSERT INTO `tb_user` VALUES ('57', '444446水电费多少分', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', '微文深诋水电费大道');
INSERT INTO `tb_user` VALUES ('58', '444446水电费多少分', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', '微文深诋水电费大道天天');
INSERT INTO `tb_user` VALUES ('59', '444446水电费多少分', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', '微文深诋水电费大道天天谁谁谁');
INSERT INTO `tb_user` VALUES ('60', '444446水电费多少分', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', '微文深诋水电费大道天天谁谁谁撒发生');
INSERT INTO `tb_user` VALUES ('61', '444446水电费多少分', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', '微文深诋水电费大道天天谁谁谁撒发生啊沙发上');
INSERT INTO `tb_user` VALUES ('62', '444446水电费多少分', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', 'werewolf');
INSERT INTO `tb_user` VALUES ('63', '444446水电费多少分', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', '许');
INSERT INTO `tb_user` VALUES ('64', '444446水电费多少分', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', '许大放送');
