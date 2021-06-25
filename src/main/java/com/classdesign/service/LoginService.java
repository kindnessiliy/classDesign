package com.classdesign.service;

import com.classdesign.domain.Role;
import com.classdesign.domain.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @author:zyh
 * @Time:2021-05-20-22:12
 * @email:1269231889@qq.com
 */
public interface LoginService extends UserDetailsService {
    List<Role> findRole(User user);

    void register(String username, String password, Integer roleId,String gender);
}
