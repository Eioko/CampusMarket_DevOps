package com.utils;

import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class FileUtils {

    public static File getOrCreateDirectory(String path) {
        File directory = new File(path);
        if (!directory.exists() && !directory.mkdirs()) {
            return null;
        }
        return directory;
    }

    public static void compressAndSaveImage(MultipartFile file, File targetFile) throws IOException {
        Thumbnails.of(file.getInputStream())
                .size(1024, 1024)
                .outputQuality(0.8)
                .toFile(targetFile);
    }

    public static boolean saveFile(MultipartFile file, String fileName) throws IOException {
        File directory = getOrCreateDirectory(PathUtils.getClassLoadRootPath() + "/pic");
        if (directory == null) {
            return false;
        }
        File targetFile = new File(directory, fileName);
        if (targetFile.exists() && !targetFile.delete()) {
            return false;
        }
        file.transferTo(targetFile);
        return true;
    }

    public static Map<String, Object> createErrorResponse(Map<String, Object> response, String message) {
        response.put("code", 400);
        response.put("msg", message);
        return response;
    }
}