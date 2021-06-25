package com.classdesign.myException;

/**
 * @author:zyh
 * @Time:2021-05-20-21:53
 * @email:1269231889@qq.com
 */
public class NotSuchBookException extends Exception{
    public NotSuchBookException(String message) {
        super(message);
    }
}
