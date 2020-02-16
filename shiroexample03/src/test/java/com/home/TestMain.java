package com.home;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author guxc
 * @date 2020/2/16
 */
public class TestMain {

    @Test
    public void testMain(){
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("classpath:shiro-config-main.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("gu","123");
        subject.login(token);
        Assert.assertTrue(subject.isAuthenticated());
    }
}
