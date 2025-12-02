<br />
<div align="center">
  <h1 align="center">🛒 Mercato API</h1>

  <p align="center">
    Uma API RESTful em Spring Boot focada em Arquitetura Limpa, Boas Práticas de Engenharia e persistência robusta com PostgreSQL.
    <br />
    <br />
    <a href="https://github.com/devcaiqueoliveira/mercato-pdv-system/issues">Reportar Bug</a>
    ·
    <a href="https://github.com/devcaiqueoliveira/mercato-pdv-system/issues">Sugerir Feature</a>
  </p>
</div>

<p align="center">
  <img alt="Java" src="https://img.shields.io/badge/Java-17-orange?logo=openjdk&logoColor=white">
  <img alt="Spring Boot" src="https://img.shields.io/badge/Spring_Boot-3.4.0-green?logo=spring-boot&logoColor=white">
  <img alt="PostgreSQL" src="https://img.shields.io/badge/PostgreSQL-blue?logo=postgresql&logoColor=white">
  <img alt="Docker" src="https://img.shields.io/badge/Docker-2496ED?logo=docker&logoColor=white">
  <img alt="Swagger" src="https://img.shields.io/badge/Swagger-85EA2D?logo=swagger&logoColor=black">
</p>

<details>
  <summary>Índice</summary>
  <ol>
    <li>
      <a href="#-sobre-o-projeto">Sobre o Projeto</a>
      <ul>
        <li><a href="#-arquitetura-e-design">Arquitetura e Design</a></li>
      </ul>
    </li>
    <li>
      <a href="#-status-do-desenvolvimento">Status do Desenvolvimento</a>
    </li>
    <li>
      <a href="#-começando">Começando</a>
      <ul>
        <li><a href="#-pré-requisitos">Pré-requisitos</a></li>
        <li><a href="#-instalação">Instalação</a></li>
      </ul>
    </li>
    <li><a href="#-documentação-viva">Documentação Viva</a></li>
  </ol>
</details>

---

## 🏛️ Sobre o Projeto

Este projeto implementa o backend para uma API de gestão de varejo (PDV). O objetivo principal não é apenas a funcionalidade, mas sim a demonstração de uma **arquitetura de software evolutiva, segura e profissional**.

O projeto está em **desenvolvimento ativo**, servindo como laboratório para aplicação de padrões de mercado e resolução de problemas reais de engenharia de software (como integridade de dados e documentação viva).

### 📐 Arquitetura e Design

As decisões de design foram baseadas em princípios de **Clean Code** e **REST** para garantir uma clara separação de responsabilidades.

#### 1. Camada de Domínio (Rich Domain Model)
As entidades não são anêmicas. Elas possuem responsabilidade sobre seu próprio estado.
* **Integridade:** Métodos como `updateFrom` garantem que atualizações de dados não corrompam campos sensíveis (como IDs ou datas de auditoria).
* **Auditoria Automática:** Uso de `@PrePersist` e `@PreUpdate` para gestão automática de timestamps.

#### 2. Camada de Serviço (Regras de Negócio)
Onde a mágica acontece de forma desacoplada.
* **Strategy Pattern:** Implementado para validações complexas (como regras de unicidade e integridade), permitindo a extensão sem modificação do código principal (*Open/Closed Principle*).
* **Transacionalidade:** Uso granular de `@Transactional` para garantir atomicidade nas operações de escrita e otimização de performance (`readOnly`) nas leituras.

#### 3. Camada de API (Segurança e Contrato)
A fachada pública do sistema é blindada.
* **DTO Pattern:** Isolamento total da camada de persistência. A API expõe apenas o necessário via `RequestDTO` e `ResponseDTO`, utilizando **Java Records** para imutabilidade.
* **MapStruct:** Conversão automatizada e performática entre DTOs e Entidades, eliminando código boilerplate e erros humanos.
* **Global Exception Handling:** Tratamento centralizado de erros (`@ControllerAdvice`), convertendo exceções técnicas em respostas HTTP semânticas (400, 404, 409) com JSON padronizado.

<p align="right">(<a href="#top">voltar ao topo</a>)</p>

## 🚧 Status do Desenvolvimento

O projeto está sendo construído em etapas. Abaixo, o que já está pronto e o que está por vir.

| Módulo | Status | Funcionalidades |
| :--- | :---: | :--- |
| **Infraestrutura** | ✅ | Docker Compose, PostgreSQL, Flyway Migrations |
| **Categorias** | ✅ | CRUD Completo, Validação de Unicidade, DTOs |
| **Produtos** | ✅ | CRUD Completo, Relacionamento com Categoria, Controle de Estoque Decimal |
| **Segurança** | 🔄 | Configuração inicial do Spring Security (Aberto para dev) |
| **Vendas** | ⏳ | Criação de pedidos, Baixa de estoque, Cálculo de total |
| **Relatórios** | ⏳ | Endpoints de análise de vendas |

<p align="right">(<a href="#top">voltar ao topo</a>)</p>
