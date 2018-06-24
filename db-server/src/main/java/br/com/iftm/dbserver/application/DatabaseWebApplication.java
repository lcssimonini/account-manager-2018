package br.com.iftm.dbserver.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class DatabaseWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatabaseWebApplication.class, args);
    }
}
