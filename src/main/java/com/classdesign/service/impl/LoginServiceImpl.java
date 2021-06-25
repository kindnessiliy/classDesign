package com.classdesign.service.impl;

import com.classdesign.domain.Role;
import com.classdesign.domain.User;
import com.classdesign.mapper.RoleMapper;
import com.classdesign.mapper.UserMapper;
import com.classdesign.service.LoginService;
import com.classdesign.utils.PasswordCoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author:zyh
 * @Time:2021-05-20-22:15
 * @email:1269231889@qq.com
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findRole(User user) {
        return userMapper.findRole(user.getId());
    }

    @Override
    public void register(String name, String password, Integer roleId, String gender) {
        User user = new User();
        user.setUsername(name);
        user.setPassword(new PasswordCoder().encode(password));
        user.setGender(gender);
        log.info("user:{}",user.toString());
        userMapper.register(user);
        Integer id = userMapper.loadUserByUsername(name).getId();
        roleMapper.insertRole(id,roleId);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("username:{}", username);
        User user = userMapper.loadUserByUsername(username);
        List<Role> role = findRole(user);
        user.setRoles(role);
        log.info("roles:{}", user.getRoles());
        return user;
    }
}
