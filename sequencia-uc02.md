```plantuml
@startuml
actor "Candidato" as ator
participant "GUI" as gui <<fronteira>>
participant ":AfiliacaoController" as controller <<controle>>
participant ":Candidato" as candidato <<entidade>>
participant ":PedidoAfiliacao" as afiliacao <<entidade>>
participant ":EmailValidador" as emailValidador <<entidade>>
participant "Perfil" as perfil <<entidade>>
participant "Habilidade" as habilidade <<entidade>>
participant "Interesse" as interesse <<entidade>>
participant "TermoDeCompromisso" as termo <<entidade>>
participant "Afilicacao" as afiliacaoent <<entidade>>

collections "Candidatos" as collectionCandidato <<Collection>>


ator -> gui: solicitaAfiliacao(email, cpf)
activate gui
gui -> controller: solicitaAfiliacao(email, cpf)
activate controller

controller -> collectionCandidato: buscarPorEmailOuCPF(email, cpf)
activate collectionCandidato
collectionCandidato --> controller: naoEncontrado
deactivate collectionCandidato

controller -> afiliacao **: criarPedidoAfiliacao()
activate afiliacao
afiliacao -> afiliacaoent **: criarAfiliacao()
activate afiliacaoent
afiliacaoent --> afiliacao: afiliacao
deactivate afiliacaoent
afiliacao --> controller: pedido
deactivate afiliacao
controller --> candidato **: <<create>>

controller -> afiliacao: associarCandidato(candidato)
activate afiliacao
afiliacao --> controller: candidatoAssociado
deactivate afiliacao

' activate candidato
' candidato --> controller: candidatoCriado
' deactivate candidato

controller --> gui: exibirFormularioIdentificacao()
deactivate controller
deactivate gui

' --- Preenchimento e validação dos dados ---
ator -> gui: informaDadosIdentificacao(nome, sexo, data_nascimento, nacionalidade, endereco, profissao)
activate gui
gui -> controller: validarDadosIdentificacao(dados)
activate controller


controller -> gui: exibirFormularioPerfilHabilidades()
deactivate controller
deactivate gui

' --- Preenchimento do perfil ---
ator -> gui: informaPerfilEInteresses(perfil, habilidades)
activate gui
gui -> controller: validarPerfil(perfil, habilidades)
activate controller

controller -> candidato: armazenarPerfil(perfil, habilidades)
activate candidato
candidato --> controller: perfilArmazenado
deactivate candidato

controller -> gui: exibirTermoCompromisso()
deactivate controller
deactivate gui

' --- Aceite do termo ---
ator -> gui: aceitaTermo()
activate gui
gui -> controller: registrarAceite()
activate controller

controller -> candidato: atualizarSituacao("Aguardando Validação")
controller -> emailValidador **: enviarLinkValidacao(email)
deactivate controller
deactivate gui

@enduml