package com.classdesign.controller;

import com.classdesign.domain.Book;
import com.classdesign.domain.User;
import com.classdesign.service.UserService;
import com.classdesign.utils.GetUser;
import com.classdesign.utils.IsEmptyUtil;
import com.classdesign.utils.PasswordCoder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author:zyh
 * @Time:2021-05-20-22:22
 * @email:1269231889@qq.com
 */
@Slf4j
@Controller
@Api(tags = "用户接口")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }

    @GetMapping("/loginAgainError")
    @ResponseBody
    public String notLoginAgain() {
        return "已经登录，请不要重复登录，如果想更换，请先退出当前用户";
    }

    @PostMapping("/main")
    @ApiOperation(value = "显示用户信息")
    public ResponseEntity<?> main() {
        log.info("hello");
        User user = GetUser.user();
        return ResponseEntity.ok()
                .body("username:" + user.getUsername()
                        + "gender:" + user.getGender());
    }

    //TODO: 需要结合前端一起写
    @PostMapping("/change")
    @ApiOperation(value = "修改用户信息")
    public String change(String name,String gender) {
        User user = GetUser.user();
        user.setUsername(name);
        user.setGender(gender);
        userService.change(user.getId(),user);
        return "ok";
    }

    @GetMapping("/haveBorrowed")
    @ApiOperation(value = "显示用户借书情况")
    public ResponseEntity<?> borrow() {
        try {
            User user = GetUser.user();
            List<Book> books = userService.haveBorrowed(user.getId());
            if (IsEmptyUtil.listIsEmpty(books)) {
                return ResponseEntity.ok()
                        .body("没有借阅数据");
            }
            return ResponseEntity.ok()
                    .body(books);
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(e.getMessage());
        }
    }

    @PostMapping("/newPassword")
    @ApiOperation(value = "改密操作")
    public ResponseEntity<?> newPassword(@RequestParam(value = "原密码")String oldPassword,
                                         @RequestParam(value = "新密码")String newPassword){
        PasswordCoder passwordCoder = new PasswordCoder();
        User user = GetUser.user();
        log.info("old password:{}",oldPassword);
        if(userService.isPassword(user.getPassword(),oldPassword)){
            log.info("密码匹配");
            userService.updatePassword(newPassword,user.getId());
            return ResponseEntity.ok()
                    .body("change successfully");
        }else{
            return ResponseEntity.status(404)
                    .body("旧密码错误");
        }
    }

}
