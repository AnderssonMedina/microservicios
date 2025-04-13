package com.adn.microserviciostats.infrastructure.shared.dto;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "tbl_DetailMutant")
public class DetailMutantDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dna;
    private boolean isMutant;
    private String identify;
    private String name;
    private String age;
    private Date dateCreate;


}
