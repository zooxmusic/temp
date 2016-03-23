package com.aeg.ims.transfer;


import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Log4j2
@SpringBootApplication
@EnableJpaRepositories
@ComponentScan("com.aeg")
public class Application {

    public static void main(String[] args) {
        log.debug("Starting SpringBoot Application");
        SpringApplication.run(Application.class);
    }
}
