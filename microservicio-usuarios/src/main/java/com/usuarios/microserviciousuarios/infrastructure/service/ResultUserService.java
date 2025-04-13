package com.usuarios.microserviciousuarios.infrastructure.service;


import com.usuarios.microserviciousuarios.domain.service.dependendy.IUser;
import com.usuarios.microserviciousuarios.infrastructure.persistence.UserRepository;
import com.usuarios.microserviciousuarios.infrastructure.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * ClassName: ResultUserService
 * Clase repository que pertenece a la capa de infraestructura donde se orquesta toda la conexión tanto con  base de datos como con servicios REST o SOAP si fuese necesario
 */
@Repository
public class ResultUserService implements IUser {

    //Se inyecta dependencia con la interfaz UserRepository que nos conecta con la base de datos
    @Autowired
    private UserRepository userRepository;


    @Override
    public void createUser(UserDto user) {
         userRepository.save(user);
    }

    @Override
    public UserDto getUser(String identify) {
        return userRepository.findByIdentify(identify);
    }
}
