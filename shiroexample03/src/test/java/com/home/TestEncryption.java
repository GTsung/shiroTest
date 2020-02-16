package com.home;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.junit.Assert;
import org.junit.Test;

/**
 * 加密解密
 *
 * @author guxc
 * @date 2020/2/16
 */
public class TestEncryption {

    @Test
    public void testBase64() {
        String str = "gu_wu123";
        // base64编码
        String base64Encode = Base64.encodeToString(str.getBytes());
        // 解码
        String s = Base64.decodeToString(base64Encode);
        Assert.assertEquals(str, s);
    }

    @Test
    public void test16() {
        // 16进制编码解码
        String str = "hello";
        String base64Encoded = Hex.encodeToString(str.getBytes());
        String str2 = new String(Hex.decode(base64Encoded.getBytes()));
        Assert.assertEquals(str, str2);
    }

    @Test
    public void testHash() {
        // 散列加密算法，一般加密后是不可逆的，常用算法有MD5,SHA等
        String pwd = "123";
        String salt = "salt";

        // MD5
//        String md5 = new Md5Hash(pwd, salt).toString(); // 8c4fb7bf681156b52fea93442c7dffc9
//        String md5 = new Md5Hash(pwd, salt).toBase64(); // jE+3v2gRVrUv6pNELH3/yQ==
        String md5 = new Md5Hash(pwd, salt).toHex(); // 8c4fb7bf681156b52fea93442c7dffc9
        System.out.println(md5);

        // SHA
        String sha = new Sha256Hash(pwd, salt).toString();
        System.out.println(sha);

    }
}