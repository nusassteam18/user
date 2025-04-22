package com.ss.user.service;

import com.ss.user.entity.Customer;
import com.ss.user.repository.CustomerRepository;
import com.ss.user.requestDTO.CustomerRegisterRequest;
import com.ss.user.responseDTO.CustomerTruncatedDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCustomer_whenExists() {
        Long userId = 1L;
        Customer mockCustomer = new Customer();
        mockCustomer.setUserId(userId);

        when(customerRepository.findById(userId)).thenReturn(Optional.of(mockCustomer));

        Customer result = customerService.getCustomer(userId);

        assertEquals(userId, result.getUserId());
    }

    @Test
    void testGetCustomer_whenNotExists_returnsEmptyCustomer() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());

        Customer result = customerService.getCustomer(1L);

        assertNotNull(result);
        assertNull(result.getUserId()); // assuming the default constructor returns null fields
    }

    @Test
    void testGetCustomersTruncated() {
        List<CustomerTruncatedDTO> mockList = List.of(new CustomerTruncatedDTO(1L, "John", "Doe", "123456789", "john@example.com"));

        when(customerRepository.findAllCustomersTruncated()).thenReturn(mockList);

        List<CustomerTruncatedDTO> result = customerService.getCustomersTruncated();

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());
        verify(customerRepository).findAllCustomersTruncated();
    }

    @Test
    void testCreateCustomer_whenEmailDoesNotExist() {
        CustomerRegisterRequest request = new CustomerRegisterRequest(
                "",
                "",
                "",
                "",
                "",
                ""
        );
        request.setEmail("new@example.com");
        request.setFirstName("New");
        request.setLastName("User");

        when(customerRepository.findCustomerByEmail("new@example.com")).thenReturn(Optional.empty());

        customerService.createCustomer(request);

        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void testCreateCustomer_whenEmailExists_throwsException() {
        CustomerRegisterRequest request = new CustomerRegisterRequest(
                "",
                "",
                "",
                "",
                "",
                ""
        );
        request.setEmail("existing@example.com");

        when(customerRepository.findCustomerByEmail("existing@example.com"))
                .thenReturn(Optional.of(new Customer()));

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            customerService.createCustomer(request);
        });

        assertEquals("User email already exist", exception.getMessage());
    }

    @Test
    void testUpdateUserCustomer_whenUserExists() {
        Customer existingCustomer = new Customer();
        existingCustomer.setUserId(1L);
        existingCustomer.setEmail("old@example.com");

        Customer updateRequest = new Customer();
        updateRequest.setUserId(1L);
        updateRequest.setEmail("new@example.com");
        updateRequest.setFirstName("New");
        updateRequest.setLastName("Name");

        when(customerRepository.findById(1L)).thenReturn(Optional.of(existingCustomer));

        customerService.updateUserCustomer(updateRequest);

        verify(customerRepository, times(1)).save(existingCustomer);
        assertEquals("new@example.com", existingCustomer.getEmail());
        assertEquals("New", existingCustomer.getFirstName());
    }

    @Test
    void testUpdateUserCustomer_whenUserDoesNotExist_throwsException() {
        Customer customer = new Customer();
        customer.setUserId(99L);

        when(customerRepository.findById(99L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            customerService.updateUserCustomer(customer);
        });

        assertEquals("User does not exist.", exception.getMessage());
    }
}
