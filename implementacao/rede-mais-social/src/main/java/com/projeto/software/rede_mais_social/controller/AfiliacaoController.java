package com.projeto.software.rede_mais_social.controller;

import com.projeto.software.rede_mais_social.dto.AfiliacaoResponseDTO;
import com.projeto.software.rede_mais_social.dto.PedidoAfiliacaoDTO;
import com.projeto.software.rede_mais_social.repository.*;
import org.springframework.http.ResponseEntity;
import com.projeto.software.rede_mais_social.dto.HabilidadesInteresses;
import com.projeto.software.rede_mais_social.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import java.time.LocalDate;

@RestController
@RequestMapping("/afiliacao")
public class AfiliacaoController {

    private CandidatoRepository candidatoRepository;
    private VoluntarioRepository voluntarioRepository;
    private CondicaoTermoRepository condicaoTermoRepository;
    private PedidoRepository pedidoRepository;
    private PessoaRepository pessoaRepository;
    private TermoDeCompromissoRepository termoDeCompromissoRepository;
    private List<Candidato> candidatos;
    private List<Voluntario> voluntarios;
    private TermoDeCompromisso termoDeCompromisso;

    @Autowired
    public AfiliacaoController(CandidatoRepository repository, VoluntarioRepository voluntarioRepository, CondicaoTermoRepository condicaoTermoRepository, PedidoRepository pedidoRepository, TermoDeCompromissoRepository termoDeCompromissoRepository) {
        this.voluntarioRepository = voluntarioRepository;
        this.condicaoTermoRepository = condicaoTermoRepository;
        this.pedidoRepository = pedidoRepository;
        this.candidatoRepository = repository;
        this.candidatos = repository.findAll();
    }
    

    @PostMapping("/iniciar-afiliacao")
    public ResponseEntity<?> iniciarProcessoAfiliacao(@RequestBody PedidoAfiliacaoDTO dto) {

        // 1. Verificar Elegibilidade (Diagrama: controller -> collectionCandidato)
        boolean candidatoExiste = candidatos.stream().anyMatch(candidatoIteracao -> {
            Entidade entidade = candidatoIteracao.getEntidade();

            if (entidade == null) return false;

            // A. Verifica Documento (CPF ou CNPJ) nas subclasses de Entidade
            if (entidade instanceof Fisica && dto.getDocumento().equals(((Fisica) entidade).getCpf())) {
                return true;
            }
            if (entidade instanceof Juridica && dto.getDocumento().equals(((Juridica) entidade).getCnpj())) {
                return true;
            }

            // B. Verifica E-mail (Iterando sobre as Localizações da Entidade)
            List<Localizacao> localizacoes = entidade.getLocalizacoes();
            if (localizacoes != null) {
                for (Localizacao localizacao : localizacoes) {
                    // Verifica se a localização é um Email
                    if (localizacao instanceof Email) {
                        Email emailEntidade = (Email) localizacao;
                        if (emailEntidade.getEmail() != null &&
                                emailEntidade.getEmail().equalsIgnoreCase(dto.getEmail())) {
                            return true;
                        }
                    }
                }
            }
            return false;
        });

        if (candidatoExiste) {
            return ResponseEntity.badRequest()
                    .body(new AfiliacaoResponseDTO(null, "ERRO", "Candidato já encontrado na base de dados."));
        }

        // 2. Verificar Voluntários (Diagrama: controller -> collectionVoluntario)
        boolean voluntarioExiste = voluntarios.stream().anyMatch(voluntarioIteracao -> {
            Entidade entidade = voluntarioIteracao.getEntidade();

            if (entidade == null) return false;

            // A. Verifica Documento (CPF ou CNPJ) nas subclasses de Entidade
            if (entidade instanceof Fisica && dto.getDocumento().equals(((Fisica) entidade).getCpf())) {
                return true;
            }
            if (entidade instanceof Juridica && dto.getDocumento().equals(((Juridica) entidade).getCnpj())) {
                return true;
            }

            // B. Verifica E-mail (Iterando sobre as Localizações da Entidade)
            List<Localizacao> localizacoes = entidade.getLocalizacoes();
            if (localizacoes != null) {
                for (Localizacao localizacao : localizacoes) {
                    // Verifica se a localização é um Email
                    if (localizacao instanceof Email) {
                        Email emailEntidade = (Email) localizacao;
                        if (emailEntidade.getEmail() != null &&
                                emailEntidade.getEmail().equalsIgnoreCase(dto.getEmail())) {
                            return true;
                        }
                    }
                }
            }
            return false;
        });
        if (voluntarioExiste) {
            return ResponseEntity.badRequest()
                    .body(new AfiliacaoResponseDTO(null, "ERRO", "Voluntário já encontrado na base de dados."));
        }

        // 3. Criar Pedido (Diagrama: controller -> pedido **: <<create>>)
        PedidoAfiliacao pedido = new PedidoAfiliacao();
        pedido.setData(LocalDate.now());
        pedido.setStatus("INICIADO");

        pedidoRepository.save(pedido);

        // 4. Retorno (O ID é essencial para a GUI continuar o fluxo)
        return ResponseEntity.ok(new AfiliacaoResponseDTO(
                pedido.getId(),
                "INICIADO",
                "Processo iniciado com sucesso. Prossiga para identificação."
        )); 
    }

