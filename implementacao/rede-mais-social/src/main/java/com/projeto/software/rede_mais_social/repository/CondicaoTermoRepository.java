package com.projeto.software.rede_mais_social.repository;

import com.projeto.software.rede_mais_social.entity.CondicaoTermo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CondicaoTermoRepository extends JpaRepository<CondicaoTermo, Long> {
    Optional<CondicaoTermo> findByTexto(String texto);
}
