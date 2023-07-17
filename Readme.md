# Vehicle Insurance Quote API

This project implements a REST API for generating personalized vehicle insurance quotes. It consists of a Spring Boot backend that calculates premiums based on customer and vehicle attributes. 

## Overview

The core functionality is implemented as a Spring Boot REST API. It exposes an endpoint that accepts user inputs like age, driving history etc. as JSON. These are used to calculate an insurance premium quote tailored to the customer. 

The business logic leverages actuarial techniques and predefined rating factors to determine the final premium amount. Some key technical capabilities:

- Retrieves base premium rate from a remote API 
- Applies calculations for deriving personalized quote based on attributes
- Generates a unique reference number for each quote

The API response contains the quote details including the premium amount and reference ID.

## Local Deployment

Prerequisites:
- Java 17+
- Maven
- Docker

```bash
# Build JAR file
mvn clean install
mvn package

# Build Docker image 
docker build -t insurance-api .

# Run container on port 8080 
docker run -p 8080:8080 insurance-api
```

The API will now be available on `http://localhost:8080`. It can be tested via Postman or cURL.

### Example
The API can be tested in Postman

```

Method: POST
api_url: http://localhost:8080/api/v1/insurance/calculate
requestBody: {
    "age": 22, 
    "drivingExperience": 2, 
    "driverRecord": 2, 
    "claims": 2, 
    "carValue": 200000,
    "annualMileage": 20000,
    "insuranceHistory": 2,
    "carAge": 2
}
```
## Challenges and Learnings

Notable challenges and learnings from this project:

- **CORS Issues** - Enabling cross-origin resource sharing (CORS) for the API posed an unexpected challenge during development. Despite configuring CORS at multiple levels - in the Spring Boot application code, EC2 security groups, and API Gateway - I was still encountering access issues from the frontend.

    Troubleshooting CORS requires carefully inspecting the browser requests/responses to understand where the preflight check is failing. Though I was unable to fully resolve the root cause within the timeline, debugging tricky CORS problems is great learning. I plan to continue researching the various CORS authentication mechanisms and server-side configurations to better understand how to secure cross-origin APIs. This will be invaluable knowledge for building robust and scalable REST interfaces.

- **Deployment Strategies** - Whilst attempting to deploy a Java application on Amazon Web Services' EC2 and configuring Nginx as a reverse proxy, I encountered an issue whereby an HTTP URL was being generated instead of an HTTPS URL upon attaching an Elastic IP to my EC2 instance. Due to my lack of ownership of a domain name, I was unable to insert an SSL certificate. In order to resolve this issue, I devised a solution involving the use of AWS Lambda to forward all incoming requests to my EC2 instance.

Overall this project provided good exposure to:
- Building RESTful web services with Spring Boot
- Implementing containerized microservices
- Designing secure and scalable API backends
- Optimizing cloud infrastructure for API workloads

## Live API

The API is deployed to AWS and can be accessed at:

https://api.insurance.com/v1/quote

Please feel free to explore and provide suggestions to improve the API design and implementation further.