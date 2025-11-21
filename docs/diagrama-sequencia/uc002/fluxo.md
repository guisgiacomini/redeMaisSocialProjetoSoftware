```plantuml
@startuml
actor "Candidato" as ator
participant "GUI" as gui <<fronteira>>
participant ":AfiliacaoController" as controller <<controle>>
participant ":PedidoAfiliacao" as pedido <<entidade>>
participant ":Candidato" as candidato <<entidade>>

' Detalhamento do Perfil
participant ":Perfil" as perfil <<entidade>>
participant ":Habilidade" as habilidade <<entidade>>
participant ":Interesse" as interesse <<entidade>>

participant ":CondicaoTermo" as condicao <<entidade>>
participant ":AceiteTermo" as aceite <<entidade>>
participant ":EmailValidador" as emailValidador <<fronteira>> 

collections "Candidatos" as collectionCandidato <<coleção>>
collections "Voluntarios" as collectionVoluntario <<coleção>>
participant "Termo" as collectionTermos <<entidade>>

' --- 1. Início do Processo ---
ator -> gui: iniciarProcessoAfiliacao(email, cpf)
activate gui
gui -> controller: verificarElegibilidade(email, cpf)
activate controller

controller -> collectionCandidato: buscarPorEmailOuCPF(email, cpf)
activate collectionCandidato
collectionCandidato --> controller: null (naoEncontrado)
deactivate collectionCandidato

controller -> collectionVoluntario: buscarPorEmailOuCPF(email, cpf)
activate collectionVoluntario
collectionVoluntario --> controller: null (naoEncontrado)
deactivate collectionVoluntario

controller -> collectionCandidato: adicionar(candidato)
activate collectionCandidato
collectionCandidato --> controller: sucesso
deactivate collectionCandidato

controller -> pedido **: <<create>>
activate pedido
pedido --> controller: pedidoIniciado
deactivate pedido

controller --> gui: exibirFormularioIdentificacao()
deactivate controller
deactivate gui

' --- 2. Dados Pessoais e Criação do Candidato ---
ator -> gui: submeterDadosIdentificacao(nome, sexo, nascimento, ...)
activate gui
gui -> controller: registrarDadosPessoais(dadosIdentificacao)
activate controller

controller -> candidato ** : <<create>>(dadosIdentificacao)
activate candidato
candidato --> controller: candidatoInstanciado
deactivate candidato

controller -> pedido: vincularCandidato(candidato)
activate pedido
pedido --> controller: vinculado
deactivate pedido

controller --> gui: exibirFormularioPerfil()
deactivate controller
deactivate gui

' --- 3. Definição de Perfil, Habilidades e Interesses ---
ator -> gui: submeterPerfil(dadosPerfil, listaHabilidades, listaInteresses)
activate gui
gui -> controller: registrarPerfilCompleto(dadosPerfil, habilidades, interesses)
activate controller

controller -> candidato: definirPerfil(dadosPerfil, habilidades, interesses)
activate candidato

' 3.1 Cria o objeto Perfil principal
candidato -> perfil ** : <<create>>(dadosPerfil)

' 3.2 Loop para criar cada Habilidade da lista
loop para cada habilidade na lista
    candidato -> habilidade ** : <<create>>(nomeHabilidade)
end

' 3.3 Loop para criar cada Interesse da lista
loop para cada interesse na lista
    candidato -> interesse ** : <<create>>(nomeInteresse)
end

candidato --> controller: perfilDefinido
deactivate candidato

' --- 4. Preparação do Termo ---
controller -> collectionTermos: buscarTermoVigente()
activate collectionTermos
collectionTermos --> controller: condicao
deactivate collectionTermos

controller --> gui: exibirTermoCompromisso(condicao)
deactivate controller
deactivate gui

' --- 5. Aceite e Finalização ---
ator -> gui: aceitarTermoCompromisso()
activate gui
gui -> controller: registrarAceite()
activate controller

controller -> aceite **: <<create>>(candidato, condicao, dataHora, ip)
activate aceite
aceite --> controller: aceiteRegistrado
deactivate aceite

controller -> pedido: registrarAceite(aceite)
activate pedido
pedido -> pedido: atualizarStatus("Aguardando Validacao")
deactivate pedido

controller -> emailValidador: enviarLinkValidacao(candidato.email)
activate emailValidador
deactivate emailValidador

controller --> gui: exibirConfirmacaoEnvio()
deactivate controller
deactivate gui

@enduml