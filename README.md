### Test API
- Get all Product
```shell
curl -X GET "http://localhost:8080/product"
```

- Get by ProductID
```shell
curl -X GET "http://localhost:8080/product/1"
```

- Add product
```shell
curl -X POST  "http://localhost:8080/product" \
-H "Content-Type: application/json" \
-d '{"productName": "Ngoc Hung", "price": 100, "quantity": 50}'  
```