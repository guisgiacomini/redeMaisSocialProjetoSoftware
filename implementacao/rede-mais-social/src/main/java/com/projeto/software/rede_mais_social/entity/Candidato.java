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

    private String nome;
    private String sexo;
    private LocalDate dataNascimento;
    private String nacionalidade;
    private String enderecoResidencial;
    private String enderecoComercial; // Pode ser um objeto @Embedded
    private String profissao;

    // Um candidato tem um pedido
    @OneToOne(mappedBy = "candidato", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private PedidoAfiliacao pedidoAfiliacao;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "perfil_id", referencedColumnName = "id")
    private Perfil perfil;

    // --- Construtor Vazio (exigido pelo JPA) ---
    public Candidato() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PedidoAfiliacao getPedidoAfiliacao() {
        return pedidoAfiliacao;
    }

    public void setPedidoAfiliacao(PedidoAfiliacao pedidoAfiliacao) {
        this.pedidoAfiliacao = pedidoAfiliacao;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getEnderecoResidencial() {
        return enderecoResidencial;
    }

    public void setEnderecoResidencial(String enderecoResidencial) {
        this.enderecoResidencial = enderecoResidencial;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Pessoa getTipoPessoa() {
        return pessoa;
    }

    public void setTipoPessoa(Pessoa tipoPessoa) {
        this.pessoa = tipoPessoa;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEnderecoComercial() {
        return enderecoComercial;
    }

    public void setEnderecoComercial(String enderecoComercial) {
        this.enderecoComercial = enderecoComercial;
    }
}