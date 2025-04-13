package com.usuarios.microserviciousuarios.application;



import com.usuarios.microserviciousuarios.domain.service.UserService;
import com.usuarios.microserviciousuarios.infrastructure.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value="users")
public class UsersCtr {

    @Autowired
    UserService userService;

    @PostMapping(value = "/createUser")
    public ResponseEntity<String> createUser(@RequestBody UserDto user) {
        if (userService.createUser(user)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }


    @GetMapping(value = "/getUser", produces =MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<UserDto> getUsers (@RequestParam String identify ){
        return  ResponseEntity.ok(userService.getUser(identify));
    }
}
