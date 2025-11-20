package com.projeto.software.rede_mais_social.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Candidato extends Papel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Pessoa pessoa;

    @Column(unique = true, nullable = true) // Nulo se for JURIDICA
    private String cpf;

    @Column(unique = true, nullable = true) // Nulo se for FISICA
    private String cnpj;

    // Um candidato tem um pedido
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_afiliacao_id")
    private PedidoAfiliacao pedidoAfiliacao;

    @OneToOne(mappedBy = "candidato", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Geografica localizacaoGeografica;

    @OneToOne(mappedBy = "candidato", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Perfil perfil;

    // --- Construtor Vazio (exigido pelo JPA) ---
    public Candidato() {
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public PedidoAfiliacao getPedidoAfiliacao() {
        return pedidoAfiliacao;
    }

    public void setPedidoAfiliacao(PedidoAfiliacao pedidoAfiliacao) {
        this.pedidoAfiliacao = pedidoAfiliacao;
    }

    public Geografica getLocalizacaoGeografica() {
        return localizacaoGeografica;
    }

    public void setLocalizacaoGeografica(Geografica localizacaoGeografica) {
        this.localizacaoGeografica = localizacaoGeografica;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }
}