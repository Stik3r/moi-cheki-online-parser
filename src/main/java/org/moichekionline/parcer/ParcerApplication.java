package org.moichekionline.parcer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ParcerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParcerApplication.class, args);
    }

}
