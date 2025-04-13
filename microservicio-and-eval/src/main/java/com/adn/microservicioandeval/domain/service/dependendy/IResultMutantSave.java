package com.adn.microservicioandeval.domain.service.dependendy;


import com.adn.microservicioandeval.domain.model.Mutant;

/**
 * interfaz que se encarga de comunicar a la capa de dominio con la capa de infraestructura, la cual provee el acceso a datos
 */
public interface IResultMutantSave {

    void saveResultMutant(Mutant mutant);
    Mutant validateExistDna(String dna);
}
