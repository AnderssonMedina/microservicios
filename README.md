#PROYECTO UNIVERSIDAD

El microservicio dnaevaluator expone dos servicios rest. Su estructura de carpetas consta de 3 capas, implementando así una arquitectura limpia donde la capa de dominio es independiente del framework

application: En esta capa se encuentra el controller(MutantController) que es la puerta de entrada o adaptador primario, obtiene los datos para enviarlos a la capa de dominio.

domain: En ésta se encuentra la clase que contiene la lógica de negocio (MutantService), los modelos y una interfaz que se inyecta a través del constructor para posteriormente comúnicarse con la capa de infraestructura.

infrastructure: En ésta se encuentra la clase que se encarga de transformar los modelos de negocio en DTOs(ResultMutantService) para luego hacer los procesamientos en base de datos y entregar respuesta a la capa de dominio.

#Especificaciones

Este proyecto fue desarrollado en el siguiente entorno:

Spring Boot versión 3.4.3

Java versión 17

Gradle versión 8.13

Base de datos: h2-console

IDE de desarrollo: IntelliJ IDEA Community Edition 2024.3

#SERVICIOS REST

url: https://dnaevaluator.rj.r.appspot.com/api/mutant
Descripción: Se encarga de evaluar si un ADN ingresado pertenece a un humano o mutante, a su vez guarda en base de datos el resultado. Si se consulta nuevamente el ADN éste recupera la información de la BD.

Validaciones: Se valida el ADN ingresado. éste debe cumplir con la regla de negocio especificada: "En donde recibirás como parámetro un array de Strings que representan cada fila de una tabla de (NxN) con la secuencia del ADN. Las letras de los Strings solo pueden ser: (A,T,C,G),"

parámetros: String [] dna

método: POST

Content-Type: application/json

Posibles respuestas:

STATUS 200: Cuando el ADN ingresado es mutante(más de una secuencia de cuatro letras iguales)

STATUS 403: Cuando el ADN ingresado es humano.

STATUS 400: Cuando la secuencia ingresada no cumple con las especificaciones de la regla de negocio.

url: https://dnaevaluator.rj.r.appspot.com/api/stats
Descripción: Retorna en un objeto tipo json las estadísticas según la cantidad de humanos y mutantes evaluados, esta información se recupera de la base de datos

parámetros: No recibe

método: GET

respuesta:

STATUS 200

Content-Type: application/json

{ "count_mutant_dna": 5, "count_human_dna": 8, "ratio": 0.63 }

#AUTOMATIZACIÓN DE PRUEBAS

El porcentaje total de lineas de código cubiertas con pruebas unitarias es del 92%

#Ejecutar pruebas locales

Puede realizarse de dos maneras:

1. abrir la consola en intellij y ejecutar el comando ./gradlew clean test, este comando primero realizara una limpieza del proyecto y luego ejecutara las pruebas si el resultado fue exitoso se mostrara el mensaje "BUILd SUCCESSFUL"

2. Buscar en las tareas de gradle task - verificación -jacocoTestReport, esta opción nos entregara un informe detallado de la cobertura de pruebas en cada uno de los archivos, el informe lo encuentra en la ruta build - reports - jacoco - test - html - index html, se abre en un navegador para ver mejor los resultados

#Ejecutar pruebas Remotas

Dado el archivo test.yml que creamos para correr las pruebas automatizadas, cuando se suba cualquier commit, dirigite github - actions - e inmediatamente veremos como se ejecuta el pipiline, se configura todo el entorno para correr las pruebas e internamente se corre el comando ./gradlew test, al final entrega el resultado, si fue exitoso o fallido.

Este archivo define un flujo de trabajo (workflow) que se ejecuta automáticamente cuando se cumplen ciertas condiciones en tu repositorio, tales como 

push: Cuando se envían cambios a una rama del repositorio.

pull_request: Cuando se abre o actualiza un pull request.
    

