package code_sys.LayerControl;

import code_sys.utils.IdFactoryUtil;
import code_sys.utils.PathUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.web.multipart.MultipartFile;
import code_sys.utils.FileUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件控制器
 *
 * @since 2024-03-22
 */
@RestController
@RequestMapping("/file")
public class LayerFileController {

    @Value("${my-server.api-context-path}")
    private String apiContextPath;

    private static final String BASE_URL = "http://localhost:31451";

    /**
     * 上传图片文件
     *
     * @param file 上传的文件
     * @return 响应结果
     */
    @PostMapping("/upload")
    public Map<String, Object> uploadFile(@RequestParam("file") MultipartFile file) {
        String uniqueId = IdFactoryUtil.getFileId();
        String newFileName = uniqueId + file.getOriginalFilename();
        Map<String, Object> response = new HashMap<>();
        File directory = FileUtils.getOrCreateDirectory(PathUtils.getClassLoadRootPath() + "/pic");

        if (directory == null) {
            return FileUtils.createErrorResponse(response, "文件目录创建失败");
        }

        File targetFile = new File(directory, newFileName);
        try {
            FileUtils.compressAndSaveImage(file, targetFile);
            response.put("code", 200);
            response.put("data", BASE_URL + apiContextPath + "/file/getFile?fileName=" + newFileName);
        } catch (IOException e) {
            return FileUtils.createErrorResponse(response, "文件上传异常");
        }
        return response;
    }

    /**
     * 上传视频文件
     *
     * @param file 上传的文件
     * @return 响应结果
     */
    @PostMapping("/video/upload")
    public Map<String, Object> videoUpload(@RequestParam("file") MultipartFile file) {
        String uniqueId = IdFactoryUtil.getFileId();
        String newFileName = uniqueId + file.getOriginalFilename();
        Map<String, Object> response = new HashMap<>();

        try {
            if (FileUtils.saveFile(file, newFileName)) {
                response.put("code", 200);
                response.put("data", apiContextPath + "/file/getFile?fileName=" + newFileName);
            } else {
                return FileUtils.createErrorResponse(response, "文件上传失败");
            }
        } catch (IOException e) {
            return FileUtils.createErrorResponse(response, "文件上传异常");
        }
        return response;
    }

    /**
     * 获取文件
     *
     * @param fileName 文件名
     * @param response HTTP响应
     * @throws IOException 异常
     */
    @GetMapping("/getFile")
    public void getFile(@RequestParam("fileName") String fileName, HttpServletResponse response) throws IOException {
        File directory = new File(PathUtils.getClassLoadRootPath() + "/pic");
        File file = new File(directory, fileName);
        if (file.exists()) {
            try (FileInputStream inputStream = new FileInputStream(file);
                 OutputStream outputStream = response.getOutputStream()) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
        }
    }
}