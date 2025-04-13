package com.adn.microserviciostats.service;

import com.adn.microserviciostats.domain.model.Stats;
import com.adn.microserviciostats.domain.service.StatsMutantService;
import com.adn.microserviciostats.domain.service.dependendy.IResultMutantSave;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import static org.mockito.Mockito.when;

/**
 * ClassName
 **/
class StatsMutantServiceTest {

    @Mock
    private IResultMutantSave resultMutantSave;

    @InjectMocks
    private StatsMutantService mutantService;


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getStats() {
        //Arrange
        when(resultMutantSave.countResult(false)).thenReturn(5L);
        when(resultMutantSave.countResult(true)).thenReturn(10L);
        //Act
        Stats stats = mutantService.getStats();
        //Assert
        Assertions.assertEquals(2.0, stats.getRatio());
        Assertions.assertEquals(5L, stats.getCountHumanDna());
        Assertions.assertEquals( 10L, stats.getCountMutantDna());

    }

    @Test
    void getStatswithDecimals() {
        //Arrange
        when(resultMutantSave.countResult(false)).thenReturn(6L);
        when(resultMutantSave.countResult(true)).thenReturn(4L);
        //Act
        Stats stats = mutantService.getStats();
        //Assert
        Assertions.assertEquals( 0.67, stats.getRatio());
        Assertions.assertEquals(6L, stats.getCountHumanDna());
        Assertions.assertEquals(4L, stats.getCountMutantDna());

    }
}