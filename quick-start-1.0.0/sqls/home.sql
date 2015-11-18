/*
Navicat MySQL Data Transfer

Source Server         : 192.168.4.155
Source Server Version : 50626
Source Host           : 192.168.4.155:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2015-11-09 09:20:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cfg_servers
-- ----------------------------
DROP TABLE IF EXISTS `cfg_servers`;
CREATE TABLE `cfg_servers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `server` varchar(255) DEFAULT NULL,
  `servername` varchar(255) DEFAULT NULL,
  `port` varchar(255) DEFAULT NULL,
  `weight` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cfg_servers
-- ----------------------------
INSERT INTO `cfg_servers` VALUES ('1', '192.168.4.155', 'memcache_11212', '11212', '10', '0');
INSERT INTO `cfg_servers` VALUES ('2', '192.168.4.155', 'sgroup/zkNode1/app1', '2181', '5000', '1');

-- ----------------------------
-- Table structure for home
-- ----------------------------
DROP TABLE IF EXISTS `home`;
CREATE TABLE `home` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `homelink` varchar(255) DEFAULT NULL,
  `homedesc` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of home
-- ----------------------------
INSERT INTO `home` VALUES ('1', 'http://127.0.0.1:8080/home', 'home描述', '无备注');
