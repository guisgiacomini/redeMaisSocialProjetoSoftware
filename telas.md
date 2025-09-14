# Telas UC002
---
### Fluxo principal

1. Candidato informa seu email e CPF (CNPJ, se for pessoa jurídica) e solicita afiliação

```plantuml
@startsalt
scale 1.5
{
  title "Solicitar Afiliação"
  "E-mail " 
  "CPF (ou CNPJ) "
  ^Tipo de Pessoa^Pessoa Física^Pessoa Jurídia^
  .
  .
  [Solicitar Afiliação]
}
@endsalt
```
4. Candidato preenche formulário de identificação e submete

```plantuml
@startsalt
scale 1.5
{
  title "Formulário de Afiliação - Identificação"
  "Nome Completo " 
  ^Sexo^Masculino^Feminino^Outro^
  .
  .
  .
  "Data de Nascimento " 
  "Nacionalidade " 
  "Endereço Residencial " 
  "Endereço Comercial" 
  "Profissão " 
  [Continuar]
}
@endsalt


```

7. Candidato preenche o formulário e submete

```plantuml
@startsalt
scale 1.5
{
  title "Formulário de Interesses"
  "Habilidades" 
  "Áreas de Interesse" 
  [Submeter]
}
@endsalt

```

10. Candidato aceita as diretrizes estabelecidas no termo de aceite

```plantuml
@startsalt
scale 1.5
{
  title "Termo de Compromisso"
  {
    <b>Termos e Condições da Rede Mais Social</b>
    {SI
        Lorem ipsum dolor sit amet, consectetur adipiscing elit.
        Sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
        Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
        Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.
        Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
    }
  }
  [ ] Li e aceito as diretrizes
  [Aceitar e Finalizar]
}
@endsalt

```

13. Sistema exibe mensagem solicitando a validação do e-mail informado
```plantuml
@startsalt
scale 1.5
{
  title "Validação Pendente"
  {
    <b>Validação pendente</b>
    Um e-mail de validação foi enviado para o endereço informado.
    Por favor, acesse seu e-mail e clique no link para continuar o processo.
  }
  [Ok]
}
@endsalt
```

16. Sistema informa a situação do candidato que está esperando a aprovação de sua afiliação pela Rede Mais Social e deve receber liberação em breve

```plantuml
@startsalt
scale 1.5
{
    title "Afiliação em Análise"
  {
    <b>Solicitação Recebida</b>
    Seu cadastro foi validado com sucesso e agora está aguardando a aprovação da equipe da Rede Mais Social.
    Você receberá uma notificação em breve.
  }
}
@endsalt
```

## Fluxos Alternativos

Email ou CPF(ou CNPJ) encontrados 

```plantuml 

@startsalt
scale 1.5
{
  title "Aviso - Cadastro Existente"
  {
    <b>Atenção!</b>
    O E-mail ou CPF/CNPJ informado já está cadastrado em nossa base de dados.
    Se você já é um voluntário, entre em contato com o suporte.
  }
  [Voltar para a Página Inicial]
}
@endsalt

```

Dados inválidos ou não informados

```plantuml 

@startsalt
scale 1.5
{
  title "Formulário de Afiliação - Identificação"
  "Nome Completo " |<color:red> Campo obrigatório
  ^Sexo^Masculino^Feminino^Outro^|<color:red> Campo obrigatório
  .
  .
  .
  "Data de Nascimento " |<color:red> Campo obrigatório
  "Nacionalidade " |<color:red> Campo obrigatório
  "Endereço Residencial " |<color:red> Campo obrigatório
  "Endereço Comercial" |<color:red> Campo obrigatório
  "Profissão " |<color:red> Campo obrigatório
  [Continuar]
}
@endsalt
```

Candidato anexa certidões e preenche formulário de identificação do representante legal e submete

```plantuml
@startsalt
scale 1.5
{+
  <b>Dados do Representante Legal</b>
  "Nome Completo" 
  "E-mail " 
  "CPF "
  .
  {+ 
    Anexar certidões 
  }

  [Continuar]
}
@endsalt
```

Candidato não aceita diretrizes

```plantuml
@startsalt
scale 1.5
{
  {+
  <b> Você não aceitou os termos e diretrizes
  .
  [Retornar à página inicial]
  }
}
@endsalt
```

 Candidato Não acessa Link de Validação no prazo
 ```plantuml
@startsalt
scale 1.5
{
  {+
  <b> Você ainda não validou seu E-mail
  .
  [Enviar link de validação]
  }
}
@endsalt
```



# Telas UC003

### Fluxo Principal
2. Sistema apresenta relação de candidatos pendentes de aprovação: 
```plantuml
@startsalt
scale 1.5
{+
    .
    title Aguardando aprovação:
    .  
    <&person> Pietro Santana dos Santos |  [Ver perfil] 
    <&person> Ana Lima Kirstp | [Ver perfil] 
    <&person> Paula Vieira Toronto | [Ver perfil] 
    <&person> Luís Rocha da Silva | [Ver perfil] 
    .
}
@endsalt
```
4. Sistema exibe informações detalhadas do candidato
```plantuml
@startsalt
scale 1.25
{+
    .
    **Candidato**: Pietro Santana dos Santos
    **Data de nascimento**: 17/12/1999 | **Nacionalidade**: Brasileiro
    **Sexo**: Masculino
    **Profissão**: Gastrônomo | **Habilidades**: 
    **Interesses**: | - Proatividade. 
    - Ajudar pessoas com habilidades de cozinha. .| - Comunicação.
    - Ensinar para as pessoas sobre técnicas de gastronomia.  | 
    - Experiência com Confeitaria.

    **Situação**: 
    ^Aguardando aprovação.^

    [Salvar]
    .
}
@endsalt
```

