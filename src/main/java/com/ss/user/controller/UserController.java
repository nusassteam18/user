package com.ss.user.controller;

import com.ss.user.entity.Admin;
import com.ss.user.entity.Customer;
import com.ss.user.requestDTO.LoginRequest;
import com.ss.user.requestDTO.RegisterRequest;
import com.ss.user.requestDTO.ForgotPassRequest;
import com.ss.user.responseDTO.LoginAdminResponse;
import com.ss.user.service.AdminService;
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

    private final AdminService adminService;

    @Autowired
    public UserController(UserService userService, AdminService adminService) {
        this.userService = userService;
        this.adminService = adminService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        User user = userService.getUserByEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());
        if (user != null) {
            if(user instanceof Customer)
                return ResponseEntity.ok(((Customer) user));
            else {
                Admin admin = (Admin) user;
                LoginAdminResponse lar = new LoginAdminResponse();
                lar.setUserId(admin.getUserId());
                lar.setFirstName(admin.getFirstName());
                lar.setLastName(admin.getLastName());
                lar.setEmail(admin.getEmail());
                lar.setPassword(admin.getPassword());
                lar.setRole(admin.getRole());
                lar.setRestaurantId(admin.getRestaurantId());

                String restaurantName = adminService.getRestaurantName(admin.getRestaurantId());
                if (!restaurantName.isEmpty())
                    lar.setRestaurantName(restaurantName);

                return ResponseEntity.ok(lar);
            }
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

    @DeleteMapping(path = "{userId}")
    public ResponseEntity<Long> deleteUser(@PathVariable("userId") Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok(userId);
    }

}