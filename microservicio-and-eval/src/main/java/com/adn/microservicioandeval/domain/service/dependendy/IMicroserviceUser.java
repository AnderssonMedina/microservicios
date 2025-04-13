package com.adn.microservicioandeval.domain.service.dependendy;

import com.adn.microservicioandeval.infrastructure.shared.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "microservicio-usuarios")
public interface IMicroserviceUser {
    
    @GetMapping(value = "/users/getUser")
    UserDto getUser(@RequestParam String identify);

}
