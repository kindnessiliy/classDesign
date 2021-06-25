package com.classdesign.controller;

import com.classdesign.domain.User;
import com.classdesign.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author:zyh
 * @Time:2021-05-20-22:19
 * @email:1269231889@qq.com
 */
@Api(tags = "登录接口")
@Slf4j
@Controller
public class LoginController {

    @Autowired
    private LoginService adminService;
    @Autowired
    private RedisTemplate<String, User> redisTemplate;

    @GetMapping("/login")
    @ApiOperation(value = "登录接口")
    public String login() {
        return "login";
    }


    @PostMapping("/register")
    @ResponseBody
    @ApiOperation("注册接口")
    public ResponseEntity<?> register(@RequestParam(value = "用户名不能为空") String username,
                                   @RequestParam(value = "密码不能为空") String password,
                                   @RequestParam(value = "性别不能为空") String gender,
                                   Model model) {
        log.info("register");
        try {
            log.info("begin");
            adminService.register(username, password, 2,gender);
            return ResponseEntity.ok()
                    .body("success");
        } catch (Exception e) {
            return ResponseEntity.status(502)
                    .body("error");
        }
    }

}
