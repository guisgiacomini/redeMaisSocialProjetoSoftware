package com.projeto.software.rede_mais_social.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "pessoa_juridica")
public class Juridica extends Pessoa {

    @Column(unique = true, length = 18)
    private String cnpj;

    public Juridica() {
        super();
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
