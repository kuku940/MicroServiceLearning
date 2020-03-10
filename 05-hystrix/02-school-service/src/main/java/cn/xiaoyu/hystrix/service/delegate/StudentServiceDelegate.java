package cn.xiaoyu.hystrix.service.delegate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
public class StudentServiceDelegate {

    @Autowired
    RestTemplate restTemplate;

    // 1. 简单超时
//	@HystrixCommand

    // 2. 定制超时
//    @HystrixCommand(commandProperties = {@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "4000")})

    // 3. 定制降级方法
//	@HystrixCommand(fallbackMethod = "callStudentServiceFallback")

    // 4. 定制线程池隔离策略
    @HystrixCommand(fallbackMethod = "callStudentServiceFallback",
            threadPoolKey = "studentServiceThreadPool",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "30"),
                    @HystrixProperty(name = "maxQueueSize", value = "10")
            }
    )
    public String callStudentService(String schoolName) {
        System.out.println("Getting School details for " + schoolName);
        String response = restTemplate.exchange("http://localhost:8090/getStudentBySchool/{schoolName}", HttpMethod.GET,
                null, new ParameterizedTypeReference<String>() {

                }, schoolName).getBody();
        System.out.println("Response Received as " + response + " - " + new Date());
        return "NORMAL FLOW !!! - School Name - " + schoolName + " ::: " + " Student Details " + response + " - "
                + new Date();
    }

    @SuppressWarnings("unused")
    private String callStudentServiceFallback(String schoolName) {
        System.out.println("Student Service is down!!! fallback route enabled...");
        return "CIRCUIT BREAKER ENABLED!!! NO Response From Student Service ath this moment. "
                + " Service will be back shortly - " + new Date();
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
