package com.projeto.software.rede_mais_social.repository;

import com.projeto.software.rede_mais_social.entity.Candidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidatoRepository extends JpaRepository<Candidato, Integer> {

    Optional<Candidato> findByEmail(String email);

}
