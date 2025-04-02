package com.ss.user.repository;
import com.ss.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findUserByEmail(String email);
    User findUserByEmailAndPassword(String email, String password);
}