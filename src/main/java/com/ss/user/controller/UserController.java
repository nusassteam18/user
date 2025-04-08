package com.ss.user.controller;

import com.ss.user.entity.Admin;
import com.ss.user.entity.Customer;
import com.ss.user.requestDTO.LoginRequest;
import com.ss.user.requestDTO.RegisterRequest;
import com.ss.user.requestDTO.ForgotPassRequest;
import com.ss.user.service.UserService;
import com.ss.user.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class UserController {

    Logger log =  LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        User user = userService.getUserByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
        if (user != null) {
            if(user instanceof Customer)
                return ResponseEntity.ok(((Customer) user));
            else
                return ResponseEntity.ok((Admin) user);
        }


        return ResponseEntity.notFound().build();
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterRequest> registerUser(@RequestBody RegisterRequest rq) {
        userService.createUser(rq);
        return ResponseEntity.ok(rq);
    }

    @PostMapping("/forgotPass")
    public ResponseEntity<User> forgotPass(@RequestBody ForgotPassRequest rq) {
        User user = userService.getUserByEmail(rq.getEmail());
        if(user == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        // Fetch user details from the database
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/test/{userId}")
    public ResponseEntity<String> callRestaurantsApi(@PathVariable Long userId) {
        String str = userService.callRestaurantsApi();

        return ResponseEntity.ok(str);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUser(@PathVariable Long userId) {
        // Fetch user details from the database
        return ResponseEntity.ok(userService.getUser(userId));
    }

    @PostMapping
    public ResponseEntity<RegisterRequest> createUser(@RequestBody RegisterRequest rq) {
        userService.createUser(rq);
        return ResponseEntity.ok(rq);
    }

    @PutMapping()
    public ResponseEntity<User> updateUser(@RequestBody User user){
        userService.updateUser(user);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/customer/{userId}")
    public ResponseEntity<Customer> updateUserCustomer(@RequestBody Customer customer){
        userService.updateUserCustomer(customer);
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/admin/{userId}")
    public ResponseEntity<Admin> updateUserAdmin(@RequestBody Admin admin){
        userService.updateUserAdmin(admin);
        return ResponseEntity.ok(admin);
    }

    @DeleteMapping(path = "{userId}")
    public ResponseEntity<Long> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok(userId);
    }

}