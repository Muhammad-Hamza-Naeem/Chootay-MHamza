# Chootay-MHamza

A management system for a bike shop, including the spare-parts and repairing. APIs for the spare-part, bikes, customers, admins and receipts(sales) are designed including all CRUD operations. Project is built in Spring Boot including Spring-Security and JWT-token for authentication. MySQL is used as a database to store data.

## Configuration 
Application.properties file

spring.jpa.properties.hibernate.dialect = org.hibernate.dialect.MySQL5Dialect

spring.jpa.hibernate.show-sql=true
spring.datasource.initialization-mode=always
spring.datasource.initialize=true
spring.datasource.continue-on-error=true

server.port=8081
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/chootay?serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=

jwt.secret = "chootaywebapplication"

## Steps for Backend:

1) After cloning the project, you need to build the project as Spring Boot App. With command mvn spring-boot:run
2) You can verify the APIs on http://localhost:8081/createAdmin .

## NOTE:
You will need Java 8, spring boot, maven and MySQL to build the project without errors.
