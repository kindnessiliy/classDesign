package com.classdesign.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @author:zyh
 * @Time:2021-06-06-18:28
 * @email:1269231889@qq.com
 */
public interface RoleMapper {
    //通过role id查找对应的role
//    List<Role> findByRid(Integer id);

    void insertRole(@Param(value = "uid") int uid,@Param(value = "rid") int rid);
}
