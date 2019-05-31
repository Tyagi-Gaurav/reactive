### Todo
- [ ] Create infrastructure with all service skeletons for dev environment
    - [ ] Write a end to end smoke test to verify that everything is working
    - [ ] SSL communication between internal and external services
    - [ ] Certificate auth between services and Kafka
    
### Backlog
- [ ] Supporting Tasks
    - [ ] Draw intended infrastructure on paper.
    - [ ] Update Readme with system design, goals, requirements etc.
    - [ ] Specify host of config service in eureka as environment variable.
    - [ ] When a jar is built on Travis, the classifier should be current tag.
    - [ ] Exclude test in buildImageDependencies
    - [ ] Move common code out into libraries. Eg. Hystrix, Filters.
    - [ ] Write a gradle task that starts up the application locally for test
     - [ ] Booking call returns relevant details
     - [ ] Config service has config for all services
    - [ ] Add Request ID to MDC and make it available in logs.
    - [ ] Add span ID for services
    - [ ] Add Application name, requestID and spanID available to logs.
    - [ ] Update infrastructure diagram with nodes of Service Gateway
    - [ ] Pipeline for project (build, test, tag, release, deploy to docker)
    - [ ] Create a semi functional audit service that reads the event and prints it.
    - [ ] Send to kafka to be wrapped in Hystrix
    - [ ] Creating build info for each microservice (/actuator/info)
- [ ] How should the token refresh happen?
- [ ] Healthcheck of all services
- [ ] Missing Tests
    - [ ] Cargo Resource
    - [ ] Routing Resource
    - [ ] Cargo Hystrix
    - [ ] Service Discovery
    - [ ] Config Service
    - [ ] Smoke tests for config service whether it can read config from github
- [ ] Encrypt properties for database passwords (See Spring cloud Config encryption)
- [ ] Expose right level of detail in /env endpoint
- [ ] Service Discovery
- [ ] Distributed Tracing
- [ ] Continuous Integration
- [ ] JWT Tokens and OAuth2
- [ ] Metrics
- [ ] Alerting
- [ ] Micro Service(s):
    - [ ] Routing Service: The Routing service chooses an itinerary with a minimum total magnitude of the Legs based on the chosen Strategy. Calls carrier to get legs and price for the whole route.
    - [ ] Customer
    - [ ] Cargo
    - [ ] Audit
    - [ ] Carrier: Each of the below carriers available as separate microservices which provide different legs, duration of legs etc. and prices based on a given DeliverySpec (Can this be simplified to
        - [ ] CarrierA:
        - [ ] CarrierB:
        - [ ] CarrierC:
    - [ ] Location
    - [ ] MetricsManager
        - [ ] Look for open source metrics manager (Prometheus?)
    - [ ] CarrierEvent: Receives events for different carriers and updates the cargo
    - [ ] SystemSimulator:
        - [ ] Generates traffic for system that performs different transactions
            - [ ] Booking with valid data
            - [ ] Booking with invalid data
            - [ ] Status calls
            - [ ] Cancel transactions
            - [ ] Change destination of booked cargo
        - [ ] Receives events from kafka for different cargo booked (along with legs booked) and based on schedule, generates events for them.
            - [ ] Events that show cargo running on time.
            - [ ] Delay for leg journeys
- [ ] Security
    - [ ] OAuth2 for customer registration
    - [ ] JWT for other services
    - [ ] Using Vault to access credentials/secrets
        - [ ] Create vault for storing credentials and secrets
        - [ ] Run vault as docker container
    - [ ] Encrypt github configuration on config server using vault
    - [ ] Authentication service to valid credentials with a database (For Basic Auth)
- [ ] Move common gradle code for modules into super gradle
    - [ ] Use environment variables in application.yaml
    - [ ] Add environment variables for docker


#### Tech Tasks (Gradle)
- [ ] Gradle all versions to be maintained in a single place
- [ ] Any warnings should fail the build

### Development Principles
* Use Clean code.
* Nothing goes into production until it is functionally and non-functionally(selected endpoints) tested.
* No manual changes.
* Monitoring to be done before pushing changes into production.