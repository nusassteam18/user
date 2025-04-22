package com.ss.user.controller;

import com.ss.user.entity.Customer;
import com.ss.user.entity.User;
import com.ss.user.requestDTO.CustomerRegisterRequest;
import com.ss.user.requestDTO.RegisterRequest;
import com.ss.user.responseDTO.CustomerTruncatedDTO;
import com.ss.user.service.CustomerService;
import com.ss.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/{customerId}/truncated")
    public ResponseEntity<CustomerTruncatedDTO> getCustomersTruncated(@PathVariable Long customerId) {

        return ResponseEntity.ok(customerService.getCustomerTruncated(customerId));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Customer> getUser(@PathVariable Long userId) {
        // Fetch customer details from the database
        return ResponseEntity.ok(customerService.getCustomer(userId));
    }

    @PostMapping("/register")
    public ResponseEntity<CustomerRegisterRequest> registerCust(@RequestBody CustomerRegisterRequest rq) {
        customerService.createCustomer(rq);
        return ResponseEntity.ok(rq);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Customer> updateUserCustomer(@RequestBody Customer customer){
        customerService.updateUserCustomer(customer);
        return ResponseEntity.ok(customer);
    }

}
