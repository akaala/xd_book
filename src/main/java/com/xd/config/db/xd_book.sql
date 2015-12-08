/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : xd_book

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2015-08-14 22:30:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_book`
-- ----------------------------
DROP TABLE IF EXISTS `tb_book`;
CREATE TABLE `tb_book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `number` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '图书编码,唯一性',
  `name` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '书名',
  `author` varchar(50) COLLATE utf8_bin DEFAULT '' COMMENT '作者',
  `price` float(11,2) DEFAULT '0.00' COMMENT '价格',
  `publisher` varchar(50) COLLATE utf8_bin DEFAULT '出版社',
  `status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '书本是否处于借阅状态.0表示未借出.1:已借出',
  PRIMARY KEY (`id`),
  UNIQUE KEY `number` (`number`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_book
-- ----------------------------
INSERT INTO `tb_book` VALUES ('1', 'T123', 'java编程思想', '诸葛亮', '25.00', '机械出版社', '0');
INSERT INTO `tb_book` VALUES ('2', 'T124', 'angularJs权威指南', '郭忠跃', '82.60', '清华大学', '0');
INSERT INTO `tb_book` VALUES ('3', 'T120', 'css权威指南', '郭忠跃', '500.00', '中心出版社', '0');

-- ----------------------------
-- Table structure for `tb_borrow`
-- ----------------------------
DROP TABLE IF EXISTS `tb_borrow`;
CREATE TABLE `tb_borrow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `appTime` datetime NOT NULL COMMENT '借书申请时间',
  `borrowTime` datetime NOT NULL COMMENT '借书时间',
  `backTime` datetime NOT NULL COMMENT '还书时间',
  `bookId` int(11) NOT NULL DEFAULT '0' COMMENT '图书id',
  `userId` int(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '借,还状态1:申请,2:已借,3:已还',
  `operatorId` int(11) NOT NULL COMMENT '经办人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Table structure for `tb_department`
-- ----------------------------
DROP TABLE IF EXISTS `tb_department`;
CREATE TABLE `tb_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '部门名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_department
-- ----------------------------
INSERT INTO `tb_department` VALUES ('1', '框架研发部');

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
  `status` int(11) DEFAULT '0' COMMENT '0:未审核,1:已审核',
  `manager` int(11) DEFAULT '0' COMMENT '0:普通职员,1:管理员',
  PRIMARY KEY (`id`),
  UNIQUE KEY `登录名` (`loginName`) USING HASH
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', '郭延思', '1', 'Project Owner', '1990-09-07', '2014-06-23', '4QrcOUm6Wau+VuBX8g+IPg==', 'guoyansi', '1', '1');
INSERT INTO `tb_user` VALUES ('2', 'admin', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', 'admin', '1', '1');
INSERT INTO `tb_user` VALUES ('3', 'test', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', 'test', '1', '0');
