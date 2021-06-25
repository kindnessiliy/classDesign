package com.classdesign.utils;

import lombok.Data;

import java.util.List;

/**
 * @author:zyh
 * @Time:2021-05-20-22:02
 * @email:1269231889@qq.com
 */
@Data
public class PageResult {
    /**
     * 页面大小
     */
    private int pageSize;
    /**
     * 总页数
     */
    private int totalPage;
    /**
     * 总数据大小
     */
    private long totalSize;
    /**
     * 当前页数
     */
    private int pageNum;
    /**
     * 数据模型
     */
    private List<?> list;
}
