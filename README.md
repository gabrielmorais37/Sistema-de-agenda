# Sistema de Agenda utilizando Java com Spring Boot REST

## ⌨️Descrição do Projeto
Este projeto foi desenvolvido como parte da disciplina de Programação Orientada a Objetos na Universidade São Francisco (Campinas, Swift), sob orientação do professor Matias. O Sistema de Agenda em Java emprega tecnologias modernas para criar uma aplicação que gerencia contatos. O propósito principal é oferecer funcionalidades de criar, ler, atualizar e excluir contatos, visando um público variado que busca uma solução organizada para gerenciar informações de contato.

## ✒️Desenvolvedores
Gabriel Ricardo de Morais - RA: 004201901451
Anderson Dias Duarte      - RA: 202202089

## 🛠️Tecnologia Empregada
O projeto foi desenvolvido em Java utilizando o framework Spring Boot para a criação de uma API REST e o Postman para realizar as chamadas. Foi utilizando um banco de dados de memória Spring Data JPA, proporcionando um ambiente ágil e eficiente para manipulação de dados.

## 📋Descrição da Arquitetura
A aplicação segue uma arquitetura baseada em REST com o Spring Boot. A estrutura principal consiste em um controlador (AgendaController) que gerencia as requisições HTTP para realizar operações CRUD no banco de dados. A classe Agenda representa um contato na agenda e possui campos para nome, celular e e-mail.

## ⚙️Funcionalidade
CRUD (Create, Read, Update, Delete)
- Criar Contato: Permite adicionar um novo contato à agenda.
- Listar Contatos: Recupera todos os contatos da agenda.
- Buscar por ID: Encontra um contato específico pelo seu ID.
- Buscar por Nome: Localiza um contato pelo seu nome.
- Atualizar Contato: Permite atualizar as informações de um contato existente.
- Excluir Contato: Remove um contato da agenda pelo seu ID.

## 📄Documentação
O código foi devidamente documentado utilizando comentários claros para explicar a lógica por trás de cada método e classe. Essa documentação busca facilitar a compreensão do funcionamento da aplicação.

## 🚀Inovação e Criatividade
O projeto vai além do básico CRUD ao implementar uma solução completa para gerenciar contatos, oferecendo funcionalidades avançadas de busca por nome, id e a possibilidade de manipular os dados de forma eficiente.