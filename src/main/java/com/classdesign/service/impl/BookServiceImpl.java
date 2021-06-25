package com.classdesign.service.impl;

import com.classdesign.domain.Book;
import com.classdesign.mapper.BookMapper;
import com.classdesign.service.BookService;
import com.classdesign.utils.PageRequest;
import com.classdesign.utils.PageResult;
import com.classdesign.utils.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author:zyh
 * @Time:2021-05-20-22:16
 * @email:1269231889@qq.com
 */
@Slf4j
@Service
public class BookServiceImpl implements BookService {
    @Resource
    private BookMapper bookMapper;

    @Override
    public Book show(Integer bid) {
        Book book = bookMapper.show(bid);
        if (book.getIsBorrowed()==1){
            return null;
        }
        return book;
    }

    @Override
    public PageResult findPage(PageRequest pageRequest, String name) {
        return PageUtil.getRes(pageRequest,getPageInfo(pageRequest,name));
    }

    @Override
    public void insertBook(String bookName, String descriptions, Integer areaId, Integer frameId, Integer rowId, Integer specificId) {
        Book book = new Book();
        book.setBookName(bookName);
        book.setDescriptions(descriptions);
        book.setAreaId(areaId);
        book.setFrameId(frameId);
        book.setRowId(rowId);
        book.setSpecificId(specificId);
        bookMapper.insertNewBook(book);
    }


    @Override
    public void deleteBook(Integer bid) {
        bookMapper.deleteBook(bid);
    }

    /**
     * 实现了分页功能
     * @param pageRequest
     * @param name
     * @return
     */
    private PageInfo<Book> getPageInfo(PageRequest pageRequest, String name) {
        int pageSize = pageRequest.getPageSize();
        int pageNum = pageRequest.getPageNum();
        log.info("pageSize:{}",pageSize);
        log.info("pageNum:{}",pageNum);
        PageHelper.startPage(pageNum,pageSize);
        List<Book> books = bookMapper.findByNamePage(pageRequest, name);
        return new PageInfo<Book>(books);
    }
}
