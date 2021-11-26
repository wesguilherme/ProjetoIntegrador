# ProjetoIntegrador

## Grupo 5 Wave - 3


### Requisito 6 - Wesley


### GET 6.1 - BlackFriday - insert

Para cadastrar um produto na lista de descontos da BlackFriday

Exemplo de lista 

```
{
    "productId": "MLB-129",
    "discount": 0.90,
    "initialDate": "2021-11-22",
    "finalDate": "2021-11-26"
}
```

***Link para uso:***

http://localhost:8090/api/v1/fresh-products/blackfriday/insert


### GET 6.2 - BlackFriday - list

Para obter uma lista de produtos com descontos da BlackFriday

***Link para uso:***

http://localhost:8090/api/v1/fresh-products/blackfriday/list

Exemplo de response 

```
[
    {
        "productId": "MLB-126",
        "discount": 0.75,
        "initialDate": "2021-11-22",
        "finalDate": "2021-11-26"
    },
    {
        "productId": "MLB-129",
        "discount": 0.10,
        "initialDate": "2021-11-22",
        "finalDate": "2021-11-26"
    }
]
```

### GET 6.3 - BlackFriday - update

Para atualizar um produto na lista de descontos da BlackFriday

Exemplo de request 

```
{
    "productId": "MLB-129",
    "discount": 0.90,
    "initialDate": "2021-11-22",
    "finalDate": "2021-11-26"
}
```

***Link para uso:***

http://localhost:8090/api/v1/fresh-products/blackfriday/update/MLB-129


### GET 6.4 - BlackFriday - delete

Para deletar um item da lista de produtos com descontos da BlackFriday

***Link para uso:***

http://localhost:8090/api/v1/fresh-products/blackfriday/delete/MLB-129
