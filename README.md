# money-transfer-application
This is an application to simulate money transfer between two parties. This is built using jersey, jax-rs, jetty.
Gradle is used for dependency management. 
Java version- 1.8
jersey- 2.27
jetty- 9.4.6

## High-level Components-
There are three components or layers in the application.
Most exrternal component is ApiResource.java. This is used for defining the api paths and taking inputs from user or other services
Middle layer is the service layer for handling all the business logics. E.g.- <name service clsases>.
A repository is defined as inner most layer to represent data store. Ideally this would be communicating to database, but for our application it uses in-memory hashmap data structures.

## Security-
Here random Integer within certain predefined ranges are used for userId, accountId, transactionId. Ranges are kept as configurable constants.

## Steps to run application-
./gradlew clean build
java -jar build/libs/moneyTransferProject-1.0-SNAPSHOT.jar

## Exposed APIs-
1. `/create-user` : Sample-
```
POST /api/transfer/money/create-user HTTP/1.1
Host: localhost:8080
Content-Type: application/json
{"name":"a",	"emailId":"a@gmail.com", "phoneNumber":"1234567890"}
```
Output-
```
{"userId": 78}
```
2. `/deposit-money` :  Sample-
```
POST /api/transfer/money/deposit-money HTTP/1.1
Host: localhost:8080
Content-Type: application/json
{"amount":"100.0", "userId":"78", "description":"test deposit 1"}
```
Output-
```
{"transactionId": 691497}
```
3. `/transfer-money` : Sample-
```
POST /api/transfer/money/transfer HTTP/1.1
Host: localhost:8080
Content-Type: application/json
{"fromUserId":"78",	"toUserId":"67", "amount":"10.0", "description":"test transfer"}
```
Output-
```
{"transactionId": 148981}
```
4. `/get-balance` : Sample-
```
GET /api/transfer/money/get-balance?userId=78 HTTP/1.1
Host: localhost:8080
Content-Type: application/json
```
Output-
```
{"balance": 90}
```
