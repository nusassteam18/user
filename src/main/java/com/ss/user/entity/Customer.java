package com.ss.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("C")
public class Customer extends User {

    @Column(name = "contact_no")
    private String contactNo;

    @Column(name = "credit_card_no")
    private String creditCardNo;

    @Column(name = "credit_card_name")
    private String creditCardName;

    @Column(name = "credit_card_expiry")
    private String creditCardExpiryDate;

    @Column(name = "credit_card_security_code")
    private String creditCardSecurityCode;

    @Column(name = "home_address")
    private String homeAddress;

    // Constructors
    public Customer() {}

    public Customer(String firstName, String lastName, String email, String password,
                    String contactNo, String creditCardNo,
                    String creditCardName, String creditCardExpiryDate,
                    String creditCardSecurityCode, String homeAddress) {
        super(firstName, lastName, email, password);
        this.contactNo = contactNo;
        this.creditCardNo = creditCardNo;
        this.creditCardName = creditCardName;
        this.creditCardExpiryDate = creditCardExpiryDate;
        this.creditCardSecurityCode = creditCardSecurityCode;
        this.homeAddress = homeAddress;
    }

    // Getters and setters
    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getCreditCardNo() {
        return creditCardNo;
    }

    public void setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

    public String getCreditCardName() {
        return creditCardName;
    }

    public void setCreditCardName(String creditCardName) {
        this.creditCardName = creditCardName;
    }

    public String getCreditCardExpiryDate() {
        return creditCardExpiryDate;
    }

    public void setCreditCardExpiryDate(String creditCardExpiryDate) {
        this.creditCardExpiryDate = creditCardExpiryDate;
    }

    public String getCreditCardSecurityCode() {
        return creditCardSecurityCode;
    }

    public void setCreditCardSecurityCode(String creditCardSecurityCode) {
        this.creditCardSecurityCode = creditCardSecurityCode;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "role=" + getRole() +
                ", userId=" + getUserId() + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", creditCardNo='" + creditCardNo + '\'' +
                ", creditCardName='" + creditCardName + '\'' +
                ", creditCardExpiryDate='" + creditCardExpiryDate + '\'' +
                ", creditCardSecurityCode='" + creditCardSecurityCode + '\'' +
                ", homeAddress='" + homeAddress + '\'' +
                '}';
    }
}