package com.classdesign.utils;

import java.util.List;

/**
 * @author:zyh
 * @Time:2021-05-20-21:59
 * @email:1269231889@qq.com
 */
public class IsEmptyUtil {
    public static boolean objectIsEmpty(Object o) {
        if (o != null) {
            if (o instanceof String) {
                String s = (String) o;
                if (s.length() == 0) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }


    public static boolean listIsEmpty(List<?> list) {
        if (list != null && !list.isEmpty()) {
            return false;
        }
        return true;
    }
}
