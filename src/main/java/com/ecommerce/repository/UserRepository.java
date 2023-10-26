package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entity.login.User;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsername(String username);
}
