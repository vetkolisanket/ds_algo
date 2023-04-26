# Food Delivery App

## Design a food delivery app like zomato or swiggy

### Requirement Gathering

#### Functional requirements
- Show a list of nearby restaurants
- Show a restaurants details (opening-closing time, dishes and their info)
- Add dishes to cart
- Cart page
- Order tracking page

#### Non-functional requirements
- Should support lower-end devices
- Should work in flaky internet conditions

#### Out of scope
- Authentication
- Highlighted/Promoted restaurants
- Discounts/Offers

### High-level diagram

![Food delivery app high-level diagram](../images/food-delivery-app.svg)

### Flow
- The backend component is responsible for responding to API service component and returning appropriate response. The main API calls would be: 
    - get restaurant list `GET /restaurants?lat=<float>&&long=<float>` which will return a list of restaurants for the given lat long basis proximity of the restaurant to the lat long, whether the restaurant is serviceable or not right now and other user specific factors
    - get restaurant details `GET /restaurant/<id>` which will return detailed information related to the restaurant with id `<id>`. It will contain information like opening and closing time of the restaurant, whether it is serviceable right now, address, service/delivery charges, list of dishes, list of offers etc.
    - add to cart `POST/PUT /cart` which will create/update cart to include the dishes mentioned in the body to be included in users cart
    - place order `POST /order` which will create/place an order of the things currently in users cart
    - get order details `GET /order?orderId=<id>` which will return information related to the order with id <id>, this will contain information like the status of the order which can be `PROCESSING`, `OUT FOR DELIVERY`, `DELIVERED` etc

### Components



![Food delivery app](../images/food-ordering-app.png)
