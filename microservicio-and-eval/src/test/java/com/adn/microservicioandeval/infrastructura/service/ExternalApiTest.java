package com.adn.microservicioandeval.infrastructura.service;

import com.adn.microservicioandeval.infrastructure.service.ExternalApi;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ExternalApiTest {

    @Autowired
    private ExternalApi externalApi;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    public void testGetInfoExternalApi() throws Exception {
        String result = externalApi.getInfoExternalApi();

        assertNotNull(result);

        ResponseEntity<String> response = restTemplate.getForEntity("https://restcountries.com/v3.1/all", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(result, response.getBody());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(result);
        assertTrue(jsonNode.isArray());
        assertFalse(jsonNode.isEmpty());
        assertTrue(jsonNode.get(0).has("name"));
    }

    @Test
    public void testGetInfoGetCountryColombia() throws Exception {
        String result = externalApi.getInfoExternalApi();

        assertNotNull(result);

        ResponseEntity<String> response = restTemplate.getForEntity("https://restcountries.com/v3.1/all", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(result, response.getBody());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(result);
        assertTrue(jsonNode.isArray());
        assertFalse(jsonNode.isEmpty());

        JsonNode colombiaNode = null;
        for (JsonNode node : jsonNode) {
            if (node.has("name") && node.get("name").has("common") && node.get("name").get("common").asText().equals("Colombia")) {
                colombiaNode = node;
                break;
            }
        }

        assertNotNull(colombiaNode);
        assertTrue(colombiaNode.has("capital"));
        assertEquals("Bogotá", colombiaNode.get("capital").get(0).asText());
        assertTrue(colombiaNode.has("population"));
        assertTrue(colombiaNode.get("population").asInt() > 0);
        assertTrue(colombiaNode.has("currencies"));
        assertTrue(colombiaNode.get("currencies").has("COP"));
        assertEquals("Colombian peso", colombiaNode.get("currencies").get("COP").get("name").asText());
    }

    @Test
    public void testGetCountriesByRegion() throws Exception {
        String region = "Americas";
        ResponseEntity<String> response = restTemplate.getForEntity("https://restcountries.com/v3.1/region/" + region, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response.getBody());

        assertTrue(jsonNode.isArray());
        assertFalse(jsonNode.isEmpty());
        assertTrue(jsonNode.get(0).has("region"));
        assertEquals(region, jsonNode.get(0).get("region").asText());
    }

    @Test
    public void testGetCountriesBySubregion() throws Exception {
        String subregion = "South America";
        ResponseEntity<String> response = restTemplate.getForEntity("https://restcountries.com/v3.1/subregion/" + subregion, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(response.getBody());

        assertTrue(jsonNode.isArray());
        assertFalse(jsonNode.isEmpty());
        assertTrue(jsonNode.get(0).has("subregion"));
        assertEquals(subregion, jsonNode.get(0).get("subregion").asText());
    }
  
}