package com.projeto.software.rede_mais_social.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "termo_compromisso")
public class TermoDeCompromisso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Lado dono do OneToOne com PedidoAfiliacao
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_afiliacao_id", unique = true)
    private PedidoAfiliacao pedidoAfiliacao;

    // Um termo possui várias condições
    @OneToMany(mappedBy = "termoDeCompromisso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CondicaoTermo> condicoes = new ArrayList<>();

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PedidoAfiliacao getPedidoAfiliacao() {
        return pedidoAfiliacao;
    }

    public void setPedidoAfiliacao(PedidoAfiliacao pedidoAfiliacao) {
        this.pedidoAfiliacao = pedidoAfiliacao;
    }

    public List<CondicaoTermo> getCondicoes() {
        return condicoes;
    }

    public void setCondicoes(List<CondicaoTermo> condicoes) {
        this.condicoes = condicoes;
    }

    public List<CondicaoTermo> buscarTermoVigente(){
        return condicoes;
    }
}
