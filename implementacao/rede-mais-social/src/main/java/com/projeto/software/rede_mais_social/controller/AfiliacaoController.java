package com.projeto.software.rede_mais_social.controller;

import com.projeto.software.rede_mais_social.dto.AfiliacaoResponseDTO;
import com.projeto.software.rede_mais_social.dto.PedidoAfiliacaoDTO;
import com.projeto.software.rede_mais_social.repository.*;
import org.springframework.http.ResponseEntity;
import com.projeto.software.rede_mais_social.dto.HabilidadesInteresses;
import com.projeto.software.rede_mais_social.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    private List<Candidato> candidatos;
    private TermoDeCompromisso termoDeCompromisso;

    @Autowired
    public AfiliacaoController(CandidatoRepository repository, VoluntarioRepository voluntarioRepository, CondicaoTermoRepository condicaoTermoRepository, PedidoRepository pedidoRepository, TermoDeCompromissoRepository termoDeCompromissoRepository) {
        this.voluntarioRepository = voluntarioRepository;
        this.condicaoTermoRepository = condicaoTermoRepository;
        this.pedidoRepository = pedidoRepository;
        this.termoDeCompromisso = termoDeCompromissoRepository.findAll().get(0);
        this.candidatoRepository = repository;
        this.candidatos = repository.findAll();
    }
    

    @PostMapping("/iniciar-afiliacao")
    public ResponseEntity<?> iniciarProcessoAfiliacao(@RequestBody PedidoAfiliacaoDTO dto) {

        // 1. Verificar Elegibilidade (Diagrama: controller -> collectionCandidato)
        boolean candidatoExiste = candidatoRepository.existsByCpfOrEmail(dto.getDocumento(), dto.getEmail());
        if (candidatoExiste) {
            return ResponseEntity.badRequest()
                    .body(new AfiliacaoResponseDTO(null, "ERRO", "Candidato já encontrado na base de dados."));
        }

        // 2. Verificar Voluntários (Diagrama: controller -> collectionVoluntario)
        boolean voluntarioExiste = voluntarioRepository.existsByCpfOrEmail(dto.getDocumento(), dto.getEmail());
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

        List<String> condicaoTermos = termoDeCompromisso.buscarTermoVigente().stream()
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
