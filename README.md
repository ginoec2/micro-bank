# micro-bank

Money transfer between account restful api

## Brief

This is an oversimplified money transfer rest api implementation based on Java EE 8.

## Tech Stack

- JDK 1.8
- Java EE 8
- Maven 3.X
- Payara Micro
- Docker

## Run time

- This rest api uses port 8080, i.e. http://localhost:8080/micro-bank

## Rest API Endopints

- GET	/micro-bank/api/v1/accounts
- GET	/micro-bank/api/v1/accounts/{accountNumber}
- GET	/micro-bank/api/v1/transfers
- POST	/micro-bank/api/v1/transfers

  Request payload:

  {
    "fromAccountNumber": "123456",
    "toAccountNumber": "100000",
    "amount": 100,
    "message": "this is a test"
  }

- GET	/micro-bank/api/v1/transfers/{id}

## Accounts available

- "accountNumber": "123456"
- "accountNumber": "987654"
- "accountNumber": "837465"
- "accountNumber": "100000"

## How to compile and install

- mvn clean install

## How to start the api

- mvn payara-micro:start

## How to stop

- cmd+C for Mac/Linux, or
- mvn payara-micro:stop

## Docker (optional)

### how to build docker image

- docker build -t micro-bank:1.0 .

### how to run docker image created

- docker run -d -p 8080:8080  micro-bank:1.0
