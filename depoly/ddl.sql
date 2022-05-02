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

/*Table structure for table `t_mock_upms_role` */

DROP TABLE IF EXISTS `t_mock_upms_role`;

CREATE TABLE `t_mock_upms_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'primary key',
  `gmt_create` datetime NOT NULL COMMENT 'create time',
  `gmt_modified` datetime NOT NULL COMMENT 'modified time',
  `role_name` varchar(128) NOT NULL COMMENT '角色名称',
  `alias` varchar(128) DEFAULT NULL COMMENT '别名',
  `routes` longtext NOT NULL COMMENT '路由集合',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ROLE_NAME` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='用户行为表';

/*Data for the table `t_mock_upms_role` */

insert  into `t_mock_upms_role`(`id`,`gmt_create`,`gmt_modified`,`role_name`,`alias`,`routes`) values (1,'2022-04-28 13:34:56','2022-04-28 13:34:58','tester','软件测试人员','[1,3,4]'),(2,'2022-04-28 13:38:03','2022-04-28 13:38:01','admin','超级管理员','[1]'),(6,'2022-04-28 14:30:24','2022-04-28 14:30:22','visitor','游客','[1]');

/*Table structure for table `t_mock_upms_route` */

DROP TABLE IF EXISTS `t_mock_upms_route`;

CREATE TABLE `t_mock_upms_route` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'primary key',
  `gmt_create` datetime NOT NULL COMMENT 'create time',
  `gmt_modified` datetime NOT NULL COMMENT 'modified time',
  `master_id` bigint(20) NOT NULL COMMENT '上级路由ID,若为1级路由则为自己',
  `path` varchar(256) NOT NULL COMMENT '路由',
  `component` varchar(128) NOT NULL COMMENT '组件名称',
  `hidden` tinyint(1) NOT NULL DEFAULT '0' COMMENT '默认0:false,表示路由在侧边栏不会显示，用于页面内按钮展示等控制',
  `redirect` varchar(128) NOT NULL DEFAULT 'noRedirect' COMMENT '默认为noRedirect，表示在面包屑导航中不可被点击',
  `always_show` tinyint(1) NOT NULL DEFAULT '1' COMMENT '默认1:true表示显示根路由',
  `name` varchar(128) NOT NULL COMMENT '路由名称，非空',
  `meta` bigint(20) NOT NULL COMMENT '元数据id',
  `active_menu` varchar(256) NOT NULL COMMENT '高亮配置',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='路由信息表';

/*Data for the table `t_mock_upms_route` */

insert  into `t_mock_upms_route`(`id`,`gmt_create`,`gmt_modified`,`master_id`,`path`,`component`,`hidden`,`redirect`,`always_show`,`name`,`meta`,`active_menu`) values (1,'2022-05-01 20:22:17','2022-05-01 20:22:19',1,'/dashboard','dashboard_index',0,'noRedirect',1,'主页',1,'/dashboard'),(3,'2022-05-02 20:01:38','2022-05-02 20:01:42',3,'/table','layout',0,'/table/complex-table',1,'数据',4,'/table'),(4,'2022-05-02 20:02:51','2022-05-20 02:33:00',3,'complex-table','complex-table',0,'noRedirect',1,'复合表格',5,'/table/complex-table');

/*Table structure for table `t_mock_upms_route_meta` */

DROP TABLE IF EXISTS `t_mock_upms_route_meta`;

CREATE TABLE `t_mock_upms_route_meta` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'primary key',
  `gmt_create` datetime NOT NULL COMMENT 'create time',
  `gmt_modified` datetime NOT NULL COMMENT 'modified time',
  `roles` longtext NOT NULL COMMENT '角色',
  `title` varchar(128) NOT NULL COMMENT '路由名称，非空',
  `icon` varchar(128) NOT NULL COMMENT '设置该路由的图标，支持 svg-class，也支持 el-icon-x element-ui 的 icon',
  `no_cache` tinyint(1) NOT NULL DEFAULT '0' COMMENT '路由名称，非空',
  `breadcrumb` tinyint(1) NOT NULL DEFAULT '1' COMMENT '如果设置为false，则不会在breadcrumb面包屑中显示(默认 true)',
  `affix` tinyint(1) NOT NULL DEFAULT '0' COMMENT '如果设置为true，它则会固定在tags-view中(默认 false)',
  `active_menu` varchar(128) NOT NULL COMMENT '高亮相对应的侧边栏',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='路由信息表';

/*Data for the table `t_mock_upms_route_meta` */

insert  into `t_mock_upms_route_meta`(`id`,`gmt_create`,`gmt_modified`,`roles`,`title`,`icon`,`no_cache`,`breadcrumb`,`affix`,`active_menu`) values (1,'2022-05-02 10:45:08','2022-05-02 10:45:06','[1,2]','首页','dashboard',0,1,1,'/dashboard/index'),(2,'2022-05-02 15:31:48','2022-05-02 15:31:46','[1,2]','','',0,1,0,''),(4,'2022-05-02 20:04:43','2022-05-02 20:04:41','[1,2]','表格','table',0,1,0,''),(5,'2022-05-02 20:05:44','2022-05-02 20:05:47','[1,2]','复合表格','list',0,1,0,'');

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

insert  into `t_mock_upms_user`(`id`,`gmt_create`,`gmt_modified`,`user_no`,`password`,`user_name`,`role_id_list`) values (1,'2022-04-26 22:01:57','2022-04-26 22:01:59','A8142','G67aEZfgSkWEviAur7gsNZtmroc=','zhtty','[1]'),(2,'2022-04-28 14:29:15','2022-04-28 14:29:12','unknown','1','游客','[6]');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
