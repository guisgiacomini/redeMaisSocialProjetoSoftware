package com.projeto.software.rede_mais_social;

import com.projeto.software.rede_mais_social.entity.*;
import com.projeto.software.rede_mais_social.repository.CandidatoRepository;
import com.projeto.software.rede_mais_social.repository.TermoDeCompromissoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
public class RedeMaisSocialApplication implements CommandLineRunner {

	private TermoDeCompromissoRepository repository;

	public RedeMaisSocialApplication(TermoDeCompromissoRepository repository) {
		this.repository = repository;
	}

	public static void main(String[] args) {
		SpringApplication.run(RedeMaisSocialApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		TermoDeCompromisso termoDeCompromisso = new TermoDeCompromisso();
		CondicaoTermo condicaoTermo = new CondicaoTermo();
		condicaoTermo.setTexto("1. O candidato compromete-se a respeitar o estatuto da ONG.");
		condicaoTermo.setTermoDeCompromisso(termoDeCompromisso);
		termoDeCompromisso.setCondicoes(List.of(condicaoTermo));
		repository.save(termoDeCompromisso);
	}
}
