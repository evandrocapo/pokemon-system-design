# pokemon-system-design

# Desafio Técnico: Pokémon Manager

Este repositório contém a solução para o desafio técnico "Pokémon Manager", que consiste em duas APIs Spring Boot que se comunicam para gerenciar dados de Pokémons e as capturas de jogadores.

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

### 2.1 **Player Lambda**

- **Responsabilidade**: Cadastro de **Pokémons capturados por jogadores**.
- **Tecnologia**: Lambda com **Java** e banco de dados DynamoDB.
- **Funcionalidades**:
  - `POST /api/player/pokemons`: Cadastra um Pokémon capturado, validando o `id_pokemon_base` com a Admin API.

---

## 📝 Documentação da API

Você pode testar os endpoints utilizando o **Swagger UI**, que estará disponível em:

- **Admin API**: `http://localhost:8080/swagger-ui.html`
- **Player API**: `http://localhost:8081/swagger-ui.html`

Ou use ferramentas como o Postman para interagir com as APIs.
