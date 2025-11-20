package com.projeto.software.rede_mais_social.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "representante")
public class Representante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Um representante solicita/realiza várias aprovações
    @OneToMany(mappedBy = "representante")
    private List<Aprovacao> aprovacoes;

    // Um representante especifica várias recomendações
    @OneToMany(mappedBy = "representante")
    private List<Recomendacao> recomendacoes;

    // Um representante 'avalia' uma Pessoa (assumindo ManyToOne)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_avaliada_id")
    private Pessoa pessoaAvaliada;

    // Um representante 'representa' uma Entidade (assumindo ManyToOne)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entidade_representada_id")
    private Entidade entidadeRepresentada;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Aprovacao> getAprovacoes() {
        return aprovacoes;
    }

    public void setAprovacoes(List<Aprovacao> aprovacoes) {
        this.aprovacoes = aprovacoes;
    }

    public List<Recomendacao> getRecomendacoes() {
        return recomendacoes;
    }

    public void setRecomendacoes(List<Recomendacao> recomendacoes) {
        this.recomendacoes = recomendacoes;
    }

    public Pessoa getPessoaAvaliada() {
        return pessoaAvaliada;
    }

    public void setPessoaAvaliada(Pessoa pessoaAvaliada) {
        this.pessoaAvaliada = pessoaAvaliada;
    }

    public Entidade getEntidadeRepresentada() {
        return entidadeRepresentada;
    }

    public void setEntidadeRepresentada(Entidade entidadeRepresentada) {
        this.entidadeRepresentada = entidadeRepresentada;
    }
}