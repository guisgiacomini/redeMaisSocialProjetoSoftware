package com.projeto.software.rede_mais_social.entity;

import jakarta.persistence.*;

/**
 * Item individual de um Aceite.
 */
@Entity
@Table(name = "item_aceite")
public class ItemAceite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Muitos itens para um aceite
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "aceite_id", nullable = false)
    private Aceite aceite;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Aceite getAceite() {
        return aceite;
    }

    public void setAceite(Aceite aceite) {
        this.aceite = aceite;
    }
}
