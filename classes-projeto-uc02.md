```plantuml
@startuml
title Diagrama de Classes de Projeto – Fluxo de Solicitação de Afiliação


class GUI <<fronteira>> {
    +solicitaAfiliacao(email, cpf)
    +exibirFormularioIdentificacao()
    +exibirFormularioPerfilHabilidades()
    +exibirTermoCompromisso()
}

class AfiliacaoController <<controle>> {
    +solicitaAfiliacao(email, cpf)
    +validarDadosIdentificacao(dados)
    +validarPerfil(perfil, habilidades)
    +registrarAceite()
}

class Candidato <<entidade>> {
    -nome: String
    -sexo: Character
    -dataNascimento: DateTime
    -nacionalidade: String
    -endereco: String
    -profissao: String
    -perfil: Perfil
    -situacao: String
    +criarCandidato(dados)
    +armazenarPerfil(perfil, habilidades)
    +atualizarSituacao(status)
}

class PedidoAfiliacao <<entidade>> {
    -status: String
    -data: Date
    +criarPedidoAfiliacao()
    +associarCandidato(candidato)
}

class EmailValidador <<entidade>> {
    +enviarLinkValidacao(email)
}

class CandidatoCollection <<entidade>> {
    +buscarPorEmailOuCPF(email, cpf)
}


GUI "1" -- "1" AfiliacaoController : aciona
AfiliacaoController "1" -- "1" CandidatoCollection : consulta
AfiliacaoController "1" -- "1" PedidoAfiliacao : cria/associa
AfiliacaoController "1" --"1" Candidato : cria/atualiza
AfiliacaoController "1" -- "1" EmailValidador : aciona
Candidato "1" -- "1" PedidoAfiliacao : está associado a

@enduml

