package com.projeto.software.rede_mais_social.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

import jakarta.persistence.*;
import java.util.List;

/**
 * Classe base para todas as entidades do sistema (Pessoas e ONGs).
 * Marcada como abstract pois no mundo real não existe uma "Entidade" genérica,
 * ela deve ser ou uma Pessoa (Física/Jurídica) ou uma ONG.
 */
@Entity
@Table(name = "entidade")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Entidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    // Uma entidade pode ter múltiplos endereços/localizações
    @OneToMany(mappedBy = "entidade", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Localizacao> localizacoes;

    // Relacionamento OneToOne com Papel (Candidato, Voluntário, etc.)
    // mappedBy = "entidade" indica que a chave estrangeira está na tabela 'papel'
    @OneToOne(mappedBy = "entidade", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Papel papel;

    // Uma entidade pode solicitar várias recomendações (conforme diagrama)
    // Se a classe Recomendacao não existir no seu pacote ainda, pode comentar as linhas abaixo
    // @OneToMany(mappedBy = "entidadeSolicitante", cascade = CascadeType.ALL)
    // private List<Recomendacao> recomendacoesSolicitadas;

    public Entidade() {
    }

    public Entidade(String nome) {
        this.nome = nome;
    }

    // --- Getters e Setters ---

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

    /*
    public List<Recomendacao> getRecomendacoesSolicitadas() {
        return recomendacoesSolicitadas;
    }

    public void setRecomendacoesSolicitadas(List<Recomendacao> recomendacoesSolicitadas) {
        this.recomendacoesSolicitadas = recomendacoesSolicitadas;
    }
    */
}