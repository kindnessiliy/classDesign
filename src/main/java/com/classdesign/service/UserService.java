package com.classdesign.service;

import com.classdesign.domain.Book;
import com.classdesign.domain.User;

import java.util.HashMap;
import java.util.List;

/**
 * @author:zyh
 * @Time:2021-05-20-22:12
 * @email:1269231889@qq.com
 */
public interface UserService  {
    /**
     * @return : User
     * @param : id
     *  显示个人信息
     *
     * */
    HashMap<String,String> show(Integer id);

    /**
     * @return : void
     * @param : id,user
     *   修改个人信息
     * */
    void change(Integer id, User use);


    /**
     * @param : id
     * @return :List<String>
     * 显示已经借阅的书
     *
     * */
    List<Book> haveBorrowed(Integer id);

    /**
     *
     * @param password
     * @return
     * 匹配原密码和现有密码
     */
    boolean isPassword(String original,String password);

    /**
     * @param newPassword
     * 写入新密码
     */
    void updatePassword(String newPassword,int uid);
}
