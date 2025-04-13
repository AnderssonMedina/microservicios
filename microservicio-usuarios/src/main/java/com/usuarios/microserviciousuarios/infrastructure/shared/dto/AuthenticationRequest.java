package com.usuarios.microserviciousuarios.infrastructure.shared.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String identify;
    private String password;
}