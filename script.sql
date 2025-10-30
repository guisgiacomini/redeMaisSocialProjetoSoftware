-- REDE MAIS SOCIAL - Script SQL

CREATE TABLE Pessoa (
    id_pessoa SERIAL PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    tipo_pessoa VARCHAR(10) CHECK (tipo_pessoa IN ('FISICA','JURIDICA'))
);

CREATE TABLE Fisica (
    id_pessoa INT PRIMARY KEY REFERENCES Pessoa(id_pessoa) ON DELETE CASCADE,
    cpf CHAR(11) UNIQUE NOT NULL
);

CREATE TABLE Juridica (
    id_pessoa INT PRIMARY KEY REFERENCES Pessoa(id_pessoa) ON DELETE CASCADE,
    cnpj CHAR(14) UNIQUE NOT NULL
);

CREATE TABLE Email (
    id_email SERIAL PRIMARY KEY,
    endereco VARCHAR(120) UNIQUE NOT NULL
);

CREATE TABLE Perfil (
    id_perfil SERIAL PRIMARY KEY
);

CREATE TABLE Habilidade (
    id_habilidade SERIAL PRIMARY KEY,
    descricao VARCHAR(100) NOT NULL
);

CREATE TABLE Interesse (
    id_interesse SERIAL PRIMARY KEY,
    descricao VARCHAR(100) NOT NULL
);

CREATE TABLE Perfil_Habilidade (
    id_perfil INT REFERENCES Perfil(id_perfil) ON DELETE CASCADE,
    id_habilidade INT REFERENCES Habilidade(id_habilidade) ON DELETE CASCADE,
    PRIMARY KEY (id_perfil, id_habilidade)
);

CREATE TABLE Perfil_Interesse (
    id_perfil INT REFERENCES Perfil(id_perfil) ON DELETE CASCADE,
    id_interesse INT REFERENCES Interesse(id_interesse) ON DELETE CASCADE,
    PRIMARY KEY (id_perfil, id_interesse)
);

CREATE TABLE Papel (
    id_papel SERIAL PRIMARY KEY,
    tipo_papel VARCHAR(20) CHECK (tipo_papel IN ('CANDIDATO','VOLUNTARIO'))
);

CREATE TABLE Entidade (
    id_entidade SERIAL PRIMARY KEY,
    id_pessoa INT REFERENCES Pessoa(id_pessoa),
    id_papel INT REFERENCES Papel(id_papel),
    id_perfil INT REFERENCES Perfil(id_perfil),
    id_email INT REFERENCES Email(id_email)
);


-- Domínio da Afiliação

CREATE TABLE TermoDeCompromisso (
    id_termo SERIAL PRIMARY KEY,
    texto TEXT NOT NULL
);

CREATE TABLE CertidaoCNPJ (
    id_certidao SERIAL PRIMARY KEY,
    nomeArquivo VARCHAR(150),
    tipo VARCHAR(50)
);

CREATE TABLE Afiliacao (
    id_afiliacao SERIAL PRIMARY KEY,
    dataDeAfiliacao DATE DEFAULT CURRENT_DATE
);

CREATE TABLE Candidato (
    id_papel INT PRIMARY KEY REFERENCES Papel(id_papel) ON DELETE CASCADE,
    id_afiliacao INT REFERENCES Afiliacao(id_afiliacao),
    id_termo INT REFERENCES TermoDeCompromisso(id_termo)
);

CREATE TABLE Candidato_Certidao (
    id_papel INT REFERENCES Candidato(id_papel),
    id_certidao INT REFERENCES CertidaoCNPJ(id_certidao),
    PRIMARY KEY (id_papel, id_certidao)
);

CREATE TABLE Voluntario (
    id_papel INT PRIMARY KEY REFERENCES Papel(id_papel),
    id_afiliacao INT REFERENCES Afiliacao(id_afiliacao)
);


-- Aprovação e Representante

CREATE TABLE Representante (
    id_representante SERIAL PRIMARY KEY,
    nome VARCHAR(120),
    email VARCHAR(120)
);

CREATE TABLE Aprovacao (
    id_aprovacao SERIAL PRIMARY KEY,
    data DATE,
    status VARCHAR(20),
    motivo TEXT,
    id_afiliacao INT REFERENCES Afiliacao(id_afiliacao),
    id_representante INT REFERENCES Representante(id_representante)
);

-- ONG e Campanhas

CREATE TABLE ONG (
    id_ong SERIAL PRIMARY KEY,
    nome VARCHAR(120) NOT NULL
);

CREATE TABLE Campanha (
    id_campanha SERIAL PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    descricao TEXT,
    id_ong INT REFERENCES ONG(id_ong)
);

CREATE TABLE Campanha_Perfil (
    id_campanha INT REFERENCES Campanha(id_campanha) ON DELETE CASCADE,
    id_perfil INT REFERENCES Perfil(id_perfil) ON DELETE CASCADE,
    PRIMARY KEY (id_campanha, id_perfil)
);

CREATE TABLE Recomendacao (
    id_recomendacao SERIAL PRIMARY KEY,
    id_representante INT REFERENCES Representante(id_representante)
);

CREATE TABLE Recomendacao_ONG (
    id_recomendacao INT REFERENCES Recomendacao(id_recomendacao) ON DELETE CASCADE,
    id_ong INT REFERENCES ONG(id_ong) ON DELETE CASCADE,
    PRIMARY KEY (id_recomendacao, id_ong)
);
