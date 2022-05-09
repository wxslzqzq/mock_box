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
  `default_check` tinyint(1) NOT NULL DEFAULT '0' COMMENT 'checkable',
  `describe` varchar(128) NOT NULL COMMENT '行为名称/行为描述',
  `action` varchar(128) NOT NULL COMMENT '行为动作',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='用户行为表';

/*Data for the table `t_mock_upms_action` */

insert  into `t_mock_upms_action`(`id`,`gmt_create`,`gmt_modified`,`default_check`,`describe`,`action`) values (1,'2022-05-09 16:39:58','2022-05-09 16:40:00',0,'新增','add'),(2,'2022-05-09 16:40:16','2022-05-09 16:40:19',0,'查询','query'),(3,'2022-05-09 16:40:33','2022-05-09 16:40:31',0,'详情','get'),(4,'2022-05-09 16:40:51','2022-05-09 16:40:53',0,'修改','update'),(5,'2022-05-09 16:41:11','2022-05-09 16:41:12',0,'删除','delete'),(12,'2022-05-09 19:22:16','2022-05-09 19:22:14',0,'导入','import'),(13,'2022-05-09 19:22:54','2022-05-09 19:22:55',0,'导出','export');

/*Table structure for table `t_mock_upms_permission` */

DROP TABLE IF EXISTS `t_mock_upms_permission`;

CREATE TABLE `t_mock_upms_permission` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'primary key',
  `gmt_create` datetime NOT NULL COMMENT 'create time',
  `gmt_modified` datetime NOT NULL COMMENT 'modified time',
  `permission_name` varchar(128) NOT NULL COMMENT '权限中文名称',
  `permission_id` varchar(128) NOT NULL COMMENT '权限英文名称',
  `actions` longtext NOT NULL COMMENT '行为集合',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='用户权限表';

/*Data for the table `t_mock_upms_permission` */

insert  into `t_mock_upms_permission`(`id`,`gmt_create`,`gmt_modified`,`permission_name`,`permission_id`,`actions`) values (1,'2022-05-09 16:41:51','2022-05-09 16:41:50','仪表盘','dashboard','[1,2,3,4,5]');

/*Table structure for table `t_mock_upms_role` */

DROP TABLE IF EXISTS `t_mock_upms_role`;

CREATE TABLE `t_mock_upms_role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'primary key',
  `gmt_create` datetime NOT NULL COMMENT 'create time',
  `gmt_modified` datetime NOT NULL COMMENT 'modified time',
  `role_name` varchar(128) NOT NULL COMMENT '角色名称',
  `alias` varchar(128) DEFAULT NULL COMMENT '别名',
  `routes` longtext NOT NULL COMMENT '路由集合',
  `permissions` longtext NOT NULL COMMENT '权限集合',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_ROLE_NAME` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='用户行为表';

/*Data for the table `t_mock_upms_role` */

insert  into `t_mock_upms_role`(`id`,`gmt_create`,`gmt_modified`,`role_name`,`alias`,`routes`,`permissions`) values (1,'2022-04-28 13:34:56','2022-04-28 13:34:58','tester','软件测试人员','[1,7]','[1]');

/*Table structure for table `t_mock_upms_route` */

DROP TABLE IF EXISTS `t_mock_upms_route`;

CREATE TABLE `t_mock_upms_route` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'primary key',
  `gmt_create` datetime NOT NULL COMMENT 'create time',
  `gmt_modified` datetime NOT NULL COMMENT 'modified time',
  `master_id` bigint(20) NOT NULL COMMENT '上级路由ID,若为1级路由则为自己',
  `path` varchar(256) DEFAULT NULL COMMENT '路由',
  `component` varchar(128) DEFAULT NULL COMMENT '组件名称',
  `hidden` tinyint(1) NOT NULL DEFAULT '0' COMMENT '控制路由和子路由是否显示在 sidebar 默认0:false',
  `redirect` varchar(128) NOT NULL DEFAULT 'noredirect' COMMENT '重定向地址, 访问这个路由时,自定进行重定向',
  `name` varchar(128) NOT NULL COMMENT '路由名称，非空',
  `meta` longtext NOT NULL COMMENT '路由元信息（路由附带扩展信息）',
  `hide_children_in_menu` tinyint(1) DEFAULT '0' COMMENT '强制菜单显示为Item而不是SubItem(配合 meta.hidden)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10036 DEFAULT CHARSET=utf8 COMMENT='路由信息表';

