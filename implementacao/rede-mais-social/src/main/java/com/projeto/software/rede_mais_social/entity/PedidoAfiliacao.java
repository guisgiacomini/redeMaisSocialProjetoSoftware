package com.projeto.software.rede_mais_social.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedido_afiliacao")
public class PedidoAfiliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;
    private LocalDate data;

    // Lado inverso do OneToOne com Candidato
    @OneToOne(mappedBy = "pedidoAfiliacao", fetch = FetchType.LAZY)
    private Candidato candidato;

    // Lado inverso do OneToOne com Voluntario
    @OneToOne(mappedBy = "pedidoAfiliacao", fetch = FetchType.LAZY)
    private Voluntario voluntario;

    // Um pedido passa por várias aprovações
    @OneToMany(mappedBy = "pedidoAfiliacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Aprovacao> aprovacoes;

    // Um pedido contém um termo de compromisso
    @OneToOne(mappedBy = "pedidoAfiliacao", cascade = CascadeType.ALL, orphanRemoval = true)
    private TermoDeCompromisso termoDeCompromisso;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public Voluntario getVoluntario() {
        return voluntario;
    }

    public void setVoluntario(Voluntario voluntario) {
        this.voluntario = voluntario;
    }

    public List<Aprovacao> getAprovacoes() {
        return aprovacoes;
    }

    public void setAprovacoes(List<Aprovacao> aprovacoes) {
        this.aprovacoes = aprovacoes;
    }

    public TermoDeCompromisso getTermoDeCompromisso() {
        return termoDeCompromisso;
    }

    public void setTermoDeCompromisso(TermoDeCompromisso termoDeCompromisso) {
        this.termoDeCompromisso = termoDeCompromisso;
    }
}
