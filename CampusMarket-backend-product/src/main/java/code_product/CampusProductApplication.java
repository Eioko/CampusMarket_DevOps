package code_product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目启动类
 */
@MapperScan("code_product.LayerMap")
@SpringBootApplication(scanBasePackages = {
        "code_product",  // 业务模块的包
        "com" // 公共模块的包
})
public class CampusProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(CampusProductApplication.class, args);
    }
}
