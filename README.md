### Test API
- Get all Product
```shell
curl -X GET "http://localhost:9090/product" \
-H "Authorization: Bearer YOUR_TOKEN"
```

- Get by ProductID
```shell
curl -X GET "http://localhost:9090/product/1" \
-H "Authorization: Bearer YOUR_TOKEN"
```

- Add product
```shell
curl -X POST  "http://localhost:9090/product" \
-H "Content-Type: application/json" \
-H "Authorization: Bearer YOUR_TOKEN" \
-d '{"productName": "Ngoc Hung", "price": 100, "quantity": 50}'  
```
- Reduce Quantity
```shell
curl -X PUT "http://localhost:9090/product/reduceQuantity/1?quantity=50"
```

- Test TOKEN
```shell
curl -H "Authorization: Bearer YOUR_TOKEN" -X GET https://localhost:9090/product
```