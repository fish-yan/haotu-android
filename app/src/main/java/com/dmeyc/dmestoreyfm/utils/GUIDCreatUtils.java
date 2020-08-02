package com.dmeyc.dmestoreyfm.utils;

import java.util.UUID;

public class GUIDCreatUtils {

    /**
     * 生成GUID
     * @return
     */
    public static String createGUID() {
        // 创建 GUID 对象
        UUID uuid = UUID.randomUUID();
        // 得到对象产生的ID
        String a = uuid.toString();
        // 转换为大写
        return a.toUpperCase();
    }
}