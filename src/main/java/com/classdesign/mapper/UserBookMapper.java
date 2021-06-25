package com.classdesign.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @author:zyh
 * @Time:2021-05-20-22:03
 * @email:1269231889@qq.com
 */
public interface UserBookMapper {

    void borrow(@Param("uid") Integer uid, @Param("bid") Integer bid, @Param("name") String bookName);

    void setBorrowed(Integer bid);

    void updateReturnBook(Integer bid);

    void setReturn(Integer bid);

    Integer getBorrowed(Integer bid);
}
