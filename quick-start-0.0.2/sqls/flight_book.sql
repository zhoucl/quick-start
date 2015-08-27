/*
Navicat MySQL Data Transfer

Source Server         : shijie99_localhost
Source Server Version : 50626
Source Host           : localhost:3306
Source Database       : tpf

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2015-08-27 13:52:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for flight_book
-- ----------------------------
DROP TABLE IF EXISTS `flight_book`;
CREATE TABLE `flight_book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bktype` varchar(30) DEFAULT NULL,
  `ds` varchar(20) DEFAULT NULL,
  `content` text,
  `olddata` text,
  `query_conditions` text COMMENT '查询条件的json串',
  `routings` text COMMENT '预定的行程的json串，最全的',
  `session_id` varchar(120) DEFAULT NULL COMMENT '价格校验时生成的id',
  `priceinfo` varchar(600) DEFAULT NULL COMMENT '新老价格 以|分隔，前面为原来的',
  `pricestr` text COMMENT '价格串',
  `execute_time` int(6) DEFAULT NULL COMMENT '单位秒',
  `status` int(3) DEFAULT NULL COMMENT '0：fail，1：success',
  `message` varchar(2000) DEFAULT NULL COMMENT '错误信息',
  `qunar_json` text COMMENT '返回给qunar的json',
  `cid` varchar(30) DEFAULT NULL COMMENT 'CID',
  `fromcity` varchar(30) DEFAULT NULL COMMENT '出发城市三字码',
  `tocity` varchar(30) DEFAULT NULL COMMENT '目的城市三字码',
  `fromdate` varchar(90) DEFAULT NULL COMMENT '出发日期',
  `retdate` varchar(90) DEFAULT NULL COMMENT '返回日期 单程为空',
  `flighttype` varchar(30) DEFAULT NULL COMMENT '航程类型单程oneWay往返roundTrip',
  `guid` varchar(30) DEFAULT NULL COMMENT 'GUID',
  `cachehash` varchar(30) DEFAULT NULL COMMENT 'cachehash',
  `qhash` varchar(30) DEFAULT NULL COMMENT '基础串hash',
  `routecodes` varchar(600) DEFAULT NULL COMMENT '航线信息串',
  `dsid` varchar(30) DEFAULT NULL COMMENT '来源+行程id',
  `fromto` varchar(60) DEFAULT NULL COMMENT '出发目的地机场三字码',
  `lastdate` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `price_add_info` varchar(600) DEFAULT '' COMMENT '加价政策信息 注释：是否公布运价(1是0否)#cid#加价政策id#出发城市代码#到达城市代码#航空公司#时间起#时间止#适用仓位#加价固定数额#加价百分比数额#商家加价固定数额（譬如：众信）#商家加价百分比数额（譬如：众信）#调税固定数额#调税分比百数额#公布运价固定数额#公布运价百分比数额#价格版本号#最低利润#减价',
  PRIMARY KEY (`id`),
  KEY `cache_guid` (`cachehash`,`guid`) USING BTREE,
  KEY `fromto_ds` (`fromto`,`dsid`) USING BTREE,
  KEY `date_type` (`lastdate`,`bktype`) USING BTREE,
  KEY `session_id` (`session_id`) USING BTREE,
  KEY `cid_guid` (`cid`,`guid`,`ds`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=406315 DEFAULT CHARSET=utf8;
