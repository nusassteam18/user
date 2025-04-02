package com.ss.user.config;

import com.ss.user.repository.AdminRepository;
import com.ss.user.repository.CustomerRepository;
import com.ss.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class UserConfig {
    Logger log =  LoggerFactory.getLogger(UserConfig.class);
    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, CustomerRepository customerRepository, AdminRepository adminRepository) {
        return args -> {
//            User user1 = new User(null, "hello1", "world", "hello1@hotmail.com", "123", User.Role.Customer);
//            User user2 = new User(null, "hello2", "world","hello2@hotmail.com", "123", User.Role.Customer);
//            User user3 = new User(null, "hello3", "world","hello3@hotmail.com", "123", User.Role.Admin);
//            userRepository.saveAll(Arrays.asList(user1, user2, user3));

//            String email, String password, String firstName,
//                    String lastName, String contactNo, String creditCardNo,
//                    String creditCardName, String creditCardExpiryDate,
//                    String creditCardSecurityCode, String homeAddress

//            User user = new User(null, "hello", "world", "hello@hotmail.com", "123");
//            userRepository.save(user);
//
//            Customer customer = new Customer("john", "tan", "john@hotmail.com", "123", "91234567", "12312", "john tan", "2025", "012", "123 City Street");
//            customerRepository.save(customer);
//
//            Admin admin = new Admin("sally", "tan", "sally@hotmail.com", "123", 1L);
//            adminRepository.save(admin);
        };
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}