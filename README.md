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

- Java 21+
- Spring Boot 3.1+
- Spring Cloud Gateway
- Maven 3.9+

## Configurando Keycloak como Auth Server

https://github.com/marcellopedrosa/api-gateway-with-keycloak/

# Rotas Dinâmicas no Spring Cloud Gateway

Este documento apresenta duas abordagens para implementar rotas dinâmicas no Spring Cloud Gateway, permitindo que microserviços consumam APIs externas através do gateway sem necessidade de reinicialização.

## 🎯 Objetivo

Permitir que um microserviço consuma rotas externas passando pelo API Gateway, com capacidade de:
- Criar rotas dinamicamente via API REST
- Manter persistência das configurações
- Garantir zero downtime durante atualizações
- Suportar alta performance e escalabilidade

## 🏗️ Abordagens Disponíveis

### 1. 🗄️ **Abordagem com Banco de Dados**

**Conceito**: Armazena as definições de rotas em um banco de dados relacional, permitindo CRUD completo via APIs REST. As rotas são carregadas na inicialização e mantidas em cache para performance otimizada.

**Como funciona**:
- Rotas são persistidas em tabelas do banco de dados
- Gateway carrega rotas na inicialização
- APIs REST permitem criar/editar/remover rotas em tempo real
- Cache em memória otimiza a consulta das rotas
- Refresh automático sincroniza mudanças sem restart

**Arquitetura**:
```
[Microserviço] → [API REST] → [Banco de Dados] → [Gateway] → [API Externa]
                      ↓              ↓
                [Cache Memory] → [Route Refresh]
```

### 2. ⚙️ **Abordagem com Config Server**

**Conceito**: Utiliza o Spring Cloud Config Server para centralizar as configurações de rotas em repositórios Git, aproveitando versionamento e profiles para diferentes ambientes.

**Como funciona**:
- Rotas são definidas em arquivos YAML/Properties no Git
- Config Server serve as configurações para o Gateway
- Mudanças no Git podem ser aplicadas via refresh endpoints
- Suporte nativo a profiles (dev, staging, prod)
- Versionamento e rollback através do Git

**Arquitetura**:
```
[Git Repository] → [Config Server] → [Gateway] → [API Externa]
       ↓                 ↓              ↓
[Version Control] → [Refresh Scope] → [Route Updates]
```

---

# Comparação: Banco de Dados vs Config Server para Rotas Dinâmicas

## 📊 Tabela Comparativa Geral

| Critério | Banco de Dados | Config Server |
|----------|----------------|---------------|
| **Performance** | ⭐⭐⭐⭐⭐ | ⭐⭐⭐ |
| **Complexidade** | ⭐⭐⭐ | ⭐⭐⭐⭐⭐ |
| **Escalabilidade** | ⭐⭐⭐⭐⭐ | ⭐⭐⭐ |
| **Manutenibilidade** | ⭐⭐⭐⭐ | ⭐⭐ |
| **Disponibilidade** | ⭐⭐⭐⭐⭐ | ⭐⭐⭐ |
| **Flexibilidade** | ⭐⭐⭐⭐⭐ | ⭐⭐⭐ |

---

## 🗄️ BANCO DE DADOS

### ✅ **VANTAGENS**

| Categoria | Descrição | Impacto |
|-----------|-----------|---------|
| **Performance** | Acesso direto aos dados via JPA/JDBC | 🚀 **Alto** |
| **Velocidade** | Consultas SQL otimizadas, índices, cache L1/L2 | 🚀 **Alto** |
| **Escalabilidade** | Connection pools, read replicas, sharding | 🚀 **Alto** |
| **CRUD Dinâmico** | API REST completa para criar/editar/deletar rotas | 🚀 **Alto** |
| **Independência** | Não depende de outros serviços (Config Server) | 🛡️ **Médio** |
| **Transações** | ACID compliance, rollback automático | 🛡️ **Médio** |
| **Auditoria** | Histórico completo de mudanças com timestamps | 📊 **Médio** |
| **Flexibilidade** | Estrutura de dados customizável | 🔧 **Médio** |
| **Cache Nativo** | Hibernate L2 cache, Redis como cache distribuído | 🚀 **Alto** |
| **Consultas Complexas** | JOINs, agregações, filtros avançados | 🔧 **Médio** |
| **Backup/Recovery** | Estratégias maduras de backup de BD | 🛡️ **Alto** |
| **Monitoramento** | Métricas de BD, slow queries, connection pools | 📊 **Alto** |

### ❌ **DESVANTAGENS**

| Categoria | Descrição | Impacto |
|-----------|-----------|---------|
| **Infraestrutura** | Precisa manter instância de banco de dados | 💰 **Médio** |
| **Dependência** | Gateway fica dependente da disponibilidade do BD | ⚠️ **Alto** |
| **Complexidade** | Mapeamento JPA, migrations, versionamento | 🔧 **Médio** |
| **Sincronização** | Multiple instances do gateway precisam sincronizar | ⚠️ **Médio** |
| **Single Point** | Se BD cair, criação de novas rotas para | ⚠️ **Alto** |
| **Overhead** | Conexões de rede, serialização/deserialização | 📉 **Baixo** |

