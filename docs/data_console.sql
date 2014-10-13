SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `data_console` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;

USE `data_console`;

CREATE  TABLE IF NOT EXISTS `data_console`.`sys_auth` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `organization_id` BIGINT(20) NULL DEFAULT NULL ,
  `user_id` BIGINT(20) NULL DEFAULT NULL ,
  `role_ids` VARCHAR(500) NULL DEFAULT NULL ,
  `type` VARCHAR(50) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `idx_sys_auth_organization` (`organization_id` ASC) ,
  INDEX `idx_sys_auth_user` (`user_id` ASC) ,
  INDEX `idx_sys_auth_type` (`type` ASC) )
ENGINE = InnoDB
AUTO_INCREMENT = 1000
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE  TABLE IF NOT EXISTS `data_console`.`sys_organization` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '组织,机构ID' ,
  `name` VARCHAR(100) NULL DEFAULT NULL COMMENT '组织名称' ,
  `type` VARCHAR(20) NULL DEFAULT NULL COMMENT '类型' ,
  `icon` VARCHAR(200) NULL DEFAULT NULL ,
  `weight` INT(11) NULL DEFAULT NULL ,
  `user_count` INT(11) NULL DEFAULT NULL COMMENT '用户数' ,
  `description` VARCHAR(511) NULL DEFAULT NULL COMMENT '组织描述' ,
  `address` VARCHAR(255) NULL DEFAULT NULL COMMENT '地址' ,
  `url` VARCHAR(200) NULL DEFAULT NULL COMMENT 'URL' ,
  `end_date` DATETIME NULL DEFAULT NULL COMMENT '结束时间' ,
  `create_date` DATETIME NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间' ,
  `modify_time` DATETIME NULL DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间' ,
  `telephone` VARCHAR(50) NULL DEFAULT NULL ,
  `status` INT(11) NULL DEFAULT NULL COMMENT '状态' ,
  `sys_user_id` BIGINT(20) NOT NULL ,
  `sys_user_sys_user_organization_id` BIGINT(20) NOT NULL ,
  `sys_group_relation_id` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`id`, `sys_user_id`, `sys_user_sys_user_organization_id`) ,
  INDEX `idx_sys_organization_name` (`name` ASC) ,
  INDEX `idx_sys_organization_type` (`type` ASC) ,
  INDEX `fk_sys_organization_sys_user1` (`sys_user_id` ASC, `sys_user_sys_user_organization_id` ASC) ,
  INDEX `fk_sys_organization_sys_group_relation1` (`sys_group_relation_id` ASC) ,
  CONSTRAINT `fk_sys_organization_sys_user1`
    FOREIGN KEY (`sys_user_id` , `sys_user_sys_user_organization_id` )
    REFERENCES `data_console`.`sys_user` (`id` , `sys_user_organization_id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_sys_organization_sys_group_relation1`
    FOREIGN KEY (`sys_group_relation_id` )
    REFERENCES `data_console`.`sys_group_relation` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 10000
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = '组织,机构,团体表\n';

CREATE  TABLE IF NOT EXISTS `data_console`.`sys_permission` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '权限ID' ,
  `name` VARCHAR(100) NULL DEFAULT NULL COMMENT '权限名陈' ,
  `permission` VARCHAR(100) NULL DEFAULT NULL COMMENT '权限' ,
  `description` VARCHAR(200) NULL DEFAULT NULL COMMENT '描述' ,
  `is_show` TINYINT(1) NULL DEFAULT NULL ,
  `sys_role_resource_permission_id` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `idx_sys_permission_name` (`name` ASC) ,
  INDEX `idx_sys_permission_permission` (`permission` ASC) ,
  INDEX `idx_sys_permission_show` (`is_show` ASC) ,
  INDEX `fk_sys_permission_sys_role_resource_permission1` (`sys_role_resource_permission_id` ASC) ,
  CONSTRAINT `fk_sys_permission_sys_role_resource_permission1`
    FOREIGN KEY (`sys_role_resource_permission_id` )
    REFERENCES `data_console`.`sys_role_resource_permission` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1000
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = '权限表\n';

CREATE  TABLE IF NOT EXISTS `data_console`.`sys_resource` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NULL DEFAULT NULL ,
  `identity` VARCHAR(100) NULL DEFAULT NULL ,
  `url` VARCHAR(200) NULL DEFAULT NULL ,
  `parent_id` BIGINT(20) NULL DEFAULT NULL ,
  `parent_ids` VARCHAR(200) NULL DEFAULT '' ,
  `icon` VARCHAR(200) NULL DEFAULT NULL ,
  `weight` INT(11) NULL DEFAULT NULL ,
  `is_show` TINYINT(1) NULL DEFAULT NULL ,
  `sys_role_resource_permission_id` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `idx_sys_resource_name` (`name` ASC) ,
  INDEX `idx_sys_resource_identity` (`identity` ASC) ,
  INDEX `idx_sys_resource_user` (`url` ASC) ,
  INDEX `idx_sys_resource_parent_id` (`parent_id` ASC) ,
  INDEX `idx_sys_resource_parent_ids_weight` (`parent_ids` ASC, `weight` ASC) ,
  INDEX `fk_sys_resource_sys_role_resource_permission1` (`sys_role_resource_permission_id` ASC) ,
  CONSTRAINT `fk_sys_resource_sys_role_resource_permission1`
    FOREIGN KEY (`sys_role_resource_permission_id` )
    REFERENCES `data_console`.`sys_role_resource_permission` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1000
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE  TABLE IF NOT EXISTS `data_console`.`sys_role` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID' ,
  `name` VARCHAR(100) NULL DEFAULT NULL COMMENT '角色名称' ,
  `role` VARCHAR(100) NULL DEFAULT NULL COMMENT '角色' ,
  `description` VARCHAR(200) NULL DEFAULT NULL COMMENT '描述\n' ,
  `is_show` TINYINT(1) NULL DEFAULT NULL ,
  `create_time` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '创建时间' ,
  `modify_time` DATETIME NOT NULL DEFAULT '1970-01-01 00:00:00' COMMENT '更新时间' ,
  `sys_role_resource_permission_id` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `idx_sys_role_name` (`name` ASC) ,
  INDEX `idx_sys_role_role` (`role` ASC) ,
  INDEX `idx_sys_role_show` (`is_show` ASC) ,
  INDEX `fk_sys_role_sys_role_resource_permission1` (`sys_role_resource_permission_id` ASC) ,
  CONSTRAINT `fk_sys_role_sys_role_resource_permission1`
    FOREIGN KEY (`sys_role_resource_permission_id` )
    REFERENCES `data_console`.`sys_role_resource_permission` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1000
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE  TABLE IF NOT EXISTS `data_console`.`sys_role_resource_permission` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `role_id` BIGINT(20) NULL DEFAULT NULL ,
  `resource_id` BIGINT(20) NULL DEFAULT NULL ,
  `permission_ids` VARCHAR(500) NULL DEFAULT NULL ,
  `sys_permission_id` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 1000
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE  TABLE IF NOT EXISTS `data_console`.`sys_user` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `username` VARCHAR(100) NULL DEFAULT NULL ,
  `email` VARCHAR(100) NULL DEFAULT NULL ,
  `mobile_phone_number` VARCHAR(20) NULL DEFAULT NULL ,
  `password` VARCHAR(100) NULL DEFAULT NULL ,
  `salt` VARCHAR(10) NULL DEFAULT NULL ,
  `status` VARCHAR(50) NULL DEFAULT NULL ,
  `organization_id` INT(11) NULL DEFAULT NULL ,
  `organization_name` VARCHAR(255) NULL DEFAULT NULL ,
  `deleted` TINYINT(1) NULL DEFAULT NULL ,
  `admin` TINYINT(1) NULL DEFAULT NULL ,
  `create_date` DATETIME NULL DEFAULT '1970-01-01 00:00:00' ,
  `modify_time` DATETIME NULL DEFAULT '1970-01-01 00:00:00' ,
  `sys_user_organization_id` BIGINT(20) NOT NULL ,
  `login_time` DATETIME NULL DEFAULT NULL COMMENT '登录时间' ,
  `last_login_time` DATETIME NULL DEFAULT NULL COMMENT '上次登录时间' ,
  `count` BIGINT(20) NULL DEFAULT NULL COMMENT '登录次数\n' ,
  `sys_auth_id` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`id`, `sys_user_organization_id`) ,
  UNIQUE INDEX `unique_sys_user_username` (`username` ASC) ,
  UNIQUE INDEX `unique_sys_user_email` (`email` ASC) ,
  UNIQUE INDEX `unique_sys_user_mobile_phone_number` (`mobile_phone_number` ASC) ,
  INDEX `idx_sys_user_status` (`status` ASC) ,
  INDEX `fk_sys_user_sys_auth1` (`sys_auth_id` ASC) ,
  CONSTRAINT `fk_sys_user_sys_auth1`
    FOREIGN KEY (`sys_auth_id` )
    REFERENCES `data_console`.`sys_auth` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 1000
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE  TABLE IF NOT EXISTS `data_console`.`sys_user_organization` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `user_id` BIGINT(20) NOT NULL ,
  `organization_id` BIGINT(20) NOT NULL ,
  `job_id` BIGINT(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE  TABLE IF NOT EXISTS `data_console`.`sys_group` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '组ID\n' ,
  `name` VARCHAR(100) NULL DEFAULT NULL ,
  `type` VARCHAR(45) NULL DEFAULT NULL ,
  `is_show` TINYINT(1) NULL DEFAULT NULL COMMENT '是否显示\n' ,
  `default_group` TINYINT(1) NULL DEFAULT NULL COMMENT '默认组' ,
  `create_time` DATETIME NULL DEFAULT NULL COMMENT '创建时间' ,
  `modify_time` DATETIME NULL DEFAULT NULL COMMENT '修改时间' ,
  `description` VARCHAR(255) NULL DEFAULT NULL COMMENT '组描述\n' ,
  `sys_group_relation_id` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`id`, `sys_group_relation_id`) ,
  INDEX `fk_sys_group_sys_group_relation1` (`sys_group_relation_id` ASC) ,
  CONSTRAINT `fk_sys_group_sys_group_relation1`
    FOREIGN KEY (`sys_group_relation_id` )
    REFERENCES `data_console`.`sys_group_relation` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci;

