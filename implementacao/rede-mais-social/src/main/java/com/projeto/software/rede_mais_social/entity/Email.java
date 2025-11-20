package com.projeto.software.rede_mais_social.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("EMAIL")
public class Email extends Localizacao{

    private String email;

    public Email(Integer id, Entidade entidade, String email) {
        super(id, entidade);
        this.email = email;
    }

    public Email(String email) {
        this.email = email;
    }

    public Email() {
        super();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
