package com.classdesign.service.impl;

import com.classdesign.mapper.UserBookMapper;
import com.classdesign.myException.HasBorrowed;
import com.classdesign.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author:zyh
 * @Time:2021-05-20-22:18
 * @email:1269231889@qq.com
 */
@Service
public class BorrowServiceImpl implements BorrowService {
    @Autowired
    private UserBookMapper userBookMapper;


    @Override
    public void borrow(Integer uid, Integer bid, String bookName) throws HasBorrowed {
        if(userBookMapper.getBorrowed(bid)==0){
            userBookMapper.borrow(uid,bid,bookName);
            userBookMapper.setBorrowed(bid);
        }else{
            throw new HasBorrowed("已经被借阅");
        }
    }

    @Override
    public void returnBook(Integer bid) {
        userBookMapper.updateReturnBook(bid);
        userBookMapper.setReturn(bid);
    }
}
