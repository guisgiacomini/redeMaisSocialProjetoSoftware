package com.projeto.software.rede_mais_social.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ong")
public class ONG extends Entidade {

    // Uma ONG pode abrir várias campanhas
    @OneToMany(mappedBy = "ong", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Campanha> campanhas = new ArrayList<>();

    // Uma ONG pode apresentar várias recomendações
    @OneToMany(mappedBy = "ongApresentadora")
    private List<Recomendacao> recomendacoesApresentadas = new ArrayList<>();

    // Getters e Setters
    public List<Campanha> getCampanhas() {
        return campanhas;
    }

    public void setCampanhas(List<Campanha> campanhas) {
        this.campanhas = campanhas;
    }

    public List<Recomendacao> getRecomendacoesApresentadas() {
        return recomendacoesApresentadas;
    }

    public void setRecomendacoesApresentadas(List<Recomendacao> recomendacoesApresentadas) {
        this.recomendacoesApresentadas = recomendacoesApresentadas;
    }
}
