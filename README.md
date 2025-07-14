# Concessionária DSW

Este projeto é uma aplicação web para gerenciar uma concessionária de veículos. Ele permite que administradores, lojas e clientes interajam com o sistema para cadastrar veículos, enviar propostas e gerenciar informações.

## Equipe
- **Leonardo Triiltz Siqueira** - CRUD Lojas e cliente; Frontend; Migração para API REST; Login; Requisitos envolvendo as propostas;
- **Lucas Alves** - Envio de email; Upload e visualização dos veículos; Internacionalização; Validações; Dockerização do banco de dados;

## Funcionalidades
O sistema é dividido em três perfis de acesso, cada um com funcionalidades específicas para atender às suas necessidades:

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

- **Backend**: Java com Spring Boot. O sistema também expõe uma API REST para interações programáticas.
- **Frontend**: Thymeleaf e Bootstrap.
- **Banco de Dados**: MySQL.
- **Gerenciamento de Dependências**: Maven.
- **Containerização**: Docker.

## Requisitos Implementados

O sistema atende a todos os requisitos funcionais e não funcionais solicitados, conforme detalhado abaixo:

(✓) **R1 - CRUD de Clientes**: Implementado um CRUD completo para a entidade Cliente, com acesso restrito a usuários com perfil de Administrador.

(✓) **R2 - CRUD de Lojas**: Implementado um CRUD completo para a entidade Loja, também com acesso restrito ao Administrador.


(✓) **R3 - Cadastro de Veículo**: Lojas autenticadas podem cadastrar novos veículos, fornecendo todas as informações necessárias, como placa, modelo, chassi, valor e fotos. 


(✓) **R4 - Listagem e Filtro de Veículos**: A página inicial, de acesso público, exibe todos os veículos à venda e oferece uma funcionalidade de busca para filtrar os veículos por modelo. 

(✓) **R5 - Proposta de Compra**: Clientes autenticados podem fazer propostas de compra para os veículos, informando o valor e as condições de pagamento. O sistema registra a data da proposta e impede que um cliente tenha mais de uma proposta em aberto para o mesmo veículo. 


(✓) **R6 - Listagem de Veículos da Loja**: Lojas autenticadas podem visualizar uma lista com todos os seus veículos cadastrados. 


(✓) **R7 - Listagem de Propostas do Cliente**: Clientes autenticados podem consultar o histórico de todas as suas propostas, com seus respectivos status (ABERTO, ACEITO, NÃO ACEITO). 

(✓) **R8 - Avaliação de Propostas pela Loja**: A loja pode analisar cada proposta recebida e alterar seu status. O cliente é notificado por e-mail sobre a decisão, recebendo uma contraproposta (opcional) em caso de recusa, ou o link para uma reunião online em caso de aceite. 


(✓) **R9 - Internacionalização**: O sistema foi internacionalizado para Português e Inglês, permitindo a troca de idioma dinamicamente. 

(✓) **R10 - Validação e Tratamento de Erros**: Todas as entradas de formulários são validadas (formato, campos obrigatórios, valores únicos, etc.). Erros de sistema, como cadastros duplicados ou falhas internas, são tratados e exibem uma página de erro amigável, com o log do erro registrado no console. 


(✓) **Outros Requisitos**: A arquitetura MVC foi seguida e a compilação é feita via Maven. O usuário Administrador padrão é criado na inicialização do sistema. 

## Pré-requisitos

Antes de rodar o projeto, certifique-se de ter instalado:

- [Docker](https://www.docker.com/)
- [Java 17+](https://www.oracle.com/java/technologies/javase-downloads.html)
- [Maven](https://maven.apache.org/)

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
   git clone https://github.com/Lusquiinha/Trabalho-DSW1-AA1.git
   ```

2. Navegue até o diretório do projeto:
   ```bash
   cd Trabalho-DSW1-AA1
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