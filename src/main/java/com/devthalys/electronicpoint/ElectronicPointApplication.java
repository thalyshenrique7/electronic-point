package com.devthalys.electronicpoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class ElectronicPointApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElectronicPointApplication.class, args);
    }

}