6. Sistema muda situação do candidato a Voluntário para aprovado e armazena

```plantuml
@startsalt
scale 1.25
{+
    .
    **Candidato**: Pietro Santana dos Santos
    **Data de nascimento**: 17/12/1999 | **Nacionalidade**: Brasileiro
    **Sexo**: Masculino
    **Profissão**: Gastrônomo | **Habilidades**: 
    **Interesses**: | - Proatividade. 
    - Ajudar pessoas com habilidades de cozinha. .| - Comunicação.
    - Ensinar para as pessoas sobre técnicas de gastronomia.  | 
    - Experiência com Confeitaria.

    **Situação**: 
    ^Aprovado.^

    [Salvar] | 

}
@endsalt
```

7. Sistema exibe perfil, habilidades e interesses do voluntário.
```plantuml
@startsalt
scale 1.5
{+
     
    .
    **Voluntário**: Pietro Santana dos Santos
    **Data de nascimento**: 17/12/1999 | **Nacionalidade**: Brasileiro
    **Sexo**: Masculino
    **Profissão**: Gastrônomo | **Habilidades**: 
    **Interesses**: | - Proatividade. 
    - Ensinar.| - Comunicação.
    - Cozinhar.  | 
    - Experiência com Confeitaria.
    . Gastronomia.
    
    [Solicitar ONGs]
    .

}
@endsalt
```

9. Sistema busca ONG que combina com perfil do voluntário na base e exibe relação de ONG e campanhas para o voluntário

```plantuml
@startsalt
scale 1.25
{+ 
    title ONGs recomendadas
    .
    **Características:** 
    - Ensino. 
    - Cozinha.
    - Gastronomia. 
    - Ensino gastronomia.
    .
    **ONGs:**
    .

    **ONG** | **Características**
    //Saber e sabor.// | Cozinha.
    //Culinária Cidadã.// | Gastronomia.
    //Artesanato da Alimentação//. | Ensino gastronomia
    .
    [Aprovar]

}
@endsalt
```

15. Sistema exibe mensagem de aprovação realizada com sucesso na tela 

```plantuml
@startsalt
scale 1.5
{+
     
    .
    <color:green>Aprovação realizada com sucesso.

    [Voltar para candidatos pendentes]
    .

}
@endsalt
```

16. Sistema apresenta a relação de candidatos pendentes de aprovação e volta para o Passo 3.
```plantuml
@startsalt
scale 1.5
{+
    .
    title Aguardando aprovação:
    .  
    <&person> Ana Lima Kirstp | [Ver perfil] 
    <&person> Paula Vieira Toronto | [Ver perfil] 
    <&person> Luís Rocha da Silva | [Ver perfil] 
    .
}
@endsalt
```

### Fluxos alternativos

#### Não há candidatos para aprovação - Passo 2
```plantuml
@startsalt
scale 1.5
{+
    .
    title Aguardando aprovação:
    .  
    //Não há candidatos para aprovação//
    [Voltar]
    .
}
@endsalt
```

#### Candidato reprovado - Passo 5

6. Sistema muda situação do candidato a Voluntário para “Rejeitado” e armazena
```plantuml
@startsalt
scale 1.25
{+
    .
    **Candidato**: Pietro Santana dos Santos
    **Data de nascimento**: 17/12/1999 | **Nacionalidade**: Brasileiro
    **Sexo**: Masculino
    **Profissão**: Gastrônomo | **Habilidades**: 
    **Interesses**: | - Proatividade. 
    - Ajudar pessoas com habilidades de cozinha. .| - Comunicação.
    - Ensinar para as pessoas sobre técnicas de gastronomia.  | 
    - Experiência com Confeitaria.

    **Situação**: 
    ^Reprovado.^

    [Salvar]
    .
}
@endsalt
```

7. Sistema exibe campo para relatar motivo da Rejeição
```plantuml
@startsalt
scale 1.5
{+
    .
    **Candidato**: Pietro Santana dos Santos
    **Situação**: Reprovado
    .
    **Motivo da rejeição:**
    {+
        " //<color:gray>Digite aqui o motivo da rejeição//  "    
    }
    
    [Salvar]
    .
}
@endsalt
```

11. Sistema exibe mensagem de rejeição realizada com sucesso na tela
```plantuml
@startsalt
scale 1.5
{+
     
    .
    <color:green>Rejeição realizada com sucesso.
    .
    [Voltar para candidatos pendentes]
    .

}
@endsalt
```

12. Sistema apresenta a relação de candidatos pendentes de aprovação e volta para o Passo 3
```plantuml
@startsalt
scale 1.5
{+
    .
    title Aguardando aprovação:
    .  
    <&person> Ana Lima Kirstp | [Ver perfil] 
    <&person> Paula Vieira Toronto | [Ver perfil] 
    <&person> Luís Rocha da Silva | [Ver perfil] 
    .
}
@endsalt
```

#### Não existem ONGs para recomendar - Passo 9

```plantuml
@startsalt
scale 1.5
{+ 
title "ONGs recomendadas"
    .
    **Características:** 
    - Ensino. 
    - Cozinha.
    - Gastronomia. 
    - Ensino gastronomia.
    .
    **ONGs:**
    .

    **ONG** | **Características**
    //Sem ONGs para recomendar//|
    .
    [Aprovar]

}
@endsalt
```

13. Sistema exibe mensagem de aprovação realizada com sucesso na tela 

```plantuml
@startsalt
scale 1.5
{+
     
    .
    <color:green>Aprovação realizada com sucesso.

    [Voltar para candidatos pendentes]
    .

}
@endsalt
```
