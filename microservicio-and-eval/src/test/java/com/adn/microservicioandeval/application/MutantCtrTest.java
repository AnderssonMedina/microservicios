package com.adn.microservicioandeval.application;

import com.adn.microservicioandeval.infrastructure.shared.dto.*;
import com.adn.microservicioandeval.domain.service.MutantService;
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

/**
 * ClassName
 **/
class MutantCtrTest {

    @Mock
    MutantService mutantService;

    @InjectMocks
    MutantCtr mutantCtr;

    @Mock
    ModelMapper mapper;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        ModelMapper mapper = Mockito.mock(ModelMapper.class);
    }

    @Test
    void validatemutantDnaOkAndDnaMutant(){
        //Arrange
        String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTC"};
        String identify = "123658787";
        DNASequenceDto dnaSequenceDto = new DNASequenceDto();
        dnaSequenceDto.setDna(dna);

        when(mutantService.isMutant(dna,identify)).thenReturn(true);
        //Act
        ResponseEntity<String> httpResponse = mutantCtr.isMutant(dnaSequenceDto, identify);
        //Assert
        Assertions.assertEquals(HttpStatus.OK, httpResponse.getStatusCode());
    }

    @Test
    void validateMutantDnaBadRequest(){
        //Arrange
        String[] dna = {"ATGCGA","CAGTGC1","TTATGT","AGAAGG","CCCCTA","TCACTC"};
        String identify = "123658787";
        DNASequenceDto dnaSequenceDto = new DNASequenceDto();
        dnaSequenceDto.setDna(dna);

        when(mutantService.isMutant(dna,identify)).thenReturn(true);
        //Act
        ResponseEntity<String> httpResponse = mutantCtr.isMutant(dnaSequenceDto, identify);
        //Assert
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, httpResponse.getStatusCode());
    }
}
