package com.projeto.software.rede_mais_social.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "papel")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Papel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "entidade_id", nullable = false, unique = true) 
    private Entidade entidade;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Entidade getEntidade() {
        return entidade;
    }

    public void setEntidade(Entidade entidade) {
        this.entidade = entidade;
    }
}