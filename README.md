# 🧾 Relatório Técnico – Projeto Trading Bot com Java Spring Boot

## 1. Descrição Geral do Projeto

Este projeto é uma aplicação Java utilizando Spring Boot que permite a interação de usuários com a API da Binance. Os usuários podem cadastrar informações de acesso à exchange, consultar preços de criptomoedas de interesse, e realizar operações de compra e venda via ordens de mercado (market orders).

A aplicação conta com funcionalidades REST completas e persistência de dados com Spring Data JPA, além de integração direta com a API da Binance por meio da biblioteca oficial `binance-connector-java`.

---

## 2. Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.4.4**
- **Spring Web & Spring Data JPA**
- **MySQL**
- **Lombok**
- **Jackson (para serialização JSON)**
- **Binance Connector Java (v3.4.1)**
- **Springdoc OpenAPI (para documentação da API)**

---

## 3. Arquitetura do Projeto

A arquitetura segue o padrão MVC (Model-View-Controller), com camadas bem definidas:

### a. Modelos (Model)

Representam as entidades persistidas no banco de dados:

- `User`: contém as credenciais da Binance, login, senha, saldo inicial e relacionamentos com configurações, tickers e ordens.
- `UserConfiguration`: configurações de trading, como percentual de perda, lucro e quantidade por ordem.
- `UserOrderReport`: registra cada operação de compra/venda realizada pelo usuário.
- `UserTrackingTicker`: lista de criptomoedas acompanhadas pelo usuário.

### b. Controladores (Controller)

Endpoints REST que manipulam requisições:

- `UserController`: cria usuários, associa configurações e tickers.
- `OrderController`: envia ordens de compra ou venda na Binance.
- `TickerController`: retorna o último preço dos ativos acompanhados.

### c. Repositórios (Repository)

Interfaces que estendem `JpaRepository` para comunicação com o banco:

- `UserRepository`
- `UserConfigurationRepository`
- `UserOrderReportRepository`
- `UserTrackingTickerRepository`

### d. Serviço de Integração (Service)

- `BinanceIntegration`: encapsula chamadas à API da Binance, como criação de ordens e recuperação de preços de tickers.

---

## 4. Fluxo de Funcionamento

### a. Cadastro de Usuário

- **Endpoint:** `POST /users`
- O usuário fornece dados de login, senha e chaves da Binance.
- Os dados são persistidos via JPA.

### b. Associação de Configurações e Tickers

- `POST /users/{id}/configuration`
- `POST /users/{id}/tracking-ticker`
- Permite que cada usuário tenha múltiplas configurações e tickers de interesse.

### c. Consulta de Preços

- **Endpoint:** `GET /{id}/tickers`
- Retorna os preços atuais de cada ticker seguido pelo usuário.

### d. Envio de Ordens

- **Endpoint:** `POST /{id}/order`
- Cria uma ordem de mercado na Binance (BUY ou SELL).
- Ao comprar, registra a operação no banco.
- Ao vender, busca a compra correspondente e atualiza com o preço de venda.

---

## 5. Segurança e Tratamento de Erros

- Utiliza `ResponseEntity` para retornar status HTTP apropriados.
- Exceções são capturadas em `try/catch`, com retorno `400 Bad Request` em falhas.
- Chaves da Binance são passadas por usuário e não hardcoded.

---

## 6. Execução e Build

- A aplicação pode ser iniciada via `TradingBotApplication.java`.
- Uso do `spring-boot-maven-plugin` para empacotamento.
- `application.properties` (não fornecido) deve conter configuração de conexão com banco de dados MySQL.

---

## 7. Possíveis Melhorias

- Implementar autenticação JWT para segurança nas requisições.
- Validação de dados com `@Valid` e `@NotNull`.
- Melhorar tratamento de exceções e logs para facilitar debugging.
- Criar testes unitários e de integração.
- Armazenar variáveis sensíveis como API Keys de forma segura (por exemplo, Hashicorp Vault ou Secrets Manager).

---
## 8. Url da api rodando na nuvem com swagger
 - https://tradebot-cjbef9c8e9ebcwhz.canadacentral-01.azurewebsites.net/swagger-ui/index.html