---

## ⚙️ CONFIG SERVER

### ✅ **VANTAGENS**

| Categoria | Descrição | Impacto |
|-----------|-----------|---------|
| **Centralização** | Configurações centralizadas para todos os serviços | 🎯 **Alto** |
| **Versionamento** | Git como repositório, histórico nativo | 📚 **Alto** |
| **Ambientes** | Profiles diferentes (dev, staging, prod) | 🎯 **Alto** |
| **Refresh Automático** | `@RefreshScope` atualiza sem restart | 🔄 **Alto** |
| **Padrão Spring** | Abordagem "oficial" do ecossistema Spring Cloud | ✅ **Médio** |
| **Criptografia** | Encrypting/Decrypting de propriedades sensíveis | 🔒 **Alto** |
| **Rollback** | Fácil voltar para versão anterior via Git | 🔄 **Alto** |
| **Distribuição** | Replica automaticamente para todas as instâncias | 🌐 **Alto** |

### ❌ **DESVANTAGENS**

| Categoria | Descrição | Impacto |
|-----------|-----------|---------|
| **Performance** | Latência de rede + overhead HTTP | 📉 **Alto** |
| **Dependência Externa** | Precisa do Config Server + Git funcionando | ⚠️ **Alto** |
| **Complexidade Setup** | Config Server + Git + Security + Profiles | 🔧 **Alto** |
| **CRUD Limitado** | Não há API REST nativa para gestão dinâmica | ⚠️ **Alto** |
| **Refresh Manual** | Precisa triggerar refresh endpoint | 🔄 **Médio** |
| **Latência** | Cada busca de config é uma chamada HTTP | 📉 **Alto** |
| **Troubleshooting** | Logs distribuídos, difícil debugar problemas | 🔧 **Alto** |
| **Cache Issues** | Problemas de cache podem causar inconsistências | ⚠️ **Médio** |
| **Network Partitions** | Falhas de rede podem deixar configs desatualizados | ⚠️ **Alto** |
| **Overhead JSON** | Parsing de YAML/JSON a cada consulta | 📉 **Baixo** |

---

## 🎯 CENÁRIOS DE USO RECOMENDADOS

### 🗄️ **Use BANCO DE DADOS quando:**

| Cenário | Justificativa |
|---------|---------------|
| **Rotas frequentemente alteradas** | Performance superior para CRUD |
| **APIs de terceiros dinâmicas** | Facilidade para adicionar/remover |
| **SLA alto (99.9%+)** | Menor dependência externa |
| **Auditoria rigorosa** | Tracking completo de mudanças |
| **Grandes volumes** | Melhor performance para muitas rotas |
| **Integração com UI Admin** | APIs REST para interfaces gráficas |

### ⚙️ **Use CONFIG SERVER quando:**

| Cenário | Justificativa |
|---------|---------------|
| **Configurações raramente alteradas** | Overhead aceitável |
| **Multiple ambientes** | Profiles nativos |
| **Equipe já usa Config Server** | Consistência arquitetural |
| **Configurações sensíveis** | Encryption nativo |
| **Rollback frequente** | Git como fonte da verdade |

---

## 🏆 RECOMENDAÇÃO FINAL

### Para seu caso específico (rotas externas dinâmicas):

**🗄️ BANCO DE DADOS é a melhor escolha** pelos seguintes motivos:

1. **Performance crítica**: Rotas são consultadas a cada requisição
2. **CRUD dinâmico**: Você precisa de APIs para criar rotas programaticamente  
3. **Zero downtime**: Menor dependência de serviços externos
4. **Escalabilidade**: Melhor performance com alto volume de requisições
5. **Simplicidade operacional**: Menos componentes para manter

### 🛠️ **Implementação Híbrida (Opcional)**

Se você quiser o melhor dos dois mundos:

```yaml
# Configurações estáticas no Config Server
spring:
  cloud:
    gateway:
      routes:
        - id: static-route-1
          # rotas que nunca mudam

# Rotas dinâmicas no Banco de Dados
# Gerenciadas via API REST
```

**Resultado**: Configurações estáticas ficam versionadas no Git, rotas dinâmicas ficam no BD com performance otimizada.

---

## 🚀 Próximos Passos

1. **Implementação**: Escolha a abordagem baseada nos cenários apresentados
2. **Monitoramento**: Configure métricas para acompanhar performance das rotas
3. **Testes**: Implemente testes de carga para validar a solução
4. **Documentação**: Documente as APIs e fluxos para a equipe

## 📚 Recursos Adicionais

- [Spring Cloud Gateway Documentation](https://spring.io/projects/spring-cloud-gateway)
- [Spring Cloud Config Documentation](https://spring.io/projects/spring-cloud-config)
- [Dynamic Route Configuration Examples](https://github.com/spring-cloud/spring-cloud-gateway)
