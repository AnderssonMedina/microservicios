package com.usuarios.microserviciousuarios.domain.service;



import com.usuarios.microserviciousuarios.domain.service.dependendy.IUser;
import com.usuarios.microserviciousuarios.infrastructure.shared.dto.UserDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * ClassName: ResultUserService
 * Clase service que pertenece a la capa de dominio donde se maneja exlusivamente la lógica de negocio
 * Clase que se encarga de manejar toda la lógica para evaluar si un AND ingresado pertenece a una humano o mutante
 */
@Service
public class UserService {

    IUser iUser;
   // private final PasswordEncoder passwordEncoder;


    /**
     * Param: IUser iUser
     * Inyección de dependencia por constructor, no se usa anotación @AutoWired para evitar dependencias con  el framework en la capa de dominio(lógica de negocio)
     * la interface IUser nos conecta con la capa de infraestructura la cual provee el acceso a datos, o integración con servicios externos si fuese necesario
     */
    public UserService(IUser iUser/*, PasswordEncoder passwordEncoder*/){
        this.iUser = iUser;
       // this.passwordEncoder = passwordEncoder;
    }

    public Boolean createUser(UserDto userDto){
        try{
            //userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            userDto.setDateCreate(new Date());
            iUser.createUser(userDto);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public UserDto getUser(String identify){
        return iUser.getUser(identify);

    }
}
