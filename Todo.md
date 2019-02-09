### Todo
- [ ] Remove deprecated warning on functional tests
- [ ] Create infrastructure for test environment
    - [ ] Create a semi functional Cargo service that accepts a request and
    returns a dummy response. Send kafka event
    - [ ] Create a semi functional audit service that reads the event and prints it.
    - [ ] Deploy the cargo service using docker locally.
    - [ ] Kafka/Zookeeper stack
- [ ] Service Discovery
- [ ] Service Gateway
- [ ] Distributed Tracing
- [ ] Continuous Integration
- [ ] JWT Tokens and OAuth2
- [ ] Service Gateway??
- [ ] Service Discovery
- [ ] Metrics
- [ ] Alerting
- [ ] Micros Service(s):
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
        - [ ] Receives events from kafka for different cargo booked (along with legs booked) and based on schedule, generates events for them.

### Done
- [x] Failure of findBugs should fail the task
- [x] Default run of gradlew should run compile, build and test