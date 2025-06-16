# API Gateway - Spring Cloud Gateway

Este projeto atua como o ponto de entrada da arquitetura de microsserviços, utilizando **Spring Cloud Gateway**.

## 🌐 Funcionalidades

- Roteamento dinâmico com base em URI.
- Integração com Eureka para descoberta de serviços.
- Validação de segurança com JWT (Auth Server externo).
- Suporte a filtros de autenticação e logging.

## ⚠️ Restrições

- **Não deve expor o `auth-server`.**
- Deve validar tokens emitidos pelo Auth Server.
- A segurança deve ser configurada com filtros globais ou por rota.

## 🧭 Requisitos

- Java 17+
- Spring Boot 3.1+
- Spring Cloud Gateway
- Maven 3.9+
