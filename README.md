# pokemon-system-design

# Desafio Técnico: Pokémon Manager

Este repositório contém a solução para o desafio técnico "Pokémon Manager", que consiste em duas APIs Spring Boot que se comunicam para gerenciar dados de Pokémons e as capturas de jogadores.

## Cenário
Você foi contratado para desenvolver o sistema de backend para um novo jogo de Pokémon. Esse sistema é dividido em dois sistemas: uma para a equipe de administração gerenciar os dados base dos Pokémons, e outra para os jogadores registrarem suas capturas.

### Requisitos Funcionais (RFs)
#### 1. Pokedex-API (Para Professores)

RF-01: O sistema deve permitir que um administrador (Professor) cadastre um novo Pokémon, fornecendo os atributos: nome, tipo, habilidades e url_imagem.

RF-02: O sistema deve permitir que um administrador visualize todos os Pokémons cadastrados na base.

RF-03: O sistema deve permitir que um administrador consulte os detalhes de um Pokémon específico por seu id.

RF-04: O sistema deve permitir que um administrador atualize os dados de um Pokémon existente.

RF-05: O sistema deve permitir que um administrador exclua um Pokémon da base de dados.

RF-06: O acesso a esta API deve ser restrito a usuários autenticados com permissão de administrador (Professor).

#### 2. Computer-API (Para Treinadores)

RF-07: O sistema deve permitir que um jogador (Treinador) registre a captura de um Pokémon, fornecendo um id_jogador, o id_pokemon_base (referência à Pokedex-API), apelido e level.

RF-08: O sistema deve validar a existência do id_pokemon_base na Pokedex-API antes de registrar a captura.

RF-09: O sistema deve permitir que um jogador visualize todos os Pokémons que ele capturou.

RF-10: O sistema deve permitir que um jogador atualize os detalhes (e.g., apelido) de um Pokémon capturado.

RF-11: O sistema deve permitir que um jogador exclua um Pokémon capturado de sua coleção.

RF-12: O sistema deve ser capaz de notificar o jogador sobre o status final do processamento da sua captura.

### Requisitos Não Funcionais (RNFs)
#### 1. Desempenho e Latência

RNF-01: A resposta inicial da Computer-API (API Gateway) para uma requisição de cadastro de Pokémon deve ser retornada em menos de 100ms para 99% das requisições, garantindo uma resposta rápida para o usuário, mesmo em horários de pico.

RNF-02: A latência de leitura (busca de Pokémons de um jogador) na Computer-API deve ser inferior a 200ms para 95% das requisições.

#### 2. Disponibilidade e Resiliência

RNF-03: A Computer-API deve ter uma disponibilidade de 99.99% durante o período de maior uso do sistema, entre 12:00 e 16:00.

RNF-04: A Computer-API deve ser capaz de operar e processar requisições de captura (utilizando dados em cache) mesmo que a Pokedex-API esteja indisponível por um período de até 15 minutos.

RNF-05: A arquitetura da Computer-API deve ser resiliente a falhas no processamento de mensagens, utilizando uma fila (SQS) e uma Dead Letter Queue para reprocessamento.

---

## 🚀 Tecnologias Utilizadas

- **Java 17+**: Linguagem de programação.
- **Spring Boot 3+**: Framework para construção de aplicações Java.
- **Spring Data JPA**: Abstração para persistência de dados em banco SQL.
- **SQL Database**: Banco de dados SQL Oracle.
- **DynamoDB**: Banco de dados AWS
- **Maven**: Ferramenta de gerenciamento de dependências.

---

## ⚙️ Arquitetura e Desenho da Solução

O sistema é composto por duas APIs RESTful, cada uma com sua responsabilidade e banco de dados isolado. A comunicação entre elas é crucial para a consistência dos dados.

### 1. **Admin API**

- **Responsabilidade**: Gerenciamento do **CRUD de Pokémons base**.
- **Tecnologia**: Aplicação Spring Boot com **Spring Data JPA** e banco de dados Oracle.
- **Funcionalidades**:
  - `GET /api/pokemons`: Lista todos os Pokémons.
  - `POST /api/pokemons`: Cria um novo Pokémon (requer autenticação).
  - `GET /api/pokemons/{id}`: Busca um Pokémon por ID.
  - `PUT /api/pokemons/{id}`: Atualiza um Pokémon.
  - `DELETE /api/pokemons/{id}`: Deleta um Pokémon.
- **URL Base**: `http://localhost:8080` (padrão do Spring Boot).

### 2. **Player API**

- **Responsabilidade**: Gerenciamento do **CRUD de Pokémons capturados por jogadores**.
- **Tecnologia**: Aplicação Spring Boot com **Spring Data JPA** e banco de dados DynamoDB.
- **Funcionalidades**:
  - `GET /api/player/pokemons/`: Lista os Pokémons capturados do treinador.
  - `GET /api/player/pokemons/{id_jogador}/{id}`: Busca um Pokémon capturado por ID.
- **URL Base**: `http://localhost:8081` (porta configurada para evitar conflitos).

<img width="949" height="1111" alt="Arquitetura Principal" src="https://github.com/evandrocapo/pokemon-system-design/blob/main/images/Arquitetura%20Principal.png" />

### 2.1 **Player Lambda**

- **Responsabilidade**: Cadastro de **Pokémons capturados por jogadores**.
- **Tecnologia**: Lambda com **Java** e banco de dados DynamoDB.
- **Funcionalidades**:
  - `POST /api/player/pokemons`: Cadastra um Pokémon capturado, validando o `id_pokemon_base` com a Admin API.
<img width="2464" height="1477" alt="Lambda Save Pokemon" src="https://github.com/evandrocapo/pokemon-system-design/blob/main/images/Lambda%20Save%20Pokemon.png" />

---

## 📝 Documentação da API

Você pode testar os endpoints utilizando o **Swagger UI**, que estará disponível em:

- **Admin API**: `http://localhost:8080/swagger-ui.html`
- **Player API**: `http://localhost:8081/swagger-ui.html`

Ou use ferramentas como o Postman para interagir com as APIs.
