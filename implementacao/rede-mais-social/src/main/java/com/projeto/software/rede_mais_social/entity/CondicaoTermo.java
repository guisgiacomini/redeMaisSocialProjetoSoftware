package com.projeto.software.rede_mais_social.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "condicao_termo")
public class CondicaoTermo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob // Para textos longos
    private String texto;

    // Muitas condições para um termo
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "termo_compromisso_id", nullable = false)
    private TermoDeCompromisso termoDeCompromisso;


    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public TermoDeCompromisso getTermoDeCompromisso() {
        return termoDeCompromisso;
    }

    public void setTermoDeCompromisso(TermoDeCompromisso termoDeCompromisso) {
        this.termoDeCompromisso = termoDeCompromisso;
    }

}