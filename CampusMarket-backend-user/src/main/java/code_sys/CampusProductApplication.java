package code_sys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目启动类
 */
@MapperScan("com.LayerMap")
@MapperScan("code_sys.LayerMap")
@SpringBootApplication(scanBasePackages = {
        "code_sys",  // 业务模块的包
        "com" // 公共模块的包
})
public class CampusProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(CampusProductApplication.class, args);
    }
}
