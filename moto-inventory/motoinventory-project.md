# MotoInventory Project

This project is a simple system that allows customers to purchase motorcycles from inventory and allows staff to manage inventory entries. This system is similar to one that you built earlier in the course. This system consists of the following:

1. A service that sits in front of the MotoInventory database and provides CRUD access to the data.
2. An edge service that provides an API that allows customers to search inventory, purchase a motorcycle, contains some business logic that processes motorcycle sales, and that communicates with the CRUD service via a Feign client.
3. A Eureka Service Registry. The CRUD service must register with the Eureka service and the edge service must use the Eureka service to locate the CRUD service.

## Requirements

### CRUD Service

The CRUD microservice must provide create, read, update, and delete functionality for Motorcycle entries in the backing database. You must design and implement the REST API that provides these services.

### Edge Service

* The edge microservice must provide an endpoint that allows customers to see the motorcycles currently in inventory. You must design and implement this endpoint.
* The edge microservice must provide an endpoint that allows customers to purchase a motorcycle. This endpoint takes in all the information about the motorcycle being purchased and returns the motorcycle  information along with information about sales tax, documentation fees, transportation costs, and total cost of the motorcycle. This returned data is not stored in the database.  You must design and implement this endpoint.
* The edge microservice must enforce the following business rules in the service layer:
  * Sales tax is 6.75% and is added to the purchase price of the motorcycle.
  * Document fees are $234. These are added to the purchase price of the motorcyle but are not subject to sales tax.
  * Transportation costs are $395 for motorcycles costing less than \$9999 and \$499 for motorcycles costing more than \$9999.

### Architecture

- The system must incorporate and use the Eureka service registry.
- The edge service must use Feign to talk to the CRUD microservice.
- We highly encourage you to use JPA for database interaction in the CRUD microservice.

### TDD

- Follow TDD when building this project.
- This includes using MockMvc to test all of the endpoint of both microservices.
- This includes JUnit and Mockito for service layer and DAO tests.

## Database

```sql
create schema if not exists moto_inventory;
use moto_inventory;

create table if not exists motorcycle (
	  id int not null auto_increment primary key,
    price decimal(7,2) not null,
    vin varchar(20) not null,
    make varchar(20) not null,
    model varchar(20) not null,
    year varchar(4) not null,
    color varchar(20) not null
);
```