    @PostMapping("/submeter-dados-identificacao")
    public ResponseEntity<?> submeterDadosIdentificacao(@RequestBody PedidoAfiliacaoDTO dto) {
        if (dto.getPedidoId() == null) return ResponseEntity.badRequest().build();

        PedidoAfiliacao pedido = pedidoRepository.findById(dto.getPedidoId())
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        Pessoa pessoa;
        if ("FISICA".equalsIgnoreCase(dto.getTipoPessoa())) {
            Fisica fisica = new Fisica();
            fisica.setCpf(dto.getDocumento());
            pessoa = fisica;
        } else {
            Juridica juridica = new Juridica();
            juridica.setCnpj(dto.getDocumento());
            pessoa = juridica;
        }
        pessoa.setNome(dto.getNome());

        List<Localizacao> localizacoes = new ArrayList<>();

        // 1. Criar Email (agora é uma Localizacao)
        Email email = new Email();
        email.setEmail(dto.getEmail());
        email.setEntidade(pessoa); // Vincula à pessoa (que é Entidade)
        localizacoes.add(email);

        // 2. Criar Endereço (Geografica)
        Geografica endereco = new Geografica();
        endereco.setEndereco(dto.getEnderecoResidencial());
        endereco.setCidade(dto.getEnderecoComercial());
        endereco.setEntidade(pessoa); // Vincula à pessoa
        localizacoes.add(endereco);

        // Adiciona tudo à lista herdada de Entidade
        pessoa.setLocalizacoes(localizacoes);

        // Salva Pessoa (cascade salva Localizacoes: Email e Geografica)
        pessoaRepository.save(pessoa);

        Candidato candidato = new Candidato();
        candidato.setEntidade(pessoa);
        candidato.setEmail(dto.getEmail());
        candidatoRepository.save(candidato);

        pedido.setCandidato(candidato);
        candidato.setPedidoAfiliacao(pedido);
        pedidoRepository.save(pedido);

        return ResponseEntity.ok(new AfiliacaoResponseDTO(pedido.getId(), "DADOS_REGISTRADOS", "Sucesso."));
    }
   

    @PostMapping("/registrar-perfil-completo")
    public ResponseEntity<List<String>> registrarPerfilCompleto(@RequestBody HabilidadesInteresses habilidadesInteresses){

        Candidato candidato = candidatos.stream().filter(candidatoIteracao -> {

            List<Localizacao> localizacoes = candidatoIteracao.getEntidade().getLocalizacoes();
            for (Localizacao localizacao: localizacoes){
                if (localizacao instanceof Email email) {
                    return habilidadesInteresses.email().equals(email.getEmail());
                }
            }
            return false;
        }).toList().get(0);

        Perfil perfil = candidato.definePerfil(habilidadesInteresses.habilidades(), habilidadesInteresses.interesses());

        perfil.setCandidato(candidato);
        candidato.setPerfil(perfil);

        candidatoRepository.save(candidato);

        List<String> condicaoTermos = termoDeCompromissoRepository.findAll().get(0).buscarTermoVigente().stream()
                .map(condicao -> condicao.getTexto()).toList();

        return ResponseEntity.ok(condicaoTermos);
    }

    @PostMapping("/registar-aceite/{idCandidato}/{idPedido}")
    public ResponseEntity<?> registrarAceite(@PathVariable Integer idCandidato, @RequestBody String condicao, @PathVariable Integer idPedido){
        Candidato candidato = candidatoRepository.findById(idCandidato).get();
        Optional<CondicaoTermo> condicaoTermo = condicaoTermoRepository.findByTexto(condicao);
        if (condicaoTermo.isEmpty()){
            return ResponseEntity.badRequest().body("Condição não encontrada");
        }
        CondicaoTermo condicaoDoTermo = condicaoTermo.get();
        Aceite aceite = new Aceite();
        aceite.setData(LocalDate.now());

        ItemAceite itemAceite = new ItemAceite();
        itemAceite.setTexto(condicao);
        itemAceite.setAceite(aceite);
        aceite.getItens().add(itemAceite);

        Optional<PedidoAfiliacao> pedido = pedidoRepository.findById(idPedido);
        if (pedido.isEmpty()){
            return ResponseEntity.badRequest().body("Pedido Afiliacao não encontrado.");
        }

        PedidoAfiliacao pedidoAfiliacao = pedido.get();
        pedidoAfiliacao.getTermoDeCompromisso().setAceite(aceite);
        pedidoAfiliacao.setStatus("Aguardando Validação.");
        candidato.setPedidoAfiliacao(pedidoAfiliacao);

        candidato = candidatoRepository.save(candidato);

        return ResponseEntity.ok(candidato.getEmail());
    }
}
