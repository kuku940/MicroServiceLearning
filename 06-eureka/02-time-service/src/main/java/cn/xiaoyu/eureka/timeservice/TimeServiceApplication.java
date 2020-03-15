package cn.xiaoyu.eureka.timeservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class TimeServiceApplication {
    @Value("${server.port}")
    private int port;

    public static void main(String[] args) {
        SpringApplication.run(TimeServiceApplication.class, args);
    }

    @GetMapping
    public String getTime() {
        return "The current time is " + new Date().toString() + "(answered by service running on " + port + ")";
    }
}
