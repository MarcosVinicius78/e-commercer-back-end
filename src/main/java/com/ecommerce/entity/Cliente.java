package com.ecommerce.entity;

import java.util.HashSet;
import java.util.Set;

import com.ecommerce.entity.login.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "customer")
@Getter
@Setter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @Column(name = "first_name")
    private String primeiroNome;

    @Column(name = "last_name")
    private String ultimoNome;

    @Column(name = "email")
    private String email;

    // @JsonIgnore
    // @JsonManagedReference
    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private Set<Order> orders = new HashSet<>();

    @JsonBackReference
    @OneToOne
    @PrimaryKeyJoinColumn
    private Usuario user;
    
    public void add(Order order){
        if (order != null) {
            if (this.orders == null) {
                this.orders = new HashSet<>();
            }

            this.orders.add(order);
            order.setCustomer(this);
        }
    }
}
