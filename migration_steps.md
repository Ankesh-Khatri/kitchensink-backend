
# Migration Steps

1. Create a Spring Boot application with the required starters and other dependencies.
2. Create a base standard skeleton.
3. Perform backend migration using API-first development by following these steps
   
   3.1 Install the OpenAPI plugin:  
   ```bash
   npm install @openapitools/openapi-generator-cli -g
   ```
   3.2 Define the API specification in `openapi.yml`.  
   3.3 Generate server stubs:  
   ```bash
   openapi-generator-cli generate -i openapi.yml -g spring -o ../../../target/
   ```
   3.4 Integrate the generated code.  
   3.5 Implement business logic.  
   3.6 Configure application properties.  
   3.7 Write unit test cases.  
   3.8 Implement Swagger UI documentation configuration.

4. Create a React.js application:  
   4.1 Use MUI to create page-wise components with validations.  
   4.2 Integrate REST APIs.

## To Start the Applications:

1. **Spring Boot Application:**
   
   1.1 Run:  
   ```bash
   mvn clean install
   ```  
   1.2 (Optional) Run tests:  
   ```bash
   mvn test
   ```  
   1.3 Run the application:  
   ```bash
   mvn spring-boot:run
   ```  
   1.4 Access the API documentation at:  
   [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)


2. **React.js Application:**
   
   2.1 Install dependencies:  
   ```bash
   npm install
   ```  
   2.2 Start the application:  
   ```bash
   npm start
   ```  
   2.3 Access the application at:  
   [http://localhost:3000](http://localhost:3000)
