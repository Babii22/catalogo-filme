package br.com.catalogo;

import br.com.catalogo.config.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
public class CatalogoFilmeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogoFilmeApplication.class, args);
	}

}