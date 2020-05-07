/*
Navicat MySQL Data Transfer

Source Server         : LOCA
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : happy_hrs_db

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2020-05-06 20:18:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `profiles_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `profiles_tbl`;
CREATE TABLE `profiles_tbl` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `gender` varchar(6) DEFAULT NULL,
  `photo` varchar(1000) DEFAULT NULL,
  `doe` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of profiles_tbl
-- ----------------------------
INSERT INTO `profiles_tbl` VALUES ('10', 'yadna01', 'jill', 'Nagendra', 'nagen@gmail.come', 'Male', 'https://www.greateralliance.org/wp-content/uploads/2019/10/half_blank31.png', '2020-05-06 19:58:26');
INSERT INTO `profiles_tbl` VALUES ('11', 'jack', 'jill', 'JAck', 'jack@gmail.com', 'Female', 'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRN9pkzoAjFb0PB8kIuBm3rrrN_lCisKmPclD37LIQtUzF8F7Pg&usqp=CAU', '2020-05-06 19:58:26');

-- ----------------------------
-- Table structure for `users_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `users_tbl`;
CREATE TABLE `users_tbl` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(50) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `mobile` bigint(20) DEFAULT NULL,
  `salutation` varchar(4) DEFAULT NULL,
  `image` varchar(500) DEFAULT NULL,
  `createdate` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of users_tbl
-- ----------------------------
INSERT INTO `users_tbl` VALUES ('4', 'synergisticit2020@gmail.com', 'wqeqw', 'JOE', 'synergisticit2020@gmail.com', '3445', 'Mr.', 'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTGIUc0hzy3N6S7Z8m-CRjGj8iT_Mg5vQirjqtHDOefKO9t1Aub&usqp=CAU', '2020-05-06 18:15:14');
INSERT INTO `users_tbl` VALUES ('5', 'javatech1000@gmail.com', 'test', 'Krishana', 'javatech1000@gmail.com', '2342342344', 'Miss', 'https://citybook.pk/wp-content/uploads/2019/01/tutors-in-lahore-2-1.jpg', '2020-05-06 18:15:54');
INSERT INTO `users_tbl` VALUES ('6', 'nagendra.synergisticit@gmail.com', 'w3w3rw', 'Mark', 'nagendra.synergisticit@gmail.com', '32424342', 'Mr.', 'https://clueylearning.com.au/wp-content/uploads/2019/10/What-is-tutoring-Different-types-of-tutoring-explained.jpg', '2020-05-06 18:17:05');
