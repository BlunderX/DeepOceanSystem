package cn.huaqucha;

import cn.huaqucha.config.DefaultConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DeepOceanSystemApplication {

    public static void main(String[] args) {
        DefaultConfig.launcher();
        //Launcher
        SpringApplication.run(DeepOceanSystemApplication.class, args);
    }

}
