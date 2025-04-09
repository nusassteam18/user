package com.ss.user.controller;

import com.ss.user.entity.Admin;
import com.ss.user.entity.Customer;
import com.ss.user.service.AdminService;
import com.ss.user.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/users/admins")
public class AdminController {
    Logger log =  LoggerFactory.getLogger(CustomerController.class);

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Admin> getUser(@PathVariable Long userId) {
        // Fetch admin details from the database
        return ResponseEntity.ok(adminService.getAdmin(userId));
    }

}
