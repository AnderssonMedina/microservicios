package com.adn.microservicioandeval.service;

import com.adn.microservicioandeval.domain.model.Mutant;
import com.adn.microservicioandeval.infrastructure.shared.dto.UserDto;
import com.adn.microservicioandeval.domain.service.MutantService;
import com.adn.microservicioandeval.domain.service.dependendy.IMicroserviceStats;
import com.adn.microservicioandeval.domain.service.dependendy.IMicroserviceUser;
import com.adn.microservicioandeval.domain.service.dependendy.IResultMutantSave;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.mockito.Mockito.when;

/**
 * ClassName
 **/
class MutantServiceTest {

    @Mock
    private IResultMutantSave resultMutantSave;

    @Mock
    private IMicroserviceUser microserviceUser;

    @Mock
    private IMicroserviceStats microserviceStats;

    @InjectMocks
    private MutantService mutantService;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void isMutantWhenDnaHasThreeMatches() {
        //Arrange
        String identify = "123658787";
        String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTC"};
        when(resultMutantSave.validateExistDna(Arrays.toString(dna))).thenReturn(null);
        when(microserviceUser.getUser(identify)).thenReturn(new UserDto());
        //Act
        boolean isMutant = mutantService.isMutant(dna, identify);
        //Assert
        Assertions.assertTrue(isMutant);

    }

    @Test
    void isMutantWhenDnaHasTwoMatches() {
        //Arrange
        String identify = "123658787";
        String[] dna = {"TTTTGA","CAGTTC","TTATGT","AGAAGG","CTGCAA","TCACTA"};
        when(resultMutantSave.validateExistDna(Arrays.toString(dna))).thenReturn(null);
        when(microserviceUser.getUser(identify)).thenReturn(new UserDto());
        //Act
        boolean isMutant = mutantService.isMutant(dna, identify);
        //Assert
        Assertions.assertTrue(isMutant);

    }
    @Test
    void isMutantWhenDnaHasDiangonalMatches() {
        //Arrange
        String identify = "123658787";
        String[] dna = {"TGCAG","CAGGC","CCGCA","CGCTA","CCTTA"};
        when(resultMutantSave.validateExistDna(Arrays.toString(dna))).thenReturn(null);
        when(microserviceUser.getUser(identify)).thenReturn(new UserDto());
        //Act
        boolean isMutant = mutantService.isMutant(dna, identify);
        //Assert
        Assertions.assertTrue(isMutant);

    }

    @Test
    void isMutantTrueAndExistInBD(){
        //Arrange
        String identify = "123658787";
        String[] dna = {"TTTTGA","CAGTTC","TTATGT","AGAAGG","CTGCAA","TCACTA"};
        Mutant mutant = new Mutant("", true, identify);
        when(resultMutantSave.validateExistDna(Arrays.toString(dna))).thenReturn(mutant);
        when(microserviceUser.getUser(identify)).thenReturn(new UserDto());
        //Act
        boolean isMutant = mutantService.isMutant(dna, identify);
        //Assert
        Assertions.assertTrue(isMutant);

    }

    @Test
    void isMutantFalseAndExistInBD(){
        //Arrange
        String identify = "123658787";
        String[] dna = {"TTGAGA","CAGTTC","TAATGT","TGCCGG","CAGCAT","TCACAT"};
        Mutant mutant = new Mutant("", false, identify);
        when(resultMutantSave.validateExistDna(Arrays.toString(dna))).thenReturn(mutant);
        when(microserviceUser.getUser(identify)).thenReturn(new UserDto());
        //Act
        boolean isMutant = mutantService.isMutant(dna, identify);
        //Assert
        Assertions.assertFalse(isMutant);

    }

    @Test
    void isMutantFalseAndNotExistInBD(){
        //Arrange
        String identify = "123658787";
        String[] dna = {"TTGAGA","CAGTTC","TAATGT","TGCCGG","CAGCAT","TCACAT"};
        when(resultMutantSave.validateExistDna(Arrays.toString(dna))).thenReturn(null);
        when(microserviceUser.getUser(identify)).thenReturn(new UserDto());
        //Act
        boolean isMutant = mutantService.isMutant(dna, identify);
        //Assert
        Assertions.assertFalse(isMutant);
    }
}