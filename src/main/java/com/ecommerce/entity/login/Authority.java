package com.ecommerce.entity.login;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "authorities")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Authority {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "authorities_id")
    private Long id;

    private String nome;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_Id")
    private Usuario user;

}