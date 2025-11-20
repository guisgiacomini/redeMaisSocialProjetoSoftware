package com.projeto.software.rede_mais_social.controller;

import com.projeto.software.rede_mais_social.dto.AfiliacaoResponseDTO;
import com.projeto.software.rede_mais_social.dto.PedidoAfiliacaoDTO;
import com.projeto.software.rede_mais_social.repository.CandidatoRepository;
import com.projeto.software.rede_mais_social.repository.PedidoRepository;
import com.projeto.software.rede_mais_social.repository.VoluntarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.software.rede_mais_social.entity.PedidoAfiliacao;

import java.time.LocalDate;

@RestController
@RequestMapping("/afiliacao")
public class AfiliacaoController {

    @Autowired
    private CandidatoRepository candidatoRepository;
    private VoluntarioRepository voluntarioRepository;
    private PedidoRepository pedidoRepository;

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
}
