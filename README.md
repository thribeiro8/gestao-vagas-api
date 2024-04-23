# Aplicação de Gerenciamento de Vagas de RH

Esta é uma aplicação Spring Boot que permite o gerenciamento de vagas de Recursos Humanos (RH).

## Requisitos
- [X] Java 17 ou superior
- [X] Maven
- [X] Banco de dados (Postgres)

## Configuração

1. Clone o repositório:

```bash
git clone https://github.com/danileao/gestao_vagas_rocket.git
```

2. Configure o banco de dados editando o arquivo application.properties e fornecendo as configurações necessárias:

```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/database
spring.datasource.username=admin
spring.datasource.password=admin
```
3. Construa a aplicação:

```bash
mvn clean install
```

4. Inicie a aplicação

```bash
mvn spring-boot:run
```

A aplicação agora deve estar em execução em http://localhost:8080.

## Documentação
Acesse a documentação completa da API para obter informações detalhadas sobre os endpoints e como usá-los aqui.
