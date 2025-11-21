package com.projeto.software.rede_mais_social.repository;

import com.projeto.software.rede_mais_social.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
}
