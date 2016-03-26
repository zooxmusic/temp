package com.aeg.ims.transfer;


import com.aeg.ims.transfer.config.ScheduleConfig;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Log4j2
@SpringBootApplication
@EnableJpaRepositories
@ComponentScan("com.aeg")
@Import({ScheduleConfig.class})
public class TransferApplication {

    public static void main(String[] args) {
        log.debug("Starting SpringBoot TransferApplication");
        SpringApplication.run(TransferApplication.class);
    }
}
