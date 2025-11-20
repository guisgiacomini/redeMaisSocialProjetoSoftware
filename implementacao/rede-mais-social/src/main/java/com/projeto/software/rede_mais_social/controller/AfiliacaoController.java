package com.projeto.software.rede_mais_social.controller;

import com.projeto.software.rede_mais_social.dto.HabilidadesInteresses;
import com.projeto.software.rede_mais_social.entity.*;
import com.projeto.software.rede_mais_social.repository.CandidatoRepository;
import com.projeto.software.rede_mais_social.repository.TermoDeCompromissoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/afiliacao")
public class AfiliacaoController {

    CandidatoRepository candidatoRepository;
    private List<Candidato> candidatos;
    private TermoDeCompromisso termoDeCompromisso;

    @Autowired
    public AfiliacaoController(CandidatoRepository repository, TermoDeCompromissoRepository termoDeCompromissoRepository) {
        this.termoDeCompromisso = termoDeCompromissoRepository.findAll().get(0);
        this.candidatoRepository = repository;
        this.candidatos = repository.findAll();
    }

    @PostMapping("/registrar-perfil-completo")
    public List<String> registrarPerfilCompleto(@RequestBody HabilidadesInteresses habilidadesInteresses){

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

        List<String> condicaoTermos = termoDeCompromisso.buscarTermoVigente().stream().map(condicao -> condicao.getTexto()).toList();

        return condicaoTermos;
    }
}
