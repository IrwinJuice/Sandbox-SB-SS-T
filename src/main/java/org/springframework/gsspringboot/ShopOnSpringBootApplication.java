package org.springframework.gsspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("org.springframework.gsspringboot")
public class ShopOnSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopOnSpringBootApplication.class, args);
    }
}
