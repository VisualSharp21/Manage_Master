#  Manage Master - Projetos 2
O software criado para disciplina de projetos de engenharia II feito pela equipe gnosis com objetivo de ajudar microempreendedores a fazer uma melhor gestão financeira e de estoque usando ferramentas como Java(Spring boot), python, html, css, JavaScript, SQL, MySQL e Docker

## 📂Estrutura de Arquivos:
```
 📦 Manager Master/
├── 🧠 idea/                    # Configurações do IntelliJ IDEA
├── 🗄️ database/                # Arquivos e scripts de banco de dados
├── 🎨 frontend/                # Aplicação frontend
├── 🐍 python/                  # Scripts ou serviços em Python
├── ⚙️ sigfebackend/ [backend]  # Backend principal do projeto
│   ├── 📁 src/                 # Código fonte do projeto
│   ├── 📁 target/              # Artefatos de build
│   ├── 🐋 Dockerfile           # Configuração do container Docker
│   ├── ❓ HELP.md              # Documentação de ajuda
│   ├── 🛠️ mvnw                 # Maven wrapper (Unix/Linux)
│   ├── 🛠️ mvnw.cmd             # Maven wrapper (Windows)
│   ├── 📜 pom.xml              # Configuração do Maven
│── ⚙️ .env                     # Variáveis de ambiente
│── 🐋 docker-compose.yml       # Orquestração de containers
│── 📄 README.md                # Documentação do backend
├── 📚 External Libraries       # Bibliotecas externas
└── 📝 Scratches and Consoles   # Arquivos temporários e consoles
```
# ⚙️Instrucoes para instalacao:
```bash
# Navegar para o backend
cd "Manager Master/sigfebackend"

# Executar com Maven Wrapper
./mvnw spring-boot:run  # Linux/Mac
mvnw.cmd spring-boot:run # Windows

# Com Docker Compose
docker-compose up --build
```


