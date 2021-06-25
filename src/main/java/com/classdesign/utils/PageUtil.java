package com.classdesign.utils;

import com.github.pagehelper.PageInfo;

/**
 * @author:zyh
 * @Time:2021-05-20-22:02
 * @email:1269231889@qq.com
 */
public class PageUtil {
    public static PageResult getRes(PageRequest pageRequest, PageInfo<?> pageInfo){
        PageResult res = new PageResult();
        res.setPageSize(pageInfo.getPageSize());
        res.setPageNum(pageInfo.getPageNum());
        res.setTotalPage(pageInfo.getPages());
        res.setTotalSize(pageInfo.getTotal());
        res.setList(pageInfo.getList());
        return res;
    }
}
