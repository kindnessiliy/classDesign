package com.classdesign.service;

/**
 * @author:zyh
 * @Time:2021-05-20-22:12
 * @email:1269231889@qq.com
 */
public interface BorrowService {

    /**
     * 借书功能实现
     * @param :user id,book id,book name
     * @return :boolean
     *
     * */
    void borrow(Integer uid,Integer bid,String bookName) throws Exception;

    /**
     * 还书
     * @param bid
     */
    void returnBook(Integer bid);

}
