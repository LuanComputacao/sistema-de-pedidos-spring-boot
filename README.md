# sistema de pedidos

## Linguagens
* Java
* JavaScript
* HTML-JSP
* CSS
* SQL

## Frameworks
* JAVA: [spring-boot](https://spring.io/)
* FrontEnd: bootstrap

## Como executar a aplição
`mvn spring-boot:run`

## Como executar os testes da aplição
`mvn test`

## Como acessar a aplicação
`localhost:8181`

## Tags
[![Maintainability](https://api.codeclimate.com/v1/badges/10e221673650c314b58c/maintainability)](https://codeclimate.com/github/LuanComputacao/sistema-de-pedidos-spring-boot/maintainability)
[![Test Coverage](https://api.codeclimate.com/v1/badges/10e221673650c314b58c/test_coverage)](https://codeclimate.com/github/LuanComputacao/sistema-de-pedidos-spring-boot/test_coverage)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/fd4cf8b8f3a143b697612778f780d369)](https://app.codacy.com/app/LuanComputacao/sistema-de-pedidos-spring-boot?utm_source=github.com&utm_medium=referral&utm_content=LuanComputacao/sistema-de-pedidos-spring-boot&utm_campaign=badger)

[![Build status](https://ci.appveyor.com/api/projects/status/p0c7ko6a08gf7g62?svg=true)](https://ci.appveyor.com/project/LuanComputacao/sistema-de-pedidos-spring-boot)
[![Build status](https://ci.appveyor.com/api/projects/status/p0c7ko6a08gf7g62/branch/master?svg=true)](https://ci.appveyor.com/project/LuanComputacao/sistema-de-pedidos-spring-boot/branch/master)


[![CircleCI](https://circleci.com/gh/LuanComputacao/sistema-de-pedidos-spring-boot.svg?style=svg)](https://circleci.com/gh/LuanComputacao/sistema-de-pedidos-spring-boot)


[![Build Status](https://travis-ci.org/LuanComputacao/sistema-de-pedidos-spring-boot.svg?branch=master)](https://travis-ci.org/LuanComputacao/sistema-de-pedidos-spring-boot)

## Detalhes

### Dados iniciais do DB 
`./src/main/resources/data.sql`

### Views directory
`./src/main/webapp/WEB-INF/views`

### Routes

```
  /
 └── clientes/
 └── cliente/
 |   └── {id}/
 └── produtos/
 └── produto/
 |   └── {id}/
 └── pedido/
 |   └── /{id}/
 |       └── itens/
 |       └── item/
 |       └── produto/{id}/item/
 └──api/
    └── v1
        └── clientes/
        |   └── {id}
        |   |   └── pedidos
        |   |       └── {pedidosID}
        |   └── {cpf}
        └── pedidos/
        |   └── {id}
        └── produtos/
            └── {id}
```
