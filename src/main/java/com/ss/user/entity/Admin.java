package com.ss.user.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("A")
public class Admin extends User {

    @Column(name = "restaurant_id")
    private Long restaurantId;

    // Constructors
    public Admin() {}

    public Admin(String firstName, String lastName, String email, String password,
                 Long restaurantId) {
        super(firstName, lastName, email, password);
        this.restaurantId = restaurantId;
    }

    // Getters and setters
    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "role=" + getRole() +
                ", userId=" + getUserId() + '\'' +
                ", firstName='" + getFirstName() + '\'' +
                ", lastName='" + getLastName() + '\'' +
                ", email='" + getEmail() + '\'' +
                ", password='" + getPassword() + '\'' +
                ", restaurantId='" + restaurantId + '\'' +
                '}';
    }
}