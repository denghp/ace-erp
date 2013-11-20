package com.ace.erp.dao;

import com.ace.erp.entity.sys.Role;
import com.ace.erp.entity.sys.User;
import com.ace.erp.entity.sys.UserRole;
import com.ace.erp.persistence.sys.RoleMapper;
import com.ace.erp.service.sys.UserService;
import com.ace.erp.persistence.sys.UserMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/*.xml")
public class UserMapperTest {
    @Resource
    private UserService userService;

    @Resource
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    private User user = null;
    @Before
    public void setup() {
        user = new User();
        user.setUsername("admin_"+ System.currentTimeMillis());
        user.setPassword("123");
        user.setEmail(System.currentTimeMillis()+"@126.com");
    }

    @Test
    public void saveUser() throws Exception {
        userService.save(user);
    }

    @Test
    public void testMapper() {
        userMapper.save(user);
    }

    @Test
    public void testsaveUserRole() {
        userMapper.save(user);
        List<Role> roleList = roleMapper.getAllRoles();
        UserRole userRole = new UserRole();
        userRole.setUser(user);
        userRole.setRole(roleList.get(0));
        userMapper.saveUserRole(userRole);

        userRole.setRole(roleList.get(1));
        userMapper.saveUserRole(userRole);
    }

    @Test
    public void testGetUserRoleList() {
       User user = userMapper.getUserRoleList(1002);
       for(Role role : user.getRoleList()) {
           System.out.println(role);
       }
    }
}
