# Concessionária DSW

Este projeto é uma aplicação web para gerenciar uma concessionária de veículos. Ele permite que administradores, lojas e clientes interajam com o sistema para cadastrar veículos, enviar propostas e gerenciar informações.

## Funcionalidades

- **Administração**:
  - Gerenciamento de clientes e lojas.
  - Visualização e edição de dados.

- **Lojas**:
  - Cadastro de veículos.
  - Visualização de propostas recebidas.
  - Avaliação de propostas.

- **Clientes**:
  - Cadastro de conta.
  - Envio de propostas para veículos.
  - Visualização de propostas enviadas.

## Tecnologias Utilizadas

- **Backend**: Java com Spring Boot.
- **Frontend**: Thymeleaf e Bootstrap.
- **Banco de Dados**: MySQL.
- **Gerenciamento de Dependências**: Maven.
- **Containerização**: Docker.

## Pré-requisitos

Antes de rodar o projeto, certifique-se de ter instalado:

- [Docker](https://www.docker.com/)
- [Java 17+](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/)
- Um IDE como [IntelliJ IDEA](https://www.jetbrains.com/idea/) ou [Eclipse](https://www.eclipse.org/).

## Configuração do Banco de Dados com Docker

1. Certifique-se de que o Docker está instalado e funcionando.

2. No diretório do projeto, execute o comando abaixo para iniciar o banco de dados MySQL:
   ```bash
   docker-compose up -d
   ```

3. O banco de dados estará disponível na porta `3306` com as seguintes credenciais:
   - **Usuário**: `root`
   - **Senha**: `root`
   - **Banco de Dados**: `concessionaria`

## Como Rodar o Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/concessionaria-dsw.git
   ```

2. Navegue até o diretório do projeto:
   ```bash
   cd concessionaria-dsw
   ```

3. Compile o projeto usando Maven:
   ```bash
   mvn clean install
   ```

4. Inicie a aplicação:
   ```bash
   mvn spring-boot:run
   ```

5. Acesse a aplicação no navegador:
   ```
   http://localhost:8080
   ```

