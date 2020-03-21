package cn.xiaoyu.springcloudactuator;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.spring.autoconfigure.MeterRegistryCustomizer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
public class SpringCloudActuatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudActuatorApplication.class, args);
    }

    @GetMapping(value = "/hello-world")
    public @ResponseBody
    String sayHello() {
        return "hello, world";
    }

    @Bean
    MeterRegistryCustomizer<MeterRegistry> metricsCommonTags() {
        return registry -> registry.config().commonTags("application", "actuator-demo");
    }
}
