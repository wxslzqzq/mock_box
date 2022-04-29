/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.31 : Database - mock
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mock` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `mock`;

/*Table structure for table `t_mock_upms_action` */

DROP TABLE IF EXISTS `t_mock_upms_action`;

CREATE TABLE `t_mock_upms_action` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'primary key',
  `gmt_create` datetime NOT NULL COMMENT 'create time',
  `gmt_modified` datetime NOT NULL COMMENT 'modified time',
  `master_id` bigint(20) NOT NULL COMMENT '所属权限组',
  `action_name` varchar(128) NOT NULL COMMENT '行为名称',
  `action` varchar(128) NOT NULL COMMENT '行为动作',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ACTION` (`action`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='用户行为表';

/*Data for the table `t_mock_upms_action` */


/*Table structure for table `t_mock_upms_permission_group` */

DROP TABLE IF EXISTS `t_mock_upms_permission_group`;

CREATE TABLE `t_mock_upms_permission_group` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'primary key',
  `gmt_create` datetime NOT NULL COMMENT 'create time',
  `gmt_modified` datetime NOT NULL COMMENT 'modified time',
  `permission_group_name` varchar(128) NOT NULL COMMENT '权限名称',
  `alias` varchar(128) DEFAULT NULL,
  `action_id_list` longtext NOT NULL COMMENT '权限组行为集合',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_PERMISSION_GROUP_NAME` (`permission_group_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户权限组登记表';

/*Data for the table `t_mock_upms_permission_group` */


/*Table structure for table `t_mock_upms_role` */

DROP TABLE IF EXISTS `t_mock_upms_role`;

CREATE TABLE `t_mock_upms_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'primary key',
  `gmt_create` datetime NOT NULL COMMENT 'create time',
  `gmt_modified` datetime NOT NULL COMMENT 'modified time',
  `master_id` bigint(20) DEFAULT '0' COMMENT '所属角色',
  `role_name` varchar(128) NOT NULL COMMENT '角色名称',
  `alias` varchar(128) DEFAULT NULL COMMENT '别名',
  `permission_group_id_list` longtext NOT NULL COMMENT '角色组集合',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ROLE_NAME` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='用户行为表';

/*Data for the table `t_mock_upms_role` */

/*Table structure for table `t_mock_upms_user` */

DROP TABLE IF EXISTS `t_mock_upms_user`;

CREATE TABLE `t_mock_upms_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'primary key',
  `gmt_create` datetime NOT NULL COMMENT 'create time',
  `gmt_modified` datetime NOT NULL COMMENT 'modified time',
  `user_no` varchar(32) NOT NULL COMMENT '工号',
  `password` varchar(256) NOT NULL COMMENT '密码加密后',
  `user_name` varchar(32) NOT NULL COMMENT '用户名',
  `role_id_list` longtext NOT NULL COMMENT '角色集合',
  PRIMARY KEY (`id`),
  KEY `uk_user_no` (`user_no`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='user information table';

/*Data for the table `t_mock_upms_user` */


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
