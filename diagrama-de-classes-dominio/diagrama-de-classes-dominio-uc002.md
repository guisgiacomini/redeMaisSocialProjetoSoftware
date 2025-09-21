```plantuml
@startuml
' Título do Diagrama
title Diagrama de Classes - Afiliação (Baseado na lista de classes fornecida)

' Configurações de Aparência
skinparam classAttributeIconSize 0
hide empty members

' --- Classes da Lista ---

class Candidato {
  -nome: String
  -cpf_cnpj: String
}

class Voluntario extends Candidato {
}

class ONG extends Candidato {
}

class Afiliacao {
 -dataSolicitacao: Date
}

class Perfil {
}

' Usando nome singular para a classe, representando a entidade Habilidade
class Habilidade {
 -descricao: String
}

' Usando nome singular para a classe, representando a entidade Interesse
class Interesse {
 -descricao: String
}

class Validacao {
 -data: Date
 -status: String
 -link: String
}

class Aprovacao {
 -data: Date
 -status: String
 -motivo: String
}

' Nome da classe simplificado para "Certidao" para legibilidade
class "Certidoes negativas de probidade\nadministrativa e ineligibilidade" as Certidao {
 -tipo: String
 -arquivo: byte[]
}

class Email {
 -endereco: String
}


' --- Relacionamentos ---

' Um Candidato pode ser um Voluntario ou uma ONG
' (Herança já definida com 'extends')

' Um Candidato tem um E-mail
Candidato "1" -- "1" Email : "possui"

' Um Candidato solicita uma Afiliação
Candidato "1" -- "1" Afiliacao : "solicita"

' A Afiliação passa por Validação e Aprovação
Afiliacao "1" -- "1" Validacao : "requer"
Afiliacao "1" -- "1" Aprovacao : "passa por"

' Apenas Voluntários possuem um Perfil
Voluntario "1" -- "1" Perfil : "possui"

' O Perfil é composto por Habilidades e Interesses
' O documento menciona um formulário com Habilidades e Interesses 
Perfil "1" *-- "0..*" Habilidade
Perfil "1" *-- "0..*" Interesse

' ONGs devem entregar certidões, conforme a regra RN5 
' Voluntários PJ também, conforme RN4 
ONG "1" -- "1..*" Certidao : "anexa"

note right of ONG
  Voluntários do tipo Pessoa Jurídica
  também anexam certidões, mas para
  simplificar o diagrama, a relação
  foi feita diretamente com a ONG.
end note

note bottom of Aprovacao
  A classe 'Aprovacao' representa o processo
  descrito no Caso de Uso UC003[cite: 75, 76],
  incluindo o resultado (aprovado/rejeitado).
end note

note bottom of Validacao
  A classe 'Validacao' representa a etapa de
  validação do e-mail, que possui um link
  válido por 24 horas, conforme a regra RN2.
end note

@enduml