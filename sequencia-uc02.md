```plantuml
@startuml
actor "Ator" as ator
participant "GUI" as gui <<fronteira>>
participant ":__afiliacaoController__" as controller <<controle>>
participant ":__Candidato__" as entityCandidato <<entidade>>
collections "Candidato" as candidato 
participant ":__PedidoAfiliacao__" as afiliacao <<entidade>>




ator -> gui: solicitaAfiliacao
activate gui
gui -> controller: solicitaAfiliacao(email, cpf)
activate controller
controller -> candidato : buscarPorEmailOuCPF(email, cpf)
activate candidato
candidato --> controller: naoEncontrado
deactivate candidato

controller --> afiliacao ** : <<create>>
deactivate controller 
deactivate gui

ator -> gui: validaDadosIdentificacao
activate gui
gui -> controller: validaDadosIdentificacao(nome, sexo, data_nascimento, nacionalidade, endereco, profissao) 
activate controller



@enduml