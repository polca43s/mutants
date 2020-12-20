# Project example mutants exercise

## Requisitos y dependencias:

* El proyecto utiliza [java 1.8](https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
* Utiliza Maven para la gestión de dependencias y actividades de instalación, build y packaging.
* Construi la app con ayuda de [Spring Boot](https://spring.io/)
* La base de datos es [H2](https://www.h2database.com/html/main.html)
* Para los tests se uso [JUnit](http://junit.org/junit5/) y [Mockito](http://site.mockito.org/).
* Para hostear la aplicacion use el hosting cloud computing [Heroku](https://www.heroku.com/).
* Para el parseo de los datos en formato Json uso [Gson](https://github.com/google/gson)

## Coverage
* ![Coverage of 100% in Classes, 97% in Methods and 95% in Lines.](Set correct Links)

## Vulnerabilidades y code review
* Se utilizo el plugin de sonar lint

## Start
* Para levantarlo local ejecutar
`mvn spring-boot:run`
  si necesita mas memoria ya que va a procesar casos de grandes datos de entrada usar las opciones de maven `export MAVEN_OPTS=-Xmx1024m -XX:MaxPermSize=128M` y setear la memoria que crean conveniente.
* Luego se le puede pegar usando Postman o herramienta similar.

      curl --location --request POST 'http://localhost:8080/mutant/'
      --header 'Content-Type: application/json'
      --data-raw '{
          "dna": [
          "ATGCGA",
          "CAGTGC",
          "TTATGT",
          "AGAAGG",
          "CCCCTA",
          "TCACTG"
          ]
      }'
        
      curl --location --request GET 'http://localhost:8080/stats'

      En caso de verificar un mutante, debería devolver un HTTP 200-OK, en caso contrario un
      403-Forbidden
      Además el /stats devuelve el siguiente formato:
      {"count_mutant_dna":5,"count_human_dna":4,"ratio":1.25}

## Deploy en Heroku
 //TODO:


## Paralelizacion y Infra
Para mejorar la performance se podría usar un Executor que corra en distintos threads los distintos recorridos que se
realizan en el algoritmo de búsqueda de genes mutantes, otra cosa que se puede hacer es usar un balancer y tener mas 
instancias de la app para atender mas requests, o si utilizamos AWS o similar utilizar una solución que autoescale en
caso de recibir cargas de trabajo.

 