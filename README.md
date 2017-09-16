# General Online Store

#### Local Deployment

- Install Local MongoDB
- Install Mongo-instance from https://github.com/mbagusprasojo/mongo-instance (mvn clean install)
- mvn clean compile
- Just run Main class

#### LIST API

##### PUT /product
Data Format : Json
````
{
    "code" : [String],
    "name" : [String],
    "desc" : [String],
    "quantity" : [INT],
    "price" : [INT]
}
````

##### GET /product
Return all product

##### PUT /coupon
Data Format : Json
````
{
    "code" : [String],
    "desc" : [String],
    "quantity" : [INT],
    "startDate" : [TIMESTAMP],
    "endDate" : [TIMESTAMP],
    "discountNominal" : [INT],
    "discountPercentage" : [INT]
}
````
##### GET /coupon
Return all coupon
 
##### PUT /order/product
Add Product to Order : Data Format
````
{
    "id" : [String]/[Can be blank],
    "product" : [{
        "id" : [String]
        "code" : [String],
        "quantity" : [INT]
    }]
}
````

##### DELETE /order/product/{orderId}/{productId}
Remove certain product from order

##### PUT /order/coupon
Add Coupon to Order : Data Format
````
{
    "id" : [String]/[Can be blank],
    "coupon" : {
        "code" : [String]
    }
}
````

##### DELETE /order/coupon/{orderId}
Remove coupon from order

##### GET /order/{orderId}
Return details of order

##### GET /order/status/{orderId}
Return status of order
