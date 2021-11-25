# ProjetoIntegrador

## Grupo 5 Wave - 3


### Requisito 1


### POST 1.1 - InboundOrder - Insert

Para cadastrar o pedido de entrada no sistema é necessario efetuar uma requisição via postman

```
{
    "orderNumber": 45,
    "orderDate":"1998-07-23",
    "sectionCode": "SEC-126",
    "batchStockDto":[
        {
            "productSellerId": 1,
            "currentTemperature": 25,
            "minimumTemperature": 9.5,
            "initialQuantity": 100,
            "currentQuantity": 100,
            "manufacturingDate": "1998-07-23",
            "manufacturingTime": "2021-09-15T12:23:37.206794",
            "dueDate": "2021-11-23",
            "batchStockNumber": 77
        },
        {
            "productSellerId": 1,
            "currentTemperature": 25,
            "minimumTemperature": 9.5,
            "initialQuantity": 100,
            "currentQuantity": 100,
            "manufacturingDate": "1998-07-23",
            "manufacturingTime": "2021-09-15T12:23:37.206794",
            "dueDate": "2021-11-29",
            "batchStockNumber": 99
        }
    ]
}
```

***Link para uso:***

http://localhost:8090/api/v1/fresh-products/inboundorder/insert



### PUT 1.2 - InboundOrder - Update


Para atualizar o pedido de entrada no sistema é necessario efetuar uma requisição via postman

```
{
    "orderNumber": 40,
    "orderDate":"2021-07-23",
    "sectionCode": "SEC-125",
    "batchStockDto":[
        {
            "productSellerId": 1,
            "currentTemperature": 15,
            "minimumTemperature": 8,
            "initialQuantity": 40,
            "currentQuantity": 80,
            "manufacturingDate": "1998-07-23",
            "manufacturingTime": "2021-09-15T12:23:37.206794",
            "dueDate": "2021-11-23",
            "batchStockNumber": 1
        },
        {
            "productSellerId": 1,
            "currentTemperature": 20,
            "minimumTemperature": 9,
            "initialQuantity": 70,
            "currentQuantity": 90,
            "manufacturingDate": "1998-07-23",
            "manufacturingTime": "2021-09-15T12:23:37.206794",
            "dueDate": "2021-11-29",
            "batchStockNumber": 2
        }
    ]
}
```

***Link para uso:***

http://localhost:8090/api/v1/fresh-products/inboundorder/update/1



### GET 2.1 - Product - list all

Para obter uma lista de todos os produtos cadastrados

***Link para uso:***

http://localhost:8090/api/v1/product/list

Exemplo de lista de produtos

```
[
    {
        "volume": 80.0,
        "maximumTemperature": 23.0,
        "minimumTemperature": 9.5,
        "product": {
            "productId": "MLB-126",
            "name": "Uva",
            "description": "Caixa de Uva",
            "type": {
                "typeId": 1,
                "initials": "RF",
                "environmentType": "REFRIGERADOS"
            }
        }
    },
    {
        "volume": 80.0,
        "maximumTemperature": 23.0,
        "minimumTemperature": 9.5,
        "product": {
            "productId": "MLB-127",
            "name": "Maca",
            "description": "Caixa de maca",
            "type": {
                "typeId": 1,
                "initials": "RF",
                "environmentType": "REFRIGERADOS"
            }
        }
    },
    {
        "volume": 80.0,
        "maximumTemperature": 23.0,
        "minimumTemperature": 9.5,
        "product": {
            "productId": "MLB-129",
            "name": "Banana",
            "description": "Caixa de Banana",
            "type": {
                "typeId": 1,
                "initials": "RF",
                "environmentType": "REFRIGERADOS"
            }
        }
    },
    {
        "volume": 80.0,
        "maximumTemperature": 23.0,
        "minimumTemperature": 9.5,
        "product": {
            "productId": "MLB-129",
            "name": "Banana",
            "description": "Caixa de Banana",
            "type": {
                "typeId": 1,
                "initials": "RF",
                "environmentType": "REFRIGERADOS"
            }
        }
    },
    {
        "volume": 80.0,
        "maximumTemperature": 23.0,
        "minimumTemperature": 9.5,
        "product": {
            "productId": "MLB-129",
            "name": "Banana",
            "description": "Caixa de Banana",
            "type": {
                "typeId": 1,
                "initials": "RF",
                "environmentType": "REFRIGERADOS"
            }
        }
    },
    {
        "volume": 80.0,
        "maximumTemperature": 23.0,
        "minimumTemperature": 9.5,
        "product": {
            "productId": "MLB-129",
            "name": "Banana",
            "description": "Caixa de Banana",
            "type": {
                "typeId": 1,
                "initials": "RF",
                "environmentType": "REFRIGERADOS"
            }
        }
    }
]
```


