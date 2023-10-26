package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.entity.login.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Long>{
    
}
