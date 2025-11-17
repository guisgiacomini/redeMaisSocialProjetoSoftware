package com.projeto.software.rede_mais_social.entity;


import jakarta.persistence.*;
import java.util.List;


@Entity
@Table(name = "pessoa")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pessoa extends Entidade {


}