package com.classdesign.controller;

import com.classdesign.utils.QrCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

/**
 * @author:zyh
 * @Time:2021-05-20-22:22
 * @email:1269231889@qq.com TODO: 使用算法扫描书架，保证数据更新，如何实现这个
 */
@Slf4j
@Controller
@Api(tags = "扫描接口")
@RequestMapping("/scan")
public class ScanController {
    private static final String DEFAULT_FILE_PATH = "D:\\桌面\\javaweb\\newClass\\design\\src\\main\\resources\\static\\";
    private static final String SUFFIX_NAME = ".jpg";
    @PostMapping("/fileUpload")
    @ApiOperation(value = "二维码上传解析接口")
    public ResponseEntity<?> file(@RequestParam(value = "file") MultipartFile file) {
        String result = null;
        File target = null;
        try {
            if (file.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body("文件为空");
            }
            String filePath = "D:\\桌面\\javaweb\\newClass\\design\\src\\main\\resources\\static\\";
            String filName = file.getOriginalFilename();
            filName = UUID.randomUUID() + SUFFIX_NAME;
            target = new File(filePath + filName);
            file.transferTo(target);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest()
                    .body("问题");
        }
        result = QrCodeUtil.parseQRCodeByFile(target);
        if (target.exists()) {
            target.delete();
        }
        return ResponseEntity.ok()
                .body(result);
    }

    @PostMapping("/createBookQrCode")
    @ApiOperation(value = "对应书籍二维码生成")
    public ResponseEntity<?> create(@RequestParam(value = "") String context) {
        String filename = UUID.randomUUID()+SUFFIX_NAME;
        try{
            File filePath = new File(DEFAULT_FILE_PATH+filename);
            QrCodeUtil.createCodeToFile(context,filePath);
        }catch (Exception e){
            return ResponseEntity.badRequest()
                    .body("创建失败");
        }
        return ResponseEntity.ok("ok");
    }
}
