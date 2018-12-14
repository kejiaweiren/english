/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.20-log : Database - english
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`english` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `english`;

/*Table structure for table `word` */

DROP TABLE IF EXISTS `word`;

CREATE TABLE `word` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `word` varchar(200) NOT NULL,
  `chinese` varchar(500) DEFAULT NULL,
  `root` varchar(20) DEFAULT NULL,
  `coreword` varchar(20) DEFAULT NULL,
  `sentence` varchar(8000) DEFAULT NULL,
  `note` varchar(5000) DEFAULT NULL,
  `phonetic` varchar(100) DEFAULT NULL,
  `status` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2936 DEFAULT CHARSET=utf8;

