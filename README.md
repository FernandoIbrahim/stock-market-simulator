# README

## Bolsa de Valores - Sistema de Simulação

Este repositório contém a implementação de um sistema de simulação de uma bolsa de valores, desenvolvido como parte do trabalho prático sobre Princípios SOLID e Padrões de Projeto, realizado em dupla na disciplina de Programação modular.

### Integrantes do Grupo
- Fernando Antônio Ferreira Ibrahim
- Gabreiel Ferreira Amaral

### Estrutura do Projeto
O projeto está estruturado conforme os princípios SOLID e utiliza padrões de projeto como Singleton, Strategy, Factory e Observer. A implementação foi realizada em Java, fazendo uso de Threads para simular a interação de múltiplos brokers com a bolsa de valores.

## Funcionamento do Sistema

### Tipos de Ordens e Operações dos Brokers na Bolsa de Valores

Neste sistema de simulação da bolsa de valores, os brokers têm a capacidade de realizar diferentes tipos de ordens, cada uma representando uma ação específica no mercado financeiro. Abaixo, descrevemos os principais tipos de ordens e as operações que os brokers podem executar:

#### Tipos de Ordens:

1. **Ordem de Compra:**
   - **Formato:** `compra <quant: int, val: real, corretora: char[4]>`
   - Envia ao servidor da bolsa de valores uma ordem de compra indicando que a corretora deseja comprar uma determinada quantidade de lotes de ações pelo preço especificado em reais.

2. **Ordem de Venda:**
   - **Formato:** `venda <quant: int, val: real, corretora: char[4]>`
   - Envia ao servidor da bolsa de valores uma ordem de venda indicando que a corretora deseja vender uma determinada quantidade de lotes de ações pelo preço especificado em reais.

3. **Informações sobre uma Ação:**
   - **Formato:** `info <data-hora: char[16] dd/mm/aaaa hh>`
   - Envia ao servidor da bolsa de valores uma solicitação de informações sobre uma ação em uma data e hora específicas.

#### Operações dos Brokers:

1. **Envio de Ordens:**
   - Os brokers podem enviar ordens de compra e venda para a bolsa de valores, indicando a quantidade desejada de lotes e o preço.

2. **Assinatura de Tópicos:**
   - Os brokers podem assinar tópicos relativos às ações que desejam acompanhar. Isso permite que recebam notificações sobre atualizações no livro de ofertas e transações realizadas.

3. **Recebimento de Atualizações:**
   - Sempre que a bolsa de valores recebe uma ordem de compra ou venda, ela encaminha essa ordem a todos os brokers interessados naquela ação específica. Os brokers são notificados sobre as atualizações no livro de ofertas.

4. **Geração de Transações:**
   - Quando o valor de uma ordem de compra é maior ou igual ao valor de uma ordem de venda para uma mesma ação, a bolsa de valores gera uma transação, indicando a quantidade e o preço da transação. As ordens correspondentes são então atualizadas ou removidas do livro de ofertas.


#### Exemplos de Ações:
A bolsa de valores é composta por diversas ações, cada uma representando uma empresa e sua atividade principal. Alguns exemplos de ações incluem AMBEV S/A ON (ABEV3) na fabricação e distribuição de bebidas, PETROBRAS PN (PETR4) no setor de petróleo e gás, e ITAUUNIBANCO PN (ITUB4) na atividade bancária.
