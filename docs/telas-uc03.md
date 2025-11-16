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
    ^Rejeitado.^

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
    **Situação**: Rejeitado
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
