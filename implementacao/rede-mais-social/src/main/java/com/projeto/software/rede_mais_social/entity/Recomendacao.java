package com.projeto.software.rede_mais_social.entity;

import jakarta.persistence.*;

/**
 * Recomendação, solicitada por uma Entidade, apresentada por uma ONG
 * e especificada por um Representante.
 */
@Entity
@Table(name = "recomendacao")
public class Recomendacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Uma recomendação é solicitada por uma Entidade
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entidade_solicitante_id")
    private Entidade entidadeSolicitante;

    // Uma recomendação é apresentada por uma ONG
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ong_apresentadora_id")
    private ONG ongApresentadora;

    // Uma recomendação é especificada por um Representante
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "representante_id")
    private Representante representante;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Entidade getEntidadeSolicitante() {
        return entidadeSolicitante;
    }

    public void setEntidadeSolicitante(Entidade entidadeSolicitante) {
        this.entidadeSolicitante = entidadeSolicitante;
    }

    public ONG getOngApresentadora() {
        return ongApresentadora;
    }

    public void setOngApresentadora(ONG ongApresentadora) {
        this.ongApresentadora = ongApresentadora;
    }

    public Representante getRepresentante() {
        return representante;
    }

    public void setRepresentante(Representante representante) {
        this.representante = representante;
    }
}