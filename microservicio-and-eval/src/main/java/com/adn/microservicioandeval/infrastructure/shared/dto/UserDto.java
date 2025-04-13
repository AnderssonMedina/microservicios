package com.adn.microservicioandeval.infrastructure.shared.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@Data
public class UserDto {
    private long id;
    private String identify;
    private String name;
    private String age;
    private Date dateCreate;
}
