package com.usuarios.microserviciousuarios.application;

import com.usuarios.microserviciousuarios.domain.service.UserService;
import com.usuarios.microserviciousuarios.infrastructure.shared.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;

class UsersCtrTest {

    @Mock
    UserService userService;

    @InjectMocks
    UsersCtr usersCtr;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUser() {
        //Arrange
        UserDto user = new UserDto();
        user.setIdentify("123456");
        user.setPassword("123456");
        user.setAge("20");
        user.setName("Anderson");
        when(userService.createUser(user)).thenReturn(true);
        //Act
        ResponseEntity<String> createUser = usersCtr.createUser(user);
        //Assert
        Assertions.assertEquals(HttpStatus.OK, createUser.getStatusCode());

    }

    @Test
    void getUsers() {
        //Arrange
        UserDto user = new UserDto();
        user.setIdentify("123456");
        user.setPassword("123456");
        user.setAge("20");
        user.setName("Anderson");
        when(userService.getUser("")).thenReturn(user);
        //Act
        ResponseEntity<UserDto> getUser = usersCtr.getUsers("");
        //Assert
        Assertions.assertEquals(HttpStatus.OK, getUser.getStatusCode());
    }
}