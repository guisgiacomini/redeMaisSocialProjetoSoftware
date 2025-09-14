# Telas UC002
---
### TELA 1 - Solicitar Afiliação
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
### TELA 2 - Formulário de identificação

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

### TELA 3 - Formulário de perfil, habilidades e interesses

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

### TELA 4 - Termos de compromisso

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

### TELA 5 - Validação pendente
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

### TELA 6  - Validação em análise

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
