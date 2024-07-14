package com.forohub.forohub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {
		"com.forohub.forohub.user.repositories",
		"com.forohub.forohub.topico.repositories"
})
@EntityScan(basePackages = {
		"com.forohub.forohub.user.entities",
		"com.forohub.forohub.topico.entities"
})
public class ForohubApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForohubApplication.class, args);
	}

}
