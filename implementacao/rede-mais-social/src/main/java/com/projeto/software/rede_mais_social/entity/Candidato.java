package com.projeto.software.rede_mais_social.entity;

import com.projeto.software.rede_mais_social.repository.CandidatoRepository;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Candidato extends Papel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true, nullable = true) // Nulo se for JURIDICA
    private String cpf;

    @Column(unique = true, nullable = true) // Nulo se for FISICA
    private String cnpj;

    // Um candidato tem um pedido
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_afiliacao_id")
    private PedidoAfiliacao pedidoAfiliacao;

    @OneToOne(mappedBy = "candidato", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Perfil perfil;

    // --- Construtor Vazio (exigido pelo JPA) ---
    public Candidato() {
        super();
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


    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Perfil definePerfil(List<String> habilidades, List<String> interesses){
        Perfil perfilDefinido = new Perfil();

        Set<Habilidade> listaHabilidades = new HashSet<>();

        for (String habilidade: habilidades){
            Habilidade habilidadeNova = new Habilidade();
            habilidadeNova.setDescricao(habilidade);
            listaHabilidades.add(habilidadeNova);
        }

        Set<Interesse> listaInteresses = new HashSet<>();

        for (String interesse: interesses){
            Interesse interesseNovo = new Interesse();
            interesseNovo.setDescricao(interesse);
            listaInteresses.add(interesseNovo);
        }
        perfilDefinido.setHabilidades(listaHabilidades);
        perfilDefinido.setInteresses(listaInteresses);

        return perfilDefinido;
    }
}