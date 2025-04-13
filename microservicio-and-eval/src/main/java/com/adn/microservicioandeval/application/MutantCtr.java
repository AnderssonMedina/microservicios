package com.adn.microservicioandeval.application;

import com.adn.microservicioandeval.domain.service.MutantService;
import com.adn.microservicioandeval.infrastructure.shared.dto.DNASequenceDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller qye se encuentra en la paca de aplicación y funciona como adaptador para acceder a la capa de dominio
 */
@RestController
@RequestMapping(value="api")
public class MutantCtr {

    //Inyección de dependencia para comunicarse con la capa de dominio
    @Autowired
    MutantService mutantService;

    @Autowired
    ModelMapper mapper;

    /**
     *
     * param dna
     * return status 403 si el adn es humano y 200 si el adn es mutante
     * Servicio rest
     **/
    @PostMapping(value = "/mutant")
    public ResponseEntity<String> isMutant(@RequestBody DNASequenceDto dna, @RequestParam String identify) {
        String[] dnaArray;
        try {
             dnaArray = dna.getDna();
        }catch(Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        if (mutantService.isMutant(dnaArray, identify)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
