@startchen


entity Pessoa {
    id_pessoa: INT <<key>>
    nome: VARCHAR
    tipo_pessoa: VARCHAR
}

entity Fisica {
    id_pessoa: INT <<key>>
    cpf: VARCHAR
    fk_id_juridica: INT
}

entity Juridica {
    id_pessoa: INT <<key>>
    cnpj: VARCHAR
}

relationship Representado_por {
}

Representado_por -(1,1)- Juridica
Representado_por -(1,N)- Fisica

Juridica ->- Pessoa 

entity Papel {
    id_papel: INT <<key>>
    tipo_papel: VARCHAR
}

entity Email {
    id_email: INT <<key>>
    endereco_email: VARCHAR
    fk_id_localizacao: INT
}

entity Localizacao {
    id_localizacao: INT <<key>>
}

entity Geografica {
    id_localizacao: INT <<key>>
    pais: VARCHAR
    estado: VARCHAR
    cidade: VARCHAR
    endereco: VARCHAR
    numero: INT
    fk_localizacao: INT
}

Geografica ->- Localizacao
Email ->- Localizacao


entity Habilidade {
    id_habilidade: INT <<key>>
    descricao: VARCHAR
}

entity Interesse {
    id_interesse: INT <<key>>
    descricao: VARCHAR
}

entity Perfil_Interesse {
    id_perfil: INT <<key>>
    id_interesse: INT <<key>>
}

entity Perfil {
    id_perfil: INT <<key>>
}   

relationship "Possui (Interesse)" as Possui_Interesse {

}

relationship "Possui (Perfil)" as Possui_Perfil {

}

Possui_Interesse -(1,1)- Interesse
Possui_Interesse -(1,N)- Perfil_Interesse

Possui_Perfil -(1,1)- Perfil
Possui_Perfil -(1,N)- Perfil_Interesse




@endchen