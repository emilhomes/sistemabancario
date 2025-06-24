# Sistema Bancário Simplificado em Java

## 📖 Descrição

Este projeto é uma aplicação de console que simula as operações básicas de um sistema bancário. Foi desenvolvido em Java puro como um exercício para aplicar e demonstrar os conceitos fundamentais da Programação Orientada a Objetos (POO). O sistema gerencia clientes, contas e as transações entre eles, tudo orquestrado por uma classe central `Banco`.

## ✨ Funcionalidades

-   **Gerenciamento de Clientes:** Cadastro de novos clientes com nome e CPF.
-   **Validação de Dados:** Validação básica para o formato do CPF no momento do cadastro.
-   **Gerenciamento de Contas:** Abertura de contas associadas a um cliente existente.
-   **Operações Bancárias:**
    -   `depositar()`: Adiciona valor ao saldo da conta.
    -   `sacar()`: Retira valor do saldo, com verificação de fundos.
    -   `consultarSaldo()`: Exibe o saldo atual da conta.
-   **Interação Centralizada:** Uma classe `Banco` atua como fachada, gerenciando as listas de clientes e contas e fornecendo métodos para as operações principais.