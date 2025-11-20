package com.projeto.software.rede_mais_social.dto;

import java.time.LocalDate;

public class PedidoAfiliacaoDTO {

    private Integer pedidoId;

    private String email;
    private String tipoPessoa; // "FISICA" ou "JURIDICA"
    private String documento;  // CPF ou CNPJ
    private String nome;
    private String sexo;       // Ex: "MASCULINO", "FEMININO"
    private LocalDate dataNascimento;
    private String nacionalidade;
    private String profissao;

    private String enderecoResidencial;
    private String enderecoComercial;

    private String habilidades;
    private String interesses;

    private boolean aceiteTermos;


    // --- Getters e Setters ---

    public Integer getPedidoId() { return pedidoId; }
    public void setPedidoId(Integer pedidoId) { this.pedidoId = pedidoId; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTipoPessoa() { return tipoPessoa; }
    public void setTipoPessoa(String tipoPessoa) { this.tipoPessoa = tipoPessoa; }

    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getNacionalidade() { return nacionalidade; }
    public void setNacionalidade(String nacionalidade) { this.nacionalidade = nacionalidade; }

    public String getProfissao() { return profissao; }
    public void setProfissao(String profissao) { this.profissao = profissao; }

    public String getEnderecoResidencial() { return enderecoResidencial; }
    public void setEnderecoResidencial(String enderecoResidencial) { this.enderecoResidencial = enderecoResidencial; }

    public String getEnderecoComercial() { return enderecoComercial; }
    public void setEnderecoComercial(String enderecoComercial) { this.enderecoComercial = enderecoComercial; }

    public String getHabilidades() { return habilidades; }
    public void setHabilidades(String habilidades) { this.habilidades = habilidades; }

    public String getInteresses() { return interesses; }
    public void setInteresses(String interesses) { this.interesses = interesses; }

    public boolean isAceiteTermos() { return aceiteTermos; }
    public void setAceiteTermos(boolean aceiteTermos) { this.aceiteTermos = aceiteTermos; }
}
