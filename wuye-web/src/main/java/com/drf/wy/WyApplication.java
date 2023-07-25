package com.drf.wy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @Auther：杜瑞丰
 * @Date：2023/7/21 10:12
 * @Project_Name:wuyeSys-parent
 */

@SpringBootApplication
@CrossOrigin
public class WyApplication {
    public static void main(String[] args) {
        SpringApplication.run(WyApplication.class);

    }
}
