package com.adn.microserviciostats.application;


import com.adn.microserviciostats.domain.service.StatsMutantService;
import com.adn.microserviciostats.infrastructure.shared.dto.DetailMutantDto;
import com.adn.microserviciostats.infrastructure.shared.dto.StatsDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
    StatsMutantService mutantService;

    @Autowired
    ModelMapper mapper;

    /**

     * param
     * return StatsDto
     * Servicio rest que retorna  las estadisticas de los ADN analizados
     **/
    @GetMapping(value = "/stats", produces =MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<StatsDto> getStats (){
        // Se realiza mapper del model Stats a StatsDTO, esto debido a que es la capa de aplicacion(adaptador) no  deben usar elementos de la casa de dominio
        return  ResponseEntity.ok(mapper.map(mutantService.getStats(), StatsDto.class));
    }

    @PostMapping(value = "/saveDetailMutant")
    public ResponseEntity<String> saveDetailMutant(@RequestBody DetailMutantDto dataMutant) {
        try {
            mutantService.saveDataMutant(dataMutant);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}