### GET 2.2 - InboundOrder - List Initials

Para obter uma lista ordenada pelo tipo do produto

***Link para uso:***

http://localhost:8090/api/v1/fresh-products/inboundorder/list/RF

Exemplo de lista

```
[
    {
        "productId": "MLB-126",
        "name": "Uva",
        "description": "Caixa de Uva",
        "type": {
            "typeId": 1,
            "initials": "RF",
            "environmentType": "REFRIGERADOS"
        }
    },
    {
        "productId": "MLB-127",
        "name": "Maca",
        "description": "Caixa de maca",
        "type": {
            "typeId": 1,
            "initials": "RF",
            "environmentType": "REFRIGERADOS"
        }
    },
    {
        "productId": "MLB-129",
        "name": "Banana",
        "description": "Caixa de Banana",
        "type": {
            "typeId": 1,
            "initials": "RF",
            "environmentType": "REFRIGERADOS"
        }
    }
]
```

### POST 2.3 - PurchaseOrder - Insert

Para obter o registro de um pedido com a lista de produtos no carinho, tendo como resposta o valor total da compra

```
{
    "date":"2021-10-31",
    "buyerId": 1,
    "orderStatus":{
        "statusCode":"cart"
    },
    "products":[
        {
            "productId": "MLB-129",
            "quantity": 6            
        }
    ]
}
```

***Link para uso:***

http://localhost:8090/api/v1/product/orders


### GET 2.4 - PurchaseOrder - List by cart

Para ver os produtos no pedido, utilizando o purchaseOrderId

***Link para uso:***

http://localhost:8090/api/v1/product/orders/1

Exemplo de lista de produtos

```
{
    "date": "2021-10-31",
    "buyerId": 1,
    "orderStatus": {
        "orderStatusId": 1,
        "statusCode": "cart"
    },
    "products": [
        {
            "productId": "MLB-126",
            "quantity": 6
        }
    ]
}
```

### PUT 2.5 - PurchaseOrder - Update


Para atualizar o pedido de entrada no sistema é necessario efetuar uma requisição via postman

```
{
    "products":[
        {
            "purchaseItemId": 15,
            "productId": "MLB-129",
            "quantity": 1           
        },
        {
            "purchaseItemId": 16,
            "productId": "MLB-129",
            "quantity": 2            
        }
    ]
}
```

***Link para uso:***

http://localhost:8090/api/v1/product/orders/update


### GET 3.1 - BatchStock - list all By product id

Para obter a lista de produtos por lote

***Link para uso:***

http://localhost:8090/api/v1/fresh-products/batchStock/listById/MLB-126


Exemplo de lista 

```
{
    "sectionResponseDto": {
        "sectionCode": "SEC-126",
        "warehouseCode": "MLB-410"
    },
    "productId": "MLB-126",
    "batchStockList": [
        {
            "batchStockNumber": 77,
            "currentQuantity": 52,
            "dueDate": "2021-11-23"
        },
        {
            "batchStockNumber": 77,
            "currentQuantity": 100,
            "dueDate": "2021-11-23"
        }
    ]
}
```

