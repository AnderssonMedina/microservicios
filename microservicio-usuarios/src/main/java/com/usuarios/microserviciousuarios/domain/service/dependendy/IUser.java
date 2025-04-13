package com.usuarios.microserviciousuarios.domain.service.dependendy;


import com.usuarios.microserviciousuarios.infrastructure.shared.dto.UserDto;

/**
 * interfaz que se encarga de comunicar a la capa de dominio con la capa de infraestructura, la cual provee el acceso a datos
 */
public interface IUser {

    void createUser(UserDto user);
    UserDto getUser(String identify);
}
