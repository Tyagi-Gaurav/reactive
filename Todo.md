### Todo
- [ ] Create infrastructure with all service skeletons for dev environment
    - [ ] Create local config server with file backends.
    - [ ] Deploy the config service using docker-compose local dev environment (Manual).
    - [ ] Update base image of Java to Java 11
    - [ ] Create vault for storing credentials and secrets
    - [ ] Run vault as docker container
    - [ ] Encrypt github configuration on config server using vault
    - [ ] Update Readme with system design, goals, requirements etc.
    - [ ] Access config of services from config service when it runs inside docker container
    - [ ] Deploy eureka into container. Access config service from eureka.
    - [ ] Specify host of config service in eureka as environment variable.
    - [ ] When a jar is built on Travis, the classifier should be current tag.
    - [ ] Exclude test in buildImageDependencies
    - [ ] Move common code out into libraries. Eg. Hystrix, Filters.
    - [ ] Write a gradle task that starts up the application locally for test
    - [ ] Write a single end to end test to verify that everything is working
     - [ ] Booking call returns relevant details
     - [ ] Config service has config for all services
    - [ ] Add Request ID to MDC and make it available in logs.
    - [ ] Add span ID for services
    - [ ] Add Application name, requestID and spanID available to logs.
    - [ ] Update infrastructure diagram with nodes of Service Gateway
    - [ ] Deploy the service discovery service using docker-compose local dev environment (Manual).
    - [ ] Deploy Kafka/Zookeeper stack using docker-compose local dev environment (Manual).
    - [ ] Deploy the routing service using docker-compose local dev environment (Manual).
    - [ ] Make routing service communicate with config service and service discovery (Manual).
    - [ ] Make routing service communicate with config service and service discovery (Manual).
    - [ ] Deploy the cargo service using docker-compose local dev environment (Manual).
    - [ ] Pipeline for project (build, test, tag, release, deploy to docker)
    - [ ] Create a semi functional audit service that reads the event and prints it.
    - [ ] Creating build info for each microservice (/actuator/info)
    - [ ] Send to kafka to be wrapped in Hystrix
    - [ ] SSL communication between internal and external services
    - [ ] Certificate auth between services and Kafka
    - [x] When a jar is built locally, the classifier on it should be DEV
    - [x] Change config service to use github for config
    - [x] Services Gateway chapter
    - [x] Zuul not accessing config service
    - [x] Hystrix chapter and use hystrix for invoking downstreams from cargo service
    - [x] Create separate infrastructure project to run docker commands
    - [x] Invoke routing service using service discovery from cargo service.
    - [x] Create service discovery project using Spring cloud and Netflix Eureka
    - [x] Create a dummy routing service with a dummy method call
    - [x] Create a config server that can be used to retrieve
            config from a configuration repo. For now, use local
            file based repository.
    - [x] Versioning each project
    - [x] Create a semi functional Cargo service that accepts a request and
        returns a dummy response. Send kafka event
- [ ] Using Vault to access credentials/secrets
- [ ] Security service to become authentication service
- [ ] Authentication service to valid credentials with a database (For Basic Auth)
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
- [ ] Service Gateway
- [ ] Distributed Tracing
- [ ] Continuous Integration
- [ ] JWT Tokens and OAuth2
- [ ] Service Gateway??
- [ ] Metrics
- [ ] Alerting
- [ ] Security
    - [ ] OAuth2 for customer registration
    - [ ] JWT for other services
- [ ] Micro Service(s):
    - [ ] Routing Service: The Routing service chooses an itinerary with a minimum total magnitude of the Legs based on the chosen Strategy. Calls carrier to get legs and price for the whole route.
    - [ ] Customer
    - [ ] Cargo
    - [ ] Audit
    - [ ] Carrier: Each of the below carriers available as separate microservices which provide different legs, duration of legs etc. and prices based on a given DeliverySpec (Can this be simplified to
        - [ ] CarrierA:
        - [ ] CarrierB:
        - [ ] CarrierC:
    - [ ] Service Discovery
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

### Backlog
#### Tech Tasks (Gradle)
- [ ] Gradle all versions to be maintained in a single place
- [ ] Any warnings should fail the build

### Done
- [x] Failure of findBugs should fail the task
- [x] Default run of gradlew should run compile, build and test

### Development Principles
* Use Clean code.
* Nothing goes into production until it is functionally and non-functionally(selected endpoints) tested.
* No manual changes.
* Monitoring to be done before pushing changes into production.