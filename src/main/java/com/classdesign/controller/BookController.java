package com.classdesign.controller;

import com.classdesign.domain.Book;
import com.classdesign.service.BookService;
import com.classdesign.utils.IsEmptyUtil;
import com.classdesign.utils.PageRequest;
import com.classdesign.utils.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author:zyh
 * @Time:2021-05-20-22:21
 * @email:1269231889@qq.com
 */
@Api(tags = "图书显示接口")
@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/detail")
    @ApiOperation(value = "显示书的详细内容")
    public ResponseEntity<?> showDetails(@RequestParam Integer bid) {
        Book book = bookService.show(bid);
        if(IsEmptyUtil.objectIsEmpty(book)){
            return ResponseEntity.status(404)
                    .body("书籍被借阅 ");
        }
        return ResponseEntity.ok()
                .body(book);
    }

    @PostMapping("/find")
    @ApiOperation(value = "查找书籍")
    public ResponseEntity<?> find(@RequestParam String bookName,
                                  @RequestBody PageRequest request) {
        PageResult page = bookService.findPage(request, bookName);
        if (IsEmptyUtil.objectIsEmpty(page)) {
            return ResponseEntity.status(404)
                    .body("没有书籍");
        }
        return ResponseEntity.ok()
                .body(page);
    }

    @PostMapping("/insert")
    @ApiOperation(value = "插入新书")
    public ResponseEntity<?> insert(@RequestParam String bookName, @RequestParam String descriptions,
                         @RequestParam Integer areaId, @RequestParam Integer frameId,
                         @RequestParam Integer rowId, @RequestParam Integer specificId){
        bookService.insertBook(bookName,descriptions,areaId,frameId,rowId,specificId);
        return ResponseEntity.ok("success");
    }

    @GetMapping("/deleteBook")
    @ApiOperation(value = "删除书籍")
    public String delete(@RequestParam Integer bid){
        bookService.deleteBook(bid);
        return "ok";
    }
}
