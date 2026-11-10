# Manage Master

Sistema desenvolvido na disciplina de Projetos de Engenharia II com o objetivo de apoiar microempreendedores na **gestão financeira, análise de dados e controle de estoque**.

O projeto utiliza uma arquitetura composta por backend Java/Spring Boot, serviço de analytics em Python, frontend web, banco de dados e execução conteinerizada com Docker.

## Arquitetura

```text
Frontend
   |
   +------------------+
   |                  |
   v                  v
Spring Boot      Python Analytics API
   |                  |
   v                  v
 MySQL         gráficos / relatórios
```

## Tecnologias

- **Python**
- **Flask**
- **Pandas**
- **Plotly**
- **Java**
- **Spring Boot**
- **JavaScript / HTML / CSS**
- **MySQL**
- **Docker / Docker Compose**

## Serviço Python de Analytics

O projeto possui uma API Python dedicada à análise de dados e geração de gráficos e relatórios.

Entre as bibliotecas utilizadas estão:

- Pandas para processamento de dados;
- Plotly para visualização;
- ReportLab para geração de relatórios;
- Flask para disponibilização dos endpoints.

O frontend consome essa API para carregar visualizações do sistema.

## Estrutura

```text
Manage_Master/
├── database/           # Banco de dados e scripts
├── frontend/           # Interface web
├── python/             # API e serviços de analytics em Python
├── sigfebackend/       # Backend Java / Spring Boot
└── docker-compose.yml  # Orquestração dos serviços
```

## Execução

### Com Docker Compose

```bash
docker compose up --build
```

### Backend Spring Boot

```bash
cd sigfebackend
./mvnw spring-boot:run
```

### Serviço Python

Consulte o diretório `python/` para dependências e configuração do serviço.

## O que este projeto demonstra

- integração entre serviços escritos em linguagens diferentes;
- desenvolvimento de APIs;
- processamento e visualização de dados com Python;
- arquitetura de microsserviços;
- conteinerização com Docker;
- integração entre frontend, backend e serviço de analytics.

## Contexto acadêmico

Projeto desenvolvido em equipe na Universidade Federal do Pará (UFPA).
