# 🧠 FórumHub - API REST com Spring Boot

Projeto desenvolvido como parte de um desafio da Alura, com o objetivo de criar uma API REST para gerenciamento de tópicos de um fórum, com autenticação, autorização e boas práticas.

## 🚀 Tecnologias Utilizadas

- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Spring Security
- JWT (Auth0)
- H2 Database (ou outro configurado)
- Maven
- Insomnia (para testes)

---

## 📦 Funcionalidades da API

### 🔐 Autenticação
- Login com e-mail e senha
- Geração de token JWT ao autenticar
- Proteção dos endpoints com filtro de autenticação JWT
- Apenas usuários autenticados conseguem criar, atualizar ou excluir tópicos

### 📚 Tópicos
- `GET /topicos` - Lista todos os tópicos
- `POST /topicos` - Cria um novo tópico
- `GET /topicos/{id}` - Detalha um tópico específico
- `PUT /topicos/{id}` - Atualiza um tópico existente
- `DELETE /topicos/{id}` - Remove um tópico

### 👤 Usuários
- Validação e autenticação com base nos dados armazenados no banco
- Senha criptografada com BCrypt

---

## 🛡️ Segurança com JWT

### 🔓 Rota Pública
- `POST /login`  
  Recebe um JSON com `email` e `senha`, e retorna um token JWT.

### 🔒 Rotas Protegidas
- Todas as demais rotas exigem envio do token JWT no cabeçalho:
