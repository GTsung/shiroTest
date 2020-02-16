package com.home.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * @author guxc
 * @date 2020/2/16
 */
public class MyRealm2 implements Realm {


    @Override
    public String getName() {
        return "b"; //realm name 为 “b”
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        return new SimpleAuthenticationInfo(
                "gu", //身份 字符串类型
                "123",   //凭据
                getName() //Realm Name
        );
    }
}
