# 🔐 Spring Security Study Project

> Projeto de estudos focado na implementação de autenticação e autorização com **Spring Security** e **JWT**, utilizando boas práticas do ecossistema Spring Boot.

---

## 📋 Sobre o Projeto

Este projeto foi desenvolvido com o objetivo de estudar e praticar os principais conceitos de segurança em aplicações Java com Spring Boot. Aborda desde a configuração básica do Spring Security até a implementação de autenticação stateless com tokens JWT.

---

## 🚀 Tecnologias Utilizadas

| Tecnologia | Versão | Descrição |
|---|---|---|
| **Java** | 17 | Linguagem principal |
| **Spring Boot** | 4.0.5 | Framework base da aplicação |
| **Spring Security** | (via Spring Boot) | Autenticação e autorização |
| **Spring Data JPA** | (via Spring Boot) | Persistência de dados com ORM |
| **Spring Web MVC** | (via Spring Boot) | Camada REST / controllers |
| **Spring Validation** | (via Spring Boot) | Validação de DTOs e entidades |
| **Auth0 Java JWT** | 4.4.0 | Geração e validação de tokens JWT |
| **Flyway** | (via Spring Boot) | Migrations e versionamento do banco de dados |
| **PostgreSQL** | (via Spring Boot) | Banco de dados relacional |
| **Lombok** | (via Spring Boot) | Redução de boilerplate com annotations |
| **Maven** | (Maven Wrapper) | Gerenciamento de dependências e build |

---

## 🛡️ Conceitos Praticados

- **Autenticação com JWT (JSON Web Token):** geração, assinatura e validação de tokens para autenticação stateless
- **Autorização baseada em roles:** controle de acesso por perfil de usuário utilizando Spring Security
- **Security Filter Chain:** configuração customizada da cadeia de filtros do Spring Security
- **Filters customizados:** implementação de filtros para interceptar requisições e validar tokens JWT
- **Gerenciamento de usuários com JPA:** persistência de usuários e roles no banco de dados
- **Migrations com Flyway:** versionamento controlado do schema do banco de dados
- **Validação de entrada de dados:** uso de Bean Validation para garantir integridade nas requisições
- **Arquitetura em camadas:** separação clara entre Controller, Service e Repository

---

## ⚙️ Pré-requisitos

Antes de executar o projeto, certifique-se de ter instalado:

- [Java 17+](https://adoptium.net/)
- [Maven 3.8+](https://maven.apache.org/) (ou use o Maven Wrapper incluído)
- [PostgreSQL](https://www.postgresql.org/) em execução

---

## 🗄️ Configuração do Banco de Dados

Crie um banco de dados PostgreSQL e configure as credenciais no arquivo `src/main/resources/application.properties` (ou `application.yml`):

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/seu_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=validate
```

> O Flyway irá executar as migrations automaticamente na inicialização da aplicação.

---

## ▶️ Como Executar

Clone o repositório:

```bash
git clone https://github.com/ebpacheco/spring-security.git
cd spring-security
```

Execute com o Maven Wrapper:

```bash
# Linux / macOS
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

Ou gere o JAR e execute:

```bash
./mvnw clean package
java -jar target/security-0.0.1-SNAPSHOT.jar
```

A aplicação estará disponível em `http://localhost:8080`.

---

## 🔑 Fluxo de Autenticação

```
POST /auth/login
  → Valida credenciais
  → Gera token JWT assinado
  → Retorna token ao cliente

GET /rota-protegida
  Authorization: Bearer <token>
  → Filtro intercepta a requisição
  → Valida e decodifica o JWT
  → Autentica o usuário no SecurityContext
  → Acesso liberado
```

---

## 📁 Estrutura do Projeto

```
src/
├── main/
│   ├── java/dev/eduardo/security/
│   │   ├── config/          # Configurações do Spring Security
│   │   ├── controller/      # Endpoints REST
│   │   ├── domain/          # Entidades e modelos de domínio
│   │   ├── dto/             # Data Transfer Objects
│   │   ├── filter/          # Filtros de autenticação JWT
│   │   ├── repository/      # Interfaces JPA
│   │   └── service/         # Regras de negócio e geração de token
│   └── resources/
│       ├── db/migration/    # Scripts SQL do Flyway
│       └── application.properties
└── test/
    └── java/dev/eduardo/security/
```

---

## 📚 Referências

- [Spring Security Reference](https://docs.spring.io/spring-security/reference/index.html)
- [Auth0 Java JWT](https://github.com/auth0/java-jwt)
- [Flyway Documentation](https://flywaydb.org/documentation/)
- [Spring Boot Reference](https://docs.spring.io/spring-boot/docs/current/reference/html/)

---

## 👨‍💻 Autor

Desenvolvido por **Eduardo Pacheco** como projeto de estudos.

[![GitHub](https://img.shields.io/badge/GitHub-ebpacheco-181717?style=flat&logo=github)](https://github.com/ebpacheco)
