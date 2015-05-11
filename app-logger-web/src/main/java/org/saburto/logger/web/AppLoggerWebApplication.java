package org.saburto.logger.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
@EnableZuulProxy
public class AppLoggerWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppLoggerWebApplication.class, args);
    }
}
