### Todo
- [ ] Create infrastructure with all service skeletons for dev environment
    - [ ] Carrier Simulation
        - [ ] Create Akka actor to read from leg database for a given carrier
        - [ ] Setup mongoDb as database for Carrier module
        - [ ] Create Rest Resource that can take GET /carrier/{name}/?startLoc=xxx&endLoc=yyy and return list of legs
        - [x] Create ETL for routes data and ingest into Mongo 
        - [x] Create Carrier Simulation Module
        - [X] Create leg information in database
    
    - [ ] Routing Service: `The` Routing service chooses an itinerary with a minimum total magnitude of the legs 
          based on the chosen Strategy. Calls carrier to get legs and price for the whole route.
          API to accept start and end location with strategy and return list of options for user from different carriers.
          - [ ] Given start and end locations, make routing service return options from a single carrier
          - [ ] Can we get test data for leg information?
          - [ ] Configure a mock carrier.
          - [ ] Link Mongo Docker with routing
          - [ ] Given start and end locations, make routing service return options from multiple carrier
          - [ ] Change location to be of a domain object type.
          - [ ] Add carrier mocks  
          - [x] What is the strategy? Check DDD book.
          - [x] What data should be modeled to store leg information?
          - [x] What database to use to store this information?  
          - [x] Mongo Docker Setup
    
    - [ ] MongoDB basic tutorial
    - [ ] Actuator video on infoq
    
    - [ ] Normalize dependencies for all projects into dependencies.gradle
    - [ ] Externalize config in smoke test to property files.
    - [ ] Update infrastructure design with current state.
    - [ ] Add healthcheck to all containers (like config service)
    
### Backlog
- [ ] Security
    - [ ] Using asynchronous keys between security service and cargo
    - [ ] Using asynchronous keys between security service and routing 
    - [ ] Create a backend for security service. 
    - [ ] SSL communication between internal and external services
    - [ ] Certificate auth between services and Kafka
    - [ ] OAuth2 for customer registration
    - [ ] JWT for other services
    - [ ] Using Vault to access credentials/secrets
        - [ ] Create vault for storing credentials and secrets
        - [ ] Run vault as docker container
    - [ ] Encrypt github configuration on config server using vault
    - [ ] Authentication service to valid credentials with a database (For Basic Auth)

- [ ] Cargo
    - [ ] Book a cargo 

- [ ] Supporting Tasks
    
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
    - [ ] Customer
    - [ ] Audit
    - [ ] Carrier: Each of the below carriers available as separate micro services which provide different legs, duration of legs etc. and prices based on a given DeliverySpec (Can this be simplified to
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
* Drive everything via automation
* Monitoring to be done before pushing changes into production.

### Done
- [x] Programmatically get a token to be used.
- [x] Understand by running security service manually, how the security configuration 
        works and document it.
- [x] Add kafka message consumption to smoke test