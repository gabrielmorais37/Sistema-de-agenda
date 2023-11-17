# Sistema de Agenda utilizando Java com Spring Boot REST

## ⌨️Descrição do Projeto
Este projeto foi desenvolvido como parte da disciplina de Programação Orientada a Objetos na Universidade São Francisco (Campinas, Swift), sob orientação do professor Matias. O Sistema de Agenda em Java emprega tecnologias modernas para criar uma aplicação que gerencia contatos. O propósito principal é oferecer funcionalidades de criar, ler, atualizar e excluir contatos, visando um público variado que busca uma solução organizada para gerenciar informações de contato.

## ✒️Desenvolvedores
- Gabriel Ricardo de Morais - RA: 004201901451
- Anderson Dias Duarte      - RA: 202202089

## 🛠️Tecnologia Empregada
O projeto foi desenvolvido em Java utilizando o framework Spring Boot para a criação de uma API REST, juntamente ao Postman para realizar as chamadas. Foi utilizando um banco de dados de memória temporário Spring Data JPA, proporcionando um ambiente ágil e eficiente para manipulação de dados.

## 📋Descrição da Arquitetura
A aplicação segue uma arquitetura baseada em REST com o Spring Boot. A estrutura principal consiste em um controlador (AgendaController) que gerencia as requisições HTTP para realizar operações CRUD no banco de dados. A classe Agenda representa um contato na agenda e possui campos para nome, celular e e-mail.

## ⚙️Funcionalidade
O Sistema de Agenda utiliza o CRUD (Create, Read, Update, Delete), com as funcionalidades abaixo:
- Criar Contato: Permite adicionar um novo contato à agenda, sendo o nome, celular e email. Só é permitido criar um contato por vez. Mas tambem é possível criar contatos repetidos, pois serão diferenciados um dos outros pelo seu id, que é auto increment, onde será possível vizualiar o id no método "Listar Contatos". Por padrão, o contato é cadastrado como ativo.
- Listar Contatos: Recupera todos os contatos da agenda, incluindo os contatos inativos. Se não haver contatos cadastrados ativos e inativos, retorna uma mensagem dizendo que a lista esta vazia.
- Buscar por ID: Encontra um contato específico pelo seu ID, se não tiver o id na lista, o sistema retorna uma mensagem dizendo que não há o id pesquisado na lista.
- Buscar por Nome: Localiza um contato pelo seu nome se não tiver o nome na lista, o sistema retorna uma mensagem dizendo que não há o nome pesquisado na lista. Caso o usuário pesquise o nome com letra maiúscula ou minúscula, o sistema entende e realiza a busca.
- Atualizar Contato: Permite atualizar as informações de um contato existente pelo seu id, é possível atualizar nome, celular e email do contato. Caso não tenha o id na agenda, o sistema retorna um erro dizendo que o contato não foi encontrado.
- Desativar Contato: Inativa o contato pelo seu id, caso o id não esteja na lista, o sistema retorna uma mensagem dizendo que o contato não foi encontrado. Caso tenha o id esteja na lista, retorna uma mensagem de sucesso.
- Ativar contato: Reativa o contato pelo seu id, caso o id não esteja na lista, o sistema retorna uma mensagem dizendo que o contato não foi encontrado. Caso tenha o id esteja na lista, retorna uma mensagem de sucesso.

## 📄Documentação
O código foi devidamente documentado utilizando comentários claros para explicar a lógica por trás de cada método e classe. Essa documentação busca facilitar a compreensão do funcionamento da aplicação.
- Link chamada Postman: https://documenter.getpostman.com/view/31189602/2s9YXo1z4a
- Link collection Postman para download: https://drive.google.com/file/d/13NFyMA_fm-kw2QfyBFGr58D0Lb4b5Pdo/view?usp=sharing

## 🚀Inovação e Criatividade
O projeto vai além do básico CRUD ao implementar uma solução completa para gerenciar contatos, oferecendo funcionalidades avançadas de busca por nome, id e a possibilidade de manipular os dados de forma eficiente.

