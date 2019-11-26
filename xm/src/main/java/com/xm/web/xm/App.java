package com.xm.web.xm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages="com.xm.web.xm")
@MapperScan(basePackages="com.xm.web.xm.mapper")
@EnableScheduling
public class App {
 public static void main(String[] args) throws Exception {
	SpringApplication.run(App.class, args);
	
}

}
