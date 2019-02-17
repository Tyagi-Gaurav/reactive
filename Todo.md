### Todo
- [ ] Create infrastructure with all service skeletons for test environment
    - [ ] Invoke routing service using service discovery from cargo service.
    - [ ] Deploy the config service using docker-compose locally.
    - [ ] Deploy Kafka/Zookeeper stack using docker-compose locally
    - [ ] Deploy the cargo service using docker-compose locally.
    - [ ] Pipeline for project (build, test, tag, deploy)
    - [ ] Hystrix chapter and use hystrix for invoking downstreams
    - [ ] Create separate infrastructure project to run docker commands
    - [ ] Create a semi functional audit service that reads the event and prints it.
    - [ ] Creating build info for each microservice
    - [x] Create service discovery project using Spring cloud and Netflix Eureka
    - [x] Create a dummy routing service with a dummy method call
    - [x] Create a config server that can be used to retrieve
            config from a configuration repo. For now, use local
            file based repository.
    - [x] Versioning each project
    - [x] Create a semi functional Cargo service that accepts a request and
        returns a dummy response. Send kafka event
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

###Functional Requirements

* M1: Online Operations
    - [ ] R16: Each booking must be a secured booking so when a customer logs-in, they must be issued a token (Use JWT?)
    - [ ] R1: Book a Cargo
        - [ ] SR1.1: Failed payments for customers with pricing policy should be put in a retry queue.
    - [ ] R2: Cancel a cargo.
    - [ ] R3: What is the current status of my cargo?
    - [ ] R4: Which carriers were used for the cargo?
    - [ ] R5: Which cargo’s were being sent between 2 locations.
    - [ ] R7: Change destination of a booked cargo.
    - [ ] R10: Changing a clients pricing policy.
    - [ ] R11: For a given cargo, retrieve the detailed itinerary for it which highlights the different legs of the journey and for each leg, which carrier would be serving that.
    - [ ] R12: Send invoices to customers automatically when payment is made
    - [ ] R13: Register Customer via email
    - [ ] R14: Register Customer via Google Auth
    - [ ] R15: Register Customer via Facebook Auth
* Admin Operations
    - [ ] R6: Which cargo took longest to reach its destination?
    - [ ] R8: What was the total sale from booking cargo’s for a given time frame?
    - [ ] R9: Which clients have booked the most number of cargos?
* S5: Async Operations
    - [ ] R1: Taking payment for cargo’s using queuing.
    - [ ] R2: Receiving key events for a cargo from different carriers about their leg journeys, loading/unloading and delivery etc.
    - [ ] R3: Calculating quotes from various providers to get the best routes. (Routing Service)
* S6: Non-Functional requirements
    * R3: TPS for each endpoint.
        * Booking: 100 TPS
        * Cancellation: 10 TPS
        * Status: 300 TPS
    * R1: System Metrics (To be published using some tool)
        - [ ] SR1: CPU Usage
        - [ ] SR2: Memory Usage
        - [ ] SR3: Thread Pool Usage
        - [ ] SR4: Circuit breaker metrics
        - [ ] SR5: Open File Handles
        - [ ] SR6: Disk Usage
        - [ ] SR7: JVM Stats
            - [ ] SR8: GC Pauses
            - [ ] SR9: Heap Usa
    * R2: Business Analytics (Using Kafka for last 30 days -- See kafka streams aggregation/hopping window)
        - [ ] R1: Most booked destinations.
        - [ ] R2: Daily/Monthly booked cargo.
        - [ ] R3: Number of Failed payments.
        - [ ] R4: Number of bookings
        - [ ] R5: Number of cancellations
* S7: Throttling
    - [ ] R1: Throttle get status call based on pricing policy of the customer.

### Done
- [x] Failure of findBugs should fail the task
- [x] Default run of gradlew should run compile, build and test

### Development Principles
* Use Clean code.
* Nothing goes into production until it is functionally and non-functionally(selected endpoints) tested.
* No manual changes.
* Monitoring to be done before pushing changes into production.