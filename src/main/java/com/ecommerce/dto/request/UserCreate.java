package com.ecommerce.dto.request;

import java.io.Serializable;

public record UserCreate(

    String username,

    String password,

    String email,

    String primeiroNome,

    String ultimoNome
)implements Serializable {
    
}
