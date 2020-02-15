package com.home;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author guxiaocong
 * @date 2020/2/15
 */
public class TestShiro {

    private Subject getSubjectInstance(String path) {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(path);
        SecurityManager securityManager = factory.getInstance();
        // 全局设置，绑定SecurityManager
        SecurityUtils.setSecurityManager(securityManager);
        // 获取subject，绑定到当前线程
        return SecurityUtils.getSubject();
    }

    /**
     * 读取shiro.ini进行登陆验证
     */
    @Test
    public void testHelloWord() {
        Subject subject = getSubjectInstance("classpath:shiro.ini");
        UsernamePasswordToken token = new UsernamePasswordToken("gu", "123");
        subject.login(token); // 内部通过realms去校验
        Assert.assertTrue(subject.isAuthenticated());
        subject.logout();
    }

    /**
     * 读取shiro-realm.ini进行自定义的单个realm验证
     */
    @Test
    public void testSingleMyRealm() {
        Subject subject = getSubjectInstance("classpath:shiro-realm.ini");
        // 由于自定义的myRealm1中指定了username与password，故ini文件中不需要再配置[users]
        UsernamePasswordToken token = new UsernamePasswordToken("gu", "123");
        try {
            //4、登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            //5、身份验证失败
            e.printStackTrace();
        }
        Assert.assertTrue(subject.isAuthenticated()); //断言用户已经登录
        //6、退出
        subject.logout();
    }

    /**
     * 测试多realm，读取shiro-multi-realm.ini
     */
    @Test
    public void testMultiRealms() {
        Subject subject = getSubjectInstance("classpath:shiro-multi-realm.ini");
        UsernamePasswordToken token = new UsernamePasswordToken("wu", "111");
        subject.login(token);
        Assert.assertTrue(subject.isAuthenticated()); //断言用户已经登录
        subject.logout();
    }

    /**
     * jdbcRealm
     */
    @Test
    public void testJDBCRealm() {
        // shiro-jdbc-realm.ini文件中配置了使用shiro内部的jdbcRealm去验证
        Subject subject = getSubjectInstance("classpath:shiro-jdbc-realm.ini");
        // 此时的username与password在数据库表中存储
        UsernamePasswordToken token = new UsernamePasswordToken("gu", "123");
        subject.login(token);
        Assert.assertTrue(subject.isAuthenticated()); //断言用户已经登录
        subject.logout();
    }
}
