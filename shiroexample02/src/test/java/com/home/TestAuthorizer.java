package com.home;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author guxc
 * @date 2020/2/15
 */
public class TestAuthorizer extends TestBase {

    @Test
    public void testIsPermitted() {
        loginAction("classpath:shiro-authorizer.ini", "gu", "123");
        //判断拥有权限：user:create
        Assert.assertTrue(getSubject().isPermitted("user1:update"));
        Assert.assertTrue(getSubject().isPermitted("user2:update"));
        //通过二进制位的方式表示权限
        Assert.assertTrue(getSubject().isPermitted("+user1+2"));//新增权限
        Assert.assertTrue(getSubject().isPermitted("+user1+8"));//查看权限
        Assert.assertTrue(getSubject().isPermitted("+user2+10"));//新增及查看

        Assert.assertFalse(getSubject().isPermitted("+user1+4"));//没有删除权限

        Assert.assertTrue(getSubject().isPermitted("menu:view"));//通过MyRolePermissionResolver解析得到的权限
    }

    @Test
    public void testIsPermitted2() {
        loginAction("classpath:shiro-authorizer-jdbc.ini", "gu", "123");
        //判断拥有权限：user:create
        Assert.assertTrue(getSubject().isPermitted("user1:update"));
        Assert.assertTrue(getSubject().isPermitted("user2:update"));
        //通过二进制位的方式表示权限
        Assert.assertTrue(getSubject().isPermitted("+user1+2"));//新增权限
        Assert.assertTrue(getSubject().isPermitted("+user1+8"));//查看权限
        Assert.assertTrue(getSubject().isPermitted("+user2+10"));//新增及查看

        Assert.assertFalse(getSubject().isPermitted("+user1+4"));//没有删除权限

        Assert.assertTrue(getSubject().isPermitted("menu:view"));//通过MyRolePermissionResolver解析得到的权限
    }
}
