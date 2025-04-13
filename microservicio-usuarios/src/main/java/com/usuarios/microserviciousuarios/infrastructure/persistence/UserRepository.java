package com.usuarios.microserviciousuarios.infrastructure.persistence;

import com.usuarios.microserviciousuarios.infrastructure.shared.dto.UserDto;
import org.springframework.data.repository.CrudRepository;


/**
 * Interfaz que extiende de CrudRepository y nos permite realizar las diferentes operaciones en la BD
 **/
public interface UserRepository extends CrudRepository<UserDto, Long> {

    UserDto findByIdentify(String identify);

}
