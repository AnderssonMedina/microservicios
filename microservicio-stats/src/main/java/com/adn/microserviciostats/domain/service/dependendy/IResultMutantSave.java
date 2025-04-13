package com.adn.microserviciostats.domain.service.dependendy;


import com.adn.microserviciostats.infrastructure.shared.dto.DetailMutantDto;

/**
 * interfaz que se encarga de comunicar a la capa de dominio con la capa de infraestructura, la cual provee el acceso a datos
 */
public interface IResultMutantSave {

    long countResult(boolean isMutant);
    void saveMutantData(DetailMutantDto mutantData);
}
