## Java Web Services <img align="center" alt="Java" height="30" width="40" src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg">

Este é um projeto desenvolvido com Spring Boot para criar uma aplicação web que escuta na porta 8080. Ele implementa uma API RESTful com suporte aos principais verbos HTTP (GET, POST, PUT, DELETE) e realiza operações de consumo de API externa, além de manipulação e tratamento de erros. Este projeto foi parte de um trabalho da faculdade.

## Funcionalidades
 - GET Endpoint: Recebe dois parâmetros opcionais e retorna uma resposta com base nos critérios definidos.
 - POST Endpoint: Recebe um JSON contendo um campo do tipo String, um campo numérico e um array de qualquer tipo. Processa o JSON e retorna a resposta adequada.
 - PUT Endpoint: Atualiza um recurso existente com base nos dados fornecidos.
 - DELETE Endpoint: Remove um recurso específico do sistema.
 - Consumo de API Externa: Um dos endpoints consome uma API externa, converte a resposta de JSON para um objeto Java, e faz log do status code da resposta.
 - Tratamento de Erros: Manipula erros adequadamente, retornando códigos de status HTTP apropriados (ex: 400, 404) e 200 para sucesso.
 - Testes Unitários: Contém testes para os métodos, incluindo pelo menos um teste usando assertThrows para validar exceções.

## Detalhes da Implementação

 - Framework: Desenvolvido com Spring Boot, proporcionando uma configuração simplificada e suporte robusto para desenvolvimento web.
 - Mecanismo de Log: Utilizado o framework de logging integrado ao Spring, ao invés de System.out.println para registrar mensagens importantes.
 - Lombok: Utilizado para reduzir o código boilerplate, como getters, setters e logs.
 - Tratamento de Erros: Implementação de tratamentos de exceções para garantir respostas apropriadas ao usuário em caso de erro.
 - Testes Automatizados: Os métodos da API possuem testes automatizados para garantir a integridade e o comportamento esperado do código.
