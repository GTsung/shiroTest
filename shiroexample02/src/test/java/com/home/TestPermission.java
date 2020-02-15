package com.home;

import org.apache.shiro.authz.UnauthorizedException;
import org.junit.Assert;
import org.junit.Test;

/**
 * @date 2020/2/15
 */
public class TestPermission extends TestBase {

    @Test
    public void testHasPermission() {
        loginAction("classpath:shiro-permission.ini", "gu", "123");
        // 判断拥有权限：user:create
        Assert.assertTrue(getSubject().isPermitted("user:create"));
        // 判断拥有权限：user:update and user:delete
        Assert.assertTrue(getSubject().isPermittedAll("user:update", "user:delete"));
        // 判断没有权限：user:view
        Assert.assertFalse(getSubject().isPermitted("user:view"));
    }

    @Test(expected = UnauthorizedException.class)
    public void testCheckPermission () {
        loginAction("classpath:shiro-permission.ini", "gu", "123");
        // 断言拥有权限：user:create
        getSubject().checkPermission("user:create");
        // 断言拥有权限：user:delete and user:update
        getSubject().checkPermissions("user:delete", "user:update");
        // 断言拥有权限：user:view 失败抛出异常
        getSubject().checkPermissions("user:view");
    }

}
