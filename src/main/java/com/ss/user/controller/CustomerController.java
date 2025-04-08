package com.ss.user.controller;

import com.ss.user.entity.User;
import com.ss.user.responseDTO.CustomerTruncatedDTO;
import com.ss.user.service.CustomerService;
import com.ss.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/users/customers")
public class CustomerController {

    Logger log =  LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/truncated")
    public ResponseEntity<List<CustomerTruncatedDTO>> getCustomersTruncated() {

        return ResponseEntity.ok(customerService.getCustomersTruncated());
    }
}
