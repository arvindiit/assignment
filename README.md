## Setup guide

#### Minimum Requirements

- Java 17
- Maven 3.x

Make sure you have java 17 and [Maven](https://maven.apache.org) installed

### Building and running the application

#####Command line in the source code folder

Build project

  ```
  $ mvn package
  ```
Run the tests
  ```
  $ mvn test
  ```

Go to target folder run the project

  ```
  $ java -jar assignment-0.0.1-SNAPSHOT.jar
  ```
#####Docker

docker build -t {tag} .

docker run -p {port}:{port} {tag}

#####Alternatively, it can be build and run within IDE like intellij


-----------------------------------------
## My solution
I've tried to make it as much production ready as I could.

I've made a simple UI to test the functionality.

Solution is spring MVC based solution so that it can be tested with in-built simple UI.

It can be easily converted to REST API solution with change the controller to Rest controller and return json object from endpoints.

I've added some error handling, and it can be improved further

I've decided to use simple relational db called H2 and made the relations between Customer and Account as one-to-many relationship and Account and Transaction also with one-to-many relationship.

Finally, I've tried to cover as much as test cases I could, I might have added more, but I needed to finish the task for today.


## Completeness
Solution is complete in itself.

All the requirement given in assignment are satisfied.

### Testing

UI can be loaded with url http://localhost:8080/

All the cases can be tested using the UI. No need of any other tool for testing the solution.

## Scope of improvements
Pagination and sorting can be implemented using PagingAndSortingRepository

Exception handling can be improved further

For fields to query from, a generic query builder can be created to cover any/all fields.

Solution can be containerized with physical databases like mysql, postgres, Oracle etc.

### Help
Solution should build and run as expected. In case of any difficulties please reach out to me at avindiit@gmail.com