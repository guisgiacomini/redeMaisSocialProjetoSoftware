```plantuml
@startuml

title Diagrama de Classes 

skinparam classAttributeIconSize 0
hide empty members

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

class Habilidade {
 -descricao: String
}

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

class CertidaoCNPJ {
 -tipo: String
 -arquivo: byte[]
}

class Email {
 -endereco: String
}



Candidato "1" -- "1" Email : "possui"


Candidato "1" -- "1" Afiliacao : "solicita"


Afiliacao "1" -- "1" Validacao : "requer"
Afiliacao "1" -- "1" Aprovacao : "passa por"


Voluntario "1" -- "1" Perfil : "possui"


Perfil "1" *-- "0..*" Habilidade
Perfil "1" *-- "0..*" Interesse


ONG "1" -- "1..*" CertidaoCNPJ : "anexa"

@enduml