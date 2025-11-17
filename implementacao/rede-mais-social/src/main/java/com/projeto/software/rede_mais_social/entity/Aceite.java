package com.projeto.software.rede_mais_social.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
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

    // Lado dono do OneToOne com CondicaoTermo
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "condicao_termo_id", unique = true)
    private CondicaoTermo condicaoTermo;

    // Um aceite possui vários itens (granularidade do aceite)
    @OneToMany(mappedBy = "aceite", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemAceite> itens;

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

    public CondicaoTermo getCondicaoTermo() {
        return condicaoTermo;
    }

    public void setCondicaoTermo(CondicaoTermo condicaoTermo) {
        this.condicaoTermo = condicaoTermo;
    }

    public List<ItemAceite> getItens() {
        return itens;
    }

    public void setItens(List<ItemAceite> itens) {
        this.itens = itens;
    }
}
