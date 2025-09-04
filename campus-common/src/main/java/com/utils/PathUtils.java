package com.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class PathUtils {
    private static final String TARGET_CLASSES = "/target/classes";

    public static String getClassLoadRootPath() {
        try {
            String rawPath = PathUtils.class.getClassLoader().getResource("").getPath();
            String decodedPath = URLDecoder.decode(rawPath, "utf-8");
            String prePath = decodedPath.replace(TARGET_CLASSES, "");

            return processPathByOS(prePath);
        } catch (UnsupportedEncodingException | NullPointerException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String processPathByOS(String prePath) {
        String osName = System.getProperty("os.name").toLowerCase();

        if (osName.startsWith("windows")) {
            return prePath.substring(1, prePath.length() - 1);
        } else {
            return prePath.substring(0, prePath.length() - 1);
        }
    }
}