# MovieFlix API

API RESTful para gerenciamento de catálogos de filmes, onde é possível cadastrar, listar, atualizar e deletar filmes, categorias e streamings. O projeto também conta com autenticação de usuário via JWT.

## 📝 Descrição

O MovieFlix é uma aplicação desenvolvida em Spring Boot que simula um catálogo de filmes. Ela permite a gestão completa de filmes, incluindo suas categorias e onde estão disponíveis para streaming. A API é protegida, e o acesso aos endpoints requer autenticação, com exceção dos endpoints de registro e login de usuários. A documentação da API é gerada automaticamente com o Swagger (OpenAPI).

## ✨ Funcionalidades

* **Autenticação**:
    * Registro de novos usuários.
    * Login com geração de token JWT.
* **Usuários**:
    * Listagem e busca de usuários por ID (requer autenticação).
* **Filmes**:
    * CRUD completo (Criar, Ler, Atualizar, Deletar).
    * Busca de filmes por categoria.
* **Categorias**:
    * CRUD completo (Criar, Ler, Atualizar, Deletar).
* **Streamings**:
    * CRUD completo (Criar, Ler, Atualizar, Deletar).

## 💻 Tecnologias Utilizadas

O projeto foi construído utilizando as seguintes tecnologias:

* **Backend**:
    * [Java 21](pom.xml)
    * [Spring Boot 3.5.4](pom.xml)
    * [Spring Web](pom.xml)
    * [Spring Security](pom.xml)
    * [Spring Data JPA](pom.xml)
* **Banco de Dados**:
    * [PostgreSQL](docker-compose.yml)
    * [Flyway](pom.xml) (para versionamento do banco de dados)
* **Autenticação**:
    * [JWT (Java JWT)](pom.xml)
* **Documentação**:
    * [SpringDoc (Swagger/OpenAPI)](src/main/java/br/com/movieflix/config/SwaggerConfig.java)
* **Build**:
    * [Apache Maven](pom.xml)
* **Containerização**:
    * [Docker](docker-compose.yml)

## 🚀 Como Executar o Projeto

Siga os passos abaixo para executar a aplicação localmente.

### Pré-requisitos

* [Java 21](https://www.oracle.com/java/technologies/downloads/#java21)
* [Apache Maven](https://maven.apache.org/download.cgi)
* [Docker](https://www.docker.com/products/docker-desktop/) e [Docker Compose](https://docs.docker.com/compose/install/)

### 1. Clonar o Repositório

```bash
git clone https://github.com/JoaoPedroCampanari/MovieFlix.git
cd MovieFlix
```

### 2. Iniciar o Banco de Dados com Docker

O projeto utiliza um banco de dados PostgreSQL, que pode ser iniciado facilmente com o Docker Compose. Na raiz do projeto, execute:

```bash
docker-compose up -d
```

Este comando irá iniciar um container com o PostgreSQL na porta `5433`, conforme configurado no arquivo `docker-compose.yml` e `application.yaml`.

### 3. Executar a Aplicação

Você pode executar a aplicação utilizando o Maven Wrapper incluído no projeto:

* No Linux ou macOS:
    ```bash
    ./mvnw spring-boot:run
    ```
* No Windows:
    ```bash
    ./mvnw.cmd spring-boot:run
    ```

A aplicação estará disponível em `http://localhost:8080`.

## 📖 Documentação da API (Swagger)

A documentação interativa da API está disponível via Swagger UI. Após iniciar a aplicação, acesse o seguinte URL no seu navegador:

* [http://localhost:8080/swagger/index.html](http://localhost:8080/swagger/index.html)

A partir da interface do Swagger, você pode visualizar todos os endpoints, seus parâmetros, e testá-los diretamente. Para endpoints que requerem autenticação, gere um token através do endpoint `/movieflix/auth/login` e utilize o botão "Authorize" para adicioná-lo no formato `Bearer {seu-token}`.

## 🔀 Endpoints da API

Aqui estão os principais endpoints disponíveis na API:

### Autenticação (`/movieflix/auth`)

* `POST /register`: Registra um novo usuário.
* `POST /login`: Realiza o login e retorna um token JWT.

### Filmes (`/movieflix/movie`)

* `GET /`: Lista todos os filmes.
* `GET /{id}`: Busca um filme por ID.
* `GET /search?category={categoryId}`: Busca filmes por ID de categoria.
* `POST /`: Cria um novo filme.
* `PUT /{id}`: Atualiza um filme existente.
* `DELETE /{id}`: Deleta um filme.

### Categorias (`/movieflix/category`)

* `GET /`: Lista todas as categorias.
* `GET /{id}`: Busca uma categoria por ID.
* `POST /`: Cria uma nova categoria.
* `DELETE /{id}`: Deleta uma categoria.

### Streamings (`/movieflix/streaming`)

* `GET /`: Lista todos os streamings.
* `GET /{id}`: Busca um streaming por ID.
* `POST /`: Cria um novo streaming.
* `DELETE /{id}`: Deleta um streaming.

---
**Observação**: Para os endpoints que necessitam de autenticação (`@SecurityRequirement(name = "bearerAuth")`), você precisa primeiro obter um token JWT através do endpoint de login e incluí-lo no cabeçalho `Authorization` de suas requisições como `Bearer <token>`.
