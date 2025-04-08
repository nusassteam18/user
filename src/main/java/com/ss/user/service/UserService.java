package com.ss.user.service;

import com.ss.user.entity.Customer;
import com.ss.user.repository.CustomerRepository;
import com.ss.user.requestDTO.RegisterRequest;
import com.ss.user.entity.User;
import com.ss.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    Logger log =  LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    private final CustomerRepository customerRepository;

    private final EmailService emailService;

    @Autowired
    public UserService(UserRepository userRepository, CustomerRepository customerRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.emailService = emailService;
    }

    @Autowired
    public RestTemplate restTemplate;

    @Value("${restaurants.url:http://www.google.com}")
    private String RESTAURANTS_URL;

    public String callRestaurantsApi(){
        log.info("RESTAURANTS_URL: {}", RESTAURANTS_URL);
        return restTemplate.getForObject(RESTAURANTS_URL, String.class);
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public void createUser(RegisterRequest rq){
        Optional<User> userOptional = userRepository.findUserByEmail(rq.getEmail());

        if(userOptional.isPresent())
            throw new IllegalStateException("User email already exist");

        User user = new User(null, rq.getFirstName(), rq.getLastName(), rq.getEmail(), rq.getPassword());
        userRepository.save(user);
    }

    public User getUserByEmailAndPassword(String email, String password) {
        User user = userRepository.findUserByEmailAndPassword(email, password);
        if(user == null)
            return null;

        return user;
    }

    public User getUserByEmail(String email) {
        Optional<User> userOptional = userRepository.findUserByEmail(email);
        if(userOptional.isPresent())
            emailService.forgotPasswordEmail(email);

        return userOptional.orElse(null);
    }

    public User getUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        return userOptional.orElseGet(User::new);
    }

    @Transactional
    public void updateUser(User user){
        Optional<User> userOptional = userRepository.findById(user.getUserId());

        if(userOptional.isEmpty())
            throw new IllegalStateException("User does not exist.");

        User updatingUser = userOptional.get();
        updatingUser.setEmail(user.getEmail());
        updatingUser.setFirstName(user.getFirstName());
        updatingUser.setLastName(user.getLastName());
        updatingUser.setPassword(user.getPassword());
        userRepository.save(updatingUser);
    }

    public void updateUserCustomer(Customer customer){
        Optional<Customer> userOptional = customerRepository.findById(customer.getUserId());

        if(userOptional.isEmpty())
            throw new IllegalStateException("User does not exist.");

        Customer updatingUser = userOptional.get();
        updatingUser.setEmail(customer.getEmail());
        updatingUser.setFirstName(customer.getFirstName());
        updatingUser.setLastName(customer.getLastName());
        updatingUser.setPassword(customer.getPassword());
        updatingUser.setContactNo(customer.getContactNo());
        updatingUser.setCreditCardNo(customer.getCreditCardNo());
        updatingUser.setCreditCardName(customer.getCreditCardName());
        updatingUser.setCreditCardExpiryDate(customer.getCreditCardExpiryDate());
        updatingUser.setCreditCardSecurityCode(customer.getCreditCardSecurityCode());
        updatingUser.setHomeAddress(customer.getHomeAddress());
        customerRepository.save(updatingUser);
    }

    public void deleteUser(Long userId) {
        boolean isExist = userRepository.existsById(userId);

        if(!isExist)
            throw new IllegalStateException("User with userId " + userId + " does not exist");

        userRepository.deleteById(userId);
    }
}
