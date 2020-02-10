package cn.xiaoyu.clientresttemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

/**
 * Created by roin.zhang on 2020/1/3
 */
@SpringBootApplication
public class ClientRestTemplateApplication implements ServletContainerInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ClientRestTemplateApplication.class, args);
    }

    @Override
    public void onStartup(Set<Class<?>> set, ServletContext context) throws ServletException {
        context.getSessionCookieConfig().setName("client-session");
    }
}