package com.usuarios.microserviciousuarios.domain.service;

import com.usuarios.microserviciousuarios.domain.service.dependendy.IUser;
import com.usuarios.microserviciousuarios.infrastructure.shared.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserServiceTest {
    @Mock
    private IUser userSave;

    @InjectMocks
    private UserService userService;

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
        //Act
         boolean createUser = userService.createUser(user);
        //Assert
        Assertions.assertTrue(createUser);
    }

    @Test
    void getUser() {
        //Arrange
        String identify = "123658787";
        UserDto user = new UserDto();
        user.setIdentify("123456");
        user.setPassword("123456");
        user.setAge("20");
        user.setName("Anderson");
        when(userSave.getUser(identify)).thenReturn(user);

        //Act
        UserDto userResponse = userService.getUser(identify);
        //Assert
        Assertions.assertEquals(userResponse, user);

    }
}