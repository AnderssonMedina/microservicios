# Proyecto de Microservicios - Análisis de ADN Mutante

Este proyecto está basado en una arquitectura de microservicios implementada con Spring Boot y Spring Cloud. Su propósito es analizar secuencias de ADN para determinar si pertenecen a mutantes, así como registrar estadísticas y datos de usuarios.

## Repositorios del Proyecto

- [Microservicios](https://github.com/AnderssonMedina/microservicios)
- [Eureka Server](https://github.com/AnderssonMedina/eureka-server)
- [Config Server](https://github.com/AnderssonMedina/spring-cloud-config)

---

## Microservicios

Se crearon tres microservicios con sus propias configuraciones (`application.properties`) y bases de datos:

### 1. **microservicio-usuarios**
- **POST** `/api/createUser`: Crea un nuevo usuario.
- **GET** `/api/user/{id}`: Obtiene información de un usuario por su identificación.


### 2. **microservicio-adn**
- **POST** `/api/mutant`: Analiza una secuencia de ADN.
  - Respuesta `200 OK` si es mutante.
  - Respuesta `403 Forbidden` si no lo es.
  - Respuesta `400 Bad Request`: Secuencia inválida.

### 3. **microservicio-stats**
- **GET** `/api/stats`: Devuelve estadísticas de ADN analizados.

---

## Arquitectura

Se sigue una arquitectura limpia con tres capas:

- **application**: Controladores.
- **domain**: Modelos y lógica de negocio.
- **infrastructure**: Conexiones con la base de datos y servicios externos.

---

## Comunicación entre Microservicios

- El servicio `adn` consume los servicios `usuarios` y `stats` utilizando **FeignClient** para obtener información del usuario y registrar el resultado del análisis.
- Interfaces definidas para facilitar la comunicación.
- Comunicación habilitada por **Eureka** para descubrimiento de servicios.

---

## Configuración Centralizada

- Se implementó un **config-server** con repositorio remoto en GitHub.
- Cada microservicio incluye un archivo `bootstrap.properties` para conectarse al servidor de configuración.

---

## Descubrimiento de Servicios con Eureka

- Se creó un servidor Eureka para registrar todos los microservicios.
- Cada microservicio se registró como cliente de Eureka.
- La comunicación entre servicios se realiza sin URLs directas gracias al descubrimiento dinámico.

---

## Pruebas de Endpoints

### Crear Usuario

```bash
curl --location 'http://localhost:8082/api/createUser' \
--header 'Content-Type: application/json' \
--data '{
  "identify": "102043617",
  "name": "Andersson mndina",
  "age": "34"
}'
```

### Validar ADN Mutante

```bash
curl --location 'http://localhost:8083/api/mutant?identify=102043617' \
--header 'Content-Type: application/json' \
--data '{
   "dna":["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTC"]
}'
```

---

## Monitorización y Métricas

- Se agregó la dependencia **Spring Boot Actuator** para exponer `/actuator/health` y métricas.
- Integración con **Prometheus** y **Grafana** para visualización en tiempo real.
  - Grafana: http://localhost:3000
  - Prometheus: http://localhost:9090/

---

## Despliegue con Docker

### Dockerfile

Cada microservicio incluye un archivo `Dockerfile` para construir su imagen a partir del JAR correspondiente.

### Docker Compose

Se creó un archivo `docker-compose.yml` que:

- Levanta los tres microservicios.
- Incluye `config-server` y `eureka-server`.
- Permite levantar todo el entorno con:

```bash
docker-compose build
docker-compose up
```

---

## Especificaciones Técnicas

- **Spring Boot**: 3.4.3  
- **Java**: 17  
- **Gradle**: 8.13  
- **Base de datos**: H2 Console  
- **IDE**: IntelliJ IDEA Community Edition 2024.3  

---

## Pruebas y Verificación

- Se probaron todos los endpoints con Postman.
- Verificación correcta de:
  - Comunicación entre microservicios.
  - Registro en Eureka.
  - Configuración centralizada.
  - Métricas en Grafana.
  - Contenedores corriendo correctamente vía Docker Compose.

---

## Automatización de Pruebas

- **Cobertura total**: 92% de líneas cubiertas con pruebas unitarias.

### Ejecutar Pruebas Locales

1. Desde consola:
```bash
./gradlew clean test
```
   Si es exitoso, mostrará `BUILD SUCCESSFUL`.

2. Desde Gradle:
   - Navegar a: `Tasks > verification > jacocoTestReport`
   - El informe se encuentra en:
     ```
     build/reports/jacoco/test/html/index.html
     ```

### Ejecutar Pruebas Remotas

Se utiliza `test.yml` para automatizar pruebas con GitHub Actions. El flujo se activa con:

- `push`: Al hacer commit.
- `pull_request`: Al crear o actualizar un pull request.

Internamente se ejecuta el comando:

```bash
./gradlew test
```

---

