package com.adn.microservicioandeval.infrastructure.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Data
@AllArgsConstructor
public class DetailMutantDto {
    private String dna;
    private boolean isMutant;
    private String identify;
    private String name;
    private String age;
    private Date dateCreate;


}
