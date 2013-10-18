#如果复制到mysql中执行时 加上
#DELIMITER ;;

drop table if exists `sys_user`;;
drop table if exists `sys_organization`;;
drop table if exists `sys_resource`;;
drop table if exists `sys_permission`;;
drop table if exists `sys_role`;;
drop table if exists `sys_role_resource_permission`;;
drop table if exists `sys_auth`;;
##user
create table `sys_user`(
  `id`         bigint not null auto_increment,
  `username`  varchar(100),
  `email`  varchar(100),
  `mobile_phone_number`  varchar(20),
  `password`  varchar(100),
  `salt`       varchar(10),
  `status`    varchar(50),
  `organization_id` int,
  `organization_name` varchar(255),
  `deleted`   bool,
  `admin`     bool,
  `create_date` datetime default '1970-01-01 00:00:00',
  `modify_time` datetime default '1970-01-01 00:00:00',
  constraint `pk_sys_user` primary key(`id`),
  constraint `unique_sys_user_username` unique(`username`),
  constraint `unique_sys_user_email` unique(`email`),
  constraint `unique_sys_user_mobile_phone_number` unique(`mobile_phone_number`),
  index `idx_sys_user_status` (`status`)
) charset=utf8 ENGINE=InnoDB;;
alter table `sys_user` auto_increment=1000;;

create table `sys_organization`(
  `id`         bigint not null auto_increment,
  `name`      varchar(100),
  `type`      varchar(20),
  `icon`       varchar(200),
  `weight`    int,
  `user_count`  int,
  `description` varchar(511),
  `address`     varchar(255),
  `url`  varchar(200),
  `end_date`    datetime ,
  `create_date` datetime default '1970-01-01 00:00:00',
  `modify_time` datetime default '1970-01-01 00:00:00',
  `telephone` varchar(50),
  `status`   int,
  constraint `pk_sys_organization` primary key(`id`),
  index `idx_sys_organization_name` (`name`),
  index `idx_sys_organization_type` (`type`)
) charset=utf8 ENGINE=InnoDB;;
alter table `sys_organization` auto_increment=10000;;

create table `sys_resource`(
  `id`         bigint not null auto_increment,
  `name`      varchar(100),
  `identity`  varchar(100),
  `url`      varchar(200),
  `parent_id` bigint,
  `parent_ids`  varchar(200) default '',
  `icon`       varchar(200),
  `weight`    int,
  `is_show`       bool,
  constraint `pk_sys_resource` primary key(`id`),
  index `idx_sys_resource_name` (`name`),
  index `idx_sys_resource_identity` (`identity`),
  index `idx_sys_resource_user` (`url`),
  index `idx_sys_resource_parent_id` (`parent_id`),
  index `idx_sys_resource_parent_ids_weight` (`parent_ids`, `weight`)
) charset=utf8 ENGINE=InnoDB;;
alter table `sys_resource` auto_increment=1000;;


create table `sys_permission`(
  `id`         bigint not null auto_increment,
  `name`      varchar(100),
  `permission`  varchar(100),
  `description`      varchar(200),
  `is_show`       bool,
  constraint `pk_sys_permission` primary key(`id`),
  index idx_sys_permission_name (`name`),
  index idx_sys_permission_permission (`permission`),
  index idx_sys_permission_show (`is_show`)
) charset=utf8 ENGINE=InnoDB;;
alter table `sys_permission` auto_increment=1000;;

create table `sys_role`(
  `id`         bigint not null auto_increment,
  `name`      varchar(100),
  `role`  varchar(100),
  `description`      varchar(200),
  `is_show`       bool,
  `create_time` datetime not null default '1970-01-01 00:00:00',
  `modify_time` datetime NOT NULL default '1970-01-01 00:00:00',
  constraint `pk_sys_role` primary key(`id`),
  index `idx_sys_role_name` (`name`),
  index `idx_sys_role_role` (`role`),
  index `idx_sys_role_show` (`is_show`)
) charset=utf8 ENGINE=InnoDB;;
alter table `sys_role` auto_increment=1000;;


create table `sys_role_resource_permission`(
  `id`         bigint not null auto_increment,
  `role_id`   bigint,
  `resource_id` bigint,
  `permission_ids` varchar(500),
  constraint `pk_sys_role_resource_permission` primary key(`id`),
  constraint `unique_sys_role_resource_permission` unique(`role_id`, `resource_id`)
) charset=utf8 ENGINE=InnoDB;;
alter table `sys_role_resource_permission` auto_increment=1000;;


/**
 * user 与 role ,organization
 */
create table `sys_auth`(
  `id`         bigint not null auto_increment,
  `organization_id`       bigint,
  `user_id`        bigint,
  `role_ids`       varchar(500),
  `type`           varchar(50),
  constraint `pk_sys_auth` primary key(`id`),
  index `idx_sys_auth_organization` (`organization_id`),
  index `idx_sys_auth_user` (`user_id`),
  index `idx_sys_auth_type` (`type`)
) charset=utf8 ENGINE=InnoDB;;
alter table `sys_auth` auto_increment=1000;;