### GET 3.2 - BatchStock - list all By product id and filter ordination

Para obter a lista de produtos por lote ordenada por validade, lote ou quantidade

***Link para uso:***

http://localhost:8090/api/v1/fresh-products/batchStock/list/MLB-126/F

Exemplo de lista 

```
{
    "sectionResponseDto": {
        "sectionCode": "SEC-126",
        "warehouseCode": "MLB-410"
    },
    "productId": "MLB-126",
    "batchStockList": [
        {
            "batchStockNumber": 77,
            "currentQuantity": 52,
            "dueDate": "2021-11-23"
        },
        {
            "batchStockNumber": 77,
            "currentQuantity": 100,
            "dueDate": "2021-11-23"
        }
    ]
}
```

### GET 4.1 - Warehouse - list product by warehouse

Para obter uma lista de produtos por warehouse

***Link para uso:***

http://localhost:8090/api/v1/warehouse/listWarehouseByProductId/MLB-129

Exemplo de lista 
```
{
    "productId": "MLB-129",
    "warehouses": [
        {
            "warehouseCode": "MLB-410",
            "quantidade": 958
        }
    ]
}

```


### GET 5.1 - BatchStock - list all By due date

Para obter uma lista de todos os produtos em um setor armazenado por data de validade

***Link para uso:***

http://localhost:8090/api/v1/fresh-products/batchStock/due-date/SEC-126/15

Exemplo de lista 

```
{
    "batchStock": [
        {
            "batchStockNumber": 99,
            "productId": "MLB-129",
            "environmentType": "REFRIGERADOS",
            "dueDate": "2021-11-29",
            "currentQuantity": 90
        },
        {
            "batchStockNumber": 99,
            "productId": "MLB-129",
            "environmentType": "REFRIGERADOS",
            "dueDate": "2021-11-29",
            "currentQuantity": 100
        },
        {
            "batchStockNumber": 99,
            "productId": "MLB-129",
            "environmentType": "REFRIGERADOS",
            "dueDate": "2021-11-29",
            "currentQuantity": 100
        },
        {
            "batchStockNumber": 99,
            "productId": "MLB-129",
            "environmentType": "REFRIGERADOS",
            "dueDate": "2021-11-29",
            "currentQuantity": 100
        },
        {
            "batchStockNumber": 99,
            "productId": "MLB-129",
            "environmentType": "REFRIGERADOS",
            "dueDate": "2021-11-29",
            "currentQuantity": 100
        }
    ]
}v
```

### GET 5.2 - BatchStock - list all By product id

Para obter uma lista de lotes por prazo de validade, que pertençam a um dos 3 tipos de produto em ordem crescente ou descrecente

***Link para uso:***

http://localhost:8090/api/v1/fresh-products/batchStock/due-date/list?quantityOfDays=15&typeId=1&sort=due_date,ASC

Exemplo de lista 

```
{
    "batchStock": [
        {
            "batchStockNumber": 99,
            "productId": "MLB-129",
            "environmentType": "REFRIGERADOS",
            "dueDate": "2021-11-29",
            "currentQuantity": 90
        },
        {
            "batchStockNumber": 99,
            "productId": "MLB-129",
            "environmentType": "REFRIGERADOS",
            "dueDate": "2021-11-29",
            "currentQuantity": 100
        },
        {
            "batchStockNumber": 99,
            "productId": "MLB-129",
            "environmentType": "REFRIGERADOS",
            "dueDate": "2021-11-29",
            "currentQuantity": 100
        },
        {
            "batchStockNumber": 99,
            "productId": "MLB-129",
            "environmentType": "REFRIGERADOS",
            "dueDate": "2021-11-29",
            "currentQuantity": 100
        },
        {
            "batchStockNumber": 99,
            "productId": "MLB-129",
            "environmentType": "REFRIGERADOS",
            "dueDate": "2021-11-29",
            "currentQuantity": 100
        }
    ]
}
```
