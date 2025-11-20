# Rede Mais Social

## Cenário: Solicita Afiliação UC002

## Tecnologias Utilizadas
- Java
- Spring Boot
- Vue.js
- PostgreSQL

# Requisitos

- Possuir o Java na versão 21
- Maven (Para rodar o Spring Boot) na versão 3.9.9
- Node.js para o Vue.js
- Docker instalado

Rode o container com o PostgreSQL e o PGAdmin:

```bash
docker compose up -d
```

Verifique se o container está ativo:

```bash
docker ps
```

Rode o projeto Spring Boot:

Abra o diretório

```bash
cd ./rede-mais-social/
```
Rode os seguintes comandos maven:

```bash
mvn clean install
```

```bash
mvn spring-boot:run
```

O projeto estará rodando na porta 8080.

---

Rode o projeto Vue.js:

Abra o diretório

```bash
cd ./front-rede-mais-social
```

Baixe as dependências:

```bash
npm i
```

Rode o comando para iniciar o projeto front

```bash
npm run dev
```

Você pode acessar pela url:

http://localhost:5173


