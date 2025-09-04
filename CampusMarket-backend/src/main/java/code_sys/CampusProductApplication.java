package code_sys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目启动类
 */
@MapperScan("code_sys.LayerMap")
@SpringBootApplication
public class CampusProductApplication {
    public static void main(String[] args) {
        SpringApplication.run(CampusProductApplication.class, args);
    }
}
