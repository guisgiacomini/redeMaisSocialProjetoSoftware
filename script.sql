-- REDE MAIS SOCIAL - SCRIPT SQL COMPLETO

-- ======= PESSOA / PAPEL / ENTIDADE ========

CREATE TABLE Pessoa (
    id_pessoa SERIAL PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    tipo_pessoa VARCHAR(10) CHECK (tipo_pessoa IN ('FISICA','JURIDICA'))
);

CREATE TABLE Fisica (
    id_pessoa INT PRIMARY KEY,
    cpf CHAR(11) UNIQUE NOT NULL,
    id_juridica INT,
    CONSTRAINT fk_fisica_pessoa FOREIGN KEY (id_pessoa)
        REFERENCES Pessoa(id_pessoa) ON DELETE CASCADE,
    CONSTRAINT fk_fisica_juridica FOREIGN KEY (id_juridica)
        REFERENCES Pessoa(id_pessoa)
);

CREATE TABLE Juridica (
    id_pessoa INT PRIMARY KEY,
    cnpj CHAR(14) UNIQUE NOT NULL,
    CONSTRAINT fk_juridica_pessoa FOREIGN KEY (id_pessoa)
        REFERENCES Pessoa(id_pessoa) ON DELETE CASCADE
);

CREATE TABLE Papel (
    id_papel SERIAL PRIMARY KEY,
    tipo_papel VARCHAR(20) CHECK (tipo_papel IN ('CANDIDATO','VOLUNTARIO'))
);

CREATE TABLE Email (
    id_email SERIAL PRIMARY KEY,
    enderecoEmail VARCHAR(120) UNIQUE NOT NULL
);

-- ======= LOCALIZAÇÃO ========

CREATE TABLE Localizacao (
    id_localizacao SERIAL PRIMARY KEY
);

CREATE TABLE Geografica (
    id_localizacao INT PRIMARY KEY,
    pais VARCHAR(60),
    estado VARCHAR(60),
    cidade VARCHAR(60),
    endereco VARCHAR(120),
    complemento VARCHAR(80),
    numero VARCHAR(20),
    CONSTRAINT fk_geo_localizacao FOREIGN KEY (id_localizacao)
        REFERENCES Localizacao(id_localizacao) ON DELETE CASCADE
);

ALTER TABLE Email
    ADD COLUMN id_localizacao INT,
    ADD CONSTRAINT fk_email_localizacao FOREIGN KEY (id_localizacao)
        REFERENCES Localizacao(id_localizacao) ON DELETE CASCADE;

-- ======= PERFIL / HABILIDADE / INTERESSE ========

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
    id_perfil INT,
    id_habilidade INT,
    PRIMARY KEY (id_perfil, id_habilidade),
    CONSTRAINT fk_ph_perfil FOREIGN KEY (id_perfil)
        REFERENCES Perfil(id_perfil) ON DELETE CASCADE,
    CONSTRAINT fk_ph_habilidade FOREIGN KEY (id_habilidade)
        REFERENCES Habilidade(id_habilidade) ON DELETE CASCADE
);

CREATE TABLE Perfil_Interesse (
    id_perfil INT,
    id_interesse INT,
    PRIMARY KEY (id_perfil, id_interesse),
    CONSTRAINT fk_pi_perfil FOREIGN KEY (id_perfil)
        REFERENCES Perfil(id_perfil) ON DELETE CASCADE,
    CONSTRAINT fk_pi_interesse FOREIGN KEY (id_interesse)
        REFERENCES Interesse(id_interesse) ON DELETE CASCADE
);

-- ======= ENTIDADE ========

CREATE TABLE Entidade (
    id_entidade SERIAL PRIMARY KEY,
    id_pessoa INT,
    id_papel INT,
    id_perfil INT,
    id_email INT,
    CONSTRAINT fk_entidade_pessoa FOREIGN KEY (id_pessoa)
        REFERENCES Pessoa(id_pessoa),
    CONSTRAINT fk_entidade_papel FOREIGN KEY (id_papel)
        REFERENCES Papel(id_papel),
    CONSTRAINT fk_entidade_perfil FOREIGN KEY (id_perfil)
        REFERENCES Perfil(id_perfil),
    CONSTRAINT fk_entidade_email FOREIGN KEY (id_email)
        REFERENCES Email(id_email)
);

CREATE TABLE Entidade_Localizacao (
    id_entidade INT,
    id_localizacao INT,
    PRIMARY KEY (id_entidade, id_localizacao),
    CONSTRAINT fk_el_entidade FOREIGN KEY (id_entidade)
        REFERENCES Entidade(id_entidade) ON DELETE CASCADE,
    CONSTRAINT fk_el_localizacao FOREIGN KEY (id_localizacao)
        REFERENCES Localizacao(id_localizacao) ON DELETE CASCADE
);

-- ======= AFILIAÇÃO ========

