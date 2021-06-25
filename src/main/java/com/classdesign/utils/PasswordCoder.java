package com.classdesign.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author:zyh
 * @Time:2021-05-20-22:02
 * @email:1269231889@qq.com
 */

public class PasswordCoder implements PasswordEncoder {
    private static final String SALT = "library";

    private String md5(String str) {
        return DigestUtils.md5Hex(str);
    }

    public String formPassWord(String password) {
        String tmp = SALT.charAt(0) + password + SALT.charAt(1);
        return md5(tmp);
    }

    @Override
    public String encode(CharSequence charSequence) {
        String password = charSequence.toString();
        return formPassWord(password);
    }

    @Override
    public boolean matches(CharSequence password, String encodedPassword) {
        return encodedPassword.equals(encode(password));
    }
}
