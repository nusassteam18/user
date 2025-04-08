package com.ss.user.repository;

import com.ss.user.entity.Customer;
import com.ss.user.responseDTO.CustomerTruncatedDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(value = "SELECT new com.ss.user.responseDTO.CustomerTruncatedDTO(c.userId as userId, c.firstName as firstName, c.lastName as lastName, c.contactNo as contactNo, c.email as email) FROM Customer c")
    List<CustomerTruncatedDTO> findAllCustomersTruncated();
}