CREATE TABLE PedidoAfiliacao (
    id_pedido SERIAL PRIMARY KEY,
    status VARCHAR(30),
    data DATE DEFAULT CURRENT_DATE
);

CREATE TABLE CertidaoCNPJ (
    id_certidao SERIAL PRIMARY KEY,
    nomeArquivo VARCHAR(150),
    tipo VARCHAR(50)
);

CREATE TABLE TermoDeCompromisso (
    id_termo SERIAL PRIMARY KEY
);

CREATE TABLE CondicaoTermo (
    id_condicao SERIAL PRIMARY KEY,
    texto TEXT NOT NULL,
    id_termo INT,
    CONSTRAINT fk_condicao_termo FOREIGN KEY (id_termo)
        REFERENCES TermoDeCompromisso(id_termo) ON DELETE CASCADE
);

CREATE TABLE Aceite (
    id_aceite SERIAL PRIMARY KEY,
    data DATE DEFAULT CURRENT_DATE
);

CREATE TABLE ItemAceite (
    id_item SERIAL PRIMARY KEY,
    id_aceite INT,
    id_condicao INT,
    CONSTRAINT fk_item_aceite FOREIGN KEY (id_aceite)
        REFERENCES Aceite(id_aceite) ON DELETE CASCADE,
    CONSTRAINT fk_item_condicao FOREIGN KEY (id_condicao)
        REFERENCES CondicaoTermo(id_condicao) ON DELETE CASCADE
);

CREATE TABLE Candidato (
    id_papel INT PRIMARY KEY,
    id_pedido INT,
    CONSTRAINT fk_candidato_papel FOREIGN KEY (id_papel)
        REFERENCES Papel(id_papel) ON DELETE CASCADE,
    CONSTRAINT fk_candidato_pedido FOREIGN KEY (id_pedido)
        REFERENCES PedidoAfiliacao(id_pedido)
);

CREATE TABLE Candidato_Certidao (
    id_papel INT,
    id_certidao INT,
    PRIMARY KEY (id_papel, id_certidao),
    CONSTRAINT fk_cc_candidato FOREIGN KEY (id_papel)
        REFERENCES Candidato(id_papel),
    CONSTRAINT fk_cc_certidao FOREIGN KEY (id_certidao)
        REFERENCES CertidaoCNPJ(id_certidao)
);

CREATE TABLE Voluntario (
    id_papel INT PRIMARY KEY,
    id_pedido INT,
    CONSTRAINT fk_voluntario_papel FOREIGN KEY (id_papel)
        REFERENCES Papel(id_papel),
    CONSTRAINT fk_voluntario_pedido FOREIGN KEY (id_pedido)
        REFERENCES PedidoAfiliacao(id_pedido)
);

-- ======= REPRESENTANTE / APROVAÇÃO ========

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
    id_pedido INT,
    id_representante INT,
    CONSTRAINT fk_aprovacao_pedido FOREIGN KEY (id_pedido)
        REFERENCES PedidoAfiliacao(id_pedido),
    CONSTRAINT fk_aprovacao_representante FOREIGN KEY (id_representante)
        REFERENCES Representante(id_representante)
);

-- ======= ONG / CAMPANHA / RECOMENDAÇÃO ========

CREATE TABLE ONG (
    id_ong SERIAL PRIMARY KEY,
    nome VARCHAR(120) NOT NULL
);

CREATE TABLE Campanha (
    id_campanha SERIAL PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    descricao TEXT,
    id_ong INT,
    CONSTRAINT fk_campanha_ong FOREIGN KEY (id_ong)
        REFERENCES ONG(id_ong)
);

CREATE TABLE Campanha_Perfil (
    id_campanha INT,
    id_perfil INT,
    PRIMARY KEY (id_campanha, id_perfil),
    CONSTRAINT fk_cp_campanha FOREIGN KEY (id_campanha)
        REFERENCES Campanha(id_campanha) ON DELETE CASCADE,
    CONSTRAINT fk_cp_perfil FOREIGN KEY (id_perfil)
        REFERENCES Perfil(id_perfil) ON DELETE CASCADE
);

CREATE TABLE Recomendacao (
    id_recomendacao SERIAL PRIMARY KEY,
    id_representante INT,
    CONSTRAINT fk_recomendacao_representante FOREIGN KEY (id_representante)
        REFERENCES Representante(id_representante)
);

CREATE TABLE Recomendacao_ONG (
    id_recomendacao INT,
    id_ong INT,
    PRIMARY KEY (id_recomendacao, id_ong),
    CONSTRAINT fk_ro_recomendacao FOREIGN KEY (id_recomendacao)
        REFERENCES Recomendacao(id_recomendacao) ON DELETE CASCADE,
    CONSTRAINT fk_ro_ong FOREIGN KEY (id_ong)
        REFERENCES ONG(id_ong) ON DELETE CASCADE
);
