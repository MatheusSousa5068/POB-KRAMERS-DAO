# Projeto de Persistência de Objetos em Java com db4oDAO

Este é um projeto escolar de Persistência de Objetos em Java, que utiliza o db4oDAO como mecanismo de persistência. O objetivo deste projeto é demonstrar a capacidade de armazenar, recuperar e manipular objetos em um banco de dados orientado a objetos usando a biblioteca db4o.

## Estrutura do Projeto

O projeto é composto por três principais entidades:

1. **Venda**
    - Atributos: id, data, lista de produtos, desconto, valor pago.
2. **Produto**
    - Atributos: nome, tipo de produto, preço.
3. **TipoProduto**
    - Atributos: nome, lista de produtos associados.

### Consultas Especiais

1. **Quais as vendas da data X**
2. **Quais as vendas que contêm um produto de preço X**
3. **Quais as vendas com mais de N produtos**

## Tecnologias Utilizadas

- Java
- db4oDAO (Biblioteca para persistência de objetos)

## **Licença**

Este projeto está licenciado sob a **[Licença MIT](https://opensource.org/license/mit/)**.

## **Agradecimentos**

Agradecemos ao Professor **Fausto Veras** por ministrar a disciplina de Persistência de Objetos e orientar este projeto.
