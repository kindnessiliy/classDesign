package com.classdesign.utils;

import lombok.Data;

/**
 * @author:zyh
 * @Time:2021-05-20-22:01
 * @email:1269231889@qq.com
 */
@Data
public class PageRequest {
    /**
     * 每页数目
     * */
    private int pageSize;
    /**
     * 当前页码
     * */
    private int pageNum;
}
