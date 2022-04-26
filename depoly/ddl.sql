CREATE TABLE `t_mock_upms_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'primary key',
  `gmt_create` datetime NOT NULL COMMENT 'create time',
  `gmt_modified` datetime NOT NULL COMMENT 'modified time',
  `user_no` varchar(32) NOT NULL  COMMENT '工号',
  `password` varchar(128) NOT NULL COMMENT '密码加密后',
  `user_name` varchar(32) NOT NULL COMMENT '用户名',
  `role_id_list` longtext NOT NULL DEFAULT '[1]' COMMENT '角色集合',
  PRIMARY KEY (`id`),
  KEY `uk_user_no` (`user_no`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='用户信息表'

