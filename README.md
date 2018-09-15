# mytaxi backend applicant test

## Task Description
You should be able to start the example application by executing com.mytaxi.MytaxiServerApplicantTestApplication, which starts a webserver on port 8080 (http://localhost:8080) and serves SwaggerUI where can inspect and try existing endpoints.

The project is based on a small web service which uses the following technologies:

* Java 1.8
* Spring MVC with Spring Boot
* Database H2 (In-Memory)
* Maven
* Intellij as IDE is preferred but not mandatory. We do provide code formatter for intellij and eclipse in the etc folder.


You should be aware of the following conventions while you are working on this exercise:

 * All new entities should have an ID with type of Long and a date_created with type of ZonedDateTime.
 * The architecture of the web service is built with the following components:
 	* DataTransferObjects: Objects which are used for outside communication via the API
   * Controller: Implements the processing logic of the web service, parsing of parameters and validation of in- and outputs.
   * Service: Implements the business logic and handles the access to the DataAccessObjects.
   * DataAccessObjects: Interface for the database. Inserts, updates, deletes and reads objects from the database.
   * DomainObjects: Functional Objects which might be persisted in the database.
 * TestDrivenDevelopment is a good choice, but it's up to you how you are testing your code.

You should commit into your local git repository and include the commit history into the final result.

---


## Task 1
 * Write a new Controller for maintaining cars (CRUD).
   * Decide on your own how the methods should look like.
   * Entity Car: Should have at least the following characteristics: license_plate, seat_count, convertible, rating, engine_type (electric, gas, ...)
   * Entity Manufacturer: Decide on your own if you will use a new table or just a string column in the car table.
 * Extend the DriverController to enable drivers to select a car they are driving with.
 * Extend the DriverController to enable drivers to deselect a car.
 * Extend the DriverDo to map the selected car to the driver.
 * Add example data to resources/data.sql

Ans: 
* CarController has all CRUD operations
* DriverController is extended with additional functionalities like selectCar, deSelectCar.
* DriverDo is mapped with selected car id. 
* Added example data to data.sql. Schema related infomation is in schema.aql
---


## Task 2
First come first serve: A car can be selected by exactly one ONLINE Driver. If a second driver tries to select a already used car you should throw a CarAlreadyInUseException.

Ans:
DriverController.selectCar and DriverController.deSelectCar implementation has this feature.
---


## Task 3
Imagine a driver management frontend that is used internally by mytaxi employees to create and edit driver related data. For a new search functionality, we need an endpoint to search for drivers. It should be possible to search for drivers by their attributes (username, online_status) as well as car characteristics (license plate, rating, etc).

* implement a new endpoint for searching or extend an existing one
* driver/car attributes as input parameters
* return list of drivers

Ans:
* DriverController.filterDrivers has this feature implemented.

---


## Task 4 (optional)
This task is _voluntarily_, if you can't get enough of hacking tech challenges, implement security.
Secure the API so that authentication is needed to access it. The details are up to you.

Please include instructions how to authenticate/login, so that we can test the endpoints you implemented!

Ans:
* Following URL is required to generate the token:
Step 1: 
Copy the generated security password from spring boot log during bootup time:
ex:
Using generated security password: 63958fb1-e000-4926-a51b-54e2c43a4fe5

Step 2: Generate the Authorization header using Postman

URL: http://localhost:8080/oauth/token
Authorization tab: Type=Basic Auth, Username: client, Password: secret

Click on "Update Request"

Step 3: 
 
curl -X POST --user 'client:secret' -d 'grant_type=password&username=user&password={generated password}' http://localhost:8080/oauth/token

Step 4
Access the API's using access token:
http://localhost:8080/v1/drivers/1?access_token=54c4093a-8599-4200-a55e-4808e3261a71

Note: evey resource must be accessed by providing parameter value in "access_token"
---


Good luck!
❤️ mytaxi



_NOTE: Please make sure to not submit any personal data with your tests result. Personal data is for example your name, your birth date, email address etc._