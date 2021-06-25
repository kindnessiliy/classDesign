package com.classdesign.mapper;

import com.classdesign.domain.Book;
import com.classdesign.domain.Role;
import com.classdesign.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author:zyh
 * @Time:2021-05-20-22:05
 * @email:1269231889@qq.com
 */
public interface UserMapper {
    User loadUserByUsername(String username);

    User show(Integer id);

    void update(User user);

    List<Book> hasBorrowed(Integer id);

    void register(User user);

    List<Role> findRole(Integer id);

    void updatePassword(@Param(value = "password") String password,@Param(value = "uid") Integer uid);
}
