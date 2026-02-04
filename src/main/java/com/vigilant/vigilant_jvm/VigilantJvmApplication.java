package com.vigilant.vigilant_jvm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class VigilantJvmApplication {
    public static void main(String[] args) {
        java.util.TimeZone.setDefault(java.util.TimeZone.getTimeZone("UTC"));
        SpringApplication.run(VigilantJvmApplication.class, args);
    }
}
