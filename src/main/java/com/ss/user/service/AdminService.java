package com.ss.user.service;

import com.ss.user.entity.Admin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ss.user.repository.AdminRepository;

import java.util.Optional;

@Service
public class AdminService {
    Logger log =  LoggerFactory.getLogger(CustomerService.class);

    private AdminRepository adminRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin getAdmin(Long userId) {
        Optional<Admin> userOptional = adminRepository.findById(userId);

        return userOptional.orElseGet(Admin::new);
    }

}
