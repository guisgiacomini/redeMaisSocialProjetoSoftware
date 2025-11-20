package com.projeto.software.rede_mais_social.repository;

import com.projeto.software.rede_mais_social.entity.Papel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
public interface PapelRepository extends JpaRepository<Papel, Integer> {
    boolean existsByCpfOrEmail(String documento, String email);
}
