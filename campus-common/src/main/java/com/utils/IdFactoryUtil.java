package com.utils;

import java.util.UUID;

public class IdFactoryUtil {

    // 定义常量明确截取范围
    private static final int START_INDEX = 1;
    private static final int END_INDEX = 8;

    /**
     * 生成文件ID
     *
     * @return 长度为7的UUID子串
     */
    public static String getFileId() {
        return UUID.randomUUID()
                .toString()
                .substring(START_INDEX, END_INDEX);
    }
}