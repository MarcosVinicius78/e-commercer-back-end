package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entity.login.Usuario;

import java.util.List;


public interface UserRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByUsername(String username);
}
