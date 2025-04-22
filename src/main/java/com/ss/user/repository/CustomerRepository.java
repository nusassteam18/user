package com.ss.user.repository;

import com.ss.user.entity.Customer;
import com.ss.user.responseDTO.CustomerTruncatedDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(value = "SELECT new com.ss.user.responseDTO.CustomerTruncatedDTO(c.userId as userId, c.firstName as firstName, c.lastName as lastName, c.contactNo as contactNo, c.email as email) FROM Customer c")
    List<CustomerTruncatedDTO> findAllCustomersTruncated();

    @Query(value = "SELECT new com.ss.user.responseDTO.CustomerTruncatedDTO(c.userId as userId, c.firstName as firstName, c.lastName as lastName, c.contactNo as contactNo, c.email as email) FROM Customer c WHERE c.userId = :customerId")
    CustomerTruncatedDTO findCustomerTruncatedByCustomerId(@Param("customerId") Long customerId);

    Optional<Customer> findCustomerByEmail(String email);
}