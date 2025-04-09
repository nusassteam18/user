package com.ss.user.requestDTO;

public class CustomerRegisterRequest extends RegisterRequest{
    private String contactNo;

    private String creditCardNo;

    private String creditCardName;

    private String creditCardExpiryDate;

    private String creditCardSecurityCode;

    private String homeAddress;

    public CustomerRegisterRequest(String contactNo, String creditCardNo, String creditCardName, String creditCardExpiryDate, String creditCardSecurityCode, String homeAddress) {
        this.contactNo = contactNo;
        this.creditCardNo = creditCardNo;
        this.creditCardName = creditCardName;
        this.creditCardExpiryDate = creditCardExpiryDate;
        this.creditCardSecurityCode = creditCardSecurityCode;
        this.homeAddress = homeAddress;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {this.contactNo = contactNo;}

    public String getCreditCardNo() {
        return creditCardNo;
    }

    public void setCreditCardNo(String creditCardNo) {this.creditCardNo = creditCardNo;}

    public String getcreditCardName() {
        return creditCardName;
    }

    public void setcreditCardName(String creditCardName) {this.creditCardName = creditCardName;}

    public String getcreditCardExpiryDate() {
        return creditCardExpiryDate;
    }

    public void setcreditCardExpiryDate(String creditCardExpiryDate) {this.creditCardExpiryDate = creditCardExpiryDate;}

    public String getcreditCardSecurityCode() {
        return creditCardSecurityCode;
    }

    public void setcreditCardSecurityCode(String creditCardSecurityCode) {this.creditCardSecurityCode = creditCardSecurityCode;}

    public String gethomeAddress() {
        return homeAddress;
    }

    public void sethomeAddress(String homeAddress) {this.homeAddress = homeAddress;}
}
