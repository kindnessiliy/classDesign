package com.classdesign.controller;

import com.classdesign.domain.User;
import com.classdesign.myException.HasBorrowed;
import com.classdesign.service.BorrowService;
import com.classdesign.utils.GetUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author:zyh
 * @Time:2021-05-20-22:22
 * @email:1269231889@qq.com
 */
@Controller
@Api(tags = "借还书接口")
@RequestMapping("/service")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @ApiOperation("借书")
    @PostMapping("/borrowBook")
    public ResponseEntity<?> borrowing(@RequestParam Integer bid, @RequestParam String name) {
        User user = GetUser.user();
        try {
            borrowService.borrow(user.getId(), bid, name);
        }catch (HasBorrowed hasBorrowed){
            return ResponseEntity.status(403)
                    .body("书已经被借阅了");
        }catch (Exception e) {
            return ResponseEntity.status(502)
                    .body("问题，重新借阅");
        }
        return ResponseEntity.ok()
                .body("借阅成功");
    }

    //TODO:还书
    @ApiOperation("还书")
    @PostMapping("/returnBook")
    public  ResponseEntity<?> returning(Integer bid){
        try {
            borrowService.returnBook(bid);
        }catch (Exception e){
            return ResponseEntity.status(402)
                    .body("error");
        }
        return ResponseEntity.ok("success");
    }

}
