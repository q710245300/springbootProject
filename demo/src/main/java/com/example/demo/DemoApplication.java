package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 要将Application类放在最外侧，即包含所有子包 ，spring-boot会自动加载启动类所在包下及其子包下的所有组件。
 *  - 也就是model/service/controller要和这个application同级别
 *  - 程序的主入库，相当于main
 */
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
