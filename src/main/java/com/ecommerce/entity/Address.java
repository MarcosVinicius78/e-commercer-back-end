package com.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "address")
@Getter
@Setter
public class Address {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Long id;

    @Column(name = "city")
    private String cidade;

    @Column(name = "country")
    private String pais;

    @Column(name = "state")
    private String estado;

    @Column(name = "street")
    private String rua;

    @Column(name = "zip_code")
    private String codigoPostal;

    @JsonBackReference
    @OneToOne
    @PrimaryKeyJoinColumn
    private Order order;

}
