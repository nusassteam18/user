package com.ss.user.responseDTO;


import com.ss.user.entity.Admin;
import com.ss.user.entity.Customer;

public class LoginResponse {
    private Customer customer;
    private Admin admin;

    public LoginResponse() {
    }

    public LoginResponse(Customer customer) {
        this.customer = customer;
    }

    public LoginResponse(Admin admin) {
        this.admin = admin;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {

        if (customer != null)
            return "LoginResponse{" +
                    "customer=" + customer +
                    '}';

        return "LoginResponse{" +
                ", admin=" + admin +
                '}';
    }
}
