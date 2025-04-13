package com.ss.user.service;

import com.ss.user.entity.Customer;
import com.ss.user.entity.User;
import com.ss.user.repository.CustomerRepository;
import com.ss.user.requestDTO.CustomerRegisterRequest;
import com.ss.user.requestDTO.RegisterRequest;
import com.ss.user.responseDTO.CustomerTruncatedDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    Logger log =  LoggerFactory.getLogger(CustomerService.class);

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomer(Long userId) {
        Optional<Customer> userOptional = customerRepository.findById(userId);

        return userOptional.orElseGet(Customer::new);
    }

    public List<CustomerTruncatedDTO> getCustomersTruncated() {

        List<CustomerTruncatedDTO> customerTruncatedDTOS = customerRepository.findAllCustomersTruncated();

        log.info("customerTruncatedDTOS: {}", customerTruncatedDTOS);

        return customerTruncatedDTOS;
    }

    public void createCustomer(CustomerRegisterRequest rq){
        Optional<Customer> userOptional = customerRepository.findCustomerByEmail(rq.getEmail());

        if(userOptional.isPresent())
            throw new IllegalStateException("User email already exist");

        Customer cust = new Customer(rq.getFirstName(), rq.getLastName(), rq.getEmail(), rq.getPassword(),
        rq.getContactNo(),rq.getCreditCardNo(),rq.getcreditCardName(),rq.getcreditCardExpiryDate(),rq.getcreditCardSecurityCode(),rq.gethomeAddress());
        customerRepository.save(cust);
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
}
