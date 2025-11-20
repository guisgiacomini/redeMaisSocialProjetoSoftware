package com.projeto.software.rede_mais_social;

import com.projeto.software.rede_mais_social.entity.Candidato;
import com.projeto.software.rede_mais_social.entity.Entidade;
import com.projeto.software.rede_mais_social.entity.Papel;
import com.projeto.software.rede_mais_social.entity.Pessoa;
import com.projeto.software.rede_mais_social.repository.CandidatoRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class RedeMaisSocialApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedeMaisSocialApplication.class, args);
	}

}
