package com.ecommerce.dto.response;

import java.io.Serializable;

import com.ecommerce.entity.Cliente;

public record ClienteResponse(
    String primeiroNome,
    String segundoNome,
    String email
)   implements Serializable {
    
    public static ClienteResponse toResponse(Cliente cliente){
        return new ClienteResponse(
            cliente.getPrimeiroNome(),
            cliente.getUltimoNome(),
            cliente.getEmail()
        );
    }
}
