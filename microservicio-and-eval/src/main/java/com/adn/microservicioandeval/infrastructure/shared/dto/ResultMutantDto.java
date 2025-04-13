package com.adn.microservicioandeval.infrastructure.shared.dto;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "tbl_ResultMutant")
public class ResultMutantDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String dna;
    private boolean isMutant;
    @Column(unique = true, nullable = false)
    private String identify;
    private Date dateCreate;


}
