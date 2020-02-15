package com.home;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * @date 2020/2/15
 */
public class TestRole extends TestBase {

    @Test
    public void testHasRole() {
        loginAction("classpath:shiro-role.ini", "gu", "123");
        Assert.assertTrue(getSubject().hasRole("role1"));
        Assert.assertTrue(getSubject().hasAllRoles(Arrays.asList("role1", "role2")));
        // 判断拥有角色：role1 and role2 and !role3
        boolean[] result = getSubject().hasRoles(Arrays.asList("role1", "role2", "role3"));
        Assert.assertTrue(result[0]);
        Assert.assertTrue(result[1]);
        Assert.assertFalse(result[2]);
    }

    /**
     * 判断用户是否有权限
     */
    @Test(expected = UnauthorizedException.class)
    public void testCheckRole() {
        loginAction("classpath:shiro-role.ini", "gu", "123");
        // 断言拥有角色：role1
        getSubject().checkRole("role1");
        // 断言拥有角色：role1 and role3 失败抛出异常
        getSubject().checkRoles("role1", "role3");
    }
}
