package com.projeto.software.rede_mais_social.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "voluntario")
public class Voluntario extends Papel {

    // Um voluntário solicita um pedido de afiliação
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_afiliacao_id")
    private PedidoAfiliacao pedidoAfiliacao;

    // Getters e Setters
    public PedidoAfiliacao getPedidoAfiliacao() {
        return pedidoAfiliacao;
    }

    public void setPedidoAfiliacao(PedidoAfiliacao pedidoAfiliacao) {
        this.pedidoAfiliacao = pedidoAfiliacao;
    }
}
