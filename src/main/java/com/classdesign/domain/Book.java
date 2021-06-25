package com.classdesign.domain;

import lombok.Data;

/**
 * @author:zyh
 * @Time:2021-05-20-21:54
 * @email:1269231889@qq.com
 */
@Data
public class Book {
    private Integer id;
    private String bookName;
    private String descriptions;
    private Integer areaId;
    private Integer frameId;
    private Integer rowId;
    private Character isBorrowed;
    /**
     * 具体的位置id
     * */
    private Integer specificId;
}
