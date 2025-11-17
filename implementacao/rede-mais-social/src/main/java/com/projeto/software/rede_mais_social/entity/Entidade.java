package com.projeto.software.rede_mais_social.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "entidade")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Entidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @OneToMany(mappedBy = "entidade", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Localizacao> localizacoes;

    @OneToOne(mappedBy = "entidade", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Papel papel;

    // @OneToMany(mappedBy = "entidadeSolicitante", cascade = CascadeType.ALL)
    // private List<Recomendacao> recomendacoesSolicitadas;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Localizacao> getLocalizacoes() {
        return localizacoes;
    }

    public void setLocalizacoes(List<Localizacao> localizacoes) {
        this.localizacoes = localizacoes;
    }

    public Papel getPapel() {
        return papel;
    }

    public void setPapel(Papel papel) {
        this.papel = papel;
    }

    // public List<Recomendacao> getRecomendacoesSolicitadas() {
    //     return recomendacoesSolicitadas;
    // }

    // public void setRecomendacoesSolicitadas(List<Recomendacao> recomendacoesSolicitadas) {
    //     this.recomendacoesSolicitadas = recomendacoesSolicitadas;
    // }
}
