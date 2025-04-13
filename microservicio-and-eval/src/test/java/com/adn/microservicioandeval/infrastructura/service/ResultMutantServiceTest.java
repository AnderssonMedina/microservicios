package com.adn.microservicioandeval.infrastructura.service;

import com.adn.microservicioandeval.domain.model.Mutant;
import com.adn.microservicioandeval.infrastructure.persistence.MutantRepository;
import com.adn.microservicioandeval.infrastructure.service.ResultMutantService;
import com.adn.microservicioandeval.infrastructure.shared.dto.ResultMutantDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import static org.mockito.Mockito.when;

/**
 * ClassName
 **/
class ResultMutantServiceTest {

    @Mock
    ModelMapper mapper;

    @Mock
    private MutantRepository mutantRepository;

    @InjectMocks
    private ResultMutantService resultMutantService;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        ModelMapper mapper = Mockito.mock(ModelMapper.class);
    }

    @Test
    void saveResultMutant() {
        //Arrange

        Mutant mutant = new Mutant("",true, "");
        ResultMutantDto mutantDto=  new ResultMutantDto();
        when(mapper.map(mutant, ResultMutantDto.class)).thenReturn(mutantDto);
        when(mutantRepository.save(mutantDto)).thenReturn(mutantDto);
        //Act
        resultMutantService.saveResultMutant(mutant);
        //Assert
        Assertions.assertNotNull(mutantDto);


    }

    @Test
    void validateExistDna() {
        //Arrange
        String dna = "[]";
        Mutant mutant = new Mutant("",true, "");
        ResultMutantDto  mutantDto=  new ResultMutantDto();
        mutantDto.setMutant(true);
        when(mapper.map(mutant, Mutant.class)).thenReturn(mutant);
        when(mutantRepository.findByDna(dna)).thenReturn(mutantDto);
        //Act
        resultMutantService.validateExistDna(dna);
        //Assert
        Assertions.assertEquals(mutant.isMutant(), true);
    }
}