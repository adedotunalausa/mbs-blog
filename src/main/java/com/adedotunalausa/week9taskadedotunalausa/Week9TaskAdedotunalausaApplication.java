package com.adedotunalausa.week9taskadedotunalausa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableJpaAuditing
@SpringBootApplication
public class Week9TaskAdedotunalausaApplication {

    public static void main(String[] args) {

        SpringApplication.run(Week9TaskAdedotunalausaApplication.class, args);
    }

}
