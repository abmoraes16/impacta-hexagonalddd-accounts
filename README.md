# lab-impacta-hexagonalddd

Esse repositório contém dois microserviços desenvolvidos utilizando a arquitetura Hexagonal com DDD em aula na Impacta. 

Segue o cenário de negócio atendido:

> Nós da área de negócio temos a necessidade de implementação de uma aplicação que seja possível realizar
> investimento de um determinado produto, para realizar o investimento é necessário verificar o saldo, caso o
> saldo seja menor que o investimento não será possível realizar a transação. Será possível também realizar um
> investimento de segmento private, porém para esse investimento será necessário uma regra de valor
> minimo de saldo em conta-corrente que será aplicado diretamente no produto específico.


## Execução

Para executar as aplicações, no diretório de cada uma executar o seguinte comando:

mvn clean spring-boot:run

A aplicação de Account esta configurada pra subir na porta 8381, enquanto a Investment está na 8382. 


### Endpoints

Consulta de Saldo: 

```
curl --location --request GET 'http://localhost:8381/api/v1/accounts/2/balance'
```

Debitar saldo da conta:

```
curl --location --request POST 'http://localhost:8381/api/v1/accounts/2/debit/' \
--header 'Content-Type: application/json' \
--data-raw '{
    "valueOfDebit": 100
}'
```

Realizar Investimento:

```
curl --location --request POST 'http://localhost:8382/api/v1/accounts/2/investment' \
--header 'Content-Type: application/json' \
--data-raw '{
    "productId": 2,
    "valueOfInvestment": 100
}'
```


