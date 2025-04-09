package com.ss.user.service;

import com.ss.user.entity.Customer;
import com.ss.user.entity.User;
import com.ss.user.repository.CustomerRepository;
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
}
