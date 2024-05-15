package com.example.advanced_testing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing;

@EnableReactiveMongoAuditing
@SpringBootApplication
public class AdvancedTestingApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdvancedTestingApplication.class, args);
    }

}