CREATE  TABLE IF NOT EXISTS `data_console`.`sys_group_relation` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `group_id` BIGINT(20) NULL DEFAULT NULL ,
  `organization_id` BIGINT(20) NULL DEFAULT NULL ,
  `user_id` BIGINT(20) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `idx_sys_group_relation_group` (`group_id` ASC) ,
  INDEX `idx_sys_group_relation_organization` (`organization_id` ASC) ,
  INDEX `idx_sys_group_relation_user` (`user_id` ASC) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = '用户组,组织关联表';

CREATE  TABLE IF NOT EXISTS `data_console`.`sys_job` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(100) NULL DEFAULT NULL ,
  `parent_id` BIGINT(20) NULL DEFAULT NULL ,
  `parent_ids` VARCHAR(200) NULL DEFAULT NULL ,
  `is_show` TINYINT(1) NULL DEFAULT NULL ,
  `icon` VARCHAR(200) NULL DEFAULT NULL ,
  `sys_user_organization_id` BIGINT(20) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_sys_job_sys_user_organization1` (`sys_user_organization_id` ASC) ,
  CONSTRAINT `fk_sys_job_sys_user_organization1`
    FOREIGN KEY (`sys_user_organization_id` )
    REFERENCES `data_console`.`sys_user_organization` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_general_ci
COMMENT = '职位表';


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
