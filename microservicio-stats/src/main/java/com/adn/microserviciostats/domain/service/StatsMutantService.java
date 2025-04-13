package com.adn.microserviciostats.domain.service;


import com.adn.microserviciostats.domain.model.Stats;
import com.adn.microserviciostats.domain.service.dependendy.IResultMutantSave;
import com.adn.microserviciostats.infrastructure.shared.dto.DetailMutantDto;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

/**
 * ClassName: StatsMutantService
 * Clase service que pertenece a la capa de dominio donde se maneja exlusivamente la lógica de negocio
 * Clase que se encarga de manejar toda la lógica para evaluar si un AND ingresado pertenece a una humano o mutante
 */
@Service
public class StatsMutantService {

    private static final String dnaHorizontalPattern = "(.*)(A{4,}|T{4,}|C{4,}|G{4,})(.*)";
    IResultMutantSave resultMutantSave;
    private int countMatches;

    /**
     * Param: IResultMutantSave dnaRepository
     * Inyección de dependencia por constructor, no se usa anotación @AutoWired para evitar dependencias con  el framework en la capa de dominio(lógica de negocio)
     * la interface IResultMutantSave nos conecta con la capa de infraestructura la cual provee el acceso a datos, o integración con servicios externos si fuese necesario
     */
    public StatsMutantService(IResultMutantSave dnaRepository) {
        this.resultMutantSave = dnaRepository;
    }


    /**
     * return: Stats
     * Método de acceso público donde se calcula las estadisticas sacando el promedio de mutantes,
     */
    public Stats getStats() {
        DecimalFormat decimalFormat = new DecimalFormat("###.#");
        long dnaHuman = resultMutantSave.countResult(false);
        long dnaMutant = resultMutantSave.countResult(true);
        double ratio = (dnaHuman == 0.0) ? 0.0 : (double) dnaMutant / dnaHuman;

        /* Retorna model stats. Se multiplica ratio * 100 y se divide por 100 para redondear decimales */
        return new Stats(dnaMutant, dnaHuman, Math.round(ratio * 100.0) / 100.0);
    }


    /**
     * return: void
     * Método de acceso público donde se almacena detalle de los adn procesados
     */
    public void saveDataMutant(DetailMutantDto dataMutant) {
         resultMutantSave.saveMutantData(dataMutant);
    }
}
