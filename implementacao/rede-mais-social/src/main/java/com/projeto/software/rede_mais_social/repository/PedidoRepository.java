package com.projeto.software.rede_mais_social.repository;

import com.projeto.software.rede_mais_social.entity.PedidoAfiliacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<PedidoAfiliacao,Integer> {
}
