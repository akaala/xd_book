/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : xd_book

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2015-08-10 22:45:41
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
  `userId` tinyint(4) NOT NULL DEFAULT '0' COMMENT '书本是否处于借阅状态.0表示未借出.',
  PRIMARY KEY (`id`),
  UNIQUE KEY `number` (`number`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_book
-- ----------------------------
INSERT INTO `tb_book` VALUES ('1', 'T123', 'java编程思想', '诸葛亮', '25.00', '机械出版社', '30');
INSERT INTO `tb_book` VALUES ('2', 'T124', 'angularJs权威指南', '郭忠跃', '82.60', '清华大学', '46');
INSERT INTO `tb_book` VALUES ('3', '是豆腐干豆腐', '是的发送到', '是的发送到', '25.00', '士大夫撒', '0');
INSERT INTO `tb_book` VALUES ('4', 'T120', 'css权威指南', '郭忠跃', '500.00', '中心出版社', '0');
INSERT INTO `tb_book` VALUES ('5', 'sad', '防守打法', '都发送', '56.00', '地方', '0');
INSERT INTO `tb_book` VALUES ('6', '22323', '问问', '发到的', '4544.00', '水电费', '0');
INSERT INTO `tb_book` VALUES ('7', '是的发送到', '东方闪电', '都是非法', '5.00', '的风格', '0');
INSERT INTO `tb_book` VALUES ('9', 'T12306', 'sfsda', 'sdf', '45.00', 'dfgz', '0');
INSERT INTO `tb_book` VALUES ('10', 'fd', 'fd', 'dsfa', '545.00', '5qfasd', '0');

-- ----------------------------
-- Table structure for `tb_borrow`
-- ----------------------------
DROP TABLE IF EXISTS `tb_borrow`;
CREATE TABLE `tb_borrow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` datetime NOT NULL COMMENT '借书时间',
  `bookId` int(11) NOT NULL DEFAULT '0' COMMENT '图书id',
  `userId` int(11) NOT NULL DEFAULT '0' COMMENT '用户id',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '借,还状态1:借,2:还,3:借申请',
  `operatorId` int(11) NOT NULL COMMENT '经办人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of tb_borrow
-- ----------------------------
INSERT INTO `tb_borrow` VALUES ('1', '2015-08-02 17:37:49', '0', '0', '0', '0');
INSERT INTO `tb_borrow` VALUES ('2', '2015-08-09 13:01:23', '1', '30', '3', '46');
INSERT INTO `tb_borrow` VALUES ('3', '2015-08-10 20:42:40', '0', '0', '0', '0');
INSERT INTO `tb_borrow` VALUES ('4', '2015-08-10 20:52:15', '3', '30', '3', '46');

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
INSERT INTO `tb_department` VALUES ('1', '研发部');
INSERT INTO `tb_department` VALUES ('2', '市场部');
INSERT INTO `tb_department` VALUES ('3', '财务部');
INSERT INTO `tb_department` VALUES ('4', '产品中心');
INSERT INTO `tb_department` VALUES ('6', '工程部');

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
INSERT INTO `tb_user` VALUES ('30', '郭延思', '1', '架构师', '1990-09-07', '2014-06-23', '4QrcOUm6Wau+VuBX8g+IPg==', 'guoyansi', '1', '1');
INSERT INTO `tb_user` VALUES ('44', '1', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', '1', '1', '0');
INSERT INTO `tb_user` VALUES ('45', '2', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', '2', '1', '1');
INSERT INTO `tb_user` VALUES ('46', '李盼盼', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', '3', '0', '1');
INSERT INTO `tb_user` VALUES ('47', '44444', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', '5', '0', '0');
INSERT INTO `tb_user` VALUES ('48', '444446', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', '6', '0', '0');
INSERT INTO `tb_user` VALUES ('49', '444446', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', '7', '0', '0');
INSERT INTO `tb_user` VALUES ('50', '444446', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', '8', '0', '0');
INSERT INTO `tb_user` VALUES ('51', '444446', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', 'asdfasdf', '0', '0');
INSERT INTO `tb_user` VALUES ('52', '444446', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', '所得税', '0', '0');
INSERT INTO `tb_user` VALUES ('53', '444446', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', '热热', '0', '0');
INSERT INTO `tb_user` VALUES ('55', '444446', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', '微文深诋', '0', '0');
INSERT INTO `tb_user` VALUES ('56', '444446', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', '微文深诋水电费', '0', '0');
INSERT INTO `tb_user` VALUES ('57', '444446水电费多少分', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', '微文深诋水电费大道', '0', '0');
INSERT INTO `tb_user` VALUES ('58', '444446水电费多少分', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', '微文深诋水电费大道天天', '1', '0');
INSERT INTO `tb_user` VALUES ('59', '444446水电费多少分', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', '微文深诋水电费大道天天谁谁谁', '0', '0');
INSERT INTO `tb_user` VALUES ('60', '444446水电费多少分', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', '微文深诋水电费大道天天谁谁谁撒发生', '0', '0');
INSERT INTO `tb_user` VALUES ('61', '444446水电费多少分', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', '微文深诋水电费大道天天谁谁谁撒发生啊沙发上', '0', '0');
INSERT INTO `tb_user` VALUES ('62', '444446水电费多少分', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', 'werewolf', '1', '0');
INSERT INTO `tb_user` VALUES ('63', '444446水电费多少分', '1', '1223', null, null, '4QrcOUm6Wau+VuBX8g+IPg==', '许', '0', '0');
