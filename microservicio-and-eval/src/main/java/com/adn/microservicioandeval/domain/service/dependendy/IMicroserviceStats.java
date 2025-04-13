package com.adn.microservicioandeval.domain.service.dependendy;

import com.adn.microservicioandeval.infrastructure.shared.dto.DetailMutantDto;
import com.adn.microservicioandeval.infrastructure.shared.dto.StatsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "microservicio-stats")
public interface IMicroserviceStats {

    @GetMapping(value = "/api/stats")
    StatsDto getStats();

    @PostMapping(value = "/api/saveDetailMutant")
    String saveDetailMutant(@RequestBody DetailMutantDto dataMutant);

}