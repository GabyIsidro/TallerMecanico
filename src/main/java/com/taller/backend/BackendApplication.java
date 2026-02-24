package com.taller.backend;

import org.springframework.boot.SpringApplication; // El traductor mágico que arranca tu aplicación Spring Boot
import org.springframework.boot.autoconfigure.SpringBootApplication; // Anotación que le dice a Spring Boot que esta es la clase principal de tu aplicación

@SpringBootApplication // Anotación que combina las dos anteriores
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}

}
