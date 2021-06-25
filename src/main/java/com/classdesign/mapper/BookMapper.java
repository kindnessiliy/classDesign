package com.classdesign.mapper;

import com.classdesign.domain.Book;
import com.classdesign.utils.PageRequest;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author:zyh
 * @Time:2021-05-20-22:03
 * @email:1269231889@qq.com
 */

public interface BookMapper {
    Book show(Integer bid);

    void updateLocation(Integer frameId, Integer rowId, Integer bookId);

    List<Book> findByName(String bookName);

    /**
     *
     * @param request
     * @param bookName
     * @return
     * 使用查询分页功能
     */
    List<Book> findByNamePage(PageRequest request, @Param("bookName") String bookName);


    void insertNewBook(Book book);

    void deleteBook(Integer bid);
}
