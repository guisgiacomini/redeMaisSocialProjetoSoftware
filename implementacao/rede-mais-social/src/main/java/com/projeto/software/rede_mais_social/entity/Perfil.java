package com.projeto.software.rede_mais_social.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String descricao;

    @ManyToMany
    private Set<Campanha> campanhas = new HashSet<>();

    @OneToOne
    private Candidato candidato;

    @ManyToMany
    private Set<Habilidade> habilidades;

    @ManyToMany
    private Set<Interesse> interesses;


    public Perfil() {
    }

    public Perfil(Integer id, String descricao, Set<Campanha> campanhas, Candidato candidato, Set<Habilidade> habilidades, Set<Interesse> interesses) {
        this.id = id;
        this.descricao = descricao;
        this.campanhas = campanhas;
        this.candidato = candidato;
        this.habilidades = habilidades;
        this.interesses = interesses;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Set<Campanha> getCampanhas() {
        return campanhas;
    }

    public void setCampanhas(Set<Campanha> campanhas) {
        this.campanhas = campanhas;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public Set<Habilidade> getHabilidades() {


        return habilidades;
    }

    public void setHabilidades(Set<Habilidade> habilidades) {
        this.habilidades = habilidades;
    }

    public Set<Interesse> getInteresses() {
        return interesses;
    }

    public void setInteresses(Set<Interesse> interesses) {
        this.interesses = interesses;
    }
}