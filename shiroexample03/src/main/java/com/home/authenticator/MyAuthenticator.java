package com.home.authenticator;

import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * @author guxc
 * @date 2020/2/16
 */
public class MyAuthenticator extends ModularRealmAuthenticator {

    public void setBytes(byte[] bytes) {
        System.out.println(new String(bytes));
    }

    public void setArray(int[] ints) {
        System.out.println(Arrays.toString(ints));
    }

    public void setSet(Set<Realm> realms) {
        System.out.println(realms);
    }

    public void setMap(Map<Object, Object> maps) {
        System.out.println(maps);
        System.out.println(maps.get("1"));
    }
}
