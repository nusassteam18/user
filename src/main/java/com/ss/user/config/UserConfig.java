package com.ss.user.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@Configuration
public class UserConfig {
    Logger log =  LoggerFactory.getLogger(UserConfig.class);
//    @Bean
//    CommandLineRunner commandLineRunner(UserRepository userRepository) {
//        return args -> {
//            User user1 = new User(null, "hello1", "world", "hello1@hotmail.com", "123", User.Role.Customer);
//            User user2 = new User(null, "hello2", "world","hello2@hotmail.com", "123", User.Role.Customer);
//            User user3 = new User(null, "hello3", "world","hello3@hotmail.com", "123", User.Role.Admin);
//            userRepository.saveAll(Arrays.asList(user1, user2, user3));
//        };
//    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}