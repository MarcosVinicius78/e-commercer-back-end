package com.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entity.Cliente;



public interface CustomerRepository extends JpaRepository<Cliente, Long>{
    List<Cliente> findByEmail(String email);
}