/*Data for the table `t_mock_upms_route` */

insert  into `t_mock_upms_route`(`id`,`gmt_create`,`gmt_modified`,`master_id`,`path`,`component`,`hidden`,`redirect`,`name`,`meta`,`hide_children_in_menu`) values (1,'2022-05-09 16:08:36','2022-05-09 16:08:36',0,NULL,'RouteView',0,'/dashboard/workplace','dashboard','{\"title\":\"仪表盘\",\"icon\":\"dashboard\",\"show\":true}',0),(2,'2022-05-09 16:08:37','2022-05-09 16:08:37',1,'/dashboard/analysis','Analysis',0,'noredirect','Analysis','{\"title\":\"分析页\",\"show\":true}',0),(3,'2022-05-09 16:08:37','2022-05-09 16:08:37',1,'https://www.baidu.com/',NULL,0,'noredirect','monitor','{\"title\":\"监控页（外部）\",\"show\":true,\"target\":\"_blank\"}',0),(4,'2022-05-09 16:08:38','2022-05-09 16:08:38',10,NULL,'AdvanceForm',0,'noredirect','advanced-form','{\"title\":\"高级表单\"}',0),(5,'2022-05-09 16:08:38','2022-05-09 16:08:38',10,NULL,'StepForm',0,'noredirect','step-form','{\"title\":\"分步表单\"}',0),(6,'2022-05-09 16:08:38','2022-05-09 16:08:38',10,NULL,'BasicForm',0,'noredirect','basic-form','{\"title\":\"基础表单\"}',0),(7,'2022-05-09 16:08:37','2022-05-09 16:08:37',1,NULL,'Workplace',0,'noredirect','workplace','{\"title\":\"工作台\",\"show\":true}',0),(10,'2022-05-09 16:08:38','2022-05-09 16:08:38',0,NULL,'RouteView',0,'/form/base-form','form','{\"title\":\"表单页\",\"icon\":\"form\"}',0),(10010,'2022-05-09 16:08:38','2022-05-09 16:08:38',0,NULL,'RouteView',0,'/list/table-list','list','{\"title\":\"列表页\",\"icon\":\"table\",\"show\":true}',0),(10011,'2022-05-09 16:08:38','2022-05-09 16:08:38',10010,'/list/table-list/:pageNo([1-9]\\d*)?','TableList',0,'noredirect','table-list','{\"title\":\"查询表格\",\"show\":true}',0),(10012,'2022-05-09 16:08:38','2022-05-09 16:08:38',10010,NULL,'StandardList',0,'noredirect','basic-list','{\"title\":\"标准列表\",\"show\":true}',0),(10013,'2022-05-09 16:08:38','2022-05-09 16:08:38',10010,NULL,'CardList',0,'noredirect','card','{\"title\":\"卡片列表\",\"show\":true}',0),(10014,'2022-05-09 16:08:38','2022-05-09 16:08:38',10010,NULL,'SearchLayout',0,'/list/search/article','search','{\"title\":\"搜索列表\",\"show\":true}',0),(10015,'2022-05-09 16:08:38','2022-05-09 16:08:38',10014,NULL,'SearchArticles',0,'noredirect','article','{\"title\":\"搜索列表（文章）\",\"show\":true}',0),(10016,'2022-05-09 16:08:38','2022-05-09 16:08:38',10014,NULL,'SearchProjects',0,'noredirect','project','{\"title\":\"搜索列表（项目）\",\"show\":true}',0),(10017,'2022-05-09 16:08:38','2022-05-09 16:08:38',10014,NULL,'SearchApplications',0,'noredirect','application','{\"title\":\"搜索列表（应用）\",\"show\":true}',0),(10018,'2022-05-09 16:08:38','2022-05-09 16:08:38',0,NULL,'RouteView',0,'/profile/basic','profile','{\"title\":\"详情页\",\"icon\":\"profile\",\"show\":true}',0),(10019,'2022-05-09 16:08:38','2022-05-09 16:08:38',10018,NULL,'ProfileBasic',0,'noredirect','basic','{\"title\":\"基础详情页\",\"show\":true}',0),(10020,'2022-05-09 16:08:38','2022-05-09 16:08:38',10018,NULL,'ProfileAdvanced',0,'noredirect','advanced','{\"title\":\"高级详情页\",\"show\":true}',0),(10021,'2022-05-09 16:08:38','2022-05-09 16:08:38',0,NULL,'PageView',0,'/result/success','result','{\"title\":\"结果页\",\"icon\":\"check-circle-o\",\"show\":true}',0),(10022,'2022-05-09 16:08:38','2022-05-09 16:08:38',10021,NULL,'ResultSuccess',0,'noredirect','success','{\"title\":\"成功\",\"show\":true,\"hiddenHeaderContent\":true}',0),(10023,'2022-05-09 16:08:38','2022-05-09 16:08:38',10021,NULL,'ResultFail',0,'noredirect','fail','{\"title\":\"失败\",\"show\":true,\"hiddenHeaderContent\":true}',0),(10024,'2022-05-09 16:08:38','2022-05-09 16:08:38',0,NULL,'RouteView',0,'/exception/403','exception','{\"title\":\"异常页\",\"icon\":\"warning\",\"show\":true}',0),(10025,'2022-05-09 16:08:38','2022-05-09 16:08:38',10024,NULL,'Exception403',0,'noredirect','403','{\"title\":\"403\",\"show\":true}',0),(10026,'2022-05-09 16:08:38','2022-05-09 16:08:38',10024,NULL,'Exception404',0,'noredirect','404','{\"title\":\"404\",\"show\":true}',0),(10027,'2022-05-09 16:08:38','2022-05-09 16:08:38',10024,NULL,'Exception500',0,'noredirect','500','{\"title\":\"500\",\"show\":true}',0),(10028,'2022-05-09 16:08:38','2022-05-09 16:08:38',0,NULL,'RouteView',0,'/account/center','account','{\"title\":\"个人页\",\"icon\":\"user\",\"show\":true}',0),(10029,'2022-05-09 16:08:38','2022-05-09 16:08:38',10028,NULL,'AccountCenter',0,'noredirect','center','{\"title\":\"个人中心\",\"show\":true}',0),(10030,'2022-05-09 16:08:38','2022-05-09 16:08:38',10028,NULL,'AccountSettings',0,'/account/settings/basic','settings','{\"title\":\"个人设置\",\"show\":true,\"hideHeader\":true,\"hideChildren\":true}',0),(10031,'2022-05-09 16:08:38','2022-05-09 16:08:38',10030,'/account/settings/basic','BasicSetting',0,'noredirect','BasicSettings','{\"title\":\"基本设置\",\"show\":false}',0),(10032,'2022-05-09 16:08:38','2022-05-09 16:08:38',10030,'/account/settings/security','SecuritySettings',0,'noredirect','SecuritySettings','{\"title\":\"安全设置\",\"show\":false}',0),(10033,'2022-05-09 16:08:38','2022-05-09 16:08:38',10030,'/account/settings/custom','CustomSettings',0,'noredirect','CustomSettings','{\"title\":\"个性化设置\",\"show\":false}',0),(10034,'2022-05-09 16:08:38','2022-05-09 16:08:38',10030,'/account/settings/binding','BindingSettings',0,'noredirect','BindingSettings','{\"title\":\"账户绑定\",\"show\":false}',0),(10035,'2022-05-09 16:08:38','2022-05-09 16:08:38',10030,'/account/settings/notification','NotificationSettings',0,'noredirect','NotificationSettings','{\"title\":\"新消息通知\",\"show\":false}',0);

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
