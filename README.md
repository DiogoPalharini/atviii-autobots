# Atviii Autobots - Gerenciador de Microserviços

Projeto em Spring Boot para gerenciamento de entidades como Empresa, Usuário, Mercadoria, Veículo, Serviço e Venda.

## Funcionalidades
- CRUD completo para as principais entidades.
- Integração com banco de dados H2 (desenvolvimento) e MySQL (produção).
- Validação de dados com Bean Validation.

## Tecnologias
- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database / MySQL
- Maven

## Como Executar
1. **Clone o repositório:**
   ```bash
   git clone https://github.com/DiogoPalharini/atviii-autobots.git
   cd atviii-autobots

2. Instale as dependências:
```bash
mvn clean install
```
Inicie o servidor:
```bash
mvn spring-boot:run
```
Acesse:

API: http://localhost:8080
