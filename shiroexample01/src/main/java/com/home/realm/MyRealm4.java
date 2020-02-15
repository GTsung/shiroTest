package com.home.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * @date 2020/2/15
 */
public class MyRealm4 implements Realm {
    @Override
    public String getName() {
        return "myRealm4";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken; // 仅支持UsernamePasswordToken
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String userName = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());
        if (!"gu".equals(userName)) {
            throw new UnknownAccountException(); // 用户名错误
        }
        if (!"123".equals(password)) {
            throw new IncorrectCredentialsException(); // 密码错误
        }
        // 验证成功
        return new SimpleAuthenticationInfo(userName, password, getName());
    }
}
