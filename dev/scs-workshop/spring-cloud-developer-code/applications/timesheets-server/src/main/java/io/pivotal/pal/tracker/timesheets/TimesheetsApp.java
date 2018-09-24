package io.pivotal.pal.tracker.timesheets;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestOperations;

@EnableDiscoveryClient
@SpringBootApplication
@EnableCircuitBreaker
@ComponentScan({"io.pivotal.pal.tracker.timesheets", "io.pivotal.pal.tracker.restsupport"})
public class TimesheetsApp {

	
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(TimesheetsApp.class, args);
    }

    @Bean
    ProjectClient projectClient(RestOperations restTemplate) {
        return new ProjectClient(restTemplate);
    }
}
