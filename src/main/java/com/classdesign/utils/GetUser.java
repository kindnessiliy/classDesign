package com.classdesign.utils;

import com.classdesign.domain.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author:zyh
 * @Time:2021-06-06-18:05
 * @email:1269231889@qq.com
 */
public class GetUser {
    public static User user(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication==null){
            return null;
        }
        Object user = authentication.getPrincipal();
        return (User) user;
    }
}
