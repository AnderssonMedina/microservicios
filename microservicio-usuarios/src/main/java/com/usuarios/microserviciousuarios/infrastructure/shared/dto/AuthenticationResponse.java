package com.usuarios.microserviciousuarios.infrastructure.shared.dto;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private String token;
}