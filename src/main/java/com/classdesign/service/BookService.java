package com.classdesign.service;

import com.classdesign.domain.Book;
import com.classdesign.utils.PageRequest;
import com.classdesign.utils.PageResult;

/**
 * @author:zyh
 * @Time:2021-05-20-22:12
 * @email:1269231889@qq.com
 */

public interface BookService {

    Book show(Integer bid);

    PageResult findPage(PageRequest pageRequest, String name);

    void insertBook(String bookName, String descriptions, Integer areaId,Integer frameId,Integer rowId,Integer specificId);

    void deleteBook(Integer bid);
}
