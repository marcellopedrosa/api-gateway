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

## Configurações do SCOPE_admin_gateway no Keycloak

1. Criar um Client Scope

![image](https://github.com/user-attachments/assets/ab5b33f3-06a7-411b-8f19-5a4be1a482fc)

2. Configure um novo mapper

![image](https://github.com/user-attachments/assets/b399da75-e822-4e5b-b057-a1768162e6cc)

3. Selecione a opção abaixo para criar um Custom Scope

![image](https://github.com/user-attachments/assets/dd98d070-99b7-488c-9d9e-48f36fe33727)

4. Preencha dessa forma para ficar igual ao que foi mapeado na configuração do gateway no [Spring Security](https://github.com/marcellopedrosa/api-gateway/blob/main/src/main/java/br/com/csc/api_gateway/configuration/SecurityConfig.java) 

![image](https://github.com/user-attachments/assets/9709cfd4-589e-4635-af38-13c973b11335)

5. Acesse seu client (backend ou frontend) > Aba "Client scopes" > Clique em "Add client scope" > Selecione "admin_gateway" > Selecione Add opção "Default" > Done! 

![image](https://github.com/user-attachments/assets/c3242216-287c-4bdd-b13e-722132779e4b)

6. Autentique-se no Keycloak > Acesse jwt.io > Verifique se o payload está como mostra a imagem:

![image](https://github.com/user-attachments/assets/c6cd6d8b-2ddc-427e-b3f1-1439154b53c8)

