package com.jabonesArtesanales.co;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EntityScan(basePackages = {
	    "com.jabonesArtesanales.co.domain",     // <-- ajusta al paquete real de tus @Entity
	})
	@EnableJpaRepositories(basePackages = {
	    "com.jabonesArtesanales.co.repository"  // <-- ajusta al paquete real de tus repos
	})
public class JabonesArtesanalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(JabonesArtesanalesApplication.class, args);
	}

}
