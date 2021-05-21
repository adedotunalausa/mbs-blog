package com.adedotunalausa.week9taskadedotunalausa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Week9TaskAdedotunalausaApplication {

    public static void main(String[] args) {
        SpringApplication.run(Week9TaskAdedotunalausaApplication.class, args);
    }

}
