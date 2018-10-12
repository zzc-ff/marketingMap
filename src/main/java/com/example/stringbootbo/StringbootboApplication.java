package com.example.stringbootbo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class StringbootboApplication {

    public static void main(String[] args) {
        SpringApplication.run(StringbootboApplication.class, args);
    }


}
