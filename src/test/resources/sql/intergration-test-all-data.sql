delete from `sys_organization`;
delete from `sys_user`;

delete from `sys_auth`;

--用户
insert into `sys_user`
  (`id`, `username`, `email`, `mobile_phone_number`, `password`, `salt`, `create_date`, `status`, `admin`, `deleted`)
values
  (1, 'admin', 'admin@sishuok.com', '13412345678', 'ec21fa1738f39d5312c6df46002d403d', 'yDd1956wn1', sysdate(), 'normal', 1, 0);

--组织机构
insert into `sys_organization`(`id`, `user_count`, weight, `name`, `status`) values (1, 2, 0, '鸿天服饰', 0);
insert into `sys_organization`(`id`, `user_count`, weight, `name`, `status`) values (2, 3, 1, '羽中飞马甸店', 0);
insert into `sys_organization`(`id`, `user_count`, weight, `name`, `status`) values (3, 5, 1, '达达五金', 1);


--授权
--用户授权
insert into sys_auth (`id`, `organization_id`,`user_id`, `role_ids`, `type`)
    values(1, 0, 1, '2,10,6', 'user');

--用户组授权
insert into sys_auth (`id`, `organization_id`, `user_id`, `role_ids`, `type`)
    values(2, 0, 0, '1', 'user_group');
insert into sys_auth (`id`, `organization_id`,`user_id`, `role_ids`, `type`)
    values(3, 0, 0, '2,3', 'user_group');

--组织机构组授权
insert into sys_auth (`id`, `organization_id`, `user_id`, `role_ids`, `type`)
    values(4, 0, 3, '2,3', 'organization_group');
insert into sys_auth (`id`, `organization_id`, `user_id`,`role_ids`, `type`)
    values(5, 0, 4, '1', 'organization_group');
