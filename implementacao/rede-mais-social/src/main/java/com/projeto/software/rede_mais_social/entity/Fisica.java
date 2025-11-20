package com.projeto.software.rede_mais_social.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "pessoa_fisica")
public class Fisica extends Pessoa {

    @Column(unique = true)
    private String cpf;

    public Fisica() {
        super();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
