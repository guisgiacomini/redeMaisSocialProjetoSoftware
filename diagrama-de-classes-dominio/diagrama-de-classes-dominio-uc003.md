```plantuml
@startuml
class Candidato {
    -nome: String
    -cpf_cnpj: String
}

class ONG { 
    -nome: String
}

class Voluntário extends Candidato {

}

class Campanha {
    -nome: String
    -descricao: String
}

class Afiliacao {
    -dataDeAfiliacao: Date
}

class Perfil {

}

class Habilidade {
    -descricao: String
}

class Interesse {
    -descricao: String
}

class Representante {
    
}

class Email{
    -endereco: String
}

class Recomendacao {

}

class Aprovacao {
    -data: Date
    -status: String
    -motivo: String
}

Perfil "1..*" -- "1..*" Habilidade: contém
Perfil "1..*" -- "1..*" Interesse: possui

Candidato "1" -- "1" Afiliacao: possui

Voluntário "1" -- "1" Perfil: possui
Voluntário "1" -- "1" Email: possui

ONG "1" *-- "0..*" Campanha: abre
Campanha "1..*" -- "1..*" Perfil: contém

Representante "1" -- "0..*" Afiliacao: avalia
Representante "1" -- "1..*" Aprovacao: solicita
Representante "1" -- "1..*" Perfil: especifica
Representante "1" -- "1..*" Recomendacao: solicita

Recomendacao "0..*" -- "0..*" ONG: apresenta



@enduml