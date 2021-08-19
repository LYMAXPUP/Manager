package com.utils;

/**
 * 字符串工具类
 */
public class StringUtils {

    /**
     * 判断字符串都不为空
     *
     * @param arr 字符串数组
     * @return true or false
     */
    public static boolean isAllNotEmpty(String... arr) {
        for (String str : arr) {
            if (isEmpty(str)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断字符串是否不为空
     *
     * @param str 字符串
     * @return true or false
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }

    /**
     * 判断字符串是否为空
     *
     * @param str 字符串
     * @return true or false
     */
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

}
