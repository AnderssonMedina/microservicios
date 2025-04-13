package com.adn.microserviciostats.infrastructure.service;


import com.adn.microserviciostats.domain.service.dependendy.IExternalApi;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ExternalApi implements IExternalApi {

    @Override
    public  String getInfoExternalApi(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://restcountries.com/v3.1/all";
        // GET request
        String result = restTemplate.getForObject(url, String.class);
        System.out.println(result);
        return result;
    }
}