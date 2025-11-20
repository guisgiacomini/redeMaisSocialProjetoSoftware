package com.projeto.software.rede_mais_social.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Aceite de uma Condição de Termo.
 */
@Entity
@Table(name = "aceite")
public class Aceite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate data;

    // Um aceite possui vários itens (granularidade do aceite)
    @OneToMany(mappedBy = "aceite", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemAceite> itens = new ArrayList<>();

    @OneToOne(mappedBy = "aceite", cascade = CascadeType.ALL)
    private TermoDeCompromisso termoDeCompromisso;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }


    public List<ItemAceite> getItens() {
        return itens;
    }

    public void setItens(List<ItemAceite> itens) {
        this.itens = itens;
    }
}
