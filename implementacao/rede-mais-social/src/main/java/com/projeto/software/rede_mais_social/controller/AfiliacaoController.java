package com.projeto.software.rede_mais_social.controller;

import com.projeto.software.rede_mais_social.dto.AfiliacaoResponseDTO;
import com.projeto.software.rede_mais_social.dto.PedidoAfiliacaoDTO;
import com.projeto.software.rede_mais_social.repository.CandidatoRepository;
import com.projeto.software.rede_mais_social.repository.PedidoRepository;
import com.projeto.software.rede_mais_social.repository.VoluntarioRepository;
import org.springframework.http.ResponseEntity;
import com.projeto.software.rede_mais_social.dto.HabilidadesInteresses;
import com.projeto.software.rede_mais_social.entity.*;
import com.projeto.software.rede_mais_social.repository.TermoDeCompromissoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import java.time.LocalDate;

@RestController
@RequestMapping("/afiliacao")
public class AfiliacaoController {

    @Autowired
    private CandidatoRepository candidatoRepository;
    private VoluntarioRepository voluntarioRepository;
    private PedidoRepository pedidoRepository;
    private List<Candidato> candidatos;
    private TermoDeCompromisso termoDeCompromisso;

    @Autowired
    public AfiliacaoController(CandidatoRepository repository, TermoDeCompromissoRepository termoDeCompromissoRepository) {
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
    public List<String> registrarPerfilCompleto(@RequestBody HabilidadesInteresses habilidadesInteresses){

        Candidato candidato = candidatos.stream().filter(candidatoIteracao -> {

            List<Localizacao> localizacoes = candidatoIteracao.getEntidade().getLocalizacoes();
            if (localizacoes instanceof Email email) {
                return habilidadesInteresses.email().equals(email.getEmail());
            }
            return false;
        }).toList().get(0);

        Perfil perfil = candidato.definePerfil(habilidadesInteresses.habilidades(), habilidadesInteresses.interesses());

        perfil.setCandidato(candidato);
        candidato.setPerfil(perfil);

        candidatoRepository.save(candidato);

        List<String> condicaoTermos = termoDeCompromisso.buscarTermoVigente().stream().map(condicao -> condicao.getTexto()).toList();

        return condicaoTermos;
    }
}
