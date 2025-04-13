package com.adn.microserviciostats.infrastructure.service;



import com.adn.microserviciostats.domain.service.dependendy.IResultMutantSave;
import com.adn.microserviciostats.infrastructure.persistence.MutantRepository;
import com.adn.microserviciostats.infrastructure.shared.dto.DetailMutantDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * ClassName: ResultMutantService
 * Clase repository que pertenece a la capa de infraestructura donde se orquesta toda la conexión tanto con  base de datos como con servicios REST o SOAP si fuese necesario
 */
@Repository
public class ResultMutantService implements IResultMutantSave {

    //Se inyecta dependencia con la interfaz MutantRepository que nos conecta con la base de datos
    @Autowired
    private MutantRepository mutantRepository;


    /**
     * param : boolean
     * método que se encarga de contar en base de datos la cantidad de mutantes o humanos según el tipo de ADN que llegue como parámetro
     */
    @Override
    public long countResult(boolean isMutant) {
       return  mutantRepository.countByType(isMutant);
    }

    @Override
    public void saveMutantData(DetailMutantDto mutantData) {
        mutantRepository.save(mutantData);
    }

}
