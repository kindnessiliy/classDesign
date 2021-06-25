package com.classdesign.service.impl;

import com.classdesign.domain.Book;
import com.classdesign.domain.User;
import com.classdesign.mapper.UserMapper;
import com.classdesign.service.UserService;
import com.classdesign.utils.PasswordCoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @author:zyh
 * @Time:2021-05-20-22:18
 * @email:1269231889@qq.com
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public HashMap<String,String> show(Integer id) {
        User show = userMapper.show(id);
        HashMap<String, String> temp = new HashMap<>(4);
        temp.put("username",show.getUsername());
        temp.put("gender",show.getGender());
        return temp;
    }

    @Override
    public void change(Integer id, User user) {
        userMapper.update(user);
    }

    @Override
    public List<Book> haveBorrowed(Integer id) {
        List<Book> books = userMapper.hasBorrowed(id);
        return books;
    }

    @Override
    public boolean isPassword(String original,String password) {
        PasswordCoder passwordCoder = new PasswordCoder();
        return passwordCoder.matches(password,original);
    }

    @Override
    public void updatePassword(String newPassword,int uid) {
        PasswordCoder passwordCoder = new PasswordCoder();
        userMapper.updatePassword(passwordCoder.encode(newPassword),uid);
    }

}
