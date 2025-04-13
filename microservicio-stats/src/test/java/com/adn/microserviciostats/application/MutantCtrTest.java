package com.adn.microserviciostats.application;


import com.adn.microserviciostats.domain.model.Stats;
import com.adn.microserviciostats.domain.service.StatsMutantService;
import com.adn.microserviciostats.infrastructure.shared.dto.StatsDto;
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
    StatsMutantService mutantService;

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
    void getStatsOk(){
        //Arrange
        Stats stats =  new Stats(10, 5, 2.0);
        StatsDto statsDtoResponse =  new StatsDto();
        statsDtoResponse.setCountMutantDna(10);
        statsDtoResponse.setCountHumanDna(5);
        statsDtoResponse.setRatio(2.0);
        when(mutantService.getStats()).thenReturn(stats);
        when(mapper.map(stats, StatsDto.class)).thenReturn(statsDtoResponse);
        //Act
        ResponseEntity<StatsDto> statsDto = mutantCtr.getStats();
        //Assert
        Assertions.assertEquals(statsDto.getStatusCode(), HttpStatus.OK);
    }




}